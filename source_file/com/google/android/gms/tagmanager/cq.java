package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.jd.a;
import com.google.android.gms.internal.kd;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class cq
  implements o.f
{
  private final String TM;
  private bg<jd.a> Wi;
  private final ExecutorService Wp;
  private final Context mContext;

  cq(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.TM = paramString;
    this.Wp = Executors.newSingleThreadExecutor();
  }

  private cr.c a(ByteArrayOutputStream paramByteArrayOutputStream)
  {
    try
    {
      cr.c localc = ba.br(paramByteArrayOutputStream.toString("UTF-8"));
      return localc;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      bh.s("Tried to convert binary resource to string for JSON parsing; not UTF-8 format");
      return null;
    }
    catch (JSONException localJSONException)
    {
      bh.w("Resource is a UTF-8 encoded string but doesn't contain a JSON container");
    }
    return null;
  }

  private cr.c k(byte[] paramArrayOfByte)
  {
    try
    {
      cr.c localc = cr.b(c.f.a(paramArrayOfByte));
      return localc;
    }
    catch (kd localkd)
    {
      bh.w("Resource doesn't contain a binary container");
      return null;
    }
    catch (cr.g localg)
    {
      bh.w("Resource doesn't contain a binary container");
    }
    return null;
  }

  public void a(bg<jd.a> parambg)
  {
    this.Wi = parambg;
  }

  public void b(jd.a parama)
  {
    this.Wp.execute(new Runnable(parama)
    {
      public void run()
      {
        cq.this.c(this.Wr);
      }
    });
  }

  public cr.c bP(int paramInt)
  {
    bh.v("Atttempting to load container from resource ID " + paramInt);
    try
    {
      InputStream localInputStream = this.mContext.getResources().openRawResource(paramInt);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      cr.b(localInputStream, localByteArrayOutputStream);
      cr.c localc1 = a(localByteArrayOutputStream);
      if (localc1 != null)
        return localc1;
      cr.c localc2 = k(localByteArrayOutputStream.toByteArray());
      return localc2;
    }
    catch (IOException localIOException)
    {
      bh.w("Error reading default container resource with ID " + paramInt);
      return null;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      bh.w("No default container resource found.");
    }
    return null;
  }

  // ERROR //
  boolean c(jd.a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 159	com/google/android/gms/tagmanager/cq:jD	()Ljava/io/File;
    //   4: astore_2
    //   5: new 161	java/io/FileOutputStream
    //   8: dup
    //   9: aload_2
    //   10: invokespecial 164	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   13: astore_3
    //   14: aload_3
    //   15: aload_1
    //   16: invokestatic 170	com/google/android/gms/internal/ke:d	(Lcom/google/android/gms/internal/ke;)[B
    //   19: invokevirtual 174	java/io/FileOutputStream:write	([B)V
    //   22: aload_3
    //   23: invokevirtual 177	java/io/FileOutputStream:close	()V
    //   26: iconst_1
    //   27: ireturn
    //   28: astore 10
    //   30: ldc 179
    //   32: invokestatic 182	com/google/android/gms/tagmanager/bh:t	(Ljava/lang/String;)V
    //   35: iconst_0
    //   36: ireturn
    //   37: astore 9
    //   39: ldc 184
    //   41: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   44: iconst_1
    //   45: ireturn
    //   46: astore 6
    //   48: ldc 186
    //   50: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   53: aload_2
    //   54: invokevirtual 192	java/io/File:delete	()Z
    //   57: pop
    //   58: aload_3
    //   59: invokevirtual 177	java/io/FileOutputStream:close	()V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore 8
    //   66: ldc 184
    //   68: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   71: iconst_0
    //   72: ireturn
    //   73: astore 4
    //   75: aload_3
    //   76: invokevirtual 177	java/io/FileOutputStream:close	()V
    //   79: aload 4
    //   81: athrow
    //   82: astore 5
    //   84: ldc 184
    //   86: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   89: goto -10 -> 79
    //
    // Exception table:
    //   from	to	target	type
    //   5	14	28	java/io/FileNotFoundException
    //   22	26	37	java/io/IOException
    //   14	22	46	java/io/IOException
    //   58	62	64	java/io/IOException
    //   14	22	73	finally
    //   48	58	73	finally
    //   75	79	82	java/io/IOException
  }

  public void iN()
  {
    this.Wp.execute(new Runnable()
    {
      public void run()
      {
        cq.this.jC();
      }
    });
  }

  // ERROR //
  void jC()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 87	com/google/android/gms/tagmanager/cq:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   4: ifnonnull +13 -> 17
    //   7: new 201	java/lang/IllegalStateException
    //   10: dup
    //   11: ldc 203
    //   13: invokespecial 205	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: getfield 87	com/google/android/gms/tagmanager/cq:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   21: invokeinterface 210 1 0
    //   26: ldc 212
    //   28: invokestatic 123	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   31: invokestatic 218	com/google/android/gms/tagmanager/ce:ju	()Lcom/google/android/gms/tagmanager/ce;
    //   34: invokevirtual 222	com/google/android/gms/tagmanager/ce:jv	()Lcom/google/android/gms/tagmanager/ce$a;
    //   37: getstatic 228	com/google/android/gms/tagmanager/ce$a:VX	Lcom/google/android/gms/tagmanager/ce$a;
    //   40: if_acmpeq +15 -> 55
    //   43: invokestatic 218	com/google/android/gms/tagmanager/ce:ju	()Lcom/google/android/gms/tagmanager/ce;
    //   46: invokevirtual 222	com/google/android/gms/tagmanager/ce:jv	()Lcom/google/android/gms/tagmanager/ce$a;
    //   49: getstatic 231	com/google/android/gms/tagmanager/ce$a:VY	Lcom/google/android/gms/tagmanager/ce$a;
    //   52: if_acmpne +32 -> 84
    //   55: aload_0
    //   56: getfield 24	com/google/android/gms/tagmanager/cq:TM	Ljava/lang/String;
    //   59: invokestatic 218	com/google/android/gms/tagmanager/ce:ju	()Lcom/google/android/gms/tagmanager/ce;
    //   62: invokevirtual 234	com/google/android/gms/tagmanager/ce:getContainerId	()Ljava/lang/String;
    //   65: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +16 -> 84
    //   71: aload_0
    //   72: getfield 87	com/google/android/gms/tagmanager/cq:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   75: getstatic 246	com/google/android/gms/tagmanager/bg$a:VA	Lcom/google/android/gms/tagmanager/bg$a;
    //   78: invokeinterface 249 2 0
    //   83: return
    //   84: new 251	java/io/FileInputStream
    //   87: dup
    //   88: aload_0
    //   89: invokevirtual 159	com/google/android/gms/tagmanager/cq:jD	()Ljava/io/File;
    //   92: invokespecial 252	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore_1
    //   96: new 42	java/io/ByteArrayOutputStream
    //   99: dup
    //   100: invokespecial 136	java/io/ByteArrayOutputStream:<init>	()V
    //   103: astore_2
    //   104: aload_1
    //   105: aload_2
    //   106: invokestatic 139	com/google/android/gms/tagmanager/cr:b	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   109: aload_0
    //   110: getfield 87	com/google/android/gms/tagmanager/cq:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   113: aload_2
    //   114: invokevirtual 145	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   117: invokestatic 258	com/google/android/gms/internal/jd$a:l	([B)Lcom/google/android/gms/internal/jd$a;
    //   120: invokeinterface 262 2 0
    //   125: aload_1
    //   126: invokevirtual 263	java/io/FileInputStream:close	()V
    //   129: ldc_w 265
    //   132: invokestatic 123	com/google/android/gms/tagmanager/bh:v	(Ljava/lang/String;)V
    //   135: return
    //   136: astore 8
    //   138: ldc_w 267
    //   141: invokestatic 60	com/google/android/gms/tagmanager/bh:s	(Ljava/lang/String;)V
    //   144: aload_0
    //   145: getfield 87	com/google/android/gms/tagmanager/cq:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   148: getstatic 246	com/google/android/gms/tagmanager/bg$a:VA	Lcom/google/android/gms/tagmanager/bg$a;
    //   151: invokeinterface 249 2 0
    //   156: return
    //   157: astore 7
    //   159: ldc_w 269
    //   162: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   165: goto -36 -> 129
    //   168: astore 5
    //   170: ldc_w 271
    //   173: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   176: aload_0
    //   177: getfield 87	com/google/android/gms/tagmanager/cq:Wi	Lcom/google/android/gms/tagmanager/bg;
    //   180: getstatic 274	com/google/android/gms/tagmanager/bg$a:VB	Lcom/google/android/gms/tagmanager/bg$a;
    //   183: invokeinterface 249 2 0
    //   188: aload_1
    //   189: invokevirtual 263	java/io/FileInputStream:close	()V
    //   192: goto -63 -> 129
    //   195: astore 6
    //   197: ldc_w 269
    //   200: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   203: goto -74 -> 129
    //   206: astore_3
    //   207: aload_1
    //   208: invokevirtual 263	java/io/FileInputStream:close	()V
    //   211: aload_3
    //   212: athrow
    //   213: astore 4
    //   215: ldc_w 269
    //   218: invokestatic 65	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   221: goto -10 -> 211
    //
    // Exception table:
    //   from	to	target	type
    //   84	96	136	java/io/FileNotFoundException
    //   125	129	157	java/io/IOException
    //   96	125	168	java/io/IOException
    //   188	192	195	java/io/IOException
    //   96	125	206	finally
    //   170	188	206	finally
    //   207	211	213	java/io/IOException
  }

  File jD()
  {
    String str = "resource_" + this.TM;
    return new File(this.mContext.getDir("google_tagmanager", 0), str);
  }

  public void release()
  {
    monitorenter;
    try
    {
      this.Wp.shutdown();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.cq
 * JD-Core Version:    0.6.0
 */