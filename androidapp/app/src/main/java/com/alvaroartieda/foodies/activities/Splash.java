package com.alvaroartieda.foodies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.fragments.SplashFragment;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, SplashFragment.newInstance()).commit();
    }
}
