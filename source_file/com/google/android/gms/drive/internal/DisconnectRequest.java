package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DisconnectRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DisconnectRequest> CREATOR = new k();
  final int wj;

  public DisconnectRequest()
  {
    this(1);
  }

  DisconnectRequest(int paramInt)
  {
    this.wj = paramInt;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    k.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.DisconnectRequest
 * JD-Core Version:    0.6.0
 */