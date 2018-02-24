package com.alvaroartieda.foodies.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaroartieda.foodies.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by alvaroartieda on 24.02.18.
 */

public class SpecialityFragment extends Fragment {

    private final String MY_PREFERENCES = "My Preferennces";
    private final String SPECIALITY = "Speciality";
    private final String PRICE = "price";

    private Button buttonItalian;
    private Button buttonMorocco;
    private Button buttonSwiss;
    private Button buttonAmerican;
    private Button buttonPeruvian;

    private EditText priceItalian;
    private EditText priceMorocco;
    private EditText priceSwiss;
    private EditText priceAmerican;
    private EditText pricePeruvian;


    public static SpecialityFragment newInstance() {
        SpecialityFragment fragment = new SpecialityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_speciality, container, false);

        buttonItalian = view.findViewById(R.id.button_italian);
        buttonMorocco = view.findViewById(R.id.button_moroccan);
        buttonSwiss = view.findViewById(R.id.button_swiss);
        buttonAmerican = view.findViewById(R.id.button_american);
        buttonPeruvian = view.findViewById(R.id.button_peruvian);

        priceItalian = view.findViewById(R.id.price_italian);
        priceMorocco = view.findViewById(R.id.price_maroc);
        priceSwiss = view.findViewById(R.id.price_swiss);
        priceAmerican = view.findViewById(R.id.price_american);
        pricePeruvian = view.findViewById(R.id.price_peruvian);

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFERENCES, MODE_PRIVATE);

        buttonItalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String priceText = (priceItalian.getText().toString().equals("") || priceItalian.getText() == null) ? "0" : priceItalian.getText().toString();
                int price = Integer.valueOf(priceText);
                if (priceText.matches("0")) {
                    Toast.makeText(getActivity(), "Please enter a Price", Toast.LENGTH_SHORT).show();
                } else {
                    prefs.edit().putString(SPECIALITY, "Italian").apply();
                    prefs.edit().putInt(PRICE, price).apply();
                    getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, PictureLocation.newInstance()).commit();
                }
            }
        });

        buttonMorocco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String priceText = (priceMorocco.getText().toString().equals("") || priceMorocco.getText() == null) ? "0" : priceItalian.getText().toString();
                int price = Integer.parseInt(priceText);
                if (priceText.matches("0")) {
                    Toast.makeText(getActivity(), "Please enter a Price", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

        buttonSwiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String priceText = (priceSwiss.getText().toString().equals("") || priceSwiss.getText() == null) ? "0" : priceSwiss.getText().toString();
                int price = Integer.parseInt(priceText);
                if (priceText.matches("0")) {
                    Toast.makeText(getActivity(), "Please enter a Price", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

        buttonAmerican.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String priceText = (priceAmerican.getText().toString().equals("") || priceAmerican.getText() == null) ? "0" : priceAmerican.getText().toString();
                int price = Integer.parseInt(priceText);
                if (priceText.matches("0")) {
                    Toast.makeText(getActivity(), "Please enter a Price", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });

        buttonPeruvian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String priceText = (pricePeruvian.getText().toString().equals("") || pricePeruvian.getText() == null) ? "0" : pricePeruvian.getText().toString();
                int price = Integer.parseInt(priceText);
                if (priceText.matches("0")) {
                    Toast.makeText(getActivity(), "Please enter a Price", Toast.LENGTH_SHORT).show();
                } else {


                }
            }
        });

        return view;
    }
}
