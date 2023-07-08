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

public class DetalleRegistro extends Fragment {

    DbRegistros registro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_registro, container, false);

        registro= new DbRegistros(requireContext());
        TextView txtIdCaso = view.findViewById(R.id.txtIdCaso2);
        TextView txtNombre = view.findViewById(R.id.txtNombre2);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcion2);
        TextView txtCedulaCliente = view.findViewById(R.id.txtCedulaCliente2);
        TextView txtEstado = view.findViewById(R.id.txtEstado2);
        TextView txtAbogado= view.findViewById(R.id.txtCedulaAbogado2);
        TextView txtMovimiento= view.findViewById(R.id.txtMovimiento2);
        Button btnRegresar = view.findViewById(R.id.btnRegresar2);
        Button borrar = view.findViewById(R.id.btnBorrar2);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int id= bundle.getInt("id");
            int idCaso = bundle.getInt("idCaso");
            String nombre = bundle.getString("nombre");
            String descripcion = bundle.getString("descripcion");
            String cedulaCliente = bundle.getString("cedulaCliente");
            String estado = bundle.getString("estado");
            int cedulaAbogado = bundle.getInt("abogado");
            String movimiento = bundle.getString("movimiento");


            txtIdCaso.setText(String.valueOf(idCaso));
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtCedulaCliente.setText(cedulaCliente);
            txtEstado.setText(String.valueOf(estado));
            txtAbogado.setText(String.valueOf(cedulaAbogado));
            txtMovimiento.setText(movimiento);
        }




                borrar.setVisibility(View.VISIBLE);


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long ac= registro.eliminarRegistro(String.valueOf(bundle.getInt("id")));




                if (ac > 0) {

                    Toast.makeText(requireContext(), "el registro eliminado correctamente", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(requireContext(), "el registro no eliminado ", Toast.LENGTH_LONG).show();

                }


                // procesos.eliminar_registros_abogado_proceso(txtIdCaso.getText().toString());
                // procesos.eliminar_registros_procesos(txtIdCaso.getText().toString());


                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content, new registro())
                            .addToBackStack(null)
                            .commit();


            }
        });


        return view;
}
    }