package com.megapelis.service.api.handler.impl;

import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.model.dto.request.UpdateMovieSerieRQ;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.entity.MovieSerie;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

public class UpdateMovieSerieHandler extends MovieSerieHandler {

    @Override
    public Object execute(Object data) throws MovieSerieException {
        MovieSerie movieSerie = (MovieSerie) data;
        MovieSerie findById = amazonDB.findById(movieSerie.getIdTMDB(), propertyService.getValue());
        if(null == findById){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_AMAZON_DB_NOT_RESULT);
        }
        amazonDB.update(movieSerie);
        return null;
    }

    @Override
    public Object validatePayload(Request request) throws MovieSerieException {
        UpdateMovieSerieRQ updateMovieSerieRQ = convertPayload(request, UpdateMovieSerieRQ.class);
        if(!MovieSerieCommon.isValidString(updateMovieSerieRQ.getId(), updateMovieSerieRQ.getName(),
                updateMovieSerieRQ.getIdTMDB(), updateMovieSerieRQ.getPremiereDate(),
                updateMovieSerieRQ.getImage(), updateMovieSerieRQ.getPoints())){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
        }
        return updateMovieSerieRQ;
    }

    @Override
    public Object buildObjectAmazon(Object data) {
        UpdateMovieSerieRQ updateMovieSerieRQ = (UpdateMovieSerieRQ) data;
        return MovieSerie.builder()
                .id(updateMovieSerieRQ.getId())
                .name(updateMovieSerieRQ.getName().trim().toUpperCase())
                .idTMDB(updateMovieSerieRQ.getIdTMDB())
                .premiereDate(updateMovieSerieRQ.getPremiereDate())
                .image(updateMovieSerieRQ.getImage())
                .points(updateMovieSerieRQ.getPoints())
                .typeService(propertyService.getValue())
                .build();
    }
}
