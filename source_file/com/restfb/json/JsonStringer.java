package com.restfb.json;

import java.io.StringWriter;

public class JsonStringer extends JsonWriter
{
  public JsonStringer()
  {
    super(new StringWriter());
  }

  public String toString()
  {
    if (this.mode == 'd')
      return this.writer.toString();
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.json.JsonStringer
 * JD-Core Version:    0.6.0
 */