package com.alvaroartieda.foodies.model;

import android.util.EventLogTags;

import com.alvaroartieda.foodies.map.model.KitchenType;
import com.alvaroartieda.foodies.model.User;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

/**
 * Created by radu on 24/02/2018.
 */

public class Chef extends User {
    private KitchenType kitchenType;
    private GeoPoint geoPoint;
    private Double price = 10.0;

    public Chef(String name, KitchenType kitchenType, GeoPoint geoPoint) {
        super(name,"");
        this.kitchenType = kitchenType;
        this.geoPoint = geoPoint;
    }


    public KitchenType getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(KitchenType kitchenType) {
        this.kitchenType = kitchenType;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
