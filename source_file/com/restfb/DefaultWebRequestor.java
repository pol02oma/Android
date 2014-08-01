package com.restfb;

import com.restfb.util.StringUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultWebRequestor
  implements WebRequestor
{
  private static final int DEFAULT_READ_TIMEOUT_IN_MS = 180000;
  private static final String MULTIPART_BOUNDARY = "**boundarystringwhichwill**neverbeencounteredinthewild**";
  private static final String MULTIPART_CARRIAGE_RETURN_AND_NEWLINE = "\r\n";
  private static final int MULTIPART_DEFAULT_BUFFER_SIZE = 8192;
  private static final String MULTIPART_TWO_HYPHENS = "--";
  private static final Logger logger = Logger.getLogger(DefaultWebRequestor.class.getName());

  protected void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable == null);
    do
    {
      return;
      try
      {
        paramCloseable.close();
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }
    while (!logger.isLoggable(Level.WARNING));
    logger.warning("Unable to close " + paramCloseable + ": " + localThrowable);
  }

  protected void closeQuietly(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection == null);
    do
    {
      return;
      try
      {
        paramHttpURLConnection.disconnect();
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }
    while (!logger.isLoggable(Level.WARNING));
    logger.warning("Unable to disconnect " + paramHttpURLConnection + ": " + localThrowable);
  }

  protected String createFormFieldName(BinaryAttachment paramBinaryAttachment)
  {
    String str = paramBinaryAttachment.getFilename();
    int i = str.lastIndexOf(".");
    if (i > 0)
      str = str.substring(0, i);
    return str;
  }

  protected void customizeConnection(HttpURLConnection paramHttpURLConnection)
  {
  }

  public WebRequestor.Response executeGet(String paramString)
    throws IOException
  {
    if (logger.isLoggable(Level.INFO))
      logger.info("Making a GET request to " + paramString);
    try
    {
      HttpURLConnection localHttpURLConnection2 = openConnection(new URL(paramString));
      localHttpURLConnection1 = localHttpURLConnection2;
      try
      {
        localHttpURLConnection1.setReadTimeout(180000);
        localHttpURLConnection1.setUseCaches(false);
        customizeConnection(localHttpURLConnection1);
        localHttpURLConnection1.setRequestMethod("GET");
        localHttpURLConnection1.connect();
        if (logger.isLoggable(Level.FINER))
          logger.finer("Response headers: " + localHttpURLConnection1.getHeaderFields());
        try
        {
          InputStream localInputStream2;
          if (localHttpURLConnection1.getResponseCode() != 200)
            localInputStream2 = localHttpURLConnection1.getErrorStream();
          InputStream localInputStream1;
          for (Object localObject4 = localInputStream2; ; localObject4 = localInputStream1)
          {
            localObject3 = localObject4;
            WebRequestor.Response localResponse = new WebRequestor.Response(Integer.valueOf(localHttpURLConnection1.getResponseCode()), StringUtils.fromInputStream(localObject3));
            closeQuietly(localHttpURLConnection1);
            return localResponse;
            localInputStream1 = localHttpURLConnection1.getInputStream();
          }
        }
        catch (IOException localIOException)
        {
          while (true)
          {
            boolean bool = logger.isLoggable(Level.WARNING);
            Object localObject3 = null;
            if (!bool)
              continue;
            logger.warning("An error occurred while making a GET request to " + paramString + ": " + localIOException);
            localObject3 = null;
          }
        }
      }
      finally
      {
      }
      closeQuietly(localHttpURLConnection1);
      throw localObject1;
    }
    finally
    {
      HttpURLConnection localHttpURLConnection1 = null;
    }
  }

  public WebRequestor.Response executePost(String paramString1, String paramString2)
    throws IOException
  {
    return executePost(paramString1, paramString2, (BinaryAttachment[])null);
  }

  public WebRequestor.Response executePost(String paramString1, String paramString2, BinaryAttachment[] paramArrayOfBinaryAttachment)
    throws IOException
  {
    int i = 0;
    if (paramArrayOfBinaryAttachment == null)
      paramArrayOfBinaryAttachment = new BinaryAttachment[0];
    String str2;
    String str3;
    if (logger.isLoggable(Level.INFO))
    {
      Logger localLogger = logger;
      StringBuilder localStringBuilder2 = new StringBuilder().append("Executing a POST to ").append(paramString1).append(" with parameters ");
      if (paramArrayOfBinaryAttachment.length <= 0)
        break label373;
      str2 = "";
      StringBuilder localStringBuilder3 = localStringBuilder2.append(str2).append(StringUtils.urlDecode(paramString2));
      if (paramArrayOfBinaryAttachment.length <= 0)
        break label381;
      str3 = " and " + paramArrayOfBinaryAttachment.length + " binary attachment[s].";
      label109: localLogger.info(str3);
    }
    try
    {
      StringBuilder localStringBuilder1 = new StringBuilder().append(paramString1);
      if (paramArrayOfBinaryAttachment.length > 0)
      {
        str1 = "?" + paramString2;
        localHttpURLConnection2 = openConnection(new URL(str1));
      }
    }
    finally
    {
      try
      {
        OutputStream localOutputStream2;
        label373: label381: WebRequestor.Response localResponse;
        while (true)
        {
          String str1;
          localHttpURLConnection2.setReadTimeout(180000);
          customizeConnection(localHttpURLConnection2);
          localHttpURLConnection2.setRequestMethod("POST");
          localHttpURLConnection2.setDoOutput(true);
          localHttpURLConnection2.setUseCaches(false);
          if (paramArrayOfBinaryAttachment.length > 0)
          {
            localHttpURLConnection2.setRequestProperty("Connection", "Keep-Alive");
            localHttpURLConnection2.setRequestProperty("Content-Type", "multipart/form-data;boundary=**boundarystringwhichwill**neverbeencounteredinthewild**");
          }
          localHttpURLConnection2.connect();
          localOutputStream2 = localHttpURLConnection2.getOutputStream();
          try
          {
            if (paramArrayOfBinaryAttachment.length > 0)
            {
              int n = paramArrayOfBinaryAttachment.length;
              for (int i1 = 0; i1 < n; i1++)
              {
                BinaryAttachment localBinaryAttachment = paramArrayOfBinaryAttachment[i1];
                localOutputStream2.write(("--**boundarystringwhichwill**neverbeencounteredinthewild**\r\nContent-Disposition: form-data; name=\"" + createFormFieldName(localBinaryAttachment) + "\"; filename=\"" + localBinaryAttachment.getFilename() + "\"" + "\r\n" + "\r\n").getBytes("UTF-8"));
                write(localBinaryAttachment.getData(), localOutputStream2, 8192);
                localOutputStream2.write("\r\n--**boundarystringwhichwill**neverbeencounteredinthewild**--\r\n".getBytes("UTF-8"));
              }
              str2 = "(sent in request body): ";
              break;
              str3 = "";
              break label109;
              str1 = "";
              continue;
            }
            localOutputStream2.write(paramString2.getBytes("UTF-8"));
            if (logger.isLoggable(Level.FINER))
              logger.finer("Response headers: " + localHttpURLConnection2.getHeaderFields());
            try
            {
              InputStream localInputStream2;
              if (localHttpURLConnection2.getResponseCode() != 200)
                localInputStream2 = localHttpURLConnection2.getErrorStream();
              InputStream localInputStream1;
              for (Object localObject5 = localInputStream2; ; localObject5 = localInputStream1)
              {
                localObject4 = localObject5;
                localResponse = new WebRequestor.Response(Integer.valueOf(localHttpURLConnection2.getResponseCode()), StringUtils.fromInputStream(localObject4));
                if (paramArrayOfBinaryAttachment.length <= 0)
                  break;
                int m = paramArrayOfBinaryAttachment.length;
                while (i < m)
                {
                  closeQuietly(paramArrayOfBinaryAttachment[i].getData());
                  i++;
                }
                localInputStream1 = localHttpURLConnection2.getInputStream();
              }
            }
            catch (IOException localIOException)
            {
              while (true)
              {
                boolean bool = logger.isLoggable(Level.WARNING);
                Object localObject4 = null;
                if (!bool)
                  continue;
                logger.warning("An error occurred while POSTing to " + paramString1 + ": " + localIOException);
                localObject4 = null;
              }
            }
          }
          finally
          {
            localOutputStream1 = localOutputStream2;
            localHttpURLConnection1 = localHttpURLConnection2;
          }
        }
        while (true)
        {
          if (paramArrayOfBinaryAttachment.length > 0)
          {
            int j = paramArrayOfBinaryAttachment.length;
            int k = 0;
            while (k < j)
            {
              closeQuietly(paramArrayOfBinaryAttachment[k].getData());
              k++;
              continue;
              closeQuietly(localOutputStream2);
              closeQuietly(localHttpURLConnection2);
              return localResponse;
            }
          }
          closeQuietly(localOutputStream1);
          closeQuietly(localHttpURLConnection1);
          throw localObject1;
          localObject2 = finally;
          localOutputStream1 = null;
          localHttpURLConnection1 = null;
        }
      }
      finally
      {
        while (true)
        {
          HttpURLConnection localHttpURLConnection2;
          HttpURLConnection localHttpURLConnection1 = localHttpURLConnection2;
          OutputStream localOutputStream1 = null;
        }
      }
    }
  }

  protected HttpURLConnection openConnection(URL paramURL)
    throws IOException
  {
    return (HttpURLConnection)paramURL.openConnection();
  }

  protected void write(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    if ((paramInputStream == null) || (paramOutputStream == null))
      throw new NullPointerException("Must provide non-null source and destination streams.");
    byte[] arrayOfByte = new byte[paramInt];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i <= 0)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.restfb.DefaultWebRequestor
 * JD-Core Version:    0.6.0
 */