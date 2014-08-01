package com.appbuilder.sdk.android;

import java.io.Serializable;

public class AppAdvData
  implements Serializable
{
  public static final int AD_HIDDEN = 2;
  public static final int AD_VISIBLE = 1;
  private static final long serialVersionUID = 1L;
  private int advAdSpaceId = 0;
  private String advApId = "";
  private String advContent = "";
  private int advPublisherId = 0;
  private String advRedirect = "";
  private String advSessionUid = "";
  private int advState = 1;
  private String advType = "";

  public int getAdvAdSpaceId()
  {
    return this.advAdSpaceId;
  }

  public String getAdvApId()
  {
    return this.advApId;
  }

  public String getAdvContent()
  {
    return this.advContent;
  }

  public int getAdvPublisherId()
  {
    return this.advPublisherId;
  }

  public String getAdvRedirect()
  {
    return this.advRedirect;
  }

  public String getAdvSessionUid()
  {
    return this.advSessionUid;
  }

  public int getAdvState()
  {
    return this.advState;
  }

  public String getAdvType()
  {
    return this.advType;
  }

  public void setAdvAdSpaceId(int paramInt)
  {
    this.advAdSpaceId = paramInt;
  }

  public void setAdvAdSpaceId(String paramString)
  {
    this.advAdSpaceId = Integer.parseInt(paramString);
  }

  public void setAdvApId(String paramString)
  {
    this.advApId = paramString;
  }

  public void setAdvContent(String paramString)
  {
    this.advContent = paramString;
  }

  public void setAdvPublisherId(int paramInt)
  {
    this.advPublisherId = paramInt;
  }

  public void setAdvPublisherId(String paramString)
  {
    this.advPublisherId = Integer.parseInt(paramString);
  }

  public void setAdvRedirect(String paramString)
  {
    this.advRedirect = paramString;
  }

  public void setAdvState(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 2))
      paramInt = 1;
    this.advState = paramInt;
  }

  public void setAdvType(String paramString)
  {
    this.advType = paramString;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.AppAdvData
 * JD-Core Version:    0.6.0
 */