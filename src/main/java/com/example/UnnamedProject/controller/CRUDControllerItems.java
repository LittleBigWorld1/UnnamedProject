package com.example.UnnamedProject.controller;

import com.example.UnnamedProject.model.LogEntry;
import com.example.UnnamedProject.model.enums.LogEntryType;
import com.example.UnnamedProject.model.enums.Status;
import com.example.UnnamedProject.model.items.LaptopItem;
import com.example.UnnamedProject.model.items.SmartphoneItem;
import com.example.UnnamedProject.model.things.*;
import com.example.UnnamedProject.service.LogEntryService;
import com.example.UnnamedProject.service.items.SmartphoneItemService;
import com.example.UnnamedProject.service.things.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.UnnamedProject.service.items.LaptopItemService;
import java.util.*;

@RestController
@RequestMapping("/crud")
public class CRUDControllerItems
{
    @Autowired
    private LaptopItemService laptopItemService;
    @Autowired
    private LaptopService laptopService;
    @Autowired
    private RAMService ramService;
    @Autowired
    private HDDService hddService;
    @Autowired
    private SSDService ssdService;
    @Autowired
    private SmartphoneItemService smartphoneItemService;
    @Autowired
    private SmartphoneService smartphoneService;
    @Autowired
    private SmartphoneChargerService smartphoneChargerService;
    @Autowired
    private LogEntryService logService;

    @PostMapping("/laptopItem/create")
    @ResponseBody
    public Map<Object,Object> laptopItemCreatePost(@RequestParam UUID laptopId,
                                       @RequestParam(required = false) UUID ramId,
                                       @RequestParam(required = false) UUID driveId,
                                       @RequestParam int price,
                                       @RequestParam int warrantyMonths) throws JsonProcessingException
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Laptop laptop=laptopService.findById(laptopId);
        if (laptop==null) return result;
        if (laptop.getStatus()==Status.SOLD) return result;
        if (ramId!=null)
        {
            RAM ram=ramService.findById(ramId);
            if (ram==null || ram.getStatus()==Status.SOLD) return result;
        }
        if (driveId!=null)
        {
            HDD hdd = hddService.findById(driveId);
            SSD ssd = ssdService.findById(driveId);
            if (hdd == null && ssd == null) return result;
            if (hdd != null && hdd.getStatus() == Status.SOLD) return result;
            if (ssd != null && ssd.getStatus() == Status.SOLD) return result;
        }
        LaptopItem laptopItem=new LaptopItem();
        laptopItem.setLaptopId(laptopId);
        laptopItem.setRamId(ramId);
        laptopItem.setDriveId(driveId);
        laptopItem.setPrice(price);
        laptopItem.setWarrantyMonths(warrantyMonths);
        laptopItem=laptopItemService.save(laptopItem);
        if (laptopItem==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",laptopItem);
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setType(LogEntryType.CREATE);
        logEntry.setActionIds(List.of(laptopItem.getId()));
        Map<String,String> details=new HashMap<>();
        details.put("laptopItem",new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(laptopItem));
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
    @GetMapping("/laptopItem/read")
    @ResponseBody
    public Map<Object,Object> laptopItemReadGet(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<LaptopItem> allList=laptopItemService.findAll();
        List<LaptopItem> resultList=new ArrayList<>();
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
    @PostMapping("/laptopItem/update")
    @ResponseBody
    public Map<Object,Object> laptopItemUpdatePost(@RequestParam UUID id,
                                                   @RequestParam(required = false) UUID laptopId,
                                                   @RequestParam(required = false) UUID ramId,
                                                   @RequestParam(required = false) UUID driveId,
                                                   @RequestParam(required = false) Integer price,
                                                   @RequestParam(required = false) Integer warrantyMonths)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        LaptopItem laptopItem=laptopItemService.findById(id);
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setType(LogEntryType.UPDATE);
        logEntry.setActionIds(List.of(id));
        Map<String,String> details=new HashMap<>();
        if (laptopItem==null) return result;
        if (laptopId!=null)
        {
            Laptop laptop = laptopService.findById(laptopId);
            if (laptop == null) return result;
        }
        if (ramId!=null)
        {
            RAM ram = ramService.findById(ramId);
            if (ram == null) return result;
        }
        if (driveId!=null)
        {
            HDD hdd = hddService.findById(driveId);
            SSD ssd = ssdService.findById(driveId);
            if (hdd == null && ssd == null) return result;
        }
        if (laptopId!=null)
        {
            details.put("oldLaptopId",laptopItem.getLaptopId().toString());
            details.put("newLaptopId",laptopId.toString());
            laptopItem.setLaptopId(laptopId);
        }
        if (ramId!=null)
        {
            details.put("oldRamId",laptopItem.getRamId().toString());
            details.put("newRamId",ramId.toString());
            laptopItem.setRamId(ramId);
        }
        if (driveId!=null)
        {
            details.put("oldDriveId",laptopItem.getDriveId().toString());
            details.put("newDriveId",driveId.toString());
            laptopItem.setDriveId(driveId);
        }
        if (price!=null)
        {
            details.put("oldPrice",laptopItem.getPrice()+"");
            details.put("newPrice",price.toString());
            laptopItem.setPrice(price);
        }
        if (warrantyMonths!=null)
        {
            details.put("oldWarrantyMonths",laptopItem.getWarrantyMonths()+"");
            details.put("newWarrantyMonths",warrantyMonths.toString());
            laptopItem.setWarrantyMonths(warrantyMonths);
        }
        logEntry.setDetails(details);
        laptopItem=laptopItemService.update(laptopItem);
        logService.save(logEntry);
        if (laptopItem==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",laptopItem);
        return result;
    }
    @PostMapping("/laptopItem/delete")
    @ResponseBody
    public Map<Object,Object> laptopItemDeletePost(@RequestParam UUID id) throws JsonProcessingException
    {
        Map<Object,Object> result=new HashMap<>();
        LogEntry logEntry=new LogEntry();
        Map<String,String> details=new HashMap<>();
        result.put("result","error");
        if (!laptopItemService.existsById(id)) return result;
        details.put("laptopItem",new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(laptopItemService.findById(id)));
        laptopItemService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setType(LogEntryType.DELETE);
        logEntry.setActionIds(List.of(id));
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }




    @PostMapping("/smartphoneItem/create")
    @ResponseBody
    public Map<Object,Object> smartphoneItemCreatePost(@RequestParam UUID smartphoneId,
                                                   @RequestParam(required = false) UUID chargerId,
                                                   @RequestParam int price,
                                                   @RequestParam int warrantyMonths) throws JsonProcessingException
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Smartphone smartphone=smartphoneService.findById(smartphoneId);
        if (smartphone==null) return result;
        if (smartphone.getStatus()==Status.SOLD) return result;
        if (chargerId!=null)
        {
            SmartphoneCharger smartphoneCharger=smartphoneChargerService.findById(chargerId);
            if (smartphoneCharger==null || smartphoneCharger.getStatus()==Status.SOLD) return result;
        }
        SmartphoneItem smartphoneItem=new SmartphoneItem();
        smartphoneItem.setSmartphoneId(smartphoneId);
        smartphoneItem.setChargerId(chargerId);
        smartphoneItem.setPrice(price);
        smartphoneItem.setWarrantyMonths(warrantyMonths);
        smartphoneItem=smartphoneItemService.save(smartphoneItem);
        if (smartphoneItem==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",smartphoneItem);
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setType(LogEntryType.CREATE);
        logEntry.setActionIds(List.of(smartphoneItem.getId()));
        Map<String,String> details=new HashMap<>();
        details.put("smartphoneItem",new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(smartphoneItem));
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
    @GetMapping("/smartphoneItem/read")
    @ResponseBody
    public Map<Object,Object> smartphoneItemReadGet(@RequestParam(required = false) Integer page,
                                                @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<SmartphoneItem> allList=smartphoneItemService.findAll();
        List<SmartphoneItem> resultList=new ArrayList<>();
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
    @PostMapping("/smartphoneItem/update")
    @ResponseBody
    public Map<Object,Object> laptopItemUpdatePost(@RequestParam UUID id,
                                                   @RequestParam(required = false) UUID smartphoneId,
                                                   @RequestParam(required = false) UUID chargerId,
                                                   @RequestParam(required = false) Integer price,
                                                   @RequestParam(required = false) Integer warrantyMonths)
    {
        LogEntry logEntry=new LogEntry();
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setType(LogEntryType.UPDATE);
        logEntry.setActionIds(List.of(id));
        Map<String,String> details=new HashMap<>();
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        SmartphoneItem smartphoneItem=smartphoneItemService.findById(id);
        if (smartphoneItem==null) return result;
        if (smartphoneId!=null)
        {
            Smartphone smartphone = smartphoneService.findById(smartphoneId);
            if (smartphone == null) return result;
        }
        if (chargerId!=null)
        {
            SmartphoneCharger smartphoneCharger = smartphoneChargerService.findById(chargerId);
            if (smartphoneCharger == null) return result;
        }
        if (smartphoneId!=null)
        {
            details.put("oldSmartphoneId",smartphoneItem.getSmartphoneId().toString());
            details.put("newSmartphoneId",smartphoneId.toString());
            smartphoneItem.setSmartphoneId(smartphoneId);
        }
        if (chargerId!=null)
        {
            details.put("oldChargerId",smartphoneItem.getChargerId().toString());
            details.put("newChargerId",chargerId.toString());
            smartphoneItem.setSmartphoneId(smartphoneId);
        }
        if (price!=null)
        {
            details.put("oldPrice",smartphoneItem.getPrice()+"");
            details.put("newPrice",price.toString());
            smartphoneItem.setPrice(price);
        }
        if (warrantyMonths!=null)
        {
            details.put("oldWarrantyMonths",smartphoneItem.getWarrantyMonths()+"");
            details.put("newWarrantyMonths",warrantyMonths.toString());
            smartphoneItem.setWarrantyMonths(warrantyMonths);
        }
        smartphoneItem=smartphoneItemService.update(smartphoneItem);
        if (smartphoneItem==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",smartphoneItem);
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
    @PostMapping("/smartphoneItem/delete")
    @ResponseBody
    public Map<Object,Object> smartphoneItemDeletePost(@RequestParam UUID id) throws JsonProcessingException
    {
        LogEntry logEntry=new LogEntry();
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        if (!smartphoneItemService.existsById(id)) return result;
        Map<String,String> details=new HashMap<>();
        details.put("smartphoneItem",new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(smartphoneItemService.findById(id)));
        smartphoneItemService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        logEntry.setDate(System.currentTimeMillis());
        logEntry.setStaffId(UUID.randomUUID()); //TODO
        logEntry.setType(LogEntryType.DELETE);
        logEntry.setActionIds(List.of(id));
        logEntry.setDetails(details);
        logService.save(logEntry);
        return result;
    }
}