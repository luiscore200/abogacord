package com.foreroinc.abogacord.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.foreroinc.abogacord.crearusuario;
import com.foreroinc.abogacord.recycler.objetoProceso;
import com.foreroinc.abogacord.recycler.objetoUsuario;

import java.util.ArrayList;

public class DbProcesos extends DbHelper{
    Context context;

    ArrayList<objetoProceso> usuarios;
    objetoProceso proceso;


    public DbProcesos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProceso(String Nombre, String Descripcion,  String cedulaCliente, String Estado){
        long datosProceso = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre", Nombre);
            values.put("Descripcion", Descripcion);
            values.put("cedulaCliente", cedulaCliente);
            values.put("Estado", Estado);

            datosProceso = db.insert(TABLE_PROCESOS, null, values);
        } catch(Exception ex){
            ex.toString();
        }
        return datosProceso;
    }

    public long insertarRol(String id, String nombre){
        long datosProceso = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("IDRol", id);
            values.put("Rolname", nombre);


            datosProceso = db.insert(TABLE_ROL, null, values);
        } catch(Exception ex){
            ex.toString();
        }
        return datosProceso;
    }


    public long insertarProcesoAbogado(String idcaso, String idabogado){
        long datosProceso = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("IDCaso", idcaso);
            values.put("cedulaAbogado", idabogado);


            datosProceso = db.insert(TABLE_ABOGADO_PROCESO, null, values);
        } catch(Exception ex){
            ex.toString();
        }
        return datosProceso;
    }

    public int eliminar_caso(String cc) {
        Log.d("Db", "idcaso: " + cc);
        int resultado = 0; // Variable para almacenar el resultado

        if (!cc.isEmpty()) {
            try {
                SQLiteDatabase db = this.getWritableDatabase();
                db.beginTransaction();

                // Eliminar registros de la tabla TABLE_ABOGADO_PROCESO
                int filasEliminadas1 = db.delete(TABLE_ABOGADO_PROCESO, "IDCaso = ?", new String[]{cc});
                Log.d("Db", "idcaso: " + filasEliminadas1);
                // Eliminar registros de la tabla TABLE_PROCESOS
                int filasEliminadas2 = db.delete(TABLE_PROCESOS, "IDCaso = ?", new String[]{cc});
                Log.d("Db", "idcaso: " + filasEliminadas2);
                db.setTransactionSuccessful(); // Confirmar la transacción si no hay errores
                db.endTransaction(); // Finalizar la transacción

                // Verificar si se eliminaron filas en ambas tablas
                if (filasEliminadas1 > 0 && filasEliminadas2 > 0) {
                    resultado = 1; // Se cumplieron ambas consultas
                    Log.d("Db", "idcaso: " + resultado);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    public int ultimo_proceso() {
        int ultimoId = -1; // Valor predeterminado si no se encuentra ningún registro

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT IDCaso FROM " + TABLE_PROCESOS + " ORDER BY IDCaso DESC LIMIT 1", null);

            if (cursor != null && cursor.moveToFirst()) {
                ultimoId = cursor.getInt(0);
                Log.d("DbProcesos", "Consulta realizada correctamente. Datos obtenidos: " + ultimoId);

            }

            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ultimoId;
    }
    public int informacionAbogado(String cc) {
        int cedulaAbogado = -1; // Valor predeterminado si no se encuentra ningún registro
        Log.d("Db", "idcaso: " + cc);
        if (!cc.isEmpty()) {
            try {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT cedulaAbogado FROM "+ TABLE_ABOGADO_PROCESO +" WHERE IDCaso = ?", new String[]{cc});

                if (cursor != null && cursor.moveToFirst()) {
                    cedulaAbogado = cursor.getInt(0);
                    Log.d("Db", "Consulta realizada correctamente. cedulaAbogado obtenida: " + cedulaAbogado);
                    cursor.close();
                    return cedulaAbogado;
                }

                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return cedulaAbogado;
    }


    public ArrayList<objetoUsuario> confirmarRollUsuario(String cc) {
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
    public ArrayList<objetoProceso> informacionProcesoAministrador() {

            try {
                SQLiteDatabase db = this.getWritableDatabase();

                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PROCESOS, null);
                ArrayList<objetoProceso> user = new ArrayList<>();
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                      proceso = new objetoProceso();
                        proceso = new objetoProceso();
                        proceso.setIDCaso(cursor.getInt(0));
                        proceso.setNombre(cursor.getString(1));
                        proceso.setDescripcion(cursor.getString(2));
                        proceso.setCedulaCliente(cursor.getInt(3));
                        proceso.setEstado(cursor.getInt(4));

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
    public ArrayList<objetoProceso> informacionProcesoCliente(String cc) {
        if (cc!="") {
            try {
                SQLiteDatabase db = this.getWritableDatabase();

                Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_PROCESOS +" WHERE cedulaCliente = ?", new String[]{cc});


                ArrayList<objetoProceso> user = new ArrayList<>();
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        proceso = new objetoProceso();
                        proceso.setIDCaso(cursor.getInt(0));
                        proceso.setNombre(cursor.getString(1));
                        proceso.setDescripcion(cursor.getString(2));
                        proceso.setCedulaCliente(cursor.getInt(3));
                        proceso.setEstado(cursor.getInt(4));

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
        return null;
    }
    public ArrayList<objetoProceso> informacionProceso(String cc) {
        usuarios = new ArrayList<>();

        if (!cc.isEmpty()) {
            try {
                SQLiteDatabase db = this.getWritableDatabase();

                // Buscar los casos relacionados con la cedulaAbogado
                Cursor cursorCasos = db.rawQuery("SELECT * FROM "+ TABLE_ABOGADO_PROCESO +" WHERE cedulaAbogado = ?", new String[]{cc});

                if (cursorCasos != null && cursorCasos.moveToFirst()) {
                    do {
                        int idCaso = cursorCasos.getInt(0);

                        // Obtener la información del usuario asociado al caso
                        Cursor cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_PROCESOS + " WHERE IDCaso = ?", new String[]{String.valueOf(idCaso)});

                        if (cursorUsuario != null && cursorUsuario.moveToFirst()) {
                            proceso = new objetoProceso();
                            proceso.setIDCaso(cursorUsuario.getInt(0));
                            proceso.setNombre(cursorUsuario.getString(1));
                            proceso.setDescripcion(cursorUsuario.getString(2));
                            proceso.setCedulaCliente(cursorUsuario.getInt(3));
                            proceso.setEstado(cursorUsuario.getInt(4));





                                    usuarios.add(proceso);

                            cursorUsuario.close();
                        }
                    } while (cursorCasos.moveToNext());
                }

                if (cursorCasos != null) {
                    cursorCasos.close();
                }

                db.close();
            } catch (Exception e) {
                Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        return usuarios;
    }

}
