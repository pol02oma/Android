package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.io.BufferInfo;
import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AbstractSessionInputBuffer
  implements SessionInputBuffer, BufferInfo
{
  private static final Charset ASCII = Charset.forName("US-ASCII");
  private boolean ascii = true;
  private byte[] buffer;
  private int bufferlen;
  private int bufferpos;
  private CharBuffer cbuf;
  private Charset charset;
  private CharsetDecoder decoder;
  private InputStream instream;
  private ByteArrayBuffer linebuffer = null;
  private int maxLineLen = -1;
  private HttpTransportMetricsImpl metrics;
  private int minChunkLimit = 512;
  private CodingErrorAction onMalformedInputAction;
  private CodingErrorAction onUnMappableInputAction;

  private int appendDecoded(CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (!paramByteBuffer.hasRemaining())
      return 0;
    if (this.decoder == null)
    {
      this.decoder = this.charset.newDecoder();
      this.decoder.onMalformedInput(this.onMalformedInputAction);
      this.decoder.onUnmappableCharacter(this.onUnMappableInputAction);
    }
    if (this.cbuf == null)
      this.cbuf = CharBuffer.allocate(1024);
    this.decoder.reset();
    int i = 0;
    while (paramByteBuffer.hasRemaining())
      i += handleDecodingResult(this.decoder.decode(paramByteBuffer, this.cbuf, true), paramCharArrayBuffer, paramByteBuffer);
    int j = i + handleDecodingResult(this.decoder.flush(this.cbuf), paramCharArrayBuffer, paramByteBuffer);
    this.cbuf.clear();
    return j;
  }

  private int handleDecodingResult(CoderResult paramCoderResult, CharArrayBuffer paramCharArrayBuffer, ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (paramCoderResult.isError())
      paramCoderResult.throwException();
    this.cbuf.flip();
    int i = this.cbuf.remaining();
    while (this.cbuf.hasRemaining())
      paramCharArrayBuffer.append(this.cbuf.get());
    this.cbuf.compact();
    return i;
  }

  private int lineFromLineBuffer(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int i = this.linebuffer.length();
    if (i > 0)
    {
      if (this.linebuffer.byteAt(i - 1) == 10)
        i--;
      if ((i > 0) && (this.linebuffer.byteAt(i - 1) == 13))
        i--;
    }
    if (this.ascii)
      paramCharArrayBuffer.append(this.linebuffer, 0, i);
    while (true)
    {
      this.linebuffer.clear();
      return i;
      i = appendDecoded(paramCharArrayBuffer, ByteBuffer.wrap(this.linebuffer.buffer(), 0, i));
    }
  }

  private int lineFromReadBuffer(CharArrayBuffer paramCharArrayBuffer, int paramInt)
    throws IOException
  {
    int i = this.bufferpos;
    this.bufferpos = (paramInt + 1);
    if ((paramInt > i) && (this.buffer[(paramInt - 1)] == 13))
      paramInt--;
    int j = paramInt - i;
    if (this.ascii)
    {
      paramCharArrayBuffer.append(this.buffer, i, j);
      return j;
    }
    return appendDecoded(paramCharArrayBuffer, ByteBuffer.wrap(this.buffer, i, j));
  }

  private int locateLF()
  {
    for (int i = this.bufferpos; i < this.bufferlen; i++)
      if (this.buffer[i] == 10)
        return i;
    return -1;
  }

  public int available()
  {
    return capacity() - length();
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  protected HttpTransportMetricsImpl createTransportMetrics()
  {
    return new HttpTransportMetricsImpl();
  }

  protected int fillBuffer()
    throws IOException
  {
    if (this.bufferpos > 0)
    {
      int m = this.bufferlen - this.bufferpos;
      if (m > 0)
        System.arraycopy(this.buffer, this.bufferpos, this.buffer, 0, m);
      this.bufferpos = 0;
      this.bufferlen = m;
    }
    int i = this.bufferlen;
    int j = this.buffer.length - i;
    int k = this.instream.read(this.buffer, i, j);
    if (k == -1)
      return -1;
    this.bufferlen = (i + k);
    this.metrics.incrementBytesTransferred(k);
    return k;
  }

  public HttpTransportMetrics getMetrics()
  {
    return this.metrics;
  }

  protected boolean hasBufferedData()
  {
    return this.bufferpos < this.bufferlen;
  }

  protected void init(InputStream paramInputStream, int paramInt, HttpParams paramHttpParams)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input stream may not be null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("Buffer size may not be negative or zero");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    this.instream = paramInputStream;
    this.buffer = new byte[paramInt];
    this.bufferpos = 0;
    this.bufferlen = 0;
    this.linebuffer = new ByteArrayBuffer(paramInt);
    this.charset = Charset.forName(HttpProtocolParams.getHttpElementCharset(paramHttpParams));
    this.ascii = this.charset.equals(ASCII);
    this.decoder = null;
    this.maxLineLen = paramHttpParams.getIntParameter("http.connection.max-line-length", -1);
    this.minChunkLimit = paramHttpParams.getIntParameter("http.connection.min-chunk-limit", 512);
    this.metrics = createTransportMetrics();
    this.onMalformedInputAction = HttpProtocolParams.getMalformedInputAction(paramHttpParams);
    this.onUnMappableInputAction = HttpProtocolParams.getUnmappableInputAction(paramHttpParams);
  }

  public int length()
  {
    return this.bufferlen - this.bufferpos;
  }

  public int read()
    throws IOException
  {
    while (!hasBufferedData())
      if (fillBuffer() == -1)
        return -1;
    byte[] arrayOfByte = this.buffer;
    int i = this.bufferpos;
    this.bufferpos = (i + 1);
    return 0xFF & arrayOfByte[i];
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return 0;
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      return 0;
    if (hasBufferedData())
    {
      int k = Math.min(paramInt2, this.bufferlen - this.bufferpos);
      System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, k);
      this.bufferpos = (k + this.bufferpos);
      return k;
    }
    if (paramInt2 > this.minChunkLimit)
    {
      int j = this.instream.read(paramArrayOfByte, paramInt1, paramInt2);
      if (j > 0)
        this.metrics.incrementBytesTransferred(j);
      return j;
    }
    while (!hasBufferedData())
      if (fillBuffer() == -1)
        return -1;
    int i = Math.min(paramInt2, this.bufferlen - this.bufferpos);
    System.arraycopy(this.buffer, this.bufferpos, paramArrayOfByte, paramInt1, i);
    this.bufferpos = (i + this.bufferpos);
    return i;
  }

  public int readLine(CharArrayBuffer paramCharArrayBuffer)
    throws IOException
  {
    int i = -1;
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    int j = 0;
    int k = 1;
    int m;
    if (k != 0)
    {
      m = locateLF();
      if (m != i)
        if (this.linebuffer.isEmpty())
          i = lineFromReadBuffer(paramCharArrayBuffer, m);
    }
    do
    {
      return i;
      k = 0;
      int i1 = m + 1 - this.bufferpos;
      this.linebuffer.append(this.buffer, this.bufferpos, i1);
      this.bufferpos = (m + 1);
      while ((this.maxLineLen > 0) && (this.linebuffer.length() >= this.maxLineLen))
      {
        throw new IOException("Maximum line length limit exceeded");
        if (hasBufferedData())
        {
          int n = this.bufferlen - this.bufferpos;
          this.linebuffer.append(this.buffer, this.bufferpos, n);
          this.bufferpos = this.bufferlen;
        }
        j = fillBuffer();
        if (j != i)
          continue;
        k = 0;
      }
      break;
    }
    while ((j == i) && (this.linebuffer.isEmpty()));
    return lineFromLineBuffer(paramCharArrayBuffer);
  }

  public String readLine()
    throws IOException
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(64);
    if (readLine(localCharArrayBuffer) != -1)
      return localCharArrayBuffer.toString();
    return null;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.io.AbstractSessionInputBuffer
 * JD-Core Version:    0.6.0
 */