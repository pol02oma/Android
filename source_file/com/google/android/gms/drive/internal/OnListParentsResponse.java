package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnListParentsResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnListParentsResponse> CREATOR = new ae();
  final DataHolder Ee;
  final int wj;

  OnListParentsResponse(int paramInt, DataHolder paramDataHolder)
  {
    this.wj = paramInt;
    this.Ee = paramDataHolder;
  }

  public int describeContents()
  {
    return 0;
  }

  public DataHolder fd()
  {
    return this.Ee;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ae.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnListParentsResponse
 * JD-Core Version:    0.6.0
 */