package com.appbuilder.u846253p1176378;

 enum DownloadStatus
{
  static
  {
    FAILED = new DownloadStatus("FAILED", 2);
    DownloadStatus[] arrayOfDownloadStatus = new DownloadStatus[3];
    arrayOfDownloadStatus[0] = NOT_DOWNLOADED;
    arrayOfDownloadStatus[1] = SUCCESS;
    arrayOfDownloadStatus[2] = FAILED;
    $VALUES = arrayOfDownloadStatus;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.DownloadStatus
 * JD-Core Version:    0.6.0
 */