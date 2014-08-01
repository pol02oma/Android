package org.apache.commons.logging;

import java.security.PrivilegedAction;

class LogFactory$1
  implements PrivilegedAction
{
  public Object run()
  {
    return LogFactory.directGetContextClassLoader();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory.1
 * JD-Core Version:    0.6.0
 */