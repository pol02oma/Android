package com.appbuilder.u846253p1176378;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class Logger
{
  private File logFile;
  private FileOutputStream logStream;

  Logger(String paramString)
    throws IOException
  {
    this.logFile = new File(paramString);
    File localFile = new File(this.logFile.getParent());
    if (!localFile.exists())
      localFile.mkdirs();
    if (!this.logFile.exists())
      this.logFile.createNewFile();
    this.logStream = new FileOutputStream(this.logFile);
  }

  void addLogMessage(String paramString)
    throws IOException
  {
    if (paramString != null)
    {
      String str = paramString + "\n";
      this.logStream.write(str.getBytes());
      this.logStream.flush();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.Logger
 * JD-Core Version:    0.6.0
 */