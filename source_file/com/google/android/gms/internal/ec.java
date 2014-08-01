package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.net.Uri;
import android.widget.ImageView;

public final class ec extends ImageView
{
  private Uri Bl;
  private int Bm;
  private int Bn;

  public void N(int paramInt)
  {
    this.Bm = paramInt;
  }

  public void d(Uri paramUri)
  {
    this.Bl = paramUri;
  }

  public int dQ()
  {
    return this.Bm;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.Bn != 0)
      paramCanvas.drawColor(this.Bn);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ec
 * JD-Core Version:    0.6.0
 */