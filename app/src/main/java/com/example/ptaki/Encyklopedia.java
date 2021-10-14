package com.example.ptaki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ptaki.A.Alczyk;
import com.example.ptaki.A.Alka;
import com.example.ptaki.B.Baczek;
import com.example.ptaki.B.Bak;
import com.example.ptaki.B.Batalion;
import com.example.ptaki.B.Bazant;
import com.example.ptaki.B.Bekasik;
import com.example.ptaki.B.Bernikla_bialolica;
import com.example.ptaki.B.Bernikla_kanadyjska;
import com.example.ptaki.B.Bernikla_obrozna;
import com.example.ptaki.B.Bernikla_rdzawoszyja;
import com.example.ptaki.B.Bialorzytka;
import com.example.ptaki.B.Bialorzytka_plowa;
import com.example.ptaki.B.Bialorzytka_pstra;
import com.example.ptaki.B.Bialorzytka_pustynna;
import com.example.ptaki.B.Bialorzytka_rdzawa;
import com.example.ptaki.B.Bialorzytka_saharyjska;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Encyklopedia extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ALCZYK = "AlczykZłapany";
    public static final String ALKA = "AlkaZłapany";
    public static final String BĄCZEK = "BączekZłapany";
    public static final String BĄK = "BąkZłapany";
    public static final String BATALION = "BatalionZłapany";
    public static final String BAŻANT = "BażantZłapany";
    public static final String BEKASIK = "BekasikZłapany";
    public static final String BERNIKLA_BIAŁOLICA = "BerniklaBiałolicaZłapany";
    public static final String BERNIKLA_KANADYJSKA = "BerniklaKanadyjskaZłapany";
    public static final String BERNIKLA_OBROŻNA = "BerniklaObrożnaZłapany";
    public static final String BERNIKLA_RDZAWOSZYJA = "BerniklaRdzawoszyjaZłapany";
    public static final String BIAŁORZYTKA = "BiałorzytkaZłapany";
    public static final String BIAŁORZYTKA_PŁOWA = "BiałorzytkaPłowaZłapany";
    public static final String BIAŁORZYTKA_PSTRA = "BiałorzytkaPstraZłapany";
    public static final String BIAŁORZYTKA_PUSTYNNA = "BiałorzytkaPustynnaZłapany";
    public static final String BIAŁORZYTKA_RDZAWA = "BiałorzytkaRdzawaZłapany";
    public static final String BIAŁORZYTKA_SAHARYJSKA = "BiałorzytkaSaharyjskaZłapany";
    ArrayList<String> listaGatunków = new ArrayList<>();
    ArrayAdapter<String> listViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view =  inflater.inflate(R.layout.encyklopedia, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        utwórzListęGatunków();
        Collections.sort(listaGatunków, Collator.getInstance(new Locale("pl", "PL")));
        listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaGatunków);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                otwórzGatunek(position);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.Rosnąco){
            Toast.makeText(getActivity(), "Sortowanie A-Z", Toast.LENGTH_SHORT).show();
            Collections.sort(listaGatunków, Collator.getInstance(new Locale("pl", "PL")));
            listViewAdapter.notifyDataSetChanged();
        }
        if(id == R.id.Malejąco){
            Toast.makeText(getActivity(), "Sortowanie Z-A", Toast.LENGTH_SHORT).show();
            Collections.reverse(listaGatunków);
            listViewAdapter.notifyDataSetChanged();
        }
        if(id == R.id.Wszystkie){
            utwórzListęGatunków();
            listViewAdapter.notifyDataSetChanged();
            item.setChecked(true);
        }
        if(id == R.id.Znalezione){
            filtrujZnalezione();
            listViewAdapter.notifyDataSetChanged();
            item.setChecked(true);
        }
        if(id == R.id.Nieznalezione){
            filtrujNieznalezione();
            listViewAdapter.notifyDataSetChanged();
            item.setChecked(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public void filtrujZnalezione(){
        listaGatunków.clear();
        boolean złapany = false;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        złapany = sharedPreferences.getBoolean(ALCZYK, false);
        if(złapany)
            listaGatunków.add("Alczyk");
        złapany = sharedPreferences.getBoolean(ALKA, false);
        if(złapany)
            listaGatunków.add("Alka");
        złapany = sharedPreferences.getBoolean(BĄCZEK, false);
        if(złapany)
            listaGatunków.add("Bączek");
        złapany = sharedPreferences.getBoolean(BĄK, false);
        if(złapany)
            listaGatunków.add("Bąk");
        złapany = sharedPreferences.getBoolean(BATALION, false);
        if(złapany)
            listaGatunków.add("Batalion");
        złapany = sharedPreferences.getBoolean(BAŻANT, false);
        if(złapany)
            listaGatunków.add("Bażant");
        złapany = sharedPreferences.getBoolean(BEKASIK, false);
        if(złapany)
            listaGatunków.add("Bekasik");
        złapany = sharedPreferences.getBoolean(BERNIKLA_BIAŁOLICA, false);
        if(złapany)
            listaGatunków.add("Bernikla białolica");
        złapany = sharedPreferences.getBoolean(BERNIKLA_KANADYJSKA, false);
        if(złapany)
            listaGatunków.add("Bernikla kanadyjska");
        złapany = sharedPreferences.getBoolean(BERNIKLA_OBROŻNA, false);
        if(złapany)
            listaGatunków.add("Bernikla obrożna");
        złapany = sharedPreferences.getBoolean(BERNIKLA_RDZAWOSZYJA, false);
        if(złapany)
            listaGatunków.add("Bernikla rdzawoszyja");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA, false);
        if(złapany)
            listaGatunków.add("Białorzytka");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_PŁOWA, false);
        if(złapany)
            listaGatunków.add("Białorzytka płowa");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_PSTRA, false);
        if(złapany)
            listaGatunków.add("Białorzytka pstra");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_PUSTYNNA, false);
        if(złapany)
            listaGatunków.add("Białorzytka pustynna");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_RDZAWA, false);
        if(złapany)
            listaGatunków.add("Białorzytka rdzawa");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_SAHARYJSKA, false);
        if(złapany)
            listaGatunków.add("Białorzytka saharyjska");
    }

    public void filtrujNieznalezione(){
        listaGatunków.clear();
        boolean złapany = false;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        złapany = sharedPreferences.getBoolean(ALCZYK, false);
        if(!złapany)
            listaGatunków.add("Alczyk");
        złapany = sharedPreferences.getBoolean(ALKA, false);
        if(!złapany)
            listaGatunków.add("Alka");
        złapany = sharedPreferences.getBoolean(BĄCZEK, false);
        if(!złapany)
            listaGatunków.add("Bączek");
        złapany = sharedPreferences.getBoolean(BĄK, false);
        if(!złapany)
            listaGatunków.add("Bąk");
        złapany = sharedPreferences.getBoolean(BATALION, false);
        if(!złapany)
            listaGatunków.add("Batalion");
        złapany = sharedPreferences.getBoolean(BAŻANT, false);
        if(!złapany)
            listaGatunków.add("Bażant");
        złapany = sharedPreferences.getBoolean(BEKASIK, false);
        if(!złapany)
            listaGatunków.add("Bekasik");
        złapany = sharedPreferences.getBoolean(BERNIKLA_BIAŁOLICA, false);
        if(!złapany)
            listaGatunków.add("Bernikla białolica");
        złapany = sharedPreferences.getBoolean(BERNIKLA_KANADYJSKA, false);
        if(!złapany)
            listaGatunków.add("Bernikla kanadyjska");
        złapany = sharedPreferences.getBoolean(BERNIKLA_OBROŻNA, false);
        if(!złapany)
            listaGatunków.add("Bernikla obrożna");
        złapany = sharedPreferences.getBoolean(BERNIKLA_RDZAWOSZYJA, false);
        if(!złapany)
            listaGatunków.add("Bernikla rdzawoszyja");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA, false);
        if(!złapany)
            listaGatunków.add("Białorzytka");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_PŁOWA, false);
        if(!złapany)
            listaGatunków.add("Białorzytka płowa");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_PSTRA, false);
        if(!złapany)
            listaGatunków.add("Białorzytka pstra");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_PUSTYNNA, false);
        if(!złapany)
            listaGatunków.add("Białorzytka pustynna");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_RDZAWA, false);
        if(!złapany)
            listaGatunków.add("Białorzytka rdzawa");
        złapany = sharedPreferences.getBoolean(BIAŁORZYTKA_SAHARYJSKA, false);
        if(!złapany)
            listaGatunków.add("Białorzytka saharyjska");
    }

    public void utwórzListęGatunków(){
        listaGatunków.clear();
        listaGatunków.add("Alczyk");
        listaGatunków.add("Alka");
        listaGatunków.add("Bączek");
        listaGatunków.add("Bąk");
        listaGatunków.add("Batalion");
        listaGatunków.add("Bażant");
        listaGatunków.add("Bekasik");
        listaGatunków.add("Bernikla białolica");
        listaGatunków.add("Bernikla kanadyjska");
        listaGatunków.add("Bernikla obrożna");
        listaGatunków.add("Bernikla rdzawoszyja");
        listaGatunków.add("Białorzytka");
        listaGatunków.add("Białorzytka płowa");
        listaGatunków.add("Białorzytka pstra");
        listaGatunków.add("Białorzytka pustynna");
        listaGatunków.add("Białorzytka rdzawa");
        listaGatunków.add("Białorzytka saharyjska");
        //nowy sposób
        listaGatunków.add("Białozór");
        listaGatunków.add("Biegus arktyczny");
        listaGatunków.add("Biegus długoskrzydły");
        listaGatunków.add("Biegus karłowaty");
        listaGatunków.add("Biegus krzywodzioby");
        listaGatunków.add("Biegus malutki");
        listaGatunków.add("Biegus mały");
        listaGatunków.add("Biegus morski");
        listaGatunków.add("Biegus płaskodzioby");
        listaGatunków.add("Biegus płowy");
        listaGatunków.add("Biegus rdzawy");
        listaGatunków.add("Biegus tundrowy");
        listaGatunków.add("Biegus wielki");
        listaGatunków.add("Biegus zmienny");
        listaGatunków.add("Bielaczek");
        listaGatunków.add("Bielik");
        listaGatunków.add("Bielik wschodni");
        listaGatunków.add("Birginiak");
        listaGatunków.add("Błotniak łąkowy");
        listaGatunków.add("Błotniak stawowy");
        listaGatunków.add("Błotniak stepowy");
        listaGatunków.add("Błotniak zbożowy");
        listaGatunków.add("Bocian biały");
        listaGatunków.add("Bocian czarny");
        listaGatunków.add("Bogatka");
        listaGatunków.add("Brodziec piegowaty");
        listaGatunków.add("Brodziec piskliwy");
        listaGatunków.add("Brodziec plamisty");
        listaGatunków.add("Brodziec pławny");
        listaGatunków.add("Brodziec śniady");
        listaGatunków.add("Brodziec żółtonogi");
        listaGatunków.add("Brzęczka");
        listaGatunków.add("Brzegówka");
        listaGatunków.add("Burzyk balearski");
        listaGatunków.add("Burzyk północny");
        listaGatunków.add("Burzyk szary");
        listaGatunków.add("Burzyk żółtodzioby");
        listaGatunków.add("Cierlik");
        listaGatunków.add("Cierniówka");
        listaGatunków.add("Cietrzew");
        listaGatunków.add("Cyraneczka");
        listaGatunków.add("Cyraneczka karolińska");
        listaGatunków.add("Cyranka");
        listaGatunków.add("Cyranka modroskrzydła");
        listaGatunków.add("Czajka");
        listaGatunków.add("Czajka stepowa");
        listaGatunków.add("Czajka towarzyska");
        listaGatunków.add("Czapla biała");
        listaGatunków.add("Czapla modronosa");
        listaGatunków.add("Czapla nadobna");
        listaGatunków.add("Czapla purpurowa");
        listaGatunków.add("Czapla siwa");
        listaGatunków.add("Czapla złotawa");
        listaGatunków.add("Czarnogłówka");
        listaGatunków.add("Czarnowron");
        listaGatunków.add("Czeczotka");
        listaGatunków.add("Czeczotka tundrowa");
        listaGatunków.add("Czernica");
        listaGatunków.add("Czerniczka");
        listaGatunków.add("Czubatka");
        listaGatunków.add("Czyż");
        listaGatunków.add("Derkacz");
        listaGatunków.add("Drop");
        listaGatunków.add("Drozdaczek ciemny");
        listaGatunków.add("Drozd czarnogardły");
        listaGatunków.add("Drozd obrożny");
        listaGatunków.add("Drozd oliwkowy");
        listaGatunków.add("Drozdoń pstry");
        listaGatunków.add("Drozd rdzawogardły");
        listaGatunków.add("Drozd rdzawoskrzydły");
        listaGatunków.add("Drozd rdzawy");
        listaGatunków.add("Droździk");
        listaGatunków.add("Drzemlik");
        listaGatunków.add("Dubelt");
        listaGatunków.add("Dudek");
        listaGatunków.add("Dymówka");
        listaGatunków.add("Dzięcioł białogrzbiety");
        listaGatunków.add("Dzięcioł białoszyi");
        listaGatunków.add("Dzięcioł czarny");
        listaGatunków.add("Dzięcioł duży");
        listaGatunków.add("Dzięciołek");
        listaGatunków.add("Dzięcioł średni");
        listaGatunków.add("Dzięcioł trójpalczasty");
        listaGatunków.add("Dzięcioł zielonosiwy");
        listaGatunków.add("Dzięcioł zielony");
        listaGatunków.add("Dzierlatka");
        listaGatunków.add("Dzierzba czarnoczelna");
        listaGatunków.add("Dzierzba pustynna");
        listaGatunków.add("Dzierzba rdzawosterna");
        listaGatunków.add("Dzierzba rudogłowa");
        listaGatunków.add("Dziwonia");
        listaGatunków.add("Dzwoniec");
        listaGatunków.add("Edredon");
        listaGatunków.add("Flaming różowy");
        listaGatunków.add("Fulmar");
        listaGatunków.add("Gadożer");
        listaGatunków.add("Gawron");
        listaGatunków.add("Gągoł");
        listaGatunków.add("Gajówka");
        listaGatunków.add("Gąsiorek");
        listaGatunków.add("Gęgawa");
        listaGatunków.add("Gęś białoczelna");
        listaGatunków.add("Gęsiówka egipska");
        listaGatunków.add("Gęś krótkodzioba");
        listaGatunków.add("Gęś mała");
        listaGatunków.add("Gęś zbożowa");
        listaGatunków.add("Gil");
        listaGatunków.add("Głowienka");
        listaGatunków.add("Głuptak");
        listaGatunków.add("Głuszec");
        listaGatunków.add("Głuszek");
        listaGatunków.add("Gołąb miejski");
        listaGatunków.add("Górniczek");
        listaGatunków.add("Grubodziób");
        listaGatunków.add("Grzywacz");
        listaGatunków.add("Hełmiatka");
        listaGatunków.add("Hubara arabska");
        listaGatunków.add("Ibis kasztanowaty");
        listaGatunków.add("Jarząbek");
        listaGatunków.add("Jarzębatka");
        listaGatunków.add("Jaskółka rudawa");
        listaGatunków.add("Jastrząb");
        listaGatunków.add("Jemiołuszka");
        listaGatunków.add("Jer");
        listaGatunków.add("Jerzyk");
        listaGatunków.add("Jerzyk alpejski");
        listaGatunków.add("Jerzyk blady");
        listaGatunków.add("Junko");
        listaGatunków.add("Kalandra czarna");
        listaGatunków.add("Kalandra szara");
        listaGatunków.add("Kamieniuszka");
        listaGatunków.add("Kamuszik");
        listaGatunków.add("Kania czarna");
        listaGatunków.add("Kania ruda");
        listaGatunków.add("Kapturka");
        listaGatunków.add("Karliczka");
        listaGatunków.add("Kawka");
        listaGatunków.add("Kazarka rdzawa");
        listaGatunków.add("Kląskawka");
        listaGatunków.add("Kobczyk");
        listaGatunków.add("Kobuz");
        listaGatunków.add("Kokoszka");
        listaGatunków.add("Kopciuszek");
        listaGatunków.add("Kormoran");
        listaGatunków.add("Kormoran czubaty");
        listaGatunków.add("Kormoran mały");
        listaGatunków.add("Kos");
        listaGatunków.add("Kowalik");
        listaGatunków.add("Krakwa");
        listaGatunków.add("Kraska");
        listaGatunków.add("Krętogłów");
        listaGatunków.add("Krogulec");
        listaGatunków.add("Krogulec krótkonogi");
        listaGatunków.add("Kropiatka");
        listaGatunków.add("Kruk");
        listaGatunków.add("Krwawodziób");
        listaGatunków.add("Krzyżodziób modrzewiowy");
        listaGatunków.add("Krzyżodziób sosnowy");
        listaGatunków.add("Krzyżodziób świerkowy");
        listaGatunków.add("Krzyżówka");
        listaGatunków.add("Kszyk");
        listaGatunków.add("Kukułka");
        listaGatunków.add("Kulczyk");
        listaGatunków.add("Kulik cienkodzioby");
        listaGatunków.add("Kulik mniejszy");
        listaGatunków.add("Kulik wielki");
        listaGatunków.add("Kulon");
        listaGatunków.add("Kurhannik");
        listaGatunków.add("Kuropatwa");
        listaGatunków.add("Kwiczoł");
        listaGatunków.add("Kwokacz");
        listaGatunków.add("Lelek");
        listaGatunków.add("Lerka");
        listaGatunków.add("Lodowiec");
        listaGatunków.add("Lodówka");
        listaGatunków.add("Łabędź czarnodzioby");
        listaGatunków.add("Łabędź krzykliwy");
        listaGatunków.add("Łabędź niemy");
        listaGatunków.add("Łęczak");
        listaGatunków.add("Łozówka");
        listaGatunków.add("Łuskowiec");
        listaGatunków.add("Łyska");
        listaGatunków.add("Makolągwa");
        listaGatunków.add("Mandarynka");
        listaGatunków.add("Markaczka");
        listaGatunków.add("Markaczka amerykańska");
        listaGatunków.add("Maskonur");
        listaGatunków.add("Mazurek");
        listaGatunków.add("Mewa białogłowa");
        listaGatunków.add("Mewa blada");
        listaGatunków.add("Mewa cienkodzioba");
        listaGatunków.add("Mewa czarnogłowa");
        listaGatunków.add("Mewa delawarska");
        listaGatunków.add("Mewa karaibska");
        listaGatunków.add("Mewa mała");
        listaGatunków.add("Mewa modrodzioba");
        listaGatunków.add("Mewa obrożna");
        listaGatunków.add("Mewa polarna");
        listaGatunków.add("Mewa romańska");
        listaGatunków.add("Mewa siodłata");
        listaGatunków.add("Mewa siwa");
        listaGatunków.add("Mewa srebrzysta");
        listaGatunków.add("Mewa trójpalczasta");
        listaGatunków.add("Mewa żółtonoga");
        listaGatunków.add("Modraczek");
        listaGatunków.add("Modraszka");
        listaGatunków.add("Mornel");
        listaGatunków.add("Muchołówka białoszyja");
        listaGatunków.add("Muchołówka mała");
        listaGatunków.add("Muchołówka szara");
        listaGatunków.add("Muchołówka żałobna");
        listaGatunków.add("Mysikrólik");
        listaGatunków.add("Myszołów");
        listaGatunków.add("Myszołów włochaty");
        listaGatunków.add("Nagórnik");
        listaGatunków.add("Nawałnik burzowy");
        listaGatunków.add("Nawałnik duży");
        listaGatunków.add("Nur białodzioby");
        listaGatunków.add("Nur czarnoszyi");
        listaGatunków.add("Nurnik");
        listaGatunków.add("Nurogęś");
        listaGatunków.add("Nur rdzawoszyi");
        listaGatunków.add("Nurzyk");
        listaGatunków.add("Nurzyk polarny");
        listaGatunków.add("Oceannik żółtopłetwy");
        listaGatunków.add("Ogorzałka");
        listaGatunków.add("Ogorzałka mała");
        listaGatunków.add("Ohar");
        listaGatunków.add("Oknówka");
        listaGatunków.add("Orlica");
        listaGatunków.add("Orlik grubodzioby");
        listaGatunków.add("Orlik krzykliwy");
        listaGatunków.add("Orłosęp");
        listaGatunków.add("Ortolan");
        listaGatunków.add("Orzechówka");
        listaGatunków.add("Orzeł cesarski");
        listaGatunków.add("Orzełek");
        listaGatunków.add("Orzeł przedni");
        listaGatunków.add("Orzeł stepowy");
        listaGatunków.add("Osetnik");
        listaGatunków.add("Ostrygojad");
        listaGatunków.add("Pardwa mszarna");
        listaGatunków.add("Pasterz");
        listaGatunków.add("Paszkot");
        listaGatunków.add("Pelikan kędzierzawy");
        listaGatunków.add("Pelikan różowy");
        listaGatunków.add("Pełzacz leśny");
        listaGatunków.add("Pełzacz ogrodowy");
        listaGatunków.add("Perkoz dwuczuby");
        listaGatunków.add("Perkozek");
        listaGatunków.add("Perkoz grubodzioby");
        listaGatunków.add("Perkoz rdzawoszyi");
        listaGatunków.add("Perkoz rogaty");
        listaGatunków.add("Piaskowiec");
        listaGatunków.add("Piecuszek");
        listaGatunków.add("Piegża");
        listaGatunków.add("Pierwiosnek");
        listaGatunków.add("Pleszka");
        listaGatunków.add("Pliszka górska");
        listaGatunków.add("Pliszka siwa");
        listaGatunków.add("Pliszka żółta");
        listaGatunków.add("Pluszcz");
        listaGatunków.add("Płaskonos");
        listaGatunków.add("Płatkonóg płaskodzioby");
        listaGatunków.add("Płatkonóg szydłodzioby");
        listaGatunków.add("Płochacz halny");
        listaGatunków.add("Płochacz syberyjski");
        listaGatunków.add("Płomykówka");
        listaGatunków.add("Podgorzałka");
        listaGatunków.add("Podróżniczek");
        listaGatunków.add("Pójdźka");
        listaGatunków.add("Pokląskwa");
        listaGatunków.add("Pokrzewka aksamitna");
        listaGatunków.add("Pokrzewka wąsata");
        listaGatunków.add("Pokrzywnica");
        listaGatunków.add("Pomurnik");
        listaGatunków.add("Poświerka");
        listaGatunków.add("Potrzeszcz");
        listaGatunków.add("Potrzos");
        listaGatunków.add("Przepiórka");
        listaGatunków.add("Puchacz");
        listaGatunków.add("Pustułeczka");
        listaGatunków.add("Pustułka");
        listaGatunków.add("Pustynnik");
        listaGatunków.add("Puszczyk");
        listaGatunków.add("Puszczyk mszarny");
        listaGatunków.add("Puszczyk uralski");
        listaGatunków.add("Raniuszek");
        listaGatunków.add("Raróg");
        listaGatunków.add("Remiz");
        listaGatunków.add("Rokitniczka");
        listaGatunków.add("Rożeniec");
        listaGatunków.add("Rudzik");
        listaGatunków.add("Rybaczek srokaty");
        listaGatunków.add("Rybitwa białoczelna");
        listaGatunków.add("Rybitwa białoskrzydła");
        listaGatunków.add("Rybitwa białowąsa");
        listaGatunków.add("Rybitwa czarna");
        listaGatunków.add("Rybitwa czubata");
        listaGatunków.add("Rybitwa krótkodzioba");
        listaGatunków.add("Rybitwa popielata");
        listaGatunków.add("Rybitwa różowa");
        listaGatunków.add("Rybitwa rzeczna");
        listaGatunków.add("Rybitwa wielkodzioba");
        listaGatunków.add("Rybołów");
        listaGatunków.add("Rycyk");
        listaGatunków.add("Rzepołuch");
        listaGatunków.add("Samotnik");
        listaGatunków.add("Sęp kasztanowaty");
        listaGatunków.add("Sęp płowy");
        listaGatunków.add("Sierpówka");
        listaGatunków.add("Sieweczka mongolska");
        listaGatunków.add("Sieweczka morska");
        listaGatunków.add("Sieweczka obrożna");
        listaGatunków.add("Sieweczka pustynna");
        listaGatunków.add("Sieweczka rzeczna");
        listaGatunków.add("Siewka szara");
        listaGatunków.add("Siewka złota");
        listaGatunków.add("Siewka złotawa");
        listaGatunków.add("Siewnica");
        listaGatunków.add("Sikora lazurowa");
        listaGatunków.add("Sikora uboga");
        listaGatunków.add("Siniak");
        listaGatunków.add("Siwerniak");
        listaGatunków.add("Skowrończyk krótkopalcowy");
        listaGatunków.add("Skowronek");
        listaGatunków.add("Skowronek białoskrzydły");
        listaGatunków.add("Słonka");
        listaGatunków.add("Słowik rdzawy");
        listaGatunków.add("Słowik syberyjski");
        listaGatunków.add("Słowik szary");
        listaGatunków.add("Sokół skalny");
        listaGatunków.add("Sokół wędrowny");
        listaGatunków.add("Sosnówka");
        listaGatunków.add("Sowa jarzębata");
        listaGatunków.add("Sowa śnieżna");
        listaGatunków.add("Sójka");
        listaGatunków.add("Sójka syberyjska");
        listaGatunków.add("Sóweczka");
        listaGatunków.add("Sroka");
        listaGatunków.add("Srokosz");
        listaGatunków.add("Sterniczka");
        listaGatunków.add("Sterniczka jamajska");
        listaGatunków.add("Strepet");
        listaGatunków.add("Strumieniówka");
        listaGatunków.add("Strzyżyk");
        listaGatunków.add("Syczek");
        listaGatunków.add("Szablodziób");
        listaGatunków.add("Szczudłak");
        listaGatunków.add("Szczygieł");
        listaGatunków.add("Szlachar");
        listaGatunków.add("Szlamiec długodzioby");
        listaGatunków.add("Szlamnik");
        listaGatunków.add("Szpak");
        listaGatunków.add("Ścierwnik");
        listaGatunków.add("Ślepowron");
        listaGatunków.add("Śmieszka");
        listaGatunków.add("Śnieguła");
        listaGatunków.add("Śnieżka");
        listaGatunków.add("Śpiewak");
        listaGatunków.add("Świergotek drzewny");
        listaGatunków.add("Świergotek łąkowy");
        listaGatunków.add("Świergotek nadmorski");
        listaGatunków.add("Świergotek polny");
        listaGatunków.add("Świergotek rdzawogardły");
        listaGatunków.add("Świergotek szponiasty");
        listaGatunków.add("Świergotek tajgowy");
        listaGatunków.add("Świerszczak");
        listaGatunków.add("Świerszczak melodyjny");
        listaGatunków.add("Świstun");
        listaGatunków.add("Świstun amerykański");
        listaGatunków.add("Świstunka ałtańska");
        listaGatunków.add("Świstunka brunatna");
        listaGatunków.add("Świstunka górska");
        listaGatunków.add("Świstunka grubodzioba");
        listaGatunków.add("Świstunka iberyjska");
        listaGatunków.add("Świstunka leśna");
        listaGatunków.add("Świstunka północna");
        listaGatunków.add("Świstunka złotawa");
        listaGatunków.add("Świstunka żółtawa");
        listaGatunków.add("Tamaryszka");
        listaGatunków.add("Terekia");
        listaGatunków.add("Trzciniak");
        listaGatunków.add("Trzcinniczek");
        listaGatunków.add("Trzcinniczek kaspijski");
        listaGatunków.add("Trzmielojad");
        listaGatunków.add("Trznadel");
        listaGatunków.add("Trznadel białogłowy");
        listaGatunków.add("Trznadel czarnogłowy");
        listaGatunków.add("Trznadel czubaty");
        listaGatunków.add("Trznadelek");
        listaGatunków.add("Trznadel złotawy");
        listaGatunków.add("Trznadel złotobrewy");
        listaGatunków.add("Turkan");
        listaGatunków.add("Turkawka");
        listaGatunków.add("Turkawka wschodnia");
        listaGatunków.add("Uhla");
        listaGatunków.add("Uhla garbonosa");
        listaGatunków.add("Uszatka");
        listaGatunków.add("Uszatka błotna");
        listaGatunków.add("Warzęcha");
        listaGatunków.add("Wąsatka");
        listaGatunków.add("Wieszczek");
        listaGatunków.add("Wilga");
        listaGatunków.add("Wireonek czerwonooki");
        listaGatunków.add("Włochatka");
        listaGatunków.add("Wodniczka");
        listaGatunków.add("Wodnik");
        listaGatunków.add("Wójcik");
        listaGatunków.add("Wróbel");
        listaGatunków.add("Wróbel skalny");
        listaGatunków.add("Wrona siwa");
        listaGatunków.add("Wydrzyk długosterny");
        listaGatunków.add("Wydrzyk ostrosterny");
        listaGatunków.add("Wydrzyk tęposterny");
        listaGatunków.add("Wydrzyk wielki");
        listaGatunków.add("Zaganiacz");
        listaGatunków.add("Zaganiacz mały");
        listaGatunków.add("Zaganiacz szczebiotliwy");
        listaGatunków.add("Zaroślówka");
        listaGatunków.add("Zausznik");
        listaGatunków.add("Zięba");
        listaGatunków.add("Zielonka");
        listaGatunków.add("Zimorodek");
        listaGatunków.add("Zniczek");
        listaGatunków.add("Żołna");
        listaGatunków.add("Żuraw");
        listaGatunków.add("Żuraw stepowy");
        listaGatunków.add("Żwirowiec łąkowy");
        listaGatunków.add("Żwirowiec stepowy");
    }

    public void otwórzGatunek(int pozycja){
        String nazwa = listaGatunków.get(pozycja);
        if(nazwa.equals("Alczyk")){
            Intent intent = new Intent(getActivity(), Alczyk.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Alka")){
            Intent intent = new Intent(getActivity(), Alka.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bączek")){
            Intent intent = new Intent(getActivity(), Baczek.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bąk")){
            Intent intent = new Intent(getActivity(), Bak.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Batalion")){
            Intent intent = new Intent(getActivity(), Batalion.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bażant")){
            Intent intent = new Intent(getActivity(), Bazant.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bekasik")){
            Intent intent = new Intent(getActivity(), Bekasik.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bernikla białolica")){
            Intent intent = new Intent(getActivity(), Bernikla_bialolica.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bernikla kanadyjska")){
            Intent intent = new Intent(getActivity(), Bernikla_kanadyjska.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bernikla obrożna")){
            Intent intent = new Intent(getActivity(), Bernikla_obrozna.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Bernikla rdzawoszyja")){
            Intent intent = new Intent(getActivity(), Bernikla_rdzawoszyja.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Białorzytka")){
            Intent intent = new Intent(getActivity(), Bialorzytka.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Białorzytka płowa")){
            Intent intent = new Intent(getActivity(), Bialorzytka_plowa.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Białorzytka pstra")){
            Intent intent = new Intent(getActivity(), Bialorzytka_pstra.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Białorzytka pustynna")){
            Intent intent = new Intent(getActivity(), Bialorzytka_pustynna.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Białorzytka rdzawa")){
            Intent intent = new Intent(getActivity(), Bialorzytka_rdzawa.class);
            startActivity(intent);
        }
        else if(nazwa.equals("Białorzytka saharyjska")){
            Intent intent = new Intent(getActivity(), Bialorzytka_saharyjska.class);
            startActivity(intent);
        }
        //Nowy styl
        else if(nazwa.equals("Białozór")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Białozór");
            intent.putExtra("Status", "BiałozórStatus");
            intent.putExtra("Notatka", "BiałozórNotatka");
            intent.putExtra("Złapany", "BiałozórZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiałozórDataZłapania");
            intent.putExtra("Lokalizacja", "BiałozórMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiałozórMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiałozórZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus arktyczny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusArktyczny");
            intent.putExtra("Status", "BiegusArktycznyStatus");
            intent.putExtra("Notatka", "BiegusArktycznyNotatka");
            intent.putExtra("Złapany", "BiegusArktycznyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusArktycznyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusArktycznyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusArktycznyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusArktycznyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus długoskrzydły")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusDługoskrzydły");
            intent.putExtra("Status", "BiegusDługoskrzydłyStatus");
            intent.putExtra("Notatka", "BiegusDługoskrzydłyNotatka");
            intent.putExtra("Złapany", "BiegusDługoskrzydłyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusDługoskrzydłyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusDługoskrzydłyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusDługoskrzydłyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusDługoskrzydłyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus karłowaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusKarłowaty");
            intent.putExtra("Status", "BiegusKarłowatyStatus");
            intent.putExtra("Notatka", "BiegusKarłowatyNotatka");
            intent.putExtra("Złapany", "BiegusKarłowatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusKarłowatyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusKarłowatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusKarłowatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusKarłowatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus krzywodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusKrzywodzioby");
            intent.putExtra("Status", "BiegusKrzywodziobyStatus");
            intent.putExtra("Notatka", "BiegusKrzywodziobyNotatka");
            intent.putExtra("Złapany", "BiegusKrzywodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusKrzywodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusKrzywodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusKrzywodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusKrzywodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus malutki")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusMalutki");
            intent.putExtra("Status", "BiegusMalutkiStatus");
            intent.putExtra("Notatka", "BiegusMalutkiNotatka");
            intent.putExtra("Złapany", "BiegusMalutkiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusMalutkiDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusMalutkiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusMalutkiMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusMalutkiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus mały")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusMały");
            intent.putExtra("Status", "BiegusMałyStatus");
            intent.putExtra("Notatka", "BiegusMałyNotatka");
            intent.putExtra("Złapany", "BiegusMałyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusMałyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusMałyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusMałyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusMałyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus morski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusMorski");
            intent.putExtra("Status", "BiegusMorskiStatus");
            intent.putExtra("Notatka", "BiegusMorskiNotatka");
            intent.putExtra("Złapany", "BiegusMorskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusMorskiDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusMorskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusMorskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusMorskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus płaskodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusPłaskodzioby");
            intent.putExtra("Status", "BiegusPłaskodziobyStatus");
            intent.putExtra("Notatka", "BiegusPłaskodziobyNotatka");
            intent.putExtra("Złapany", "BiegusPłaskodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusPłaskodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusPłaskodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusPłaskodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusPłaskodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus płowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusPłowy");
            intent.putExtra("Status", "BiegusPłowyStatus");
            intent.putExtra("Notatka", "BiegusPłowyNotatka");
            intent.putExtra("Złapany", "BiegusPłowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusPłowyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusPłowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusPłowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusPłowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus rdzawy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusRdzawy");
            intent.putExtra("Status", "BiegusRdzawyStatus");
            intent.putExtra("Notatka", "BiegusRdzawyNotatka");
            intent.putExtra("Złapany", "BiegusRdzawyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusRdzawyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusRdzawyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusRdzawyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusRdzawyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus tundrowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusTundrowy");
            intent.putExtra("Status", "BiegusTundrowyStatus");
            intent.putExtra("Notatka", "BiegusTundrowyNotatka");
            intent.putExtra("Złapany", "BiegusTundrowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusTundrowyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusTundrowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusTundrowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusTundrowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus wielki")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusWielki");
            intent.putExtra("Status", "BiegusWielkiStatus");
            intent.putExtra("Notatka", "BiegusWielkiNotatka");
            intent.putExtra("Złapany", "BiegusWielkiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusWielkiDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusWielkiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusWielkiMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusWielkiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Biegus zmienny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BiegusZmienny");
            intent.putExtra("Status", "BiegusZmiennyStatus");
            intent.putExtra("Notatka", "BiegusZmiennyNotatka");
            intent.putExtra("Złapany", "BiegusZmiennyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BiegusZmiennyDataZłapania");
            intent.putExtra("Lokalizacja", "BiegusZmiennyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BiegusZmiennyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BiegusZmiennyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Bielaczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Bielaczek");
            intent.putExtra("Status", "BielaczekStatus");
            intent.putExtra("Notatka", "BielaczekNotatka");
            intent.putExtra("Złapany", "BielaczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BielaczekDataZłapania");
            intent.putExtra("Lokalizacja", "BielaczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BielaczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "BielaczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Bielik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Bielik");
            intent.putExtra("Status", "BielikStatus");
            intent.putExtra("Notatka", "BielikNotatka");
            intent.putExtra("Złapany", "BielikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BielikDataZłapania");
            intent.putExtra("Lokalizacja", "BielikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BielikMaZdjęcie");
            intent.putExtra("Zdjęcie", "BielikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Bielik wschodni")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BielikWschodni");
            intent.putExtra("Status", "BielikWschodniStatus");
            intent.putExtra("Notatka", "BielikWschodniNotatka");
            intent.putExtra("Złapany", "BielikWschodniZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BielikWschodniDataZłapania");
            intent.putExtra("Lokalizacja", "BielikWschodniMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BielikWschodniMaZdjęcie");
            intent.putExtra("Zdjęcie", "BielikWschodniZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Birginiak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Birginiak");
            intent.putExtra("Status", "BirginiakStatus");
            intent.putExtra("Notatka", "BirginiakNotatka");
            intent.putExtra("Złapany", "BirginiakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BirginiakDataZłapania");
            intent.putExtra("Lokalizacja", "BirginiakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BirginiakMaZdjęcie");
            intent.putExtra("Zdjęcie", "BirginiakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Błotniak łąkowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BłotniakŁąkowy");
            intent.putExtra("Status", "BłotniakŁąkowyStatus");
            intent.putExtra("Notatka", "BłotniakŁąkowyNotatka");
            intent.putExtra("Złapany", "BłotniakŁąkowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BłotniakŁąkowyDataZłapania");
            intent.putExtra("Lokalizacja", "BłotniakŁąkowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BłotniakŁąkowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BłotniakŁąkowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Błotniak stawowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BłotniakStawowy");
            intent.putExtra("Status", "BłotniakStawowyStatus");
            intent.putExtra("Notatka", "BłotniakStawowyNotatka");
            intent.putExtra("Złapany", "BłotniakStawowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BłotniakStawowyDataZłapania");
            intent.putExtra("Lokalizacja", "BłotniakStawowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BłotniakStawowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BłotniakStawowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Błotniak stepowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BłotniakStepowy");
            intent.putExtra("Status", "BłotniakStepowyStatus");
            intent.putExtra("Notatka", "BłotniakStepowyNotatka");
            intent.putExtra("Złapany", "BłotniakStepowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BłotniakStepowyDataZłapania");
            intent.putExtra("Lokalizacja", "BłotniakStepowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BłotniakStepowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BłotniakStepowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Błotniak zbożowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BłotniakZbożowy");
            intent.putExtra("Status", "BłotniakZbożowyStatus");
            intent.putExtra("Notatka", "BłotniakZbożowyNotatka");
            intent.putExtra("Złapany", "BłotniakZbożowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BłotniakZbożowyDataZłapania");
            intent.putExtra("Lokalizacja", "BłotniakZbożowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BłotniakZbożowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BłotniakZbożowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Bocian biały")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BocianBiały");
            intent.putExtra("Status", "BocianBiałyStatus");
            intent.putExtra("Notatka", "BocianBiałyNotatka");
            intent.putExtra("Złapany", "BocianBiałyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BocianBiałyDataZłapania");
            intent.putExtra("Lokalizacja", "BocianBiałyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BocianBiałyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BocianBiałyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Bocian czarny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BocianCzarny");
            intent.putExtra("Status", "BocianCzarnyStatus");
            intent.putExtra("Notatka", "BocianCzarnyNotatka");
            intent.putExtra("Złapany", "BocianCzarnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BocianCzarnyDataZłapania");
            intent.putExtra("Lokalizacja", "BocianCzarnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BocianCzarnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BocianCzarnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Bogatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Bogatka");
            intent.putExtra("Status", "BogatkaStatus");
            intent.putExtra("Notatka", "BogatkaNotatka");
            intent.putExtra("Złapany", "BogatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BogatkaDataZłapania");
            intent.putExtra("Lokalizacja", "BogatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BogatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "BogatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brodziec piegowaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BrodziecPiegowaty");
            intent.putExtra("Status", "BrodziecPiegowatyStatus");
            intent.putExtra("Notatka", "BrodziecPiegowatyNotatka");
            intent.putExtra("Złapany", "BrodziecPiegowatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrodziecPiegowatyDataZłapania");
            intent.putExtra("Lokalizacja", "BrodziecPiegowatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrodziecPiegowatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrodziecPiegowatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brodziec piskliwy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BrodziecPiskliwy");
            intent.putExtra("Status", "BrodziecPiskliwyStatus");
            intent.putExtra("Notatka", "BrodziecPiskliwyNotatka");
            intent.putExtra("Złapany", "BrodziecPiskliwyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrodziecPiskliwyDataZłapania");
            intent.putExtra("Lokalizacja", "BrodziecPiskliwyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrodziecPiskliwyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrodziecPiskliwyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brodziec plamisty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BrodziecPlamisty");
            intent.putExtra("Status", "BrodziecPlamistyStatus");
            intent.putExtra("Notatka", "BrodziecPlamistyNotatka");
            intent.putExtra("Złapany", "BrodziecPlamistyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrodziecPlamistyDataZłapania");
            intent.putExtra("Lokalizacja", "BrodziecPlamistyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrodziecPlamistyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrodziecPlamistyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brodziec pławny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BrodziecPławny");
            intent.putExtra("Status", "BrodziecPławnyStatus");
            intent.putExtra("Notatka", "BrodziecPławnyNotatka");
            intent.putExtra("Złapany", "BrodziecPławnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrodziecPławnyDataZłapania");
            intent.putExtra("Lokalizacja", "BrodziecPławnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrodziecPławnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrodziecPławnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brodziec śniady")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BrodziecŚniady");
            intent.putExtra("Status", "BrodziecŚniadyStatus");
            intent.putExtra("Notatka", "BrodziecŚniadyNotatka");
            intent.putExtra("Złapany", "BrodziecŚniadyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrodziecŚniadyDataZłapania");
            intent.putExtra("Lokalizacja", "BrodziecŚniadyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrodziecŚniadyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrodziecŚniadyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brodziec żółtonogi")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BrodziecŻółtonogi");
            intent.putExtra("Status", "BrodziecŻółtonogiStatus");
            intent.putExtra("Notatka", "BrodziecŻółtonogiNotatka");
            intent.putExtra("Złapany", "BrodziecŻółtonogiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrodziecŻółtonogiDataZłapania");
            intent.putExtra("Lokalizacja", "BrodziecŻółtonogiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrodziecŻółtonogiMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrodziecŻółtonogiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brzęczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Brzęczka");
            intent.putExtra("Status", "BrzęczkaStatus");
            intent.putExtra("Notatka", "BrzęczkaNotatka");
            intent.putExtra("Złapany", "BrzęczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrzęczkaDataZłapania");
            intent.putExtra("Lokalizacja", "BrzęczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrzęczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrzęczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Brzegówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Brzegówka");
            intent.putExtra("Status", "BrzegówkaStatus");
            intent.putExtra("Notatka", "BrzegówkaNotatka");
            intent.putExtra("Złapany", "BrzegówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BrzegówkaDataZłapania");
            intent.putExtra("Lokalizacja", "BrzegówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BrzegówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "BrzegówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Burzyk balearski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BurzykBalearski");
            intent.putExtra("Status", "BurzykBalearskiStatus");
            intent.putExtra("Notatka", "BurzykBalearskiNotatka");
            intent.putExtra("Złapany", "BurzykBalearskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BurzykBalearskiDataZłapania");
            intent.putExtra("Lokalizacja", "BurzykBalearskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BurzykBalearskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "BurzykBalearskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Burzyk północny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BurzykPółnocny");
            intent.putExtra("Status", "BurzykPółnocnyStatus");
            intent.putExtra("Notatka", "BurzykPółnocnyNotatka");
            intent.putExtra("Złapany", "BurzykPółnocnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BurzykPółnocnyDataZłapania");
            intent.putExtra("Lokalizacja", "BurzykPółnocnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BurzykPółnocnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BurzykPółnocnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Burzyk szary")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BurzykSzary");
            intent.putExtra("Status", "BurzykSzaryStatus");
            intent.putExtra("Notatka", "BurzykSzaryNotatka");
            intent.putExtra("Złapany", "BurzykSzaryZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BurzykSzaryDataZłapania");
            intent.putExtra("Lokalizacja", "BurzykSzaryMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BurzykSzaryMaZdjęcie");
            intent.putExtra("Zdjęcie", "BurzykSzaryZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Burzyk żółtodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "BurzykŻółtodzioby");
            intent.putExtra("Status", "BurzykŻółtodziobyStatus");
            intent.putExtra("Notatka", "BurzykŻółtodziobyNotatka");
            intent.putExtra("Złapany", "BurzykŻółtodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "BurzykŻółtodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "BurzykŻółtodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "BurzykŻółtodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "BurzykŻółtodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cierlik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Cierlik");
            intent.putExtra("Status", "CierlikStatus");
            intent.putExtra("Notatka", "CierlikNotatka");
            intent.putExtra("Złapany", "CierlikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CierlikDataZłapania");
            intent.putExtra("Lokalizacja", "CierlikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CierlikMaZdjęcie");
            intent.putExtra("Zdjęcie", "CierlikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cierniówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Cierniówka");
            intent.putExtra("Status", "CierniówkaStatus");
            intent.putExtra("Notatka", "CierniówkaNotatka");
            intent.putExtra("Złapany", "CierniówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CierniówkaDataZłapania");
            intent.putExtra("Lokalizacja", "CierniówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CierniówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CierniówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cietrzew")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Cietrzew");
            intent.putExtra("Status", "CietrzewStatus");
            intent.putExtra("Notatka", "CietrzewNotatka");
            intent.putExtra("Złapany", "CietrzewZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CietrzewDataZłapania");
            intent.putExtra("Lokalizacja", "CietrzewMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CietrzewMaZdjęcie");
            intent.putExtra("Zdjęcie", "CietrzewZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cyraneczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Cyraneczka");
            intent.putExtra("Status", "CyraneczkaStatus");
            intent.putExtra("Notatka", "CyraneczkaNotatka");
            intent.putExtra("Złapany", "CyraneczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CyraneczkaDataZłapania");
            intent.putExtra("Lokalizacja", "CyraneczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CyraneczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CyraneczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cyraneczka karolińska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CyraneczkaKarolińska");
            intent.putExtra("Status", "CyraneczkaKarolińskaStatus");
            intent.putExtra("Notatka", "CyraneczkaKarolińskaNotatka");
            intent.putExtra("Złapany", "CyraneczkaKarolińskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CyraneczkaKarolińskaDataZłapania");
            intent.putExtra("Lokalizacja", "CyraneczkaKarolińskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CyraneczkaKarolińskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CyraneczkaKarolińskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cyranka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Cyranka");
            intent.putExtra("Status", "CyrankaStatus");
            intent.putExtra("Notatka", "CyrankaNotatka");
            intent.putExtra("Złapany", "CyrankaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CyrankaDataZłapania");
            intent.putExtra("Lokalizacja", "CyrankaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CyrankaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CyrankaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Cyranka modroskrzydła")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CyrankaModroskrzydła");
            intent.putExtra("Status", "CyrankaModroskrzydłaStatus");
            intent.putExtra("Notatka", "CyrankaModroskrzydłaNotatka");
            intent.putExtra("Złapany", "CyrankaModroskrzydłaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CyrankaModroskrzydłaDataZłapania");
            intent.putExtra("Lokalizacja", "CyrankaModroskrzydłaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CyrankaModroskrzydłaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CyrankaModroskrzydłaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czajka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czajka");
            intent.putExtra("Status", "CzajkaStatus");
            intent.putExtra("Notatka", "CzajkaNotatka");
            intent.putExtra("Złapany", "CzajkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzajkaDataZłapania");
            intent.putExtra("Lokalizacja", "CzajkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzajkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzajkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czajka stepowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzajkaStepowa");
            intent.putExtra("Status", "CzajkaStepowaStatus");
            intent.putExtra("Notatka", "CzajkaStepowaNotatka");
            intent.putExtra("Złapany", "CzajkaStepowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzajkaStepowaDataZłapania");
            intent.putExtra("Lokalizacja", "CzajkaStepowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzajkaStepowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzajkaStepowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czajka towarzyska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzajkaTowarzyska");
            intent.putExtra("Status", "CzajkaTowarzyskaStatus");
            intent.putExtra("Notatka", "CzajkaTowarzyskaNotatka");
            intent.putExtra("Złapany", "CzajkaTowarzyskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzajkaTowarzyskaDataZłapania");
            intent.putExtra("Lokalizacja", "CzajkaTowarzyskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzajkaTowarzyskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzajkaTowarzyskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czapla biała")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzaplaBiała");
            intent.putExtra("Status", "CzaplaBiałaStatus");
            intent.putExtra("Notatka", "CzaplaBiałaNotatka");
            intent.putExtra("Złapany", "CzaplaBiałaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzaplaBiałaDataZłapania");
            intent.putExtra("Lokalizacja", "CzaplaBiałaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzaplaBiałaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzaplaBiałaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czapla modronosa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzaplaModronosa");
            intent.putExtra("Status", "CzaplaModronosaStatus");
            intent.putExtra("Notatka", "CzaplaModronosaNotatka");
            intent.putExtra("Złapany", "CzaplaModronosaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzaplaModronosaDataZłapania");
            intent.putExtra("Lokalizacja", "CzaplaModronosaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzaplaModronosaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzaplaModronosaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czapla nadobna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzaplaNadobna");
            intent.putExtra("Status", "CzaplaNadobnaStatus");
            intent.putExtra("Notatka", "CzaplaNadobnaNotatka");
            intent.putExtra("Złapany", "CzaplaNadobnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzaplaNadobnaDataZłapania");
            intent.putExtra("Lokalizacja", "CzaplaNadobnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzaplaNadobnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzaplaNadobnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czapla purpurowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzaplaPurpurowa");
            intent.putExtra("Status", "CzaplaPurpurowaStatus");
            intent.putExtra("Notatka", "CzaplaPurpurowaNotatka");
            intent.putExtra("Złapany", "CzaplaPurpurowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzaplaPurpurowaDataZłapania");
            intent.putExtra("Lokalizacja", "CzaplaPurpurowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzaplaPurpurowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzaplaPurpurowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czapla siwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzaplaSiwa");
            intent.putExtra("Status", "CzaplaSiwaStatus");
            intent.putExtra("Notatka", "CzaplaSiwaNotatka");
            intent.putExtra("Złapany", "CzaplaSiwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzaplaSiwaDataZłapania");
            intent.putExtra("Lokalizacja", "CzaplaSiwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzaplaSiwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzaplaSiwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czapla złotawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzaplaZłotawa");
            intent.putExtra("Status", "CzaplaZłotawaStatus");
            intent.putExtra("Notatka", "CzaplaZłotawaNotatka");
            intent.putExtra("Złapany", "CzaplaZłotawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzaplaZłotawaDataZłapania");
            intent.putExtra("Lokalizacja", "CzaplaZłotawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzaplaZłotawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzaplaZłotawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czarnogłówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czarnogłówka");
            intent.putExtra("Status", "CzarnogłówkaStatus");
            intent.putExtra("Notatka", "CzarnogłówkaNotatka");
            intent.putExtra("Złapany", "CzarnogłówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzarnogłówkaDataZłapania");
            intent.putExtra("Lokalizacja", "CzarnogłówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzarnogłówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzarnogłówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czarnowron")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czarnowron");
            intent.putExtra("Status", "CzarnowronStatus");
            intent.putExtra("Notatka", "CzarnowronNotatka");
            intent.putExtra("Złapany", "CzarnowronZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzarnowronDataZłapania");
            intent.putExtra("Lokalizacja", "CzarnowronMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzarnowronMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzarnowronZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czeczotka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czeczotka");
            intent.putExtra("Status", "CzeczotkaStatus");
            intent.putExtra("Notatka", "CzeczotkaNotatka");
            intent.putExtra("Złapany", "CzeczotkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzeczotkaDataZłapania");
            intent.putExtra("Lokalizacja", "CzeczotkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzeczotkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzeczotkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czeczotka tundrowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "CzeczotkaTundrowa");
            intent.putExtra("Status", "CzeczotkaTundrowaStatus");
            intent.putExtra("Notatka", "CzeczotkaTundrowaNotatka");
            intent.putExtra("Złapany", "CzeczotkaTundrowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzeczotkaTundrowaDataZłapania");
            intent.putExtra("Lokalizacja", "CzeczotkaTundrowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzeczotkaTundrowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzeczotkaTundrowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czernica")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czernica");
            intent.putExtra("Status", "CzernicaStatus");
            intent.putExtra("Notatka", "CzernicaNotatka");
            intent.putExtra("Złapany", "CzernicaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzernicaDataZłapania");
            intent.putExtra("Lokalizacja", "CzernicaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzernicaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzernicaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czerniczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czerniczka");
            intent.putExtra("Status", "CzerniczkaStatus");
            intent.putExtra("Notatka", "CzerniczkaNotatka");
            intent.putExtra("Złapany", "CzerniczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzerniczkaDataZłapania");
            intent.putExtra("Lokalizacja", "CzerniczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzerniczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzerniczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czubatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czubatka");
            intent.putExtra("Status", "CzubatkaStatus");
            intent.putExtra("Notatka", "CzubatkaNotatka");
            intent.putExtra("Złapany", "CzubatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzubatkaDataZłapania");
            intent.putExtra("Lokalizacja", "CzubatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzubatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzubatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Czyż")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Czyż");
            intent.putExtra("Status", "CzyżStatus");
            intent.putExtra("Notatka", "CzyżNotatka");
            intent.putExtra("Złapany", "CzyżZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "CzyżDataZłapania");
            intent.putExtra("Lokalizacja", "CzyżMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "CzyżMaZdjęcie");
            intent.putExtra("Zdjęcie", "CzyżZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Derkacz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Derkacz");
            intent.putExtra("Status", "DerkaczStatus");
            intent.putExtra("Notatka", "DerkaczNotatka");
            intent.putExtra("Złapany", "DerkaczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DerkaczDataZłapania");
            intent.putExtra("Lokalizacja", "DerkaczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DerkaczMaZdjęcie");
            intent.putExtra("Zdjęcie", "DerkaczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drop")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Drop");
            intent.putExtra("Status", "DropStatus");
            intent.putExtra("Notatka", "DropNotatka");
            intent.putExtra("Złapany", "DropZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DropDataZłapania");
            intent.putExtra("Lokalizacja", "DropMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DropMaZdjęcie");
            intent.putExtra("Zdjęcie", "DropZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozdaczek ciemny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdaczekCiemny");
            intent.putExtra("Status", "DrozdaczekCiemnyStatus");
            intent.putExtra("Notatka", "DrozdaczekCiemnyNotatka");
            intent.putExtra("Złapany", "DrozdaczekCiemnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdaczekCiemnyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdaczekCiemnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdaczekCiemnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdaczekCiemnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozd czarnogardły")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdCzarnogardły");
            intent.putExtra("Status", "DrozdCzarnogardłyStatus");
            intent.putExtra("Notatka", "DrozdCzarnogardłyNotatka");
            intent.putExtra("Złapany", "DrozdCzarnogardłyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdCzarnogardłyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdCzarnogardłyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdCzarnogardłyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdCzarnogardłyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozd obrożny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdObrożny");
            intent.putExtra("Status", "DrozdObrożnyStatus");
            intent.putExtra("Notatka", "DrozdObrożnyNotatka");
            intent.putExtra("Złapany", "DrozdObrożnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdObrożnyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdObrożnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdObrożnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdObrożnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozd oliwkowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdOliwkowy");
            intent.putExtra("Status", "DrozdOliwkowyStatus");
            intent.putExtra("Notatka", "DrozdOliwkowyNotatka");
            intent.putExtra("Złapany", "DrozdOliwkowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdOliwkowyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdOliwkowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdOliwkowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdOliwkowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozdoń pstry")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdońPstry");
            intent.putExtra("Status", "DrozdońPstryStatus");
            intent.putExtra("Notatka", "DrozdońPstryNotatka");
            intent.putExtra("Złapany", "DrozdońPstryZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdońPstryDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdońPstryMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdońPstryMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdońPstryZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozd rdzawogardły")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdRdzawogardły");
            intent.putExtra("Status", "DrozdRdzawogardłyStatus");
            intent.putExtra("Notatka", "DrozdRdzawogardłyNotatka");
            intent.putExtra("Złapany", "DrozdRdzawogardłyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdRdzawogardłyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdRdzawogardłyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdRdzawogardłyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdRdzawogardłyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozd rdzawoskrzydły")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdRdzawoskrzydły");
            intent.putExtra("Status", "DrozdRdzawoskrzydłyStatus");
            intent.putExtra("Notatka", "DrozdRdzawoskrzydłyNotatka");
            intent.putExtra("Złapany", "DrozdRdzawoskrzydłyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdRdzawoskrzydłyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdRdzawoskrzydłyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdRdzawoskrzydłyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdRdzawoskrzydłyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drozd rdzawy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DrozdRdzawy");
            intent.putExtra("Status", "DrozdRdzawyStatus");
            intent.putExtra("Notatka", "DrozdRdzawyNotatka");
            intent.putExtra("Złapany", "DrozdRdzawyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrozdRdzawyDataZłapania");
            intent.putExtra("Lokalizacja", "DrozdRdzawyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrozdRdzawyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrozdRdzawyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Droździk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Droździk");
            intent.putExtra("Status", "DroździkStatus");
            intent.putExtra("Notatka", "DroździkNotatka");
            intent.putExtra("Złapany", "DroździkZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DroździkDataZłapania");
            intent.putExtra("Lokalizacja", "DroździkMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DroździkMaZdjęcie");
            intent.putExtra("Zdjęcie", "DroździkZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Drzemlik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Drzemlik");
            intent.putExtra("Status", "DrzemlikStatus");
            intent.putExtra("Notatka", "DrzemlikNotatka");
            intent.putExtra("Złapany", "DrzemlikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DrzemlikDataZłapania");
            intent.putExtra("Lokalizacja", "DrzemlikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DrzemlikMaZdjęcie");
            intent.putExtra("Zdjęcie", "DrzemlikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dubelt")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dubelt");
            intent.putExtra("Status", "DubeltStatus");
            intent.putExtra("Notatka", "DubeltNotatka");
            intent.putExtra("Złapany", "DubeltZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DubeltDataZłapania");
            intent.putExtra("Lokalizacja", "DubeltMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DubeltMaZdjęcie");
            intent.putExtra("Zdjęcie", "DubeltZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dudek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dudek");
            intent.putExtra("Status", "DudekStatus");
            intent.putExtra("Notatka", "DudekNotatka");
            intent.putExtra("Złapany", "DudekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DudekDataZłapania");
            intent.putExtra("Lokalizacja", "DudekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DudekMaZdjęcie");
            intent.putExtra("Zdjęcie", "DudekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dymówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dymówka");
            intent.putExtra("Status", "DymówkaStatus");
            intent.putExtra("Notatka", "DymówkaNotatka");
            intent.putExtra("Złapany", "DymówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DymówkaDataZłapania");
            intent.putExtra("Lokalizacja", "DymówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DymówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DymówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł białogrzbiety")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołBiałogrzbiety");
            intent.putExtra("Status", "DzięciołBiałogrzbietyStatus");
            intent.putExtra("Notatka", "DzięciołBiałogrzbietyNotatka");
            intent.putExtra("Złapany", "DzięciołBiałogrzbietyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołBiałogrzbietyDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołBiałogrzbietyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołBiałogrzbietyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołBiałogrzbietyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł białoszyi")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołBiałoszyi");
            intent.putExtra("Status", "DzięciołBiałoszyiStatus");
            intent.putExtra("Notatka", "DzięciołBiałoszyiNotatka");
            intent.putExtra("Złapany", "DzięciołBiałoszyiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołBiałoszyiDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołBiałoszyiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołBiałoszyiMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołBiałoszyiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł czarny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołCzarny");
            intent.putExtra("Status", "DzięciołCzarnyStatus");
            intent.putExtra("Notatka", "DzięciołCzarnyNotatka");
            intent.putExtra("Złapany", "DzięciołCzarnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołCzarnyDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołCzarnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołCzarnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołCzarnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł duży")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołDuży");
            intent.putExtra("Status", "DzięciołDużyStatus");
            intent.putExtra("Notatka", "DzięciołDużyNotatka");
            intent.putExtra("Złapany", "DzięciołDużyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołDużyDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołDużyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołDużyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołDużyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięciołek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dzięciołek");
            intent.putExtra("Status", "DzięciołekStatus");
            intent.putExtra("Notatka", "DzięciołekNotatka");
            intent.putExtra("Złapany", "DzięciołekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołekDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołekMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł średni")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołŚredni");
            intent.putExtra("Status", "DzięciołŚredniStatus");
            intent.putExtra("Notatka", "DzięciołŚredniNotatka");
            intent.putExtra("Złapany", "DzięciołŚredniZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołŚredniDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołŚredniMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołŚredniMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołŚredniZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł trójpalczasty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołTrójpalczasty");
            intent.putExtra("Status", "DzięciołTrójpalczastyStatus");
            intent.putExtra("Notatka", "DzięciołTrójpalczastyNotatka");
            intent.putExtra("Złapany", "DzięciołTrójpalczastyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołTrójpalczastyDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołTrójpalczastyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołTrójpalczastyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołTrójpalczastyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł zielonosiwy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołZielonosiwy");
            intent.putExtra("Status", "DzięciołZielonosiwyStatus");
            intent.putExtra("Notatka", "DzięciołZielonosiwyNotatka");
            intent.putExtra("Złapany", "DzięciołZielonosiwyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołZielonosiwyDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołZielonosiwyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołZielonosiwyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołZielonosiwyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzięcioł zielony")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzięciołZielony");
            intent.putExtra("Status", "DzięciołZielonyStatus");
            intent.putExtra("Notatka", "DzięciołZielonyNotatka");
            intent.putExtra("Złapany", "DzięciołZielonyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzięciołZielonyDataZłapania");
            intent.putExtra("Lokalizacja", "DzięciołZielonyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzięciołZielonyMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzięciołZielonyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzierlatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dzierlatka");
            intent.putExtra("Status", "DzierlatkaStatus");
            intent.putExtra("Notatka", "DzierlatkaNotatka");
            intent.putExtra("Złapany", "DzierlatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzierlatkaDataZłapania");
            intent.putExtra("Lokalizacja", "DzierlatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzierlatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzierlatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzierzba czarnoczelna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzierzbaCzarnoczelna");
            intent.putExtra("Status", "DzierzbaCzarnoczelnaStatus");
            intent.putExtra("Notatka", "DzierzbaCzarnoczelnaNotatka");
            intent.putExtra("Złapany", "DzierzbaCzarnoczelnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzierzbaCzarnoczelnaDataZłapania");
            intent.putExtra("Lokalizacja", "DzierzbaCzarnoczelnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzierzbaCzarnoczelnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzierzbaCzarnoczelnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzierzba pustynna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzierzbaPustynna");
            intent.putExtra("Status", "DzierzbaPustynnaStatus");
            intent.putExtra("Notatka", "DzierzbaPustynnaNotatka");
            intent.putExtra("Złapany", "DzierzbaPustynnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzierzbaPustynnaDataZłapania");
            intent.putExtra("Lokalizacja", "DzierzbaPustynnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzierzbaPustynnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzierzbaPustynnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzierzba rdzawosterna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzierzbaRdzawosterna");
            intent.putExtra("Status", "DzierzbaRdzawosternaStatus");
            intent.putExtra("Notatka", "DzierzbaRdzawosternaNotatka");
            intent.putExtra("Złapany", "DzierzbaRdzawosternaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzierzbaRdzawosternaDataZłapania");
            intent.putExtra("Lokalizacja", "DzierzbaRdzawosternaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzierzbaRdzawosternaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzierzbaRdzawosternaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzierzba rudogłowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "DzierzbaRudogłowa");
            intent.putExtra("Status", "DzierzbaRudogłowaStatus");
            intent.putExtra("Notatka", "DzierzbaRudogłowaNotatka");
            intent.putExtra("Złapany", "DzierzbaRudogłowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzierzbaRudogłowaDataZłapania");
            intent.putExtra("Lokalizacja", "DzierzbaRudogłowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzierzbaRudogłowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzierzbaRudogłowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dziwonia")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dziwonia");
            intent.putExtra("Status", "DziwoniaStatus");
            intent.putExtra("Notatka", "DziwoniaNotatka");
            intent.putExtra("Złapany", "DziwoniaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DziwoniaDataZłapania");
            intent.putExtra("Lokalizacja", "DziwoniaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DziwoniaMaZdjęcie");
            intent.putExtra("Zdjęcie", "DziwoniaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Dzwoniec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Dzwoniec");
            intent.putExtra("Status", "DzwoniecStatus");
            intent.putExtra("Notatka", "DzwoniecNotatka");
            intent.putExtra("Złapany", "DzwoniecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "DzwoniecDataZłapania");
            intent.putExtra("Lokalizacja", "DzwoniecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "DzwoniecMaZdjęcie");
            intent.putExtra("Zdjęcie", "DzwoniecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Edredon")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Edredon");
            intent.putExtra("Status", "EdredonStatus");
            intent.putExtra("Notatka", "EdredonNotatka");
            intent.putExtra("Złapany", "EdredonZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "EdredonDataZłapania");
            intent.putExtra("Lokalizacja", "EdredonMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "EdredonMaZdjęcie");
            intent.putExtra("Zdjęcie", "EdredonZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Flaming różowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "FlamingRóżowy");
            intent.putExtra("Status", "FlamingRóżowyStatus");
            intent.putExtra("Notatka", "FlamingRóżowyNotatka");
            intent.putExtra("Złapany", "FlamingRóżowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "FlamingRóżowyDataZłapania");
            intent.putExtra("Lokalizacja", "FlamingRóżowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "FlamingRóżowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "FlamingRóżowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Fulmar")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Fulmar");
            intent.putExtra("Status", "FulmarStatus");
            intent.putExtra("Notatka", "FulmarNotatka");
            intent.putExtra("Złapany", "FulmarZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "FulmarDataZłapania");
            intent.putExtra("Lokalizacja", "FulmarMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "FulmarMaZdjęcie");
            intent.putExtra("Zdjęcie", "FulmarZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gadożer")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gadożer");
            intent.putExtra("Status", "GadożerStatus");
            intent.putExtra("Notatka", "GadożerNotatka");
            intent.putExtra("Złapany", "GadożerZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GadożerDataZłapania");
            intent.putExtra("Lokalizacja", "GadożerMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GadożerMaZdjęcie");
            intent.putExtra("Zdjęcie", "GadożerZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gawron")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gawron");
            intent.putExtra("Status", "GawronStatus");
            intent.putExtra("Notatka", "GawronNotatka");
            intent.putExtra("Złapany", "GawronZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GawronDataZłapania");
            intent.putExtra("Lokalizacja", "GawronMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GawronMaZdjęcie");
            intent.putExtra("Zdjęcie", "GawronZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gągoł")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gągoł");
            intent.putExtra("Status", "GągołStatus");
            intent.putExtra("Notatka", "GągołNotatka");
            intent.putExtra("Złapany", "GągołZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GągołDataZłapania");
            intent.putExtra("Lokalizacja", "GągołMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GągołMaZdjęcie");
            intent.putExtra("Zdjęcie", "GągołZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gajówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gajówka");
            intent.putExtra("Status", "GajówkaStatus");
            intent.putExtra("Notatka", "GajówkaNotatka");
            intent.putExtra("Złapany", "GajówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GajówkaDataZłapania");
            intent.putExtra("Lokalizacja", "GajówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GajówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GajówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gąsiorek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gąsiorek");
            intent.putExtra("Status", "GąsiorekStatus");
            intent.putExtra("Notatka", "GąsiorekNotatka");
            intent.putExtra("Złapany", "GąsiorekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GąsiorekDataZłapania");
            intent.putExtra("Lokalizacja", "GąsiorekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GąsiorekMaZdjęcie");
            intent.putExtra("Zdjęcie", "GąsiorekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gęgawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gęgawa");
            intent.putExtra("Status", "GęgawaStatus");
            intent.putExtra("Notatka", "GęgawaNotatka");
            intent.putExtra("Złapany", "GęgawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GęgawaDataZłapania");
            intent.putExtra("Lokalizacja", "GęgawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GęgawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GęgawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gęś białoczelna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "GęśBiałoczelna");
            intent.putExtra("Status", "GęśBiałoczelnaStatus");
            intent.putExtra("Notatka", "GęśBiałoczelnaNotatka");
            intent.putExtra("Złapany", "GęśBiałoczelnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GęśBiałoczelnaDataZłapania");
            intent.putExtra("Lokalizacja", "GęśBiałoczelnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GęśBiałoczelnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GęśBiałoczelnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gęsiówka egipska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "GęsiówkaEgipska");
            intent.putExtra("Status", "GęsiówkaEgipskaStatus");
            intent.putExtra("Notatka", "GęsiówkaEgipskaNotatka");
            intent.putExtra("Złapany", "GęsiówkaEgipskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GęsiówkaEgipskaDataZłapania");
            intent.putExtra("Lokalizacja", "GęsiówkaEgipskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GęsiówkaEgipskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GęsiówkaEgipskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gęś krótkodzioba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "GęśKrótkodzioba");
            intent.putExtra("Status", "GęśKrótkodziobaStatus");
            intent.putExtra("Notatka", "GęśKrótkodziobaNotatka");
            intent.putExtra("Złapany", "GęśKrótkodziobaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GęśKrótkodziobaDataZłapania");
            intent.putExtra("Lokalizacja", "GęśKrótkodziobaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GęśKrótkodziobaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GęśKrótkodziobaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gęś mała")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "GęśMała");
            intent.putExtra("Status", "GęśMałaStatus");
            intent.putExtra("Notatka", "GęśMałaNotatka");
            intent.putExtra("Złapany", "GęśMałaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GęśMałaDataZłapania");
            intent.putExtra("Lokalizacja", "GęśMałaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GęśMałaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GęśMałaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gęś zbożowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "GęśZbożowa");
            intent.putExtra("Status", "GęśZbożowaStatus");
            intent.putExtra("Notatka", "GęśZbożowaNotatka");
            intent.putExtra("Złapany", "GęśZbożowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GęśZbożowaDataZłapania");
            intent.putExtra("Lokalizacja", "GęśZbożowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GęśZbożowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GęśZbożowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gil")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Gil");
            intent.putExtra("Status", "GilStatus");
            intent.putExtra("Notatka", "GilNotatka");
            intent.putExtra("Złapany", "GilZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GilDataZłapania");
            intent.putExtra("Lokalizacja", "GilMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GilMaZdjęcie");
            intent.putExtra("Zdjęcie", "GilZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Głowienka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Głowienka");
            intent.putExtra("Status", "GłowienkaStatus");
            intent.putExtra("Notatka", "GłowienkaNotatka");
            intent.putExtra("Złapany", "GłowienkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GłowienkaDataZłapania");
            intent.putExtra("Lokalizacja", "GłowienkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GłowienkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "GłowienkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Głuptak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Głuptak");
            intent.putExtra("Status", "GłuptakStatus");
            intent.putExtra("Notatka", "GłuptakNotatka");
            intent.putExtra("Złapany", "GłuptakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GłuptakDataZłapania");
            intent.putExtra("Lokalizacja", "GłuptakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GłuptakMaZdjęcie");
            intent.putExtra("Zdjęcie", "GłuptakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Głuszec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Głuszec");
            intent.putExtra("Status", "GłuszecStatus");
            intent.putExtra("Notatka", "GłuszecNotatka");
            intent.putExtra("Złapany", "GłuszecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GłuszecDataZłapania");
            intent.putExtra("Lokalizacja", "GłuszecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GłuszecMaZdjęcie");
            intent.putExtra("Zdjęcie", "GłuszecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Głuszek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Głuszek");
            intent.putExtra("Status", "GłuszekStatus");
            intent.putExtra("Notatka", "GłuszekNotatka");
            intent.putExtra("Złapany", "GłuszekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GłuszekDataZłapania");
            intent.putExtra("Lokalizacja", "GłuszekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GłuszekMaZdjęcie");
            intent.putExtra("Zdjęcie", "GłuszekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Gołąb miejski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "GołąbMiejski");
            intent.putExtra("Status", "GołąbMiejskiStatus");
            intent.putExtra("Notatka", "GołąbMiejskiNotatka");
            intent.putExtra("Złapany", "GołąbMiejskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GołąbMiejskiDataZłapania");
            intent.putExtra("Lokalizacja", "GołąbMiejskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GołąbMiejskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "GołąbMiejskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Górniczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Górniczek");
            intent.putExtra("Status", "GórniczekStatus");
            intent.putExtra("Notatka", "GórniczekNotatka");
            intent.putExtra("Złapany", "GórniczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GórniczekDataZłapania");
            intent.putExtra("Lokalizacja", "GórniczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GórniczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "GórniczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Grubodziób")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Grubodziób");
            intent.putExtra("Status", "GrubodzióbStatus");
            intent.putExtra("Notatka", "GrubodzióbNotatka");
            intent.putExtra("Złapany", "GrubodzióbZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GrubodzióbDataZłapania");
            intent.putExtra("Lokalizacja", "GrubodzióbMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GrubodzióbMaZdjęcie");
            intent.putExtra("Zdjęcie", "GrubodzióbZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Grzywacz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Grzywacz");
            intent.putExtra("Status", "GrzywaczStatus");
            intent.putExtra("Notatka", "GrzywaczNotatka");
            intent.putExtra("Złapany", "GrzywaczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "GrzywaczDataZłapania");
            intent.putExtra("Lokalizacja", "GrzywaczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "GrzywaczMaZdjęcie");
            intent.putExtra("Zdjęcie", "GrzywaczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Hełmiatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Hełmiatka");
            intent.putExtra("Status", "HełmiatkaStatus");
            intent.putExtra("Notatka", "HełmiatkaNotatka");
            intent.putExtra("Złapany", "HełmiatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "HełmiatkaDataZłapania");
            intent.putExtra("Lokalizacja", "HełmiatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "HełmiatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "HełmiatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Hubara arabska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "HubaraArabska");
            intent.putExtra("Status", "HubaraArabskaStatus");
            intent.putExtra("Notatka", "HubaraArabskaNotatka");
            intent.putExtra("Złapany", "HubaraArabskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "HubaraArabskaDataZłapania");
            intent.putExtra("Lokalizacja", "HubaraArabskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "HubaraArabskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "HubaraArabskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ibis kasztanowaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "IbisKasztanowaty");
            intent.putExtra("Status", "IbisKasztanowatyStatus");
            intent.putExtra("Notatka", "IbisKasztanowatyNotatka");
            intent.putExtra("Złapany", "IbisKasztanowatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "IbisKasztanowatyDataZłapania");
            intent.putExtra("Lokalizacja", "IbisKasztanowatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "IbisKasztanowatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "IbisKasztanowatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jarząbek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Jarząbek");
            intent.putExtra("Status", "JarząbekStatus");
            intent.putExtra("Notatka", "JarząbekNotatka");
            intent.putExtra("Złapany", "JarząbekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JarząbekDataZłapania");
            intent.putExtra("Lokalizacja", "JarząbekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JarząbekMaZdjęcie");
            intent.putExtra("Zdjęcie", "JarząbekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jarzębatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Jarzębatka");
            intent.putExtra("Status", "JarzębatkaStatus");
            intent.putExtra("Notatka", "JarzębatkaNotatka");
            intent.putExtra("Złapany", "JarzębatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JarzębatkaDataZłapania");
            intent.putExtra("Lokalizacja", "JarzębatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JarzębatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "JarzębatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jaskółka rudawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "JaskółkaRudawa");
            intent.putExtra("Status", "JaskółkaRudawaStatus");
            intent.putExtra("Notatka", "JaskółkaRudawaNotatka");
            intent.putExtra("Złapany", "JaskółkaRudawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JaskółkaRudawaDataZłapania");
            intent.putExtra("Lokalizacja", "JaskółkaRudawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JaskółkaRudawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "JaskółkaRudawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jastrząb")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Jastrząb");
            intent.putExtra("Status", "JastrząbStatus");
            intent.putExtra("Notatka", "JastrząbNotatka");
            intent.putExtra("Złapany", "JastrząbZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JastrząbDataZłapania");
            intent.putExtra("Lokalizacja", "JastrząbMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JastrząbMaZdjęcie");
            intent.putExtra("Zdjęcie", "JastrząbZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jemiołuszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Jemiołuszka");
            intent.putExtra("Status", "JemiołuszkaStatus");
            intent.putExtra("Notatka", "JemiołuszkaNotatka");
            intent.putExtra("Złapany", "JemiołuszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JemiołuszkaDataZłapania");
            intent.putExtra("Lokalizacja", "JemiołuszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JemiołuszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "JemiołuszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jer")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Jer");
            intent.putExtra("Status", "JerStatus");
            intent.putExtra("Notatka", "JerNotatka");
            intent.putExtra("Złapany", "JerZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JerDataZłapania");
            intent.putExtra("Lokalizacja", "JerMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JerMaZdjęcie");
            intent.putExtra("Zdjęcie", "JerZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jerzyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Jerzyk");
            intent.putExtra("Status", "JerzykStatus");
            intent.putExtra("Notatka", "JerzykNotatka");
            intent.putExtra("Złapany", "JerzykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JerzykDataZłapania");
            intent.putExtra("Lokalizacja", "JerzykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JerzykMaZdjęcie");
            intent.putExtra("Zdjęcie", "JerzykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jerzyk alpejski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "JerzykAlpejski");
            intent.putExtra("Status", "JerzykAlpejskiStatus");
            intent.putExtra("Notatka", "JerzykAlpejskiNotatka");
            intent.putExtra("Złapany", "JerzykAlpejskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JerzykAlpejskiDataZłapania");
            intent.putExtra("Lokalizacja", "JerzykAlpejskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JerzykAlpejskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "JerzykAlpejskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Jerzyk blady")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "JerzykBlady");
            intent.putExtra("Status", "JerzykBladyStatus");
            intent.putExtra("Notatka", "JerzykBladyNotatka");
            intent.putExtra("Złapany", "JerzykBladyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JerzykBladyDataZłapania");
            intent.putExtra("Lokalizacja", "JerzykBladyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JerzykBladyMaZdjęcie");
            intent.putExtra("Zdjęcie", "JerzykBladyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Junko")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Junko");
            intent.putExtra("Status", "JunkoStatus");
            intent.putExtra("Notatka", "JunkoNotatka");
            intent.putExtra("Złapany", "JunkoZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "JunkoDataZłapania");
            intent.putExtra("Lokalizacja", "JunkoMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "JunkoMaZdjęcie");
            intent.putExtra("Zdjęcie", "JunkoZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kalandra czarna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KalandraCzarna");
            intent.putExtra("Status", "KalandraCzarnaStatus");
            intent.putExtra("Notatka", "KalandraCzarnaNotatka");
            intent.putExtra("Złapany", "KalandraCzarnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KalandraCzarnaDataZłapania");
            intent.putExtra("Lokalizacja", "KalandraCzarnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KalandraCzarnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KalandraCzarnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kalandra szara")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KalandraSzara");
            intent.putExtra("Status", "KalandraSzaraStatus");
            intent.putExtra("Notatka", "KalandraSzaraNotatka");
            intent.putExtra("Złapany", "KalandraSzaraZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KalandraSzaraDataZłapania");
            intent.putExtra("Lokalizacja", "KalandraSzaraMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KalandraSzaraMaZdjęcie");
            intent.putExtra("Zdjęcie", "KalandraSzaraZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kamieniuszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kamieniuszka");
            intent.putExtra("Status", "KamieniuszkaStatus");
            intent.putExtra("Notatka", "KamieniuszkaNotatka");
            intent.putExtra("Złapany", "KamieniuszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KamieniuszkaDataZłapania");
            intent.putExtra("Lokalizacja", "KamieniuszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KamieniuszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KamieniuszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kamuszik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kamuszik");
            intent.putExtra("Status", "KamuszikStatus");
            intent.putExtra("Notatka", "KamuszikNotatka");
            intent.putExtra("Złapany", "KamuszikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KamuszikDataZłapania");
            intent.putExtra("Lokalizacja", "KamuszikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KamuszikMaZdjęcie");
            intent.putExtra("Zdjęcie", "KamuszikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kania czarna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KaniaCzarna");
            intent.putExtra("Status", "KaniaCzarnaStatus");
            intent.putExtra("Notatka", "KaniaCzarnaNotatka");
            intent.putExtra("Złapany", "KaniaCzarnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KaniaCzarnaDataZłapania");
            intent.putExtra("Lokalizacja", "KaniaCzarnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KaniaCzarnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KaniaCzarnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kania ruda")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KaniaRuda");
            intent.putExtra("Status", "KaniaRudaStatus");
            intent.putExtra("Notatka", "KaniaRudaNotatka");
            intent.putExtra("Złapany", "KaniaRudaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KaniaRudaDataZłapania");
            intent.putExtra("Lokalizacja", "KaniaRudaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KaniaRudaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KaniaRudaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kapturka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kapturka");
            intent.putExtra("Status", "KapturkaStatus");
            intent.putExtra("Notatka", "KapturkaNotatka");
            intent.putExtra("Złapany", "KapturkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KapturkaDataZłapania");
            intent.putExtra("Lokalizacja", "KapturkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KapturkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KapturkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Karliczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Karliczka");
            intent.putExtra("Status", "KarliczkaStatus");
            intent.putExtra("Notatka", "KarliczkaNotatka");
            intent.putExtra("Złapany", "KarliczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KarliczkaDataZłapania");
            intent.putExtra("Lokalizacja", "KarliczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KarliczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KarliczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kawka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kawka");
            intent.putExtra("Status", "KawkaStatus");
            intent.putExtra("Notatka", "KawkaNotatka");
            intent.putExtra("Złapany", "KawkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KawkaDataZłapania");
            intent.putExtra("Lokalizacja", "KawkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KawkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KawkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kazarka rdzawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KazarkaRdzawa");
            intent.putExtra("Status", "KazarkaRdzawaStatus");
            intent.putExtra("Notatka", "KazarkaRdzawaNotatka");
            intent.putExtra("Złapany", "KazarkaRdzawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KazarkaRdzawaDataZłapania");
            intent.putExtra("Lokalizacja", "KazarkaRdzawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KazarkaRdzawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KazarkaRdzawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kląskawka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kląskawka");
            intent.putExtra("Status", "KląskawkaStatus");
            intent.putExtra("Notatka", "KląskawkaNotatka");
            intent.putExtra("Złapany", "KląskawkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KląskawkaDataZłapania");
            intent.putExtra("Lokalizacja", "KląskawkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KląskawkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KląskawkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kobczyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kobczyk");
            intent.putExtra("Status", "KobczykStatus");
            intent.putExtra("Notatka", "KobczykNotatka");
            intent.putExtra("Złapany", "KobczykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KobczykDataZłapania");
            intent.putExtra("Lokalizacja", "KobczykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KobczykMaZdjęcie");
            intent.putExtra("Zdjęcie", "KobczykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kobuz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kobuz");
            intent.putExtra("Status", "KobuzStatus");
            intent.putExtra("Notatka", "KobuzNotatka");
            intent.putExtra("Złapany", "KobuzZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KobuzDataZłapania");
            intent.putExtra("Lokalizacja", "KobuzMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KobuzMaZdjęcie");
            intent.putExtra("Zdjęcie", "KobuzZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kokoszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kokoszka");
            intent.putExtra("Status", "KokoszkaStatus");
            intent.putExtra("Notatka", "KokoszkaNotatka");
            intent.putExtra("Złapany", "KokoszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KokoszkaDataZłapania");
            intent.putExtra("Lokalizacja", "KokoszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KokoszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KokoszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kopciuszek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kopciuszek");
            intent.putExtra("Status", "KopciuszekStatus");
            intent.putExtra("Notatka", "KopciuszekNotatka");
            intent.putExtra("Złapany", "KopciuszekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KopciuszekDataZłapania");
            intent.putExtra("Lokalizacja", "KopciuszekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KopciuszekMaZdjęcie");
            intent.putExtra("Zdjęcie", "KopciuszekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kormoran")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kormoran");
            intent.putExtra("Status", "KormoranStatus");
            intent.putExtra("Notatka", "KormoranNotatka");
            intent.putExtra("Złapany", "KormoranZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KormoranDataZłapania");
            intent.putExtra("Lokalizacja", "KormoranMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KormoranMaZdjęcie");
            intent.putExtra("Zdjęcie", "KormoranZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kormoran czubaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KormoranCzubaty");
            intent.putExtra("Status", "KormoranCzubatyStatus");
            intent.putExtra("Notatka", "KormoranCzubatyNotatka");
            intent.putExtra("Złapany", "KormoranCzubatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KormoranCzubatyDataZłapania");
            intent.putExtra("Lokalizacja", "KormoranCzubatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KormoranCzubatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KormoranCzubatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kormoran mały")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KormoranMały");
            intent.putExtra("Status", "KormoranMałyStatus");
            intent.putExtra("Notatka", "KormoranMałyNotatka");
            intent.putExtra("Złapany", "KormoranMałyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KormoranMałyDataZłapania");
            intent.putExtra("Lokalizacja", "KormoranMałyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KormoranMałyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KormoranMałyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kos")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kos");
            intent.putExtra("Status", "KosStatus");
            intent.putExtra("Notatka", "KosNotatka");
            intent.putExtra("Złapany", "KosZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KosDataZłapania");
            intent.putExtra("Lokalizacja", "KosMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KosMaZdjęcie");
            intent.putExtra("Zdjęcie", "KosZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kowalik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kowalik");
            intent.putExtra("Status", "KowalikStatus");
            intent.putExtra("Notatka", "KowalikNotatka");
            intent.putExtra("Złapany", "KowalikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KowalikDataZłapania");
            intent.putExtra("Lokalizacja", "KowalikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KowalikMaZdjęcie");
            intent.putExtra("Zdjęcie", "KowalikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krawka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Krawka");
            intent.putExtra("Status", "KrawkaStatus");
            intent.putExtra("Notatka", "KrawkaNotatka");
            intent.putExtra("Złapany", "KrawkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrawkaDataZłapania");
            intent.putExtra("Lokalizacja", "KrawkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrawkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrawkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kraska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kraska");
            intent.putExtra("Status", "KraskaStatus");
            intent.putExtra("Notatka", "KraskaNotatka");
            intent.putExtra("Złapany", "KraskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KraskaDataZłapania");
            intent.putExtra("Lokalizacja", "KraskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KraskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KraskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krętogłów")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Krętogłów");
            intent.putExtra("Status", "KrętogłówStatus");
            intent.putExtra("Notatka", "KrętogłówNotatka");
            intent.putExtra("Złapany", "KrętogłówZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrętogłówDataZłapania");
            intent.putExtra("Lokalizacja", "KrętogłówMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrętogłówMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrętogłówZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krogulec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Krogulec");
            intent.putExtra("Status", "KrogulecStatus");
            intent.putExtra("Notatka", "KrogulecNotatka");
            intent.putExtra("Złapany", "KrogulecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrogulecDataZłapania");
            intent.putExtra("Lokalizacja", "KrogulecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrogulecMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrogulecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krogulec krótkonogi")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KrogulecKrótkonogi");
            intent.putExtra("Status", "KrogulecKrótkonogiStatus");
            intent.putExtra("Notatka", "KrogulecKrótkonogiNotatka");
            intent.putExtra("Złapany", "KrogulecKrótkonogiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrogulecKrótkonogiDataZłapania");
            intent.putExtra("Lokalizacja", "KrogulecKrótkonogiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrogulecKrótkonogiMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrogulecKrótkonogiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kropiatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kropiatka");
            intent.putExtra("Status", "KropiatkaStatus");
            intent.putExtra("Notatka", "KropiatkaNotatka");
            intent.putExtra("Złapany", "KropiatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KropiatkaDataZłapania");
            intent.putExtra("Lokalizacja", "KropiatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KropiatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KropiatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kruk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kruk");
            intent.putExtra("Status", "KrukStatus");
            intent.putExtra("Notatka", "KrukNotatka");
            intent.putExtra("Złapany", "KrukZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrukDataZłapania");
            intent.putExtra("Lokalizacja", "KrukMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrukMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrukZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krwawodziób")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Krwawodziób");
            intent.putExtra("Status", "KrwawodzióbStatus");
            intent.putExtra("Notatka", "KrwawodzióbNotatka");
            intent.putExtra("Złapany", "KrwawodzióbZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrwawodzióbDataZłapania");
            intent.putExtra("Lokalizacja", "KrwawodzióbMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrwawodzióbMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrwawodzióbZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krzyżodziób")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Krzyżodziób");
            intent.putExtra("Status", "KrzyżodzióbStatus");
            intent.putExtra("Notatka", "KrzyżodzióbNotatka");
            intent.putExtra("Złapany", "KrzyżodzióbZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrzyżodzióbDataZłapania");
            intent.putExtra("Lokalizacja", "KrzyżodzióbMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrzyżodzióbMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrzyżodzióbZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krzyżodziób modrzewiowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KrzyżodzióbModrzewiowy");
            intent.putExtra("Status", "KrzyżodzióbModrzewiowyStatus");
            intent.putExtra("Notatka", "KrzyżodzióbModrzewiowyNotatka");
            intent.putExtra("Złapany", "KrzyżodzióbModrzewiowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrzyżodzióbModrzewiowyDataZłapania");
            intent.putExtra("Lokalizacja", "KrzyżodzióbModrzewiowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrzyżodzióbModrzewiowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrzyżodzióbModrzewiowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krzyżodziób sosnowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KrzyżodzióbSosnowy");
            intent.putExtra("Status", "KrzyżodzióbSosnowyStatus");
            intent.putExtra("Notatka", "KrzyżodzióbSosnowyNotatka");
            intent.putExtra("Złapany", "KrzyżodzióbSosnowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrzyżodzióbSosnowyDataZłapania");
            intent.putExtra("Lokalizacja", "KrzyżodzióbSosnowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrzyżodzióbSosnowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrzyżodzióbSosnowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krzyżodziób świerkowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KrzyżodzióbŚwierkowy");
            intent.putExtra("Status", "KrzyżodzióbŚwierkowyStatus");
            intent.putExtra("Notatka", "KrzyżodzióbŚwierkowyNotatka");
            intent.putExtra("Złapany", "KrzyżodzióbŚwierkowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrzyżodzióbŚwierkowyDataZłapania");
            intent.putExtra("Lokalizacja", "KrzyżodzióbŚwierkowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrzyżodzióbŚwierkowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrzyżodzióbŚwierkowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Krzyżówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Krzyżówka");
            intent.putExtra("Status", "KrzyżówkaStatus");
            intent.putExtra("Notatka", "KrzyżówkaNotatka");
            intent.putExtra("Złapany", "KrzyżówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KrzyżówkaDataZłapania");
            intent.putExtra("Lokalizacja", "KrzyżówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KrzyżówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KrzyżówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kszyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kszyk");
            intent.putExtra("Status", "KszykStatus");
            intent.putExtra("Notatka", "KszykNotatka");
            intent.putExtra("Złapany", "KszykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KszykDataZłapania");
            intent.putExtra("Lokalizacja", "KszykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KszykMaZdjęcie");
            intent.putExtra("Zdjęcie", "KszykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kukułka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kukułka");
            intent.putExtra("Status", "KukułkaStatus");
            intent.putExtra("Notatka", "KukułkaNotatka");
            intent.putExtra("Złapany", "KukułkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KukułkaDataZłapania");
            intent.putExtra("Lokalizacja", "KukułkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KukułkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KukułkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kulczyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kulczyk");
            intent.putExtra("Status", "KulczykStatus");
            intent.putExtra("Notatka", "KulczykNotatka");
            intent.putExtra("Złapany", "KulczykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KulczykDataZłapania");
            intent.putExtra("Lokalizacja", "KulczykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KulczykMaZdjęcie");
            intent.putExtra("Zdjęcie", "KulczykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kulik cienkodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KulikCienkodzioby");
            intent.putExtra("Status", "KulikCienkodziobyStatus");
            intent.putExtra("Notatka", "KulikCienkodziobyNotatka");
            intent.putExtra("Złapany", "KulikCienkodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KulikCienkodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "KulikCienkodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KulikCienkodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KulikCienkodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kulik mniejszy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KulikMniejszy");
            intent.putExtra("Status", "KulikMniejszyStatus");
            intent.putExtra("Notatka", "KulikMniejszyNotatka");
            intent.putExtra("Złapany", "KulikMniejszyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KulikMniejszyDataZłapania");
            intent.putExtra("Lokalizacja", "KulikMniejszyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KulikMniejszyMaZdjęcie");
            intent.putExtra("Zdjęcie", "KulikMniejszyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kulik wielki")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "KulikWielki");
            intent.putExtra("Status", "KulikWielkiStatus");
            intent.putExtra("Notatka", "KulikWielkiNotatka");
            intent.putExtra("Złapany", "KulikWielkiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KulikWielkiDataZłapania");
            intent.putExtra("Lokalizacja", "KulikWielkiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KulikWielkiMaZdjęcie");
            intent.putExtra("Zdjęcie", "KulikWielkiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kulon")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kulon");
            intent.putExtra("Status", "KulonStatus");
            intent.putExtra("Notatka", "KulonNotatka");
            intent.putExtra("Złapany", "KulonZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KulonDataZłapania");
            intent.putExtra("Lokalizacja", "KulonMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KulonMaZdjęcie");
            intent.putExtra("Zdjęcie", "KulonZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kurhannik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kurhannik");
            intent.putExtra("Status", "KurhannikStatus");
            intent.putExtra("Notatka", "KurhannikNotatka");
            intent.putExtra("Złapany", "KurhannikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KurhannikDataZłapania");
            intent.putExtra("Lokalizacja", "KurhannikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KurhannikMaZdjęcie");
            intent.putExtra("Zdjęcie", "KurhannikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kuropatwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kuropatwa");
            intent.putExtra("Status", "KuropatwaStatus");
            intent.putExtra("Notatka", "KuropatwaNotatka");
            intent.putExtra("Złapany", "KuropatwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KuropatwaDataZłapania");
            intent.putExtra("Lokalizacja", "KuropatwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KuropatwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "KuropatwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kwiczoł")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kwiczoł");
            intent.putExtra("Status", "KwiczołStatus");
            intent.putExtra("Notatka", "KwiczołNotatka");
            intent.putExtra("Złapany", "KwiczołZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KwiczołDataZłapania");
            intent.putExtra("Lokalizacja", "KwiczołMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KwiczołMaZdjęcie");
            intent.putExtra("Zdjęcie", "KwiczołZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Kwokacz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Kwokacz");
            intent.putExtra("Status", "KwokaczStatus");
            intent.putExtra("Notatka", "KwokaczNotatka");
            intent.putExtra("Złapany", "KwokaczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "KwokaczDataZłapania");
            intent.putExtra("Lokalizacja", "KwokaczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "KwokaczMaZdjęcie");
            intent.putExtra("Zdjęcie", "KwokaczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Lelek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Lelek");
            intent.putExtra("Status", "LelekStatus");
            intent.putExtra("Notatka", "LelekNotatka");
            intent.putExtra("Złapany", "LelekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "LelekDataZłapania");
            intent.putExtra("Lokalizacja", "LelekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "LelekMaZdjęcie");
            intent.putExtra("Zdjęcie", "LelekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Lerka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Lerka");
            intent.putExtra("Status", "LerkaStatus");
            intent.putExtra("Notatka", "LerkaNotatka");
            intent.putExtra("Złapany", "LerkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "LerkaDataZłapania");
            intent.putExtra("Lokalizacja", "LerkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "LerkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "LerkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Lodowiec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Lodowiec");
            intent.putExtra("Status", "LodowiecStatus");
            intent.putExtra("Notatka", "LodowiecNotatka");
            intent.putExtra("Złapany", "LodowiecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "LodowiecDataZłapania");
            intent.putExtra("Lokalizacja", "LodowiecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "LodowiecMaZdjęcie");
            intent.putExtra("Zdjęcie", "LodowiecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Lodówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Lodówka");
            intent.putExtra("Status", "LodówkaStatus");
            intent.putExtra("Notatka", "LodówkaNotatka");
            intent.putExtra("Złapany", "LodówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "LodówkaDataZłapania");
            intent.putExtra("Lokalizacja", "LodówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "LodówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "LodówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łabędź czarnodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŁabędźCzarnodzioby");
            intent.putExtra("Status", "ŁabędźCzarnodziobyStatus");
            intent.putExtra("Notatka", "ŁabędźCzarnodziobyNotatka");
            intent.putExtra("Złapany", "ŁabędźCzarnodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁabędźCzarnodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "ŁabędźCzarnodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁabędźCzarnodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁabędźCzarnodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łabędź krzykliwy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŁabędźKrzykliwy");
            intent.putExtra("Status", "ŁabędźKrzykliwyStatus");
            intent.putExtra("Notatka", "ŁabędźKrzykliwyNotatka");
            intent.putExtra("Złapany", "ŁabędźKrzykliwyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁabędźKrzykliwyDataZłapania");
            intent.putExtra("Lokalizacja", "ŁabędźKrzykliwyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁabędźKrzykliwyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁabędźKrzykliwyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łabędź niemy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŁabędźNiemy");
            intent.putExtra("Status", "ŁabędźNiemyStatus");
            intent.putExtra("Notatka", "ŁabędźNiemyNotatka");
            intent.putExtra("Złapany", "ŁabędźNiemyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁabędźNiemyDataZłapania");
            intent.putExtra("Lokalizacja", "ŁabędźNiemyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁabędźNiemyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁabędźNiemyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łęczak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Łęczak");
            intent.putExtra("Status", "ŁęczakStatus");
            intent.putExtra("Notatka", "ŁęczakNotatka");
            intent.putExtra("Złapany", "ŁęczakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁęczakDataZłapania");
            intent.putExtra("Lokalizacja", "ŁęczakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁęczakMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁęczakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łozówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Łozówka");
            intent.putExtra("Status", "ŁozówkaStatus");
            intent.putExtra("Notatka", "ŁozówkaNotatka");
            intent.putExtra("Złapany", "ŁozówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁozówkaDataZłapania");
            intent.putExtra("Lokalizacja", "ŁozówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁozówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁozówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łuskowiec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Łuskowiec");
            intent.putExtra("Status", "ŁuskowiecStatus");
            intent.putExtra("Notatka", "ŁuskowiecNotatka");
            intent.putExtra("Złapany", "ŁuskowiecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁuskowiecDataZłapania");
            intent.putExtra("Lokalizacja", "ŁuskowiecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁuskowiecMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁuskowiecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Łyska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Łyska");
            intent.putExtra("Status", "ŁyskaStatus");
            intent.putExtra("Notatka", "ŁyskaNotatka");
            intent.putExtra("Złapany", "ŁyskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŁyskaDataZłapania");
            intent.putExtra("Lokalizacja", "ŁyskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŁyskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŁyskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Makolągwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Makolągwa");
            intent.putExtra("Status", "MakolągwaStatus");
            intent.putExtra("Notatka", "MakolągwaNotatka");
            intent.putExtra("Złapany", "MakolągwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MakolągwaDataZłapania");
            intent.putExtra("Lokalizacja", "MakolągwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MakolągwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MakolągwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mandarynka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Mandarynka");
            intent.putExtra("Status", "MandarynkaStatus");
            intent.putExtra("Notatka", "MandarynkaNotatka");
            intent.putExtra("Złapany", "MandarynkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MandarynkaDataZłapania");
            intent.putExtra("Lokalizacja", "MandarynkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MandarynkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MandarynkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Markaczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Markaczka");
            intent.putExtra("Status", "MarkaczkaStatus");
            intent.putExtra("Notatka", "MarkaczkaNotatka");
            intent.putExtra("Złapany", "MarkaczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MarkaczkaDataZłapania");
            intent.putExtra("Lokalizacja", "MarkaczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MarkaczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MarkaczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Markaczka amerykańska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MarkaczkaAmerykańska");
            intent.putExtra("Status", "MarkaczkaAmerykańskaStatus");
            intent.putExtra("Notatka", "MarkaczkaAmerykańskaNotatka");
            intent.putExtra("Złapany", "MarkaczkaAmerykańskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MarkaczkaAmerykańskaDataZłapania");
            intent.putExtra("Lokalizacja", "MarkaczkaAmerykańskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MarkaczkaAmerykańskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MarkaczkaAmerykańskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Maskonur")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Maskonur");
            intent.putExtra("Status", "MaskonurStatus");
            intent.putExtra("Notatka", "MaskonurNotatka");
            intent.putExtra("Złapany", "MaskonurZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MaskonurDataZłapania");
            intent.putExtra("Lokalizacja", "MaskonurMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MaskonurMaZdjęcie");
            intent.putExtra("Zdjęcie", "MaskonurZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mazurek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Mazurek");
            intent.putExtra("Status", "MazurekStatus");
            intent.putExtra("Notatka", "MazurekNotatka");
            intent.putExtra("Złapany", "MazurekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MazurekDataZłapania");
            intent.putExtra("Lokalizacja", "MazurekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MazurekMaZdjęcie");
            intent.putExtra("Zdjęcie", "MazurekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa białogłowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaBiałogłowa");
            intent.putExtra("Status", "MewaBiałogłowaStatus");
            intent.putExtra("Notatka", "MewaBiałogłowaNotatka");
            intent.putExtra("Złapany", "MewaBiałogłowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaBiałogłowaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaBiałogłowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaBiałogłowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaBiałogłowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa blada")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaBlada");
            intent.putExtra("Status", "MewaBladaStatus");
            intent.putExtra("Notatka", "MewaBladaNotatka");
            intent.putExtra("Złapany", "MewaBladaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaBladaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaBladaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaBladaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaBladaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa cienkodzioba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaCienkodzioba");
            intent.putExtra("Status", "MewaCienkodziobaStatus");
            intent.putExtra("Notatka", "MewaCienkodziobaNotatka");
            intent.putExtra("Złapany", "MewaCienkodziobaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaCienkodziobaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaCienkodziobaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaCienkodziobaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaCienkodziobaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa czarnogłowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaCzarnogłowa");
            intent.putExtra("Status", "MewaCzarnogłowaStatus");
            intent.putExtra("Notatka", "MewaCzarnogłowaNotatka");
            intent.putExtra("Złapany", "MewaCzarnogłowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaCzarnogłowaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaCzarnogłowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaCzarnogłowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaCzarnogłowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa delawarska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaDelawarska");
            intent.putExtra("Status", "MewaDelawarskaStatus");
            intent.putExtra("Notatka", "MewaDelawarskaNotatka");
            intent.putExtra("Złapany", "MewaDelawarskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaDelawarskaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaDelawarskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaDelawarskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaDelawarskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa karaibska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaKaraibska");
            intent.putExtra("Status", "MewaKaraibskaStatus");
            intent.putExtra("Notatka", "MewaKaraibskaNotatka");
            intent.putExtra("Złapany", "MewaKaraibskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaKaraibskaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaKaraibskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaKaraibskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaKaraibskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa mała")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaMała");
            intent.putExtra("Status", "MewaMałaStatus");
            intent.putExtra("Notatka", "MewaMałaNotatka");
            intent.putExtra("Złapany", "MewaMałaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaMałaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaMałaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaMałaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaMałaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa modrodzioba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaModrodzioba");
            intent.putExtra("Status", "MewaModrodziobaStatus");
            intent.putExtra("Notatka", "MewaModrodziobaNotatka");
            intent.putExtra("Złapany", "MewaModrodziobaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaModrodziobaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaModrodziobaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaModrodziobaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaModrodziobaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa obrożna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaObrożna");
            intent.putExtra("Status", "MewaObrożnaStatus");
            intent.putExtra("Notatka", "MewaObrożnaNotatka");
            intent.putExtra("Złapany", "MewaObrożnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaObrożnaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaObrożnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaObrożnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaObrożnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa polarna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaPolarna");
            intent.putExtra("Status", "MewaPolarnaStatus");
            intent.putExtra("Notatka", "MewaPolarnaNotatka");
            intent.putExtra("Złapany", "MewaPolarnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaPolarnaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaPolarnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaPolarnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaPolarnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa romańska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaRomańska");
            intent.putExtra("Status", "MewaRomańskaStatus");
            intent.putExtra("Notatka", "MewaRomańskaNotatka");
            intent.putExtra("Złapany", "MewaRomańskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaRomańskaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaRomańskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaRomańskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaRomańskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa siodłowata")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaSiodłowata");
            intent.putExtra("Status", "MewaSiodłowataStatus");
            intent.putExtra("Notatka", "MewaSiodłowataNotatka");
            intent.putExtra("Złapany", "MewaSiodłowataZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaSiodłowataDataZłapania");
            intent.putExtra("Lokalizacja", "MewaSiodłowataMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaSiodłowataMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaSiodłowataZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa siwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaSiwa");
            intent.putExtra("Status", "MewaSiwaStatus");
            intent.putExtra("Notatka", "MewaSiwaNotatka");
            intent.putExtra("Złapany", "MewaSiwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaSiwaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaSiwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaSiwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaSiwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa srebrzysta")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaSrebrzysta");
            intent.putExtra("Status", "MewaSrebrzystaStatus");
            intent.putExtra("Notatka", "MewaSrebrzystaNotatka");
            intent.putExtra("Złapany", "MewaSrebrzystaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaSrebrzystaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaSrebrzystaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaSrebrzystaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaSrebrzystaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa trójpalczasta")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaTrójpalczasta");
            intent.putExtra("Status", "MewaTrójpalczastaStatus");
            intent.putExtra("Notatka", "MewaTrójpalczastaNotatka");
            intent.putExtra("Złapany", "MewaTrójpalczastaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaTrójpalczastaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaTrójpalczastaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaTrójpalczastaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaTrójpalczastaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mewa żółtonoga")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MewaŻółtonoga");
            intent.putExtra("Status", "MewaŻółtonogaStatus");
            intent.putExtra("Notatka", "MewaŻółtonogaNotatka");
            intent.putExtra("Złapany", "MewaŻółtonogaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MewaŻółtonogaDataZłapania");
            intent.putExtra("Lokalizacja", "MewaŻółtonogaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MewaŻółtonogaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MewaŻółtonogaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Modraczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Modraczek");
            intent.putExtra("Status", "ModraczekStatus");
            intent.putExtra("Notatka", "ModraczekNotatka");
            intent.putExtra("Złapany", "ModraczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ModraczekDataZłapania");
            intent.putExtra("Lokalizacja", "ModraczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ModraczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "ModraczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Modraszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Modraszka");
            intent.putExtra("Status", "ModraszkaStatus");
            intent.putExtra("Notatka", "ModraszkaNotatka");
            intent.putExtra("Złapany", "ModraszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ModraszkaDataZłapania");
            intent.putExtra("Lokalizacja", "ModraszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ModraszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ModraszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mornel")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Mornel");
            intent.putExtra("Status", "MornelStatus");
            intent.putExtra("Notatka", "MornelNotatka");
            intent.putExtra("Złapany", "MornelZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MornelDataZłapania");
            intent.putExtra("Lokalizacja", "MornelMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MornelMaZdjęcie");
            intent.putExtra("Zdjęcie", "MornelZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Muchołówka białoszyja")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MuchołówkaBiałoszyja");
            intent.putExtra("Status", "MuchołówkaBiałoszyjaStatus");
            intent.putExtra("Notatka", "MuchołówkaBiałoszyjaNotatka");
            intent.putExtra("Złapany", "MuchołówkaBiałoszyjaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MuchołówkaBiałoszyjaDataZłapania");
            intent.putExtra("Lokalizacja", "MuchołówkaBiałoszyjaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MuchołówkaBiałoszyjaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MuchołówkaBiałoszyjaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Muchołówka mała")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MuchołówkaMała");
            intent.putExtra("Status", "MuchołówkaMałaStatus");
            intent.putExtra("Notatka", "MuchołówkaMałaNotatka");
            intent.putExtra("Złapany", "MuchołówkaMałaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MuchołówkaMałaDataZłapania");
            intent.putExtra("Lokalizacja", "MuchołówkaMałaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MuchołówkaMałaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MuchołówkaMałaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Muchołówka szara")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MuchołówkaSzara");
            intent.putExtra("Status", "MuchołówkaSzaraStatus");
            intent.putExtra("Notatka", "MuchołówkaSzaraNotatka");
            intent.putExtra("Złapany", "MuchołówkaSzaraZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MuchołówkaSzaraDataZłapania");
            intent.putExtra("Lokalizacja", "MuchołówkaSzaraMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MuchołówkaSzaraMaZdjęcie");
            intent.putExtra("Zdjęcie", "MuchołówkaSzaraZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Muchołówka żałobna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MuchołówkaŻałobna");
            intent.putExtra("Status", "MuchołówkaŻałobnaStatus");
            intent.putExtra("Notatka", "MuchołówkaŻałobnaNotatka");
            intent.putExtra("Złapany", "MuchołówkaŻałobnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MuchołówkaŻałobnaDataZłapania");
            intent.putExtra("Lokalizacja", "MuchołówkaŻałobnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MuchołówkaŻałobnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "MuchołówkaŻałobnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Mysikrólik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Mysikrólik");
            intent.putExtra("Status", "MysikrólikStatus");
            intent.putExtra("Notatka", "MysikrólikNotatka");
            intent.putExtra("Złapany", "MysikrólikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MysikrólikDataZłapania");
            intent.putExtra("Lokalizacja", "MysikrólikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MysikrólikMaZdjęcie");
            intent.putExtra("Zdjęcie", "MysikrólikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Myszołów")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Myszołów");
            intent.putExtra("Status", "MyszołówStatus");
            intent.putExtra("Notatka", "MyszołówNotatka");
            intent.putExtra("Złapany", "MyszołówZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MyszołówDataZłapania");
            intent.putExtra("Lokalizacja", "MyszołówMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MyszołówMaZdjęcie");
            intent.putExtra("Zdjęcie", "MyszołówZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Myszołów włochaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "MyszołówWłochaty");
            intent.putExtra("Status", "MyszołówWłochatyStatus");
            intent.putExtra("Notatka", "MyszołówWłochatyNotatka");
            intent.putExtra("Złapany", "MyszołówWłochatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "MyszołówWłochatyDataZłapania");
            intent.putExtra("Lokalizacja", "MyszołówWłochatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "MyszołówWłochatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "MyszołówWłochatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nagórnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Nagórnik");
            intent.putExtra("Status", "NagórnikStatus");
            intent.putExtra("Notatka", "NagórnikNotatka");
            intent.putExtra("Złapany", "NagórnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NagórnikDataZłapania");
            intent.putExtra("Lokalizacja", "NagórnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NagórnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "NagórnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nawałnik burzowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "NawałnikBurzowy");
            intent.putExtra("Status", "NawałnikBurzowyStatus");
            intent.putExtra("Notatka", "NawałnikBurzowyNotatka");
            intent.putExtra("Złapany", "NawałnikBurzowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NawałnikBurzowyDataZłapania");
            intent.putExtra("Lokalizacja", "NawałnikBurzowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NawałnikBurzowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "NawałnikBurzowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nawałnik duży")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "NawałnikDuży");
            intent.putExtra("Status", "NawałnikDużyStatus");
            intent.putExtra("Notatka", "NawałnikDużyNotatka");
            intent.putExtra("Złapany", "NawałnikDużyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NawałnikDużyDataZłapania");
            intent.putExtra("Lokalizacja", "NawałnikDużyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NawałnikDużyMaZdjęcie");
            intent.putExtra("Zdjęcie", "NawałnikDużyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nur białodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "NurBiałodzioby");
            intent.putExtra("Status", "NurBiałodziobyStatus");
            intent.putExtra("Notatka", "NurBiałodziobyNotatka");
            intent.putExtra("Złapany", "NurBiałodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurBiałodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "NurBiałodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurBiałodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurBiałodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nur czarnoszyi")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "NurCzarnoszyi");
            intent.putExtra("Status", "NurCzarnoszyiStatus");
            intent.putExtra("Notatka", "NurCzarnoszyiNotatka");
            intent.putExtra("Złapany", "NurCzarnoszyiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurCzarnoszyiDataZłapania");
            intent.putExtra("Lokalizacja", "NurCzarnoszyiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurCzarnoszyiMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurCzarnoszyiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nurnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Nurnik");
            intent.putExtra("Status", "NurnikStatus");
            intent.putExtra("Notatka", "NurnikNotatka");
            intent.putExtra("Złapany", "NurnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurnikDataZłapania");
            intent.putExtra("Lokalizacja", "NurnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nurogęś")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Nurogęś");
            intent.putExtra("Status", "NurogęśStatus");
            intent.putExtra("Notatka", "NurogęśNotatka");
            intent.putExtra("Złapany", "NurogęśZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurogęśDataZłapania");
            intent.putExtra("Lokalizacja", "NurogęśMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurogęśMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurogęśZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nur rdzawoszyi")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "NurRdzawoszyi");
            intent.putExtra("Status", "NurRdzawoszyiStatus");
            intent.putExtra("Notatka", "NurRdzawoszyiNotatka");
            intent.putExtra("Złapany", "NurRdzawoszyiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurRdzawoszyiDataZłapania");
            intent.putExtra("Lokalizacja", "NurRdzawoszyiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurRdzawoszyiMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurRdzawoszyiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nurzyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Nurzyk");
            intent.putExtra("Status", "NurzykStatus");
            intent.putExtra("Notatka", "NurzykNotatka");
            intent.putExtra("Złapany", "NurzykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurzykDataZłapania");
            intent.putExtra("Lokalizacja", "NurzykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurzykMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurzykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Nurzyk polarny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "NurzykPolarny");
            intent.putExtra("Status", "NurzykPolarnyStatus");
            intent.putExtra("Notatka", "NurzykPolarnyNotatka");
            intent.putExtra("Złapany", "NurzykPolarnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "NurzykPolarnyDataZłapania");
            intent.putExtra("Lokalizacja", "NurzykPolarnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "NurzykPolarnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "NurzykPolarnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Oceannik żółtopłetwy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "OceannikŻółtopłetwy");
            intent.putExtra("Status", "OceannikŻółtopłetwyStatus");
            intent.putExtra("Notatka", "OceannikŻółtopłetwyNotatka");
            intent.putExtra("Złapany", "OceannikŻółtopłetwyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OceannikŻółtopłetwyDataZłapania");
            intent.putExtra("Lokalizacja", "OceannikŻółtopłetwyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OceannikŻółtopłetwyMaZdjęcie");
            intent.putExtra("Zdjęcie", "OceannikŻółtopłetwyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ogorzałka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Ogorzałka");
            intent.putExtra("Status", "OgorzałkaStatus");
            intent.putExtra("Notatka", "OgorzałkaNotatka");
            intent.putExtra("Złapany", "OgorzałkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OgorzałkaDataZłapania");
            intent.putExtra("Lokalizacja", "OgorzałkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OgorzałkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "OgorzałkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ogorzałka mała")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "OgorzałkaMała");
            intent.putExtra("Status", "OgorzałkaMałaStatus");
            intent.putExtra("Notatka", "OgorzałkaMałaNotatka");
            intent.putExtra("Złapany", "OgorzałkaMałaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OgorzałkaMałaDataZłapania");
            intent.putExtra("Lokalizacja", "OgorzałkaMałaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OgorzałkaMałaMaZdjęcie");
            intent.putExtra("Zdjęcie", "OgorzałkaMałaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ohar")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Ohar");
            intent.putExtra("Status", "OharStatus");
            intent.putExtra("Notatka", "OharNotatka");
            intent.putExtra("Złapany", "OharZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OharDataZłapania");
            intent.putExtra("Lokalizacja", "OharMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OharMaZdjęcie");
            intent.putExtra("Zdjęcie", "OharZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Oknówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Oknówka");
            intent.putExtra("Status", "OknówkaStatus");
            intent.putExtra("Notatka", "OknówkaNotatka");
            intent.putExtra("Złapany", "OknówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OknówkaDataZłapania");
            intent.putExtra("Lokalizacja", "OknówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OknówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "OknówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orlica")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Orlica");
            intent.putExtra("Status", "OrlicaStatus");
            intent.putExtra("Notatka", "OrlicaNotatka");
            intent.putExtra("Złapany", "OrlicaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrlicaDataZłapania");
            intent.putExtra("Lokalizacja", "OrlicaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrlicaMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrlicaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orlik grubodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "OrlikGrubodzioby");
            intent.putExtra("Status", "OrlikGrubodziobyStatus");
            intent.putExtra("Notatka", "OrlikGrubodziobyNotatka");
            intent.putExtra("Złapany", "OrlikGrubodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrlikGrubodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "OrlikGrubodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrlikGrubodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrlikGrubodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orlik krzykliwy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "OrlikKrzykliwy");
            intent.putExtra("Status", "OrlikKrzykliwyStatus");
            intent.putExtra("Notatka", "OrlikKrzykliwyNotatka");
            intent.putExtra("Złapany", "OrlikKrzykliwyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrlikKrzykliwyDataZłapania");
            intent.putExtra("Lokalizacja", "OrlikKrzykliwyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrlikKrzykliwyMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrlikKrzykliwyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orłosęp")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Orłosęp");
            intent.putExtra("Status", "OrłosępStatus");
            intent.putExtra("Notatka", "OrłosępNotatka");
            intent.putExtra("Złapany", "OrłosępZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrłosępDataZłapania");
            intent.putExtra("Lokalizacja", "OrłosępMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrłosępMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrłosępZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ortolan")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Ortolan");
            intent.putExtra("Status", "OrtolanStatus");
            intent.putExtra("Notatka", "OrtolanNotatka");
            intent.putExtra("Złapany", "OrtolanZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrtolanDataZłapania");
            intent.putExtra("Lokalizacja", "OrtolanMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrtolanMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrtolanZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orzechówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Orzechówka");
            intent.putExtra("Status", "OrzechówkaStatus");
            intent.putExtra("Notatka", "OrzechówkaNotatka");
            intent.putExtra("Złapany", "OrzechówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrzechówkaDataZłapania");
            intent.putExtra("Lokalizacja", "OrzechówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrzechówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrzechówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orzeł cesarski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "OrzełCesarski");
            intent.putExtra("Status", "OrzełCesarskiStatus");
            intent.putExtra("Notatka", "OrzełCesarskiNotatka");
            intent.putExtra("Złapany", "OrzełCesarskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrzełCesarskiDataZłapania");
            intent.putExtra("Lokalizacja", "OrzełCesarskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrzełCesarskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrzełCesarskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Orzeł stepowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "OrzełStepowy");
            intent.putExtra("Status", "OrzełStepowyStatus");
            intent.putExtra("Notatka", "OrzełStepowyNotatka");
            intent.putExtra("Złapany", "OrzełStepowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OrzełStepowyDataZłapania");
            intent.putExtra("Lokalizacja", "OrzełStepowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OrzełStepowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "OrzełStepowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Osetnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Osetnik");
            intent.putExtra("Status", "OsetnikStatus");
            intent.putExtra("Notatka", "OsetnikNotatka");
            intent.putExtra("Złapany", "OsetnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OsetnikDataZłapania");
            intent.putExtra("Lokalizacja", "OsetnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OsetnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "OsetnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ostrygojad")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Ostrygojad");
            intent.putExtra("Status", "OstrygojadStatus");
            intent.putExtra("Notatka", "OstrygojadNotatka");
            intent.putExtra("Złapany", "OstrygojadZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "OstrygojadDataZłapania");
            intent.putExtra("Lokalizacja", "OstrygojadMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "OstrygojadMaZdjęcie");
            intent.putExtra("Zdjęcie", "OstrygojadZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("PardwaMszarna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PardwaMszarna");
            intent.putExtra("Status", "PardwaMszarnaStatus");
            intent.putExtra("Notatka", "PardwaMszarnaNotatka");
            intent.putExtra("Złapany", "PardwaMszarnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PardwaMszarnaDataZłapania");
            intent.putExtra("Lokalizacja", "PardwaMszarnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PardwaMszarnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PardwaMszarnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pasterz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pasterz");
            intent.putExtra("Status", "PasterzStatus");
            intent.putExtra("Notatka", "PasterzNotatka");
            intent.putExtra("Złapany", "PasterzZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PasterzDataZłapania");
            intent.putExtra("Lokalizacja", "PasterzMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PasterzMaZdjęcie");
            intent.putExtra("Zdjęcie", "PasterzZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Paszkot")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Paszkot");
            intent.putExtra("Status", "PaszkotStatus");
            intent.putExtra("Notatka", "PaszkotNotatka");
            intent.putExtra("Złapany", "PaszkotZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PaszkotDataZłapania");
            intent.putExtra("Lokalizacja", "PaszkotMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PaszkotMaZdjęcie");
            intent.putExtra("Zdjęcie", "PaszkotZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pelikan kędzierzawy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PelikanKędzierzawy");
            intent.putExtra("Status", "PelikanKędzierzawyStatus");
            intent.putExtra("Notatka", "PelikanKędzierzawyNotatka");
            intent.putExtra("Złapany", "PelikanKędzierzawyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PelikanKędzierzawyDataZłapania");
            intent.putExtra("Lokalizacja", "PelikanKędzierzawyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PelikanKędzierzawyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PelikanKędzierzawyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pelikan różowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PelikanRóżowy");
            intent.putExtra("Status", "PelikanRóżowyStatus");
            intent.putExtra("Notatka", "PelikanRóżowyNotatka");
            intent.putExtra("Złapany", "PelikanRóżowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PelikanRóżowyDataZłapania");
            intent.putExtra("Lokalizacja", "PelikanRóżowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PelikanRóżowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PelikanRóżowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pełzacz leśny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PełzaczLeśny");
            intent.putExtra("Status", "PełzaczLeśnyStatus");
            intent.putExtra("Notatka", "PełzaczLeśnyNotatka");
            intent.putExtra("Złapany", "PełzaczLeśnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PełzaczLeśnyDataZłapania");
            intent.putExtra("Lokalizacja", "PełzaczLeśnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PełzaczLeśnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PełzaczLeśnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pełzacz ogrodowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PełzaczOgrodowy");
            intent.putExtra("Status", "PełzaczOgrodowyStatus");
            intent.putExtra("Notatka", "PełzaczOgrodowyNotatka");
            intent.putExtra("Złapany", "PełzaczOgrodowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PełzaczOgrodowyDataZłapania");
            intent.putExtra("Lokalizacja", "PełzaczOgrodowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PełzaczOgrodowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PełzaczOgrodowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Perkoz dwuczuby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PerkozDwuczuby");
            intent.putExtra("Status", "PerkozDwuczubyStatus");
            intent.putExtra("Notatka", "PerkozDwuczubyNotatka");
            intent.putExtra("Złapany", "PerkozDwuczubyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PerkozDwuczubyDataZłapania");
            intent.putExtra("Lokalizacja", "PerkozDwuczubyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PerkozDwuczubyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PerkozDwuczubyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Perkozek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Perkozek");
            intent.putExtra("Status", "PerkozekStatus");
            intent.putExtra("Notatka", "PerkozekNotatka");
            intent.putExtra("Złapany", "PerkozekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PerkozekDataZłapania");
            intent.putExtra("Lokalizacja", "PerkozekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PerkozekMaZdjęcie");
            intent.putExtra("Zdjęcie", "PerkozekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Perkoz grubodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PerkozGrubodzioby");
            intent.putExtra("Status", "PerkozGrubodziobyStatus");
            intent.putExtra("Notatka", "PerkozGrubodziobyNotatka");
            intent.putExtra("Złapany", "PerkozGrubodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PerkozGrubodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "PerkozGrubodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PerkozGrubodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PerkozGrubodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Perkoz rdzawoszyi")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PerkozRdzawoszyi");
            intent.putExtra("Status", "PerkozRdzawoszyiStatus");
            intent.putExtra("Notatka", "PerkozRdzawoszyiNotatka");
            intent.putExtra("Złapany", "PerkozRdzawoszyiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PerkozRdzawoszyiDataZłapania");
            intent.putExtra("Lokalizacja", "PerkozRdzawoszyiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PerkozRdzawoszyiMaZdjęcie");
            intent.putExtra("Zdjęcie", "PerkozRdzawoszyiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Perkoz rogaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PerkozRogaty");
            intent.putExtra("Status", "PerkozRogatyStatus");
            intent.putExtra("Notatka", "PerkozRogatyNotatka");
            intent.putExtra("Złapany", "PerkozRogatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PerkozRogatyDataZłapania");
            intent.putExtra("Lokalizacja", "PerkozRogatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PerkozRogatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PerkozRogatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Piaskowiec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Piaskowiec");
            intent.putExtra("Status", "PiaskowiecStatus");
            intent.putExtra("Notatka", "PiaskowiecNotatka");
            intent.putExtra("Złapany", "PiaskowiecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PiaskowiecDataZłapania");
            intent.putExtra("Lokalizacja", "PiaskowiecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PiaskowiecMaZdjęcie");
            intent.putExtra("Zdjęcie", "PiaskowiecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Piecuszek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Piecuszek");
            intent.putExtra("Status", "PiecuszekStatus");
            intent.putExtra("Notatka", "PiecuszekNotatka");
            intent.putExtra("Złapany", "PiecuszekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PiecuszekDataZłapania");
            intent.putExtra("Lokalizacja", "PiecuszekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PiecuszekMaZdjęcie");
            intent.putExtra("Zdjęcie", "PiecuszekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Piegża")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Piegża");
            intent.putExtra("Status", "PiegżaStatus");
            intent.putExtra("Notatka", "PiegżaNotatka");
            intent.putExtra("Złapany", "PiegżaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PiegżaDataZłapania");
            intent.putExtra("Lokalizacja", "PiegżaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PiegżaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PiegżaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pierwiosnek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pierwiosnek");
            intent.putExtra("Status", "PierwiosnekStatus");
            intent.putExtra("Notatka", "PierwiosnekNotatka");
            intent.putExtra("Złapany", "PierwiosnekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PierwiosnekDataZłapania");
            intent.putExtra("Lokalizacja", "PierwiosnekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PierwiosnekMaZdjęcie");
            intent.putExtra("Zdjęcie", "PierwiosnekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pleszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pleszka");
            intent.putExtra("Status", "PleszkaStatus");
            intent.putExtra("Notatka", "PleszkaNotatka");
            intent.putExtra("Złapany", "PleszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PleszkaDataZłapania");
            intent.putExtra("Lokalizacja", "PleszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PleszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PleszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pliszka górska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PliszkaGórska");
            intent.putExtra("Status", "PliszkaGórskaStatus");
            intent.putExtra("Notatka", "PliszkaGórskaNotatka");
            intent.putExtra("Złapany", "PliszkaGórskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PliszkaGórskaDataZłapania");
            intent.putExtra("Lokalizacja", "PliszkaGórskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PliszkaGórskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PliszkaGórskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pliszka siwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PliszkaSiwa");
            intent.putExtra("Status", "PliszkaSiwaStatus");
            intent.putExtra("Notatka", "PliszkaSiwaNotatka");
            intent.putExtra("Złapany", "PliszkaSiwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PliszkaSiwaDataZłapania");
            intent.putExtra("Lokalizacja", "PliszkaSiwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PliszkaSiwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PliszkaSiwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pliszka żółta")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PliszkaŻółta");
            intent.putExtra("Status", "PliszkaŻółtaStatus");
            intent.putExtra("Notatka", "PliszkaŻółtaNotatka");
            intent.putExtra("Złapany", "PliszkaŻółtaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PliszkaŻółtaDataZłapania");
            intent.putExtra("Lokalizacja", "PliszkaŻółtaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PliszkaŻółtaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PliszkaŻółtaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pluszcz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pluszcz");
            intent.putExtra("Status", "PluszczStatus");
            intent.putExtra("Notatka", "PluszczNotatka");
            intent.putExtra("Złapany", "PluszczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PluszczDataZłapania");
            intent.putExtra("Lokalizacja", "PluszczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PluszczMaZdjęcie");
            intent.putExtra("Zdjęcie", "PluszczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Płaskonos")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Płaskonos");
            intent.putExtra("Status", "PłaskonosStatus");
            intent.putExtra("Notatka", "PłaskonosNotatka");
            intent.putExtra("Złapany", "PłaskonosZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PłaskonosDataZłapania");
            intent.putExtra("Lokalizacja", "PłaskonosMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PłaskonosMaZdjęcie");
            intent.putExtra("Zdjęcie", "PłaskonosZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Płatkonóg płaskodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PłatkonógPłaskodzioby");
            intent.putExtra("Status", "PłatkonógPłaskodziobyStatus");
            intent.putExtra("Notatka", "PłatkonógPłaskodziobyNotatka");
            intent.putExtra("Złapany", "PłatkonógPłaskodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PłatkonógPłaskodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "PłatkonógPłaskodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PłatkonógPłaskodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PłatkonógPłaskodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Płatkonóg szydłodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PłatkonógSzydłodzioby");
            intent.putExtra("Status", "PłatkonógSzydłodziobyStatus");
            intent.putExtra("Notatka", "PłatkonógSzydłodziobyNotatka");
            intent.putExtra("Złapany", "PłatkonógSzydłodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PłatkonógSzydłodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "PłatkonógSzydłodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PłatkonógSzydłodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PłatkonógSzydłodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Płochacz halny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PłochaczHalny");
            intent.putExtra("Status", "PłochaczHalnyStatus");
            intent.putExtra("Notatka", "PłochaczHalnyNotatka");
            intent.putExtra("Złapany", "PłochaczHalnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PłochaczHalnyDataZłapania");
            intent.putExtra("Lokalizacja", "PłochaczHalnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PłochaczHalnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PłochaczHalnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Płochacz syberyjski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PłochaczSyberyjski");
            intent.putExtra("Status", "PłochaczSyberyjskiStatus");
            intent.putExtra("Notatka", "PłochaczSyberyjskiNotatka");
            intent.putExtra("Złapany", "PłochaczSyberyjskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PłochaczSyberyjskiDataZłapania");
            intent.putExtra("Lokalizacja", "PłochaczSyberyjskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PłochaczSyberyjskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "PłochaczSyberyjskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Płomykówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Płomykówka");
            intent.putExtra("Status", "PłomykówkaStatus");
            intent.putExtra("Notatka", "PłomykówkaNotatka");
            intent.putExtra("Złapany", "PłomykówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PłomykówkaDataZłapania");
            intent.putExtra("Lokalizacja", "PłomykówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PłomykówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PłomykówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Podgorzałka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Podgorzałka");
            intent.putExtra("Status", "PodgorzałkaStatus");
            intent.putExtra("Notatka", "PodgorzałkaNotatka");
            intent.putExtra("Złapany", "PodgorzałkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PodgorzałkaDataZłapania");
            intent.putExtra("Lokalizacja", "PodgorzałkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PodgorzałkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PodgorzałkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Podróżniczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Podróżniczek");
            intent.putExtra("Status", "PodróżniczekStatus");
            intent.putExtra("Notatka", "PodróżniczekNotatka");
            intent.putExtra("Złapany", "PodróżniczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PodróżniczekDataZłapania");
            intent.putExtra("Lokalizacja", "PodróżniczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PodróżniczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "PodróżniczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pójdźka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pójdźka");
            intent.putExtra("Status", "PójdźkaStatus");
            intent.putExtra("Notatka", "PójdźkaNotatka");
            intent.putExtra("Złapany", "PójdźkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PójdźkaDataZłapania");
            intent.putExtra("Lokalizacja", "PójdźkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PójdźkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PójdźkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pokląskwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pokląskwa");
            intent.putExtra("Status", "PokląskwaStatus");
            intent.putExtra("Notatka", "PokląskwaNotatka");
            intent.putExtra("Złapany", "PokląskwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PokląskwaDataZłapania");
            intent.putExtra("Lokalizacja", "PokląskwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PokląskwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PokląskwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pokrzewka aksamitna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PokrzewkaAksamitna");
            intent.putExtra("Status", "PokrzewkaAksamitnaStatus");
            intent.putExtra("Notatka", "PokrzewkaAksamitnaNotatka");
            intent.putExtra("Złapany", "PokrzewkaAksamitnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PokrzewkaAksamitnaDataZłapania");
            intent.putExtra("Lokalizacja", "PokrzewkaAksamitnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PokrzewkaAksamitnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PokrzewkaAksamitnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("PokrzewkaWąsata")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PokrzewkaWąsata");
            intent.putExtra("Status", "PokrzewkaWąsataStatus");
            intent.putExtra("Notatka", "PokrzewkaWąsataNotatka");
            intent.putExtra("Złapany", "PokrzewkaWąsataZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PokrzewkaWąsataDataZłapania");
            intent.putExtra("Lokalizacja", "PokrzewkaWąsataMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PokrzewkaWąsataMaZdjęcie");
            intent.putExtra("Zdjęcie", "PokrzewkaWąsataZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pokrzywnica")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pokrzywnica");
            intent.putExtra("Status", "PokrzywnicaStatus");
            intent.putExtra("Notatka", "PokrzywnicaNotatka");
            intent.putExtra("Złapany", "PokrzywnicaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PokrzywnicaDataZłapania");
            intent.putExtra("Lokalizacja", "PokrzywnicaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PokrzywnicaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PokrzywnicaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pomurnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pomurnik");
            intent.putExtra("Status", "PomurnikStatus");
            intent.putExtra("Notatka", "PomurnikNotatka");
            intent.putExtra("Złapany", "PomurnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PomurnikDataZłapania");
            intent.putExtra("Lokalizacja", "PomurnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PomurnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "PomurnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Poświerka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Poświerka");
            intent.putExtra("Status", "PoświerkaStatus");
            intent.putExtra("Notatka", "PoświerkaNotatka");
            intent.putExtra("Złapany", "PoświerkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PoświerkaDataZłapania");
            intent.putExtra("Lokalizacja", "PoświerkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PoświerkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PoświerkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Potrzeszcz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Potrzeszcz");
            intent.putExtra("Status", "PotrzeszczStatus");
            intent.putExtra("Notatka", "PotrzeszczNotatka");
            intent.putExtra("Złapany", "PotrzeszczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PotrzeszczDataZłapania");
            intent.putExtra("Lokalizacja", "PotrzeszczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PotrzeszczMaZdjęcie");
            intent.putExtra("Zdjęcie", "PotrzeszczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Potrzos")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Potrzos");
            intent.putExtra("Status", "PotrzosStatus");
            intent.putExtra("Notatka", "PotrzosNotatka");
            intent.putExtra("Złapany", "PotrzosZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PotrzosDataZłapania");
            intent.putExtra("Lokalizacja", "PotrzosMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PotrzosMaZdjęcie");
            intent.putExtra("Zdjęcie", "PotrzosZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Przepiórka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Przepiórka");
            intent.putExtra("Status", "PrzepiórkaStatus");
            intent.putExtra("Notatka", "PrzepiórkaNotatka");
            intent.putExtra("Złapany", "PrzepiórkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PrzepiórkaDataZłapania");
            intent.putExtra("Lokalizacja", "PrzepiórkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PrzepiórkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PrzepiórkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Puchacz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Puchacz");
            intent.putExtra("Status", "PuchaczStatus");
            intent.putExtra("Notatka", "PuchaczNotatka");
            intent.putExtra("Złapany", "PuchaczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PuchaczDataZłapania");
            intent.putExtra("Lokalizacja", "PuchaczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PuchaczMaZdjęcie");
            intent.putExtra("Zdjęcie", "PuchaczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pustułeczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pustułeczka");
            intent.putExtra("Status", "PustułeczkaStatus");
            intent.putExtra("Notatka", "PustułeczkaNotatka");
            intent.putExtra("Złapany", "PustułeczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PustułeczkaDataZłapania");
            intent.putExtra("Lokalizacja", "PustułeczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PustułeczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PustułeczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pustułka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pustułka");
            intent.putExtra("Status", "PustułkaStatus");
            intent.putExtra("Notatka", "PustułkaNotatka");
            intent.putExtra("Złapany", "PustułkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PustułkaDataZłapania");
            intent.putExtra("Lokalizacja", "PustułkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PustułkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "PustułkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Pustynnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Pustynnik");
            intent.putExtra("Status", "PustynnikStatus");
            intent.putExtra("Notatka", "PustynnikNotatka");
            intent.putExtra("Złapany", "PustynnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PustynnikDataZłapania");
            intent.putExtra("Lokalizacja", "PustynnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PustynnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "PustynnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Puszczyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Puszczyk");
            intent.putExtra("Status", "PuszczykStatus");
            intent.putExtra("Notatka", "PuszczykNotatka");
            intent.putExtra("Złapany", "PuszczykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PuszczykDataZłapania");
            intent.putExtra("Lokalizacja", "PuszczykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PuszczykMaZdjęcie");
            intent.putExtra("Zdjęcie", "PuszczykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Puszczyk mszarny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PuszczykMszarny");
            intent.putExtra("Status", "PuszczykMszarnyStatus");
            intent.putExtra("Notatka", "PuszczykMszarnyNotatka");
            intent.putExtra("Złapany", "PuszczykMszarnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PuszczykMszarnyDataZłapania");
            intent.putExtra("Lokalizacja", "PuszczykMszarnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PuszczykMszarnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "PuszczykMszarnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Puszczyk uralski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "PuszczykUralski");
            intent.putExtra("Status", "PuszczykUralskiStatus");
            intent.putExtra("Notatka", "PuszczykUralskiNotatka");
            intent.putExtra("Złapany", "PuszczykUralskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "PuszczykUralskiDataZłapania");
            intent.putExtra("Lokalizacja", "PuszczykUralskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "PuszczykUralskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "PuszczykUralskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Raniuszek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Raniuszek");
            intent.putExtra("Status", "RaniuszekStatus");
            intent.putExtra("Notatka", "RaniuszekNotatka");
            intent.putExtra("Złapany", "RaniuszekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RaniuszekDataZłapania");
            intent.putExtra("Lokalizacja", "RaniuszekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RaniuszekMaZdjęcie");
            intent.putExtra("Zdjęcie", "RaniuszekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Raróg")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Raróg");
            intent.putExtra("Status", "RarógStatus");
            intent.putExtra("Notatka", "RarógNotatka");
            intent.putExtra("Złapany", "RarógZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RarógDataZłapania");
            intent.putExtra("Lokalizacja", "RarógMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RarógMaZdjęcie");
            intent.putExtra("Zdjęcie", "RarógZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Remiz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Remiz");
            intent.putExtra("Status", "RemizStatus");
            intent.putExtra("Notatka", "RemizNotatka");
            intent.putExtra("Złapany", "RemizZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RemizDataZłapania");
            intent.putExtra("Lokalizacja", "RemizMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RemizMaZdjęcie");
            intent.putExtra("Zdjęcie", "RemizZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rokitniczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Rokitniczka");
            intent.putExtra("Status", "RokitniczkaStatus");
            intent.putExtra("Notatka", "RokitniczkaNotatka");
            intent.putExtra("Złapany", "RokitniczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RokitniczkaDataZłapania");
            intent.putExtra("Lokalizacja", "RokitniczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RokitniczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RokitniczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rożeniec")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Rożeniec");
            intent.putExtra("Status", "RożeniecStatus");
            intent.putExtra("Notatka", "RożeniecNotatka");
            intent.putExtra("Złapany", "RożeniecZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RożeniecDataZłapania");
            intent.putExtra("Lokalizacja", "RożeniecMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RożeniecMaZdjęcie");
            intent.putExtra("Zdjęcie", "RożeniecZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rudzik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Rudzik");
            intent.putExtra("Status", "RudzikStatus");
            intent.putExtra("Notatka", "RudzikNotatka");
            intent.putExtra("Złapany", "RudzikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RudzikDataZłapania");
            intent.putExtra("Lokalizacja", "RudzikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RudzikMaZdjęcie");
            intent.putExtra("Zdjęcie", "RudzikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybaczek srokaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybaczekSrokaty");
            intent.putExtra("Status", "RybaczekSrokatyStatus");
            intent.putExtra("Notatka", "RybaczekSrokatyNotatka");
            intent.putExtra("Złapany", "RybaczekSrokatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybaczekSrokatyDataZłapania");
            intent.putExtra("Lokalizacja", "RybaczekSrokatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybaczekSrokatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybaczekSrokatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa białoczelna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaBiałoczelna");
            intent.putExtra("Status", "RybitwaBiałoczelnaStatus");
            intent.putExtra("Notatka", "RybitwaBiałoczelnaNotatka");
            intent.putExtra("Złapany", "RybitwaBiałoczelnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaBiałoczelnaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaBiałoczelnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaBiałoczelnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaBiałoczelnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa białoskrzydła")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaBiałoskrzydła");
            intent.putExtra("Status", "RybitwaBiałoskrzydłaStatus");
            intent.putExtra("Notatka", "RybitwaBiałoskrzydłaNotatka");
            intent.putExtra("Złapany", "RybitwaBiałoskrzydłaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaBiałoskrzydłaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaBiałoskrzydłaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaBiałoskrzydłaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaBiałoskrzydłaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa białowąsa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaBiałowąsa");
            intent.putExtra("Status", "RybitwaBiałowąsaStatus");
            intent.putExtra("Notatka", "RybitwaBiałowąsaNotatka");
            intent.putExtra("Złapany", "RybitwaBiałowąsaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaBiałowąsaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaBiałowąsaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaBiałowąsaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaBiałowąsaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa czarna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaCzarna");
            intent.putExtra("Status", "RybitwaCzarnaStatus");
            intent.putExtra("Notatka", "RybitwaCzarnaNotatka");
            intent.putExtra("Złapany", "RybitwaCzarnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaCzarnaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaCzarnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaCzarnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaCzarnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa czubata")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaCzubata");
            intent.putExtra("Status", "RybitwaCzubataStatus");
            intent.putExtra("Notatka", "RybitwaCzubataNotatka");
            intent.putExtra("Złapany", "RybitwaCzubataZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaCzubataDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaCzubataMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaCzubataMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaCzubataZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa krótkodzioba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaKrótkodzioba");
            intent.putExtra("Status", "RybitwaKrótkodziobaStatus");
            intent.putExtra("Notatka", "RybitwaKrótkodziobaNotatka");
            intent.putExtra("Złapany", "RybitwaKrótkodziobaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaKrótkodziobaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaKrótkodziobaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaKrótkodziobaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaKrótkodziobaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa popielata")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaPopielata");
            intent.putExtra("Status", "RybitwaPopielataStatus");
            intent.putExtra("Notatka", "RybitwaPopielataNotatka");
            intent.putExtra("Złapany", "RybitwaPopielataZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaPopielataDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaPopielataMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaPopielataMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaPopielataZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa różowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaRóżowa");
            intent.putExtra("Status", "RybitwaRóżowaStatus");
            intent.putExtra("Notatka", "RybitwaRóżowaNotatka");
            intent.putExtra("Złapany", "RybitwaRóżowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaRóżowaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaRóżowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaRóżowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaRóżowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa rzeczna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaRzeczna");
            intent.putExtra("Status", "RybitwaRzecznaStatus");
            intent.putExtra("Notatka", "RybitwaRzecznaNotatka");
            intent.putExtra("Złapany", "RybitwaRzecznaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaRzecznaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaRzecznaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaRzecznaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaRzecznaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybitwa wielkodzioba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "RybitwaWielkodzioba");
            intent.putExtra("Status", "RybitwaWielkodziobaStatus");
            intent.putExtra("Notatka", "RybitwaWielkodziobaNotatka");
            intent.putExtra("Złapany", "RybitwaWielkodziobaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybitwaWielkodziobaDataZłapania");
            intent.putExtra("Lokalizacja", "RybitwaWielkodziobaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybitwaWielkodziobaMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybitwaWielkodziobaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rybołów")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Rybołów");
            intent.putExtra("Status", "RybołówStatus");
            intent.putExtra("Notatka", "RybołówNotatka");
            intent.putExtra("Złapany", "RybołówZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RybołówDataZłapania");
            intent.putExtra("Lokalizacja", "RybołówMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RybołówMaZdjęcie");
            intent.putExtra("Zdjęcie", "RybołówZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rycyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Rycyk");
            intent.putExtra("Status", "RycykStatus");
            intent.putExtra("Notatka", "RycykNotatka");
            intent.putExtra("Złapany", "RycykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RycykDataZłapania");
            intent.putExtra("Lokalizacja", "RycykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RycykMaZdjęcie");
            intent.putExtra("Zdjęcie", "RycykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Rzepołuch")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Rzepołuch");
            intent.putExtra("Status", "RzepołuchStatus");
            intent.putExtra("Notatka", "RzepołuchNotatka");
            intent.putExtra("Złapany", "RzepołuchZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "RzepołuchDataZłapania");
            intent.putExtra("Lokalizacja", "RzepołuchMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "RzepołuchMaZdjęcie");
            intent.putExtra("Zdjęcie", "RzepołuchZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Samotnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Samotnik");
            intent.putExtra("Status", "SamotnikStatus");
            intent.putExtra("Notatka", "SamotnikNotatka");
            intent.putExtra("Złapany", "SamotnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SamotnikDataZłapania");
            intent.putExtra("Lokalizacja", "SamotnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SamotnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "SamotnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sęp kasztanowaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SępKasztanowaty");
            intent.putExtra("Status", "SępKasztanowatyStatus");
            intent.putExtra("Notatka", "SępKasztanowatyNotatka");
            intent.putExtra("Złapany", "SępKasztanowatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SępKasztanowatyDataZłapania");
            intent.putExtra("Lokalizacja", "SępKasztanowatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SępKasztanowatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SępKasztanowatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("SępPłowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SępPłowy");
            intent.putExtra("Status", "SępPłowyStatus");
            intent.putExtra("Notatka", "SępPłowyNotatka");
            intent.putExtra("Złapany", "SępPłowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SępPłowyDataZłapania");
            intent.putExtra("Lokalizacja", "SępPłowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SępPłowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SępPłowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sierpówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Sierpówka");
            intent.putExtra("Status", "SierpówkaStatus");
            intent.putExtra("Notatka", "SierpówkaNotatka");
            intent.putExtra("Złapany", "SierpówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SierpówkaDataZłapania");
            intent.putExtra("Lokalizacja", "SierpówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SierpówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SierpówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sieweczka mongolska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SieweczkaMongolska");
            intent.putExtra("Status", "SieweczkaMongolskaStatus");
            intent.putExtra("Notatka", "SieweczkaMongolskaNotatka");
            intent.putExtra("Złapany", "SieweczkaMongolskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SieweczkaMongolskaDataZłapania");
            intent.putExtra("Lokalizacja", "SieweczkaMongolskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SieweczkaMongolskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SieweczkaMongolskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sieweczka morska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SieweczkaMorska");
            intent.putExtra("Status", "SieweczkaMorskaStatus");
            intent.putExtra("Notatka", "SieweczkaMorskaNotatka");
            intent.putExtra("Złapany", "SieweczkaMorskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SieweczkaMorskaDataZłapania");
            intent.putExtra("Lokalizacja", "SieweczkaMorskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SieweczkaMorskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SieweczkaMorskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sieweczka obrożna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SieweczkaObrożna");
            intent.putExtra("Status", "SieweczkaObrożnaStatus");
            intent.putExtra("Notatka", "SieweczkaObrożnaNotatka");
            intent.putExtra("Złapany", "SieweczkaObrożnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SieweczkaObrożnaDataZłapania");
            intent.putExtra("Lokalizacja", "SieweczkaObrożnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SieweczkaObrożnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SieweczkaObrożnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sieweczka pustynna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SieweczkaPustynna");
            intent.putExtra("Status", "SieweczkaPustynnaStatus");
            intent.putExtra("Notatka", "SieweczkaPustynnaNotatka");
            intent.putExtra("Złapany", "SieweczkaPustynnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SieweczkaPustynnaDataZłapania");
            intent.putExtra("Lokalizacja", "SieweczkaPustynnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SieweczkaPustynnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SieweczkaPustynnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sieweczka rzeczna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SieweczkaRzeczna");
            intent.putExtra("Status", "SieweczkaRzecznaStatus");
            intent.putExtra("Notatka", "SieweczkaRzecznaNotatka");
            intent.putExtra("Złapany", "SieweczkaRzecznaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SieweczkaRzecznaDataZłapania");
            intent.putExtra("Lokalizacja", "SieweczkaRzecznaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SieweczkaRzecznaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SieweczkaRzecznaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Siewka szara")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SiewkaSzara");
            intent.putExtra("Status", "SiewkaSzaraStatus");
            intent.putExtra("Notatka", "SiewkaSzaraNotatka");
            intent.putExtra("Złapany", "SiewkaSzaraZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SiewkaSzaraDataZłapania");
            intent.putExtra("Lokalizacja", "SiewkaSzaraMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SiewkaSzaraMaZdjęcie");
            intent.putExtra("Zdjęcie", "SiewkaSzaraZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Siewka złota")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SiewkaZłota");
            intent.putExtra("Status", "SiewkaZłotaStatus");
            intent.putExtra("Notatka", "SiewkaZłotaNotatka");
            intent.putExtra("Złapany", "SiewkaZłotaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SiewkaZłotaDataZłapania");
            intent.putExtra("Lokalizacja", "SiewkaZłotaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SiewkaZłotaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SiewkaZłotaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Siewka złotawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SiewkaZłotawa");
            intent.putExtra("Status", "SiewkaZłotawaStatus");
            intent.putExtra("Notatka", "SiewkaZłotawaNotatka");
            intent.putExtra("Złapany", "SiewkaZłotawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SiewkaZłotawaDataZłapania");
            intent.putExtra("Lokalizacja", "SiewkaZłotawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SiewkaZłotawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SiewkaZłotawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Siewnica")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Siewnica");
            intent.putExtra("Status", "SiewnicaStatus");
            intent.putExtra("Notatka", "SiewnicaNotatka");
            intent.putExtra("Złapany", "SiewnicaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SiewnicaDataZłapania");
            intent.putExtra("Lokalizacja", "SiewnicaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SiewnicaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SiewnicaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sikora lazurowa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SikoraLazurowa");
            intent.putExtra("Status", "SikoraLazurowaStatus");
            intent.putExtra("Notatka", "SikoraLazurowaNotatka");
            intent.putExtra("Złapany", "SikoraLazurowaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SikoraLazurowaDataZłapania");
            intent.putExtra("Lokalizacja", "SikoraLazurowaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SikoraLazurowaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SikoraLazurowaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sikora uboga")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SikoraUboga");
            intent.putExtra("Status", "SikoraUbogaStatus");
            intent.putExtra("Notatka", "SikoraUbogaNotatka");
            intent.putExtra("Złapany", "SikoraUbogaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SikoraUbogaDataZłapania");
            intent.putExtra("Lokalizacja", "SikoraUbogaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SikoraUbogaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SikoraUbogaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Siniak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Siniak");
            intent.putExtra("Status", "SiniakStatus");
            intent.putExtra("Notatka", "SiniakNotatka");
            intent.putExtra("Złapany", "SiniakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SiniakDataZłapania");
            intent.putExtra("Lokalizacja", "SiniakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SiniakMaZdjęcie");
            intent.putExtra("Zdjęcie", "SiniakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Siwerniak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Siwerniak");
            intent.putExtra("Status", "SiwerniakStatus");
            intent.putExtra("Notatka", "SiwerniakNotatka");
            intent.putExtra("Złapany", "SiwerniakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SiwerniakDataZłapania");
            intent.putExtra("Lokalizacja", "SiwerniakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SiwerniakMaZdjęcie");
            intent.putExtra("Zdjęcie", "SiwerniakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Skowrończyk krótkopalcowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SkowrończykKrótkopalcowy");
            intent.putExtra("Status", "SkowrończykKrótkopalcowyStatus");
            intent.putExtra("Notatka", "SkowrończykKrótkopalcowyNotatka");
            intent.putExtra("Złapany", "SkowrończykKrótkopalcowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SkowrończykKrótkopalcowyDataZłapania");
            intent.putExtra("Lokalizacja", "SkowrończykKrótkopalcowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SkowrończykKrótkopalcowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SkowrończykKrótkopalcowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Skowronek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Skowronek");
            intent.putExtra("Status", "SkowronekStatus");
            intent.putExtra("Notatka", "SkowronekNotatka");
            intent.putExtra("Złapany", "SkowronekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SkowronekDataZłapania");
            intent.putExtra("Lokalizacja", "SkowronekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SkowronekMaZdjęcie");
            intent.putExtra("Zdjęcie", "SkowronekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Skowronek białoskrzydły")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SkowronekBiałoskrzydły");
            intent.putExtra("Status", "SkowronekBiałoskrzydłyStatus");
            intent.putExtra("Notatka", "SkowronekBiałoskrzydłyNotatka");
            intent.putExtra("Złapany", "SkowronekBiałoskrzydłyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SkowronekBiałoskrzydłyDataZłapania");
            intent.putExtra("Lokalizacja", "SkowronekBiałoskrzydłyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SkowronekBiałoskrzydłyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SkowronekBiałoskrzydłyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Słonka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Słonka");
            intent.putExtra("Status", "SłonkaStatus");
            intent.putExtra("Notatka", "SłonkaNotatka");
            intent.putExtra("Złapany", "SłonkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SłonkaDataZłapania");
            intent.putExtra("Lokalizacja", "SłonkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SłonkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SłonkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Słowik rdzawy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SłowikRdzawy");
            intent.putExtra("Status", "SłowikRdzawyStatus");
            intent.putExtra("Notatka", "SłowikRdzawyNotatka");
            intent.putExtra("Złapany", "SłowikRdzawyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SłowikRdzawyDataZłapania");
            intent.putExtra("Lokalizacja", "SłowikRdzawyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SłowikRdzawyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SłowikRdzawyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Słowik syberyjski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SłowikSyberyjski");
            intent.putExtra("Status", "SłowikSyberyjskiStatus");
            intent.putExtra("Notatka", "SłowikSyberyjskiNotatka");
            intent.putExtra("Złapany", "SłowikSyberyjskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SłowikSyberyjskiDataZłapania");
            intent.putExtra("Lokalizacja", "SłowikSyberyjskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SłowikSyberyjskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "SłowikSyberyjskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Słowik szary")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SłowikSzary");
            intent.putExtra("Status", "SłowikSzaryStatus");
            intent.putExtra("Notatka", "SłowikSzaryNotatka");
            intent.putExtra("Złapany", "SłowikSzaryZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SłowikSzaryDataZłapania");
            intent.putExtra("Lokalizacja", "SłowikSzaryMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SłowikSzaryMaZdjęcie");
            intent.putExtra("Zdjęcie", "SłowikSzaryZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sokół skalny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SokółSkalny");
            intent.putExtra("Status", "SokółSkalnyStatus");
            intent.putExtra("Notatka", "SokółSkalnyNotatka");
            intent.putExtra("Złapany", "SokółSkalnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SokółSkalnyDataZłapania");
            intent.putExtra("Lokalizacja", "SokółSkalnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SokółSkalnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SokółSkalnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sokół wędrowny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SokółWędrowny");
            intent.putExtra("Status", "SokółWędrownyStatus");
            intent.putExtra("Notatka", "SokółWędrownyNotatka");
            intent.putExtra("Złapany", "SokółWędrownyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SokółWędrownyDataZłapania");
            intent.putExtra("Lokalizacja", "SokółWędrownyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SokółWędrownyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SokółWędrownyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sosnówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Sosnówka");
            intent.putExtra("Status", "SosnówkaStatus");
            intent.putExtra("Notatka", "SosnówkaNotatka");
            intent.putExtra("Złapany", "SosnówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SosnówkaDataZłapania");
            intent.putExtra("Lokalizacja", "SosnówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SosnówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SosnówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sowa jarzębata")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SowaJarzębata");
            intent.putExtra("Status", "SowaJarzębataStatus");
            intent.putExtra("Notatka", "SowaJarzębataNotatka");
            intent.putExtra("Złapany", "SowaJarzębataZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SowaJarzębataDataZłapania");
            intent.putExtra("Lokalizacja", "SowaJarzębataMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SowaJarzębataMaZdjęcie");
            intent.putExtra("Zdjęcie", "SowaJarzębataZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sowa śnieżna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SowaŚnieżna");
            intent.putExtra("Status", "SowaŚnieżnaStatus");
            intent.putExtra("Notatka", "SowaŚnieżnaNotatka");
            intent.putExtra("Złapany", "SowaŚnieżnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SowaŚnieżnaDataZłapania");
            intent.putExtra("Lokalizacja", "SowaŚnieżnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SowaŚnieżnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SowaŚnieżnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sójka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Sójka");
            intent.putExtra("Status", "SójkaStatus");
            intent.putExtra("Notatka", "SójkaNotatka");
            intent.putExtra("Złapany", "SójkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SójkaDataZłapania");
            intent.putExtra("Lokalizacja", "SójkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SójkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SójkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sójka syberyjska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SójkaSyberyjska");
            intent.putExtra("Status", "SójkaSyberyjskaStatus");
            intent.putExtra("Notatka", "SójkaSyberyjskaNotatka");
            intent.putExtra("Złapany", "SójkaSyberyjskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SójkaSyberyjskaDataZłapania");
            intent.putExtra("Lokalizacja", "SójkaSyberyjskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SójkaSyberyjskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SójkaSyberyjskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sóweczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Sóweczka");
            intent.putExtra("Status", "SóweczkaStatus");
            intent.putExtra("Notatka", "SóweczkaNotatka");
            intent.putExtra("Złapany", "SóweczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SóweczkaDataZłapania");
            intent.putExtra("Lokalizacja", "SóweczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SóweczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SóweczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sroka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Sroka");
            intent.putExtra("Status", "SrokaStatus");
            intent.putExtra("Notatka", "SrokaNotatka");
            intent.putExtra("Złapany", "SrokaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SrokaDataZłapania");
            intent.putExtra("Lokalizacja", "SrokaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SrokaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SrokaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Srokosz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Srokosz");
            intent.putExtra("Status", "SrokoszStatus");
            intent.putExtra("Notatka", "SrokoszNotatka");
            intent.putExtra("Złapany", "SrokoszZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SrokoszDataZłapania");
            intent.putExtra("Lokalizacja", "SrokoszMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SrokoszMaZdjęcie");
            intent.putExtra("Zdjęcie", "SrokoszZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sterniczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Sterniczka");
            intent.putExtra("Status", "SterniczkaStatus");
            intent.putExtra("Notatka", "SterniczkaNotatka");
            intent.putExtra("Złapany", "SterniczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SterniczkaDataZłapania");
            intent.putExtra("Lokalizacja", "SterniczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SterniczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SterniczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Sterniczka jamajska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SterniczkaJamajska");
            intent.putExtra("Status", "SterniczkaJamajskaStatus");
            intent.putExtra("Notatka", "SterniczkaJamajskaNotatka");
            intent.putExtra("Złapany", "SterniczkaJamajskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SterniczkaJamajskaDataZłapania");
            intent.putExtra("Lokalizacja", "SterniczkaJamajskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SterniczkaJamajskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "SterniczkaJamajskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Strepet")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Strepet");
            intent.putExtra("Status", "StrepetStatus");
            intent.putExtra("Notatka", "StrepetNotatka");
            intent.putExtra("Złapany", "StrepetZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "StrepetDataZłapania");
            intent.putExtra("Lokalizacja", "StrepetMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "StrepetMaZdjęcie");
            intent.putExtra("Zdjęcie", "StrepetZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Strumieniówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Strumieniówka");
            intent.putExtra("Status", "StrumieniówkaStatus");
            intent.putExtra("Notatka", "StrumieniówkaNotatka");
            intent.putExtra("Złapany", "StrumieniówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "StrumieniówkaDataZłapania");
            intent.putExtra("Lokalizacja", "StrumieniówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "StrumieniówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "StrumieniówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Strzyżyk")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Strzyżyk");
            intent.putExtra("Status", "StrzyżykStatus");
            intent.putExtra("Notatka", "StrzyżykNotatka");
            intent.putExtra("Złapany", "StrzyżykZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "StrzyżykDataZłapania");
            intent.putExtra("Lokalizacja", "StrzyżykMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "StrzyżykMaZdjęcie");
            intent.putExtra("Zdjęcie", "StrzyżykZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Syczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Syczek");
            intent.putExtra("Status", "SyczekStatus");
            intent.putExtra("Notatka", "SyczekNotatka");
            intent.putExtra("Złapany", "SyczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SyczekDataZłapania");
            intent.putExtra("Lokalizacja", "SyczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SyczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "SyczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szablodziób")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Szablodziób");
            intent.putExtra("Status", "SzablodzióbStatus");
            intent.putExtra("Notatka", "SzablodzióbNotatka");
            intent.putExtra("Złapany", "SzablodzióbZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzablodzióbDataZłapania");
            intent.putExtra("Lokalizacja", "SzablodzióbMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzablodzióbMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzablodzióbZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szczudłak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Szczudłak");
            intent.putExtra("Status", "SzczudłakStatus");
            intent.putExtra("Notatka", "SzczudłakNotatka");
            intent.putExtra("Złapany", "SzczudłakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzczudłakDataZłapania");
            intent.putExtra("Lokalizacja", "SzczudłakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzczudłakMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzczudłakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szczygieł")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Szczygieł");
            intent.putExtra("Status", "SzczygiełStatus");
            intent.putExtra("Notatka", "SzczygiełNotatka");
            intent.putExtra("Złapany", "SzczygiełZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzczygiełDataZłapania");
            intent.putExtra("Lokalizacja", "SzczygiełMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzczygiełMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzczygiełZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szlachar")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Szlachar");
            intent.putExtra("Status", "SzlacharStatus");
            intent.putExtra("Notatka", "SzlacharNotatka");
            intent.putExtra("Złapany", "SzlacharZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzlacharDataZłapania");
            intent.putExtra("Lokalizacja", "SzlacharMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzlacharMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzlacharZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szlamiec długodzioby")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "SzlamiecDługodzioby");
            intent.putExtra("Status", "SzlamiecDługodziobyStatus");
            intent.putExtra("Notatka", "SzlamiecDługodziobyNotatka");
            intent.putExtra("Złapany", "SzlamiecDługodziobyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzlamiecDługodziobyDataZłapania");
            intent.putExtra("Lokalizacja", "SzlamiecDługodziobyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzlamiecDługodziobyMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzlamiecDługodziobyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szlamnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Szlamnik");
            intent.putExtra("Status", "SzlamnikStatus");
            intent.putExtra("Notatka", "SzlamnikNotatka");
            intent.putExtra("Złapany", "SzlamnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzlamnikDataZłapania");
            intent.putExtra("Lokalizacja", "SzlamnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzlamnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzlamnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Szpak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Szpak");
            intent.putExtra("Status", "SzpakStatus");
            intent.putExtra("Notatka", "SzpakNotatka");
            intent.putExtra("Złapany", "SzpakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "SzpakDataZłapania");
            intent.putExtra("Lokalizacja", "SzpakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "SzpakMaZdjęcie");
            intent.putExtra("Zdjęcie", "SzpakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ścierwnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Ścierwnik");
            intent.putExtra("Status", "ŚcierwnikStatus");
            intent.putExtra("Notatka", "ŚcierwnikNotatka");
            intent.putExtra("Złapany", "ŚcierwnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚcierwnikDataZłapania");
            intent.putExtra("Lokalizacja", "ŚcierwnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚcierwnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚcierwnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Ślepowron")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Ślepowron");
            intent.putExtra("Status", "ŚlepowronStatus");
            intent.putExtra("Notatka", "ŚlepowronNotatka");
            intent.putExtra("Złapany", "ŚlepowronZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚlepowronDataZłapania");
            intent.putExtra("Lokalizacja", "ŚlepowronMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚlepowronMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚlepowronZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Śmieszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Śmieszka");
            intent.putExtra("Status", "ŚmieszkaStatus");
            intent.putExtra("Notatka", "ŚmieszkaNotatka");
            intent.putExtra("Złapany", "ŚmieszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚmieszkaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚmieszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚmieszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚmieszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Śnieguła")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Śnieguła");
            intent.putExtra("Status", "ŚniegułaStatus");
            intent.putExtra("Notatka", "ŚniegułaNotatka");
            intent.putExtra("Złapany", "ŚniegułaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚniegułaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚniegułaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚniegułaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚniegułaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Śnieżka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Śnieżka");
            intent.putExtra("Status", "ŚnieżkaStatus");
            intent.putExtra("Notatka", "ŚnieżkaNotatka");
            intent.putExtra("Złapany", "ŚnieżkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚnieżkaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚnieżkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚnieżkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚnieżkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Śpiewak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Śpiewak");
            intent.putExtra("Status", "ŚpiewakStatus");
            intent.putExtra("Notatka", "ŚpiewakNotatka");
            intent.putExtra("Złapany", "ŚpiewakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚpiewakDataZłapania");
            intent.putExtra("Lokalizacja", "ŚpiewakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚpiewakMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚpiewakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek drzewny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekDrzewny");
            intent.putExtra("Status", "ŚwiergotekDrzewnyStatus");
            intent.putExtra("Notatka", "ŚwiergotekDrzewnyNotatka");
            intent.putExtra("Złapany", "ŚwiergotekDrzewnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekDrzewnyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekDrzewnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekDrzewnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekDrzewnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek łąkowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekŁąkowy");
            intent.putExtra("Status", "ŚwiergotekŁąkowyStatus");
            intent.putExtra("Notatka", "ŚwiergotekŁąkowyNotatka");
            intent.putExtra("Złapany", "ŚwiergotekŁąkowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekŁąkowyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekŁąkowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekŁąkowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekŁąkowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek nadmorski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekNadmorski");
            intent.putExtra("Status", "ŚwiergotekNadmorskiStatus");
            intent.putExtra("Notatka", "ŚwiergotekNadmorskiNotatka");
            intent.putExtra("Złapany", "ŚwiergotekNadmorskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekNadmorskiDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekNadmorskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekNadmorskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekNadmorskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek polny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekPolny");
            intent.putExtra("Status", "ŚwiergotekPolnyStatus");
            intent.putExtra("Notatka", "ŚwiergotekPolnyNotatka");
            intent.putExtra("Złapany", "ŚwiergotekPolnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekPolnyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekPolnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekPolnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekPolnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek rdzawogardły")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekRdzawogardły");
            intent.putExtra("Status", "ŚwiergotekRdzawogardłyStatus");
            intent.putExtra("Notatka", "ŚwiergotekRdzawogardłyNotatka");
            intent.putExtra("Złapany", "ŚwiergotekRdzawogardłyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekRdzawogardłyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekRdzawogardłyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekRdzawogardłyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekRdzawogardłyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek szponiasty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekSzponiasty");
            intent.putExtra("Status", "ŚwiergotekSzponiastyStatus");
            intent.putExtra("Notatka", "ŚwiergotekSzponiastyNotatka");
            intent.putExtra("Złapany", "ŚwiergotekSzponiastyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekSzponiastyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekSzponiastyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekSzponiastyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekSzponiastyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świergotek tajgowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwiergotekTajgowy");
            intent.putExtra("Status", "ŚwiergotekTajgowyStatus");
            intent.putExtra("Notatka", "ŚwiergotekTajgowyNotatka");
            intent.putExtra("Złapany", "ŚwiergotekTajgowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwiergotekTajgowyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwiergotekTajgowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwiergotekTajgowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwiergotekTajgowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świerszczak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Świerszczak");
            intent.putExtra("Status", "ŚwierszczakStatus");
            intent.putExtra("Notatka", "ŚwierszczakNotatka");
            intent.putExtra("Złapany", "ŚwierszczakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwierszczakDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwierszczakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwierszczakMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwierszczakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świerszczak melodyjny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwierszczakMelodyjny");
            intent.putExtra("Status", "ŚwierszczakMelodyjnyStatus");
            intent.putExtra("Notatka", "ŚwierszczakMelodyjnyNotatka");
            intent.putExtra("Złapany", "ŚwierszczakMelodyjnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwierszczakMelodyjnyDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwierszczakMelodyjnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwierszczakMelodyjnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwierszczakMelodyjnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstun")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Świstun");
            intent.putExtra("Status", "ŚwistunStatus");
            intent.putExtra("Notatka", "ŚwistunNotatka");
            intent.putExtra("Złapany", "ŚwistunZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstun amerykański")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunAmerykański");
            intent.putExtra("Status", "ŚwistunAmerykańskiStatus");
            intent.putExtra("Notatka", "ŚwistunAmerykańskiNotatka");
            intent.putExtra("Złapany", "ŚwistunAmerykańskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunAmerykańskiDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunAmerykańskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunAmerykańskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunAmerykańskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka ałtańska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaAłtańska");
            intent.putExtra("Status", "ŚwistunkaAłtańskaStatus");
            intent.putExtra("Notatka", "ŚwistunkaAłtańskaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaAłtańskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaAłtańskaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaAłtańskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaAłtańskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaAłtańskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka brunatna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaBrunatna");
            intent.putExtra("Status", "ŚwistunkaBrunatnaStatus");
            intent.putExtra("Notatka", "ŚwistunkaBrunatnaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaBrunatnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaBrunatnaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaBrunatnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaBrunatnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaBrunatnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka górska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaGórska");
            intent.putExtra("Status", "ŚwistunkaGórskaStatus");
            intent.putExtra("Notatka", "ŚwistunkaGórskaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaGórskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaGórskaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaGórskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaGórskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaGórskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka grubodzioba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaGrubodzioba");
            intent.putExtra("Status", "ŚwistunkaGrubodziobaStatus");
            intent.putExtra("Notatka", "ŚwistunkaGrubodziobaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaGrubodziobaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaGrubodziobaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaGrubodziobaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaGrubodziobaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaGrubodziobaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka iberyjska")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaIberyjska");
            intent.putExtra("Status", "ŚwistunkaIberyjskaStatus");
            intent.putExtra("Notatka", "ŚwistunkaIberyjskaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaIberyjskaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaIberyjskaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaIberyjskaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaIberyjskaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaIberyjskaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka leśna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaLeśna");
            intent.putExtra("Status", "ŚwistunkaLeśnaStatus");
            intent.putExtra("Notatka", "ŚwistunkaLeśnaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaLeśnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaLeśnaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaLeśnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaLeśnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaLeśnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka północna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaPółnocna");
            intent.putExtra("Status", "ŚwistunkaPółnocnaStatus");
            intent.putExtra("Notatka", "ŚwistunkaPółnocnaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaPółnocnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaPółnocnaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaPółnocnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaPółnocnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaPółnocnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka złotawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaZłotawa");
            intent.putExtra("Status", "ŚwistunkaZłotawaStatus");
            intent.putExtra("Notatka", "ŚwistunkaZłotawaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaZłotawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaZłotawaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaZłotawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaZłotawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaZłotawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Świstunka żółtawa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŚwistunkaŻółtawa");
            intent.putExtra("Status", "ŚwistunkaŻółtawaStatus");
            intent.putExtra("Notatka", "ŚwistunkaŻółtawaNotatka");
            intent.putExtra("Złapany", "ŚwistunkaŻółtawaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŚwistunkaŻółtawaDataZłapania");
            intent.putExtra("Lokalizacja", "ŚwistunkaŻółtawaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŚwistunkaŻółtawaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŚwistunkaŻółtawaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Tamaryszka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Tamaryszka");
            intent.putExtra("Status", "TamaryszkaStatus");
            intent.putExtra("Notatka", "TamaryszkaNotatka");
            intent.putExtra("Złapany", "TamaryszkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TamaryszkaDataZłapania");
            intent.putExtra("Lokalizacja", "TamaryszkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TamaryszkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "TamaryszkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Terekia")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Terekia");
            intent.putExtra("Status", "TerekiaStatus");
            intent.putExtra("Notatka", "TerekiaNotatka");
            intent.putExtra("Złapany", "TerekiaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TerekiaDataZłapania");
            intent.putExtra("Lokalizacja", "TerekiaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TerekiaMaZdjęcie");
            intent.putExtra("Zdjęcie", "TerekiaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trzciniak")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Trzciniak");
            intent.putExtra("Status", "TrzciniakStatus");
            intent.putExtra("Notatka", "TrzciniakNotatka");
            intent.putExtra("Złapany", "TrzciniakZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrzciniakDataZłapania");
            intent.putExtra("Lokalizacja", "TrzciniakMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrzciniakMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrzciniakZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trzcinniczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Trzcinniczek");
            intent.putExtra("Status", "TrzcinniczekStatus");
            intent.putExtra("Notatka", "TrzcinniczekNotatka");
            intent.putExtra("Złapany", "TrzcinniczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrzcinniczekDataZłapania");
            intent.putExtra("Lokalizacja", "TrzcinniczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrzcinniczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrzcinniczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trzcinniczek kaspijski")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TrzcinniczekKaspijski");
            intent.putExtra("Status", "TrzcinniczekKaspijskiStatus");
            intent.putExtra("Notatka", "TrzcinniczekKaspijskiNotatka");
            intent.putExtra("Złapany", "TrzcinniczekKaspijskiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrzcinniczekKaspijskiDataZłapania");
            intent.putExtra("Lokalizacja", "TrzcinniczekKaspijskiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrzcinniczekKaspijskiMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrzcinniczekKaspijskiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trzmielojad")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Trzmielojad");
            intent.putExtra("Status", "TrzmielojadStatus");
            intent.putExtra("Notatka", "TrzmielojadNotatka");
            intent.putExtra("Złapany", "TrzmielojadZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrzmielojadDataZłapania");
            intent.putExtra("Lokalizacja", "TrzmielojadMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrzmielojadMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrzmielojadZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadel")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Trznadel");
            intent.putExtra("Status", "TrznadelStatus");
            intent.putExtra("Notatka", "TrznadelNotatka");
            intent.putExtra("Złapany", "TrznadelZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadel białogłowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TrznadelBiałogłowy");
            intent.putExtra("Status", "TrznadelBiałogłowyStatus");
            intent.putExtra("Notatka", "TrznadelBiałogłowyNotatka");
            intent.putExtra("Złapany", "TrznadelBiałogłowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelBiałogłowyDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelBiałogłowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelBiałogłowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelBiałogłowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadel czarnogłowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TrznadelCzarnogłowy");
            intent.putExtra("Status", "TrznadelCzarnogłowyStatus");
            intent.putExtra("Notatka", "TrznadelCzarnogłowyNotatka");
            intent.putExtra("Złapany", "TrznadelCzarnogłowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelCzarnogłowyDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelCzarnogłowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelCzarnogłowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelCzarnogłowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadel czubaty")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TrznadelCzubaty");
            intent.putExtra("Status", "TrznadelCzubatyStatus");
            intent.putExtra("Notatka", "TrznadelCzubatyNotatka");
            intent.putExtra("Złapany", "TrznadelCzubatyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelCzubatyDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelCzubatyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelCzubatyMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelCzubatyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadelek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Trznadelek");
            intent.putExtra("Status", "TrznadelekStatus");
            intent.putExtra("Notatka", "TrznadelekNotatka");
            intent.putExtra("Złapany", "TrznadelekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelekDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelekMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadel złotawy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TrznadelZłotawy");
            intent.putExtra("Status", "TrznadelZłotawyStatus");
            intent.putExtra("Notatka", "TrznadelZłotawyNotatka");
            intent.putExtra("Złapany", "TrznadelZłotawyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelZłotawyDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelZłotawyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelZłotawyMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelZłotawyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Trznadel złotobrewy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TrznadelZłotobrewy");
            intent.putExtra("Status", "TrznadelZłotobrewyStatus");
            intent.putExtra("Notatka", "TrznadelZłotobrewyNotatka");
            intent.putExtra("Złapany", "TrznadelZłotobrewyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TrznadelZłotobrewyDataZłapania");
            intent.putExtra("Lokalizacja", "TrznadelZłotobrewyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TrznadelZłotobrewyMaZdjęcie");
            intent.putExtra("Zdjęcie", "TrznadelZłotobrewyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Turkan")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Turkan");
            intent.putExtra("Status", "TurkanStatus");
            intent.putExtra("Notatka", "TurkanNotatka");
            intent.putExtra("Złapany", "TurkanZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TurkanDataZłapania");
            intent.putExtra("Lokalizacja", "TurkanMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TurkanMaZdjęcie");
            intent.putExtra("Zdjęcie", "TurkanZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Turkawka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Turkawka");
            intent.putExtra("Status", "TurkawkaStatus");
            intent.putExtra("Notatka", "TurkawkaNotatka");
            intent.putExtra("Złapany", "TurkawkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TurkawkaDataZłapania");
            intent.putExtra("Lokalizacja", "TurkawkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TurkawkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "TurkawkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Turkawka wschodnia")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "TurkawkaWschodnia");
            intent.putExtra("Status", "TurkawkaWschodniaStatus");
            intent.putExtra("Notatka", "TurkawkaWschodniaNotatka");
            intent.putExtra("Złapany", "TurkawkaWschodniaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "TurkawkaWschodniaDataZłapania");
            intent.putExtra("Lokalizacja", "TurkawkaWschodniaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "TurkawkaWschodniaMaZdjęcie");
            intent.putExtra("Zdjęcie", "TurkawkaWschodniaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Uhla")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Uhla");
            intent.putExtra("Status", "UhlaStatus");
            intent.putExtra("Notatka", "UhlaNotatka");
            intent.putExtra("Złapany", "UhlaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "UhlaDataZłapania");
            intent.putExtra("Lokalizacja", "UhlaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "UhlaMaZdjęcie");
            intent.putExtra("Zdjęcie", "UhlaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Uhla garbonosa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "UhlaGarbonosa");
            intent.putExtra("Status", "UhlaGarbonosaStatus");
            intent.putExtra("Notatka", "UhlaGarbonosaNotatka");
            intent.putExtra("Złapany", "UhlaGarbonosaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "UhlaGarbonosaDataZłapania");
            intent.putExtra("Lokalizacja", "UhlaGarbonosaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "UhlaGarbonosaMaZdjęcie");
            intent.putExtra("Zdjęcie", "UhlaGarbonosaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Uszatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Uszatka");
            intent.putExtra("Status", "UszatkaStatus");
            intent.putExtra("Notatka", "UszatkaNotatka");
            intent.putExtra("Złapany", "UszatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "UszatkaDataZłapania");
            intent.putExtra("Lokalizacja", "UszatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "UszatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "UszatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Uszatka błotna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "UszatkaBłotna");
            intent.putExtra("Status", "UszatkaBłotnaStatus");
            intent.putExtra("Notatka", "UszatkaBłotnaNotatka");
            intent.putExtra("Złapany", "UszatkaBłotnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "UszatkaBłotnaDataZłapania");
            intent.putExtra("Lokalizacja", "UszatkaBłotnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "UszatkaBłotnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "UszatkaBłotnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Warzęcha")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Warzęcha");
            intent.putExtra("Status", "WarzęchaStatus");
            intent.putExtra("Notatka", "WarzęchaNotatka");
            intent.putExtra("Złapany", "WarzęchaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WarzęchaDataZłapania");
            intent.putExtra("Lokalizacja", "WarzęchaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WarzęchaMaZdjęcie");
            intent.putExtra("Zdjęcie", "WarzęchaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wąsatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wąsatka");
            intent.putExtra("Status", "WąsatkaStatus");
            intent.putExtra("Notatka", "WąsatkaNotatka");
            intent.putExtra("Złapany", "WąsatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WąsatkaDataZłapania");
            intent.putExtra("Lokalizacja", "WąsatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WąsatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "WąsatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wieszczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wieszczek");
            intent.putExtra("Status", "WieszczekStatus");
            intent.putExtra("Notatka", "WieszczekNotatka");
            intent.putExtra("Złapany", "WieszczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WieszczekDataZłapania");
            intent.putExtra("Lokalizacja", "WieszczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WieszczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "WieszczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wilga")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wilga");
            intent.putExtra("Status", "WilgaStatus");
            intent.putExtra("Notatka", "WilgaNotatka");
            intent.putExtra("Złapany", "WilgaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WilgaDataZłapania");
            intent.putExtra("Lokalizacja", "WilgaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WilgaMaZdjęcie");
            intent.putExtra("Zdjęcie", "WilgaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wireonek czerwonooki")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WireonekCzerwonooki");
            intent.putExtra("Status", "WireonekCzerwonookiStatus");
            intent.putExtra("Notatka", "WireonekCzerwonookiNotatka");
            intent.putExtra("Złapany", "WireonekCzerwonookiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WireonekCzerwonookiDataZłapania");
            intent.putExtra("Lokalizacja", "WireonekCzerwonookiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WireonekCzerwonookiMaZdjęcie");
            intent.putExtra("Zdjęcie", "WireonekCzerwonookiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Włochatka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Włochatka");
            intent.putExtra("Status", "WłochatkaStatus");
            intent.putExtra("Notatka", "WłochatkaNotatka");
            intent.putExtra("Złapany", "WłochatkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WłochatkaDataZłapania");
            intent.putExtra("Lokalizacja", "WłochatkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WłochatkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "WłochatkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wodniczka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wodniczka");
            intent.putExtra("Status", "WodniczkaStatus");
            intent.putExtra("Notatka", "WodniczkaNotatka");
            intent.putExtra("Złapany", "WodniczkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WodniczkaDataZłapania");
            intent.putExtra("Lokalizacja", "WodniczkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WodniczkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "WodniczkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wodnik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wodnik");
            intent.putExtra("Status", "WodnikStatus");
            intent.putExtra("Notatka", "WodnikNotatka");
            intent.putExtra("Złapany", "WodnikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WodnikDataZłapania");
            intent.putExtra("Lokalizacja", "WodnikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WodnikMaZdjęcie");
            intent.putExtra("Zdjęcie", "WodnikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wójcik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wójcik");
            intent.putExtra("Status", "WójcikStatus");
            intent.putExtra("Notatka", "WójcikNotatka");
            intent.putExtra("Złapany", "WójcikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WójcikDataZłapania");
            intent.putExtra("Lokalizacja", "WójcikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WójcikMaZdjęcie");
            intent.putExtra("Zdjęcie", "WójcikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wróbel")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Wróbel");
            intent.putExtra("Status", "WróbelStatus");
            intent.putExtra("Notatka", "WróbelNotatka");
            intent.putExtra("Złapany", "WróbelZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WróbelDataZłapania");
            intent.putExtra("Lokalizacja", "WróbelMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WróbelMaZdjęcie");
            intent.putExtra("Zdjęcie", "WróbelZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wróbel skalny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WróbelSkalny");
            intent.putExtra("Status", "WróbelSkalnyStatus");
            intent.putExtra("Notatka", "WróbelSkalnyNotatka");
            intent.putExtra("Złapany", "WróbelSkalnyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WróbelSkalnyDataZłapania");
            intent.putExtra("Lokalizacja", "WróbelSkalnyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WróbelSkalnyMaZdjęcie");
            intent.putExtra("Zdjęcie", "WróbelSkalnyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wrona siwa")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WronaSiwa");
            intent.putExtra("Status", "WronaSiwaStatus");
            intent.putExtra("Notatka", "WronaSiwaNotatka");
            intent.putExtra("Złapany", "WronaSiwaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WronaSiwaDataZłapania");
            intent.putExtra("Lokalizacja", "WronaSiwaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WronaSiwaMaZdjęcie");
            intent.putExtra("Zdjęcie", "WronaSiwaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wydrzyk długosterny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WydrzykDługosterny");
            intent.putExtra("Status", "WydrzykDługosternyStatus");
            intent.putExtra("Notatka", "WydrzykDługosternyNotatka");
            intent.putExtra("Złapany", "WydrzykDługosternyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WydrzykDługosternyDataZłapania");
            intent.putExtra("Lokalizacja", "WydrzykDługosternyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WydrzykDługosternyMaZdjęcie");
            intent.putExtra("Zdjęcie", "WydrzykDługosternyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wydrzyk ostrosterny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WydrzykOstrosterny");
            intent.putExtra("Status", "WydrzykOstrosternyStatus");
            intent.putExtra("Notatka", "WydrzykOstrosternyNotatka");
            intent.putExtra("Złapany", "WydrzykOstrosternyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WydrzykOstrosternyDataZłapania");
            intent.putExtra("Lokalizacja", "WydrzykOstrosternyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WydrzykOstrosternyMaZdjęcie");
            intent.putExtra("Zdjęcie", "WydrzykOstrosternyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wydrzyk tęposterny")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WydrzykTęposterny");
            intent.putExtra("Status", "WydrzykTęposternyStatus");
            intent.putExtra("Notatka", "WydrzykTęposternyNotatka");
            intent.putExtra("Złapany", "WydrzykTęposternyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WydrzykTęposternyDataZłapania");
            intent.putExtra("Lokalizacja", "WydrzykTęposternyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WydrzykTęposternyMaZdjęcie");
            intent.putExtra("Zdjęcie", "WydrzykTęposternyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Wydrzyk wielki")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "WydrzykWielki");
            intent.putExtra("Status", "WydrzykWielkiStatus");
            intent.putExtra("Notatka", "WydrzykWielkiNotatka");
            intent.putExtra("Złapany", "WydrzykWielkiZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "WydrzykWielkiDataZłapania");
            intent.putExtra("Lokalizacja", "WydrzykWielkiMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "WydrzykWielkiMaZdjęcie");
            intent.putExtra("Zdjęcie", "WydrzykWielkiZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zaganiacz")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zaganiacz");
            intent.putExtra("Status", "ZaganiaczStatus");
            intent.putExtra("Notatka", "ZaganiaczNotatka");
            intent.putExtra("Złapany", "ZaganiaczZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZaganiaczDataZłapania");
            intent.putExtra("Lokalizacja", "ZaganiaczMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZaganiaczMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZaganiaczZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zaganiacz mały")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ZaganiaczMały");
            intent.putExtra("Status", "ZaganiaczMałyStatus");
            intent.putExtra("Notatka", "ZaganiaczMałyNotatka");
            intent.putExtra("Złapany", "ZaganiaczMałyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZaganiaczMałyDataZłapania");
            intent.putExtra("Lokalizacja", "ZaganiaczMałyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZaganiaczMałyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZaganiaczMałyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zaganiacz szczebiotliwy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ZaganiaczSzczebiotliwy");
            intent.putExtra("Status", "ZaganiaczSzczebiotliwyStatus");
            intent.putExtra("Notatka", "ZaganiaczSzczebiotliwyNotatka");
            intent.putExtra("Złapany", "ZaganiaczSzczebiotliwyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZaganiaczSzczebiotliwyDataZłapania");
            intent.putExtra("Lokalizacja", "ZaganiaczSzczebiotliwyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZaganiaczSzczebiotliwyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZaganiaczSzczebiotliwyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zaroślówka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zaroślówka");
            intent.putExtra("Status", "ZaroślówkaStatus");
            intent.putExtra("Notatka", "ZaroślówkaNotatka");
            intent.putExtra("Złapany", "ZaroślówkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZaroślówkaDataZłapania");
            intent.putExtra("Lokalizacja", "ZaroślówkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZaroślówkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZaroślówkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zausznik")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zausznik");
            intent.putExtra("Status", "ZausznikStatus");
            intent.putExtra("Notatka", "ZausznikNotatka");
            intent.putExtra("Złapany", "ZausznikZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZausznikDataZłapania");
            intent.putExtra("Lokalizacja", "ZausznikMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZausznikMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZausznikZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zięba")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zięba");
            intent.putExtra("Status", "ZiębaStatus");
            intent.putExtra("Notatka", "ZiębaNotatka");
            intent.putExtra("Złapany", "ZiębaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZiębaDataZłapania");
            intent.putExtra("Lokalizacja", "ZiębaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZiębaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZiębaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zielonka")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zielonka");
            intent.putExtra("Status", "ZielonkaStatus");
            intent.putExtra("Notatka", "ZielonkaNotatka");
            intent.putExtra("Złapany", "ZielonkaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZielonkaDataZłapania");
            intent.putExtra("Lokalizacja", "ZielonkaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZielonkaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZielonkaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zimorodek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zimorodek");
            intent.putExtra("Status", "ZimorodekStatus");
            intent.putExtra("Notatka", "ZimorodekNotatka");
            intent.putExtra("Złapany", "ZimorodekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZimorodekDataZłapania");
            intent.putExtra("Lokalizacja", "ZimorodekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZimorodekMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZimorodekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Zniczek")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Zniczek");
            intent.putExtra("Status", "ZniczekStatus");
            intent.putExtra("Notatka", "ZniczekNotatka");
            intent.putExtra("Złapany", "ZniczekZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ZniczekDataZłapania");
            intent.putExtra("Lokalizacja", "ZniczekMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ZniczekMaZdjęcie");
            intent.putExtra("Zdjęcie", "ZniczekZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Żołna")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Żołna");
            intent.putExtra("Status", "ŻołnaStatus");
            intent.putExtra("Notatka", "ŻołnaNotatka");
            intent.putExtra("Złapany", "ŻołnaZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŻołnaDataZłapania");
            intent.putExtra("Lokalizacja", "ŻołnaMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŻołnaMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŻołnaZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Żuraw")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "Żuraw");
            intent.putExtra("Status", "ŻurawStatus");
            intent.putExtra("Notatka", "ŻurawNotatka");
            intent.putExtra("Złapany", "ŻurawZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŻurawDataZłapania");
            intent.putExtra("Lokalizacja", "ŻurawMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŻurawMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŻurawZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Żuraw stepowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŻurawStepowy");
            intent.putExtra("Status", "ŻurawStepowyStatus");
            intent.putExtra("Notatka", "ŻurawStepowyNotatka");
            intent.putExtra("Złapany", "ŻurawStepowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŻurawStepowyDataZłapania");
            intent.putExtra("Lokalizacja", "ŻurawStepowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŻurawStepowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŻurawStepowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Żwirowiec łąkowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŻwirowiecŁąkowy");
            intent.putExtra("Status", "ŻwirowiecŁąkowyStatus");
            intent.putExtra("Notatka", "ŻwirowiecŁąkowyNotatka");
            intent.putExtra("Złapany", "ŻwirowiecŁąkowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŻwirowiecŁąkowyDataZłapania");
            intent.putExtra("Lokalizacja", "ŻwirowiecŁąkowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŻwirowiecŁąkowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŻwirowiecŁąkowyZdjęcie");
            startActivity(intent);
        }
        else if(nazwa.equals("Żwirowiec stepowy")){
            Intent intent = new Intent(getActivity(), Gatunek.class);
            intent.putExtra("Gatunek", "ŻwirowiecStepowy");
            intent.putExtra("Status", "ŻwirowiecStepowyStatus");
            intent.putExtra("Notatka", "ŻwirowiecStepowyNotatka");
            intent.putExtra("Złapany", "ŻwirowiecStepowyZłapany");
            intent.putExtra("LiczbaZnalezionych", "LiczbaZnalezionych");
            intent.putExtra("DataZłapania", "ŻwirowiecStepowyDataZłapania");
            intent.putExtra("Lokalizacja", "ŻwirowiecStepowyMiejsceZłapania");
            intent.putExtra("MaZdjęcie", "ŻwirowiecStepowyMaZdjęcie");
            intent.putExtra("Zdjęcie", "ŻwirowiecStepowyZdjęcie");
            startActivity(intent);
        }
    }
}
