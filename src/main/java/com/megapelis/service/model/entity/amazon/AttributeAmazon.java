package com.megapelis.service.model.entity.amazon;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeAmazon implements Serializable {
    private String key;
    private AttributeValue attribute;
}
