package com.example.rafa.contentprovidermusica.Gestores;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafa.contentprovidermusica.BD.Ayudante;
import com.example.rafa.contentprovidermusica.BD.Contrato;
import com.example.rafa.contentprovidermusica.Datos.Interprete;

import java.util.ArrayList;
import java.util.List;


public class GestorInterprete {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorInterprete(Context c) {
        Log.v("SQLAAD", "Gestor de constructor de recetas");
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Interprete pl){
        ContentValues valores= new ContentValues();
        valores.put(Contrato.TablaInterprete.NOMBREINTERPRETE, pl.getNombreInterprete());

        long id = bd.insert(Contrato.TablaInterprete.TABLA,null,valores);
        return id;
    }

    public int delete(Interprete p){
        return delete(p.getIdInterprete());
    }

    public int delete(long id){
        String condicion = Contrato.TablaInterprete._ID+"= ?";
        String[] argumentos = {id +"" };
        int cuenta = bd.delete(Contrato.TablaInterprete.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Interprete pl) {

        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaInterprete.NOMBREINTERPRETE, pl.getNombreInterprete());
        String condicion = Contrato.TablaInterprete._ID + " = ?";
        String[] argumentos = { pl.getIdInterprete() + "" };

        int cuenta = bd.update(Contrato.TablaInterprete.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Interprete> select(){
        return select(null, null);
    }

    public List<Interprete> select(String condicion, String[] params) {
        List<Interprete> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, params);
        Interprete ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            la.add(ag);
        }
        cursor.close();
        return la;
    }

    public Interprete getRow(Cursor c) {
        Interprete pl = new Interprete();
        Log.v("aadsql",""+c.getColumnCount());
        pl.setIdInterprete(c.getLong(c.getColumnIndex(Contrato.TablaInterprete._ID)));
        pl.setNombreInterprete(c.getString(c.getColumnIndex(Contrato.TablaInterprete.NOMBREINTERPRETE)));
        return pl;
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(Contrato.TablaInterprete.TABLA, null, condicion, parametros, null, null, Contrato.TablaInterprete.NOMBREINTERPRETE + ", "+Contrato.TablaInterprete._ID);
        return cursor;
    }
}
