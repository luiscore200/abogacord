package com.foreroinc.abogacord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foreroinc.abogacord.db.DbProcesos;
import com.foreroinc.abogacord.db.DbRegistros;
import com.foreroinc.abogacord.db.DbUsuarios;
import com.foreroinc.abogacord.recycler.objetoUsuario;

import java.util.ArrayList;

public class Detalles extends Fragment {

    DbUsuarios user;
    ArrayList<objetoUsuario> datos;

    DbRegistros registro;


    DbProcesos procesos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalles, container, false);
        procesos = new DbProcesos(requireContext());
        user = new DbUsuarios(requireContext());
        registro= new DbRegistros(requireContext());

        datos = user.informacionlog(String.valueOf(user.obtenerValorAA()));



        TextView txtIdCaso = view.findViewById(R.id.txtIdCaso);
        TextView txtNombre = view.findViewById(R.id.txtNombre);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcion);
        TextView txtCedulaCliente = view.findViewById(R.id.txtCedulaCliente);
        TextView txtEstado = view.findViewById(R.id.txtEstado);
        TextView txtAbogado= view.findViewById(R.id.txtCedulaAbogado);
        Button btnRegresar = view.findViewById(R.id.btnRegresar);
        Button borrar = view.findViewById(R.id.btnBorrar);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int idCaso = bundle.getInt("idCaso");
            String nombre = bundle.getString("nombre");
            String descripcion = bundle.getString("descripcion");
            String cedulaCliente = bundle.getString("cedulaCliente");
            String estado = bundle.getString("estado");
            int cedulaAbogado = bundle.getInt("abogado");


            txtIdCaso.setText(String.valueOf(idCaso));
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtCedulaCliente.setText(cedulaCliente);
            txtEstado.setText(String.valueOf(estado));
            txtAbogado.setText(String.valueOf(cedulaAbogado));
        }

        if(datos.size()>0){
            if(datos.get(0).getIDRoll()==3){

            borrar.setVisibility(View.GONE);

            }else{

            borrar.setVisibility(View.VISIBLE);

            }



        }

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              int c= procesos.eliminar_caso(txtIdCaso.getText().toString());


                if(c>0){
                    Toast.makeText(requireContext()," se ha eliminado correctamente", Toast.LENGTH_LONG).show();

                }else{

                    Toast.makeText(requireContext(),"no se ha eliminado correctamente", Toast.LENGTH_LONG).show();
                }



               // procesos.eliminar_registros_abogado_proceso(txtIdCaso.getText().toString());
               // procesos.eliminar_registros_procesos(txtIdCaso.getText().toString());
             if(datos.get(0).getIDRoll()==2){
                 requireActivity().getSupportFragmentManager().beginTransaction()
                         .replace(R.id.content, new lista_abogado())
                         .addToBackStack(null)
                         .commit();

             }else{

                 requireActivity().getSupportFragmentManager().beginTransaction()
                         .replace(R.id.content, new lista_administrador())
                         .addToBackStack(null)
                         .commit();
             }

            }
        });


        return view;
    }
  //  Recuerda que debes ajustar los IDs de los TextViews y el botón de acuerdo al diseño del fragmento DetallesFragment.








}