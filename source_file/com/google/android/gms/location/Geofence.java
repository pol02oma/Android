package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.hj;

public abstract interface Geofence
{
  public static final int GEOFENCE_TRANSITION_DWELL = 4;
  public static final int GEOFENCE_TRANSITION_ENTER = 1;
  public static final int GEOFENCE_TRANSITION_EXIT = 2;
  public static final long NEVER_EXPIRE = -1L;

  public abstract String getRequestId();

  public static final class Builder
  {
    private String Hh = null;
    private int KU = 0;
    private long KV = -9223372036854775808L;
    private short KW = -1;
    private double KX;
    private double KY;
    private float KZ;
    private int La = 0;
    private int Lb = -1;

    public Geofence build()
    {
      if (this.Hh == null)
        throw new IllegalArgumentException("Request ID not set.");
      if (this.KU == 0)
        throw new IllegalArgumentException("Transitions types not set.");
      if (((0x4 & this.KU) != 0) && (this.Lb < 0))
        throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
      if (this.KV == -9223372036854775808L)
        throw new IllegalArgumentException("Expiration not set.");
      if (this.KW == -1)
        throw new IllegalArgumentException("Geofence region not set.");
      if (this.La < 0)
        throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
      return new hj(this.Hh, this.KU, 1, this.KX, this.KY, this.KZ, this.KV, this.La, this.Lb);
    }

    public Builder setCircularRegion(double paramDouble1, double paramDouble2, float paramFloat)
    {
      this.KW = 1;
      this.KX = paramDouble1;
      this.KY = paramDouble2;
      this.KZ = paramFloat;
      return this;
    }

    public Builder setExpirationDuration(long paramLong)
    {
      if (paramLong < 0L)
      {
        this.KV = -1L;
        return this;
      }
      this.KV = (paramLong + SystemClock.elapsedRealtime());
      return this;
    }

    public Builder setLoiteringDelay(int paramInt)
    {
      this.Lb = paramInt;
      return this;
    }

    public Builder setNotificationResponsiveness(int paramInt)
    {
      this.La = paramInt;
      return this;
    }

    public Builder setRequestId(String paramString)
    {
      this.Hh = paramString;
      return this;
    }

    public Builder setTransitionTypes(int paramInt)
    {
      this.KU = paramInt;
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.location.Geofence
 * JD-Core Version:    0.6.0
 */