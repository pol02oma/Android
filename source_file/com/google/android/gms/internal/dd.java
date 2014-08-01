package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class dd extends WebView
  implements DownloadListener
{
  private ab mF;
  private final db mG;
  private final Object mg = new Object();
  private final l nP;
  private final de pY;
  private final a pZ;
  private bo qa;
  private boolean qb;
  private boolean qc;

  private dd(a parama, ab paramab, boolean paramBoolean1, boolean paramBoolean2, l paraml, db paramdb)
  {
    super(parama);
    this.pZ = parama;
    this.mF = paramab;
    this.qb = paramBoolean1;
    this.nP = paraml;
    this.mG = paramdb;
    setBackgroundColor(0);
    WebSettings localWebSettings = getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSavePassword(false);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    cv.a(parama, paramdb.pU, localWebSettings);
    if (Build.VERSION.SDK_INT >= 17)
    {
      cx.a(getContext(), localWebSettings);
      setDownloadListener(this);
      if (Build.VERSION.SDK_INT < 11)
        break label186;
      this.pY = new dg(this, paramBoolean2);
      label133: setWebViewClient(this.pY);
      if (Build.VERSION.SDK_INT < 14)
        break label203;
      setWebChromeClient(new dh(this));
    }
    while (true)
    {
      bf();
      return;
      if (Build.VERSION.SDK_INT < 11)
        break;
      cw.a(getContext(), localWebSettings);
      break;
      label186: this.pY = new de(this, paramBoolean2);
      break label133;
      label203: if (Build.VERSION.SDK_INT < 11)
        continue;
      setWebChromeClient(new df(this));
    }
  }

  public static dd a(Context paramContext, ab paramab, boolean paramBoolean1, boolean paramBoolean2, l paraml, db paramdb)
  {
    return new dd(new a(paramContext), paramab, paramBoolean1, paramBoolean2, paraml, paramdb);
  }

  private void bf()
  {
    while (true)
    {
      synchronized (this.mg)
      {
        if ((this.qb) || (this.mF.lo))
        {
          if (Build.VERSION.SDK_INT >= 14)
            continue;
          da.s("Disabling hardware acceleration on an overlay.");
          bg();
          return;
          da.s("Enabling hardware acceleration on an overlay.");
          bh();
        }
      }
      if (Build.VERSION.SDK_INT < 18)
      {
        da.s("Disabling hardware acceleration on an AdView.");
        bg();
        continue;
      }
      da.s("Enabling hardware acceleration on an AdView.");
      bh();
    }
  }

  private void bg()
  {
    synchronized (this.mg)
    {
      if ((!this.qc) && (Build.VERSION.SDK_INT >= 11))
        cw.c(this);
      this.qc = true;
      return;
    }
  }

  private void bh()
  {
    synchronized (this.mg)
    {
      if ((this.qc) && (Build.VERSION.SDK_INT >= 11))
        cw.d(this);
      this.qc = false;
      return;
    }
  }

  public ab Q()
  {
    synchronized (this.mg)
    {
      ab localab = this.mF;
      return localab;
    }
  }

  public void a(Context paramContext, ab paramab)
  {
    synchronized (this.mg)
    {
      this.pZ.setBaseContext(paramContext);
      this.qa = null;
      this.mF = paramab;
      this.qb = false;
      cv.b(this);
      loadUrl("about:blank");
      this.pY.reset();
      return;
    }
  }

  public void a(ab paramab)
  {
    synchronized (this.mg)
    {
      this.mF = paramab;
      requestLayout();
      return;
    }
  }

  public void a(bo parambo)
  {
    synchronized (this.mg)
    {
      this.qa = parambo;
      return;
    }
  }

  public void a(String paramString, Map<String, ?> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("javascript:AFMA_ReceiveMessage('");
    localStringBuilder.append(paramString);
    localStringBuilder.append("'");
    if (paramMap != null);
    try
    {
      String str = cv.m(paramMap).toString();
      localStringBuilder.append(",");
      localStringBuilder.append(str);
      localStringBuilder.append(");");
      da.v("Dispatching AFMA event: " + localStringBuilder);
      loadUrl(localStringBuilder.toString());
      return;
    }
    catch (JSONException localJSONException)
    {
      da.w("Could not convert AFMA event parameters to JSON.");
    }
  }

  public void aY()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.mG.pU);
    a("onhide", localHashMap);
  }

  public void aZ()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.mG.pU);
    a("onshow", localHashMap);
  }

  public bo ba()
  {
    synchronized (this.mg)
    {
      bo localbo = this.qa;
      return localbo;
    }
  }

  public de bb()
  {
    return this.pY;
  }

  public l bc()
  {
    return this.nP;
  }

  public db bd()
  {
    return this.mG;
  }

  public boolean be()
  {
    synchronized (this.mg)
    {
      boolean bool = this.qb;
      return bool;
    }
  }

  public void n(boolean paramBoolean)
  {
    synchronized (this.mg)
    {
      this.qb = paramBoolean;
      bf();
      return;
    }
  }

  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.parse(paramString1), paramString4);
      getContext().startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      da.s("Couldn't find an Activity to view url/mimetype: " + paramString1 + " / " + paramString4);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int n;
    for (int i = 2147483647; ; i = n)
    {
      int m;
      label249: 
      do
      {
        int k;
        while (true)
        {
          synchronized (this.mg)
          {
            if ((!isInEditMode()) && (!this.qb))
              continue;
            super.onMeasure(paramInt1, paramInt2);
            return;
            int j = View.MeasureSpec.getMode(paramInt1);
            k = View.MeasureSpec.getSize(paramInt1);
            m = View.MeasureSpec.getMode(paramInt2);
            n = View.MeasureSpec.getSize(paramInt2);
            if (j == -2147483648)
              break label249;
            if (j != 1073741824)
              break;
            break label249;
            if ((this.mF.widthPixels > i1) || (this.mF.heightPixels > i))
            {
              da.w("Not enough space to show ad. Needs " + this.mF.widthPixels + "x" + this.mF.heightPixels + " pixels, but only has " + k + "x" + n + " pixels.");
              if (getVisibility() == 8)
                continue;
              setVisibility(4);
              setMeasuredDimension(0, 0);
              return;
            }
          }
          if (getVisibility() != 8)
            setVisibility(0);
          setMeasuredDimension(this.mF.widthPixels, this.mF.heightPixels);
        }
        int i1 = i;
        continue;
        i1 = k;
      }
      while ((m != -2147483648) && (m != 1073741824));
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.nP != null)
      this.nP.a(paramMotionEvent);
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setContext(Context paramContext)
  {
    this.pZ.setBaseContext(paramContext);
  }

  private static class a extends MutableContextWrapper
  {
    private Activity qd;
    private Context qe;

    public a(Context paramContext)
    {
      super();
      setBaseContext(paramContext);
    }

    public void setBaseContext(Context paramContext)
    {
      this.qe = paramContext.getApplicationContext();
      if ((paramContext instanceof Activity));
      for (Activity localActivity = (Activity)paramContext; ; localActivity = null)
      {
        this.qd = localActivity;
        super.setBaseContext(this.qe);
        return;
      }
    }

    public void startActivity(Intent paramIntent)
    {
      if (this.qd != null)
      {
        this.qd.startActivity(paramIntent);
        return;
      }
      paramIntent.setFlags(268435456);
      this.qe.startActivity(paramIntent);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.dd
 * JD-Core Version:    0.6.0
 */