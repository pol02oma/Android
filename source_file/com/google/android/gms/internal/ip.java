package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class ip
  implements Parcelable.Creator<io>
{
  static void a(io paramio, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = paramio.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, paramio.getVersionCode());
    if (localSet.contains(Integer.valueOf(2)))
      b.a(paramParcel, 2, paramio.getId(), true);
    if (localSet.contains(Integer.valueOf(4)))
      b.a(paramParcel, 4, paramio.hS(), paramInt, true);
    if (localSet.contains(Integer.valueOf(5)))
      b.a(paramParcel, 5, paramio.getStartDate(), true);
    if (localSet.contains(Integer.valueOf(6)))
      b.a(paramParcel, 6, paramio.hT(), paramInt, true);
    if (localSet.contains(Integer.valueOf(7)))
      b.a(paramParcel, 7, paramio.getType(), true);
    b.D(paramParcel, i);
  }

  public io aH(Parcel paramParcel)
  {
    String str1 = null;
    int i = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    int j = 0;
    Object localObject1 = null;
    String str2 = null;
    Object localObject2 = null;
    String str3 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      case 3:
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        j = a.g(paramParcel, k);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2:
        str3 = a.m(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 4:
        im localim2 = (im)a.a(paramParcel, k, im.CREATOR);
        localHashSet.add(Integer.valueOf(4));
        localObject2 = localim2;
        break;
      case 5:
        str2 = a.m(paramParcel, k);
        localHashSet.add(Integer.valueOf(5));
        break;
      case 6:
        im localim1 = (im)a.a(paramParcel, k, im.CREATOR);
        localHashSet.add(Integer.valueOf(6));
        localObject1 = localim1;
        break;
      case 7:
        str1 = a.m(paramParcel, k);
        localHashSet.add(Integer.valueOf(7));
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new io(localHashSet, j, str3, localObject2, str2, localObject1, str1);
  }

  public io[] bE(int paramInt)
  {
    return new io[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ip
 * JD-Core Version:    0.6.0
 */