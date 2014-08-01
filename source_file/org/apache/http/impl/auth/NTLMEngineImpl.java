package org.apache.http.impl.auth;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.EncodingUtils;

final class NTLMEngineImpl
  implements NTLMEngine
{
  static final String DEFAULT_CHARSET = "ASCII";
  protected static final int FLAG_NEGOTIATE_128 = 536870912;
  protected static final int FLAG_NEGOTIATE_ALWAYS_SIGN = 32768;
  protected static final int FLAG_NEGOTIATE_KEY_EXCH = 1073741824;
  protected static final int FLAG_NEGOTIATE_NTLM = 512;
  protected static final int FLAG_NEGOTIATE_NTLM2 = 524288;
  protected static final int FLAG_NEGOTIATE_SEAL = 32;
  protected static final int FLAG_NEGOTIATE_SIGN = 16;
  protected static final int FLAG_TARGET_DESIRED = 4;
  protected static final int FLAG_UNICODE_ENCODING = 1;
  private static final SecureRandom RND_GEN;
  private static byte[] SIGNATURE;
  private String credentialCharset = "ASCII";

  static
  {
    try
    {
      SecureRandom localSecureRandom2 = SecureRandom.getInstance("SHA1PRNG");
      localSecureRandom1 = localSecureRandom2;
      RND_GEN = localSecureRandom1;
      byte[] arrayOfByte = EncodingUtils.getBytes("NTLMSSP", "ASCII");
      SIGNATURE = new byte[1 + arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, SIGNATURE, 0, arrayOfByte.length);
      SIGNATURE[arrayOfByte.length] = 0;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        SecureRandom localSecureRandom1 = null;
    }
  }

  static int F(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | paramInt3 & (paramInt1 ^ 0xFFFFFFFF);
  }

  static int G(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt2 | paramInt1 & paramInt3 | paramInt2 & paramInt3;
  }

  static int H(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt3 ^ (paramInt1 ^ paramInt2);
  }

  private static String convertDomain(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static String convertHost(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static byte[] createBlob(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = { 1, 1, 0, 0 };
    byte[] arrayOfByte2 = { 0, 0, 0, 0 };
    byte[] arrayOfByte3 = { 0, 0, 0, 0 };
    long l = 10000L * (11644473600000L + System.currentTimeMillis());
    byte[] arrayOfByte4 = new byte[8];
    for (int i = 0; i < 8; i++)
    {
      arrayOfByte4[i] = (byte)(int)l;
      l >>>= 8;
    }
    byte[] arrayOfByte5 = new byte[8 + (arrayOfByte1.length + arrayOfByte2.length + arrayOfByte4.length) + arrayOfByte3.length + paramArrayOfByte2.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 0, arrayOfByte1.length);
    int j = 0 + arrayOfByte1.length;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte5, j, arrayOfByte2.length);
    int k = j + arrayOfByte2.length;
    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, k, arrayOfByte4.length);
    int m = k + arrayOfByte4.length;
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte5, m, 8);
    int n = m + 8;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, n, arrayOfByte3.length);
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte5, n + arrayOfByte3.length, paramArrayOfByte2.length);
    return arrayOfByte5;
  }

  private static Key createDESKey(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte1 = new byte[7];
    System.arraycopy(paramArrayOfByte, paramInt, arrayOfByte1, 0, 7);
    byte[] arrayOfByte2 = new byte[8];
    arrayOfByte2[0] = arrayOfByte1[0];
    arrayOfByte2[1] = (byte)(arrayOfByte1[0] << 7 | (0xFF & arrayOfByte1[1]) >>> 1);
    arrayOfByte2[2] = (byte)(arrayOfByte1[1] << 6 | (0xFF & arrayOfByte1[2]) >>> 2);
    arrayOfByte2[3] = (byte)(arrayOfByte1[2] << 5 | (0xFF & arrayOfByte1[3]) >>> 3);
    arrayOfByte2[4] = (byte)(arrayOfByte1[3] << 4 | (0xFF & arrayOfByte1[4]) >>> 4);
    arrayOfByte2[5] = (byte)(arrayOfByte1[4] << 3 | (0xFF & arrayOfByte1[5]) >>> 5);
    arrayOfByte2[6] = (byte)(arrayOfByte1[5] << 2 | (0xFF & arrayOfByte1[6]) >>> 6);
    arrayOfByte2[7] = (byte)(arrayOfByte1[6] << 1);
    oddParity(arrayOfByte2);
    return new SecretKeySpec(arrayOfByte2, "DES");
  }

  static byte[] getLMResponse(String paramString, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    return lmResponse(lmHash(paramString), paramArrayOfByte);
  }

  static byte[] getLMv2Response(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    return lmv2Response(ntlmv2Hash(paramString1, paramString2, paramString3), paramArrayOfByte1, paramArrayOfByte2);
  }

  static byte[] getNTLM2SessionResponse(String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = ntlmHash(paramString);
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte1);
      localMessageDigest.update(paramArrayOfByte2);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      byte[] arrayOfByte3 = new byte[8];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, 8);
      byte[] arrayOfByte4 = lmResponse(arrayOfByte1, arrayOfByte3);
      return arrayOfByte4;
    }
    catch (Exception localException)
    {
      if ((localException instanceof NTLMEngineException))
        throw ((NTLMEngineException)localException);
    }
    throw new NTLMEngineException(localException.getMessage(), localException);
  }

  static byte[] getNTLMResponse(String paramString, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    return lmResponse(ntlmHash(paramString), paramArrayOfByte);
  }

  static byte[] getNTLMv2Response(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    return lmv2Response(ntlmv2Hash(paramString1, paramString2, paramString3), paramArrayOfByte1, createBlob(paramArrayOfByte2, paramArrayOfByte3));
  }

  private static byte[] lmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = paramString.toUpperCase().getBytes("US-ASCII");
      int i = Math.min(arrayOfByte1.length, 14);
      byte[] arrayOfByte2 = new byte[14];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
      Key localKey1 = createDESKey(arrayOfByte2, 0);
      Key localKey2 = createDESKey(arrayOfByte2, 7);
      byte[] arrayOfByte3 = "KGS!@#$%".getBytes("US-ASCII");
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, localKey1);
      byte[] arrayOfByte4 = localCipher.doFinal(arrayOfByte3);
      localCipher.init(1, localKey2);
      byte[] arrayOfByte5 = localCipher.doFinal(arrayOfByte3);
      byte[] arrayOfByte6 = new byte[16];
      System.arraycopy(arrayOfByte4, 0, arrayOfByte6, 0, 8);
      System.arraycopy(arrayOfByte5, 0, arrayOfByte6, 8, 8);
      return arrayOfByte6;
    }
    catch (Exception localException)
    {
    }
    throw new NTLMEngineException(localException.getMessage(), localException);
  }

  private static byte[] lmResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = new byte[21];
      System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0, 16);
      Key localKey1 = createDESKey(arrayOfByte1, 0);
      Key localKey2 = createDESKey(arrayOfByte1, 7);
      Key localKey3 = createDESKey(arrayOfByte1, 14);
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, localKey1);
      byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, localKey2);
      byte[] arrayOfByte3 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, localKey3);
      byte[] arrayOfByte4 = localCipher.doFinal(paramArrayOfByte2);
      byte[] arrayOfByte5 = new byte[24];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte5, 0, 8);
      System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 8, 8);
      System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 16, 8);
      return arrayOfByte5;
    }
    catch (Exception localException)
    {
    }
    throw new NTLMEngineException(localException.getMessage(), localException);
  }

  private static byte[] lmv2Response(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    HMACMD5 localHMACMD5 = new HMACMD5(paramArrayOfByte1);
    localHMACMD5.update(paramArrayOfByte2);
    localHMACMD5.update(paramArrayOfByte3);
    byte[] arrayOfByte1 = localHMACMD5.getOutput();
    byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfByte3.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
    System.arraycopy(paramArrayOfByte3, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfByte3.length);
    return arrayOfByte2;
  }

  private static byte[] makeNTLM2RandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[24];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
      Arrays.fill(arrayOfByte, 8, 24, 0);
      return arrayOfByte;
    }
  }

  private static byte[] makeRandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[8];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
      return arrayOfByte;
    }
  }

  private static byte[] ntlmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes("UnicodeLittleUnmarked");
      MD4 localMD4 = new MD4();
      localMD4.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMD4.getOutput();
      return arrayOfByte2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new NTLMEngineException("Unicode not supported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
  }

  private static byte[] ntlmv2Hash(String paramString1, String paramString2, String paramString3)
    throws NTLMEngineException
  {
    try
    {
      HMACMD5 localHMACMD5 = new HMACMD5(ntlmHash(paramString3));
      localHMACMD5.update(paramString2.toUpperCase().getBytes("UnicodeLittleUnmarked"));
      localHMACMD5.update(paramString1.getBytes("UnicodeLittleUnmarked"));
      byte[] arrayOfByte = localHMACMD5.getOutput();
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new NTLMEngineException("Unicode not supported! " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
  }

  private static void oddParity(byte[] paramArrayOfByte)
  {
    int i = 0;
    if (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i];
      int k;
      if ((0x1 & (j >>> 7 ^ j >>> 6 ^ j >>> 5 ^ j >>> 4 ^ j >>> 3 ^ j >>> 2 ^ j >>> 1)) == 0)
      {
        k = 1;
        label48: if (k == 0)
          break label72;
        paramArrayOfByte[i] = (byte)(0x1 | paramArrayOfByte[i]);
      }
      while (true)
      {
        i++;
        break;
        k = 0;
        break label48;
        label72: paramArrayOfByte[i] = (byte)(0xFFFFFFFE & paramArrayOfByte[i]);
      }
    }
  }

  private static byte[] readSecurityBuffer(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    int i = readUShort(paramArrayOfByte, paramInt);
    int j = readULong(paramArrayOfByte, paramInt + 4);
    if (paramArrayOfByte.length < j + i)
      throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(paramArrayOfByte, j, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  private static int readULong(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 4)
      throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    return 0xFF & paramArrayOfByte[paramInt] | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 8 | (0xFF & paramArrayOfByte[(paramInt + 2)]) << 16 | (0xFF & paramArrayOfByte[(paramInt + 3)]) << 24;
  }

  private static int readUShort(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 2)
      throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    return 0xFF & paramArrayOfByte[paramInt] | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 8;
  }

  static int rotintlft(int paramInt1, int paramInt2)
  {
    return paramInt1 << paramInt2 | paramInt1 >>> 32 - paramInt2;
  }

  private static String stripDotSuffix(String paramString)
  {
    int i = paramString.indexOf(".");
    if (i != -1)
      paramString = paramString.substring(0, i);
    return paramString;
  }

  static void writeULong(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = (byte)(paramInt1 & 0xFF);
    paramArrayOfByte[(paramInt2 + 1)] = (byte)(0xFF & paramInt1 >> 8);
    paramArrayOfByte[(paramInt2 + 2)] = (byte)(0xFF & paramInt1 >> 16);
    paramArrayOfByte[(paramInt2 + 3)] = (byte)(0xFF & paramInt1 >> 24);
  }

  public String generateType1Msg(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return getType1Message(paramString2, paramString1);
  }

  public String generateType3Msg(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    Type2Message localType2Message = new Type2Message(paramString5);
    return getType3Message(paramString1, paramString2, paramString4, paramString3, localType2Message.getChallenge(), localType2Message.getFlags(), localType2Message.getTarget(), localType2Message.getTargetInfo());
  }

  String getCredentialCharset()
  {
    return this.credentialCharset;
  }

  final String getResponseFor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    if ((paramString1 == null) || (paramString1.trim().equals("")))
      return getType1Message(paramString4, paramString5);
    Type2Message localType2Message = new Type2Message(paramString1);
    return getType3Message(paramString2, paramString3, paramString4, paramString5, localType2Message.getChallenge(), localType2Message.getFlags(), localType2Message.getTarget(), localType2Message.getTargetInfo());
  }

  String getType1Message(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return new Type1Message(paramString2, paramString1).getResponse();
  }

  String getType3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    return new Type3Message(paramString4, paramString3, paramString1, paramString2, paramArrayOfByte1, paramInt, paramString5, paramArrayOfByte2).getResponse();
  }

  void setCredentialCharset(String paramString)
  {
    this.credentialCharset = paramString;
  }

  static class HMACMD5
  {
    protected byte[] ipad;
    protected MessageDigest md5;
    protected byte[] opad;

    HMACMD5(byte[] paramArrayOfByte)
      throws NTLMEngineException
    {
      int j;
      try
      {
        this.md5 = MessageDigest.getInstance("MD5");
        this.ipad = new byte[64];
        this.opad = new byte[64];
        int i = paramArrayOfByte.length;
        if (i > 64)
        {
          this.md5.update(paramArrayOfByte);
          paramArrayOfByte = this.md5.digest();
          i = paramArrayOfByte.length;
        }
        for (j = 0; j < i; j++)
        {
          this.ipad[j] = (byte)(0x36 ^ paramArrayOfByte[j]);
          this.opad[j] = (byte)(0x5C ^ paramArrayOfByte[j]);
        }
      }
      catch (Exception localException)
      {
        throw new NTLMEngineException("Error getting md5 message digest implementation: " + localException.getMessage(), localException);
      }
      while (j < 64)
      {
        this.ipad[j] = 54;
        this.opad[j] = 92;
        j++;
      }
      this.md5.reset();
      this.md5.update(this.ipad);
    }

    byte[] getOutput()
    {
      byte[] arrayOfByte = this.md5.digest();
      this.md5.update(this.opad);
      return this.md5.digest(arrayOfByte);
    }

    void update(byte[] paramArrayOfByte)
    {
      this.md5.update(paramArrayOfByte);
    }

    void update(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      this.md5.update(paramArrayOfByte, paramInt1, paramInt2);
    }
  }

  static class MD4
  {
    protected int A = 1732584193;
    protected int B = -271733879;
    protected int C = -1732584194;
    protected int D = 271733878;
    protected long count = 0L;
    protected byte[] dataBuffer = new byte[64];

    byte[] getOutput()
    {
      int i = (int)(0x3F & this.count);
      if (i < 56);
      byte[] arrayOfByte1;
      for (int j = 56 - i; ; j = 120 - i)
      {
        arrayOfByte1 = new byte[j + 8];
        arrayOfByte1[0] = -128;
        for (int k = 0; k < 8; k++)
          arrayOfByte1[(j + k)] = (byte)(int)(8L * this.count >>> k * 8);
      }
      update(arrayOfByte1);
      byte[] arrayOfByte2 = new byte[16];
      NTLMEngineImpl.writeULong(arrayOfByte2, this.A, 0);
      NTLMEngineImpl.writeULong(arrayOfByte2, this.B, 4);
      NTLMEngineImpl.writeULong(arrayOfByte2, this.C, 8);
      NTLMEngineImpl.writeULong(arrayOfByte2, this.D, 12);
      return arrayOfByte2;
    }

    protected void processBuffer()
    {
      int[] arrayOfInt = new int[16];
      for (int i = 0; i < 16; i++)
        arrayOfInt[i] = ((0xFF & this.dataBuffer[(i * 4)]) + ((0xFF & this.dataBuffer[(1 + i * 4)]) << 8) + ((0xFF & this.dataBuffer[(2 + i * 4)]) << 16) + ((0xFF & this.dataBuffer[(3 + i * 4)]) << 24));
      int j = this.A;
      int k = this.B;
      int m = this.C;
      int n = this.D;
      round1(arrayOfInt);
      round2(arrayOfInt);
      round3(arrayOfInt);
      this.A = (j + this.A);
      this.B = (k + this.B);
      this.C = (m + this.C);
      this.D = (n + this.D);
    }

    protected void round1(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[0], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[1], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[2], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[3], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[4], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[5], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[6], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[7], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[8], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[9], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[10], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[11], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[12], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[13], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[14], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[15], 19);
    }

    protected void round2(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[0]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[4]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[8]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[12]), 13);
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[1]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[5]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[9]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[13]), 13);
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[2]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[6]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[10]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[14]), 13);
      this.A = NTLMEngineImpl.rotintlft(1518500249 + (this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[3]), 3);
      this.D = NTLMEngineImpl.rotintlft(1518500249 + (this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[7]), 5);
      this.C = NTLMEngineImpl.rotintlft(1518500249 + (this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[11]), 9);
      this.B = NTLMEngineImpl.rotintlft(1518500249 + (this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[15]), 13);
    }

    protected void round3(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[0]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[8]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[4]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[12]), 15);
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[2]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[10]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[6]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[14]), 15);
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[1]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[9]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[5]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[13]), 15);
      this.A = NTLMEngineImpl.rotintlft(1859775393 + (this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[3]), 3);
      this.D = NTLMEngineImpl.rotintlft(1859775393 + (this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[11]), 9);
      this.C = NTLMEngineImpl.rotintlft(1859775393 + (this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[7]), 11);
      this.B = NTLMEngineImpl.rotintlft(1859775393 + (this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[15]), 15);
    }

    void update(byte[] paramArrayOfByte)
    {
      int i = (int)(0x3F & this.count);
      int j = 0;
      while (i + (paramArrayOfByte.length - j) >= this.dataBuffer.length)
      {
        int m = this.dataBuffer.length - i;
        System.arraycopy(paramArrayOfByte, j, this.dataBuffer, i, m);
        this.count += m;
        j += m;
        processBuffer();
        i = 0;
      }
      if (j < paramArrayOfByte.length)
      {
        int k = paramArrayOfByte.length - j;
        System.arraycopy(paramArrayOfByte, j, this.dataBuffer, i, k);
        this.count += k;
        (i + k);
      }
    }
  }

  static class NTLMMessage
  {
    private int currentOutputPosition = 0;
    private byte[] messageContents = null;

    NTLMMessage()
    {
    }

    NTLMMessage(String paramString, int paramInt)
      throws NTLMEngineException
    {
      this.messageContents = Base64.decodeBase64(EncodingUtils.getBytes(paramString, "ASCII"));
      if (this.messageContents.length < NTLMEngineImpl.SIGNATURE.length)
        throw new NTLMEngineException("NTLM message decoding error - packet too short");
      for (int i = 0; i < NTLMEngineImpl.SIGNATURE.length; i++)
      {
        if (this.messageContents[i] == NTLMEngineImpl.SIGNATURE[i])
          continue;
        throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
      }
      int j = readULong(NTLMEngineImpl.SIGNATURE.length);
      if (j != paramInt)
        throw new NTLMEngineException("NTLM type " + Integer.toString(paramInt) + " message expected - instead got type " + Integer.toString(j));
      this.currentOutputPosition = this.messageContents.length;
    }

    protected void addByte(byte paramByte)
    {
      this.messageContents[this.currentOutputPosition] = paramByte;
      this.currentOutputPosition = (1 + this.currentOutputPosition);
    }

    protected void addBytes(byte[] paramArrayOfByte)
    {
      for (int i = 0; i < paramArrayOfByte.length; i++)
      {
        this.messageContents[this.currentOutputPosition] = paramArrayOfByte[i];
        this.currentOutputPosition = (1 + this.currentOutputPosition);
      }
    }

    protected void addULong(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(0xFF & paramInt >> 8));
      addByte((byte)(0xFF & paramInt >> 16));
      addByte((byte)(0xFF & paramInt >> 24));
    }

    protected void addUShort(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(0xFF & paramInt >> 8));
    }

    protected int getMessageLength()
    {
      return this.currentOutputPosition;
    }

    protected int getPreambleLength()
    {
      return 4 + NTLMEngineImpl.SIGNATURE.length;
    }

    String getResponse()
    {
      byte[] arrayOfByte2;
      if (this.messageContents.length > this.currentOutputPosition)
      {
        arrayOfByte2 = new byte[this.currentOutputPosition];
        for (int i = 0; i < this.currentOutputPosition; i++)
          arrayOfByte2[i] = this.messageContents[i];
      }
      for (byte[] arrayOfByte1 = arrayOfByte2; ; arrayOfByte1 = this.messageContents)
        return EncodingUtils.getAsciiString(Base64.encodeBase64(arrayOfByte1));
    }

    protected void prepareResponse(int paramInt1, int paramInt2)
    {
      this.messageContents = new byte[paramInt1];
      this.currentOutputPosition = 0;
      addBytes(NTLMEngineImpl.SIGNATURE);
      addULong(paramInt2);
    }

    protected byte readByte(int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramInt + 1)
        throw new NTLMEngineException("NTLM: Message too short");
      return this.messageContents[paramInt];
    }

    protected void readBytes(byte[] paramArrayOfByte, int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramInt + paramArrayOfByte.length)
        throw new NTLMEngineException("NTLM: Message too short");
      System.arraycopy(this.messageContents, paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    protected byte[] readSecurityBuffer(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.access$300(this.messageContents, paramInt);
    }

    protected int readULong(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.access$200(this.messageContents, paramInt);
    }

    protected int readUShort(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.access$100(this.messageContents, paramInt);
    }
  }

  static class Type1Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] domainBytes;
    protected byte[] hostBytes;

    Type1Message(String paramString1, String paramString2)
      throws NTLMEngineException
    {
      try
      {
        String str1 = NTLMEngineImpl.access$400(paramString2);
        String str2 = NTLMEngineImpl.access$500(paramString1);
        this.hostBytes = str1.getBytes("UnicodeLittleUnmarked");
        this.domainBytes = str2.toUpperCase().getBytes("UnicodeLittleUnmarked");
        return;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
      throw new NTLMEngineException("Unicode unsupported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }

    String getResponse()
    {
      prepareResponse(32 + this.hostBytes.length + this.domainBytes.length, 1);
      addULong(537395765);
      addUShort(this.domainBytes.length);
      addUShort(this.domainBytes.length);
      addULong(32 + this.hostBytes.length);
      addUShort(this.hostBytes.length);
      addUShort(this.hostBytes.length);
      addULong(32);
      addBytes(this.hostBytes);
      addBytes(this.domainBytes);
      return super.getResponse();
    }
  }

  static class Type2Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] challenge = new byte[8];
    protected int flags;
    protected String target;
    protected byte[] targetInfo;

    Type2Message(String paramString)
      throws NTLMEngineException
    {
      super(2);
      readBytes(this.challenge, 24);
      this.flags = readULong(20);
      if ((0x1 & this.flags) == 0)
        throw new NTLMEngineException("NTLM type 2 message has flags that make no sense: " + Integer.toString(this.flags));
      this.target = null;
      byte[] arrayOfByte2;
      if (getMessageLength() >= 20)
      {
        arrayOfByte2 = readSecurityBuffer(12);
        if (arrayOfByte2.length == 0);
      }
      try
      {
        this.target = new String(arrayOfByte2, "UnicodeLittleUnmarked");
        this.targetInfo = null;
        if (getMessageLength() >= 48)
        {
          byte[] arrayOfByte1 = readSecurityBuffer(40);
          if (arrayOfByte1.length != 0)
            this.targetInfo = arrayOfByte1;
        }
        return;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
      throw new NTLMEngineException(localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }

    byte[] getChallenge()
    {
      return this.challenge;
    }

    int getFlags()
    {
      return this.flags;
    }

    String getTarget()
    {
      return this.target;
    }

    byte[] getTargetInfo()
    {
      return this.targetInfo;
    }
  }

  static class Type3Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] domainBytes;
    protected byte[] hostBytes;
    protected byte[] lmResp;
    protected byte[] ntResp;
    protected int type2Flags;
    protected byte[] userBytes;

    Type3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
      throws NTLMEngineException
    {
      this.type2Flags = paramInt;
      String str1 = NTLMEngineImpl.access$400(paramString2);
      String str2 = NTLMEngineImpl.access$500(paramString1);
      if ((paramArrayOfByte2 != null) && (paramString5 != null));
      try
      {
        byte[] arrayOfByte2 = NTLMEngineImpl.access$600();
        this.ntResp = NTLMEngineImpl.getNTLMv2Response(paramString5, paramString3, paramString4, paramArrayOfByte1, arrayOfByte2, paramArrayOfByte2);
        this.lmResp = NTLMEngineImpl.getLMv2Response(paramString5, paramString3, paramString4, paramArrayOfByte1, arrayOfByte2);
      }
      catch (NTLMEngineException localNTLMEngineException)
      {
        try
        {
          while (true)
          {
            this.domainBytes = str2.toUpperCase().getBytes("UnicodeLittleUnmarked");
            this.hostBytes = str1.getBytes("UnicodeLittleUnmarked");
            this.userBytes = paramString3.getBytes("UnicodeLittleUnmarked");
            return;
            if ((0x80000 & paramInt) != 0)
            {
              byte[] arrayOfByte1 = NTLMEngineImpl.access$700();
              this.ntResp = NTLMEngineImpl.getNTLM2SessionResponse(paramString4, paramArrayOfByte1, arrayOfByte1);
              this.lmResp = arrayOfByte1;
              continue;
              localNTLMEngineException = localNTLMEngineException;
              this.ntResp = new byte[0];
              this.lmResp = NTLMEngineImpl.getLMResponse(paramString4, paramArrayOfByte1);
              continue;
            }
            this.ntResp = NTLMEngineImpl.getNTLMResponse(paramString4, paramArrayOfByte1);
            this.lmResp = NTLMEngineImpl.getLMResponse(paramString4, paramArrayOfByte1);
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
        }
      }
      throw new NTLMEngineException("Unicode not supported: " + localUnsupportedEncodingException.getMessage(), localUnsupportedEncodingException);
    }

    String getResponse()
    {
      int i = this.ntResp.length;
      int j = this.lmResp.length;
      int k = this.domainBytes.length;
      int m = this.hostBytes.length;
      int n = this.userBytes.length;
      int i1 = 64 + j;
      int i2 = i1 + i;
      int i3 = i2 + k;
      int i4 = i3 + n;
      int i5 = 0 + (i4 + m);
      prepareResponse(i5, 3);
      addUShort(j);
      addUShort(j);
      addULong(64);
      addUShort(i);
      addUShort(i);
      addULong(i1);
      addUShort(k);
      addUShort(k);
      addULong(i2);
      addUShort(n);
      addUShort(n);
      addULong(i3);
      addUShort(m);
      addUShort(m);
      addULong(i4);
      addULong(0);
      addULong(i5);
      addULong(0x20000205 | 0x80000 & this.type2Flags | 0x10 & this.type2Flags | 0x20 & this.type2Flags | 0x40000000 & this.type2Flags | 0x8000 & this.type2Flags);
      addBytes(this.lmResp);
      addBytes(this.ntResp);
      addBytes(this.domainBytes);
      addBytes(this.userBytes);
      addBytes(this.hostBytes);
      return super.getResponse();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.impl.auth.NTLMEngineImpl
 * JD-Core Version:    0.6.0
 */