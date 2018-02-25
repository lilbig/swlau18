package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alvaroartieda.foodies.R;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class PictureLocation extends Fragment {

    private Button buttonProfile;
    private Button buttonMeal;
    private Button buttonLocation;
    private Button buttonValidate;

    public static PictureLocation newInstance() {
        PictureLocation fragment = new PictureLocation();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_picture_location, container, false);

        buttonProfile = view.findViewById(R.id.button_profile_pic);
        buttonMeal = view.findViewById(R.id.button_speciality);
        buttonLocation = view.findViewById(R.id.button_location);
        buttonValidate = view.findViewById(R.id.button_validation);


        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, DialogPicture.newInstance(1)).commit();
            }
        });

        buttonMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, DialogPicture.newInstance(2)).commit();
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, DialogPicture.newInstance(3)).commit();
            }
        });

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, ProfileFragment.newInstance()).commit();
            }
        });

        return view;
    }
}
