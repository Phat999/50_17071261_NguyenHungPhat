package com.example.cau1.service;

import com.example.cau1.entity.House;
import com.example.cau1.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;


    public House saveDepartment(House house) {
        log.info("inside saveDepartment of Department controller!");
        return  houseRepository.save(house);
    }


    public House findHouseById(Long houseId) {
        log.info("inside findDepartmentById of Department controller!");
        System.out.println("called findDepartmentById() from DB");
        return houseRepository.findById(houseId).get();
    }

    public List<House> getAllHouse() {
        System.out.println(houseRepository.findAll() + "la");
        return houseRepository.findAll();
    }

    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }
    @CachePut(value = "house", key ="id" )

    public void updateHouse(House house, Long id) {
        House houseTemp = houseRepository.findById(id).orElse(null);
        houseTemp.setName(house.getName());
        houseTemp.setPrice(house.getPrice());
        houseTemp.setDescription(house.getDescription());
        houseRepository.save(houseTemp);
    }
}
