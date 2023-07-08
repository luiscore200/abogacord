package com.foreroinc.abogacord.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.foreroinc.abogacord.recycler.ReporteCaso;
import com.foreroinc.abogacord.recycler.objetoProceso;

import java.util.ArrayList;
import java.util.List;

public class DbRegistros extends DbHelper{

    Context context;
    ReporteCaso proceso;

    public DbRegistros(@Nullable Context context) {
        super(context);
    }


    public List<ReporteCaso> ver(){


        try {
            SQLiteDatabase db = this.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REGISTROS, null);
            ArrayList<ReporteCaso> user = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    ReporteCaso proceso= new ReporteCaso();

                    proceso.setId(String.valueOf(cursor.getInt(0)));
                    proceso.setPeriodo(cursor.getString(1));
                    proceso.setDescripcion(cursor.getString(2));
                    proceso.setEnero(cursor.getString(3));
                    proceso.setFebrero(cursor.getString(4));
                    proceso.setMarzo(cursor.getString(5));
                    proceso.setAbril(cursor.getString(6));
                    proceso.setMayo(cursor.getString(7));
                    proceso.setJunio(cursor.getString(8));
                    proceso.setJulio(cursor.getString(9 ));
                    proceso.setAgosto(cursor.getString(10));
                    proceso.setSeptiembre(cursor.getString(11));
                    proceso.setOctubre(cursor.getString(12));
                    proceso.setNoviembre(cursor.getString(13));
                    proceso.setDiciembre(cursor.getString(14));



                    user.add(proceso);

                } while (cursor.moveToNext());
            }
            cursor.close(); // Asegurarse de cerrar el cursor

            Log.d("DbProcesos", "Consulta realizada correctamente. Datos obtenidos: " + user.size());

            return user;
        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public long insertarRegistro(String periodo,String descripcion,String enero,String febrero,String marzo,String abril,String mayo,String junio,String julio,String agosto, String septiembre,String octubre,String noviembre,String diciembre) {
        SQLiteDatabase db = this.getWritableDatabase();
        long resultado = -1;

        try {
            ContentValues values = new ContentValues();
            values.put("Periodo", periodo);
            values.put("Descripcion", descripcion);
            values.put("Enero", enero);
            values.put("Febrero", febrero);
            values.put("Marzo", marzo);
            values.put("Abril", abril);
            values.put("Mayo", mayo);
            values.put("Junio", junio);
            values.put("Julio", julio);
            values.put("Agosto", agosto);
            values.put("Septiembre", septiembre);
            values.put("Octubre", octubre);
            values.put("Noviembre", noviembre);
            values.put("Diciembre", diciembre);

         ;

            resultado = db.insert(TABLE_REGISTROS, null, values);
        } catch (Exception e) {
            // Manejo de la excepción
            Log.e("DB_ERROR", "Error al insertar el registro en la tabla " + TABLE_REGISTROS, e);
        } finally {
            db.close();
        }

        return resultado;
    }

    public int eliminarRegistro(String ID) {
        int datos = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            datos = db.delete(TABLE_REGISTROS, "ID = ?", new String[]{ID});
        } catch (Exception ex) {
            ex.toString();
        }
        return datos;
    }







}
