package com.foreroinc.abogacord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foreroinc.abogacord.db.DbProcesos;
import com.foreroinc.abogacord.db.DbUsuarios;
import com.foreroinc.abogacord.recycler.adaptadorProceso;
import com.foreroinc.abogacord.recycler.objetoProceso;
import com.foreroinc.abogacord.recycler.objetoUsuario;

import java.util.ArrayList;


public class lista_abogado extends Fragment implements adaptadorProceso.ListItemClick {

 RecyclerView recycler;
 DbProcesos proceso;

 DbUsuarios usuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lista_abogado, container, false);

        recycler = view.findViewById(R.id.lista_abogado);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));

        proceso = new DbProcesos(requireContext());
        usuarios= new DbUsuarios(requireContext());

        ArrayList<objetoProceso> user = proceso.informacionProceso(String.valueOf(usuarios.obtenerValorAA()));

        adaptadorProceso Ad = new adaptadorProceso(user, this);
          recycler.setAdapter(Ad);
          Ad.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onListItemClick(int clickedItem) {
        ArrayList<objetoProceso> listaProcesos = proceso.informacionProceso(String.valueOf(usuarios.obtenerValorAA()));
        objetoProceso procesoSeleccionado = listaProcesos.get(clickedItem);




        int estadoInt = procesoSeleccionado.getEstado();
        String estadoString = "";
        switch (estadoInt) {
            case 1:
                estadoString = "Abierto";
                break;
            case 2:
                estadoString = "Cerrado";
                break;
            case 3:
                estadoString = "Resuelto";
                break;
            case 4:
                estadoString = "En Trámite";
                break;
            case 5:
                estadoString = "En Juzgado";
                break;
            case 6:
                estadoString = "Apelación";
                break;
            case 7:
                estadoString = "Por Asignar";
                break;
            case 8:
                estadoString = "Indagatoria";
                break;
            default:
                estadoString = "Estado Desconocido";
                break;
        }

        Detalles detallesFragment = new Detalles();
        Bundle bundle = new Bundle();
        bundle.putInt("idCaso", procesoSeleccionado.getIDCaso());
        bundle.putString("nombre", procesoSeleccionado.getNombre());
        bundle.putString("descripcion", procesoSeleccionado.getDescripcion());
        bundle.putString("cedulaCliente", String.valueOf(procesoSeleccionado.getCedulaCliente()));
        bundle.putString("estado", estadoString);
        bundle.putInt("abogado",usuarios.obtenerValorAA());

        // Se guarda el estado como String en lugar de entero
        detallesFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, detallesFragment)
                .addToBackStack(null)
                .commit();
    }
}