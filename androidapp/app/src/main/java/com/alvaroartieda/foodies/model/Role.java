package com.alvaroartieda.foodies.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by radu on 25/02/2018.
 */
@JsonFormat(shape = JsonFormat.Shape.SCALAR)
public enum Role {
    CHEF,
    GOURMAND;

    @JsonCreator
    public static Role forValue(String value) {
        return Enum.valueOf(Role.class,value.toUpperCase());
    }
}
