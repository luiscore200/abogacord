package com.foreroinc.abogacord.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "abogacord.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_ROL = "t_rol";
    public static final String TABLE_PROCESOS = "t_procesos";
    public static final String TABLE_ESTADO = "t_estado";
    public static final String TABLE_ABOGADO_PROCESO = "t_abogado_proceso";

    public static final String TABLE_REGISTROS = "t_registro";



    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Tabla de Rol, con datos predeterminados para llave foranea de Tabla de Usuarios
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ROL + "(" +
                "IDRol INTEGER PRIMARY KEY," +
                "Rolname TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ROL + "(IDRol, Rolname) VALUES (1, 'Administrador')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ROL + "(IDRol, Rolname) VALUES (2, 'Abogado')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ROL + "(IDRol, Rolname) VALUES (3, 'Cliente')");

        //Tabla de Usuarios
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "Cedula INTEGER PRIMARY KEY," +
                "Nombres TEXT NOT NULL," +
                "Apellidos TEXT NOT NULL," +
                "Usuario TEXT NOT NULL UNIQUE," +
                "Contrasena TEXT NOT NULL," +
                "IDRol INTEGER NOT NULL," +
                "FOREIGN KEY (IDRol) REFERENCES " + TABLE_ROL + "(IDRol))");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USUARIOS + " VALUES (1, 'Admin', 'istrador', 'admin', 'admin', 1)");

        //Tabla de Estados, con datos predeterminados para llave foránea de Tabla de Procesos

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ESTADO + "(" +
                "IDEstado INTEGER PRIMARY KEY," +
                "Estadoname TEXT NOT NULL)");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (1, 'Abierto')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (2, 'Cerrado')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (3, 'Resuelto')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (4, 'Apelacion')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (5, 'En Juzgado')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (6, 'En Tramite')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (7, 'Indagatoria')");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_ESTADO + "(IDEstado, Estadoname) VALUES (8, 'Por Asignar')");

        //Tabla de Procesos

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PROCESOS + "(" +
                "IDCaso INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT NOT NULL," +
                "Descripcion TEXT NOT NULL," +
                "cedulaCliente TEXT NOT NULL," +

                "Estado TEXT NOT NULL," +
                "FOREIGN KEY (cedulaCliente) REFERENCES " + TABLE_USUARIOS + "(Cedula)," +

                "FOREIGN KEY (Estado) REFERENCES " + TABLE_ESTADO + "(IDEstado))");


     //Tabla de registros

     sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTROS + "(" +
                 "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                 "Periodo TEXT NOT NULL," +
                 "Descripcion TEXT NOT NULL," +
                 "Enero TEXT NOT NULL," +
                 "Febrero TEXT NOT NULL," +
                "Marzo TEXT NOT NULL," +
                 "Abril TEXT NOT NULL," +
                 "Mayo TEXT NOT NULL," +
                 "Junio TEXT NOT NULL," +
                 "Julio TEXT NOT NULL," +
                 "Agosto TEXT NOT NULL," +
                "Septiembre TEXT NOT NULL," +
                 "Octubre TEXT NOT NULL," +
                 "Noviembre TEXT NOT NULL," +
                 "Diciembre TEXT NOT NULL)" );


        //Tabla de Caso Abogado

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ABOGADO_PROCESO + "(" +
                "IDCaso INTEGER PRIMARY KEY," +
                "cedulaAbogado INTEGER NOT NULL," +
                "FOREIGN KEY (IDCaso) REFERENCES " + TABLE_PROCESOS + "(IDCaso)," +
                "FOREIGN KEY (cedulaAbogado) REFERENCES " + TABLE_USUARIOS + "(Cedula))");
    }


    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS );
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ROL );
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ESTADO );
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PROCESOS );
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ABOGADO_PROCESO );
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_REGISTROS );
        onCreate(sqLiteDatabase);

    }


}