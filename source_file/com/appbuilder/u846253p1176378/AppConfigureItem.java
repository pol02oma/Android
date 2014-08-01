package com.appbuilder.u846253p1176378;

import java.io.Serializable;

class AppConfigureItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected DownloadStatus mDownloadStatus = DownloadStatus.NOT_DOWNLOADED;

  public DownloadStatus getDownloadStatus()
  {
    return this.mDownloadStatus;
  }

  public void setDownloadStatus(DownloadStatus paramDownloadStatus)
  {
    this.mDownloadStatus = paramDownloadStatus;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.AppConfigureItem
 * JD-Core Version:    0.6.0
 */