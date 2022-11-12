package com.megapelis.service;

import com.megapelis.service.api.abstractfactory.MovieSerieFactory;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.response.generic.Response;

/**
 * Clase {@link App}
 * @author sergio.barrios.
 */
public class App {

    /**
     * Metodo que permite realizar el llamado de los servicios.
     * @param request
     * @return {@link Response}
     */
    public Response handler(Request request){
        return MovieSerieFactory.handler(request);
    }
}
