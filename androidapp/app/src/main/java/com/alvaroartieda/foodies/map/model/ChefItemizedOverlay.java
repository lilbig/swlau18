package com.alvaroartieda.foodies.map.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.fragments.ProfileFragment;

import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.InputStream;
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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.light_brown)));
        dialog.setContentView(R.layout.chefinfo_layer);
        //dialog.setTitle("This is my custom dialog box");

        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        TextView nameChef = dialog.findViewById(R.id.name);
        nameChef.setText(chef.getName());

        ImageView imageView =  dialog.findViewById(R.id.imagefood);
        DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);
        downloadImageTask.execute("https://loremflickr.com/200/200/food,"+chef.getKitchenType().toString().toLowerCase()+"/all");


        Button placeOrderButton = dialog.findViewById(R.id.placeOrderBtn);
        placeOrderButton.setText(String.format("%4.2f CHF",chef.getPrice()));
        placeOrderButton.setOnClickListener((view)-> {
            dialog.dismiss();
            activity.getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment.newInstance()).commit();});

        //now that the dialog is set up, it's time to show it
        dialog.show();

        return true;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY-1);
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                Log.d("Downloading",urldisplay);
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
