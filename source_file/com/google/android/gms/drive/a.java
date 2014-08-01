package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
  implements Parcelable.Creator<Contents>
{
  static void a(Contents paramContents, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramContents.wj);
    b.a(paramParcel, 2, paramContents.AC, paramInt, false);
    b.c(paramParcel, 3, paramContents.CQ);
    b.c(paramParcel, 4, paramContents.CR);
    b.a(paramParcel, 5, paramContents.CS, paramInt, false);
    b.D(paramParcel, i);
  }

  public Contents[] ad(int paramInt)
  {
    return new Contents[paramInt];
  }

  public Contents y(Parcel paramParcel)
  {
    DriveId localDriveId = null;
    int i = 0;
    int j = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel);
    int k = 0;
    ParcelFileDescriptor localParcelFileDescriptor = null;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = com.google.android.gms.common.internal.safeparcel.a.n(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.S(n))
      {
      default:
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, n);
        break;
      case 1:
        m = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 2:
        localParcelFileDescriptor = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, n, ParcelFileDescriptor.CREATOR);
        break;
      case 3:
        k = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 4:
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, n);
        break;
      case 5:
        localDriveId = (DriveId)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, n, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new Contents(m, localParcelFileDescriptor, k, i, localDriveId);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.a
 * JD-Core Version:    0.6.0
 */