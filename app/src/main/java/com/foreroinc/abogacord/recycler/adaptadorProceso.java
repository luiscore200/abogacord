package com.foreroinc.abogacord.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.foreroinc.abogacord.R;

import java.util.ArrayList;

public class adaptadorProceso extends RecyclerView.Adapter<adaptadorProceso.ViewHolderDatos> {

    private ArrayList<objetoProceso> nombre;
    private ListItemClick seleccion;

    public adaptadorProceso(ArrayList<objetoProceso> nombre, ListItemClick listener) {
        this.nombre = nombre;
        this.seleccion = listener;
    }

    public interface ListItemClick {
        void onListItemClick(int clickedItem);
    }

    @Override
    public adaptadorProceso.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlista_abogado, parent, false);
        return new ViewHolderDatos(vista);
    }

    @Override
    public void onBindViewHolder(adaptadorProceso.ViewHolderDatos holder, int position) {
        holder.asignarDatosProceso(nombre.get(position));
    }

    @Override
    public int getItemCount() {
        return nombre.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreTextView, estadoTextView;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre_recicler_abogado);
            estadoTextView = itemView.findViewById(R.id.estado_recicler_abogado);

            itemView.setOnClickListener(this); // Establecer el OnClickListener en la vista del elemento
        }

        public void asignarDatosProceso(objetoProceso item) {
            nombreTextView.setText(item.getNombre());
            int estadoInt = item.getEstado();
            String estadoString;

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

            estadoTextView.setText(estadoString);
        }

        @Override
        public void onClick(View view) {
            int clickedItem = getAdapterPosition();
            seleccion.onListItemClick(clickedItem);
        }
    }
}