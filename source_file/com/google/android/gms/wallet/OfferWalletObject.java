package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class OfferWalletObject
  implements SafeParcelable
{
  public static final Parcelable.Creator<OfferWalletObject> CREATOR = new n();
  String Zm;
  String eN;
  private final int wj;

  OfferWalletObject()
  {
    this.wj = 2;
  }

  OfferWalletObject(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.eN = paramString1;
    this.Zm = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getId()
  {
    return this.eN;
  }

  public String getRedemptionCode()
  {
    return this.Zm;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    n.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.OfferWalletObject
 * JD-Core Version:    0.6.0
 */