package org.apache.james.mime4j.samples.dom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.james.mime4j.codec.DecodeMonitor;

public final class LoggingMonitor extends DecodeMonitor
{
  public static DecodeMonitor MONITOR;
  private static Log log = LogFactory.getLog(LoggingMonitor.class);

  static
  {
    MONITOR = new LoggingMonitor();
  }

  public boolean isListening()
  {
    return true;
  }

  public boolean warn(String paramString1, String paramString2)
  {
    if (paramString2 != null)
      log.warn(paramString1 + "; " + paramString2);
    while (true)
    {
      return false;
      log.warn(paramString1);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.samples.dom.LoggingMonitor
 * JD-Core Version:    0.6.0
 */