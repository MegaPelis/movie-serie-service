package com.megapelis.service.api.handler.impl;

import com.megapelis.service.api.handler.MovieSerieHandler;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.response.FindAllMovieSerieRS;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindAllMovieSerieHandler extends MovieSerieHandler {

    @Override
    public Object execute(Object data) throws MovieSerieException {
        return FindAllMovieSerieRS.builder()
                .findAll(amazonDB
                        .findAll(propertyService.getValue())
                        .stream()
                        .flatMap(movieSerie -> Stream.of(MovieSerieCommon.convertMovieSerieToFindByIdMovieSerieRS(movieSerie)))
                        .collect(Collectors.toList())
                ).build();
    }
    @Override
    public Object validatePayload(Request request) throws MovieSerieException {
        return request.getData();
    }

    @Override
    public Object buildObjectAmazon(Object data) {
        return data;
    }
}
