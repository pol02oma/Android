package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class g
  implements Parcelable.Creator<CreateFileIntentSenderRequest>
{
  static void a(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramCreateFileIntentSenderRequest.wj);
    b.a(paramParcel, 2, paramCreateFileIntentSenderRequest.Ds, paramInt, false);
    b.c(paramParcel, 3, paramCreateFileIntentSenderRequest.CQ);
    b.a(paramParcel, 4, paramCreateFileIntentSenderRequest.CX, false);
    b.a(paramParcel, 5, paramCreateFileIntentSenderRequest.CY, paramInt, false);
    b.D(paramParcel, i);
  }

  public CreateFileIntentSenderRequest H(Parcel paramParcel)
  {
    int i = 0;
    DriveId localDriveId = null;
    int j = a.o(paramParcel);
    String str = null;
    MetadataBundle localMetadataBundle = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        k = a.g(paramParcel, m);
        break;
      case 2:
        localMetadataBundle = (MetadataBundle)a.a(paramParcel, m, MetadataBundle.CREATOR);
        break;
      case 3:
        i = a.g(paramParcel, m);
        break;
      case 4:
        str = a.m(paramParcel, m);
        break;
      case 5:
        localDriveId = (DriveId)a.a(paramParcel, m, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new CreateFileIntentSenderRequest(k, localMetadataBundle, i, str, localDriveId);
  }

  public CreateFileIntentSenderRequest[] am(int paramInt)
  {
    return new CreateFileIntentSenderRequest[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.g
 * JD-Core Version:    0.6.0
 */