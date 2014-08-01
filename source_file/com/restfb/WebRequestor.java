package com.restfb;

import com.restfb.util.StringUtils;
import java.io.IOException;

public abstract interface WebRequestor
{
  public abstract Response executeGet(String paramString)
    throws IOException;

  public abstract Response executePost(String paramString1, String paramString2)
    throws IOException;

  public abstract Response executePost(String paramString1, String paramString2, BinaryAttachment[] paramArrayOfBinaryAttachment)
    throws IOException;

  public static class Response
  {
    private String body;
    private Integer statusCode;

    public Response(Integer paramInteger, String paramString)
    {
      this.statusCode = paramInteger;
      this.body = StringUtils.trimToEmpty(paramString);
    }

    public String getBody()
    {
      return this.body;
    }

    public Integer getStatusCode()
    {
      return this.statusCode;
    }

    public String toString()
    {
      if (StringUtils.isBlank(getBody()))
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = getStatusCode();
        return String.format("HTTP status code %d and an empty response body.", arrayOfObject2);
      }
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = getStatusCode();
      arrayOfObject1[1] = getBody();
      return String.format("HTTP status code %d and response body: %s", arrayOfObject1);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.WebRequestor
 * JD-Core Version:    0.6.0
 */