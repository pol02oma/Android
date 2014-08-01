package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class ea extends Drawable
  implements Drawable.Callback
{
  private boolean AO = true;
  private int AS = 0;
  private long AT;
  private int AU;
  private int AV;
  private int AW = 255;
  private int AX;
  private int AY = 0;
  private boolean AZ;
  private b Ba;
  private Drawable Bb;
  private Drawable Bc;
  private boolean Bd;
  private boolean Be;
  private boolean Bf;
  private int Bg;

  public ea(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    if (paramDrawable1 == null)
      paramDrawable1 = a.dP();
    this.Bb = paramDrawable1;
    paramDrawable1.setCallback(this);
    b localb1 = this.Ba;
    localb1.Bk |= paramDrawable1.getChangingConfigurations();
    if (paramDrawable2 == null)
      paramDrawable2 = a.dP();
    this.Bc = paramDrawable2;
    paramDrawable2.setCallback(this);
    b localb2 = this.Ba;
    localb2.Bk |= paramDrawable2.getChangingConfigurations();
  }

  ea(b paramb)
  {
    this.Ba = new b(paramb);
  }

  public boolean canConstantState()
  {
    if (!this.Bd)
      if ((this.Bb.getConstantState() == null) || (this.Bc.getConstantState() == null))
        break label44;
    label44: for (boolean bool = true; ; bool = false)
    {
      this.Be = bool;
      this.Bd = true;
      return this.Be;
    }
  }

  public Drawable dO()
  {
    return this.Bc;
  }

  public void draw(Canvas paramCanvas)
  {
    int i = 1;
    switch (this.AS)
    {
    default:
    case 1:
    case 2:
    }
    int k;
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    while (true)
    {
      int j = i;
      while (true)
      {
        k = this.AY;
        bool = this.AO;
        localDrawable1 = this.Bb;
        localDrawable2 = this.Bc;
        if (j != 0)
        {
          if ((!bool) || (k == 0))
            localDrawable1.draw(paramCanvas);
          if (k == this.AW)
          {
            localDrawable2.setAlpha(this.AW);
            localDrawable2.draw(paramCanvas);
          }
          return;
          this.AT = SystemClock.uptimeMillis();
          this.AS = 2;
          j = 0;
          continue;
          if (this.AT < 0L)
            break;
          float f1 = (float)(SystemClock.uptimeMillis() - this.AT) / this.AX;
          if (f1 >= 1.0F);
          while (true)
          {
            if (i != 0)
              this.AS = 0;
            float f2 = Math.min(f1, 1.0F);
            this.AY = (int)(this.AU + f2 * (this.AV - this.AU));
            break;
            i = 0;
          }
        }
      }
    }
    if (bool)
      localDrawable1.setAlpha(this.AW - k);
    localDrawable1.draw(paramCanvas);
    if (bool)
      localDrawable1.setAlpha(this.AW);
    if (k > 0)
    {
      localDrawable2.setAlpha(k);
      localDrawable2.draw(paramCanvas);
      localDrawable2.setAlpha(this.AW);
    }
    invalidateSelf();
  }

  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.Ba.Bj | this.Ba.Bk;
  }

  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState())
    {
      this.Ba.Bj = getChangingConfigurations();
      return this.Ba;
    }
    return null;
  }

  public int getIntrinsicHeight()
  {
    return Math.max(this.Bb.getIntrinsicHeight(), this.Bc.getIntrinsicHeight());
  }

  public int getIntrinsicWidth()
  {
    return Math.max(this.Bb.getIntrinsicWidth(), this.Bc.getIntrinsicWidth());
  }

  public int getOpacity()
  {
    if (!this.Bf)
    {
      this.Bg = Drawable.resolveOpacity(this.Bb.getOpacity(), this.Bc.getOpacity());
      this.Bf = true;
    }
    return this.Bg;
  }

  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (fr.eJ())
    {
      Drawable.Callback localCallback = getCallback();
      if (localCallback != null)
        localCallback.invalidateDrawable(this);
    }
  }

  public Drawable mutate()
  {
    if ((!this.AZ) && (super.mutate() == this))
    {
      if (!canConstantState())
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      this.Bb.mutate();
      this.Bc.mutate();
      this.AZ = true;
    }
    return this;
  }

  protected void onBoundsChange(Rect paramRect)
  {
    this.Bb.setBounds(paramRect);
    this.Bc.setBounds(paramRect);
  }

  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (fr.eJ())
    {
      Drawable.Callback localCallback = getCallback();
      if (localCallback != null)
        localCallback.scheduleDrawable(this, paramRunnable, paramLong);
    }
  }

  public void setAlpha(int paramInt)
  {
    if (this.AY == this.AW)
      this.AY = paramInt;
    this.AW = paramInt;
    invalidateSelf();
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.Bb.setColorFilter(paramColorFilter);
    this.Bc.setColorFilter(paramColorFilter);
  }

  public void startTransition(int paramInt)
  {
    this.AU = 0;
    this.AV = this.AW;
    this.AY = 0;
    this.AX = paramInt;
    this.AS = 1;
    invalidateSelf();
  }

  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (fr.eJ())
    {
      Drawable.Callback localCallback = getCallback();
      if (localCallback != null)
        localCallback.unscheduleDrawable(this, paramRunnable);
    }
  }

  private static final class a extends Drawable
  {
    private static final a Bh = new a();
    private static final a Bi = new a(null);

    public void draw(Canvas paramCanvas)
    {
    }

    public Drawable.ConstantState getConstantState()
    {
      return Bi;
    }

    public int getOpacity()
    {
      return -2;
    }

    public void setAlpha(int paramInt)
    {
    }

    public void setColorFilter(ColorFilter paramColorFilter)
    {
    }

    private static final class a extends Drawable.ConstantState
    {
      public int getChangingConfigurations()
      {
        return 0;
      }

      public Drawable newDrawable()
      {
        return ea.a.dP();
      }
    }
  }

  static final class b extends Drawable.ConstantState
  {
    int Bj;
    int Bk;

    b(b paramb)
    {
      if (paramb != null)
      {
        this.Bj = paramb.Bj;
        this.Bk = paramb.Bk;
      }
    }

    public int getChangingConfigurations()
    {
      return this.Bj;
    }

    public Drawable newDrawable()
    {
      return new ea(this);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ea
 * JD-Core Version:    0.6.0
 */