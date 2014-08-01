package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import java.util.Map;

public final class be extends bf.a
{
  private Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> mQ;

  private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> bg n(String paramString)
    throws RemoteException
  {
    Class localClass;
    try
    {
      localClass = Class.forName(paramString, false, be.class.getClassLoader());
      if (!MediationAdapter.class.isAssignableFrom(localClass))
      {
        da.w("Could not instantiate mediation adapter: " + paramString + ".");
        throw new RemoteException();
      }
    }
    catch (Throwable localThrowable)
    {
      da.w("Could not instantiate mediation adapter: " + paramString + ". " + localThrowable.getMessage());
      throw new RemoteException();
    }
    MediationAdapter localMediationAdapter = (MediationAdapter)localClass.newInstance();
    bi localbi = new bi(localMediationAdapter, (com.google.ads.mediation.NetworkExtras)this.mQ.get(localMediationAdapter.getAdditionalParametersType()));
    return localbi;
  }

  public void c(Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> paramMap)
  {
    this.mQ = paramMap;
  }

  public bg m(String paramString)
    throws RemoteException
  {
    return n(paramString);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.be
 * JD-Core Version:    0.6.0
 */