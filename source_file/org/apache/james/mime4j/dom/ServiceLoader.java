package org.apache.james.mime4j.dom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;

class ServiceLoader
{
  static <T> T load(Class<T> paramClass)
  {
    String str1 = "META-INF/services/" + paramClass.getName();
    ClassLoader localClassLoader = paramClass.getClassLoader();
    try
    {
      Enumeration localEnumeration = localClassLoader.getResources(str1);
      if (localEnumeration.hasMoreElements())
        localInputStream = ((URL)localEnumeration.nextElement()).openStream();
    }
    catch (IOException localIOException)
    {
      try
      {
        while (true)
        {
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream));
          while (true)
          {
            String str2 = localBufferedReader.readLine();
            if (str2 == null)
              break;
            String str3 = str2.trim();
            int i = str3.indexOf('#');
            if (i != -1)
              str3 = str3.substring(0, i).trim();
            if (str3.length() == 0)
              continue;
            Class localClass = localClassLoader.loadClass(str3);
            if (!paramClass.isAssignableFrom(localClass))
              continue;
            Object localObject2 = paramClass.cast(localClass.newInstance());
            return localObject2;
          }
          localBufferedReader.close();
          localInputStream.close();
        }
        localIOException = localIOException;
        throw new ServiceLoaderException(localIOException);
      }
      finally
      {
        InputStream localInputStream;
        localInputStream.close();
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new ServiceLoaderException("Unknown SPI class '" + paramClass.getName() + "'", localClassNotFoundException);
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return null;
    }
    catch (InstantiationException localInstantiationException)
    {
    }
    throw new ServiceLoaderException("SPI class '" + paramClass.getName() + "' cannot be instantiated", localInstantiationException);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.ServiceLoader
 * JD-Core Version:    0.6.0
 */