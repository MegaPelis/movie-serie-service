package com.megapelis.service.api.handler;

import com.google.gson.JsonObject;
import com.megapelis.service.api.amazon.AmazonDB;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.request.generic.RequestProperty;
import com.megapelis.service.model.dto.response.generic.Response;
import com.megapelis.service.model.entity.MovieSerieTypeEnum;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieConstant;
import com.megapelis.service.util.MovieSerieException;

/**
 * Clase {@link MovieSerieHandler}
 * @author sergio.barrios.
 */
public abstract class MovieSerieHandler {

    protected AmazonDB amazonDB;
    protected RequestProperty propertyService;

    public MovieSerieHandler(){
        this.amazonDB = new AmazonDB();
    }

    /**
     * Metodo que permite ejecutar la logica del handler.
     * @param request
     * @return {@link Response}
     */
    public Response execute(Request request){
        Response response;
        try {
            propertyService = MovieSerieCommon.getProperty(MovieSerieConstant.STRING_PROPERTY_SERVICE, request.getProperties());
            MovieSerieTypeEnum.isValid(propertyService.getValue());
            Object object = validatePayload(request);
            Object objectAmazon = buildObjectAmazon(object);
            Object dataResponse = execute(objectAmazon);
            response = MovieSerieCommon.buildResponse(request, MovieSerieStatusEnum.SUCCESS, dataResponse);
        } catch (MovieSerieException exception) {
            response = MovieSerieCommon.buildResponse(request, exception.getStatus());
        }
        return response;
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

    /**
     * Metodo que permite validar el payload.
     * @param request
     * @return {@link Object}
     * @throws MovieSerieException
     */
    public abstract Object validatePayload(Request request) throws MovieSerieException;

    /**
     * Metodo que permite construir el objeto de amazon.
     * @param data
     * @return {@link  Object}
     */
    public abstract Object buildObjectAmazon(Object data);

    /**
     * Metodo que permite construir la url del servicio.
     * @return {@link JsonObject}
     * @throws MovieSerieException
     */
    public abstract Object execute(Object data) throws MovieSerieException;
}
