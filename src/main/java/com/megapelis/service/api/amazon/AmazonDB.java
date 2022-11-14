package com.megapelis.service.api.amazon;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.megapelis.service.model.entity.MovieSerie;
import com.megapelis.service.model.entity.MovieSerieStatusEnum;
import com.megapelis.service.model.entity.amazon.AttributeAmazon;
import com.megapelis.service.model.entity.amazon.QueryAmazon;
import com.megapelis.service.util.MovieSerieCommon;
import com.megapelis.service.util.MovieSerieConstant;
import com.megapelis.service.util.MovieSerieException;

import java.util.*;

public class AmazonDB {

    private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper dynamoDBMapper;

    public AmazonDB(){
    }

    /**
     * Metodo que permite realizar la conexión con bd.
     */
    public void connection(){
        if(null == amazonDynamoDB)
            amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        if(null == dynamoDBMapper)
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    /**
     * Metodo que permite consultar por el id y tipo.
     * @param idTMDB
     * @param type
     * @return {@link  MovieSerie}
     * @throws MovieSerieException
     */
    public MovieSerie findById(String idTMDB, String type) throws MovieSerieException {
        QueryAmazon query = QueryAmazon.builder()
                .query(MovieSerieCommon.getEnv(MovieSerieConstant.STRING_ENV_QUERY_FIND_BY_ID))
                .attributes(MovieSerieCommon.addAttributes(null,
                        AttributeAmazon.builder().key(MovieSerieConstant.STRING_EAV_ID_TMDB).attribute(new AttributeValue().withS(idTMDB)).build(),
                        AttributeAmazon.builder().key(MovieSerieConstant.STRING_EAV_TYPE).attribute(new AttributeValue().withS(type)).build())
                ).projectionExpression(MovieSerieCommon.getEnv(MovieSerieConstant.STRING_ENV_ATTRIBUTE_FIND_BY_ID))
                .build();
        List<MovieSerie> movieSeries = query(query, com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_FIND_BY_ID);
        if(!MovieSerieCommon.isList(movieSeries))
            throw new MovieSerieException(com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_NOT_RESULT);
        return movieSeries.get(MovieSerieConstant.INTEGER_ZERO);
    }

    /**
     * Metodo que permite consultar todo por el tipo.
     * @param type
     * @return {@link List}
     * @throws MovieSerieException
     */
    public List<MovieSerie> findAll(String type) throws MovieSerieException{
        QueryAmazon query = QueryAmazon.builder()
                .query(MovieSerieCommon.getEnv(MovieSerieConstant.STRING_ENV_QUERY_FIND_ALL))
                .attributes(MovieSerieCommon.addAttributes(null,
                        AttributeAmazon.builder().key(MovieSerieConstant.STRING_EAV_TYPE).attribute(new AttributeValue().withS(type)).build())
                ).projectionExpression(MovieSerieCommon.getEnv(MovieSerieConstant.STRING_ENV_ATTRIBUTE_FIND_ALL))
                .build();
        return query(query, com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_FIND_ALL);
    }

    /**
     * Metodo que permite ejecutar una consulta.
     * @param queryAmazon
     * @param error
     * @return {@link List}
     * @throws MovieSerieException
     */
    public List<MovieSerie> query(QueryAmazon queryAmazon, com.megapelis.service.model.enums.MovieSerieStatusEnum error) throws MovieSerieException{
        try {
            MovieSerieCommon.output(queryAmazon);
            connection();
            ScanRequest scanRequest = new ScanRequest()
                    .withTableName(MovieSerieConstant.TABLE_MOVIE_SERIE_NAME)
                    .withFilterExpression(queryAmazon.getQuery())
                    .withProjectionExpression(queryAmazon.getProjectionExpression())
                    .withExpressionAttributeValues(queryAmazon.getAttributes());
            ScanResult result = amazonDynamoDB.scan(scanRequest);
            List<MovieSerie> movieSeries = new ArrayList<>();
            result.getItems().stream().forEach(item -> {
                movieSeries.add(MovieSerie
                        .builder()
                        .id(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_ID, queryAmazon.getProjectionExpression()))
                        .name(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_NAME, queryAmazon.getProjectionExpression()))
                        .idTMDB(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_ID_TMDB, queryAmazon.getProjectionExpression()))
                        .image(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_IMAGE, queryAmazon.getProjectionExpression()))
                        .points(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_POINTS, queryAmazon.getProjectionExpression()))
                        .status(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_STATUS, queryAmazon.getProjectionExpression()))
                        .typeService(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_TYPE_SERVICE, queryAmazon.getProjectionExpression()))
                        .premiereDate(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_PREMIERE_DATE, queryAmazon.getProjectionExpression()))
                        .createdDate(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_CREATED_DATE, queryAmazon.getProjectionExpression()))
                        .lastModifiedDate(MovieSerieCommon.getValueAttribute(item, MovieSerieConstant.TABLE_MOVIE_SERIE_COLUMN_LAST_MODIFIED_DATE, queryAmazon.getProjectionExpression()))
                        .build());
            });
            return movieSeries;
        }catch (Exception exception) {
            MovieSerieCommon.output(exception);
            throw new MovieSerieException(error);
        }
    }

    /**
     * Metodo que permite registrar un elemento.
     * @param movieSerie
     * @throws MovieSerieException
     */
    public void save(MovieSerie movieSerie) throws MovieSerieException {
        try{
            connection();
            movieSerie.setCreatedDate(MovieSerieCommon.getDateTime());
            movieSerie.setLastModifiedDate(null);
            movieSerie.setStatus(MovieSerieStatusEnum.ACTIVE.name());
            MovieSerieCommon.output(movieSerie);
            dynamoDBMapper.save(movieSerie);
        } catch (Exception exception) {
            MovieSerieCommon.output(exception);
            throw new MovieSerieException(com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_SAVE);
        }
    }

    /**
     * Metodo que permite actualizar un elemento.
     * @param movieSerie
     * @throws MovieSerieException
     */
    public void update(MovieSerie movieSerie) throws MovieSerieException {
        try{
            connection();
            movieSerie.setLastModifiedDate(MovieSerieCommon.getDateTime());
            MovieSerieCommon.output(movieSerie);
            dynamoDBMapper.save(movieSerie);
        }catch (Exception exception){
            MovieSerieCommon.output(exception);
            throw new MovieSerieException(com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_UPDATE);
        }
    }

    /**
     * Metodo que permite actualizar el estado de un elemento.
     * @param id
     * @param status
     * @throws MovieSerieException
     */
    public void updateStatus(String id, String status) throws MovieSerieException {
        try{
            connection();
            MovieSerie movieSerie = dynamoDBMapper.load(MovieSerie.class, id);
            if(null == movieSerie || !MovieSerieCommon.isValidString(movieSerie.getId()))
                throw new MovieSerieException(com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_NOT_RESULT);
            movieSerie.setLastModifiedDate(MovieSerieCommon.getDateTime());
            movieSerie.setStatus(status);
            MovieSerieCommon.output(movieSerie);
            dynamoDBMapper.save(movieSerie);
        }catch (MovieSerieException movieSerieException){
            throw new MovieSerieException(movieSerieException.getStatus());
        }catch (Exception exception){
            MovieSerieCommon.output(exception);
            throw new MovieSerieException(com.megapelis.service.model.enums.MovieSerieStatusEnum.ERROR_AMAZON_DB_UPDATE);
        }
    }
}
