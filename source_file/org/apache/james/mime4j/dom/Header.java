package org.apache.james.mime4j.dom;

import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.stream.Field;

public abstract interface Header extends Iterable<Field>
{
  public abstract void addField(Field paramField);

  public abstract Field getField(String paramString);

  public abstract List<Field> getFields();

  public abstract List<Field> getFields(String paramString);

  public abstract Iterator<Field> iterator();

  public abstract int removeFields(String paramString);

  public abstract void setField(Field paramField);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.Header
 * JD-Core Version:    0.6.0
 */