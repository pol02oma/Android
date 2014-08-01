package com.appbuilder.u846253p1176378;

import android.app.Application;
import android.util.Log;

public class App extends Application
{
  public void onLowMemory()
  {
    super.onLowMemory();
    Log.d("XXX", "onLowMemory");
  }

  public void onTerminate()
  {
    Log.d("XXX", "Terminate");
    super.onTerminate();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.App
 * JD-Core Version:    0.6.0
 */