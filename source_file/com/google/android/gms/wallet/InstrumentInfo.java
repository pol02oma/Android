package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class InstrumentInfo
  implements SafeParcelable
{
  public static final Parcelable.Creator<InstrumentInfo> CREATOR = new h();
  private String Yw;
  private String Yx;
  private final int wj;

  InstrumentInfo(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.Yw = paramString1;
    this.Yx = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getInstrumentDetails()
  {
    return this.Yx;
  }

  public String getInstrumentType()
  {
    return this.Yw;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.InstrumentInfo
 * JD-Core Version:    0.6.0
 */