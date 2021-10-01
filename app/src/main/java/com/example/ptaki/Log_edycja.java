package com.example.ptaki;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Log_edycja extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String SHARED_PREFS = "sharedPrefs";

    TextView txtViewGatunek, txtViewData;
    String sGatunek, sData, wybranaData, sLokalizacja, sOrginalnaLokalizacja;
    Button bUsuń;
    EditText editTextLokalizacja;
    boolean zmiana;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_edycja);

        zmiana = false;
        txtViewGatunek = (TextView) findViewById(R.id.txtViewLogEdycjaGatunek2);
        txtViewData = (TextView) findViewById(R.id.txtViewLogEdycjaData2);
        editTextLokalizacja = (EditText) findViewById(R.id.editTextLogEdycjaMiejsce2);
        bUsuń = (Button)findViewById(R.id.usuń);
        bUsuń.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                int liczba = sharedPreferences.getInt("LiczbaZnalezionych", 1);
                liczba--;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (sGatunek.equals("Alczyk")) {
                    editor.putString("AlczykStatus", "Nie złapano");
                    editor.putBoolean("AlczykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Alka")) {
                    editor.putString("AlkaStatus", "Nie złapano");
                    editor.putBoolean("AlkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bączek")) {
                    editor.putString("BączekStatus", "Nie złapano");
                    editor.putBoolean("BączekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bąk")) {
                    editor.putString("BąkStatus", "Nie złapano");
                    editor.putBoolean("BąkZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Batalion")) {
                    editor.putString("BatalionStatus", "Nie złapano");
                    editor.putBoolean("BatalionZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bażant")) {
                    editor.putString("BażantStatus", "Nie złapano");
                    editor.putBoolean("BażantZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bekasik")) {
                    editor.putString("BekasikStatus", "Nie złapano");
                    editor.putBoolean("BekasikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bernikla białolica")) {
                    editor.putString("BerniklaBiałolicaStatus", "Nie złapano");
                    editor.putBoolean("BerniklaBiałolicaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bernikla kanadyjska")) {
                    editor.putString("BerniklaKanadyjskaStatus", "Nie złapano");
                    editor.putBoolean("BerniklaKanadyjskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bernikla obrożna")) {
                    editor.putString("BerniklaObrożnaStatus", "Nie złapano");
                    editor.putBoolean("BerniklaObrożnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bernikla rdzawoszyja")) {
                    editor.putString("BerniklaRdzawoszyjaStatus", "Nie złapano");
                    editor.putBoolean("BerniklaRdzawoszyjaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Białorzytka")) {
                    editor.putString("BiałorzytkaStatus", "Nie złapano");
                    editor.putBoolean("BiałorzytkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Białorzytka płowa")) {
                    editor.putString("BiałorzytkaPłowaStatus", "Nie złapano");
                    editor.putBoolean("BiałorzytkaPłowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Białorzytka pstra")) {
                    editor.putString("BiałorzytkaPstraStatus", "Nie złapano");
                    editor.putBoolean("BiałorzytkaPstraZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Białorzytka pustynna")) {
                    editor.putString("BiałorzytkaPustynnaStatus", "Nie złapano");
                    editor.putBoolean("BiałorzytkaPustynnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Białorzytka rdzawa")) {
                    editor.putString("BiałorzytkaRdzawaStatus", "Nie złapano");
                    editor.putBoolean("BiałorzytkaRdzawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Białorzytka saharyjska")) {
                    editor.putString("BiałorzytkaSaharyjskaStatus", "Nie złapano");
                    editor.putBoolean("BiałorzytkaSaharyjskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                editor.apply();
                finish();
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            sGatunek = extras.getString("Gatunek");
            sData = extras.getString("Data");
            wybranaData = sData;
            sLokalizacja = extras.getString("Lokalizacja");
            sOrginalnaLokalizacja = extras.getString("Lokalizacja");
        }
        txtViewGatunek.setText(sGatunek);
        txtViewData.setText(sData);
        txtViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        editTextLokalizacja.setText(sLokalizacja);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        wybranaData = DateFormat.getDateInstance().format(c.getTime());
        txtViewData.setText(wybranaData);
        zmiana = true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setTitle("Zapis");
        alert.setMessage("Chcesz zapisać zmiany?");
        alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(zmiana || !editTextLokalizacja.getText().toString().equals(sOrginalnaLokalizacja)) {
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (sGatunek.equals("Alczyk")) {
                        editor.putString("AlczykDataZłapania", wybranaData);
                        editor.putString("AlczykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Alka")){
                        editor.putString("AlkaDataZłapania", wybranaData);
                        editor.putString("AlkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bączek")){
                        editor.putString("BączekDataZłapania", wybranaData);
                        editor.putString("BączekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bąk")){
                        editor.putString("BąkDataZłapania", wybranaData);
                        editor.putString("BąkMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Batalion")){
                        editor.putString("BatalionDataZłapania", wybranaData);
                        editor.putString("BatalionMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bażant")){
                        editor.putString("BażantDataZłapania", wybranaData);
                        editor.putString("BażantMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bekasik")){
                        editor.putString("BekasikDataZłapania", wybranaData);
                        editor.putString("BekasikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bernikla białolica")){
                        editor.putString("BerniklaBiałolicaDataZłapania", wybranaData);
                        editor.putString("BerniklaBiałolicaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bernikla kanadyjska")){
                        editor.putString("BerniklaKanadyjskaDataZłapania", wybranaData);
                        editor.putString("BerniklaKanadyjskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bernikla obrożna")){
                        editor.putString("BerniklaObrożnaDataZłapania", wybranaData);
                        editor.putString("BerniklaObrożnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bernikla rdzawoszyja")){
                        editor.putString("BerniklaRdzawoszyjaDataZłapania", wybranaData);
                        editor.putString("BerniklaRdzawoszyjaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Białorzytka")){
                        editor.putString("BiałorzytkaDataZłapania", wybranaData);
                        editor.putString("BiałorzytkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Białorzytka płowa")){
                        editor.putString("BiałorzytkaPłowaDataZłapania", wybranaData);
                        editor.putString("BiałorzytkaPłowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Białorzytka pstra")){
                        editor.putString("BiałorzytkaPstraDataZłapania", wybranaData);
                        editor.putString("BiałorzytkaPstraMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Białorzytka pustynna")){
                        editor.putString("BiałorzytkaPustynnaDataZłapania", wybranaData);
                        editor.putString("BiałorzytkaPustynnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Białorzytka rdzawa")){
                        editor.putString("BiałorzytkaRdzawaDataZłapania", wybranaData);
                        editor.putString("BiałorzytkaRdzawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Białorzytka saharyjska")){
                        editor.putString("BiałorzytkaSaharyjskaDataZłapania", wybranaData);
                        editor.putString("BiałorzytkaSaharyjskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    editor.apply();
                    Log_edycja.super.onBackPressed();
                }
                else
                    Log_edycja.super.onBackPressed();
            }
        });
        alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log_edycja.super.onBackPressed();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }
}
