package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dv extends dp
{
  private static final long yi = TimeUnit.HOURS.toMillis(24L);
  private static final long yj = TimeUnit.HOURS.toMillis(24L);
  private static final long yk = TimeUnit.HOURS.toMillis(24L);
  private static final long yl = TimeUnit.SECONDS.toMillis(1L);
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private long ym;
  private MediaStatus yn;
  private final dy yo = new dy(yj);
  private final dy yp = new dy(yi);
  private final dy yq = new dy(yi);
  private final dy yr = new dy(yi);
  private final dy ys = new dy(yk);
  private final dy yt = new dy(yi);
  private final dy yu = new dy(yi);
  private final dy yv = new dy(yi);
  private final Runnable yw = new a(null);
  private boolean yx;

  public dv()
  {
    super("urn:x-cast:com.google.cast.media", "MediaControlChannel");
    dj();
  }

  private void a(long paramLong, JSONObject paramJSONObject)
    throws JSONException
  {
    int i = 1;
    boolean bool = this.yo.m(paramLong);
    int j;
    if ((this.ys.dl()) && (!this.ys.m(paramLong)))
    {
      j = i;
      if (((!this.yt.dl()) || (this.yt.m(paramLong))) && ((!this.yu.dl()) || (this.yu.m(paramLong))))
        break label268;
      label80: if (j == 0)
        break label289;
    }
    label268: label289: for (int k = 2; ; k = 0)
    {
      if (i != 0)
        k |= 1;
      if ((bool) || (this.yn == null))
      {
        this.yn = new MediaStatus(paramJSONObject);
        this.ym = SystemClock.elapsedRealtime();
      }
      for (int m = 7; ; m = this.yn.a(paramJSONObject, k))
      {
        if ((m & 0x1) != 0)
        {
          this.ym = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((m & 0x2) != 0)
        {
          this.ym = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((m & 0x4) != 0)
          onMetadataUpdated();
        this.yo.c(paramLong, 0);
        this.yp.c(paramLong, 0);
        this.yq.c(paramLong, 0);
        this.yr.c(paramLong, 0);
        this.ys.c(paramLong, 0);
        this.yt.c(paramLong, 0);
        this.yu.c(paramLong, 0);
        this.yv.c(paramLong, 0);
        return;
        j = 0;
        break;
        i = 0;
        break label80;
      }
    }
  }

  private void dj()
  {
    u(false);
    this.ym = 0L;
    this.yn = null;
    this.yo.clear();
    this.ys.clear();
    this.yt.clear();
  }

  private void u(boolean paramBoolean)
  {
    if (this.yx != paramBoolean)
    {
      this.yx = paramBoolean;
      if (paramBoolean)
        this.mHandler.postDelayed(this.yw, yl);
    }
    else
    {
      return;
    }
    this.mHandler.removeCallbacks(this.yw);
  }

  public final void P(String paramString)
  {
    this.xB.b("message received: %s", new Object[] { paramString });
    JSONObject localJSONObject1;
    String str;
    long l;
    try
    {
      localJSONObject1 = new JSONObject(paramString);
      str = localJSONObject1.getString("type");
      l = localJSONObject1.optLong("requestId", -1L);
      if (str.equals("MEDIA_STATUS"))
      {
        JSONArray localJSONArray = localJSONObject1.getJSONArray("status");
        if (localJSONArray.length() > 0)
        {
          a(l, localJSONArray.getJSONObject(0));
          return;
        }
        this.yn = null;
        onStatusUpdated();
        onMetadataUpdated();
        this.yv.c(l, 0);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      du localdu = this.xB;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localJSONException.getMessage();
      arrayOfObject[1] = paramString;
      localdu.d("Message is malformed (%s); ignoring: %s", arrayOfObject);
      return;
    }
    if (str.equals("INVALID_PLAYER_STATE"))
    {
      this.xB.d("received unexpected error: Invalid Player State.", new Object[0]);
      JSONObject localJSONObject5 = localJSONObject1.optJSONObject("customData");
      this.yo.b(l, 1, localJSONObject5);
      this.yp.b(l, 1, localJSONObject5);
      this.yq.b(l, 1, localJSONObject5);
      this.yr.b(l, 1, localJSONObject5);
      this.ys.b(l, 1, localJSONObject5);
      this.yt.b(l, 1, localJSONObject5);
      this.yu.b(l, 1, localJSONObject5);
      this.yv.b(l, 1, localJSONObject5);
      return;
    }
    if (str.equals("LOAD_FAILED"))
    {
      JSONObject localJSONObject4 = localJSONObject1.optJSONObject("customData");
      this.yo.b(l, 1, localJSONObject4);
      return;
    }
    if (str.equals("LOAD_CANCELLED"))
    {
      JSONObject localJSONObject3 = localJSONObject1.optJSONObject("customData");
      this.yo.b(l, 2, localJSONObject3);
      return;
    }
    if (str.equals("INVALID_REQUEST"))
    {
      this.xB.d("received unexpected error: Invalid Request.", new Object[0]);
      JSONObject localJSONObject2 = localJSONObject1.optJSONObject("customData");
      this.yo.b(l, 1, localJSONObject2);
      this.yp.b(l, 1, localJSONObject2);
      this.yq.b(l, 1, localJSONObject2);
      this.yr.b(l, 1, localJSONObject2);
      this.ys.b(l, 1, localJSONObject2);
      this.yt.b(l, 1, localJSONObject2);
      this.yu.b(l, 1, localJSONObject2);
      this.yv.b(l, 1, localJSONObject2);
    }
  }

  public long a(dx paramdx)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yv.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "GET_STATUS");
      if (this.yn != null)
        localJSONObject.put("mediaSessionId", this.yn.cU());
      label67: a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label67;
    }
  }

  public long a(dx paramdx, double paramDouble, JSONObject paramJSONObject)
    throws IOException, IllegalStateException, IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble)))
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    JSONObject localJSONObject1 = new JSONObject();
    long l = cW();
    this.yt.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject1.put("requestId", l);
      localJSONObject1.put("type", "SET_VOLUME");
      localJSONObject1.put("mediaSessionId", cU());
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("level", paramDouble);
      localJSONObject1.put("volume", localJSONObject2);
      if (paramJSONObject != null)
        localJSONObject1.put("customData", paramJSONObject);
      label151: a(localJSONObject1.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label151;
    }
  }

  public long a(dx paramdx, long paramLong, int paramInt, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.ys.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SEEK");
      localJSONObject.put("mediaSessionId", cU());
      localJSONObject.put("currentTime", dr.l(paramLong));
      if (paramInt == 1)
        localJSONObject.put("resumeState", "PLAYBACK_START");
      while (true)
      {
        if (paramJSONObject != null)
          localJSONObject.put("customData", paramJSONObject);
        label110: a(localJSONObject.toString(), l, null);
        return l;
        if (paramInt != 2)
          continue;
        localJSONObject.put("resumeState", "PLAYBACK_PAUSE");
      }
    }
    catch (JSONException localJSONException)
    {
      break label110;
    }
  }

  public long a(dx paramdx, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yo.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "LOAD");
      localJSONObject.put("media", paramMediaInfo.cT());
      localJSONObject.put("autoplay", paramBoolean);
      localJSONObject.put("currentTime", dr.l(paramLong));
      if (paramJSONObject != null)
        localJSONObject.put("customData", paramJSONObject);
      label103: a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label103;
    }
  }

  public long a(dx paramdx, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yp.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PAUSE");
      localJSONObject.put("mediaSessionId", cU());
      if (paramJSONObject != null)
        localJSONObject.put("customData", paramJSONObject);
      label72: a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label72;
    }
  }

  public long a(dx paramdx, boolean paramBoolean, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject1 = new JSONObject();
    long l = cW();
    this.yu.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject1.put("requestId", l);
      localJSONObject1.put("type", "SET_VOLUME");
      localJSONObject1.put("mediaSessionId", cU());
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("muted", paramBoolean);
      localJSONObject1.put("volume", localJSONObject2);
      if (paramJSONObject != null)
        localJSONObject1.put("customData", paramJSONObject);
      label107: a(localJSONObject1.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label107;
    }
  }

  public void a(long paramLong, int paramInt)
  {
    this.yo.c(paramLong, paramInt);
    this.yp.c(paramLong, paramInt);
    this.yq.c(paramLong, paramInt);
    this.yr.c(paramLong, paramInt);
    this.ys.c(paramLong, paramInt);
    this.yt.c(paramLong, paramInt);
    this.yu.c(paramLong, paramInt);
    this.yv.c(paramLong, paramInt);
  }

  public long b(dx paramdx, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yr.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "STOP");
      localJSONObject.put("mediaSessionId", cU());
      if (paramJSONObject != null)
        localJSONObject.put("customData", paramJSONObject);
      label72: a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label72;
    }
  }

  public long c(dx paramdx, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yq.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PLAY");
      localJSONObject.put("mediaSessionId", cU());
      if (paramJSONObject != null)
        localJSONObject.put("customData", paramJSONObject);
      label72: a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException localJSONException)
    {
      break label72;
    }
  }

  public long cU()
    throws IllegalStateException
  {
    if (this.yn == null)
      throw new IllegalStateException("No current media session");
    return this.yn.cU();
  }

  public void cX()
  {
    dj();
  }

  public long getApproximateStreamPosition()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo == null);
    do
      return 0L;
    while (this.ym == 0L);
    double d = this.yn.getPlaybackRate();
    long l1 = this.yn.getStreamPosition();
    int i = this.yn.getPlayerState();
    if ((d == 0.0D) || (i != 2))
      return l1;
    long l2 = SystemClock.elapsedRealtime() - this.ym;
    long l3;
    if (l2 < 0L)
      l3 = 0L;
    while (true)
    {
      if (l3 == 0L)
        return l1;
      long l4 = localMediaInfo.getStreamDuration();
      long l5 = l1 + ()(d * l3);
      if (l5 > l4);
      while (true)
      {
        return l4;
        if (l5 < 0L)
        {
          l4 = 0L;
          continue;
        }
        l4 = l5;
      }
      l3 = l2;
    }
  }

  public MediaInfo getMediaInfo()
  {
    if (this.yn == null)
      return null;
    return this.yn.getMediaInfo();
  }

  public MediaStatus getMediaStatus()
  {
    return this.yn;
  }

  public long getStreamDuration()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo != null)
      return localMediaInfo.getStreamDuration();
    return 0L;
  }

  protected void onMetadataUpdated()
  {
  }

  protected void onStatusUpdated()
  {
  }

  private class a
    implements Runnable
  {
    private a()
    {
    }

    public void run()
    {
      dv.a(dv.this, false);
      long l = SystemClock.elapsedRealtime();
      dv.a(dv.this).d(l, 3);
      dv.b(dv.this).d(l, 3);
      dv.c(dv.this).d(l, 3);
      dv.d(dv.this).d(l, 3);
      dv.e(dv.this).d(l, 3);
      dv.f(dv.this).d(l, 3);
      dv.g(dv.this).d(l, 3);
      dv.h(dv.this).d(l, 3);
      while (true)
      {
        synchronized (dy.yD)
        {
          if ((!dv.a(dv.this).dl()) && (!dv.e(dv.this).dl()) && (!dv.f(dv.this).dl()) && (!dv.g(dv.this).dl()))
          {
            boolean bool2 = dv.h(dv.this).dl();
            bool1 = false;
            if (!bool2)
            {
              dv.b(dv.this, bool1);
              return;
            }
          }
        }
        boolean bool1 = true;
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dv
 * JD-Core Version:    0.6.0
 */