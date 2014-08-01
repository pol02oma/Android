package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class db
  implements SafeParcelable
{
  public static final dc CREATOR = new dc();
  public String pU;
  public int pV;
  public int pW;
  public boolean pX;
  public final int versionCode;

  public db(int paramInt1, int paramInt2, boolean paramBoolean)
  {
  }

  db(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.versionCode = paramInt1;
    this.pU = paramString;
    this.pV = paramInt2;
    this.pW = paramInt3;
    this.pX = paramBoolean;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    dc.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.db
 * JD-Core Version:    0.6.0
 */