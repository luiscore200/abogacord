package com.foreroinc.abogacord;
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

public class crearusuario extends Fragment {

    EditText usuario_cedula, usuario_nombres, usuario_apellidos, usuario_usuario, usuario_contrasena;
    String usuario_rol;
    RadioButton rb1, rb2, rb3;
    Button crearusuario_btn_enviar;
    DbUsuarios dbUsuarios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crearusuario, container, false);

        usuario_cedula = view.findViewById(R.id.usuario_cedula);
        usuario_nombres = view.findViewById(R.id.usuario_nombres);
        usuario_apellidos = view.findViewById(R.id.usuario_apellidos);
        usuario_usuario = view.findViewById(R.id.usuario_usuario);
        usuario_contrasena = view.findViewById(R.id.usuario_contrasena);
        usuario_rol = "";

        rb1 = view.findViewById(R.id.btn_administrador);
        rb2 = view.findViewById(R.id.btn_abogado);
        rb3 = view.findViewById(R.id.btn_cliente);
        crearusuario_btn_enviar = view.findViewById(R.id.crearusuario_btn_enviar);

        dbUsuarios = new DbUsuarios(requireContext());



        crearusuario_btn_enviar.setOnClickListener(new View.OnClickListener() {
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


                long datos = dbUsuarios.insertarUsuario(
                        usuario_cedula.getText().toString(),
                        usuario_nombres.getText().toString(),
                        usuario_apellidos.getText().toString(),
                        usuario_usuario.getText().toString(),
                        usuario_contrasena.getText().toString(),
                        usuario_rol
                );

                if (datos > 0) {
                    Toast.makeText(requireContext(), "Usuario Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(requireContext(), "Error al Guardar", Toast.LENGTH_LONG).show();
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
