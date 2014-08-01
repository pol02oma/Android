package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.util.LangUtils;

@NotThreadSafe
public class BasicHeaderElement
  implements HeaderElement, Cloneable
{
  private final String name;
  private final NameValuePair[] parameters;
  private final String value;

  public BasicHeaderElement(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }

  public BasicHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name may not be null");
    this.name = paramString1;
    this.value = paramString2;
    if (paramArrayOfNameValuePair != null)
    {
      this.parameters = paramArrayOfNameValuePair;
      return;
    }
    this.parameters = new NameValuePair[0];
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    while (true)
    {
      return true;
      if (!(paramObject instanceof Cloneable))
        break;
      BasicHeaderElement localBasicHeaderElement = (BasicHeaderElement)paramObject;
      if ((!this.name.equals(localBasicHeaderElement.name)) || (!LangUtils.equals(this.value, localBasicHeaderElement.value)) || (!LangUtils.equals(this.parameters, localBasicHeaderElement.parameters)))
        return false;
    }
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public NameValuePair getParameter(int paramInt)
  {
    return this.parameters[paramInt];
  }

  public NameValuePair getParameterByName(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    for (int i = 0; ; i++)
    {
      int j = this.parameters.length;
      Object localObject = null;
      if (i < j)
      {
        NameValuePair localNameValuePair = this.parameters[i];
        if (!localNameValuePair.getName().equalsIgnoreCase(paramString))
          continue;
        localObject = localNameValuePair;
      }
      return localObject;
    }
  }

  public int getParameterCount()
  {
    return this.parameters.length;
  }

  public NameValuePair[] getParameters()
  {
    return (NameValuePair[])this.parameters.clone();
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int i = LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
    for (int j = 0; j < this.parameters.length; j++)
      i = LangUtils.hashCode(i, this.parameters[j]);
    return i;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    if (this.value != null)
    {
      localStringBuilder.append("=");
      localStringBuilder.append(this.value);
    }
    for (int i = 0; i < this.parameters.length; i++)
    {
      localStringBuilder.append("; ");
      localStringBuilder.append(this.parameters[i]);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.message.BasicHeaderElement
 * JD-Core Version:    0.6.0
 */