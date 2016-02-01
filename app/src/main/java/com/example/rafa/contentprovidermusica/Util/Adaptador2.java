package com.example.rafa.contentprovidermusica.Util;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafa.contentprovidermusica.Datos.Cancion;
import com.example.rafa.contentprovidermusica.Datos.Disco;
import com.example.rafa.contentprovidermusica.Datos.Interprete;
import com.example.rafa.contentprovidermusica.R;

import java.util.ArrayList;

public class Adaptador2 extends CursorAdapter {

    private TextView tv;
    private Cancion cancion;
    private Interprete interpr;
    private Disco disc;

    public Adaptador2(Context co, Cursor cu, Cancion cancion, Interprete interpr, Disco disc) {
        super(co, cu, true);
        this.cancion = cancion;
        this.interpr = interpr;
        this.disc = disc;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item2, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tv = (TextView) view.findViewById(R.id.tv9);

        tv.append("\n" + "IDCancion" + cancion.getIdCancion() + "Titulo: " + cancion.getTitulo() +
                "\n" + "IDInterprete" + interpr.getIdInterprete() + "Interprete: " + interpr.getNombreInterprete() +
                "\n" + " IDDisco:" + disc.getIdDisco() + "Disco: " + disc.getNombreDisco() + "\n");
    }
}
