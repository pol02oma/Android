package twitter4j;

import java.io.Serializable;
import java.util.Map;

public abstract interface TwitterAPIConfiguration extends TwitterResponse, Serializable
{
  public abstract int getCharactersReservedPerMedia();

  public abstract int getMaxMediaPerUpload();

  public abstract String[] getNonUsernamePaths();

  public abstract int getPhotoSizeLimit();

  public abstract Map<Integer, MediaEntity.Size> getPhotoSizes();

  public abstract int getShortURLLength();

  public abstract int getShortURLLengthHttps();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.TwitterAPIConfiguration
 * JD-Core Version:    0.6.0
 */