package com.foreroinc.abogacord;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.foreroinc.abogacord.db.DbUsuarios;
import com.foreroinc.abogacord.recycler.objetoUsuario;

import java.util.ArrayList;


public class modificar_usuario extends Fragment {

    ArrayList<objetoUsuario> user;
    DbUsuarios db;
    String usuario_rol;
    RadioButton rb1, rb2, rb3;
    Button modificarusuario_btn_enviar;
    Button eliminarusuario_btn_enviar;

    Button buscarusuario_btn_enviar;

    EditText usuario_cedula, usuario_nombres, usuario_apellidos, usuario_usuario, usuario_contrasena;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_modificar_usuario, container, false);


        user= new ArrayList<objetoUsuario>();
        db= new DbUsuarios(requireContext());



        usuario_cedula = view.findViewById(R.id.modificarusuario_cedula);
        usuario_nombres = view.findViewById(R.id.modificarusuario_nombres);
        usuario_apellidos = view.findViewById(R.id.modificarusuario_apellidos);
        usuario_usuario = view.findViewById(R.id.modificarusuario_usuario);
        usuario_contrasena = view.findViewById(R.id.modificarusuario_contraseña);
        usuario_rol = "";

        rb1 = view.findViewById(R.id.modificar_btn_administrador);
        rb2 = view.findViewById(R.id.modificar_btn_abogado);
        rb3 = view.findViewById(R.id.modificar_btn_cliente);
        buscarusuario_btn_enviar = view.findViewById(R.id.buscar_usuario_boton);
        modificarusuario_btn_enviar = view.findViewById(R.id.modificar_usuario_boton);
        eliminarusuario_btn_enviar = view.findViewById(R.id.eliminar_usuario_boton);

        eliminarusuario_btn_enviar.setEnabled(false);
        modificarusuario_btn_enviar.setEnabled(false);
        usuario_nombres.setEnabled(false);
        usuario_apellidos.setEnabled(false);
        usuario_usuario.setEnabled(false);
        usuario_contrasena.setEnabled(false);
        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);


        buscarusuario_btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user=db.modificarUsuario(usuario_cedula.getText().toString());

               if(user.size()>0){

                   eliminarusuario_btn_enviar.setEnabled(true);
                   modificarusuario_btn_enviar.setEnabled(true);
                   usuario_nombres.setEnabled(true);
                   usuario_apellidos.setEnabled(true);
                   usuario_usuario.setEnabled(true);
                   usuario_contrasena.setEnabled(true);
                   rb1.setEnabled(true);
                   rb2.setEnabled(true);
                   rb3.setEnabled(true);

                   usuario_nombres.setText(user.get(0).getNombres());
                   usuario_apellidos.setText(user.get(0).getApellidos());
                   usuario_usuario.setText(user.get(0).getUsuario());
                   usuario_contrasena.setText(user.get(0).getContrasena());

                   if (user.get(0).getIDRoll()==1) {
                       rb1.isChecked();
                   } else if (user.get(0).getIDRoll()==2) {
                       rb2.isChecked();
                   } else if (user.get(0).getIDRoll()==3) {
                       rb3.isChecked();
                   }
               }else{

                   Toast.makeText(requireContext(),"no se ha encontrado un usuario con esa identificacion",Toast.LENGTH_LONG).show();
               }

            }
        });
        modificarusuario_btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()) {
                    usuario_rol = "1";
                } else if (rb2.isChecked()) {
                    usuario_rol = "2";
                } else if (rb3.isChecked()) {
                    usuario_rol = "3";
                }

                System.out.println("Cedula: " + usuario_cedula.getText().toString());
                System.out.println("Nombres: " + usuario_nombres.getText().toString());
                System.out.println("Apellidos: " + usuario_apellidos.getText().toString());
                System.out.println("Usuario: " + usuario_usuario.getText().toString());
                System.out.println("Contrasena: " + usuario_contrasena.getText().toString());
                System.out.println("Rol: " + usuario_rol);


                int datos = db.modificarUsuario(
                        usuario_cedula.getText().toString(),
                        usuario_nombres.getText().toString(),
                        usuario_apellidos.getText().toString(),
                        usuario_usuario.getText().toString(),
                        usuario_contrasena.getText().toString(),
                        usuario_rol
                );

                if (datos > 0) {
                    Toast.makeText(requireContext(), "Usuario modificado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(requireContext(), "Error al modificar", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });
        eliminarusuario_btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()) {
                    usuario_rol = "1";
                } else if (rb2.isChecked()) {
                    usuario_rol = "2";
                } else if (rb3.isChecked()) {
                    usuario_rol = "3";
                }

                System.out.println("Cedula: " + usuario_cedula.getText().toString());
                System.out.println("Nombres: " + usuario_nombres.getText().toString());
                System.out.println("Apellidos: " + usuario_apellidos.getText().toString());
                System.out.println("Usuario: " + usuario_usuario.getText().toString());
                System.out.println("Contrasena: " + usuario_contrasena.getText().toString());
                System.out.println("Rol: " + usuario_rol);


                 int datos = db.eliminarUsuario(usuario_cedula.getText().toString());



                if (datos > 0) {
                    Toast.makeText(requireContext(), "Usuario eliminado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(requireContext(), "Error al eliminar", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });




        return view;
    }

    private void limpiar() {
        usuario_cedula.setText("");
        usuario_nombres.setText("");
        usuario_apellidos.setText("");
        usuario_usuario.setText("");
        usuario_contrasena.setText("");
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
    }
}