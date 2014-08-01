package org.apache.james.mime4j.dom.field;

import java.util.List;

public abstract interface ContentLanguageField extends ParsedField
{
  public abstract List<String> getLanguages();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.ContentLanguageField
 * JD-Core Version:    0.6.0
 */