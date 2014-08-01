package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.dr;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CastDevice
  implements SafeParcelable
{
  public static final Parcelable.Creator<CastDevice> CREATOR = new b();
  private String wC;
  String wD;
  private Inet4Address wE;
  private String wF;
  private String wG;
  private String wH;
  private int wI;
  private List<WebImage> wJ;
  private final int wj;

  private CastDevice()
  {
    this(1, null, null, null, null, null, -1, new ArrayList());
  }

  CastDevice(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, List<WebImage> paramList)
  {
    this.wj = paramInt1;
    this.wC = paramString1;
    this.wD = paramString2;
    if (this.wD != null);
    try
    {
      InetAddress localInetAddress = InetAddress.getByName(this.wD);
      if ((localInetAddress instanceof Inet4Address))
        this.wE = ((Inet4Address)localInetAddress);
      this.wF = paramString3;
      this.wG = paramString4;
      this.wH = paramString5;
      this.wI = paramInt2;
      this.wJ = paramList;
      return;
    }
    catch (UnknownHostException localUnknownHostException)
    {
      while (true)
        this.wE = null;
    }
  }

  public static CastDevice getFromBundle(Bundle paramBundle)
  {
    if (paramBundle == null)
      return null;
    paramBundle.setClassLoader(CastDevice.class.getClassLoader());
    return (CastDevice)paramBundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    CastDevice localCastDevice;
    do
      while (true)
      {
        return true;
        if (!(paramObject instanceof CastDevice))
          return false;
        localCastDevice = (CastDevice)paramObject;
        if (getDeviceId() != null)
          break;
        if (localCastDevice.getDeviceId() != null)
          return false;
      }
    while ((dr.a(this.wC, localCastDevice.wC)) && (dr.a(this.wE, localCastDevice.wE)) && (dr.a(this.wG, localCastDevice.wG)) && (dr.a(this.wF, localCastDevice.wF)) && (dr.a(this.wH, localCastDevice.wH)) && (this.wI == localCastDevice.wI) && (dr.a(this.wJ, localCastDevice.wJ)));
    return false;
  }

  public String getDeviceId()
  {
    return this.wC;
  }

  public String getDeviceVersion()
  {
    return this.wH;
  }

  public String getFriendlyName()
  {
    return this.wF;
  }

  public WebImage getIcon(int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    if (this.wJ.isEmpty())
      return null;
    if ((paramInt1 <= 0) || (paramInt2 <= 0))
      return (WebImage)this.wJ.get(0);
    Iterator localIterator = this.wJ.iterator();
    Object localObject2 = null;
    Object localObject3;
    int i;
    int j;
    Object localObject4;
    if (localIterator.hasNext())
    {
      localObject3 = (WebImage)localIterator.next();
      i = ((WebImage)localObject3).getWidth();
      j = ((WebImage)localObject3).getHeight();
      if ((i >= paramInt1) && (j >= paramInt2))
      {
        if ((localObject2 != null) && ((((WebImage)localObject2).getWidth() <= i) || (((WebImage)localObject2).getHeight() <= j)))
          break label223;
        Object localObject5 = localObject1;
        localObject4 = localObject3;
        localObject3 = localObject5;
      }
    }
    while (true)
    {
      localObject2 = localObject4;
      localObject1 = localObject3;
      break;
      if ((i < paramInt1) && (j < paramInt2) && ((localObject1 == null) || ((localObject1.getWidth() < i) && (localObject1.getHeight() < j))))
      {
        localObject4 = localObject2;
        continue;
        if (localObject2 != null);
        while (true)
        {
          return localObject2;
          if (localObject1 != null)
          {
            localObject2 = localObject1;
            continue;
          }
          localObject2 = (WebImage)this.wJ.get(0);
        }
      }
      label223: localObject3 = localObject1;
      localObject4 = localObject2;
    }
  }

  public List<WebImage> getIcons()
  {
    return Collections.unmodifiableList(this.wJ);
  }

  public Inet4Address getIpAddress()
  {
    return this.wE;
  }

  public String getModelName()
  {
    return this.wG;
  }

  public int getServicePort()
  {
    return this.wI;
  }

  int getVersionCode()
  {
    return this.wj;
  }

  public boolean hasIcons()
  {
    return !this.wJ.isEmpty();
  }

  public int hashCode()
  {
    if (this.wC == null)
      return 0;
    return this.wC.hashCode();
  }

  public boolean isSameDevice(CastDevice paramCastDevice)
  {
    if (paramCastDevice == null);
    while (true)
    {
      return false;
      if (getDeviceId() != null)
        break;
      if (paramCastDevice.getDeviceId() == null)
        return true;
    }
    return dr.a(getDeviceId(), paramCastDevice.getDeviceId());
  }

  public void putInBundle(Bundle paramBundle)
  {
    if (paramBundle == null)
      return;
    paramBundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.wF;
    arrayOfObject[1] = this.wC;
    return String.format("\"%s\" (%s)", arrayOfObject);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.cast.CastDevice
 * JD-Core Version:    0.6.0
 */