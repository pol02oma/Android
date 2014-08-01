package com.google.android.gms.internal;

import android.content.Context;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class cy extends ct
{
  private final Context mContext;
  private final String pR;
  private final String pS;

  public cy(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext;
    this.pR = paramString1;
    this.pS = paramString2;
  }

  public void aB()
  {
    try
    {
      da.v("Pinging URL: " + this.pS);
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(this.pS).openConnection();
      try
      {
        cv.a(this.mContext, this.pR, true, localHttpURLConnection);
        int i = localHttpURLConnection.getResponseCode();
        if ((i < 200) || (i >= 300))
          da.w("Received non-success response code " + i + " from pinging URL: " + this.pS);
        return;
      }
      finally
      {
        localHttpURLConnection.disconnect();
      }
    }
    catch (IOException localIOException)
    {
      da.w("Error while pinging URL: " + this.pS + ". " + localIOException.getMessage());
    }
  }

  public void onStop()
  {
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cy
 * JD-Core Version:    0.6.0
 */