package com.example.ptaki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptaki.GpsTracker;
import com.example.ptaki.R;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Gatunek extends AppCompatActivity implements  View.OnClickListener{

    public static final String SHARED_PREFS = "sharedPrefs";
    /*public static final String TEXT = "BączekStatus";
    public static final String TEXT2 = "BączekNotatka";
    public static final String TEXT3 = "BączekZłapany";
    public static final String TEXT4 = "LiczbaZnalezionych";
    public static final String DATAZŁAPANIA = "BączekDataZłapania";
    public static final String LOKALIZACJA = "BączekMiejsceZłapania";
    public static final String MAZDJĘCIE = "BączekMaZdjęcie";
    public static final String ZDJĘCIE = "BączekZdjęcie;";*/

    private static final int GALLERY_REQUEST = 9;

    private ImageView imageView, imgViewGatunek;

    private String GATUNEK, TEXT, TEXT2, TEXT3, TEXT4, DATAZŁAPANIA, LOKALIZACJA, MAZDJĘCIE, ZDJĘCIE;

    Button bMamGo;
    TextView tvStatus, tvTytuł, tvOpis;
    String status;
    String notatka;
    int IlośćZłapanych;
    boolean Złapany;

    private GpsTracker gpsTracker;
    private String lokalizacja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatunek);
        tvTytuł = (TextView)findViewById(R.id.txtViewGatunekTytuł);
        tvOpis = (TextView)findViewById(R.id.txtViewGatunekOpis);
        tvStatus = (TextView)findViewById(R.id.txtViewStatus);
        bMamGo = (Button)findViewById(R.id.bMamGo);
        bMamGo.setOnClickListener(this);
        imgViewGatunek = findViewById(R.id.imgViewGatunekGłówne);
        imageView = findViewById(R.id.imgViewGatunekUżytkownika);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromGallery();
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GATUNEK = extras.getString("Gatunek");
            TEXT = extras.getString("Status");
            TEXT2 = extras.getString("Notatka");
            TEXT3 = extras.getString("Złapany");
            TEXT4 = extras.getString("LiczbaZnalezionych");
            DATAZŁAPANIA = extras.getString("DataZłapania");
            LOKALIZACJA = extras.getString("Lokalizacja");
            MAZDJĘCIE = extras.getString("MaZdjęcie");
            ZDJĘCIE = extras.getString("Zdjęcie");
        }
        loadData();
        updateViews();
        setViews();
        if(Złapany)
            bMamGo.setVisibility(View.INVISIBLE);
    }

    private void getImageFromGallery(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(MAZDJĘCIE, true);
            editor.putString(ZDJĘCIE, imageUri.toString());
            editor.apply();
        };
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bMamGo && !Złapany){
            Calendar calendar = Calendar.getInstance();
            String dataZłapania = DateFormat.getDateInstance().format(calendar.getTime());
            getLocation(v);
            tvStatus.setText("Złapano");
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            IlośćZłapanych = sharedPreferences.getInt(TEXT4, 0);
            IlośćZłapanych++;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXT, "Złapano!");
            editor.putBoolean(TEXT3, true);
            editor.putInt(TEXT4, IlośćZłapanych);
            editor.putString(DATAZŁAPANIA, dataZłapania);
            editor.putString(LOKALIZACJA, lokalizacja);
            editor.apply();
            bMamGo.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText editText = findViewById(R.id.GatunekNotatka);
        String notatka = editText.getText().toString();
        editor.putString(TEXT2, notatka);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        status = sharedPreferences.getString(TEXT, "Nie złapano");
        notatka = sharedPreferences.getString(TEXT2, "");
        Złapany = sharedPreferences.getBoolean(TEXT3, false);
        Uri uri = Uri.parse(sharedPreferences.getString(ZDJĘCIE, ""));
        if(sharedPreferences.getBoolean(MAZDJĘCIE, false)){
            imageView.setImageURI(null);
            imageView.setImageURI(uri);
        }
    }

    public void updateViews() {
        tvStatus.setText(status);
        EditText editText = findViewById(R.id.GatunekNotatka);
        editText.setText(notatka);
    }

    public void getLocation(View view){
        gpsTracker = new GpsTracker(Gatunek.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                String kraj = addresses.get(0).getCountryName();
                String miasto = addresses.get(0).getLocality();
                lokalizacja = miasto + ", " + kraj;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    private void setViews(){
        if(GATUNEK.equals("Białozór")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bialozor));
            tvTytuł.setText(R.string.bialozor_tytuł);
            tvOpis.setText(R.string.bialozor_opis);
        }
        if(GATUNEK.equals("BiegusArktyczny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_arktyczny));
            tvTytuł.setText(R.string.biegus_arktyczny_tytuł);
            tvOpis.setText(R.string.biegus_arktyczny_opis);
        }
        if(GATUNEK.equals("BiegusDługoskrzydły")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_dlugoskrzydly));
            tvTytuł.setText(R.string.biegus_dlugoskrzydly_tytuł);
            tvOpis.setText(R.string.biegus_dlugoskrzydly_opis);
        }
        if(GATUNEK.equals("BiegusKarłowaty")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_karlowaty));
            tvTytuł.setText(R.string.biegus_karlowaty_tytuł);
            tvOpis.setText(R.string.biegus_karlowaty_opis);
        }
        if(GATUNEK.equals("BiegusKrzywodzioby")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_krzywodzioby));
            tvTytuł.setText(R.string.biegus_krzywodzioby_tytuł);
            tvOpis.setText(R.string.biegus_krzywodzioby_opis);
        }
        if(GATUNEK.equals("BiegusMalutki")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_malutki));
            tvTytuł.setText(R.string.biegus_malutki_tytuł);
            tvOpis.setText(R.string.biegus_malutki_opis);
        }
        if(GATUNEK.equals("BiegusMały")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_maly));
            tvTytuł.setText(R.string.biegus_maly_tytuł);
            tvOpis.setText(R.string.biegus_maly_opis);
        }
        if(GATUNEK.equals("BiegusMorski")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_morski));
            tvTytuł.setText(R.string.biegus_morski_tytuł);
            tvOpis.setText(R.string.biegus_morski_opis);
        }
        if(GATUNEK.equals("BiegusPłaskodzioby")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_plaskodzioby));
            tvTytuł.setText(R.string.biegus_plaskodzioby_tytuł);
            tvOpis.setText(R.string.biegus_plaskodzioby_opis);
        }
        if(GATUNEK.equals("BiegusPłowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_plowy));
            tvTytuł.setText(R.string.biegus_plowy_tytuł);
            tvOpis.setText(R.string.biegus_plowy_opis);
        }
        if(GATUNEK.equals("BiegusRdzawy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_rdzawy));
            tvTytuł.setText(R.string.biegus_rdzawy_tytuł);
            tvOpis.setText(R.string.biegus_rdzawy_opis);
        }
        if(GATUNEK.equals("BiegusTundrowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_tundrowy));
            tvTytuł.setText(R.string.biegus_tundrowy_tytuł);
            tvOpis.setText(R.string.biegus_tundrowy_opis);
        }
        if(GATUNEK.equals("BiegusWielki")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_wielki));
            tvTytuł.setText(R.string.biegus_wielki_tytuł);
            tvOpis.setText(R.string.biegus_wielki_opis);
        }
        if(GATUNEK.equals("BiegusZmienny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.biegus_zmienny));
            tvTytuł.setText(R.string.biegus_zmienny_tytuł);
            tvOpis.setText(R.string.biegus_zmienny_opis);
        }
        if(GATUNEK.equals("Bielaczek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bielaczek));
            tvTytuł.setText(R.string.bielaczek_tytuł);
            tvOpis.setText(R.string.bielaczek_opis);
        }
        if(GATUNEK.equals("Bielik")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bielik));
            tvTytuł.setText(R.string.bielik_tytuł);
            tvOpis.setText(R.string.bielik_opis);
        }
        if(GATUNEK.equals("BielikWschodni")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bielik_wschodni));
            tvTytuł.setText(R.string.bielik_wschodni_tytuł);
            tvOpis.setText(R.string.bielik_wschodni_opis);
        }
        if(GATUNEK.equals("Birginiak")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.birginiak));
            tvTytuł.setText(R.string.birginiak_tytuł);
            tvOpis.setText(R.string.birginiak_opis);
        }
        if(GATUNEK.equals("BłotniakŁąkowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.blotniak_lakowy));
            tvTytuł.setText(R.string.blotniak_lakowy_tytuł);
            tvOpis.setText(R.string.blotniak_lakowy_opis);
        }
        if(GATUNEK.equals("BłotniakStawowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.blotniak_stawowy));
            tvTytuł.setText(R.string.blotniak_stawowy_tytuł);
            tvOpis.setText(R.string.blotniak_stawowy_opis);
        }
        if(GATUNEK.equals("BłotniakStepowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.blotniak_stepowy));
            tvTytuł.setText(R.string.blotniak_stepowy_tytuł);
            tvOpis.setText(R.string.blotniak_stepowy_opis);
        }
        if(GATUNEK.equals("BłotniakZbożowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.blotniak_zbozowy));
            tvTytuł.setText(R.string.blotniak_zbozowy_tytuł);
            tvOpis.setText(R.string.blotniak_zbozowy_opis);
        }
        if(GATUNEK.equals("BocianBiały")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bocian_bialy));
            tvTytuł.setText(R.string.bocian_bialy_tytuł);
            tvOpis.setText(R.string.bocian_bialy_opis);
        }
        if(GATUNEK.equals("BocianCzarny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bocian_czarny));
            tvTytuł.setText(R.string.bocian_czarny_tytuł);
            tvOpis.setText(R.string.bocian_czarny_opis);
        }
        if(GATUNEK.equals("Bogatka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.bogatka));
            tvTytuł.setText(R.string.bogatka_tytuł);
            tvOpis.setText(R.string.bogatka_opis);
        }
        if(GATUNEK.equals("BrodziecPiegowaty")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.brodziec_piegowaty));
            tvTytuł.setText(R.string.brodziec_piegowaty_tytuł);
            tvOpis.setText(R.string.brodziec_piegowaty_opis);
        }
        if(GATUNEK.equals("BrodziecPiskliwy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.brodziec_piskliwy));
            tvTytuł.setText(R.string.brodziec_piskliwy_tytuł);
            tvOpis.setText(R.string.brodziec_piskliwy_opis);
        }
        if(GATUNEK.equals("BrodziecPlamisty")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.brodziec_plamisty));
            tvTytuł.setText(R.string.brodziec_plamisty_tytuł);
            tvOpis.setText(R.string.brodziec_plamisty_opis);
        }
        if(GATUNEK.equals("BrodziecPławny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.brodziec_plawny));
            tvTytuł.setText(R.string.brodziec_plawny_tytuł);
            tvOpis.setText(R.string.brodziec_plawny_opis);
        }
        if(GATUNEK.equals("BrodziecŚniady")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.brodziec_sniady));
            tvTytuł.setText(R.string.brodziec_sniady_tytuł);
            tvOpis.setText(R.string.brodziec_sniady_opis);
        }
        if(GATUNEK.equals("BrodziecŻółtonogi")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.brodziec_zoltonogi));
            tvTytuł.setText(R.string.brodziec_zoltonogi_tytuł);
            tvOpis.setText(R.string.brodziec_zoltonogi_opis);
        }
        if(GATUNEK.equals("Cierlik")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cierlik));
            tvTytuł.setText(R.string.cierlik_tytuł);
            tvOpis.setText(R.string.cierlik_opis);
        }
        if(GATUNEK.equals("Cierniówka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cierniowka));
            tvTytuł.setText(R.string.cierniowka_tytuł);
            tvOpis.setText(R.string.cierniowka_opis);
        }
        if(GATUNEK.equals("Cietrzew")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cietrzew));
            tvTytuł.setText(R.string.cietrzew_tytuł);
            tvOpis.setText(R.string.cietrzew_opis);
        }
        if(GATUNEK.equals("Cyraneczka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cyraneczka));
            tvTytuł.setText(R.string.cyraneczka_tytuł);
            tvOpis.setText(R.string.cyraneczka_opis);
        }
        if(GATUNEK.equals("CyraneczkaKarolińska")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cyraneczka_karolinska));
            tvTytuł.setText(R.string.cyraneczka_karolinska_tytuł);
            tvOpis.setText(R.string.cyraneczka_karolinska_opis);
        }
        if(GATUNEK.equals("Cyranka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cyranka));
            tvTytuł.setText(R.string.cyranka_tytuł);
            tvOpis.setText(R.string.cyranka_opis);
        }
        if(GATUNEK.equals("CyrankaModroskrzydła")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.cyranka_modroskrzydla));
            tvTytuł.setText(R.string.cyranka_modroskrzydla_tytuł);
            tvOpis.setText(R.string.cyranka_modroskrzydla_opis);
        }
        if(GATUNEK.equals("Czajka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czajka));
            tvTytuł.setText(R.string.czajka_tytuł);
            tvOpis.setText(R.string.czajka_opis);
        }
        if(GATUNEK.equals("CzajkaStepowa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czajka_stepowa));
            tvTytuł.setText(R.string.czajka_stepowa_tytuł);
            tvOpis.setText(R.string.czajka_stepowa_opis);
        }
        if(GATUNEK.equals("CzajkaTowarzyska")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czajka_towarzyska));
            tvTytuł.setText(R.string.czajka_towarzyska_tytuł);
            tvOpis.setText(R.string.czajka_towarzyska_opis);
        }
        if(GATUNEK.equals("CzaplaBiałą")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czapla_biala));
            tvTytuł.setText(R.string.czapla_biala_tytuł);
            tvOpis.setText(R.string.czapla_biala_opis);
        }
        if(GATUNEK.equals("CzaplaModronosa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czapla_modronosa));
            tvTytuł.setText(R.string.czapla_modronosa_tytuł);
            tvOpis.setText(R.string.czapla_modronosa_opis);
        }
        if(GATUNEK.equals("CzaplaNadobna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czapla_nadobna));
            tvTytuł.setText(R.string.czapla_nadobna_tytuł);
            tvOpis.setText(R.string.czapla_nadobna_opis);
        }
        if(GATUNEK.equals("CzaplaPurpurowa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czapla_purpurowa));
            tvTytuł.setText(R.string.czapla_purpurowa_tytuł);
            tvOpis.setText(R.string.czapla_purpurowa_opis);
        }
        if(GATUNEK.equals("CzaplaSiwa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czapla_siwa));
            tvTytuł.setText(R.string.czapla_siwa_tytuł);
            tvOpis.setText(R.string.czapla_siwa_opis);
        }
        if(GATUNEK.equals("CzaplaZłotawa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czapla_zlotawa));
            tvTytuł.setText(R.string.czapla_zlotawa_tytuł);
            tvOpis.setText(R.string.czapla_zlotawa_opis);
        }
        if(GATUNEK.equals("Czarnogłówka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czarnoglowka));
            tvTytuł.setText(R.string.czarnoglowka_tytuł);
            tvOpis.setText(R.string.czarnoglowka_opis);
        }
        if(GATUNEK.equals("Czarnowron")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czarnowron));
            tvTytuł.setText(R.string.czarnowron_tytuł);
            tvOpis.setText(R.string.czarnowron_opis);
        }
        if(GATUNEK.equals("Czeczotka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czeczotka));
            tvTytuł.setText(R.string.czeczotka_tytuł);
            tvOpis.setText(R.string.czeczotka_opis);
        }
        if(GATUNEK.equals("CzeczotkaTundrowa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czeczotka_tundrowa));
            tvTytuł.setText(R.string.czeczotka_tundrowa_tytuł);
            tvOpis.setText(R.string.czeczotka_tundrowa_opis);
        }
        if(GATUNEK.equals("Czernica")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czernica));
            tvTytuł.setText(R.string.czernica_tytuł);
            tvOpis.setText(R.string.czernica_opis);
        }
        if(GATUNEK.equals("Czerniczka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czerniczka));
            tvTytuł.setText(R.string.czerniczka_tytuł);
            tvOpis.setText(R.string.czerniczka_opis);
        }
        if(GATUNEK.equals("Czubatka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czubatka));
            tvTytuł.setText(R.string.czubatka_tytuł);
            tvOpis.setText(R.string.czubatka_opis);
        }
        if(GATUNEK.equals("Czyż")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.czyz));
            tvTytuł.setText(R.string.czyz_tytuł);
            tvOpis.setText(R.string.czyz_opis);
        }
        if(GATUNEK.equals("Derkacz")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.derkacz));
            tvTytuł.setText(R.string.derkacz_tytuł);
            tvOpis.setText(R.string.derkacz_opis);
        }
        if(GATUNEK.equals("Drop")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drop));
            tvTytuł.setText(R.string.drop_tytuł);
            tvOpis.setText(R.string.drop_opis);
        }
        if(GATUNEK.equals("DrozdaczekCiemny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozdaczek_ciemny));
            tvTytuł.setText(R.string.drozdaczek_ciemny_tytuł);
            tvOpis.setText(R.string.drozdaczek_ciemny_opis);
        }
        if(GATUNEK.equals("DrozdCzarnogardły")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozd_czarnogardly));
            tvTytuł.setText(R.string.drozd_czarnogardly_tytuł);
            tvOpis.setText(R.string.drozd_czarnogardly_opis);
        }
        if(GATUNEK.equals("DrozdObrożny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozd_obrozny));
            tvTytuł.setText(R.string.drozd_obrozny_tytuł);
            tvOpis.setText(R.string.drozd_obrozny_opis);
        }
        if(GATUNEK.equals("DrozdOliwkowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozd_oliwkowy));
            tvTytuł.setText(R.string.drozd_oliwkowy_tytuł);
            tvOpis.setText(R.string.drozd_oliwkowy_opis);
        }
        if(GATUNEK.equals("DrozdońPstry")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozdon_pstry));
            tvTytuł.setText(R.string.drozdon_pstry_tytuł);
            tvOpis.setText(R.string.drozdon_pstry_opis);
        }
        if(GATUNEK.equals("DrozdRdzawogardły")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozd_rdzawogardly));
            tvTytuł.setText(R.string.drozd_rdzawogardly_tytuł);
            tvOpis.setText(R.string.drozd_rdzawogardly_opis);
        }
        if(GATUNEK.equals("DrozdRdzawoskrzydły")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozd_rdzawoskrzydly));
            tvTytuł.setText(R.string.drozd_rdzawoskrzydly_tytuł);
            tvOpis.setText(R.string.drozd_rdzawoskrzydly_opis);
        }
        if(GATUNEK.equals("DrozdRdzawy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozd_rdzawy));
            tvTytuł.setText(R.string.drozd_rdzawy_tytuł);
            tvOpis.setText(R.string.drozd_rdzawy_opis);
        }
        if(GATUNEK.equals("Droździk")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drozdzik));
            tvTytuł.setText(R.string.drozdzik_tytuł);
            tvOpis.setText(R.string.drozdzik_opis);
        }
        if(GATUNEK.equals("Drzemlik")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.drzemlik));
            tvTytuł.setText(R.string.drzemlik_tytuł);
            tvOpis.setText(R.string.drzemlik_opis);
        }
        if(GATUNEK.equals("Dubelt")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dubelt));
            tvTytuł.setText(R.string.dubelt_tytuł);
            tvOpis.setText(R.string.dubelt_opis);
        }
        if(GATUNEK.equals("Dudek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dudek));
            tvTytuł.setText(R.string.dudek_tytuł);
            tvOpis.setText(R.string.dudek_opis);
        }
        if(GATUNEK.equals("Dymówka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dymowka));
            tvTytuł.setText(R.string.dymowka_tytuł);
            tvOpis.setText(R.string.dymowka_opis);
        }
        if(GATUNEK.equals("DzięciołBiałogrzbiety")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_bialogrzbiety));
            tvTytuł.setText(R.string.dzieciol_bialogrzbiety_tytuł);
            tvOpis.setText(R.string.dzieciol_bialogrzbiety_opis);
        }
        if(GATUNEK.equals("DzięciołBiałoszyi")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_bialoszyi));
            tvTytuł.setText(R.string.dzieciol_bialoszyi_tytuł);
            tvOpis.setText(R.string.dzieciol_bialoszyi_opis);
        }
        if(GATUNEK.equals("DzięciołCzarny")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_czarny));
            tvTytuł.setText(R.string.dzieciol_czarny_tytuł);
            tvOpis.setText(R.string.dzieciol_czarny_opis);
        }
        if(GATUNEK.equals("DzięciołDuży")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_duzy));
            tvTytuł.setText(R.string.dzieciol_duzy_tytuł);
            tvOpis.setText(R.string.dzieciol_duzy_opis);
        }
        if(GATUNEK.equals("Dzięciołek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciolek));
            tvTytuł.setText(R.string.dzieciolek_tytuł);
            tvOpis.setText(R.string.dzieciolek_opis);
        }
        if(GATUNEK.equals("DzięciołŚredni")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_sredni));
            tvTytuł.setText(R.string.dzieciol_sredni_tytuł);
            tvOpis.setText(R.string.dzieciol_sredni_opis);
        }
        if(GATUNEK.equals("DzięciołTrójpalczasty")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_trojpalczasty));
            tvTytuł.setText(R.string.dzieciol_trojpalczasty_tytuł);
            tvOpis.setText(R.string.dzieciol_trojpalczasty_opis);
        }
        if(GATUNEK.equals("DzięciołZielonosiwy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_zielonosiwy));
            tvTytuł.setText(R.string.dzieciol_zielonosiwy_tytuł);
            tvOpis.setText(R.string.dzieciol_zielonosiwy_opis);
        }
        if(GATUNEK.equals("DzięciołZielony")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzieciol_zielony));
            tvTytuł.setText(R.string.dzieciol_zielony_tytuł);
            tvOpis.setText(R.string.dzieciol_zielony_opis);
        }
        if(GATUNEK.equals("Dzierlatka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzierlatka));
            tvTytuł.setText(R.string.dzierlatka_tytuł);
            tvOpis.setText(R.string.dzierlatka_opis);
        }
        if(GATUNEK.equals("DzierzbaCzarnoczelna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzierzba_czarnoczelna));
            tvTytuł.setText(R.string.dzierzba_czarnoczelna_tytuł);
            tvOpis.setText(R.string.dzierzba_czarnoczelna_opis);
        }
        if(GATUNEK.equals("DzierzbaPustynna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzierzba_pustynna));
            tvTytuł.setText(R.string.dzierzba_pustynna_tytuł);
            tvOpis.setText(R.string.dzierzba_pustynna_opis);
        }
        if(GATUNEK.equals("DzierzbaRdzawosterna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzierzba_rdzawosterna));
            tvTytuł.setText(R.string.dzierzba_rdzawosterna_tytuł);
            tvOpis.setText(R.string.dzierzba_rdzawosterna_opis);
        }
        if(GATUNEK.equals("DzierzbaRudogłowa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzierzba_rudoglowa));
            tvTytuł.setText(R.string.dzierzba_rudoglowa_tytuł);
            tvOpis.setText(R.string.dzierzba_rudoglowa_opis);
        }
        if(GATUNEK.equals("Dziwonia")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dziwonia));
            tvTytuł.setText(R.string.dziwonia_tytuł);
            tvOpis.setText(R.string.dziwonia_opis);
        }
        if(GATUNEK.equals("Dzwoniec")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.dzwoniec));
            tvTytuł.setText(R.string.dzwoniec_tytuł);
            tvOpis.setText(R.string.dzwoniec_opis);
        }
        if(GATUNEK.equals("Edredon")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.edredon));
            tvTytuł.setText(R.string.edredon_tytuł);
            tvOpis.setText(R.string.edredon_opis);
        }
        if(GATUNEK.equals("FlamingRóżowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.flaming_rozowy));
            tvTytuł.setText(R.string.flaming_rozowy_tytuł);
            tvOpis.setText(R.string.flaming_rozowy_opis);
        }
        if(GATUNEK.equals("Fulmar")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.fulmar));
            tvTytuł.setText(R.string.fulmar_tytuł);
            tvOpis.setText(R.string.fulmar_opis);
        }
        if(GATUNEK.equals("Gadożer")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gadozer));
            tvTytuł.setText(R.string.gadozer_tytuł);
            tvOpis.setText(R.string.gadozer_opis);
        }
        if(GATUNEK.equals("Gawron")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gawron));
            tvTytuł.setText(R.string.gawron_tytuł);
            tvOpis.setText(R.string.gawron_opis);
        }
        if(GATUNEK.equals("Gągoł")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gagol));
            tvTytuł.setText(R.string.gagol_tytuł);
            tvOpis.setText(R.string.gagol_opis);
        }
        if(GATUNEK.equals("Gajówka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gajowka));
            tvTytuł.setText(R.string.gajowka_tytuł);
            tvOpis.setText(R.string.gajowka_opis);
        }
        if(GATUNEK.equals("Gąsiorek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gasiorek));
            tvTytuł.setText(R.string.gasiorek_tytuł);
            tvOpis.setText(R.string.gasiorek_opis);
        }
        if(GATUNEK.equals("Gęgawa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gegawa));
            tvTytuł.setText(R.string.gegawa_tytuł);
            tvOpis.setText(R.string.gegawa_opis);
        }
        if(GATUNEK.equals("GęśBiałoczelna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ges_bialoczelna));
            tvTytuł.setText(R.string.ges_bialoczelna_tytuł);
            tvOpis.setText(R.string.ges_bialoczelna_opis);
        }
        if(GATUNEK.equals("GęsiówkaEgipska")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gesiowka_egipska));
            tvTytuł.setText(R.string.gesiowka_egipska_tytuł);
            tvOpis.setText(R.string.gesiowka_egipska_opis);
        }
        if(GATUNEK.equals("GęśKrótkodzioba")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ges_krotkodzioba));
            tvTytuł.setText(R.string.ges_krotkodzioba_tytuł);
            tvOpis.setText(R.string.ges_krotkodzioba_opis);
        }
        if(GATUNEK.equals("GęśMała")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ges_mala));
            tvTytuł.setText(R.string.ges_mala_tytuł);
            tvOpis.setText(R.string.ges_mala_opis);
        }
        if(GATUNEK.equals("GęśZbożowa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ges_zbozowa));
            tvTytuł.setText(R.string.ges_zbozowa_tytuł);
            tvOpis.setText(R.string.ges_zbozowa_opis);
        }
        if(GATUNEK.equals("Gil")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gil));
            tvTytuł.setText(R.string.gil_tytuł);
            tvOpis.setText(R.string.gil_opis);
        }
        if(GATUNEK.equals("Głowienka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.glowienka));
            tvTytuł.setText(R.string.glowienka_tytuł);
            tvOpis.setText(R.string.glowienka_opis);
        }
        if(GATUNEK.equals("Głuptak")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gluptak));
            tvTytuł.setText(R.string.gluptak_tytuł);
            tvOpis.setText(R.string.gluptak_opis);
        }
        if(GATUNEK.equals("Głuszec")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gluszec));
            tvTytuł.setText(R.string.gluszec_tytuł);
            tvOpis.setText(R.string.gluszec_opis);
        }
        if(GATUNEK.equals("Głuszek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gluszek));
            tvTytuł.setText(R.string.gluszek_tytuł);
            tvOpis.setText(R.string.gluszek_opis);
        }
        if(GATUNEK.equals("GołąbMiejski")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.golab_miejski));
            tvTytuł.setText(R.string.golab_miejski_tytuł);
            tvOpis.setText(R.string.golab_miejski_opis);
        }
        if(GATUNEK.equals("Górniczek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.gorniczek));
            tvTytuł.setText(R.string.gorniczek_tytuł);
            tvOpis.setText(R.string.gorniczek_opis);
        }
        if(GATUNEK.equals("Grubodziób")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.grubodziob));
            tvTytuł.setText(R.string.grubodziob_tytuł);
            tvOpis.setText(R.string.grubodziob_opis);
        }
        if(GATUNEK.equals("Grzywacz")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.grzywacz));
            tvTytuł.setText(R.string.grzywacz_tytuł);
            tvOpis.setText(R.string.grzywacz_opis);
        }
        if(GATUNEK.equals("Hełmiatka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.helmiatka));
            tvTytuł.setText(R.string.helmiatka_tytuł);
            tvOpis.setText(R.string.helmiatka_opis);
        }
        if(GATUNEK.equals("HubaraArabska")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.hubara_arabska));
            tvTytuł.setText(R.string.hubara_arabska_tytuł);
            tvOpis.setText(R.string.hubara_arabska_opis);
        }
        if(GATUNEK.equals("IbisKasztanowaty")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ibis_kasztanowaty));
            tvTytuł.setText(R.string.ibis_kasztanowaty_tytuł);
            tvOpis.setText(R.string.ibis_kasztanowaty_opis);
        }
        if(GATUNEK.equals("Jarząbek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jarzabek));
            tvTytuł.setText(R.string.jarzabek_tytuł);
            tvOpis.setText(R.string.jarzabek_opis);
        }
        if(GATUNEK.equals("Jarzębatka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jarzebatka));
            tvTytuł.setText(R.string.jarzebatka_tytuł);
            tvOpis.setText(R.string.jarzebatka_opis);
        }
        if(GATUNEK.equals("JaskółkaRudawa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jaskolka_rudawa));
            tvTytuł.setText(R.string.jaskolka_rudawa_tytuł);
            tvOpis.setText(R.string.jaskolka_rudawa_opis);
        }
        if(GATUNEK.equals("Jastrząb")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jastrzab));
            tvTytuł.setText(R.string.jastrzab_tytuł);
            tvOpis.setText(R.string.jastrzab_opis);
        }
        if(GATUNEK.equals("Jemiołuszka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jemioluszka));
            tvTytuł.setText(R.string.jemioluszka_tytuł);
            tvOpis.setText(R.string.jemioluszka_opis);
        }
        if(GATUNEK.equals("Jer")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jer));
            tvTytuł.setText(R.string.jer_tytuł);
            tvOpis.setText(R.string.jer_opis);
        }
        if(GATUNEK.equals("Jerzyk")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jerzyk));
            tvTytuł.setText(R.string.jerzyk_tytuł);
            tvOpis.setText(R.string.jerzyk_opis);
        }
        if(GATUNEK.equals("JerzykAlpejski")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jerzyk_alpejski));
            tvTytuł.setText(R.string.jerzyk_alpejski_tytuł);
            tvOpis.setText(R.string.jerzyk_alpejski_opis);
        }
        if(GATUNEK.equals("JerzykBlady")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.jerzyk_blady));
            tvTytuł.setText(R.string.jerzyk_blady_tytuł);
            tvOpis.setText(R.string.jerzyk_blady_opis);
        }
        if(GATUNEK.equals("Junko")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.junko));
            tvTytuł.setText(R.string.junko_tytuł);
            tvOpis.setText(R.string.junko_opis);
        }
        if(GATUNEK.equals("KalandraCzarna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kalandra_czarna));
            tvTytuł.setText(R.string.kalandra_czarna_tytuł);
            tvOpis.setText(R.string.kalandra_czarna_opis);
        }
        if(GATUNEK.equals("KalandraSzara")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kalandra_szara));
            tvTytuł.setText(R.string.kalandra_szara_tytuł);
            tvOpis.setText(R.string.kalandra_szara_opis);
        }
        if(GATUNEK.equals("Kamieniuszka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kamieniuszka));
            tvTytuł.setText(R.string.kamieniuszka_tytuł);
            tvOpis.setText(R.string.kamieniuszka_opis);
        }
        if(GATUNEK.equals("Kamuszik")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kamusznik));
            tvTytuł.setText(R.string.kamuszik_tytuł);
            tvOpis.setText(R.string.kamuszik_opis);
        }
        if(GATUNEK.equals("KaniaCzarna")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kania_czarna));
            tvTytuł.setText(R.string.kania_czarna_tytuł);
            tvOpis.setText(R.string.kania_czarna_opis);
        }
        if(GATUNEK.equals("KaniaRuda")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kania_ruda));
            tvTytuł.setText(R.string.kania_ruda_tytuł);
            tvOpis.setText(R.string.kania_ruda_opis);
        }
        if(GATUNEK.equals("Kapturka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kapturka));
            tvTytuł.setText(R.string.kapturka_tytuł);
            tvOpis.setText(R.string.kapturka_opis);
        }
        if(GATUNEK.equals("Karliczka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.karliczka));
            tvTytuł.setText(R.string.karliczka_tytuł);
            tvOpis.setText(R.string.karliczka_opis);
        }
        if(GATUNEK.equals("Kawka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kawka));
            tvTytuł.setText(R.string.kawka_tytuł);
            tvOpis.setText(R.string.kawka_opis);
        }
        if(GATUNEK.equals("KazarkaRdzawa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kazarka_rdzawa));
            tvTytuł.setText(R.string.kazarka_rdzawa_tytuł);
            tvOpis.setText(R.string.kazarka_rdzawa_opis);
        }
        if(GATUNEK.equals("Kląskawka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.klaskawka));
            tvTytuł.setText(R.string.klaskawka_tytuł);
            tvOpis.setText(R.string.klaskawka_opis);
        }
        if(GATUNEK.equals("Kobczyk")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kobczyk));
            tvTytuł.setText(R.string.kobczyk_tytuł);
            tvOpis.setText(R.string.kobczyk_opis);
        }
        if(GATUNEK.equals("Kobuz")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kobuz));
            tvTytuł.setText(R.string.kobuz_tytuł);
            tvOpis.setText(R.string.kobuz_opis);
        }
        if(GATUNEK.equals("Kokoszka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kokoszka));
            tvTytuł.setText(R.string.kokoszka_tytuł);
            tvOpis.setText(R.string.kokoszka_opis);
        }
        if(GATUNEK.equals("Kopciuszek")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kopciuszek));
            tvTytuł.setText(R.string.kopciuszek_tytuł);
            tvOpis.setText(R.string.kopciuszek_opis);
        }
        if(GATUNEK.equals("Kormoran")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kormoran));
            tvTytuł.setText(R.string.kormoran_tytuł);
            tvOpis.setText(R.string.kormoran_opis);
        }
        if(GATUNEK.equals("KormoranCzubaty")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kormoran_czubaty));
            tvTytuł.setText(R.string.kormoran_czubaty_tytuł);
            tvOpis.setText(R.string.kormoran_czubaty_opis);
        }
        if(GATUNEK.equals("KormoranMały")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kormoran_maly));
            tvTytuł.setText(R.string.kormoran_maly_tytuł);
            tvOpis.setText(R.string.kormoran_maly_opis);
        }
        if(GATUNEK.equals("Kos")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kos));
            tvTytuł.setText(R.string.kos_tytuł);
            tvOpis.setText(R.string.kos_opis);
        }
        if(GATUNEK.equals("Kowalik")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kowalik));
            tvTytuł.setText(R.string.kowalik_tytuł);
            tvOpis.setText(R.string.kowalik_opis);
        }
        if(GATUNEK.equals("Krakwa")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krakwa));
            tvTytuł.setText(R.string.krakwa_tytuł);
            tvOpis.setText(R.string.krakwa_opis);
        }
        if(GATUNEK.equals("Kraska")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kraska));
            tvTytuł.setText(R.string.kraska_tytuł);
            tvOpis.setText(R.string.kraska_opis);
        }
        if(GATUNEK.equals("Krętogłów")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kretoglow));
            tvTytuł.setText(R.string.kretoglow_tytuł);
            tvOpis.setText(R.string.kretoglow_opis);
        }
        if(GATUNEK.equals("Krogulec")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krogulec));
            tvTytuł.setText(R.string.krogulec_tytuł);
            tvOpis.setText(R.string.krogulec_opis);
        }
        if(GATUNEK.equals("KrogulecKrótkonogi")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krogulec_krotkonogi));
            tvTytuł.setText(R.string.krogulec_krotkonogi_tytuł);
            tvOpis.setText(R.string.krogulec_krotkonogi_opis);
        }
        if(GATUNEK.equals("Kropiatka")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kropiatka));
            tvTytuł.setText(R.string.kropiatka_tytuł);
            tvOpis.setText(R.string.kropiatka_opis);
        }
        if(GATUNEK.equals("Kruk")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kruk));
            tvTytuł.setText(R.string.kruk_tytuł);
            tvOpis.setText(R.string.kruk_opis);
        }
        if(GATUNEK.equals("Krwawodziób")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krwawodziob));
            tvTytuł.setText(R.string.krwawodziob_tytuł);
            tvOpis.setText(R.string.krwawodziob_opis);
        }
        if(GATUNEK.equals("KrzyżodzióbModrzewiowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krzyzodziob_modrzewiowy));
            tvTytuł.setText(R.string.krzyzodziob_modrzewiowy_tytuł);
            tvOpis.setText(R.string.krzyzodziob_modrzewiowy_opis);
        }
        if(GATUNEK.equals("KrzyżodzióbSosnowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krzyzodziob_sosnowy));
            tvTytuł.setText(R.string.krzyzodziob_sosnowy_tytuł);
            tvOpis.setText(R.string.krzyzodziob_sosnowy_opis);
        }
        if(GATUNEK.equals("KrzyżodzióbŚwierkowy")){
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krzyzodziob_swierkowy));
            tvTytuł.setText(R.string.krzyzodziob_swierkowy_tytuł);
            tvOpis.setText(R.string.krzyzodziob_swierkowy_opis);
        }
        if(GATUNEK.equals("Krzyżówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.krzyzowka));
            tvTytuł.setText(R.string.krzyzowka_tytuł);
            tvOpis.setText(R.string.krzyzowka_opis);
        }
        if(GATUNEK.equals("Kszyk")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kszyk));
            tvTytuł.setText(R.string.kszyk_tytuł);
            tvOpis.setText(R.string.kszyk_opis);
        }
        if(GATUNEK.equals("Kukułka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kukulka));
            tvTytuł.setText(R.string.kukulka_tytuł);
            tvOpis.setText(R.string.kukulka_opis);
        }
        if(GATUNEK.equals("Kulczyk")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kulczyk));
            tvTytuł.setText(R.string.kulczyk_tytuł);
            tvOpis.setText(R.string.kulczyk_opis);
        }
        if(GATUNEK.equals("KulikCienkodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kulik_cienkodzioby));
            tvTytuł.setText(R.string.kulik_cienkodzioby_tytuł);
            tvOpis.setText(R.string.kulik_cienkodzioby_opis);
        }
        if(GATUNEK.equals("KulikMniejszy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kulik_mniejszy));
            tvTytuł.setText(R.string.kulik_mniejszy_tytuł);
            tvOpis.setText(R.string.kulik_mniejszy_opis);
        }
        if(GATUNEK.equals("KulikWielki")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kulik_wielki));
            tvTytuł.setText(R.string.kulik_wielki_tytuł);
            tvOpis.setText(R.string.kulik_wielki_opis);
        }
        if(GATUNEK.equals("Kulon")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kulon));
            tvTytuł.setText(R.string.kulon_tytuł);
            tvOpis.setText(R.string.kulon_opis);
        }
        if(GATUNEK.equals("Kurhannik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kurhannik));
            tvTytuł.setText(R.string.kurhannik_tytuł);
            tvOpis.setText(R.string.kurhannik_opis);
        }
        if(GATUNEK.equals("Kuropatwa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kuropatwa));
            tvTytuł.setText(R.string.kuropatwa_tytuł);
            tvOpis.setText(R.string.kuropatwa_opis);
        }
        if(GATUNEK.equals("Kwiczoł")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kwiczol));
            tvTytuł.setText(R.string.kwiczol_tytuł);
            tvOpis.setText(R.string.kwiczol_opis);
        }
        if(GATUNEK.equals("Kwokacz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.kwokacz));
            tvTytuł.setText(R.string.kwokacz_tytuł);
            tvOpis.setText(R.string.kwokacz_opis);
        }
        if(GATUNEK.equals("Lelek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.lelek));
            tvTytuł.setText(R.string.lelek_tytuł);
            tvOpis.setText(R.string.lelek_opis);
        }
        if(GATUNEK.equals("Lerka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.lerka));
            tvTytuł.setText(R.string.lerka_tytuł);
            tvOpis.setText(R.string.lerka_opis);
        }
        if(GATUNEK.equals("Lodowiec")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.lodowiec));
            tvTytuł.setText(R.string.lodowiec_tytuł);
            tvOpis.setText(R.string.lodowiec_opis);
        }
        if(GATUNEK.equals("Lodówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.lodowka));
            tvTytuł.setText(R.string.lodowka_tytuł);
            tvOpis.setText(R.string.lodowka_opis);
        }
        if(GATUNEK.equals("ŁabędźCzarnodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.labedz_czarnodzioby));
            tvTytuł.setText(R.string.labedz_czarnodzioby_tytuł);
            tvOpis.setText(R.string.labedz_czarnodzioby_opis);
        }
        if(GATUNEK.equals("ŁabędźKrzykliwy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.labedz_krzykliwy));
            tvTytuł.setText(R.string.labedz_krzykliwy_tytuł);
            tvOpis.setText(R.string.labedz_krzykliwy_opis);
        }
        if(GATUNEK.equals("ŁabędźNiemy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.labedz_niemy));
            tvTytuł.setText(R.string.labedz_niemy_tytuł);
            tvOpis.setText(R.string.labedz_niemy_opis);
        }
        if(GATUNEK.equals("Łęczak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.leczak));
            tvTytuł.setText(R.string.leczak_tytuł);
            tvOpis.setText(R.string.leczak_opis);
        }
        if(GATUNEK.equals("Łozówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.lozowka));
            tvTytuł.setText(R.string.lozowka_tytuł);
            tvOpis.setText(R.string.lozowka_opis);
        }
        if(GATUNEK.equals("Łuskowiec")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.luskowiec));
            tvTytuł.setText(R.string.luskowiec_tytuł);
            tvOpis.setText(R.string.luskowiec_opis);
        }
        if(GATUNEK.equals("Łyska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.lyska));
            tvTytuł.setText(R.string.lyska_tytuł);
            tvOpis.setText(R.string.lyska_opis);
        }
        if(GATUNEK.equals("Makolągwa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.makolagwa));
            tvTytuł.setText(R.string.makolagwa_tytuł);
            tvOpis.setText(R.string.makolagwa_opis);
        }
        if(GATUNEK.equals("Mandarynka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mandarynka));
            tvTytuł.setText(R.string.mandarynka_tytuł);
            tvOpis.setText(R.string.mandarynka_opis);
        }
        if(GATUNEK.equals("Markaczka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.markaczka));
            tvTytuł.setText(R.string.markaczka_tytuł);
            tvOpis.setText(R.string.markaczka_opis);
        }
        if(GATUNEK.equals("MarkaczkaAmerykańska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.markaczka_amerykanska));
            tvTytuł.setText(R.string.markaczka_amerykanska_tytuł);
            tvOpis.setText(R.string.markaczka_amerykanska_opis);
        }
        if(GATUNEK.equals("Maskonur")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.maskonur));
            tvTytuł.setText(R.string.maskonur_tytuł);
            tvOpis.setText(R.string.maskonur_opis);
        }
        if(GATUNEK.equals("Mazurek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mazurek));
            tvTytuł.setText(R.string.mazurek_tytuł);
            tvOpis.setText(R.string.mazurek_opis);
        }
        if(GATUNEK.equals("MewaBiałogłowa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_bialoglowa));
            tvTytuł.setText(R.string.mewa_bialoglowa_tytuł);
            tvOpis.setText(R.string.mewa_bialoglowa_opis);
        }
        if(GATUNEK.equals("MewaBlada")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_blada));
            tvTytuł.setText(R.string.mewa_blada_tytuł);
            tvOpis.setText(R.string.mewa_blada_opis);
        }
        if(GATUNEK.equals("MewaCienkodzioba")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_cienkodzioba));
            tvTytuł.setText(R.string.mewa_cienkodzioba_tytuł);
            tvOpis.setText(R.string.mewa_cienkodzioba_opis);
        }
        if(GATUNEK.equals("MewaCzarnogłowa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_czarnoglowa));
            tvTytuł.setText(R.string.mewa_czarnoglowa_tytuł);
            tvOpis.setText(R.string.mewa_czarnoglowa_opis);
        }
        if(GATUNEK.equals("MewaDelawarska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_delawarska));
            tvTytuł.setText(R.string.mewa_delawarska_tytuł);
            tvOpis.setText(R.string.mewa_delawarska_opis);
        }
        if(GATUNEK.equals("MewaKaraibska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_karaibska));
            tvTytuł.setText(R.string.mewa_karaibska_tytuł);
            tvOpis.setText(R.string.mewa_karaibska_opis);
        }
        if(GATUNEK.equals("MewaMała")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_mala));
            tvTytuł.setText(R.string.mewa_mala_tytuł);
            tvOpis.setText(R.string.mewa_mala_opis);
        }
        if(GATUNEK.equals("MewaModrodzioba")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_modrodzioba));
            tvTytuł.setText(R.string.mewa_modrodzioba_tytuł);
            tvOpis.setText(R.string.mewa_modrodzioba_opis);
        }
        if(GATUNEK.equals("MewaObrożna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_obrozna));
            tvTytuł.setText(R.string.mewa_obrozna_tytuł);
            tvOpis.setText(R.string.mewa_obrozna_opis);
        }
        if(GATUNEK.equals("MewaPolarna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_polarna));
            tvTytuł.setText(R.string.mewa_polarna_tytuł);
            tvOpis.setText(R.string.mewa_polarna_opis);
        }
        if(GATUNEK.equals("MewaRomańska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_romanska));
            tvTytuł.setText(R.string.mewa_romanska_tytuł);
            tvOpis.setText(R.string.mewa_romanska_opis);
        }
        if(GATUNEK.equals("MewaSiodłata")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_siodlata));
            tvTytuł.setText(R.string.mewa_siodlata_tytuł);
            tvOpis.setText(R.string.mewa_siodlata_opis);
        }
        if(GATUNEK.equals("MewaSiwa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_siwa));
            tvTytuł.setText(R.string.mewa_siwa_tytuł);
            tvOpis.setText(R.string.mewa_siwa_opis);
        }
        if(GATUNEK.equals("MewaSrebrzysta")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_srebrzysta));
            tvTytuł.setText(R.string.mewa_srebrzysta_tytuł);
            tvOpis.setText(R.string.mewa_srebrzysta_opis);
        }
        if(GATUNEK.equals("MewaTrójpalczasta")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_trojpalczasta));
            tvTytuł.setText(R.string.mewa_trojpalczasta_tytuł);
            tvOpis.setText(R.string.mewa_trojpalczasta_opis);
        }
        if(GATUNEK.equals("MewaŻółtonoga")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mewa_zoltonoga));
            tvTytuł.setText(R.string.mewa_zoltonoga_tytuł);
            tvOpis.setText(R.string.mewa_zoltonoga_opis);
        }
        if(GATUNEK.equals("Modraczek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.modraczek));
            tvTytuł.setText(R.string.modraczek_tytuł);
            tvOpis.setText(R.string.modraczek_opis);
        }
        if(GATUNEK.equals("Modraszka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.modraszka));
            tvTytuł.setText(R.string.modraszka_tytuł);
            tvOpis.setText(R.string.modraszka_opis);
        }
        if(GATUNEK.equals("Mornel")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mornel));
            tvTytuł.setText(R.string.mornel_tytuł);
            tvOpis.setText(R.string.mornel_opis);
        }
        if(GATUNEK.equals("MuchołówkaBiałoszyja")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mucholowka_bialoszyja));
            tvTytuł.setText(R.string.mucholowka_bialoszyja_tytuł);
            tvOpis.setText(R.string.mucholowka_bialoszyja_opis);
        }
        if(GATUNEK.equals("MuchołówkaMałą")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mucholowka_mala));
            tvTytuł.setText(R.string.mucholowka_mala_tytuł);
            tvOpis.setText(R.string.mucholowka_mala_opis);
        }
        if(GATUNEK.equals("MuchołówkaSzara")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mucholowka_szara));
            tvTytuł.setText(R.string.mucholowka_szara_tytuł);
            tvOpis.setText(R.string.mucholowka_szara_opis);
        }
        if(GATUNEK.equals("MuchołówkaŻałobna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mucholowka_zalobna));
            tvTytuł.setText(R.string.mucholowka_zalobna_tytuł);
            tvOpis.setText(R.string.mucholowka_zalobna_opis);
        }
        if(GATUNEK.equals("Mysikrólik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.mysikrolik));
            tvTytuł.setText(R.string.mysikrolik_tytuł);
            tvOpis.setText(R.string.mysikrolik_opis);
        }
        if(GATUNEK.equals("Myszołów")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.myszolow));
            tvTytuł.setText(R.string.myszolow_tytuł);
            tvOpis.setText(R.string.myszolow_opis);
        }
        if(GATUNEK.equals("MyszołówWłochaty")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.myszolow_wlochaty));
            tvTytuł.setText(R.string.myszolow_wlochaty_tytuł);
            tvOpis.setText(R.string.myszolow_wlochaty_opis);
        }
        if(GATUNEK.equals("Nagórnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nagornik));
            tvTytuł.setText(R.string.nagornik_tytuł);
            tvOpis.setText(R.string.nagornik_opis);
        }
    }
}
