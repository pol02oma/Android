package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class ah
  implements Parcelable.Creator<OpenFileIntentSenderRequest>
{
  static void a(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramOpenFileIntentSenderRequest.wj);
    b.a(paramParcel, 2, paramOpenFileIntentSenderRequest.CX, false);
    b.a(paramParcel, 3, paramOpenFileIntentSenderRequest.Dk, false);
    b.a(paramParcel, 4, paramOpenFileIntentSenderRequest.CY, paramInt, false);
    b.D(paramParcel, i);
  }

  public OpenFileIntentSenderRequest V(Parcel paramParcel)
  {
    DriveId localDriveId = null;
    int i = a.o(paramParcel);
    int j = 0;
    String[] arrayOfString = null;
    String str = null;
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
        arrayOfString = a.x(paramParcel, k);
        break;
      case 4:
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new OpenFileIntentSenderRequest(j, str, arrayOfString, localDriveId);
  }

  public OpenFileIntentSenderRequest[] aA(int paramInt)
  {
    return new OpenFileIntentSenderRequest[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.ah
 * JD-Core Version:    0.6.0
 */