package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;

public class id
  implements SafeParcelable
{
  public static final ie CREATOR = new ie();
  public final String OG;
  public final String OH;
  public final int versionCode;

  public id(int paramInt, String paramString1, String paramString2)
  {
    this.versionCode = paramInt;
    this.OG = paramString1;
    this.OH = paramString2;
  }

  public id(Context paramContext, Locale paramLocale)
  {
    this.versionCode = 0;
    this.OG = paramContext.getPackageName();
    this.OH = paramLocale.toString();
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    id localid;
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof id)))
        return false;
      localid = (id)paramObject;
    }
    while ((this.OH.equals(localid.OH)) && (this.OG.equals(localid.OG)));
    return false;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.OG;
    arrayOfObject[1] = this.OH;
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("clientPackageName", this.OG).a("locale", this.OH).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ie.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.id
 * JD-Core Version:    0.6.0
 */