package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ProxyCard
  implements SafeParcelable
{
  public static final Parcelable.Creator<ProxyCard> CREATOR = new o();
  String Zn;
  String Zo;
  int Zp;
  int Zq;
  private final int wj;

  ProxyCard(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3)
  {
    this.wj = paramInt1;
    this.Zn = paramString1;
    this.Zo = paramString2;
    this.Zp = paramInt2;
    this.Zq = paramInt3;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getCvn()
  {
    return this.Zo;
  }

  public int getExpirationMonth()
  {
    return this.Zp;
  }

  public int getExpirationYear()
  {
    return this.Zq;
  }

  public String getPan()
  {
    return this.Zn;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    o.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.ProxyCard
 * JD-Core Version:    0.6.0
 */