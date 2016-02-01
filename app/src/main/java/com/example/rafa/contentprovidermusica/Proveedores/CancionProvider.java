package com.example.rafa.contentprovidermusica.Proveedores;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.rafa.contentprovidermusica.BD.Ayudante;
import com.example.rafa.contentprovidermusica.BD.Contrato;

public class CancionProvider extends ContentProvider{

    public static final UriMatcher uriMatcher;
    public static final int CANCION = 1;
    public static final int CANCION_ID = 2;
    private Ayudante abd;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Contrato.TablaCancion.AUTHORITY, Contrato.TablaCancion.TABLA, CANCION);
        uriMatcher.addURI(Contrato.TablaCancion.AUTHORITY, Contrato.TablaCancion.TABLA + "/#", CANCION_ID);
    }

    @Override
    public boolean onCreate() {
        abd = new Ayudante(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = abd.getReadableDatabase();
        int match = uriMatcher.match(uri);
        Cursor c;
        switch (match) {
            case CANCION:
                c = db.query(Contrato.TablaCancion.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CANCION_ID:
                long idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaCancion.TABLA, projection, Contrato.TablaCancion._ID + "= ? " , new String [] {idActividad + ""}, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaCancion.CONTENT_URI);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case CANCION:
                return Contrato.TablaCancion.MJLTIPLE_MIME;
            case CANCION_ID:
                return Contrato.TablaCancion.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != CANCION) {
            throw new IllegalArgumentException("URI desconocida : " + uri);
        }

        if (values == null) {
            throw new IllegalArgumentException("Cliente null ");
        }

        SQLiteDatabase db = abd.getWritableDatabase();
        long rowId = db.insert(Contrato.TablaCancion.TABLA, null, values);
        if (rowId > 0) {
            Uri uri_actividad = ContentUris.withAppendedId(Contrato.TablaCancion.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri_actividad, null);
            return uri_actividad;
        }
        throw new SQLException("Error al insertar fila en : " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int affected;
        switch (match) {
            case CANCION:
                affected = db.delete(Contrato.TablaCancion.TABLA,selection,selectionArgs);
                break;
            case CANCION_ID:
                long idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contrato.TablaCancion.TABLA,Contrato.TablaCancion._ID + "= ?" , new String [] {idActividad + ""});

                break;
            default:
                throw new IllegalArgumentException("Elemento actividad desconocido: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = abd.getWritableDatabase();
        int affected;
        switch (uriMatcher.match(uri)) {
            case CANCION:
                affected = db.update(Contrato.TablaCancion.TABLA, values, selection, selectionArgs);
                break;
            case CANCION_ID:
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contrato.TablaCancion.TABLA, values, Contrato.TablaCancion._ID + "= ?" , new String [] {idActividad});
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }
}