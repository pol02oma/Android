package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ce
  implements Parcelable.Creator<cd>
{
  static void a(cd paramcd, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramcd.versionCode);
    b.a(paramParcel, 2, paramcd.ob, false);
    b.a(paramParcel, 3, paramcd.oc, paramInt, false);
    b.a(paramParcel, 4, paramcd.kQ, paramInt, false);
    b.a(paramParcel, 5, paramcd.adUnitId, false);
    b.a(paramParcel, 6, paramcd.applicationInfo, paramInt, false);
    b.a(paramParcel, 7, paramcd.od, paramInt, false);
    b.a(paramParcel, 8, paramcd.oe, false);
    b.a(paramParcel, 9, paramcd.of, false);
    b.a(paramParcel, 10, paramcd.og, false);
    b.a(paramParcel, 11, paramcd.kN, paramInt, false);
    b.a(paramParcel, 12, paramcd.oh, false);
    b.D(paramParcel, i);
  }

  public cd f(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    int j = 0;
    Bundle localBundle1 = null;
    z localz = null;
    ab localab = null;
    String str1 = null;
    ApplicationInfo localApplicationInfo = null;
    PackageInfo localPackageInfo = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    db localdb = null;
    Bundle localBundle2 = null;
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
        localBundle1 = a.o(paramParcel, k);
        break;
      case 3:
        localz = (z)a.a(paramParcel, k, z.CREATOR);
        break;
      case 4:
        localab = (ab)a.a(paramParcel, k, ab.CREATOR);
        break;
      case 5:
        str1 = a.m(paramParcel, k);
        break;
      case 6:
        localApplicationInfo = (ApplicationInfo)a.a(paramParcel, k, ApplicationInfo.CREATOR);
        break;
      case 7:
        localPackageInfo = (PackageInfo)a.a(paramParcel, k, PackageInfo.CREATOR);
        break;
      case 8:
        str2 = a.m(paramParcel, k);
        break;
      case 9:
        str3 = a.m(paramParcel, k);
        break;
      case 10:
        str4 = a.m(paramParcel, k);
        break;
      case 11:
        localdb = (db)a.a(paramParcel, k, db.CREATOR);
        break;
      case 12:
        localBundle2 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new cd(j, localBundle1, localz, localab, str1, localApplicationInfo, localPackageInfo, str2, str3, str4, localdb, localBundle2);
  }

  public cd[] k(int paramInt)
  {
    return new cd[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ce
 * JD-Core Version:    0.6.0
 */