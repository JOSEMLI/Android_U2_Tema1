package com.example.android_u2_tema1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private RecyclerView.LayoutManager layoutManager;
  private MiNuevoAdaptador adaptador;
  private ArrayList<Cliente> misdatos;
  public Vector<String> valor;
  private String res;
  HttpURLConnection conexion;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recycler_view);
    misdatos = new ArrayList<>();
    misdatos.add(new Cliente("1", "Juanito", "Perez"));
    misdatos.add(new Cliente("2", "Pablito", "Canto"));

    //cambiamos esta linea que solo leia este array lkist por otra liena  que lee datos del internet
   // adaptador = new MiNuevoAdaptador(this, misdatos);

    //cambiamos por esta linea y se agrego el conseguirstring() en la lista cliente
    adaptador = new MiNuevoAdaptador(this,
      ListaClientes(conseguirstring()));

    recyclerView.setAdapter(adaptador);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    new Peticion().execute();
  }

  //Leyendo JSON
  private ArrayList<Cliente> ListaClientes(String string) {
    ArrayList<Cliente> Clientes = new ArrayList<>();
    try {
      //convierte de json array a lista
      JSONArray json_array = new JSONArray(string);
      for (int i = 0; i < json_array.length(); i++) {
        JSONObject objeto = json_array.getJSONObject(i);
        Clientes.add(new Cliente(objeto.getString("Cod_persona"), objeto.getString("Nombre"),objeto.getString("Apellidos")));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return Clientes;
  }
  public String conseguirstring() {
    try {
      String miurl= this.getString(R.string.dominio)+this.getString(R.string.vercliente);
      URL url=new URL(miurl);
      conexion = (HttpURLConnection) url.openConnection();
      if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea = reader.readLine();
        res=linea;
      } else {
        Log.e("mierror", conexion.getResponseMessage());
      }
    } catch (Exception e) {
      return res="";
    } finally {
      if (conexion!=null) conexion.disconnect();
    }
    return res;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_insertar, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_insertar:
        startActivity(new Intent(this, InsertarCliente.class));
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  // se modifico pa la actualizacion de datos
  @Override
  protected void onResume() {
    super.onResume();
    adaptador.update(ListaClientes(conseguirstring()));
  }


  public static class Peticion extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... voids) {

      final String url = "https://fugacious-bits.000webhostapp.com/";

      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(url)
              .addConverterFactory(GsonConverterFactory.create())

              .build();

      ServiceRetrofit service = retrofit.create(ServiceRetrofit.class);
      Call<List<Cliente>> response = service.getUsersGet();
      try {
        for (Cliente user : response.execute().body())
          Log.e("Respuesta: ",user.getNombre()+ " "+user.getApellido()+" "+user.getSexo()+" "+user.getCelular());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
  }




}
