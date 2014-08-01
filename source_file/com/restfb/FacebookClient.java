package com.restfb;

import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchResponse;
import com.restfb.util.ReflectionUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract interface FacebookClient
{
  public abstract List<AccessToken> convertSessionKeysToAccessTokens(String paramString1, String paramString2, String[] paramArrayOfString);

  public abstract boolean deleteObject(String paramString);

  public abstract List<BatchResponse> executeBatch(List<BatchRequest> paramList, List<BinaryAttachment> paramList1);

  public abstract List<BatchResponse> executeBatch(BatchRequest[] paramArrayOfBatchRequest);

  public abstract <T> T executeMultiquery(Map<String, String> paramMap, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public abstract <T> List<T> executeQuery(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public abstract <T> Connection<T> fetchConnection(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public abstract <T> Connection<T> fetchConnectionPage(String paramString, Class<T> paramClass);

  public abstract <T> T fetchObject(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public abstract <T> T fetchObjects(List<String> paramList, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public abstract JsonMapper getJsonMapper();

  public abstract WebRequestor getWebRequestor();

  public abstract <T> T publish(String paramString, Class<T> paramClass, BinaryAttachment paramBinaryAttachment, Parameter[] paramArrayOfParameter);

  public abstract <T> T publish(String paramString, Class<T> paramClass, Parameter[] paramArrayOfParameter);

  public static class AccessToken
  {

    @Facebook("access_token")
    private String accessToken;

    @Facebook
    private Long expires;

    public boolean equals(Object paramObject)
    {
      return ReflectionUtils.equals(this, paramObject);
    }

    public String getAccessToken()
    {
      return this.accessToken;
    }

    public Date getExpires()
    {
      if (this.expires == null)
        return null;
      return new Date(1000L * this.expires.longValue());
    }

    public int hashCode()
    {
      return ReflectionUtils.hashCode(this);
    }

    public String toString()
    {
      return ReflectionUtils.toString(this);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.FacebookClient
 * JD-Core Version:    0.6.0
 */