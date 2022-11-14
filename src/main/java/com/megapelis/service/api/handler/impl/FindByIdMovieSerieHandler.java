package com.megapelis.service.api.handler.impl;

import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.model.dto.request.FindByIdMovieSerieRQ;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.entity.MovieSerie;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

public class FindByIdMovieSerieHandler extends MovieSerieHandler {

    @Override
    public Object execute(Object data) throws MovieSerieException {
        FindByIdMovieSerieRQ findByIdMovieSerieRQ = (FindByIdMovieSerieRQ) data;
        MovieSerie movieSerie = amazonDB.findById(findByIdMovieSerieRQ.getIdTMDB(), propertyService.getValue());
        return MovieSerieCommon.convertMovieSerieToFindByIdMovieSerieRS(movieSerie);
    }

    @Override
    public Object validatePayload(Request request) throws MovieSerieException {
        FindByIdMovieSerieRQ findByIdMovieSerieRQ = convertPayload(request, FindByIdMovieSerieRQ.class);
        if(!MovieSerieCommon.isValidString(findByIdMovieSerieRQ.getIdTMDB())){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
        }
        return findByIdMovieSerieRQ;
    }

    @Override
    public Object buildObjectAmazon(Object data) {
        return data;
    }
}
