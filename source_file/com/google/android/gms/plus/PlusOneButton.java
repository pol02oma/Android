package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.eu;
import com.google.android.gms.plus.internal.g;

public final class PlusOneButton extends FrameLayout
{
  public static final int ANNOTATION_BUBBLE = 1;
  public static final int ANNOTATION_INLINE = 2;
  public static final int ANNOTATION_NONE = 0;
  public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
  public static final int SIZE_MEDIUM = 1;
  public static final int SIZE_SMALL = 0;
  public static final int SIZE_STANDARD = 3;
  public static final int SIZE_TALL = 2;
  private View QV;
  private int QW;
  private int QX;
  private OnPlusOneClickListener QY;
  private int mSize;
  private String pS;

  public PlusOneButton(Context paramContext)
  {
    this(paramContext, null);
  }

  public PlusOneButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mSize = getSize(paramContext, paramAttributeSet);
    this.QW = getAnnotation(paramContext, paramAttributeSet);
    this.QX = -1;
    v(getContext());
    if (isInEditMode());
  }

  protected static int getAnnotation(Context paramContext, AttributeSet paramAttributeSet)
  {
    String str = eu.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", paramContext, paramAttributeSet, true, false, "PlusOneButton");
    int i;
    if ("INLINE".equalsIgnoreCase(str))
      i = 2;
    boolean bool;
    do
    {
      return i;
      bool = "NONE".equalsIgnoreCase(str);
      i = 0;
    }
    while (bool);
    return 1;
  }

  protected static int getSize(Context paramContext, AttributeSet paramAttributeSet)
  {
    String str = eu.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", paramContext, paramAttributeSet, true, false, "PlusOneButton");
    if ("SMALL".equalsIgnoreCase(str))
      return 0;
    if ("MEDIUM".equalsIgnoreCase(str))
      return 1;
    if ("TALL".equalsIgnoreCase(str))
      return 2;
    return 3;
  }

  private void v(Context paramContext)
  {
    if (this.QV != null)
      removeView(this.QV);
    this.QV = g.a(paramContext, this.mSize, this.QW, this.pS, this.QX);
    setOnPlusOneClickListener(this.QY);
    addView(this.QV);
  }

  public void initialize(String paramString, int paramInt)
  {
    er.a(getContext() instanceof Activity, "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
    this.pS = paramString;
    this.QX = paramInt;
    v(getContext());
  }

  public void initialize(String paramString, OnPlusOneClickListener paramOnPlusOneClickListener)
  {
    this.pS = paramString;
    this.QX = 0;
    v(getContext());
    setOnPlusOneClickListener(paramOnPlusOneClickListener);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.QV.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    View localView = this.QV;
    measureChild(localView, paramInt1, paramInt2);
    setMeasuredDimension(localView.getMeasuredWidth(), localView.getMeasuredHeight());
  }

  public void setAnnotation(int paramInt)
  {
    this.QW = paramInt;
    v(getContext());
  }

  public void setOnPlusOneClickListener(OnPlusOneClickListener paramOnPlusOneClickListener)
  {
    this.QY = paramOnPlusOneClickListener;
    this.QV.setOnClickListener(new DefaultOnPlusOneClickListener(paramOnPlusOneClickListener));
  }

  public void setSize(int paramInt)
  {
    this.mSize = paramInt;
    v(getContext());
  }

  protected class DefaultOnPlusOneClickListener
    implements View.OnClickListener, PlusOneButton.OnPlusOneClickListener
  {
    private final PlusOneButton.OnPlusOneClickListener QZ;

    public DefaultOnPlusOneClickListener(PlusOneButton.OnPlusOneClickListener arg2)
    {
      Object localObject;
      this.QZ = localObject;
    }

    public void onClick(View paramView)
    {
      Intent localIntent = (Intent)PlusOneButton.a(PlusOneButton.this).getTag();
      if (this.QZ != null)
      {
        this.QZ.onPlusOneClick(localIntent);
        return;
      }
      onPlusOneClick(localIntent);
    }

    public void onPlusOneClick(Intent paramIntent)
    {
      Context localContext = PlusOneButton.this.getContext();
      if (((localContext instanceof Activity)) && (paramIntent != null))
        ((Activity)localContext).startActivityForResult(paramIntent, PlusOneButton.b(PlusOneButton.this));
    }
  }

  public static abstract interface OnPlusOneClickListener
  {
    public abstract void onPlusOneClick(Intent paramIntent);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.plus.PlusOneButton
 * JD-Core Version:    0.6.0
 */