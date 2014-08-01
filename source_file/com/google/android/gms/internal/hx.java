package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public final class hx extends hm
  implements SafeParcelable
{
  public static final hy CREATOR = new hy();
  private final Bundle Od;
  private final hz Oe;
  private final LatLng Of;
  private final float Og;
  private final LatLngBounds Oh;
  private final String Oi;
  private final Uri Oj;
  private final boolean Ok;
  private final float Ol;
  private final int Om;
  private final long On;
  private final List<ht> Oo;
  private final Map<ht, String> Op;
  private final TimeZone Oq;
  private Locale Or;
  private ic Os;
  private final String uS;
  final int wj;

  hx(int paramInt1, String paramString1, List<ht> paramList, Bundle paramBundle, hz paramhz, LatLng paramLatLng, float paramFloat1, LatLngBounds paramLatLngBounds, String paramString2, Uri paramUri, boolean paramBoolean, float paramFloat2, int paramInt2, long paramLong)
  {
    this.wj = paramInt1;
    this.uS = paramString1;
    this.Oo = Collections.unmodifiableList(paramList);
    this.Od = paramBundle;
    this.Oe = paramhz;
    this.Of = paramLatLng;
    this.Og = paramFloat1;
    this.Oh = paramLatLngBounds;
    this.Oi = paramString2;
    this.Oj = paramUri;
    this.Ok = paramBoolean;
    this.Ol = paramFloat2;
    this.Om = paramInt2;
    this.On = paramLong;
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(ht.aI(str), paramBundle.getString(str));
    }
    this.Op = Collections.unmodifiableMap(localHashMap);
    this.Oq = TimeZone.getTimeZone(this.Oi);
    this.Or = null;
    this.Os = null;
  }

  private void aJ(String paramString)
  {
    if (this.Os != null)
      this.Os.a(new hx.a.a(this.uS, paramString));
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    hx localhx;
    do
    {
      return true;
      if (!(paramObject instanceof hx))
        return false;
      localhx = (hx)paramObject;
    }
    while ((this.uS.equals(localhx.uS)) && (ep.equal(this.Or, localhx.Or)) && (this.On == localhx.On));
    return false;
  }

  public Uri gA()
  {
    aJ("getWebsiteUri");
    return this.Oj;
  }

  public boolean gB()
  {
    aJ("isPermanentlyClosed");
    return this.Ok;
  }

  public int gC()
  {
    aJ("getPriceLevel");
    return this.Om;
  }

  public long gD()
  {
    return this.On;
  }

  public Bundle gE()
  {
    return this.Od;
  }

  public hz gF()
  {
    return this.Oe;
  }

  public String gG()
  {
    return this.Oi;
  }

  public String getId()
  {
    aJ("getId");
    return this.uS;
  }

  public float getRating()
  {
    aJ("getRating");
    return this.Ol;
  }

  public List<ht> gw()
  {
    aJ("getTypes");
    return this.Oo;
  }

  public LatLng gx()
  {
    aJ("getLatLng");
    return this.Of;
  }

  public float gy()
  {
    aJ("getLevelNumber");
    return this.Og;
  }

  public LatLngBounds gz()
  {
    aJ("getViewport");
    return this.Oh;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.uS;
    arrayOfObject[1] = this.Or;
    arrayOfObject[2] = Long.valueOf(this.On);
    return ep.hashCode(arrayOfObject);
  }

  public String toString()
  {
    return ep.e(this).a("id", this.uS).a("localization", this.Oe).a("locale", this.Or).a("latlng", this.Of).a("levelNumber", Float.valueOf(this.Og)).a("viewport", this.Oh).a("timeZone", this.Oi).a("websiteUri", this.Oj).a("isPermanentlyClosed", Boolean.valueOf(this.Ok)).a("priceLevel", Integer.valueOf(this.Om)).a("timestampSecs", Long.valueOf(this.On)).toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hy.a(this, paramParcel, paramInt);
  }

  public static final class a
    implements SafeParcelable
  {
    public static final hw CREATOR = new hw();
    private final String LE;
    private final String Ot;
    private final int Ou;
    private final String mTag;
    final int wj;

    a(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2)
    {
      this.wj = paramInt1;
      this.LE = paramString1;
      this.mTag = paramString2;
      this.Ot = paramString3;
      this.Ou = paramInt2;
    }

    public int describeContents()
    {
      return 0;
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject);
      a locala;
      do
      {
        return true;
        if (!(paramObject instanceof a))
          return false;
        locala = (a)paramObject;
      }
      while ((this.LE.equals(locala.LE)) && (ep.equal(this.mTag, locala.mTag)));
      return false;
    }

    public String gH()
    {
      return this.Ot;
    }

    public int gI()
    {
      return this.Ou;
    }

    public String getTag()
    {
      return this.mTag;
    }

    public String gt()
    {
      return this.LE;
    }

    public int hashCode()
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = this.LE;
      arrayOfObject[1] = this.mTag;
      arrayOfObject[2] = this.Ot;
      arrayOfObject[3] = Integer.valueOf(this.Ou);
      return ep.hashCode(arrayOfObject);
    }

    public String toString()
    {
      return ep.e(this).a("placeId", this.LE).a("tag", this.mTag).a("callingAppPackageName", this.Ot).a("callingAppVersionCode", Integer.valueOf(this.Ou)).toString();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      hw.a(this, paramParcel, paramInt);
    }

    public static class a
    {
      private final String LE;
      private String Ot;
      private int Ou;
      private final String mTag;

      public a(String paramString1, String paramString2)
      {
        this.LE = paramString1;
        this.mTag = paramString2;
      }

      public a aK(String paramString)
      {
        this.Ot = paramString;
        return this;
      }

      public a bv(int paramInt)
      {
        this.Ou = paramInt;
        return this;
      }

      public hx.a gJ()
      {
        return new hx.a(0, this.LE, this.mTag, this.Ot, this.Ou);
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.hx
 * JD-Core Version:    0.6.0
 */