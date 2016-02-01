package com.example.rafa.contentprovidermusica.Datos;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.rafa.contentprovidermusica.BD.Contrato;

public class Disco {

    private long idDisco;
    private String nombreDisco;
    private long idInterprete;

    public Disco() {

    }

    public Disco(long idDisco, String nombre) {
        this.idDisco = idDisco;
        this.nombreDisco = nombre;
    }

    public Disco(long idDisco, String nombre, long idInterprete) {
        this.idDisco = idDisco;
        this.nombreDisco = nombre;
        this.idInterprete = idInterprete;
    }

    public long getIdDisco() {
        return idDisco;
    }

    public void setIdDisco(long idDisco) {
        this.idDisco = idDisco;
    }

    public long getIdInterprete() {
        return idInterprete;
    }

    public void setIdInterprete(long idInterprete) {
        this.idInterprete = idInterprete;
    }

    public String getNombreDisco() {
        return nombreDisco;
    }

    public void setNombreDisco(String nombreDisco) {
        this.nombreDisco = nombreDisco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disco disco = (Disco) o;

        if (idDisco != disco.idDisco) return false;
        if (idInterprete != disco.idInterprete) return false;
        return !(nombreDisco != null ? !nombreDisco.equals(disco.nombreDisco) : disco.nombreDisco != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (idDisco ^ (idDisco >>> 32));
        result = 31 * result + (nombreDisco != null ? nombreDisco.hashCode() : 0);
        result = 31 * result + (int) (idInterprete ^ (idInterprete >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "idDisco=" + idDisco +
                ", nombre='" + nombreDisco + '\'' +
                ", idInterprete=" + idInterprete +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.TablaDisco._ID,this.idDisco);
        cv.put(Contrato.TablaDisco.NOMBREDISCO,this.nombreDisco);
        cv.put(Contrato.TablaDisco.IDINTERPRETEDISCO,this.idInterprete);
        return cv;
    }

    public void set(Cursor c){
        this.idDisco = c.getLong(c.getColumnIndex(Contrato.TablaDisco._ID));
        this.nombreDisco = c.getString(c.getColumnIndex(Contrato.TablaDisco.NOMBREDISCO));
        this.idInterprete = c.getLong(c.getColumnIndex(Contrato.TablaDisco.IDINTERPRETEDISCO));
    }
}
