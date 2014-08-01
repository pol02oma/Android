package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder
  implements SafeParcelable
{
  public static final Parcelable.Creator<FilterHolder> CREATOR = new c();
  final ComparisonFilter<?> ER;
  final FieldOnlyFilter ES;
  final LogicalFilter ET;
  final NotFilter EU;
  final InFilter<?> EV;
  private final Filter EW;
  final int wj;

  FilterHolder(int paramInt, ComparisonFilter<?> paramComparisonFilter, FieldOnlyFilter paramFieldOnlyFilter, LogicalFilter paramLogicalFilter, NotFilter paramNotFilter, InFilter<?> paramInFilter)
  {
    this.wj = paramInt;
    this.ER = paramComparisonFilter;
    this.ES = paramFieldOnlyFilter;
    this.ET = paramLogicalFilter;
    this.EU = paramNotFilter;
    this.EV = paramInFilter;
    if (this.ER != null)
    {
      this.EW = this.ER;
      return;
    }
    if (this.ES != null)
    {
      this.EW = this.ES;
      return;
    }
    if (this.ET != null)
    {
      this.EW = this.ET;
      return;
    }
    if (this.EU != null)
    {
      this.EW = this.EU;
      return;
    }
    if (this.EV != null)
    {
      this.EW = this.EV;
      return;
    }
    throw new IllegalArgumentException("At least one filter must be set.");
  }

  public FilterHolder(Filter paramFilter)
  {
    this.wj = 1;
    ComparisonFilter localComparisonFilter;
    FieldOnlyFilter localFieldOnlyFilter;
    label38: LogicalFilter localLogicalFilter;
    label56: NotFilter localNotFilter;
    if ((paramFilter instanceof ComparisonFilter))
    {
      localComparisonFilter = (ComparisonFilter)paramFilter;
      this.ER = localComparisonFilter;
      if (!(paramFilter instanceof FieldOnlyFilter))
        break label150;
      localFieldOnlyFilter = (FieldOnlyFilter)paramFilter;
      this.ES = localFieldOnlyFilter;
      if (!(paramFilter instanceof LogicalFilter))
        break label155;
      localLogicalFilter = (LogicalFilter)paramFilter;
      this.ET = localLogicalFilter;
      if (!(paramFilter instanceof NotFilter))
        break label161;
      localNotFilter = (NotFilter)paramFilter;
      label75: this.EU = localNotFilter;
      if (!(paramFilter instanceof InFilter))
        break label167;
    }
    label150: label155: label161: label167: for (InFilter localInFilter = (InFilter)paramFilter; ; localInFilter = null)
    {
      this.EV = localInFilter;
      if ((this.ER != null) || (this.ES != null) || (this.ET != null) || (this.EU != null) || (this.EV != null))
        break label173;
      throw new IllegalArgumentException("Invalid filter type or null filter.");
      localComparisonFilter = null;
      break;
      localFieldOnlyFilter = null;
      break label38;
      localLogicalFilter = null;
      break label56;
      localNotFilter = null;
      break label75;
    }
    label173: this.EW = paramFilter;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    c.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.FilterHolder
 * JD-Core Version:    0.6.0
 */