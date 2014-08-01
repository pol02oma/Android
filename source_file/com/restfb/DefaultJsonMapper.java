package com.restfb;

import com.restfb.exception.FacebookJsonMappingException;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonException;
import com.restfb.json.JsonObject;
import com.restfb.types.Post.Comments;
import com.restfb.util.ReflectionUtils;
import com.restfb.util.ReflectionUtils.FieldWithAnnotation;
import com.restfb.util.StringUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultJsonMapper
  implements JsonMapper
{
  private static final Logger logger = Logger.getLogger(DefaultJsonMapper.class.getName());
  protected JsonMappingErrorHandler jsonMappingErrorHandler;

  public DefaultJsonMapper()
  {
    this(new JsonMappingErrorHandler()
    {
      public boolean handleMappingError(String paramString, Class<?> paramClass, Exception paramException)
      {
        return false;
      }
    });
  }

  public DefaultJsonMapper(JsonMappingErrorHandler paramJsonMappingErrorHandler)
  {
    if (paramJsonMappingErrorHandler == null)
      throw new IllegalArgumentException("The jsonMappingErrorHandler parameter cannot be null.");
    this.jsonMappingErrorHandler = paramJsonMappingErrorHandler;
  }

  protected <T> T createInstance(Class<T> paramClass)
  {
    String str = "Unable to create an instance of " + paramClass + ". Please make sure that if it's a nested class, is marked 'static'. " + "It should have a no-argument constructor.";
    Constructor localConstructor;
    try
    {
      localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (localConstructor == null)
        throw new FacebookJsonMappingException("Unable to find a default constructor for " + paramClass);
    }
    catch (Exception localException)
    {
      throw new FacebookJsonMappingException(str, localException);
    }
    localConstructor.setAccessible(true);
    Object localObject = localConstructor.newInstance(new Object[0]);
    return localObject;
  }

  protected Set<String> facebookFieldNamesWithMultipleMappings(List<ReflectionUtils.FieldWithAnnotation<Facebook>> paramList)
  {
    HashMap localHashMap = new HashMap();
    HashSet localHashSet = new HashSet();
    Iterator localIterator1 = paramList.iterator();
    if (localIterator1.hasNext())
    {
      String str = getFacebookFieldName((ReflectionUtils.FieldWithAnnotation)localIterator1.next());
      if (localHashMap.containsKey(str));
      for (int i = ((Integer)localHashMap.get(str)).intValue(); ; i = 0)
      {
        localHashMap.put(str, Integer.valueOf(i + 1));
        break;
      }
    }
    Iterator localIterator2 = localHashMap.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      if (((Integer)localEntry.getValue()).intValue() <= 1)
        continue;
      localHashSet.add(localEntry.getKey());
    }
    return Collections.unmodifiableSet(localHashSet);
  }

  protected String getFacebookFieldName(ReflectionUtils.FieldWithAnnotation<Facebook> paramFieldWithAnnotation)
  {
    String str = ((Facebook)paramFieldWithAnnotation.getAnnotation()).value();
    Field localField = paramFieldWithAnnotation.getField();
    if (StringUtils.isBlank(str))
    {
      if (logger.isLoggable(Level.FINEST))
        logger.finest("No explicit Facebook field name found for " + localField + ", so defaulting to the field name itself (" + localField.getName() + ")");
      str = localField.getName();
    }
    return str;
  }

  protected boolean isEmptyObject(String paramString)
  {
    return "{}".equals(paramString);
  }

  protected void logMultipleMappingFailedForField(String paramString1, ReflectionUtils.FieldWithAnnotation<Facebook> paramFieldWithAnnotation, String paramString2)
  {
    if (!logger.isLoggable(Level.FINER));
    Field localField;
    do
    {
      return;
      localField = paramFieldWithAnnotation.getField();
    }
    while (!logger.isLoggable(Level.FINER));
    logger.finer("Could not map '" + paramString1 + "' to " + localField.getDeclaringClass().getSimpleName() + "." + localField.getName() + ", but continuing on because '" + paramString1 + "' is mapped to multiple fields in " + localField.getDeclaringClass().getSimpleName() + ". JSON is " + paramString2);
  }

  public <T> List<T> toJavaList(String paramString, Class<T> paramClass)
  {
    int i = 1;
    if (paramClass == null)
      throw new FacebookJsonMappingException("You must specify the Java type to map to.");
    Object localObject1 = StringUtils.trimToEmpty(paramString);
    if (StringUtils.isBlank((String)localObject1))
    {
      if (this.jsonMappingErrorHandler.handleMappingError((String)localObject1, paramClass, null))
        return null;
      throw new FacebookJsonMappingException("JSON is an empty string - can't map it.");
    }
    if (((String)localObject1).startsWith("{"))
      if (isEmptyObject((String)localObject1))
      {
        if (logger.isLoggable(Level.FINER))
          logger.finer("Encountered {} when we should've seen []. Mapping the {} as an empty list and moving on...");
        return new ArrayList();
      }
    while (true)
    {
      Object localObject3;
      try
      {
        JsonObject localJsonObject = new JsonObject((String)localObject1);
        String[] arrayOfString = JsonObject.getNames(localJsonObject);
        if (arrayOfString == null)
          break label258;
        if ((arrayOfString.length != i) || (!"data".equals(arrayOfString[0])))
          break label450;
        localObject3 = localJsonObject.get("data");
        if ((i != 0) || ((localObject3 instanceof JsonArray)))
          break label247;
        if (this.jsonMappingErrorHandler.handleMappingError((String)localObject1, paramClass, null))
          return null;
        throw new FacebookJsonMappingException("JSON is an object but is being mapped as a list instead. Offending JSON is '" + (String)localObject1 + "'.");
      }
      catch (JsonException localJsonException)
      {
        if (!this.jsonMappingErrorHandler.handleMappingError((String)localObject1, paramClass, localJsonException))
          break label324;
      }
      return null;
      label247: String str = localObject3.toString();
      localObject1 = str;
      label258: for (Object localObject2 = localObject1; ; localObject2 = localObject1)
        try
        {
          ArrayList localArrayList = new ArrayList();
          JsonArray localJsonArray = new JsonArray(localObject2);
          int j = 0;
          while (j < localJsonArray.length())
          {
            localArrayList.add(toJavaObject(localJsonArray.get(j).toString(), paramClass));
            j++;
            continue;
            label324: throw new FacebookJsonMappingException("Unable to convert Facebook response JSON to a list of " + paramClass.getName() + " instances.  Offending JSON is " + (String)localObject1, localJsonException);
          }
          List localList = Collections.unmodifiableList(localArrayList);
          return localList;
        }
        catch (FacebookJsonMappingException localFacebookJsonMappingException)
        {
          throw localFacebookJsonMappingException;
        }
        catch (Exception localException)
        {
          if (this.jsonMappingErrorHandler.handleMappingError(localObject2, paramClass, localException))
            return null;
          throw new FacebookJsonMappingException("Unable to convert Facebook response JSON to a list of " + paramClass.getName() + " instances", localException);
        }
      label450: i = 0;
    }
  }

  public <T> T toJavaObject(String paramString, Class<T> paramClass)
  {
    if (StringUtils.isBlank(paramString))
    {
      if (this.jsonMappingErrorHandler.handleMappingError(paramString, paramClass, null))
        return null;
      throw new FacebookJsonMappingException("JSON is an empty string - can't map it.");
    }
    if (paramString.startsWith("["))
    {
      if (this.jsonMappingErrorHandler.handleMappingError(paramString, paramClass, null))
        return null;
      throw new FacebookJsonMappingException("JSON is an array but is being mapped as an object - you should map it as a List instead. Offending JSON is '" + paramString + "'.");
    }
    try
    {
      if (paramClass.equals(JsonObject.class))
      {
        JsonObject localJsonObject1 = new JsonObject(paramString);
        return localJsonObject1;
      }
    }
    catch (FacebookJsonMappingException localFacebookJsonMappingException1)
    {
      throw localFacebookJsonMappingException1;
      List localList = ReflectionUtils.findFieldsWithAnnotation(paramClass, Facebook.class);
      localSet = facebookFieldNamesWithMultipleMappings(localList);
      if (localList.size() == 0)
      {
        if (isEmptyObject(paramString))
          return createInstance(paramClass);
        return toPrimitiveJavaType(paramString, paramClass);
      }
      if ("null".equals(paramString))
        return null;
      if ("false".equals(paramString))
      {
        if (logger.isLoggable(Level.FINE))
          logger.fine("Encountered 'false' from Facebook when trying to map to " + paramClass.getSimpleName() + " - mapping null instead.");
      }
      else
      {
        localJsonObject2 = new JsonObject(paramString);
        localObject = createInstance(paramClass);
        if ((localObject instanceof JsonObject))
          return localJsonObject2;
        Iterator localIterator = localList.iterator();
        while (true)
        {
          if (!localIterator.hasNext())
            break label516;
          localFieldWithAnnotation = (ReflectionUtils.FieldWithAnnotation)localIterator.next();
          str = getFacebookFieldName(localFieldWithAnnotation);
          if (localJsonObject2.has(str))
            break;
          if (!logger.isLoggable(Level.FINER))
            continue;
          logger.finer("No JSON value present for '" + str + "', skipping. JSON is '" + paramString + "'.");
        }
      }
    }
    catch (Exception localException1)
    {
      Set localSet;
      JsonObject localJsonObject2;
      Object localObject;
      ReflectionUtils.FieldWithAnnotation localFieldWithAnnotation;
      String str;
      while (this.jsonMappingErrorHandler.handleMappingError(paramString, paramClass, localException1))
      {
        return null;
        localFieldWithAnnotation.getField().setAccessible(true);
        boolean bool = localSet.contains(str);
        if (bool)
        {
          try
          {
            localFieldWithAnnotation.getField().set(localObject, toJavaType(localFieldWithAnnotation, localJsonObject2, str));
          }
          catch (FacebookJsonMappingException localFacebookJsonMappingException2)
          {
            logMultipleMappingFailedForField(str, localFieldWithAnnotation, paramString);
          }
          catch (JsonException localJsonException)
          {
            logMultipleMappingFailedForField(str, localFieldWithAnnotation, paramString);
          }
          continue;
        }
        try
        {
          localFieldWithAnnotation.getField().set(localObject, toJavaType(localFieldWithAnnotation, localJsonObject2, str));
        }
        catch (Exception localException2)
        {
        }
        if (this.jsonMappingErrorHandler.handleMappingError(paramString, paramClass, localException2))
          continue;
        throw localException2;
        label516: return localObject;
      }
      throw new FacebookJsonMappingException("Unable to map JSON to Java. Offending JSON is '" + paramString + "'.", localException1);
    }
    return null;
  }

  protected Object toJavaType(ReflectionUtils.FieldWithAnnotation<Facebook> paramFieldWithAnnotation, JsonObject paramJsonObject, String paramString)
  {
    Class localClass = paramFieldWithAnnotation.getField().getType();
    Object localObject = paramJsonObject.get(paramString);
    if (JsonObject.NULL.equals(localObject))
      return null;
    if (String.class.equals(localClass))
    {
      if (((localObject instanceof JsonArray)) && (((JsonArray)localObject).length() == 0))
      {
        if (logger.isLoggable(Level.FINER))
          logger.finer("Coercing an empty JSON array to an empty string for " + paramFieldWithAnnotation);
        return "";
      }
      return localObject.toString();
    }
    if ((Integer.class.equals(localClass)) || (Integer.TYPE.equals(localClass)))
      return new Integer(paramJsonObject.getInt(paramString));
    if ((Boolean.class.equals(localClass)) || (Boolean.TYPE.equals(localClass)))
      return new Boolean(paramJsonObject.getBoolean(paramString));
    if ((Long.class.equals(localClass)) || (Long.TYPE.equals(localClass)))
      return new Long(paramJsonObject.getLong(paramString));
    if ((Double.class.equals(localClass)) || (Double.TYPE.equals(localClass)))
      return new Double(paramJsonObject.getDouble(paramString));
    if ((Float.class.equals(localClass)) || (Float.TYPE.equals(localClass)))
      return Float.valueOf(new BigDecimal(paramJsonObject.getString(paramString)).floatValue());
    if (BigInteger.class.equals(localClass))
      return new BigInteger(paramJsonObject.getString(paramString));
    if (BigDecimal.class.equals(localClass))
      return new BigDecimal(paramJsonObject.getString(paramString));
    if (List.class.equals(localClass))
      return toJavaList(localObject.toString(), ReflectionUtils.getFirstParameterizedTypeArgument(paramFieldWithAnnotation.getField()));
    String str = localObject.toString();
    if ((Post.Comments.class.isAssignableFrom(localClass)) && ((localObject instanceof JsonArray)))
    {
      if (logger.isLoggable(Level.FINE))
        logger.fine("Encountered comment array '" + str + "' but expected a " + Post.Comments.class.getSimpleName() + " object instead.  Working around that " + "by coercing into an empty " + Post.Comments.class.getSimpleName() + " instance...");
      JsonObject localJsonObject = new JsonObject();
      localJsonObject.put("count", 0);
      localJsonObject.put("data", new JsonArray());
      str = localJsonObject.toString();
    }
    return toJavaObject(str, localClass);
  }

  public String toJson(Object paramObject)
  {
    return toJsonInternal(paramObject, false).toString();
  }

  public String toJson(Object paramObject, boolean paramBoolean)
  {
    return toJsonInternal(paramObject, paramBoolean).toString();
  }

  protected Object toJsonInternal(Object paramObject, boolean paramBoolean)
  {
    if (paramObject == null)
      paramObject = JsonObject.NULL;
    do
    {
      return paramObject;
      if ((paramObject instanceof List))
      {
        JsonArray localJsonArray = new JsonArray();
        Iterator localIterator1 = ((List)paramObject).iterator();
        while (localIterator1.hasNext())
          localJsonArray.put(toJsonInternal(localIterator1.next(), paramBoolean));
        return localJsonArray;
      }
      if (!(paramObject instanceof Map))
        continue;
      JsonObject localJsonObject1 = new JsonObject();
      Iterator localIterator2 = ((Map)paramObject).entrySet().iterator();
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator2.next();
        if (!(localEntry.getKey() instanceof String))
          throw new FacebookJsonMappingException("Your Map keys must be of type " + String.class + " in order to be converted to JSON.  Offending map is " + paramObject);
        try
        {
          localJsonObject1.put((String)localEntry.getKey(), toJsonInternal(localEntry.getValue(), paramBoolean));
        }
        catch (JsonException localJsonException)
        {
          throw new FacebookJsonMappingException("Unable to process value '" + localEntry.getValue() + "' for key '" + localEntry.getKey() + "' in Map " + paramObject, localJsonException);
        }
      }
      return localJsonObject1;
    }
    while (ReflectionUtils.isPrimitive(paramObject));
    if ((paramObject instanceof BigInteger))
      return Long.valueOf(((BigInteger)paramObject).longValue());
    if ((paramObject instanceof BigDecimal))
      return Double.valueOf(((BigDecimal)paramObject).doubleValue());
    List localList = ReflectionUtils.findFieldsWithAnnotation(paramObject.getClass(), Facebook.class);
    JsonObject localJsonObject2 = new JsonObject();
    Set localSet = facebookFieldNamesWithMultipleMappings(localList);
    if (localSet.size() > 0)
      throw new FacebookJsonMappingException("Unable to convert to JSON because multiple @" + Facebook.class.getSimpleName() + " annotations for the same name are present: " + localSet);
    Iterator localIterator3 = localList.iterator();
    while (localIterator3.hasNext())
    {
      ReflectionUtils.FieldWithAnnotation localFieldWithAnnotation = (ReflectionUtils.FieldWithAnnotation)localIterator3.next();
      String str = getFacebookFieldName(localFieldWithAnnotation);
      localFieldWithAnnotation.getField().setAccessible(true);
      try
      {
        Object localObject = localFieldWithAnnotation.getField().get(paramObject);
        if ((paramBoolean) && (localObject == null))
          continue;
        localJsonObject2.put(str, toJsonInternal(localObject, paramBoolean));
      }
      catch (Exception localException)
      {
        throw new FacebookJsonMappingException("Unable to process field '" + str + "' for " + paramObject.getClass(), localException);
      }
    }
    return localJsonObject2;
  }

  protected <T> T toPrimitiveJavaType(String paramString, Class<T> paramClass)
  {
    if (String.class.equals(paramClass))
    {
      if ((paramString.length() > 1) && (paramString.startsWith("\"")) && (paramString.endsWith("\"")))
      {
        String str = paramString.replaceFirst("\"", "");
        paramString = str.substring(0, -1 + str.length());
      }
      return paramString;
    }
    if ((Integer.class.equals(paramClass)) || (Integer.TYPE.equals(paramClass)))
      return new Integer(paramString);
    if ((Boolean.class.equals(paramClass)) || (Boolean.TYPE.equals(paramClass)))
      return new Boolean(paramString);
    if ((Long.class.equals(paramClass)) || (Long.TYPE.equals(paramClass)))
      return new Long(paramString);
    if ((Double.class.equals(paramClass)) || (Double.TYPE.equals(paramClass)))
      return new Double(paramString);
    if ((Float.class.equals(paramClass)) || (Float.TYPE.equals(paramClass)))
      return new Float(paramString);
    if (BigInteger.class.equals(paramClass))
      return new BigInteger(paramString);
    if (BigDecimal.class.equals(paramClass))
      return new BigDecimal(paramString);
    if (this.jsonMappingErrorHandler.handleMappingError(paramString, paramClass, null))
      return null;
    throw new FacebookJsonMappingException("Don't know how to map JSON to " + paramClass + ". Are you sure you're mapping to the right class? " + "Offending JSON is '" + paramString + "'.");
  }

  public static abstract interface JsonMappingErrorHandler
  {
    public abstract boolean handleMappingError(String paramString, Class<?> paramClass, Exception paramException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.DefaultJsonMapper
 * JD-Core Version:    0.6.0
 */