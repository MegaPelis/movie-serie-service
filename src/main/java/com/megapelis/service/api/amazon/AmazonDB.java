package com.megapelis.service.api.amazon;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.megapelis.service.model.entity.MovieSerie;
import com.megapelis.service.util.MovieSerieCommon;

public class AmazonDB {

    private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper dynamoDBMapper;

    public AmazonDB(){
    }

    public void connection(){
        amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    public void save(MovieSerie movieSerie){
        if(null == movieSerie || MovieSerieCommon.isValidString(
                movieSerie.getName(), movieSerie.getIdTMDB(), movieSerie.getPremiereDate())){

        }
        connection();
        movieSerie.setCreatedDate(MovieSerieCommon.getDateTime());
        movieSerie.setLastModifiedDate(null);
        movieSerie.setStatus("true");
        MovieSerieCommon.output(movieSerie);
        dynamoDBMapper.save(movieSerie);
    }
}
