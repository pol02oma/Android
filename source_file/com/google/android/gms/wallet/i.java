package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class i
  implements Parcelable.Creator<LineItem>
{
  static void a(LineItem paramLineItem, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramLineItem.getVersionCode());
    b.a(paramParcel, 2, paramLineItem.description, false);
    b.a(paramParcel, 3, paramLineItem.Yy, false);
    b.a(paramParcel, 4, paramLineItem.Yz, false);
    b.a(paramParcel, 5, paramLineItem.Yf, false);
    b.c(paramParcel, 6, paramLineItem.YA);
    b.a(paramParcel, 7, paramLineItem.Yg, false);
    b.D(paramParcel, i);
  }

  public LineItem aZ(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int j = a.o(paramParcel);
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
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
        str5 = a.m(paramParcel, m);
        break;
      case 3:
        str4 = a.m(paramParcel, m);
        break;
      case 4:
        str3 = a.m(paramParcel, m);
        break;
      case 5:
        str2 = a.m(paramParcel, m);
        break;
      case 6:
        i = a.g(paramParcel, m);
        break;
      case 7:
        str1 = a.m(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new LineItem(k, str5, str4, str3, str2, i, str1);
  }

  public LineItem[] cf(int paramInt)
  {
    return new LineItem[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.i
 * JD-Core Version:    0.6.0
 */