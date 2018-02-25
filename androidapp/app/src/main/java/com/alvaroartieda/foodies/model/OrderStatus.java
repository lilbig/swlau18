package com.alvaroartieda.foodies.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by radu on 25/02/2018.
 */

public enum OrderStatus {
    ORDER_RECEIVED,
    ORDER_ACCEPTED,
    ORDER_PREPARED,
    ORDER_DELIVERED;

    @JsonCreator
    public static OrderStatus forValue(String value) {
        return Enum.valueOf(OrderStatus.class,value.toUpperCase());
    }
}
