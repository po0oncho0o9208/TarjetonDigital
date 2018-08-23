package com.games.user.tarjetondigital;

import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    AdView mAdView;
    ImageView imv2, movil;
    Boolean confirmacion;


    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


    public void createSimpleDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ya podrás recibir NOTIFICACIONES en tu celular, cuando llegue el Tarjetón o alguna noticia relevante del instituto." +
                "  En esta aplicacion no se piden datos, solo te lleva a la pagina\n" +
                "oficial del IMSS para descargar el tarjeton tanto activos como jubilados.\n" +
                "Tambien obtendras algunos documentos de consulta podras ver promociones ");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "LEIDO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSimpleDialog();

        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1984616735532779/9679963023");
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest1);

    }
    public void calendario (View view){

        Intent intent11=new Intent(this,Calendario.class);
        startActivity(intent11);
    }

    public void activos (View view) {

            Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetondigital/"));
            startActivity(intent1);
        }

    public  void jubilados (View view){

        Intent intentae = new Intent(Intent.ACTION_VIEW, Uri.parse("http://rh.imss.gob.mx/tarjetonjubilados/(S(afzkevxvymx14vcajnskmjbf))/default.aspx"));
        startActivity(intentae);
    }
        public void Compartir (View view) {

            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }
            }
            if (confirmacion = true) {


                Intent intento = new Intent(Intent.ACTION_SEND);
            intento.setType("*/*");
            String paramString1 = Integer.toString(R.drawable.logos);
            Bitmap topo2 = BitmapFactory.decodeResource(getResources(), R.drawable.logos);
            String fileName = paramString1 + "" + ".png";
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            topo2.compress(Bitmap.CompressFormat.PNG, 40, bytes);
            File ExternalStorageDirectory = Environment.getExternalStorageDirectory();
            File file = new File(ExternalStorageDirectory + File.separator + fileName);
            FileOutputStream fileOutputStream = null;
            try {
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes.toByteArray());
            } catch (IOException e) {

            } finally {
                if (fileOutputStream != null) {
                    Uri bmpUri = Uri.parse(file.getPath());
                    intento.putExtra(Intent.EXTRA_TEXT, "En esta aplicación podrás descargar tu tarjetón digital, veras sus promociones"  + Html.fromHtml("<br />") +
                            "y recibirás notificaciones de cuando llegue el tarjetón , así como noticias relevantes del IMSS  " + Html.fromHtml("<br />") +
                            "https://play.google.com/store/apps/details?id=com.tarjetonimss.user.imsswebtarjeton");
                    intento.putExtra(
                            Intent.EXTRA_STREAM,
                            bmpUri);
                    startActivity(Intent.createChooser(intento,
                            "Siguenos en nuestra pagina "));
                }
            }
        }
            else
            {		 Toast.makeText(MainActivity.this, "Gracias, bonito día", Toast.LENGTH_SHORT).show();
            }
        }


    public void documentos (View view){
        Intent intent121=new Intent(this,Documentos.class);
        startActivity(intent121);

    }

    public void noti (View view){

        Intent intent08=new Intent(this,Noticias.class);
        startActivity(intent08);
    }

    public void promociones (View view){

        Intent intent1281=new Intent(this,Promociones.class);
        startActivity(intent1281);
    }


}
