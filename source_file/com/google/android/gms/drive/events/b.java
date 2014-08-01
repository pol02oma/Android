package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.drive.DriveId;

public class b
  implements Parcelable.Creator<ConflictEvent>
{
  static void a(ConflictEvent paramConflictEvent, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.p(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramConflictEvent.wj);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramConflictEvent.CS, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.D(paramParcel, i);
  }

  public ConflictEvent B(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    DriveId localDriveId = null;
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
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new ConflictEvent(j, localDriveId);
  }

  public ConflictEvent[] ag(int paramInt)
  {
    return new ConflictEvent[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.events.b
 * JD-Core Version:    0.6.0
 */