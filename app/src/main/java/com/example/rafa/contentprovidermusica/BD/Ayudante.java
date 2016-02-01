package com.example.rafa.contentprovidermusica.BD;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="musica.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1;
        sql1="create table "+ Contrato.TablaCancion.TABLA+ " ("+
                Contrato.TablaCancion._ID+ " integer primary key autoincrement, "+
                Contrato.TablaCancion.TITULO+" text)";
        db.execSQL(sql1);

        String sql2;
        sql2="create table "+ Contrato.TablaDisco.TABLA+ " ("+
                Contrato.TablaDisco._ID+ " integer primary key autoincrement, "+
                Contrato.TablaDisco.NOMBREDISCO+" text, "+
                Contrato.TablaDisco.IDINTERPRETEDISCO+" integer)";
        db.execSQL(sql2);

        String sql3;
        sql3="create table "+ Contrato.TablaInterprete.TABLA+ " ("+
                Contrato.TablaInterprete._ID+ " integer primary key autoincrement, "+
                Contrato.TablaInterprete.NOMBREINTERPRETE+" text)";
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlB1,sqlB2,sqlB3;

        sqlB1 = "DROP TABLE IF EXISTS " + Contrato.TablaCancion.TABLA ;
        sqlB2 = "DROP TABLE IF EXISTS " + Contrato.TablaInterprete.TABLA;
        sqlB3 = "DROP TABLE IF EXISTS " + Contrato.TablaDisco.TABLA;

        db.execSQL(sqlB1);
        db.execSQL(sqlB2);
        db.execSQL(sqlB3);

        Log.v("SQLAAD", "onUpgrade");
        onCreate(db);
    }
}
