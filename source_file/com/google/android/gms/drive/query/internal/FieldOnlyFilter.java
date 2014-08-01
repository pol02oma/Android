package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;

public class FieldOnlyFilter
  implements SafeParcelable, Filter
{
  public static final Parcelable.Creator<FieldOnlyFilter> CREATOR = new b();
  final MetadataBundle EP;
  private final MetadataField<?> EQ;
  final int wj;

  FieldOnlyFilter(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.wj = paramInt;
    this.EP = paramMetadataBundle;
    this.EQ = d.b(paramMetadataBundle);
  }

  public FieldOnlyFilter(MetadataField<?> paramMetadataField)
  {
    this(1, MetadataBundle.a(paramMetadataField, null));
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.FieldOnlyFilter
 * JD-Core Version:    0.6.0
 */