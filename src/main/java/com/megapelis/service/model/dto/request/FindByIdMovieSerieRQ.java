package com.megapelis.service.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase {@link FindByIdMovieSerieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindByIdMovieSerieRQ implements Serializable {
    private String idTMDB;
}
