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
                if (sGatunek.equals("Białozór")) {
                    editor.putString("BiałozórStatus", "Nie złapano");
                    editor.putBoolean("BiałozórZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus arktyczny")) {
                    editor.putString("BiegusArktycznyStatus", "Nie złapano");
                    editor.putBoolean("BiegusArktycznyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus długoskrzydły")) {
                    editor.putString("BiegusDługoskrzydłyStatus", "Nie złapano");
                    editor.putBoolean("BiegusDługoskrzydłyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus karłowaty")) {
                    editor.putString("BiegusKarłowatyStatus", "Nie złapano");
                    editor.putBoolean("BiegusKarłowatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus krzywodzioby")) {
                    editor.putString("BiegusKrzywodziobyStatus", "Nie złapano");
                    editor.putBoolean("BiegusKrzywodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus malutki")) {
                    editor.putString("BiegusMalutkiStatus", "Nie złapano");
                    editor.putBoolean("BiegusMalutkiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus mały")) {
                    editor.putString("BiegusMałyStatus", "Nie złapano");
                    editor.putBoolean("BiegusMałyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus morski")) {
                    editor.putString("BiegusMorskiStatus", "Nie złapano");
                    editor.putBoolean("BiegusMorskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus płaskodzioby")) {
                    editor.putString("BiegusPłaskodziobyStatus", "Nie złapano");
                    editor.putBoolean("BiegusPłaskodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus płowy")) {
                    editor.putString("BiegusPłowyStatus", "Nie złapano");
                    editor.putBoolean("BiegusPłowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus rdzawy")) {
                    editor.putString("BiegusRdzawyStatus", "Nie złapano");
                    editor.putBoolean("BiegusRdzawyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus tundrowy")) {
                    editor.putString("BiegusTundrowyStatus", "Nie złapano");
                    editor.putBoolean("BiegusTundrowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus wielki")) {
                    editor.putString("BiegusWielkiStatus", "Nie złapano");
                    editor.putBoolean("BiegusWielkiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Biegus zmienny")) {
                    editor.putString("BiegusZmiennyStatus", "Nie złapano");
                    editor.putBoolean("BiegusZmiennyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bielaczek")) {
                    editor.putString("BielaczekStatus", "Nie złapano");
                    editor.putBoolean("BielaczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bielik")) {
                    editor.putString("BielikStatus", "Nie złapano");
                    editor.putBoolean("BielikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bielik wschodni")) {
                    editor.putString("BielikWschodniStatus", "Nie złapano");
                    editor.putBoolean("BielikWschodniZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Birginiak")) {
                    editor.putString("BirginiakStatus", "Nie złapano");
                    editor.putBoolean("BirginiakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Błotniak łąkowy")) {
                    editor.putString("BłotniakŁąkowyStatus", "Nie złapano");
                    editor.putBoolean("BłotniakŁąkowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Błotniak stawowy")) {
                    editor.putString("BłotniakStawowyStatus", "Nie złapano");
                    editor.putBoolean("BłotniakStawowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Błotniak stepowy")) {
                    editor.putString("BłotniakStepowyStatus", "Nie złapano");
                    editor.putBoolean("BłotniakStepowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Błotniak zbożowy")) {
                    editor.putString("BłotniakZbożowyStatus", "Nie złapano");
                    editor.putBoolean("BłotniakZbożowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bocian biały")) {
                    editor.putString("BocianBiałyStatus", "Nie złapano");
                    editor.putBoolean("BocianBiałyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bocian czarny")) {
                    editor.putString("BocianCzarnyStatus", "Nie złapano");
                    editor.putBoolean("BocianCzarnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Bogatka")) {
                    editor.putString("BogatkaStatus", "Nie złapano");
                    editor.putBoolean("BogatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brodziec piegowaty")) {
                    editor.putString("BrodziecPiegowatyStatus", "Nie złapano");
                    editor.putBoolean("BrodziecPiegowatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brodziec piskliwy")) {
                    editor.putString("BrodziecPiskliwyStatus", "Nie złapano");
                    editor.putBoolean("BrodziecPiskliwyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brodziec plamisty")) {
                    editor.putString("BrodziecPlamistyStatus", "Nie złapano");
                    editor.putBoolean("BrodziecPlamistyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brodziec pławny")) {
                    editor.putString("BrodziecPławnyStatus", "Nie złapano");
                    editor.putBoolean("BrodziecPławnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brodziec śniady")) {
                    editor.putString("BrodziecŚniadyStatus", "Nie złapano");
                    editor.putBoolean("BrodziecŚniadyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brodziec żółtonogi")) {
                    editor.putString("BrodziecŻółtonogiStatus", "Nie złapano");
                    editor.putBoolean("BrodziecŻółtonogiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brzęczka")) {
                    editor.putString("BrzęczkaStatus", "Nie złapano");
                    editor.putBoolean("BrzęczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Brzegówka")) {
                    editor.putString("BrzegówkaStatus", "Nie złapano");
                    editor.putBoolean("BrzegówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Burzyk balearski")) {
                    editor.putString("BurzykBalearskiStatus", "Nie złapano");
                    editor.putBoolean("BurzykBalearskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Burzyk północny")) {
                    editor.putString("BurzykPółnocnyStatus", "Nie złapano");
                    editor.putBoolean("BurzykPółnocnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Burzyk szary")) {
                    editor.putString("BurzykSzaryStatus", "Nie złapano");
                    editor.putBoolean("BurzykSzaryZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Burzyk żółtodzioby")) {
                    editor.putString("BurzykŻółtodziobyStatus", "Nie złapano");
                    editor.putBoolean("BurzykŻółtodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cierlik")) {
                    editor.putString("CierlikStatus", "Nie złapano");
                    editor.putBoolean("CierlikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cierniówka")) {
                    editor.putString("CierniówkaStatus", "Nie złapano");
                    editor.putBoolean("CierniówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cietrzew")) {
                    editor.putString("CietrzewStatus", "Nie złapano");
                    editor.putBoolean("CietrzewZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cyraneczka")) {
                    editor.putString("CyraneczkaStatus", "Nie złapano");
                    editor.putBoolean("CyraneczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cyraneczka karolińska")) {
                    editor.putString("CyraneczkaKarolińskaStatus", "Nie złapano");
                    editor.putBoolean("CyraneczkaKarolińskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cyranka")) {
                    editor.putString("CyrankaStatus", "Nie złapano");
                    editor.putBoolean("CyrankaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Cyranka modroskrzydła")) {
                    editor.putString("CyrankaModroskrzydłaStatus", "Nie złapano");
                    editor.putBoolean("CyrankaModroskrzydłaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czajka")) {
                    editor.putString("CzajkaStatus", "Nie złapano");
                    editor.putBoolean("CzajkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czajka stepowa")) {
                    editor.putString("CzajkaStepowaStatus", "Nie złapano");
                    editor.putBoolean("CzajkaStepowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czajka towarzyska")) {
                    editor.putString("CzajkaTowarzyskaStatus", "Nie złapano");
                    editor.putBoolean("CzajkaTowarzyskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czapla biała")) {
                    editor.putString("CzaplaBiałaStatus", "Nie złapano");
                    editor.putBoolean("CzaplaBiałaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czapla modronosa")) {
                    editor.putString("CzaplaModronosaStatus", "Nie złapano");
                    editor.putBoolean("CzaplaModronosaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czapla nadobna")) {
                    editor.putString("CzaplaNadobnaStatus", "Nie złapano");
                    editor.putBoolean("CzaplaNadobnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czapla purpurowa")) {
                    editor.putString("CzaplaPurpurowaStatus", "Nie złapano");
                    editor.putBoolean("CzaplaPurpurowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czapla siwa")) {
                    editor.putString("CzaplaSiwaStatus", "Nie złapano");
                    editor.putBoolean("CzaplaSiwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czapla złotawa")) {
                    editor.putString("CzaplaZłotawaStatus", "Nie złapano");
                    editor.putBoolean("CzaplaZłotawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czarnogłówka")) {
                    editor.putString("CzarnogłówkaStatus", "Nie złapano");
                    editor.putBoolean("CzarnogłówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czarnowron")) {
                    editor.putString("CzarnowronStatus", "Nie złapano");
                    editor.putBoolean("CzarnowronZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czeczotka")) {
                    editor.putString("CzeczotkaStatus", "Nie złapano");
                    editor.putBoolean("CzeczotkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czeczotka tundrowa")) {
                    editor.putString("CzeczotkaTundrowaStatus", "Nie złapano");
                    editor.putBoolean("CzeczotkaTundrowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czernica")) {
                    editor.putString("CzernicaStatus", "Nie złapano");
                    editor.putBoolean("CzernicaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czerniczka")) {
                    editor.putString("CzerniczkaStatus", "Nie złapano");
                    editor.putBoolean("CzerniczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czubatka")) {
                    editor.putString("CzubatkaStatus", "Nie złapano");
                    editor.putBoolean("CzubatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Czyż")) {
                    editor.putString("CzyżStatus", "Nie złapano");
                    editor.putBoolean("CzyżZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Derkacz")) {
                    editor.putString("DerkaczStatus", "Nie złapano");
                    editor.putBoolean("DerkaczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drop")) {
                    editor.putString("DropStatus", "Nie złapano");
                    editor.putBoolean("DropZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozdaczek ciemny")) {
                    editor.putString("DrozdaczekCiemnyStatus", "Nie złapano");
                    editor.putBoolean("DrozdaczekCiemnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozd czarnogardły")) {
                    editor.putString("DrozdCzarnogardłyStatus", "Nie złapano");
                    editor.putBoolean("DrozdCzarnogardłyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozd obrożny")) {
                    editor.putString("DrozdObrożnyStatus", "Nie złapano");
                    editor.putBoolean("DrozdObrożnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozd oliwkowy")) {
                    editor.putString("DrozdOliwkowyStatus", "Nie złapano");
                    editor.putBoolean("DrozdOliwkowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozdoń pstry")) {
                    editor.putString("Drozdoń pstryStatus", "Nie złapano");
                    editor.putBoolean("Drozdoń pstryZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozd rdzawogardły")) {
                    editor.putString("DrozdRdzawogardłyStatus", "Nie złapano");
                    editor.putBoolean("DrozdRdzawogardłyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozd rdzawoskrzydły")) {
                    editor.putString("DrozdRdzawoskrzydłyStatus", "Nie złapano");
                    editor.putBoolean("DrozdRdzawoskrzydłyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drozd rdzawy")) {
                    editor.putString("DrozdRdzawyStatus", "Nie złapano");
                    editor.putBoolean("DrozdRdzawyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Droździk")) {
                    editor.putString("DroździkStatus", "Nie złapano");
                    editor.putBoolean("DroździkZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Drzemlik")) {
                    editor.putString("DrzemlikStatus", "Nie złapano");
                    editor.putBoolean("DrzemlikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dubelt")) {
                    editor.putString("DubeltStatus", "Nie złapano");
                    editor.putBoolean("DubeltZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dudek")) {
                    editor.putString("DudekStatus", "Nie złapano");
                    editor.putBoolean("DudekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dymówka")) {
                    editor.putString("DymówkaStatus", "Nie złapano");
                    editor.putBoolean("DymówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł białogrzbiety")) {
                    editor.putString("DzięciołBiałogrzbietyStatus", "Nie złapano");
                    editor.putBoolean("DzięciołBiałogrzbietyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł białoszyi")) {
                    editor.putString("DzięciołBiałoszyiStatus", "Nie złapano");
                    editor.putBoolean("DzięciołBiałoszyiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł czarny")) {
                    editor.putString("DzięciołCzarnyStatus", "Nie złapano");
                    editor.putBoolean("DzięciołCzarnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł duży")) {
                    editor.putString("DzięciołDużyStatus", "Nie złapano");
                    editor.putBoolean("DzięciołDużyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięciołek")) {
                    editor.putString("DzięciołekStatus", "Nie złapano");
                    editor.putBoolean("DzięciołekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł średni")) {
                    editor.putString("DzięciołŚredniStatus", "Nie złapano");
                    editor.putBoolean("DzięciołŚredniZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł trójpalczasty")) {
                    editor.putString("DzięciołTrójpalczastyStatus", "Nie złapano");
                    editor.putBoolean("DzięciołTrójpalczastyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł zielonosiwy")) {
                    editor.putString("DzięciołZielonosiwyStatus", "Nie złapano");
                    editor.putBoolean("DzięciołZielonosiwyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzięcioł zielony")) {
                    editor.putString("DzięciołZielonyStatus", "Nie złapano");
                    editor.putBoolean("DzięciołZielonyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzierlatka")) {
                    editor.putString("DzierlatkaStatus", "Nie złapano");
                    editor.putBoolean("DzierlatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzierzba czarnoczelna")) {
                    editor.putString("DzierzbaCzarnoczelnaStatus", "Nie złapano");
                    editor.putBoolean("DzierzbaCzarnoczelnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzierzba pustynna")) {
                    editor.putString("DzierzbaPustynnaStatus", "Nie złapano");
                    editor.putBoolean("DzierzbaPustynnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzierzba rdzawosterna")) {
                    editor.putString("DzierzbaRdzawosternaStatus", "Nie złapano");
                    editor.putBoolean("DzierzbaRdzawosternaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzierzba rudogłowa")) {
                    editor.putString("DzierzbaRudogłowaStatus", "Nie złapano");
                    editor.putBoolean("DzierzbaRudogłowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dziwonia")) {
                    editor.putString("DziwoniaStatus", "Nie złapano");
                    editor.putBoolean("DziwoniaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Dzwoniec")) {
                    editor.putString("DzwoniecStatus", "Nie złapano");
                    editor.putBoolean("DzwoniecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Edredon")) {
                    editor.putString("EdredonStatus", "Nie złapano");
                    editor.putBoolean("EdredonZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Flaming różowy")) {
                    editor.putString("FlamingRóżowyStatus", "Nie złapano");
                    editor.putBoolean("FlamingRóżowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Fulmar")) {
                    editor.putString("FulmarStatus", "Nie złapano");
                    editor.putBoolean("FulmarZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gadożer")) {
                    editor.putString("GadożerStatus", "Nie złapano");
                    editor.putBoolean("GadożerZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gawron")) {
                    editor.putString("GawronStatus", "Nie złapano");
                    editor.putBoolean("GawronZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gągoł")) {
                    editor.putString("GągołStatus", "Nie złapano");
                    editor.putBoolean("GągołZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gajówka")) {
                    editor.putString("GajówkaStatus", "Nie złapano");
                    editor.putBoolean("GajówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gąsiorek")) {
                    editor.putString("GąsiorekStatus", "Nie złapano");
                    editor.putBoolean("GąsiorekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gęgawa")) {
                    editor.putString("GęgawaStatus", "Nie złapano");
                    editor.putBoolean("GęgawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gęś białoczelna")) {
                    editor.putString("GęśBiałoczelnaStatus", "Nie złapano");
                    editor.putBoolean("GęśBiałoczelnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gęsiówka egipska")) {
                    editor.putString("GęsiówkaEgipskaStatus", "Nie złapano");
                    editor.putBoolean("GęsiówkaEgipskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gęś krótkodzioba")) {
                    editor.putString("GęśKrótkodziobaStatus", "Nie złapano");
                    editor.putBoolean("GęśKrótkodziobaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gęś mała")) {
                    editor.putString("GęśMałaStatus", "Nie złapano");
                    editor.putBoolean("GęśMałaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gęś zbożowa")) {
                    editor.putString("GęśZbożowaStatus", "Nie złapano");
                    editor.putBoolean("GęśZbożowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gil")) {
                    editor.putString("GilStatus", "Nie złapano");
                    editor.putBoolean("GilZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Głowienka")) {
                    editor.putString("GłowienkaStatus", "Nie złapano");
                    editor.putBoolean("GłowienkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Głuptak")) {
                    editor.putString("GłuptakStatus", "Nie złapano");
                    editor.putBoolean("GłuptakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Głuszec")) {
                    editor.putString("GłuszecStatus", "Nie złapano");
                    editor.putBoolean("GłuszecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Głuszek")) {
                    editor.putString("GłuszekStatus", "Nie złapano");
                    editor.putBoolean("GłuszekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Gołąb miejski")) {
                    editor.putString("GołąbMiejskiStatus", "Nie złapano");
                    editor.putBoolean("GołąbMiejskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Górniczek")) {
                    editor.putString("GórniczekStatus", "Nie złapano");
                    editor.putBoolean("GórniczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Grubodziób")) {
                    editor.putString("GrubodzióbStatus", "Nie złapano");
                    editor.putBoolean("GrubodzióbZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Grzywacz")) {
                    editor.putString("GrzywaczStatus", "Nie złapano");
                    editor.putBoolean("GrzywaczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Hełmiatka")) {
                    editor.putString("HełmiatkaStatus", "Nie złapano");
                    editor.putBoolean("HełmiatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Hubara arabska")) {
                    editor.putString("HubaraArabskaStatus", "Nie złapano");
                    editor.putBoolean("HubaraArabskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ibis kasztanowaty")) {
                    editor.putString("IbisKasztanowatyStatus", "Nie złapano");
                    editor.putBoolean("IbisKasztanowatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jarząbek")) {
                    editor.putString("JarząbekStatus", "Nie złapano");
                    editor.putBoolean("JarząbekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jarzębatka")) {
                    editor.putString("JarzębatkaStatus", "Nie złapano");
                    editor.putBoolean("JarzębatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jaskółka rudawa")) {
                    editor.putString("JaskółkaRudawaStatus", "Nie złapano");
                    editor.putBoolean("JaskółkaRudawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jastrząb")) {
                    editor.putString("JastrząbStatus", "Nie złapano");
                    editor.putBoolean("JastrząbZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jemiołuszka")) {
                    editor.putString("JemiołuszkaStatus", "Nie złapano");
                    editor.putBoolean("JemiołuszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jer")) {
                    editor.putString("JerStatus", "Nie złapano");
                    editor.putBoolean("JerZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jerzyk")) {
                    editor.putString("JerzykStatus", "Nie złapano");
                    editor.putBoolean("JerzykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jerzyk alpejski")) {
                    editor.putString("JerzykAlpejskiStatus", "Nie złapano");
                    editor.putBoolean("JerzykAlpejskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Jerzyk blady")) {
                    editor.putString("JerzykBladyStatus", "Nie złapano");
                    editor.putBoolean("JerzykBladyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Junko")) {
                    editor.putString("JunkoStatus", "Nie złapano");
                    editor.putBoolean("JunkoZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kalandra czarna")) {
                    editor.putString("KalandraCzarnaStatus", "Nie złapano");
                    editor.putBoolean("KalandraCzarnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kalandra szara")) {
                    editor.putString("KalandraSzaraStatus", "Nie złapano");
                    editor.putBoolean("KalandraSzaraZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kamieniuszka")) {
                    editor.putString("KamieniuszkaStatus", "Nie złapano");
                    editor.putBoolean("KamieniuszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kamuszik")) {
                    editor.putString("KamuszikStatus", "Nie złapano");
                    editor.putBoolean("KamuszikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kania czarna")) {
                    editor.putString("KaniaCzarnaStatus", "Nie złapano");
                    editor.putBoolean("KaniaCzarnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kania ruda")) {
                    editor.putString("KaniaRudaStatus", "Nie złapano");
                    editor.putBoolean("KaniaRudaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kapturka")) {
                    editor.putString("KapturkaStatus", "Nie złapano");
                    editor.putBoolean("KapturkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Karliczka")) {
                    editor.putString("KarliczkaStatus", "Nie złapano");
                    editor.putBoolean("KarliczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kawka")) {
                    editor.putString("KawkaStatus", "Nie złapano");
                    editor.putBoolean("KawkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kazarka rdzawa")) {
                    editor.putString("KazarkaRdzawaStatus", "Nie złapano");
                    editor.putBoolean("KazarkaRdzawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kląskawka")) {
                    editor.putString("KląskawkaStatus", "Nie złapano");
                    editor.putBoolean("KląskawkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kobczyk")) {
                    editor.putString("KobczykStatus", "Nie złapano");
                    editor.putBoolean("KobczykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kobuz")) {
                    editor.putString("KobuzStatus", "Nie złapano");
                    editor.putBoolean("KobuzZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kokoszka")) {
                    editor.putString("KokoszkaStatus", "Nie złapano");
                    editor.putBoolean("KokoszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kopciuszek")) {
                    editor.putString("KopciuszekStatus", "Nie złapano");
                    editor.putBoolean("KopciuszekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kormoran")) {
                    editor.putString("KormoranStatus", "Nie złapano");
                    editor.putBoolean("KormoranZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kormoran czubaty")) {
                    editor.putString("KormoranCzubatyStatus", "Nie złapano");
                    editor.putBoolean("KormoranCzubatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kormoran mały")) {
                    editor.putString("KormoranMałyStatus", "Nie złapano");
                    editor.putBoolean("KormoranMałyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kos")) {
                    editor.putString("KosStatus", "Nie złapano");
                    editor.putBoolean("KosZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kowalik")) {
                    editor.putString("KowalikStatus", "Nie złapano");
                    editor.putBoolean("KowalikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krakwa")) {
                    editor.putString("KrakwaStatus", "Nie złapano");
                    editor.putBoolean("KrakwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kraska")) {
                    editor.putString("KraskaStatus", "Nie złapano");
                    editor.putBoolean("KraskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krętogłów")) {
                    editor.putString("KrętogłówStatus", "Nie złapano");
                    editor.putBoolean("KrętogłówZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krogulec")) {
                    editor.putString("KrogulecStatus", "Nie złapano");
                    editor.putBoolean("KrogulecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krogulec krótkonogi")) {
                    editor.putString("KrogulecKrótkonogiStatus", "Nie złapano");
                    editor.putBoolean("KrogulecKrótkonogiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kropiatka")) {
                    editor.putString("KropiatkaStatus", "Nie złapano");
                    editor.putBoolean("KropiatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kruk")) {
                    editor.putString("KrukStatus", "Nie złapano");
                    editor.putBoolean("KrukZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krwawodziób")) {
                    editor.putString("KrwawodzióbStatus", "Nie złapano");
                    editor.putBoolean("KrwawodzióbZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krzyżodziób modrzewiowy")) {
                    editor.putString("KrzyżodzióbModrzewiowyStatus", "Nie złapano");
                    editor.putBoolean("KrzyżodzióbModrzewiowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krzyżodziób sosnowy")) {
                    editor.putString("KrzyżodzióbSosnowyStatus", "Nie złapano");
                    editor.putBoolean("KrzyżodzióbSosnowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krzyżodziób świerkowy")) {
                    editor.putString("KrzyżodzióbŚwierkowyStatus", "Nie złapano");
                    editor.putBoolean("KrzyżodzióbŚwierkowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Krzyżówka")) {
                    editor.putString("KrzyżówkaStatus", "Nie złapano");
                    editor.putBoolean("KrzyżówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kszyk")) {
                    editor.putString("KszykStatus", "Nie złapano");
                    editor.putBoolean("KszykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kukułka")) {
                    editor.putString("KukułkaStatus", "Nie złapano");
                    editor.putBoolean("KukułkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kulczyk")) {
                    editor.putString("KulczykStatus", "Nie złapano");
                    editor.putBoolean("KulczykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kulik cienkodzioby")) {
                    editor.putString("KulikCienkodziobyStatus", "Nie złapano");
                    editor.putBoolean("KulikCienkodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kulik mniejszy")) {
                    editor.putString("KulikMniejszyStatus", "Nie złapano");
                    editor.putBoolean("KulikMniejszyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kulik wielki")) {
                    editor.putString("KulikWielkiStatus", "Nie złapano");
                    editor.putBoolean("KulikWielkiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kulon")) {
                    editor.putString("KulonStatus", "Nie złapano");
                    editor.putBoolean("KulonZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kurhannik")) {
                    editor.putString("KurhannikStatus", "Nie złapano");
                    editor.putBoolean("KurhannikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kuropatwa")) {
                    editor.putString("KuropatwaStatus", "Nie złapano");
                    editor.putBoolean("KuropatwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kwiczoł")) {
                    editor.putString("KwiczołStatus", "Nie złapano");
                    editor.putBoolean("KwiczołZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Kwokacz")) {
                    editor.putString("KwokaczStatus", "Nie złapano");
                    editor.putBoolean("KwokaczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Lelek")) {
                    editor.putString("LelekStatus", "Nie złapano");
                    editor.putBoolean("LelekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Lerka")) {
                    editor.putString("LerkaStatus", "Nie złapano");
                    editor.putBoolean("LerkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Lodowiec")) {
                    editor.putString("LodowiecStatus", "Nie złapano");
                    editor.putBoolean("LodowiecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Lodówka")) {
                    editor.putString("LodówkaStatus", "Nie złapano");
                    editor.putBoolean("LodówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łabędź czarnodzioby")) {
                    editor.putString("ŁabędźCzarnodziobyStatus", "Nie złapano");
                    editor.putBoolean("ŁabędźCzarnodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łabędź krzykliwy")) {
                    editor.putString("ŁabędźKrzykliwyStatus", "Nie złapano");
                    editor.putBoolean("ŁabędźKrzykliwyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łabędź niemy")) {
                    editor.putString("ŁabędźNiemyStatus", "Nie złapano");
                    editor.putBoolean("ŁabędźNiemyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łęczak")) {
                    editor.putString("ŁęczakStatus", "Nie złapano");
                    editor.putBoolean("ŁęczakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łozówka")) {
                    editor.putString("ŁozówkaStatus", "Nie złapano");
                    editor.putBoolean("ŁozówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łuskowiec")) {
                    editor.putString("ŁuskowiecStatus", "Nie złapano");
                    editor.putBoolean("ŁuskowiecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Łyska")) {
                    editor.putString("ŁyskaStatus", "Nie złapano");
                    editor.putBoolean("ŁyskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Makolągwa")) {
                    editor.putString("MakolągwaStatus", "Nie złapano");
                    editor.putBoolean("MakolągwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mandarynka")) {
                    editor.putString("MandarynkaStatus", "Nie złapano");
                    editor.putBoolean("MandarynkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Markaczka")) {
                    editor.putString("MarkaczkaStatus", "Nie złapano");
                    editor.putBoolean("MarkaczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Markaczka amerykańska")) {
                    editor.putString("MarkaczkaAmerykańskaStatus", "Nie złapano");
                    editor.putBoolean("MarkaczkaAmerykańskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Maskonur")) {
                    editor.putString("MaskonurStatus", "Nie złapano");
                    editor.putBoolean("MaskonurZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mazurek")) {
                    editor.putString("MazurekStatus", "Nie złapano");
                    editor.putBoolean("MazurekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa białogłowa")) {
                    editor.putString("MewaBiałogłowaStatus", "Nie złapano");
                    editor.putBoolean("MewaBiałogłowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa blada")) {
                    editor.putString("MewaBladaStatus", "Nie złapano");
                    editor.putBoolean("MewaBladaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa cienkodzioba")) {
                    editor.putString("MewaCienkodziobaStatus", "Nie złapano");
                    editor.putBoolean("MewaCienkodziobaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa czarnogłowa")) {
                    editor.putString("MewaCzarnogłowaStatus", "Nie złapano");
                    editor.putBoolean("MewaCzarnogłowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa delawarska")) {
                    editor.putString("MewaDelawarskaStatus", "Nie złapano");
                    editor.putBoolean("MewaDelawarskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa karaibska")) {
                    editor.putString("MewaKaraibskaStatus", "Nie złapano");
                    editor.putBoolean("MewaKaraibskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa mała")) {
                    editor.putString("MewaMałaStatus", "Nie złapano");
                    editor.putBoolean("MewaMałaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa modrodzioba")) {
                    editor.putString("MewaModrodziobaStatus", "Nie złapano");
                    editor.putBoolean("MewaModrodziobaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa obrożna")) {
                    editor.putString("MewaObrożnaStatus", "Nie złapano");
                    editor.putBoolean("MewaObrożnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa polarna")) {
                    editor.putString("MewaPolarnaStatus", "Nie złapano");
                    editor.putBoolean("MewaPolarnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa romańska")) {
                    editor.putString("MewaRomańskaStatus", "Nie złapano");
                    editor.putBoolean("MewaRomańskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa siodłata")) {
                    editor.putString("MewaSiodłataStatus", "Nie złapano");
                    editor.putBoolean("MewaSiodłataZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa siwa")) {
                    editor.putString("MewaSiwaStatus", "Nie złapano");
                    editor.putBoolean("MewaSiwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa srebrzysta")) {
                    editor.putString("MewaSrebrzystaStatus", "Nie złapano");
                    editor.putBoolean("MewaSrebrzystaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa trójpalczasta")) {
                    editor.putString("MewaTrójpalczastaStatus", "Nie złapano");
                    editor.putBoolean("MewaTrójpalczastaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mewa żółtonoga")) {
                    editor.putString("MewaŻółtonogaStatus", "Nie złapano");
                    editor.putBoolean("MewaŻółtonogaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Modraczek")) {
                    editor.putString("ModraczekStatus", "Nie złapano");
                    editor.putBoolean("ModraczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Modraszka")) {
                    editor.putString("ModraszkaStatus", "Nie złapano");
                    editor.putBoolean("ModraszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mornel")) {
                    editor.putString("MornelStatus", "Nie złapano");
                    editor.putBoolean("MornelZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Muchołówka białoszyja")) {
                    editor.putString("MuchołówkaBiałoszyjaStatus", "Nie złapano");
                    editor.putBoolean("MuchołówkaBiałoszyjaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Muchłówka mała")) {
                    editor.putString("MuchłówkaMałaStatus", "Nie złapano");
                    editor.putBoolean("MuchłówkaMałaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Muchołówka szara")) {
                    editor.putString("MuchołówkaSzaraStatus", "Nie złapano");
                    editor.putBoolean("MuchołówkaSzaraZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Muchołówka żałobna")) {
                    editor.putString("MuchołówkaŻałobnaStatus", "Nie złapano");
                    editor.putBoolean("MuchołówkaŻałobnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Mysikrólik")) {
                    editor.putString("MysikrólikStatus", "Nie złapano");
                    editor.putBoolean("MysikrólikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Myszołów")) {
                    editor.putString("MyszołówStatus", "Nie złapano");
                    editor.putBoolean("MyszołówZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Myszołów włochaty")) {
                    editor.putString("MyszołówWłochatyStatus", "Nie złapano");
                    editor.putBoolean("MyszołówWłochatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nagórnik")) {
                    editor.putString("NagórnikStatus", "Nie złapano");
                    editor.putBoolean("NagórnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nawałnik burzowy")) {
                    editor.putString("NawałnikBurzowyStatus", "Nie złapano");
                    editor.putBoolean("NawałnikBurzowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nawałnik duży")) {
                    editor.putString("NawałnikDużyStatus", "Nie złapano");
                    editor.putBoolean("NawałnikDużyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nur białodzioby")) {
                    editor.putString("NurBiałodziobyStatus", "Nie złapano");
                    editor.putBoolean("NurBiałodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nur czarnoszyi")) {
                    editor.putString("NurCzarnoszyiStatus", "Nie złapano");
                    editor.putBoolean("NurCzarnoszyiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nurnik")) {
                    editor.putString("NurnikStatus", "Nie złapano");
                    editor.putBoolean("NurnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nurogęś")) {
                    editor.putString("NurogęśStatus", "Nie złapano");
                    editor.putBoolean("NurogęśZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nur rdzawoszyi")) {
                    editor.putString("NurRdzawoszyiStatus", "Nie złapano");
                    editor.putBoolean("NurRdzawoszyiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nurzyk")) {
                    editor.putString("NurzykStatus", "Nie złapano");
                    editor.putBoolean("NurzykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Nurzyk polarny")) {
                    editor.putString("NurzykPolarnyStatus", "Nie złapano");
                    editor.putBoolean("NurzykPolarnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Oceannik żółtopłetwy")) {
                    editor.putString("OceannikŻółtopłetwyStatus", "Nie złapano");
                    editor.putBoolean("OceannikŻółtopłetwyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ogorzałka")) {
                    editor.putString("OgorzałkaStatus", "Nie złapano");
                    editor.putBoolean("OgorzałkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ogorzałka mała")) {
                    editor.putString("OgorzałkaMałaStatus", "Nie złapano");
                    editor.putBoolean("OgorzałkaMałaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ohar")) {
                    editor.putString("OharStatus", "Nie złapano");
                    editor.putBoolean("OharZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Oknówka")) {
                    editor.putString("OknówkaStatus", "Nie złapano");
                    editor.putBoolean("OknówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orlica")) {
                    editor.putString("OrlicaStatus", "Nie złapano");
                    editor.putBoolean("OrlicaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orlik grubodzioby")) {
                    editor.putString("OrlikGrubodziobyStatus", "Nie złapano");
                    editor.putBoolean("OrlikGrubodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orlik krzykliwy")) {
                    editor.putString("OrlikKrzykliwyStatus", "Nie złapano");
                    editor.putBoolean("OrlikKrzykliwyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orłosęp")) {
                    editor.putString("OrłosępStatus", "Nie złapano");
                    editor.putBoolean("OrłosępZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ortolan")) {
                    editor.putString("OrtolanStatus", "Nie złapano");
                    editor.putBoolean("OrtolanZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orzechówka")) {
                    editor.putString("OrzechówkaStatus", "Nie złapano");
                    editor.putBoolean("OrzechówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orzeł cesarski")) {
                    editor.putString("OrzełCesarskiStatus", "Nie złapano");
                    editor.putBoolean("OrzełCesarskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orzełek")) {
                    editor.putString("OrzełekStatus", "Nie złapano");
                    editor.putBoolean("OrzełekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orzeł przedni")) {
                    editor.putString("OrzełPrzedniStatus", "Nie złapano");
                    editor.putBoolean("OrzełPrzedniZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Orzeł stepowy")) {
                    editor.putString("OrzełStepowyStatus", "Nie złapano");
                    editor.putBoolean("OrzełStepowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Osetnik")) {
                    editor.putString("OsetnikStatus", "Nie złapano");
                    editor.putBoolean("OsetnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ostrygojad")) {
                    editor.putString("OstrygojadStatus", "Nie złapano");
                    editor.putBoolean("OstrygojadZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pardwa mszarna")) {
                    editor.putString("PardwaMszarnaStatus", "Nie złapano");
                    editor.putBoolean("PardwaMszarnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pasterz")) {
                    editor.putString("PasterzStatus", "Nie złapano");
                    editor.putBoolean("PasterzZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Paszkot")) {
                    editor.putString("PaszkotStatus", "Nie złapano");
                    editor.putBoolean("PaszkotZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pelikan kędzierzawy")) {
                    editor.putString("PelikanKędzierzawyStatus", "Nie złapano");
                    editor.putBoolean("PelikanKędzierzawyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pelikan różowy")) {
                    editor.putString("Pelikan różowyStatus", "Nie złapano");
                    editor.putBoolean("Pelikan różowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pełzacz leśny")) {
                    editor.putString("PełzaczLeśnyStatus", "Nie złapano");
                    editor.putBoolean("PełzaczLeśnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pełzacz ogrodowy")) {
                    editor.putString("PełzaczOgrodowyStatus", "Nie złapano");
                    editor.putBoolean("PełzaczOgrodowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Perkoz dwuczuby")) {
                    editor.putString("PerkozDwuczubyStatus", "Nie złapano");
                    editor.putBoolean("PerkozDwuczubyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Perkozek")) {
                    editor.putString("PerkozekStatus", "Nie złapano");
                    editor.putBoolean("PerkozekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Perkoz grubodzioby")) {
                    editor.putString("PerkozGrubodziobyStatus", "Nie złapano");
                    editor.putBoolean("PerkozGrubodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Perkoz rdzawoszyi")) {
                    editor.putString("PerkozRdzawoszyiStatus", "Nie złapano");
                    editor.putBoolean("PerkozRdzawoszyiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Perkoz rogaty")) {
                    editor.putString("PerkozRogatyStatus", "Nie złapano");
                    editor.putBoolean("PerkozRogatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Piaskowiec")) {
                    editor.putString("PiaskowiecStatus", "Nie złapano");
                    editor.putBoolean("PiaskowiecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Piecuszek")) {
                    editor.putString("PiecuszekStatus", "Nie złapano");
                    editor.putBoolean("PiecuszekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Piegża")) {
                    editor.putString("PiegżaStatus", "Nie złapano");
                    editor.putBoolean("PiegżaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pierwiosnek")) {
                    editor.putString("PierwiosnekStatus", "Nie złapano");
                    editor.putBoolean("PierwiosnekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pleszka")) {
                    editor.putString("PleszkaStatus", "Nie złapano");
                    editor.putBoolean("PleszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pliszka górska")) {
                    editor.putString("PliszkaGórskaStatus", "Nie złapano");
                    editor.putBoolean("PliszkaGórskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pliszka siwa")) {
                    editor.putString("PliszkaSiwaStatus", "Nie złapano");
                    editor.putBoolean("PliszkaSiwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pliszka żółta")) {
                    editor.putString("PliszkaŻółtaStatus", "Nie złapano");
                    editor.putBoolean("PliszkaŻółtaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pluszcz")) {
                    editor.putString("PluszczStatus", "Nie złapano");
                    editor.putBoolean("PluszczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Płaskonos")) {
                    editor.putString("PłaskonosStatus", "Nie złapano");
                    editor.putBoolean("PłaskonosZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Płatkonóg płaskodzioby")) {
                    editor.putString("PłatkonógPłaskodziobyStatus", "Nie złapano");
                    editor.putBoolean("PłatkonógPłaskodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Płatkonóg szydłodzioby")) {
                    editor.putString("PłatkonógSzydłodziobyStatus", "Nie złapano");
                    editor.putBoolean("PłatkonógSzydłodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Płochacz halny")) {
                    editor.putString("PłochaczHalnyStatus", "Nie złapano");
                    editor.putBoolean("PłochaczHalnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Płochacz syberyjski")) {
                    editor.putString("PłochaczSyberyjskiStatus", "Nie złapano");
                    editor.putBoolean("PłochaczSyberyjskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Płomykówka")) {
                    editor.putString("PłomykówkaStatus", "Nie złapano");
                    editor.putBoolean("PłomykówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Podgorzałka")) {
                    editor.putString("PodgorzałkaStatus", "Nie złapano");
                    editor.putBoolean("PodgorzałkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Podróżniczek")) {
                    editor.putString("PodróżniczekStatus", "Nie złapano");
                    editor.putBoolean("PodróżniczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pójdźka")) {
                    editor.putString("PójdźkaStatus", "Nie złapano");
                    editor.putBoolean("PójdźkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pokląskwa")) {
                    editor.putString("PokląskwaStatus", "Nie złapano");
                    editor.putBoolean("PokląskwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pokrzewka aksamitna")) {
                    editor.putString("PokrzewkaAksamitnaStatus", "Nie złapano");
                    editor.putBoolean("PokrzewkaAksamitnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pokrzewka wąsata")) {
                    editor.putString("PokrzewkaWąsataStatus", "Nie złapano");
                    editor.putBoolean("PokrzewkaWąsataZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pokrzywnica")) {
                    editor.putString("PokrzywnicaStatus", "Nie złapano");
                    editor.putBoolean("PokrzywnicaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pomurnik")) {
                    editor.putString("PomurnikStatus", "Nie złapano");
                    editor.putBoolean("PomurnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Poświerka")) {
                    editor.putString("PoświerkaStatus", "Nie złapano");
                    editor.putBoolean("PoświerkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Potrzeszcz")) {
                    editor.putString("PotrzeszczStatus", "Nie złapano");
                    editor.putBoolean("PotrzeszczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Potrzos")) {
                    editor.putString("PotrzosStatus", "Nie złapano");
                    editor.putBoolean("PotrzosZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Przepiórka")) {
                    editor.putString("PrzepiórkaStatus", "Nie złapano");
                    editor.putBoolean("PrzepiórkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Puchacz")) {
                    editor.putString("PuchaczStatus", "Nie złapano");
                    editor.putBoolean("PuchaczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pustułeczka")) {
                    editor.putString("PustułeczkaStatus", "Nie złapano");
                    editor.putBoolean("PustułeczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pustułka")) {
                    editor.putString("PustułkaStatus", "Nie złapano");
                    editor.putBoolean("PustułkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Pustynnik")) {
                    editor.putString("PustynnikStatus", "Nie złapano");
                    editor.putBoolean("PustynnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Puszczyk")) {
                    editor.putString("PuszczykStatus", "Nie złapano");
                    editor.putBoolean("PuszczykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Puszczyk mszarny")) {
                    editor.putString("PuszczykMszarnyStatus", "Nie złapano");
                    editor.putBoolean("PuszczykMszarnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Puszczyk uralski")) {
                    editor.putString("PuszczykUralskiStatus", "Nie złapano");
                    editor.putBoolean("PuszczykUralskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Raniuszek")) {
                    editor.putString("RaniuszekStatus", "Nie złapano");
                    editor.putBoolean("RaniuszekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Raróg")) {
                    editor.putString("RarógStatus", "Nie złapano");
                    editor.putBoolean("RarógZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Remiz")) {
                    editor.putString("RemizStatus", "Nie złapano");
                    editor.putBoolean("RemizZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rokitniczka")) {
                    editor.putString("RokitniczkaStatus", "Nie złapano");
                    editor.putBoolean("RokitniczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rożeniec")) {
                    editor.putString("RożeniecStatus", "Nie złapano");
                    editor.putBoolean("RożeniecZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rudzik")) {
                    editor.putString("RudzikStatus", "Nie złapano");
                    editor.putBoolean("RudzikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybaczek srokaty")) {
                    editor.putString("RybaczekSrokatyStatus", "Nie złapano");
                    editor.putBoolean("RybaczekSrokatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa białoczelna")) {
                    editor.putString("RybitwaBiałoczelnaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaBiałoczelnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa białoskrzydła")) {
                    editor.putString("RybitwaBiałoskrzydłaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaBiałoskrzydłaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa białowąsa")) {
                    editor.putString("RybitwaBiałowąsaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaBiałowąsaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa czarna")) {
                    editor.putString("RybitwaCzarnaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaCzarnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa czubata")) {
                    editor.putString("RybitwaCzubataStatus", "Nie złapano");
                    editor.putBoolean("RybitwaCzubataZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa krótkodzioba")) {
                    editor.putString("RybitwaKrótkodziobaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaKrótkodziobaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa popielata")) {
                    editor.putString("RybitwaPopielataStatus", "Nie złapano");
                    editor.putBoolean("RybitwaPopielataZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa różowa")) {
                    editor.putString("RybitwaRóżowaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaRóżowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa rzeczna")) {
                    editor.putString("RybitwaRzecznaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaRzecznaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybitwa wielkodzioba")) {
                    editor.putString("RybitwaWielkodziobaStatus", "Nie złapano");
                    editor.putBoolean("RybitwaWielkodziobaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rybołów")) {
                    editor.putString("RybołówStatus", "Nie złapano");
                    editor.putBoolean("RybołówZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rycyk")) {
                    editor.putString("RycykStatus", "Nie złapano");
                    editor.putBoolean("RycykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Rzepołuch")) {
                    editor.putString("RzepołuchStatus", "Nie złapano");
                    editor.putBoolean("RzepołuchZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Samotnik")) {
                    editor.putString("SamotnikStatus", "Nie złapano");
                    editor.putBoolean("SamotnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sęp kasztanowaty")) {
                    editor.putString("SępKasztanowatyStatus", "Nie złapano");
                    editor.putBoolean("SępKasztanowatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sęp płowy")) {
                    editor.putString("SępPłowyStatus", "Nie złapano");
                    editor.putBoolean("SępPłowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sierpówka")) {
                    editor.putString("SierpówkaStatus", "Nie złapano");
                    editor.putBoolean("SierpówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sieweczka mongolska")) {
                    editor.putString("SieweczkaMongolskaStatus", "Nie złapano");
                    editor.putBoolean("SieweczkaMongolskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sieweczka morska")) {
                    editor.putString("SieweczkaMorskaStatus", "Nie złapano");
                    editor.putBoolean("SieweczkaMorskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sieweczka obrożna")) {
                    editor.putString("SieweczkaObrożnaStatus", "Nie złapano");
                    editor.putBoolean("SieweczkaObrożnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sieweczka pustynna")) {
                    editor.putString("SieweczkaPustynnaStatus", "Nie złapano");
                    editor.putBoolean("SieweczkaPustynnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sieweczka rzeczna")) {
                    editor.putString("SieweczkaRzecznaStatus", "Nie złapano");
                    editor.putBoolean("SieweczkaRzecznaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Siewka szara")) {
                    editor.putString("SiewkaSzaraStatus", "Nie złapano");
                    editor.putBoolean("SiewkaSzaraZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Siewka złota")) {
                    editor.putString("SiewkaZłotaStatus", "Nie złapano");
                    editor.putBoolean("SiewkaZłotaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Siewka złotawa")) {
                    editor.putString("SiewkaZłotawaStatus", "Nie złapano");
                    editor.putBoolean("SiewkaZłotawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Siewnica")) {
                    editor.putString("SiewnicaStatus", "Nie złapano");
                    editor.putBoolean("SiewnicaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sikora lazurowa")) {
                    editor.putString("SikoraLazurowaStatus", "Nie złapano");
                    editor.putBoolean("SikoraLazurowaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sikora uboga")) {
                    editor.putString("SikoraUbogaStatus", "Nie złapano");
                    editor.putBoolean("SikoraUbogaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Siniak")) {
                    editor.putString("SiniakStatus", "Nie złapano");
                    editor.putBoolean("SiniakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Siwerniak")) {
                    editor.putString("SiwerniakStatus", "Nie złapano");
                    editor.putBoolean("SiwerniakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Skowrończyk krótkopalcowy")) {
                    editor.putString("SkowrończykKrótkopalcowyStatus", "Nie złapano");
                    editor.putBoolean("SkowrończykKrótkopalcowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Skowronek")) {
                    editor.putString("SkowronekStatus", "Nie złapano");
                    editor.putBoolean("SkowronekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Skowronek białoskrzydły")) {
                    editor.putString("SkowronekBiałoskrzydłyStatus", "Nie złapano");
                    editor.putBoolean("SkowronekBiałoskrzydłyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Słonka")) {
                    editor.putString("SłonkaStatus", "Nie złapano");
                    editor.putBoolean("SłonkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Słowik rdzawy")) {
                    editor.putString("SłowikRdzawyStatus", "Nie złapano");
                    editor.putBoolean("SłowikRdzawyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Słowik syberyjski")) {
                    editor.putString("SłowikSyberyjskiStatus", "Nie złapano");
                    editor.putBoolean("SłowikSyberyjskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Słowik szary")) {
                    editor.putString("SłowikSzaryStatus", "Nie złapano");
                    editor.putBoolean("SłowikSzaryZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sokół skalny")) {
                    editor.putString("SokółSkalnyStatus", "Nie złapano");
                    editor.putBoolean("SokółSkalnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sokół wędrowny")) {
                    editor.putString("SokółWędrownyStatus", "Nie złapano");
                    editor.putBoolean("SokółWędrownyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sosnówka")) {
                    editor.putString("SosnówkaStatus", "Nie złapano");
                    editor.putBoolean("SosnówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sowa jarzębata")) {
                    editor.putString("SowaJarzębataStatus", "Nie złapano");
                    editor.putBoolean("SowaJarzębataZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sowa śnieżna")) {
                    editor.putString("SowaŚnieżnaStatus", "Nie złapano");
                    editor.putBoolean("SowaŚnieżnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sójka")) {
                    editor.putString("SójkaStatus", "Nie złapano");
                    editor.putBoolean("SójkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sójka syberyjska")) {
                    editor.putString("SójkaSyberyjskaStatus", "Nie złapano");
                    editor.putBoolean("SójkaSyberyjskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sóweczka")) {
                    editor.putString("SóweczkaStatus", "Nie złapano");
                    editor.putBoolean("SóweczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sroka")) {
                    editor.putString("SrokaStatus", "Nie złapano");
                    editor.putBoolean("SrokaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Srokosz")) {
                    editor.putString("SrokoszStatus", "Nie złapano");
                    editor.putBoolean("SrokoszZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sterniczka")) {
                    editor.putString("SterniczkaStatus", "Nie złapano");
                    editor.putBoolean("SterniczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Sterniczka jamajska")) {
                    editor.putString("SterniczkaJamajskaStatus", "Nie złapano");
                    editor.putBoolean("SterniczkaJamajskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Strepet")) {
                    editor.putString("StrepetStatus", "Nie złapano");
                    editor.putBoolean("StrepetZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Strumieniówka")) {
                    editor.putString("StrumieniówkaStatus", "Nie złapano");
                    editor.putBoolean("StrumieniówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Strzyżyk")) {
                    editor.putString("StrzyżykStatus", "Nie złapano");
                    editor.putBoolean("StrzyżykZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Syczek")) {
                    editor.putString("SyczekStatus", "Nie złapano");
                    editor.putBoolean("SyczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szablodziób")) {
                    editor.putString("SzablodzióbStatus", "Nie złapano");
                    editor.putBoolean("SzablodzióbZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szczudłak")) {
                    editor.putString("SzczudłakStatus", "Nie złapano");
                    editor.putBoolean("SzczudłakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szczygieł")) {
                    editor.putString("SzczygiełStatus", "Nie złapano");
                    editor.putBoolean("SzczygiełZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szlachar")) {
                    editor.putString("SzlacharStatus", "Nie złapano");
                    editor.putBoolean("SzlacharZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szlamiec długodzioby")) {
                    editor.putString("SzlamiecDługodziobyStatus", "Nie złapano");
                    editor.putBoolean("SzlamiecDługodziobyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szlamnik")) {
                    editor.putString("SzlamnikStatus", "Nie złapano");
                    editor.putBoolean("SzlamnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Szpak")) {
                    editor.putString("SzpakStatus", "Nie złapano");
                    editor.putBoolean("SzpakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ścierwnik")) {
                    editor.putString("ŚcierwnikStatus", "Nie złapano");
                    editor.putBoolean("ŚcierwnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Ślepowron")) {
                    editor.putString("ŚlepowronStatus", "Nie złapano");
                    editor.putBoolean("ŚlepowronZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Śmieszka")) {
                    editor.putString("ŚmieszkaStatus", "Nie złapano");
                    editor.putBoolean("ŚmieszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Śnieguła")) {
                    editor.putString("ŚniegułaStatus", "Nie złapano");
                    editor.putBoolean("ŚniegułaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Śnieżka")) {
                    editor.putString("ŚnieżkaStatus", "Nie złapano");
                    editor.putBoolean("ŚnieżkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Śpiewak")) {
                    editor.putString("ŚpiewakStatus", "Nie złapano");
                    editor.putBoolean("ŚpiewakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek drzewny")) {
                    editor.putString("ŚwiergotekDrzewnyStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekDrzewnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek łąkowy")) {
                    editor.putString("ŚwiergotekŁąkowyStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekŁąkowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek nadmorski")) {
                    editor.putString("ŚwiergotekNadmorskiStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekNadmorskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek polny")) {
                    editor.putString("ŚwiergotekPolnyStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekPolnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek rdzawogardły")) {
                    editor.putString("ŚwiergotekRdzawogardłyStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekRdzawogardłyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek szponiasty")) {
                    editor.putString("ŚwiergotekSzponiastyStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekSzponiastyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świergotek tajgowy")) {
                    editor.putString("ŚwiergotekTajgowyStatus", "Nie złapano");
                    editor.putBoolean("ŚwiergotekTajgowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świerszczak")) {
                    editor.putString("ŚwierszczakStatus", "Nie złapano");
                    editor.putBoolean("ŚwierszczakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świerszczak melodyjny")) {
                    editor.putString("ŚwierszczakMelodyjnyStatus", "Nie złapano");
                    editor.putBoolean("ŚwierszczakMelodyjnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstun")) {
                    editor.putString("ŚwistunStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstun amerykański")) {
                    editor.putString("ŚwistunAmerykańskiStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunAmerykańskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka ałtańska")) {
                    editor.putString("Świstunka ałtańskaStatus", "Nie złapano");
                    editor.putBoolean("Świstunka ałtańskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka brunatna")) {
                    editor.putString("ŚwistunkaBrunatnaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaBrunatnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka górska")) {
                    editor.putString("ŚwistunkaGórskaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaGórskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka grubodzioba")) {
                    editor.putString("ŚwistunkaGrubodziobaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaGrubodziobaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka iberyjska")) {
                    editor.putString("ŚwistunkaIberyjskaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaIberyjskaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka leśna")) {
                    editor.putString("ŚwistunkaLeśnaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaLeśnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka północna")) {
                    editor.putString("ŚwistunkaPółnocnaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaPółnocnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka złotawa")) {
                    editor.putString("ŚwistunkaZłotawaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaZłotawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Świstunka żółtawa")) {
                    editor.putString("ŚwistunkaŻółtawaStatus", "Nie złapano");
                    editor.putBoolean("ŚwistunkaŻółtawaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Tamaryszka")) {
                    editor.putString("TamaryszkaStatus", "Nie złapano");
                    editor.putBoolean("TamaryszkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Terekia")) {
                    editor.putString("TerekiaStatus", "Nie złapano");
                    editor.putBoolean("TerekiaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trzciniak")) {
                    editor.putString("TrzciniakStatus", "Nie złapano");
                    editor.putBoolean("TrzciniakZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trzcinniczek")) {
                    editor.putString("TrzcinniczekStatus", "Nie złapano");
                    editor.putBoolean("TrzcinniczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trzcinniczek kaspijski")) {
                    editor.putString("TrzcinniczekKaspijskiStatus", "Nie złapano");
                    editor.putBoolean("TrzcinniczekKaspijskiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trzmielojad")) {
                    editor.putString("TrzmielojadStatus", "Nie złapano");
                    editor.putBoolean("TrzmielojadZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadel")) {
                    editor.putString("TrznadelStatus", "Nie złapano");
                    editor.putBoolean("TrznadelZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadel białogłowy")) {
                    editor.putString("TrznadelBiałogłowyStatus", "Nie złapano");
                    editor.putBoolean("TrznadelBiałogłowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadel czarnogłowy")) {
                    editor.putString("TrznadelCzarnogłowyStatus", "Nie złapano");
                    editor.putBoolean("TrznadelCzarnogłowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadel czubaty")) {
                    editor.putString("TrznadelCzubatyStatus", "Nie złapano");
                    editor.putBoolean("TrznadelCzubatyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadelek")) {
                    editor.putString("TrznadelekStatus", "Nie złapano");
                    editor.putBoolean("TrznadelekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadel złotawy")) {
                    editor.putString("TrznadelZłotawyStatus", "Nie złapano");
                    editor.putBoolean("TrznadelZłotawyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Trznadel złotobrewy")) {
                    editor.putString("TrznadelZłotobrewyStatus", "Nie złapano");
                    editor.putBoolean("TrznadelZłotobrewyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Turkan")) {
                    editor.putString("TurkanStatus", "Nie złapano");
                    editor.putBoolean("TurkanZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Turkawka")) {
                    editor.putString("TurkawkaStatus", "Nie złapano");
                    editor.putBoolean("TurkawkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Turkawka wschodnia")) {
                    editor.putString("TurkawkaWschodniaStatus", "Nie złapano");
                    editor.putBoolean("TurkawkaWschodniaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Uhla")) {
                    editor.putString("UhlaStatus", "Nie złapano");
                    editor.putBoolean("UhlaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Uhla garbonosa")) {
                    editor.putString("UhlaGarbonosaStatus", "Nie złapano");
                    editor.putBoolean("UhlaGarbonosaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Uszatka")) {
                    editor.putString("UszatkaStatus", "Nie złapano");
                    editor.putBoolean("UszatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Uszatka błotna")) {
                    editor.putString("UszatkaBłotnaStatus", "Nie złapano");
                    editor.putBoolean("UszatkaBłotnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Warzęcha")) {
                    editor.putString("WarzęchaStatus", "Nie złapano");
                    editor.putBoolean("WarzęchaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wąsatka")) {
                    editor.putString("WąsatkaStatus", "Nie złapano");
                    editor.putBoolean("WąsatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wieszczek")) {
                    editor.putString("WieszczekStatus", "Nie złapano");
                    editor.putBoolean("WieszczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wilga")) {
                    editor.putString("WilgaStatus", "Nie złapano");
                    editor.putBoolean("WilgaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wireonek czerwonooki")) {
                    editor.putString("WireonekCzerwonookiStatus", "Nie złapano");
                    editor.putBoolean("WireonekCzerwonookiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Włochatka")) {
                    editor.putString("WłochatkaStatus", "Nie złapano");
                    editor.putBoolean("WłochatkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wodniczka")) {
                    editor.putString("WodniczkaStatus", "Nie złapano");
                    editor.putBoolean("WodniczkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wodnik")) {
                    editor.putString("WodnikStatus", "Nie złapano");
                    editor.putBoolean("WodnikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wójcik")) {
                    editor.putString("WójcikStatus", "Nie złapano");
                    editor.putBoolean("WójcikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wróbel")) {
                    editor.putString("WróbelStatus", "Nie złapano");
                    editor.putBoolean("WróbelZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wróbel skalny")) {
                    editor.putString("WróbelSkalnyStatus", "Nie złapano");
                    editor.putBoolean("WróbelSkalnyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wrona siwa")) {
                    editor.putString("WronaSiwaStatus", "Nie złapano");
                    editor.putBoolean("WronaSiwaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wydrzyk długosterny")) {
                    editor.putString("WydrzykDługosternyStatus", "Nie złapano");
                    editor.putBoolean("WydrzykDługosternyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wydrzyk ostrosterny")) {
                    editor.putString("WydrzykOstrosternyStatus", "Nie złapano");
                    editor.putBoolean("WydrzykOstrosternyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wydrzyk tęposterny")) {
                    editor.putString("WydrzykTęposternyStatus", "Nie złapano");
                    editor.putBoolean("WydrzykTęposternyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Wydrzyk wielki")) {
                    editor.putString("WydrzykWielkiStatus", "Nie złapano");
                    editor.putBoolean("WydrzykWielkiZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zaganiacz")) {
                    editor.putString("ZaganiaczStatus", "Nie złapano");
                    editor.putBoolean("ZaganiaczZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zaganiacz mały")) {
                    editor.putString("ZaganiaczMałyStatus", "Nie złapano");
                    editor.putBoolean("ZaganiaczMałyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zaganiacz szczebiotliwy")) {
                    editor.putString("ZaganiaczSzczebiotliwyStatus", "Nie złapano");
                    editor.putBoolean("ZaganiaczSzczebiotliwyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zaroślówka")) {
                    editor.putString("ZaroślówkaStatus", "Nie złapano");
                    editor.putBoolean("ZaroślówkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zausznik")) {
                    editor.putString("ZausznikStatus", "Nie złapano");
                    editor.putBoolean("ZausznikZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zięba")) {
                    editor.putString("ZiębaStatus", "Nie złapano");
                    editor.putBoolean("ZiębaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zielonka")) {
                    editor.putString("ZielonkaStatus", "Nie złapano");
                    editor.putBoolean("ZielonkaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zimorodek")) {
                    editor.putString("ZimorodekStatus", "Nie złapano");
                    editor.putBoolean("ZimorodekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Zniczek")) {
                    editor.putString("ZniczekStatus", "Nie złapano");
                    editor.putBoolean("ZniczekZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Żołna")) {
                    editor.putString("ŻołnaStatus", "Nie złapano");
                    editor.putBoolean("ŻołnaZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Żuraw")) {
                    editor.putString("ŻurawStatus", "Nie złapano");
                    editor.putBoolean("ŻurawZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Żuraw stepowy")) {
                    editor.putString("ŻurawStepowyStatus", "Nie złapano");
                    editor.putBoolean("ŻurawStepowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Żwirowiec łąkowy")) {
                    editor.putString("ŻwirowiecŁąkowyStatus", "Nie złapano");
                    editor.putBoolean("ŻwirowiecŁąkowyZłapany", false);
                    editor.putInt("LiczbaZnalezionych", liczba);
                }
                if (sGatunek.equals("Żwirowiec stepowy")) {
                    editor.putString("ŻwirowiecStepowyStatus", "Nie złapano");
                    editor.putBoolean("ŻwirowiecStepowyZłapany", false);
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
                    if (sGatunek.equals("Białozór")){
                        editor.putString("BiałozórDataZłapania", wybranaData);
                        editor.putString("BiałozórMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus arktyczny")){
                        editor.putString("BiegusArktycznyDataZłapania", wybranaData);
                        editor.putString("BiegusArktycznyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus długoskrzydły")){
                        editor.putString("BiegusDługoskrzydłyDataZłapania", wybranaData);
                        editor.putString("BiegusDługoskrzydłyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus karłowaty")){
                        editor.putString("BiegusKarłowatyDataZłapania", wybranaData);
                        editor.putString("BiegusKarłowatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus krzywodzioby")){
                        editor.putString("BiegusKrzywodziobyDataZłapania", wybranaData);
                        editor.putString("BiegusKrzywodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus malutki")){
                        editor.putString("BiegusMalutkiDataZłapania", wybranaData);
                        editor.putString("BiegusMalutkiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus mały")){
                        editor.putString("BiegusMałyDataZłapania", wybranaData);
                        editor.putString("BiegusMałyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus morski")){
                        editor.putString("BiegusMorskiDataZłapania", wybranaData);
                        editor.putString("BiegusMorskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus płaskodzioby")){
                        editor.putString("BiegusPłaskodziobyDataZłapania", wybranaData);
                        editor.putString("BiegusPłaskodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus płowy")){
                        editor.putString("BiegusPłowyDataZłapania", wybranaData);
                        editor.putString("BiegusPłowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus rdzawy")){
                        editor.putString("BiegusRdzawyDataZłapania", wybranaData);
                        editor.putString("BiegusRdzawyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus tundrowy")){
                        editor.putString("BiegusTundrowyDataZłapania", wybranaData);
                        editor.putString("BiegusTundrowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus wielki")){
                        editor.putString("BiegusWielkiDataZłapania", wybranaData);
                        editor.putString("BiegusWielkiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Biegus zmienny")){
                        editor.putString("BiegusZmiennyDataZłapania", wybranaData);
                        editor.putString("BiegusZmiennyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bielaczek")){
                        editor.putString("BielaczekDataZłapania", wybranaData);
                        editor.putString("BielaczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bielik")){
                        editor.putString("BielikDataZłapania", wybranaData);
                        editor.putString("BielikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bielik wschodni")){
                        editor.putString("BielikWschodniDataZłapania", wybranaData);
                        editor.putString("BielikWschodniMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Birginiak")){
                        editor.putString("BirginiakDataZłapania", wybranaData);
                        editor.putString("BirginiakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Błotniak łąkowy")){
                        editor.putString("BłotniakŁąkowyDataZłapania", wybranaData);
                        editor.putString("BłotniakŁąkowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Błotniak stawowy")){
                        editor.putString("BłotniakStawowyDataZłapania", wybranaData);
                        editor.putString("BłotniakStawowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Błotniak stepowy")){
                        editor.putString("BłotniakStepowyDataZłapania", wybranaData);
                        editor.putString("BłotniakStepowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Błotniak zbożowy")){
                        editor.putString("BłotniakZbożowyDataZłapania", wybranaData);
                        editor.putString("BłotniakZbożowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bocian biały")){
                        editor.putString("BocianBiałyDataZłapania", wybranaData);
                        editor.putString("BocianBiałyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bocian czarny")){
                        editor.putString("BocianCzarnyDataZłapania", wybranaData);
                        editor.putString("BocianCzarnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Bogatka")){
                        editor.putString("BogatkaDataZłapania", wybranaData);
                        editor.putString("BogatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brodziec piegowaty")){
                        editor.putString("BrodziecPiegowatyDataZłapania", wybranaData);
                        editor.putString("BrodziecPiegowatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brodziec piskliwy")){
                        editor.putString("BrodziecPiskliwyDataZłapania", wybranaData);
                        editor.putString("BrodziecPiskliwyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brodziec plamisty")){
                        editor.putString("BrodziecPlamistyDataZłapania", wybranaData);
                        editor.putString("BrodziecPlamistyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brodziec pławny")){
                        editor.putString("BrodziecPławnyDataZłapania", wybranaData);
                        editor.putString("BrodziecPławnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brodziec śniady")){
                        editor.putString("BrodziecŚniadyDataZłapania", wybranaData);
                        editor.putString("BrodziecŚniadyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brodziec żółtonogi")){
                        editor.putString("BrodziecŻółtonogiDataZłapania", wybranaData);
                        editor.putString("BrodziecŻółtonogiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brzęczka")){
                        editor.putString("BrzęczkaDataZłapania", wybranaData);
                        editor.putString("BrzęczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Brzegówka")){
                        editor.putString("BrzegówkaDataZłapania", wybranaData);
                        editor.putString("BrzegówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Burzyk balearski")){
                        editor.putString("BurzykBalearskiDataZłapania", wybranaData);
                        editor.putString("BurzykBalearskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Burzyk północny")){
                        editor.putString("BurzykPółnocnyDataZłapania", wybranaData);
                        editor.putString("BurzykPółnocnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Burzyk szary")){
                        editor.putString("BurzykSzaryDataZłapania", wybranaData);
                        editor.putString("BurzykSzaryMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Burzyk żółtodzioby")){
                        editor.putString("BurzykŻółtodziobyDataZłapania", wybranaData);
                        editor.putString("BurzykŻółtodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cierlik")){
                        editor.putString("CierlikDataZłapania", wybranaData);
                        editor.putString("CierlikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cierniówka")){
                        editor.putString("CierniówkaDataZłapania", wybranaData);
                        editor.putString("CierniówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cietrzew")){
                        editor.putString("CietrzewDataZłapania", wybranaData);
                        editor.putString("CietrzewMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cyraneczka")){
                        editor.putString("CyraneczkaDataZłapania", wybranaData);
                        editor.putString("CyraneczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cyraneczka karolińska")){
                        editor.putString("Cyraneczka karolińskaDataZłapania", wybranaData);
                        editor.putString("Cyraneczka karolińskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cyranka")){
                        editor.putString("CyrankaDataZłapania", wybranaData);
                        editor.putString("CyrankaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Cyranka modroskrzydła")){
                        editor.putString("CyrankaModroskrzydłaDataZłapania", wybranaData);
                        editor.putString("CyrankaModroskrzydłaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czajka")){
                        editor.putString("CzajkaDataZłapania", wybranaData);
                        editor.putString("CzajkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czajka stepowa")){
                        editor.putString("CzajkaStepowaDataZłapania", wybranaData);
                        editor.putString("CzajkaStepowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czajka towarzyska")){
                        editor.putString("CzajkaTowarzyskaDataZłapania", wybranaData);
                        editor.putString("CzajkaTowarzyskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czapla biała")){
                        editor.putString("Czapla białaDataZłapania", wybranaData);
                        editor.putString("Czapla białaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czapla modronosa")){
                        editor.putString("CzaplaModronosaDataZłapania", wybranaData);
                        editor.putString("CzaplaModronosaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czapla nadobna")){
                        editor.putString("CzaplaNadobnaDataZłapania", wybranaData);
                        editor.putString("CzaplaNadobnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czapla purpurowa")){
                        editor.putString("CzaplaPurpurowaDataZłapania", wybranaData);
                        editor.putString("CzaplaPurpurowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czapla siwa")){
                        editor.putString("CzaplaSiwaDataZłapania", wybranaData);
                        editor.putString("CzaplaSiwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czapla złotawa")){
                        editor.putString("CzaplaZłotawaDataZłapania", wybranaData);
                        editor.putString("CzaplaZłotawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czarnogłówka")){
                        editor.putString("CzarnogłówkaDataZłapania", wybranaData);
                        editor.putString("CzarnogłówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czarnowron")){
                        editor.putString("CzarnowronDataZłapania", wybranaData);
                        editor.putString("CzarnowronMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czeczotka")){
                        editor.putString("CzeczotkaDataZłapania", wybranaData);
                        editor.putString("CzeczotkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czeczotka tundrowa")){
                        editor.putString("CzeczotkaTundrowaDataZłapania", wybranaData);
                        editor.putString("CzeczotkaTundrowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czernica")){
                        editor.putString("CzernicaDataZłapania", wybranaData);
                        editor.putString("CzernicaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czerniczka")){
                        editor.putString("CzerniczkaDataZłapania", wybranaData);
                        editor.putString("CzerniczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czubatka")){
                        editor.putString("CzubatkaDataZłapania", wybranaData);
                        editor.putString("CzubatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Czyż")){
                        editor.putString("CzyżDataZłapania", wybranaData);
                        editor.putString("CzyżMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Derkacz")){
                        editor.putString("DerkaczDataZłapania", wybranaData);
                        editor.putString("DerkaczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drop")){
                        editor.putString("DropDataZłapania", wybranaData);
                        editor.putString("DropMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozdaczek ciemny")){
                        editor.putString("DrozdaczekCiemnyDataZłapania", wybranaData);
                        editor.putString("DrozdaczekCiemnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozd czarnogardły")){
                        editor.putString("DrozdCzarnogardłyDataZłapania", wybranaData);
                        editor.putString("DrozdCzarnogardłyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozd obrożny")){
                        editor.putString("DrozdObrożnyDataZłapania", wybranaData);
                        editor.putString("DrozdObrożnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozd oliwkowy")){
                        editor.putString("DrozdOliwkowyDataZłapania", wybranaData);
                        editor.putString("DrozdOliwkowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozdoń pstry")){
                        editor.putString("DrozdońPstryDataZłapania", wybranaData);
                        editor.putString("DrozdońPstryMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozd rdzawogardły")){
                        editor.putString("DrozdRdzawogardłyDataZłapania", wybranaData);
                        editor.putString("DrozdRdzawogardłyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozd rdzawoskrzydły")){
                        editor.putString("DrozdRdzawoskrzydłyDataZłapania", wybranaData);
                        editor.putString("DrozdRdzawoskrzydłyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drozd rdzawy")){
                        editor.putString("DrozdRdzawyDataZłapania", wybranaData);
                        editor.putString("DrozdRdzawyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Droździk")){
                        editor.putString("DroździkDataZłapania", wybranaData);
                        editor.putString("DroździkMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Drzemlik")){
                        editor.putString("DrzemlikDataZłapania", wybranaData);
                        editor.putString("DrzemlikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dubelt")){
                        editor.putString("DubeltDataZłapania", wybranaData);
                        editor.putString("DubeltMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dudek")){
                        editor.putString("DudekDataZłapania", wybranaData);
                        editor.putString("DudekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dymówka")){
                        editor.putString("DymówkaDataZłapania", wybranaData);
                        editor.putString("DymówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł białogrzbiety")){
                        editor.putString("DzięciołBiałogrzbietyDataZłapania", wybranaData);
                        editor.putString("DzięciołBiałogrzbietyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł białoszyi")){
                        editor.putString("DzięciołBiałoszyiDataZłapania", wybranaData);
                        editor.putString("DzięciołBiałoszyiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł czarny")){
                        editor.putString("DzięciołCzarnyDataZłapania", wybranaData);
                        editor.putString("DzięciołCzarnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł duży")){
                        editor.putString("DzięciołDużyDataZłapania", wybranaData);
                        editor.putString("DzięciołDużyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięciołek")){
                        editor.putString("DzięciołekDataZłapania", wybranaData);
                        editor.putString("DzięciołekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł średni")){
                        editor.putString("DzięciołŚredniDataZłapania", wybranaData);
                        editor.putString("DzięciołŚredniMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł trójpalczasty")){
                        editor.putString("DzięciołTrójpalczastyDataZłapania", wybranaData);
                        editor.putString("DzięciołTrójpalczastyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł zielonosiwy")){
                        editor.putString("DzięciołZielonosiwyDataZłapania", wybranaData);
                        editor.putString("DzięciołZielonosiwyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzięcioł zielony")){
                        editor.putString("DzięciołZielonyDataZłapania", wybranaData);
                        editor.putString("DzięciołZielonyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzierlatka")){
                        editor.putString("DzierlatkaDataZłapania", wybranaData);
                        editor.putString("DzierlatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzierzba czarnoczelna")){
                        editor.putString("DzierzbaCzarnoczelnaDataZłapania", wybranaData);
                        editor.putString("DzierzbaCzarnoczelnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzierzba pustynna")){
                        editor.putString("DzierzbaPustynnaDataZłapania", wybranaData);
                        editor.putString("DzierzbaPustynnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzierzba rdzawosterna")){
                        editor.putString("DzierzbaRdzawosternaDataZłapania", wybranaData);
                        editor.putString("DzierzbaRdzawosternaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzierzba rudogłowa")){
                        editor.putString("DzierzbaRudogłowaDataZłapania", wybranaData);
                        editor.putString("DzierzbaRudogłowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dziwonia")){
                        editor.putString("DziwoniaDataZłapania", wybranaData);
                        editor.putString("DziwoniaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Dzwoniec")){
                        editor.putString("DzwoniecDataZłapania", wybranaData);
                        editor.putString("DzwoniecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Edredon")){
                        editor.putString("EdredonDataZłapania", wybranaData);
                        editor.putString("EdredonMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Flaming różowy")){
                        editor.putString("FlamingRóżowyDataZłapania", wybranaData);
                        editor.putString("FlamingRóżowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Fulmar")){
                        editor.putString("FulmarDataZłapania", wybranaData);
                        editor.putString("FulmarMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gadożer")){
                        editor.putString("GadożerDataZłapania", wybranaData);
                        editor.putString("GadożerMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gawron")){
                        editor.putString("GawronDataZłapania", wybranaData);
                        editor.putString("GawronMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gągoł")){
                        editor.putString("GągołDataZłapania", wybranaData);
                        editor.putString("GągołMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gajówka")){
                        editor.putString("GajówkaDataZłapania", wybranaData);
                        editor.putString("GajówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gąsiorek")){
                        editor.putString("GąsiorekDataZłapania", wybranaData);
                        editor.putString("GąsiorekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gęgawa")){
                        editor.putString("GęgawaDataZłapania", wybranaData);
                        editor.putString("GęgawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gęś białoczelna")){
                        editor.putString("GęśBiałoczelnaDataZłapania", wybranaData);
                        editor.putString("GęśBiałoczelnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gęsiówka egipska")){
                        editor.putString("GęsiówkaEgipskaDataZłapania", wybranaData);
                        editor.putString("GęsiówkaEgipskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gęś krótkodzioba")){
                        editor.putString("GęśKrótkodziobaDataZłapania", wybranaData);
                        editor.putString("GęśKrótkodziobaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gęś mała")){
                        editor.putString("GęśMałaDataZłapania", wybranaData);
                        editor.putString("GęśMałaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gęś zbożowa")){
                        editor.putString("GęśZbożowaDataZłapania", wybranaData);
                        editor.putString("GęśZbożowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gil")){
                        editor.putString("GilDataZłapania", wybranaData);
                        editor.putString("GilMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Głowienka")){
                        editor.putString("GłowienkaDataZłapania", wybranaData);
                        editor.putString("GłowienkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Głuptak")){
                        editor.putString("GłuptakDataZłapania", wybranaData);
                        editor.putString("GłuptakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Głuszec")){
                        editor.putString("GłuszecDataZłapania", wybranaData);
                        editor.putString("GłuszecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Głuszek")){
                        editor.putString("GłuszekDataZłapania", wybranaData);
                        editor.putString("GłuszekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Gołąb miejski")){
                        editor.putString("GołąbMiejskiDataZłapania", wybranaData);
                        editor.putString("GołąbMiejskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Górniczek")){
                        editor.putString("GórniczekDataZłapania", wybranaData);
                        editor.putString("GórniczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Grubodziób")){
                        editor.putString("GrubodzióbDataZłapania", wybranaData);
                        editor.putString("GrubodzióbMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Grzywacz")){
                        editor.putString("GrzywaczDataZłapania", wybranaData);
                        editor.putString("GrzywaczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Hełmiatka")){
                        editor.putString("HełmiatkaDataZłapania", wybranaData);
                        editor.putString("HełmiatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Hubara arabska")){
                        editor.putString("HubaraArabskaDataZłapania", wybranaData);
                        editor.putString("HubaraArabskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ibis kasztanowaty")){
                        editor.putString("IbisKasztanowatyDataZłapania", wybranaData);
                        editor.putString("IbisKasztanowatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jarząbek")){
                        editor.putString("JarząbekDataZłapania", wybranaData);
                        editor.putString("JarząbekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jarzębatka")){
                        editor.putString("JarzębatkaDataZłapania", wybranaData);
                        editor.putString("JarzębatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jaskółka rudawa")){
                        editor.putString("JaskółkaRudawaDataZłapania", wybranaData);
                        editor.putString("JaskółkaRudawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jastrząb")){
                        editor.putString("JastrząbDataZłapania", wybranaData);
                        editor.putString("JastrząbMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jemiołuszka")){
                        editor.putString("JemiołuszkaDataZłapania", wybranaData);
                        editor.putString("JemiołuszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jer")){
                        editor.putString("JerDataZłapania", wybranaData);
                        editor.putString("JerMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jerzyk")){
                        editor.putString("JerzykDataZłapania", wybranaData);
                        editor.putString("JerzykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jerzyk alpejski")){
                        editor.putString("JerzykAlpejskiDataZłapania", wybranaData);
                        editor.putString("JerzykAlpejskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Jerzyk blady")){
                        editor.putString("JerzykBladyDataZłapania", wybranaData);
                        editor.putString("JerzykBladyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Junko")){
                        editor.putString("JunkoDataZłapania", wybranaData);
                        editor.putString("JunkoMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kalandra czarna")){
                        editor.putString("KalandraCzarnaDataZłapania", wybranaData);
                        editor.putString("KalandraCzarnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kalandra szara")){
                        editor.putString("KalandraSzaraDataZłapania", wybranaData);
                        editor.putString("KalandraSzaraMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kamieniuszka")){
                        editor.putString("KamieniuszkaDataZłapania", wybranaData);
                        editor.putString("KamieniuszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kamuszik")){
                        editor.putString("KamuszikDataZłapania", wybranaData);
                        editor.putString("KamuszikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kania czarna")){
                        editor.putString("KaniaCzarnaDataZłapania", wybranaData);
                        editor.putString("KaniaCzarnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kania ruda")){
                        editor.putString("KaniaRudaDataZłapania", wybranaData);
                        editor.putString("KaniaRudaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kapturka")){
                        editor.putString("KapturkaDataZłapania", wybranaData);
                        editor.putString("KapturkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Karliczka")){
                        editor.putString("KarliczkaDataZłapania", wybranaData);
                        editor.putString("KarliczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kawka")){
                        editor.putString("KawkaDataZłapania", wybranaData);
                        editor.putString("KawkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kazarka rdzawa")){
                        editor.putString("KazarkaRdzawaDataZłapania", wybranaData);
                        editor.putString("KazarkaRdzawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kląskawka")){
                        editor.putString("KląskawkaDataZłapania", wybranaData);
                        editor.putString("KląskawkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kobczyk")){
                        editor.putString("KobczykDataZłapania", wybranaData);
                        editor.putString("KobczykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kobuz")){
                        editor.putString("KobuzDataZłapania", wybranaData);
                        editor.putString("KobuzMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kokoszka")){
                        editor.putString("KokoszkaDataZłapania", wybranaData);
                        editor.putString("KokoszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kopciuszek")){
                        editor.putString("KopciuszekDataZłapania", wybranaData);
                        editor.putString("KopciuszekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kormoran")){
                        editor.putString("KormoranDataZłapania", wybranaData);
                        editor.putString("KormoranMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kormoran czubaty")){
                        editor.putString("KormoranCzubatyDataZłapania", wybranaData);
                        editor.putString("KormoranCzubatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kormoran mały")){
                        editor.putString("KormoranMałyDataZłapania", wybranaData);
                        editor.putString("KormoranMałyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kos")){
                        editor.putString("KosDataZłapania", wybranaData);
                        editor.putString("KosMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kowalik")){
                        editor.putString("KowalikDataZłapania", wybranaData);
                        editor.putString("KowalikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krakwa")){
                        editor.putString("KrakwaDataZłapania", wybranaData);
                        editor.putString("KrakwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kraska")){
                        editor.putString("KraskaDataZłapania", wybranaData);
                        editor.putString("KraskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krętogłów")){
                        editor.putString("KrętogłówDataZłapania", wybranaData);
                        editor.putString("KrętogłówMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krogulec")){
                        editor.putString("KrogulecDataZłapania", wybranaData);
                        editor.putString("KrogulecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krogulec krótkonogi")){
                        editor.putString("KrogulecKrótkonogiDataZłapania", wybranaData);
                        editor.putString("KrogulecKrótkonogiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kropiatka")){
                        editor.putString("KropiatkaDataZłapania", wybranaData);
                        editor.putString("KropiatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kruk")){
                        editor.putString("KrukDataZłapania", wybranaData);
                        editor.putString("KrukMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krwawodziób")){
                        editor.putString("KrwawodzióbDataZłapania", wybranaData);
                        editor.putString("KrwawodzióbMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krzyżodziób modrzewiowy")){
                        editor.putString("KrzyżodzióbModrzewiowyDataZłapania", wybranaData);
                        editor.putString("KrzyżodzióbModrzewiowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krzyżodziób sosnowy")){
                        editor.putString("KrzyżodzióbSosnowyDataZłapania", wybranaData);
                        editor.putString("KrzyżodzióbSosnowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krzyżodziób świerkowy")){
                        editor.putString("KrzyżodzióbŚwierkowyDataZłapania", wybranaData);
                        editor.putString("KrzyżodzióbŚwierkowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Krzyżówka")){
                        editor.putString("KrzyżówkaDataZłapania", wybranaData);
                        editor.putString("KrzyżówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kszyk")){
                        editor.putString("KszykDataZłapania", wybranaData);
                        editor.putString("KszykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kukułka")){
                        editor.putString("KukułkaDataZłapania", wybranaData);
                        editor.putString("KukułkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kulczyk")){
                        editor.putString("KulczykDataZłapania", wybranaData);
                        editor.putString("KulczykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kulik cienkodzioby")){
                        editor.putString("KulikCienkodziobyDataZłapania", wybranaData);
                        editor.putString("KulikCienkodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kulik mniejszy")){
                        editor.putString("KulikMniejszyDataZłapania", wybranaData);
                        editor.putString("KulikMniejszyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kulik wielki")){
                        editor.putString("KulikWielkiDataZłapania", wybranaData);
                        editor.putString("KulikWielkiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kulon")){
                        editor.putString("KulonDataZłapania", wybranaData);
                        editor.putString("KulonMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kurhannik")){
                        editor.putString("KurhannikDataZłapania", wybranaData);
                        editor.putString("KurhannikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kuropatwa")){
                        editor.putString("KuropatwaDataZłapania", wybranaData);
                        editor.putString("KuropatwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kwiczoł")){
                        editor.putString("KwiczołDataZłapania", wybranaData);
                        editor.putString("KwiczołMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Kwokacz")){
                        editor.putString("KwokaczDataZłapania", wybranaData);
                        editor.putString("KwokaczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Lelek")){
                        editor.putString("LelekDataZłapania", wybranaData);
                        editor.putString("LelekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Lerka")){
                        editor.putString("LerkaDataZłapania", wybranaData);
                        editor.putString("LerkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Lodowiec")){
                        editor.putString("LodowiecDataZłapania", wybranaData);
                        editor.putString("LodowiecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Lodówka")){
                        editor.putString("LodówkaDataZłapania", wybranaData);
                        editor.putString("LodówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łabędź czarnodzioby")){
                        editor.putString("ŁabędźCzarnodziobyDataZłapania", wybranaData);
                        editor.putString("ŁabędźCzarnodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łabędź krzykliwy")){
                        editor.putString("ŁabędźKrzykliwyDataZłapania", wybranaData);
                        editor.putString("ŁabędźKrzykliwyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łabędź niemy")){
                        editor.putString("ŁabędźNiemyDataZłapania", wybranaData);
                        editor.putString("ŁabędźNiemyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łęczak")){
                        editor.putString("ŁęczakDataZłapania", wybranaData);
                        editor.putString("ŁęczakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łozówka")){
                        editor.putString("ŁozówkaDataZłapania", wybranaData);
                        editor.putString("ŁozówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łuskowiec")){
                        editor.putString("ŁuskowiecDataZłapania", wybranaData);
                        editor.putString("ŁuskowiecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Łyska")){
                        editor.putString("ŁyskaDataZłapania", wybranaData);
                        editor.putString("ŁyskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Makolągwa")){
                        editor.putString("MakolągwaDataZłapania", wybranaData);
                        editor.putString("MakolągwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mandarynka")){
                        editor.putString("MandarynkaDataZłapania", wybranaData);
                        editor.putString("MandarynkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Markaczka")){
                        editor.putString("MarkaczkaDataZłapania", wybranaData);
                        editor.putString("MarkaczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Markaczka amerykańska")){
                        editor.putString("MarkaczkaAmerykańskaDataZłapania", wybranaData);
                        editor.putString("MarkaczkaAmerykańskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Maskonur")){
                        editor.putString("MaskonurDataZłapania", wybranaData);
                        editor.putString("MaskonurMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mazurek")){
                        editor.putString("MazurekDataZłapania", wybranaData);
                        editor.putString("MazurekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa białogłowa")){
                        editor.putString("MewaBiałogłowaDataZłapania", wybranaData);
                        editor.putString("MewaBiałogłowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa blada")){
                        editor.putString("MewaBladaDataZłapania", wybranaData);
                        editor.putString("MewaBladaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa cienkodzioba")){
                        editor.putString("MewaCienkodziobaDataZłapania", wybranaData);
                        editor.putString("MewaCienkodziobaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa czarnogłowa")){
                        editor.putString("MewaCzarnogłowaDataZłapania", wybranaData);
                        editor.putString("MewaCzarnogłowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa delawarska")){
                        editor.putString("MewaDelawarskaDataZłapania", wybranaData);
                        editor.putString("MewaDelawarskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa karaibska")){
                        editor.putString("MewaKaraibskaDataZłapania", wybranaData);
                        editor.putString("MewaKaraibskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa mała")){
                        editor.putString("MewaMałaDataZłapania", wybranaData);
                        editor.putString("MewaMałaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa modrodzioba")){
                        editor.putString("MewaModrodziobaDataZłapania", wybranaData);
                        editor.putString("MewaModrodziobaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa obrożna")){
                        editor.putString("MewaObrożnaDataZłapania", wybranaData);
                        editor.putString("MewaObrożnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa polarna")){
                        editor.putString("MewaPolarnaDataZłapania", wybranaData);
                        editor.putString("MewaPolarnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa romańska")){
                        editor.putString("MewaRomańskaDataZłapania", wybranaData);
                        editor.putString("MewaRomańskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa siodłata")){
                        editor.putString("MewaSiodłataDataZłapania", wybranaData);
                        editor.putString("MewaSiodłataMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa siwa")){
                        editor.putString("MewaSiwaDataZłapania", wybranaData);
                        editor.putString("MewaSiwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa srebrzysta")){
                        editor.putString("MewaSrebrzystaDataZłapania", wybranaData);
                        editor.putString("MewaSrebrzystaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa trójpalczasta")){
                        editor.putString("MewaTrójpalczastaDataZłapania", wybranaData);
                        editor.putString("MewaTrójpalczastaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mewa żółtonoga")){
                        editor.putString("MewaŻółtonogaDataZłapania", wybranaData);
                        editor.putString("MewaŻółtonogaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Modraczek")){
                        editor.putString("ModraczekDataZłapania", wybranaData);
                        editor.putString("ModraczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Modraszka")){
                        editor.putString("ModraszkaDataZłapania", wybranaData);
                        editor.putString("ModraszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mornel")){
                        editor.putString("MornelDataZłapania", wybranaData);
                        editor.putString("MornelMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Muchołówka białoszyja")){
                        editor.putString("MuchołówkaBiałoszyjaDataZłapania", wybranaData);
                        editor.putString("MuchołówkaBiałoszyjaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Muchłówka mała")){
                        editor.putString("MuchłówkaMałaDataZłapania", wybranaData);
                        editor.putString("MuchłówkaMałaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Muchołówka szara")){
                        editor.putString("MuchołówkaSzaraDataZłapania", wybranaData);
                        editor.putString("MuchołówkaSzaraMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Muchołówka żałobna")){
                        editor.putString("MuchołówkaŻałobnaDataZłapania", wybranaData);
                        editor.putString("MuchołówkaŻałobnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Mysikrólik")){
                        editor.putString("MysikrólikDataZłapania", wybranaData);
                        editor.putString("MysikrólikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Myszołów")){
                        editor.putString("MyszołówDataZłapania", wybranaData);
                        editor.putString("MyszołówMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Myszołów włochaty")){
                        editor.putString("MyszołówWłochatyDataZłapania", wybranaData);
                        editor.putString("MyszołówWłochatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nagórnik")){
                        editor.putString("NagórnikDataZłapania", wybranaData);
                        editor.putString("NagórnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nawałnik burzowy")){
                        editor.putString("NawałnikBurzowyDataZłapania", wybranaData);
                        editor.putString("NawałnikBurzowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nawałnik duży")){
                        editor.putString("NawałnikDużyDataZłapania", wybranaData);
                        editor.putString("NawałnikDużyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nur białodzioby")){
                        editor.putString("NurBiałodziobyDataZłapania", wybranaData);
                        editor.putString("NurBiałodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nur czarnoszyi")){
                        editor.putString("NurCzarnoszyiDataZłapania", wybranaData);
                        editor.putString("NurCzarnoszyiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nurnik")){
                        editor.putString("NurnikDataZłapania", wybranaData);
                        editor.putString("NurnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nurogęś")){
                        editor.putString("NurogęśDataZłapania", wybranaData);
                        editor.putString("NurogęśMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nur rdzawoszyi")){
                        editor.putString("NurRdzawoszyiDataZłapania", wybranaData);
                        editor.putString("NurRdzawoszyiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nurzyk")){
                        editor.putString("NurzykDataZłapania", wybranaData);
                        editor.putString("NurzykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Nurzyk polarny")){
                        editor.putString("NurzykPolarnyDataZłapania", wybranaData);
                        editor.putString("NurzykPolarnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Oceannik żółtopłetwy")){
                        editor.putString("OceannikŻółtopłetwyDataZłapania", wybranaData);
                        editor.putString("OceannikŻółtopłetwyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ogorzałka")){
                        editor.putString("OgorzałkaDataZłapania", wybranaData);
                        editor.putString("OgorzałkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ogorzałka mała")){
                        editor.putString("OgorzałkaMałaDataZłapania", wybranaData);
                        editor.putString("OgorzałkaMałaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ohar")){
                        editor.putString("OharDataZłapania", wybranaData);
                        editor.putString("OharMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Oknówka")){
                        editor.putString("OknówkaDataZłapania", wybranaData);
                        editor.putString("OknówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orlica")){
                        editor.putString("OrlicaDataZłapania", wybranaData);
                        editor.putString("OrlicaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orlik grubodzioby")){
                        editor.putString("OrlikGrubodziobyDataZłapania", wybranaData);
                        editor.putString("OrlikGrubodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orlik krzykliwy")){
                        editor.putString("OrlikKrzykliwyDataZłapania", wybranaData);
                        editor.putString("OrlikKrzykliwyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orłosęp")){
                        editor.putString("OrłosępDataZłapania", wybranaData);
                        editor.putString("OrłosępMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ortolan")){
                        editor.putString("OrtolanDataZłapania", wybranaData);
                        editor.putString("OrtolanMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orzechówka")){
                        editor.putString("OrzechówkaDataZłapania", wybranaData);
                        editor.putString("OrzechówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orzeł cesarski")){
                        editor.putString("OrzełCesarskiDataZłapania", wybranaData);
                        editor.putString("OrzełCesarskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orzełek")){
                        editor.putString("OrzełekDataZłapania", wybranaData);
                        editor.putString("OrzełekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orzeł przedni")){
                        editor.putString("OrzełPrzedniDataZłapania", wybranaData);
                        editor.putString("OrzełPrzedniMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Orzeł stepowy")){
                        editor.putString("OrzełStepowyDataZłapania", wybranaData);
                        editor.putString("OrzełStepowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Osetnik")){
                        editor.putString("OsetnikDataZłapania", wybranaData);
                        editor.putString("OsetnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ostrygojad")){
                        editor.putString("OstrygojadDataZłapania", wybranaData);
                        editor.putString("OstrygojadMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pardwa mszarna")){
                        editor.putString("PardwaMszarnaDataZłapania", wybranaData);
                        editor.putString("PardwaMszarnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pasterz")){
                        editor.putString("PasterzDataZłapania", wybranaData);
                        editor.putString("PasterzMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Paszkot")){
                        editor.putString("PaszkotDataZłapania", wybranaData);
                        editor.putString("PaszkotMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pelikan kędzierzawy")){
                        editor.putString("PelikanKędzierzawyDataZłapania", wybranaData);
                        editor.putString("PelikanKędzierzawyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pelikan różowy")){
                        editor.putString("PelikanRóżowyDataZłapania", wybranaData);
                        editor.putString("PelikanRóżowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pełzacz leśny")){
                        editor.putString("PełzaczLeśnyDataZłapania", wybranaData);
                        editor.putString("PełzaczLeśnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pełzacz ogrodowy")){
                        editor.putString("PełzaczOgrodowyDataZłapania", wybranaData);
                        editor.putString("PełzaczOgrodowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Perkoz dwuczuby")){
                        editor.putString("PerkozDwuczubyDataZłapania", wybranaData);
                        editor.putString("PerkozDwuczubyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Perkozek")){
                        editor.putString("PerkozekDataZłapania", wybranaData);
                        editor.putString("PerkozekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Perkoz grubodzioby")){
                        editor.putString("PerkozGrubodziobyDataZłapania", wybranaData);
                        editor.putString("PerkozGrubodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Perkoz rdzawoszyi")){
                        editor.putString("PerkozRdzawoszyiDataZłapania", wybranaData);
                        editor.putString("PerkozRdzawoszyiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Perkoz rogaty")){
                        editor.putString("PerkozRogatyDataZłapania", wybranaData);
                        editor.putString("PerkozRogatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Piaskowiec")){
                        editor.putString("PiaskowiecDataZłapania", wybranaData);
                        editor.putString("PiaskowiecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Piecuszek")){
                        editor.putString("PiecuszekDataZłapania", wybranaData);
                        editor.putString("PiecuszekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Piegża")){
                        editor.putString("PiegżaDataZłapania", wybranaData);
                        editor.putString("PiegżaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pierwiosnek")){
                        editor.putString("PierwiosnekDataZłapania", wybranaData);
                        editor.putString("PierwiosnekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pleszka")){
                        editor.putString("PleszkaDataZłapania", wybranaData);
                        editor.putString("PleszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pliszka górska")){
                        editor.putString("PliszkaGórskaDataZłapania", wybranaData);
                        editor.putString("PliszkaGórskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pliszka siwa")){
                        editor.putString("PliszkaSiwaDataZłapania", wybranaData);
                        editor.putString("PliszkaSiwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pliszka żółta")){
                        editor.putString("PliszkaŻółtaDataZłapania", wybranaData);
                        editor.putString("PliszkaŻółtaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pluszcz")){
                        editor.putString("PluszczDataZłapania", wybranaData);
                        editor.putString("PluszczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Płaskonos")){
                        editor.putString("PłaskonosDataZłapania", wybranaData);
                        editor.putString("PłaskonosMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Płatkonóg płaskodzioby")){
                        editor.putString("PłatkonógPłaskodziobyDataZłapania", wybranaData);
                        editor.putString("PłatkonógPłaskodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Płatkonóg szydłodzioby")){
                        editor.putString("PłatkonógSzydłodziobyDataZłapania", wybranaData);
                        editor.putString("PłatkonógSzydłodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Płochacz halny")){
                        editor.putString("PłochaczHalnyDataZłapania", wybranaData);
                        editor.putString("PłochaczHalnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Płochacz syberyjski")){
                        editor.putString("PłochaczSyberyjskiDataZłapania", wybranaData);
                        editor.putString("PłochaczSyberyjskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Płomykówka")){
                        editor.putString("PłomykówkaDataZłapania", wybranaData);
                        editor.putString("PłomykówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Podgorzałka")){
                        editor.putString("PodgorzałkaDataZłapania", wybranaData);
                        editor.putString("PodgorzałkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Podróżniczek")){
                        editor.putString("PodróżniczekDataZłapania", wybranaData);
                        editor.putString("PodróżniczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pójdźka")){
                        editor.putString("PójdźkaDataZłapania", wybranaData);
                        editor.putString("PójdźkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pokląskwa")){
                        editor.putString("PokląskwaDataZłapania", wybranaData);
                        editor.putString("PokląskwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pokrzewka aksamitna")){
                        editor.putString("PokrzewkaAksamitnaDataZłapania", wybranaData);
                        editor.putString("PokrzewkaAksamitnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pokrzewka wąsata")){
                        editor.putString("PokrzewkaWąsataDataZłapania", wybranaData);
                        editor.putString("PokrzewkaWąsataMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pokrzywnica")){
                        editor.putString("PokrzywnicaDataZłapania", wybranaData);
                        editor.putString("PokrzywnicaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pomurnik")){
                        editor.putString("PomurnikDataZłapania", wybranaData);
                        editor.putString("PomurnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Poświerka")){
                        editor.putString("PoświerkaDataZłapania", wybranaData);
                        editor.putString("PoświerkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Potrzeszcz")){
                        editor.putString("PotrzeszczDataZłapania", wybranaData);
                        editor.putString("PotrzeszczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Potrzos")){
                        editor.putString("PotrzosDataZłapania", wybranaData);
                        editor.putString("PotrzosMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Przepiórka")){
                        editor.putString("PrzepiórkaDataZłapania", wybranaData);
                        editor.putString("PrzepiórkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Puchacz")){
                        editor.putString("PuchaczDataZłapania", wybranaData);
                        editor.putString("PuchaczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pustułeczka")){
                        editor.putString("PustułeczkaDataZłapania", wybranaData);
                        editor.putString("PustułeczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pustułka")){
                        editor.putString("PustułkaDataZłapania", wybranaData);
                        editor.putString("PustułkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Pustynnik")){
                        editor.putString("PustynnikDataZłapania", wybranaData);
                        editor.putString("PustynnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Puszczyk")){
                        editor.putString("PuszczykDataZłapania", wybranaData);
                        editor.putString("PuszczykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Puszczyk mszarny")){
                        editor.putString("PuszczykMszarnyDataZłapania", wybranaData);
                        editor.putString("PuszczykMszarnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Puszczyk uralski")){
                        editor.putString("PuszczykUralskiDataZłapania", wybranaData);
                        editor.putString("PuszczykUralskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Raniuszek")){
                        editor.putString("RaniuszekDataZłapania", wybranaData);
                        editor.putString("RaniuszekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Raróg")){
                        editor.putString("RarógDataZłapania", wybranaData);
                        editor.putString("RarógMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Remiz")){
                        editor.putString("RemizDataZłapania", wybranaData);
                        editor.putString("RemizMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rokitniczka")){
                        editor.putString("RokitniczkaDataZłapania", wybranaData);
                        editor.putString("RokitniczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rożeniec")){
                        editor.putString("RożeniecDataZłapania", wybranaData);
                        editor.putString("RożeniecMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rudzik")){
                        editor.putString("RudzikDataZłapania", wybranaData);
                        editor.putString("RudzikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybaczek srokaty")){
                        editor.putString("RybaczekSrokatyDataZłapania", wybranaData);
                        editor.putString("RybaczekSrokatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa białoczelna")){
                        editor.putString("RybitwaBiałoczelnaDataZłapania", wybranaData);
                        editor.putString("RybitwaBiałoczelnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa białoskrzydła")){
                        editor.putString("RybitwaBiałoskrzydłaDataZłapania", wybranaData);
                        editor.putString("RybitwaBiałoskrzydłaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa białowąsa")){
                        editor.putString("RybitwaBiałowąsaDataZłapania", wybranaData);
                        editor.putString("RybitwaBiałowąsaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa czarna")){
                        editor.putString("RybitwaCzarnaDataZłapania", wybranaData);
                        editor.putString("RybitwaCzarnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa czubata")){
                        editor.putString("RybitwaCzubataDataZłapania", wybranaData);
                        editor.putString("RybitwaCzubataMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa krótkodzioba")){
                        editor.putString("RybitwaKrótkodziobaDataZłapania", wybranaData);
                        editor.putString("RybitwaKrótkodziobaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa popielata")){
                        editor.putString("RybitwaPopielataDataZłapania", wybranaData);
                        editor.putString("RybitwaPopielataMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa różowa")){
                        editor.putString("RybitwaRóżowaDataZłapania", wybranaData);
                        editor.putString("RybitwaRóżowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa rzeczna")){
                        editor.putString("RybitwaRzecznaDataZłapania", wybranaData);
                        editor.putString("RybitwaRzecznaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybitwa wielkodzioba")){
                        editor.putString("RybitwaWielkodziobaDataZłapania", wybranaData);
                        editor.putString("RybitwaWielkodziobaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rybołów")){
                        editor.putString("RybołówDataZłapania", wybranaData);
                        editor.putString("RybołówMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rycyk")){
                        editor.putString("RycykDataZłapania", wybranaData);
                        editor.putString("RycykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Rzepołuch")){
                        editor.putString("RzepołuchDataZłapania", wybranaData);
                        editor.putString("RzepołuchMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Samotnik")){
                        editor.putString("SamotnikDataZłapania", wybranaData);
                        editor.putString("SamotnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sęp kasztanowaty")){
                        editor.putString("SępKasztanowatyDataZłapania", wybranaData);
                        editor.putString("SępKasztanowatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sęp płowy")){
                        editor.putString("SępPłowyDataZłapania", wybranaData);
                        editor.putString("SępPłowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sierpówka")){
                        editor.putString("SierpówkaDataZłapania", wybranaData);
                        editor.putString("SierpówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sieweczka mongolska")){
                        editor.putString("SieweczkaMongolskaDataZłapania", wybranaData);
                        editor.putString("SieweczkaMongolskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sieweczka morska")){
                        editor.putString("SieweczkaMorskaDataZłapania", wybranaData);
                        editor.putString("SieweczkaMorskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sieweczka obrożna")){
                        editor.putString("SieweczkaObrożnaDataZłapania", wybranaData);
                        editor.putString("SieweczkaObrożnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sieweczka pustynna")){
                        editor.putString("SieweczkaPustynnaDataZłapania", wybranaData);
                        editor.putString("SieweczkaPustynnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sieweczka rzeczna")){
                        editor.putString("SieweczkaRzecznaDataZłapania", wybranaData);
                        editor.putString("SieweczkaRzecznaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Siewka szara")){
                        editor.putString("SiewkaSzaraDataZłapania", wybranaData);
                        editor.putString("SiewkaSzaraMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Siewka złota")){
                        editor.putString("SiewkaZłotaDataZłapania", wybranaData);
                        editor.putString("SiewkaZłotaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Siewka złotawa")){
                        editor.putString("SiewkaZłotawaDataZłapania", wybranaData);
                        editor.putString("SiewkaZłotawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Siewnica")){
                        editor.putString("SiewnicaDataZłapania", wybranaData);
                        editor.putString("SiewnicaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sikora lazurowa")){
                        editor.putString("SikoraLazurowaDataZłapania", wybranaData);
                        editor.putString("SikoraLazurowaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sikora uboga")){
                        editor.putString("SikoraUbogaDataZłapania", wybranaData);
                        editor.putString("SikoraUbogaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Siniak")){
                        editor.putString("SiniakDataZłapania", wybranaData);
                        editor.putString("SiniakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Siwerniak")){
                        editor.putString("SiwerniakDataZłapania", wybranaData);
                        editor.putString("SiwerniakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Skowrończyk krótkopalcowy")){
                        editor.putString("SkowrończyKrótkopalcowyDataZłapania", wybranaData);
                        editor.putString("SkowrończykKrótkopalcowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Skowronek")){
                        editor.putString("SkowronekDataZłapania", wybranaData);
                        editor.putString("SkowronekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Skowronek białoskrzydły")){
                        editor.putString("SkowronekBiałoskrzydłyDataZłapania", wybranaData);
                        editor.putString("SkowronekBiałoskrzydłyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Słonka")){
                        editor.putString("SłonkaDataZłapania", wybranaData);
                        editor.putString("SłonkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Słowik rdzawy")){
                        editor.putString("SłowikRdzawyDataZłapania", wybranaData);
                        editor.putString("SłowikRdzawyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Słowik syberyjski")){
                        editor.putString("SłowikSyberyjskiDataZłapania", wybranaData);
                        editor.putString("SłowikSyberyjskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Słowik szary")){
                        editor.putString("SłowikSzaryDataZłapania", wybranaData);
                        editor.putString("SłowikSzaryMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sokół skalny")){
                        editor.putString("SokółSkalnyDataZłapania", wybranaData);
                        editor.putString("SokółSkalnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sokół wędrowny")){
                        editor.putString("SokółWędrownyDataZłapania", wybranaData);
                        editor.putString("SokółWędrownyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sosnówka")){
                        editor.putString("SosnówkaDataZłapania", wybranaData);
                        editor.putString("SosnówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sowa jarzębata")){
                        editor.putString("SowaJarzębataDataZłapania", wybranaData);
                        editor.putString("SowaJarzębataMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sowa śnieżna")){
                        editor.putString("SowaŚnieżnaDataZłapania", wybranaData);
                        editor.putString("SowaŚnieżnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sójka")){
                        editor.putString("SójkaDataZłapania", wybranaData);
                        editor.putString("SójkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sójka syberyjska")){
                        editor.putString("SójkaSyberyjskaDataZłapania", wybranaData);
                        editor.putString("SójkaSyberyjskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sóweczka")){
                        editor.putString("SóweczkaDataZłapania", wybranaData);
                        editor.putString("SóweczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sroka")){
                        editor.putString("SrokaDataZłapania", wybranaData);
                        editor.putString("SrokaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Srokosz")){
                        editor.putString("SrokoszDataZłapania", wybranaData);
                        editor.putString("SrokoszMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sterniczka")){
                        editor.putString("SterniczkaDataZłapania", wybranaData);
                        editor.putString("SterniczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Sterniczka jamajska")){
                        editor.putString("SterniczkaJamajskaDataZłapania", wybranaData);
                        editor.putString("SterniczkaJamajskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Strepet")){
                        editor.putString("StrepetDataZłapania", wybranaData);
                        editor.putString("StrepetMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Strumieniówka")){
                        editor.putString("StrumieniówkaDataZłapania", wybranaData);
                        editor.putString("StrumieniówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Strzyżyk")){
                        editor.putString("StrzyżykDataZłapania", wybranaData);
                        editor.putString("StrzyżykMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Syczek")){
                        editor.putString("SyczekDataZłapania", wybranaData);
                        editor.putString("SyczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szablodziób")){
                        editor.putString("SzablodzióbDataZłapania", wybranaData);
                        editor.putString("SzablodzióbMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szczudłak")){
                        editor.putString("SzczudłakDataZłapania", wybranaData);
                        editor.putString("SzczudłakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szczygieł")){
                        editor.putString("SzczygiełDataZłapania", wybranaData);
                        editor.putString("SzczygiełMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szlachar")){
                        editor.putString("SzlacharDataZłapania", wybranaData);
                        editor.putString("SzlacharMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szlamiec długodzioby")){
                        editor.putString("SzlamiecDługodziobyDataZłapania", wybranaData);
                        editor.putString("SzlamiecDługodziobyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szlamnik")){
                        editor.putString("SzlamnikDataZłapania", wybranaData);
                        editor.putString("SzlamnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Szpak")){
                        editor.putString("SzpakDataZłapania", wybranaData);
                        editor.putString("SzpakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ścierwnik")){
                        editor.putString("ŚcierwnikDataZłapania", wybranaData);
                        editor.putString("ŚcierwnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Ślepowron")){
                        editor.putString("ŚlepowronDataZłapania", wybranaData);
                        editor.putString("ŚlepowronMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Śmieszka")){
                        editor.putString("ŚmieszkaDataZłapania", wybranaData);
                        editor.putString("ŚmieszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Śnieguła")){
                        editor.putString("ŚniegułaDataZłapania", wybranaData);
                        editor.putString("ŚniegułaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Śnieżka")){
                        editor.putString("ŚnieżkaDataZłapania", wybranaData);
                        editor.putString("ŚnieżkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Śpiewak")){
                        editor.putString("ŚpiewakDataZłapania", wybranaData);
                        editor.putString("ŚpiewakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek drzewny")){
                        editor.putString("ŚwiergotekDrzewnyDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekDrzewnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek łąkowy")){
                        editor.putString("ŚwiergotekŁąkowyDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekŁąkowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek nadmorski")){
                        editor.putString("ŚwiergotekNadmorskiDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekNadmorskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek polny")){
                        editor.putString("ŚwiergotekPolnyDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekPolnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek rdzawogardły")){
                        editor.putString("ŚwiergotekRdzawogardłyDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekRdzawogardłyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek szponiasty")){
                        editor.putString("ŚwiergotekSzponiastyDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekSzponiastyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świergotek tajgowy")){
                        editor.putString("ŚwiergotekTajgowyDataZłapania", wybranaData);
                        editor.putString("ŚwiergotekTajgowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świerszczak")){
                        editor.putString("ŚwierszczakDataZłapania", wybranaData);
                        editor.putString("ŚwierszczakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świerszczak melodyjny")){
                        editor.putString("ŚwierszczakMelodyjnyDataZłapania", wybranaData);
                        editor.putString("ŚwierszczakMelodyjnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstun")){
                        editor.putString("ŚwistunDataZłapania", wybranaData);
                        editor.putString("ŚwistunMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstun amerykański")){
                        editor.putString("ŚwistunAmerykańskiDataZłapania", wybranaData);
                        editor.putString("ŚwistunAmerykańskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka ałtańska")){
                        editor.putString("ŚwistunkaAłtańskaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaAłtańskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka brunatna")){
                        editor.putString("ŚwistunkaBrunatnaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaBrunatnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka górska")){
                        editor.putString("ŚwistunkaGórskaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaGórskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka grubodzioba")){
                        editor.putString("ŚwistunkaGrubodziobaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaGrubodziobaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka iberyjska")){
                        editor.putString("ŚwistunkaIberyjskaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaIberyjskaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka leśna")){
                        editor.putString("ŚwistunkaLeśnaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaLeśnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka północna")){
                        editor.putString("ŚwistunkaPółnocnaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaPółnocnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka złotawa")){
                        editor.putString("ŚwistunkaZłotawaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaZłotawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Świstunka żółtawa")){
                        editor.putString("ŚwistunkaŻółtawaDataZłapania", wybranaData);
                        editor.putString("ŚwistunkaŻółtawaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Tamaryszka")){
                        editor.putString("TamaryszkaDataZłapania", wybranaData);
                        editor.putString("TamaryszkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Terekia")){
                        editor.putString("TerekiaDataZłapania", wybranaData);
                        editor.putString("TerekiaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trzciniak")){
                        editor.putString("TrzciniakDataZłapania", wybranaData);
                        editor.putString("TrzciniakMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trzcinniczek")){
                        editor.putString("TrzcinniczekDataZłapania", wybranaData);
                        editor.putString("TrzcinniczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trzcinniczek kaspijski")){
                        editor.putString("TrzcinniczekKaspijskiDataZłapania", wybranaData);
                        editor.putString("TrzcinniczekKaspijskiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trzmielojad")){
                        editor.putString("TrzmielojadDataZłapania", wybranaData);
                        editor.putString("TrzmielojadMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadel")){
                        editor.putString("TrznadelDataZłapania", wybranaData);
                        editor.putString("TrznadelMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadel białogłowy")){
                        editor.putString("TrznadelBiałogłowyDataZłapania", wybranaData);
                        editor.putString("TrznadelBiałogłowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadel czarnogłowy")){
                        editor.putString("TrznadelCzarnogłowyDataZłapania", wybranaData);
                        editor.putString("TrznadelCzarnogłowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadel czubaty")){
                        editor.putString("TrznadelCzubatyDataZłapania", wybranaData);
                        editor.putString("TrznadelCzubatyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadelek")){
                        editor.putString("TrznadelekDataZłapania", wybranaData);
                        editor.putString("TrznadelekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadel złotawy")){
                        editor.putString("TrznadelZłotawyDataZłapania", wybranaData);
                        editor.putString("TrznadelZłotawyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Trznadel złotobrewy")){
                        editor.putString("TrznadelZłotobrewyDataZłapania", wybranaData);
                        editor.putString("TrznadelZłotobrewyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Turkan")){
                        editor.putString("TurkanDataZłapania", wybranaData);
                        editor.putString("TurkanMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Turkawka")){
                        editor.putString("TurkawkaDataZłapania", wybranaData);
                        editor.putString("TurkawkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Turkawka wschodnia")){
                        editor.putString("TurkawkaWschodniaDataZłapania", wybranaData);
                        editor.putString("TurkawkaWschodniaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Uhla")){
                        editor.putString("UhlaDataZłapania", wybranaData);
                        editor.putString("UhlaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Uhla garbonosa")){
                        editor.putString("UhlaGarbonosaDataZłapania", wybranaData);
                        editor.putString("UhlaGarbonosaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Uszatka")){
                        editor.putString("UszatkaDataZłapania", wybranaData);
                        editor.putString("UszatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Uszatka błotna")){
                        editor.putString("UszatkaBłotnaDataZłapania", wybranaData);
                        editor.putString("UszatkaBłotnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Warzęcha")){
                        editor.putString("WarzęchaDataZłapania", wybranaData);
                        editor.putString("WarzęchaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wąsatka")){
                        editor.putString("WąsatkaDataZłapania", wybranaData);
                        editor.putString("WąsatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wieszczek")){
                        editor.putString("WieszczekDataZłapania", wybranaData);
                        editor.putString("WieszczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wilga")){
                        editor.putString("WilgaDataZłapania", wybranaData);
                        editor.putString("WilgaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wireonek czerwonooki")){
                        editor.putString("WireonekCzerwonookiDataZłapania", wybranaData);
                        editor.putString("WireonekCzerwonookiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Włochatka")){
                        editor.putString("WłochatkaDataZłapania", wybranaData);
                        editor.putString("WłochatkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wodniczka")){
                        editor.putString("WodniczkaDataZłapania", wybranaData);
                        editor.putString("WodniczkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wodnik")){
                        editor.putString("WodnikDataZłapania", wybranaData);
                        editor.putString("WodnikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wójcik")){
                        editor.putString("WójcikDataZłapania", wybranaData);
                        editor.putString("WójcikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wróbel")){
                        editor.putString("WróbelDataZłapania", wybranaData);
                        editor.putString("WróbelMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wróbel skalny")){
                        editor.putString("WróbelSkalnyDataZłapania", wybranaData);
                        editor.putString("WróbelSkalnyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wrona siwa")){
                        editor.putString("WronaSiwaDataZłapania", wybranaData);
                        editor.putString("WronaSiwaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wydrzyk długosterny")){
                        editor.putString("WydrzykDługosternyDataZłapania", wybranaData);
                        editor.putString("WydrzykDługosternyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wydrzyk ostrosterny")){
                        editor.putString("WydrzykOstrosternyDataZłapania", wybranaData);
                        editor.putString("WydrzykOstrosternyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wydrzyk tęposterny")){
                        editor.putString("WydrzykTęposternyDataZłapania", wybranaData);
                        editor.putString("WydrzykTęposternyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Wydrzyk wielki")){
                        editor.putString("WydrzykWielkiDataZłapania", wybranaData);
                        editor.putString("WydrzykWielkiMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zaganiacz")){
                        editor.putString("ZaganiaczDataZłapania", wybranaData);
                        editor.putString("ZaganiaczMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zaganiacz mały")){
                        editor.putString("ZaganiaczMałyDataZłapania", wybranaData);
                        editor.putString("ZaganiaczMałyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zaganiacz szczebiotliwy")){
                        editor.putString("ZaganiaczSzczebiotliwyDataZłapania", wybranaData);
                        editor.putString("ZaganiaczSzczebiotliwyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zaroślówka")){
                        editor.putString("ZaroślówkaDataZłapania", wybranaData);
                        editor.putString("ZaroślówkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zausznik")){
                        editor.putString("ZausznikDataZłapania", wybranaData);
                        editor.putString("ZausznikMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zięba")){
                        editor.putString("ZiębaDataZłapania", wybranaData);
                        editor.putString("ZiębaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zielonka")){
                        editor.putString("ZielonkaDataZłapania", wybranaData);
                        editor.putString("ZielonkaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zimorodek")){
                        editor.putString("ZimorodekDataZłapania", wybranaData);
                        editor.putString("ZimorodekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Zniczek")){
                        editor.putString("ZniczekDataZłapania", wybranaData);
                        editor.putString("ZniczekMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Żołna")){
                        editor.putString("ŻołnaDataZłapania", wybranaData);
                        editor.putString("ŻołnaMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Żuraw")){
                        editor.putString("ŻurawDataZłapania", wybranaData);
                        editor.putString("ŻurawMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Żuraw stepowy")){
                        editor.putString("ŻurawStepowyDataZłapania", wybranaData);
                        editor.putString("ŻurawStepowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Żwirowiec łąkowy")){
                        editor.putString("ŻwirowiecŁąkowyDataZłapania", wybranaData);
                        editor.putString("ŻwirowiecŁąkowyMiejsceZłapania", editTextLokalizacja.getText().toString());
                    }
                    if (sGatunek.equals("Żwirowiec stepowy")){
                        editor.putString("ŻwirowiecStepowyDataZłapania", wybranaData);
                        editor.putString("ŻwirowiecStepowyMiejsceZłapania", editTextLokalizacja.getText().toString());
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
