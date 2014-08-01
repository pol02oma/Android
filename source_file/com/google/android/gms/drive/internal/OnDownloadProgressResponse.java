package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnDownloadProgressResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDownloadProgressResponse> CREATOR = new aa();
  final long DZ;
  final long Ea;
  final int wj;

  OnDownloadProgressResponse(int paramInt, long paramLong1, long paramLong2)
  {
    this.wj = paramInt;
    this.DZ = paramLong1;
    this.Ea = paramLong2;
  }

  public int describeContents()
  {
    return 0;
  }

  public long eY()
  {
    return this.DZ;
  }

  public long eZ()
  {
    return this.Ea;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    aa.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnDownloadProgressResponse
 * JD-Core Version:    0.6.0
 */