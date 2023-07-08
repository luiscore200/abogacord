package com.foreroinc.abogacord;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.foreroinc.abogacord.db.DbUsuarios;

public class LoginActivity extends AppCompatActivity {

    public Button loginButton;
    public EditText textUser, textPass;
    public ProgressBar progressBar1;

    DbUsuarios user ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        textUser = (EditText) findViewById(R.id.textUser);
        textPass = (EditText) findViewById(R.id.textPass);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                user = new DbUsuarios(LoginActivity.this);
                if (user.loginUsuario(textUser.getText().toString(),textPass.getText().toString())) {
                    new TaskLogin().execute(textUser.getText().toString());
                    Toast.makeText(LoginActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "¡Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class TaskLogin extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            progressBar1.setVisibility(View.VISIBLE);
            loginButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            progressBar1.setVisibility(View.INVISIBLE);
            loginButton.setEnabled(true);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("user",textUser.getText().toString());
            startActivities(new Intent[]{intent});



        }
    }
}
