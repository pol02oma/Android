package org.apache.http.client.utils;

import org.apache.http.annotation.Immutable;

@Immutable
public class CloneUtils
{
  // ERROR //
  public static Object clone(Object paramObject)
    throws java.lang.CloneNotSupportedException
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: instanceof 21
    //   10: ifeq +96 -> 106
    //   13: aload_0
    //   14: invokevirtual 25	java/lang/Object:getClass	()Ljava/lang/Class;
    //   17: astore_1
    //   18: aload_1
    //   19: ldc 26
    //   21: aconst_null
    //   22: checkcast 28	[Ljava/lang/Class;
    //   25: invokevirtual 34	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   28: astore_3
    //   29: aload_3
    //   30: aload_0
    //   31: aconst_null
    //   32: checkcast 36	[Ljava/lang/Object;
    //   35: invokevirtual 42	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   38: astore 7
    //   40: aload 7
    //   42: areturn
    //   43: astore_2
    //   44: new 44	java/lang/NoSuchMethodError
    //   47: dup
    //   48: aload_2
    //   49: invokevirtual 48	java/lang/NoSuchMethodException:getMessage	()Ljava/lang/String;
    //   52: invokespecial 51	java/lang/NoSuchMethodError:<init>	(Ljava/lang/String;)V
    //   55: athrow
    //   56: astore 5
    //   58: aload 5
    //   60: invokevirtual 55	java/lang/reflect/InvocationTargetException:getCause	()Ljava/lang/Throwable;
    //   63: astore 6
    //   65: aload 6
    //   67: instanceof 13
    //   70: ifeq +9 -> 79
    //   73: aload 6
    //   75: checkcast 13	java/lang/CloneNotSupportedException
    //   78: athrow
    //   79: new 57	java/lang/Error
    //   82: dup
    //   83: ldc 59
    //   85: aload 6
    //   87: invokespecial 62	java/lang/Error:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   90: athrow
    //   91: astore 4
    //   93: new 64	java/lang/IllegalAccessError
    //   96: dup
    //   97: aload 4
    //   99: invokevirtual 65	java/lang/IllegalAccessException:getMessage	()Ljava/lang/String;
    //   102: invokespecial 66	java/lang/IllegalAccessError:<init>	(Ljava/lang/String;)V
    //   105: athrow
    //   106: new 13	java/lang/CloneNotSupportedException
    //   109: dup
    //   110: invokespecial 67	java/lang/CloneNotSupportedException:<init>	()V
    //   113: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   18	29	43	java/lang/NoSuchMethodException
    //   29	40	56	java/lang/reflect/InvocationTargetException
    //   29	40	91	java/lang/IllegalAccessException
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.http.client.utils.CloneUtils
 * JD-Core Version:    0.6.0
 */