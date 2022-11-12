package com.megapelis.service.model.enums;

import com.megapelis.service.util.MovieSerieException;
import lombok.Getter;

import java.util.Arrays;

/**
 * Clase {@link MovieSerieOperationEnum}
 * @author sergio.barrios.
 */
@Getter
public enum MovieSerieOperationEnum {
    FIND_BY_ID("findById"),
    FIND_ALL("findAll"),
    SAVE("save"),
    UPDATE("update"),
    UPDATE_STATUS("updateStatus"),

    MOVIE("movie"),
    SERIE("serie");

    private String name;

    MovieSerieOperationEnum(String name){
        this.name = name;
    }

    /**
     * Metodo que permite validar si existe esa operación
     * @param name
     * @return {@link MovieSerieOperationEnum}
     * @throws MovieSerieException
     */
    public static MovieSerieOperationEnum isValid(String name) throws MovieSerieException {
        return Arrays.stream(MovieSerieOperationEnum.values())
                .filter(operationEnum -> operationEnum.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new MovieSerieException(MovieSerieStatusEnum.ERROR_SERVICE_OR_OPERATION));
    }
}
