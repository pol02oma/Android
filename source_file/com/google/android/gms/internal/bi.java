package com.google.android.gms.internal;

import TSERVER_PARAMETERS;;
import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobServerParameters;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public final class bi<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends bg.a
{
  private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mR;
  private final NETWORK_EXTRAS mS;

  public bi(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> paramMediationAdapter, NETWORK_EXTRAS paramNETWORK_EXTRAS)
  {
    this.mR = paramMediationAdapter;
    this.mS = paramNETWORK_EXTRAS;
  }

  private SERVER_PARAMETERS a(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    HashMap localHashMap2;
    if (paramString1 != null)
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString1);
        localHashMap2 = new HashMap(localJSONObject.length());
        Iterator localIterator = localJSONObject.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localHashMap2.put(str, localJSONObject.getString(str));
        }
      }
      catch (Throwable localThrowable)
      {
        da.b("Could not get MediationServerParameters.", localThrowable);
        throw new RemoteException();
      }
    Object localObject2;
    HashMap localHashMap1;
    for (Object localObject1 = localHashMap2; ; localObject1 = localHashMap1)
    {
      Class localClass = this.mR.getServerParametersType();
      localObject2 = null;
      if (localClass != null)
      {
        MediationServerParameters localMediationServerParameters = (MediationServerParameters)localClass.newInstance();
        localMediationServerParameters.load((Map)localObject1);
        localObject2 = localMediationServerParameters;
      }
      if (!(localObject2 instanceof AdMobServerParameters))
        break;
      AdMobServerParameters localAdMobServerParameters = (AdMobServerParameters)localObject2;
      localAdMobServerParameters.adJson = paramString2;
      localAdMobServerParameters.tagForChildDirectedTreatment = paramInt;
      return localObject2;
      localHashMap1 = new HashMap(0);
    }
    return (TSERVER_PARAMETERS)localObject2;
  }

  public void a(b paramb, ab paramab, z paramz, String paramString, bh parambh)
    throws RemoteException
  {
    a(paramb, paramab, paramz, paramString, null, parambh);
  }

  public void a(b paramb, ab paramab, z paramz, String paramString1, String paramString2, bh parambh)
    throws RemoteException
  {
    if (!(this.mR instanceof MediationBannerAdapter))
    {
      da.w("MediationAdapter is not a MediationBannerAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    da.s("Requesting banner ad from adapter.");
    try
    {
      ((MediationBannerAdapter)this.mR).requestBannerAd(new bj(parambh), (Activity)c.b(paramb), a(paramString1, paramz.tagForChildDirectedTreatment, paramString2), bk.b(paramab), bk.e(paramz), this.mS);
      return;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not request banner ad from adapter.", localThrowable);
    }
    throw new RemoteException();
  }

  public void a(b paramb, z paramz, String paramString, bh parambh)
    throws RemoteException
  {
    a(paramb, paramz, paramString, null, parambh);
  }

  public void a(b paramb, z paramz, String paramString1, String paramString2, bh parambh)
    throws RemoteException
  {
    if (!(this.mR instanceof MediationInterstitialAdapter))
    {
      da.w("MediationAdapter is not a MediationInterstitialAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    da.s("Requesting interstitial ad from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.mR).requestInterstitialAd(new bj(parambh), (Activity)c.b(paramb), a(paramString1, paramz.tagForChildDirectedTreatment, paramString2), bk.e(paramz), this.mS);
      return;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not request interstitial ad from adapter.", localThrowable);
    }
    throw new RemoteException();
  }

  public void destroy()
    throws RemoteException
  {
    try
    {
      this.mR.destroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not destroy adapter.", localThrowable);
    }
    throw new RemoteException();
  }

  public b getView()
    throws RemoteException
  {
    if (!(this.mR instanceof MediationBannerAdapter))
    {
      da.w("MediationAdapter is not a MediationBannerAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      b localb = c.h(((MediationBannerAdapter)this.mR).getBannerView());
      return localb;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not get banner view from adapter.", localThrowable);
    }
    throw new RemoteException();
  }

  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.mR instanceof MediationInterstitialAdapter))
    {
      da.w("MediationAdapter is not a MediationInterstitialAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    da.s("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.mR).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not show interstitial from adapter.", localThrowable);
    }
    throw new RemoteException();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bi
 * JD-Core Version:    0.6.0
 */