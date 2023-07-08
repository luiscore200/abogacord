package com.foreroinc.abogacord.ui;

import com.foreroinc.abogacord.recycler.ReporteCaso;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    // Métodos para las llamadas a la API
    @GET("pjyv-r529.json")
    Call<List<ReporteCaso>> getReportesCasos();

    @POST("guardar_json.php")
    Call<Void> guardarArchivoJson(@Body RequestBody requestBody);

}
