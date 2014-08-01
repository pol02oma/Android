package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ep
{
  public static a e(Object paramObject)
  {
    return new a(paramObject, null);
  }

  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static int hashCode(Object[] paramArrayOfObject)
  {
    return Arrays.hashCode(paramArrayOfObject);
  }

  public static final class a
  {
    private final List<String> Ce;
    private final Object Cf;

    private a(Object paramObject)
    {
      this.Cf = er.f(paramObject);
      this.Ce = new ArrayList();
    }

    public a a(String paramString, Object paramObject)
    {
      this.Ce.add((String)er.f(paramString) + "=" + String.valueOf(paramObject));
      return this;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(100).append(this.Cf.getClass().getSimpleName()).append('{');
      int i = this.Ce.size();
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append((String)this.Ce.get(j));
        if (j >= i - 1)
          continue;
        localStringBuilder.append(", ");
      }
      return '}';
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ep
 * JD-Core Version:    0.6.0
 */