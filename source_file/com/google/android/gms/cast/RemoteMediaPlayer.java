package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.dq;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.dw;
import com.google.android.gms.internal.dx;
import java.io.IOException;
import org.json.JSONObject;

public class RemoteMediaPlayer
  implements Cast.MessageReceivedCallback
{
  public static final int RESUME_STATE_PAUSE = 2;
  public static final int RESUME_STATE_PLAY = 1;
  public static final int RESUME_STATE_UNCHANGED = 0;
  public static final int STATUS_CANCELED = 2;
  public static final int STATUS_FAILED = 1;
  public static final int STATUS_REPLACED = 4;
  public static final int STATUS_SUCCEEDED = 0;
  public static final int STATUS_TIMED_OUT = 3;
  private final Object mg = new Object();
  private final dv xg = new dv()
  {
    protected void onMetadataUpdated()
    {
      RemoteMediaPlayer.b(RemoteMediaPlayer.this);
    }

    protected void onStatusUpdated()
    {
      RemoteMediaPlayer.a(RemoteMediaPlayer.this);
    }
  };
  private final a xh = new a();
  private OnMetadataUpdatedListener xi;
  private OnStatusUpdatedListener xj;

  public RemoteMediaPlayer()
  {
    this.xg.a(this.xh);
  }

  private void onMetadataUpdated()
  {
    if (this.xi != null)
      this.xi.onMetadataUpdated();
  }

  private void onStatusUpdated()
  {
    if (this.xj != null)
      this.xj.onStatusUpdated();
  }

  public long getApproximateStreamPosition()
  {
    synchronized (this.mg)
    {
      long l = this.xg.getApproximateStreamPosition();
      return l;
    }
  }

  public MediaInfo getMediaInfo()
  {
    synchronized (this.mg)
    {
      MediaInfo localMediaInfo = this.xg.getMediaInfo();
      return localMediaInfo;
    }
  }

  public MediaStatus getMediaStatus()
  {
    synchronized (this.mg)
    {
      MediaStatus localMediaStatus = this.xg.getMediaStatus();
      return localMediaStatus;
    }
  }

  public String getNamespace()
  {
    return this.xg.getNamespace();
  }

  public long getStreamDuration()
  {
    synchronized (this.mg)
    {
      long l = this.xg.getStreamDuration();
      return l;
    }
  }

  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo)
  {
    return load(paramGoogleApiClient, paramMediaInfo, true, 0L, null);
  }

  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, 0L, null);
  }

  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong)
  {
    return load(paramGoogleApiClient, paramMediaInfo, paramBoolean, paramLong, null);
  }

  public PendingResult<MediaChannelResult> load(GoogleApiClient paramGoogleApiClient, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, JSONObject paramJSONObject)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramMediaInfo, paramBoolean, paramLong, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xy, this.xm, this.xn, this.xo, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public void onMessageReceived(CastDevice paramCastDevice, String paramString1, String paramString2)
  {
    this.xg.P(paramString2);
  }

  public PendingResult<MediaChannelResult> pause(GoogleApiClient paramGoogleApiClient)
  {
    return pause(paramGoogleApiClient, null);
  }

  public PendingResult<MediaChannelResult> pause(GoogleApiClient paramGoogleApiClient, JSONObject paramJSONObject)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xy, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public PendingResult<MediaChannelResult> play(GoogleApiClient paramGoogleApiClient)
  {
    return play(paramGoogleApiClient, null);
  }

  public PendingResult<MediaChannelResult> play(GoogleApiClient paramGoogleApiClient, JSONObject paramJSONObject)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).c(this.xy, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public PendingResult<MediaChannelResult> requestStatus(GoogleApiClient paramGoogleApiClient)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xy);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong)
  {
    return seek(paramGoogleApiClient, paramLong, 0, null);
  }

  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong, int paramInt)
  {
    return seek(paramGoogleApiClient, paramLong, paramInt, null);
  }

  public PendingResult<MediaChannelResult> seek(GoogleApiClient paramGoogleApiClient, long paramLong, int paramInt, JSONObject paramJSONObject)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramLong, paramInt, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xy, this.xq, this.xr, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener paramOnMetadataUpdatedListener)
  {
    this.xi = paramOnMetadataUpdatedListener;
  }

  public void setOnStatusUpdatedListener(OnStatusUpdatedListener paramOnStatusUpdatedListener)
  {
    this.xj = paramOnStatusUpdatedListener;
  }

  public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    return setStreamMute(paramGoogleApiClient, paramBoolean, null);
  }

  public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient paramGoogleApiClient, boolean paramBoolean, JSONObject paramJSONObject)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramBoolean, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xy, this.xt, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient paramGoogleApiClient, double paramDouble)
    throws IllegalArgumentException
  {
    return setStreamVolume(paramGoogleApiClient, paramDouble, null);
  }

  public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient paramGoogleApiClient, double paramDouble, JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble)))
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramDouble, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xy, this.xs, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public PendingResult<MediaChannelResult> stop(GoogleApiClient paramGoogleApiClient)
  {
    return stop(paramGoogleApiClient, null);
  }

  public PendingResult<MediaChannelResult> stop(GoogleApiClient paramGoogleApiClient, JSONObject paramJSONObject)
  {
    return paramGoogleApiClient.b(new b(paramGoogleApiClient, paramJSONObject)
    {
      protected void a(dq paramdq)
      {
        synchronized (RemoteMediaPlayer.c(RemoteMediaPlayer.this))
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(this.xl);
        }
        try
        {
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).b(this.xy, this.xp);
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          monitorexit;
          return;
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            a(j(new Status(1)));
            RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
          }
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        finally
        {
          RemoteMediaPlayer.d(RemoteMediaPlayer.this).b(null);
        }
        throw localObject3;
      }
    });
  }

  public static abstract interface MediaChannelResult extends Result
  {
  }

  public static abstract interface OnMetadataUpdatedListener
  {
    public abstract void onMetadataUpdated();
  }

  public static abstract interface OnStatusUpdatedListener
  {
    public abstract void onStatusUpdated();
  }

  private class a
    implements dw
  {
    private GoogleApiClient xu;
    private long xv = 0L;

    public a()
    {
    }

    public void a(String paramString1, String paramString2, long paramLong, String paramString3)
      throws IOException
    {
      if (this.xu == null)
        throw new IOException("No GoogleApiClient available");
      Cast.CastApi.sendMessage(this.xu, paramString1, paramString2).setResultCallback(new a(paramLong));
    }

    public void b(GoogleApiClient paramGoogleApiClient)
    {
      this.xu = paramGoogleApiClient;
    }

    public long cV()
    {
      long l = 1L + this.xv;
      this.xv = l;
      return l;
    }

    private final class a
      implements ResultCallback<Status>
    {
      private final long xw;

      a(long arg2)
      {
        Object localObject;
        this.xw = localObject;
      }

      public void i(Status paramStatus)
      {
        if (!paramStatus.isSuccess())
          RemoteMediaPlayer.e(RemoteMediaPlayer.this).a(this.xw, paramStatus.getStatusCode());
      }
    }
  }

  private static abstract class b extends Cast.a<RemoteMediaPlayer.MediaChannelResult>
  {
    dx xy = new dx()
    {
      public void a(long paramLong, int paramInt, JSONObject paramJSONObject)
      {
        RemoteMediaPlayer.b.this.a(new RemoteMediaPlayer.c(new Status(paramInt), paramJSONObject));
      }

      public void k(long paramLong)
      {
        RemoteMediaPlayer.b.this.a(RemoteMediaPlayer.b.this.j(new Status(4)));
      }
    };

    public RemoteMediaPlayer.MediaChannelResult j(Status paramStatus)
    {
      return new RemoteMediaPlayer.MediaChannelResult(paramStatus)
      {
        public Status getStatus()
        {
          return this.vb;
        }
      };
    }
  }

  private static final class c
    implements RemoteMediaPlayer.MediaChannelResult
  {
    private final Status vl;
    private final JSONObject wP;

    c(Status paramStatus, JSONObject paramJSONObject)
    {
      this.vl = paramStatus;
      this.wP = paramJSONObject;
    }

    public Status getStatus()
    {
      return this.vl;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.RemoteMediaPlayer
 * JD-Core Version:    0.6.0
 */