package com.alvaroartieda.foodies.map.model;

import android.app.Activity;

import com.alvaroartieda.foodies.model.Chef;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 24/02/2018.
 */

public class ChefOverlay extends OverlayItem {

    private Chef chef;

    public ChefOverlay(String aTitle, String aSnippet, IGeoPoint aGeoPoint) {
        super(aTitle, aSnippet, aGeoPoint);
    }

    public ChefOverlay(String aUid, String aTitle, String aDescription, IGeoPoint aGeoPoint) {
        super(aUid, aTitle, aDescription, aGeoPoint);
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public static ChefOverlay from(Chef chef, Activity activity){
        ChefOverlay chefOverlay = new ChefOverlay(chef.getName(),chef.getKitchenType().toString(),chef.getGeoPoint());
        chefOverlay.setMarker(chef.getKitchenType().getIcon(activity));
        chefOverlay.setChef(chef);
        return chefOverlay;
    }


    public static List<ChefOverlay> from(List<Chef> chefList, final Activity activity){
        List<ChefOverlay> pois = new ArrayList<>();
        for(Chef chef :chefList){
            pois.add(ChefOverlay.from(chef,activity));
        }

        return pois;
    }
}
