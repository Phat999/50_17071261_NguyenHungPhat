package com.example.cau1.controller;

import com.example.cau1.entity.House;
import com.example.cau1.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
@Slf4j
@EnableCaching
public class HouseController {
    @Autowired
    private HouseService houseService;

    @PostMapping
    @CacheEvict(value = "house",allEntries = true)
    public House saveDepartment(@RequestBody House house){
        log.info("inside saveDepartment method of Department controller!");
        return houseService.saveDepartment(house);
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#houseId",value = "house")
    public House findHouseById(@PathVariable("id") Long houseId){
        log.info("inside findHouseById method of Department controller!");
        return houseService.findHouseById(houseId);
    }
    @GetMapping("")
    public Object getAllHouse(){
        try {
            return new ResponseEntity<List<House>>(houseService.getAllHouse(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error get danh sách House!",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    @CacheEvict(value = "house",allEntries = true)

    public Object deleteHouseById(@PathVariable("id") Long id){
        try {
            houseService.deleteHouse(id);
            return new ResponseEntity<String>("Delete thành công!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<String>("Error delete!",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public Object updateHouseById(@RequestBody House houseId, @PathVariable("id") Long id){
        try{
            houseService.updateHouse(houseId, id);
            return new ResponseEntity<String>("Update thành công", HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<String>("Error update!",HttpStatus.BAD_REQUEST);
        }
    }
}
