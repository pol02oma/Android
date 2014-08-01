package com.google.android.gms.internal;

import android.os.Bundle;

public class cr
{
  private final Object mg = new Object();
  private int pF;
  private int pG;
  private final String pl;

  public cr(String paramString)
  {
    this.pl = paramString;
  }

  public void a(int paramInt1, int paramInt2)
  {
    synchronized (this.mg)
    {
      this.pF = paramInt1;
      this.pG = paramInt2;
      cp.a(this.pl, this);
      return;
    }
  }

  public Bundle toBundle()
  {
    synchronized (this.mg)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("pmnli", this.pF);
      localBundle.putInt("pmnll", this.pG);
      return localBundle;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cr
 * JD-Core Version:    0.6.0
 */