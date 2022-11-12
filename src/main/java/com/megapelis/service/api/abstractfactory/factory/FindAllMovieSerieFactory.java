package com.megapelis.service.api.abstractfactory.factory;

import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.request.generic.RequestProperty;
import com.megapelis.service.model.dto.response.generic.Response;
import com.megapelis.service.model.enums.MovieSerieOperationEnum;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

public class FindAllMovieSerieFactory {

    private FindAllMovieSerieFactory(){
    }

    public static Response handler(Request request, RequestProperty property) throws MovieSerieException {
        Response response;
        switch (MovieSerieOperationEnum.isValid(property.getValue())){
            case MOVIE:
                response = null;
                break;
            case SERIE:
                response = null;
                break;
            default:
                response =  MovieSerieCommon.buildResponse(request, MovieSerieStatusEnum.ERROR);
                break;
        }
        return response;
    }
}
