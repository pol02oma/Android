package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class d
  implements Parcelable.Creator<DriveId>
{
  static void a(DriveId paramDriveId, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramDriveId.wj);
    b.a(paramParcel, 2, paramDriveId.Dc, false);
    b.a(paramParcel, 3, paramDriveId.Dd);
    b.a(paramParcel, 4, paramDriveId.De);
    b.D(paramParcel, i);
  }

  public DriveId[] ae(int paramInt)
  {
    return new DriveId[paramInt];
  }

  public DriveId z(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = a.o(paramParcel);
    int j = 0;
    String str = null;
    long l2 = l1;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        j = a.g(paramParcel, k);
        break;
      case 2:
        str = a.m(paramParcel, k);
        break;
      case 3:
        l2 = a.h(paramParcel, k);
        break;
      case 4:
        l1 = a.h(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new DriveId(j, str, l2, l1);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.d
 * JD-Core Version:    0.6.0
 */