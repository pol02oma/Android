package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ka<M extends ka<M>> extends ke
{
  protected List<kg> aae;

  public final <T> T a(kb<M, T> paramkb)
  {
    return paramkb.g(this.aae);
  }

  public void a(jz paramjz)
    throws IOException
  {
    if (this.aae == null);
    for (int i = 0; ; i = this.aae.size())
      for (int j = 0; j < i; j++)
      {
        kg localkg = (kg)this.aae.get(j);
        paramjz.cF(localkg.tag);
        paramjz.p(localkg.aai);
      }
  }

  protected final boolean a(jy paramjy, int paramInt)
    throws IOException
  {
    int i = paramjy.getPosition();
    if (!paramjy.cv(paramInt))
      return false;
    if (this.aae == null)
      this.aae = new ArrayList();
    byte[] arrayOfByte = paramjy.e(i, paramjy.getPosition() - i);
    this.aae.add(new kg(paramInt, arrayOfByte));
    return true;
  }

  public int c()
  {
    if (this.aae == null);
    int k;
    for (int i = 0; ; i = this.aae.size())
    {
      int j = 0;
      k = 0;
      while (j < i)
      {
        kg localkg = (kg)this.aae.get(j);
        k = k + jz.cG(localkg.tag) + localkg.aai.length;
        j++;
      }
    }
    this.DY = k;
    return k;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ka
 * JD-Core Version:    0.6.0
 */