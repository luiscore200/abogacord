package com.foreroinc.abogacord;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foreroinc.abogacord.db.DbHelper;
import com.foreroinc.abogacord.ui.InternalStorageManager;

public class splashactivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        DbHelper dbHelper = new DbHelper(splashactivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(splashactivity.this, "Base de datos creada", Toast.LENGTH_LONG).show();
            db.close();
        }else{
            Toast.makeText(splashactivity.this, "Error al crear la base de datos", Toast.LENGTH_LONG).show();
        }

        String filename = "myfile.txt";
        String content = "4,secretaria \n 5,practicante \n 6,estudiante";
        InternalStorageManager.createAndWriteFile(getApplicationContext(), filename, content);


        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
