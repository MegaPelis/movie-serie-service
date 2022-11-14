package com.megapelis.service.model.dto.response;

import lombok.*;

import java.util.List;

/**
 * Clase {@link FindAllMovieSerieRS}
 * @author sergio.barrios.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllMovieSerieRS {
    private List<FindByIdMovieSerieRS> findAll;
}
