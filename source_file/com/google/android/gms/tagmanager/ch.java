package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class ch extends aj
{
  private static final String ID = a.ap.toString();
  private static final String Wc = b.bt.toString();
  private static final String Wd = b.bu.toString();
  private static final String We = b.cQ.toString();
  private static final String Wf = b.cK.toString();

  public ch()
  {
    super(str, arrayOfString);
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(Wc);
    d.a locala2 = (d.a)paramMap.get(Wd);
    if ((locala1 == null) || (locala1 == di.ku()) || (locala2 == null) || (locala2 == di.ku()))
      return di.ku();
    int i = 64;
    if (di.n((d.a)paramMap.get(We)).booleanValue())
      i = 66;
    d.a locala3 = (d.a)paramMap.get(Wf);
    int j;
    if (locala3 != null)
    {
      Long localLong = di.l(locala3);
      if (localLong == di.kp())
        return di.ku();
      j = localLong.intValue();
      if (j < 0)
        return di.ku();
    }
    else
    {
      j = 1;
    }
    try
    {
      String str1 = di.j(locala1);
      Matcher localMatcher = Pattern.compile(di.j(locala2), i).matcher(str1);
      boolean bool = localMatcher.find();
      String str2 = null;
      if (bool)
      {
        int k = localMatcher.groupCount();
        str2 = null;
        if (k >= j)
          str2 = localMatcher.group(j);
      }
      if (str2 == null)
        return di.ku();
      d.a locala4 = di.r(str2);
      return locala4;
    }
    catch (PatternSyntaxException localPatternSyntaxException)
    {
    }
    return di.ku();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ch
 * JD-Core Version:    0.6.0
 */