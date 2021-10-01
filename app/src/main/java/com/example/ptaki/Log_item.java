package com.example.ptaki;

public class Log_item {

    private String mGatunek;
    private String mData;
    private String mLokalizacja;

    public Log_item(String Gatunek, String Data, String Lokalizacja){
        mGatunek = Gatunek;
        mData = Data;
        mLokalizacja = Lokalizacja;
    }

    public String getmGatunek(){
        return mGatunek;
    }

    public String getmData(){
        return mData;
    }

    public String getmLokalizacja(){
        return mLokalizacja;
    }
}
