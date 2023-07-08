package com.foreroinc.abogacord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.foreroinc.abogacord.db.DbRegistros;
import com.foreroinc.abogacord.recycler.ReporteCaso;
import com.foreroinc.abogacord.recycler.adaptadorRegistro;
import com.foreroinc.abogacord.recycler.adaptadorRegistro2;
import com.foreroinc.abogacord.ui.ApiServiceManager;

import java.util.ArrayList;
import java.util.List;


public class Registro2 extends Fragment implements adaptadorRegistro.ListItem {

    private RecyclerView recyclerView;
    DbRegistros registro;
    private adaptadorRegistro2 adaptador;

    List<ReporteCaso> lista;
    private int expandedItem = RecyclerView.NO_POSITION; // Posición del elemento expandido

    public Registro2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registro2, container, false);
        registro= new DbRegistros(requireContext());

        lista = registro.ver();
        // Configurar el RecyclerView
        recyclerView = rootView.findViewById(R.id.lista_registro_recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adaptador = new adaptadorRegistro2(lista, this::onListItemClick,requireContext());
        recyclerView.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();



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