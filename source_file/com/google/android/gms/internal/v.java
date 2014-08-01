package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ViewSwitcher;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import java.util.HashSet;

public final class v extends ag.a
  implements ap, az, br, bu, by.a, u
{
  private final bf kH;
  private final a kI;
  private final w kJ;

  public v(Context paramContext, ab paramab, String paramString, bf parambf, db paramdb)
  {
    this.kI = new a(paramContext, paramab, paramString, paramdb);
    this.kH = parambf;
    this.kJ = new w(this);
    da.u("Use AdRequest.Builder.addTestDevice(\"" + cz.l(paramContext) + "\") to get test ads on this device.");
    cv.i(paramContext);
  }

  private void a(int paramInt)
  {
    da.w("Failed to load ad: " + paramInt);
    if (this.kI.kO != null);
    try
    {
      this.kI.kO.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdFailedToLoad().", localRemoteException);
    }
  }

  private void aa()
  {
    da.u("Ad closing.");
    if (this.kI.kO != null);
    try
    {
      this.kI.kO.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdClosed().", localRemoteException);
    }
  }

  private void ab()
  {
    da.u("Ad leaving application.");
    if (this.kI.kO != null);
    try
    {
      this.kI.kO.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdLeftApplication().", localRemoteException);
    }
  }

  private void ac()
  {
    da.u("Ad opening.");
    if (this.kI.kO != null);
    try
    {
      this.kI.kO.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdOpened().", localRemoteException);
    }
  }

  private void ad()
  {
    da.u("Ad finished loading.");
    if (this.kI.kO != null);
    try
    {
      this.kI.kO.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdLoaded().", localRemoteException);
    }
  }

  private boolean ae()
  {
    int i = 1;
    if (!cv.a(this.kI.kL.getPackageManager(), this.kI.kL.getPackageName(), "android.permission.INTERNET"))
    {
      if (!this.kI.kQ.lo)
        cz.a(this.kI.kK, this.kI.kQ, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
      i = 0;
    }
    if (!cv.h(this.kI.kL))
    {
      if (!this.kI.kQ.lo)
        cz.a(this.kI.kK, this.kI.kQ, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
      i = 0;
    }
    if ((i == 0) && (!this.kI.kQ.lo))
      this.kI.kK.setVisibility(0);
    return i;
  }

  private void af()
  {
    if (this.kI.kR == null)
      da.w("Ad state was null when trying to ping click URLs.");
    do
    {
      return;
      da.s("Pinging click URLs.");
      this.kI.kS.aK();
      if (this.kI.kR.mt == null)
        continue;
      cv.a(this.kI.kL, this.kI.kN.pU, this.kI.kR.mt);
    }
    while ((this.kI.kR.pf == null) || (this.kI.kR.pf.mt == null));
    bd.a(this.kI.kL, this.kI.kN.pU, this.kI.kR, this.kI.adUnitId, false, this.kI.kR.pf.mt);
  }

  private void ag()
  {
    if (this.kI.kR != null)
    {
      this.kI.kR.nu.destroy();
      this.kI.kR = null;
    }
  }

  private void b(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-2, -2);
    this.kI.kK.addView(paramView, localLayoutParams);
  }

  private void b(boolean paramBoolean)
  {
    if (this.kI.kR == null)
      da.w("Ad state was null when trying to ping impression URLs.");
    do
    {
      return;
      da.s("Pinging Impression URLs.");
      this.kI.kS.aJ();
      if (this.kI.kR.mu != null)
        cv.a(this.kI.kL, this.kI.kN.pU, this.kI.kR.mu);
      if ((this.kI.kR.pf == null) || (this.kI.kR.pf.mu == null))
        continue;
      bd.a(this.kI.kL, this.kI.kN.pU, this.kI.kR, this.kI.adUnitId, paramBoolean, this.kI.kR.pf.mu);
    }
    while ((this.kI.kR.mM == null) || (this.kI.kR.mM.mp == null));
    bd.a(this.kI.kL, this.kI.kN.pU, this.kI.kR, this.kI.adUnitId, paramBoolean, this.kI.kR.mM.mp);
  }

  // ERROR //
  private boolean b(cn paramcn)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 291	com/google/android/gms/internal/cn:ok	Z
    //   4: ifeq +193 -> 197
    //   7: aload_1
    //   8: getfield 295	com/google/android/gms/internal/cn:mN	Lcom/google/android/gms/internal/bg;
    //   11: invokeinterface 301 1 0
    //   16: invokestatic 306	com/google/android/gms/dynamic/c:b	(Lcom/google/android/gms/dynamic/b;)Ljava/lang/Object;
    //   19: checkcast 308	android/view/View
    //   22: astore 5
    //   24: aload_0
    //   25: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   28: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   31: invokevirtual 312	android/widget/ViewSwitcher:getNextView	()Landroid/view/View;
    //   34: astore 6
    //   36: aload 6
    //   38: ifnull +15 -> 53
    //   41: aload_0
    //   42: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   45: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   48: aload 6
    //   50: invokevirtual 315	android/widget/ViewSwitcher:removeView	(Landroid/view/View;)V
    //   53: aload_0
    //   54: aload 5
    //   56: invokespecial 317	com/google/android/gms/internal/v:b	(Landroid/view/View;)V
    //   59: aload_0
    //   60: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   63: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   66: invokevirtual 321	android/widget/ViewSwitcher:getChildCount	()I
    //   69: iconst_1
    //   70: if_icmple +13 -> 83
    //   73: aload_0
    //   74: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   77: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   80: invokevirtual 324	android/widget/ViewSwitcher:showNext	()V
    //   83: aload_0
    //   84: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   87: getfield 194	com/google/android/gms/internal/v$a:kR	Lcom/google/android/gms/internal/cn;
    //   90: ifnull +70 -> 160
    //   93: aload_0
    //   94: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   97: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   100: invokevirtual 312	android/widget/ViewSwitcher:getNextView	()Landroid/view/View;
    //   103: astore_2
    //   104: aload_2
    //   105: instanceof 251
    //   108: ifeq +162 -> 270
    //   111: aload_2
    //   112: checkcast 251	com/google/android/gms/internal/dd
    //   115: aload_0
    //   116: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   119: getfield 141	com/google/android/gms/internal/v$a:kL	Landroid/content/Context;
    //   122: aload_0
    //   123: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   126: getfield 159	com/google/android/gms/internal/v$a:kQ	Lcom/google/android/gms/internal/ab;
    //   129: invokevirtual 327	com/google/android/gms/internal/dd:a	(Landroid/content/Context;Lcom/google/android/gms/internal/ab;)V
    //   132: aload_0
    //   133: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   136: getfield 194	com/google/android/gms/internal/v$a:kR	Lcom/google/android/gms/internal/cn;
    //   139: getfield 295	com/google/android/gms/internal/cn:mN	Lcom/google/android/gms/internal/bg;
    //   142: ifnull +18 -> 160
    //   145: aload_0
    //   146: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   149: getfield 194	com/google/android/gms/internal/v$a:kR	Lcom/google/android/gms/internal/cn;
    //   152: getfield 295	com/google/android/gms/internal/cn:mN	Lcom/google/android/gms/internal/bg;
    //   155: invokeinterface 328 1 0
    //   160: aload_0
    //   161: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   164: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   167: iconst_0
    //   168: invokevirtual 189	android/widget/ViewSwitcher:setVisibility	(I)V
    //   171: iconst_1
    //   172: ireturn
    //   173: astore 4
    //   175: ldc_w 330
    //   178: aload 4
    //   180: invokestatic 103	com/google/android/gms/internal/da:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   183: iconst_0
    //   184: ireturn
    //   185: astore 7
    //   187: ldc_w 332
    //   190: aload 7
    //   192: invokestatic 103	com/google/android/gms/internal/da:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   195: iconst_0
    //   196: ireturn
    //   197: aload_1
    //   198: getfield 335	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   201: ifnull -142 -> 59
    //   204: aload_1
    //   205: getfield 249	com/google/android/gms/internal/cn:nu	Lcom/google/android/gms/internal/dd;
    //   208: aload_1
    //   209: getfield 335	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   212: invokevirtual 338	com/google/android/gms/internal/dd:a	(Lcom/google/android/gms/internal/ab;)V
    //   215: aload_0
    //   216: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   219: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   222: invokevirtual 341	android/widget/ViewSwitcher:removeAllViews	()V
    //   225: aload_0
    //   226: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   229: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   232: aload_1
    //   233: getfield 335	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   236: getfield 345	com/google/android/gms/internal/ab:widthPixels	I
    //   239: invokevirtual 348	android/widget/ViewSwitcher:setMinimumWidth	(I)V
    //   242: aload_0
    //   243: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   246: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   249: aload_1
    //   250: getfield 335	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   253: getfield 351	com/google/android/gms/internal/ab:heightPixels	I
    //   256: invokevirtual 354	android/widget/ViewSwitcher:setMinimumHeight	(I)V
    //   259: aload_0
    //   260: aload_1
    //   261: getfield 249	com/google/android/gms/internal/cn:nu	Lcom/google/android/gms/internal/dd;
    //   264: invokespecial 317	com/google/android/gms/internal/v:b	(Landroid/view/View;)V
    //   267: goto -208 -> 59
    //   270: aload_2
    //   271: ifnull -139 -> 132
    //   274: aload_0
    //   275: getfield 34	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   278: getfield 169	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   281: aload_2
    //   282: invokevirtual 315	android/widget/ViewSwitcher:removeView	(Landroid/view/View;)V
    //   285: goto -153 -> 132
    //   288: astore_3
    //   289: ldc_w 356
    //   292: invokestatic 88	com/google/android/gms/internal/da:w	(Ljava/lang/String;)V
    //   295: goto -135 -> 160
    //
    // Exception table:
    //   from	to	target	type
    //   7	24	173	android/os/RemoteException
    //   53	59	185	java/lang/Throwable
    //   145	160	288	android/os/RemoteException
  }

  private cd.a c(z paramz)
  {
    ApplicationInfo localApplicationInfo = this.kI.kL.getApplicationInfo();
    try
    {
      PackageInfo localPackageInfo2 = this.kI.kL.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0);
      localPackageInfo1 = localPackageInfo2;
      if ((!this.kI.kQ.lo) && (this.kI.kK.getParent() != null))
      {
        int[] arrayOfInt = new int[2];
        this.kI.kK.getLocationOnScreen(arrayOfInt);
        int i = arrayOfInt[0];
        int j = arrayOfInt[1];
        DisplayMetrics localDisplayMetrics = this.kI.kL.getResources().getDisplayMetrics();
        int k = this.kI.kK.getWidth();
        int m = this.kI.kK.getHeight();
        if ((this.kI.kK.isShown()) && (i + k > 0) && (j + m > 0) && (i <= localDisplayMetrics.widthPixels) && (j <= localDisplayMetrics.heightPixels))
        {
          n = 1;
          localBundle1 = new Bundle(5);
          localBundle1.putInt("x", i);
          localBundle1.putInt("y", j);
          localBundle1.putInt("width", k);
          localBundle1.putInt("height", m);
          localBundle1.putInt("visible", n);
          String str = cp.aP();
          this.kI.kS = new co(str, this.kI.adUnitId);
          this.kI.kS.f(paramz);
          Bundle localBundle2 = cp.a(this.kI, str, this.kI.kL);
          return new cd.a(localBundle1, paramz, this.kI.kQ, this.kI.adUnitId, localApplicationInfo, localPackageInfo1, str, cp.pu, this.kI.kN, localBundle2);
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        PackageInfo localPackageInfo1 = null;
        continue;
        int n = 0;
        continue;
        Bundle localBundle1 = null;
      }
    }
  }

  public void O()
  {
    af();
  }

  public b P()
  {
    er.ac("getAdFrame must be called on the main UI thread.");
    return c.h(this.kI.kK);
  }

  public ab Q()
  {
    er.ac("getAdSize must be called on the main UI thread.");
    return this.kI.kQ;
  }

  public void R()
  {
    ab();
  }

  public void S()
  {
    if (this.kI.kQ.lo)
      ag();
    aa();
    this.kI.kS.aL();
  }

  public void T()
  {
    if (this.kI.kQ.lo)
      b(false);
    ac();
  }

  public void U()
  {
    O();
  }

  public void V()
  {
    S();
  }

  public void W()
  {
    R();
  }

  public void X()
  {
    T();
  }

  public void Y()
  {
    if (this.kI.kR != null)
      da.w("Mediation adapter " + this.kI.kR.mO + " refreshed, but mediation adapters should never refresh.");
    b(true);
    ad();
  }

  public void Z()
  {
    er.ac("recordManualImpression must be called on the main UI thread.");
    if (this.kI.kR == null)
      da.w("Ad state was null when trying to ping manual tracking URLs.");
    do
    {
      return;
      da.s("Pinging manual tracking URLs.");
    }
    while (this.kI.kR.om == null);
    cv.a(this.kI.kL, this.kI.kN.pU, this.kI.kR.om);
  }

  public void a(ab paramab)
  {
    er.ac("setAdSize must be called on the main UI thread.");
    this.kI.kQ = paramab;
    if (this.kI.kR != null)
      this.kI.kR.nu.a(paramab);
    if (this.kI.kK.getChildCount() > 1)
      this.kI.kK.removeView(this.kI.kK.getNextView());
    this.kI.kK.setMinimumWidth(paramab.widthPixels);
    this.kI.kK.setMinimumHeight(paramab.heightPixels);
    this.kI.kK.requestLayout();
  }

  public void a(af paramaf)
  {
    er.ac("setAdListener must be called on the main UI thread.");
    this.kI.kO = paramaf;
  }

  public void a(ai paramai)
  {
    er.ac("setAppEventListener must be called on the main UI thread.");
    this.kI.kT = paramai;
  }

  public void a(cn paramcn)
  {
    this.kI.kP = null;
    if ((paramcn.errorCode != -2) && (paramcn.errorCode != 3))
      cp.a(this.kI);
    if (paramcn.errorCode == -1)
      return;
    boolean bool;
    if (paramcn.oc.extras != null)
    {
      bool = paramcn.oc.extras.getBoolean("_noRefresh", false);
      if (!this.kI.kQ.lo)
        break label176;
      cv.a(paramcn.nu);
    }
    while (true)
    {
      if ((paramcn.errorCode == 3) && (paramcn.pf != null) && (paramcn.pf.mv != null))
      {
        da.s("Pinging no fill URLs.");
        bd.a(this.kI.kL, this.kI.kN.pU, paramcn, this.kI.adUnitId, false, paramcn.pf.mv);
      }
      if (paramcn.errorCode == -2)
        break label276;
      a(paramcn.errorCode);
      return;
      bool = false;
      break;
      label176: if (bool)
        continue;
      if (paramcn.mx > 0L)
      {
        this.kJ.a(paramcn.oc, paramcn.mx);
        continue;
      }
      if ((paramcn.pf != null) && (paramcn.pf.mx > 0L))
      {
        this.kJ.a(paramcn.oc, paramcn.pf.mx);
        continue;
      }
      if ((paramcn.ok) || (paramcn.errorCode != 2))
        continue;
      this.kJ.d(paramcn.oc);
    }
    label276: if ((!this.kI.kQ.lo) && (!b(paramcn)))
    {
      a(0);
      return;
    }
    if ((this.kI.kR != null) && (this.kI.kR.mP != null))
      this.kI.kR.mP.a(null);
    if (paramcn.mP != null)
      paramcn.mP.a(this);
    this.kI.kR = paramcn;
    if (paramcn.pg != null)
      this.kI.kQ = paramcn.pg;
    this.kI.kS.g(paramcn.ph);
    this.kI.kS.h(paramcn.pi);
    this.kI.kS.k(this.kI.kQ.lo);
    this.kI.kS.l(paramcn.ok);
    if (!this.kI.kQ.lo)
      b(false);
    if (this.kI.kU == null)
      this.kI.kU = new cr(this.kI.adUnitId);
    int i;
    if (paramcn.pf != null)
      i = paramcn.pf.my;
    for (int j = paramcn.pf.mz; ; j = 0)
    {
      this.kI.kU.a(i, j);
      ad();
      return;
      i = 0;
    }
  }

  public boolean a(z paramz)
  {
    er.ac("loadAd must be called on the main UI thread.");
    if (this.kI.kP != null)
      da.w("An ad request is already in progress. Aborting.");
    do
    {
      return false;
      if ((!this.kI.kQ.lo) || (this.kI.kR == null))
        continue;
      da.w("An interstitial is already loading. Aborting.");
      return false;
    }
    while (!ae());
    da.u("Starting ad request.");
    this.kJ.cancel();
    cd.a locala = c(paramz);
    Object localObject;
    if (this.kI.kQ.lo)
    {
      dd localdd2 = dd.a(this.kI.kL, this.kI.kQ, false, false, this.kI.kM, this.kI.kN);
      localdd2.bb().a(this, null, this, this, true);
      localObject = localdd2;
      this.kI.kP = by.a(this.kI.kL, locala, this.kI.kM, (dd)localObject, this.kH, this);
      return true;
    }
    View localView = this.kI.kK.getNextView();
    dd localdd1;
    if ((localView instanceof dd))
    {
      localdd1 = (dd)localView;
      localdd1.a(this.kI.kL, this.kI.kQ);
    }
    while (true)
    {
      localdd1.bb().a(this, this, this, this, false);
      localObject = localdd1;
      break;
      if (localView != null)
        this.kI.kK.removeView(localView);
      localdd1 = dd.a(this.kI.kL, this.kI.kQ, false, false, this.kI.kM, this.kI.kN);
      if (this.kI.kQ.lp != null)
        continue;
      b(localdd1);
    }
  }

  public void b(z paramz)
  {
    ViewParent localViewParent = this.kI.kK.getParent();
    if (((localViewParent instanceof View)) && (((View)localViewParent).isShown()) && (cv.aS()))
    {
      a(paramz);
      return;
    }
    da.u("Ad is not visible. Not refreshing ad.");
    this.kJ.d(paramz);
  }

  public void destroy()
  {
    er.ac("destroy must be called on the main UI thread.");
    this.kI.kO = null;
    this.kI.kT = null;
    this.kJ.cancel();
    stopLoading();
    if (this.kI.kK != null)
      this.kI.kK.removeAllViews();
    if ((this.kI.kR != null) && (this.kI.kR.nu != null))
      this.kI.kR.nu.destroy();
  }

  public boolean isReady()
  {
    er.ac("isLoaded must be called on the main UI thread.");
    return (this.kI.kP == null) && (this.kI.kR != null);
  }

  public void onAppEvent(String paramString1, String paramString2)
  {
    if (this.kI.kT != null);
    try
    {
      this.kI.kT.onAppEvent(paramString1, paramString2);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call the AppEventListener.", localRemoteException);
    }
  }

  public void pause()
  {
    er.ac("pause must be called on the main UI thread.");
    if (this.kI.kR != null)
      cv.a(this.kI.kR.nu);
  }

  public void resume()
  {
    er.ac("resume must be called on the main UI thread.");
    if (this.kI.kR != null)
      cv.b(this.kI.kR.nu);
  }

  public void showInterstitial()
  {
    er.ac("showInterstitial must be called on the main UI thread.");
    if (!this.kI.kQ.lo)
    {
      da.w("Cannot call showInterstitial on a banner ad.");
      return;
    }
    if (this.kI.kR == null)
    {
      da.w("The interstitial has not loaded.");
      return;
    }
    if (this.kI.kR.nu.be())
    {
      da.w("The interstitial is already showing.");
      return;
    }
    this.kI.kR.nu.n(true);
    if (this.kI.kR.ok)
      try
      {
        this.kI.kR.mN.showInterstitial();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        da.b("Could not show interstitial.", localRemoteException);
        ag();
        return;
      }
    bq localbq = new bq(this, this, this, this.kI.kR.nu, this.kI.kR.orientation, this.kI.kN);
    bo.a(this.kI.kL, localbq);
  }

  public void stopLoading()
  {
    er.ac("stopLoading must be called on the main UI thread.");
    if (this.kI.kR != null)
    {
      this.kI.kR.nu.stopLoading();
      this.kI.kR = null;
    }
    if (this.kI.kP != null)
      this.kI.kP.cancel();
  }

  public static final class a
  {
    public final String adUnitId;
    public final ViewSwitcher kK;
    public final Context kL;
    public final l kM;
    public final db kN;
    public af kO;
    public ct kP;
    public ab kQ;
    public cn kR;
    public co kS;
    public ai kT;
    public cr kU = null;
    private HashSet<co> kV = null;

    public a(Context paramContext, ab paramab, String paramString, db paramdb)
    {
      if (paramab.lo)
        this.kK = null;
      while (true)
      {
        this.kQ = paramab;
        this.adUnitId = paramString;
        this.kL = paramContext;
        this.kM = new l(k.a(paramdb.pU, paramContext));
        this.kN = paramdb;
        return;
        this.kK = new ViewSwitcher(paramContext);
        this.kK.setMinimumWidth(paramab.widthPixels);
        this.kK.setMinimumHeight(paramab.heightPixels);
        this.kK.setVisibility(4);
      }
    }

    public void a(HashSet<co> paramHashSet)
    {
      this.kV = paramHashSet;
    }

    public HashSet<co> ah()
    {
      return this.kV;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.v
 * JD-Core Version:    0.6.0
 */