package com.google.android.gms.analytics;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

class h
  implements m
{
  private static final Object qI = new Object();
  private static h qW;
  private final Context mContext;
  private String qX;
  private boolean qY = false;
  private final Object qZ = new Object();

  protected h(Context paramContext)
  {
    this.mContext = paramContext;
    bx();
  }

  public static h bu()
  {
    synchronized (qI)
    {
      h localh = qW;
      return localh;
    }
  }

  private String bv()
  {
    if (!this.qY);
    synchronized (this.qZ)
    {
      if (!this.qY)
        aa.v("Waiting for clientId to load");
      try
      {
        do
          this.qZ.wait();
        while (!this.qY);
        aa.v("Loaded clientId");
        return this.qX;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          aa.t("Exception while waiting for clientId: " + localInterruptedException);
      }
    }
  }

  private void bx()
  {
    new Thread("client_id_fetcher")
    {
      public void run()
      {
        synchronized (h.a(h.this))
        {
          h.a(h.this, h.this.by());
          h.a(h.this, true);
          h.a(h.this).notifyAll();
          return;
        }
      }
    }
    .start();
  }

  public static void n(Context paramContext)
  {
    synchronized (qI)
    {
      if (qW == null)
        qW = new h(paramContext);
      return;
    }
  }

  private boolean y(String paramString)
  {
    try
    {
      aa.v("Storing clientId.");
      FileOutputStream localFileOutputStream = this.mContext.openFileOutput("gaClientId", 0);
      localFileOutputStream.write(paramString.getBytes());
      localFileOutputStream.close();
      return true;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      aa.t("Error creating clientId file.");
      return false;
    }
    catch (IOException localIOException)
    {
      aa.t("Error writing to clientId file.");
    }
    return false;
  }

  protected String bw()
  {
    String str = UUID.randomUUID().toString().toLowerCase();
    try
    {
      if (!y(str))
        str = "0";
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  String by()
  {
    Object localObject = null;
    try
    {
      localFileInputStream = this.mContext.openFileInput("gaClientId");
      byte[] arrayOfByte = new byte[''];
      int i = localFileInputStream.read(arrayOfByte, 0, 128);
      if (localFileInputStream.available() > 0)
      {
        aa.t("clientId file seems corrupted, deleting it.");
        localFileInputStream.close();
        this.mContext.deleteFile("gaClientId");
      }
      while (true)
      {
        if (localObject == null)
          localObject = bw();
        return localObject;
        if (i > 0)
          break;
        aa.t("clientId file seems empty, deleting it.");
        localFileInputStream.close();
        this.mContext.deleteFile("gaClientId");
        localObject = null;
      }
      str = new String(arrayOfByte, 0, i);
    }
    catch (IOException localIOException1)
    {
      while (true)
        try
        {
          FileInputStream localFileInputStream;
          localFileInputStream.close();
          localObject = str;
          continue;
          localIOException1 = localIOException1;
          aa.t("Error reading clientId file, deleting it.");
          this.mContext.deleteFile("gaClientId");
        }
        catch (IOException localIOException2)
        {
          localObject = str;
          continue;
        }
        catch (FileNotFoundException localFileNotFoundException2)
        {
          String str;
          localObject = str;
        }
    }
    catch (FileNotFoundException localFileNotFoundException1)
    {
      while (true)
        localObject = null;
    }
  }

  public String getValue(String paramString)
  {
    if ("&cid".equals(paramString))
      return bv();
    return null;
  }

  public boolean x(String paramString)
  {
    return "&cid".equals(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.analytics.h
 * JD-Core Version:    0.6.0
 */