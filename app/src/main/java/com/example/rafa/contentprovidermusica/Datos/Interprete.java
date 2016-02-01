package com.example.rafa.contentprovidermusica.Datos;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.rafa.contentprovidermusica.BD.Contrato;

public class Interprete {

    private long idInterprete;
    private String nombreInterprete;

    public Interprete() {

    }

    public Interprete(String nombreInterprete, long idInterprete) {
        this.nombreInterprete = nombreInterprete;
        this.idInterprete = idInterprete;
    }

    public long getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(long idInterprete) {
        this.idInterprete = idInterprete;
    }

    public String getNombreInterprete() {
        return nombreInterprete;
    }

    public void setNombreInterprete(String nombreInterprete) {
        this.nombreInterprete = nombreInterprete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interprete that = (Interprete) o;

        if (idInterprete != that.idInterprete) return false;
        return !(nombreInterprete != null ? !nombreInterprete.equals(that.nombreInterprete) : that.nombreInterprete != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (idInterprete ^ (idInterprete >>> 32));
        result = 31 * result + (nombreInterprete != null ? nombreInterprete.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Interprete{" +
                "idInterprete=" + idInterprete +
                ", nombreInterprete='" + nombreInterprete + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.TablaInterprete._ID,this.idInterprete);
        cv.put(Contrato.TablaInterprete.NOMBREINTERPRETE,this.nombreInterprete);
        return cv;
    }

    public void set(Cursor c){
        this.idInterprete = c.getLong(c.getColumnIndex(Contrato.TablaInterprete._ID));
        this.nombreInterprete = c.getString(c.getColumnIndex(Contrato.TablaInterprete.NOMBREINTERPRETE));
    }
}
