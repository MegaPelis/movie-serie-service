package com.megapelis.service.model.entity;

import com.megapelis.service.util.MovieSerieException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MovieSerieStatusEnum {

    ACTIVE("activado"),
    DISABLE("desactivado");

    private String name;

    MovieSerieStatusEnum(String name){
        this.name = name;
    }

    public static MovieSerieStatusEnum isValid(String name) throws MovieSerieException {
        return Arrays.stream(MovieSerieStatusEnum.values())
                .filter(typeEnum -> typeEnum.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new MovieSerieException(com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_FORMAT_REQUEST));
    }
}
