package com.restfb.types;

import com.restfb.Facebook;
import com.restfb.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class Insight extends NamedFacebookType
{
  private static final long serialVersionUID = 1L;

  @Facebook
  private String description;

  @Facebook
  private String period;

  @Facebook
  private List<JsonObject> values = new ArrayList();

  public String getDescription()
  {
    return this.description;
  }

  public String getPeriod()
  {
    return this.period;
  }

  public List<JsonObject> getValues()
  {
    return this.values;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.types.Insight
 * JD-Core Version:    0.6.0
 */