package org.apache.james.mime4j.util;

public final class LangUtils
{
  public static final int HASH_OFFSET = 37;
  public static final int HASH_SEED = 17;

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      return paramObject2 == null;
    return paramObject1.equals(paramObject2);
  }

  public static boolean equalsIgnoreCase(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2 == null;
    return paramString1.equalsIgnoreCase(paramString2);
  }

  public static int hashCode(int paramInt1, int paramInt2)
  {
    return paramInt2 + paramInt1 * 37;
  }

  public static int hashCode(int paramInt, Object paramObject)
  {
    if (paramObject != null);
    for (int i = paramObject.hashCode(); ; i = 0)
      return hashCode(paramInt, i);
  }

  public static int hashCode(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
      return hashCode(paramInt, i);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.util.LangUtils
 * JD-Core Version:    0.6.0
 */