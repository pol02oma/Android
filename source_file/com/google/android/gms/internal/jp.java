package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jp
  implements SafeParcelable
{
  public static final Parcelable.Creator<jp> CREATOR = new jq();
  String ZK;
  String oi;
  private final int wj;

  jp()
  {
    this.wj = 1;
  }

  jp(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.ZK = paramString1;
    this.oi = paramString2;
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
    jq.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.jp
 * JD-Core Version:    0.6.0
 */