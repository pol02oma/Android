package org.apache.james.mime4j.field.structured.parser;

public class TokenMgrError extends Error
{
  static final int INVALID_LEXICAL_STATE = 2;
  static final int LEXICAL_ERROR = 0;
  static final int LOOP_DETECTED = 3;
  static final int STATIC_LEXER_ERROR = 1;
  private static final long serialVersionUID = 1L;
  int errorCode;

  public TokenMgrError()
  {
  }

  public TokenMgrError(String paramString, int paramInt)
  {
    super(paramString);
    this.errorCode = paramInt;
  }

  public TokenMgrError(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, String paramString, char paramChar, int paramInt4)
  {
    this(LexicalError(paramBoolean, paramInt1, paramInt2, paramInt3, paramString, paramChar), paramInt4);
  }

  protected static String LexicalError(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, String paramString, char paramChar)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Lexical error at line ").append(paramInt2).append(", column ").append(paramInt3).append(".  Encountered: ");
    if (paramBoolean);
    for (String str = "<EOF> "; ; str = "\"" + addEscapes(String.valueOf(paramChar)) + "\"" + " (" + paramChar + "), ")
      return str + "after : \"" + addEscapes(paramString) + "\"";
  }

  protected static final String addEscapes(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramString.length())
    {
      char c;
      switch (paramString.charAt(i))
      {
      default:
        c = paramString.charAt(i);
        if ((c >= ' ') && (c <= '~'))
          break;
        String str = "0000" + Integer.toString(c, 16);
        localStringBuffer.append("\\u" + str.substring(-4 + str.length(), str.length()));
      case '\000':
      case '\b':
      case '\t':
      case '\n':
      case '\f':
      case '\r':
      case '"':
      case '\'':
      case '\\':
      }
      while (true)
      {
        i++;
        break;
        localStringBuffer.append("\\b");
        continue;
        localStringBuffer.append("\\t");
        continue;
        localStringBuffer.append("\\n");
        continue;
        localStringBuffer.append("\\f");
        continue;
        localStringBuffer.append("\\r");
        continue;
        localStringBuffer.append("\\\"");
        continue;
        localStringBuffer.append("\\'");
        continue;
        localStringBuffer.append("\\\\");
        continue;
        localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString();
  }

  public String getMessage()
  {
    return super.getMessage();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.structured.parser.TokenMgrError
 * JD-Core Version:    0.6.0
 */