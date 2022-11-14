package com.megapelis.service.api.factory;

import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.api.handler.impl.*;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.response.generic.Response;
import com.megapelis.service.model.enums.MovieSerieOperationEnum;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
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
        Response response = null;
        MovieSerieHandler handler = null;
        try {
            MovieSerieCommon.isValidRequest(request);
            switch (MovieSerieOperationEnum.isValid(request.getOperation())){
                case FIND_BY_ID:
                    handler = new FindByIdMovieSerieHandler();
                    break;
                case FIND_ALL:
                    handler = new FindAllMovieSerieHandler();
                    break;
                case SAVE:
                    handler = new SaveMovieSerieHandler();
                    break;
                case UPDATE:
                    handler = new UpdateMovieSerieHandler();
                    break;
                case UPDATE_STATUS:
                    handler = new UpdateStatusMovieSerieHandler();
                    break;
                default:
                    response =  MovieSerieCommon.buildResponse(request, MovieSerieStatusEnum.ERROR);
                    break;
            }
        } catch (MovieSerieException e) {
            response =  MovieSerieCommon.buildResponse(request, e.getStatus());
        }
        if(null == response && null != handler)
            response = handler.execute(request);
        MovieSerieCommon.output(response);
        return response;
    }
}
