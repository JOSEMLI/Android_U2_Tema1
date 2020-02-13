package com.example.android_u2_tema1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceRetrofit {
    @GET("clientes.php")
    Call<List<Cliente>> getUsersGet();
}
