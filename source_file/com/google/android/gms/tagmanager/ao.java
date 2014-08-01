package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class ao extends aj
{
  private static final String ID = a.al.toString();
  private static final String US = b.bt.toString();
  private static final String UU;
  private static final String UY = b.bk.toString();

  static
  {
    UU = b.cS.toString();
  }

  public ao()
  {
    super(str, arrayOfString);
  }

  private byte[] c(String paramString, byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
    localMessageDigest.update(paramArrayOfByte);
    return localMessageDigest.digest();
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
    d.a locala2 = (d.a)paramMap.get(UY);
    String str2;
    if (locala2 == null)
      str2 = "MD5";
    while (true)
    {
      d.a locala3 = (d.a)paramMap.get(UU);
      String str3;
      label79: byte[] arrayOfByte;
      if (locala3 == null)
      {
        str3 = "text";
        if (!"text".equals(str3))
          break label134;
        arrayOfByte = str1.getBytes();
      }
      try
      {
        while (true)
        {
          d.a locala4 = di.r(j.d(c(str2, arrayOfByte)));
          return locala4;
          str2 = di.j(locala2);
          break;
          str3 = di.j(locala3);
          break label79;
          label134: if (!"base16".equals(str3))
            break label153;
          arrayOfByte = j.aX(str1);
        }
        label153: bh.t("Hash: unknown input format: " + str3);
        return di.ku();
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        bh.t("Hash: unknown algorithm: " + str2);
      }
    }
    return di.ku();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.ao
 * JD-Core Version:    0.6.0
 */