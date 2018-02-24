package com.alvaroartieda.foodies.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.fragments.ProfileFragment;
import com.alvaroartieda.foodies.fragments.WhoAreYouFragment;
import com.alvaroartieda.foodies.map.MapFragment;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SharedPreferences prefs = getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        //getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, WhoAreYouFragment.newInstance()).commit();
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, MapFragment.newInstance()).commit();



    }
}
