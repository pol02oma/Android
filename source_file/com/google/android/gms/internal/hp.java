package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hp
  implements SafeParcelable
{
  public static final hq CREATOR = new hq();
  private final String LE;
  private final String mTag;
  final int wj;

  hp(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.LE = paramString1;
    this.mTag = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof hp));
    hp localhp;
    do
    {
      return false;
      localhp = (hp)paramObject;
    }
    while ((!ep.equal(this.LE, localhp.LE)) || (!ep.equal(this.mTag, localhp.mTag)));
    return true;
  }

  public String getTag()
  {
    return this.mTag;
  }

  public String gt()
  {
    return this.LE;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.LE;
    arrayOfObject[1] = this.mTag;
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("mPlaceId", this.LE).a("mTag", this.mTag).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hq.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hp
 * JD-Core Version:    0.6.0
 */