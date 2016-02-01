package com.example.rafa.contentprovidermusica.Datos;


import android.content.ContentValues;
import android.database.Cursor;

import com.example.rafa.contentprovidermusica.BD.Contrato;

public class Cancion {

    private long idCancion;
    private String titulo;

    public Cancion(){

    }

    public Cancion(long idCancion, String titulo) {
        this.idCancion = idCancion;
        this.titulo = titulo;
    }

    public long getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(long idCancion) {
        this.idCancion = idCancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cancion cancion = (Cancion) o;

        if (idCancion != cancion.idCancion) return false;
        return !(titulo != null ? !titulo.equals(cancion.titulo) : cancion.titulo != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (idCancion ^ (idCancion >>> 32));
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", titulo='" + titulo + '\'' +
                '}';
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(Contrato.TablaCancion._ID,this.idCancion);
        cv.put(Contrato.TablaCancion.TITULO,this.titulo);
        return cv;
    }

    public void set(Cursor c){
        this.idCancion = c.getLong(c.getColumnIndex(Contrato.TablaCancion._ID));
        this.titulo = c.getString(c.getColumnIndex(Contrato.TablaCancion.TITULO));
    }
}
