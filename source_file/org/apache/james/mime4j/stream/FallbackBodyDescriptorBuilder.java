package org.apache.james.mime4j.stream;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.util.MimeUtil;

class FallbackBodyDescriptorBuilder
  implements BodyDescriptorBuilder
{
  private static final String DEFAULT_MEDIA_TYPE = "text";
  private static final String DEFAULT_MIME_TYPE = "text/plain";
  private static final String DEFAULT_SUB_TYPE = "plain";
  private static final String EMAIL_MESSAGE_MIME_TYPE = "message/rfc822";
  private static final String MEDIA_TYPE_MESSAGE = "message";
  private static final String MEDIA_TYPE_TEXT = "text";
  private static final String SUB_TYPE_EMAIL = "rfc822";
  private static final String US_ASCII = "us-ascii";
  private String boundary;
  private String charset;
  private long contentLength;
  private String mediaType;
  private String mimeType;
  private final DecodeMonitor monitor;
  private final String parentMimeType;
  private String subType;
  private String transferEncoding;

  public FallbackBodyDescriptorBuilder()
  {
    this(null, null);
  }

  public FallbackBodyDescriptorBuilder(String paramString, DecodeMonitor paramDecodeMonitor)
  {
    this.parentMimeType = paramString;
    if (paramDecodeMonitor != null);
    while (true)
    {
      this.monitor = paramDecodeMonitor;
      reset();
      return;
      paramDecodeMonitor = DecodeMonitor.SILENT;
    }
  }

  private void parseContentType(Field paramField)
    throws MimeException
  {
    if ((paramField instanceof RawField));
    String str1;
    HashMap localHashMap;
    for (RawField localRawField = (RawField)paramField; ; localRawField = new RawField(paramField.getName(), paramField.getBody()))
    {
      RawBody localRawBody = RawFieldParser.DEFAULT.parseRawBody(localRawField);
      str1 = localRawBody.getValue();
      localHashMap = new HashMap();
      Iterator localIterator = localRawBody.getParams().iterator();
      while (localIterator.hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
        localHashMap.put(localNameValuePair.getName().toLowerCase(Locale.US), localNameValuePair.getValue());
      }
    }
    String str2 = null;
    String str3 = null;
    if (str1 != null)
    {
      str1 = str1.toLowerCase().trim();
      int i = str1.indexOf('/');
      str2 = null;
      str3 = null;
      int j = 0;
      if (i != -1)
      {
        str3 = str1.substring(0, i).trim();
        str2 = str1.substring(i + 1).trim();
        int k = str3.length();
        j = 0;
        if (k > 0)
        {
          int m = str2.length();
          j = 0;
          if (m > 0)
          {
            str1 = str3 + "/" + str2;
            j = 1;
          }
        }
      }
      if (j == 0)
      {
        str1 = null;
        str3 = null;
        str2 = null;
      }
    }
    String str4 = (String)localHashMap.get("boundary");
    if ((str1 != null) && (((str1.startsWith("multipart/")) && (str4 != null)) || (!str1.startsWith("multipart/"))))
    {
      this.mimeType = str1;
      this.mediaType = str3;
      this.subType = str2;
    }
    if (MimeUtil.isMultipart(this.mimeType))
      this.boundary = str4;
    String str5 = (String)localHashMap.get("charset");
    this.charset = null;
    if (str5 != null)
    {
      String str6 = str5.trim();
      if (str6.length() > 0)
        this.charset = str6;
    }
  }

  public Field addField(RawField paramRawField)
    throws MimeException
  {
    String str1 = paramRawField.getName().toLowerCase(Locale.US);
    if ((str1.equals("content-transfer-encoding")) && (this.transferEncoding == null))
    {
      String str4 = paramRawField.getBody();
      if (str4 != null)
      {
        String str5 = str4.trim().toLowerCase(Locale.US);
        if (str5.length() > 0)
          this.transferEncoding = str5;
      }
    }
    while (true)
    {
      return null;
      if ((str1.equals("content-length")) && (this.contentLength == -1L))
      {
        String str2 = paramRawField.getBody();
        if (str2 == null)
          continue;
        String str3 = str2.trim();
        try
        {
          this.contentLength = Long.parseLong(str3.trim());
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
        if (!this.monitor.warn("Invalid content length: " + str3, "ignoring Content-Length header"))
          continue;
        throw new MimeException("Invalid Content-Length header: " + str3);
      }
      if ((!str1.equals("content-type")) || (this.mimeType != null))
        continue;
      parseContentType(paramRawField);
    }
  }

  public BodyDescriptor build()
  {
    String str1 = this.mimeType;
    String str2 = this.mediaType;
    String str3 = this.subType;
    String str4 = this.charset;
    String str5;
    if (str1 == null)
    {
      if (MimeUtil.isSameMimeType("multipart/digest", this.parentMimeType))
      {
        str1 = "message/rfc822";
        str2 = "message";
        str3 = "rfc822";
      }
    }
    else
    {
      if ((str4 == null) && ("text".equals(str2)))
        str4 = "us-ascii";
      str5 = this.boundary;
      if (this.transferEncoding == null)
        break label116;
    }
    label116: for (String str6 = this.transferEncoding; ; str6 = "7bit")
    {
      return new BasicBodyDescriptor(str1, str2, str3, str5, str4, str6, this.contentLength);
      str1 = "text/plain";
      str2 = "text";
      str3 = "plain";
      break;
    }
  }

  public BodyDescriptorBuilder newChild()
  {
    return new FallbackBodyDescriptorBuilder(this.mimeType, this.monitor);
  }

  public void reset()
  {
    this.mimeType = null;
    this.subType = null;
    this.mediaType = null;
    this.boundary = null;
    this.charset = null;
    this.transferEncoding = null;
    this.contentLength = -1L;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder
 * JD-Core Version:    0.6.0
 */