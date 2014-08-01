package org.apache.commons.logging;

import java.security.PrivilegedAction;

class LogFactory$2
  implements PrivilegedAction
{
  private final ClassLoader val$classLoader;
  private final String val$factoryClass;

  public Object run()
  {
    return LogFactory.createFactory(this.val$factoryClass, this.val$classLoader);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory.2
 * JD-Core Version:    0.6.0
 */