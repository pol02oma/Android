package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jl
  implements SafeParcelable
{
  public static final Parcelable.Creator<jl> CREATOR = new jo();
  jr YM;
  jm ZD;
  String label;
  String type;
  private final int wj;

  jl()
  {
    this.wj = 1;
  }

  jl(int paramInt, String paramString1, jm paramjm, String paramString2, jr paramjr)
  {
    this.wj = paramInt;
    this.label = paramString1;
    this.ZD = paramjm;
    this.type = paramString2;
    this.YM = paramjr;
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
    jo.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jl
 * JD-Core Version:    0.6.0
 */