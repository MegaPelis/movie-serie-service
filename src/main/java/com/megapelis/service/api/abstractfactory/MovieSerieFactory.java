package com.megapelis.service.api.abstractfactory;

import com.megapelis.service.api.abstractfactory.factory.*;
import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.api.handler.impl.*;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.request.generic.RequestProperty;
import com.megapelis.service.model.dto.response.generic.Response;
import com.megapelis.service.model.enums.MovieSerieOperationEnum;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieConstant;
import com.megapelis.service.util.MovieSerieException;

/**
 * Clase {@link MovieSerieFactory}
 * @author sergio.barrios.
 */
public class MovieSerieFactory {

    private MovieSerieFactory(){
    }

    /**
     * Fabrica que permite ejecutar mediante la operación.
     * @param request
     * @return {@link Response}
     */
    public static Response handler(Request request) {
        MovieSerieCommon.output(request);
        Response response;
        try {
            MovieSerieCommon.isValidRequest(request);
            RequestProperty property = MovieSerieCommon.getProperty(MovieSerieConstant.STRING_PROPERTY_SERVICE, request.getProperties());
            switch (MovieSerieOperationEnum.isValid(request.getOperation())){
                case FIND_BY_ID:
                    response = FindByIdMovieSerieFactory.handler(request, property);
                    break;
                case FIND_ALL:
                    response = FindAllMovieSerieFactory.handler(request, property);
                    break;
                case SAVE:
                    response = SaveMovieSerieFactory.handler(request, property);
                    break;
                case UPDATE:
                    response = UpdateMovieSerieFactory.handler(request, property);
                    break;
                case UPDATE_STATUS:
                    response = UpdateStatusMovieSerieFactory.handler(request, property);
                    break;
                default:
                    response =  MovieSerieCommon.buildResponse(request, MovieSerieStatusEnum.ERROR);
                    break;
            }
        } catch (MovieSerieException e) {
            response =  MovieSerieCommon.buildResponse(request, e.getStatus());
        }
        MovieSerieCommon.output(response);
        return response;
    }
}
