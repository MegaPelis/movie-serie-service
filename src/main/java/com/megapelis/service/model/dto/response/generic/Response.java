package com.megapelis.service.model.dto.response.generic;

import lombok.*;

/**
 * Clase {@link Response}
 * @author sergio.barrios.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String traceId;
    private String dateTime;
    private String service;
    private String operation;
    private ResponseStatus status;
    private Object data;
}
