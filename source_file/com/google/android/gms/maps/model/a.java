package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
{
  static void a(CameraPosition paramCameraPosition, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramCameraPosition.getVersionCode());
    b.a(paramParcel, 2, paramCameraPosition.target, paramInt, false);
    b.a(paramParcel, 3, paramCameraPosition.zoom);
    b.a(paramParcel, 4, paramCameraPosition.tilt);
    b.a(paramParcel, 5, paramCameraPosition.bearing);
    b.D(paramParcel, i);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.model.a
 * JD-Core Version:    0.6.0
 */