package com.alvaroartieda.foodies.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.fragments.ProfileFragment;
import com.alvaroartieda.foodies.fragments.WhoAreYouFragment;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class MainActivity extends AppCompatActivity {

    private final String MY_PREFERENCES = "My Preferennces";
    private final String ALREADY_USER = "already_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        boolean restoredUser = prefs.getBoolean(ALREADY_USER, false);

        if(!restoredUser){
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, WhoAreYouFragment.newInstance()).commit();
        }else{
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, ProfileFragment.newInstance()).commit();
        }


    }





}
