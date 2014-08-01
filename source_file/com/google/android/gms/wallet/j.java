package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.internal.fj;
import com.google.android.gms.internal.jj;
import com.google.android.gms.internal.jl;
import com.google.android.gms.internal.jp;
import com.google.android.gms.internal.jr;
import com.google.android.gms.internal.jt;
import com.google.android.gms.internal.jv;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class j
  implements Parcelable.Creator<LoyaltyWalletObject>
{
  static void a(LoyaltyWalletObject paramLoyaltyWalletObject, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramLoyaltyWalletObject.getVersionCode());
    b.a(paramParcel, 2, paramLoyaltyWalletObject.eN, false);
    b.a(paramParcel, 3, paramLoyaltyWalletObject.YC, false);
    b.a(paramParcel, 4, paramLoyaltyWalletObject.YD, false);
    b.a(paramParcel, 5, paramLoyaltyWalletObject.YE, false);
    b.a(paramParcel, 6, paramLoyaltyWalletObject.YF, false);
    b.a(paramParcel, 7, paramLoyaltyWalletObject.YG, false);
    b.a(paramParcel, 8, paramLoyaltyWalletObject.YH, false);
    b.a(paramParcel, 9, paramLoyaltyWalletObject.YI, false);
    b.a(paramParcel, 10, paramLoyaltyWalletObject.YJ, false);
    b.a(paramParcel, 11, paramLoyaltyWalletObject.YK, false);
    b.c(paramParcel, 12, paramLoyaltyWalletObject.state);
    b.b(paramParcel, 13, paramLoyaltyWalletObject.YL, false);
    b.a(paramParcel, 14, paramLoyaltyWalletObject.YM, paramInt, false);
    b.b(paramParcel, 15, paramLoyaltyWalletObject.YN, false);
    b.a(paramParcel, 17, paramLoyaltyWalletObject.YP, false);
    b.a(paramParcel, 16, paramLoyaltyWalletObject.YO, false);
    b.a(paramParcel, 19, paramLoyaltyWalletObject.YR);
    b.b(paramParcel, 18, paramLoyaltyWalletObject.YQ, false);
    b.b(paramParcel, 21, paramLoyaltyWalletObject.YT, false);
    b.b(paramParcel, 20, paramLoyaltyWalletObject.YS, false);
    b.a(paramParcel, 23, paramLoyaltyWalletObject.YV, paramInt, false);
    b.b(paramParcel, 22, paramLoyaltyWalletObject.YU, false);
    b.D(paramParcel, i);
  }

  public LoyaltyWalletObject ba(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
    String str8 = null;
    String str9 = null;
    String str10 = null;
    int k = 0;
    ArrayList localArrayList1 = fj.eH();
    jr localjr = null;
    ArrayList localArrayList2 = fj.eH();
    String str11 = null;
    String str12 = null;
    ArrayList localArrayList3 = fj.eH();
    boolean bool = false;
    ArrayList localArrayList4 = fj.eH();
    ArrayList localArrayList5 = fj.eH();
    ArrayList localArrayList6 = fj.eH();
    jl localjl = null;
    while (paramParcel.dataPosition() < i)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        j = a.g(paramParcel, m);
        break;
      case 2:
        str1 = a.m(paramParcel, m);
        break;
      case 3:
        str2 = a.m(paramParcel, m);
        break;
      case 4:
        str3 = a.m(paramParcel, m);
        break;
      case 5:
        str4 = a.m(paramParcel, m);
        break;
      case 6:
        str5 = a.m(paramParcel, m);
        break;
      case 7:
        str6 = a.m(paramParcel, m);
        break;
      case 8:
        str7 = a.m(paramParcel, m);
        break;
      case 9:
        str8 = a.m(paramParcel, m);
        break;
      case 10:
        str9 = a.m(paramParcel, m);
        break;
      case 11:
        str10 = a.m(paramParcel, m);
        break;
      case 12:
        k = a.g(paramParcel, m);
        break;
      case 13:
        localArrayList1 = a.c(paramParcel, m, jv.CREATOR);
        break;
      case 14:
        localjr = (jr)a.a(paramParcel, m, jr.CREATOR);
        break;
      case 15:
        localArrayList2 = a.c(paramParcel, m, LatLng.CREATOR);
        break;
      case 17:
        str12 = a.m(paramParcel, m);
        break;
      case 16:
        str11 = a.m(paramParcel, m);
        break;
      case 19:
        bool = a.c(paramParcel, m);
        break;
      case 18:
        localArrayList3 = a.c(paramParcel, m, jj.CREATOR);
        break;
      case 21:
        localArrayList5 = a.c(paramParcel, m, jp.CREATOR);
        break;
      case 20:
        localArrayList4 = a.c(paramParcel, m, jt.CREATOR);
        break;
      case 23:
        localjl = (jl)a.a(paramParcel, m, jl.CREATOR);
        break;
      case 22:
        localArrayList6 = a.c(paramParcel, m, jt.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new LoyaltyWalletObject(j, str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, k, localArrayList1, localjr, localArrayList2, str11, str12, localArrayList3, bool, localArrayList4, localArrayList5, localArrayList6, localjl);
  }

  public LoyaltyWalletObject[] cg(int paramInt)
  {
    return new LoyaltyWalletObject[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.wallet.j
 * JD-Core Version:    0.6.0
 */