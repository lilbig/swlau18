package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.activities.MainActivity;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class SplashFragment extends Fragment {

    private ImageView logoStart;

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_splash, container, false);

        logoStart = view.findViewById(R.id.logo);

        setAnimation();

        //launchMainActivity();

        return view;
    }

    private void setAnimation(){
        Animation fade_in = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        Animation fade_out = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

        AnimationSet s = new AnimationSet(false);

        s.addAnimation(fade_in);
        fade_out.setStartOffset(fade_in.getDuration());
        s.addAnimation(fade_out);
        s.setFillAfter(true);

        logoStart.startAnimation(s);

        s.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                launchMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

    private void launchMainActivity(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();
    }




}
