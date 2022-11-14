package com.megapelis.service.model.entity;

import com.megapelis.service.model.enums.MovieSerieOperationEnum;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MovieSerieTypeEnum {

    SERIE("serie"),
    MOVIE("movie");

    private String name;

    MovieSerieTypeEnum(String name){
        this.name = name;
    }

    public static MovieSerieTypeEnum isValid(String name) throws MovieSerieException {
        return Arrays.stream(MovieSerieTypeEnum.values())
                .filter(typeEnum -> typeEnum.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new MovieSerieException(MovieSerieStatusEnum.ERROR_PROPERTY_VALUE));
    }
}
