package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Operator
  implements SafeParcelable
{
  public static final Parcelable.Creator<Operator> CREATOR = new h();
  public static final Operator Fa = new Operator("=");
  public static final Operator Fb = new Operator("<");
  public static final Operator Fc = new Operator("<=");
  public static final Operator Fd = new Operator(">");
  public static final Operator Fe = new Operator(">=");
  public static final Operator Ff = new Operator("and");
  public static final Operator Fg = new Operator("or");
  public static final Operator Fh = new Operator("not");
  public static final Operator Fi = new Operator("contains");
  final String mTag;
  final int wj;

  Operator(int paramInt, String paramString)
  {
    this.wj = paramInt;
    this.mTag = paramString;
  }

  private Operator(String paramString)
  {
    this(1, paramString);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    Operator localOperator;
    do
      while (true)
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        localOperator = (Operator)paramObject;
        if (this.mTag != null)
          break;
        if (localOperator.mTag != null)
          return false;
      }
    while (this.mTag.equals(localOperator.mTag));
    return false;
  }

  public int hashCode()
  {
    if (this.mTag == null);
    for (int i = 0; ; i = this.mTag.hashCode())
      return i + 31;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.drive.query.internal.Operator
 * JD-Core Version:    0.6.0
 */