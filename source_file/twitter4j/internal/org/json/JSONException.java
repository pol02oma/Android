package twitter4j.internal.org.json;

public class JSONException extends Exception
{
  private static final long serialVersionUID;
  private Throwable cause;

  public JSONException(String paramString)
  {
    super(paramString);
  }

  public JSONException(Throwable paramThrowable)
  {
    super(paramThrowable.getMessage());
    this.cause = paramThrowable;
  }

  public Throwable getCause()
  {
    return this.cause;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.org.json.JSONException
 * JD-Core Version:    0.6.0
 */