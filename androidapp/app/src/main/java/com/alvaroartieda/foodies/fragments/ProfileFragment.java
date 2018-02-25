package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.map.MapFragment;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class ProfileFragment extends Fragment {

    private final String MY_PREFERENCES = "My Preferennces";
    private final String ALREADY_USER = "already_user";
    private final String USER = "user";
    private final String DESCRIPTION = "description";
    private final String PROFILE_TYPE = "profile_type";

    private Button proceedBtn;
    private TextView userNameTxt;
    private RatingBar ratingBar;
    private ImageButton logoutBtn;
    private SharedPreferences prefs;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_profile, container, false);

        proceedBtn = view.findViewById(R.id.proceedWithOrder);
        userNameTxt = view.findViewById(R.id.userNameTxt);
        ratingBar = view.findViewById(R.id.ratingBar);
        logoutBtn = view.findViewById(R.id.logoutBtn);


       prefs = getActivity().getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        userNameTxt.setText(prefs.getString(USER,"Jon Doe"));
        ratingBar.setRating(3.0f + new Random().nextInt(50) / 25.0f);

        if(prefs.getString(PROFILE_TYPE,"C").equals("C")){
            proceedBtn.setText("CREATE");
            proceedBtn.setOnClickListener(viewBtn ->
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, SpecialityFragment.newInstance()).commit());
        }else{
            proceedBtn.setText("ORDER");
            proceedBtn.setOnClickListener(viewBtn ->
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, MapFragment.newInstance()).commit());
        }

        logoutBtn.setOnClickListener((viewBtn)->{
            logoutUser();
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, WhoAreYouFragment.newInstance()).commit();
        });
        return view;
    }

    private void logoutUser() {
        prefs.edit().clear().commit();
    }
}
