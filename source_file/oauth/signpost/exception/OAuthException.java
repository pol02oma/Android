package oauth.signpost.exception;

public abstract class OAuthException extends Exception
{
  public OAuthException(String paramString)
  {
    super(paramString);
  }

  public OAuthException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public OAuthException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     oauth.signpost.exception.OAuthException
 * JD-Core Version:    0.6.0
 */