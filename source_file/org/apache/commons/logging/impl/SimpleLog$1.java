package org.apache.commons.logging.impl;

import java.security.PrivilegedAction;

class SimpleLog$1
  implements PrivilegedAction
{
  private final String val$name;

  public Object run()
  {
    ClassLoader localClassLoader = SimpleLog.access$000();
    if (localClassLoader != null)
      return localClassLoader.getResourceAsStream(this.val$name);
    return ClassLoader.getSystemResourceAsStream(this.val$name);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.SimpleLog.1
 * JD-Core Version:    0.6.0
 */