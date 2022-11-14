package com.megapelis.service.api.handler.impl;

import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.model.dto.request.SaveMovieSerieRQ;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.entity.MovieSerie;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

public class SaveMovieSerieHandler extends MovieSerieHandler {

    @Override
    public Object execute(Object data) throws MovieSerieException {
        MovieSerie movieSerie = (MovieSerie) data;
        MovieSerie findById = null;
        try{
            findById = amazonDB.findById(movieSerie.getIdTMDB(), propertyService.getValue());
        }catch (MovieSerieException movieSerieException){
            if(MovieSerieStatusEnum.ERROR_AMAZON_DB_NOT_RESULT != movieSerieException.getStatus() &&
                    MovieSerieStatusEnum.ERROR_AMAZON_DB_FIND_BY_ID != movieSerieException.getStatus()){
                throw new MovieSerieException(movieSerieException.getStatus());
            }
        }
        if(null != findById){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_AMAZON_DB_SAVE_EXIST);
        }
        amazonDB.save(movieSerie);
        return null;
    }

    @Override
    public Object validatePayload(Request request) throws MovieSerieException {
        SaveMovieSerieRQ saveMovieSerieRQ = convertPayload(request, SaveMovieSerieRQ.class);
        if(!MovieSerieCommon.isValidString(saveMovieSerieRQ.getName(), saveMovieSerieRQ.getIdTMDB(), saveMovieSerieRQ.getPremiereDate(),
                saveMovieSerieRQ.getImage(), saveMovieSerieRQ.getPoints())){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
        }
        return saveMovieSerieRQ;
    }

    @Override
    public Object buildObjectAmazon(Object data) {
        SaveMovieSerieRQ saveMovieSerieRQ = (SaveMovieSerieRQ) data;
        return MovieSerie.builder()
                .name(saveMovieSerieRQ.getName().trim().toUpperCase())
                .idTMDB(saveMovieSerieRQ.getIdTMDB())
                .premiereDate(saveMovieSerieRQ.getPremiereDate())
                .image(saveMovieSerieRQ.getImage())
                .points(saveMovieSerieRQ.getPoints())
                .typeService(propertyService.getValue())
                .build();
    }
}
