package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.util.List;
import org.json.JSONException;

public final class bz extends ct
  implements ca.a, de.a
{
  private final bf kH;
  private final Context mContext;
  private final Object mg = new Object();
  private ay mh;
  private final by.a nM;
  private final Object nN = new Object();
  private final cd.a nO;
  private final l nP;
  private ct nQ;
  private cf nR;
  private boolean nS = false;
  private aw nT;
  private bc nU;
  private final dd ng;

  public bz(Context paramContext, cd.a parama, l paraml, dd paramdd, bf parambf, by.a parama1)
  {
    this.kH = parambf;
    this.nM = parama1;
    this.ng = paramdd;
    this.mContext = paramContext;
    this.nO = parama;
    this.nP = paraml;
  }

  private ab a(cd paramcd)
    throws bz.a
  {
    if (this.nR.on == null)
      throw new a("The ad response must specify one of the supported ad sizes.", 0);
    String[] arrayOfString = this.nR.on.split("x");
    if (arrayOfString.length != 2)
      throw new a("Could not parse the ad size from the ad response: " + this.nR.on, 0);
    while (true)
    {
      int m;
      ab localab;
      try
      {
        int i = Integer.parseInt(arrayOfString[0]);
        int j = Integer.parseInt(arrayOfString[1]);
        ab[] arrayOfab = paramcd.kQ.lp;
        int k = arrayOfab.length;
        m = 0;
        if (m >= k)
          break;
        localab = arrayOfab[m];
        float f = this.mContext.getResources().getDisplayMetrics().density;
        if (localab.width == -1)
        {
          n = (int)(localab.widthPixels / f);
          if (localab.height != -2)
            break label255;
          i1 = (int)(localab.heightPixels / f);
          if ((i != n) || (j != i1))
            break label265;
          return new ab(localab, paramcd.kQ.lp);
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new a("Could not parse the ad size from the ad response: " + this.nR.on, 0);
      }
      int n = localab.width;
      continue;
      label255: int i1 = localab.height;
      continue;
      label265: m++;
    }
    throw new a("The ad size from the ad response was not one of the requested sizes: " + this.nR.on, 0);
  }

  private void a(cd paramcd, long paramLong)
    throws bz.a
  {
    synchronized (this.nN)
    {
      this.nT = new aw(this.mContext, paramcd, this.kH, this.mh);
      this.nU = this.nT.a(paramLong, 60000L);
      switch (this.nU.mL)
      {
      default:
        throw new a("Unexpected mediation result: " + this.nU.mL, 0);
      case 1:
      case 0:
      }
    }
    throw new a("No fill from any mediation ad networks.", 3);
  }

  private void aC()
    throws bz.a
  {
    if (this.nR.errorCode == -3);
    do
    {
      return;
      if (!TextUtils.isEmpty(this.nR.oi))
        continue;
      throw new a("No fill from ad server.", 3);
    }
    while (!this.nR.ok);
    try
    {
      this.mh = new ay(this.nR.oi);
      return;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new a("Could not parse mediation config: " + this.nR.oi, 0);
  }

  private void b(long paramLong)
    throws bz.a
  {
    cz.pT.post(new Runnable()
    {
      public void run()
      {
        while (true)
        {
          synchronized (bz.a(bz.this))
          {
            if (bz.c(bz.this).errorCode != -2)
              return;
            bz.d(bz.this).bb().a(bz.this);
            if (bz.c(bz.this).errorCode == -3)
            {
              da.v("Loading URL in WebView: " + bz.c(bz.this).nw);
              bz.d(bz.this).loadUrl(bz.c(bz.this).nw);
              return;
            }
          }
          da.v("Loading HTML in WebView.");
          bz.d(bz.this).loadDataWithBaseURL(cv.p(bz.c(bz.this).nw), bz.c(bz.this).oi, "text/html", "UTF-8", null);
        }
      }
    });
    d(paramLong);
  }

  private void c(long paramLong)
    throws bz.a
  {
    do
    {
      if (e(paramLong))
        continue;
      throw new a("Timed out waiting for ad response.", 2);
    }
    while (this.nR == null);
    synchronized (this.nN)
    {
      this.nQ = null;
      if ((this.nR.errorCode != -2) && (this.nR.errorCode != -3))
        throw new a("There was a problem getting an ad response. ErrorCode: " + this.nR.errorCode, this.nR.errorCode);
    }
  }

  private void d(long paramLong)
    throws bz.a
  {
    do
    {
      if (e(paramLong))
        continue;
      throw new a("Timed out waiting for WebView to finish loading.", 2);
    }
    while (!this.nS);
  }

  private boolean e(long paramLong)
    throws bz.a
  {
    long l = 60000L - (SystemClock.elapsedRealtime() - paramLong);
    if (l <= 0L)
      return false;
    try
    {
      this.mg.wait(l);
      return true;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    throw new a("Ad request cancelled.", -1);
  }

  public void a(cf paramcf)
  {
    synchronized (this.mg)
    {
      da.s("Received ad response.");
      this.nR = paramcf;
      this.mg.notify();
      return;
    }
  }

  public void a(dd paramdd)
  {
    synchronized (this.mg)
    {
      da.s("WebView finished loading.");
      this.nS = true;
      this.mg.notify();
      return;
    }
  }

  public void aB()
  {
    while (true)
    {
      ab localab1;
      long l1;
      synchronized (this.mg)
      {
        da.s("AdLoaderBackgroundTask started.");
        String str1 = this.nP.y().a(this.mContext);
        cd localcd = new cd(this.nO, str1);
        localab1 = null;
        int i = -2;
        l1 = -1L;
        long l4;
        try
        {
          l4 = SystemClock.elapsedRealtime();
          ct localct = ca.a(this.mContext, localcd, this);
          synchronized (this.nN)
          {
            this.nQ = localct;
            if (this.nQ != null)
              continue;
            throw new a("Could not start the ad request service.", 0);
          }
        }
        catch (a locala)
        {
          i = locala.getErrorCode();
          if (i == 3)
            continue;
        }
        if (i == -1)
        {
          da.u(locala.getMessage());
          this.nR = new cf(i);
          cz.pT.post(new Runnable()
          {
            public void run()
            {
              bz.this.onStop();
            }
          });
          l2 = l1;
          localab2 = localab1;
          z localz = localcd.oc;
          dd localdd = this.ng;
          List localList1 = this.nR.mt;
          List localList2 = this.nR.mu;
          List localList3 = this.nR.om;
          int j = this.nR.orientation;
          long l3 = this.nR.mx;
          String str2 = localcd.of;
          boolean bool = this.nR.ok;
          if (this.nU == null)
            break label498;
          localax = this.nU.mM;
          if (this.nU == null)
            break label504;
          localbg = this.nU.mN;
          if (this.nU == null)
            break label510;
          str3 = this.nU.mO;
          ay localay = this.mh;
          if (this.nU == null)
            break label516;
          localba = this.nU.mP;
          cn localcn = new cn(localz, localdd, localList1, i, localList2, localList3, j, l3, str2, bool, localax, localbg, str3, localay, localba, this.nR.ol, localab2, this.nR.oj, l2, this.nR.oo);
          cz.pT.post(new Runnable(localcn)
          {
            public void run()
            {
              synchronized (bz.a(bz.this))
              {
                bz.b(bz.this).a(this.nW);
                return;
              }
            }
          });
          return;
          monitorexit;
          c(l4);
          l1 = SystemClock.elapsedRealtime();
          aC();
          ab[] arrayOfab = localcd.kQ.lp;
          localab1 = null;
          if (arrayOfab == null)
            continue;
          localab1 = a(localcd);
          if (!this.nR.ok)
            continue;
          a(localcd, l4);
          break label522;
          b(l4);
        }
      }
      da.w(locala.getMessage());
      continue;
      label498: ax localax = null;
      continue;
      label504: bg localbg = null;
      continue;
      label510: String str3 = null;
      continue;
      label516: ba localba = null;
      continue;
      label522: long l2 = l1;
      ab localab2 = localab1;
    }
  }

  public void onStop()
  {
    synchronized (this.nN)
    {
      if (this.nQ != null)
        this.nQ.cancel();
      this.ng.stopLoading();
      cv.a(this.ng);
      if (this.nT != null)
        this.nT.cancel();
      return;
    }
  }

  private static final class a extends Exception
  {
    private final int nX;

    public a(String paramString, int paramInt)
    {
      super();
      this.nX = paramInt;
    }

    public int getErrorCode()
    {
      return this.nX;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.bz
 * JD-Core Version:    0.6.0
 */