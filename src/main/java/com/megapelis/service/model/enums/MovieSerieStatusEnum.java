package com.megapelis.service.model.enums;

import lombok.Getter;

/**
 * Clase {@link MovieSerieStatusEnum}
 * @author sergio.barrios.
 */
@Getter
public enum MovieSerieStatusEnum {

    SUCCESS("200", "La petición se ha procesado exitosamente!", "Super todo ha salido bien!"),
    ERROR("500", "No se ha procesado la petición.", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),
    ERROR_ENV("409", "No se ha procesado el valor de la variable de entorno", "Ouch, lamentamos que haya ocurrido esto."),
    ERROR_ENV_NOT_CONTENT("404", "No fue encontrado el valor de la variable de entorno", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),
    ERROR_SERVICE_OR_OPERATION("404", "No fue encontrado ningún servicio.", "Esperamos estar pronto para ti!"),

    ERROR_PROPERTY_VALUE("404", "El valor de la propiedad no es el esperado.", "Esperamos estar pronto para ti!"),
    ERROR_FORMAT_REQUEST("400", "El formato de la petición no es el esperado.", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),

    ERROR_AMAZON_DB_NOT_RESULT("404", "No se ha encontrado ningún elemento en bd.", "Vuelva a intentar mas tarde."),

    ERROR_AMAZON_DB_FIND_BY_ID("404", "No se ha encontrado ningún elemento en bd (findById).", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),
    ERROR_AMAZON_DB_FIND_ALL("404", "No se ha encontrado ningún elemento en bd (findAll).", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),
    ERROR_AMAZON_DB_SAVE_EXIST("404", "Ya se encuentra registrado ese elemento.", "Algo se nos hace familiar, elemento repetido."),
    ERROR_AMAZON_DB_SAVE("404", "No se ha registrado el elemento en bd.", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),
    ERROR_AMAZON_DB_UPDATE("404", "No se ha actualizado el elemento en bd.", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde.");

    private final String code;
    private final String messageBackend;
    private final String messageFrontend;

    MovieSerieStatusEnum(String code, String messageBackend, String messageFrontend){
        this.code = code;
        this.messageBackend = messageBackend;
        this.messageFrontend = messageFrontend;
    }
}
