package com.appbuilder.sdk.android;

import java.io.Serializable;

public abstract interface AppBuilderInterface
{
  public abstract void create();

  public abstract void destroy();

  public abstract Serializable getSession();

  public abstract void pause();

  public abstract void restart();

  public abstract void resume();

  public abstract void setSession(Serializable paramSerializable);

  public abstract void start();

  public abstract void stop();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.AppBuilderInterface
 * JD-Core Version:    0.6.0
 */