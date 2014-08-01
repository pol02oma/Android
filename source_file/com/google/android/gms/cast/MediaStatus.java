package com.google.android.gms.cast;

import com.google.android.gms.internal.dr;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaStatus
{
  public static final long COMMAND_PAUSE = 1L;
  public static final long COMMAND_SEEK = 2L;
  public static final long COMMAND_SET_VOLUME = 4L;
  public static final long COMMAND_SKIP_BACKWARD = 32L;
  public static final long COMMAND_SKIP_FORWARD = 16L;
  public static final long COMMAND_TOGGLE_MUTE = 8L;
  public static final int IDLE_REASON_CANCELED = 2;
  public static final int IDLE_REASON_ERROR = 4;
  public static final int IDLE_REASON_FINISHED = 1;
  public static final int IDLE_REASON_INTERRUPTED = 3;
  public static final int IDLE_REASON_NONE = 0;
  public static final int PLAYER_STATE_BUFFERING = 4;
  public static final int PLAYER_STATE_IDLE = 1;
  public static final int PLAYER_STATE_PAUSED = 3;
  public static final int PLAYER_STATE_PLAYING = 2;
  public static final int PLAYER_STATE_UNKNOWN;
  private JSONObject wP;
  private MediaInfo wQ;
  private long wY;
  private double wZ;
  private int xa;
  private int xb;
  private long xc;
  private long xd;
  private double xe;
  private boolean xf;

  public MediaStatus(JSONObject paramJSONObject)
    throws JSONException
  {
    a(paramJSONObject, 0);
  }

  public int a(JSONObject paramJSONObject, int paramInt)
    throws JSONException
  {
    int i = 2;
    long l1 = paramJSONObject.getLong("mediaSessionId");
    if (l1 != this.wY)
      this.wY = l1;
    for (int j = 1; ; j = 0)
    {
      String str1;
      int k;
      if (paramJSONObject.has("playerState"))
      {
        str1 = paramJSONObject.getString("playerState");
        if (!str1.equals("IDLE"))
          break label422;
        k = 1;
      }
      while (true)
      {
        if (k != this.xa)
        {
          this.xa = k;
          j |= 2;
        }
        String str2;
        if ((k == 1) && (paramJSONObject.has("idleReason")))
        {
          str2 = paramJSONObject.getString("idleReason");
          if (!str2.equals("CANCELLED"))
            break label470;
        }
        while (true)
        {
          if (i != this.xb)
          {
            this.xb = i;
            j |= 2;
          }
          if (paramJSONObject.has("playbackRate"))
          {
            double d2 = paramJSONObject.getDouble("playbackRate");
            if (this.wZ != d2)
            {
              this.wZ = d2;
              j |= 2;
            }
          }
          if ((paramJSONObject.has("currentTime")) && ((paramInt & 0x2) == 0))
          {
            long l3 = dr.b(paramJSONObject.getDouble("currentTime"));
            if (l3 != this.xc)
            {
              this.xc = l3;
              j |= 2;
            }
          }
          if (paramJSONObject.has("supportedMediaCommands"))
          {
            long l2 = paramJSONObject.getLong("supportedMediaCommands");
            if (l2 != this.xd)
            {
              this.xd = l2;
              j |= 2;
            }
          }
          if ((paramJSONObject.has("volume")) && ((paramInt & 0x1) == 0))
          {
            JSONObject localJSONObject2 = paramJSONObject.getJSONObject("volume");
            double d1 = localJSONObject2.getDouble("level");
            if (d1 != this.xe)
            {
              this.xe = d1;
              j |= 2;
            }
            boolean bool = localJSONObject2.getBoolean("muted");
            if (bool != this.xf)
            {
              this.xf = bool;
              j |= 2;
            }
          }
          if (paramJSONObject.has("customData"))
          {
            this.wP = paramJSONObject.getJSONObject("customData");
            j |= 2;
          }
          if (paramJSONObject.has("media"))
          {
            JSONObject localJSONObject1 = paramJSONObject.getJSONObject("media");
            this.wQ = new MediaInfo(localJSONObject1);
            j |= 2;
            if (localJSONObject1.has("metadata"))
              j |= 4;
          }
          return j;
          label422: if (str1.equals("PLAYING"))
          {
            k = i;
            break;
          }
          if (str1.equals("PAUSED"))
          {
            k = 3;
            break;
          }
          if (!str1.equals("BUFFERING"))
            break label520;
          k = 4;
          break;
          label470: if (str2.equals("INTERRUPTED"))
          {
            i = 3;
            continue;
          }
          if (str2.equals("FINISHED"))
          {
            i = 1;
            continue;
          }
          if (str2.equals("ERROR"))
          {
            i = 4;
            continue;
          }
          i = 0;
        }
        label520: k = 0;
      }
    }
  }

  public long cU()
  {
    return this.wY;
  }

  public JSONObject getCustomData()
  {
    return this.wP;
  }

  public int getIdleReason()
  {
    return this.xb;
  }

  public MediaInfo getMediaInfo()
  {
    return this.wQ;
  }

  public double getPlaybackRate()
  {
    return this.wZ;
  }

  public int getPlayerState()
  {
    return this.xa;
  }

  public long getStreamPosition()
  {
    return this.xc;
  }

  public double getStreamVolume()
  {
    return this.xe;
  }

  public boolean isMediaCommandSupported(long paramLong)
  {
    return (paramLong & this.xd) != 0L;
  }

  public boolean isMute()
  {
    return this.xf;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.MediaStatus
 * JD-Core Version:    0.6.0
 */