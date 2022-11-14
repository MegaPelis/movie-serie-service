package com.megapelis.service.api.handler.impl;

import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.model.dto.request.UpdateStatusMovieSerieRQ;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

public class UpdateStatusMovieSerieHandler extends MovieSerieHandler {

    @Override
    public Object execute(Object data) throws MovieSerieException {
        UpdateStatusMovieSerieRQ updateStatusMovieSerieRQ = (UpdateStatusMovieSerieRQ) data;
        amazonDB.updateStatus(updateStatusMovieSerieRQ.getId(), updateStatusMovieSerieRQ.getStatus());
        return null;
    }

    @Override
    public Object validatePayload(Request request) throws MovieSerieException {
        UpdateStatusMovieSerieRQ updateStatusMovieSerieRQ = convertPayload(request, UpdateStatusMovieSerieRQ.class);
        if(!MovieSerieCommon.isValidString(updateStatusMovieSerieRQ.getId(), updateStatusMovieSerieRQ.getStatus())){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
        }
        com.megapelis.service.model.entity.MovieSerieStatusEnum.valueOf(updateStatusMovieSerieRQ.getStatus());
        return updateStatusMovieSerieRQ;
    }

    @Override
    public Object buildObjectAmazon(Object data) {
        return data;
    }
}
