package com.megapelis.service.util;

import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase {@link MovieSerieException}
 * @author sergio.barrios.
 */
@Getter
@Setter
public class MovieSerieException extends Exception{
    private MovieSerieStatusEnum status;

    public MovieSerieException(MovieSerieStatusEnum status){
        super(status.getMessageBackend());
        this.status = status;
    }
}
