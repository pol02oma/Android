package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class NotFilter
  implements SafeParcelable, Filter
{
  public static final Parcelable.Creator<NotFilter> CREATOR = new g();
  final FilterHolder EZ;
  final int wj;

  NotFilter(int paramInt, FilterHolder paramFilterHolder)
  {
    this.wj = paramInt;
    this.EZ = paramFilterHolder;
  }

  public NotFilter(Filter paramFilter)
  {
    this(1, new FilterHolder(paramFilter));
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    g.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.NotFilter
 * JD-Core Version:    0.6.0
 */