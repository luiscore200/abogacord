package com.foreroinc.abogacord;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.foreroinc.abogacord.db.DbUsuarios;

public class consultarcita extends Fragment {

    DbUsuarios dbUsuarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultarcita, container, false);


        return view;
    }
}
