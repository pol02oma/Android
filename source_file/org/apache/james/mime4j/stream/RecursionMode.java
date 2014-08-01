package org.apache.james.mime4j.stream;

public enum RecursionMode
{
  static
  {
    M_NO_RECURSE = new RecursionMode("M_NO_RECURSE", 1);
    M_RAW = new RecursionMode("M_RAW", 2);
    M_FLAT = new RecursionMode("M_FLAT", 3);
    RecursionMode[] arrayOfRecursionMode = new RecursionMode[4];
    arrayOfRecursionMode[0] = M_RECURSE;
    arrayOfRecursionMode[1] = M_NO_RECURSE;
    arrayOfRecursionMode[2] = M_RAW;
    arrayOfRecursionMode[3] = M_FLAT;
    $VALUES = arrayOfRecursionMode;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.RecursionMode
 * JD-Core Version:    0.6.0
 */