package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class z
  implements SafeParcelable
{
  public static final aa CREATOR = new aa();
  public final Bundle extras;
  public final long le;
  public final int lf;
  public final List<String> lg;
  public final boolean lh;
  public final boolean li;
  public final String lj;
  public final am lk;
  public final Location ll;
  public final String lm;
  public final int tagForChildDirectedTreatment;
  public final int versionCode;

  z(int paramInt1, long paramLong, Bundle paramBundle, int paramInt2, List<String> paramList, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, am paramam, Location paramLocation, String paramString2)
  {
    this.versionCode = paramInt1;
    this.le = paramLong;
    this.extras = paramBundle;
    this.lf = paramInt2;
    this.lg = paramList;
    this.lh = paramBoolean1;
    this.tagForChildDirectedTreatment = paramInt3;
    this.li = paramBoolean2;
    this.lj = paramString1;
    this.lk = paramam;
    this.ll = paramLocation;
    this.lm = paramString2;
  }

  public z(Context paramContext, aj paramaj)
  {
    this.versionCode = 3;
    Date localDate = paramaj.getBirthday();
    long l;
    List localList;
    label76: AdMobExtras localAdMobExtras;
    if (localDate != null)
    {
      l = localDate.getTime();
      this.le = l;
      this.lm = paramaj.getContentUrl();
      this.lf = paramaj.getGender();
      Set localSet = paramaj.getKeywords();
      if (localSet.isEmpty())
        break label192;
      localList = Collections.unmodifiableList(new ArrayList(localSet));
      this.lg = localList;
      this.lh = paramaj.isTestDevice(paramContext);
      this.tagForChildDirectedTreatment = paramaj.al();
      this.ll = paramaj.getLocation();
      localAdMobExtras = (AdMobExtras)paramaj.getNetworkExtras(AdMobExtras.class);
      if (localAdMobExtras == null)
        break label198;
    }
    label192: label198: for (Bundle localBundle = localAdMobExtras.getExtras(); ; localBundle = null)
    {
      this.extras = localBundle;
      this.li = paramaj.getManualImpressionsEnabled();
      this.lj = paramaj.getPublisherProvidedId();
      SearchAdRequest localSearchAdRequest = paramaj.aj();
      am localam = null;
      if (localSearchAdRequest != null)
        localam = new am(localSearchAdRequest);
      this.lk = localam;
      return;
      l = -1L;
      break;
      localList = null;
      break label76;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    aa.a(this, paramParcel, paramInt);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.z
 * JD-Core Version:    0.6.0
 */