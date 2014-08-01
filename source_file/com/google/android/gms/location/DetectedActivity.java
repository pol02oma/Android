package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DetectedActivity
  implements SafeParcelable
{
  public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();
  public static final int IN_VEHICLE = 0;
  public static final int ON_BICYCLE = 1;
  public static final int ON_FOOT = 2;
  public static final int STILL = 3;
  public static final int TILTING = 5;
  public static final int UNKNOWN = 4;
  int KS;
  int KT;
  private final int wj;

  public DetectedActivity(int paramInt1, int paramInt2)
  {
    this.wj = 1;
    this.KS = paramInt1;
    this.KT = paramInt2;
  }

  public DetectedActivity(int paramInt1, int paramInt2, int paramInt3)
  {
    this.wj = paramInt1;
    this.KS = paramInt2;
    this.KT = paramInt3;
  }

  private int bh(int paramInt)
  {
    if (paramInt > 6)
      paramInt = 4;
    return paramInt;
  }

  public int describeContents()
  {
    return 0;
  }

  public int getConfidence()
  {
    return this.KT;
  }

  public int getType()
  {
    return bh(this.KS);
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public String toString()
  {
    return "DetectedActivity [type=" + getType() + ", confidence=" + this.KT + "]";
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    DetectedActivityCreator.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.location.DetectedActivity
 * JD-Core Version:    0.6.0
 */