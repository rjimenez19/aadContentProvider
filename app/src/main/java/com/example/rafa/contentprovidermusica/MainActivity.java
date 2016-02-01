package com.example.rafa.contentprovidermusica;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rafa.contentprovidermusica.BD.Contrato;
import com.example.rafa.contentprovidermusica.Datos.Cancion;
import com.example.rafa.contentprovidermusica.Datos.Disco;
import com.example.rafa.contentprovidermusica.Datos.Interprete;
import com.example.rafa.contentprovidermusica.Gestores.GestorCancion;
import com.example.rafa.contentprovidermusica.Gestores.GestorDisco;
import com.example.rafa.contentprovidermusica.Gestores.GestorInterprete;
import com.example.rafa.contentprovidermusica.Util.Adaptador;
import com.example.rafa.contentprovidermusica.Util.Adaptador2;

import java.util.ArrayList;

public class  MainActivity extends AppCompatActivity {

    private Context context;
    private Adaptador2 adp;

    private ListView lv;
    private TextView tv;

    private GestorCancion gc;
    private GestorInterprete gi;
    private GestorDisco gd;

    private String titulo;
    private String interprete;
    private String disco;
    private Long idCancion;
    private Long idInterprete;
    private Long idDisco;

    private Cancion cancion;
    private Interprete interpr;
    private Disco disc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SecondActivity.class);
                startActivity(i);
            }
        });
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        lv = (ListView) findViewById(R.id.lvMusica);
        tv = (TextView) findViewById(R.id.textView);

        gc = new GestorCancion(this);
        gi = new GestorInterprete(this);
        gd = new GestorDisco(this);

        obtenerMusica();
    }

    public void obtenerMusica() {
        Cursor cur = getContentResolver().query(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.IS_MUSIC + "= 1", null, null);
        while (cur.moveToNext()) {

            titulo = cur.getString(2);
            interprete = cur.getString(22);
            disco = cur.getString(28);
            idCancion = Long.parseLong(cur.getString(0));
            idInterprete = Long.parseLong(cur.getString(11));
            idDisco = Long.parseLong(cur.getString(13));

            cancion = new Cancion(idCancion, titulo);
            interpr = new Interprete(interprete, idInterprete);
            disc = new Disco(idDisco, disco, idInterprete);

            gc.open();
            gc.insert(cancion);
            gc.close();
            gi.open();
            gi.insert(interpr);
            gi.close();
            gd.open();
            gd.insert(disc);
            gd.close();

            for (int i = 0; i < cur.getColumnCount(); i++) {
                Log.v("NOMBRE", i + " " + cur.getColumnName(i));
                Log.v("TITULO", i + " " + cur.getString(i));
            }
            tv.append("\n" + "IDCancion" + idCancion + "Titulo: " + titulo +
                    "\n" + "IDInterprete" + idInterprete + "Interprete: " + interprete +
                    "\n" + " IDDisco:" + idDisco + "Disco: " + disco + "\n");
        }

        adp = new Adaptador2(this,cur, cancion,interpr,disc);
        lv.setAdapter(adp);

        Log.v("JODER", cancion.getTitulo());

        cur.close();
    }

    public static String getEstructuraCursor(Cursor cursor) {
        String registro = "";
        String[] nombres = cursor.getColumnNames();
        int contador = 0;
        for (String s : nombres) {
            contador++;
            registro += contador + " " + s + "\n";
        }
        return registro;
    }
}


