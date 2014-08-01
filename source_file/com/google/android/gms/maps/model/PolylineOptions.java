package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class PolylineOptions
  implements SafeParcelable
{
  public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();
  private float PP = 0.0F;
  private boolean PQ = true;
  private float PU = 10.0F;
  private final List<LatLng> Qo;
  private boolean Qq = false;
  private final int wj;
  private int yX = -16777216;

  public PolylineOptions()
  {
    this.wj = 1;
    this.Qo = new ArrayList();
  }

  PolylineOptions(int paramInt1, List paramList, float paramFloat1, int paramInt2, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.wj = paramInt1;
    this.Qo = paramList;
    this.PU = paramFloat1;
    this.yX = paramInt2;
    this.PP = paramFloat2;
    this.PQ = paramBoolean1;
    this.Qq = paramBoolean2;
  }

  public PolylineOptions add(LatLng paramLatLng)
  {
    this.Qo.add(paramLatLng);
    return this;
  }

  public PolylineOptions add(LatLng[] paramArrayOfLatLng)
  {
    this.Qo.addAll(Arrays.asList(paramArrayOfLatLng));
    return this;
  }

  public PolylineOptions addAll(Iterable<LatLng> paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext())
    {
      LatLng localLatLng = (LatLng)localIterator.next();
      this.Qo.add(localLatLng);
    }
    return this;
  }

  public PolylineOptions color(int paramInt)
  {
    this.yX = paramInt;
    return this;
  }

  public int describeContents()
  {
    return 0;
  }

  public PolylineOptions geodesic(boolean paramBoolean)
  {
    this.Qq = paramBoolean;
    return this;
  }

  public int getColor()
  {
    return this.yX;
  }

  public List<LatLng> getPoints()
  {
    return this.Qo;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public float getWidth()
  {
    return this.PU;
  }

  public float getZIndex()
  {
    return this.PP;
  }

  public boolean isGeodesic()
  {
    return this.Qq;
  }

  public boolean isVisible()
  {
    return this.PQ;
  }

  public PolylineOptions visible(boolean paramBoolean)
  {
    this.PQ = paramBoolean;
    return this;
  }

  public PolylineOptions width(float paramFloat)
  {
    this.PU = paramFloat;
    return this;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      h.a(this, paramParcel, paramInt);
      return;
    }
    PolylineOptionsCreator.a(this, paramParcel, paramInt);
  }

  public PolylineOptions zIndex(float paramFloat)
  {
    this.PP = paramFloat;
    return this;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.model.PolylineOptions
 * JD-Core Version:    0.6.0
 */