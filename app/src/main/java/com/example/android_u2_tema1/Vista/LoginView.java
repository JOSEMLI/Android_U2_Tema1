package com.example.android_u2_tema1.Vista;

import android.content.Context;

public interface LoginView {
  void showProgress();
  void HideProgress();
  void erropassError();;
  void navigateToHome();

  Context context();

  void loginon();
  void loginout();

}
