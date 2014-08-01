package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class c
  implements Parcelable.Creator<FilterHolder>
{
  static void a(FilterHolder paramFilterHolder, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.a(paramParcel, 1, paramFilterHolder.ER, paramInt, false);
    b.c(paramParcel, 1000, paramFilterHolder.wj);
    b.a(paramParcel, 2, paramFilterHolder.ES, paramInt, false);
    b.a(paramParcel, 3, paramFilterHolder.ET, paramInt, false);
    b.a(paramParcel, 4, paramFilterHolder.EU, paramInt, false);
    b.a(paramParcel, 5, paramFilterHolder.EV, paramInt, false);
    b.D(paramParcel, i);
  }

  public FilterHolder[] aJ(int paramInt)
  {
    return new FilterHolder[paramInt];
  }

  public FilterHolder ae(Parcel paramParcel)
  {
    InFilter localInFilter = null;
    int i = a.o(paramParcel);
    int j = 0;
    NotFilter localNotFilter = null;
    LogicalFilter localLogicalFilter = null;
    FieldOnlyFilter localFieldOnlyFilter = null;
    ComparisonFilter localComparisonFilter = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default:
        a.b(paramParcel, k);
        break;
      case 1:
        localComparisonFilter = (ComparisonFilter)a.a(paramParcel, k, ComparisonFilter.CREATOR);
        break;
      case 1000:
        j = a.g(paramParcel, k);
        break;
      case 2:
        localFieldOnlyFilter = (FieldOnlyFilter)a.a(paramParcel, k, FieldOnlyFilter.CREATOR);
        break;
      case 3:
        localLogicalFilter = (LogicalFilter)a.a(paramParcel, k, LogicalFilter.CREATOR);
        break;
      case 4:
        localNotFilter = (NotFilter)a.a(paramParcel, k, NotFilter.CREATOR);
        break;
      case 5:
        localInFilter = (InFilter)a.a(paramParcel, k, InFilter.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new FilterHolder(j, localComparisonFilter, localFieldOnlyFilter, localLogicalFilter, localNotFilter, localInFilter);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.c
 * JD-Core Version:    0.6.0
 */