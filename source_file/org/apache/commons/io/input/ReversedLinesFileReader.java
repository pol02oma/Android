package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.apache.commons.io.Charsets;

public class ReversedLinesFileReader
  implements Closeable
{
  private final int avoidNewlineSplitBufferSize;
  private final int blockSize;
  private final int byteDecrement;
  private FilePart currentFilePart;
  private final Charset encoding;
  private final byte[][] newLineSequences;
  private final RandomAccessFile randomAccessFile;
  private final long totalBlockCount;
  private final long totalByteLength;
  private boolean trailingNewlineOfFileSkipped = false;

  public ReversedLinesFileReader(File paramFile)
    throws IOException
  {
    this(paramFile, 4096, Charset.defaultCharset().toString());
  }

  public ReversedLinesFileReader(File paramFile, int paramInt, String paramString)
    throws IOException
  {
    this(paramFile, paramInt, Charsets.toCharset(paramString));
  }

  public ReversedLinesFileReader(File paramFile, int paramInt, Charset paramCharset)
    throws IOException
  {
    this.blockSize = paramInt;
    this.encoding = paramCharset;
    this.randomAccessFile = new RandomAccessFile(paramFile, "r");
    this.totalByteLength = this.randomAccessFile.length();
    int i = (int)(this.totalByteLength % paramInt);
    Charset localCharset;
    if (i > 0)
    {
      this.totalBlockCount = (1L + this.totalByteLength / paramInt);
      this.currentFilePart = new FilePart(this.totalBlockCount, i, null, null);
      localCharset = Charsets.toCharset(paramCharset);
      if (localCharset.newEncoder().maxBytesPerChar() != 1.0F)
        break label196;
      this.byteDecrement = 1;
    }
    while (true)
    {
      byte[][] arrayOfByte = new byte[3][];
      arrayOfByte[0] = "\r\n".getBytes(paramCharset);
      arrayOfByte[1] = "\n".getBytes(paramCharset);
      arrayOfByte[2] = "\r".getBytes(paramCharset);
      this.newLineSequences = arrayOfByte;
      this.avoidNewlineSplitBufferSize = this.newLineSequences[0].length;
      return;
      this.totalBlockCount = (this.totalByteLength / paramInt);
      if (this.totalByteLength <= 0L)
        break;
      i = paramInt;
      break;
      label196: if (localCharset == Charset.forName("UTF-8"))
      {
        this.byteDecrement = 1;
        continue;
      }
      if (localCharset == Charset.forName("Shift_JIS"))
      {
        this.byteDecrement = 1;
        continue;
      }
      if ((localCharset != Charset.forName("UTF-16BE")) && (localCharset != Charset.forName("UTF-16LE")))
        break label260;
      this.byteDecrement = 2;
    }
    label260: if (localCharset == Charset.forName("UTF-16"))
      throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
    throw new UnsupportedEncodingException("Encoding " + paramCharset + " is not supported yet (feel free to submit a patch)");
  }

  public void close()
    throws IOException
  {
    this.randomAccessFile.close();
  }

  public String readLine()
    throws IOException
  {
    for (String str = this.currentFilePart.readLine(); str == null; str = this.currentFilePart.readLine())
    {
      this.currentFilePart = this.currentFilePart.rollOver();
      if (this.currentFilePart == null)
        break;
    }
    if (("".equals(str)) && (!this.trailingNewlineOfFileSkipped))
    {
      this.trailingNewlineOfFileSkipped = true;
      str = readLine();
    }
    return str;
  }

  private class FilePart
  {
    private int currentLastBytePos;
    private final byte[] data;
    private byte[] leftOver;
    private final long no;

    private FilePart(long arg2, int paramArrayOfByte, byte[] arg5)
      throws IOException
    {
      this.no = ???;
      Object localObject;
      if (localObject != null);
      int i;
      for (byte[] arrayOfByte1 = localObject.length; ; i = 0)
      {
        this.data = new byte[paramArrayOfByte + arrayOfByte1];
        long l = (??? - 1L) * ReversedLinesFileReader.this.blockSize;
        if (??? <= 0L)
          break;
        ReversedLinesFileReader.this.randomAccessFile.seek(l);
        if (ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, paramArrayOfByte) == paramArrayOfByte)
          break;
        throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
      }
      if (localObject != null)
        System.arraycopy(localObject, 0, this.data, paramArrayOfByte, localObject.length);
      this.currentLastBytePos = (-1 + this.data.length);
      this.leftOver = null;
    }

    private void createLeftOver()
    {
      int i = 1 + this.currentLastBytePos;
      if (i > 0)
      {
        this.leftOver = new byte[i];
        System.arraycopy(this.data, 0, this.leftOver, 0, i);
      }
      while (true)
      {
        this.currentLastBytePos = -1;
        return;
        this.leftOver = null;
      }
    }

    private int getNewLineMatchByteCount(byte[] paramArrayOfByte, int paramInt)
    {
      byte[][] arrayOfByte = ReversedLinesFileReader.this.newLineSequences;
      int i = arrayOfByte.length;
      for (int j = 0; ; j++)
      {
        int k = 0;
        if (j < i)
        {
          byte[] arrayOfByte1 = arrayOfByte[j];
          int m = 1;
          int n = -1 + arrayOfByte1.length;
          if (n >= 0)
          {
            int i1 = paramInt + n - (-1 + arrayOfByte1.length);
            if ((i1 >= 0) && (paramArrayOfByte[i1] == arrayOfByte1[n]));
            for (int i2 = 1; ; i2 = 0)
            {
              m &= i2;
              n--;
              break;
            }
          }
          if (m == 0)
            continue;
          k = arrayOfByte1.length;
        }
        return k;
      }
    }

    private String readLine()
      throws IOException
    {
      int i;
      int j;
      label16: String str;
      if (this.no == 1L)
      {
        i = 1;
        j = this.currentLastBytePos;
        str = null;
        if (j > -1)
        {
          if ((i != 0) || (j >= ReversedLinesFileReader.this.avoidNewlineSplitBufferSize))
            break label84;
          createLeftOver();
        }
      }
      while (true)
      {
        if ((i != 0) && (this.leftOver != null))
        {
          str = new String(this.leftOver, ReversedLinesFileReader.this.encoding);
          this.leftOver = null;
        }
        return str;
        i = 0;
        break;
        label84: int k = getNewLineMatchByteCount(this.data, j);
        if (k > 0)
        {
          int m = j + 1;
          int n = 1 + (this.currentLastBytePos - m);
          if (n < 0)
            throw new IllegalStateException("Unexpected negative line length=" + n);
          byte[] arrayOfByte = new byte[n];
          System.arraycopy(this.data, m, arrayOfByte, 0, n);
          str = new String(arrayOfByte, ReversedLinesFileReader.this.encoding);
          this.currentLastBytePos = (j - k);
          continue;
        }
        j -= ReversedLinesFileReader.this.byteDecrement;
        if (j >= 0)
          break label16;
        createLeftOver();
        str = null;
      }
    }

    private FilePart rollOver()
      throws IOException
    {
      if (this.currentLastBytePos > -1)
        throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
      if (this.no > 1L)
        return new FilePart(ReversedLinesFileReader.this, this.no - 1L, ReversedLinesFileReader.this.blockSize, this.leftOver);
      if (this.leftOver != null)
        throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
      return null;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.ReversedLinesFileReader
 * JD-Core Version:    0.6.0
 */