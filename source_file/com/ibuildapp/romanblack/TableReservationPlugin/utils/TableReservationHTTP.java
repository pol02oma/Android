package com.ibuildapp.romanblack.TableReservationPlugin.utils;

import android.util.Log;
import com.appbuilder.sdk.android.Statics;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class TableReservationHTTP
{
  private static final String ADD_ORDER_URL = "http://" + Statics.BASE_DOMEN + "/mdscr/tablereservation/addorder";
  private static final int ADD_REQUEST_ERROR = 4;
  private static final String CANCEL_ORDER_URL = "http://" + Statics.BASE_DOMEN + "/mdscr/tablereservation/cancelorder";
  private static final int CANCEL_REQUEST_ERROR = 3;
  private static final String LIST_ORDER_URL = "http://" + Statics.BASE_DOMEN + "/mdscr/tablereservation/getorders";
  private static final String LOGIN_URL;
  private static final String MAIL_ORDER_URL = "http://" + Statics.BASE_DOMEN + "/mdscr/tablereservation/sendmail";
  public static final String SIGNUP_URL;
  private static final String TAG = "com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP";

  static
  {
    LOGIN_URL = "http://" + Statics.BASE_DOMEN + "/mdscr/user/login";
    SIGNUP_URL = "http://" + Statics.BASE_DOMEN + "/mdscr/user/signup";
  }

  public static String loginPost(String paramString1, String paramString2)
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 15000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 15000);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
    ArrayList localArrayList = new ArrayList();
    try
    {
      HttpPost localHttpPost = new HttpPost(LOGIN_URL);
      localArrayList.add(new BasicNameValuePair("login", paramString1));
      localArrayList.add(new BasicNameValuePair("password", paramString2));
      localArrayList.add(new BasicNameValuePair("app_id", Statics.appId));
      localArrayList.add(new BasicNameValuePair("token", Statics.appToken));
      localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList));
      String str = (String)localDefaultHttpClient.execute(localHttpPost, new BasicResponseHandler());
      Log.d("", "");
      return str;
    }
    catch (Exception localException)
    {
      Log.e("REGISTRATION ERROR", localException.getMessage(), localException);
    }
    return null;
  }

  // ERROR //
  public static String sendAddOrderRequest(com.appbuilder.sdk.android.authorization.entities.User paramUser, android.os.Handler paramHandler, com.ibuildapp.romanblack.TableReservationPlugin.TableReservationInfo paramTableReservationInfo)
  {
    // Byte code:
    //   0: new 72	org/apache/http/params/BasicHttpParams
    //   3: dup
    //   4: invokespecial 73	org/apache/http/params/BasicHttpParams:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: sipush 15000
    //   12: invokestatic 79	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   15: aload_3
    //   16: sipush 15000
    //   19: invokestatic 82	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   22: new 84	org/apache/http/impl/client/DefaultHttpClient
    //   25: dup
    //   26: aload_3
    //   27: invokespecial 87	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   30: astore 4
    //   32: new 92	org/apache/http/client/methods/HttpPost
    //   35: dup
    //   36: getstatic 45	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:ADD_ORDER_URL	Ljava/lang/String;
    //   39: invokespecial 95	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   42: astore 5
    //   44: ldc 19
    //   46: new 23	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   53: ldc 167
    //   55: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: getstatic 45	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:ADD_ORDER_URL	Ljava/lang/String;
    //   61: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   70: pop
    //   71: new 171	org/apache/http/entity/mime/MultipartEntity
    //   74: dup
    //   75: invokespecial 172	org/apache/http/entity/mime/MultipartEntity:<init>	()V
    //   78: astore 7
    //   80: aconst_null
    //   81: astore 8
    //   83: aload_0
    //   84: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   87: getstatic 184	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:FACEBOOK	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   90: if_acmpne +492 -> 582
    //   93: ldc 186
    //   95: astore 23
    //   97: aload 7
    //   99: ldc 188
    //   101: new 190	org/apache/http/entity/mime/content/StringBody
    //   104: dup
    //   105: new 23	java/lang/StringBuilder
    //   108: dup
    //   109: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   112: aload_0
    //   113: invokevirtual 193	com/appbuilder/sdk/android/authorization/entities/User:getUserFirstName	()Ljava/lang/String;
    //   116: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc 195
    //   121: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: aload_0
    //   125: invokevirtual 198	com/appbuilder/sdk/android/authorization/entities/User:getUserLastName	()Ljava/lang/String;
    //   128: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: ldc 200
    //   136: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   139: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   142: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   145: new 190	org/apache/http/entity/mime/content/StringBody
    //   148: dup
    //   149: aload 23
    //   151: ldc 200
    //   153: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   156: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   159: astore 24
    //   161: aload 7
    //   163: ldc 215
    //   165: aload 24
    //   167: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   170: aload 7
    //   172: ldc 217
    //   174: new 190	org/apache/http/entity/mime/content/StringBody
    //   177: dup
    //   178: aload_0
    //   179: invokevirtual 220	com/appbuilder/sdk/android/authorization/entities/User:getAccountId	()Ljava/lang/String;
    //   182: ldc 200
    //   184: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   187: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   190: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   193: aload 7
    //   195: ldc 112
    //   197: new 190	org/apache/http/entity/mime/content/StringBody
    //   200: dup
    //   201: aload_2
    //   202: invokevirtual 225	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getAppid	()Ljava/lang/String;
    //   205: ldc 200
    //   207: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   210: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   213: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   216: aload 7
    //   218: ldc 227
    //   220: new 190	org/apache/http/entity/mime/content/StringBody
    //   223: dup
    //   224: aload_2
    //   225: invokevirtual 230	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getModuleid	()Ljava/lang/String;
    //   228: ldc 200
    //   230: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   233: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   236: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   239: invokestatic 236	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   242: invokevirtual 237	java/util/UUID:toString	()Ljava/lang/String;
    //   245: astore 8
    //   247: new 190	org/apache/http/entity/mime/content/StringBody
    //   250: dup
    //   251: aload 8
    //   253: ldc 200
    //   255: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   258: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   261: astore 25
    //   263: aload 7
    //   265: ldc 239
    //   267: aload 25
    //   269: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   272: aload_2
    //   273: invokevirtual 243	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderDate	()Ljava/util/Date;
    //   276: astore 26
    //   278: aload 26
    //   280: aload_2
    //   281: invokevirtual 247	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
    //   284: getfield 252	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:houres	I
    //   287: invokevirtual 258	java/util/Date:setHours	(I)V
    //   290: aload 26
    //   292: aload_2
    //   293: invokevirtual 247	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
    //   296: getfield 261	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:minutes	I
    //   299: invokevirtual 264	java/util/Date:setMinutes	(I)V
    //   302: aload 26
    //   304: invokevirtual 268	java/util/Date:getTimezoneOffset	()I
    //   307: pop
    //   308: invokestatic 271	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:timeZoneToString	()Ljava/lang/String;
    //   311: astore 28
    //   313: new 190	org/apache/http/entity/mime/content/StringBody
    //   316: dup
    //   317: aload 28
    //   319: ldc 200
    //   321: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   324: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   327: astore 29
    //   329: aload 7
    //   331: ldc_w 273
    //   334: aload 29
    //   336: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   339: aload 7
    //   341: ldc_w 275
    //   344: new 190	org/apache/http/entity/mime/content/StringBody
    //   347: dup
    //   348: aload 26
    //   350: invokevirtual 279	java/util/Date:getTime	()J
    //   353: ldc2_w 280
    //   356: ldiv
    //   357: invokestatic 286	java/lang/Long:toString	(J)Ljava/lang/String;
    //   360: ldc 200
    //   362: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   365: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   368: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   371: aload 7
    //   373: ldc_w 288
    //   376: new 190	org/apache/http/entity/mime/content/StringBody
    //   379: dup
    //   380: aload_2
    //   381: invokevirtual 291	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getPersonsAmount	()I
    //   384: invokestatic 296	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   387: ldc 200
    //   389: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   392: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   395: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   398: aload 7
    //   400: ldc_w 298
    //   403: new 190	org/apache/http/entity/mime/content/StringBody
    //   406: dup
    //   407: aload_2
    //   408: invokevirtual 301	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getSpecialRequest	()Ljava/lang/String;
    //   411: ldc 200
    //   413: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   416: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   419: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   422: aload 7
    //   424: ldc_w 303
    //   427: new 190	org/apache/http/entity/mime/content/StringBody
    //   430: dup
    //   431: aload_2
    //   432: invokevirtual 306	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getPhoneNumber	()Ljava/lang/String;
    //   435: ldc 200
    //   437: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   440: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   443: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   446: aload 7
    //   448: ldc_w 308
    //   451: new 190	org/apache/http/entity/mime/content/StringBody
    //   454: dup
    //   455: aload_2
    //   456: invokevirtual 311	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getCustomerEmail	()Ljava/lang/String;
    //   459: ldc 200
    //   461: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   464: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   467: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   470: aload 7
    //   472: ldc 112
    //   474: new 190	org/apache/http/entity/mime/content/StringBody
    //   477: dup
    //   478: getstatic 115	com/appbuilder/sdk/android/Statics:appId	Ljava/lang/String;
    //   481: ldc 200
    //   483: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   486: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   489: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   492: aload 7
    //   494: ldc 117
    //   496: new 190	org/apache/http/entity/mime/content/StringBody
    //   499: dup
    //   500: getstatic 120	com/appbuilder/sdk/android/Statics:appToken	Ljava/lang/String;
    //   503: ldc 200
    //   505: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   508: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   511: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   514: aload 5
    //   516: aload 7
    //   518: invokevirtual 129	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   521: new 131	org/apache/http/impl/client/BasicResponseHandler
    //   524: dup
    //   525: invokespecial 132	org/apache/http/impl/client/BasicResponseHandler:<init>	()V
    //   528: astore 11
    //   530: aload 4
    //   532: aload 5
    //   534: aload 11
    //   536: invokeinterface 138 3 0
    //   541: checkcast 140	java/lang/String
    //   544: astore 18
    //   546: ldc_w 312
    //   549: ldc 142
    //   551: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   554: pop
    //   555: aload 18
    //   557: invokestatic 318	com/ibuildapp/romanblack/TableReservationPlugin/JSONParser:parseQueryError	(Ljava/lang/String;)Ljava/lang/String;
    //   560: astore 20
    //   562: aload 20
    //   564: ifnull +15 -> 579
    //   567: aload 20
    //   569: invokevirtual 321	java/lang/String:length	()I
    //   572: istore 21
    //   574: iload 21
    //   576: ifne +152 -> 728
    //   579: aload 8
    //   581: areturn
    //   582: aload_0
    //   583: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   586: getstatic 324	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:TWITTER	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   589: if_acmpne +47 -> 636
    //   592: ldc_w 326
    //   595: astore 23
    //   597: aload 7
    //   599: ldc 188
    //   601: new 190	org/apache/http/entity/mime/content/StringBody
    //   604: dup
    //   605: aload_0
    //   606: invokevirtual 329	com/appbuilder/sdk/android/authorization/entities/User:getUserName	()Ljava/lang/String;
    //   609: ldc 200
    //   611: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   614: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   617: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   620: goto -475 -> 145
    //   623: astore 9
    //   625: ldc 142
    //   627: ldc 142
    //   629: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   632: pop
    //   633: goto -119 -> 514
    //   636: aload_0
    //   637: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   640: getstatic 332	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:IBUILDAPP	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   643: if_acmpne +34 -> 677
    //   646: ldc_w 334
    //   649: astore 23
    //   651: aload 7
    //   653: ldc 188
    //   655: new 190	org/apache/http/entity/mime/content/StringBody
    //   658: dup
    //   659: aload_0
    //   660: invokevirtual 329	com/appbuilder/sdk/android/authorization/entities/User:getUserName	()Ljava/lang/String;
    //   663: ldc 200
    //   665: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   668: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   671: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   674: goto -529 -> 145
    //   677: aload_0
    //   678: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   681: astore 30
    //   683: getstatic 337	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:GUEST	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   686: astore 31
    //   688: aconst_null
    //   689: astore 23
    //   691: aload 30
    //   693: aload 31
    //   695: if_acmpne -550 -> 145
    //   698: ldc_w 339
    //   701: astore 23
    //   703: aload 7
    //   705: ldc 188
    //   707: new 190	org/apache/http/entity/mime/content/StringBody
    //   710: dup
    //   711: ldc_w 341
    //   714: ldc 200
    //   716: invokestatic 206	java/nio/charset/Charset:forName	(Ljava/lang/String;)Ljava/nio/charset/Charset;
    //   719: invokespecial 209	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;Ljava/nio/charset/Charset;)V
    //   722: invokevirtual 213	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
    //   725: goto -580 -> 145
    //   728: aload_1
    //   729: iconst_4
    //   730: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   733: pop
    //   734: aconst_null
    //   735: areturn
    //   736: astore 16
    //   738: aload_1
    //   739: iconst_4
    //   740: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   743: pop
    //   744: aconst_null
    //   745: areturn
    //   746: astore 14
    //   748: aload_1
    //   749: iconst_4
    //   750: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   753: pop
    //   754: aconst_null
    //   755: areturn
    //   756: astore 12
    //   758: aload_1
    //   759: iconst_4
    //   760: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   763: pop
    //   764: aconst_null
    //   765: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   83	93	623	java/lang/Exception
    //   97	145	623	java/lang/Exception
    //   145	514	623	java/lang/Exception
    //   582	592	623	java/lang/Exception
    //   597	620	623	java/lang/Exception
    //   636	646	623	java/lang/Exception
    //   651	674	623	java/lang/Exception
    //   677	688	623	java/lang/Exception
    //   703	725	623	java/lang/Exception
    //   530	562	736	org/apache/http/conn/ConnectTimeoutException
    //   567	574	736	org/apache/http/conn/ConnectTimeoutException
    //   728	734	736	org/apache/http/conn/ConnectTimeoutException
    //   530	562	746	org/apache/http/client/ClientProtocolException
    //   567	574	746	org/apache/http/client/ClientProtocolException
    //   728	734	746	org/apache/http/client/ClientProtocolException
    //   530	562	756	java/io/IOException
    //   567	574	756	java/io/IOException
    //   728	734	756	java/io/IOException
  }

  // ERROR //
  public static void sendCancelOrderRequest(String paramString, com.appbuilder.sdk.android.authorization.entities.User paramUser, android.os.Handler paramHandler, com.ibuildapp.romanblack.TableReservationPlugin.TableReservationInfo paramTableReservationInfo)
  {
    // Byte code:
    //   0: new 72	org/apache/http/params/BasicHttpParams
    //   3: dup
    //   4: invokespecial 73	org/apache/http/params/BasicHttpParams:<init>	()V
    //   7: astore 4
    //   9: aload 4
    //   11: sipush 15000
    //   14: invokestatic 79	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   17: aload 4
    //   19: sipush 15000
    //   22: invokestatic 82	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   25: new 84	org/apache/http/impl/client/DefaultHttpClient
    //   28: dup
    //   29: aload 4
    //   31: invokespecial 87	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   34: astore 5
    //   36: new 92	org/apache/http/client/methods/HttpPost
    //   39: dup
    //   40: getstatic 49	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:CANCEL_ORDER_URL	Ljava/lang/String;
    //   43: invokespecial 95	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   46: astore 6
    //   48: ldc 19
    //   50: new 23	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   57: ldc_w 353
    //   60: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: getstatic 49	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:CANCEL_ORDER_URL	Ljava/lang/String;
    //   66: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   75: pop
    //   76: new 89	java/util/ArrayList
    //   79: dup
    //   80: invokespecial 90	java/util/ArrayList:<init>	()V
    //   83: astore 8
    //   85: aload 8
    //   87: new 97	org/apache/http/message/BasicNameValuePair
    //   90: dup
    //   91: ldc 239
    //   93: aload_0
    //   94: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   97: invokeinterface 108 2 0
    //   102: pop
    //   103: aload 8
    //   105: new 97	org/apache/http/message/BasicNameValuePair
    //   108: dup
    //   109: ldc 217
    //   111: aload_1
    //   112: invokevirtual 220	com/appbuilder/sdk/android/authorization/entities/User:getAccountId	()Ljava/lang/String;
    //   115: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   118: invokeinterface 108 2 0
    //   123: pop
    //   124: aload_1
    //   125: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   128: getstatic 184	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:FACEBOOK	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   131: if_acmpne +153 -> 284
    //   134: ldc 186
    //   136: astore 13
    //   138: aload 8
    //   140: new 97	org/apache/http/message/BasicNameValuePair
    //   143: dup
    //   144: ldc 215
    //   146: aload 13
    //   148: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: invokeinterface 108 2 0
    //   156: pop
    //   157: aload 8
    //   159: new 97	org/apache/http/message/BasicNameValuePair
    //   162: dup
    //   163: ldc 112
    //   165: aload_3
    //   166: invokevirtual 225	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getAppid	()Ljava/lang/String;
    //   169: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: invokeinterface 108 2 0
    //   177: pop
    //   178: aload 8
    //   180: new 97	org/apache/http/message/BasicNameValuePair
    //   183: dup
    //   184: ldc 227
    //   186: aload_3
    //   187: invokevirtual 230	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getModuleid	()Ljava/lang/String;
    //   190: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   193: invokeinterface 108 2 0
    //   198: pop
    //   199: aload 8
    //   201: new 97	org/apache/http/message/BasicNameValuePair
    //   204: dup
    //   205: ldc 112
    //   207: getstatic 115	com/appbuilder/sdk/android/Statics:appId	Ljava/lang/String;
    //   210: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   213: invokeinterface 108 2 0
    //   218: pop
    //   219: aload 8
    //   221: new 97	org/apache/http/message/BasicNameValuePair
    //   224: dup
    //   225: ldc 117
    //   227: getstatic 120	com/appbuilder/sdk/android/Statics:appToken	Ljava/lang/String;
    //   230: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   233: invokeinterface 108 2 0
    //   238: pop
    //   239: aload 6
    //   241: new 122	org/apache/http/client/entity/UrlEncodedFormEntity
    //   244: dup
    //   245: aload 8
    //   247: invokespecial 125	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;)V
    //   250: invokevirtual 129	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   253: new 131	org/apache/http/impl/client/BasicResponseHandler
    //   256: dup
    //   257: invokespecial 132	org/apache/http/impl/client/BasicResponseHandler:<init>	()V
    //   260: astore 21
    //   262: ldc_w 354
    //   265: aload 5
    //   267: aload 6
    //   269: aload 21
    //   271: invokeinterface 138 3 0
    //   276: checkcast 140	java/lang/String
    //   279: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   282: pop
    //   283: return
    //   284: aload_1
    //   285: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   288: getstatic 324	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:TWITTER	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   291: if_acmpne +11 -> 302
    //   294: ldc_w 326
    //   297: astore 13
    //   299: goto -161 -> 138
    //   302: aload_1
    //   303: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   306: getstatic 332	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:IBUILDAPP	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   309: if_acmpne +11 -> 320
    //   312: ldc_w 334
    //   315: astore 13
    //   317: goto -179 -> 138
    //   320: aload_1
    //   321: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   324: astore 11
    //   326: getstatic 337	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:GUEST	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   329: astore 12
    //   331: aconst_null
    //   332: astore 13
    //   334: aload 11
    //   336: aload 12
    //   338: if_acmpne -200 -> 138
    //   341: ldc_w 339
    //   344: astore 13
    //   346: goto -208 -> 138
    //   349: astore 19
    //   351: ldc 142
    //   353: ldc 142
    //   355: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   358: pop
    //   359: goto -106 -> 253
    //   362: astore 28
    //   364: aload_2
    //   365: iconst_3
    //   366: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   369: pop
    //   370: return
    //   371: astore 25
    //   373: ldc_w 354
    //   376: ldc 142
    //   378: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   381: pop
    //   382: aload_2
    //   383: iconst_3
    //   384: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   387: pop
    //   388: return
    //   389: astore 22
    //   391: ldc_w 354
    //   394: ldc 142
    //   396: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   399: pop
    //   400: aload_2
    //   401: iconst_3
    //   402: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   405: pop
    //   406: return
    //
    // Exception table:
    //   from	to	target	type
    //   239	253	349	java/io/UnsupportedEncodingException
    //   262	283	362	org/apache/http/conn/ConnectTimeoutException
    //   262	283	371	org/apache/http/client/ClientProtocolException
    //   262	283	389	java/io/IOException
  }

  // ERROR //
  public static String sendListOrdersRequest(com.appbuilder.sdk.android.authorization.entities.User paramUser, com.ibuildapp.romanblack.TableReservationPlugin.TableReservationInfo paramTableReservationInfo)
  {
    // Byte code:
    //   0: new 72	org/apache/http/params/BasicHttpParams
    //   3: dup
    //   4: invokespecial 73	org/apache/http/params/BasicHttpParams:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: ldc_w 358
    //   12: getstatic 364	org/apache/http/HttpVersion:HTTP_1_1	Lorg/apache/http/HttpVersion;
    //   15: invokeinterface 370 3 0
    //   20: pop
    //   21: aload_2
    //   22: sipush 5000
    //   25: invokestatic 79	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   28: aload_2
    //   29: sipush 5000
    //   32: invokestatic 82	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   35: new 84	org/apache/http/impl/client/DefaultHttpClient
    //   38: dup
    //   39: aload_2
    //   40: invokespecial 87	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   43: astore 4
    //   45: new 92	org/apache/http/client/methods/HttpPost
    //   48: dup
    //   49: getstatic 53	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:LIST_ORDER_URL	Ljava/lang/String;
    //   52: invokespecial 95	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   55: astore 5
    //   57: ldc 19
    //   59: new 23	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   66: ldc_w 372
    //   69: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: getstatic 53	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:LIST_ORDER_URL	Ljava/lang/String;
    //   75: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   84: pop
    //   85: new 89	java/util/ArrayList
    //   88: dup
    //   89: invokespecial 90	java/util/ArrayList:<init>	()V
    //   92: astore 7
    //   94: aload 7
    //   96: new 97	org/apache/http/message/BasicNameValuePair
    //   99: dup
    //   100: ldc 112
    //   102: aload_1
    //   103: invokevirtual 225	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getAppid	()Ljava/lang/String;
    //   106: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   109: invokeinterface 108 2 0
    //   114: pop
    //   115: aload 7
    //   117: new 97	org/apache/http/message/BasicNameValuePair
    //   120: dup
    //   121: ldc 227
    //   123: aload_1
    //   124: invokevirtual 230	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getModuleid	()Ljava/lang/String;
    //   127: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: invokeinterface 108 2 0
    //   135: pop
    //   136: aload 7
    //   138: new 97	org/apache/http/message/BasicNameValuePair
    //   141: dup
    //   142: ldc 217
    //   144: aload_0
    //   145: invokevirtual 220	com/appbuilder/sdk/android/authorization/entities/User:getAccountId	()Ljava/lang/String;
    //   148: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: invokeinterface 108 2 0
    //   156: pop
    //   157: aload 7
    //   159: new 97	org/apache/http/message/BasicNameValuePair
    //   162: dup
    //   163: ldc 112
    //   165: getstatic 115	com/appbuilder/sdk/android/Statics:appId	Ljava/lang/String;
    //   168: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   171: invokeinterface 108 2 0
    //   176: pop
    //   177: aload 7
    //   179: new 97	org/apache/http/message/BasicNameValuePair
    //   182: dup
    //   183: ldc 117
    //   185: getstatic 120	com/appbuilder/sdk/android/Statics:appToken	Ljava/lang/String;
    //   188: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   191: invokeinterface 108 2 0
    //   196: pop
    //   197: aload_0
    //   198: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   201: getstatic 184	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:FACEBOOK	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   204: if_acmpne +92 -> 296
    //   207: ldc 186
    //   209: astore 15
    //   211: aload 7
    //   213: new 97	org/apache/http/message/BasicNameValuePair
    //   216: dup
    //   217: ldc 215
    //   219: aload 15
    //   221: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   224: invokeinterface 108 2 0
    //   229: pop
    //   230: aload 5
    //   232: new 122	org/apache/http/client/entity/UrlEncodedFormEntity
    //   235: dup
    //   236: aload 7
    //   238: invokespecial 125	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;)V
    //   241: invokevirtual 129	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   244: new 131	org/apache/http/impl/client/BasicResponseHandler
    //   247: dup
    //   248: invokespecial 132	org/apache/http/impl/client/BasicResponseHandler:<init>	()V
    //   251: astore 19
    //   253: aload 4
    //   255: aload 5
    //   257: aload 19
    //   259: invokeinterface 138 3 0
    //   264: checkcast 140	java/lang/String
    //   267: astore 26
    //   269: aload 26
    //   271: invokestatic 318	com/ibuildapp/romanblack/TableReservationPlugin/JSONParser:parseQueryError	(Ljava/lang/String;)Ljava/lang/String;
    //   274: astore 27
    //   276: aload 27
    //   278: ifnull +15 -> 293
    //   281: aload 27
    //   283: invokevirtual 321	java/lang/String:length	()I
    //   286: istore 28
    //   288: iload 28
    //   290: ifne +85 -> 375
    //   293: aload 26
    //   295: areturn
    //   296: aload_0
    //   297: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   300: getstatic 324	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:TWITTER	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   303: if_acmpne +11 -> 314
    //   306: ldc_w 326
    //   309: astore 15
    //   311: goto -100 -> 211
    //   314: aload_0
    //   315: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   318: getstatic 332	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:IBUILDAPP	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   321: if_acmpne +11 -> 332
    //   324: ldc_w 334
    //   327: astore 15
    //   329: goto -118 -> 211
    //   332: aload_0
    //   333: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   336: astore 13
    //   338: getstatic 337	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:GUEST	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   341: astore 14
    //   343: aconst_null
    //   344: astore 15
    //   346: aload 13
    //   348: aload 14
    //   350: if_acmpne -139 -> 211
    //   353: ldc_w 339
    //   356: astore 15
    //   358: goto -147 -> 211
    //   361: astore 17
    //   363: ldc 142
    //   365: ldc 142
    //   367: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   370: pop
    //   371: ldc_w 374
    //   374: areturn
    //   375: ldc_w 374
    //   378: areturn
    //   379: astore 24
    //   381: ldc_w 375
    //   384: ldc 142
    //   386: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   389: pop
    //   390: ldc_w 374
    //   393: areturn
    //   394: astore 22
    //   396: ldc_w 375
    //   399: ldc 142
    //   401: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   404: pop
    //   405: ldc_w 374
    //   408: areturn
    //   409: astore 20
    //   411: ldc_w 375
    //   414: ldc 142
    //   416: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   419: pop
    //   420: ldc_w 374
    //   423: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   230	244	361	java/io/UnsupportedEncodingException
    //   253	276	379	org/apache/http/conn/ConnectTimeoutException
    //   281	288	379	org/apache/http/conn/ConnectTimeoutException
    //   253	276	394	org/apache/http/client/ClientProtocolException
    //   281	288	394	org/apache/http/client/ClientProtocolException
    //   253	276	409	java/io/IOException
    //   281	288	409	java/io/IOException
  }

  // ERROR //
  public static String sendMail(int paramInt, com.appbuilder.sdk.android.authorization.entities.User paramUser, com.ibuildapp.romanblack.TableReservationPlugin.TableReservationInfo paramTableReservationInfo, String paramString, android.os.Handler paramHandler)
  {
    // Byte code:
    //   0: new 72	org/apache/http/params/BasicHttpParams
    //   3: dup
    //   4: invokespecial 73	org/apache/http/params/BasicHttpParams:<init>	()V
    //   7: astore 5
    //   9: aload 5
    //   11: sipush 15000
    //   14: invokestatic 79	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   17: aload 5
    //   19: sipush 15000
    //   22: invokestatic 82	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   25: new 84	org/apache/http/impl/client/DefaultHttpClient
    //   28: dup
    //   29: aload 5
    //   31: invokespecial 87	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   34: astore 6
    //   36: new 92	org/apache/http/client/methods/HttpPost
    //   39: dup
    //   40: getstatic 57	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:MAIL_ORDER_URL	Ljava/lang/String;
    //   43: invokespecial 95	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   46: astore 7
    //   48: ldc 19
    //   50: new 23	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   57: ldc_w 379
    //   60: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: getstatic 57	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:MAIL_ORDER_URL	Ljava/lang/String;
    //   66: invokevirtual 32	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   75: pop
    //   76: new 89	java/util/ArrayList
    //   79: dup
    //   80: invokespecial 90	java/util/ArrayList:<init>	()V
    //   83: astore 9
    //   85: aload 9
    //   87: new 97	org/apache/http/message/BasicNameValuePair
    //   90: dup
    //   91: ldc_w 381
    //   94: aload_3
    //   95: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: invokeinterface 108 2 0
    //   103: pop
    //   104: aload 9
    //   106: new 97	org/apache/http/message/BasicNameValuePair
    //   109: dup
    //   110: ldc_w 383
    //   113: aload_2
    //   114: invokevirtual 386	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantName	()Ljava/lang/String;
    //   117: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: invokeinterface 108 2 0
    //   125: pop
    //   126: aload 9
    //   128: new 97	org/apache/http/message/BasicNameValuePair
    //   131: dup
    //   132: ldc_w 388
    //   135: aload_2
    //   136: invokevirtual 391	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantadress	()Ljava/lang/String;
    //   139: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   142: invokeinterface 108 2 0
    //   147: pop
    //   148: aload 9
    //   150: new 97	org/apache/http/message/BasicNameValuePair
    //   153: dup
    //   154: ldc_w 393
    //   157: aload_2
    //   158: invokevirtual 396	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantphone	()Ljava/lang/String;
    //   161: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   164: invokeinterface 108 2 0
    //   169: pop
    //   170: aload_1
    //   171: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   174: getstatic 184	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:FACEBOOK	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   177: if_acmpne +383 -> 560
    //   180: ldc 186
    //   182: astore 16
    //   184: aload 9
    //   186: new 97	org/apache/http/message/BasicNameValuePair
    //   189: dup
    //   190: ldc 215
    //   192: aload 16
    //   194: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   197: invokeinterface 108 2 0
    //   202: pop
    //   203: aload 9
    //   205: new 97	org/apache/http/message/BasicNameValuePair
    //   208: dup
    //   209: ldc 217
    //   211: aload_1
    //   212: invokevirtual 220	com/appbuilder/sdk/android/authorization/entities/User:getAccountId	()Ljava/lang/String;
    //   215: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   218: invokeinterface 108 2 0
    //   223: pop
    //   224: aload 9
    //   226: new 97	org/apache/http/message/BasicNameValuePair
    //   229: dup
    //   230: ldc_w 398
    //   233: aload_1
    //   234: invokevirtual 329	com/appbuilder/sdk/android/authorization/entities/User:getUserName	()Ljava/lang/String;
    //   237: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   240: invokeinterface 108 2 0
    //   245: pop
    //   246: aload 9
    //   248: new 97	org/apache/http/message/BasicNameValuePair
    //   251: dup
    //   252: ldc_w 400
    //   255: aload_1
    //   256: invokevirtual 193	com/appbuilder/sdk/android/authorization/entities/User:getUserFirstName	()Ljava/lang/String;
    //   259: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   262: invokeinterface 108 2 0
    //   267: pop
    //   268: aload 9
    //   270: new 97	org/apache/http/message/BasicNameValuePair
    //   273: dup
    //   274: ldc_w 402
    //   277: aload_1
    //   278: invokevirtual 198	com/appbuilder/sdk/android/authorization/entities/User:getUserLastName	()Ljava/lang/String;
    //   281: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   284: invokeinterface 108 2 0
    //   289: pop
    //   290: aload_2
    //   291: invokevirtual 243	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderDate	()Ljava/util/Date;
    //   294: astore 22
    //   296: aload 22
    //   298: aload_2
    //   299: invokevirtual 247	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
    //   302: getfield 252	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:houres	I
    //   305: invokevirtual 258	java/util/Date:setHours	(I)V
    //   308: aload 22
    //   310: aload_2
    //   311: invokevirtual 247	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getOrderTime	()Lcom/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes;
    //   314: getfield 261	com/ibuildapp/romanblack/TableReservationPlugin/entity/HouresMinutes:minutes	I
    //   317: invokevirtual 264	java/util/Date:setMinutes	(I)V
    //   320: aload 9
    //   322: new 97	org/apache/http/message/BasicNameValuePair
    //   325: dup
    //   326: ldc_w 273
    //   329: invokestatic 271	com/ibuildapp/romanblack/TableReservationPlugin/utils/TableReservationHTTP:timeZoneToString	()Ljava/lang/String;
    //   332: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   335: invokeinterface 108 2 0
    //   340: pop
    //   341: aload 9
    //   343: new 97	org/apache/http/message/BasicNameValuePair
    //   346: dup
    //   347: ldc_w 275
    //   350: aload 22
    //   352: invokevirtual 279	java/util/Date:getTime	()J
    //   355: ldc2_w 280
    //   358: ldiv
    //   359: invokestatic 286	java/lang/Long:toString	(J)Ljava/lang/String;
    //   362: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   365: invokeinterface 108 2 0
    //   370: pop
    //   371: aload 9
    //   373: new 97	org/apache/http/message/BasicNameValuePair
    //   376: dup
    //   377: ldc_w 288
    //   380: aload_2
    //   381: invokevirtual 291	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getPersonsAmount	()I
    //   384: invokestatic 296	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   387: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   390: invokeinterface 108 2 0
    //   395: pop
    //   396: aload 9
    //   398: new 97	org/apache/http/message/BasicNameValuePair
    //   401: dup
    //   402: ldc_w 404
    //   405: aload_1
    //   406: invokevirtual 407	com/appbuilder/sdk/android/authorization/entities/User:getUserEmail	()Ljava/lang/String;
    //   409: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   412: invokeinterface 108 2 0
    //   417: pop
    //   418: aload 9
    //   420: new 97	org/apache/http/message/BasicNameValuePair
    //   423: dup
    //   424: ldc_w 409
    //   427: aload_2
    //   428: invokevirtual 412	com/ibuildapp/romanblack/TableReservationPlugin/TableReservationInfo:getRestaurantmail	()Ljava/lang/String;
    //   431: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   434: invokeinterface 108 2 0
    //   439: pop
    //   440: aload 9
    //   442: new 97	org/apache/http/message/BasicNameValuePair
    //   445: dup
    //   446: ldc_w 414
    //   449: iload_0
    //   450: invokestatic 296	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   453: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   456: invokeinterface 108 2 0
    //   461: pop
    //   462: aload 9
    //   464: new 97	org/apache/http/message/BasicNameValuePair
    //   467: dup
    //   468: ldc 112
    //   470: getstatic 115	com/appbuilder/sdk/android/Statics:appId	Ljava/lang/String;
    //   473: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   476: invokeinterface 108 2 0
    //   481: pop
    //   482: aload 9
    //   484: new 97	org/apache/http/message/BasicNameValuePair
    //   487: dup
    //   488: ldc 117
    //   490: getstatic 120	com/appbuilder/sdk/android/Statics:appToken	Ljava/lang/String;
    //   493: invokespecial 102	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   496: invokeinterface 108 2 0
    //   501: pop
    //   502: aload 7
    //   504: new 122	org/apache/http/client/entity/UrlEncodedFormEntity
    //   507: dup
    //   508: aload 9
    //   510: invokespecial 125	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;)V
    //   513: invokevirtual 129	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   516: new 131	org/apache/http/impl/client/BasicResponseHandler
    //   519: dup
    //   520: invokespecial 132	org/apache/http/impl/client/BasicResponseHandler:<init>	()V
    //   523: astore 34
    //   525: aload 6
    //   527: aload 7
    //   529: aload 34
    //   531: invokeinterface 138 3 0
    //   536: checkcast 140	java/lang/String
    //   539: astore 43
    //   541: ldc_w 415
    //   544: ldc 142
    //   546: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   549: pop
    //   550: aload 43
    //   552: invokestatic 318	com/ibuildapp/romanblack/TableReservationPlugin/JSONParser:parseQueryError	(Ljava/lang/String;)Ljava/lang/String;
    //   555: astore 45
    //   557: aload 45
    //   559: areturn
    //   560: aload_1
    //   561: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   564: getstatic 324	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:TWITTER	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   567: if_acmpne +11 -> 578
    //   570: ldc_w 326
    //   573: astore 16
    //   575: goto -391 -> 184
    //   578: aload_1
    //   579: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   582: getstatic 332	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:IBUILDAPP	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   585: if_acmpne +11 -> 596
    //   588: ldc_w 334
    //   591: astore 16
    //   593: goto -409 -> 184
    //   596: aload_1
    //   597: invokevirtual 178	com/appbuilder/sdk/android/authorization/entities/User:getAccountType	()Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   600: astore 14
    //   602: getstatic 337	com/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES:GUEST	Lcom/appbuilder/sdk/android/authorization/entities/User$ACCOUNT_TYPES;
    //   605: astore 15
    //   607: aconst_null
    //   608: astore 16
    //   610: aload 14
    //   612: aload 15
    //   614: if_acmpne -430 -> 184
    //   617: ldc_w 339
    //   620: astore 16
    //   622: goto -438 -> 184
    //   625: astore 31
    //   627: ldc 142
    //   629: ldc 142
    //   631: invokestatic 169	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   634: pop
    //   635: aload 4
    //   637: iconst_3
    //   638: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   641: pop
    //   642: ldc_w 374
    //   645: areturn
    //   646: astore 41
    //   648: aload 4
    //   650: iconst_3
    //   651: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   654: pop
    //   655: ldc_w 374
    //   658: areturn
    //   659: astore 38
    //   661: ldc_w 415
    //   664: ldc 142
    //   666: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   669: pop
    //   670: aload 4
    //   672: iconst_3
    //   673: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   676: pop
    //   677: ldc_w 374
    //   680: areturn
    //   681: astore 35
    //   683: ldc_w 415
    //   686: ldc 142
    //   688: invokestatic 148	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   691: pop
    //   692: aload 4
    //   694: iconst_3
    //   695: invokevirtual 347	android/os/Handler:sendEmptyMessage	(I)Z
    //   698: pop
    //   699: ldc_w 374
    //   702: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   502	516	625	java/io/UnsupportedEncodingException
    //   525	557	646	org/apache/http/conn/ConnectTimeoutException
    //   525	557	659	org/apache/http/client/ClientProtocolException
    //   525	557	681	java/io/IOException
  }

  public static String timeZoneToString()
  {
    int i = Calendar.getInstance().get(15) / 60000;
    if (i >= 0);
    for (String str1 = "+"; ; str1 = "-")
    {
      String str2 = Integer.toString(Math.abs(i) / 60);
      String str3 = Integer.toString(Math.abs(i) % 60);
      if (str2.length() == 1)
        str2 = "0" + str2;
      if (str3.length() == 1)
        str3 = "0" + str3;
      return str1 + str2 + str3;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.utils.TableReservationHTTP
 * JD-Core Version:    0.6.0
 */