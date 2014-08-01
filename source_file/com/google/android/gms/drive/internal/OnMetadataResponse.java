package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class OnMetadataResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnMetadataResponse> CREATOR = new af();
  final MetadataBundle Ds;
  final int wj;

  OnMetadataResponse(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.wj = paramInt;
    this.Ds = paramMetadataBundle;
  }

  public int describeContents()
  {
    return 0;
  }

  public MetadataBundle fe()
  {
    return this.Ds;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    af.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.OnMetadataResponse
 * JD-Core Version:    0.6.0
 */