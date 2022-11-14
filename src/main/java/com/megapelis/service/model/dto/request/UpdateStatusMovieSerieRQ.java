package com.megapelis.service.model.dto.request;

import com.megapelis.service.model.entity.MovieSerieStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase {@link UpdateStatusMovieSerieRQ}
 * @author sergio.barrios.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusMovieSerieRQ {
    private String id;
    private String status;
}
