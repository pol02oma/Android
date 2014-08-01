package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.UUID;

public class cg
{
  private static final String d = cg.class.getSimpleName();
  String a = null;
  long b = -1L;
  int c = -1;
  private File e = null;

  public cg()
  {
    this.a = UUID.randomUUID().toString();
    this.e = eg.a().b().getFileStreamPath(".flurrydatasenderblock." + this.a);
  }

  public cg(String paramString)
  {
    this.a = paramString;
    this.e = eg.a().b().getFileStreamPath(".flurrydatasenderblock." + this.a);
  }

  public String a()
  {
    return this.a;
  }

  // ERROR //
  public boolean a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: invokestatic 79	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   5: invokestatic 82	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   8: if_acmpne +13 -> 21
    //   11: bipush 6
    //   13: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   16: ldc 84
    //   18: invokestatic 89	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   21: iconst_4
    //   22: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   25: new 55	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   32: ldc 91
    //   34: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_0
    //   38: getfield 36	com/flurry/sdk/cg:e	Ljava/io/File;
    //   41: invokevirtual 96	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   44: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokestatic 89	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   53: aload_0
    //   54: getfield 36	com/flurry/sdk/cg:e	Ljava/io/File;
    //   57: invokestatic 101	com/flurry/sdk/et:a	(Ljava/io/File;)Z
    //   60: istore 6
    //   62: iload 6
    //   64: ifne +9 -> 73
    //   67: aconst_null
    //   68: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   71: iconst_0
    //   72: ireturn
    //   73: new 108	java/io/DataOutputStream
    //   76: dup
    //   77: new 110	java/io/FileOutputStream
    //   80: dup
    //   81: aload_0
    //   82: getfield 36	com/flurry/sdk/cg:e	Ljava/io/File;
    //   85: invokespecial 113	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   88: invokespecial 116	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   91: astore 4
    //   93: aload_1
    //   94: arraylength
    //   95: istore 7
    //   97: aload 4
    //   99: iload 7
    //   101: invokevirtual 120	java/io/DataOutputStream:writeShort	(I)V
    //   104: aload 4
    //   106: aload_1
    //   107: invokevirtual 124	java/io/DataOutputStream:write	([B)V
    //   110: aload 4
    //   112: iconst_0
    //   113: invokevirtual 120	java/io/DataOutputStream:writeShort	(I)V
    //   116: iconst_1
    //   117: istore_2
    //   118: aload_0
    //   119: iload 7
    //   121: putfield 34	com/flurry/sdk/cg:c	I
    //   124: aload_0
    //   125: invokestatic 130	java/lang/System:currentTimeMillis	()J
    //   128: putfield 32	com/flurry/sdk/cg:b	J
    //   131: aload 4
    //   133: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   136: iload_2
    //   137: ireturn
    //   138: astore 5
    //   140: aconst_null
    //   141: astore 4
    //   143: bipush 6
    //   145: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   148: ldc 132
    //   150: aload 5
    //   152: invokestatic 135	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   155: aload 4
    //   157: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   160: iload_2
    //   161: ireturn
    //   162: astore_3
    //   163: aconst_null
    //   164: astore 4
    //   166: aload 4
    //   168: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   171: aload_3
    //   172: athrow
    //   173: astore_3
    //   174: goto -8 -> 166
    //   177: astore 5
    //   179: goto -36 -> 143
    //
    // Exception table:
    //   from	to	target	type
    //   53	62	138	java/lang/Throwable
    //   73	93	138	java/lang/Throwable
    //   53	62	162	finally
    //   73	93	162	finally
    //   93	116	173	finally
    //   118	131	173	finally
    //   143	155	173	finally
    //   93	116	177	java/lang/Throwable
    //   118	131	177	java/lang/Throwable
  }

  // ERROR //
  public byte[] b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: invokestatic 79	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   5: invokestatic 82	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   8: if_acmpne +13 -> 21
    //   11: bipush 6
    //   13: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   16: ldc 138
    //   18: invokestatic 89	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   21: aload_0
    //   22: getfield 36	com/flurry/sdk/cg:e	Ljava/io/File;
    //   25: invokevirtual 142	java/io/File:exists	()Z
    //   28: ifeq +133 -> 161
    //   31: iconst_4
    //   32: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   35: new 55	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 56	java/lang/StringBuilder:<init>	()V
    //   42: ldc 144
    //   44: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_0
    //   48: getfield 36	com/flurry/sdk/cg:e	Ljava/io/File;
    //   51: invokevirtual 96	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   54: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 89	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   63: new 146	java/io/DataInputStream
    //   66: dup
    //   67: new 148	java/io/FileInputStream
    //   70: dup
    //   71: aload_0
    //   72: getfield 36	com/flurry/sdk/cg:e	Ljava/io/File;
    //   75: invokespecial 149	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   78: invokespecial 152	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   81: astore_2
    //   82: aload_2
    //   83: invokevirtual 156	java/io/DataInputStream:readUnsignedShort	()I
    //   86: istore 5
    //   88: iload 5
    //   90: ifne +9 -> 99
    //   93: aload_2
    //   94: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   97: aconst_null
    //   98: areturn
    //   99: iload 5
    //   101: newarray byte
    //   103: astore_1
    //   104: aload_2
    //   105: aload_1
    //   106: invokevirtual 159	java/io/DataInputStream:readFully	([B)V
    //   109: aload_2
    //   110: invokevirtual 156	java/io/DataInputStream:readUnsignedShort	()I
    //   113: istore 6
    //   115: iload 6
    //   117: ifne +3 -> 120
    //   120: aload_2
    //   121: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   124: aload_1
    //   125: areturn
    //   126: astore_3
    //   127: aconst_null
    //   128: astore_2
    //   129: bipush 6
    //   131: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   134: ldc 161
    //   136: aload_3
    //   137: invokestatic 135	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload_2
    //   141: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   144: aload_1
    //   145: areturn
    //   146: astore 7
    //   148: aconst_null
    //   149: astore_2
    //   150: aload 7
    //   152: astore 4
    //   154: aload_2
    //   155: invokestatic 106	com/flurry/sdk/fh:a	(Ljava/io/Closeable;)V
    //   158: aload 4
    //   160: athrow
    //   161: iconst_4
    //   162: getstatic 23	com/flurry/sdk/cg:d	Ljava/lang/String;
    //   165: ldc 163
    //   167: invokestatic 89	com/flurry/sdk/ex:a	(ILjava/lang/String;Ljava/lang/String;)V
    //   170: aconst_null
    //   171: areturn
    //   172: astore 4
    //   174: goto -20 -> 154
    //   177: astore_3
    //   178: goto -49 -> 129
    //
    // Exception table:
    //   from	to	target	type
    //   63	82	126	java/lang/Throwable
    //   63	82	146	finally
    //   82	88	172	finally
    //   99	115	172	finally
    //   129	140	172	finally
    //   82	88	177	java/lang/Throwable
    //   99	115	177	java/lang/Throwable
  }

  public boolean c()
  {
    if (this.e.exists())
    {
      if (this.e.delete())
      {
        ex.a(4, d, "Deleted persistence file");
        this.b = -1L;
        this.c = -1;
        return true;
      }
      ex.a(6, d, "Cannot delete persistence file");
    }
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.flurry.sdk.cg
 * JD-Core Version:    0.6.0
 */