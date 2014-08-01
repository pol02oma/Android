package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class CountrySpecification
  implements SafeParcelable
{
  public static final Parcelable.Creator<CountrySpecification> CREATOR = new a();
  String oQ;
  private final int wj;

  CountrySpecification(int paramInt, String paramString)
  {
    this.wj = paramInt;
    this.oQ = paramString;
  }

  public CountrySpecification(String paramString)
  {
    this.wj = 1;
    this.oQ = paramString;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getCountryCode()
  {
    return this.oQ;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.identity.intents.model.CountrySpecification
 * JD-Core Version:    0.6.0
 */