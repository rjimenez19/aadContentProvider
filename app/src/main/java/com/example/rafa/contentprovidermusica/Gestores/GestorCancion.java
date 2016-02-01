package com.example.rafa.contentprovidermusica.Gestores;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafa.contentprovidermusica.BD.Ayudante;
import com.example.rafa.contentprovidermusica.BD.Contrato;
import com.example.rafa.contentprovidermusica.Datos.Cancion;

import java.util.ArrayList;
import java.util.List;

public class GestorCancion {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorCancion(Context c) {
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

    public long insert(Cancion pl){
        ContentValues valores= new ContentValues();
        valores.put(Contrato.TablaCancion.TITULO, pl.getTitulo());

        long id = bd.insert(Contrato.TablaCancion.TABLA,null,valores);
        return id;
    }

    public int delete(Cancion p){
        return delete(p.getIdCancion());
    }

    public int delete(long id){
        String condicion = Contrato.TablaCancion._ID+"= ?";
        String[] argumentos = {id +"" };
        int cuenta = bd.delete(Contrato.TablaCancion.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Cancion pl) {

        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCancion.TITULO, pl.getTitulo());

        String condicion = Contrato.TablaCancion._ID + " = ?";
        String[] argumentos = { pl.getIdCancion() + "" };

        int cuenta = bd.update(Contrato.TablaCancion.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Cancion> select(){
        return select(null, null);
    }

    public List<Cancion> select(String condicion, String[] params) {
        List<Cancion> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, params);
        Cancion ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            la.add(ag);
        }
        cursor.close();
        return la;
    }

    public Cancion getRow(Cursor c) {
        Cancion pl = new Cancion();
        Log.v("aadsql",""+c.getColumnCount());
        pl.setIdCancion(c.getLong(c.getColumnIndex(Contrato.TablaCancion._ID)));
        pl.setTitulo(c.getString(c.getColumnIndex(Contrato.TablaCancion.TITULO)));
        return pl;
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(Contrato.TablaCancion.TABLA, null, condicion, parametros, null, null, Contrato.TablaCancion.TITULO+", "+Contrato.TablaCancion._ID);
        return cursor;
    }
}
