package com.example.rafa.contentprovidermusica;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rafa.contentprovidermusica.BD.Contrato;
import com.example.rafa.contentprovidermusica.Datos.Cancion;
import com.example.rafa.contentprovidermusica.Datos.Disco;
import com.example.rafa.contentprovidermusica.Datos.Interprete;
import com.example.rafa.contentprovidermusica.Util.Adaptador;

public class SecondActivity extends AppCompatActivity {



    private Cancion a = new Cancion();
    private Interprete b = new Interprete();
    private Disco c = new Disco();

    private ListView lv;
    private Adaptador adp;
    private TextView tv;

    private Uri uriCancion = Contrato.TablaCancion.CONTENT_URI;
    private Uri uriInterprete = Contrato.TablaInterprete.CONTENT_URI;
    private Uri uriDisco = Contrato.TablaDisco.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = (ListView) findViewById(R.id.listaCanciones);
        tv = (TextView) findViewById(R.id.textView3);
        init();
    }

    public void init() {

        Cursor cur = getContentResolver().query(uriCancion, null, null , null, null);
        Cursor cur2 = getContentResolver().query(uriInterprete, null, null , null, null);
        Cursor cur3 = getContentResolver().query(uriDisco, null, null , null, null);

        while (cur.moveToNext() && cur2.moveToNext() && cur3.moveToNext()) {
//          a = new Cancion(cur.getInt(0),cur.getString(1));
            a.setIdCancion(cur.getInt(0));
            a.setTitulo(cur.getString(1));
            tv.append("\n" + "IDCancion: " + a.getIdCancion() + " Titulo: " + a.getTitulo());
            b.setIdInterprete(cur2.getInt(0));
            b.setNombreInterprete(cur2.getString(1));
            tv.append("\n" + "IDInterprete: " + b.getIdInterprete() + " Interprete: " + b.getNombreInterprete());
            c.setIdDisco(cur3.getInt(0));
            c.setNombreDisco(cur3.getString(1));
            tv.append("\n" + "IDDisco: " + c.getIdDisco() + " Disco: " + c.getNombreDisco() + "\n");
        }

        adp = new Adaptador(this,cur,a,b,c);
        lv.setAdapter(adp);

        cur.close();
        cur2.close();
        cur3.close();
    }
}

