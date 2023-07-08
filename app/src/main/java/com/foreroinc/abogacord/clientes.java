package com.foreroinc.abogacord;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.foreroinc.abogacord.db.DbUsuarios;
import com.foreroinc.abogacord.recycler.objetoUsuario;
import java.util.ArrayList;


public class clientes extends Fragment {//implements adaptador.ListItemClick {


    ArrayList<objetoUsuario> nombres;
    RecyclerView recycler;
    DbUsuarios usuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_clientes, container, false);

        recycler = view.findViewById(R.id.lista);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));

        usuarios = new DbUsuarios(requireContext());

        ArrayList<objetoUsuario> user = usuarios.mostrarUsuario();

      //  adaptador Ad = new adaptador(user, this);
      //  recycler.setAdapter(Ad);
     //  Ad.notifyDataSetChanged();

        return view;
    }

   // @Override
    //public void onListItemClick(int clikedItem) {
     //   String mensaje = "el nombre del objeto clickeado es: "+ clikedItem;
      //  Toast ar = Toast.makeText(requireContext(), mensaje, Toast.LENGTH_LONG);
       // ar.show();
    //}
}