package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.LocationRequest;

public final class hr
  implements SafeParcelable
{
  public static final hs CREATOR = new hs();
  private final LocationRequest LF;
  private final hn LG;
  final int wj;

  public hr(int paramInt, LocationRequest paramLocationRequest, hn paramhn)
  {
    this.wj = paramInt;
    this.LF = paramLocationRequest;
    this.LG = paramhn;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    hr localhr;
    do
    {
      return true;
      if (!(paramObject instanceof hr))
        return false;
      localhr = (hr)paramObject;
    }
    while ((this.LF.equals(localhr.LF)) && (this.LG.equals(localhr.LG)));
    return false;
  }

  public LocationRequest gu()
  {
    return this.LF;
  }

  public hn gv()
  {
    return this.LG;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.LF;
    arrayOfObject[1] = this.LG;
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("locationRequest", this.LF).a("filter", this.LG).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hs.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hr
 * JD-Core Version:    0.6.0
 */