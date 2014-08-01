package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import java.util.Arrays;

public class h
  implements SafeParcelable
{
  public static final j CREATOR = new j();
  private final String[] Rm;
  private final String[] Rn;
  private final String[] Ro;
  private final String Rp;
  private final String Rq;
  private final String Rr;
  private final String Rs;
  private final PlusCommonExtras Rt;
  private final String vi;
  private final int wj;

  h(int paramInt, String paramString1, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String paramString3, String paramString4, String paramString5, PlusCommonExtras paramPlusCommonExtras)
  {
    this.wj = paramInt;
    this.vi = paramString1;
    this.Rm = paramArrayOfString1;
    this.Rn = paramArrayOfString2;
    this.Ro = paramArrayOfString3;
    this.Rp = paramString2;
    this.Rq = paramString3;
    this.Rr = paramString4;
    this.Rs = paramString5;
    this.Rt = paramPlusCommonExtras;
  }

  public h(String paramString1, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString2, String paramString3, String paramString4, PlusCommonExtras paramPlusCommonExtras)
  {
    this.wj = 1;
    this.vi = paramString1;
    this.Rm = paramArrayOfString1;
    this.Rn = paramArrayOfString2;
    this.Ro = paramArrayOfString3;
    this.Rp = paramString2;
    this.Rq = paramString3;
    this.Rr = paramString4;
    this.Rs = null;
    this.Rt = paramPlusCommonExtras;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof h));
    h localh;
    do
    {
      return false;
      localh = (h)paramObject;
    }
    while ((this.wj != localh.wj) || (!ep.equal(this.vi, localh.vi)) || (!Arrays.equals(this.Rm, localh.Rm)) || (!Arrays.equals(this.Rn, localh.Rn)) || (!Arrays.equals(this.Ro, localh.Ro)) || (!ep.equal(this.Rp, localh.Rp)) || (!ep.equal(this.Rq, localh.Rq)) || (!ep.equal(this.Rr, localh.Rr)) || (!ep.equal(this.Rs, localh.Rs)) || (!ep.equal(this.Rt, localh.Rt)));
    return true;
  }

  public String getAccountName()
  {
    return this.vi;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[10];
    arrayOfObject[0] = Integer.valueOf(this.wj);
    arrayOfObject[1] = this.vi;
    arrayOfObject[2] = this.Rm;
    arrayOfObject[3] = this.Rn;
    arrayOfObject[4] = this.Ro;
    arrayOfObject[5] = this.Rp;
    arrayOfObject[6] = this.Rq;
    arrayOfObject[7] = this.Rr;
    arrayOfObject[8] = this.Rs;
    arrayOfObject[9] = this.Rt;
    return ep.hashCode(arrayOfObject);
  }

  public String[] hq()
  {
    return this.Rm;
  }

  public String[] hr()
  {
    return this.Rn;
  }

  public String[] hs()
  {
    return this.Ro;
  }

  public String ht()
  {
    return this.Rp;
  }

  public String hu()
  {
    return this.Rq;
  }

  public String hv()
  {
    return this.Rr;
  }

  public String hw()
  {
    return this.Rs;
  }

  public PlusCommonExtras hx()
  {
    return this.Rt;
  }

  public Bundle hy()
  {
    Bundle localBundle = new Bundle();
    localBundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
    this.Rt.m(localBundle);
    return localBundle;
  }

  public String toString()
  {
    return ep.e(this).a("versionCode", Integer.valueOf(this.wj)).a("accountName", this.vi).a("requestedScopes", this.Rm).a("visibleActivities", this.Rn).a("requiredFeatures", this.Ro).a("packageNameForAuth", this.Rp).a("callingPackageName", this.Rq).a("applicationName", this.Rr).a("extra", this.Rt.toString()).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.internal.h
 * JD-Core Version:    0.6.0
 */