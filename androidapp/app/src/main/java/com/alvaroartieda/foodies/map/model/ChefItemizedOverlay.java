package com.alvaroartieda.foodies.map.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alvaroartieda.foodies.R;

import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 24/02/2018.
 */

public class ChefItemizedOverlay extends ItemizedIconOverlay<ChefOverlay> {
    private Context mContext;
    private Activity activity;

    public ChefItemizedOverlay(List<ChefOverlay> pList, OnItemGestureListener<ChefOverlay> pOnItemGestureListener, Context pContext, Activity activity) {
        super(pList, pOnItemGestureListener, pContext);
        mContext = pContext;
        this.activity = activity;
    }

    protected boolean onTap(int index) {
        ChefOverlay item = mItemList.get(index);
        Chef chef = item.getChef();



        //set up dialog
        Dialog dialog = new Dialog(activity);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.chefinfo_layer);
        //dialog.setTitle("This is my custom dialog box");

        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        TextView priceChF = dialog.findViewById(R.id.price);
        priceChF.setText(String.format("%4.2f CHF",chef.getPrice()));
        Button placeOrderButton = dialog.findViewById(R.id.placeOrderBtn);
        placeOrderButton.setOnClickListener((view)-> placeOrderButton.setText("ordered"));

        //now that the dialog is set up, it's time to show it
        dialog.show();

        return true;
    }


}
