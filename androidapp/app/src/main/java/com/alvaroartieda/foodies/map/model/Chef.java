package com.alvaroartieda.foodies.map.model;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

/**
 * Created by radu on 24/02/2018.
 */

public class Chef extends OverlayItem{

    public Chef(String aTitle, String aSnippet, IGeoPoint aGeoPoint) {
        super(aTitle, aSnippet, aGeoPoint);
    }

    public Chef(String aUid, String aTitle, String aDescription, IGeoPoint aGeoPoint) {
        super(aUid, aTitle, aDescription, aGeoPoint);
    }
}
