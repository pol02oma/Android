package org.apache.commons.logging.impl;

import java.security.PrivilegedAction;

class LogFactoryImpl$2
  implements PrivilegedAction
{
  private final String val$def;
  private final String val$key;

  public Object run()
  {
    return System.getProperty(this.val$key, this.val$def);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogFactoryImpl.2
 * JD-Core Version:    0.6.0
 */