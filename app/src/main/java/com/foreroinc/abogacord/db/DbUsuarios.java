package com.foreroinc.abogacord.db;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.foreroinc.abogacord.recycler.objetoUsuario;

import java.util.ArrayList;

public class DbUsuarios extends DbHelper {
    private Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String Cedula, String Nombres, String Apellidos, String Usuario, String Contrasena, String IDRol) {
        long datos = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Cedula", Cedula);
            values.put("Nombres", Nombres);
            values.put("Apellidos", Apellidos);
            values.put("Usuario", Usuario);
            values.put("Contrasena", Contrasena);
            values.put("IDRol", IDRol);

            datos = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return datos;
    }

    public int modificarUsuario(String Cedula, String Nombres, String Apellidos, String Usuario, String Contrasena, String IDRol) {
        int datos = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombres", Nombres);
            values.put("Apellidos", Apellidos);
            values.put("Usuario", Usuario);
            values.put("Contrasena", Contrasena);
            values.put("IDRol", IDRol);

            datos = db.update(TABLE_USUARIOS, values, "Cedula = ?", new String[]{Cedula});
        } catch (Exception ex) {
            ex.toString();
        }
        return datos;
    }

    public int eliminarUsuario(String cedula) {
        int datos = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            datos = db.delete(TABLE_USUARIOS, "Cedula = ?", new String[]{cedula});
        } catch (Exception ex) {
            ex.toString();
        }
        return datos;
    }
    public ArrayList<objetoUsuario> mostrarUsuario() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS, null);
            ArrayList<objetoUsuario> user = new ArrayList<>();
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    objetoUsuario us = new objetoUsuario();
                    us.setCedula(cursor.getInt(0));
                    us.setNombres(cursor.getString(1));
                    us.setApellidos(cursor.getString(2));
                    us.setUsuario(cursor.getString(3));
                    us.setContrasena(cursor.getString(4));
                    us.setIDRol(cursor.getInt(5));

                    user.add(us);

                } while (cursor.moveToNext());
            }
            return user;
        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public ArrayList<objetoUsuario> modificarUsuario(String cc) {
        if (cc != "") {
            try {
                SQLiteDatabase db = this.getWritableDatabase();

                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE cedula = ?", new String[]{cc});
                ArrayList<objetoUsuario> user = new ArrayList<>();
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        objetoUsuario us = new objetoUsuario();
                        us.setCedula(cursor.getInt(0));
                        us.setNombres(cursor.getString(1));
                        us.setApellidos(cursor.getString(2));
                        us.setUsuario(cursor.getString(3));
                        us.setContrasena(cursor.getString(4));
                        us.setIDRol(cursor.getInt(5));

                        user.add(us);

                    } while (cursor.moveToNext());
                }
                cursor.close(); // Asegurarse de cerrar el cursor
                return user;
            } catch (Exception e) {
                Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        return null;
    }

    public ArrayList<objetoUsuario> informacionlog(String cc) {
        if(cc!="" ) {
            try {
                SQLiteDatabase db = this.getWritableDatabase();

                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE cedula = ?", new String[]{cc});
                ArrayList<objetoUsuario> user = new ArrayList<>();
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        objetoUsuario us = new objetoUsuario();
                        us.setCedula(cursor.getInt(0));
                        us.setNombres(cursor.getString(1));
                        us.setApellidos(cursor.getString(2));
                        us.setUsuario(cursor.getString(3));
                        us.setContrasena(cursor.getString(4));
                        us.setIDRol(cursor.getInt(5));

                        user.add(us);

                    } while (cursor.moveToNext());
                }
                return user;
            } catch (Exception e) {
                Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        return null;
    }

    public boolean loginUsuario(String a, String b) {
     if(a!="" && b!=""){

         try {
             SQLiteDatabase db = this.getWritableDatabase();

             String contraseña = b;
             String usuario = a;
             String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE Contrasena = ? AND Usuario = ?";
             String[] selectionArgs = {contraseña, usuario};

             Cursor cursor = db.rawQuery(query, selectionArgs);
             if (cursor != null && cursor.moveToFirst()) {
                 int aa = cursor.getInt(0);
                 guardarValorAA(aa); // Guardar el valor de aa en SharedPreferences
                 cursor.close();
                 return true;
             } else {
                 return false;
             }
         } catch (Exception e) {
             Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
             return false;
         }
     }
     return false;
    }

    private void guardarValorAA(int aa) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("valor_aa", aa);
        editor.apply();
    }

    public int obtenerValorAA() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt("valor_aa", 0);
    }
}