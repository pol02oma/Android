package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public final class cp
{
  private static final Object op;
  public static final String pu;
  private static cq pv;
  private static BigInteger pw;
  private static HashSet<co> px;
  private static HashMap<String, cr> py;

  static
  {
    UUID localUUID = UUID.randomUUID();
    byte[] arrayOfByte1 = BigInteger.valueOf(localUUID.getLeastSignificantBits()).toByteArray();
    byte[] arrayOfByte2 = BigInteger.valueOf(localUUID.getMostSignificantBits()).toByteArray();
    Object localObject = new BigInteger(1, arrayOfByte1).toString();
    int i = 0;
    while (true)
    {
      if (i < 2);
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(arrayOfByte1);
        localMessageDigest.update(arrayOfByte2);
        byte[] arrayOfByte3 = new byte[8];
        System.arraycopy(localMessageDigest.digest(), 0, arrayOfByte3, 0, 8);
        String str = new BigInteger(1, arrayOfByte3).toString();
        localObject = str;
        label105: i++;
        continue;
        pu = (String)localObject;
        op = new Object();
        pv = new cq(pu);
        pw = BigInteger.ONE;
        px = new HashSet();
        py = new HashMap();
        return;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        break label105;
      }
    }
  }

  public static Bundle a(v.a parama, String paramString, Context paramContext)
  {
    Bundle localBundle1;
    Bundle localBundle2;
    synchronized (op)
    {
      localBundle1 = new Bundle();
      localBundle1.putBundle("app", pv.b(paramString, paramContext));
      localBundle2 = new Bundle();
      Iterator localIterator1 = py.keySet().iterator();
      if (localIterator1.hasNext())
      {
        String str = (String)localIterator1.next();
        localBundle2.putBundle(str, ((cr)py.get(str)).toBundle());
      }
    }
    localBundle1.putBundle("slots", localBundle2);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator2 = px.iterator();
    while (localIterator2.hasNext())
      localArrayList.add(((co)localIterator2.next()).toBundle());
    localBundle1.putParcelableArrayList("ads", localArrayList);
    parama.a(px);
    px = new HashSet();
    monitorexit;
    return localBundle1;
  }

  public static void a(co paramco)
  {
    synchronized (op)
    {
      px.add(paramco);
      return;
    }
  }

  public static void a(v.a parama)
  {
    synchronized (op)
    {
      px.addAll(parama.ah());
      return;
    }
  }

  public static void a(String paramString, cr paramcr)
  {
    synchronized (op)
    {
      py.put(paramString, paramcr);
      return;
    }
  }

  public static String aP()
  {
    synchronized (op)
    {
      String str = pw.toString();
      pw = pw.add(BigInteger.ONE);
      return str;
    }
  }

  public static cq aQ()
  {
    synchronized (op)
    {
      cq localcq = pv;
      return localcq;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.internal.cp
 * JD-Core Version:    0.6.0
 */