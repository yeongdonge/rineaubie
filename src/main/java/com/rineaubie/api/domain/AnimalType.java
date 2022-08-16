package com.rineaubie.api.domain;

import com.rineaubie.api.service.animal.AnimalService;
import com.rineaubie.api.service.animal.CatService;
import com.rineaubie.api.service.animal.DogService;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
public enum AnimalType {
    DOG(DogService.class),
    CAT(CatService.class);

    private final Class<? extends AnimalService> animalService;

    public AnimalService create() {
        try {
            return animalService.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
