package com.megapelis.service.api.handler;

import com.megapelis.service.api.amazon.AmazonDB;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

/**
 * Clase {@link MovieSerieGenericCommon}
 * @author sergio.barrios.
 */
public class MovieSerieGenericCommon {
    protected AmazonDB amazonDB;

    public MovieSerieGenericCommon(){
        this.amazonDB = new AmazonDB();
    }

    /**
     * Metodo que permite parsear el payload.
     * @param request
     * @param clazz
     * @return {@link T}
     * @param <T>
     * @throws MovieSerieException
     */
    protected <T> T convertPayload(Request request, Class<T> clazz) throws MovieSerieException {
        if(null == request || null == request.getData())
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
        return MovieSerieCommon.convertObjectToClass(request.getData(), clazz);
    }
}
