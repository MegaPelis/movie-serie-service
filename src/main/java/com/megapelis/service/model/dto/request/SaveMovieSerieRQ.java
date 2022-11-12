package com.megapelis.service.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;

/**
 * Clase {@link SaveMovieSerieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveMovieSerieRQ implements Serializable {
    private String name;
    private String idTMDB;
    private String premiereDate;
    private String image;
    private String points;
    private TreeSet<String> genders;

}
