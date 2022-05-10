package com.example.UnnamedProject.controller;

import com.example.UnnamedProject.model.LogEntry;
import com.example.UnnamedProject.model.Order;
import com.example.UnnamedProject.model.enums.DriveSlotType;
import com.example.UnnamedProject.model.enums.LogEntryType;
import com.example.UnnamedProject.model.enums.Status;
import com.example.UnnamedProject.model.things.SSD;
import com.example.UnnamedProject.service.LogEntryService;
import com.example.UnnamedProject.service.OrderService;
import com.example.UnnamedProject.service.things.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.*;
import java.util.*;

@RestController
@RequestMapping("/crud")
public class CRUDController
{
    @Autowired
    private HDDService hddService;
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private RAMService ramService;
    @Autowired
    private SmartphoneService smartphoneService;
    @Autowired
    private SmartphoneChargerService smartphoneChargerService;
    @Autowired
    private SSDService ssdService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private LogEntryService logService;

    @PostMapping("/order/create")
    @ResponseBody
    public Map<Object,Object> orderCreatePost(@RequestParam UUID... itemIds) throws JsonProcessingException
    {
        List<UUID> list=new ArrayList<>(List.of(itemIds));
        boolean flag=true;
        for (UUID id : list)
        {
            if (!hddService.exists(id) || !laptopService.exists(id)
             || !ramService.exists(id) || !smartphoneChargerService.exists(id)
             || !smartphoneService.exists(id) || !ssdService.exists(id)) {flag=false; break;}
        }
        Map<Object,Object> result=new HashMap<>();
        flag=true;//TODO DEBUG
        if (!flag)
        {
            result.put("result","error");
            return result;
        }
        Order order=new Order();
        order.setItemIds(list);
        order.setDate(System.currentTimeMillis());
        order=orderService.save(order);
        if (order==null)
        {
            result.put("result","error");
            return result;
        }
        result.put("result","success");
        result.put("object",order);
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setType(LogEntryType.CREATE);
        logEntry.setStaffId(UUID.randomUUID());
        //TODO
        logEntry.setActionIds(List.of(order.getId()));
        Map<String,String> details=new HashMap<>();
        details.put("order",new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(order));
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
    @GetMapping("/order/read")
    @ResponseBody
    public Map<Object,Object> orderReadGet(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<Order> allList=orderService.findAll();
        List<Order> resultList=new ArrayList<>();
        if (page==null) page=1;
        if (size==null) size=10;
        for (int i=(page-1)*size; i<page*size; i++)
        {
            if (allList.size()-1<i) break;
            resultList.add(allList.get(i));
        }
        result=new HashMap<>();
        result.put("result","success");
        result.put("list",resultList);
        return result;
    }
    @PostMapping("/order/update")
    @ResponseBody
    public Map<Object,Object> orderUpdatePost(@RequestParam UUID id,
                                            @RequestParam(required = false) UUID itemIds,
                                            @RequestParam(required = false) Long date)
    {
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setType(LogEntryType.UPDATE);
        logEntry.setStaffId(UUID.randomUUID());
        //TODO
        logEntry.setActionIds(new ArrayList<>(List.of(id)));
        Map<String,String> details=new HashMap<>();
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Order order=orderService.findById(id);
        if (order==null) return result;
        if (itemIds!=null)
        {
            details.put("oldItemIds",order.getItemIds().toString());
            details.put("newItemIds",itemIds.toString());
            order.setItemIds(new ArrayList<>(List.of(itemIds)));
        }
        if (date!=null)
        {
            details.put("oldDate",order.getItemIds().toString());
            details.put("newDate",date.toString());
            order.setDate(date);
        }
        order=orderService.update(order);
        if (order==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",order);
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
    @PostMapping("/order/delete")
    @ResponseBody
    public Map<Object,Object> orderDeletePost(@RequestParam UUID id) throws JsonProcessingException
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Order order=orderService.findById(id);
        if (order==null) return result;
        orderService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setType(LogEntryType.DELETE);
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setActionIds(new ArrayList<>(List.of(id)));
        Map<String,String> details=new HashMap<>();
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
}
