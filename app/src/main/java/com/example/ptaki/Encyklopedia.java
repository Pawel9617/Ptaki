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
    }
}
