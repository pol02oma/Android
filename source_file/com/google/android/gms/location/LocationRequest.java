package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;

public final class LocationRequest
  implements SafeParcelable
{
  public static final LocationRequestCreator CREATOR = new LocationRequestCreator();
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  public static final int PRIORITY_LOW_POWER = 104;
  public static final int PRIORITY_NO_POWER = 105;
  long KV;
  long Lc;
  long Ld;
  boolean Le;
  int Lf;
  float Lg;
  int mPriority;
  private final int wj;

  public LocationRequest()
  {
    this.wj = 1;
    this.mPriority = 102;
    this.Lc = 3600000L;
    this.Ld = 600000L;
    this.Le = false;
    this.KV = 9223372036854775807L;
    this.Lf = 2147483647;
    this.Lg = 0.0F;
  }

  LocationRequest(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt3, float paramFloat)
  {
    this.wj = paramInt1;
    this.mPriority = paramInt2;
    this.Lc = paramLong1;
    this.Ld = paramLong2;
    this.Le = paramBoolean;
    this.KV = paramLong3;
    this.Lf = paramInt3;
    this.Lg = paramFloat;
  }

  private static void a(float paramFloat)
  {
    if (paramFloat < 0.0F)
      throw new IllegalArgumentException("invalid displacement: " + paramFloat);
  }

  private static void bi(int paramInt)
  {
    switch (paramInt)
    {
    case 101:
    case 103:
    default:
      throw new IllegalArgumentException("invalid quality: " + paramInt);
    case 100:
    case 102:
    case 104:
    case 105:
    }
  }

  public static String bj(int paramInt)
  {
    switch (paramInt)
    {
    case 101:
    case 103:
    default:
      return "???";
    case 100:
      return "PRIORITY_HIGH_ACCURACY";
    case 102:
      return "PRIORITY_BALANCED_POWER_ACCURACY";
    case 104:
      return "PRIORITY_LOW_POWER";
    case 105:
    }
    return "PRIORITY_NO_POWER";
  }

  public static LocationRequest create()
  {
    return new LocationRequest();
  }

  private static void r(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("invalid interval: " + paramLong);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    LocationRequest localLocationRequest;
    do
    {
      return true;
      if (!(paramObject instanceof LocationRequest))
        return false;
      localLocationRequest = (LocationRequest)paramObject;
    }
    while ((this.mPriority == localLocationRequest.mPriority) && (this.Lc == localLocationRequest.Lc) && (this.Ld == localLocationRequest.Ld) && (this.Le == localLocationRequest.Le) && (this.KV == localLocationRequest.KV) && (this.Lf == localLocationRequest.Lf) && (this.Lg == localLocationRequest.Lg));
    return false;
  }

  public long getExpirationTime()
  {
    return this.KV;
  }

  public long getFastestInterval()
  {
    return this.Ld;
  }

  public long getInterval()
  {
    return this.Lc;
  }

  public int getNumUpdates()
  {
    return this.Lf;
  }

  public int getPriority()
  {
    return this.mPriority;
  }

  public float getSmallestDisplacement()
  {
    return this.Lg;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = Integer.valueOf(this.mPriority);
    arrayOfObject[1] = Long.valueOf(this.Lc);
    arrayOfObject[2] = Long.valueOf(this.Ld);
    arrayOfObject[3] = Boolean.valueOf(this.Le);
    arrayOfObject[4] = Long.valueOf(this.KV);
    arrayOfObject[5] = Integer.valueOf(this.Lf);
    arrayOfObject[6] = Float.valueOf(this.Lg);
    return ep.hashCode(arrayOfObject);
  }

  public LocationRequest setExpirationDuration(long paramLong)
  {
    long l = SystemClock.elapsedRealtime();
    if (paramLong > 9223372036854775807L - l);
    for (this.KV = 9223372036854775807L; ; this.KV = (l + paramLong))
    {
      if (this.KV < 0L)
        this.KV = 0L;
      return this;
    }
  }

  public LocationRequest setExpirationTime(long paramLong)
  {
    this.KV = paramLong;
    if (this.KV < 0L)
      this.KV = 0L;
    return this;
  }

  public LocationRequest setFastestInterval(long paramLong)
  {
    r(paramLong);
    this.Le = true;
    this.Ld = paramLong;
    return this;
  }

  public LocationRequest setInterval(long paramLong)
  {
    r(paramLong);
    this.Lc = paramLong;
    if (!this.Le)
      this.Ld = ()(this.Lc / 6.0D);
    return this;
  }

  public LocationRequest setNumUpdates(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("invalid numUpdates: " + paramInt);
    this.Lf = paramInt;
    return this;
  }

  public LocationRequest setPriority(int paramInt)
  {
    bi(paramInt);
    this.mPriority = paramInt;
    return this;
  }

  public LocationRequest setSmallestDisplacement(float paramFloat)
  {
    a(paramFloat);
    this.Lg = paramFloat;
    return this;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request[").append(bj(this.mPriority));
    if (this.mPriority != 105)
    {
      localStringBuilder.append(" requested=");
      localStringBuilder.append(this.Lc + "ms");
    }
    localStringBuilder.append(" fastest=");
    localStringBuilder.append(this.Ld + "ms");
    if (this.KV != 9223372036854775807L)
    {
      long l = this.KV - SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l + "ms");
    }
    if (this.Lf != 2147483647)
      localStringBuilder.append(" num=").append(this.Lf);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    LocationRequestCreator.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.location.LocationRequest
 * JD-Core Version:    0.6.0
 */