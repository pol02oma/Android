package org.apache.james.mime4j.dom.field;

import java.util.Map;

public abstract interface ContentTypeField extends ParsedField
{
  public static final String PARAM_BOUNDARY = "boundary";
  public static final String PARAM_CHARSET = "charset";
  public static final String TYPE_MESSAGE_RFC822 = "message/rfc822";
  public static final String TYPE_MULTIPART_DIGEST = "multipart/digest";
  public static final String TYPE_MULTIPART_PREFIX = "multipart/";
  public static final String TYPE_TEXT_PLAIN = "text/plain";

  public abstract String getBoundary();

  public abstract String getCharset();

  public abstract String getMediaType();

  public abstract String getMimeType();

  public abstract String getParameter(String paramString);

  public abstract Map<String, String> getParameters();

  public abstract String getSubType();

  public abstract boolean isMimeType(String paramString);

  public abstract boolean isMultipart();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.ContentTypeField
 * JD-Core Version:    0.6.0
 */