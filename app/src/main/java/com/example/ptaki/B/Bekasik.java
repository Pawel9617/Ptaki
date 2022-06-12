package com.example.ptaki.B;

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

import com.example.ptaki.Gatunek;
import com.example.ptaki.GpsTracker;
import com.example.ptaki.R;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Bekasik extends AppCompatActivity implements  View.OnClickListener{

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "BekasikStatus";
    public static final String TEXT2 = "BekasikNotatka";
    public static final String TEXT3 = "BekasikZłapany";
    public static final String TEXT4 = "LiczbaZnalezionych";
    public static final String DATAZŁAPANIA = "BekasikDataZłapania";
    public static final String LOKALIZACJA = "BekasikMiejsceZłapania";
    public static final String MAZDJĘCIE = "BekasikMaZdjęcie";
    public static final String ZDJĘCIE = "BekasikZdjęcie;";

    private static final int GALLERY_REQUEST = 9;

    private ImageView imageView;

    Button bMamGo;
    TextView tvStatus;
    String status;
    String notatka;
    int IlośćZłapanych;
    boolean Złapany;

    private GpsTracker gpsTracker;
    private String lokalizacja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bekasik);
        tvStatus = (TextView)findViewById(R.id.txtViewStatus);
        bMamGo = (Button)findViewById(R.id.bMamGo);
        bMamGo.setOnClickListener(this);
        imageView = findViewById(R.id.imgViewBekasikUżytkownika);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getImageFromGallery();
                Toast.makeText(Bekasik.this, "Funkcja niedostępna", Toast.LENGTH_SHORT).show();
            }
        });
        loadData();
        updateViews();
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
        EditText editText = findViewById(R.id.BekasikNotatka);
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
        EditText editText = findViewById(R.id.BekasikNotatka);
        editText.setText(notatka);
    }

    public void getLocation(View view){
        gpsTracker = new GpsTracker(Bekasik.this);
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
}
