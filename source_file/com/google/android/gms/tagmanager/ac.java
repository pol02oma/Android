package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ac extends aj
{
  private static final String ID = a.aj.toString();
  private static final String US = b.bt.toString();
  private static final String UT = b.dt.toString();
  private static final String UU = b.cS.toString();
  private static final String UV = b.dB.toString();

  public ac()
  {
    super(str, arrayOfString);
  }

  public boolean iy()
  {
    return true;
  }

  public d.a u(Map<String, d.a> paramMap)
  {
    d.a locala1 = (d.a)paramMap.get(US);
    if ((locala1 == null) || (locala1 == di.ku()))
      return di.ku();
    String str1 = di.j(locala1);
    d.a locala2 = (d.a)paramMap.get(UU);
    String str2;
    d.a locala3;
    String str3;
    if (locala2 == null)
    {
      str2 = "text";
      locala3 = (d.a)paramMap.get(UV);
      if (locala3 != null)
        break label165;
      str3 = "base16";
      label79: d.a locala4 = (d.a)paramMap.get(UT);
      if ((locala4 == null) || (!di.n(locala4).booleanValue()))
        break label355;
    }
    label165: label328: label355: for (int i = 3; ; i = 2)
    {
      while (true)
      {
        byte[] arrayOfByte1;
        try
        {
          if (!"text".equals(str2))
            continue;
          byte[] arrayOfByte2 = str1.getBytes();
          arrayOfByte1 = arrayOfByte2;
          if ("base16".equals(str3))
          {
            str4 = j.d(arrayOfByte1);
            return di.r(str4);
            str2 = di.j(locala2);
            break;
            str3 = di.j(locala3);
            break label79;
            if (!"base16".equals(str2))
              continue;
            arrayOfByte1 = j.aX(str1);
            continue;
            if (!"base64".equals(str2))
              continue;
            arrayOfByte1 = Base64.decode(str1, i);
            continue;
            if (!"base64url".equals(str2))
              continue;
            arrayOfByte1 = Base64.decode(str1, i | 0x8);
            continue;
            bh.t("Encode: unknown input format: " + str2);
            d.a locala5 = di.ku();
            return locala5;
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          bh.t("Encode: invalid input:");
          return di.ku();
        }
        if ("base64".equals(str3))
        {
          str4 = Base64.encodeToString(arrayOfByte1, i);
          continue;
        }
        if (!"base64url".equals(str3))
          break label328;
        String str4 = Base64.encodeToString(arrayOfByte1, i | 0x8);
      }
      bh.t("Encode: unknown output format: " + str3);
      return di.ku();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ac
 * JD-Core Version:    0.6.0
 */