package org.apache.commons.logging;

import java.security.PrivilegedAction;

class LogFactory$3
  implements PrivilegedAction
{
  private final ClassLoader val$loader;
  private final String val$name;

  public Object run()
  {
    if (this.val$loader != null)
      return this.val$loader.getResourceAsStream(this.val$name);
    return ClassLoader.getSystemResourceAsStream(this.val$name);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory.3
 * JD-Core Version:    0.6.0
 */