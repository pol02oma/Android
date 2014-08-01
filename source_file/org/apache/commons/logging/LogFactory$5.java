package org.apache.commons.logging;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.PrivilegedAction;
import java.util.Properties;

class LogFactory$5
  implements PrivilegedAction
{
  private final URL val$url;

  public Object run()
  {
    try
    {
      InputStream localInputStream = this.val$url.openStream();
      if (localInputStream != null)
      {
        Properties localProperties = new Properties();
        localProperties.load(localInputStream);
        localInputStream.close();
        return localProperties;
      }
    }
    catch (IOException localIOException)
    {
      if (LogFactory.isDiagnosticsEnabled())
        LogFactory.access$000("Unable to read URL " + this.val$url);
    }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory.5
 * JD-Core Version:    0.6.0
 */