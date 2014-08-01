package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class js
  implements Parcelable.Creator<jr>
{
  static void a(jr paramjr, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramjr.getVersionCode());
    b.a(paramParcel, 2, paramjr.ZL);
    b.a(paramParcel, 3, paramjr.ZM);
    b.D(paramParcel, i);
  }

  public jr bl(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = a.o(paramParcel);
    int j = 0;
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
        l2 = a.h(paramParcel, k);
        break;
      case 3:
        l1 = a.h(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new jr(j, l2, l1);
  }

  public jr[] cr(int paramInt)
  {
    return new jr[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.js
 * JD-Core Version:    0.6.0
 */