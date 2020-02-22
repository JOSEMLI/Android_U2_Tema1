package com.example.android_u2_tema1.Presentador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android_u2_tema1.MainActivity;
import com.example.android_u2_tema1.Model.LoginInteractor;
import com.example.android_u2_tema1.Vista.LoginView;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenterImpl implements LoginPresenter , LoginInteractor.OnLoginFinishedListener{

  private boolean login;
  private LoginView loginView;
  private LoginInteractor loginInteractor;
  Context ctx;


  private SharedPreferences.Editor editor;

  private SharedPreferences prefs;

  public LoginPresenterImpl(LoginView loginView,LoginInteractor loginInteractor)
  {
    this.loginView = loginView;
    this.loginInteractor = loginInteractor;
      ctx = loginView.context();
    //es una forma de guardar informacion en el archivo config
    prefs = ctx.getSharedPreferences("Config", MODE_PRIVATE);
  }


  @Override
  public void validateCredentials(String username, String password) {

    loginView.showProgress();
    loginInteractor.login(username,password,this);

  }

  @Override
  public void revisar() {

    login = prefs.getBoolean("onlogin", false);
    if (login) {
      loginView.loginon();
    }
    loginView.loginout();
  }

  @Override
  public void onUsernameError() {

    loginView.erropassError();
    loginView.HideProgress();
  }

  @Override
  public void onPasswordError() {

    loginView.erropassError();
    loginView.HideProgress();
  }

  @Override
  public void onSuccess() {

    loginView.HideProgress();
    editor = prefs.edit();
    editor.putBoolean("onlogin", true);
    editor.apply();
    loginView.navigateToHome();
  }
}
