package com.alvaroartieda.foodies.map.model;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.alvaroartieda.foodies.R;

/**
 * Created by radu on 24/02/2018.
 */

public enum KitchenType {
    ITALIEN(R.drawable.it_32),
    JAPAN(R.drawable.jp_32),
    AMERICAN(R.drawable.us_32),
    INDIAN(R.drawable.in_32),
    SUISSE(R.drawable.ch_32);

    private Integer resourceId;

    KitchenType(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Drawable getIcon(Activity activity) {
        return activity.getResources().getDrawable(resourceId);
    }

    public String toString(){
        return this.name();
    }

}
