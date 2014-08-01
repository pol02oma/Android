package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import java.lang.ref.WeakReference;

public class gd
{
  protected fx HB;
  protected a HC;

  private gd(fx paramfx, int paramInt)
  {
    this.HB = paramfx;
    aV(paramInt);
  }

  public static gd a(fx paramfx, int paramInt)
  {
    if (fr.eK())
      return new b(paramfx, paramInt);
    return new gd(paramfx, paramInt);
  }

  protected void aV(int paramInt)
  {
    this.HC = new a(paramInt, new Binder(), null);
  }

  public void f(View paramView)
  {
  }

  public void fN()
  {
    this.HB.a(this.HC.HD, this.HC.fQ());
  }

  public Bundle fO()
  {
    return this.HC.fQ();
  }

  public IBinder fP()
  {
    return this.HC.HD;
  }

  public void setGravity(int paramInt)
  {
    this.HC.gravity = paramInt;
  }

  public static final class a
  {
    public IBinder HD;
    public int HE = -1;
    public int bottom = 0;
    public int gravity;
    public int left = 0;
    public int right = 0;
    public int top = 0;

    private a(int paramInt, IBinder paramIBinder)
    {
      this.gravity = paramInt;
      this.HD = paramIBinder;
    }

    public Bundle fQ()
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("popupLocationInfo.gravity", this.gravity);
      localBundle.putInt("popupLocationInfo.displayId", this.HE);
      localBundle.putInt("popupLocationInfo.left", this.left);
      localBundle.putInt("popupLocationInfo.top", this.top);
      localBundle.putInt("popupLocationInfo.right", this.right);
      localBundle.putInt("popupLocationInfo.bottom", this.bottom);
      return localBundle;
    }
  }

  private static final class b extends gd
    implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener
  {
    private boolean GA = false;
    private WeakReference<View> HF;

    protected b(fx paramfx, int paramInt)
    {
      super(paramInt, null);
    }

    private void g(View paramView)
    {
      int i = -1;
      if (fr.eO())
      {
        Display localDisplay = paramView.getDisplay();
        if (localDisplay != null)
          i = localDisplay.getDisplayId();
      }
      IBinder localIBinder = paramView.getWindowToken();
      int[] arrayOfInt = new int[2];
      paramView.getLocationInWindow(arrayOfInt);
      int j = paramView.getWidth();
      int k = paramView.getHeight();
      this.HC.HE = i;
      this.HC.HD = localIBinder;
      this.HC.left = arrayOfInt[0];
      this.HC.top = arrayOfInt[1];
      this.HC.right = (j + arrayOfInt[0]);
      this.HC.bottom = (k + arrayOfInt[1]);
      if (this.GA)
      {
        fN();
        this.GA = false;
      }
    }

    protected void aV(int paramInt)
    {
      this.HC = new gd.a(paramInt, null, null);
    }

    public void f(View paramView)
    {
      this.HB.fH();
      ViewTreeObserver localViewTreeObserver;
      if (this.HF != null)
      {
        View localView2 = (View)this.HF.get();
        Context localContext2 = this.HB.getContext();
        if ((localView2 == null) && ((localContext2 instanceof Activity)))
          localView2 = ((Activity)localContext2).getWindow().getDecorView();
        if (localView2 != null)
        {
          localView2.removeOnAttachStateChangeListener(this);
          localViewTreeObserver = localView2.getViewTreeObserver();
          if (!fr.eN())
            break label184;
          localViewTreeObserver.removeOnGlobalLayoutListener(this);
        }
      }
      while (true)
      {
        this.HF = null;
        Context localContext1 = this.HB.getContext();
        if ((paramView == null) && ((localContext1 instanceof Activity)))
        {
          View localView1 = ((Activity)localContext1).findViewById(16908290);
          if (localView1 == null)
            localView1 = ((Activity)localContext1).getWindow().getDecorView();
          fz.g("PopupManager", "You have not specified a View to use as content view for popups. Falling back to the Activity content view which may not work properly in future versions of the API. Use setViewForPopups() to set your content view.");
          paramView = localView1;
        }
        if (paramView == null)
          break;
        g(paramView);
        this.HF = new WeakReference(paramView);
        paramView.addOnAttachStateChangeListener(this);
        paramView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        return;
        label184: localViewTreeObserver.removeGlobalOnLayoutListener(this);
      }
      fz.h("PopupManager", "No content view usable to display popups. Popups will not be displayed in response to this client's calls. Use setViewForPopups() to set your content view.");
    }

    public void fN()
    {
      if (this.HC.HD != null)
      {
        super.fN();
        return;
      }
      if (this.HF != null);
      for (boolean bool = true; ; bool = false)
      {
        this.GA = bool;
        return;
      }
    }

    public void onGlobalLayout()
    {
      if (this.HF == null);
      View localView;
      do
      {
        return;
        localView = (View)this.HF.get();
      }
      while (localView == null);
      g(localView);
    }

    public void onViewAttachedToWindow(View paramView)
    {
      g(paramView);
    }

    public void onViewDetachedFromWindow(View paramView)
    {
      this.HB.fH();
      paramView.removeOnAttachStateChangeListener(this);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.gd
 * JD-Core Version:    0.6.0
 */