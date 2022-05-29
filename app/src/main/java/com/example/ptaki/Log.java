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

        String temp;

        temp = "BiegusArktyczny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item(temp, sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }

        temp = "BiegusDługoskrzydły";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus długoskrzydły", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusKarłowaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus karłowaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusKrzywodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus krzywodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusMalutki";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus malutki", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusMały";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus mały", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusMorski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus morski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusPłaskodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus płaskodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusPłowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus płowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusRdzawy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus rdzawy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusTundrowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus tundrowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusWielki";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus wielki", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BiegusZmienny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Biegus zmienny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Bielaczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Bielaczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Bielik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Bielik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BielikWschodni";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Bielik wschodni", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Birginiak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Birginiak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BłotniakŁąkowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Błotniak łąkowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BłotniakStawowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Błotniak stawowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BłotniakStepowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Błotniak stepowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BłotniakZbożowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Błotniak zbożowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BocianBiały";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Bocian biały", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BocianCzarny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Bocian czarny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Bogatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Bogatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BrodziecPiegowaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brodziec piegowaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BrodziecPiskliwy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brodziec piskliwy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BrodziecPlamisty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brodziec plamisty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BrodziecPławny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brodziec pławny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BrodziecŚniady";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brodziec śniady", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BrodziecŻółtonogi";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brodziec żółtonogi", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Brzęczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brzęczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Brzegówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Brzegówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BurzykBalearski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Burzyk balearski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BurzykPółnocny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Burzyk północny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BurzykSzary";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Burzyk szary", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "BurzykŻółtodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Burzyk żółtodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Cierlik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cierlik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Cierniówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cierniówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Cietrzew";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cietrzew", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Cyraneczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cyraneczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CyraneczkaKarolińska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cyraneczka karolińska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Cyranka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cyranka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CyrankaModroskrzydła";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Cyranka modroskrzydła", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czajka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czajka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzajkaStepowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czajka stepowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzajkaTowarzyska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czajka towarzyska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzaplaBiała";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czapla biała", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzaplaModronosa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czapla modronosa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzaplaNadobna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czapla nadobna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzaplaPurpurowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czapla purpurowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzaplaSiwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czapla siwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzaplaZłotawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czapla złotawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czarnogłówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czarnogłówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czarnowron";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czarnowron", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czeczotka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czeczotka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "CzeczotkaTundrowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czeczotka tundrowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czernica";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czernica", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czerniczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czerniczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czubatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czubatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Czyż";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Czyż", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Derkacz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Derkacz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Drop";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drop", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdaczekCiemny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozdaczek ciemny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdCzarnogardły";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozd czarnogardły", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdObrożny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozd obrożny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdOliwkowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozd oliwkowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdońPstry";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozdoń pstry", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdRdzawogardły";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozd rdzawogardły", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdRdzawoskrzydły";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozd rdzawoskrzydły", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DrozdRdzawy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drozd rdzawy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Droździk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Droździk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Drzemlik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Drzemlik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dubelt";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dubelt", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dudek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dudek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dymówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dymówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołBiałogrzbiety";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł białogrzbiety", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołBiałoszyi";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł białoszyi", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołCzarny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł czarny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołDuży";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł duży", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dzięciołek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięciołek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołŚredni";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł średni", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołTrójpalczasty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł trójpalczasty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołZielonosiwy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł zielonosiwy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzięciołZielony";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzięcioł zielony", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dzierlatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzierlatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzierzbaCzarnoczelna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzierzba czarnoczelna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzierzbaPustynna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzierzba pustynna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzierzbaRdzawosterna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzierzba rdzawosterna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "DzierzbaRudogłowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzierzba rudogłowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dziwonia";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dziwonia", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Dzwoniec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Dzwoniec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Edredon";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Edredon", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "FlamingRóżowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Flaming różowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Fulmar";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Fulmar", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gadożer";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gadożer", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gawron";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gawron", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gągoł";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gągoł", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gajówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gajówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gąsiorek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gąsiorek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gęgawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gęgawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "GęśBiałoczelna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gęś białoczelna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "GęsiówkaEgipska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gęsiówka egipska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "GęśKrótkodzioba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gęś krótkodzioba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "GęśMała";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gęś mała", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "GęśZbożowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gęś zbożowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Gil";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gil", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Głowienka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Głowienka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Głuptak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Głuptak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Głuszec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Głuszec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Głuszek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Głuszek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "GołąbMiejski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Gołąb miejski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Górniczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Górniczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Grubodziób";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Grubodziób", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Grzywacz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Grzywacz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Hełmiatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Hełmiatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "HubaraArabska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Hubara arabska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "IbisKasztanowaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ibis kasztanowaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Jarząbek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jarząbek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Jarzębatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jarzębatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "JaskółkaRudawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jaskółka rudawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Jastrząb";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jastrząb", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Jemiołuszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jemiołuszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Jer";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jer", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Jerzyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jerzyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "JerzykAlpejski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jerzyk alpejski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "JerzykBlady";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Jerzyk blady", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Junko";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Junko", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KalandraCzarna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kalandra czarna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KalandraSzara";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kalandra szara", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kamieniuszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kamieniuszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kamuszik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kamuszik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KaniaCzarna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kania czarna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KaniaRuda";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kania ruda", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kapturka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kapturka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Karliczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Karliczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kawka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kawka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KazarkaRdzawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kazarka rdzawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kląskawka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kląskawka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kobczyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kobczyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kobuz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kobuz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kokoszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kokoszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kopciuszek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kopciuszek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kormoran";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kormoran", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KormoranCzubaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kormoran czubaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KormoranMały";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kormoran mały", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kos";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kos", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kowalik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kowalik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Krakwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krakwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kraska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kraska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Krętogłów";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krętogłów", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Krogulec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krogulec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KrogulecKrótkonogi";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krogulec krótkonogi", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kropiatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kropiatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kruk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kruk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Krwawodziób";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krwawodziób", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KrzyżodzióbModrzewiowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krzyżodziób modrzewiowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KrzyżodzióbSosnowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krzyżodziób sosnowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KrzyżodzióbŚwierkowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krzyżodziób świerkowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Krzyżówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Krzyżówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kszyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kszyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kukułka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kukułka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kulczyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kulczyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KulikCienkodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kulik cienkodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KulikMniejszy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kulik mniejszy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "KulikWielki";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kulik wielki", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kulon";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kulon", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kurhannik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kurhannik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kuropatwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kuropatwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kwiczoł";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kwiczoł", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Kwokacz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Kwokacz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Lelek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Lelek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Lerka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Lerka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Lodowiec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Lodowiec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Lodówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Lodówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŁabędźCzarnodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łabędź czarnodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŁabędźKrzykliwy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łabędź krzykliwy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŁabędźNiemy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łabędź niemy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Łęczak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łęczak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Łozówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łozówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Łuskowiec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łuskowiec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Łyska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Łyska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Makolągwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Makolągwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Mandarynka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mandarynka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Markaczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Markaczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MarkaczkaAmerykańska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Markaczka amerykańska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Maskonur";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Maskonur", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Mazurek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mazurek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaBiałogłowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa białogłowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaBlada";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa blada", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaCienkodzioba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa cienkodzioba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaCzarnogłowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa czarnogłowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaDelawarska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa delawarska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaKaraibska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa karaibska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaMała";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa mała", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaModrodzioba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa modrodzioba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaObrożna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa obrożna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaPolarna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa polarna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaRomańska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa romańska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaSiodłata";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa siodłata", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaSiwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa siwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaSrebrzysta";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa srebrzysta", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaTrójpalczasta";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa trójpalczasta", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MewaŻółtonoga";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mewa żółtonoga", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Modraczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Modraczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Modraszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Modraszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Mornel";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mornel", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MuchołówkaBiałoszyja";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Muchołówka białoszyja", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MuchłówkaMała";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Muchłówka mała", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MuchołówkaSzara";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Muchołówka szara", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MuchołówkaŻałobna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Muchołówka żałobna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Mysikrólik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Mysikrólik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Myszołów";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Myszołów", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "MyszołówWłochaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Myszołów włochaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Nagórnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nagórnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "NawałnikBurzowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nawałnik burzowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "NawałnikDuży";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nawałnik duży", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "NurBiałodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nur białodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "NurCzarnoszyi";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nur czarnoszyi", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Nurnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nurnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Nurogęś";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nurogęś", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "NurRdzawoszyi";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nur rdzawoszyi", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Nurzyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nurzyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "NurzykPolarny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Nurzyk polarny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OceannikŻółtopłetwy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Oceannik żółtopłetwy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Ogorzałka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ogorzałka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OgorzałkaMała";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ogorzałka mała", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Ohar";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ohar", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Oknówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Oknówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Orlica";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orlica", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OrlikGrubodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orlik grubodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OrlikKrzykliwy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orlik krzykliwy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Orłosęp";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orłosęp", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Ortolan";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ortolan", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Orzechówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orzechówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OrzełCesarski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orzeł cesarski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Orzełek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orzełek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OrzełPrzedni";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orzeł przedni", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "OrzełStepowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Orzeł stepowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Osetnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Osetnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Ostrygojad";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ostrygojad", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PardwaMszarna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pardwa mszarna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pasterz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pasterz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Paszkot";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Paszkot", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PelikanKędzierzawy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pelikan kędzierzawy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PelikanRóżowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pelikan różowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PełzaczLeśny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pełzacz leśny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PełzaczOgrodowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pełzacz ogrodowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PerkozDwuczuby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Perkoz dwuczuby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Perkozek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Perkozek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PerkozGrubodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Perkoz grubodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PerkozRdzawoszyi";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Perkoz rdzawoszyi", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PerkozRogaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Perkoz rogaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Piaskowiec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Piaskowiec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Piecuszek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Piecuszek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Piegża";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Piegża", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pierwiosnek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pierwiosnek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pleszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pleszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PliszkaGórska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pliszka górska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PliszkaSiwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pliszka siwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PliszkaŻółta";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pliszka żółta", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pluszcz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pluszcz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Płaskonos";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Płaskonos", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PłatkonógPłaskodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Płatkonóg płaskodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PłatkonógSzydłodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Płatkonóg szydłodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PłochaczHalny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Płochacz halny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PłochaczSyberyjski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Płochacz syberyjski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Płomykówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Płomykówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Podgorzałka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Podgorzałka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Podróżniczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Podróżniczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pójdźka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pójdźka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pokląskwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pokląskwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PokrzewkaAksamitna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pokrzewka aksamitna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PokrzewkaWąsata";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pokrzewka wąsata", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pokrzywnica";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pokrzywnica", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pomurnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pomurnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Poświerka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Poświerka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Potrzeszcz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Potrzeszcz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Potrzos";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Potrzos", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Przepiórka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Przepiórka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Puchacz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Puchacz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pustułeczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pustułeczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pustułka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pustułka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Pustynnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Pustynnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Puszczyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Puszczyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PuszczykMszarny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Puszczyk mszarny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "PuszczykUralski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Puszczyk uralski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Raniuszek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Raniuszek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Raróg";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Raróg", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Remiz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Remiz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Rokitniczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rokitniczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Rożeniec";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rożeniec", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Rudzik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rudzik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybaczekSrokaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybaczek srokaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaBiałoczelna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa białoczelna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaBiałoskrzydła";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa białoskrzydła", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaBiałowąsa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa białowąsa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaCzarna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa czarna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaCzubata";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa czubata", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaKrótkodzioba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa krótkodzioba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaPopielata";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa popielata", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaRóżowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa różowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaRzeczna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa rzeczna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "RybitwaWielkodzioba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybitwa wielkodzioba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Rybołów";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rybołów", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Rycyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rycyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Rzepołuch";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Rzepołuch", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Samotnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Samotnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SępKasztanowaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sęp kasztanowaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SępPłowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sęp płowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Sierpówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sierpówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SieweczkaMongolska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sieweczka mongolska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SieweczkaMorska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sieweczka morska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SieweczkaObrożna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sieweczka obrożna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SieweczkaPustynna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sieweczka pustynna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SieweczkaRzeczna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sieweczka rzeczna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SiewkaSzara";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Siewka szara", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SiewkaZłota";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Siewka złota", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SiewkaZłotawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Siewka złotawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Siewnica";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Siewnica", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SikoraLazurowa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sikora lazurowa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SikoraUboga";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sikora uboga", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Siniak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Siniak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Siwerniak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Siwerniak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SkowrończykKrótkopalcowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Skowrończyk krótkopalcowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Skowronek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Skowronek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SkowronekBiałoskrzydły";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Skowronek białoskrzydły", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Słonka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Słonka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SłowikRdzawy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Słowik rdzawy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SłowikSyberyjski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Słowik syberyjski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SłowikSzary";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Słowik szary", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SokółSkalny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sokół skalny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SokółWędrowny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sokół wędrowny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Sosnówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sosnówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SowaJarzębata";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sowa jarzębata", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SowaŚnieżna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sowa śnieżna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Sójka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sójka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SójkaSyberyjska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sójka syberyjska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Sóweczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sóweczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Sroka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sroka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Srokosz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Srokosz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Sterniczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sterniczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SterniczkaJamajska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Sterniczka jamajska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Strepet";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Strepet", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Strumieniówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Strumieniówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Strzyżyk";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Strzyżyk", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Syczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Syczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Szablodziób";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szablodziób", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Szczudłak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szczudłak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Szczygieł";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szczygieł", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Szlachar";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szlachar", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "SzlamiecDługodzioby";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szlamiec długodzioby", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Szlamnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szlamnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Szpak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Szpak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Ścierwnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ścierwnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Ślepowron";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Ślepowron", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Śmieszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Śmieszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Śnieguła";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Śnieguła", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Śnieżka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Śnieżka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Śpiewak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Śpiewak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekDrzewny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek drzewny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekŁąkowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek łąkowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekNadmorski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek nadmorski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekPolny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek polny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekRdzawogardły";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek rdzawogardły", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekSzponiasty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek szponiasty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwiergotekTajgowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świergotek tajgowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Świerszczak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świerszczak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwierszczakMelodyjny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świerszczak melodyjny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Świstun";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstun", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunAmerykański";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstun amerykański", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaAłtańska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka ałtańska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaBrunatna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka brunatna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaGórska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka górska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaGrubodzioba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka grubodzioba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaIberyjska";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka iberyjska", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaLeśna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka leśna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaPółnocna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka północna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaZłotawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka złotawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŚwistunkaŻółtawa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Świstunka żółtawa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Tamaryszka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Tamaryszka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Terekia";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Terekia", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Trzciniak";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trzciniak", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Trzcinniczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trzcinniczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TrzcinniczekKaspijski";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trzcinniczek kaspijski", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Trzmielojad";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trzmielojad", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Trznadel";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadel", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TrznadelBiałogłowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadel białogłowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TrznadelCzarnogłowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadel czarnogłowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TrznadelCzubaty";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadel czubaty", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Trznadelek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadelek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TrznadelZłotawy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadel złotawy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TrznadelZłotobrewy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Trznadel złotobrewy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Turkan";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Turkan", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Turkawka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Turkawka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "TurkawkaWschodnia";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Turkawka wschodnia", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Uhla";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Uhla", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "UhlaGarbonosa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Uhla garbonosa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Uszatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Uszatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "UszatkaBłotna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Uszatka błotna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Warzęcha";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Warzęcha", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wąsatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wąsatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wieszczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wieszczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wilga";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wilga", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WireonekCzerwonooki";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wireonek czerwonooki", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Włochatka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Włochatka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wodniczka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wodniczka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wodnik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wodnik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wójcik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wójcik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Wróbel";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wróbel", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WróbelSkalny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wróbel skalny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WronaSiwa";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wrona siwa", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WydrzykDługosterny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wydrzyk długosterny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WydrzykOstrosterny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wydrzyk ostrosterny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WydrzykTęposterny";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wydrzyk tęposterny", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "WydrzykWielki";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Wydrzyk wielki", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zaganiacz";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zaganiacz", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ZaganiaczMały";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zaganiacz mały", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ZaganiaczSzczebiotliwy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zaganiacz szczebiotliwy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zaroślówka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zaroślówka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zausznik";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zausznik", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zięba";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zięba", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zielonka";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zielonka", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zimorodek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zimorodek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Zniczek";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Zniczek", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Żołna";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Żołna", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "Żuraw";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Żuraw", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŻurawStepowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Żuraw stepowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŻwirowiecŁąkowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Żwirowiec łąkowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }
        temp = "ŻwirowiecStepowy";
        Gatunek = temp + "Złapany";
        DataZłapania = temp + "DataZłapania";
        Miejsce = temp + "MiejsceZłapania";
        złapany = sharedPreferences.getBoolean(Gatunek, false);
        if(złapany){
            item = new Log_item("Żwirowiec stepowy", sharedPreferences.getString(DataZłapania, ""), sharedPreferences.getString(Miejsce, "Brak zapisanej lokalizacji"));
            logList.add(item);
        }


    }
}
