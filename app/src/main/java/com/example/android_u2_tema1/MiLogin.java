package com.example.android_u2_tema1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android_u2_tema1.Model.LoginInteractorImpl;
import com.example.android_u2_tema1.Presentador.LoginPresenter;
import com.example.android_u2_tema1.Presentador.LoginPresenterImpl;
import com.example.android_u2_tema1.Vista.LoginView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MiLogin extends AppCompatActivity  implements LoginView {

  private EditText Login;
  private EditText password;
  ProgressBar progresbar ;
  String usuario;
  String pass;
  //se agrego 9
  LoginPresenter loginPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    loginPresenter = new LoginPresenterImpl(this,new LoginInteractorImpl());
    loginPresenter.revisar();
  }

  public void onLogin(View view) {
//cuando pulso el boton
    usuario = Login.getText().toString();
    pass = password.getText().toString();
    loginPresenter.validateCredentials(usuario,pass);
  }

  @Override
  public void showProgress() {

    progresbar.setVisibility(View.VISIBLE);
  }

  @Override
  public void HideProgress() {
    progresbar.setVisibility(View.GONE);
  }

  @Override
  public void erropassError() {
    Toast.makeText(MiLogin.this, "Ingreso Fallido", Toast.LENGTH_SHORT).show();
  }
  @Override
  public void navigateToHome() {

    Intent i = new Intent(MiLogin.this, MainActivity.class);
    startActivity(i);
    finish();
  }

  @Override
  public Context context() {
    return this;
  }

  @Override
  public void loginon() {
      startActivity(new Intent(this, MainActivity.class));
      finish();
  }

  @Override
  public void loginout() {
    setContentView(R.layout.activity_mi_login);
    Login = (EditText) findViewById(R.id.edtUsuario);
    password = (EditText) findViewById(R.id.edtPassword);
    progresbar = findViewById(R.id.progresslogin);
  }


}
