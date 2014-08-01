package com.ibuildapp.romanblack.MapPlugin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MapBottomPanel extends LinearLayout
{
  private Paint borderPaint;
  private Paint innerPaint;

  public MapBottomPanel(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public MapBottomPanel(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void init()
  {
    this.innerPaint = new Paint();
    this.borderPaint = new Paint();
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    RectF localRectF = new RectF();
    localRectF.set(0.0F, 0.0F, getMeasuredWidth(), getMeasuredHeight());
    paramCanvas.drawRoundRect(localRectF, 5.0F, 5.0F, this.innerPaint);
    paramCanvas.drawRoundRect(localRectF, 5.0F, 5.0F, this.borderPaint);
    super.dispatchDraw(paramCanvas);
  }

  public void setBorderPaint(Paint paramPaint)
  {
    this.borderPaint = paramPaint;
  }

  public void setInnerPaint(Paint paramPaint)
  {
    this.innerPaint = paramPaint;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.MapPlugin.MapBottomPanel
 * JD-Core Version:    0.6.0
 */