package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;

public class b
  implements SafeParcelable
{
  public static final c CREATOR = new c();
  int Lh;
  int Li;
  long Lj;
  private final int wj;

  b(int paramInt1, int paramInt2, int paramInt3, long paramLong)
  {
    this.wj = paramInt1;
    this.Lh = paramInt2;
    this.Li = paramInt3;
    this.Lj = paramLong;
  }

  private String bk(int paramInt)
  {
    switch (paramInt)
    {
    case 1:
    default:
      return "STATUS_UNKNOWN";
    case 0:
      return "STATUS_SUCCESSFUL";
    case 2:
      return "STATUS_TIMED_OUT_ON_SCAN";
    case 3:
      return "STATUS_NO_INFO_IN_DATABASE";
    case 4:
      return "STATUS_INVALID_SCAN";
    case 5:
      return "STATUS_UNABLE_TO_QUERY_DATABASE";
    case 6:
      return "STATUS_SCANS_DISABLED_IN_SETTINGS";
    case 7:
      return "STATUS_LOCATION_DISABLED_IN_SETTINGS";
    case 8:
    }
    return "STATUS_IN_PROGRESS";
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof b));
    b localb;
    do
    {
      return false;
      localb = (b)paramObject;
    }
    while ((this.Lh != localb.Lh) || (this.Li != localb.Li) || (this.Lj != localb.Lj));
    return true;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.Lh);
    arrayOfObject[1] = Integer.valueOf(this.Li);
    arrayOfObject[2] = Long.valueOf(this.Lj);
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LocationStatus[cell status: ").append(bk(this.Lh));
    localStringBuilder.append(", wifi status: ").append(bk(this.Li));
    localStringBuilder.append(", elapsed realtime ns: ").append(this.Lj);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.location.b
 * JD-Core Version:    0.6.0
 */