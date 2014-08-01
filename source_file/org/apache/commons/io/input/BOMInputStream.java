package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.ByteOrderMark;

public class BOMInputStream extends ProxyInputStream
{
  private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator = new Comparator()
  {
    public int compare(ByteOrderMark paramByteOrderMark1, ByteOrderMark paramByteOrderMark2)
    {
      int i = paramByteOrderMark1.length();
      int j = paramByteOrderMark2.length();
      if (i > j)
        return -1;
      if (j > i)
        return 1;
      return 0;
    }
  };
  private final List<ByteOrderMark> boms;
  private ByteOrderMark byteOrderMark;
  private int fbIndex;
  private int fbLength;
  private int[] firstBytes;
  private final boolean include;
  private int markFbIndex;
  private boolean markedAtStart;

  public BOMInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, false, arrayOfByteOrderMark);
  }

  public BOMInputStream(InputStream paramInputStream, boolean paramBoolean)
  {
    this(paramInputStream, paramBoolean, arrayOfByteOrderMark);
  }

  public BOMInputStream(InputStream paramInputStream, boolean paramBoolean, ByteOrderMark[] paramArrayOfByteOrderMark)
  {
    super(paramInputStream);
    if ((paramArrayOfByteOrderMark == null) || (paramArrayOfByteOrderMark.length == 0))
      throw new IllegalArgumentException("No BOMs specified");
    this.include = paramBoolean;
    Arrays.sort(paramArrayOfByteOrderMark, ByteOrderMarkLengthComparator);
    this.boms = Arrays.asList(paramArrayOfByteOrderMark);
  }

  public BOMInputStream(InputStream paramInputStream, ByteOrderMark[] paramArrayOfByteOrderMark)
  {
    this(paramInputStream, false, paramArrayOfByteOrderMark);
  }

  private ByteOrderMark find()
  {
    Iterator localIterator = this.boms.iterator();
    while (localIterator.hasNext())
    {
      ByteOrderMark localByteOrderMark = (ByteOrderMark)localIterator.next();
      if (matches(localByteOrderMark))
        return localByteOrderMark;
    }
    return null;
  }

  private boolean matches(ByteOrderMark paramByteOrderMark)
  {
    for (int i = 0; i < paramByteOrderMark.length(); i++)
      if (paramByteOrderMark.get(i) != this.firstBytes[i])
        return false;
    return true;
  }

  private int readFirstBytes()
    throws IOException
  {
    getBOM();
    if (this.fbIndex < this.fbLength)
    {
      int[] arrayOfInt = this.firstBytes;
      int i = this.fbIndex;
      this.fbIndex = (i + 1);
      return arrayOfInt[i];
    }
    return -1;
  }

  public ByteOrderMark getBOM()
    throws IOException
  {
    int i;
    if (this.firstBytes == null)
    {
      this.fbLength = 0;
      this.firstBytes = new int[((ByteOrderMark)this.boms.get(0)).length()];
      i = 0;
      if (i < this.firstBytes.length)
      {
        this.firstBytes[i] = this.in.read();
        this.fbLength = (1 + this.fbLength);
        if (this.firstBytes[i] >= 0)
          break label130;
      }
      this.byteOrderMark = find();
      if ((this.byteOrderMark != null) && (!this.include))
      {
        if (this.byteOrderMark.length() >= this.firstBytes.length)
          break label136;
        this.fbIndex = this.byteOrderMark.length();
      }
    }
    while (true)
    {
      return this.byteOrderMark;
      label130: i++;
      break;
      label136: this.fbLength = 0;
    }
  }

  public String getBOMCharsetName()
    throws IOException
  {
    getBOM();
    if (this.byteOrderMark == null)
      return null;
    return this.byteOrderMark.getCharsetName();
  }

  public boolean hasBOM()
    throws IOException
  {
    return getBOM() != null;
  }

  public boolean hasBOM(ByteOrderMark paramByteOrderMark)
    throws IOException
  {
    if (!this.boms.contains(paramByteOrderMark))
      throw new IllegalArgumentException("Stream not configure to detect " + paramByteOrderMark);
    return (this.byteOrderMark != null) && (getBOM().equals(paramByteOrderMark));
  }

  public void mark(int paramInt)
  {
    monitorenter;
    try
    {
      this.markFbIndex = this.fbIndex;
      if (this.firstBytes == null);
      for (boolean bool = true; ; bool = false)
      {
        this.markedAtStart = bool;
        this.in.mark(paramInt);
        return;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int read()
    throws IOException
  {
    int i = readFirstBytes();
    if (i >= 0)
      return i;
    return this.in.read();
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = 0;
    int j = 0;
    int k = paramInt1;
    while ((paramInt2 > 0) && (j >= 0))
    {
      j = readFirstBytes();
      if (j < 0)
        continue;
      int n = k + 1;
      paramArrayOfByte[k] = (byte)(j & 0xFF);
      paramInt2--;
      i++;
      k = n;
    }
    int m = this.in.read(paramArrayOfByte, k, paramInt2);
    if (m < 0)
    {
      if (i > 0)
        return i;
      return -1;
    }
    return i + m;
  }

  public void reset()
    throws IOException
  {
    monitorenter;
    try
    {
      this.fbIndex = this.markFbIndex;
      if (this.markedAtStart)
        this.firstBytes = null;
      this.in.reset();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public long skip(long paramLong)
    throws IOException
  {
    while ((paramLong > 0L) && (readFirstBytes() >= 0))
      paramLong -= 1L;
    return this.in.skip(paramLong);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.BOMInputStream
 * JD-Core Version:    0.6.0
 */