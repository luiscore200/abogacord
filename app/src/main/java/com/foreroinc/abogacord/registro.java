package com.foreroinc.abogacord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foreroinc.abogacord.recycler.adaptadorRegistro;
import com.foreroinc.abogacord.ui.ApiServiceManager;
import com.foreroinc.abogacord.recycler.ReporteCaso;

import java.util.ArrayList;
import java.util.List;


public class registro extends Fragment implements adaptadorRegistro.ListItem {

    private RecyclerView recyclerView;
    private adaptadorRegistro adaptador;
    private int expandedItem = RecyclerView.NO_POSITION; // Posición del elemento expandido

    public registro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registro, container, false);

        // Configurar el RecyclerView
        recyclerView = rootView.findViewById(R.id.lista_registro_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptador = new adaptadorRegistro(new ArrayList<>(), this,requireContext());
        recyclerView.setAdapter(adaptador);

        // Realizar la llamada al API para obtener los reportes de casos
        ApiServiceManager apiServiceManager = new ApiServiceManager();
      apiServiceManager.getReportesCasos(new ApiServiceManager.ApiCallback<List<ReporteCaso>>() {
            @Override
            public void onSuccess(List<ReporteCaso> reportesCasos) {
                // Actualizar los datos del adaptador y refrescar el RecyclerView
                adaptador.setReporte(reportesCasos);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(getContext(), "Error al obtener los datos: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItem) {
        if (expandedItem != RecyclerView.NO_POSITION) {
            int previousExpandedItem = expandedItem;
            adaptador.collapseItem();
            if (previousExpandedItem == clickedItem) {
                expandedItem = RecyclerView.NO_POSITION;
                return;
            }
        }

        adaptador.expandItem(clickedItem);
        expandedItem = clickedItem;

        ReporteCaso reporteCaso = adaptador.getReporte().get(clickedItem);
         String id = reporteCaso.getPeriodo();
        Toast.makeText(getContext(), "cc del objeto seleccionado: " + id, Toast.LENGTH_SHORT).show();
    }









}