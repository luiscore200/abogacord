
package com.foreroinc.abogacord.recycler;


        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.foreroinc.abogacord.R;
        import com.foreroinc.abogacord.db.DbRegistros;

        import java.util.ArrayList;
        import java.util.List;

public class adaptadorRegistro extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ReporteCaso> reporte;
    private int expandedItem = RecyclerView.NO_POSITION; // Posición del elemento expandido
    private ListItem seleccion;

    Context contexto;

    private static final int VIEW_TYPE_DEFAULT = 0;
    private static final int VIEW_TYPE_EXPANDED = 1;

    public adaptadorRegistro(List<ReporteCaso> caso, ListItem seleccion, Context contexto) {
        this.reporte = caso;
        this.seleccion = seleccion;
        this.contexto=contexto;
    }

    public void setReporte(List<ReporteCaso> reporte) {
        this.reporte = reporte;
        notifyDataSetChanged();
    }

    public List<ReporteCaso> getReporte() {
        return reporte;
    }

    public interface ListItem {
        void onListItemClick(int clickedItem);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_EXPANDED) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expandido, parent, false);
            return new ExpandedViewHolder(vista);
        } else {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlista, parent, false);
            return new DefaultViewHolder(vista);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExpandedViewHolder) {
            ExpandedViewHolder expandedViewHolder = (ExpandedViewHolder) holder;
            expandedViewHolder.asignarDatosRegistro(reporte.get(position));
        } else {
            DefaultViewHolder defaultViewHolder = (DefaultViewHolder) holder;
            defaultViewHolder.asignarDatosRegistro(reporte.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return reporte.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == expandedItem) {
            return VIEW_TYPE_EXPANDED;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    public void expandItem(int position) {
        if (expandedItem != RecyclerView.NO_POSITION) {
            notifyItemChanged(expandedItem);
        }
        expandedItem = position;
        notifyItemChanged(expandedItem);
    }

    public void collapseItem() {
        if (expandedItem != RecyclerView.NO_POSITION) {
            notifyItemChanged(expandedItem);
            expandedItem = RecyclerView.NO_POSITION;
        }
    }

    public boolean isItemExpanded(int position) {
        return position == expandedItem;
    }

    public class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, id, punto;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_recicler);
            id = itemView.findViewById(R.id.id_recicler);
            punto = itemView.findViewById(R.id.punto);

            itemView.setOnClickListener(this);
        }

        public void asignarDatosRegistro(ReporteCaso item) {
            id.setText(String.valueOf(getAdapterPosition()));
            nombre.setText(" ");
            punto.setText(". ");
        }

        @Override
        public void onClick(View view) {
            int clickedItem = getAdapterPosition();
            if (isItemExpanded(clickedItem)) {
                collapseItem();
            } else {
                expandItem(clickedItem);
            }
            seleccion.onListItemClick(clickedItem);
            Log.d("RecyclerView", "Elemento clicado en la posición: " + clickedItem);
        }
    }

    public class ExpandedViewHolder extends RecyclerView.ViewHolder {

     Button boton;   TextView id, periodo, descripcion, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre;

        public ExpandedViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_recicler_expanded);
            periodo = itemView.findViewById(R.id.periodo_expanded);
            descripcion = itemView.findViewById(R.id.descripcion_expanded);
            enero = itemView.findViewById(R.id.enero_expanded);
            febrero = itemView.findViewById(R.id.febrero_expanded);
            marzo = itemView.findViewById(R.id.marzo_expanded);
            abril = itemView.findViewById(R.id.abril_expanded);
            mayo = itemView.findViewById(R.id.mayo_expanded);
            junio = itemView.findViewById(R.id.junio_expanded);
            julio = itemView.findViewById(R.id.julio_expanded);
            agosto = itemView.findViewById(R.id.agosto_expanded);
            septiembre = itemView.findViewById(R.id.septiembre_expanded);
            octubre = itemView.findViewById(R.id.octubre_expanded);
            noviembre = itemView.findViewById(R.id.noviembre_expanded);
            diciembre = itemView.findViewById(R.id.diciembre_expanded);
            boton = itemView.findViewById(R.id.boton_expanded);
        }

        public void asignarDatosRegistro(ReporteCaso item) {
            id.setText(String.valueOf(getAdapterPosition()));
            periodo.setText(item.getPeriodo());
            descripcion.setText(item.getDescripcion());
            enero.setText(item.getEnero());
            febrero.setText(item.getFebrero());
            marzo.setText(item.getMarzo());
            abril.setText(item.getAbril());
            mayo.setText(item.getMayo());
            junio.setText(item.getJunio());
            julio.setText(item.getJulio());
            agosto.setText(item.getAgosto());
            septiembre.setText(item.getSeptiembre());
            octubre.setText(item.getOctubre());
            noviembre.setText(item.getNoviembre());
            diciembre.setText(item.getDiciembre());
            boton.setText("Agregar");

            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DbRegistros reg= new DbRegistros(contexto);

                  long ac=  reg.insertarRegistro(item.getPeriodo(),item.getDescripcion(),item.getEnero(),item.getFebrero(),
                            item.getMarzo(),item.getAbril(), item.getMayo(),item.getJunio(),item.getJulio(),
                            item.getAgosto(),item.getSeptiembre(), item.getOctubre(),item.getNoviembre(),item.getDiciembre());
                    if (ac > 0) {

                        Toast.makeText(contexto, "el proceso se ha asignado correctamente", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(contexto, "el proceso no se ha asignado correctamente", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
}
}
