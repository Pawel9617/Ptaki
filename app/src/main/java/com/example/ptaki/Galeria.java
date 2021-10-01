package com.example.ptaki;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class Galeria extends Fragment implements View.OnClickListener {

    private ImageView imgView1A, imgView1B, imgView2A, imgView2B, imgView3A, imgView3B, imgView4A, imgView4B, imgView5A, imgView5B, imgView6A, imgView6B, imgView7A, imgView7B, imgView8A, imgView8B, imgView9A;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.galeria, container, false);

        imgView1A = (ImageView) view.findViewById(R.id.imgView1A);
        imgView1A.setClickable(true);
        imgView1A.setOnClickListener(this);
        imgView1B = (ImageView) view.findViewById(R.id.imgView1B);
        imgView1B.setClickable(true);
        imgView1B.setOnClickListener(this);
        imgView2A = (ImageView) view.findViewById(R.id.imgView2A);
        imgView2A.setClickable(true);
        imgView2A.setOnClickListener(this);
        imgView2B = (ImageView) view.findViewById(R.id.imgView2B);
        imgView2B.setClickable(true);
        imgView2B.setOnClickListener(this);
        imgView3A = (ImageView) view.findViewById(R.id.imgView3A);
        imgView3A.setClickable(true);
        imgView3A.setOnClickListener(this);
        imgView3B = (ImageView) view.findViewById(R.id.imgView3B);
        imgView3B.setClickable(true);
        imgView3B.setOnClickListener(this);
        imgView4A = (ImageView) view.findViewById(R.id.imgView4A);
        imgView4A.setClickable(true);
        imgView4A.setOnClickListener(this);
        imgView4B = (ImageView) view.findViewById(R.id.imgView4B);
        imgView4B.setClickable(true);
        imgView4B.setOnClickListener(this);
        imgView5A = (ImageView) view.findViewById(R.id.imgView5A);
        imgView5A.setClickable(true);
        imgView5A.setOnClickListener(this);
        imgView5B = (ImageView) view.findViewById(R.id.imgView5B);
        imgView5B.setClickable(true);
        imgView5B.setOnClickListener(this);
        imgView6A = (ImageView) view.findViewById(R.id.imgView6A);
        imgView6A.setClickable(true);
        imgView6A.setOnClickListener(this);
        imgView6B = (ImageView) view.findViewById(R.id.imgView6B);
        imgView6B.setClickable(true);
        imgView6B.setOnClickListener(this);
        imgView7A = (ImageView) view.findViewById(R.id.imgView7A);
        imgView7A.setClickable(true);
        imgView7A.setOnClickListener(this);
        imgView7B = (ImageView) view.findViewById(R.id.imgView7B);
        imgView7B.setClickable(true);
        imgView7B.setOnClickListener(this);
        imgView8A = (ImageView) view.findViewById(R.id.imgView8A);
        imgView8A.setClickable(true);
        imgView8A.setOnClickListener(this);
        imgView8B = (ImageView) view.findViewById(R.id.imgView8B);
        imgView8B.setClickable(true);
        imgView8B.setOnClickListener(this);
        imgView9A = (ImageView) view.findViewById(R.id.imgView9A);
        imgView9A.setClickable(true);
        imgView9A.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.imgView1A:
                intent = new Intent(getActivity(), Alczyk.class);
                startActivity(intent);
                break;
            case R.id.imgView1B:
                intent = new Intent(getActivity(), Alka.class);
                startActivity(intent);
                break;
            case R.id.imgView2A:
                intent = new Intent(getActivity(), Baczek.class);
                startActivity(intent);
                break;
            case R.id.imgView2B:
                intent = new Intent(getActivity(), Bak.class);
                startActivity(intent);
                break;
            case R.id.imgView3A:
                intent = new Intent(getActivity(), Batalion.class);
                startActivity(intent);
                break;
            case R.id.imgView3B:
                intent = new Intent(getActivity(), Bazant.class);
                startActivity(intent);
                break;
            case R.id.imgView4A:
                intent = new Intent(getActivity(), Bekasik.class);
                startActivity(intent);
                break;
            case R.id.imgView4B:
                intent = new Intent(getActivity(), Bernikla_bialolica.class);
                startActivity(intent);
                break;
            case R.id.imgView5A:
                intent = new Intent(getActivity(), Bernikla_kanadyjska.class);
                startActivity(intent);
                break;
            case R.id.imgView5B:
                intent = new Intent(getActivity(), Bernikla_obrozna.class);
                startActivity(intent);
                break;
            case R.id.imgView6A:
                intent = new Intent(getActivity(), Bernikla_rdzawoszyja.class);
                startActivity(intent);
                break;
            case R.id.imgView6B:
                intent = new Intent(getActivity(), Bialorzytka.class);
                startActivity(intent);
                break;
            case R.id.imgView7A:
                intent = new Intent(getActivity(), Bialorzytka_plowa.class);
                startActivity(intent);
                break;
            case R.id.imgView7B:
                intent = new Intent(getActivity(), Bialorzytka_pstra.class);
                startActivity(intent);
                break;
            case R.id.imgView8A:
                intent = new Intent(getActivity(), Bialorzytka_pustynna.class);
                startActivity(intent);
                break;
            case R.id.imgView8B:
                intent = new Intent(getActivity(), Bialorzytka_rdzawa.class);
                startActivity(intent);
                break;
            case R.id.imgView9A:
                intent = new Intent(getActivity(), Bialorzytka_saharyjska.class);
                startActivity(intent);
                break;
        }
    }
}
