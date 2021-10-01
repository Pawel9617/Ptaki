package com.example.ptaki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Achievements extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";

    ArrayList<String> listaOsiągnięć = new ArrayList<>();
    ArrayAdapter<String> listViewAdapter;
    int liczbaOsiągnieć = 5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.achievements, container, false);
        wczytajDane();
        wczytajOsiągnięcia();
        ListView listView = (ListView) view.findViewById(R.id.AchievementsView);
        listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaOsiągnięć);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sprawdźOsiągnięcie(position);
            }
        });
        return view;
    }

    public void wczytajDane(){
        listaOsiągnięć.clear();
        listaOsiągnięć.add("Nowicjusz");
        listaOsiągnięć.add("Amator");
        listaOsiągnięć.add("Czeladnik");
        listaOsiągnięć.add("Profesjonalista");
        listaOsiągnięć.add("Mistrz");
    }

    public void wczytajOsiągnięcia(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.getInt("LiczbaZnalezionych", 0) >= 5){
            editor.putBoolean("Osiągnięcie1", true);
        }
        if(sharedPreferences.getInt("LiczbaZnalezionych", 0) >= 25){
            editor.putBoolean("Osiągnięcie2", true);
        }
        if(sharedPreferences.getInt("LiczbaZnalezionych", 0) >= 100){
            editor.putBoolean("Osiągnięcie3", true);
        }
        if(sharedPreferences.getInt("LiczbaZnalezionych", 0) >= 250){
            editor.putBoolean("Osiągnięcie4", true);
        }
        if(sharedPreferences.getInt("LiczbaZnalezionych", 0) >= 400){
            editor.putBoolean("Osiągnięcie5", true);
        }
        editor.apply();
    }

    public void sprawdźOsiągnięcie(int pozycja){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switch (pozycja){
            case 0 :
                if(sharedPreferences.getBoolean("Osiągnięcie1", false)){
                    Toast.makeText(getActivity(), "Gratulację! Udało Ci się odnaleźć 5 gatunków.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int i = 5 - sharedPreferences.getInt("LiczbaZnalezionych", 0);
                    Toast.makeText(getActivity(), "Nie udało Ci się odnaleźć jeszcze 5 gatunków. Potrzebujesz jeszcze " + i, Toast.LENGTH_SHORT).show();
                }
                break;
            case 1 :
                if(sharedPreferences.getBoolean("Osiągnięcie2", false)){
                    Toast.makeText(getActivity(), "Gratulację! Udało Ci się odnaleźć 25 gatunków.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int i = 25 - sharedPreferences.getInt("LiczbaZnalezionych", 0);
                    Toast.makeText(getActivity(), "Nie udało Ci się odnaleźć jeszcze 25 gatunków. Potrzebujesz jeszcze " + i, Toast.LENGTH_SHORT).show();
                }
                break;
            case 2 :
                if(sharedPreferences.getBoolean("Osiągnięcie3", false)){
                    Toast.makeText(getActivity(), "Gratulację! Udało Ci się odnaleźć 100 gatunków.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int i = 100 - sharedPreferences.getInt("LiczbaZnalezionych", 0);
                    Toast.makeText(getActivity(), "Nie udało Ci się odnaleźć jeszcze 100 gatunków. Potrzebujesz jeszcze " + i, Toast.LENGTH_SHORT).show();
                }
                break;
            case 3 :
                if(sharedPreferences.getBoolean("Osiągnięcie4", false)){
                    Toast.makeText(getActivity(), "Gratulację! Udało Ci się odnaleźć 250 gatunków.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int i = 250 - sharedPreferences.getInt("LiczbaZnalezionych", 0);
                    Toast.makeText(getActivity(), "Nie udało Ci się odnaleźć jeszcze 250 gatunków. Potrzebujesz jeszcze " + i, Toast.LENGTH_SHORT).show();
                }
                break;
            case 4 :
                if(sharedPreferences.getBoolean("Osiągnięcie5", false)){
                    Toast.makeText(getActivity(), "Gratulację! Udało Ci się odnaleźć 400 gatunków.", Toast.LENGTH_SHORT).show();
                }
                else{
                    int i = 400 - sharedPreferences.getInt("LiczbaZnalezionych", 0);
                    Toast.makeText(getActivity(), "Nie udało Ci się odnaleźć jeszcze 400 gatunków. Potrzebujesz jeszcze " + i, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
