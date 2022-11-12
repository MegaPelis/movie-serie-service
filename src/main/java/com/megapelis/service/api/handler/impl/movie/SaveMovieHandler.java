package com.megapelis.service.api.handler.impl.movie;

import com.google.gson.JsonObject;
import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.model.dto.request.SaveMovieSerieRQ;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.entity.MovieSerie;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

public class SaveMovieHandler extends MovieSerieHandler {
    @Override
    public Object validatePayload(Request request) throws MovieSerieException {
        SaveMovieSerieRQ saveMovieSerieRQ = convertPayload(request, SaveMovieSerieRQ.class);
        if(!MovieSerieCommon.isValidString(saveMovieSerieRQ.getName(), saveMovieSerieRQ.getIdTMDB(),
                saveMovieSerieRQ.getPremiereDate(), saveMovieSerieRQ.getImage(),
                saveMovieSerieRQ.getPoints()) ||
                !MovieSerieCommon.isTreSet(saveMovieSerieRQ.getGenders())){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
        }
        return saveMovieSerieRQ;
    }

    @Override
    public Object buildObjectAmazon(Object data) {
        SaveMovieSerieRQ saveMovieSerieRQ = (SaveMovieSerieRQ) data;
        return MovieSerie.builder()
                .name(saveMovieSerieRQ.getName())
                .idTMDB(saveMovieSerieRQ.getIdTMDB())
                .premiereDate(saveMovieSerieRQ.getPremiereDate())
                .image(saveMovieSerieRQ.getImage())
                .points(saveMovieSerieRQ.getPoints())
                .genders(saveMovieSerieRQ.getGenders().toString())
                .build();
    }

    @Override
    public JsonObject execute(Object data) throws MovieSerieException {
        MovieSerie movieSerie = (MovieSerie) data;
        amazonDB.save(movieSerie);
        return null;
    }
}
