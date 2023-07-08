package com.foreroinc.abogacord;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foreroinc.abogacord.db.DbProcesos;
import com.foreroinc.abogacord.db.DbRegistros;
import com.foreroinc.abogacord.db.DbUsuarios;
import com.foreroinc.abogacord.recycler.objetoUsuario;

import java.util.ArrayList;

public class crearproceso extends Fragment {

    EditText caso_nombre, caso_descripcion, caso_cedulaCliente, abogado_caso;
    String caso_cedulaAbogado, caso_estado;
    Spinner opciones;
    Button crearproceso_btn_enviar;
    DbProcesos dbProcesos;
    DbUsuarios usuarios;

    DbRegistros registros;

    ArrayList<objetoUsuario> user;
    ArrayList<objetoUsuario> a;
    ArrayList<objetoUsuario> f;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crearproceso, container, false);
        abogado_caso = view.findViewById(R.id.caso_abogado_1);
        caso_nombre = view.findViewById(R.id.caso_nombre);
        caso_descripcion = view.findViewById(R.id.caso_descripcion);
        caso_cedulaCliente = view.findViewById(R.id.caso_cedulaCliente);
        caso_estado = "";
        dbProcesos = new DbProcesos(requireContext());
        usuarios = new DbUsuarios(requireContext());
        registros = new DbRegistros(requireContext());

        a = new ArrayList<objetoUsuario>();
        f = new ArrayList<objetoUsuario>();
        a = usuarios.informacionlog(String.valueOf(usuarios.obtenerValorAA()));
        int aa = a.get(0).getIDRoll();
        Toast.makeText(requireContext(), "el roll es" + aa, Toast.LENGTH_LONG).show();


        opciones = (Spinner) view.findViewById(R.id.caso_estado);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.crearproceso_estado, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        if (aa != 1) {
            abogado_caso.setVisibility(View.GONE);


        crearproceso_btn_enviar = view.findViewById(R.id.crearproceso_btn_enviar);


        caso_cedulaAbogado = String.valueOf(usuarios.obtenerValorAA());
        crearproceso_btn_enviar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                // f=usuarios.modificarUsuario(abogado_caso.getText().toString());


                String estado = "";
                estado = opciones.getSelectedItem().toString();

                if (estado.equals("Abierto")) {
                    caso_estado = "1";
                } else if (estado.equals("Cerrado")) {
                    caso_estado = "2";
                } else if (estado.equals("Resuelto")) {
                    caso_estado = "3";
                } else if (estado.equals("En Tramite")) {
                    caso_estado = "4";
                } else if (estado.equals("En Juzgado")) {
                    caso_estado = "5";
                } else if (estado.equals("Apelacion")) {
                    caso_estado = "6";
                } else if (estado.equals("Por Asignar")) {
                    caso_estado = "7";
                } else if (estado.equals("Indagatoria")) {
                    caso_estado = "8";
                }


                user = new ArrayList<objetoUsuario>();

                user = dbProcesos.confirmarRollUsuario(String.valueOf(caso_cedulaCliente.getText()));

                if(user.size()>0) {
                    if (user.get(0).getIDRoll() == 3) {
                        Toast.makeText(requireContext(), "intentnado crear proceso", Toast.LENGTH_LONG).show();

                        long datos = dbProcesos.insertarProceso(
                                caso_nombre.getText().toString(),
                                caso_descripcion.getText().toString(),
                                caso_cedulaCliente.getText().toString(),
                                caso_estado
                        );


                        if (datos > 0) {
                            Toast.makeText(requireContext(), "Proceso Guardado", Toast.LENGTH_LONG).show();
                            long ab;
                            int a = dbProcesos.ultimo_proceso();

                            Toast.makeText(requireContext(), String.valueOf(a), Toast.LENGTH_LONG).show();
                            ab = dbProcesos.insertarProcesoAbogado(String.valueOf(a), caso_cedulaAbogado);





                            if (ab > 0) {

                                Toast.makeText(requireContext(), "el proceso se ha asignado correctamente", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(requireContext(), "el proceso no se ha asignado correctamente", Toast.LENGTH_LONG).show();

                            }


                            limpiar();
                        } else {
                            Toast.makeText(requireContext(), "Error al Guardar", Toast.LENGTH_LONG).show();
                            limpiar();
                        }

                    } else if (user.get(0).getIDRoll() == 2 || user.get(0).getIDRoll() == 1) {

                        Toast.makeText(requireContext(), "Este usuario no es un cliente", Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(requireContext(), "Este usuario no es un cliente, no existe", Toast.LENGTH_LONG).show();
                }


            }
        });

    }else{ ///////////////////////////////////////////////
            crearproceso_btn_enviar = view.findViewById(R.id.crearproceso_btn_enviar);
            abogado_caso.setVisibility(View.VISIBLE);


            crearproceso_btn_enviar.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {


                    String roll= abogado_caso.getText().toString();

                    String estado = "";
                    estado = opciones.getSelectedItem().toString();

                    if (estado.equals("Abierto")) {
                        caso_estado = "1";
                    } else if (estado.equals("Cerrado")) {
                        caso_estado = "2";
                    } else if (estado.equals("Resuelto")) {
                        caso_estado = "3";
                    } else if (estado.equals("En Tramite")) {
                        caso_estado = "4";
                    } else if (estado.equals("En Juzgado")) {
                        caso_estado = "5";
                    } else if (estado.equals("Apelacion")) {
                        caso_estado = "6";
                    } else if (estado.equals("Por Asignar")) {
                        caso_estado = "7";
                    } else if (estado.equals("Indagatoria")) {
                        caso_estado = "8";
                    }


                    user = new ArrayList<objetoUsuario>();

                    user = dbProcesos.confirmarRollUsuario(String.valueOf(caso_cedulaCliente.getText()));

                    f= dbProcesos.confirmarRollUsuario(roll);

                    if(f.size()>0) {
                        if(f.get(0).getIDRoll()==2) {

                        if(user.size()>0) {
                            if (user.get(0).getIDRoll() == 3) {
                                Toast.makeText(requireContext(), "intentnado crear proceso", Toast.LENGTH_LONG).show();

                                long datos = dbProcesos.insertarProceso(
                                        caso_nombre.getText().toString(),
                                        caso_descripcion.getText().toString(),
                                        caso_cedulaCliente.getText().toString(),
                                        caso_estado
                                );


                                if (datos > 0) {

                                    ////////////
                                    Toast.makeText(requireContext(), "Proceso Guardado", Toast.LENGTH_LONG).show();
                                    long ab;
                                    int a = dbProcesos.ultimo_proceso();

                                    Toast.makeText(requireContext(), String.valueOf(a), Toast.LENGTH_LONG).show();
                                    ab = dbProcesos.insertarProcesoAbogado(String.valueOf(a), abogado_caso.getText().toString());



                                    //////////////////////////////

                                    if (ab > 0) {

                                        Toast.makeText(requireContext(), "el proceso se ha asignado correctamente", Toast.LENGTH_LONG).show();
                                    } else {

                                        Toast.makeText(requireContext(), "el proceso no se ha asignado correctamente", Toast.LENGTH_LONG).show();

                                    }


                                    limpiar();
                                } else {
                                    Toast.makeText(requireContext(), "Error al Guardar", Toast.LENGTH_LONG).show();
                                    limpiar();
                                }

                            } else if (user.get(0).getIDRoll() == 2 || user.get(0).getIDRoll() == 1) {

                                Toast.makeText(requireContext(), "Este usuario no es un cliente", Toast.LENGTH_LONG).show();
                            }//
                        }else{
                            Toast.makeText(requireContext(), "Este usuario no es un cliente, no existe", Toast.LENGTH_LONG).show();

                        }
                        }else{

                            Toast.makeText(requireContext(), "Este usuario no es un abogado", Toast.LENGTH_LONG).show();
                        }
                    }else{

                        Toast.makeText(requireContext(), "Este usuario no es un abogado, no existe", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }

        return view;
    }

    private void limpiar() {
        caso_nombre.setText("");
        caso_descripcion.setText("");
        caso_cedulaCliente.setText("");
        opciones.setSelection(1);
    }
}

