package twitter4j;

import java.io.PrintStream;

public final class Version
{
  private static final String TITLE = "Twitter4J";
  private static final String VERSION = "3.0.6-SNAPSHOT(build: 6b148528110cab823a73f4ae60f90cae89fd2fb5)";

  private Version()
  {
    throw new AssertionError();
  }

  public static String getVersion()
  {
    return "3.0.6-SNAPSHOT(build: 6b148528110cab823a73f4ae60f90cae89fd2fb5)";
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println("Twitter4J 3.0.6-SNAPSHOT(build: 6b148528110cab823a73f4ae60f90cae89fd2fb5)");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.Version
 * JD-Core Version:    0.6.0
 */