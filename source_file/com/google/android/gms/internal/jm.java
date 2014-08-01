package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jm
  implements SafeParcelable
{
  public static final Parcelable.Creator<jm> CREATOR = new jn();
  int ZE;
  String ZF;
  double ZG;
  String ZH;
  long ZI;
  int ZJ;
  private final int wj;

  jm()
  {
    this.wj = 1;
    this.ZJ = -1;
    this.ZE = -1;
    this.ZG = -1.0D;
  }

  jm(int paramInt1, int paramInt2, String paramString1, double paramDouble, String paramString2, long paramLong, int paramInt3)
  {
    this.wj = paramInt1;
    this.ZE = paramInt2;
    this.ZF = paramString1;
    this.ZG = paramDouble;
    this.ZH = paramString2;
    this.ZI = paramLong;
    this.ZJ = paramInt3;
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
    jn.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jm
 * JD-Core Version:    0.6.0
 */