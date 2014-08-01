package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class hj
  implements SafeParcelable, Geofence
{
  public static final hk CREATOR = new hk();
  private final String Hh;
  private final int KU;
  private final short KW;
  private final double KX;
  private final double KY;
  private final float KZ;
  private final int La;
  private final int Lb;
  private final long Lz;
  private final int wj;

  public hj(int paramInt1, String paramString, int paramInt2, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt3, int paramInt4)
  {
    aH(paramString);
    b(paramFloat);
    a(paramDouble1, paramDouble2);
    int i = bn(paramInt2);
    this.wj = paramInt1;
    this.KW = paramShort;
    this.Hh = paramString;
    this.KX = paramDouble1;
    this.KY = paramDouble2;
    this.KZ = paramFloat;
    this.Lz = paramLong;
    this.KU = i;
    this.La = paramInt3;
    this.Lb = paramInt4;
  }

  public hj(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3)
  {
    this(1, paramString, paramInt1, paramShort, paramDouble1, paramDouble2, paramFloat, paramLong, paramInt2, paramInt3);
  }

  private static void a(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 > 90.0D) || (paramDouble1 < -90.0D))
      throw new IllegalArgumentException("invalid latitude: " + paramDouble1);
    if ((paramDouble2 > 180.0D) || (paramDouble2 < -180.0D))
      throw new IllegalArgumentException("invalid longitude: " + paramDouble2);
  }

  private static void aH(String paramString)
  {
    if ((paramString == null) || (paramString.length() > 100))
      throw new IllegalArgumentException("requestId is null or too long: " + paramString);
  }

  private static void b(float paramFloat)
  {
    if (paramFloat <= 0.0F)
      throw new IllegalArgumentException("invalid radius: " + paramFloat);
  }

  private static int bn(int paramInt)
  {
    int i = paramInt & 0x7;
    if (i == 0)
      throw new IllegalArgumentException("No supported transition specified: " + paramInt);
    return i;
  }

  private static String bo(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 1:
    }
    return "CIRCLE";
  }

  public static hj h(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    hj localhj = CREATOR.av(localParcel);
    localParcel.recycle();
    return localhj;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    hj localhj;
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (!(paramObject instanceof hj))
        return false;
      localhj = (hj)paramObject;
      if (this.KZ != localhj.KZ)
        return false;
      if (this.KX != localhj.KX)
        return false;
      if (this.KY != localhj.KY)
        return false;
    }
    while (this.KW == localhj.KW);
    return false;
  }

  public long getExpirationTime()
  {
    return this.Lz;
  }

  public double getLatitude()
  {
    return this.KX;
  }

  public double getLongitude()
  {
    return this.KY;
  }

  public int getNotificationResponsiveness()
  {
    return this.La;
  }

  public String getRequestId()
  {
    return this.Hh;
  }

  public int getVersionCode()
  {
    return this.wj;
  }

  public short gn()
  {
    return this.KW;
  }

  public float go()
  {
    return this.KZ;
  }

  public int gp()
  {
    return this.KU;
  }

  public int gq()
  {
    return this.Lb;
  }

  public int hashCode()
  {
    long l1 = Double.doubleToLongBits(this.KX);
    int i = 31 + (int)(l1 ^ l1 >>> 32);
    long l2 = Double.doubleToLongBits(this.KY);
    return 31 * (31 * (31 * (i * 31 + (int)(l2 ^ l2 >>> 32)) + Float.floatToIntBits(this.KZ)) + this.KW) + this.KU;
  }

  public String toString()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = bo(this.KW);
    arrayOfObject[1] = this.Hh;
    arrayOfObject[2] = Integer.valueOf(this.KU);
    arrayOfObject[3] = Double.valueOf(this.KX);
    arrayOfObject[4] = Double.valueOf(this.KY);
    arrayOfObject[5] = Float.valueOf(this.KZ);
    arrayOfObject[6] = Integer.valueOf(this.La / 1000);
    arrayOfObject[7] = Integer.valueOf(this.Lb);
    arrayOfObject[8] = Long.valueOf(this.Lz);
    return String.format(localLocale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", arrayOfObject);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hk.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hj
 * JD-Core Version:    0.6.0
 */