package com.megapelis.service.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase {@link UpdateMovieSerieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMovieSerieRQ {
    private String id;
    private String name;
    private String idTMDB;
    private String premiereDate;
    private String image;
    private String points;
}
