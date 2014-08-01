package org.apache.james.mime4j.codec;

public class DecodeMonitor
{
  public static final DecodeMonitor SILENT;
  public static final DecodeMonitor STRICT = new DecodeMonitor()
  {
    public boolean isListening()
    {
      return true;
    }

    public boolean warn(String paramString1, String paramString2)
    {
      return true;
    }
  };

  static
  {
    SILENT = new DecodeMonitor();
  }

  public boolean isListening()
  {
    return false;
  }

  public boolean warn(String paramString1, String paramString2)
  {
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.codec.DecodeMonitor
 * JD-Core Version:    0.6.0
 */