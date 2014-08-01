package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class is
  implements Parcelable.Creator<ir>
{
  static void a(ir paramir, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = paramir.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, paramir.getVersionCode());
    if (localSet.contains(Integer.valueOf(2)))
      b.a(paramParcel, 2, paramir.getAboutMe(), true);
    if (localSet.contains(Integer.valueOf(3)))
      b.a(paramParcel, 3, paramir.hW(), paramInt, true);
    if (localSet.contains(Integer.valueOf(4)))
      b.a(paramParcel, 4, paramir.getBirthday(), true);
    if (localSet.contains(Integer.valueOf(5)))
      b.a(paramParcel, 5, paramir.getBraggingRights(), true);
    if (localSet.contains(Integer.valueOf(6)))
      b.c(paramParcel, 6, paramir.getCircledByCount());
    if (localSet.contains(Integer.valueOf(7)))
      b.a(paramParcel, 7, paramir.hX(), paramInt, true);
    if (localSet.contains(Integer.valueOf(8)))
      b.a(paramParcel, 8, paramir.getCurrentLocation(), true);
    if (localSet.contains(Integer.valueOf(9)))
      b.a(paramParcel, 9, paramir.getDisplayName(), true);
    if (localSet.contains(Integer.valueOf(12)))
      b.c(paramParcel, 12, paramir.getGender());
    if (localSet.contains(Integer.valueOf(14)))
      b.a(paramParcel, 14, paramir.getId(), true);
    if (localSet.contains(Integer.valueOf(15)))
      b.a(paramParcel, 15, paramir.hY(), paramInt, true);
    if (localSet.contains(Integer.valueOf(16)))
      b.a(paramParcel, 16, paramir.isPlusUser());
    if (localSet.contains(Integer.valueOf(19)))
      b.a(paramParcel, 19, paramir.hZ(), paramInt, true);
    if (localSet.contains(Integer.valueOf(18)))
      b.a(paramParcel, 18, paramir.getLanguage(), true);
    if (localSet.contains(Integer.valueOf(21)))
      b.c(paramParcel, 21, paramir.getObjectType());
    if (localSet.contains(Integer.valueOf(20)))
      b.a(paramParcel, 20, paramir.getNickname(), true);
    if (localSet.contains(Integer.valueOf(23)))
      b.b(paramParcel, 23, paramir.ib(), true);
    if (localSet.contains(Integer.valueOf(22)))
      b.b(paramParcel, 22, paramir.ia(), true);
    if (localSet.contains(Integer.valueOf(25)))
      b.c(paramParcel, 25, paramir.getRelationshipStatus());
    if (localSet.contains(Integer.valueOf(24)))
      b.c(paramParcel, 24, paramir.getPlusOneCount());
    if (localSet.contains(Integer.valueOf(27)))
      b.a(paramParcel, 27, paramir.getUrl(), true);
    if (localSet.contains(Integer.valueOf(26)))
      b.a(paramParcel, 26, paramir.getTagline(), true);
    if (localSet.contains(Integer.valueOf(29)))
      b.a(paramParcel, 29, paramir.isVerified());
    if (localSet.contains(Integer.valueOf(28)))
      b.b(paramParcel, 28, paramir.ic(), true);
    b.D(paramParcel, i);
  }

  public ir aI(Parcel paramParcel)
  {
    int i = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    int j = 0;
    String str1 = null;
    Object localObject1 = null;
    String str2 = null;
    String str3 = null;
    int k = 0;
    Object localObject2 = null;
    String str4 = null;
    String str5 = null;
    int m = 0;
    String str6 = null;
    Object localObject3 = null;
    boolean bool1 = false;
    String str7 = null;
    Object localObject4 = null;
    String str8 = null;
    int n = 0;
    ArrayList localArrayList1 = null;
    ArrayList localArrayList2 = null;
    int i1 = 0;
    int i2 = 0;
    String str9 = null;
    String str10 = null;
    ArrayList localArrayList3 = null;
    boolean bool2 = false;
    while (paramParcel.dataPosition() < i)
    {
      int i3 = a.n(paramParcel);
      switch (a.S(i3))
      {
      case 10:
      case 11:
      case 13:
      case 17:
      default:
        a.b(paramParcel, i3);
        break;
      case 1:
        j = a.g(paramParcel, i3);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2:
        str1 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3:
        ir.a locala = (ir.a)a.a(paramParcel, i3, ir.a.CREATOR);
        localHashSet.add(Integer.valueOf(3));
        localObject1 = locala;
        break;
      case 4:
        str2 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(4));
        break;
      case 5:
        str3 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6:
        k = a.g(paramParcel, i3);
        localHashSet.add(Integer.valueOf(6));
        break;
      case 7:
        ir.b localb = (ir.b)a.a(paramParcel, i3, ir.b.CREATOR);
        localHashSet.add(Integer.valueOf(7));
        localObject2 = localb;
        break;
      case 8:
        str4 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(8));
        break;
      case 9:
        str5 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(9));
        break;
      case 12:
        m = a.g(paramParcel, i3);
        localHashSet.add(Integer.valueOf(12));
        break;
      case 14:
        str6 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(14));
        break;
      case 15:
        ir.c localc = (ir.c)a.a(paramParcel, i3, ir.c.CREATOR);
        localHashSet.add(Integer.valueOf(15));
        localObject3 = localc;
        break;
      case 16:
        bool1 = a.c(paramParcel, i3);
        localHashSet.add(Integer.valueOf(16));
        break;
      case 19:
        ir.d locald = (ir.d)a.a(paramParcel, i3, ir.d.CREATOR);
        localHashSet.add(Integer.valueOf(19));
        localObject4 = locald;
        break;
      case 18:
        str7 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(18));
        break;
      case 21:
        n = a.g(paramParcel, i3);
        localHashSet.add(Integer.valueOf(21));
        break;
      case 20:
        str8 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(20));
        break;
      case 23:
        localArrayList2 = a.c(paramParcel, i3, ir.g.CREATOR);
        localHashSet.add(Integer.valueOf(23));
        break;
      case 22:
        localArrayList1 = a.c(paramParcel, i3, ir.f.CREATOR);
        localHashSet.add(Integer.valueOf(22));
        break;
      case 25:
        i2 = a.g(paramParcel, i3);
        localHashSet.add(Integer.valueOf(25));
        break;
      case 24:
        i1 = a.g(paramParcel, i3);
        localHashSet.add(Integer.valueOf(24));
        break;
      case 27:
        str10 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(27));
        break;
      case 26:
        str9 = a.m(paramParcel, i3);
        localHashSet.add(Integer.valueOf(26));
        break;
      case 29:
        bool2 = a.c(paramParcel, i3);
        localHashSet.add(Integer.valueOf(29));
        break;
      case 28:
        localArrayList3 = a.c(paramParcel, i3, ir.h.CREATOR);
        localHashSet.add(Integer.valueOf(28));
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new ir(localHashSet, j, str1, localObject1, str2, str3, k, localObject2, str4, str5, m, str6, localObject3, bool1, str7, localObject4, str8, n, localArrayList1, localArrayList2, i1, i2, str9, str10, localArrayList3, bool2);
  }

  public ir[] bF(int paramInt)
  {
    return new ir[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.is
 * JD-Core Version:    0.6.0
 */