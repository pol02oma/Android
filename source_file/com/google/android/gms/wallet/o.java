package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class o
  implements Parcelable.Creator<ProxyCard>
{
  static void a(ProxyCard paramProxyCard, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramProxyCard.getVersionCode());
    b.a(paramParcel, 2, paramProxyCard.Zn, false);
    b.a(paramParcel, 3, paramProxyCard.Zo, false);
    b.c(paramParcel, 4, paramProxyCard.Zp);
    b.c(paramParcel, 5, paramProxyCard.Zq);
    b.D(paramParcel, i);
  }

  public ProxyCard bf(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int j = a.o(paramParcel);
    int k = 0;
    String str2 = null;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      default:
        a.b(paramParcel, n);
        break;
      case 1:
        m = a.g(paramParcel, n);
        break;
      case 2:
        str2 = a.m(paramParcel, n);
        break;
      case 3:
        str1 = a.m(paramParcel, n);
        break;
      case 4:
        k = a.g(paramParcel, n);
        break;
      case 5:
        i = a.g(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new ProxyCard(m, str2, str1, k, i);
  }

  public ProxyCard[] cl(int paramInt)
  {
    return new ProxyCard[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.o
 * JD-Core Version:    0.6.0
 */