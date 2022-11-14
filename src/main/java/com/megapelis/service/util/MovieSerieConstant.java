package com.megapelis.service.util;

/**
 * Clase {@link MovieSerieConstant}
 * @author sergio.barrios.
 */
public class MovieSerieConstant {

    public static final boolean BOOLEAN_TRUE = true;
    public static final boolean BOOLEAN_FALSE = false;

    public static final int INTEGER_ZERO = 0;

    // Fecha
    public static final String STRING_DATE_ZONE = "America/Bogota";
    public static final String STRING_DATE_TIME_FORMAT = "yyyy/MM/dd hh:mm:ss";

    // SERVICIO
    public static final String STRING_SERVICE_NAME = "MovieSerie";
    public static final String STRING_PROPERTY_SERVICE = "service";

    // TABLE
    public static final String TABLE_MOVIE_SERIE_NAME = "movie-serie";
    public static final String TABLE_MOVIE_SERIE_COLUMN_ID = "id";
    public static final String TABLE_MOVIE_SERIE_COLUMN_NAME = "name";
    public static final String TABLE_MOVIE_SERIE_COLUMN_ID_TMDB = "id_tmdb";
    public static final String TABLE_MOVIE_SERIE_COLUMN_IMAGE = "image";
    public static final String TABLE_MOVIE_SERIE_COLUMN_POINTS = "points";
    public static final String TABLE_MOVIE_SERIE_COLUMN_STATUS = "status";
    public static final String TABLE_MOVIE_SERIE_COLUMN_TYPE_SERVICE = "type_service";
    public static final String TABLE_MOVIE_SERIE_COLUMN_PREMIERE_DATE = "premiere_date";
    public static final String TABLE_MOVIE_SERIE_COLUMN_CREATED_DATE = "created_date";
    public static final String TABLE_MOVIE_SERIE_COLUMN_LAST_MODIFIED_DATE = "last_modified_date";

    // EAV
    public static final String STRING_EAV_ID_TMDB = ":id_tmdb";
    public static final String STRING_EAV_TYPE = ":type_service";

    // ENV
    public static final String STRING_ENV_QUERY_FIND_BY_ID = "MEGAPELIS_QUERY_FIND_BY_ID";
    public static final String STRING_ENV_QUERY_FIND_ALL = "MEGAPELIS_QUERY_FIND_ALL";

    public static final String STRING_ENV_ATTRIBUTE_FIND_BY_ID = "MEGAPELIS_ATTRIBUTE_FIND_BY_ID";
    public static final String STRING_ENV_ATTRIBUTE_FIND_ALL = "MEGAPELIS_ATTRIBUTE_FIND_ALL";

    private MovieSerieConstant(){}
}
