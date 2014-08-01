package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.r;

public final class CameraPosition
  implements SafeParcelable
{
  public static final CameraPositionCreator CREATOR = new CameraPositionCreator();
  public final float bearing;
  public final LatLng target;
  public final float tilt;
  private final int wj;
  public final float zoom;

  CameraPosition(int paramInt, LatLng paramLatLng, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    er.b(paramLatLng, "null camera target");
    if ((0.0F <= paramFloat2) && (paramFloat2 <= 90.0F));
    for (boolean bool = true; ; bool = false)
    {
      er.b(bool, "Tilt needs to be between 0 and 90 inclusive");
      this.wj = paramInt;
      this.target = paramLatLng;
      this.zoom = paramFloat1;
      this.tilt = (paramFloat2 + 0.0F);
      if (paramFloat3 <= 0.0D)
        paramFloat3 = 360.0F + paramFloat3 % 360.0F;
      this.bearing = (paramFloat3 % 360.0F);
      return;
    }
  }

  public CameraPosition(LatLng paramLatLng, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this(1, paramLatLng, paramFloat1, paramFloat2, paramFloat3);
  }

  public static Builder builder()
  {
    return new Builder();
  }

  public static Builder builder(CameraPosition paramCameraPosition)
  {
    return new Builder(paramCameraPosition);
  }

  public static CameraPosition createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null)
      return null;
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    float f1;
    if (localTypedArray.hasValue(2))
      f1 = localTypedArray.getFloat(2, 0.0F);
    while (true)
    {
      float f2;
      if (localTypedArray.hasValue(3))
        f2 = localTypedArray.getFloat(3, 0.0F);
      while (true)
      {
        LatLng localLatLng = new LatLng(f1, f2);
        Builder localBuilder = builder();
        localBuilder.target(localLatLng);
        if (localTypedArray.hasValue(5))
          localBuilder.zoom(localTypedArray.getFloat(5, 0.0F));
        if (localTypedArray.hasValue(1))
          localBuilder.bearing(localTypedArray.getFloat(1, 0.0F));
        if (localTypedArray.hasValue(4))
          localBuilder.tilt(localTypedArray.getFloat(4, 0.0F));
        return localBuilder.build();
        f2 = 0.0F;
      }
      f1 = 0.0F;
    }
  }

  public static final CameraPosition fromLatLngZoom(LatLng paramLatLng, float paramFloat)
  {
    return new CameraPosition(paramLatLng, paramFloat, 0.0F, 0.0F);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    CameraPosition localCameraPosition;
    do
    {
      return true;
      if (!(paramObject instanceof CameraPosition))
        return false;
      localCameraPosition = (CameraPosition)paramObject;
    }
    while ((this.target.equals(localCameraPosition.target)) && (Float.floatToIntBits(this.zoom) == Float.floatToIntBits(localCameraPosition.zoom)) && (Float.floatToIntBits(this.tilt) == Float.floatToIntBits(localCameraPosition.tilt)) && (Float.floatToIntBits(this.bearing) == Float.floatToIntBits(localCameraPosition.bearing)));
    return false;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.target;
    arrayOfObject[1] = Float.valueOf(this.zoom);
    arrayOfObject[2] = Float.valueOf(this.tilt);
    arrayOfObject[3] = Float.valueOf(this.bearing);
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("target", this.target).a("zoom", Float.valueOf(this.zoom)).a("tilt", Float.valueOf(this.tilt)).a("bearing", Float.valueOf(this.bearing)).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      a.a(this, paramParcel, paramInt);
      return;
    }
    CameraPositionCreator.a(this, paramParcel, paramInt);
  }

  public static final class Builder
  {
    private LatLng PF;
    private float PG;
    private float PH;
    private float PI;

    public Builder()
    {
    }

    public Builder(CameraPosition paramCameraPosition)
    {
      this.PF = paramCameraPosition.target;
      this.PG = paramCameraPosition.zoom;
      this.PH = paramCameraPosition.tilt;
      this.PI = paramCameraPosition.bearing;
    }

    public Builder bearing(float paramFloat)
    {
      this.PI = paramFloat;
      return this;
    }

    public CameraPosition build()
    {
      return new CameraPosition(this.PF, this.PG, this.PH, this.PI);
    }

    public Builder target(LatLng paramLatLng)
    {
      this.PF = paramLatLng;
      return this;
    }

    public Builder tilt(float paramFloat)
    {
      this.PH = paramFloat;
      return this;
    }

    public Builder zoom(float paramFloat)
    {
      this.PG = paramFloat;
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.model.CameraPosition
 * JD-Core Version:    0.6.0
 */