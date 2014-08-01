package org.apache.http.conn.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.auth.x500.X500Principal;
import org.apache.http.annotation.Immutable;
import org.apache.http.conn.util.InetAddressUtils;

@Immutable
public abstract class AbstractVerifier
  implements X509HostnameVerifier
{
  private static final String[] BAD_COUNTRY_2LDS = { "ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org" };

  static
  {
    Arrays.sort(BAD_COUNTRY_2LDS);
  }

  public static boolean acceptableCountryWildcard(String paramString)
  {
    String[] arrayOfString = paramString.split("\\.");
    if ((arrayOfString.length != 3) || (arrayOfString[2].length() != 2));
    do
      return true;
    while (Arrays.binarySearch(BAD_COUNTRY_2LDS, arrayOfString[1]) < 0);
    return false;
  }

  public static int countDots(String paramString)
  {
    int i = 0;
    for (int j = 0; j < paramString.length(); j++)
    {
      if (paramString.charAt(j) != '.')
        continue;
      i++;
    }
    return i;
  }

  public static String[] getCNs(X509Certificate paramX509Certificate)
  {
    LinkedList localLinkedList = new LinkedList();
    StringTokenizer localStringTokenizer = new StringTokenizer(paramX509Certificate.getSubjectX500Principal().toString(), ",");
    while (localStringTokenizer.hasMoreTokens())
    {
      String str = localStringTokenizer.nextToken();
      int i = str.indexOf("CN=");
      if (i < 0)
        continue;
      localLinkedList.add(str.substring(i + 3));
    }
    if (!localLinkedList.isEmpty())
    {
      String[] arrayOfString = new String[localLinkedList.size()];
      localLinkedList.toArray(arrayOfString);
      return arrayOfString;
    }
    return null;
  }

  public static String[] getDNSSubjectAlts(X509Certificate paramX509Certificate)
  {
    return getSubjectAlts(paramX509Certificate, null);
  }

  private static String[] getSubjectAlts(X509Certificate paramX509Certificate, String paramString)
  {
    int i;
    if (isIPAddress(paramString))
      i = 7;
    while (true)
    {
      LinkedList localLinkedList = new LinkedList();
      try
      {
        Collection localCollection2 = paramX509Certificate.getSubjectAlternativeNames();
        localCollection1 = localCollection2;
        if (localCollection1 != null)
        {
          Iterator localIterator = localCollection1.iterator();
          while (localIterator.hasNext())
          {
            List localList = (List)localIterator.next();
            if (((Integer)localList.get(0)).intValue() != i)
              continue;
            localLinkedList.add((String)localList.get(1));
            continue;
            i = 2;
          }
        }
      }
      catch (CertificateParsingException localCertificateParsingException)
      {
        while (true)
        {
          Logger.getLogger(AbstractVerifier.class.getName()).log(Level.FINE, "Error parsing certificate.", localCertificateParsingException);
          Collection localCollection1 = null;
        }
        if (localLinkedList.isEmpty())
          break;
        String[] arrayOfString = new String[localLinkedList.size()];
        localLinkedList.toArray(arrayOfString);
        return arrayOfString;
      }
    }
    return null;
  }

  private static boolean isIPAddress(String paramString)
  {
    return (paramString != null) && ((InetAddressUtils.isIPv4Address(paramString)) || (InetAddressUtils.isIPv6Address(paramString)));
  }

  public final void verify(String paramString, X509Certificate paramX509Certificate)
    throws SSLException
  {
    verify(paramString, getCNs(paramX509Certificate), getSubjectAlts(paramX509Certificate, paramString));
  }

  public final void verify(String paramString, SSLSocket paramSSLSocket)
    throws IOException
  {
    if (paramString == null)
      throw new NullPointerException("host to verify is null");
    SSLSession localSSLSession = paramSSLSocket.getSession();
    if (localSSLSession == null)
    {
      paramSSLSocket.getInputStream().available();
      localSSLSession = paramSSLSocket.getSession();
      if (localSSLSession == null)
      {
        paramSSLSocket.startHandshake();
        localSSLSession = paramSSLSocket.getSession();
      }
    }
    verify(paramString, (X509Certificate)localSSLSession.getPeerCertificates()[0]);
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
    throws SSLException
  {
    LinkedList localLinkedList = new LinkedList();
    if ((paramArrayOfString1 != null) && (paramArrayOfString1.length > 0) && (paramArrayOfString1[0] != null))
      localLinkedList.add(paramArrayOfString1[0]);
    if (paramArrayOfString2 != null)
    {
      int j = paramArrayOfString2.length;
      for (int k = 0; k < j; k++)
      {
        String str7 = paramArrayOfString2[k];
        if (str7 == null)
          continue;
        localLinkedList.add(str7);
      }
    }
    if (localLinkedList.isEmpty())
    {
      String str6 = "Certificate for <" + paramString + "> doesn't contain CN or DNS subjectAlt";
      SSLException localSSLException = new SSLException(str6);
      throw localSSLException;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = paramString.trim().toLowerCase(Locale.ENGLISH);
    boolean bool = false;
    Iterator localIterator = localLinkedList.iterator();
    label431: label437: label443: label459: label465: label475: 
    while (true)
    {
      String str2;
      int i;
      if (localIterator.hasNext())
      {
        str2 = ((String)localIterator.next()).toLowerCase(Locale.ENGLISH);
        localStringBuilder.append(" <");
        localStringBuilder.append(str2);
        localStringBuilder.append('>');
        if (localIterator.hasNext())
          localStringBuilder.append(" OR");
        String[] arrayOfString = str2.split("\\.");
        if ((arrayOfString.length < 3) || (!arrayOfString[0].endsWith("*")) || (!acceptableCountryWildcard(str2)) || (isIPAddress(paramString)))
          break label431;
        i = 1;
        if (i == 0)
          break label465;
        if (arrayOfString[0].length() <= 1)
          break label443;
        String str3 = arrayOfString[0].substring(0, -2 + arrayOfString.length);
        String str4 = str2.substring(arrayOfString[0].length());
        String str5 = str1.substring(str3.length());
        if ((!str1.startsWith(str3)) || (!str5.endsWith(str4)))
          break label437;
        bool = true;
        label356: if ((bool) && (paramBoolean))
        {
          if (countDots(str1) != countDots(str2))
            break label459;
          bool = true;
        }
      }
      while (true)
      {
        if (!bool)
          break label475;
        if (bool)
          return;
        throw new SSLException("hostname in certificate didn't match: <" + paramString + "> !=" + localStringBuilder);
        i = 0;
        break;
        bool = false;
        break label356;
        bool = str1.endsWith(str2.substring(1));
        break label356;
        bool = false;
        continue;
        bool = str1.equals(str2);
      }
    }
  }

  public final boolean verify(String paramString, SSLSession paramSSLSession)
  {
    try
    {
      verify(paramString, (X509Certificate)paramSSLSession.getPeerCertificates()[0]);
      return true;
    }
    catch (SSLException localSSLException)
    {
    }
    return false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.conn.ssl.AbstractVerifier
 * JD-Core Version:    0.6.0
 */