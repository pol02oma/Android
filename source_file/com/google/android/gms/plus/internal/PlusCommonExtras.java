package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;

public class PlusCommonExtras
  implements SafeParcelable
{
  public static final f CREATOR;
  public static String TAG = "PlusCommonExtras";
  private String Rj;
  private String Rk;
  private final int wj;

  static
  {
    CREATOR = new f();
  }

  public PlusCommonExtras()
  {
    this.wj = 1;
    this.Rj = "";
    this.Rk = "";
  }

  PlusCommonExtras(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.Rj = paramString1;
    this.Rk = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlusCommonExtras));
    PlusCommonExtras localPlusCommonExtras;
    do
    {
      return false;
      localPlusCommonExtras = (PlusCommonExtras)paramObject;
    }
    while ((this.wj != localPlusCommonExtras.wj) || (!ep.equal(this.Rj, localPlusCommonExtras.Rj)) || (!ep.equal(this.Rk, localPlusCommonExtras.Rk)));
    return true;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.wj);
    arrayOfObject[1] = this.Rj;
    arrayOfObject[2] = this.Rk;
    return ep.hashCode(arrayOfObject);
  }

  public String ho()
  {
    return this.Rj;
  }

  public String hp()
  {
    return this.Rk;
  }

  public void m(Bundle paramBundle)
  {
  }

  public String toString()
  {
    return ep.e(this).a("versionCode", Integer.valueOf(this.wj)).a("Gpsrc", this.Rj).a("ClientCallingPackage", this.Rk).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.internal.PlusCommonExtras
 * JD-Core Version:    0.6.0
 */