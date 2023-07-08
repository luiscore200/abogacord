package com.foreroinc.abogacord;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.foreroinc.abogacord.db.DbProcesos;
import com.foreroinc.abogacord.ui.InternalStorageManager;

import java.util.List;


public class fichero extends Fragment {

    DbProcesos procesos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_fichero, container, false);
        procesos= new DbProcesos(requireContext());
        String filename = "myfile.txt";
        String fileContent = InternalStorageManager.readFile(requireContext(), filename);

        readAndPrintFile(requireContext());
        TextView textView = vista.findViewById(R.id.fichero);
       textView.setText(fileContent);

        return vista;
    }

    public void readAndPrintFile(Context context) {
        String filename = "myfile.txt";
        List<InternalStorageManager.ListItem> list = InternalStorageManager.readFile2(context, filename);
        for (InternalStorageManager.ListItem item : list) {
            int index = item.getIndex();
            String name = item.getName();
            String a="Index: " + index + ", Name: " + name;
            Toast.makeText(requireContext(),a,Toast.LENGTH_SHORT).show();
            procesos.insertarRol(String.valueOf(index),name);
        }
    }


}