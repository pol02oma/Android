package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.r;

public final class LatLngBounds
  implements SafeParcelable
{
  public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();
  public final LatLng northeast;
  public final LatLng southwest;
  private final int wj;

  LatLngBounds(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2)
  {
    er.b(paramLatLng1, "null southwest");
    er.b(paramLatLng2, "null northeast");
    if (paramLatLng2.latitude >= paramLatLng1.latitude);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Double.valueOf(paramLatLng1.latitude);
      arrayOfObject[1] = Double.valueOf(paramLatLng2.latitude);
      er.a(bool, "southern latitude exceeds northern latitude (%s > %s)", arrayOfObject);
      this.wj = paramInt;
      this.southwest = paramLatLng1;
      this.northeast = paramLatLng2;
      return;
    }
  }

  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    this(1, paramLatLng1, paramLatLng2);
  }

  private static double b(double paramDouble1, double paramDouble2)
  {
    return (360.0D + (paramDouble1 - paramDouble2)) % 360.0D;
  }

  public static Builder builder()
  {
    return new Builder();
  }

  private static double c(double paramDouble1, double paramDouble2)
  {
    return (360.0D + (paramDouble2 - paramDouble1)) % 360.0D;
  }

  private boolean c(double paramDouble)
  {
    return (this.southwest.latitude <= paramDouble) && (paramDouble <= this.northeast.latitude);
  }

  private boolean d(double paramDouble)
  {
    if (this.southwest.longitude <= this.northeast.longitude)
      return (this.southwest.longitude <= paramDouble) && (paramDouble <= this.northeast.longitude);
    int i;
    if (this.southwest.longitude > paramDouble)
    {
      boolean bool = paramDouble < this.northeast.longitude;
      i = 0;
      if (bool);
    }
    else
    {
      i = 1;
    }
    return i;
  }

  public boolean contains(LatLng paramLatLng)
  {
    return (c(paramLatLng.latitude)) && (d(paramLatLng.longitude));
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    LatLngBounds localLatLngBounds;
    do
    {
      return true;
      if (!(paramObject instanceof LatLngBounds))
        return false;
      localLatLngBounds = (LatLngBounds)paramObject;
    }
    while ((this.southwest.equals(localLatLngBounds.southwest)) && (this.northeast.equals(localLatLngBounds.northeast)));
    return false;
  }

  public LatLng getCenter()
  {
    double d1 = (this.southwest.latitude + this.northeast.latitude) / 2.0D;
    double d2 = this.northeast.longitude;
    double d3 = this.southwest.longitude;
    double d4;
    if (d3 <= d2)
      d4 = (d2 + d3) / 2.0D;
    while (true)
    {
      return new LatLng(d1, d4);
      d4 = (d3 + (d2 + 360.0D)) / 2.0D;
    }
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.southwest;
    arrayOfObject[1] = this.northeast;
    return ep.hashCode(arrayOfObject);
  }

  public LatLngBounds including(LatLng paramLatLng)
  {
    double d1 = Math.min(this.southwest.latitude, paramLatLng.latitude);
    double d2 = Math.max(this.northeast.latitude, paramLatLng.latitude);
    double d3 = this.northeast.longitude;
    double d4 = this.southwest.longitude;
    double d5 = paramLatLng.longitude;
    double d6;
    if (!d(d5))
      if (b(d4, d5) < c(d3, d5))
        d6 = d3;
    while (true)
    {
      return new LatLngBounds(new LatLng(d1, d5), new LatLng(d2, d6));
      d6 = d5;
      d5 = d4;
      continue;
      d5 = d4;
      d6 = d3;
    }
  }

  public String toString()
  {
    return ep.e(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      d.a(this, paramParcel, paramInt);
      return;
    }
    LatLngBoundsCreator.a(this, paramParcel, paramInt);
  }

  public static final class Builder
  {
    private double Qa = (1.0D / 0.0D);
    private double Qb = (-1.0D / 0.0D);
    private double Qc = (0.0D / 0.0D);
    private double Qd = (0.0D / 0.0D);

    private boolean d(double paramDouble)
    {
      if (this.Qc <= this.Qd)
        return (this.Qc <= paramDouble) && (paramDouble <= this.Qd);
      int i;
      if (this.Qc > paramDouble)
      {
        boolean bool = paramDouble < this.Qd;
        i = 0;
        if (bool);
      }
      else
      {
        i = 1;
      }
      return i;
    }

    public LatLngBounds build()
    {
      if (!Double.isNaN(this.Qc));
      for (boolean bool = true; ; bool = false)
      {
        er.a(bool, "no included points");
        return new LatLngBounds(new LatLng(this.Qa, this.Qc), new LatLng(this.Qb, this.Qd));
      }
    }

    public Builder include(LatLng paramLatLng)
    {
      this.Qa = Math.min(this.Qa, paramLatLng.latitude);
      this.Qb = Math.max(this.Qb, paramLatLng.latitude);
      double d = paramLatLng.longitude;
      if (Double.isNaN(this.Qc))
      {
        this.Qc = d;
        this.Qd = d;
      }
      do
        return this;
      while (d(d));
      if (LatLngBounds.d(this.Qc, d) < LatLngBounds.e(this.Qd, d))
      {
        this.Qc = d;
        return this;
      }
      this.Qd = d;
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.model.LatLngBounds
 * JD-Core Version:    0.6.0
 */