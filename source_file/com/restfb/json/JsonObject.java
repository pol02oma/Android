package com.restfb.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class JsonObject
{
  public static final Object NULL = new Null(null);
  private Map<Object, Object> map;

  public JsonObject()
  {
    this.map = new HashMap();
  }

  public JsonObject(JsonObject paramJsonObject, String[] paramArrayOfString)
  {
    this();
    for (int i = 0; i < paramArrayOfString.length; i++)
      putOnce(paramArrayOfString[i], paramJsonObject.opt(paramArrayOfString[i]));
  }

  public JsonObject(JsonTokener paramJsonTokener)
  {
    this();
    if (paramJsonTokener.nextClean() != '{')
      throw paramJsonTokener.syntaxError("A JsonObject text must begin with '{'");
    do
    {
      paramJsonTokener.back();
      String str;
      int i;
      switch (paramJsonTokener.nextClean())
      {
      default:
        paramJsonTokener.back();
        str = paramJsonTokener.nextValue().toString();
        i = paramJsonTokener.nextClean();
        if (i == 61)
        {
          if (paramJsonTokener.next() == '>')
            break;
          paramJsonTokener.back();
        }
      case '\000':
        do
        {
          putOnce(str, paramJsonTokener.nextValue());
          switch (paramJsonTokener.nextClean())
          {
          default:
            throw paramJsonTokener.syntaxError("Expected a ',' or '}'");
            throw paramJsonTokener.syntaxError("A JsonObject text must end with '}'");
          case ',':
          case ';':
          case '}':
          }
        }
        while (i == 58);
        throw paramJsonTokener.syntaxError("Expected a ':' after a key");
      case '}':
      }
    }
    while (paramJsonTokener.nextClean() != '}');
  }

  public JsonObject(Object paramObject)
  {
    this();
    populateInternalMap(paramObject, false);
  }

  public JsonObject(Object paramObject, boolean paramBoolean)
  {
    this();
    populateInternalMap(paramObject, paramBoolean);
  }

  public JsonObject(Object paramObject, String[] paramArrayOfString)
  {
    this();
    Class localClass = paramObject.getClass();
    int i = 0;
    while (true)
    {
      String str;
      if (i < paramArrayOfString.length)
        str = paramArrayOfString[i];
      try
      {
        putOpt(str, localClass.getField(str).get(paramObject));
        label42: i++;
        continue;
        return;
      }
      catch (Exception localException)
      {
        break label42;
      }
    }
  }

  public JsonObject(String paramString)
  {
    this(new JsonTokener(paramString));
  }

  public JsonObject(Map<Object, Object> paramMap)
  {
    if (paramMap == null)
      paramMap = new HashMap();
    this.map = paramMap;
  }

  public JsonObject(Map<Object, Object> paramMap, boolean paramBoolean)
  {
    this.map = new HashMap();
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (isStandardProperty(localEntry.getValue().getClass()))
        {
          this.map.put(localEntry.getKey(), localEntry.getValue());
          continue;
        }
        this.map.put(localEntry.getKey(), new JsonObject(localEntry.getValue(), paramBoolean));
      }
    }
  }

  public static String doubleToString(double paramDouble)
  {
    String str;
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble)))
      str = "null";
    do
    {
      do
      {
        return str;
        str = Double.toString(paramDouble);
      }
      while ((str.indexOf('.') <= 0) || (str.indexOf('e') >= 0) || (str.indexOf('E') >= 0));
      while (str.endsWith("0"))
        str = str.substring(0, -1 + str.length());
    }
    while (!str.endsWith("."));
    return str.substring(0, -1 + str.length());
  }

  public static String[] getNames(JsonObject paramJsonObject)
  {
    int i = paramJsonObject.length();
    if (i == 0)
      return null;
    Iterator localIterator = paramJsonObject.keys();
    String[] arrayOfString = new String[i];
    for (int j = 0; localIterator.hasNext(); j++)
      arrayOfString[j] = ((String)localIterator.next());
    return arrayOfString;
  }

  public static String[] getNames(Object paramObject)
  {
    String[] arrayOfString = null;
    if (paramObject == null);
    while (true)
    {
      return arrayOfString;
      Field[] arrayOfField = paramObject.getClass().getFields();
      int i = arrayOfField.length;
      arrayOfString = null;
      if (i == 0)
        continue;
      arrayOfString = new String[i];
      for (int j = 0; j < i; j++)
        arrayOfString[j] = arrayOfField[j].getName();
    }
  }

  static boolean isStandardProperty(Class<?> paramClass)
  {
    return (paramClass.isPrimitive()) || (paramClass.isAssignableFrom(Byte.class)) || (paramClass.isAssignableFrom(Short.class)) || (paramClass.isAssignableFrom(Integer.class)) || (paramClass.isAssignableFrom(Long.class)) || (paramClass.isAssignableFrom(Float.class)) || (paramClass.isAssignableFrom(Double.class)) || (paramClass.isAssignableFrom(Character.class)) || (paramClass.isAssignableFrom(String.class)) || (paramClass.isAssignableFrom(Boolean.class));
  }

  public static String numberToString(Number paramNumber)
  {
    if (paramNumber == null)
      throw new JsonException("Null pointer");
    testValidity(paramNumber);
    String str = paramNumber.toString();
    if ((str.indexOf('.') > 0) && (str.indexOf('e') < 0) && (str.indexOf('E') < 0))
    {
      while (str.endsWith("0"))
        str = str.substring(0, -1 + str.length());
      if (str.endsWith("."))
        str = str.substring(0, -1 + str.length());
    }
    return str;
  }

  private void populateInternalMap(Object paramObject, boolean paramBoolean)
  {
    int i = 0;
    Class localClass = paramObject.getClass();
    if (localClass.getClassLoader() == null)
      paramBoolean = false;
    Method[] arrayOfMethod;
    if (paramBoolean)
    {
      arrayOfMethod = localClass.getMethods();
      if (i >= arrayOfMethod.length)
        break label472;
    }
    while (true)
    {
      String str2;
      Object localObject;
      try
      {
        Method localMethod = arrayOfMethod[i];
        if (!Modifier.isPublic(localMethod.getModifiers()))
          continue;
        String str1 = localMethod.getName();
        str2 = "";
        if (!str1.startsWith("get"))
          continue;
        str2 = str1.substring(3);
        if ((str2.length() <= 0) || (!Character.isUpperCase(str2.charAt(0))) || (localMethod.getParameterTypes().length != 0))
          continue;
        if (str2.length() != 1)
          continue;
        str3 = str2.toLowerCase();
        localObject = localMethod.invoke(paramObject, (Object[])null);
        if (localObject != null)
          continue;
        this.map.put(str3, NULL);
        i++;
        break;
        arrayOfMethod = localClass.getDeclaredMethods();
        i = 0;
        break;
        if (!str1.startsWith("is"))
          continue;
        str2 = str1.substring(2);
        continue;
        if (Character.isUpperCase(str2.charAt(1)))
          break label473;
        str3 = str2.substring(0, 1).toLowerCase() + str2.substring(1);
        continue;
        if (localObject.getClass().isArray())
        {
          this.map.put(str3, new JsonArray(localObject, paramBoolean));
          continue;
        }
      }
      catch (Exception localException)
      {
        throw new RuntimeException(localException);
      }
      if ((localObject instanceof Collection))
      {
        this.map.put(str3, new JsonArray((Collection)localObject, paramBoolean));
        continue;
      }
      if ((localObject instanceof Map))
      {
        this.map.put(str3, new JsonObject((Map)localObject, paramBoolean));
        continue;
      }
      if (isStandardProperty(localObject.getClass()))
      {
        this.map.put(str3, localObject);
        continue;
      }
      if ((localObject.getClass().getPackage().getName().startsWith("java")) || (localObject.getClass().getClassLoader() == null))
      {
        this.map.put(str3, localObject.toString());
        continue;
      }
      this.map.put(str3, new JsonObject(localObject, paramBoolean));
      continue;
      label472: return;
      label473: String str3 = str2;
    }
  }

  public static String quote(String paramString)
  {
    int i = 0;
    if ((paramString == null) || (paramString.length() == 0))
      return "\"\"";
    int j = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(j + 4);
    localStringBuilder.append('"');
    int k = 0;
    if (i < j)
    {
      int m = paramString.charAt(i);
      switch (m)
      {
      default:
        if ((m >= 32) && ((m < 128) || (m >= 160)) && ((m < 8192) || (m >= 8448)))
          break;
        String str = "000" + Integer.toHexString(m);
        localStringBuilder.append("\\u" + str.substring(-4 + str.length()));
      case 34:
      case 92:
      case 47:
      case 8:
      case 9:
      case 10:
      case 12:
      case 13:
      }
      while (true)
      {
        i++;
        k = m;
        break;
        localStringBuilder.append('\\');
        localStringBuilder.append(m);
        continue;
        if (k == 60)
          localStringBuilder.append('\\');
        localStringBuilder.append(m);
        continue;
        localStringBuilder.append("\\b");
        continue;
        localStringBuilder.append("\\t");
        continue;
        localStringBuilder.append("\\n");
        continue;
        localStringBuilder.append("\\f");
        continue;
        localStringBuilder.append("\\r");
        continue;
        localStringBuilder.append(m);
      }
    }
    localStringBuilder.append('"');
    return localStringBuilder.toString();
  }

  public static Object stringToValue(String paramString)
  {
    if (paramString.equals(""));
    int i;
    do
    {
      return paramString;
      if (paramString.equalsIgnoreCase("true"))
        return Boolean.TRUE;
      if (paramString.equalsIgnoreCase("false"))
        return Boolean.FALSE;
      if (paramString.equalsIgnoreCase("null"))
        return NULL;
      i = paramString.charAt(0);
    }
    while (((i < 48) || (i > 57)) && (i != 46) && (i != 45) && (i != 43));
    if ((i != 48) || ((paramString.length() > 2) && ((paramString.charAt(1) == 'x') || (paramString.charAt(1) == 'X'))));
    try
    {
      Integer localInteger3 = new Integer(Integer.parseInt(paramString.substring(2), 16));
      return localInteger3;
      try
      {
        Integer localInteger2 = new Integer(Integer.parseInt(paramString, 8));
        return localInteger2;
      }
      catch (Exception localException2)
      {
      }
      try
      {
        label164: if ((paramString.indexOf('.') > -1) || (paramString.indexOf('e') > -1) || (paramString.indexOf('E') > -1))
          return Double.valueOf(paramString);
        Long localLong = new Long(paramString);
        if (localLong.longValue() == localLong.intValue())
        {
          Integer localInteger1 = new Integer(localLong.intValue());
          return localInteger1;
        }
        return localLong;
      }
      catch (Exception localException1)
      {
        return paramString;
      }
    }
    catch (Exception localException3)
    {
      break label164;
    }
  }

  static void testValidity(Object paramObject)
  {
    if (paramObject != null)
      if ((paramObject instanceof Double))
      {
        if ((((Double)paramObject).isInfinite()) || (((Double)paramObject).isNaN()))
          throw new JsonException("JSON does not allow non-finite numbers.");
      }
      else if (((paramObject instanceof Float)) && ((((Float)paramObject).isInfinite()) || (((Float)paramObject).isNaN())))
        throw new JsonException("JSON does not allow non-finite numbers.");
  }

  static String valueToString(Object paramObject)
  {
    if ((paramObject == null) || (paramObject.equals(null)))
      return "null";
    if ((paramObject instanceof JsonString))
    {
      String str;
      try
      {
        str = ((JsonString)paramObject).toJsonString();
        if ((str instanceof String))
          return (String)str;
      }
      catch (Exception localException)
      {
        throw new JsonException(localException);
      }
      throw new JsonException("Bad value from toJSONString: " + str);
    }
    if ((paramObject instanceof Number))
      return numberToString((Number)paramObject);
    if (((paramObject instanceof Boolean)) || ((paramObject instanceof JsonObject)) || ((paramObject instanceof JsonArray)))
      return paramObject.toString();
    if ((paramObject instanceof Map))
      return new JsonObject((Map)paramObject).toString();
    if ((paramObject instanceof Collection))
      return new JsonArray((Collection)paramObject).toString();
    if (paramObject.getClass().isArray())
      return new JsonArray(paramObject).toString();
    return quote(paramObject.toString());
  }

  static String valueToString(Object paramObject, int paramInt1, int paramInt2)
  {
    if ((paramObject == null) || (paramObject.equals(null)))
      return "null";
    try
    {
      if ((paramObject instanceof JsonString))
      {
        String str1 = ((JsonString)paramObject).toJsonString();
        if ((str1 instanceof String))
        {
          String str2 = (String)str1;
          return str2;
        }
      }
    }
    catch (Exception localException)
    {
      if ((paramObject instanceof Number))
        return numberToString((Number)paramObject);
      if ((paramObject instanceof Boolean))
        return paramObject.toString();
      if ((paramObject instanceof JsonObject))
        return ((JsonObject)paramObject).toString(paramInt1, paramInt2);
      if ((paramObject instanceof JsonArray))
        return ((JsonArray)paramObject).toString(paramInt1, paramInt2);
      if ((paramObject instanceof Map))
        return new JsonObject((Map)paramObject).toString(paramInt1, paramInt2);
      if ((paramObject instanceof Collection))
        return new JsonArray((Collection)paramObject).toString(paramInt1, paramInt2);
      if (paramObject.getClass().isArray())
        return new JsonArray(paramObject).toString(paramInt1, paramInt2);
    }
    return quote(paramObject.toString());
  }

  public JsonObject accumulate(String paramString, Object paramObject)
  {
    testValidity(paramObject);
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      if ((paramObject instanceof JsonArray))
        paramObject = new JsonArray().put(paramObject);
      put(paramString, paramObject);
      return this;
    }
    if ((localObject instanceof JsonArray))
    {
      ((JsonArray)localObject).put(paramObject);
      return this;
    }
    put(paramString, new JsonArray().put(localObject).put(paramObject));
    return this;
  }

  public JsonObject append(String paramString, Object paramObject)
  {
    testValidity(paramObject);
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      put(paramString, new JsonArray().put(paramObject));
      return this;
    }
    if ((localObject instanceof JsonArray))
    {
      put(paramString, ((JsonArray)localObject).put(paramObject));
      return this;
    }
    throw new JsonException("JsonObject[" + paramString + "] is not a JsonArray.");
  }

  public Object get(String paramString)
  {
    Object localObject = opt(paramString);
    if (localObject == null)
      throw new JsonException("JsonObject[" + quote(paramString) + "] not found.");
    return localObject;
  }

  public boolean getBoolean(String paramString)
  {
    Object localObject = get(paramString);
    if ((localObject.equals(Boolean.FALSE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("false"))))
      return false;
    if ((localObject.equals(Boolean.TRUE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("true"))))
      return true;
    throw new JsonException("JsonObject[" + quote(paramString) + "] is not a Boolean.");
  }

  public double getDouble(String paramString)
  {
    Object localObject = get(paramString);
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
    throw new JsonException("JsonObject[" + quote(paramString) + "] is not a number.");
  }

  public int getInt(String paramString)
  {
    Object localObject = get(paramString);
    if ((localObject instanceof Number))
      return ((Number)localObject).intValue();
    return (int)getDouble(paramString);
  }

  public JsonArray getJsonArray(String paramString)
  {
    Object localObject = get(paramString);
    if ((localObject instanceof JsonArray))
      return (JsonArray)localObject;
    throw new JsonException("JsonObject[" + quote(paramString) + "] is not a JsonArray.");
  }

  public JsonObject getJsonObject(String paramString)
  {
    Object localObject = get(paramString);
    if ((localObject instanceof JsonObject))
      return (JsonObject)localObject;
    throw new JsonException("JsonObject[" + quote(paramString) + "] is not a JsonObject.");
  }

  public long getLong(String paramString)
  {
    Object localObject = get(paramString);
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).longValue();
      long l = Long.parseLong((String)localObject);
      return l;
    }
    catch (Exception localException)
    {
    }
    throw new JsonException("JSONObject[" + quote(paramString) + "] is not a long.");
  }

  public String getString(String paramString)
  {
    return get(paramString).toString();
  }

  public boolean has(String paramString)
  {
    return this.map.containsKey(paramString);
  }

  public boolean isNull(String paramString)
  {
    return NULL.equals(opt(paramString));
  }

  public Iterator<?> keys()
  {
    return this.map.keySet().iterator();
  }

  public int length()
  {
    return this.map.size();
  }

  public JsonArray names()
  {
    JsonArray localJsonArray = new JsonArray();
    Iterator localIterator = keys();
    while (localIterator.hasNext())
      localJsonArray.put(localIterator.next());
    if (localJsonArray.length() == 0)
      localJsonArray = null;
    return localJsonArray;
  }

  public Object opt(String paramString)
  {
    if (paramString == null)
      return null;
    return this.map.get(paramString);
  }

  public boolean optBoolean(String paramString)
  {
    return optBoolean(paramString, false);
  }

  public boolean optBoolean(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = getBoolean(paramString);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return paramBoolean;
  }

  public double optDouble(String paramString)
  {
    return optDouble(paramString, (0.0D / 0.0D));
  }

  public double optDouble(String paramString, double paramDouble)
  {
    try
    {
      Object localObject = opt(paramString);
      if ((localObject instanceof Number))
        return ((Number)localObject).doubleValue();
      double d = new Double((String)localObject).doubleValue();
      return d;
    }
    catch (Exception localException)
    {
    }
    return paramDouble;
  }

  public int optInt(String paramString)
  {
    return optInt(paramString, 0);
  }

  public int optInt(String paramString, int paramInt)
  {
    try
    {
      int i = getInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt;
  }

  public JsonArray optJsonArray(String paramString)
  {
    Object localObject = opt(paramString);
    if ((localObject instanceof JsonArray))
      return (JsonArray)localObject;
    return null;
  }

  public JsonObject optJsonObject(String paramString)
  {
    Object localObject = opt(paramString);
    if ((localObject instanceof JsonObject))
      return (JsonObject)localObject;
    return null;
  }

  public long optLong(String paramString)
  {
    return optLong(paramString, 0L);
  }

  public long optLong(String paramString, long paramLong)
  {
    try
    {
      long l = getLong(paramString);
      return l;
    }
    catch (Exception localException)
    {
    }
    return paramLong;
  }

  public String optString(String paramString)
  {
    return optString(paramString, "");
  }

  public String optString(String paramString1, String paramString2)
  {
    Object localObject = opt(paramString1);
    if (localObject != null)
      paramString2 = localObject.toString();
    return paramString2;
  }

  public JsonObject put(String paramString, double paramDouble)
  {
    put(paramString, new Double(paramDouble));
    return this;
  }

  public JsonObject put(String paramString, int paramInt)
  {
    put(paramString, new Integer(paramInt));
    return this;
  }

  public JsonObject put(String paramString, long paramLong)
  {
    put(paramString, new Long(paramLong));
    return this;
  }

  public JsonObject put(String paramString, Object paramObject)
  {
    if (paramString == null)
      throw new JsonException("Null key.");
    if (paramObject != null)
    {
      testValidity(paramObject);
      this.map.put(paramString, paramObject);
      return this;
    }
    remove(paramString);
    return this;
  }

  public JsonObject put(String paramString, Collection<?> paramCollection)
  {
    put(paramString, new JsonArray(paramCollection));
    return this;
  }

  public JsonObject put(String paramString, Map<?, ?> paramMap)
  {
    put(paramString, new JsonObject(paramMap));
    return this;
  }

  public JsonObject put(String paramString, boolean paramBoolean)
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(paramString, localBoolean);
      return this;
    }
  }

  public JsonObject putOnce(String paramString, Object paramObject)
  {
    if ((paramString != null) && (paramObject != null))
    {
      if (opt(paramString) != null)
        throw new JsonException("Duplicate key \"" + paramString + "\"");
      put(paramString, paramObject);
    }
    return this;
  }

  public JsonObject putOpt(String paramString, Object paramObject)
  {
    if ((paramString != null) && (paramObject != null))
      put(paramString, paramObject);
    return this;
  }

  public Object remove(String paramString)
  {
    return this.map.remove(paramString);
  }

  public Iterator<?> sortedKeys()
  {
    return new TreeSet(this.map.keySet()).iterator();
  }

  public JsonArray toJsonArray(JsonArray paramJsonArray)
  {
    if ((paramJsonArray == null) || (paramJsonArray.length() == 0))
      return null;
    JsonArray localJsonArray = new JsonArray();
    for (int i = 0; i < paramJsonArray.length(); i++)
      localJsonArray.put(opt(paramJsonArray.getString(i)));
    return localJsonArray;
  }

  public String toString()
  {
    try
    {
      Iterator localIterator = keys();
      StringBuilder localStringBuilder = new StringBuilder("{");
      while (localIterator.hasNext())
      {
        if (localStringBuilder.length() > 1)
          localStringBuilder.append(',');
        Object localObject = localIterator.next();
        localStringBuilder.append(quote(localObject.toString()));
        localStringBuilder.append(':');
        localStringBuilder.append(valueToString(this.map.get(localObject)));
      }
      localStringBuilder.append('}');
      String str = localStringBuilder.toString();
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
      return "{}";
    Iterator localIterator = sortedKeys();
    StringBuilder localStringBuilder = new StringBuilder("{");
    int k = paramInt2 + paramInt1;
    if (j == 1)
    {
      Object localObject2 = localIterator.next();
      localStringBuilder.append(quote(localObject2.toString()));
      localStringBuilder.append(": ");
      localStringBuilder.append(valueToString(this.map.get(localObject2), paramInt1, paramInt2));
    }
    while (true)
    {
      localStringBuilder.append('}');
      return localStringBuilder.toString();
      Object localObject1;
      localStringBuilder.append(quote(localObject1.toString()));
      localStringBuilder.append(": ");
      localStringBuilder.append(valueToString(this.map.get(localObject1), paramInt1, k));
      if (localIterator.hasNext())
      {
        localObject1 = localIterator.next();
        if (localStringBuilder.length() > 1)
          localStringBuilder.append(",\n");
        while (true)
        {
          for (int m = 0; m < k; m++)
            localStringBuilder.append(' ');
          break;
          localStringBuilder.append('\n');
        }
      }
      if (localStringBuilder.length() <= 1)
        continue;
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
      Object localObject2;
      try
      {
        Iterator localIterator = keys();
        paramWriter.write(123);
        if (!localIterator.hasNext())
          break label138;
        if (i != 0)
          paramWriter.write(44);
        Object localObject1 = localIterator.next();
        paramWriter.write(quote(localObject1.toString()));
        paramWriter.write(58);
        localObject2 = this.map.get(localObject1);
        if ((localObject2 instanceof JsonObject))
        {
          ((JsonObject)localObject2).write(paramWriter);
          continue;
        }
        if ((localObject2 instanceof JsonArray))
          ((JsonArray)localObject2).write(paramWriter);
      }
      catch (IOException localIOException)
      {
        throw new JsonException(localIOException);
      }
      paramWriter.write(valueToString(localObject2));
      continue;
      label138: paramWriter.write(125);
      return paramWriter;
    }
  }

  private static final class Null
  {
    protected final Object clone()
    {
      return this;
    }

    public boolean equals(Object paramObject)
    {
      return (paramObject == null) || (paramObject == this);
    }

    public String toString()
    {
      return "null";
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.json.JsonObject
 * JD-Core Version:    0.6.0
 */