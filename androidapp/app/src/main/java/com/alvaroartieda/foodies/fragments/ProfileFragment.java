package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alvaroartieda.foodies.R;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class ProfileFragment extends Fragment {



    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_profile, container, false);


        return view;
    }
}
