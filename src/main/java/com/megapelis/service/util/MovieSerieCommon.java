package com.megapelis.service.util;

import com.google.gson.Gson;
import com.megapelis.service.model.dto.request.generic.Request;
import com.megapelis.service.model.dto.request.generic.RequestProperty;
import com.megapelis.service.model.dto.response.generic.Response;
import com.megapelis.service.model.dto.response.generic.ResponseStatus;
import com.megapelis.service.model.enums.MovieSerieStatusEnum;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Clase {@link MovieSerieCommon}
 * @author sergio.barrios.
 */
public class MovieSerieCommon {

    private MovieSerieCommon(){}

    /**
     * Metodo que permite validar una cadena no este vacia.
     * @param value
     * @return {@link boolean}
     */
    public static boolean isValidString(String value){
        return null != value && !value.trim().isEmpty();
    }

    /**
     * Metodo que permite validar varias cadenas.
     * @param values
     * @return {@link boolean}
     */
    public static boolean isValidString(String ... values) {
        for (String value : values) {
            if (!isValidString(value)) {
                return MovieSerieConstant.BOOLEAN_FALSE;
            }
        }
        return MovieSerieConstant.BOOLEAN_TRUE;
    }

    /**
     * Metodo que permite validar una lista.
     * @param list
     * @return {@link boolean}
     */
    public static boolean isList(List list){
        return null != list && !list.isEmpty();
    }

    public static boolean isTreSet(TreeSet treeSet){
        return null != treeSet && !treeSet.isEmpty();
    }

    /**
     * Metodo que valida la petición.
     * @param request
     * @throws MovieSerieException
     */
    public static void isValidRequest(Request request) throws MovieSerieException {
        boolean isError = MovieSerieConstant.BOOLEAN_FALSE;
        if(null == request || !isValidString(request.getTraceId(), request.getDateTime(), request.getService(), request.getOperation()))
            isError = MovieSerieConstant.BOOLEAN_TRUE;
        if(!request.getService().equalsIgnoreCase(MovieSerieConstant.STRING_SERVICE_NAME))
            isError = MovieSerieConstant.BOOLEAN_TRUE;
        if(request.getService().equalsIgnoreCase(request.getOperation()))
            isError = MovieSerieConstant.BOOLEAN_TRUE;
        RequestProperty property = getProperty(MovieSerieConstant.STRING_PROPERTY_SERVICE, request.getProperties());
        if(null == property || !isValidString(property.getValue())){
            isError = MovieSerieConstant.BOOLEAN_TRUE;
        }
        if(isError)
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_FORMAT_REQUEST);
    }

    /**
     * Metodo que permite obtener la fecha y hora actual.
     * @return {@link String}
     */
    public static String getDateTime(){
        return ZonedDateTime.now(ZoneId.of(MovieSerieConstant.STRING_DATE_ZONE))
                .format(DateTimeFormatter.ofPattern(MovieSerieConstant.STRING_DATE_TIME_FORMAT));
    }

    /**
     * Metodo que permite obtener el valor de una variable de entorno.
     * @param name
     * @return {@link String}
     * @throws MovieSerieException
     */
    public static String getEnv(String name) throws MovieSerieException {
        if(!isValidString(name))
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_ENV);
        String env = System.getenv(name);
        if(!isValidString(env)){
            throw new MovieSerieException(MovieSerieStatusEnum.ERROR_ENV_NOT_CONTENT);
        }
        return env;
    }

    /**
     * Metodo que permite obtener el valor de una propiedad.
     * @param key
     * @param properties
     * @return {@link RequestProperty}
     */
    public static RequestProperty getProperty(String key, List<RequestProperty> properties){
        if(!isList(properties) || isValidString(key))
            return null;
        List<RequestProperty> propertiesNew =  properties.stream().filter(property -> property.getName().equalsIgnoreCase(key)).collect(Collectors.toList());
        if(propertiesNew.isEmpty())
            return null;
        return propertiesNew.get(MovieSerieConstant.INTEGER_ZERO);
    }

    /**
     * Metodo que permite obtener el objeto en cadena.
     * @param object
     * @return {@link  String}
     */
    public static String getStringJSON(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * Metodo que convierte un objeto a clase.
     * @param object
     * @param clazz
     * @return {@link T}
     * @param <T>
     */
    public static <T> T convertObjectToClass(Object object, Class<T> clazz){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return gson.fromJson(json, clazz);
    }

    /**
     * Metodo que permite darle formato a una cadena
     * @param value
     * @param values
     * @return {@link String}
     */
    public static String formatString(String value, String ... values){
        return String.format(value, values);
    }

    /**
     * Metodo que permite registrar la salida para cloud watch.
     * @param object
     */
    public static void output(Object object){
        if(object instanceof Response || object instanceof Request)
            System.out.println(getStringJSON(object));
        else
            System.out.println(object);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @return {@link Response}
     */
    public static Response buildResponse(Request request){
        return buildResponse(request, null, null, null, null);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @param statusEnum
     * @return {@link Response}
     */
    public static Response buildResponse(Request request, MovieSerieStatusEnum statusEnum){
        return buildResponse(request, statusEnum, null);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @param statusEnum
     * @param data
     * @return {@link Response}
     */
    public static Response buildResponse(Request request, MovieSerieStatusEnum statusEnum, Object data){
        return buildResponse(request, statusEnum.getCode(), statusEnum.getMessageBackend(), statusEnum.getMessageFrontend(), data);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     * @param request
     * @param code
     * @param messageBackend
     * @param messageFrontend
     * @param data
     * @return {@link Response}
     */
    private static Response buildResponse(Request request, String code, String messageBackend, String messageFrontend, Object data){
        if(null == request)
            return null;
        if(!isValidString(code,messageBackend, messageFrontend)){
            code = MovieSerieStatusEnum.ERROR.getCode();
            messageBackend = MovieSerieStatusEnum.ERROR.getMessageBackend();
            messageFrontend = MovieSerieStatusEnum.ERROR.getMessageFrontend();
        }
        if(null != data){
            Gson gson = new Gson();
            data = gson.toJson(data);
        }
        String dateTime = MovieSerieCommon.getDateTime();
        return Response
                .builder()
                .traceId(request.getTraceId())
                .dateTime(dateTime)
                .service(request.getService())
                .operation(request.getOperation())
                .status(new ResponseStatus(code, messageBackend, messageFrontend))
                .data(data)
                .build();
    }
}
