package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.internal.ea;
import com.google.android.gms.internal.eb;
import com.google.android.gms.internal.ec;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.fr;
import java.lang.ref.WeakReference;

public final class a
{
  final a AF;
  private int AG = 0;
  private int AH = 0;
  int AI;
  private int AJ;
  private WeakReference<ImageManager.OnImageLoadedListener> AK;
  private WeakReference<ImageView> AL;
  private WeakReference<TextView> AM;
  private int AN = -1;
  private boolean AO = true;
  private boolean AP = false;
  private int AQ;

  public a(int paramInt)
  {
    this.AF = new a(null);
    this.AH = paramInt;
  }

  public a(Uri paramUri)
  {
    this.AF = new a(paramUri);
    this.AH = 0;
  }

  private ea a(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null)
      if (!(paramDrawable1 instanceof ea));
    for (paramDrawable1 = ((ea)paramDrawable1).dO(); ; paramDrawable1 = null)
      return new ea(paramDrawable1, paramDrawable2);
  }

  private void a(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    switch (this.AI)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    TextView localTextView;
    do
    {
      ImageView localImageView;
      do
      {
        ImageManager.OnImageLoadedListener localOnImageLoadedListener;
        do
        {
          do
            return;
          while (paramBoolean2);
          localOnImageLoadedListener = (ImageManager.OnImageLoadedListener)this.AK.get();
        }
        while (localOnImageLoadedListener == null);
        localOnImageLoadedListener.onImageLoaded(this.AF.uri, paramDrawable, paramBoolean3);
        return;
        localImageView = (ImageView)this.AL.get();
      }
      while (localImageView == null);
      a(localImageView, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
      return;
      localTextView = (TextView)this.AM.get();
    }
    while (localTextView == null);
    a(localTextView, this.AN, paramDrawable, paramBoolean1, paramBoolean2);
  }

  private void a(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((!paramBoolean2) && (!paramBoolean3));
    for (int i = 1; (i != 0) && ((paramImageView instanceof ec)); i = 0)
    {
      int k = ((ec)paramImageView).dQ();
      if ((this.AH == 0) || (k != this.AH))
        break;
      return;
    }
    boolean bool = b(paramBoolean1, paramBoolean2);
    if (bool);
    for (Object localObject = a(paramImageView.getDrawable(), paramDrawable); ; localObject = paramDrawable)
    {
      paramImageView.setImageDrawable((Drawable)localObject);
      ec localec;
      Uri localUri;
      if ((paramImageView instanceof ec))
      {
        localec = (ec)paramImageView;
        if (!paramBoolean3)
          break label157;
        localUri = this.AF.uri;
        label115: localec.d(localUri);
        if (i == 0)
          break label163;
      }
      label157: label163: for (int j = this.AH; ; j = 0)
      {
        localec.N(j);
        if (!bool)
          break;
        ((ea)localObject).startTransition(250);
        return;
        localUri = null;
        break label115;
      }
    }
  }

  private void a(TextView paramTextView, int paramInt, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = b(paramBoolean1, paramBoolean2);
    Drawable[] arrayOfDrawable;
    Drawable localDrawable;
    if (fr.eO())
    {
      arrayOfDrawable = paramTextView.getCompoundDrawablesRelative();
      localDrawable = arrayOfDrawable[paramInt];
      if (!bool)
        break label172;
    }
    label130: label139: label148: label157: label172: for (Object localObject1 = a(localDrawable, paramDrawable); ; localObject1 = paramDrawable)
    {
      Object localObject2;
      label50: Object localObject3;
      label59: Object localObject4;
      label68: Object localObject5;
      if (paramInt == 0)
      {
        localObject2 = localObject1;
        if (paramInt != 1)
          break label130;
        localObject3 = localObject1;
        if (paramInt != 2)
          break label139;
        localObject4 = localObject1;
        if (paramInt != 3)
          break label148;
        localObject5 = localObject1;
        label77: if (!fr.eO())
          break label157;
        paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable)localObject2, (Drawable)localObject3, (Drawable)localObject4, (Drawable)localObject5);
      }
      while (true)
      {
        if (bool)
          ((ea)localObject1).startTransition(250);
        return;
        arrayOfDrawable = paramTextView.getCompoundDrawables();
        break;
        localObject2 = arrayOfDrawable[0];
        break label50;
        localObject3 = arrayOfDrawable[1];
        break label59;
        localObject4 = arrayOfDrawable[2];
        break label68;
        localObject5 = arrayOfDrawable[3];
        break label77;
        paramTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable)localObject2, (Drawable)localObject3, (Drawable)localObject4, (Drawable)localObject5);
      }
    }
  }

  private boolean b(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (this.AO) && (!paramBoolean2) && ((!paramBoolean1) || (this.AP));
  }

  public void L(int paramInt)
  {
    this.AH = paramInt;
  }

  void a(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    ed.d(paramBitmap);
    if ((0x1 & this.AQ) != 0)
      paramBitmap = eb.a(paramBitmap);
    a(new BitmapDrawable(paramContext.getResources(), paramBitmap), paramBoolean, false, true);
  }

  public void a(ImageView paramImageView)
  {
    ed.d(paramImageView);
    this.AK = null;
    this.AL = new WeakReference(paramImageView);
    this.AM = null;
    this.AN = -1;
    this.AI = 2;
    this.AJ = paramImageView.hashCode();
  }

  public void a(ImageManager.OnImageLoadedListener paramOnImageLoadedListener)
  {
    ed.d(paramOnImageLoadedListener);
    this.AK = new WeakReference(paramOnImageLoadedListener);
    this.AL = null;
    this.AM = null;
    this.AN = -1;
    this.AI = 1;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramOnImageLoadedListener;
    arrayOfObject[1] = this.AF;
    this.AJ = ep.hashCode(arrayOfObject);
  }

  void b(Context paramContext, boolean paramBoolean)
  {
    int i = this.AH;
    Drawable localDrawable = null;
    if (i != 0)
    {
      Resources localResources = paramContext.getResources();
      localDrawable = localResources.getDrawable(this.AH);
      if ((0x1 & this.AQ) != 0)
        localDrawable = eb.a(localResources, localDrawable);
    }
    a(localDrawable, paramBoolean, false, false);
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (!(paramObject instanceof a))
      i = 0;
    do
      return i;
    while ((this == paramObject) || (((a)paramObject).hashCode() == hashCode()));
    return false;
  }

  public int hashCode()
  {
    return this.AJ;
  }

  void x(Context paramContext)
  {
    int i = this.AG;
    Drawable localDrawable = null;
    if (i != 0)
    {
      Resources localResources = paramContext.getResources();
      localDrawable = localResources.getDrawable(this.AG);
      if ((0x1 & this.AQ) != 0)
        localDrawable = eb.a(localResources, localDrawable);
    }
    a(localDrawable, false, true, false);
  }

  public static final class a
  {
    public final Uri uri;

    public a(Uri paramUri)
    {
      this.uri = paramUri;
    }

    public boolean equals(Object paramObject)
    {
      int i = 1;
      if (!(paramObject instanceof a))
        i = 0;
      do
        return i;
      while ((this == paramObject) || (((a)paramObject).hashCode() == hashCode()));
      return false;
    }

    public int hashCode()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.uri;
      return ep.hashCode(arrayOfObject);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.common.images.a
 * JD-Core Version:    0.6.0
 */