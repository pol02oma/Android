package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Query;

public class QueryRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<QueryRequest> CREATOR = new ai();
  final Query Ef;
  final int wj;

  QueryRequest(int paramInt, Query paramQuery)
  {
    this.wj = paramInt;
    this.Ef = paramQuery;
  }

  public QueryRequest(Query paramQuery)
  {
    this(1, paramQuery);
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ai.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.internal.QueryRequest
 * JD-Core Version:    0.6.0
 */