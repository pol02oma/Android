package io.vov.vitamio.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto
{
  private Cipher ecipher;

  public Crypto(String paramString)
  {
    try
    {
      setupCrypto(new SecretKeySpec(generateKey(paramString), "AES"));
      return;
    }
    catch (Exception localException)
    {
      Log.e("Crypto", localException);
    }
  }

  private static byte[] generateKey(String paramString)
  {
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes("UTF-8");
      byte[] arrayOfByte2 = MessageDigest.getInstance("SHA256").digest(arrayOfByte1);
      return arrayOfByte2;
    }
    catch (Exception localException)
    {
      Log.e("generateKey", localException);
    }
    return null;
  }

  public static String md5(String paramString)
  {
    Object localObject;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      String str;
      for (localObject = new BigInteger(1, localMessageDigest.digest()).toString(16); ((String)localObject).length() < 32; localObject = str)
        str = "0" + (String)localObject;
    }
    catch (Exception localException)
    {
      localObject = "";
    }
    return (String)localObject;
  }

  private PublicKey readKeyFromStream(InputStream paramInputStream)
    throws IOException
  {
    ObjectInputStream localObjectInputStream = new ObjectInputStream(new BufferedInputStream(paramInputStream));
    try
    {
      PublicKey localPublicKey = (PublicKey)localObjectInputStream.readObject();
      return localPublicKey;
    }
    catch (Exception localException)
    {
      Log.e("readKeyFromStream", localException);
      return null;
    }
    finally
    {
      localObjectInputStream.close();
    }
    throw localObject;
  }

  private void setupCrypto(SecretKey paramSecretKey)
  {
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
    try
    {
      this.ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      this.ecipher.init(1, paramSecretKey, localIvParameterSpec);
      return;
    }
    catch (Exception localException)
    {
      this.ecipher = null;
      Log.e("setupCrypto", localException);
    }
  }

  public String encrypt(String paramString)
  {
    if (this.ecipher == null)
      return "";
    try
    {
      String str = Base64.encodeToString(this.ecipher.doFinal(paramString.getBytes("UTF-8")), 2);
      return str;
    }
    catch (Exception localException)
    {
      Log.e("encryp", localException);
    }
    return "";
  }

  public String rsaEncrypt(InputStream paramInputStream, String paramString)
  {
    try
    {
      String str = rsaEncrypt(paramInputStream, paramString.getBytes("UTF-8"));
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return "";
  }

  public String rsaEncrypt(InputStream paramInputStream, byte[] paramArrayOfByte)
  {
    try
    {
      PublicKey localPublicKey = readKeyFromStream(paramInputStream);
      Cipher localCipher = Cipher.getInstance("RSA/ECB/NoPadding");
      localCipher.init(1, localPublicKey);
      String str = Base64.encodeToString(localCipher.doFinal(paramArrayOfByte), 2);
      return str;
    }
    catch (Exception localException)
    {
      Log.e("rsaEncrypt", localException);
    }
    return "";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     io.vov.vitamio.utils.Crypto
 * JD-Core Version:    0.6.0
 */