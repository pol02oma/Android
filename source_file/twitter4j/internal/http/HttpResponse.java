package twitter4j.internal.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import twitter4j.conf.ConfigurationContext;
import twitter4j.internal.logging.Logger;
import twitter4j.internal.org.json.JSONArray;
import twitter4j.internal.org.json.JSONObject;

public abstract class HttpResponse
{
  private static final Logger logger = Logger.getLogger(HttpResponseImpl.class);
  protected final HttpClientConfiguration CONF;
  protected InputStream is;
  private JSONObject json = null;
  private JSONArray jsonArray = null;
  protected String responseAsString = null;
  protected int statusCode;
  private boolean streamConsumed = false;

  HttpResponse()
  {
    this.CONF = ConfigurationContext.getInstance();
  }

  public HttpResponse(HttpClientConfiguration paramHttpClientConfiguration)
  {
    this.CONF = paramHttpClientConfiguration;
  }

  private void disconnectForcibly()
  {
    try
    {
      disconnect();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  // ERROR //
  public JSONArray asJSONArray()
    throws twitter4j.TwitterException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 43	twitter4j/internal/http/HttpResponse:jsonArray	Ltwitter4j/internal/org/json/JSONArray;
    //   4: ifnonnull +80 -> 84
    //   7: aconst_null
    //   8: astore_1
    //   9: aload_0
    //   10: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   13: astore 5
    //   15: aconst_null
    //   16: astore_1
    //   17: aload 5
    //   19: ifnonnull +70 -> 89
    //   22: aload_0
    //   23: invokevirtual 70	twitter4j/internal/http/HttpResponse:asReader	()Ljava/io/Reader;
    //   26: astore_1
    //   27: aload_0
    //   28: new 72	twitter4j/internal/org/json/JSONArray
    //   31: dup
    //   32: new 74	twitter4j/internal/org/json/JSONTokener
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 77	twitter4j/internal/org/json/JSONTokener:<init>	(Ljava/io/Reader;)V
    //   40: invokespecial 80	twitter4j/internal/org/json/JSONArray:<init>	(Ltwitter4j/internal/org/json/JSONTokener;)V
    //   43: putfield 43	twitter4j/internal/http/HttpResponse:jsonArray	Ltwitter4j/internal/org/json/JSONArray;
    //   46: aload_0
    //   47: getfield 51	twitter4j/internal/http/HttpResponse:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   50: invokeinterface 86 1 0
    //   55: ifeq +120 -> 175
    //   58: getstatic 32	twitter4j/internal/http/HttpResponse:logger	Ltwitter4j/internal/logging/Logger;
    //   61: aload_0
    //   62: getfield 43	twitter4j/internal/http/HttpResponse:jsonArray	Ltwitter4j/internal/org/json/JSONArray;
    //   65: iconst_1
    //   66: invokevirtual 90	twitter4j/internal/org/json/JSONArray:toString	(I)Ljava/lang/String;
    //   69: invokevirtual 94	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   72: aload_1
    //   73: ifnull +7 -> 80
    //   76: aload_1
    //   77: invokevirtual 99	java/io/Reader:close	()V
    //   80: aload_0
    //   81: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   84: aload_0
    //   85: getfield 43	twitter4j/internal/http/HttpResponse:jsonArray	Ltwitter4j/internal/org/json/JSONArray;
    //   88: areturn
    //   89: aload_0
    //   90: new 72	twitter4j/internal/org/json/JSONArray
    //   93: dup
    //   94: aload_0
    //   95: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   98: invokespecial 103	twitter4j/internal/org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   101: putfield 43	twitter4j/internal/http/HttpResponse:jsonArray	Ltwitter4j/internal/org/json/JSONArray;
    //   104: aconst_null
    //   105: astore_1
    //   106: goto -60 -> 46
    //   109: astore 4
    //   111: getstatic 32	twitter4j/internal/http/HttpResponse:logger	Ltwitter4j/internal/logging/Logger;
    //   114: invokevirtual 106	twitter4j/internal/logging/Logger:isDebugEnabled	()Z
    //   117: ifeq +102 -> 219
    //   120: new 62	twitter4j/TwitterException
    //   123: dup
    //   124: new 108	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   131: aload 4
    //   133: invokevirtual 113	twitter4j/internal/org/json/JSONException:getMessage	()Ljava/lang/String;
    //   136: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: ldc 119
    //   141: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_0
    //   145: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   148: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: aload 4
    //   156: invokespecial 124	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   159: athrow
    //   160: astore_2
    //   161: aload_1
    //   162: ifnull +7 -> 169
    //   165: aload_1
    //   166: invokevirtual 99	java/io/Reader:close	()V
    //   169: aload_0
    //   170: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   173: aload_2
    //   174: athrow
    //   175: getstatic 32	twitter4j/internal/http/HttpResponse:logger	Ltwitter4j/internal/logging/Logger;
    //   178: astore 6
    //   180: aload_0
    //   181: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   184: ifnull +19 -> 203
    //   187: aload_0
    //   188: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   191: astore 8
    //   193: aload 6
    //   195: aload 8
    //   197: invokevirtual 94	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   200: goto -128 -> 72
    //   203: aload_0
    //   204: getfield 43	twitter4j/internal/http/HttpResponse:jsonArray	Ltwitter4j/internal/org/json/JSONArray;
    //   207: invokevirtual 125	twitter4j/internal/org/json/JSONArray:toString	()Ljava/lang/String;
    //   210: astore 7
    //   212: aload 7
    //   214: astore 8
    //   216: goto -23 -> 193
    //   219: new 62	twitter4j/TwitterException
    //   222: dup
    //   223: aload 4
    //   225: invokevirtual 113	twitter4j/internal/org/json/JSONException:getMessage	()Ljava/lang/String;
    //   228: aload 4
    //   230: invokespecial 124	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   233: athrow
    //   234: astore 9
    //   236: goto -156 -> 80
    //   239: astore_3
    //   240: goto -71 -> 169
    //
    // Exception table:
    //   from	to	target	type
    //   9	15	109	twitter4j/internal/org/json/JSONException
    //   22	46	109	twitter4j/internal/org/json/JSONException
    //   46	72	109	twitter4j/internal/org/json/JSONException
    //   89	104	109	twitter4j/internal/org/json/JSONException
    //   175	193	109	twitter4j/internal/org/json/JSONException
    //   193	200	109	twitter4j/internal/org/json/JSONException
    //   203	212	109	twitter4j/internal/org/json/JSONException
    //   9	15	160	finally
    //   22	46	160	finally
    //   46	72	160	finally
    //   89	104	160	finally
    //   111	160	160	finally
    //   175	193	160	finally
    //   193	200	160	finally
    //   203	212	160	finally
    //   219	234	160	finally
    //   76	80	234	java/io/IOException
    //   165	169	239	java/io/IOException
  }

  // ERROR //
  public JSONObject asJSONObject()
    throws twitter4j.TwitterException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	twitter4j/internal/http/HttpResponse:json	Ltwitter4j/internal/org/json/JSONObject;
    //   4: ifnonnull +80 -> 84
    //   7: aconst_null
    //   8: astore_1
    //   9: aload_0
    //   10: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   13: astore 5
    //   15: aconst_null
    //   16: astore_1
    //   17: aload 5
    //   19: ifnonnull +70 -> 89
    //   22: aload_0
    //   23: invokevirtual 70	twitter4j/internal/http/HttpResponse:asReader	()Ljava/io/Reader;
    //   26: astore_1
    //   27: aload_0
    //   28: new 129	twitter4j/internal/org/json/JSONObject
    //   31: dup
    //   32: new 74	twitter4j/internal/org/json/JSONTokener
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 77	twitter4j/internal/org/json/JSONTokener:<init>	(Ljava/io/Reader;)V
    //   40: invokespecial 130	twitter4j/internal/org/json/JSONObject:<init>	(Ltwitter4j/internal/org/json/JSONTokener;)V
    //   43: putfield 41	twitter4j/internal/http/HttpResponse:json	Ltwitter4j/internal/org/json/JSONObject;
    //   46: aload_0
    //   47: getfield 51	twitter4j/internal/http/HttpResponse:CONF	Ltwitter4j/internal/http/HttpClientConfiguration;
    //   50: invokeinterface 86 1 0
    //   55: ifeq +93 -> 148
    //   58: getstatic 32	twitter4j/internal/http/HttpResponse:logger	Ltwitter4j/internal/logging/Logger;
    //   61: aload_0
    //   62: getfield 41	twitter4j/internal/http/HttpResponse:json	Ltwitter4j/internal/org/json/JSONObject;
    //   65: iconst_1
    //   66: invokevirtual 131	twitter4j/internal/org/json/JSONObject:toString	(I)Ljava/lang/String;
    //   69: invokevirtual 94	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   72: aload_1
    //   73: ifnull +7 -> 80
    //   76: aload_1
    //   77: invokevirtual 99	java/io/Reader:close	()V
    //   80: aload_0
    //   81: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   84: aload_0
    //   85: getfield 41	twitter4j/internal/http/HttpResponse:json	Ltwitter4j/internal/org/json/JSONObject;
    //   88: areturn
    //   89: aload_0
    //   90: new 129	twitter4j/internal/org/json/JSONObject
    //   93: dup
    //   94: aload_0
    //   95: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   98: invokespecial 132	twitter4j/internal/org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   101: putfield 41	twitter4j/internal/http/HttpResponse:json	Ltwitter4j/internal/org/json/JSONObject;
    //   104: aconst_null
    //   105: astore_1
    //   106: goto -60 -> 46
    //   109: astore 4
    //   111: aload_0
    //   112: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   115: ifnonnull +77 -> 192
    //   118: new 62	twitter4j/TwitterException
    //   121: dup
    //   122: aload 4
    //   124: invokevirtual 113	twitter4j/internal/org/json/JSONException:getMessage	()Ljava/lang/String;
    //   127: aload 4
    //   129: invokespecial 124	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   132: athrow
    //   133: astore_2
    //   134: aload_1
    //   135: ifnull +7 -> 142
    //   138: aload_1
    //   139: invokevirtual 99	java/io/Reader:close	()V
    //   142: aload_0
    //   143: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   146: aload_2
    //   147: athrow
    //   148: getstatic 32	twitter4j/internal/http/HttpResponse:logger	Ltwitter4j/internal/logging/Logger;
    //   151: astore 6
    //   153: aload_0
    //   154: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   157: ifnull +19 -> 176
    //   160: aload_0
    //   161: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   164: astore 8
    //   166: aload 6
    //   168: aload 8
    //   170: invokevirtual 94	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   173: goto -101 -> 72
    //   176: aload_0
    //   177: getfield 41	twitter4j/internal/http/HttpResponse:json	Ltwitter4j/internal/org/json/JSONObject;
    //   180: invokevirtual 133	twitter4j/internal/org/json/JSONObject:toString	()Ljava/lang/String;
    //   183: astore 7
    //   185: aload 7
    //   187: astore 8
    //   189: goto -23 -> 166
    //   192: new 62	twitter4j/TwitterException
    //   195: dup
    //   196: new 108	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   203: aload 4
    //   205: invokevirtual 113	twitter4j/internal/org/json/JSONException:getMessage	()Ljava/lang/String;
    //   208: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: ldc 119
    //   213: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: aload_0
    //   217: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   220: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: aload 4
    //   228: invokespecial 124	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   231: athrow
    //   232: astore 9
    //   234: goto -154 -> 80
    //   237: astore_3
    //   238: goto -96 -> 142
    //
    // Exception table:
    //   from	to	target	type
    //   9	15	109	twitter4j/internal/org/json/JSONException
    //   22	46	109	twitter4j/internal/org/json/JSONException
    //   46	72	109	twitter4j/internal/org/json/JSONException
    //   89	104	109	twitter4j/internal/org/json/JSONException
    //   148	166	109	twitter4j/internal/org/json/JSONException
    //   166	173	109	twitter4j/internal/org/json/JSONException
    //   176	185	109	twitter4j/internal/org/json/JSONException
    //   9	15	133	finally
    //   22	46	133	finally
    //   46	72	133	finally
    //   89	104	133	finally
    //   111	133	133	finally
    //   148	166	133	finally
    //   166	173	133	finally
    //   176	185	133	finally
    //   192	232	133	finally
    //   76	80	232	java/io/IOException
    //   138	142	237	java/io/IOException
  }

  public Reader asReader()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.is, "UTF-8"));
      return localBufferedReader;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return new InputStreamReader(this.is);
  }

  public InputStream asStream()
  {
    if (this.streamConsumed)
      throw new IllegalStateException("Stream has already been consumed.");
    return this.is;
  }

  // ERROR //
  public String asString()
    throws twitter4j.TwitterException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   4: ifnonnull +190 -> 194
    //   7: aconst_null
    //   8: astore_1
    //   9: aconst_null
    //   10: astore_2
    //   11: aload_0
    //   12: invokevirtual 160	twitter4j/internal/http/HttpResponse:asStream	()Ljava/io/InputStream;
    //   15: astore 7
    //   17: aload 7
    //   19: astore_2
    //   20: aload_2
    //   21: ifnonnull +25 -> 46
    //   24: aload_2
    //   25: ifnull +7 -> 32
    //   28: aload_2
    //   29: invokevirtual 163	java/io/InputStream:close	()V
    //   32: iconst_0
    //   33: ifeq +7 -> 40
    //   36: aconst_null
    //   37: invokevirtual 164	java/io/BufferedReader:close	()V
    //   40: aload_0
    //   41: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   44: aconst_null
    //   45: areturn
    //   46: new 137	java/io/BufferedReader
    //   49: dup
    //   50: new 139	java/io/InputStreamReader
    //   53: dup
    //   54: aload_2
    //   55: ldc 143
    //   57: invokespecial 146	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   60: invokespecial 147	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   63: astore 8
    //   65: new 108	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   72: astore 9
    //   74: aload 8
    //   76: invokevirtual 167	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   79: astore 10
    //   81: aload 10
    //   83: ifnull +61 -> 144
    //   86: aload 9
    //   88: aload 10
    //   90: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: ldc 169
    //   95: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: pop
    //   99: goto -25 -> 74
    //   102: astore_3
    //   103: aload 8
    //   105: astore_1
    //   106: new 62	twitter4j/TwitterException
    //   109: dup
    //   110: aload_3
    //   111: invokevirtual 170	java/io/IOException:getMessage	()Ljava/lang/String;
    //   114: aload_3
    //   115: invokespecial 124	twitter4j/TwitterException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   118: athrow
    //   119: astore 4
    //   121: aload_2
    //   122: ifnull +7 -> 129
    //   125: aload_2
    //   126: invokevirtual 163	java/io/InputStream:close	()V
    //   129: aload_1
    //   130: ifnull +7 -> 137
    //   133: aload_1
    //   134: invokevirtual 164	java/io/BufferedReader:close	()V
    //   137: aload_0
    //   138: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   141: aload 4
    //   143: athrow
    //   144: aload_0
    //   145: aload 9
    //   147: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: putfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   153: getstatic 32	twitter4j/internal/http/HttpResponse:logger	Ltwitter4j/internal/logging/Logger;
    //   156: aload_0
    //   157: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   160: invokevirtual 94	twitter4j/internal/logging/Logger:debug	(Ljava/lang/String;)V
    //   163: aload_2
    //   164: invokevirtual 163	java/io/InputStream:close	()V
    //   167: aload_0
    //   168: iconst_1
    //   169: putfield 39	twitter4j/internal/http/HttpResponse:streamConsumed	Z
    //   172: aload_2
    //   173: ifnull +7 -> 180
    //   176: aload_2
    //   177: invokevirtual 163	java/io/InputStream:close	()V
    //   180: aload 8
    //   182: ifnull +8 -> 190
    //   185: aload 8
    //   187: invokevirtual 164	java/io/BufferedReader:close	()V
    //   190: aload_0
    //   191: invokespecial 101	twitter4j/internal/http/HttpResponse:disconnectForcibly	()V
    //   194: aload_0
    //   195: getfield 37	twitter4j/internal/http/HttpResponse:responseAsString	Ljava/lang/String;
    //   198: areturn
    //   199: astore 15
    //   201: goto -169 -> 32
    //   204: astore 14
    //   206: goto -166 -> 40
    //   209: astore 13
    //   211: goto -31 -> 180
    //   214: astore 12
    //   216: goto -26 -> 190
    //   219: astore 6
    //   221: goto -92 -> 129
    //   224: astore 5
    //   226: goto -89 -> 137
    //   229: astore 4
    //   231: aload 8
    //   233: astore_1
    //   234: goto -113 -> 121
    //   237: astore_3
    //   238: aconst_null
    //   239: astore_1
    //   240: goto -134 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   65	74	102	java/io/IOException
    //   74	81	102	java/io/IOException
    //   86	99	102	java/io/IOException
    //   144	172	102	java/io/IOException
    //   11	17	119	finally
    //   46	65	119	finally
    //   106	119	119	finally
    //   28	32	199	java/io/IOException
    //   36	40	204	java/io/IOException
    //   176	180	209	java/io/IOException
    //   185	190	214	java/io/IOException
    //   125	129	219	java/io/IOException
    //   133	137	224	java/io/IOException
    //   65	74	229	finally
    //   74	81	229	finally
    //   86	99	229	finally
    //   144	172	229	finally
    //   11	17	237	java/io/IOException
    //   46	65	237	java/io/IOException
  }

  public abstract void disconnect()
    throws IOException;

  public abstract String getResponseHeader(String paramString);

  public abstract Map<String, List<String>> getResponseHeaderFields();

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public String toString()
  {
    return "HttpResponse{statusCode=" + this.statusCode + ", responseAsString='" + this.responseAsString + '\'' + ", is=" + this.is + ", streamConsumed=" + this.streamConsumed + '}';
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     twitter4j.internal.http.HttpResponse
 * JD-Core Version:    0.6.0
 */