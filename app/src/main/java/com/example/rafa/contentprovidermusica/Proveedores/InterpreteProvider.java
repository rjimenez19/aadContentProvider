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

public class InterpreteProvider extends ContentProvider{
    public static final UriMatcher uriMatcher;
    public static final int INTERPRETE = 1;
    public static final int INTERPRETE_ID = 2;
    private Ayudante abd;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Contrato.TablaInterprete.AUTHORITY, Contrato.TablaInterprete.TABLA, INTERPRETE);
        uriMatcher.addURI(Contrato.TablaInterprete.AUTHORITY, Contrato.TablaInterprete.TABLA + "/#", INTERPRETE_ID);
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
            case INTERPRETE:
                c = db.query(Contrato.TablaInterprete.TABLA, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INTERPRETE_ID:
                long idActividad = ContentUris.parseId(uri);
                c = db.query(Contrato.TablaInterprete.TABLA, projection, Contrato.TablaInterprete._ID + "= ? " , new String [] {idActividad + ""}, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(),Contrato.TablaInterprete.CONTENT_URI);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case INTERPRETE:
                return Contrato.TablaInterprete.MJLTIPLE_MIME;
            case INTERPRETE_ID:
                return Contrato.TablaInterprete.SINGLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uriMatcher.match(uri) != INTERPRETE) {
            throw new IllegalArgumentException("URI desconocida : " + uri);
        }

        if (values == null) {
            throw new IllegalArgumentException("Cliente null ");
        }

        SQLiteDatabase db = abd.getWritableDatabase();
        long rowId = db.insert(Contrato.TablaInterprete.TABLA, null, values);
        if (rowId > 0) {
            Uri uri_actividad = ContentUris.withAppendedId(Contrato.TablaInterprete.CONTENT_URI, rowId);
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
            case INTERPRETE:
                affected = db.delete(Contrato.TablaInterprete.TABLA,selection,selectionArgs);
                break;
            case INTERPRETE_ID:
                long idActividad = ContentUris.parseId(uri);
                affected = db.delete(Contrato.TablaInterprete.TABLA,Contrato.TablaInterprete._ID + "= ?" , new String [] {idActividad + ""});

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
            case INTERPRETE:
                affected = db.update(Contrato.TablaInterprete.TABLA, values, selection, selectionArgs);
                break;
            case INTERPRETE_ID:
                String idActividad = uri.getPathSegments().get(1);
                affected = db.update(Contrato.TablaInterprete.TABLA, values, Contrato.TablaInterprete._ID + "= ?" , new String [] {idActividad});
                break;
            default:
                throw new IllegalArgumentException("URI desconocida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }
}
