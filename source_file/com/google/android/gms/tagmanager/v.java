package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fn;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class v
  implements DataLayer.c
{
  private static final String UD = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "datalayer", "ID", "key", "value", "expires" });
  private fl Ty;
  private final Executor UE;
  private a UF;
  private int UG;
  private final Context mContext;

  public v(Context paramContext)
  {
    this(paramContext, fn.eI(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
  }

  v(Context paramContext, fl paramfl, String paramString, int paramInt, Executor paramExecutor)
  {
    this.mContext = paramContext;
    this.Ty = paramfl;
    this.UG = paramInt;
    this.UE = paramExecutor;
    this.UF = new a(this.mContext, paramString);
  }

  private SQLiteDatabase G(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.UF.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.w(paramString);
    }
    return null;
  }

  // ERROR //
  private void b(List<b> paramList, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 65	com/google/android/gms/tagmanager/v:Ty	Lcom/google/android/gms/internal/fl;
    //   6: invokeinterface 112 1 0
    //   11: lstore 6
    //   13: aload_0
    //   14: lload 6
    //   16: invokespecial 116	com/google/android/gms/tagmanager/v:t	(J)V
    //   19: aload_0
    //   20: aload_1
    //   21: invokeinterface 122 1 0
    //   26: invokespecial 126	com/google/android/gms/tagmanager/v:bQ	(I)V
    //   29: aload_0
    //   30: aload_1
    //   31: lload 6
    //   33: lload_2
    //   34: ladd
    //   35: invokespecial 129	com/google/android/gms/tagmanager/v:c	(Ljava/util/List;J)V
    //   38: aload_0
    //   39: invokespecial 132	com/google/android/gms/tagmanager/v:iW	()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore 4
    //   47: aload_0
    //   48: invokespecial 132	com/google/android/gms/tagmanager/v:iW	()V
    //   51: aload 4
    //   53: athrow
    //   54: astore 5
    //   56: aload_0
    //   57: monitorexit
    //   58: aload 5
    //   60: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	38	45	finally
    //   38	42	54	finally
    //   47	54	54	finally
  }

  private void bQ(int paramInt)
  {
    int i = paramInt + (iV() - this.UG);
    if (i > 0)
    {
      List localList = bR(i);
      bh.u("DataLayer store full, deleting " + localList.size() + " entries to make room.");
      g((String[])localList.toArray(new String[0]));
    }
  }

  // ERROR //
  private List<String> bR(int paramInt)
  {
    // Byte code:
    //   0: new 172	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 173	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: iload_1
    //   9: ifgt +10 -> 19
    //   12: ldc 175
    //   14: invokestatic 90	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   17: aload_2
    //   18: areturn
    //   19: aload_0
    //   20: ldc 177
    //   22: invokespecial 179	com/google/android/gms/tagmanager/v:G	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   25: astore_3
    //   26: aload_3
    //   27: ifnonnull +5 -> 32
    //   30: aload_2
    //   31: areturn
    //   32: aload_3
    //   33: ldc 24
    //   35: iconst_1
    //   36: anewarray 34	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc 26
    //   43: aastore
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: aconst_null
    //   48: ldc 181
    //   50: iconst_1
    //   51: anewarray 4	java/lang/Object
    //   54: dup
    //   55: iconst_0
    //   56: ldc 26
    //   58: aastore
    //   59: invokestatic 38	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   62: iload_1
    //   63: invokestatic 186	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   66: invokevirtual 192	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   69: astore 7
    //   71: aload 7
    //   73: astore 5
    //   75: aload 5
    //   77: invokeinterface 198 1 0
    //   82: ifeq +35 -> 117
    //   85: aload_2
    //   86: aload 5
    //   88: iconst_0
    //   89: invokeinterface 202 2 0
    //   94: invokestatic 206	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   97: invokeinterface 210 2 0
    //   102: pop
    //   103: aload 5
    //   105: invokeinterface 213 1 0
    //   110: istore 9
    //   112: iload 9
    //   114: ifne -29 -> 85
    //   117: aload 5
    //   119: ifnull +10 -> 129
    //   122: aload 5
    //   124: invokeinterface 216 1 0
    //   129: aload_2
    //   130: areturn
    //   131: astore 6
    //   133: aconst_null
    //   134: astore 5
    //   136: new 141	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 142	java/lang/StringBuilder:<init>	()V
    //   143: ldc 218
    //   145: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 6
    //   150: invokevirtual 221	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   153: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 157	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 90	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
    //   162: aload 5
    //   164: ifnull -35 -> 129
    //   167: aload 5
    //   169: invokeinterface 216 1 0
    //   174: goto -45 -> 129
    //   177: astore 4
    //   179: aconst_null
    //   180: astore 5
    //   182: aload 5
    //   184: ifnull +10 -> 194
    //   187: aload 5
    //   189: invokeinterface 216 1 0
    //   194: aload 4
    //   196: athrow
    //   197: astore 4
    //   199: goto -17 -> 182
    //   202: astore 6
    //   204: goto -68 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   32	71	131	android/database/sqlite/SQLiteException
    //   32	71	177	finally
    //   75	85	197	finally
    //   85	112	197	finally
    //   136	162	197	finally
    //   75	85	202	android/database/sqlite/SQLiteException
    //   85	112	202	android/database/sqlite/SQLiteException
  }

  private void bj(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = G("Error opening database for clearKeysWithPrefix.");
    if (localSQLiteDatabase == null)
      return;
    try
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = paramString;
      arrayOfString[1] = (paramString + ".%");
      int i = localSQLiteDatabase.delete("datalayer", "key = ? OR key LIKE ?", arrayOfString);
      bh.v("Cleared " + i + " items");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.w("Error deleting entries with key prefix: " + paramString + " (" + localSQLiteException + ").");
      return;
    }
    finally
    {
      iW();
    }
    throw localObject;
  }

  private List<DataLayer.a> c(List<b> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      b localb = (b)localIterator.next();
      localArrayList.add(new DataLayer.a(localb.UA, j(localb.UM)));
    }
    return localArrayList;
  }

  private void c(List<b> paramList, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = G("Error opening database for writeEntryToDatabase.");
    if (localSQLiteDatabase == null);
    while (true)
    {
      return;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        b localb = (b)localIterator.next();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("expires", Long.valueOf(paramLong));
        localContentValues.put("key", localb.UA);
        localContentValues.put("value", localb.UM);
        localSQLiteDatabase.insert("datalayer", null, localContentValues);
      }
    }
  }

  private List<b> d(List<DataLayer.a> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DataLayer.a locala = (DataLayer.a)localIterator.next();
      localArrayList.add(new b(locala.UA, j(locala.UB)));
    }
    return localArrayList;
  }

  private void g(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0));
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = G("Error opening database for deleteEntries.");
    }
    while (localSQLiteDatabase == null);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "ID";
    arrayOfObject[1] = TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?"));
    String str = String.format("%s in (%s)", arrayOfObject);
    try
    {
      localSQLiteDatabase.delete("datalayer", str, paramArrayOfString);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.w("Error deleting entries " + Arrays.toString(paramArrayOfString));
    }
  }

  private List<DataLayer.a> iT()
  {
    try
    {
      t(this.Ty.currentTimeMillis());
      List localList = c(iU());
      return localList;
    }
    finally
    {
      iW();
    }
    throw localObject;
  }

  private List<b> iU()
  {
    SQLiteDatabase localSQLiteDatabase = G("Error opening database for loadSerialized.");
    ArrayList localArrayList = new ArrayList();
    if (localSQLiteDatabase == null)
      return localArrayList;
    Cursor localCursor = localSQLiteDatabase.query("datalayer", new String[] { "key", "value" }, null, null, null, null, "ID", null);
    try
    {
      if (localCursor.moveToNext())
        localArrayList.add(new b(localCursor.getString(0), localCursor.getBlob(1)));
    }
    finally
    {
      localCursor.close();
    }
    return localArrayList;
  }

  private int iV()
  {
    Cursor localCursor = null;
    SQLiteDatabase localSQLiteDatabase = G("Error opening database for getNumStoredEntries.");
    int i = 0;
    if (localSQLiteDatabase == null);
    while (true)
    {
      return i;
      try
      {
        localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from datalayer", null);
        boolean bool = localCursor.moveToFirst();
        i = 0;
        if (bool)
        {
          long l = localCursor.getLong(0);
          i = (int)l;
        }
        return i;
      }
      catch (SQLiteException localSQLiteException)
      {
        bh.w("Error getting numStoredEntries");
        i = 0;
        return 0;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }
    throw localObject;
  }

  private void iW()
  {
    try
    {
      this.UF.close();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
    }
  }

  // ERROR //
  private Object j(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 373	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 376	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_2
    //   9: new 378	java/io/ObjectInputStream
    //   12: dup
    //   13: aload_2
    //   14: invokespecial 381	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_3
    //   18: aload_3
    //   19: invokevirtual 384	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: astore 10
    //   24: aload_3
    //   25: ifnull +7 -> 32
    //   28: aload_3
    //   29: invokevirtual 385	java/io/ObjectInputStream:close	()V
    //   32: aload_2
    //   33: invokevirtual 386	java/io/ByteArrayInputStream:close	()V
    //   36: aload 10
    //   38: areturn
    //   39: astore 14
    //   41: aconst_null
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +7 -> 51
    //   47: aload_3
    //   48: invokevirtual 385	java/io/ObjectInputStream:close	()V
    //   51: aload_2
    //   52: invokevirtual 386	java/io/ByteArrayInputStream:close	()V
    //   55: aconst_null
    //   56: areturn
    //   57: astore 5
    //   59: aconst_null
    //   60: areturn
    //   61: astore 13
    //   63: aconst_null
    //   64: astore_3
    //   65: aload_3
    //   66: ifnull +7 -> 73
    //   69: aload_3
    //   70: invokevirtual 385	java/io/ObjectInputStream:close	()V
    //   73: aload_2
    //   74: invokevirtual 386	java/io/ByteArrayInputStream:close	()V
    //   77: aconst_null
    //   78: areturn
    //   79: astore 7
    //   81: aconst_null
    //   82: areturn
    //   83: astore 12
    //   85: aconst_null
    //   86: astore_3
    //   87: aload 12
    //   89: astore 8
    //   91: aload_3
    //   92: ifnull +7 -> 99
    //   95: aload_3
    //   96: invokevirtual 385	java/io/ObjectInputStream:close	()V
    //   99: aload_2
    //   100: invokevirtual 386	java/io/ByteArrayInputStream:close	()V
    //   103: aload 8
    //   105: athrow
    //   106: astore 9
    //   108: goto -5 -> 103
    //   111: astore 8
    //   113: goto -22 -> 91
    //   116: astore 6
    //   118: goto -53 -> 65
    //   121: astore 4
    //   123: goto -80 -> 43
    //   126: astore 11
    //   128: aload 10
    //   130: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   9	18	39	java/io/IOException
    //   47	51	57	java/io/IOException
    //   51	55	57	java/io/IOException
    //   9	18	61	java/lang/ClassNotFoundException
    //   69	73	79	java/io/IOException
    //   73	77	79	java/io/IOException
    //   9	18	83	finally
    //   95	99	106	java/io/IOException
    //   99	103	106	java/io/IOException
    //   18	24	111	finally
    //   18	24	116	java/lang/ClassNotFoundException
    //   18	24	121	java/io/IOException
    //   28	32	126	java/io/IOException
    //   32	36	126	java/io/IOException
  }

  // ERROR //
  private byte[] j(Object paramObject)
  {
    // Byte code:
    //   0: new 388	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 389	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_2
    //   8: new 391	java/io/ObjectOutputStream
    //   11: dup
    //   12: aload_2
    //   13: invokespecial 394	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_3
    //   17: aload_3
    //   18: aload_1
    //   19: invokevirtual 398	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   22: aload_2
    //   23: invokevirtual 402	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   26: astore 8
    //   28: aload_3
    //   29: ifnull +7 -> 36
    //   32: aload_3
    //   33: invokevirtual 403	java/io/ObjectOutputStream:close	()V
    //   36: aload_2
    //   37: invokevirtual 404	java/io/ByteArrayOutputStream:close	()V
    //   40: aload 8
    //   42: areturn
    //   43: astore 11
    //   45: aconst_null
    //   46: astore_3
    //   47: aload_3
    //   48: ifnull +7 -> 55
    //   51: aload_3
    //   52: invokevirtual 403	java/io/ObjectOutputStream:close	()V
    //   55: aload_2
    //   56: invokevirtual 404	java/io/ByteArrayOutputStream:close	()V
    //   59: aconst_null
    //   60: areturn
    //   61: astore 5
    //   63: aconst_null
    //   64: areturn
    //   65: astore 10
    //   67: aconst_null
    //   68: astore_3
    //   69: aload 10
    //   71: astore 6
    //   73: aload_3
    //   74: ifnull +7 -> 81
    //   77: aload_3
    //   78: invokevirtual 403	java/io/ObjectOutputStream:close	()V
    //   81: aload_2
    //   82: invokevirtual 404	java/io/ByteArrayOutputStream:close	()V
    //   85: aload 6
    //   87: athrow
    //   88: astore 7
    //   90: goto -5 -> 85
    //   93: astore 6
    //   95: goto -22 -> 73
    //   98: astore 4
    //   100: goto -53 -> 47
    //   103: astore 9
    //   105: aload 8
    //   107: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	17	43	java/io/IOException
    //   51	55	61	java/io/IOException
    //   55	59	61	java/io/IOException
    //   8	17	65	finally
    //   77	81	88	java/io/IOException
    //   81	85	88	java/io/IOException
    //   17	28	93	finally
    //   17	28	98	java/io/IOException
    //   32	36	103	java/io/IOException
    //   36	40	103	java/io/IOException
  }

  private void t(long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = G("Error opening database for deleteOlderThan.");
    if (localSQLiteDatabase == null)
      return;
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = Long.toString(paramLong);
      int i = localSQLiteDatabase.delete("datalayer", "expires <= ?", arrayOfString);
      bh.v("Deleted " + i + " expired items");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      bh.w("Error deleting old entries.");
    }
  }

  public void a(DataLayer.c.a parama)
  {
    this.UE.execute(new Runnable(parama)
    {
      public void run()
      {
        this.UK.b(v.a(v.this));
      }
    });
  }

  public void a(List<DataLayer.a> paramList, long paramLong)
  {
    List localList = d(paramList);
    this.UE.execute(new Runnable(localList, paramLong)
    {
      public void run()
      {
        v.a(v.this, this.UH, this.UI);
      }
    });
  }

  public void bi(String paramString)
  {
    this.UE.execute(new Runnable(paramString)
    {
      public void run()
      {
        v.a(v.this, this.UL);
      }
    });
  }

  class a extends SQLiteOpenHelper
  {
    a(Context paramString, String arg3)
    {
      super(str, null, 1);
    }

    private void a(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor = paramSQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = localCursor.getColumnNames();
        for (int i = 0; i < arrayOfString.length; i++)
          localHashSet.add(arrayOfString[i]);
        localCursor.close();
        if ((!localHashSet.remove("key")) || (!localHashSet.remove("value")) || (!localHashSet.remove("ID")) || (!localHashSet.remove("expires")))
          throw new SQLiteException("Database column missing");
      }
      finally
      {
        localCursor.close();
      }
      if (!localHashSet.isEmpty())
        throw new SQLiteException("Database has extra columns");
    }

    // ERROR //
    private boolean a(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_2
      //   3: ldc 70
      //   5: iconst_1
      //   6: anewarray 72	java/lang/String
      //   9: dup
      //   10: iconst_0
      //   11: ldc 74
      //   13: aastore
      //   14: ldc 76
      //   16: iconst_1
      //   17: anewarray 72	java/lang/String
      //   20: dup
      //   21: iconst_0
      //   22: aload_1
      //   23: aastore
      //   24: aconst_null
      //   25: aconst_null
      //   26: aconst_null
      //   27: invokevirtual 80	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   30: astore 8
      //   32: aload 8
      //   34: invokeinterface 83 1 0
      //   39: istore 10
      //   41: aload 8
      //   43: ifnull +10 -> 53
      //   46: aload 8
      //   48: invokeinterface 43 1 0
      //   53: iload 10
      //   55: ireturn
      //   56: astore 5
      //   58: aconst_null
      //   59: astore 6
      //   61: new 85	java/lang/StringBuilder
      //   64: dup
      //   65: invokespecial 86	java/lang/StringBuilder:<init>	()V
      //   68: ldc 88
      //   70: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   73: aload_1
      //   74: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   77: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   80: invokestatic 101	com/google/android/gms/tagmanager/bh:w	(Ljava/lang/String;)V
      //   83: aload 6
      //   85: ifnull +10 -> 95
      //   88: aload 6
      //   90: invokeinterface 43 1 0
      //   95: iconst_0
      //   96: ireturn
      //   97: astore 4
      //   99: aload_3
      //   100: ifnull +9 -> 109
      //   103: aload_3
      //   104: invokeinterface 43 1 0
      //   109: aload 4
      //   111: athrow
      //   112: astore 4
      //   114: aload 8
      //   116: astore_3
      //   117: goto -18 -> 99
      //   120: astore 7
      //   122: aload 6
      //   124: astore_3
      //   125: aload 7
      //   127: astore 4
      //   129: goto -30 -> 99
      //   132: astore 9
      //   134: aload 8
      //   136: astore 6
      //   138: goto -77 -> 61
      //
      // Exception table:
      //   from	to	target	type
      //   2	32	56	android/database/sqlite/SQLiteException
      //   2	32	97	finally
      //   32	41	112	finally
      //   61	83	120	finally
      //   32	41	132	android/database/sqlite/SQLiteException
    }

    public SQLiteDatabase getWritableDatabase()
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase2 = super.getWritableDatabase();
        localSQLiteDatabase1 = localSQLiteDatabase2;
        if (localSQLiteDatabase1 == null)
          localSQLiteDatabase1 = super.getWritableDatabase();
        return localSQLiteDatabase1;
      }
      catch (SQLiteException localSQLiteException)
      {
        while (true)
        {
          v.b(v.this).getDatabasePath("google_tagmanager.db").delete();
          SQLiteDatabase localSQLiteDatabase1 = null;
        }
      }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      ak.B(paramSQLiteDatabase.getPath());
    }

    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor;
      if (Build.VERSION.SDK_INT < 15)
        localCursor = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
      try
      {
        localCursor.moveToFirst();
        localCursor.close();
        if (!a("datalayer", paramSQLiteDatabase))
        {
          paramSQLiteDatabase.execSQL(v.iX());
          return;
        }
      }
      finally
      {
        localCursor.close();
      }
      a(paramSQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }

  private static class b
  {
    final String UA;
    final byte[] UM;

    b(String paramString, byte[] paramArrayOfByte)
    {
      this.UA = paramString;
      this.UM = paramArrayOfByte;
    }

    public String toString()
    {
      return "KeyAndSerialized: key = " + this.UA + " serialized hash = " + Arrays.hashCode(this.UM);
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gms.tagmanager.v
 * JD-Core Version:    0.6.0
 */