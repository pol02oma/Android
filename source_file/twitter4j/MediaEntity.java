package twitter4j;

import java.io.Serializable;
import java.util.Map;

public abstract interface MediaEntity extends URLEntity
{
  public abstract long getId();

  public abstract String getMediaURL();

  public abstract String getMediaURLHttps();

  public abstract Map<Integer, Size> getSizes();

  public abstract String getType();

  public static abstract interface Size extends Serializable
  {
    public static final int CROP = 101;
    public static final int FIT = 100;
    public static final Integer LARGE;
    public static final Integer MEDIUM;
    public static final Integer SMALL;
    public static final Integer THUMB = Integer.valueOf(0);

    static
    {
      SMALL = Integer.valueOf(1);
      MEDIUM = Integer.valueOf(2);
      LARGE = Integer.valueOf(3);
    }

    public abstract int getHeight();

    public abstract int getResize();

    public abstract int getWidth();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.MediaEntity
 * JD-Core Version:    0.6.0
 */