package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.internal.ee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Api
{
  private final b<?> za;
  private final ArrayList<Scope> zb;

  public Api(b<?> paramb, Scope[] paramArrayOfScope)
  {
    this.za = paramb;
    this.zb = new ArrayList(Arrays.asList(paramArrayOfScope));
  }

  public b<?> dp()
  {
    return this.za;
  }

  public List<Scope> dq()
  {
    return this.zb;
  }

  public static abstract interface a
  {
    public abstract void connect();

    public abstract void disconnect();

    public abstract Looper getLooper();

    public abstract boolean isConnected();
  }

  public static abstract interface b<T extends Api.a>
  {
    public abstract T b(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener);

    public abstract int getPriority();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.api.Api
 * JD-Core Version:    0.6.0
 */