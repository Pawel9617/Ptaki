package com.example.ptaki;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class Home extends Fragment {

    int ilosc;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "LiczbaZnalezionych";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        sprawdźUprawnienia();
        TextView tVLiczba = (TextView)view.findViewById(R.id.tvHome);
        TextView tvCiekawostka = (TextView)view.findViewById(R.id.txtViewCiekawostkaTreść);
        int ciekawostka = new Random().nextInt(6)+1;
        switch (ciekawostka){
            case 1:
                tvCiekawostka.setText(R.string.fact1);
                break;
            case 2:
                tvCiekawostka.setText(R.string.fact2);
                break;
            case 3:
                tvCiekawostka.setText(R.string.fact3);
                break;
            case 4:
                tvCiekawostka.setText(R.string.fact4);
                break;
            case 5:
                tvCiekawostka.setText(R.string.fact5);
                break;
        }
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, 0);
        ilosc = sharedPreferences.getInt(TEXT, 0);
        String liczba = "Udało Ci się odnaleźć: " + ilosc;
        tVLiczba.setText(liczba);
        //Guzik do resetu postępów
        Button reset = (Button) view.findViewById(R.id.btnReset);
        reset.setVisibility(View.INVISIBLE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().apply();
            }
        });

        return view;
    }

    public void sprawdźUprawnienia(){
        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
        }
    }
}
