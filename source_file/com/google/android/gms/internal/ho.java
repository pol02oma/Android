package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class ho
  implements Parcelable.Creator<hn>
{
  static void a(hn paramhn, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.b(paramParcel, 1, paramhn.LA, false);
    b.c(paramParcel, 1000, paramhn.wj);
    b.a(paramParcel, 2, paramhn.gr(), false);
    b.a(paramParcel, 3, paramhn.gs());
    b.D(paramParcel, i);
  }

  public hn aw(Parcel paramParcel)
  {
    String str = null;
    boolean bool = false;
    int i = a.o(paramParcel);
    ArrayList localArrayList = null;
    int j = 0;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        localArrayList = a.c(paramParcel, k, ht.CREATOR);
        break;
      case 1000:
        j = a.g(paramParcel, k);
        break;
      case 2:
        str = a.m(paramParcel, k);
        break;
      case 3:
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new hn(j, localArrayList, str, bool);
  }

  public hn[] bq(int paramInt)
  {
    return new hn[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ho
 * JD-Core Version:    0.6.0
 */