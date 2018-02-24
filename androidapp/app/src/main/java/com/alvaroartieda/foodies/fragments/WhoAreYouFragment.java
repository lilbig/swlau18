package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alvaroartieda.foodies.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class WhoAreYouFragment extends Fragment {

    private final String MY_PREFERENCES = "My Preferennces";
    private final String ALREADY_USER = "already_user";
    private final String USER = "user";
    private final String DESCRIPTION = "description";
    private final String PROFILE_TYPE = "profile_type";
    private EditText editName;
    private EditText editDescription;

    private Button buttonChef;
    private Button buttonGourmand;

    public static WhoAreYouFragment newInstance() {
        WhoAreYouFragment fragment = new WhoAreYouFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_whoareyou, container, false);

        editName = view.findViewById(R.id.edit_name);
        editDescription = view.findViewById(R.id.edit_description);

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        buttonChef = view.findViewById(R.id.button_chef);
        buttonGourmand = view.findViewById(R.id.button_gourmand);


        buttonChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                if (name.matches("") || description.matches("") ) {
                    Toast.makeText(getActivity(), "You did not enter a username or description", Toast.LENGTH_SHORT).show();
                } else {

                    prefs.edit().putBoolean(ALREADY_USER, true).apply();
                    prefs.edit().putString(USER, name).apply();
                    prefs.edit().putString(DESCRIPTION, name).apply();
                    prefs.edit().putString(PROFILE_TYPE, "C").apply();
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, SpecialityFragment.newInstance()).commit();

                }
            }
        });

        buttonGourmand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String description = editDescription.getText().toString();
                if (name.matches("") || description.matches("") ) {
                    Toast.makeText(getActivity(), "You did not enter a username or description", Toast.LENGTH_SHORT).show();
                } else {

                    prefs.edit().putBoolean(ALREADY_USER, true).apply();
                    prefs.edit().putString(USER, name).apply();
                    prefs.edit().putString(DESCRIPTION, name).apply();
                    prefs.edit().putString(PROFILE_TYPE, "G").apply();

                }
            }
        });


        return view;
    }





}
