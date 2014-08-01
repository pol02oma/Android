package org.apache.http.conn.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpInetSocketAddress;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.LayeredSchemeSocketFactory;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SchemeLayeredSocketFactory;
import org.apache.http.params.HttpParams;

@ThreadSafe
public class SSLSocketFactory
  implements SchemeLayeredSocketFactory, LayeredSchemeSocketFactory, LayeredSocketFactory
{
  public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = new AllowAllHostnameVerifier();
  public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = new BrowserCompatHostnameVerifier();
  private static final char[] EMPTY_PASSWORD;
  public static final String SSL = "SSL";
  public static final String SSLV2 = "SSLv2";
  public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = new StrictHostnameVerifier();
  public static final String TLS = "TLS";
  private volatile X509HostnameVerifier hostnameVerifier;
  private final HostNameResolver nameResolver;
  private final javax.net.ssl.SSLSocketFactory socketfactory;

  static
  {
    EMPTY_PASSWORD = "".toCharArray();
  }

  @Deprecated
  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, HostNameResolver paramHostNameResolver)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, null), paramHostNameResolver);
  }

  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, paramTrustStrategy), paramX509HostnameVerifier);
  }

  public SSLSocketFactory(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this(createSSLContext(paramString1, paramKeyStore1, paramString2, paramKeyStore2, paramSecureRandom, null), paramX509HostnameVerifier);
  }

  public SSLSocketFactory(KeyStore paramKeyStore)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, paramKeyStore, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(KeyStore paramKeyStore, String paramString)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", paramKeyStore, paramString, null, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(KeyStore paramKeyStore1, String paramString, KeyStore paramKeyStore2)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", paramKeyStore1, paramString, paramKeyStore2, null, null, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(SSLContext paramSSLContext)
  {
    this(paramSSLContext, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  @Deprecated
  public SSLSocketFactory(SSLContext paramSSLContext, HostNameResolver paramHostNameResolver)
  {
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
    this.nameResolver = paramHostNameResolver;
  }

  public SSLSocketFactory(SSLContext paramSSLContext, X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramSSLContext == null)
      throw new IllegalArgumentException("SSL context may not be null");
    this.socketfactory = paramSSLContext.getSocketFactory();
    this.hostnameVerifier = paramX509HostnameVerifier;
    this.nameResolver = null;
  }

  public SSLSocketFactory(javax.net.ssl.SSLSocketFactory paramSSLSocketFactory, X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramSSLSocketFactory == null)
      throw new IllegalArgumentException("SSL socket factory may not be null");
    this.socketfactory = paramSSLSocketFactory;
    this.hostnameVerifier = paramX509HostnameVerifier;
    this.nameResolver = null;
  }

  public SSLSocketFactory(TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, null, null, paramTrustStrategy, BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
  }

  public SSLSocketFactory(TrustStrategy paramTrustStrategy, X509HostnameVerifier paramX509HostnameVerifier)
    throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
  {
    this("TLS", null, null, null, null, paramTrustStrategy, paramX509HostnameVerifier);
  }

  private static SSLContext createDefaultSSLContext()
    throws SSLInitializationException
  {
    try
    {
      SSLContext localSSLContext = createSSLContext("TLS", null, null, null, null, null);
      return localSSLContext;
    }
    catch (Exception localException)
    {
    }
    throw new SSLInitializationException("Failure initializing default SSL context", localException);
  }

  private static SSLContext createSSLContext(String paramString1, KeyStore paramKeyStore1, String paramString2, KeyStore paramKeyStore2, SecureRandom paramSecureRandom, TrustStrategy paramTrustStrategy)
    throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException
  {
    if (paramString1 == null)
      paramString1 = "TLS";
    KeyManagerFactory localKeyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    if (paramString2 != null);
    KeyManager[] arrayOfKeyManager;
    TrustManager[] arrayOfTrustManager;
    for (char[] arrayOfChar = paramString2.toCharArray(); ; arrayOfChar = null)
    {
      localKeyManagerFactory.init(paramKeyStore1, arrayOfChar);
      arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
      TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      localTrustManagerFactory.init(paramKeyStore2);
      arrayOfTrustManager = localTrustManagerFactory.getTrustManagers();
      if ((arrayOfTrustManager == null) || (paramTrustStrategy == null))
        break;
      for (int i = 0; i < arrayOfTrustManager.length; i++)
      {
        TrustManager localTrustManager = arrayOfTrustManager[i];
        if (!(localTrustManager instanceof X509TrustManager))
          continue;
        arrayOfTrustManager[i] = new TrustManagerDecorator((X509TrustManager)localTrustManager, paramTrustStrategy);
      }
    }
    SSLContext localSSLContext = SSLContext.getInstance(paramString1);
    localSSLContext.init(arrayOfKeyManager, arrayOfTrustManager, paramSecureRandom);
    return localSSLContext;
  }

  private static SSLContext createSystemSSLContext()
    throws SSLInitializationException
  {
    try
    {
      SSLContext localSSLContext = createSystemSSLContext("TLS", null);
      return localSSLContext;
    }
    catch (Exception localException)
    {
    }
    throw new SSLInitializationException("Failure initializing default system SSL context", localException);
  }

  private static SSLContext createSystemSSLContext(String paramString, SecureRandom paramSecureRandom)
    throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException
  {
    if (paramString == null)
      paramString = "TLS";
    String str1 = System.getProperty("ssl.TrustManagerFactory.algorithm");
    if (str1 == null)
      str1 = TrustManagerFactory.getDefaultAlgorithm();
    String str2 = System.getProperty("javax.net.ssl.trustStoreType");
    if (str2 == null)
      str2 = KeyStore.getDefaultType();
    TrustManagerFactory localTrustManagerFactory;
    String str6;
    String str7;
    KeyManagerFactory localKeyManagerFactory;
    label101: SSLContext localSSLContext;
    KeyManager[] arrayOfKeyManager;
    if ("none".equalsIgnoreCase(str2))
    {
      localTrustManagerFactory = TrustManagerFactory.getInstance(str1);
      str6 = System.getProperty("ssl.KeyManagerFactory.algorithm");
      if (str6 == null)
        str6 = KeyManagerFactory.getDefaultAlgorithm();
      str7 = System.getProperty("javax.net.ssl.keyStoreType");
      if (str7 == null)
        str7 = KeyStore.getDefaultType();
      if (!"none".equalsIgnoreCase(str7))
        break label415;
      localKeyManagerFactory = KeyManagerFactory.getInstance(str6);
      localSSLContext = SSLContext.getInstance(paramString);
      if (localKeyManagerFactory == null)
        break label581;
      arrayOfKeyManager = localKeyManagerFactory.getKeyManagers();
      label119: if (localTrustManagerFactory == null)
        break label587;
    }
    label193: label322: label581: label587: for (TrustManager[] arrayOfTrustManager = localTrustManagerFactory.getTrustManagers(); ; arrayOfTrustManager = null)
    {
      while (true)
      {
        while (true)
        {
          localSSLContext.init(arrayOfKeyManager, arrayOfTrustManager, paramSecureRandom);
          return localSSLContext;
          String str3 = System.getProperty("javax.net.ssl.trustStore");
          if (str3 != null)
          {
            File localFile1 = new File(str3);
            localTrustManagerFactory = TrustManagerFactory.getInstance(str1);
            String str4 = System.getProperty("javax.net.ssl.trustStoreProvider");
            KeyStore localKeyStore1;
            String str5;
            FileInputStream localFileInputStream1;
            if (str4 != null)
            {
              localKeyStore1 = KeyStore.getInstance(str2, str4);
              str5 = System.getProperty("javax.net.ssl.trustStorePassword");
              localFileInputStream1 = new FileInputStream(localFile1);
              if (str5 == null)
                break label256;
            }
            try
            {
              for (char[] arrayOfChar1 = str5.toCharArray(); ; arrayOfChar1 = EMPTY_PASSWORD)
              {
                localKeyStore1.load(localFileInputStream1, arrayOfChar1);
                localFileInputStream1.close();
                localTrustManagerFactory.init(localKeyStore1);
                break;
                localKeyStore1 = KeyStore.getInstance(str2);
                break label193;
              }
            }
            finally
            {
              localFileInputStream1.close();
            }
          }
        }
        File localFile3 = new File(System.getProperty("java.home"));
        File localFile4 = new File(localFile3, "lib/security/jssecacerts");
        File localFile5;
        KeyStore localKeyStore3;
        String str11;
        FileInputStream localFileInputStream3;
        if (!localFile4.exists())
        {
          localFile5 = new File(localFile3, "lib/security/cacerts");
          localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
          localKeyStore3 = KeyStore.getInstance(KeyStore.getDefaultType());
          str11 = System.getProperty("javax.net.ssl.trustStorePassword");
          localFileInputStream3 = new FileInputStream(localFile5);
          if (str11 == null)
            break label399;
        }
        try
        {
          for (char[] arrayOfChar4 = str11.toCharArray(); ; arrayOfChar4 = null)
          {
            localKeyStore3.load(localFileInputStream3, arrayOfChar4);
            localFileInputStream3.close();
            localTrustManagerFactory.init(localKeyStore3);
            break;
            localFile5 = localFile4;
            break label322;
          }
        }
        finally
        {
          localFileInputStream3.close();
        }
      }
      String str8 = System.getProperty("javax.net.ssl.keyStore");
      File localFile2 = null;
      if (str8 != null)
        localFile2 = new File(str8);
      localKeyManagerFactory = null;
      if (localFile2 == null)
        break label101;
      localKeyManagerFactory = KeyManagerFactory.getInstance(str6);
      String str9 = System.getProperty("javax.net.ssl.keyStoreProvider");
      KeyStore localKeyStore2;
      label477: String str10;
      FileInputStream localFileInputStream2;
      if (str9 != null)
      {
        localKeyStore2 = KeyStore.getInstance(str7, str9);
        str10 = System.getProperty("javax.net.ssl.keyStorePassword");
        localFileInputStream2 = new FileInputStream(localFile2);
        if (str10 == null)
          break label555;
      }
      while (true)
      {
        try
        {
          char[] arrayOfChar2 = str10.toCharArray();
          localKeyStore2.load(localFileInputStream2, arrayOfChar2);
          localFileInputStream2.close();
          if (str10 != null)
          {
            arrayOfChar3 = str10.toCharArray();
            localKeyManagerFactory.init(localKeyStore2, arrayOfChar3);
            break;
            localKeyStore2 = KeyStore.getInstance(str7);
            break label477;
            arrayOfChar2 = EMPTY_PASSWORD;
            continue;
          }
        }
        finally
        {
          localFileInputStream2.close();
        }
        char[] arrayOfChar3 = EMPTY_PASSWORD;
      }
      arrayOfKeyManager = null;
      break label119;
    }
  }

  public static SSLSocketFactory getSocketFactory()
    throws SSLInitializationException
  {
    return new SSLSocketFactory(createDefaultSSLContext());
  }

  public static SSLSocketFactory getSystemSocketFactory()
    throws SSLInitializationException
  {
    return new SSLSocketFactory(createSystemSSLContext());
  }

  @Deprecated
  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    InetSocketAddress localInetSocketAddress;
    if (paramInetAddress == null)
    {
      localInetSocketAddress = null;
      if (paramInt2 <= 0);
    }
    else
    {
      if (paramInt2 < 0)
        paramInt2 = 0;
      localInetSocketAddress = new InetSocketAddress(paramInetAddress, paramInt2);
    }
    if (this.nameResolver != null);
    for (InetAddress localInetAddress = this.nameResolver.resolve(paramString); ; localInetAddress = InetAddress.getByName(paramString))
      return connectSocket(paramSocket, new HttpInetSocketAddress(new HttpHost(paramString, paramInt1), localInetAddress, paramInt1), localInetSocketAddress, paramHttpParams);
  }

  // ERROR //
  public Socket connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress1, InetSocketAddress paramInetSocketAddress2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +14 -> 15
    //   4: new 102	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc_w 296
    //   11: invokespecial 107	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   14: athrow
    //   15: aload 4
    //   17: ifnonnull +14 -> 31
    //   20: new 102	java/lang/IllegalArgumentException
    //   23: dup
    //   24: ldc_w 298
    //   27: invokespecial 107	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   30: athrow
    //   31: aload_1
    //   32: ifnull +112 -> 144
    //   35: aload_1
    //   36: astore 5
    //   38: aload_3
    //   39: ifnull +19 -> 58
    //   42: aload 5
    //   44: aload 4
    //   46: invokestatic 304	org/apache/http/params/HttpConnectionParams:getSoReuseaddr	(Lorg/apache/http/params/HttpParams;)Z
    //   49: invokevirtual 310	java/net/Socket:setReuseAddress	(Z)V
    //   52: aload 5
    //   54: aload_3
    //   55: invokevirtual 314	java/net/Socket:bind	(Ljava/net/SocketAddress;)V
    //   58: aload 4
    //   60: invokestatic 318	org/apache/http/params/HttpConnectionParams:getConnectionTimeout	(Lorg/apache/http/params/HttpParams;)I
    //   63: istore 6
    //   65: aload 4
    //   67: invokestatic 321	org/apache/http/params/HttpConnectionParams:getSoTimeout	(Lorg/apache/http/params/HttpParams;)I
    //   70: istore 7
    //   72: aload 5
    //   74: iload 7
    //   76: invokevirtual 325	java/net/Socket:setSoTimeout	(I)V
    //   79: aload 5
    //   81: aload_2
    //   82: iload 6
    //   84: invokevirtual 329	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   87: aload_2
    //   88: instanceof 276
    //   91: ifeq +101 -> 192
    //   94: aload_2
    //   95: checkcast 276	org/apache/http/conn/HttpInetSocketAddress
    //   98: invokevirtual 333	org/apache/http/conn/HttpInetSocketAddress:getHttpHost	()Lorg/apache/http/HttpHost;
    //   101: invokevirtual 336	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   104: astore 9
    //   106: aload 5
    //   108: instanceof 338
    //   111: ifeq +90 -> 201
    //   114: aload 5
    //   116: checkcast 338	javax/net/ssl/SSLSocket
    //   119: astore 11
    //   121: aload_0
    //   122: getfield 98	org/apache/http/conn/ssl/SSLSocketFactory:hostnameVerifier	Lorg/apache/http/conn/ssl/X509HostnameVerifier;
    //   125: ifnull +16 -> 141
    //   128: aload_0
    //   129: getfield 98	org/apache/http/conn/ssl/SSLSocketFactory:hostnameVerifier	Lorg/apache/http/conn/ssl/X509HostnameVerifier;
    //   132: aload 9
    //   134: aload 11
    //   136: invokeinterface 344 3 0
    //   141: aload 11
    //   143: areturn
    //   144: aload_0
    //   145: getfield 96	org/apache/http/conn/ssl/SSLSocketFactory:socketfactory	Ljavax/net/ssl/SSLSocketFactory;
    //   148: invokevirtual 350	javax/net/ssl/SSLSocketFactory:createSocket	()Ljava/net/Socket;
    //   151: astore 5
    //   153: goto -115 -> 38
    //   156: astore 8
    //   158: new 263	org/apache/http/conn/ConnectTimeoutException
    //   161: dup
    //   162: new 352	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 353	java/lang/StringBuilder:<init>	()V
    //   169: ldc_w 355
    //   172: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload_2
    //   176: invokevirtual 362	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   179: ldc_w 364
    //   182: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 367	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: invokespecial 368	org/apache/http/conn/ConnectTimeoutException:<init>	(Ljava/lang/String;)V
    //   191: athrow
    //   192: aload_2
    //   193: invokevirtual 369	java/net/InetSocketAddress:getHostName	()Ljava/lang/String;
    //   196: astore 9
    //   198: goto -92 -> 106
    //   201: aload_2
    //   202: invokevirtual 373	java/net/InetSocketAddress:getPort	()I
    //   205: istore 10
    //   207: aload_0
    //   208: getfield 96	org/apache/http/conn/ssl/SSLSocketFactory:socketfactory	Ljavax/net/ssl/SSLSocketFactory;
    //   211: aload 5
    //   213: aload 9
    //   215: iload 10
    //   217: iconst_1
    //   218: invokevirtual 376	javax/net/ssl/SSLSocketFactory:createSocket	(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
    //   221: checkcast 338	javax/net/ssl/SSLSocket
    //   224: astore 11
    //   226: aload_0
    //   227: aload 11
    //   229: invokevirtual 380	org/apache/http/conn/ssl/SSLSocketFactory:prepareSocket	(Ljavax/net/ssl/SSLSocket;)V
    //   232: goto -111 -> 121
    //   235: astore 12
    //   237: aload 11
    //   239: invokevirtual 381	javax/net/ssl/SSLSocket:close	()V
    //   242: aload 12
    //   244: athrow
    //   245: astore 13
    //   247: goto -5 -> 242
    //
    // Exception table:
    //   from	to	target	type
    //   72	87	156	java/net/SocketTimeoutException
    //   128	141	235	java/io/IOException
    //   237	242	245	java/lang/Exception
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, HttpParams paramHttpParams)
    throws IOException, UnknownHostException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, true);
    prepareSocket(localSSLSocket);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, localSSLSocket);
    return localSSLSocket;
  }

  public Socket createLayeredSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    prepareSocket(localSSLSocket);
    if (this.hostnameVerifier != null)
      this.hostnameVerifier.verify(paramString, localSSLSocket);
    return localSSLSocket;
  }

  @Deprecated
  public Socket createSocket()
    throws IOException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket();
    prepareSocket(localSSLSocket);
    return localSSLSocket;
  }

  @Deprecated
  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return createLayeredSocket(paramSocket, paramString, paramInt, paramBoolean);
  }

  public Socket createSocket(HttpParams paramHttpParams)
    throws IOException
  {
    SSLSocket localSSLSocket = (SSLSocket)this.socketfactory.createSocket();
    prepareSocket(localSSLSocket);
    return localSSLSocket;
  }

  public X509HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    if (!(paramSocket instanceof SSLSocket))
      throw new IllegalArgumentException("Socket not created by this factory");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed");
    return true;
  }

  protected void prepareSocket(SSLSocket paramSSLSocket)
    throws IOException
  {
  }

  @Deprecated
  public void setHostnameVerifier(X509HostnameVerifier paramX509HostnameVerifier)
  {
    if (paramX509HostnameVerifier == null)
      throw new IllegalArgumentException("Hostname verifier may not be null");
    this.hostnameVerifier = paramX509HostnameVerifier;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ssl.SSLSocketFactory
 * JD-Core Version:    0.6.0
 */