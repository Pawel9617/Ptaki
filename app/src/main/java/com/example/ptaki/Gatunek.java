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
import android.widget.Toast;

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
                //getImageFromGallery();
                Toast.makeText(Gatunek.this, "Funkcja niedostępna", Toast.LENGTH_SHORT).show();
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
        /*Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);*/
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
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
        /*if(sharedPreferences.getBoolean(MAZDJĘCIE, false)){
            imageView.setImageURI(null);
            imageView.setImageURI(uri);
        }*/
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
        if(GATUNEK.equals("NawałnikDuży")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nawalnik_duzy));
            tvTytuł.setText(R.string.nawalnik_duzy_tytuł);
            tvOpis.setText(R.string.nawalnik_duzy_opis);
        }
        if(GATUNEK.equals("NurBiałodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nur_bialodzioby));
            tvTytuł.setText(R.string.nur_bialodzioby_tytuł);
            tvOpis.setText(R.string.nur_bialodzioby_opis);
        }
        if(GATUNEK.equals("NurCzarnoszyi")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nur_czarnoszyi));
            tvTytuł.setText(R.string.nur_czarnoszyi_tytuł);
            tvOpis.setText(R.string.nur_czarnoszyi_opis);
        }
        if(GATUNEK.equals("Nurnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nurnik));
            tvTytuł.setText(R.string.nurnik_tytuł);
            tvOpis.setText(R.string.nurnik_opis);
        }
        if(GATUNEK.equals("Nurogęś")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nuroges));
            tvTytuł.setText(R.string.nuroges_tytuł);
            tvOpis.setText(R.string.nuroges_opis);
        }
        if(GATUNEK.equals("NurRdzawoszyi")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nur_rdzawoszyi));
            tvTytuł.setText(R.string.nur_rdzawoszyi_tytuł);
            tvOpis.setText(R.string.nur_rdzawoszyi_opis);
        }
        if(GATUNEK.equals("Nurzyk")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nurzyk));
            tvTytuł.setText(R.string.nurzyk_tytuł);
            tvOpis.setText(R.string.nurzyk_opis);
        }
        if(GATUNEK.equals("NurzykPolarny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.nurzyk_polarny));
            tvTytuł.setText(R.string.nurzyk_polarny_tytuł);
            tvOpis.setText(R.string.nurzyk_polarny_opis);
        }
        if(GATUNEK.equals("OceannikŻółtopłetwy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.oceannik_zoltopletwy));
            tvTytuł.setText(R.string.oceannik_zoltopletwy_tytuł);
            tvOpis.setText(R.string.oceannik_zoltopletwy_opis);
        }
        if(GATUNEK.equals("Ogorzałka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ogorzalka));
            tvTytuł.setText(R.string.ogorzalka_tytuł);
            tvOpis.setText(R.string.ogorzalka_opis);
        }
        if(GATUNEK.equals("OgorzałkaMała")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ogorzalka_mala));
            tvTytuł.setText(R.string.ogorzalka_mala_tytuł);
            tvOpis.setText(R.string.ogorzalka_mala_opis);
        }
        if(GATUNEK.equals("Ohar")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ohar));
            tvTytuł.setText(R.string.ohar_tytuł);
            tvOpis.setText(R.string.ohar_opis);
        }
        if(GATUNEK.equals("Oknówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.oknowka));
            tvTytuł.setText(R.string.oknowka_tytuł);
            tvOpis.setText(R.string.oknowka_opis);
        }
        if(GATUNEK.equals("Orlica")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orlica));
            tvTytuł.setText(R.string.orlica_tytuł);
            tvOpis.setText(R.string.orlica_opis);
        }
        if(GATUNEK.equals("OrlikGrubodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orlik_grubodzioby));
            tvTytuł.setText(R.string.orlik_grubodzioby_tytuł);
            tvOpis.setText(R.string.orlik_grubodzioby_opis);
        }
        if(GATUNEK.equals("OrlikKrzykliwy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orlik_krzykliwy));
            tvTytuł.setText(R.string.orlik_krzykliwy_tytuł);
            tvOpis.setText(R.string.orlik_krzykliwy_opis);
        }
        if(GATUNEK.equals("Orłosęp")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orlosep));
            tvTytuł.setText(R.string.orlosep_tytuł);
            tvOpis.setText(R.string.orlosep_opis);
        }
        if(GATUNEK.equals("Ortolan")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ortolan));
            tvTytuł.setText(R.string.ortolan_tytuł);
            tvOpis.setText(R.string.ortolan_opis);
        }
        if(GATUNEK.equals("Orzechówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orzechowka));
            tvTytuł.setText(R.string.orzechowka_tytuł);
            tvOpis.setText(R.string.orzechowka_opis);
        }
        if(GATUNEK.equals("OrzełCesarski")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orzel_cesarski));
            tvTytuł.setText(R.string.orzel_cesarski_tytuł);
            tvOpis.setText(R.string.orzel_cesarski_opis);
        }
        if(GATUNEK.equals("Orzełek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orzelek));
            tvTytuł.setText(R.string.orzelek_tytuł);
            tvOpis.setText(R.string.orzelek_opis);
        }
        if(GATUNEK.equals("OrzełPrzedni")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orzel_przedni));
            tvTytuł.setText(R.string.orzel_przedni_tytuł);
            tvOpis.setText(R.string.orzel_przedni_opis);
        }
        if(GATUNEK.equals("OrzełStepowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.orzel_stepowy));
            tvTytuł.setText(R.string.orzel_stepowy_tytuł);
            tvOpis.setText(R.string.orzel_stepowy_opis);
        }
        if(GATUNEK.equals("Osetnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.osetnik));
            tvTytuł.setText(R.string.osetnik_tytuł);
            tvOpis.setText(R.string.osetnik_opis);
        }
        if(GATUNEK.equals("Ostrygojad")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.ostrygojad));
            tvTytuł.setText(R.string.ostrygojad_tytuł);
            tvOpis.setText(R.string.ostrygojad_opis);
        }
        if(GATUNEK.equals("PardwaMszarna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pardwa_mszarna));
            tvTytuł.setText(R.string.pardwa_mszarna_tytuł);
            tvOpis.setText(R.string.pardwa_mszarna_opis);
        }
        if(GATUNEK.equals("Pasterz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pasterz));
            tvTytuł.setText(R.string.pasterz_tytuł);
            tvOpis.setText(R.string.pasterz_opis);
        }
        if(GATUNEK.equals("Paszkot")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.paszkot));
            tvTytuł.setText(R.string.paszkot_tytuł);
            tvOpis.setText(R.string.paszkot_opis);
        }
        if(GATUNEK.equals("PelikanKędzierzawy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pelikan_kedzierzawy));
            tvTytuł.setText(R.string.pelikan_kedzierzawy_tytuł);
            tvOpis.setText(R.string.pelikan_kedzierzawy_opis);
        }
        if(GATUNEK.equals("PelikanRóżowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pelikan_rozowy));
            tvTytuł.setText(R.string.pelikan_rozowy_tytuł);
            tvOpis.setText(R.string.pelikan_rozowy_opis);
        }
        if(GATUNEK.equals("PełzaczLeśny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pelzacz_lesny));
            tvTytuł.setText(R.string.pelzacz_lesny_tytuł);
            tvOpis.setText(R.string.pelzacz_lesny_opis);
        }
        if(GATUNEK.equals("PełzaczOgrodowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pelzacz_ogrodowy));
            tvTytuł.setText(R.string.pelzacz_ogrodowy_tytuł);
            tvOpis.setText(R.string.pelzacz_ogrodowy_opis);
        }
        if(GATUNEK.equals("PerkozDwuczuby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.perkoz_dwuczuby));
            tvTytuł.setText(R.string.perkoz_dwuczuby_tytuł);
            tvOpis.setText(R.string.perkoz_dwuczuby_opis);
        }
        if(GATUNEK.equals("Perkozek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.perkozek));
            tvTytuł.setText(R.string.perkozek_tytuł);
            tvOpis.setText(R.string.perkozek_opis);
        }
        if(GATUNEK.equals("PerkozGrubodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.perkoz_grubodzioby));
            tvTytuł.setText(R.string.perkoz_grubodzioby_tytuł);
            tvOpis.setText(R.string.perkoz_grubodzioby_opis);
        }
        if(GATUNEK.equals("PerkozRogaty")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.perkoz_rogaty));
            tvTytuł.setText(R.string.perkoz_rogaty_tytuł);
            tvOpis.setText(R.string.perkoz_rogaty_opis);
        }
        if(GATUNEK.equals("Piaskowiec")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.piaskowiec));
            tvTytuł.setText(R.string.piaskowiec_tytuł);
            tvOpis.setText(R.string.piaskowiec_opis);
        }
        if(GATUNEK.equals("Piecuszek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.piecuszek));
            tvTytuł.setText(R.string.piecuszka_tytuł);
            tvOpis.setText(R.string.piecuszka_opis);
        }
        if(GATUNEK.equals("Piegża")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.piegza));
            tvTytuł.setText(R.string.piegza_tytuł);
            tvOpis.setText(R.string.piegza_opis);
        }
        if(GATUNEK.equals("Pierwiosnek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pierwiosnek));
            tvTytuł.setText(R.string.pierwiosnek_tytuł);
            tvOpis.setText(R.string.pierwiosnek_opis);
        }
        if(GATUNEK.equals("Pleszka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pleszka));
            tvTytuł.setText(R.string.pleszka_tytuł);
            tvOpis.setText(R.string.pleszka_opis);
        }
        if(GATUNEK.equals("PliszkaGórska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pliszka_gorska));
            tvTytuł.setText(R.string.pliszka_gorska_tytuł);
            tvOpis.setText(R.string.pliszka_gorska_opis);
        }
        if(GATUNEK.equals("PliszkaSiwa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pliszka_siwa));
            tvTytuł.setText(R.string.pliszka_siwa_tytuł);
            tvOpis.setText(R.string.pliszka_siwa_opis);
        }
        if(GATUNEK.equals("PliszkaŻółta")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pliszka_zolta));
            tvTytuł.setText(R.string.pliszka_zolta_tytuł);
            tvOpis.setText(R.string.pliszka_zolta_opis);
        }
        if(GATUNEK.equals("PliszkaŻółta")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pliszka_zolta));
            tvTytuł.setText(R.string.pliszka_zolta_tytuł);
            tvOpis.setText(R.string.pliszka_zolta_opis);
        }
        if(GATUNEK.equals("Pluszcz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pluszcz));
            tvTytuł.setText(R.string.pluszcz_tytuł);
            tvOpis.setText(R.string.pluszcz_opis);
        }
        if(GATUNEK.equals("Płaskonos")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.plaskonos));
            tvTytuł.setText(R.string.plaskonos_tytuł);
            tvOpis.setText(R.string.plaskonos_opis);
        }
        if(GATUNEK.equals("PłatkonógPłaskodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.platkonog_plaskodzioby));
            tvTytuł.setText(R.string.platkonog_plaskodzioby_tytuł);
            tvOpis.setText(R.string.platkonog_plaskodzioby_opis);
        }
        if(GATUNEK.equals("PłatkonógSzydłodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.platkonog_szydlodzioby));
            tvTytuł.setText(R.string.platkonog_szydlodzioby_tytuł);
            tvOpis.setText(R.string.platkonog_szydlodzioby_opis);
        }
        if(GATUNEK.equals("PłochaczHalny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.plochacz_halny));
            tvTytuł.setText(R.string.plochacz_halny_tytuł);
            tvOpis.setText(R.string.plochacz_halny_opis);
        }
        if(GATUNEK.equals("PłochaczcSyberyjski")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.plochacz_syberyjski));
            tvTytuł.setText(R.string.plochacz_syberyjski_tytuł);
            tvOpis.setText(R.string.plochacz_syberyjski_opis);
        }
        if(GATUNEK.equals("Płomykówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.plomykowka));
            tvTytuł.setText(R.string.plomykowka_tytuł);
            tvOpis.setText(R.string.plomykowka_opis);
        }
        if(GATUNEK.equals("Podgorzałka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.podgorzalka));
            tvTytuł.setText(R.string.podgorzalka_tytuł);
            tvOpis.setText(R.string.podgorzalka_opis);
        }
        if(GATUNEK.equals("Podróżniczek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.podrozniczek));
            tvTytuł.setText(R.string.podrozniczek_tytuł);
            tvOpis.setText(R.string.podrozniczek_opis);
        }
        if(GATUNEK.equals("Pójdźka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pojdzka));
            tvTytuł.setText(R.string.pojdzka_tytuł);
            tvOpis.setText(R.string.pojdzka_opis);
        }
        if(GATUNEK.equals("Pokląskwa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.poklaskwa));
            tvTytuł.setText(R.string.poklaskwa_tytuł);
            tvOpis.setText(R.string.poklaskwa_opis);
        }
        if(GATUNEK.equals("PokrzewkaAksamitna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pokrzewka_aksamitna));
            tvTytuł.setText(R.string.pokrzewka_aksamitna_tytuł);
            tvOpis.setText(R.string.pokrzewka_aksamitna_opis);
        }
        if(GATUNEK.equals("PokrzewkaWąsata")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pokrzewka_wasata));
            tvTytuł.setText(R.string.pokrzewka_wasata_tytuł);
            tvOpis.setText(R.string.pokrzewka_wasata_opis);
        }
        if(GATUNEK.equals("Pokrzywnica")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pokrzywnica));
            tvTytuł.setText(R.string.pokrzywnica_tytuł);
            tvOpis.setText(R.string.pokrzywnica_opis);
        }
        if(GATUNEK.equals("Pomurnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pomurnik));
            tvTytuł.setText(R.string.pomurnik_tytuł);
            tvOpis.setText(R.string.pomurnik_opis);
        }
        if(GATUNEK.equals("Poświerka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.poswierka));
            tvTytuł.setText(R.string.poswierka_tytuł);
            tvOpis.setText(R.string.poswierka_opis);
        }
        if(GATUNEK.equals("Potrzeszcz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.potrzeszcz));
            tvTytuł.setText(R.string.potrzeszcz_tytuł);
            tvOpis.setText(R.string.potrzeszcz_opis);
        }
        if(GATUNEK.equals("Potrzos")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.potrzos));
            tvTytuł.setText(R.string.potrzos_tytuł);
            tvOpis.setText(R.string.potrzos_opis);
        }
        if(GATUNEK.equals("Przepiórka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.przepiorka));
            tvTytuł.setText(R.string.przepiorka_tytuł);
            tvOpis.setText(R.string.przepiorka_opis);
        }
        if(GATUNEK.equals("Puchacz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.puchacz));
            tvTytuł.setText(R.string.puchacz_tytuł);
            tvOpis.setText(R.string.puchacz_opis);
        }
        if(GATUNEK.equals("Pustułeczka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pustuleczka));
            tvTytuł.setText(R.string.pustuleczka_tytuł);
            tvOpis.setText(R.string.pustuleczka_opis);
        }
        if(GATUNEK.equals("Pustułka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pustulka));
            tvTytuł.setText(R.string.pustulka_tytuł);
            tvOpis.setText(R.string.pustulka_opis);
        }
        if(GATUNEK.equals("Pustynnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.pustynnik));
            tvTytuł.setText(R.string.pustynnik_tytuł);
            tvOpis.setText(R.string.pustynnik_opis);
        }
        if(GATUNEK.equals("Puszczyk")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.puszczyk));
            tvTytuł.setText(R.string.puszczyk_tytuł);
            tvOpis.setText(R.string.puszczyk_opis);
        }
        if(GATUNEK.equals("PuszczykMszarny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.puszczyk_mszarny));
            tvTytuł.setText(R.string.puszczyk_mszarny_tytuł);
            tvOpis.setText(R.string.puszczyk_mszarny_opis);
        }
        if(GATUNEK.equals("PuszczykUralski")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.puszczyk_uralski));
            tvTytuł.setText(R.string.puszczyk_uralski_tytuł);
            tvOpis.setText(R.string.puszczyk_uralski_opis);
        }
        if(GATUNEK.equals("Raniuszek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.raniuszek));
            tvTytuł.setText(R.string.raniuszek_tytuł);
            tvOpis.setText(R.string.raniuszek_opis);
        }
        if(GATUNEK.equals("Raróg")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rarog));
            tvTytuł.setText(R.string.rarog_tytuł);
            tvOpis.setText(R.string.rarog_opis);
        }
        if(GATUNEK.equals("Remiz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.remiz));
            tvTytuł.setText(R.string.remiz_tytuł);
            tvOpis.setText(R.string.remiz_opis);
        }
        if(GATUNEK.equals("Rokitniczka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rokitniczka));
            tvTytuł.setText(R.string.rokitniczka_tytuł);
            tvOpis.setText(R.string.rokitniczka_opis);
        }
        if(GATUNEK.equals("Rożeniec")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rozeniec));
            tvTytuł.setText(R.string.rozeniec_tytuł);
            tvOpis.setText(R.string.rozeniec_opis);
        }
        if(GATUNEK.equals("Rudzik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rudzik));
            tvTytuł.setText(R.string.rudzik_tytuł);
            tvOpis.setText(R.string.rudzik_opis);
        }
        if(GATUNEK.equals("RybaczekSrokaty")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybaczek_srokaty));
            tvTytuł.setText(R.string.rybaczek_srokaty_tytuł);
            tvOpis.setText(R.string.rybaczek_srokaty_opis);
        }
        if(GATUNEK.equals("RybitwaBiałoczelna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_bialoczelna));
            tvTytuł.setText(R.string.rybitwa_bialoczelna_tytuł);
            tvOpis.setText(R.string.rybitwa_bialoczelna_opis);
        }
        if(GATUNEK.equals("RybitwaBiałoskrzydła")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_bialoskrzydla));
            tvTytuł.setText(R.string.rybitwa_bialoskrzydla_tytuł);
            tvOpis.setText(R.string.rybitwa_bialoskrzydla_opis);
        }
        if(GATUNEK.equals("RybitwaBiałowąsa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_bialowasa));
            tvTytuł.setText(R.string.rybitwa_bialowasa_tytuł);
            tvOpis.setText(R.string.rybitwa_bialowasa_opis);
        }
        if(GATUNEK.equals("RybitwaCzarna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_czarna));
            tvTytuł.setText(R.string.rybitwa_czarna_tytuł);
            tvOpis.setText(R.string.rybitwa_czarna_opis);
        }
        if(GATUNEK.equals("RybitwaCzubata")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_czubata));
            tvTytuł.setText(R.string.rybitwa_czubata_tytuł);
            tvOpis.setText(R.string.rybitwa_czubata_opis);
        }
        if(GATUNEK.equals("RybitwaKrótkodzioba")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_krotkodzioba));
            tvTytuł.setText(R.string.rybitwa_krotkodzioba_tytuł);
            tvOpis.setText(R.string.rybitwa_krotkodzioba_opis);
        }
        if(GATUNEK.equals("RybitwaPopielata")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_popielata));
            tvTytuł.setText(R.string.rybitwa_popielata_tytuł);
            tvOpis.setText(R.string.rybitwa_popielata_opis);
        }
        if(GATUNEK.equals("RybitwaRóżowa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_rozowa));
            tvTytuł.setText(R.string.rybitwa_rozowa_tytuł);
            tvOpis.setText(R.string.rybitwa_rozowa_opis);
        }
        if(GATUNEK.equals("RybitwaRzeczna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_rzeczna));
            tvTytuł.setText(R.string.rybitwa_rzeczna_tytuł);
            tvOpis.setText(R.string.rybitwa_rzeczna_opis);
        }
        if(GATUNEK.equals("RybitwaWielkodzioba")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybitwa_wielkodzioba));
            tvTytuł.setText(R.string.rybitwa_wielkodzioba_tytuł);
            tvOpis.setText(R.string.rybitwa_wielkodzioba_opis);
        }
        if(GATUNEK.equals("Rybołów")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rybolow));
            tvTytuł.setText(R.string.rybolow_tytuł);
            tvOpis.setText(R.string.rybolow_opis);
        }
        if(GATUNEK.equals("Rycyk")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rycyk));
            tvTytuł.setText(R.string.rycyk_tytuł);
            tvOpis.setText(R.string.rycyk_opis);
        }
        if(GATUNEK.equals("Rzepołuch")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.rzepoluch));
            tvTytuł.setText(R.string.rzepoluch_tytuł);
            tvOpis.setText(R.string.rzepoluch_opis);
        }
        if(GATUNEK.equals("Samotnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.samotnik));
            tvTytuł.setText(R.string.samotnik_tytuł);
            tvOpis.setText(R.string.samotnik_opis);
        }
        if(GATUNEK.equals("SępKasztanowaty")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sep_kasztanowaty));
            tvTytuł.setText(R.string.sep_kasztanowaty_tytuł);
            tvOpis.setText(R.string.sep_kasztanowaty_opis);
        }
        if(GATUNEK.equals("SępPłowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sep_plowy));
            tvTytuł.setText(R.string.sep_plowy_tytuł);
            tvOpis.setText(R.string.sep_plowy_opis);
        }
        if(GATUNEK.equals("Sierpówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sierpowka));
            tvTytuł.setText(R.string.sierpowka_tytuł);
            tvOpis.setText(R.string.sierpowka_opis);
        }
        if(GATUNEK.equals("SieweczkaMongolska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sieweczka_mongolska));
            tvTytuł.setText(R.string.sieweczka_mongolska_tytuł);
            tvOpis.setText(R.string.sieweczka_mongolska_opis);
        }
        if(GATUNEK.equals("SieweczkaMorska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sieweczka_morska));
            tvTytuł.setText(R.string.sieweczka_morska_tytuł);
            tvOpis.setText(R.string.sieweczka_morska_opis);
        }
        if(GATUNEK.equals("SieweczkaObrożna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sieweczka_obrozna));
            tvTytuł.setText(R.string.sieweczka_obrozna_tytuł);
            tvOpis.setText(R.string.sieweczka_obrozna_opis);
        }
        if(GATUNEK.equals("SieweczkaPustynna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sieweczka_pustynna));
            tvTytuł.setText(R.string.sieweczka_pustynna_tytuł);
            tvOpis.setText(R.string.sieweczka_pustynna_opis);
        }
        if(GATUNEK.equals("SieweczkaRzeczna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sieweczka_rzeczna));
            tvTytuł.setText(R.string.sieweczka_rzeczna_tytuł);
            tvOpis.setText(R.string.sieweczka_rzeczna_opis);
        }
        if(GATUNEK.equals("SiewkaSzara")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.siewka_szara));
            tvTytuł.setText(R.string.siewka_szara_tytuł);
            tvOpis.setText(R.string.siewka_szara_opis);
        }
        if(GATUNEK.equals("SiewkaZłota")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.siewka_zlota));
            tvTytuł.setText(R.string.siewka_zlota_tytuł);
            tvOpis.setText(R.string.siewka_zlota_opis);
        }
        if(GATUNEK.equals("SiewkaZłotawa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.siewka_zlotawa));
            tvTytuł.setText(R.string.siewka_zlotawa_tytuł);
            tvOpis.setText(R.string.siewka_zlotawa_opis);
        }
        if(GATUNEK.equals("Siewnica")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.siewnica));
            tvTytuł.setText(R.string.siewnica_tytuł);
            tvOpis.setText(R.string.siewnica_opis);
        }
        if(GATUNEK.equals("SikoraLazurowa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sikora_lazurowa));
            tvTytuł.setText(R.string.sikora_lazurowa_tytuł);
            tvOpis.setText(R.string.sikora_lazurowa_opis);
        }
        if(GATUNEK.equals("SikoraUboga")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sikora_uboga));
            tvTytuł.setText(R.string.sikora_uboga_tytuł);
            tvOpis.setText(R.string.sikora_uboga_opis);
        }
        if(GATUNEK.equals("Siniak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.siniak));
            tvTytuł.setText(R.string.siniak_tytuł);
            tvOpis.setText(R.string.siniak_opis);
        }
        if(GATUNEK.equals("Siwerniak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.siwerniak));
            tvTytuł.setText(R.string.siwerniak_tytuł);
            tvOpis.setText(R.string.siwerniak_opis);
        }
        if(GATUNEK.equals("SkowrończykKrótkopalcowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.skowronczyk_krotkopalcowy));
            tvTytuł.setText(R.string.skowronczyk_krotkopalcowy_tytuł);
            tvOpis.setText(R.string.skowronczyk_krotkopalcowy_opis);
        }
        if(GATUNEK.equals("Skowronek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.skowronek));
            tvTytuł.setText(R.string.skowronek_tytuł);
            tvOpis.setText(R.string.skowronek_opis);
        }
        if(GATUNEK.equals("SkowronekBiałoskrzydły")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.skowronek_bialoskrzydly));
            tvTytuł.setText(R.string.skowronek_bialoskrzydly_tytuł);
            tvOpis.setText(R.string.skowronek_bialoskrzydly_opis);
        }
        if(GATUNEK.equals("Słonka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.slonka));
            tvTytuł.setText(R.string.slonka_tytuł);
            tvOpis.setText(R.string.slonka_opis);
        }
        if(GATUNEK.equals("SłowikRdzawy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.slowik_rdzawy));
            tvTytuł.setText(R.string.slowik_rdzawy_tytuł);
            tvOpis.setText(R.string.slowik_rdzawy_opis);
        }
        if(GATUNEK.equals("SłowikSyberyjski")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.slowik_syberyjski));
            tvTytuł.setText(R.string.slowik_syberyjski_tytuł);
            tvOpis.setText(R.string.slowik_syberyjski_opis);
        }
        if(GATUNEK.equals("SłowikSzary")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.slowik_szary));
            tvTytuł.setText(R.string.slowik_szary_tytuł);
            tvOpis.setText(R.string.slowik_szary_opis);
        }
        if(GATUNEK.equals("SokółSkalny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sokol_skalny));
            tvTytuł.setText(R.string.sokol_skalny_tytuł);
            tvOpis.setText(R.string.sokol_skalny_opis);
        }
        if(GATUNEK.equals("SokółWędrowny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sokol_wedrowny));
            tvTytuł.setText(R.string.sokol_wedrowny_tytuł);
            tvOpis.setText(R.string.sokol_wedrowny_opis);
        }
        if(GATUNEK.equals("Sosnówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sosnowka));
            tvTytuł.setText(R.string.sosnowka_tytuł);
            tvOpis.setText(R.string.sosnowka_opis);
        }
        if(GATUNEK.equals("SowaJarzębata")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sowa_jarzebata));
            tvTytuł.setText(R.string.sowa_jarzebata_tytuł);
            tvOpis.setText(R.string.sowa_jarzebata_opis);
        }
        if(GATUNEK.equals("SowaŚnieżna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sowa_sniezna));
            tvTytuł.setText(R.string.sowa_sniezna_tytuł);
            tvOpis.setText(R.string.sowa_sniezna_opis);
        }
        if(GATUNEK.equals("Sójka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sojka));
            tvTytuł.setText(R.string.sojka_tytuł);
            tvOpis.setText(R.string.sojka_opis);
        }
        if(GATUNEK.equals("SójkaSyberyjska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sojka_syberyjska));
            tvTytuł.setText(R.string.sojka_syberyjska_tytuł);
            tvOpis.setText(R.string.sojka_syberyjska_opis);
        }
        if(GATUNEK.equals("Sóweczka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.soweczka));
            tvTytuł.setText(R.string.soweczka_tytuł);
            tvOpis.setText(R.string.soweczka_opis);
        }
        if(GATUNEK.equals("Sroka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sroka));
            tvTytuł.setText(R.string.sroka_tytuł);
            tvOpis.setText(R.string.sroka_opis);
        }
        if(GATUNEK.equals("Srokosz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.srokosz));
            tvTytuł.setText(R.string.srokosz_tytuł);
            tvOpis.setText(R.string.srokosz_opis);
        }
        if(GATUNEK.equals("Sterniczka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sterniczka));
            tvTytuł.setText(R.string.sterniczka_tytuł);
            tvOpis.setText(R.string.sterniczka_opis);
        }
        if(GATUNEK.equals("SterniczkaJamajska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sterniczka_jamajska));
            tvTytuł.setText(R.string.sterniczka_jamajska_tytuł);
            tvOpis.setText(R.string.sterniczka_jamajska_opis);
        }
        if(GATUNEK.equals("Strepet")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.strepet));
            tvTytuł.setText(R.string.strepet_tytuł);
            tvOpis.setText(R.string.strepet_opis);
        }
        if(GATUNEK.equals("Strumieniówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.strumieniowka));
            tvTytuł.setText(R.string.strumieniowka_tytuł);
            tvOpis.setText(R.string.strumieniowka_opis);
        }
        if(GATUNEK.equals("Strzyżyk")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.strzyzyk));
            tvTytuł.setText(R.string.strzyzyk_tytuł);
            tvOpis.setText(R.string.strzyzyk_opis);
        }
        if(GATUNEK.equals("Syczek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.syczek));
            tvTytuł.setText(R.string.syczek_tytuł);
            tvOpis.setText(R.string.syczek_opis);
        }
        if(GATUNEK.equals("Szablodziób")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szablodziob));
            tvTytuł.setText(R.string.szablodziob_tytuł);
            tvOpis.setText(R.string.szablodziob_opis);
        }
        if(GATUNEK.equals("Szczudłak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szczudlak));
            tvTytuł.setText(R.string.szczudlak_tytuł);
            tvOpis.setText(R.string.szczudlak_opis);
        }
        if(GATUNEK.equals("Szczygieł")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szczygiel));
            tvTytuł.setText(R.string.szczygiel_tytuł);
            tvOpis.setText(R.string.szczygiel_opis);
        }
        if(GATUNEK.equals("Szlachar")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szlachar));
            tvTytuł.setText(R.string.szlachar_tytuł);
            tvOpis.setText(R.string.szlachar_opis);
        }
        if(GATUNEK.equals("SzlamiecDługodzioby")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szlamiec_dlugodzioby));
            tvTytuł.setText(R.string.szlamiec_dlugodzioby_tytuł);
            tvOpis.setText(R.string.szlamiec_dlugodzioby_opis);
        }
        if(GATUNEK.equals("Szlamnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szlamnik));
            tvTytuł.setText(R.string.szlamnik_tytuł);
            tvOpis.setText(R.string.szlamnik_opis);
        }
        if(GATUNEK.equals("Szpak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.szpak));
            tvTytuł.setText(R.string.szpak_tytuł);
            tvOpis.setText(R.string.szpak_opis);
        }
        if(GATUNEK.equals("Ścierwnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.scierwnik));
            tvTytuł.setText(R.string.scierwnik_tytuł);
            tvOpis.setText(R.string.scierwnik_opis);
        }
        if(GATUNEK.equals("Ślepowron")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.slepowron));
            tvTytuł.setText(R.string.slepowron_tytuł);
            tvOpis.setText(R.string.slepowron_opis);
        }
        if(GATUNEK.equals("Śmieszka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.smieszka));
            tvTytuł.setText(R.string.smieszka_tytuł);
            tvOpis.setText(R.string.smieszka_opis);
        }
        if(GATUNEK.equals("Śnieguła")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sniegula));
            tvTytuł.setText(R.string.sniegula_tytuł);
            tvOpis.setText(R.string.sniegula_opis);
        }
        if(GATUNEK.equals("Śnieżka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.sniezka));
            tvTytuł.setText(R.string.sniezka_tytuł);
            tvOpis.setText(R.string.sniezka_opis);
        }
        if(GATUNEK.equals("Śpiewak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.spiewak));
            tvTytuł.setText(R.string.spiewak_tytuł);
            tvOpis.setText(R.string.spiewak_opis);
        }
        if(GATUNEK.equals("ŚwiergotekDrzewny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_drzewny));
            tvTytuł.setText(R.string.swiergotek_drzewny_tytuł);
            tvOpis.setText(R.string.swiergotek_drzewny_opis);
        }
        if(GATUNEK.equals("ŚwiergotekŁąkowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_lakowy));
            tvTytuł.setText(R.string.swiergotek_lakowy_tytuł);
            tvOpis.setText(R.string.swiergotek_lakowy_opis);
        }
        if(GATUNEK.equals("ŚwiergotekNadmorski")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_nadmorski));
            tvTytuł.setText(R.string.swiergotek_nadmorski_tytuł);
            tvOpis.setText(R.string.swiergotek_nadmorski_opis);
        }
        if(GATUNEK.equals("ŚwiergotekPolny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_polny));
            tvTytuł.setText(R.string.swiergotek_polny_tytuł);
            tvOpis.setText(R.string.swiergotek_polny_opis);
        }
        if(GATUNEK.equals("ŚwiergotekRdzawogardły")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_rdzawogardly));
            tvTytuł.setText(R.string.swiergotek_rdzawogardly_tytuł);
            tvOpis.setText(R.string.swiergotek_rdzawogardly_opis);
        }
        if(GATUNEK.equals("ŚwiergotekSzponiasty")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_szponiasty));
            tvTytuł.setText(R.string.swiergotek_szponiasty_tytuł);
            tvOpis.setText(R.string.swiergotek_szponiasty_opis);
        }
        if(GATUNEK.equals("ŚwiergotekTajgowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swiergotek_tajgowy));
            tvTytuł.setText(R.string.swiergotek_tajgowy_tytuł);
            tvOpis.setText(R.string.swiergotek_tajgowy_opis);
        }
        if(GATUNEK.equals("Świerszczak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swierszczak));
            tvTytuł.setText(R.string.swierszczak_tytuł);
            tvOpis.setText(R.string.swierszczak_opis);
        }
        if(GATUNEK.equals("ŚwierszczakMelodyjny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swierszczak_melodyjny));
            tvTytuł.setText(R.string.swierszczak_melodyjny_tytuł);
            tvOpis.setText(R.string.swierszczak_melodyjny_opis);
        }
        if(GATUNEK.equals("Świstun")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistun));
            tvTytuł.setText(R.string.swistun_tytuł);
            tvOpis.setText(R.string.swistun_opis);
        }
        if(GATUNEK.equals("ŚwistunAmerykański")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistun_amerykanski));
            tvTytuł.setText(R.string.swistun_amerykanski_tytuł);
            tvOpis.setText(R.string.swistun_amerykanski_opis);
        }
        if(GATUNEK.equals("ŚwistunkaAłtańska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_altanska));
            tvTytuł.setText(R.string.swistunka_altanska_tytuł);
            tvOpis.setText(R.string.swistunka_altanska_opis);
        }
        if(GATUNEK.equals("ŚwistunkaBrunatna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_brunatna));
            tvTytuł.setText(R.string.swistunka_brunatna_tytuł);
            tvOpis.setText(R.string.swistunka_brunatna_opis);
        }
        if(GATUNEK.equals("ŚwistunkaGórska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_gorska));
            tvTytuł.setText(R.string.swistunka_gorska_tytuł);
            tvOpis.setText(R.string.swistunka_gorska_opis);
        }
        if(GATUNEK.equals("ŚwistunkaGrubodzioba")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_grubodzioba));
            tvTytuł.setText(R.string.swistunka_grubodzioba_tytuł);
            tvOpis.setText(R.string.swistunka_grubodzioba_opis);
        }
        if(GATUNEK.equals("ŚwistunkaIberyjska")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_iberyjska));
            tvTytuł.setText(R.string.swistunka_iberyjska_tytuł);
            tvOpis.setText(R.string.swistunka_iberyjska_opis);
        }
        if(GATUNEK.equals("ŚwistunkaLeśna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_lesna));
            tvTytuł.setText(R.string.swistunka_lesna_tytuł);
            tvOpis.setText(R.string.swistunka_lesna_opis);
        }
        if(GATUNEK.equals("ŚwistunkaPółnocna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_polnocna));
            tvTytuł.setText(R.string.swistunka_polnocna_tytuł);
            tvOpis.setText(R.string.swistunka_polnocna_opis);
        }
        if(GATUNEK.equals("ŚwistunkaZłotawa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_zlotawa));
            tvTytuł.setText(R.string.swistunka_zlotawa_tytuł);
            tvOpis.setText(R.string.swistunka_zlotawa_opis);
        }
        if(GATUNEK.equals("ŚwistunkaŻółtawa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.swistunka_zoltawa));
            tvTytuł.setText(R.string.swistunka_zoltawa_tytuł);
            tvOpis.setText(R.string.swistunka_zoltawa_opis);
        }
        if(GATUNEK.equals("Tamaryszka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.tamaryszka));
            tvTytuł.setText(R.string.tamaryszka_tytuł);
            tvOpis.setText(R.string.tamaryszka_opis);
        }
        if(GATUNEK.equals("Terekia")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.terekia));
            tvTytuł.setText(R.string.terekia_tytuł);
            tvOpis.setText(R.string.terekia_opis);
        }
        if(GATUNEK.equals("Trzciniak")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trzciniak));
            tvTytuł.setText(R.string.trzciniak_tytuł);
            tvOpis.setText(R.string.trzciniak_opis);
        }
        if(GATUNEK.equals("Trzcinniczek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trzcinniczek));
            tvTytuł.setText(R.string.trzcinniczek_tytuł);
            tvOpis.setText(R.string.trzcinniczek_opis);
        }
        if(GATUNEK.equals("TrzcinniczekKaspijski")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trzcinniczek_kaspijski));
            tvTytuł.setText(R.string.trzcinniczek_kaspijski_tytuł);
            tvOpis.setText(R.string.trzcinniczek_kaspijski_opis);
        }
        if(GATUNEK.equals("Trzmielojad")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trzmielojad));
            tvTytuł.setText(R.string.trzmielojad_tytuł);
            tvOpis.setText(R.string.trzmielojad_opis);
        }
        if(GATUNEK.equals("Trznadel")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadel));
            tvTytuł.setText(R.string.trznadel_tytuł);
            tvOpis.setText(R.string.trznadel_opis);
        }
        if(GATUNEK.equals("TrznadelBiałogłowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadel_bialoglowy));
            tvTytuł.setText(R.string.trznadel_bialoglowy_tytuł);
            tvOpis.setText(R.string.trznadel_bialoglowy_opis);
        }
        if(GATUNEK.equals("TrznadelCzarnogłowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadel_czarnoglowy));
            tvTytuł.setText(R.string.trznadel_czarnoglowy_tytuł);
            tvOpis.setText(R.string.trznadel_czarnoglowy_opis);
        }
        if(GATUNEK.equals("TrznadelCzubaty")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadel_czubaty));
            tvTytuł.setText(R.string.trznadel_czubaty_tytuł);
            tvOpis.setText(R.string.trznadel_czubaty_opis);
        }
        if(GATUNEK.equals("Trznadelek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadelek));
            tvTytuł.setText(R.string.trznadelek_tytuł);
            tvOpis.setText(R.string.trznadelek_opis);
        }
        if(GATUNEK.equals("TrznadelZłotawy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadel_zlotawy));
            tvTytuł.setText(R.string.trznadel_zlotawy_tytuł);
            tvOpis.setText(R.string.trznadel_zlotawy_opis);
        }
        if(GATUNEK.equals("TrznadelZłotobrewy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.trznadel_zlotobrewy));
            tvTytuł.setText(R.string.trznadel_zlotobrewy_tytuł);
            tvOpis.setText(R.string.trznadel_zlotobrewy_opis);
        }
        if(GATUNEK.equals("Turkan")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.turkan));
            tvTytuł.setText(R.string.turkan_tytuł);
            tvOpis.setText(R.string.turkan_opis);
        }
        if(GATUNEK.equals("Turkawka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.turkawka));
            tvTytuł.setText(R.string.turkawka_tytuł);
            tvOpis.setText(R.string.turkawka_opis);
        }
        if(GATUNEK.equals("TurkawkaWschodnia")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.turkawka_wschodnia));
            tvTytuł.setText(R.string.turkawka_wschodnia_tytuł);
            tvOpis.setText(R.string.turkawka_wschodnia_opis);
        }
        if(GATUNEK.equals("Uhla")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.uhla));
            tvTytuł.setText(R.string.uhla_tytuł);
            tvOpis.setText(R.string.uhla_opis);
        }
        if(GATUNEK.equals("UhlaGarbonosa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.uhla_garbonosa));
            tvTytuł.setText(R.string.uhla_garbonosa_tytuł);
            tvOpis.setText(R.string.uhla_garbonosa_opis);
        }
        if(GATUNEK.equals("Uszatka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.uszatka));
            tvTytuł.setText(R.string.uszatka_tytuł);
            tvOpis.setText(R.string.uszatka_opis);
        }
        if(GATUNEK.equals("UszatkaBłotna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.uszatka_blotna));
            tvTytuł.setText(R.string.uszatka_blotna_tytuł);
            tvOpis.setText(R.string.uszatka_blotna_opis);
        }
        if(GATUNEK.equals("Warzęcha")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.warzecha));
            tvTytuł.setText(R.string.warzecha_tytuł);
            tvOpis.setText(R.string.warzecha_opis);
        }
        if(GATUNEK.equals("Wąsatka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wasatka));
            tvTytuł.setText(R.string.wasatka_tytuł);
            tvOpis.setText(R.string.wasatka_opis);
        }
        if(GATUNEK.equals("Wieszczek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wieszczek));
            tvTytuł.setText(R.string.wieszczek_tytuł);
            tvOpis.setText(R.string.wieszczek_opis);
        }
        if(GATUNEK.equals("Wilga")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wilga));
            tvTytuł.setText(R.string.wilga_tytuł);
            tvOpis.setText(R.string.wilga_opis);
        }
        if(GATUNEK.equals("WireonekCzerwonooki")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wireonek_czerwonooki));
            tvTytuł.setText(R.string.wireonek_czerwonooki_tytuł);
            tvOpis.setText(R.string.wireonek_czerwonooki_opis);
        }
        if(GATUNEK.equals("Włochatka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wlochatka));
            tvTytuł.setText(R.string.wlochatka_tytuł);
            tvOpis.setText(R.string.wlochatka_opis);
        }
        if(GATUNEK.equals("Wodniczka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wodniczka));
            tvTytuł.setText(R.string.wodniczka_tytuł);
            tvOpis.setText(R.string.wodniczka_opis);
        }
        if(GATUNEK.equals("Wodnik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wodnik));
            tvTytuł.setText(R.string.wodnik_tytuł);
            tvOpis.setText(R.string.wodnik_opis);
        }
        if(GATUNEK.equals("Wójcik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wojcik));
            tvTytuł.setText(R.string.wojcik_tytuł);
            tvOpis.setText(R.string.wojcik_opis);
        }
        if(GATUNEK.equals("Wróbel")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wrobel));
            tvTytuł.setText(R.string.wrobel_tytuł);
            tvOpis.setText(R.string.wrobel_opis);
        }
        if(GATUNEK.equals("WróbelSkalny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wrobel_skalny));
            tvTytuł.setText(R.string.wrobel_skalny_tytuł);
            tvOpis.setText(R.string.wrobel_skalny_opis);
        }
        if(GATUNEK.equals("WronaSiwa")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wrona_siwa));
            tvTytuł.setText(R.string.wrona_siwa_tytuł);
            tvOpis.setText(R.string.wrona_siwa_opis);
        }
        if(GATUNEK.equals("WydrzykDługosterny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wydrzyk_dlugosterny));
            tvTytuł.setText(R.string.wydrzyk_dlugosterny_tytuł);
            tvOpis.setText(R.string.wydrzyk_dlugosterny_opis);
        }
        if(GATUNEK.equals("WydrzykOstrosterny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wydrzyk_ostrosterny));
            tvTytuł.setText(R.string.wydrzyk_ostrosterny_tytuł);
            tvOpis.setText(R.string.wydrzyk_ostrosterny_opis);
        }
        if(GATUNEK.equals("WydrzykTęposterny")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wydrzyk_teposterny));
            tvTytuł.setText(R.string.wydrzyk_teposterny_tytuł);
            tvOpis.setText(R.string.wydrzyk_teposterny_opis);
        }
        if(GATUNEK.equals("WydrzykWielki")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.wydrzyk_wielki));
            tvTytuł.setText(R.string.wydrzyk_wielki_tytuł);
            tvOpis.setText(R.string.wydrzyk_wielki_opis);
        }
        if(GATUNEK.equals("Zaganiacz")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zaganiacz));
            tvTytuł.setText(R.string.zaganiacz_tytuł);
            tvOpis.setText(R.string.zaganiacz_opis);
        }
        if(GATUNEK.equals("ZaganiaczMały")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zaganiacz_maly));
            tvTytuł.setText(R.string.zaganiacz_maly_tytuł);
            tvOpis.setText(R.string.zaganiacz_maly_opis);
        }
        if(GATUNEK.equals("ZaganiaczSzczebiotliwy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zaganiacz_szczebiotliwy));
            tvTytuł.setText(R.string.zaganiacz_szczebiotliwy_tytuł);
            tvOpis.setText(R.string.zaganiacz_szczebiotliwy_opis);
        }
        if(GATUNEK.equals("Zaroślówka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zaroslowka));
            tvTytuł.setText(R.string.zaroslowka_tytuł);
            tvOpis.setText(R.string.zaroslowka_opis);
        }
        if(GATUNEK.equals("Zausznik")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zausznik));
            tvTytuł.setText(R.string.zausznik_tytuł);
            tvOpis.setText(R.string.zausznik_opis);
        }
        if(GATUNEK.equals("Zięba")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zieba));
            tvTytuł.setText(R.string.zieba_tytuł);
            tvOpis.setText(R.string.zieba_opis);
        }
        if(GATUNEK.equals("Zielonka")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zielonka));
            tvTytuł.setText(R.string.zielonka_tytuł);
            tvOpis.setText(R.string.zielonka_opis);
        }
        if(GATUNEK.equals("Zimorodek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zimorodek));
            tvTytuł.setText(R.string.zimorodek_tytuł);
            tvOpis.setText(R.string.zimorodek_opis);
        }
        if(GATUNEK.equals("Zniczek")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zniczek));
            tvTytuł.setText(R.string.zniczek_tytuł);
            tvOpis.setText(R.string.zniczek_opis);
        }
        if(GATUNEK.equals("Żolna")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zolna));
            tvTytuł.setText(R.string.zolna_tytuł);
            tvOpis.setText(R.string.zolna_opis);
        }
        if(GATUNEK.equals("Żuraw")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zuraw));
            tvTytuł.setText(R.string.zuraw_tytuł);
            tvOpis.setText(R.string.zuraw_opis);
        }
        if(GATUNEK.equals("ŻurawStepowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zuraw_stepowy));
            tvTytuł.setText(R.string.zuraw_stepowy_tytuł);
            tvOpis.setText(R.string.zuraw_stepowy_opis);
        }
        if(GATUNEK.equals("ŻwirowiecŁąkowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zwirowiec_lakowy));
            tvTytuł.setText(R.string.zwirowiec_lakowy_tytuł);
            tvOpis.setText(R.string.zwirowiec_lakowy_opis);
        }
        if(GATUNEK.equals("ŻwirowiecStepowy")) {
            imgViewGatunek.setImageDrawable(getResources().getDrawable(R.drawable.zwirowiec_stepowy));
            tvTytuł.setText(R.string.zwirowiec_stepowy_tytuł);
            tvOpis.setText(R.string.zwirowiec_stepowy_opis);
        }
    }
}
