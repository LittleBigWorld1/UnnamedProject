package com.example.UnnamedProject.controller;

import com.example.UnnamedProject.model.enums.ChargerPlugType;
import com.example.UnnamedProject.model.enums.DriveSlotType;
import com.example.UnnamedProject.model.enums.RAMType;
import com.example.UnnamedProject.model.enums.Status;
import com.example.UnnamedProject.model.items.LaptopItem;
import com.example.UnnamedProject.model.things.*;
import com.example.UnnamedProject.service.LogEntryService;
import com.example.UnnamedProject.service.things.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@RestController
@RequestMapping("/crud")
public class CRUDControllerThings
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
    private LogEntryService logService;
    //TODO logging

    @PostMapping("/hdd/create")
    @ResponseBody
    public Map<Object,Object> hddCreatePost(@RequestParam String brand,
                                            @RequestParam String name,
                                            @RequestParam DriveSlotType driveSlotType,
                                            @RequestParam int price,
                                            @RequestParam int volumeGB)
    {
        HDD hdd=new HDD();
        hdd.setBrand(brand);
        hdd.setName(name);
        hdd.setDriveSlotType(driveSlotType);
        hdd.setPrice(price);
        hdd.setVolumeGB(volumeGB);
        hdd.setStatus(Status.AVAILABLE);
        Map<Object,Object> result=new HashMap<>();
        hdd=hddService.save(hdd);
        if (hdd==null) result.put("result","error");
        else
        {
            result.put("result","success");
            result.put("object",hdd);
        }
        return result;
    }
    @GetMapping("/hdd/read")
    @ResponseBody
    public Map<Object,Object> hddReadGet(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<HDD> allList=hddService.findAll();
        List<HDD> resultList=new ArrayList<>();
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
    @PostMapping("/hdd/update")
    @ResponseBody
    public Map<Object,Object> hddUpdatePost(@RequestParam UUID id,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) DriveSlotType driveSlotType,
                                            @RequestParam(required = false) Integer price,
                                            @RequestParam(required = false) Integer volumeGB,
                                            @RequestParam(required = false) Status status)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        HDD hdd= hddService.findById(id);
        if (hdd==null) return result;
        if (brand!=null) hdd.setBrand(brand);
        if (name!=null) hdd.setName(name);
        if (driveSlotType!=null) hdd.setDriveSlotType(driveSlotType);
        if (price!=null) hdd.setPrice(price);
        if (volumeGB!=null) hdd.setVolumeGB(volumeGB);
        if (status!=null) hdd.setStatus(status);
        hdd=hddService.update(hdd);
        if (hdd==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",hdd);
        return result;
    }
    @PostMapping("/hdd/delete")
    @ResponseBody
    public Map<Object,Object> hddDeletePost(@RequestParam UUID id)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        HDD hdd=hddService.findById(id);
        if (hdd==null) return result;
        hddService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        return result;
    }



    @PostMapping("/laptop/create")
    @ResponseBody
    public Map<Object,Object> laptopCreatePost(@RequestParam String brand,
                                            @RequestParam String name,
                                            @RequestParam RAMType ramType,
                                            @RequestParam DriveSlotType driveSlotType,
                                            @RequestParam int price)
    {
        Laptop laptop=new Laptop();
        laptop.setBrand(brand);
        laptop.setName(name);
        laptop.setRamType(ramType);
        laptop.setDriveSlotType(driveSlotType);
        laptop.setPrice(price);
        laptop.setStatus(Status.AVAILABLE);
        Map<Object,Object> result=new HashMap<>();
        laptop=laptopService.save(laptop);
        if (laptop==null) result.put("result","error");
        else
        {
            result.put("result","success");
            result.put("object",laptop);
        }
        return result;
    }
    @GetMapping("/laptop/read")
    @ResponseBody
    public Map<Object,Object> laptopReadGet(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<Laptop> allList=laptopService.findAll();
        List<Laptop> resultList=new ArrayList<>();
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
    @PostMapping("/laptop/update")
    @ResponseBody
    public Map<Object,Object> laptopUpdatePost(@RequestParam UUID id,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) RAMType ramType,
                                            @RequestParam(required = false) DriveSlotType driveSlotType,
                                            @RequestParam(required = false) Integer price,
                                            @RequestParam(required = false) Status status)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Laptop laptop=laptopService.findById(id);
        if (laptop==null) return result;
        if (brand!=null) laptop.setBrand(brand);
        if (name!=null) laptop.setName(name);
        if (driveSlotType!=null) laptop.setDriveSlotType(driveSlotType);
        if (price!=null) laptop.setPrice(price);
        if (ramType!=null) laptop.setRamType(ramType);
        if (status!=null) laptop.setStatus(status);
        laptop=laptopService.update(laptop);
        if (laptop==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",laptop);
        return result;
    }
    @PostMapping("/laptop/delete")
    @ResponseBody
    public Map<Object,Object> laptopDeletePost(@RequestParam UUID id)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Laptop laptop=laptopService.findById(id);
        if (laptop==null) return result;
        laptopService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        return result;
    }




    @PostMapping("/ram/create")
    @ResponseBody
    public Map<Object,Object> ramCreatePost(@RequestParam String brand,
                                               @RequestParam String name,
                                               @RequestParam RAMType ramType,
                                               @RequestParam int volumeGB,
                                               @RequestParam int price)
    {
        RAM ram=new RAM();
        ram.setBrand(brand);
        ram.setName(name);
        ram.setRamType(ramType);
        ram.setVolumeGB(volumeGB);
        ram.setPrice(price);
        ram.setStatus(Status.AVAILABLE);
        Map<Object,Object> result=new HashMap<>();
        ram=ramService.save(ram);
        if (ram==null) result.put("result","error");
        else
        {
            result.put("result","success");
            result.put("object",ram);
        }
        return result;
    }
    @GetMapping("/ram/read")
    @ResponseBody
    public Map<Object,Object> ramReadGet(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<RAM> allList=ramService.findAll();
        List<RAM> resultList=new ArrayList<>();
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
    @PostMapping("/ram/update")
    @ResponseBody
    public Map<Object,Object> ramUpdatePost(@RequestParam UUID id,
                                               @RequestParam(required = false) String brand,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) RAMType ramType,
                                               @RequestParam(required = false) Integer price,
                                               @RequestParam(required = false) Integer volumeGB,
                                               @RequestParam(required = false) Status status)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        RAM ram=ramService.findById(id);
        if (ram==null) return result;
        if (brand!=null) ram.setBrand(brand);
        if (name!=null) ram.setName(name);
        if (price!=null) ram.setPrice(price);
        if (volumeGB!=null) ram.setVolumeGB(volumeGB);
        if (ramType!=null) ram.setRamType(ramType);
        if (status!=null) ram.setStatus(status);
        ram=ramService.update(ram);
        if (ram==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",ram);
        return result;
    }
    @PostMapping("/ram/delete")
    @ResponseBody
    public Map<Object,Object> ramDeletePost(@RequestParam UUID id)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        RAM ram=ramService.findById(id);
        if (ram==null) return result;
        ramService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        return result;
    }




    @PostMapping("/smartphone/create")
    @ResponseBody
    public Map<Object,Object> smartphoneCreatePost(@RequestParam String brand,
                                                   @RequestParam String name,
                                                   @RequestParam ChargerPlugType chargerPlugType,
                                                   @RequestParam int price,
                                                   @RequestParam int chargerVoltage)
    {
        Smartphone smartphone=new Smartphone();
        smartphone.setBrand(brand);
        smartphone.setName(name);
        smartphone.setChargerPlugType(chargerPlugType);
        smartphone.setPrice(price);
        smartphone.setChargerVoltage(chargerVoltage);
        smartphone.setStatus(Status.AVAILABLE);
        Map<Object,Object> result=new HashMap<>();
        smartphone=smartphoneService.save(smartphone);
        if (smartphone==null) result.put("result","error");
        else
        {
            result.put("result","success");
            result.put("object",smartphone);
        }
        return result;
    }
    @GetMapping("/smartphone/read")
    @ResponseBody
    public Map<Object,Object>smartphoneReadGet(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<Smartphone> allList=smartphoneService.findAll();
        List<Smartphone> resultList=new ArrayList<>();
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
    @PostMapping("/smartphone/update")
    @ResponseBody
    public Map<Object,Object> smartphoneUpdatePost(@RequestParam UUID id,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) ChargerPlugType chargerPlugType,
                                            @RequestParam(required = false) Integer price,
                                            @RequestParam(required = false) Integer chargerVoltage,
                                            @RequestParam(required = false) Status status)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Smartphone smartphone=smartphoneService.findById(id);
        if (smartphone==null) return result;
        if (brand!=null) smartphone.setBrand(brand);
        if (name!=null) smartphone.setName(name);
        if (price!=null) smartphone.setPrice(price);
        if (chargerPlugType!=null) smartphone.setChargerPlugType(chargerPlugType);
        if (chargerVoltage!=null) smartphone.setChargerVoltage(chargerVoltage);
        if (status!=null) smartphone.setStatus(status);
        smartphone=smartphoneService.update(smartphone);
        if (smartphone==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",smartphone);
        return result;
    }
    @PostMapping("/smartphone/delete")
    @ResponseBody
    public Map<Object,Object> smartphoneDeletePost(@RequestParam UUID id)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        Smartphone smartpnone=smartphoneService.findById(id);
        if (smartpnone==null) return result;
        smartphoneService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        return result;
    }




    @PostMapping("/smartphoneCharger/create")
    @ResponseBody
    public Map<Object,Object> smartphoneChargerCreatePost(@RequestParam String brand,
                                            @RequestParam String name,
                                            @RequestParam ChargerPlugType chargerPlugType,
                                            @RequestParam int price,
                                            @RequestParam int voltage)
    {
        SmartphoneCharger smartphoneCharger=new SmartphoneCharger();
        smartphoneCharger.setBrand(brand);
        smartphoneCharger.setName(name);
        smartphoneCharger.setChargerPlugType(chargerPlugType);
        smartphoneCharger.setPrice(price);
        smartphoneCharger.setVoltage(voltage);
        smartphoneCharger.setStatus(Status.AVAILABLE);
        Map<Object,Object> result=new HashMap<>();
        smartphoneCharger=smartphoneChargerService.save(smartphoneCharger);
        if (smartphoneCharger==null) result.put("result","error");
        else
        {
            result.put("result","success");
            result.put("object",smartphoneCharger);
        }
        return result;
    }
    @GetMapping("/smartphoneCharger/read")
    @ResponseBody
    public Map<Object,Object> smartphoneChargerReadGet(@RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<SmartphoneCharger> allList=smartphoneChargerService.findAll();
        List<SmartphoneCharger> resultList=new ArrayList<>();
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
    @PostMapping("/smartphoneCharger/update")
    @ResponseBody
    public Map<Object,Object> smartphoneChargerUpdatePost(@RequestParam UUID id,
                                            @RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) ChargerPlugType chargerPlugType,
                                            @RequestParam(required = false) Integer price,
                                            @RequestParam(required = false) Integer voltage,
                                            @RequestParam(required = false) Status status)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        SmartphoneCharger smartphoneCharger=smartphoneChargerService.findById(id);
        if (smartphoneCharger==null) return result;
        if (brand!=null) smartphoneCharger.setBrand(brand);
        if (name!=null) smartphoneCharger.setName(name);
        if (price!=null) smartphoneCharger.setPrice(price);
        if (voltage!=null) smartphoneCharger.setVoltage(voltage);
        if (chargerPlugType!=null) smartphoneCharger.setChargerPlugType(chargerPlugType);
        if (status!=null) smartphoneCharger.setStatus(status);
        smartphoneCharger=smartphoneChargerService.update(smartphoneCharger);
        if (smartphoneCharger==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",smartphoneCharger);
        return result;
    }
    @PostMapping("/smartphoneCharger/delete")
    @ResponseBody
    public Map<Object,Object> smartphoneChargerDeletePost(@RequestParam UUID id)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        SmartphoneCharger smartphoneCharger=smartphoneChargerService.findById(id);
        if (smartphoneCharger==null) return result;
        smartphoneChargerService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        return result;
    }



    @PostMapping("/ssd/create")
    @ResponseBody
    public Map<Object,Object> ssdCreatePost(@RequestParam String brand,
                                                          @RequestParam String name,
                                                          @RequestParam DriveSlotType driveSlotType,
                                                          @RequestParam int price,
                                                          @RequestParam int volumeGB)
    {
        SSD ssd=new SSD();
        ssd.setBrand(brand);
        ssd.setName(name);
        ssd.setDriveSlotType(driveSlotType);
        ssd.setPrice(price);
        ssd.setVolumeGB(volumeGB);
        ssd.setStatus(Status.AVAILABLE);
        Map<Object,Object> result=new HashMap<>();
        ssd=ssdService.save(ssd);
        if (ssd==null) result.put("result","error");
        else
        {
            result.put("result","success");
            result.put("object",ssd);
        }
        return result;
    }
    @GetMapping("/ssd/read")
    @ResponseBody
    public Map<Object,Object> ssdReadGet(@RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        List<SSD> allList=ssdService.findAll();
        List<SSD> resultList=new ArrayList<>();
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
    @PostMapping("/ssd/update")
    @ResponseBody
    public Map<Object,Object> ssdUpdatePost(@RequestParam UUID id,
                                                          @RequestParam(required = false) String brand,
                                                          @RequestParam(required = false) String name,
                                                          @RequestParam(required = false) DriveSlotType driveSlotType,
                                                          @RequestParam(required = false) Integer price,
                                                          @RequestParam(required = false) Integer volumeGB,
                                                          @RequestParam(required = false) Status status)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        SSD ssd=ssdService.findById(id);
        if (ssd==null) return result;
        if (brand!=null) ssd.setBrand(brand);
        if (name!=null) ssd.setName(name);
        if (price!=null) ssd.setPrice(price);
        if (volumeGB!=null) ssd.setVolumeGB(volumeGB);
        if (driveSlotType!=null) ssd.setDriveSlotType(driveSlotType);
        if (status!=null) ssd.setStatus(status);
        ssd=ssdService.update(ssd);
        if (ssd==null) return result;
        result=new HashMap<>();
        result.put("result","success");
        result.put("object",ssd);
        return result;
    }
    @PostMapping("/ssd/delete")
    @ResponseBody
    public Map<Object,Object> ssdDeletePost(@RequestParam UUID id)
    {
        Map<Object,Object> result=new HashMap<>();
        result.put("result","error");
        SSD ssd=ssdService.findById(id);
        if (ssd==null) return result;
        ssdService.deleteById(id);
        result=new HashMap<>();
        result.put("result","success");
        return result;
    }
}
