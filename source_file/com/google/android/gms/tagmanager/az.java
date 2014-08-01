package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class az extends aj
{
  private static final String ID = a.an.toString();
  private static final String US = b.bt.toString();
  private static final String Vo = b.cW.toString();
  private static final String Vp = b.cZ.toString();
  private static final String Vq = b.cz.toString();

  public az()
  {
    super(str, arrayOfString);
  }

  private String a(String paramString, a parama, Set<Character> paramSet)
  {
    switch (1.Vr[parama.ordinal()])
    {
    default:
      return paramString;
    case 1:
      try
      {
        String str4 = dl.bO(paramString);
        return str4;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        bh.c("Joiner: unsupported encoding", localUnsupportedEncodingException);
        return paramString;
      }
    case 2:
    }
    String str1 = paramString.replace("\\", "\\\\");
    Iterator localIterator = paramSet.iterator();
    String str3;
    for (String str2 = str1; localIterator.hasNext(); str2 = str2.replace(str3, "\\" + str3))
      str3 = ((Character)localIterator.next()).toString();
    return str2;
  }

  private void a(StringBuilder paramStringBuilder, String paramString, a parama, Set<Character> paramSet)
  {
    paramStringBuilder.append(a(paramString, parama, paramSet));
  }

  private void a(Set<Character> paramSet, String paramString)
  {
    for (int i = 0; i < paramString.length(); i++)
      paramSet.add(Character.valueOf(paramString.charAt(i)));
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(US);
    if (locala1 == null)
      return di.ku();
    d.a locala2 = (d.a)paramMap.get(Vo);
    String str1;
    String str2;
    label70: a locala4;
    String str5;
    a locala6;
    Object localObject;
    if (locala2 != null)
    {
      str1 = di.j(locala2);
      d.a locala3 = (d.a)paramMap.get(Vp);
      if (locala3 == null)
        break label186;
      str2 = di.j(locala3);
      locala4 = a.Vs;
      d.a locala5 = (d.a)paramMap.get(Vq);
      if (locala5 == null)
        break label432;
      str5 = di.j(locala5);
      if (!"url".equals(str5))
        break label193;
      locala6 = a.Vt;
      localObject = null;
    }
    while (true)
    {
      label119: StringBuilder localStringBuilder = new StringBuilder();
      switch (locala1.type)
      {
      default:
        a(localStringBuilder, di.j(locala1), locala6, (Set)localObject);
      case 2:
      case 3:
      }
      while (true)
      {
        return di.r(localStringBuilder.toString());
        str1 = "";
        break;
        label186: str2 = "=";
        break label70;
        label193: if ("backslash".equals(str5))
        {
          locala6 = a.Vu;
          localObject = new HashSet();
          a((Set)localObject, str1);
          a((Set)localObject, str2);
          ((Set)localObject).remove(Character.valueOf('\\'));
          break label119;
        }
        bh.t("Joiner: unsupported escape type: " + str5);
        return di.ku();
        int j = 1;
        d.a[] arrayOfa = locala1.fZ;
        int k = arrayOfa.length;
        int m = 0;
        while (m < k)
        {
          d.a locala7 = arrayOfa[m];
          if (j == 0)
            localStringBuilder.append(str1);
          a(localStringBuilder, di.j(locala7), locala6, (Set)localObject);
          m++;
          j = 0;
        }
        for (int i = 0; i < locala1.ga.length; i++)
        {
          if (i > 0)
            localStringBuilder.append(str1);
          String str3 = di.j(locala1.ga[i]);
          String str4 = di.j(locala1.gb[i]);
          a(localStringBuilder, str3, locala6, (Set)localObject);
          localStringBuilder.append(str2);
          a(localStringBuilder, str4, locala6, (Set)localObject);
        }
      }
      label432: locala6 = locala4;
      localObject = null;
    }
  }

  private static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = Vs;
      arrayOfa[1] = Vt;
      arrayOfa[2] = Vu;
      Vv = arrayOfa;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.az
 * JD-Core Version:    0.6.0
 */