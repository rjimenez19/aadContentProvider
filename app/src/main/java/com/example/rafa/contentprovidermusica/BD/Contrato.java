package com.example.rafa.contentprovidermusica.BD;


import android.net.Uri;
import android.provider.BaseColumns;

public class Contrato {

    public static abstract class TablaCancion implements BaseColumns {
        public static final String TABLA = "cancion";
        public static final String IDCANCION = "idCancion";
        public static final String TITULO = "titulo";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.rafa.contentprovidermusica.Proveedores.CancionProvider";

        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME = "vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME = "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }

    public static abstract class TablaDisco implements BaseColumns {
        public static final String TABLA = "disco";
        public static final String IDDISCO = "idCancion";
        public static final String NOMBREDISCO = "nombreDisco";
        public static final String IDINTERPRETEDISCO = "idInterpreteDisco";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.rafa.contentprovidermusica.Proveedores.DiscoProvider";

        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME = "vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME = "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }

    public static abstract class TablaInterprete implements BaseColumns {
        public static final String TABLA = "interprete";
        public static final String IDINTERPRETE = "idInterprete";
        public static final String NOMBREINTERPRETE = "nombreInterprete";

        //La autoridad es la cadena q identifica a qué contentprovider se llama
        public final static String AUTHORITY = "com.example.rafa.contentprovidermusica.Proveedores.InterpreteProvider";

        //Definir como llego a la tabla cliente (a q tabla estoy llegando)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLA);
        public final static String SINGLE_MIME = "vnd.android.cursor.item/vnd." + AUTHORITY + TABLA;
        public final static String MJLTIPLE_MIME = "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLA;
    }
}
