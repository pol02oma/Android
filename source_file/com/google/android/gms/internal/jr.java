package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jr
  implements SafeParcelable
{
  public static final Parcelable.Creator<jr> CREATOR = new js();
  long ZL;
  long ZM;
  private final int wj;

  jr()
  {
    this.wj = 1;
  }

  jr(int paramInt, long paramLong1, long paramLong2)
  {
    this.wj = paramInt;
    this.ZL = paramLong1;
    this.ZM = paramLong2;
  }

  public int describeContents()
  {
    return 0;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    js.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jr
 * JD-Core Version:    0.6.0
 */