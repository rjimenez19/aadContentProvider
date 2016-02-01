package com.example.rafa.contentprovidermusica.Util;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.rafa.contentprovidermusica.Datos.Cancion;
import com.example.rafa.contentprovidermusica.Datos.Disco;
import com.example.rafa.contentprovidermusica.Datos.Interprete;
import com.example.rafa.contentprovidermusica.R;


public class Adaptador extends CursorAdapter {

    private Cancion c;
    private Interprete i;
    private Disco d;

    public Adaptador(Context co, Cursor cu, Cancion c, Interprete i, Disco d) {
        super(co, cu, true);
        this.c = c;
        this.i = i;
        this.d = d;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv4 = (TextView) view.findViewById(R.id.tvCancion);
        TextView tv5 = (TextView) view.findViewById(R.id.tvInterprete);
        TextView tv6 = (TextView) view.findViewById(R.id.tvDisco);

        tv4.setText("\n" + "IDCancion" + c.getIdCancion() + "Titulo: " + c.getTitulo() +
                "\n" + "IDInterprete" + i.getIdInterprete() + "Interprete: " + i.getNombreInterprete() +
                "\n" + " IDDisco:" + d.getIdDisco() + "Disco: " + d.getNombreDisco() + "\n");
    }
}