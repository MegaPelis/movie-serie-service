package com.megapelis.service.api.handler;

import com.google.gson.JsonObject;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.response.generic.Response;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

/**
 * Clase {@link MovieSerieHandler}
 * @author sergio.barrios.
 */
public abstract class MovieSerieHandler extends MovieSerieGenericCommon {

    /**
     * Metodo que permite ejecutar la logica del handler.
     * @param request
     * @return {@link Response}
     */
    public Response execute(Request request){
        Response response;
        try {
            Object object = validatePayload(request);
            Object objectAmazon = buildObjectAmazon(object);
            JsonObject jsonObject = execute(objectAmazon);
            response = MovieSerieCommon.buildResponse(request, MovieSerieStatusEnum.SUCCESS, jsonObject);
        } catch (MovieSerieException exception) {
            response = MovieSerieCommon.buildResponse(request, exception.getStatus());
        }
        return response;
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
    public abstract JsonObject execute(Object data) throws MovieSerieException;
}
