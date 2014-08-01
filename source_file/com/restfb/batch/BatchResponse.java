package com.restfb.batch;

import com.restfb.Facebook;
import com.restfb.util.ReflectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BatchResponse
{

  @Facebook
  private String body;

  @Facebook
  private Integer code;

  @Facebook
  private List<BatchHeader> headers = new ArrayList();

  protected BatchResponse()
  {
  }

  public BatchResponse(Integer paramInteger, List<BatchHeader> paramList, String paramString)
  {
    this.code = paramInteger;
    this.body = paramString;
    if (paramList != null)
      this.headers.addAll(paramList);
  }

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
  }

  public String getBody()
  {
    return this.body;
  }

  public Integer getCode()
  {
    return this.code;
  }

  public List<BatchHeader> getHeaders()
  {
    return Collections.unmodifiableList(this.headers);
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

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.batch.BatchResponse
 * JD-Core Version:    0.6.0
 */