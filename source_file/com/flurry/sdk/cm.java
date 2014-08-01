package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cm
{
  private static final String a = cm.class.getSimpleName();
  private boolean b;
  private List<cj> c;
  private long d = -1L;

  private static String a(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 4))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; i < -4 + paramString.length(); i++)
        localStringBuilder.append('*');
      localStringBuilder.append(paramString.substring(-4 + paramString.length()));
      paramString = localStringBuilder.toString();
    }
    return paramString;
  }

  private boolean a(String paramString, DataInputStream paramDataInputStream)
    throws IOException
  {
    int i = paramDataInputStream.readUnsignedShort();
    ex.a(3, a, "File version: " + i);
    if (i > 2)
    {
      ex.a(6, a, "Unknown agent file version: " + i);
      throw new IOException("Unknown agent file version: " + i);
    }
    if (i >= 2)
    {
      String str1 = paramDataInputStream.readUTF();
      ex.a(3, a, "Loading API key: " + a(paramString));
      if (str1.equals(paramString))
      {
        ArrayList localArrayList = new ArrayList();
        paramDataInputStream.readUTF();
        boolean bool = paramDataInputStream.readBoolean();
        long l = paramDataInputStream.readLong();
        ex.a(3, a, "Loading session reports");
        int j = 0;
        while (true)
        {
          int k = paramDataInputStream.readUnsignedShort();
          if (k == 0)
          {
            ex.a(3, a, "Persistent file loaded");
            a(bool);
            a(l);
            a(localArrayList);
            return true;
          }
          byte[] arrayOfByte = new byte[k];
          paramDataInputStream.readFully(arrayOfByte);
          localArrayList.add(0, new cj(arrayOfByte));
          String str2 = a;
          StringBuilder localStringBuilder = new StringBuilder().append("Session report added: ");
          j++;
          ex.a(3, str2, j);
        }
      }
      ex.a(3, a, "Api keys do not match, old: " + a(paramString) + ", new: " + a(str1));
      return false;
    }
    ex.a(5, a, "Deleting old file version: " + i);
    return false;
  }

  public void a(long paramLong)
  {
    this.d = paramLong;
  }

  public void a(DataOutputStream paramDataOutputStream, String paramString1, String paramString2)
    throws IOException
  {
    try
    {
      paramDataOutputStream.writeShort(46586);
      paramDataOutputStream.writeShort(2);
      paramDataOutputStream.writeUTF(paramString1);
      paramDataOutputStream.writeUTF(paramString2);
      paramDataOutputStream.writeBoolean(this.b);
      paramDataOutputStream.writeLong(this.d);
      for (int i = -1 + this.c.size(); ; i--)
      {
        byte[] arrayOfByte;
        int j;
        if (i >= 0)
        {
          arrayOfByte = ((cj)this.c.get(i)).a();
          j = arrayOfByte.length;
          if (j + 2 + paramDataOutputStream.size() > 50000)
            ex.a(6, a, "discarded sessions: " + i);
        }
        else
        {
          paramDataOutputStream.writeShort(0);
          return;
        }
        paramDataOutputStream.writeShort(j);
        paramDataOutputStream.write(arrayOfByte);
      }
    }
    catch (Throwable localThrowable)
    {
      ex.a(6, a, "", localThrowable);
      throw new IOException(localThrowable.getMessage());
    }
    finally
    {
      fh.a(paramDataOutputStream);
    }
    throw localObject;
  }

  public void a(List<cj> paramList)
  {
    this.c = paramList;
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public boolean a()
  {
    return this.b;
  }

  public boolean a(DataInputStream paramDataInputStream, String paramString)
    throws IOException
  {
    try
    {
      int i = paramDataInputStream.readUnsignedShort();
      ex.a(4, a, "Magic: " + i);
      boolean bool1;
      if (i == 46586)
        bool1 = a(paramString, paramDataInputStream);
      for (boolean bool2 = bool1; ; bool2 = false)
      {
        return bool2;
        ex.a(3, a, "Unexpected file type");
      }
    }
    catch (Throwable localThrowable)
    {
      ex.a(6, a, "Error when loading persistent file", localThrowable);
      throw new IOException(localThrowable.getMessage());
    }
    finally
    {
      fh.a(paramDataInputStream);
    }
    throw localObject;
  }

  public List<cj> b()
  {
    return this.c;
  }

  public long c()
  {
    return this.d;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.cm
 * JD-Core Version:    0.6.0
 */