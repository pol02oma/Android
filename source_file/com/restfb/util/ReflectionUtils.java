package com.restfb.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ReflectionUtils
{
  private static final Map<ClassAnnotationCacheKey, List<?>> FIELDS_WITH_ANNOTATION_CACHE = Collections.synchronizedMap(new HashMap());

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) && (paramObject2 == null))
      return true;
    if ((paramObject1 == null) || (paramObject2 == null))
      return false;
    if ((!paramObject1.getClass().isInstance(paramObject2)) && (!paramObject2.getClass().isInstance(paramObject1)))
      return false;
    HashSet localHashSet = new HashSet(getAccessors(paramObject1.getClass()));
    localHashSet.retainAll(getAccessors(paramObject2.getClass()));
    Iterator localIterator = localHashSet.iterator();
    Method localMethod;
    if (localIterator.hasNext())
      localMethod = (Method)localIterator.next();
    Object localObject1;
    Object localObject2;
    do
    {
      try
      {
        localObject1 = localMethod.invoke(paramObject1, new Object[0]);
        localObject2 = localMethod.invoke(paramObject2, new Object[0]);
        if (localObject1 != null)
          continue;
        if (localObject2 == null)
          break;
        continue;
        boolean bool = localObject1.equals(localObject2);
        if (bool)
          break;
        return false;
      }
      catch (Exception localException)
      {
        throw new IllegalStateException("Unable to reflectively invoke " + localMethod, localException);
      }
      return true;
    }
    while ((localObject1 != null) && (localObject2 != null));
    return false;
  }

  public static <T extends Annotation> List<FieldWithAnnotation<T>> findFieldsWithAnnotation(Class<?> paramClass, Class<T> paramClass1)
  {
    ClassAnnotationCacheKey localClassAnnotationCacheKey = new ClassAnnotationCacheKey(paramClass, paramClass1, null);
    List localList1 = (List)FIELDS_WITH_ANNOTATION_CACHE.get(localClassAnnotationCacheKey);
    if (localList1 != null)
      return localList1;
    ArrayList localArrayList = new ArrayList();
    while (!Object.class.equals(paramClass))
    {
      for (Field localField : paramClass.getDeclaredFields())
      {
        Annotation localAnnotation = localField.getAnnotation(paramClass1);
        if (localAnnotation == null)
          continue;
        localArrayList.add(new FieldWithAnnotation(localField, localAnnotation));
      }
      paramClass = paramClass.getSuperclass();
    }
    List localList2 = Collections.unmodifiableList(localArrayList);
    FIELDS_WITH_ANNOTATION_CACHE.put(localClassAnnotationCacheKey, localList2);
    return localList2;
  }

  public static List<Method> getAccessors(Class<?> paramClass)
  {
    if (paramClass == null)
      throw new IllegalArgumentException("The 'clazz' parameter cannot be null.");
    ArrayList localArrayList = new ArrayList();
    for (Method localMethod : paramClass.getMethods())
    {
      String str = localMethod.getName();
      if (("getClass".equals(str)) || ("hashCode".equals(str)) || (localMethod.getReturnType() == null) || (Void.class.equals(localMethod.getReturnType())) || (localMethod.getParameterTypes().length != 0) || (((!str.startsWith("get")) || (str.length() <= 3)) && ((!str.startsWith("is")) || (str.length() <= 2)) && ((!str.startsWith("has")) || (str.length() <= 3))))
        continue;
      localArrayList.add(localMethod);
    }
    Collections.sort(localArrayList, new Comparator()
    {
      public int compare(Method paramMethod1, Method paramMethod2)
      {
        return paramMethod1.getName().compareTo(paramMethod2.getName());
      }
    });
    return Collections.unmodifiableList(localArrayList);
  }

  public static Class<?> getFirstParameterizedTypeArgument(Field paramField)
  {
    Type localType1 = paramField.getGenericType();
    if (!(localType1 instanceof ParameterizedType))
      return null;
    Type localType2 = ((ParameterizedType)localType1).getActualTypeArguments()[0];
    if ((localType2 instanceof Class));
    for (Class localClass = (Class)localType2; ; localClass = null)
      return localClass;
  }

  public static int hashCode(Object paramObject)
  {
    int i = 0;
    if (paramObject == null);
    Iterator localIterator;
    do
    {
      return i;
      localIterator = getAccessors(paramObject.getClass()).iterator();
      i = 17;
    }
    while (!localIterator.hasNext());
    Method localMethod = (Method)localIterator.next();
    while (true)
    {
      try
      {
        Object localObject = localMethod.invoke(paramObject, new Object[0]);
        if (localObject != null)
        {
          int k = i * 31;
          int m = localObject.hashCode();
          j = m + k;
          i = j;
        }
      }
      catch (Exception localException)
      {
        throw new IllegalStateException("Unable to reflectively invoke " + localMethod + " on " + paramObject, localException);
      }
      int j = i;
    }
  }

  public static boolean isPrimitive(Object paramObject)
  {
    if (paramObject == null);
    Class localClass;
    do
    {
      return false;
      localClass = paramObject.getClass();
    }
    while ((!(paramObject instanceof String)) && (!(paramObject instanceof Integer)) && (!Integer.TYPE.equals(localClass)) && (!(paramObject instanceof Boolean)) && (!Boolean.TYPE.equals(localClass)) && (!(paramObject instanceof Long)) && (!Long.TYPE.equals(localClass)) && (!(paramObject instanceof Double)) && (!Double.TYPE.equals(localClass)) && (!(paramObject instanceof Float)) && (!Float.TYPE.equals(localClass)) && (!(paramObject instanceof Byte)) && (!Byte.TYPE.equals(localClass)) && (!(paramObject instanceof Short)) && (!Short.TYPE.equals(localClass)) && (!(paramObject instanceof Character)) && (!Character.TYPE.equals(localClass)));
    return true;
  }

  public static String toString(Object paramObject)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramObject.getClass().getSimpleName());
    localStringBuilder.append("[");
    Iterator localIterator = getAccessors(paramObject.getClass()).iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Method localMethod = (Method)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        try
        {
          String str = localMethod.getName();
          if (!str.startsWith("is"))
            break label211;
          j = 2;
          localStringBuilder.append(str.substring(j, j + 1).toLowerCase() + str.substring(j + 1));
          localStringBuilder.append("=");
          localStringBuilder.append(localMethod.invoke(paramObject, new Object[0]));
        }
        catch (Exception localException)
        {
          throw new IllegalStateException("Unable to reflectively invoke " + localMethod + " on " + paramObject.getClass(), localException);
        }
        localStringBuilder.append(" ");
        continue;
        label211: int j = 3;
      }
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }

  private static final class ClassAnnotationCacheKey
  {
    private final Class<? extends Annotation> annotation;
    private final Class<?> clazz;

    private ClassAnnotationCacheKey(Class<?> paramClass, Class<? extends Annotation> paramClass1)
    {
      this.clazz = paramClass;
      this.annotation = paramClass1;
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject);
      ClassAnnotationCacheKey localClassAnnotationCacheKey;
      do
        while (true)
        {
          return true;
          if (paramObject == null)
            return false;
          if (getClass() != paramObject.getClass())
            return false;
          localClassAnnotationCacheKey = (ClassAnnotationCacheKey)paramObject;
          if (this.annotation == null)
          {
            if (localClassAnnotationCacheKey.annotation != null)
              return false;
          }
          else if (!this.annotation.equals(localClassAnnotationCacheKey.annotation))
            return false;
          if (this.clazz != null)
            break;
          if (localClassAnnotationCacheKey.clazz != null)
            return false;
        }
      while (this.clazz.equals(localClassAnnotationCacheKey.clazz));
      return false;
    }

    public int hashCode()
    {
      int i;
      int j;
      int k;
      if (this.annotation == null)
      {
        i = 0;
        j = 31 * (i + 31);
        Class localClass = this.clazz;
        k = 0;
        if (localClass != null)
          break label45;
      }
      while (true)
      {
        return j + k;
        i = this.annotation.hashCode();
        break;
        label45: k = this.clazz.hashCode();
      }
    }
  }

  public static class FieldWithAnnotation<T extends Annotation>
  {
    private T annotation;
    private Field field;

    public FieldWithAnnotation(Field paramField, T paramT)
    {
      this.field = paramField;
      this.annotation = paramT;
    }

    public T getAnnotation()
    {
      return this.annotation;
    }

    public Field getField()
    {
      return this.field;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = this.field.getDeclaringClass().getName();
      arrayOfObject[1] = this.field.getName();
      arrayOfObject[2] = this.field.getType();
      arrayOfObject[3] = this.annotation;
      return String.format("Field %s.%s (%s): %s", arrayOfObject);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.util.ReflectionUtils
 * JD-Core Version:    0.6.0
 */