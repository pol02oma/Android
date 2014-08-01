package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.a;
import com.google.android.gms.internal.dq;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.er;
import java.io.IOException;

public final class Cast
{
  public static final Api API;
  public static final CastApi CastApi;
  public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
  public static final int MAX_MESSAGE_LENGTH = 65536;
  public static final int MAX_NAMESPACE_LENGTH = 128;
  static final Api.b<dq> va = new Api.b()
  {
    public dq c(Context paramContext, Looper paramLooper, ee paramee, GoogleApiClient.ApiOptions paramApiOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      er.b(paramApiOptions, "Setting the API options is required.");
      er.b(paramApiOptions instanceof Cast.CastOptions, "Must provide valid CastOptions!");
      Cast.CastOptions localCastOptions = (Cast.CastOptions)paramApiOptions;
      return new dq(paramContext, paramLooper, localCastOptions.wv, Cast.CastOptions.a(localCastOptions), localCastOptions.ww, paramConnectionCallbacks, paramOnConnectionFailedListener);
    }

    public int getPriority()
    {
      return 2147483647;
    }
  };

  static
  {
    API = new Api(va, new Scope[0]);
    CastApi = new Cast.CastApi.a();
  }

  public static abstract interface ApplicationConnectionResult extends Result
  {
    public abstract ApplicationMetadata getApplicationMetadata();

    public abstract String getApplicationStatus();

    public abstract String getSessionId();

    public abstract boolean getWasLaunched();
  }

  public static abstract interface CastApi
  {
    public abstract ApplicationMetadata getApplicationMetadata(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;

    public abstract String getApplicationStatus(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;

    public abstract double getVolume(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;

    public abstract boolean isMute(GoogleApiClient paramGoogleApiClient)
      throws IllegalStateException;

    public abstract PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient);

    public abstract PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString);

    public abstract PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);

    public abstract PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString);

    public abstract PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean);

    public abstract PendingResult<Status> leaveApplication(GoogleApiClient paramGoogleApiClient);

    public abstract void removeMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString)
      throws IOException, IllegalArgumentException;

    public abstract void requestStatus(GoogleApiClient paramGoogleApiClient)
      throws IOException, IllegalStateException;

    public abstract PendingResult<Status> sendMessage(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);

    public abstract void setMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString, Cast.MessageReceivedCallback paramMessageReceivedCallback)
      throws IOException, IllegalStateException;

    public abstract void setMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
      throws IOException, IllegalStateException;

    public abstract void setVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
      throws IOException, IllegalArgumentException, IllegalStateException;

    public abstract PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient);

    public abstract PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient, String paramString);

    public static final class a
      implements Cast.CastApi
    {
      public ApplicationMetadata getApplicationMetadata(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((dq)paramGoogleApiClient.a(Cast.va)).getApplicationMetadata();
      }

      public String getApplicationStatus(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((dq)paramGoogleApiClient.a(Cast.va)).getApplicationStatus();
      }

      public double getVolume(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((dq)paramGoogleApiClient.a(Cast.va)).da();
      }

      public boolean isMute(GoogleApiClient paramGoogleApiClient)
        throws IllegalStateException
      {
        return ((dq)paramGoogleApiClient.a(Cast.va)).isMute();
      }

      public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient)
      {
        return paramGoogleApiClient.b(new Cast.c()
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.b(null, null, this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString)
      {
        return paramGoogleApiClient.b(new Cast.c(paramString)
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.b(this.ws, null, this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public PendingResult<Cast.ApplicationConnectionResult> joinApplication(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2)
      {
        return paramGoogleApiClient.b(new Cast.c(paramString1, paramString2)
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.b(this.ws, this.wu, this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString)
      {
        return paramGoogleApiClient.b(new Cast.c(paramString)
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.a(this.ws, false, this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public PendingResult<Cast.ApplicationConnectionResult> launchApplication(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean)
      {
        return paramGoogleApiClient.b(new Cast.c(paramString, paramBoolean)
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.a(this.ws, this.wt, this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public PendingResult<Status> leaveApplication(GoogleApiClient paramGoogleApiClient)
      {
        return paramGoogleApiClient.b(new Cast.b()
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.e(this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public void removeMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString)
        throws IOException, IllegalArgumentException
      {
        try
        {
          ((dq)paramGoogleApiClient.a(Cast.va)).Q(paramString);
          return;
        }
        catch (RemoteException localRemoteException)
        {
        }
        throw new IOException("service error");
      }

      public void requestStatus(GoogleApiClient paramGoogleApiClient)
        throws IOException, IllegalStateException
      {
        try
        {
          ((dq)paramGoogleApiClient.a(Cast.va)).cZ();
          return;
        }
        catch (RemoteException localRemoteException)
        {
        }
        throw new IOException("service error");
      }

      public PendingResult<Status> sendMessage(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2)
      {
        return paramGoogleApiClient.b(new Cast.b(paramString1, paramString2)
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.a(this.wp, this.wq, this);
              return;
            }
            catch (IllegalArgumentException localIllegalArgumentException)
            {
              x(2001);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public void setMessageReceivedCallbacks(GoogleApiClient paramGoogleApiClient, String paramString, Cast.MessageReceivedCallback paramMessageReceivedCallback)
        throws IOException, IllegalStateException
      {
        try
        {
          ((dq)paramGoogleApiClient.a(Cast.va)).a(paramString, paramMessageReceivedCallback);
          return;
        }
        catch (RemoteException localRemoteException)
        {
        }
        throw new IOException("service error");
      }

      public void setMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
        throws IOException, IllegalStateException
      {
        try
        {
          ((dq)paramGoogleApiClient.a(Cast.va)).t(paramBoolean);
          return;
        }
        catch (RemoteException localRemoteException)
        {
        }
        throw new IOException("service error");
      }

      public void setVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
        throws IOException, IllegalArgumentException, IllegalStateException
      {
        try
        {
          ((dq)paramGoogleApiClient.a(Cast.va)).a(paramDouble);
          return;
        }
        catch (RemoteException localRemoteException)
        {
        }
        throw new IOException("service error");
      }

      public PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient)
      {
        return paramGoogleApiClient.b(new Cast.b()
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            try
            {
              paramdq.a("", this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }

      public PendingResult<Status> stopApplication(GoogleApiClient paramGoogleApiClient, String paramString)
      {
        return paramGoogleApiClient.b(new Cast.b(paramString)
        {
          protected void a(dq paramdq)
            throws RemoteException
          {
            if (TextUtils.isEmpty(this.wu))
            {
              c(2001, "IllegalArgument: sessionId cannot be null or empty");
              return;
            }
            try
            {
              paramdq.a(this.wu, this);
              return;
            }
            catch (IllegalStateException localIllegalStateException)
            {
              x(2001);
            }
          }
        });
      }
    }
  }

  public static final class CastOptions
    implements GoogleApiClient.ApiOptions
  {
    final CastDevice wv;
    final Cast.Listener ww;
    private final int wx;

    private CastOptions(Builder paramBuilder)
    {
      this.wv = paramBuilder.wy;
      this.ww = paramBuilder.wz;
      this.wx = Builder.a(paramBuilder);
    }

    public static Builder builder(CastDevice paramCastDevice, Cast.Listener paramListener)
    {
      return new Builder(paramCastDevice, paramListener, null);
    }

    public static final class Builder
    {
      private int wA;
      CastDevice wy;
      Cast.Listener wz;

      private Builder(CastDevice paramCastDevice, Cast.Listener paramListener)
      {
        er.b(paramCastDevice, "CastDevice parameter cannot be null");
        er.b(paramListener, "CastListener parameter cannot be null");
        this.wy = paramCastDevice;
        this.wz = paramListener;
        this.wA = 0;
      }

      public Cast.CastOptions build()
      {
        return new Cast.CastOptions(this, null);
      }

      public Builder setVerboseLoggingEnabled(boolean paramBoolean)
      {
        if (paramBoolean)
        {
          this.wA = (0x1 | this.wA);
          return this;
        }
        this.wA = (0xFFFFFFFE & this.wA);
        return this;
      }
    }
  }

  public static abstract class Listener
  {
    public void onApplicationDisconnected(int paramInt)
    {
    }

    public void onApplicationStatusChanged()
    {
    }

    public void onVolumeChanged()
    {
    }
  }

  public static abstract interface MessageReceivedCallback
  {
    public abstract void onMessageReceived(CastDevice paramCastDevice, String paramString1, String paramString2);
  }

  protected static abstract class a<R extends Result> extends a.a<R, dq>
    implements PendingResult<R>
  {
    public a()
    {
      super();
    }

    public void c(int paramInt, String paramString)
    {
      a(d(new Status(paramInt, paramString, null)));
    }

    public void x(int paramInt)
    {
      a(d(new Status(paramInt)));
    }
  }

  private static abstract class b extends Cast.a<Status>
  {
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }

  private static abstract class c extends Cast.a<Cast.ApplicationConnectionResult>
  {
    public Cast.ApplicationConnectionResult h(Status paramStatus)
    {
      return new Cast.ApplicationConnectionResult(paramStatus)
      {
        public ApplicationMetadata getApplicationMetadata()
        {
          return null;
        }

        public String getApplicationStatus()
        {
          return null;
        }

        public String getSessionId()
        {
          return null;
        }

        public Status getStatus()
        {
          return this.vb;
        }

        public boolean getWasLaunched()
        {
          return false;
        }
      };
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.Cast
 * JD-Core Version:    0.6.0
 */