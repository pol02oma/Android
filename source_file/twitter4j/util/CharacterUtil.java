package twitter4j.util;

public final class CharacterUtil
{
  private CharacterUtil()
  {
    throw new AssertionError();
  }

  public static int count(String paramString)
  {
    return paramString.length();
  }

  public static boolean isExceedingLengthLimitation(String paramString)
  {
    return count(paramString) > 140;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.util.CharacterUtil
 * JD-Core Version:    0.6.0
 */