package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class fi
  implements Parcelable.Creator<fh>
{
  static void a(fh paramfh, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramfh.getVersionCode());
    b.a(paramParcel, 2, paramfh.eF(), false);
    b.a(paramParcel, 3, paramfh.eG(), paramInt, false);
    b.D(paramParcel, i);
  }

  public fh[] aa(int paramInt)
  {
    return new fh[paramInt];
  }

  public fh x(Parcel paramParcel)
  {
    fe localfe = null;
    int i = a.o(paramParcel);
    int j = 0;
    Parcel localParcel = null;
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
        localParcel = a.z(paramParcel, k);
        break;
      case 3:
        localfe = (fe)a.a(paramParcel, k, fe.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new fh(j, localParcel, localfe);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.fi
 * JD-Core Version:    0.6.0
 */