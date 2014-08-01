package io.vov.vitamio;

public abstract interface MediaScannerClient
{
  public abstract void addNoMediaFolder(String paramString);

  public abstract void handleStringTag(String paramString1, byte[] paramArrayOfByte, String paramString2);

  public abstract void scanFile(String paramString, long paramLong1, long paramLong2);

  public abstract void setMimeType(String paramString);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.MediaScannerClient
 * JD-Core Version:    0.6.0
 */