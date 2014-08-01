package com.restfb;

import com.restfb.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Parameter
{
  public final String name;
  public final String value;

  private Parameter(String paramString, Object paramObject, JsonMapper paramJsonMapper)
  {
    if ((StringUtils.isBlank(paramString)) || (paramObject == null))
      throw new IllegalArgumentException(Parameter.class + " instances must have a non-blank name and non-null value.");
    if (paramJsonMapper == null)
      throw new IllegalArgumentException("Provided " + JsonMapper.class + " must not be null.");
    this.name = StringUtils.trimToEmpty(paramString).toLowerCase();
    if ((paramObject instanceof Date));
    for (String str = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(paramObject); ; str = paramJsonMapper.toJson(paramObject))
    {
      this.value = str;
      return;
    }
  }

  public static Parameter with(String paramString, Object paramObject)
  {
    return with(paramString, paramObject, new DefaultJsonMapper());
  }

  public static Parameter with(String paramString, Object paramObject, JsonMapper paramJsonMapper)
  {
    return new Parameter(paramString, paramObject, paramJsonMapper);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    Parameter localParameter;
    do
    {
      do
        return false;
      while (!getClass().equals(paramObject.getClass()));
      localParameter = (Parameter)paramObject;
    }
    while (((this.name != localParameter.name) && (!this.name.equals(localParameter.name))) || ((this.value != localParameter.value) && (!this.value.equals(localParameter.value))));
    return true;
  }

  public int hashCode()
  {
    return 41 * (259 + this.name.hashCode()) + this.value.hashCode();
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.name;
    arrayOfObject[1] = this.value;
    return String.format("Parameter[%s=%s]", arrayOfObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.Parameter
 * JD-Core Version:    0.6.0
 */