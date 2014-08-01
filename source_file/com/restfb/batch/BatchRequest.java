package com.restfb.batch;

import com.restfb.Facebook;
import com.restfb.Parameter;
import com.restfb.util.ReflectionUtils;
import com.restfb.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BatchRequest
{

  @Facebook("attached_files")
  private String attachedFiles;

  @Facebook
  private String body;

  @Facebook("depends_on")
  private String dependsOn;

  @Facebook
  private List<BatchHeader> headers = new ArrayList();

  @Facebook
  private String method;

  @Facebook
  private String name;

  @Facebook("omit_response_on_success")
  private boolean omitResponseOnSuccess;

  @Facebook("relative_url")
  private String relativeUrl;

  protected BatchRequest(String paramString1, List<Parameter> paramList1, String paramString2, List<BatchHeader> paramList, List<Parameter> paramList2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    if (StringUtils.isBlank(paramString1))
      throw new IllegalArgumentException("The 'relativeUrl' parameter is required.");
    this.relativeUrl = paramString1;
    this.method = paramString2;
    this.headers = paramList;
    this.attachedFiles = paramString3;
    this.dependsOn = paramString4;
    this.name = paramString5;
    this.omitResponseOnSuccess = paramBoolean;
    if (paramList1.size() > 0)
      if (this.relativeUrl.indexOf("?") != -1)
        break label143;
    label143: for (String str = "%s?%s"; ; str = "%s&%s")
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.relativeUrl;
      arrayOfObject[1] = generateParameterString(paramList1);
      this.relativeUrl = String.format(str, arrayOfObject);
      this.body = generateParameterString(paramList2);
      return;
    }
  }

  public boolean equals(Object paramObject)
  {
    return ReflectionUtils.equals(this, paramObject);
  }

  protected String generateParameterString(List<Parameter> paramList)
  {
    if (paramList == null)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Parameter localParameter = (Parameter)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(StringUtils.urlEncode(localParameter.name));
        localStringBuilder.append("=");
        localStringBuilder.append(StringUtils.urlEncode(localParameter.value));
        break;
        localStringBuilder.append("&");
      }
    }
    return localStringBuilder.toString();
  }

  public String getAttachedFiles()
  {
    return this.attachedFiles;
  }

  public String getBody()
  {
    return this.body;
  }

  public String getDependsOn()
  {
    return this.dependsOn;
  }

  public List<BatchHeader> getHeaders()
  {
    return Collections.unmodifiableList(this.headers);
  }

  public String getMethod()
  {
    return this.method;
  }

  public String getName()
  {
    return this.name;
  }

  public String getRelativeUrl()
  {
    return this.relativeUrl;
  }

  public int hashCode()
  {
    return ReflectionUtils.hashCode(this);
  }

  public boolean isOmitResponseOnSuccess()
  {
    return this.omitResponseOnSuccess;
  }

  public String toString()
  {
    return ReflectionUtils.toString(this);
  }

  public static class BatchRequestBuilder
  {
    private String attachedFiles;
    private List<Parameter> bodyParameters = new ArrayList();
    private String dependsOn;
    private List<BatchHeader> headers = new ArrayList();
    private String method = "GET";
    private String name;
    private boolean omitResponseOnSuccess;
    private List<Parameter> parameters = new ArrayList();
    private String relativeUrl;

    public BatchRequestBuilder(String paramString)
    {
      this.relativeUrl = paramString;
    }

    public BatchRequestBuilder attachedFiles(String paramString)
    {
      this.attachedFiles = paramString;
      return this;
    }

    public BatchRequestBuilder body(Parameter[] paramArrayOfParameter)
    {
      this.bodyParameters.clear();
      this.bodyParameters.addAll(Arrays.asList(paramArrayOfParameter));
      return this;
    }

    public BatchRequest build()
    {
      return new BatchRequest(this.relativeUrl, this.parameters, this.method, this.headers, this.bodyParameters, this.attachedFiles, this.dependsOn, this.name, this.omitResponseOnSuccess);
    }

    public BatchRequestBuilder dependsOn(String paramString)
    {
      this.dependsOn = paramString;
      return this;
    }

    public BatchRequestBuilder headers(BatchHeader[] paramArrayOfBatchHeader)
    {
      this.headers.clear();
      this.headers.addAll(Arrays.asList(paramArrayOfBatchHeader));
      return this;
    }

    public BatchRequestBuilder method(String paramString)
    {
      this.method = paramString;
      return this;
    }

    public BatchRequestBuilder name(String paramString)
    {
      this.name = paramString;
      return this;
    }

    public BatchRequestBuilder omitResponseOnSuccess(boolean paramBoolean)
    {
      this.omitResponseOnSuccess = paramBoolean;
      return this;
    }

    public BatchRequestBuilder parameters(Parameter[] paramArrayOfParameter)
    {
      this.parameters.clear();
      this.parameters.addAll(Arrays.asList(paramArrayOfParameter));
      return this;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.batch.BatchRequest
 * JD-Core Version:    0.6.0
 */