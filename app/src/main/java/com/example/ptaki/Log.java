package com.example.ptaki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Log extends Fragment {

    private RecyclerView mRecyclerView;
    private Log_adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Log_item> logList = new ArrayList<>();

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ZŁAPANY1 = "AlczykZłapany";
    public static final String DATAZŁAPANIA1 = "AlczykDataZłapania";
    public static final String MIEJSCE1 = "AlczykMiejsceZłapania";
    public static final String ZŁAPANY2 = "AlkaZłapany";
    public static final String DATAZŁAPANIA2 = "AlkaDataZłapania";
    public static final String MIEJSCE2 = "AlkaMiejsceZłapania";
    public static final String ZŁAPANY3 = "BączekZłapany";
    public static final String DATAZŁAPANIA3 = "BączekDataZłapania";
    public static final String MIEJSCE3 = "BączekMiejsceZłapania";
    public static final String ZŁAPANY4 = "BąkZłapany";
    public static final String DATAZŁAPANIA4 = "BąkDataZłapania";
    public static final String MIEJSCE4 = "BąkMiejsceZłapania";
    public static final String ZŁAPANY5 = "BatalionZłapany";
    public static final String DATAZŁAPANIA5 = "BatalionDataZłapania";
    public static final String MIEJSCE5 = "BatalionMiejsceZłapania";
    public static final String ZŁAPANY6 = "BażantZłapany";
    public static final String DATAZŁAPANIA6 = "BażantDataZłapania";
    public static final String MIEJSCE6 = "BażantMiejsceZłapania";
    public static final String ZŁAPANY7 = "BekasikZłapany";
    public static final String DATAZŁAPANIA7 = "BekasikDataZłapania";
    public static final String MIEJSCE7 = "BekasikMiejsceZłapania";
    public static final String ZŁAPANY8 = "BerniklaBiałolicaZłapany";
    public static final String DATAZŁAPANIA8 = "BerniklaBiałolicaDataZłapania";
    public static final String MIEJSCE8 = "BerniklaBiałolicaMiejsceZłapania";
    public static final String ZŁAPANY9 = "BerniklaKanadyjskaZłapany";
    public static final String DATAZŁAPANIA9 = "BerniklaKanadyjskaDataZłapania";
    public static final String MIEJSCE9 = "BerniklaKanadyjskaMiejsceZłapania";
    public static final String ZŁAPANY10 = "BerniklaObrożnaZłapany";
    public static final String DATAZŁAPANIA10 = "BerniklaObrożnaDataZłapania";
    public static final String MIEJSCE10 = "BerniklaObrożnaMiejsceZłapania";
    public static final String ZŁAPANY11 = "BerniklaRdzawoszyjaZłapany";
    public static final String DATAZŁAPANIA11 = "BerniklaRdzawoszyjaDataZłapania";
    public static final String MIEJSCE11 = "BerniklaRdzawoszyjaMiejsceZłapania";
    public static final String ZŁAPANY12 = "BiałorzytkaZłapany";
    public static final String DATAZŁAPANIA12 = "BiałorzytkaDataZłapania";
    public static final String MIEJSCE12 = "BiałorzytkaMiejsceZłapania";
    public static final String ZŁAPANY13 = "BiałorzytkaPłowaZłapany";
    public static final String DATAZŁAPANIA13 = "BiałorzytkaPłowaDataZłapania";
    public static final String MIEJSCE13 = "BiałorzytkaPłowaMiejsceZłapania";
    public static final String ZŁAPANY14 = "BiałorzytkaPstraZłapany";
    public static final String DATAZŁAPANIA14 = "BiałorzytkaPstraDataZłapania";
    public static final String MIEJSCE14 = "BiałorzytkaPstraMiejsceZłapania";
    public static final String ZŁAPANY15 = "BiałorzytkaPustynnaZłapany";
    public static final String DATAZŁAPANIA15 = "BiałorzytkaPustynnaDataZłapania";
    public static final String MIEJSCE15 = "BiałorzytkaPustynnaMiejsceZłapania";
    public static final String ZŁAPANY16 = "BiałorzytkaRdzawaZłapany";
    public static final String DATAZŁAPANIA16 = "BiałorzytkaRdzawaDataZłapania";
    public static final String MIEJSCE16 = "BiałorzytkaRdzawaMiejsceZłapania";
    public static final String ZŁAPANY17 = "BiałorzytkaSaharyjskaZłapany";
    public static final String DATAZŁAPANIA17 = "BiałorzytkaSaharyjskaDataZłapania";
    public static final String MIEJSCE17 = "BiałorzytkaSaharyjskaMiejsceZłapania";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.log, container, false);
        wczytajDane();
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new Log_adapter(logList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Log_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), Log_edycja.class);
                intent.putExtra("Gatunek", logList.get(position).getmGatunek());
                intent.putExtra("Data", logList.get(position).getmData());
                intent.putExtra("Lokalizacja", logList.get(position).getmLokalizacja());
                startActivity(intent);
                mAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    public void wczytajDane(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        boolean złapany;
        Log_item item;
        złapany = sharedPreferences.getBoolean(ZŁAPANY1, false);
        if(złapany){
            item = new Log_item("Alczyk", sharedPreferences.getString(DATAZŁAPANIA1, ""), sharedPreferences.getString(MIEJSCE1, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY2, false);
        if(złapany){
            item = new Log_item("Alka", sharedPreferences.getString(DATAZŁAPANIA2, ""), sharedPreferences.getString(MIEJSCE2, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY3, false);
        if(złapany){
            item = new Log_item("Bączek", sharedPreferences.getString(DATAZŁAPANIA3, ""), sharedPreferences.getString(MIEJSCE3, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY4, false);
        if(złapany){
            item = new Log_item("Bąk", sharedPreferences.getString(DATAZŁAPANIA4, ""), sharedPreferences.getString(MIEJSCE4, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY5, false);
        if(złapany){
            item = new Log_item("Batalion", sharedPreferences.getString(DATAZŁAPANIA5, ""), sharedPreferences.getString(MIEJSCE5, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY6, false);
        if(złapany){
            item = new Log_item("Bażant", sharedPreferences.getString(DATAZŁAPANIA6, ""), sharedPreferences.getString(MIEJSCE6, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY7, false);
        if(złapany){
            item = new Log_item("Bekasik", sharedPreferences.getString(DATAZŁAPANIA7, ""), sharedPreferences.getString(MIEJSCE7, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY8, false);
        if(złapany){
            item = new Log_item("Bernikla białolica", sharedPreferences.getString(DATAZŁAPANIA8, ""), sharedPreferences.getString(MIEJSCE8, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY9, false);
        if(złapany){
            item = new Log_item("Bernikla kanadyjska", sharedPreferences.getString(DATAZŁAPANIA9, ""), sharedPreferences.getString(MIEJSCE9, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY10, false);
        if(złapany){
            item = new Log_item("Bernikla obrożna", sharedPreferences.getString(DATAZŁAPANIA10, ""), sharedPreferences.getString(MIEJSCE10, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY11, false);
        if(złapany){
            item = new Log_item("Bernikla rdzawoszyja", sharedPreferences.getString(DATAZŁAPANIA11, ""), sharedPreferences.getString(MIEJSCE11, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY12, false);
        if(złapany){
            item = new Log_item("Białorzytka", sharedPreferences.getString(DATAZŁAPANIA12, ""), sharedPreferences.getString(MIEJSCE12, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY13, false);
        if(złapany){
            item = new Log_item("Białorzytka płowa", sharedPreferences.getString(DATAZŁAPANIA13, ""), sharedPreferences.getString(MIEJSCE13, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY14, false);
        if(złapany){
            item = new Log_item("Białorzytka pstra", sharedPreferences.getString(DATAZŁAPANIA14, ""), sharedPreferences.getString(MIEJSCE14, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY15, false);
        if(złapany){
            item = new Log_item("Białorzytka pustynna", sharedPreferences.getString(DATAZŁAPANIA15, ""), sharedPreferences.getString(MIEJSCE15, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY16, false);
        if(złapany){
            item = new Log_item("Białorzytka rdzawa", sharedPreferences.getString(DATAZŁAPANIA16, ""), sharedPreferences.getString(MIEJSCE16, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        złapany = sharedPreferences.getBoolean(ZŁAPANY17, false);
        if(złapany){
            item = new Log_item("Białorzytka saharyjska", sharedPreferences.getString(DATAZŁAPANIA17, ""), sharedPreferences.getString(MIEJSCE17, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        //Nowy Styl
        String Gatunek = "BiałozórZłapany";
        String DataZłapania = "BiałozórDataZłapania";
        String Miejsce = "BiałozórMiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Białozór", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }

        String temp = "BiegusArktyczny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus Arktyczny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
    }
}
