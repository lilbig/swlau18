package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alvaroartieda.foodies.R;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class WhoAreYouFragment extends Fragment {

    private EditText editName;

    public static WhoAreYouFragment newInstance() {
        WhoAreYouFragment fragment = new WhoAreYouFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_whoareyou, container, false);

        editName = view.findViewById(R.id.edit_name);

        return view;
    }



}
