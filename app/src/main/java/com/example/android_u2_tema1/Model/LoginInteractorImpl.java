package com.example.android_u2_tema1.Model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android_u2_tema1.MainActivity;
import com.example.android_u2_tema1.MiLogin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginInteractorImpl implements LoginInteractor{

  HttpURLConnection conexion;
  private SharedPreferences prefs;
  private SharedPreferences.Editor editor;
  OnLoginFinishedListener listener;
  String usuario;
  String pass;

  @Override
  public void login(final String usuario, final String pass, final OnLoginFinishedListener listener) {

    this.listener = listener;
    this.usuario = usuario;
    this.pass = pass;

    MiTarea tarea = new MiTarea();
    tarea.execute();
  }

    class MiTarea extends AsyncTask<Integer, Integer, Integer> {


      @Override protected Integer doInBackground(Integer... n) {
        //ejecucion en segundo plano
        //es la conecion

        int result = 3;
        try {
          //para saber que datos estamos enviando
          Log.i("miruta","https://bamboo-amplitude.000webhostapp.com/UPT"+"/login.php?Usuario=" + usuario + "&pass=" + pass);
          //solo fue un agregado
          URL url = new URL("https://bamboo-amplitude.000webhostapp.com/UPT"+ "/login.php?Usuario=" + usuario + "&pass=" + pass);
          conexion = (HttpURLConnection) url
              .openConnection();
          if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea = reader.readLine();
            if (linea.equals("success"))
              result = 0;
            else
              result = 1;
          } else {
            Log.e("mifallo", conexion.getResponseMessage());
          }
        } catch (Exception e) {
          Log.e("mifallo", e.getMessage());
        } finally {
          if (conexion != null) conexion.disconnect();
          return result;
        }

      }

      @Override protected void onPostExecute(Integer V) {
        //si es fallido hace esto
        if (V == 0) {
          listener.onSuccess();
        } else{listener.onPasswordError();}

      }
    }





}
