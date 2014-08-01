package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;
import java.util.Collections;

public class InFilter<T>
  implements SafeParcelable, Filter
{
  public static final e CREATOR = new e();
  final MetadataBundle EP;
  private final CollectionMetadataField<T> EX;
  final int wj;

  InFilter(int paramInt, MetadataBundle paramMetadataBundle)
  {
    this.wj = paramInt;
    this.EP = paramMetadataBundle;
    this.EX = ((CollectionMetadataField)d.b(paramMetadataBundle));
  }

  public InFilter(CollectionMetadataField<T> paramCollectionMetadataField, T paramT)
  {
    this(1, MetadataBundle.a(paramCollectionMetadataField, Collections.singleton(paramT)));
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    e.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.InFilter
 * JD-Core Version:    0.6.0
 */