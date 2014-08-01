package org.apache.commons.logging;

import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.Enumeration;

class LogFactory$4
  implements PrivilegedAction
{
  private final ClassLoader val$loader;
  private final String val$name;

  public Object run()
  {
    try
    {
      if (this.val$loader != null)
        return this.val$loader.getResources(this.val$name);
      Enumeration localEnumeration = ClassLoader.getSystemResources(this.val$name);
      return localEnumeration;
    }
    catch (IOException localIOException)
    {
      if (LogFactory.isDiagnosticsEnabled())
      {
        LogFactory.access$000("Exception while trying to find configuration file " + this.val$name + ":" + localIOException.getMessage());
        return null;
      }
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory.4
 * JD-Core Version:    0.6.0
 */