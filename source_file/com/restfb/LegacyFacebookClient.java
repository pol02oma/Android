package com.restfb;

import java.util.List;
import java.util.Map;

public abstract interface LegacyFacebookClient
{
  public abstract <T> T execute(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  @Deprecated
  public abstract <T> T execute(String paramString1, String paramString2, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  @Deprecated
  public abstract void execute(String paramString1, String paramString2, Parameter[] paramArrayOfParameter);

  public abstract void execute(String paramString, Parameter[] paramArrayOfParameter);

  public abstract <T> List<T> executeForList(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  @Deprecated
  public abstract <T> List<T> executeForList(String paramString1, String paramString2, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public abstract <T> T executeMultiquery(Map<String, String> paramMap, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  @Deprecated
  public abstract <T> T executeMultiquery(Map<String, String> paramMap, String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.LegacyFacebookClient
 * JD-Core Version:    0.6.0
 */