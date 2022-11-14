package com.megapelis.service.model.dto.response;

import lombok.*;

import java.io.Serializable;

/**
 * Clase {@link FindByIdMovieSerieRS}
 * @author sergio.barrios.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindByIdMovieSerieRS implements Serializable {

    private String id;
    private String name;
    private String idTMDB;
    private String image;
    private String points;
    private String status;
    private String type;
    private String premiereDate;
    private String createdDate;
    private String lastModifiedDate;
}
