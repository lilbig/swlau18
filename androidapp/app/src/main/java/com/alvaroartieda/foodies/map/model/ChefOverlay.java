package com.alvaroartieda.foodies.map.model;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alvaroartieda.foodies.R;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 24/02/2018.
 */

public class ChefOverlay extends OverlayItem {

    public static class ChefInfo extends InfoWindow{

        public ChefInfo(View v, MapView mapView) {
            super(v, mapView);
        }

        @Override
        public void onOpen(Object item) {

        }

        @Override
        public void onClose() {

        }
    }

    public ChefOverlay(String aTitle, String aSnippet, IGeoPoint aGeoPoint) {
        super(aTitle, aSnippet, aGeoPoint);
    }

    public ChefOverlay(String aUid, String aTitle, String aDescription, IGeoPoint aGeoPoint) {
        super(aUid, aTitle, aDescription, aGeoPoint);
    }

    public static ChefOverlay from(Chef chef, Activity activity, MapView mapView){
        ChefOverlay chefOverlay = new ChefOverlay(chef.getName(),chef.getKitchenType().toString(),chef.getGeoPoint());
        Marker marker = new Marker(mapView);
        marker.setIcon(chef.getKitchenType().getIcon(activity));
        View chefinfoView = activity.getLayoutInflater().inflate(R.layout.chefinfo_layer,null);
        TextView priceChF = chefinfoView.findViewById(R.id.price);
        priceChF.setText(String.format("%4.2f CHF",chef.getPrice()));
        Button placeOrderButton = chefinfoView.findViewById(R.id.placeOrderBtn);
        placeOrderButton.setOnClickListener((view)-> placeOrderButton.setText("ordered"));
        ChefInfo chefInfo = new ChefInfo(chefinfoView,mapView);
        marker.setInfoWindow(chefInfo);
        chefOverlay.(marker);

        return chefOverlay;
    }


    public static List<ChefOverlay> from(List<Chef> chefList, final Activity activity, MapView mapView){
        List<ChefOverlay> pois = new ArrayList<>();
        for(Chef chef :chefList){
            pois.add(ChefOverlay.from(chef,activity,mapView));
        }

        return pois;
    }
}
