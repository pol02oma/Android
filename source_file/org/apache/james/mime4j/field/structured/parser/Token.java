package org.apache.james.mime4j.field.structured.parser;

import java.io.Serializable;

public class Token
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public int beginColumn;
  public int beginLine;
  public int endColumn;
  public int endLine;
  public String image;
  public int kind;
  public Token next;
  public Token specialToken;

  public Token()
  {
  }

  public Token(int paramInt)
  {
    this(paramInt, null);
  }

  public Token(int paramInt, String paramString)
  {
    this.kind = paramInt;
    this.image = paramString;
  }

  public static Token newToken(int paramInt)
  {
    return newToken(paramInt, null);
  }

  public static Token newToken(int paramInt, String paramString)
  {
    return new Token(paramInt, paramString);
  }

  public Object getValue()
  {
    return null;
  }

  public String toString()
  {
    return this.image;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.structured.parser.Token
 * JD-Core Version:    0.6.0
 */