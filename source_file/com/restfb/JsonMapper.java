package com.restfb;

import java.util.List;

public abstract interface JsonMapper
{
  public abstract <T> List<T> toJavaList(String paramString, Class<T> paramClass);

  public abstract <T> T toJavaObject(String paramString, Class<T> paramClass);

  public abstract String toJson(Object paramObject);

  public abstract String toJson(Object paramObject, boolean paramBoolean);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.JsonMapper
 * JD-Core Version:    0.6.0
 */