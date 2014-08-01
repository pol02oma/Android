package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListEntriesResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnListEntriesResponse> CREATOR = new ad();
  final DataHolder Ed;
  final int wj;

  OnListEntriesResponse(int paramInt, DataHolder paramDataHolder)
  {
    this.wj = paramInt;
    this.Ed = paramDataHolder;
  }

  public int describeContents()
  {
    return 0;
  }

  public DataHolder fc()
  {
    return this.Ed;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ad.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnListEntriesResponse
 * JD-Core Version:    0.6.0
 */