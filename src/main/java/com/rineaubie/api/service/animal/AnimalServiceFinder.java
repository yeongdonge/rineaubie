package com.rineaubie.api.service.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimalServiceFinder {

    private final List<AnimalService> animalServices;

    public AnimalService findService(String type) {
        String s = type.toUpperCase();
        return animalServices.stream()
                .filter(animalService -> animalService.getType().name().equals(s))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }
}
