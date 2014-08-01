package org.apache.james.mime4j.dom.field;

import java.util.Date;
import java.util.Map;

public abstract interface ContentDispositionField extends ParsedField
{
  public static final String DISPOSITION_TYPE_ATTACHMENT = "attachment";
  public static final String DISPOSITION_TYPE_INLINE = "inline";
  public static final String PARAM_CREATION_DATE = "creation-date";
  public static final String PARAM_FILENAME = "filename";
  public static final String PARAM_MODIFICATION_DATE = "modification-date";
  public static final String PARAM_READ_DATE = "read-date";
  public static final String PARAM_SIZE = "size";

  public abstract Date getCreationDate();

  public abstract String getDispositionType();

  public abstract String getFilename();

  public abstract Date getModificationDate();

  public abstract String getParameter(String paramString);

  public abstract Map<String, String> getParameters();

  public abstract Date getReadDate();

  public abstract long getSize();

  public abstract boolean isAttachment();

  public abstract boolean isDispositionType(String paramString);

  public abstract boolean isInline();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.ContentDispositionField
 * JD-Core Version:    0.6.0
 */