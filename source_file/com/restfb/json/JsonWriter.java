package com.restfb.json;

import java.io.IOException;
import java.io.Writer;

public class JsonWriter
{
  private static final int maxdepth = 20;
  private boolean comma = false;
  protected char mode = 'i';
  private JsonObject[] stack = new JsonObject[20];
  private int top = 0;
  protected Writer writer;

  public JsonWriter(Writer paramWriter)
  {
    this.writer = paramWriter;
  }

  private JsonWriter append(String paramString)
  {
    if (paramString == null)
      throw new JsonException("Null pointer");
    if ((this.mode == 'o') || (this.mode == 'a'))
      try
      {
        if ((this.comma) && (this.mode == 'a'))
          this.writer.write(44);
        this.writer.write(paramString);
        if (this.mode == 'o')
          this.mode = 'k';
        this.comma = true;
        return this;
      }
      catch (IOException localIOException)
      {
        throw new JsonException(localIOException);
      }
    throw new JsonException("Value out of sequence.");
  }

  private JsonWriter end(char paramChar1, char paramChar2)
  {
    if (this.mode != paramChar1)
    {
      if (paramChar1 == 'o');
      for (String str = "Misplaced endObject."; ; str = "Misplaced endArray.")
        throw new JsonException(str);
    }
    pop(paramChar1);
    try
    {
      this.writer.write(paramChar2);
      this.comma = true;
      return this;
    }
    catch (IOException localIOException)
    {
    }
    throw new JsonException(localIOException);
  }

  private void pop(char paramChar)
  {
    char c1 = 'a';
    if (this.top <= 0)
      throw new JsonException("Nesting error.");
    if (this.stack[(-1 + this.top)] == null);
    for (char c2 = c1; c2 != paramChar; c2 = 'k')
      throw new JsonException("Nesting error.");
    this.top = (-1 + this.top);
    if (this.top == 0)
      c1 = 'd';
    while (true)
    {
      this.mode = c1;
      return;
      if (this.stack[(-1 + this.top)] == null)
        continue;
      c1 = 'k';
    }
  }

  private void push(JsonObject paramJsonObject)
  {
    if (this.top >= 20)
      throw new JsonException("Nesting too deep.");
    this.stack[this.top] = paramJsonObject;
    if (paramJsonObject == null);
    for (char c = 'a'; ; c = 'k')
    {
      this.mode = c;
      this.top = (1 + this.top);
      return;
    }
  }

  public JsonWriter array()
  {
    if ((this.mode == 'i') || (this.mode == 'o') || (this.mode == 'a'))
    {
      push(null);
      append("[");
      this.comma = false;
      return this;
    }
    throw new JsonException("Misplaced array.");
  }

  public JsonWriter endArray()
  {
    return end('a', ']');
  }

  public JsonWriter endObject()
  {
    return end('k', '}');
  }

  public JsonWriter key(String paramString)
  {
    if (paramString == null)
      throw new JsonException("Null key.");
    if (this.mode == 'k')
      try
      {
        this.stack[(-1 + this.top)].putOnce(paramString, Boolean.TRUE);
        if (this.comma)
          this.writer.write(44);
        this.writer.write(JsonObject.quote(paramString));
        this.writer.write(58);
        this.comma = false;
        this.mode = 'o';
        return this;
      }
      catch (IOException localIOException)
      {
        throw new JsonException(localIOException);
      }
    throw new JsonException("Misplaced key.");
  }

  public JsonWriter object()
  {
    if (this.mode == 'i')
      this.mode = 'o';
    if ((this.mode == 'o') || (this.mode == 'a'))
    {
      append("{");
      push(new JsonObject());
      this.comma = false;
      return this;
    }
    throw new JsonException("Misplaced object.");
  }

  public JsonWriter value(double paramDouble)
  {
    return value(new Double(paramDouble));
  }

  public JsonWriter value(long paramLong)
  {
    return append(Long.toString(paramLong));
  }

  public JsonWriter value(Object paramObject)
  {
    return append(JsonObject.valueToString(paramObject));
  }

  public JsonWriter value(boolean paramBoolean)
  {
    if (paramBoolean);
    for (String str = "true"; ; str = "false")
      return append(str);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.json.JsonWriter
 * JD-Core Version:    0.6.0
 */