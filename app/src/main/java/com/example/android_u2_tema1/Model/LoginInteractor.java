package com.example.android_u2_tema1.Model;

public interface LoginInteractor {
  interface OnLoginFinishedListener {
    void onUsernameError();
    void onPasswordError();
    void onSuccess();
  }
  void login(String username, String password, OnLoginFinishedListener listener);
}
