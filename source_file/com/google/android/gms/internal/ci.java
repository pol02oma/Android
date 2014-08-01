package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ci extends ch.a
{
  private static final Object op = new Object();
  private static ci oq;
  private final Context mContext;
  private final au or;

  private ci(Context paramContext, au paramau)
  {
    this.mContext = paramContext;
    this.or = paramau;
  }

  private static cf a(Context paramContext, au paramau, cd paramcd)
  {
    da.s("Starting ad request from service.");
    paramau.init();
    cm localcm = new cm(paramContext);
    if (localcm.oX == -1)
    {
      da.s("Device is offline.");
      return new cf(2);
    }
    ck localck = new ck(paramcd.applicationInfo.packageName);
    if (paramcd.oc.extras != null)
    {
      String str3 = paramcd.oc.extras.getString("_ad");
      if (str3 != null)
        return cj.a(paramContext, paramcd, str3);
    }
    String str1 = cj.a(paramcd, localcm, paramau.a(250L));
    if (str1 == null)
      return new cf(0);
    cz.pT.post(new Runnable(paramContext, paramcd, localck, str1)
    {
      public void run()
      {
        dd localdd = dd.a(this.os, new ab(), false, false, null, this.ot.kN);
        localdd.setWillNotDraw(true);
        this.ou.b(localdd);
        de localde = localdd.bb();
        localde.a("/invalidRequest", this.ou.oz);
        localde.a("/loadAdURL", this.ou.oA);
        localde.a("/log", aq.mb);
        da.s("Getting the ad request URL.");
        localdd.loadDataWithBaseURL("http://googleads.g.doubleclick.net", "<!DOCTYPE html><html><head><script src=\"http://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.js\"></script><script>AFMA_buildAdURL(" + this.ov + ");</script></head><body></body></html>", "text/html", "UTF-8", null);
      }
    });
    String str2 = localck.aI();
    if (TextUtils.isEmpty(str2))
      return new cf(localck.getErrorCode());
    return a(paramContext, paramcd.kN.pU, str2);
  }

  // ERROR //
  public static cf a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 152	com/google/android/gms/internal/cl
    //   3: dup
    //   4: invokespecial 153	com/google/android/gms/internal/cl:<init>	()V
    //   7: astore_3
    //   8: new 155	java/net/URL
    //   11: dup
    //   12: aload_2
    //   13: invokespecial 156	java/net/URL:<init>	(Ljava/lang/String;)V
    //   16: astore 4
    //   18: invokestatic 162	android/os/SystemClock:elapsedRealtime	()J
    //   21: lstore 6
    //   23: aload 4
    //   25: astore 8
    //   27: iconst_0
    //   28: istore 9
    //   30: aload 8
    //   32: invokevirtual 166	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   35: checkcast 168	java/net/HttpURLConnection
    //   38: astore 10
    //   40: aload_0
    //   41: aload_1
    //   42: iconst_0
    //   43: aload 10
    //   45: invokestatic 173	com/google/android/gms/internal/cv:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   48: aload 10
    //   50: invokevirtual 176	java/net/HttpURLConnection:getResponseCode	()I
    //   53: istore 12
    //   55: aload 10
    //   57: invokevirtual 180	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   60: astore 13
    //   62: iload 12
    //   64: sipush 200
    //   67: if_icmplt +72 -> 139
    //   70: iload 12
    //   72: sipush 300
    //   75: if_icmpge +64 -> 139
    //   78: aload 8
    //   80: invokevirtual 183	java/net/URL:toString	()Ljava/lang/String;
    //   83: astore 18
    //   85: new 185	java/io/InputStreamReader
    //   88: dup
    //   89: aload 10
    //   91: invokevirtual 189	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   94: invokespecial 192	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   97: invokestatic 195	com/google/android/gms/internal/cv:a	(Ljava/lang/Readable;)Ljava/lang/String;
    //   100: astore 19
    //   102: aload 18
    //   104: aload 13
    //   106: aload 19
    //   108: iload 12
    //   110: invokestatic 198	com/google/android/gms/internal/ci:a	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   113: aload_3
    //   114: aload 18
    //   116: aload 13
    //   118: aload 19
    //   120: invokevirtual 201	com/google/android/gms/internal/cl:a	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
    //   123: aload_3
    //   124: lload 6
    //   126: invokevirtual 205	com/google/android/gms/internal/cl:f	(J)Lcom/google/android/gms/internal/cf;
    //   129: astore 20
    //   131: aload 10
    //   133: invokevirtual 208	java/net/HttpURLConnection:disconnect	()V
    //   136: aload 20
    //   138: areturn
    //   139: aload 8
    //   141: invokevirtual 183	java/net/URL:toString	()Ljava/lang/String;
    //   144: aload 13
    //   146: aconst_null
    //   147: iload 12
    //   149: invokestatic 198	com/google/android/gms/internal/ci:a	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   152: iload 12
    //   154: sipush 300
    //   157: if_icmplt +94 -> 251
    //   160: iload 12
    //   162: sipush 400
    //   165: if_icmpge +86 -> 251
    //   168: aload 10
    //   170: ldc 210
    //   172: invokevirtual 213	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   175: astore 15
    //   177: aload 15
    //   179: invokestatic 132	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   182: ifeq +26 -> 208
    //   185: ldc 215
    //   187: invokestatic 218	com/google/android/gms/internal/da:w	(Ljava/lang/String;)V
    //   190: new 55	com/google/android/gms/internal/cf
    //   193: dup
    //   194: iconst_0
    //   195: invokespecial 58	com/google/android/gms/internal/cf:<init>	(I)V
    //   198: astore 17
    //   200: aload 10
    //   202: invokevirtual 208	java/net/HttpURLConnection:disconnect	()V
    //   205: aload 17
    //   207: areturn
    //   208: new 155	java/net/URL
    //   211: dup
    //   212: aload 15
    //   214: invokespecial 156	java/net/URL:<init>	(Ljava/lang/String;)V
    //   217: astore 8
    //   219: iinc 9 1
    //   222: iload 9
    //   224: iconst_5
    //   225: if_icmple +67 -> 292
    //   228: ldc 220
    //   230: invokestatic 218	com/google/android/gms/internal/da:w	(Ljava/lang/String;)V
    //   233: new 55	com/google/android/gms/internal/cf
    //   236: dup
    //   237: iconst_0
    //   238: invokespecial 58	com/google/android/gms/internal/cf:<init>	(I)V
    //   241: astore 16
    //   243: aload 10
    //   245: invokevirtual 208	java/net/HttpURLConnection:disconnect	()V
    //   248: aload 16
    //   250: areturn
    //   251: new 222	java/lang/StringBuilder
    //   254: dup
    //   255: invokespecial 223	java/lang/StringBuilder:<init>	()V
    //   258: ldc 225
    //   260: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: iload 12
    //   265: invokevirtual 232	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   268: invokevirtual 233	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   271: invokestatic 218	com/google/android/gms/internal/da:w	(Ljava/lang/String;)V
    //   274: new 55	com/google/android/gms/internal/cf
    //   277: dup
    //   278: iconst_0
    //   279: invokespecial 58	com/google/android/gms/internal/cf:<init>	(I)V
    //   282: astore 14
    //   284: aload 10
    //   286: invokevirtual 208	java/net/HttpURLConnection:disconnect	()V
    //   289: aload 14
    //   291: areturn
    //   292: aload_3
    //   293: aload 13
    //   295: invokevirtual 237	com/google/android/gms/internal/cl:d	(Ljava/util/Map;)V
    //   298: aload 10
    //   300: invokevirtual 208	java/net/HttpURLConnection:disconnect	()V
    //   303: goto -273 -> 30
    //   306: astore 5
    //   308: new 222	java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial 223	java/lang/StringBuilder:<init>	()V
    //   315: ldc 239
    //   317: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: aload 5
    //   322: invokevirtual 242	java/io/IOException:getMessage	()Ljava/lang/String;
    //   325: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: invokevirtual 233	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   331: invokestatic 218	com/google/android/gms/internal/da:w	(Ljava/lang/String;)V
    //   334: new 55	com/google/android/gms/internal/cf
    //   337: dup
    //   338: iconst_2
    //   339: invokespecial 58	com/google/android/gms/internal/cf:<init>	(I)V
    //   342: areturn
    //   343: astore 11
    //   345: aload 10
    //   347: invokevirtual 208	java/net/HttpURLConnection:disconnect	()V
    //   350: aload 11
    //   352: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	23	306	java/io/IOException
    //   30	40	306	java/io/IOException
    //   131	136	306	java/io/IOException
    //   200	205	306	java/io/IOException
    //   243	248	306	java/io/IOException
    //   284	289	306	java/io/IOException
    //   298	303	306	java/io/IOException
    //   345	353	306	java/io/IOException
    //   40	62	343	finally
    //   78	131	343	finally
    //   139	152	343	finally
    //   168	200	343	finally
    //   208	219	343	finally
    //   228	243	343	finally
    //   251	284	343	finally
    //   292	298	343	finally
  }

  public static ci a(Context paramContext, au paramau)
  {
    synchronized (op)
    {
      if (oq == null)
        oq = new ci(paramContext.getApplicationContext(), paramau);
      ci localci = oq;
      return localci;
    }
  }

  private static void a(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (da.n(2))
    {
      da.v("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        Iterator localIterator1 = paramMap.keySet().iterator();
        while (localIterator1.hasNext())
        {
          String str1 = (String)localIterator1.next();
          da.v("    " + str1 + ":");
          Iterator localIterator2 = ((List)paramMap.get(str1)).iterator();
          while (localIterator2.hasNext())
          {
            String str2 = (String)localIterator2.next();
            da.v("      " + str2);
          }
        }
      }
      da.v("  Body:");
      if (paramString2 != null)
        for (int i = 0; i < Math.min(paramString2.length(), 100000); i += 1000)
          da.v(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
      da.v("    null");
      da.v("  Response Code:\n    " + paramInt + "\n}");
    }
  }

  public cf b(cd paramcd)
  {
    return a(this.mContext, this.or, paramcd);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.ci
 * JD-Core Version:    0.6.0
 */