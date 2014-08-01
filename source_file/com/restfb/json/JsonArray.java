package com.restfb.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JsonArray
{
  private ArrayList<Object> myArrayList;

  public JsonArray()
  {
    this.myArrayList = new ArrayList();
  }

  public JsonArray(JsonTokener paramJsonTokener)
  {
    this();
    int i = paramJsonTokener.nextClean();
    char c1;
    if (i == 91)
    {
      c1 = ']';
      if (paramJsonTokener.nextClean() != ']')
        break label47;
    }
    label47: char c2;
    do
    {
      return;
      if (i == 40)
      {
        c1 = ')';
        break;
      }
      throw paramJsonTokener.syntaxError("A JsonArray text must start with '['");
      paramJsonTokener.back();
      while (true)
      {
        if (paramJsonTokener.nextClean() == ',')
        {
          paramJsonTokener.back();
          this.myArrayList.add(null);
        }
        while (true)
        {
          c2 = paramJsonTokener.nextClean();
          switch (c2)
          {
          default:
            throw paramJsonTokener.syntaxError("Expected a ',' or ']'");
            paramJsonTokener.back();
            this.myArrayList.add(paramJsonTokener.nextValue());
          case ',':
          case ';':
          case ')':
          case ']':
          }
        }
        if (paramJsonTokener.nextClean() == ']')
          break;
        paramJsonTokener.back();
      }
    }
    while (c1 == c2);
    throw paramJsonTokener.syntaxError("Expected a '" + new Character(c1) + "'");
  }

  public JsonArray(Object paramObject)
  {
    this();
    if (paramObject.getClass().isArray())
    {
      int i = Array.getLength(paramObject);
      for (int j = 0; j < i; j++)
        put(Array.get(paramObject, j));
    }
    throw new JsonException("JsonArray initial value should be a string or collection or array.");
  }

  public JsonArray(Object paramObject, boolean paramBoolean)
  {
    this();
    if (paramObject.getClass().isArray())
    {
      int i = Array.getLength(paramObject);
      int j = 0;
      if (j < i)
      {
        Object localObject = Array.get(paramObject, j);
        if (JsonObject.isStandardProperty(localObject.getClass()))
          this.myArrayList.add(localObject);
        while (true)
        {
          j++;
          break;
          this.myArrayList.add(new JsonObject(localObject, paramBoolean));
        }
      }
    }
    else
    {
      throw new JsonException("JsonArray initial value should be a string or collection or array.");
    }
  }

  public JsonArray(String paramString)
  {
    this(new JsonTokener(paramString));
  }

  public JsonArray(Collection<?> paramCollection)
  {
    if (paramCollection == null);
    for (ArrayList localArrayList = new ArrayList(); ; localArrayList = new ArrayList(paramCollection))
    {
      this.myArrayList = localArrayList;
      return;
    }
  }

  public JsonArray(Collection<?> paramCollection, boolean paramBoolean)
  {
    this.myArrayList = new ArrayList();
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if ((localObject instanceof Map))
        {
          this.myArrayList.add(new JsonObject((Map)localObject, paramBoolean));
          continue;
        }
        if (!JsonObject.isStandardProperty(localObject.getClass()))
        {
          this.myArrayList.add(new JsonObject(localObject, paramBoolean));
          continue;
        }
        this.myArrayList.add(localObject);
      }
    }
  }

  public Object get(int paramInt)
  {
    Object localObject = opt(paramInt);
    if (localObject == null)
      throw new JsonException("JsonArray[" + paramInt + "] not found.");
    return localObject;
  }

  public boolean getBoolean(int paramInt)
  {
    Object localObject = get(paramInt);
    if ((localObject.equals(Boolean.FALSE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("false"))))
      return false;
    if ((localObject.equals(Boolean.TRUE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("true"))))
      return true;
    throw new JsonException("JsonArray[" + paramInt + "] is not a Boolean.");
  }

  public double getDouble(int paramInt)
  {
    Object localObject = get(paramInt);
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).doubleValue();
      double d = Double.valueOf((String)localObject).doubleValue();
      return d;
    }
    catch (Exception localException)
    {
    }
    throw new JsonException("JsonArray[" + paramInt + "] is not a number.");
  }

  public int getInt(int paramInt)
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof Number))
      return ((Number)localObject).intValue();
    return (int)getDouble(paramInt);
  }

  public JsonArray getJsonArray(int paramInt)
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof JsonArray))
      return (JsonArray)localObject;
    throw new JsonException("JsonArray[" + paramInt + "] is not a JsonArray.");
  }

  public JsonObject getJsonObject(int paramInt)
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof JsonObject))
      return (JsonObject)localObject;
    throw new JsonException("JsonArray[" + paramInt + "] is not a JsonObject.");
  }

  public long getLong(int paramInt)
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof Number))
      return ((Number)localObject).longValue();
    return ()getDouble(paramInt);
  }

  public String getString(int paramInt)
  {
    return get(paramInt).toString();
  }

  public boolean isNull(int paramInt)
  {
    return JsonObject.NULL.equals(opt(paramInt));
  }

  public String join(String paramString)
  {
    int i = length();
    StringBuilder localStringBuilder = new StringBuilder();
    for (int j = 0; j < i; j++)
    {
      if (j > 0)
        localStringBuilder.append(paramString);
      localStringBuilder.append(JsonObject.valueToString(this.myArrayList.get(j)));
    }
    return localStringBuilder.toString();
  }

  public int length()
  {
    return this.myArrayList.size();
  }

  public Object opt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= length()))
      return null;
    return this.myArrayList.get(paramInt);
  }

  public boolean optBoolean(int paramInt)
  {
    return optBoolean(paramInt, false);
  }

  public boolean optBoolean(int paramInt, boolean paramBoolean)
  {
    try
    {
      boolean bool = getBoolean(paramInt);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return paramBoolean;
  }

  public double optDouble(int paramInt)
  {
    return optDouble(paramInt, (0.0D / 0.0D));
  }

  public double optDouble(int paramInt, double paramDouble)
  {
    try
    {
      double d = getDouble(paramInt);
      return d;
    }
    catch (Exception localException)
    {
    }
    return paramDouble;
  }

  public int optInt(int paramInt)
  {
    return optInt(paramInt, 0);
  }

  public int optInt(int paramInt1, int paramInt2)
  {
    try
    {
      int i = getInt(paramInt1);
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt2;
  }

  public JsonArray optJsonArray(int paramInt)
  {
    Object localObject = opt(paramInt);
    if ((localObject instanceof JsonArray))
      return (JsonArray)localObject;
    return null;
  }

  public JsonObject optJsonObject(int paramInt)
  {
    Object localObject = opt(paramInt);
    if ((localObject instanceof JsonObject))
      return (JsonObject)localObject;
    return null;
  }

  public long optLong(int paramInt)
  {
    return optLong(paramInt, 0L);
  }

  public long optLong(int paramInt, long paramLong)
  {
    try
    {
      long l = getLong(paramInt);
      return l;
    }
    catch (Exception localException)
    {
    }
    return paramLong;
  }

  public String optString(int paramInt)
  {
    return optString(paramInt, "");
  }

  public String optString(int paramInt, String paramString)
  {
    Object localObject = opt(paramInt);
    if (localObject != null)
      paramString = localObject.toString();
    return paramString;
  }

  public JsonArray put(double paramDouble)
  {
    Double localDouble = new Double(paramDouble);
    JsonObject.testValidity(localDouble);
    put(localDouble);
    return this;
  }

  public JsonArray put(int paramInt)
  {
    put(new Integer(paramInt));
    return this;
  }

  public JsonArray put(int paramInt, double paramDouble)
  {
    put(paramInt, new Double(paramDouble));
    return this;
  }

  public JsonArray put(int paramInt1, int paramInt2)
  {
    put(paramInt1, new Integer(paramInt2));
    return this;
  }

  public JsonArray put(int paramInt, long paramLong)
  {
    put(paramInt, new Long(paramLong));
    return this;
  }

  public JsonArray put(int paramInt, Object paramObject)
  {
    JsonObject.testValidity(paramObject);
    if (paramInt < 0)
      throw new JsonException("JsonArray[" + paramInt + "] not found.");
    if (paramInt < length())
    {
      this.myArrayList.set(paramInt, paramObject);
      return this;
    }
    while (paramInt != length())
      put(JsonObject.NULL);
    put(paramObject);
    return this;
  }

  public JsonArray put(int paramInt, Collection<?> paramCollection)
  {
    put(paramInt, new JsonArray(paramCollection));
    return this;
  }

  public JsonArray put(int paramInt, Map<?, ?> paramMap)
  {
    put(paramInt, new JsonObject(paramMap));
    return this;
  }

  public JsonArray put(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(paramInt, localBoolean);
      return this;
    }
  }

  public JsonArray put(long paramLong)
  {
    put(new Long(paramLong));
    return this;
  }

  public JsonArray put(Object paramObject)
  {
    this.myArrayList.add(paramObject);
    return this;
  }

  public JsonArray put(Collection<?> paramCollection)
  {
    put(new JsonArray(paramCollection));
    return this;
  }

  public JsonArray put(Map<?, ?> paramMap)
  {
    put(new JsonObject(paramMap));
    return this;
  }

  public JsonArray put(boolean paramBoolean)
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(localBoolean);
      return this;
    }
  }

  public Object remove(int paramInt)
  {
    Object localObject = opt(paramInt);
    this.myArrayList.remove(paramInt);
    return localObject;
  }

  public JsonObject toJsonObject(JsonArray paramJsonArray)
  {
    if ((paramJsonArray == null) || (paramJsonArray.length() == 0) || (length() == 0))
      return null;
    JsonObject localJsonObject = new JsonObject();
    for (int i = 0; i < paramJsonArray.length(); i++)
      localJsonObject.put(paramJsonArray.getString(i), opt(i));
    return localJsonObject;
  }

  public String toString()
  {
    try
    {
      String str = '[' + join(",") + ']';
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String toString(int paramInt)
  {
    return toString(paramInt, 0);
  }

  String toString(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = length();
    if (j == 0)
      return "[]";
    StringBuilder localStringBuilder = new StringBuilder("[");
    if (j == 1)
      localStringBuilder.append(JsonObject.valueToString(this.myArrayList.get(0), paramInt1, paramInt2));
    while (true)
    {
      localStringBuilder.append(']');
      return localStringBuilder.toString();
      int k = paramInt2 + paramInt1;
      localStringBuilder.append('\n');
      for (int m = 0; m < j; m++)
      {
        if (m > 0)
          localStringBuilder.append(",\n");
        for (int n = 0; n < k; n++)
          localStringBuilder.append(' ');
        localStringBuilder.append(JsonObject.valueToString(this.myArrayList.get(m), paramInt1, k));
      }
      localStringBuilder.append('\n');
      while (i < paramInt2)
      {
        localStringBuilder.append(' ');
        i++;
      }
    }
  }

  public Writer write(Writer paramWriter)
  {
    for (int i = 0; ; i = 1)
    {
      int k;
      Object localObject;
      try
      {
        int j = length();
        paramWriter.write(91);
        k = 0;
        if (k >= j)
          break label109;
        if (i != 0)
          paramWriter.write(44);
        localObject = this.myArrayList.get(k);
        if ((localObject instanceof JsonObject))
        {
          ((JsonObject)localObject).write(paramWriter);
          break label117;
        }
        if ((localObject instanceof JsonArray))
          ((JsonArray)localObject).write(paramWriter);
      }
      catch (IOException localIOException)
      {
        throw new JsonException(localIOException);
      }
      paramWriter.write(JsonObject.valueToString(localObject));
      break label117;
      label109: paramWriter.write(93);
      return paramWriter;
      label117: k++;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.json.JsonArray
 * JD-Core Version:    0.6.0
 */