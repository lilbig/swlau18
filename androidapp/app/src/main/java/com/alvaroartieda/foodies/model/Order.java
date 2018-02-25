package com.alvaroartieda.foodies.model;

/**
 * Created by radu on 25/02/2018.
 */

public class Order {
    private Long id;
    private Long from;
    private Long to;
    private OrderStatus orderStatus;

    public Order(Long from, Long to, OrderStatus orderStatus) {
        this.from = from;
        this.to = to;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
