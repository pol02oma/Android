package org.apache.commons.logging.impl;

import java.security.PrivilegedAction;

class LogFactoryImpl$3
  implements PrivilegedAction
{
  private final LogFactoryImpl this$0;
  private final ClassLoader val$cl;

  public Object run()
  {
    return this.val$cl.getParent();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.impl.LogFactoryImpl.3
 * JD-Core Version:    0.6.0
 */