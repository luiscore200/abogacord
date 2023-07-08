package com.foreroinc.abogacord.ui;
import android.os.AsyncTask;

import com.foreroinc.abogacord.recycler.ReporteCaso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import android.os.AsyncTask;

import com.foreroinc.abogacord.recycler.ReporteCaso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceManager {
    private static final String BASE_URL = "https://www.datos.gov.co/resource/";
 //"http://192.168.1.83/registro/"
    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }

    public void getReportesCasos(ApiCallback<List<ReporteCaso>> callback) {
        new AsyncTask<Void, Void, List<ReporteCaso>>() {
            @Override
            protected List<ReporteCaso> doInBackground(Void... voids) {
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);

                try {
                    retrofit2.Response<List<ReporteCaso>> response = apiService.getReportesCasos().execute();
                    if (response.isSuccessful()) {
                        return response.body();
                    } else {
                        throw new IOException("Error en la respuesta de la API");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<ReporteCaso> reportesCasos) {
                if (reportesCasos != null) {
                    callback.onSuccess(reportesCasos);
                } else {
                    callback.onFailure("Error al obtener los reportes de casos");
                }
            }
        }.execute();
    }
}

