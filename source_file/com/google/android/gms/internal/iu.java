package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class iu
  implements Parcelable.Creator<ir.b>
{
  static void a(ir.b paramb, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    Set localSet = paramb.hB();
    if (localSet.contains(Integer.valueOf(1)))
      b.c(paramParcel, 1, paramb.getVersionCode());
    if (localSet.contains(Integer.valueOf(2)))
      b.a(paramParcel, 2, paramb.jdMethod_if(), paramInt, true);
    if (localSet.contains(Integer.valueOf(3)))
      b.a(paramParcel, 3, paramb.ig(), paramInt, true);
    if (localSet.contains(Integer.valueOf(4)))
      b.c(paramParcel, 4, paramb.getLayout());
    b.D(paramParcel, i);
  }

  public ir.b aK(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = 0;
    int j = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    Object localObject2 = null;
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
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2:
        ir.b.a locala = (ir.b.a)a.a(paramParcel, m, ir.b.a.CREATOR);
        localHashSet.add(Integer.valueOf(2));
        localObject2 = locala;
        break;
      case 3:
        ir.b.b localb = (ir.b.b)a.a(paramParcel, m, ir.b.b.CREATOR);
        localHashSet.add(Integer.valueOf(3));
        localObject1 = localb;
        break;
      case 4:
        i = a.g(paramParcel, m);
        localHashSet.add(Integer.valueOf(4));
      }
    }
    if (paramParcel.dataPosition() != j)
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    return new ir.b(localHashSet, k, localObject2, localObject1, i);
  }

  public ir.b[] bH(int paramInt)
  {
    return new ir.b[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.iu
 * JD-Core Version:    0.6.0
 */