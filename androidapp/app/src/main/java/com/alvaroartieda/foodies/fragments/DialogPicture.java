package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alvaroartieda.foodies.R;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class DialogPicture extends DialogFragment {

    private static int choice;
    private ImageView profilepic;
    private ImageView mealpic;
    private ImageView location;

    public static DialogFragment newInstance(int a) {
        DialogPicture fragment = new DialogPicture();
        choice = a;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dialog_pictures, container, false);

        profilepic = view.findViewById(R.id.profile_pic);
        mealpic = view.findViewById(R.id.meal_pic);
        location = view.findViewById(R.id.location_pic);

        if(choice == 1){
            profilepic.setVisibility(View.VISIBLE);
        } else if (choice ==2){
            mealpic.setVisibility(View.VISIBLE);
        } else if(choice == 3){
            location.setVisibility(View.VISIBLE);
        }

        return view;
    }

    }
