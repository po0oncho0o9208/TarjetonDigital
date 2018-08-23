package com.games.user.tarjetondigital;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Documentos extends AppCompatActivity {

    AdView mAdView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);
        mAdView = findViewById(R.id.adView10);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
    public void contrato (View view){
        Intent intent12=new Intent(this,Contratos.class);
        startActivity(intent12);
    }
    public void Convenio (View view){
        Intent intent121=new Intent(this,Txt.class);
        startActivity(intent121);
    }
    public  void OtrosDocunentos (View view){

        Intent intentae = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sntss.org.mx/requerimientos/leyes-y-reglamentos-gfee"));
        startActivity(intentae);
    }}

