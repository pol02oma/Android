package com.google.android.gms.internal;

import java.util.Arrays;

public final class kc
{
  public static final Object aah = new Object();

  public static boolean equals(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if ((paramArrayOfInt1 == null) || (paramArrayOfInt1.length == 0))
      return (paramArrayOfInt2 == null) || (paramArrayOfInt2.length == 0);
    return Arrays.equals(paramArrayOfInt1, paramArrayOfInt2);
  }

  public static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    int i;
    if (paramArrayOfObject1 == null)
    {
      i = 0;
      if (paramArrayOfObject2 != null)
        break label43;
    }
    int k;
    int m;
    label43: for (int j = 0; ; j = paramArrayOfObject2.length)
    {
      k = 0;
      for (m = 0; (m < i) && (paramArrayOfObject1[m] == null); m++);
      i = paramArrayOfObject1.length;
      break;
    }
    while (true)
    {
      if ((n < j) && (paramArrayOfObject2[n] == null))
      {
        n++;
        continue;
      }
      int i1;
      int i2;
      label86: int i3;
      if (m >= i)
      {
        i1 = 1;
        if (n < j)
          break label108;
        i2 = 1;
        if ((i1 == 0) || (i2 == 0))
          break label114;
        i3 = 1;
      }
      label108: label114: boolean bool;
      do
      {
        do
        {
          return i3;
          i1 = 0;
          break;
          i2 = 0;
          break label86;
          i3 = 0;
        }
        while (i1 != i2);
        bool = paramArrayOfObject1[m].equals(paramArrayOfObject2[n]);
        i3 = 0;
      }
      while (!bool);
      int i4 = m + 1;
      k = n + 1;
      m = i4;
      break;
      int n = k;
    }
  }

  public static int hashCode(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      return 0;
    return Arrays.hashCode(paramArrayOfInt);
  }

  public static int hashCode(Object[] paramArrayOfObject)
  {
    int i = 0;
    if (paramArrayOfObject == null);
    for (int j = 0; ; j = paramArrayOfObject.length)
      for (int k = 0; k < j; k++)
      {
        Object localObject = paramArrayOfObject[k];
        if (localObject == null)
          continue;
        i = i * 31 + localObject.hashCode();
      }
    return i;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.kc
 * JD-Core Version:    0.6.0
 */