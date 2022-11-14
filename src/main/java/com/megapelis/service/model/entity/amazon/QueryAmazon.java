package com.megapelis.service.model.entity.amazon;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryAmazon implements Serializable {
    private String query;
    private Map<String, AttributeValue> attributes;
    private String projectionExpression;
}
