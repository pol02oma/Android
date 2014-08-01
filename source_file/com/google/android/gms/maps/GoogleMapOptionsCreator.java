package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.maps.model.CameraPosition;

public class GoogleMapOptionsCreator
  implements Parcelable.Creator<GoogleMapOptions>
{
  public static final int CONTENT_DESCRIPTION;

  static void a(GoogleMapOptions paramGoogleMapOptions, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramGoogleMapOptions.getVersionCode());
    b.a(paramParcel, 2, paramGoogleMapOptions.gN());
    b.a(paramParcel, 3, paramGoogleMapOptions.gO());
    b.c(paramParcel, 4, paramGoogleMapOptions.getMapType());
    b.a(paramParcel, 5, paramGoogleMapOptions.getCamera(), paramInt, false);
    b.a(paramParcel, 6, paramGoogleMapOptions.gP());
    b.a(paramParcel, 7, paramGoogleMapOptions.gQ());
    b.a(paramParcel, 8, paramGoogleMapOptions.gR());
    b.a(paramParcel, 9, paramGoogleMapOptions.gS());
    b.a(paramParcel, 10, paramGoogleMapOptions.gT());
    b.a(paramParcel, 11, paramGoogleMapOptions.gU());
    b.D(paramParcel, i);
  }

  public GoogleMapOptions createFromParcel(Parcel paramParcel)
  {
    byte b1 = 0;
    int i = a.o(paramParcel);
    CameraPosition localCameraPosition = null;
    byte b2 = 0;
    byte b3 = 0;
    byte b4 = 0;
    byte b5 = 0;
    byte b6 = 0;
    int j = 0;
    byte b7 = 0;
    byte b8 = 0;
    int k = 0;
    while (paramParcel.dataPosition() < i)
    {
      int m = a.n(paramParcel);
      switch (a.S(m))
      {
      default:
        a.b(paramParcel, m);
        break;
      case 1:
        k = a.g(paramParcel, m);
        break;
      case 2:
        b8 = a.e(paramParcel, m);
        break;
      case 3:
        b7 = a.e(paramParcel, m);
        break;
      case 4:
        j = a.g(paramParcel, m);
        break;
      case 5:
        localCameraPosition = (CameraPosition)a.a(paramParcel, m, CameraPosition.CREATOR);
        break;
      case 6:
        b6 = a.e(paramParcel, m);
        break;
      case 7:
        b5 = a.e(paramParcel, m);
        break;
      case 8:
        b4 = a.e(paramParcel, m);
        break;
      case 9:
        b3 = a.e(paramParcel, m);
        break;
      case 10:
        b2 = a.e(paramParcel, m);
        break;
      case 11:
        b1 = a.e(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != i)
      throw new a.a("Overread allowed size end=" + i, paramParcel);
    return new GoogleMapOptions(k, b8, b7, j, localCameraPosition, b6, b5, b4, b3, b2, b1);
  }

  public GoogleMapOptions[] newArray(int paramInt)
  {
    return new GoogleMapOptions[paramInt];
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.maps.GoogleMapOptionsCreator
 * JD-Core Version:    0.6.0
 */