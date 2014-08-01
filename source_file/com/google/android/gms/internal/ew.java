package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ew
  implements SafeParcelable
{
  public static final ex CREATOR = new ex();
  private final ey Co;
  private final int wj;

  ew(int paramInt, ey paramey)
  {
    this.wj = paramInt;
    this.Co = paramey;
  }

  private ew(ey paramey)
  {
    this.wj = 1;
    this.Co = paramey;
  }

  public static ew a(fb.b<?, ?> paramb)
  {
    if ((paramb instanceof ey))
      return new ew((ey)paramb);
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }

  public int describeContents()
  {
    return 0;
  }

  ey ei()
  {
    return this.Co;
  }

  public fb.b<?, ?> ej()
  {
    if (this.Co != null)
      return this.Co;
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ex.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ew
 * JD-Core Version:    0.6.0
 */