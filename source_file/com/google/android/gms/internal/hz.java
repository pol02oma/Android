package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public final class hz
  implements SafeParcelable
{
  public static final ia CREATOR = new ia();
  public final String Ov;
  public final String Ow;
  public final String Ox;
  public final List<String> Oy;
  public final String name;
  public final int versionCode;

  public hz(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.Ov = paramString2;
    this.Ow = paramString3;
    this.Ox = paramString4;
    this.Oy = paramList;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    hz localhz;
    do
    {
      return true;
      if (!(paramObject instanceof hz))
        return false;
      localhz = (hz)paramObject;
    }
    while ((ep.equal(this.name, localhz.name)) && (ep.equal(this.Ov, localhz.Ov)) && (ep.equal(this.Ow, localhz.Ow)) && (ep.equal(this.Ox, localhz.Ox)) && (ep.equal(this.Oy, localhz.Oy)));
    return false;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.name;
    arrayOfObject[1] = this.Ov;
    arrayOfObject[2] = this.Ow;
    arrayOfObject[3] = this.Ox;
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("name", this.name).a("address", this.Ov).a("internationalPhoneNumber", this.Ow).a("regularOpenHours", this.Ox).a("attributions", this.Oy).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ia.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hz
 * JD-Core Version:    0.6.0
 */