package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class kb<M extends ka<M>, T>
{
  protected final Class<T> aaf;
  protected final boolean aag;
  protected final int tag;
  protected final int type;

  private kb(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.aaf = paramClass;
    this.tag = paramInt2;
    this.aag = paramBoolean;
  }

  public static <M extends ka<M>, T extends ke> kb<M, T> a(int paramInt1, Class<T> paramClass, int paramInt2)
  {
    return new kb(paramInt1, paramClass, paramInt2, false);
  }

  protected void a(kg paramkg, List<Object> paramList)
  {
    paramList.add(o(jy.n(paramkg.aai)));
  }

  protected boolean cI(int paramInt)
  {
    return paramInt == this.tag;
  }

  final T g(List<kg> paramList)
  {
    int i = 0;
    Object localObject1;
    if (paramList == null)
      localObject1 = null;
    while (true)
    {
      return localObject1;
      if (!this.aag)
        break;
      ArrayList localArrayList = new ArrayList();
      for (int j = 0; j < paramList.size(); j++)
      {
        kg localkg = (kg)paramList.get(j);
        if ((!cI(localkg.tag)) || (localkg.aai.length == 0))
          continue;
        a(localkg, localArrayList);
      }
      int k = localArrayList.size();
      if (k == 0)
        return null;
      localObject1 = this.aaf.cast(Array.newInstance(this.aaf.getComponentType(), k));
      while (i < k)
      {
        Array.set(localObject1, i, localArrayList.get(i));
        i++;
      }
    }
    int m = -1 + paramList.size();
    Object localObject2 = null;
    Object localObject3;
    if ((localObject2 == null) && (m >= 0))
    {
      localObject3 = (kg)paramList.get(m);
      if ((!cI(((kg)localObject3).tag)) || (((kg)localObject3).aai.length == 0))
        break label243;
    }
    while (true)
    {
      m--;
      localObject2 = localObject3;
      break;
      if (localObject2 == null)
        return null;
      return this.aaf.cast(o(jy.n(localObject2.aai)));
      label243: localObject3 = localObject2;
    }
  }

  protected Object o(jy paramjy)
  {
    Class localClass;
    if (this.aag)
      localClass = this.aaf.getComponentType();
    try
    {
      switch (this.type)
      {
      default:
        throw new IllegalArgumentException("Unknown type " + this.type);
      case 10:
      case 11:
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      while (true)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, localInstantiationException);
        localClass = this.aaf;
      }
      ke localke2 = (ke)localClass.newInstance();
      paramjy.a(localke2, kh.cK(this.tag));
      return localke2;
      ke localke1 = (ke)localClass.newInstance();
      paramjy.a(localke1);
      return localke1;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalArgumentException("Error creating instance of class " + localClass, localIllegalAccessException);
    }
    catch (IOException localIOException)
    {
    }
    throw new IllegalArgumentException("Error reading extension field", localIOException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.kb
 * JD-Core Version:    0.6.0
 */