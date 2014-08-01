package org.apache.commons.logging;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public abstract class LogFactory
{
  public static final String DIAGNOSTICS_DEST_PROPERTY = "org.apache.commons.logging.diagnostics.dest";
  public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
  public static final String FACTORY_PROPERTIES = "commons-logging.properties";
  public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
  public static final String HASHTABLE_IMPLEMENTATION_PROPERTY = "org.apache.commons.logging.LogFactory.HashtableImpl";
  public static final String PRIORITY_KEY = "priority";
  protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
  public static final String TCCL_KEY = "use_tccl";
  private static final String WEAK_HASHTABLE_CLASSNAME = "org.apache.commons.logging.impl.WeakHashtable";
  static Class class$java$lang$Thread;
  static Class class$org$apache$commons$logging$LogFactory;
  private static String diagnosticPrefix;
  private static PrintStream diagnosticsStream = null;
  protected static Hashtable factories = null;
  protected static LogFactory nullClassLoaderFactory = null;
  private static ClassLoader thisClassLoader;

  static
  {
    Class localClass1;
    Class localClass2;
    if (class$org$apache$commons$logging$LogFactory == null)
    {
      localClass1 = class$("org.apache.commons.logging.LogFactory");
      class$org$apache$commons$logging$LogFactory = localClass1;
      thisClassLoader = getClassLoader(localClass1);
      initDiagnostics();
      if (class$org$apache$commons$logging$LogFactory != null)
        break label83;
      localClass2 = class$("org.apache.commons.logging.LogFactory");
      class$org$apache$commons$logging$LogFactory = localClass2;
    }
    while (true)
    {
      logClassLoaderEnvironment(localClass2);
      factories = createFactoryStore();
      if (isDiagnosticsEnabled())
        logDiagnostic("BOOTSTRAP COMPLETED");
      return;
      localClass1 = class$org$apache$commons$logging$LogFactory;
      break;
      label83: localClass2 = class$org$apache$commons$logging$LogFactory;
    }
  }

  private static void cacheFactory(ClassLoader paramClassLoader, LogFactory paramLogFactory)
  {
    if (paramLogFactory != null)
    {
      if (paramClassLoader == null)
        nullClassLoaderFactory = paramLogFactory;
    }
    else
      return;
    factories.put(paramClassLoader, paramLogFactory);
  }

  static Class class$(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
  }

  protected static Object createFactory(String paramString, ClassLoader paramClassLoader)
  {
    Class localClass1 = null;
    if (paramClassLoader != null);
    while (true)
    {
      try
      {
        localClass1 = paramClassLoader.loadClass(paramString);
        if (class$org$apache$commons$logging$LogFactory != null)
          continue;
        Class localClass4 = class$("org.apache.commons.logging.LogFactory");
        class$org$apache$commons$logging$LogFactory = localClass4;
        if (!localClass4.isAssignableFrom(localClass1))
          continue;
        if (!isDiagnosticsEnabled())
          continue;
        logDiagnostic("Loaded class " + localClass1.getName() + " from classloader " + objectId(paramClassLoader));
        return (LogFactory)localClass1.newInstance();
        localClass4 = class$org$apache$commons$logging$LogFactory;
        continue;
        if (!isDiagnosticsEnabled())
          continue;
        StringBuffer localStringBuffer2 = new StringBuffer().append("Factory class ").append(localClass1.getName()).append(" loaded from classloader ").append(objectId(localClass1.getClassLoader())).append(" does not extend '");
        if (class$org$apache$commons$logging$LogFactory != null)
          continue;
        localClass5 = class$("org.apache.commons.logging.LogFactory");
        class$org$apache$commons$logging$LogFactory = localClass5;
        logDiagnostic(localClass5.getName() + "' as loaded by this classloader.");
        logHierarchy("[BAD CL TREE] ", paramClassLoader);
        continue;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        if (paramClassLoader != thisClassLoader)
          break label580;
        if (!isDiagnosticsEnabled())
          continue;
        logDiagnostic("Unable to locate any class called '" + paramString + "' via classloader " + objectId(paramClassLoader));
        throw localClassNotFoundException;
      }
      catch (Exception localException)
      {
        if (!isDiagnosticsEnabled())
          continue;
        logDiagnostic("Unable to create LogFactory instance.");
        if (localClass1 == null)
          break;
        if (class$org$apache$commons$logging$LogFactory != null)
          break label631;
        localClass2 = class$("org.apache.commons.logging.LogFactory");
        class$org$apache$commons$logging$LogFactory = localClass2;
        if (localClass2.isAssignableFrom(localClass1))
          break;
        return new LogConfigurationException("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", localException);
        Class localClass5 = class$org$apache$commons$logging$LogFactory;
        continue;
      }
      catch (NoClassDefFoundError localNoClassDefFoundError)
      {
        if (paramClassLoader != thisClassLoader)
          break label580;
        if (!isDiagnosticsEnabled())
          continue;
        logDiagnostic("Class '" + paramString + "' cannot be loaded" + " via classloader " + objectId(paramClassLoader) + " - it depends on some other class that cannot" + " be found.");
        throw localNoClassDefFoundError;
      }
      catch (ClassCastException localClassCastException)
      {
        if (paramClassLoader != thisClassLoader)
          break label580;
      }
      boolean bool = implementsLogFactory(localClass1);
      StringBuffer localStringBuffer1 = new StringBuffer().append("The application has specified that a custom LogFactory implementation should be used but Class '").append(paramString).append("' cannot be converted to '");
      Class localClass3;
      String str1;
      if (class$org$apache$commons$logging$LogFactory == null)
      {
        localClass3 = class$("org.apache.commons.logging.LogFactory");
        class$org$apache$commons$logging$LogFactory = localClass3;
        str1 = localClass3.getName() + "'. ";
        if (!bool)
          break label555;
      }
      label555: for (String str2 = str1 + "The conflict is caused by the presence of multiple LogFactory classes in incompatible classloaders. " + "Background can be found in http://commons.apache.org/logging/tech.html. " + "If you have not explicitly specified a custom LogFactory then it is likely that " + "the container has set one without your knowledge. " + "In this case, consider using the commons-logging-adapters.jar file or " + "specifying the standard LogFactory from the command line. "; ; str2 = str1 + "Please check the custom implementation. ")
      {
        String str3 = str2 + "Help can be found @http://commons.apache.org/logging/troubleshooting.html.";
        if (isDiagnosticsEnabled())
          logDiagnostic(str3);
        throw new ClassCastException(str3);
        localClass3 = class$org$apache$commons$logging$LogFactory;
        break;
      }
      label580: if (isDiagnosticsEnabled())
        logDiagnostic("Unable to load factory class via classloader " + objectId(paramClassLoader) + " - trying the classloader associated with this LogFactory.");
      localClass1 = Class.forName(paramString);
      LogFactory localLogFactory = (LogFactory)localClass1.newInstance();
      return localLogFactory;
      label631: Class localClass2 = class$org$apache$commons$logging$LogFactory;
    }
    return new LogConfigurationException(localException);
  }

  private static final Hashtable createFactoryStore()
  {
    try
    {
      String str2 = getSystemProperty("org.apache.commons.logging.LogFactory.HashtableImpl", null);
      str1 = str2;
      if (str1 == null)
        str1 = "org.apache.commons.logging.impl.WeakHashtable";
    }
    catch (SecurityException localSecurityException)
    {
      try
      {
        localHashtable = (Hashtable)Class.forName(str1).newInstance();
        if (localHashtable == null)
          localHashtable = new Hashtable();
        return localHashtable;
        localSecurityException = localSecurityException;
        str1 = null;
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          String str1;
          boolean bool = "org.apache.commons.logging.impl.WeakHashtable".equals(str1);
          Hashtable localHashtable = null;
          if (bool)
            continue;
          if (isDiagnosticsEnabled())
          {
            logDiagnostic("[ERROR] LogFactory: Load of custom hashtable failed");
            localHashtable = null;
            continue;
          }
          System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
          localHashtable = null;
        }
      }
    }
  }

  // ERROR //
  protected static ClassLoader directGetContextClassLoader()
    throws LogConfigurationException
  {
    // Byte code:
    //   0: getstatic 261	org/apache/commons/logging/LogFactory:class$java$lang$Thread	Ljava/lang/Class;
    //   3: ifnonnull +50 -> 53
    //   6: ldc_w 263
    //   9: invokestatic 58	org/apache/commons/logging/LogFactory:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   12: astore 8
    //   14: aload 8
    //   16: putstatic 261	org/apache/commons/logging/LogFactory:class$java$lang$Thread	Ljava/lang/Class;
    //   19: aload 8
    //   21: astore_2
    //   22: aload_2
    //   23: ldc_w 265
    //   26: aconst_null
    //   27: checkcast 267	[Ljava/lang/Class;
    //   30: invokevirtual 271	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   33: astore_3
    //   34: aload_3
    //   35: invokestatic 277	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   38: aconst_null
    //   39: checkcast 279	[Ljava/lang/Object;
    //   42: invokevirtual 285	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   45: checkcast 122	java/lang/ClassLoader
    //   48: astore 6
    //   50: aload 6
    //   52: areturn
    //   53: getstatic 261	org/apache/commons/logging/LogFactory:class$java$lang$Thread	Ljava/lang/Class;
    //   56: astore_2
    //   57: goto -35 -> 22
    //   60: astore 7
    //   62: new 179	org/apache/commons/logging/LogConfigurationException
    //   65: dup
    //   66: ldc_w 287
    //   69: aload 7
    //   71: invokespecial 184	org/apache/commons/logging/LogConfigurationException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   74: athrow
    //   75: astore_0
    //   76: getstatic 54	org/apache/commons/logging/LogFactory:class$org$apache$commons$logging$LogFactory	Ljava/lang/Class;
    //   79: ifnonnull +54 -> 133
    //   82: ldc 17
    //   84: invokestatic 58	org/apache/commons/logging/LogFactory:class$	(Ljava/lang/String;)Ljava/lang/Class;
    //   87: astore_1
    //   88: aload_1
    //   89: putstatic 54	org/apache/commons/logging/LogFactory:class$org$apache$commons$logging$LogFactory	Ljava/lang/Class;
    //   92: aload_1
    //   93: invokestatic 62	org/apache/commons/logging/LogFactory:getClassLoader	(Ljava/lang/Class;)Ljava/lang/ClassLoader;
    //   96: areturn
    //   97: astore 4
    //   99: aload 4
    //   101: invokevirtual 291	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   104: instanceof 229
    //   107: istore 5
    //   109: aconst_null
    //   110: astore 6
    //   112: iload 5
    //   114: ifne -64 -> 50
    //   117: new 179	org/apache/commons/logging/LogConfigurationException
    //   120: dup
    //   121: ldc_w 293
    //   124: aload 4
    //   126: invokevirtual 291	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   129: invokespecial 184	org/apache/commons/logging/LogConfigurationException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   132: athrow
    //   133: getstatic 54	org/apache/commons/logging/LogFactory:class$org$apache$commons$logging$LogFactory	Ljava/lang/Class;
    //   136: astore_1
    //   137: goto -45 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   34	50	60	java/lang/IllegalAccessException
    //   0	19	75	java/lang/NoSuchMethodException
    //   22	34	75	java/lang/NoSuchMethodException
    //   34	50	75	java/lang/NoSuchMethodException
    //   53	57	75	java/lang/NoSuchMethodException
    //   62	75	75	java/lang/NoSuchMethodException
    //   99	109	75	java/lang/NoSuchMethodException
    //   117	133	75	java/lang/NoSuchMethodException
    //   34	50	97	java/lang/reflect/InvocationTargetException
  }

  private static LogFactory getCachedFactory(ClassLoader paramClassLoader)
  {
    if (paramClassLoader == null)
      return nullClassLoaderFactory;
    return (LogFactory)factories.get(paramClassLoader);
  }

  protected static ClassLoader getClassLoader(Class paramClass)
  {
    try
    {
      ClassLoader localClassLoader = paramClass.getClassLoader();
      return localClassLoader;
    }
    catch (SecurityException localSecurityException)
    {
      if (isDiagnosticsEnabled())
        logDiagnostic("Unable to get classloader for class '" + paramClass + "' due to security restrictions - " + localSecurityException.getMessage());
    }
    throw localSecurityException;
  }

  private static final Properties getConfigurationFile(ClassLoader paramClassLoader, String paramString)
  {
    Object localObject1 = null;
    double d1 = 0.0D;
    Object localObject2 = null;
    while (true)
    {
      URL localURL;
      Properties localProperties;
      try
      {
        Enumeration localEnumeration = getResources(paramClassLoader, paramString);
        localObject1 = null;
        localObject2 = null;
        if (localEnumeration == null)
          return null;
        if (!localEnumeration.hasMoreElements())
          continue;
        localURL = (URL)localEnumeration.nextElement();
        localProperties = getProperties(localURL);
        if (localProperties == null)
          continue;
        if (localObject1 == null)
        {
          localObject2 = localURL;
          localObject1 = localProperties;
          String str2 = localObject1.getProperty("priority");
          d1 = 0.0D;
          if (str2 == null)
            continue;
          d1 = Double.parseDouble(str2);
          if (!isDiagnosticsEnabled())
            continue;
          logDiagnostic("[LOOKUP] Properties file found at '" + localURL + "'" + " with priority " + d1);
          continue;
        }
      }
      catch (SecurityException localSecurityException)
      {
        if (!isDiagnosticsEnabled())
          continue;
        logDiagnostic("SecurityException thrown while trying to find/read config files.");
        if (!isDiagnosticsEnabled())
          continue;
        if (localObject1 != null)
          break label381;
        logDiagnostic("[LOOKUP] No properties file of name '" + paramString + "' found.");
        return localObject1;
      }
      String str1 = localProperties.getProperty("priority");
      double d2 = 0.0D;
      if (str1 != null)
        d2 = Double.parseDouble(str1);
      if (d2 > d1)
      {
        if (isDiagnosticsEnabled())
          logDiagnostic("[LOOKUP] Properties file at '" + localURL + "'" + " with priority " + d2 + " overrides file at '" + localObject2 + "'" + " with priority " + d1);
      }
      else
      {
        if (!isDiagnosticsEnabled())
          continue;
        logDiagnostic("[LOOKUP] Properties file at '" + localURL + "'" + " with priority " + d2 + " does not override file at '" + localObject2 + "'" + " with priority " + d1);
        continue;
        label381: logDiagnostic("[LOOKUP] Properties file of name '" + paramString + "' found at '" + localObject2 + '"');
        continue;
      }
      localObject2 = localURL;
      localObject1 = localProperties;
      d1 = d2;
    }
  }

  protected static ClassLoader getContextClassLoader()
    throws LogConfigurationException
  {
    return directGetContextClassLoader();
  }

  private static ClassLoader getContextClassLoaderInternal()
    throws LogConfigurationException
  {
    return (ClassLoader)AccessController.doPrivileged(new LogFactory.1());
  }

  // ERROR //
  public static LogFactory getFactory()
    throws LogConfigurationException
  {
    // Byte code:
    //   0: invokestatic 386	org/apache/commons/logging/LogFactory:getContextClassLoaderInternal	()Ljava/lang/ClassLoader;
    //   3: astore_0
    //   4: aload_0
    //   5: ifnonnull +15 -> 20
    //   8: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   11: ifeq +9 -> 20
    //   14: ldc_w 388
    //   17: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   20: aload_0
    //   21: invokestatic 390	org/apache/commons/logging/LogFactory:getCachedFactory	(Ljava/lang/ClassLoader;)Lorg/apache/commons/logging/LogFactory;
    //   24: astore_1
    //   25: aload_1
    //   26: ifnull +5 -> 31
    //   29: aload_1
    //   30: areturn
    //   31: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   34: ifeq +36 -> 70
    //   37: new 131	java/lang/StringBuffer
    //   40: dup
    //   41: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   44: ldc_w 392
    //   47: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   50: aload_0
    //   51: invokestatic 147	org/apache/commons/logging/LogFactory:objectId	(Ljava/lang/Object;)Ljava/lang/String;
    //   54: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   57: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   60: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   63: ldc_w 394
    //   66: aload_0
    //   67: invokestatic 171	org/apache/commons/logging/LogFactory:logHierarchy	(Ljava/lang/String;Ljava/lang/ClassLoader;)V
    //   70: aload_0
    //   71: ldc 14
    //   73: invokestatic 396	org/apache/commons/logging/LogFactory:getConfigurationFile	(Ljava/lang/ClassLoader;Ljava/lang/String;)Ljava/util/Properties;
    //   76: astore_2
    //   77: aload_0
    //   78: astore_3
    //   79: aload_2
    //   80: ifnull +31 -> 111
    //   83: aload_2
    //   84: ldc 29
    //   86: invokevirtual 332	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   89: astore 19
    //   91: aload 19
    //   93: ifnull +18 -> 111
    //   96: aload 19
    //   98: invokestatic 402	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   101: invokevirtual 405	java/lang/Boolean:booleanValue	()Z
    //   104: ifne +7 -> 111
    //   107: getstatic 64	org/apache/commons/logging/LogFactory:thisClassLoader	Ljava/lang/ClassLoader;
    //   110: astore_3
    //   111: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   114: ifeq +9 -> 123
    //   117: ldc_w 407
    //   120: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   123: ldc 17
    //   125: aconst_null
    //   126: invokestatic 233	org/apache/commons/logging/LogFactory:getSystemProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   129: astore 17
    //   131: aload 17
    //   133: ifnull +362 -> 495
    //   136: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   139: ifeq +38 -> 177
    //   142: new 131	java/lang/StringBuffer
    //   145: dup
    //   146: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   149: ldc_w 409
    //   152: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   155: aload 17
    //   157: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   160: ldc_w 411
    //   163: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   166: ldc 17
    //   168: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   171: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   174: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   177: aload 17
    //   179: aload_3
    //   180: aload_0
    //   181: invokestatic 415	org/apache/commons/logging/LogFactory:newFactory	(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/ClassLoader;)Lorg/apache/commons/logging/LogFactory;
    //   184: astore 18
    //   186: aload 18
    //   188: astore_1
    //   189: aload_1
    //   190: ifnonnull +146 -> 336
    //   193: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   196: ifeq +9 -> 205
    //   199: ldc_w 417
    //   202: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   205: aload_0
    //   206: ldc 26
    //   208: invokestatic 421	org/apache/commons/logging/LogFactory:getResourceAsStream	(Ljava/lang/ClassLoader;Ljava/lang/String;)Ljava/io/InputStream;
    //   211: astore 10
    //   213: aload 10
    //   215: ifnull +416 -> 631
    //   218: new 423	java/io/InputStreamReader
    //   221: dup
    //   222: aload 10
    //   224: ldc_w 425
    //   227: invokespecial 428	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   230: astore 11
    //   232: new 430	java/io/BufferedReader
    //   235: dup
    //   236: aload 11
    //   238: invokespecial 433	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   241: astore 12
    //   243: aload 12
    //   245: invokevirtual 436	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   248: astore 13
    //   250: aload 12
    //   252: invokevirtual 439	java/io/BufferedReader:close	()V
    //   255: aload 13
    //   257: ifnull +79 -> 336
    //   260: ldc_w 441
    //   263: aload 13
    //   265: invokevirtual 240	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   268: ifne +68 -> 336
    //   271: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   274: ifeq +50 -> 324
    //   277: new 131	java/lang/StringBuffer
    //   280: dup
    //   281: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   284: ldc_w 443
    //   287: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   290: aload 13
    //   292: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   295: ldc_w 445
    //   298: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   301: ldc 26
    //   303: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   306: ldc_w 447
    //   309: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   312: ldc_w 449
    //   315: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   318: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   321: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   324: aload 13
    //   326: aload_3
    //   327: aload_0
    //   328: invokestatic 415	org/apache/commons/logging/LogFactory:newFactory	(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/ClassLoader;)Lorg/apache/commons/logging/LogFactory;
    //   331: astore 14
    //   333: aload 14
    //   335: astore_1
    //   336: aload_1
    //   337: ifnonnull +76 -> 413
    //   340: aload_2
    //   341: ifnull +367 -> 708
    //   344: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   347: ifeq +9 -> 356
    //   350: ldc_w 451
    //   353: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   356: aload_2
    //   357: ldc 17
    //   359: invokevirtual 332	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   362: astore 8
    //   364: aload 8
    //   366: ifnull +327 -> 693
    //   369: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   372: ifeq +33 -> 405
    //   375: new 131	java/lang/StringBuffer
    //   378: dup
    //   379: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   382: ldc_w 453
    //   385: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   388: aload 8
    //   390: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   393: ldc_w 342
    //   396: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   399: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   402: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   405: aload 8
    //   407: aload_3
    //   408: aload_0
    //   409: invokestatic 415	org/apache/commons/logging/LogFactory:newFactory	(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/ClassLoader;)Lorg/apache/commons/logging/LogFactory;
    //   412: astore_1
    //   413: aload_1
    //   414: ifnonnull +25 -> 439
    //   417: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   420: ifeq +9 -> 429
    //   423: ldc_w 455
    //   426: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   429: ldc 11
    //   431: getstatic 64	org/apache/commons/logging/LogFactory:thisClassLoader	Ljava/lang/ClassLoader;
    //   434: aload_0
    //   435: invokestatic 415	org/apache/commons/logging/LogFactory:newFactory	(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/ClassLoader;)Lorg/apache/commons/logging/LogFactory;
    //   438: astore_1
    //   439: aload_1
    //   440: ifnull +283 -> 723
    //   443: aload_0
    //   444: aload_1
    //   445: invokestatic 457	org/apache/commons/logging/LogFactory:cacheFactory	(Ljava/lang/ClassLoader;Lorg/apache/commons/logging/LogFactory;)V
    //   448: aload_2
    //   449: ifnull +274 -> 723
    //   452: aload_2
    //   453: invokevirtual 461	java/util/Properties:propertyNames	()Ljava/util/Enumeration;
    //   456: astore 6
    //   458: aload 6
    //   460: invokeinterface 317 1 0
    //   465: ifeq +258 -> 723
    //   468: aload 6
    //   470: invokeinterface 320 1 0
    //   475: checkcast 236	java/lang/String
    //   478: astore 7
    //   480: aload_1
    //   481: aload 7
    //   483: aload_2
    //   484: aload 7
    //   486: invokevirtual 332	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   489: invokevirtual 465	org/apache/commons/logging/LogFactory:setAttribute	(Ljava/lang/String;Ljava/lang/Object;)V
    //   492: goto -34 -> 458
    //   495: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   498: ifeq -309 -> 189
    //   501: ldc_w 467
    //   504: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   507: goto -318 -> 189
    //   510: astore 5
    //   512: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   515: ifeq -326 -> 189
    //   518: new 131	java/lang/StringBuffer
    //   521: dup
    //   522: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   525: ldc_w 469
    //   528: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   531: aload 5
    //   533: invokevirtual 112	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   536: invokestatic 472	org/apache/commons/logging/LogFactory:trim	(Ljava/lang/String;)Ljava/lang/String;
    //   539: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   542: ldc_w 474
    //   545: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   548: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   551: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   554: goto -365 -> 189
    //   557: astore 4
    //   559: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   562: ifeq +39 -> 601
    //   565: new 131	java/lang/StringBuffer
    //   568: dup
    //   569: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   572: ldc_w 476
    //   575: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   578: aload 4
    //   580: invokevirtual 112	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   583: invokestatic 472	org/apache/commons/logging/LogFactory:trim	(Ljava/lang/String;)Ljava/lang/String;
    //   586: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   589: ldc_w 478
    //   592: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   595: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   598: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   601: aload 4
    //   603: athrow
    //   604: astore 15
    //   606: new 423	java/io/InputStreamReader
    //   609: dup
    //   610: aload 10
    //   612: invokespecial 481	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   615: astore 16
    //   617: new 430	java/io/BufferedReader
    //   620: dup
    //   621: aload 16
    //   623: invokespecial 433	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   626: astore 12
    //   628: goto -385 -> 243
    //   631: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   634: ifeq -298 -> 336
    //   637: ldc_w 483
    //   640: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   643: goto -307 -> 336
    //   646: astore 9
    //   648: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   651: ifeq -315 -> 336
    //   654: new 131	java/lang/StringBuffer
    //   657: dup
    //   658: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   661: ldc_w 469
    //   664: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   667: aload 9
    //   669: invokevirtual 112	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   672: invokestatic 472	org/apache/commons/logging/LogFactory:trim	(Ljava/lang/String;)Ljava/lang/String;
    //   675: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   678: ldc_w 474
    //   681: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   684: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   687: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   690: goto -354 -> 336
    //   693: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   696: ifeq -283 -> 413
    //   699: ldc_w 485
    //   702: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   705: goto -292 -> 413
    //   708: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   711: ifeq -298 -> 413
    //   714: ldc_w 487
    //   717: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   720: goto -307 -> 413
    //   723: aload_1
    //   724: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   123	131	510	java/lang/SecurityException
    //   136	177	510	java/lang/SecurityException
    //   177	186	510	java/lang/SecurityException
    //   495	507	510	java/lang/SecurityException
    //   123	131	557	java/lang/RuntimeException
    //   136	177	557	java/lang/RuntimeException
    //   177	186	557	java/lang/RuntimeException
    //   495	507	557	java/lang/RuntimeException
    //   218	243	604	java/io/UnsupportedEncodingException
    //   205	213	646	java/lang/Exception
    //   218	243	646	java/lang/Exception
    //   243	255	646	java/lang/Exception
    //   260	324	646	java/lang/Exception
    //   324	333	646	java/lang/Exception
    //   606	628	646	java/lang/Exception
    //   631	643	646	java/lang/Exception
  }

  public static Log getLog(Class paramClass)
    throws LogConfigurationException
  {
    return getFactory().getInstance(paramClass);
  }

  public static Log getLog(String paramString)
    throws LogConfigurationException
  {
    return getFactory().getInstance(paramString);
  }

  private static Properties getProperties(URL paramURL)
  {
    return (Properties)AccessController.doPrivileged(new LogFactory.5(paramURL));
  }

  private static InputStream getResourceAsStream(ClassLoader paramClassLoader, String paramString)
  {
    return (InputStream)AccessController.doPrivileged(new LogFactory.3(paramClassLoader, paramString));
  }

  private static Enumeration getResources(ClassLoader paramClassLoader, String paramString)
  {
    return (Enumeration)AccessController.doPrivileged(new LogFactory.4(paramClassLoader, paramString));
  }

  private static String getSystemProperty(String paramString1, String paramString2)
    throws SecurityException
  {
    return (String)AccessController.doPrivileged(new LogFactory.6(paramString1, paramString2));
  }

  private static boolean implementsLogFactory(Class paramClass)
  {
    boolean bool = false;
    if (paramClass != null)
      try
      {
        ClassLoader localClassLoader = paramClass.getClassLoader();
        if (localClassLoader == null)
        {
          logDiagnostic("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
          return false;
        }
        logHierarchy("[CUSTOM LOG FACTORY] ", localClassLoader);
        bool = Class.forName("org.apache.commons.logging.LogFactory", false, localClassLoader).isAssignableFrom(paramClass);
        if (bool)
        {
          logDiagnostic("[CUSTOM LOG FACTORY] " + paramClass.getName() + " implements LogFactory but was loaded by an incompatible classloader.");
          return bool;
        }
      }
      catch (SecurityException localSecurityException)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + localSecurityException.getMessage());
        return bool;
        logDiagnostic("[CUSTOM LOG FACTORY] " + paramClass.getName() + " does not implement LogFactory.");
        return bool;
      }
      catch (LinkageError localLinkageError)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: " + localLinkageError.getMessage());
        return bool;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        logDiagnostic("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
      }
    return bool;
  }

  private static void initDiagnostics()
  {
    String str1;
    try
    {
      str1 = getSystemProperty("org.apache.commons.logging.diagnostics.dest", null);
      if (str1 == null)
        return;
    }
    catch (SecurityException localSecurityException1)
    {
      return;
    }
    if (str1.equals("STDOUT"))
      diagnosticsStream = System.out;
    try
    {
      ClassLoader localClassLoader = thisClassLoader;
      if (thisClassLoader == null);
      String str2;
      for (localObject = "BOOTLOADER"; ; localObject = str2)
      {
        while (true)
        {
          diagnosticPrefix = "[LogFactory from " + (String)localObject + "] ";
          return;
          if (str1.equals("STDERR"))
          {
            diagnosticsStream = System.err;
            break;
          }
          try
          {
            diagnosticsStream = new PrintStream(new FileOutputStream(str1, true));
          }
          catch (IOException localIOException)
          {
            return;
          }
        }
        str2 = objectId(localClassLoader);
      }
    }
    catch (SecurityException localSecurityException2)
    {
      while (true)
        Object localObject = "UNKNOWN";
    }
  }

  protected static boolean isDiagnosticsEnabled()
  {
    return diagnosticsStream != null;
  }

  private static void logClassLoaderEnvironment(Class paramClass)
  {
    if (!isDiagnosticsEnabled())
      return;
    try
    {
      logDiagnostic("[ENV] Extension directories (java.ext.dir): " + System.getProperty("java.ext.dir"));
      logDiagnostic("[ENV] Application classpath (java.class.path): " + System.getProperty("java.class.path"));
      str = paramClass.getName();
    }
    catch (SecurityException localSecurityException1)
    {
      try
      {
        ClassLoader localClassLoader = getClassLoader(paramClass);
        logDiagnostic("[ENV] Class " + str + " was loaded via classloader " + objectId(localClassLoader));
        logHierarchy("[ENV] Ancestry of classloader which loaded " + str + " is ", localClassLoader);
        return;
        localSecurityException1 = localSecurityException1;
        logDiagnostic("[ENV] Security setting prevent interrogation of system classpaths.");
      }
      catch (SecurityException localSecurityException2)
      {
        String str;
        logDiagnostic("[ENV] Security forbids determining the classloader for " + str);
      }
    }
  }

  private static final void logDiagnostic(String paramString)
  {
    if (diagnosticsStream != null)
    {
      diagnosticsStream.print(diagnosticPrefix);
      diagnosticsStream.println(paramString);
      diagnosticsStream.flush();
    }
  }

  // ERROR //
  private static void logHierarchy(String paramString, ClassLoader paramClassLoader)
  {
    // Byte code:
    //   0: invokestatic 79	org/apache/commons/logging/LogFactory:isDiagnosticsEnabled	()Z
    //   3: ifne +4 -> 7
    //   6: return
    //   7: aload_1
    //   8: ifnull +50 -> 58
    //   11: aload_1
    //   12: invokevirtual 591	java/lang/Object:toString	()Ljava/lang/String;
    //   15: astore 12
    //   17: new 131	java/lang/StringBuffer
    //   20: dup
    //   21: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   24: aload_0
    //   25: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   28: aload_1
    //   29: invokestatic 147	org/apache/commons/logging/LogFactory:objectId	(Ljava/lang/Object;)Ljava/lang/String;
    //   32: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   35: ldc_w 593
    //   38: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   41: aload 12
    //   43: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   46: ldc_w 342
    //   49: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   52: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   55: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   58: invokestatic 596	java/lang/ClassLoader:getSystemClassLoader	()Ljava/lang/ClassLoader;
    //   61: astore_3
    //   62: aload_1
    //   63: ifnull -57 -> 6
    //   66: new 131	java/lang/StringBuffer
    //   69: dup
    //   70: new 131	java/lang/StringBuffer
    //   73: dup
    //   74: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   77: aload_0
    //   78: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   81: ldc_w 598
    //   84: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   87: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   90: invokespecial 599	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   93: astore 4
    //   95: aload 4
    //   97: aload_1
    //   98: invokestatic 147	org/apache/commons/logging/LogFactory:objectId	(Ljava/lang/Object;)Ljava/lang/String;
    //   101: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   104: pop
    //   105: aload_1
    //   106: aload_3
    //   107: if_acmpne +12 -> 119
    //   110: aload 4
    //   112: ldc_w 601
    //   115: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   118: pop
    //   119: aload_1
    //   120: invokevirtual 604	java/lang/ClassLoader:getParent	()Ljava/lang/ClassLoader;
    //   123: astore 8
    //   125: aload 8
    //   127: astore_1
    //   128: aload 4
    //   130: ldc_w 606
    //   133: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   136: pop
    //   137: aload_1
    //   138: ifnonnull -43 -> 95
    //   141: aload 4
    //   143: ldc_w 608
    //   146: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   149: pop
    //   150: aload 4
    //   152: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   155: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   158: return
    //   159: astore_2
    //   160: new 131	java/lang/StringBuffer
    //   163: dup
    //   164: invokespecial 132	java/lang/StringBuffer:<init>	()V
    //   167: aload_0
    //   168: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   171: ldc_w 610
    //   174: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   177: invokevirtual 150	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   180: invokestatic 85	org/apache/commons/logging/LogFactory:logDiagnostic	(Ljava/lang/String;)V
    //   183: return
    //   184: astore 6
    //   186: aload 4
    //   188: ldc_w 612
    //   191: invokevirtual 138	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   194: pop
    //   195: goto -45 -> 150
    //
    // Exception table:
    //   from	to	target	type
    //   58	62	159	java/lang/SecurityException
    //   119	125	184	java/lang/SecurityException
  }

  protected static final void logRawDiagnostic(String paramString)
  {
    if (diagnosticsStream != null)
    {
      diagnosticsStream.println(paramString);
      diagnosticsStream.flush();
    }
  }

  protected static LogFactory newFactory(String paramString, ClassLoader paramClassLoader)
  {
    return newFactory(paramString, paramClassLoader, null);
  }

  protected static LogFactory newFactory(String paramString, ClassLoader paramClassLoader1, ClassLoader paramClassLoader2)
    throws LogConfigurationException
  {
    Object localObject = AccessController.doPrivileged(new LogFactory.2(paramString, paramClassLoader1));
    if ((localObject instanceof LogConfigurationException))
    {
      LogConfigurationException localLogConfigurationException = (LogConfigurationException)localObject;
      if (isDiagnosticsEnabled())
        logDiagnostic("An error occurred while loading the factory class:" + localLogConfigurationException.getMessage());
      throw localLogConfigurationException;
    }
    if (isDiagnosticsEnabled())
      logDiagnostic("Created object " + objectId(localObject) + " to manage classloader " + objectId(paramClassLoader2));
    return (LogFactory)localObject;
  }

  public static String objectId(Object paramObject)
  {
    if (paramObject == null)
      return "null";
    return paramObject.getClass().getName() + "@" + System.identityHashCode(paramObject);
  }

  public static void release(ClassLoader paramClassLoader)
  {
    if (isDiagnosticsEnabled())
      logDiagnostic("Releasing factory for classloader " + objectId(paramClassLoader));
    Hashtable localHashtable = factories;
    monitorenter;
    if (paramClassLoader == null);
    try
    {
      if (nullClassLoaderFactory != null)
      {
        nullClassLoaderFactory.release();
        nullClassLoaderFactory = null;
      }
      while (true)
      {
        return;
        LogFactory localLogFactory = (LogFactory)factories.get(paramClassLoader);
        if (localLogFactory == null)
          continue;
        localLogFactory.release();
        factories.remove(paramClassLoader);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void releaseAll()
  {
    if (isDiagnosticsEnabled())
      logDiagnostic("Releasing factory for all classloaders.");
    synchronized (factories)
    {
      Enumeration localEnumeration = factories.elements();
      if (localEnumeration.hasMoreElements())
        ((LogFactory)localEnumeration.nextElement()).release();
    }
    factories.clear();
    if (nullClassLoaderFactory != null)
    {
      nullClassLoaderFactory.release();
      nullClassLoaderFactory = null;
    }
    monitorexit;
  }

  private static String trim(String paramString)
  {
    if (paramString == null)
      return null;
    return paramString.trim();
  }

  public abstract Object getAttribute(String paramString);

  public abstract String[] getAttributeNames();

  public abstract Log getInstance(Class paramClass)
    throws LogConfigurationException;

  public abstract Log getInstance(String paramString)
    throws LogConfigurationException;

  public abstract void release();

  public abstract void removeAttribute(String paramString);

  public abstract void setAttribute(String paramString, Object paramObject);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogFactory
 * JD-Core Version:    0.6.0
 */