package org.apache.james.mime4j.codec;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QuotedPrintableOutputStream extends FilterOutputStream
{
  private static final byte CR = 13;
  private static final int DEFAULT_BUFFER_SIZE = 3072;
  private static final byte DOT = 46;
  private static final byte EQ = 61;
  private static final byte[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static final byte LF = 10;
  private static final byte QUOTED_PRINTABLE_LAST_PLAIN = 126;
  private static final int QUOTED_PRINTABLE_MAX_LINE_LENGTH = 76;
  private static final int QUOTED_PRINTABLE_OCTETS_PER_ESCAPE = 3;
  private static final byte SP = 32;
  private static final byte TB = 9;
  private final boolean binary;
  private boolean closed = false;
  private int nextSoftBreak;
  private final byte[] outBuffer;
  private int outputIndex;
  private boolean pendingCR;
  private boolean pendingSpace;
  private boolean pendingTab;
  private byte[] singleByte = new byte[1];

  public QuotedPrintableOutputStream(int paramInt, OutputStream paramOutputStream, boolean paramBoolean)
  {
    super(paramOutputStream);
    this.outBuffer = new byte[paramInt];
    this.binary = paramBoolean;
    this.pendingSpace = false;
    this.pendingTab = false;
    this.pendingCR = false;
    this.outputIndex = 0;
    this.nextSoftBreak = 77;
  }

  public QuotedPrintableOutputStream(OutputStream paramOutputStream, boolean paramBoolean)
  {
    this(3072, paramOutputStream, paramBoolean);
  }

  private void clearPending()
    throws IOException
  {
    this.pendingSpace = false;
    this.pendingTab = false;
    this.pendingCR = false;
  }

  private void completeEncoding()
    throws IOException
  {
    writePending();
    flushOutput();
  }

  private void encode(byte paramByte)
    throws IOException
  {
    if (paramByte == 10)
    {
      if (this.binary)
      {
        writePending();
        escape(paramByte);
        return;
      }
      if (this.pendingCR)
      {
        if (this.pendingSpace)
          escape(32);
        while (true)
        {
          lineBreak();
          clearPending();
          return;
          if (!this.pendingTab)
            continue;
          escape(9);
        }
      }
      writePending();
      plain(paramByte);
      return;
    }
    if (paramByte == 13)
    {
      if (this.binary)
      {
        escape(paramByte);
        return;
      }
      this.pendingCR = true;
      return;
    }
    writePending();
    if (paramByte == 32)
    {
      if (this.binary)
      {
        escape(paramByte);
        return;
      }
      this.pendingSpace = true;
      return;
    }
    if (paramByte == 9)
    {
      if (this.binary)
      {
        escape(paramByte);
        return;
      }
      this.pendingTab = true;
      return;
    }
    if (paramByte < 32)
    {
      escape(paramByte);
      return;
    }
    if (paramByte > 126)
    {
      escape(paramByte);
      return;
    }
    if ((paramByte == 61) || (paramByte == 46))
    {
      escape(paramByte);
      return;
    }
    plain(paramByte);
  }

  private void encodeChunk(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    for (int i = paramInt1; i < paramInt2 + paramInt1; i++)
      encode(paramArrayOfByte[i]);
  }

  private void escape(byte paramByte)
    throws IOException
  {
    int i = -1 + this.nextSoftBreak;
    this.nextSoftBreak = i;
    if (i <= 3)
      softBreak();
    int j = paramByte & 0xFF;
    write(61);
    this.nextSoftBreak = (-1 + this.nextSoftBreak);
    write(HEX_DIGITS[(j >> 4)]);
    this.nextSoftBreak = (-1 + this.nextSoftBreak);
    write(HEX_DIGITS[(j % 16)]);
  }

  private void lineBreak()
    throws IOException
  {
    write(13);
    write(10);
    this.nextSoftBreak = 76;
  }

  private void plain(byte paramByte)
    throws IOException
  {
    int i = -1 + this.nextSoftBreak;
    this.nextSoftBreak = i;
    if (i <= 1)
      softBreak();
    write(paramByte);
  }

  private void softBreak()
    throws IOException
  {
    write(61);
    lineBreak();
  }

  private void write(byte paramByte)
    throws IOException
  {
    byte[] arrayOfByte = this.outBuffer;
    int i = this.outputIndex;
    this.outputIndex = (i + 1);
    arrayOfByte[i] = paramByte;
    if (this.outputIndex >= this.outBuffer.length)
      flushOutput();
  }

  private void writePending()
    throws IOException
  {
    if (this.pendingSpace)
      plain(32);
    while (true)
    {
      clearPending();
      return;
      if (this.pendingTab)
      {
        plain(9);
        continue;
      }
      if (!this.pendingCR)
        continue;
      plain(13);
    }
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
    try
    {
      completeEncoding();
      return;
    }
    finally
    {
      this.closed = true;
    }
    throw localObject;
  }

  public void flush()
    throws IOException
  {
    flushOutput();
  }

  void flushOutput()
    throws IOException
  {
    if (this.outputIndex < this.outBuffer.length)
      this.out.write(this.outBuffer, 0, this.outputIndex);
    while (true)
    {
      this.outputIndex = 0;
      return;
      this.out.write(this.outBuffer);
    }
  }

  public void write(int paramInt)
    throws IOException
  {
    this.singleByte[0] = (byte)paramInt;
    write(this.singleByte, 0, 1);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Stream has been closed");
    encodeChunk(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.codec.QuotedPrintableOutputStream
 * JD-Core Version:    0.6.0
 */