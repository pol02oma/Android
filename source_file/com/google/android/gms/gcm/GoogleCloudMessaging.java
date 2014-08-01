package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GoogleCloudMessaging
{
  public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
  public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
  static GoogleCloudMessaging Kq;
  public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
  public static final String MESSAGE_TYPE_MESSAGE = "gcm";
  public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
  private PendingIntent Kr;
  final BlockingQueue<Intent> Ks = new LinkedBlockingQueue();
  private Handler Kt = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramMessage)
    {
      Intent localIntent = (Intent)paramMessage.obj;
      GoogleCloudMessaging.this.Ks.add(localIntent);
    }
  };
  private Messenger Ku = new Messenger(this.Kt);
  private Context kL;

  private void a(String paramString1, String paramString2, long paramLong, int paramInt, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper())
      throw new IOException("MAIN_THREAD");
    if (paramString1 == null)
      throw new IllegalArgumentException("Missing 'to'");
    Intent localIntent = new Intent("com.google.android.gcm.intent.SEND");
    localIntent.putExtras(paramBundle);
    c(localIntent);
    localIntent.setPackage("com.google.android.gms");
    localIntent.putExtra("google.to", paramString1);
    localIntent.putExtra("google.message_id", paramString2);
    localIntent.putExtra("google.ttl", Long.toString(paramLong));
    localIntent.putExtra("google.delay", Integer.toString(paramInt));
    this.kL.sendOrderedBroadcast(localIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
  }

  private void c(String[] paramArrayOfString)
  {
    String str = d(paramArrayOfString);
    Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent.setPackage("com.google.android.gms");
    localIntent.putExtra("google.messenger", this.Ku);
    c(localIntent);
    localIntent.putExtra("sender", str);
    this.kL.startService(localIntent);
  }

  public static GoogleCloudMessaging getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (Kq == null)
      {
        Kq = new GoogleCloudMessaging();
        Kq.kL = paramContext;
      }
      GoogleCloudMessaging localGoogleCloudMessaging = Kq;
      return localGoogleCloudMessaging;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void gg()
  {
    Intent localIntent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
    localIntent.setPackage("com.google.android.gms");
    this.Ks.clear();
    localIntent.putExtra("google.messenger", this.Ku);
    c(localIntent);
    this.kL.startService(localIntent);
  }

  void c(Intent paramIntent)
  {
    monitorenter;
    try
    {
      if (this.Kr == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        this.Kr = PendingIntent.getBroadcast(this.kL, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", this.Kr);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void close()
  {
    gh();
  }

  String d(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      throw new IllegalArgumentException("No senderIds");
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfString[0]);
    for (int i = 1; i < paramArrayOfString.length; i++)
      localStringBuilder.append(',').append(paramArrayOfString[i]);
    return localStringBuilder.toString();
  }

  public String getMessageType(Intent paramIntent)
  {
    String str;
    if (!"com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction()))
      str = null;
    do
    {
      return str;
      str = paramIntent.getStringExtra("message_type");
    }
    while (str != null);
    return "gcm";
  }

  void gh()
  {
    monitorenter;
    try
    {
      if (this.Kr != null)
      {
        this.Kr.cancel();
        this.Kr = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String register(String[] paramArrayOfString)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper())
      throw new IOException("MAIN_THREAD");
    this.Ks.clear();
    c(paramArrayOfString);
    Intent localIntent;
    try
    {
      localIntent = (Intent)this.Ks.poll(5000L, TimeUnit.MILLISECONDS);
      if (localIntent == null)
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new IOException(localInterruptedException.getMessage());
    }
    String str1 = localIntent.getStringExtra("registration_id");
    if (str1 != null)
      return str1;
    localIntent.getStringExtra("error");
    String str2 = localIntent.getStringExtra("error");
    if (str2 != null)
      throw new IOException(str2);
    throw new IOException("SERVICE_NOT_AVAILABLE");
  }

  public void send(String paramString1, String paramString2, long paramLong, Bundle paramBundle)
    throws IOException
  {
    a(paramString1, paramString2, paramLong, -1, paramBundle);
  }

  public void send(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    send(paramString1, paramString2, -1L, paramBundle);
  }

  public void unregister()
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper())
      throw new IOException("MAIN_THREAD");
    gg();
    Intent localIntent;
    try
    {
      localIntent = (Intent)this.Ks.poll(5000L, TimeUnit.MILLISECONDS);
      if (localIntent == null)
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new IOException(localInterruptedException.getMessage());
    }
    if (localIntent.getStringExtra("unregistered") != null)
      return;
    String str = localIntent.getStringExtra("error");
    if (str != null)
      throw new IOException(str);
    throw new IOException("SERVICE_NOT_AVAILABLE");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.gcm.GoogleCloudMessaging
 * JD-Core Version:    0.6.0
 */