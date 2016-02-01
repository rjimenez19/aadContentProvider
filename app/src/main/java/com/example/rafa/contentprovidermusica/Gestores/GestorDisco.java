package com.example.rafa.contentprovidermusica.Gestores;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rafa.contentprovidermusica.BD.Ayudante;
import com.example.rafa.contentprovidermusica.BD.Contrato;
import com.example.rafa.contentprovidermusica.Datos.Disco;

import java.util.ArrayList;
import java.util.List;

public class GestorDisco {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorDisco(Context c) {
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

    public long insert(Disco pl){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaDisco.NOMBREDISCO, pl.getNombreDisco());
        valores.put(Contrato.TablaDisco.IDINTERPRETEDISCO, pl.getIdInterprete());
        long id = bd.insert(Contrato.TablaDisco.TABLA,null,valores);
        return id;
    }

    public int delete(Disco p){
        return delete(p.getIdDisco());
    }

    public int delete(long id){
        String condicion = Contrato.TablaDisco._ID+"= ?";
        String[] argumentos = {id +"" };
        int cuenta = bd.delete(Contrato.TablaDisco.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Disco pl) {

        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaDisco.NOMBREDISCO, pl.getNombreDisco());

        String condicion = Contrato.TablaDisco._ID + " = ?";
        String[] argumentos = { pl.getIdDisco() + "" };

        int cuenta = bd.update(Contrato.TablaDisco.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public List<Disco> select(){
        return select(null, null);
    }

    public List<Disco> select(String condicion, String[] params) {
        List<Disco> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, params);
        Disco ag;
        while (!cursor.isAfterLast()) {
            ag = getRow(cursor);
            la.add(ag);
        }
        cursor.close();
        return la;
    }

    public Disco getRow(Cursor c) {
        Disco pl = new Disco();
        Log.v("aadsql", "" + c.getColumnCount());
        pl.setIdDisco(c.getLong(c.getColumnIndex(Contrato.TablaDisco._ID)));
        pl.setNombreDisco(c.getString(c.getColumnIndex(Contrato.TablaDisco.NOMBREDISCO)));

        return pl;
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(Contrato.TablaDisco.TABLA, null, condicion, parametros, null, null, Contrato.TablaDisco.NOMBREDISCO + ", "+Contrato.TablaDisco._ID);
        return cursor;
    }
}
