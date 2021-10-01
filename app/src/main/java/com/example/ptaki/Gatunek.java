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
    }
}