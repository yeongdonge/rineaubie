package com.rineaubie.api.controller;

import com.rineaubie.api.domain.AnimalType;
import com.rineaubie.api.service.animal.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AnimalController {

//    private final List<AnimalService> animalServices;
//    private final AnimalServiceFinder animalServiceFinder;

    private final Map<String, AnimalService> animalServices;


    //Component List 주입
    // map (key:beanName, value:service)
    // enum

    @GetMapping("/sound")
    public String sound(@RequestParam String type) {
        log.info("animalServices={}", animalServices);

//        AnimalService service = animalServices.get(type.toLowerCase() + "Service");

        AnimalType animalType = AnimalType.valueOf(type.toUpperCase());
        AnimalService service = animalType.create();

//        AnimalService service = animalServiceFinder.findService(type);
        return service.getSound();
    }

}
