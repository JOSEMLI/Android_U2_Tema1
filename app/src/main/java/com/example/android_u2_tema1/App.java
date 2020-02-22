package com.example.android_u2_tema1;

import android.app.Application;

public class App  extends Application {

  private  static  App mistance;

  @Override
  public void onCreate()
  {
    super.onCreate();
    mistance = this;
  }

  public static App getMistance() {
    return mistance;
  }

  public static void setMistance(App mistance) {
    App.mistance = mistance;
  }
}
