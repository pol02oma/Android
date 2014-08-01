package com.appbuilder.u846253p1176378;

import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import com.appbuilder.sdk.android.AppAdvData;
import com.appbuilder.sdk.android.BarDesigner;
import com.appbuilder.sdk.android.LoginScreen;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.u846253p1176378.GPSNotification.GPSItem;
import com.appbuilder.u846253p1176378.GPSNotification.GPSItem.Show;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AppConfigureParser
{
  private AppConfigure appConfig = new AppConfigure();
  private String xml = "";
  private InputStream xmlStream;

  public AppConfigureParser(InputStream paramInputStream)
  {
    this.xmlStream = paramInputStream;
  }

  public AppConfigureParser(String paramString)
  {
    this.xml = paramString;
  }

  private boolean checkForSpec(String paramString)
  {
    if (paramString.contains("&amp;"));
    do
      return true;
    while ((paramString.contains("&apos;")) || (paramString.contains("&quot;")) || (paramString.contains("&lt;")) || (paramString.contains("&gt;")));
    return false;
  }

  private static String getCharacterDataFromElement(Element paramElement)
  {
    Node localNode = paramElement.getFirstChild();
    if ((localNode instanceof CharacterData))
      return ((CharacterData)localNode).getData();
    return "";
  }

  private String removeSpec(String paramString)
  {
    return paramString.replace("&amp;", "&").replace("&apos;", "'").replace("&quot;", "").replace("&lt;", "<").replace("&gt;", ">").replace("&nbsp;", " ");
  }

  // ERROR //
  public AppConfigure parse()
    throws java.lang.RuntimeException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 19	com/appbuilder/u846253p1176378/AppConfigureParser:xml	Ljava/lang/String;
    //   4: invokevirtual 91	java/lang/String:length	()I
    //   7: ifne +15 -> 22
    //   10: aload_0
    //   11: getfield 26	com/appbuilder/u846253p1176378/AppConfigureParser:xmlStream	Ljava/io/InputStream;
    //   14: ifnonnull +8 -> 22
    //   17: aload_0
    //   18: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   21: areturn
    //   22: aload_0
    //   23: getfield 19	com/appbuilder/u846253p1176378/AppConfigureParser:xml	Ljava/lang/String;
    //   26: invokevirtual 91	java/lang/String:length	()I
    //   29: istore_2
    //   30: aconst_null
    //   31: astore_3
    //   32: iload_2
    //   33: ifne +22 -> 55
    //   36: new 93	java/io/CharArrayReader
    //   39: dup
    //   40: aload_0
    //   41: getfield 19	com/appbuilder/u846253p1176378/AppConfigureParser:xml	Ljava/lang/String;
    //   44: invokevirtual 97	java/lang/String:toCharArray	()[C
    //   47: invokespecial 100	java/io/CharArrayReader:<init>	([C)V
    //   50: astore 4
    //   52: aload 4
    //   54: astore_3
    //   55: invokestatic 106	javax/xml/parsers/DocumentBuilderFactory:newInstance	()Ljavax/xml/parsers/DocumentBuilderFactory;
    //   58: astore 5
    //   60: aload 5
    //   62: iconst_1
    //   63: invokevirtual 110	javax/xml/parsers/DocumentBuilderFactory:setCoalescing	(Z)V
    //   66: aload 5
    //   68: invokevirtual 114	javax/xml/parsers/DocumentBuilderFactory:newDocumentBuilder	()Ljavax/xml/parsers/DocumentBuilder;
    //   71: astore 6
    //   73: aload_0
    //   74: getfield 19	com/appbuilder/u846253p1176378/AppConfigureParser:xml	Ljava/lang/String;
    //   77: invokevirtual 91	java/lang/String:length	()I
    //   80: ifeq +562 -> 642
    //   83: new 116	org/xml/sax/InputSource
    //   86: dup
    //   87: aload_3
    //   88: invokespecial 119	org/xml/sax/InputSource:<init>	(Ljava/io/Reader;)V
    //   91: astore 7
    //   93: aload 6
    //   95: aload 7
    //   97: invokevirtual 124	javax/xml/parsers/DocumentBuilder:parse	(Lorg/xml/sax/InputSource;)Lorg/w3c/dom/Document;
    //   100: astore 8
    //   102: aload 8
    //   104: ldc 126
    //   106: invokeinterface 132 2 0
    //   111: iconst_0
    //   112: invokeinterface 138 2 0
    //   117: checkcast 51	org/w3c/dom/Element
    //   120: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   123: astore 9
    //   125: aload_0
    //   126: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   129: aload 9
    //   131: invokevirtual 143	com/appbuilder/u846253p1176378/AppConfigure:setAppName	(Ljava/lang/String;)V
    //   134: ldc 144
    //   136: new 146	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   143: ldc 149
    //   145: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload 9
    //   150: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   156: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   159: pop
    //   160: aload 8
    //   162: ldc 164
    //   164: invokeinterface 132 2 0
    //   169: iconst_0
    //   170: invokeinterface 138 2 0
    //   175: invokeinterface 170 1 0
    //   180: iconst_0
    //   181: invokeinterface 138 2 0
    //   186: invokeinterface 173 1 0
    //   191: astore 11
    //   193: aload_0
    //   194: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   197: aload 11
    //   199: invokevirtual 176	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundColor	(Ljava/lang/String;)V
    //   202: ldc 144
    //   204: new 146	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   211: ldc 178
    //   213: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: aload 11
    //   218: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   227: pop
    //   228: aload 8
    //   230: ldc 180
    //   232: invokeinterface 132 2 0
    //   237: iconst_0
    //   238: invokeinterface 138 2 0
    //   243: checkcast 51	org/w3c/dom/Element
    //   246: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   249: astore 13
    //   251: aload_0
    //   252: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   255: aload 13
    //   257: invokevirtual 183	com/appbuilder/u846253p1176378/AppConfigure:setBackgroundImageUrl	(Ljava/lang/String;)V
    //   260: ldc 144
    //   262: new 146	java/lang/StringBuilder
    //   265: dup
    //   266: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   269: ldc 185
    //   271: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: aload 13
    //   276: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   285: pop
    //   286: aload 8
    //   288: ldc 187
    //   290: invokeinterface 132 2 0
    //   295: iconst_0
    //   296: invokeinterface 138 2 0
    //   301: checkcast 51	org/w3c/dom/Element
    //   304: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   307: astore 209
    //   309: aload_0
    //   310: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   313: aload 209
    //   315: invokevirtual 190	com/appbuilder/u846253p1176378/AppConfigure:setmBackgorundImageData	(Ljava/lang/String;)V
    //   318: ldc 144
    //   320: new 146	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   327: ldc 192
    //   329: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: aload 209
    //   334: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   340: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   343: pop
    //   344: aload 8
    //   346: ldc 194
    //   348: invokeinterface 132 2 0
    //   353: iconst_0
    //   354: invokeinterface 138 2 0
    //   359: invokeinterface 170 1 0
    //   364: iconst_0
    //   365: invokeinterface 138 2 0
    //   370: invokeinterface 173 1 0
    //   375: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   378: istore 16
    //   380: aload_0
    //   381: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   384: astore 17
    //   386: iload 16
    //   388: ifle +5273 -> 5661
    //   391: iconst_1
    //   392: istore 18
    //   394: aload 17
    //   396: iload 18
    //   398: invokevirtual 203	com/appbuilder/u846253p1176378/AppConfigure:setShowLink	(Z)V
    //   401: ldc 144
    //   403: new 146	java/lang/StringBuilder
    //   406: dup
    //   407: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   410: ldc 205
    //   412: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: iload 16
    //   417: invokestatic 209	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   420: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   426: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   429: pop
    //   430: aload 8
    //   432: ldc 211
    //   434: invokeinterface 132 2 0
    //   439: iconst_0
    //   440: invokeinterface 138 2 0
    //   445: invokeinterface 170 1 0
    //   450: iconst_0
    //   451: invokeinterface 138 2 0
    //   456: invokeinterface 173 1 0
    //   461: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   464: istore 207
    //   466: aload_0
    //   467: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   470: iload 207
    //   472: invokevirtual 215	com/appbuilder/u846253p1176378/AppConfigure:setDateFormat	(I)V
    //   475: ldc 144
    //   477: new 146	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   484: ldc 217
    //   486: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: iload 207
    //   491: invokestatic 209	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   494: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   500: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   503: pop
    //   504: aload 8
    //   506: ldc 219
    //   508: invokeinterface 132 2 0
    //   513: iconst_0
    //   514: invokeinterface 138 2 0
    //   519: invokeinterface 170 1 0
    //   524: iconst_0
    //   525: invokeinterface 138 2 0
    //   530: invokeinterface 173 1 0
    //   535: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   538: istore 205
    //   540: aload_0
    //   541: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   544: iload 205
    //   546: invokevirtual 222	com/appbuilder/u846253p1176378/AppConfigure:setShowMenu	(I)V
    //   549: ldc 144
    //   551: new 146	java/lang/StringBuilder
    //   554: dup
    //   555: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   558: ldc 224
    //   560: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: iload 205
    //   565: invokestatic 209	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   568: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   574: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   577: pop
    //   578: aload 8
    //   580: ldc 226
    //   582: invokeinterface 132 2 0
    //   587: astore 24
    //   589: aload 24
    //   591: invokeinterface 229 1 0
    //   596: istore 25
    //   598: iconst_0
    //   599: istore 26
    //   601: iload 26
    //   603: iload 25
    //   605: if_icmpge +545 -> 1150
    //   608: new 231	com/appbuilder/u846253p1176378/WidgetUIImage
    //   611: dup
    //   612: invokespecial 232	com/appbuilder/u846253p1176378/WidgetUIImage:<init>	()V
    //   615: astore 27
    //   617: aload 24
    //   619: iload 26
    //   621: invokeinterface 138 2 0
    //   626: astore 28
    //   628: aload 28
    //   630: invokeinterface 236 1 0
    //   635: iconst_1
    //   636: if_icmpeq +75 -> 711
    //   639: goto +5016 -> 5655
    //   642: aload 6
    //   644: new 116	org/xml/sax/InputSource
    //   647: dup
    //   648: aload_0
    //   649: getfield 26	com/appbuilder/u846253p1176378/AppConfigureParser:xmlStream	Ljava/io/InputStream;
    //   652: invokespecial 238	org/xml/sax/InputSource:<init>	(Ljava/io/InputStream;)V
    //   655: invokevirtual 124	javax/xml/parsers/DocumentBuilder:parse	(Lorg/xml/sax/InputSource;)Lorg/w3c/dom/Document;
    //   658: astore 8
    //   660: goto -558 -> 102
    //   663: astore 20
    //   665: ldc 144
    //   667: ldc 240
    //   669: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   672: pop
    //   673: aload_0
    //   674: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   677: iconst_0
    //   678: invokevirtual 215	com/appbuilder/u846253p1176378/AppConfigure:setDateFormat	(I)V
    //   681: goto -177 -> 504
    //   684: astore_1
    //   685: aload_0
    //   686: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   689: areturn
    //   690: astore 22
    //   692: ldc 144
    //   694: ldc 242
    //   696: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   699: pop
    //   700: aload_0
    //   701: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   704: iconst_0
    //   705: invokevirtual 222	com/appbuilder/u846253p1176378/AppConfigure:setShowMenu	(I)V
    //   708: goto -130 -> 578
    //   711: aload 28
    //   713: checkcast 51	org/w3c/dom/Element
    //   716: ldc 244
    //   718: invokeinterface 245 2 0
    //   723: iconst_0
    //   724: invokeinterface 138 2 0
    //   729: invokeinterface 170 1 0
    //   734: iconst_0
    //   735: invokeinterface 138 2 0
    //   740: invokeinterface 173 1 0
    //   745: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   748: istore 29
    //   750: ldc 144
    //   752: new 146	java/lang/StringBuilder
    //   755: dup
    //   756: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   759: ldc 247
    //   761: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   764: iload 29
    //   766: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   769: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   772: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   775: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   778: pop
    //   779: aload 27
    //   781: iload 29
    //   783: invokevirtual 252	com/appbuilder/u846253p1176378/WidgetUIImage:setLeft	(I)V
    //   786: aload 28
    //   788: checkcast 51	org/w3c/dom/Element
    //   791: ldc 254
    //   793: invokeinterface 245 2 0
    //   798: iconst_0
    //   799: invokeinterface 138 2 0
    //   804: invokeinterface 170 1 0
    //   809: iconst_0
    //   810: invokeinterface 138 2 0
    //   815: invokeinterface 173 1 0
    //   820: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   823: istore 31
    //   825: ldc 144
    //   827: new 146	java/lang/StringBuilder
    //   830: dup
    //   831: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   834: ldc_w 256
    //   837: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   840: iload 31
    //   842: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   845: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   848: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   851: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   854: pop
    //   855: aload 27
    //   857: iload 31
    //   859: invokevirtual 259	com/appbuilder/u846253p1176378/WidgetUIImage:setTop	(I)V
    //   862: aload 28
    //   864: checkcast 51	org/w3c/dom/Element
    //   867: ldc_w 261
    //   870: invokeinterface 245 2 0
    //   875: iconst_0
    //   876: invokeinterface 138 2 0
    //   881: invokeinterface 170 1 0
    //   886: iconst_0
    //   887: invokeinterface 138 2 0
    //   892: invokeinterface 173 1 0
    //   897: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   900: istore 33
    //   902: ldc 144
    //   904: new 146	java/lang/StringBuilder
    //   907: dup
    //   908: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   911: ldc_w 263
    //   914: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   917: iload 33
    //   919: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   922: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   928: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   931: pop
    //   932: aload 27
    //   934: iload 33
    //   936: invokevirtual 266	com/appbuilder/u846253p1176378/WidgetUIImage:setWidth	(I)V
    //   939: aload 28
    //   941: checkcast 51	org/w3c/dom/Element
    //   944: ldc_w 268
    //   947: invokeinterface 245 2 0
    //   952: iconst_0
    //   953: invokeinterface 138 2 0
    //   958: invokeinterface 170 1 0
    //   963: iconst_0
    //   964: invokeinterface 138 2 0
    //   969: invokeinterface 173 1 0
    //   974: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   977: istore 35
    //   979: ldc 144
    //   981: new 146	java/lang/StringBuilder
    //   984: dup
    //   985: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   988: ldc_w 270
    //   991: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   994: iload 35
    //   996: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   999: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1005: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1008: pop
    //   1009: aload 27
    //   1011: iload 35
    //   1013: invokevirtual 273	com/appbuilder/u846253p1176378/WidgetUIImage:setHeight	(I)V
    //   1016: aload 28
    //   1018: checkcast 51	org/w3c/dom/Element
    //   1021: ldc_w 275
    //   1024: invokeinterface 245 2 0
    //   1029: iconst_0
    //   1030: invokeinterface 138 2 0
    //   1035: checkcast 51	org/w3c/dom/Element
    //   1038: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   1041: astore 37
    //   1043: aload 27
    //   1045: aload 37
    //   1047: invokevirtual 278	com/appbuilder/u846253p1176378/WidgetUIImage:setSourceUrl	(Ljava/lang/String;)V
    //   1050: ldc 144
    //   1052: new 146	java/lang/StringBuilder
    //   1055: dup
    //   1056: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   1059: ldc_w 280
    //   1062: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1065: aload 37
    //   1067: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1070: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1073: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1076: pop
    //   1077: aload 28
    //   1079: checkcast 51	org/w3c/dom/Element
    //   1082: ldc_w 282
    //   1085: invokeinterface 245 2 0
    //   1090: iconst_0
    //   1091: invokeinterface 138 2 0
    //   1096: checkcast 51	org/w3c/dom/Element
    //   1099: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   1102: astore 40
    //   1104: aload 27
    //   1106: aload 40
    //   1108: invokevirtual 285	com/appbuilder/u846253p1176378/WidgetUIImage:setmImageData	(Ljava/lang/String;)V
    //   1111: ldc 144
    //   1113: new 146	java/lang/StringBuilder
    //   1116: dup
    //   1117: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   1120: ldc_w 287
    //   1123: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1126: aload 40
    //   1128: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1131: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1134: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1137: pop
    //   1138: aload_0
    //   1139: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1142: aload 27
    //   1144: invokevirtual 291	com/appbuilder/u846253p1176378/AppConfigure:addImage	(Lcom/appbuilder/u846253p1176378/WidgetUIImage;)V
    //   1147: goto +4508 -> 5655
    //   1150: aload 8
    //   1152: ldc_w 293
    //   1155: invokeinterface 132 2 0
    //   1160: astore 199
    //   1162: aload 199
    //   1164: ifnull +143 -> 1307
    //   1167: iconst_0
    //   1168: istore 200
    //   1170: aload 199
    //   1172: invokeinterface 229 1 0
    //   1177: istore 201
    //   1179: iload 200
    //   1181: iload 201
    //   1183: if_icmpge +124 -> 1307
    //   1186: aload 199
    //   1188: iload 200
    //   1190: invokeinterface 138 2 0
    //   1195: astore 202
    //   1197: aload 202
    //   1199: ifnull +4468 -> 5667
    //   1202: aload 202
    //   1204: invokeinterface 236 1 0
    //   1209: iconst_1
    //   1210: if_icmpeq +6 -> 1216
    //   1213: goto +4454 -> 5667
    //   1216: ldc_w 295
    //   1219: aload 202
    //   1221: invokeinterface 298 1 0
    //   1226: invokeinterface 301 1 0
    //   1231: invokevirtual 304	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1234: ifeq +4433 -> 5667
    //   1237: ldc 17
    //   1239: astore 203
    //   1241: aload 202
    //   1243: checkcast 51	org/w3c/dom/Element
    //   1246: ldc_w 306
    //   1249: invokeinterface 245 2 0
    //   1254: astore 204
    //   1256: aload 204
    //   1258: iconst_0
    //   1259: invokeinterface 138 2 0
    //   1264: ifnull +29 -> 1293
    //   1267: aload 204
    //   1269: iconst_0
    //   1270: invokeinterface 138 2 0
    //   1275: invokeinterface 170 1 0
    //   1280: iconst_0
    //   1281: invokeinterface 138 2 0
    //   1286: invokeinterface 173 1 0
    //   1291: astore 203
    //   1293: aload_0
    //   1294: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1297: aload 203
    //   1299: invokevirtual 309	com/appbuilder/u846253p1176378/AppConfigure:setPushNotificationAccount	(Ljava/lang/String;)V
    //   1302: goto +4365 -> 5667
    //   1305: astore 42
    //   1307: aload 8
    //   1309: ldc_w 311
    //   1312: invokeinterface 132 2 0
    //   1317: astore 43
    //   1319: aload 43
    //   1321: ifnull +511 -> 1832
    //   1324: iconst_0
    //   1325: istore 184
    //   1327: aload 43
    //   1329: invokeinterface 229 1 0
    //   1334: istore 185
    //   1336: iload 184
    //   1338: iload 185
    //   1340: if_icmpge +492 -> 1832
    //   1343: aload 43
    //   1345: iload 184
    //   1347: invokeinterface 138 2 0
    //   1352: astore 187
    //   1354: aload 187
    //   1356: ifnull +4317 -> 5673
    //   1359: aload 187
    //   1361: invokeinterface 236 1 0
    //   1366: iconst_1
    //   1367: if_icmpeq +6 -> 1373
    //   1370: goto +4303 -> 5673
    //   1373: ldc_w 295
    //   1376: aload 187
    //   1378: invokeinterface 298 1 0
    //   1383: invokeinterface 301 1 0
    //   1388: invokevirtual 304	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1391: ifeq +4282 -> 5673
    //   1394: ldc 17
    //   1396: astore 188
    //   1398: new 313	com/appbuilder/u846253p1176378/GPSNotification/GPSItem
    //   1401: dup
    //   1402: invokespecial 314	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:<init>	()V
    //   1405: astore 189
    //   1407: aload 187
    //   1409: checkcast 51	org/w3c/dom/Element
    //   1412: ldc_w 316
    //   1415: invokeinterface 245 2 0
    //   1420: astore 190
    //   1422: aload 190
    //   1424: iconst_0
    //   1425: invokeinterface 138 2 0
    //   1430: ifnull +29 -> 1459
    //   1433: aload 190
    //   1435: iconst_0
    //   1436: invokeinterface 138 2 0
    //   1441: invokeinterface 170 1 0
    //   1446: iconst_0
    //   1447: invokeinterface 138 2 0
    //   1452: invokeinterface 173 1 0
    //   1457: astore 188
    //   1459: new 318	java/lang/Double
    //   1462: dup
    //   1463: aload 188
    //   1465: invokespecial 320	java/lang/Double:<init>	(Ljava/lang/String;)V
    //   1468: astore 191
    //   1470: aload 189
    //   1472: aload 191
    //   1474: invokevirtual 324	java/lang/Double:doubleValue	()D
    //   1477: invokevirtual 328	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:setLatitude	(D)V
    //   1480: aload 187
    //   1482: checkcast 51	org/w3c/dom/Element
    //   1485: ldc_w 330
    //   1488: invokeinterface 245 2 0
    //   1493: astore 192
    //   1495: aload 192
    //   1497: iconst_0
    //   1498: invokeinterface 138 2 0
    //   1503: ifnull +29 -> 1532
    //   1506: aload 192
    //   1508: iconst_0
    //   1509: invokeinterface 138 2 0
    //   1514: invokeinterface 170 1 0
    //   1519: iconst_0
    //   1520: invokeinterface 138 2 0
    //   1525: invokeinterface 173 1 0
    //   1530: astore 188
    //   1532: new 318	java/lang/Double
    //   1535: dup
    //   1536: aload 188
    //   1538: invokespecial 320	java/lang/Double:<init>	(Ljava/lang/String;)V
    //   1541: astore 193
    //   1543: aload 189
    //   1545: aload 193
    //   1547: invokevirtual 324	java/lang/Double:doubleValue	()D
    //   1550: invokevirtual 333	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:setLongitude	(D)V
    //   1553: aload 187
    //   1555: checkcast 51	org/w3c/dom/Element
    //   1558: ldc_w 335
    //   1561: invokeinterface 245 2 0
    //   1566: astore 194
    //   1568: aload 194
    //   1570: iconst_0
    //   1571: invokeinterface 138 2 0
    //   1576: ifnull +29 -> 1605
    //   1579: aload 194
    //   1581: iconst_0
    //   1582: invokeinterface 138 2 0
    //   1587: invokeinterface 170 1 0
    //   1592: iconst_0
    //   1593: invokeinterface 138 2 0
    //   1598: invokeinterface 173 1 0
    //   1603: astore 188
    //   1605: aload 189
    //   1607: aload 188
    //   1609: invokevirtual 338	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:setTitle	(Ljava/lang/String;)V
    //   1612: aload 187
    //   1614: checkcast 51	org/w3c/dom/Element
    //   1617: ldc_w 340
    //   1620: invokeinterface 245 2 0
    //   1625: astore 195
    //   1627: aload 195
    //   1629: iconst_0
    //   1630: invokeinterface 138 2 0
    //   1635: ifnull +29 -> 1664
    //   1638: aload 195
    //   1640: iconst_0
    //   1641: invokeinterface 138 2 0
    //   1646: invokeinterface 170 1 0
    //   1651: iconst_0
    //   1652: invokeinterface 138 2 0
    //   1657: invokeinterface 173 1 0
    //   1662: astore 188
    //   1664: aload 189
    //   1666: aload 188
    //   1668: invokevirtual 343	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:setDescription	(Ljava/lang/String;)V
    //   1671: aload 187
    //   1673: checkcast 51	org/w3c/dom/Element
    //   1676: ldc_w 345
    //   1679: invokeinterface 245 2 0
    //   1684: astore 196
    //   1686: aload 196
    //   1688: iconst_0
    //   1689: invokeinterface 138 2 0
    //   1694: ifnull +29 -> 1723
    //   1697: aload 196
    //   1699: iconst_0
    //   1700: invokeinterface 138 2 0
    //   1705: invokeinterface 170 1 0
    //   1710: iconst_0
    //   1711: invokeinterface 138 2 0
    //   1716: invokeinterface 173 1 0
    //   1721: astore 188
    //   1723: new 196	java/lang/Integer
    //   1726: dup
    //   1727: aload 188
    //   1729: invokespecial 346	java/lang/Integer:<init>	(Ljava/lang/String;)V
    //   1732: astore 197
    //   1734: aload 189
    //   1736: aload 197
    //   1738: invokevirtual 349	java/lang/Integer:intValue	()I
    //   1741: invokevirtual 352	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:setRadius	(I)V
    //   1744: aload 187
    //   1746: checkcast 51	org/w3c/dom/Element
    //   1749: ldc_w 354
    //   1752: invokeinterface 245 2 0
    //   1757: astore 198
    //   1759: aload 198
    //   1761: iconst_0
    //   1762: invokeinterface 138 2 0
    //   1767: ifnull +29 -> 1796
    //   1770: aload 198
    //   1772: iconst_0
    //   1773: invokeinterface 138 2 0
    //   1778: invokeinterface 170 1 0
    //   1783: iconst_0
    //   1784: invokeinterface 138 2 0
    //   1789: invokeinterface 173 1 0
    //   1794: astore 188
    //   1796: aload 188
    //   1798: ldc_w 356
    //   1801: invokevirtual 360	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1804: ifeq +11 -> 1815
    //   1807: aload 189
    //   1809: getstatic 366	com/appbuilder/u846253p1176378/GPSNotification/GPSItem$Show:PLURAL	Lcom/appbuilder/u846253p1176378/GPSNotification/GPSItem$Show;
    //   1812: invokevirtual 370	com/appbuilder/u846253p1176378/GPSNotification/GPSItem:setCountOfView	(Lcom/appbuilder/u846253p1176378/GPSNotification/GPSItem$Show;)V
    //   1815: aload_0
    //   1816: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   1819: aload 189
    //   1821: invokevirtual 374	com/appbuilder/u846253p1176378/AppConfigure:addGPSNotification	(Lcom/appbuilder/u846253p1176378/GPSNotification/GPSItem;)V
    //   1824: goto +3849 -> 5673
    //   1827: astore 186
    //   1829: goto +3844 -> 5673
    //   1832: new 376	com/appbuilder/sdk/android/AppAdvData
    //   1835: dup
    //   1836: invokespecial 377	com/appbuilder/sdk/android/AppAdvData:<init>	()V
    //   1839: astore 44
    //   1841: aload 8
    //   1843: ldc_w 379
    //   1846: invokeinterface 132 2 0
    //   1851: astore 45
    //   1853: aload 45
    //   1855: invokeinterface 229 1 0
    //   1860: istore 46
    //   1862: iconst_0
    //   1863: istore 47
    //   1865: iload 47
    //   1867: iload 46
    //   1869: if_icmpge +620 -> 2489
    //   1872: aload 45
    //   1874: iload 47
    //   1876: invokeinterface 138 2 0
    //   1881: astore 48
    //   1883: aload 48
    //   1885: invokeinterface 236 1 0
    //   1890: iconst_1
    //   1891: if_icmpeq +6 -> 1897
    //   1894: goto +3785 -> 5679
    //   1897: ldc_w 295
    //   1900: aload 48
    //   1902: invokeinterface 298 1 0
    //   1907: invokeinterface 301 1 0
    //   1912: invokevirtual 304	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1915: ifeq +3764 -> 5679
    //   1918: ldc 17
    //   1920: astore 49
    //   1922: aload 48
    //   1924: checkcast 51	org/w3c/dom/Element
    //   1927: ldc_w 381
    //   1930: invokeinterface 245 2 0
    //   1935: astore 50
    //   1937: aload 50
    //   1939: iconst_0
    //   1940: invokeinterface 138 2 0
    //   1945: ifnull +29 -> 1974
    //   1948: aload 50
    //   1950: iconst_0
    //   1951: invokeinterface 138 2 0
    //   1956: invokeinterface 170 1 0
    //   1961: iconst_0
    //   1962: invokeinterface 138 2 0
    //   1967: invokeinterface 173 1 0
    //   1972: astore 49
    //   1974: aload 44
    //   1976: invokevirtual 384	com/appbuilder/sdk/android/AppAdvData:getAdvType	()Ljava/lang/String;
    //   1979: invokevirtual 91	java/lang/String:length	()I
    //   1982: ifne +10 -> 1992
    //   1985: aload 44
    //   1987: aload 49
    //   1989: invokevirtual 387	com/appbuilder/sdk/android/AppAdvData:setAdvType	(Ljava/lang/String;)V
    //   1992: ldc 144
    //   1994: new 146	java/lang/StringBuilder
    //   1997: dup
    //   1998: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2001: ldc_w 389
    //   2004: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2007: aload 49
    //   2009: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2012: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2015: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2018: pop
    //   2019: ldc 17
    //   2021: astore 52
    //   2023: aload 48
    //   2025: checkcast 51	org/w3c/dom/Element
    //   2028: ldc_w 391
    //   2031: invokeinterface 245 2 0
    //   2036: astore 53
    //   2038: aload 53
    //   2040: iconst_0
    //   2041: invokeinterface 138 2 0
    //   2046: ifnull +29 -> 2075
    //   2049: aload 53
    //   2051: iconst_0
    //   2052: invokeinterface 138 2 0
    //   2057: invokeinterface 170 1 0
    //   2062: iconst_0
    //   2063: invokeinterface 138 2 0
    //   2068: invokeinterface 173 1 0
    //   2073: astore 52
    //   2075: aload 44
    //   2077: aload 52
    //   2079: invokevirtual 394	com/appbuilder/sdk/android/AppAdvData:setAdvRedirect	(Ljava/lang/String;)V
    //   2082: ldc 144
    //   2084: new 146	java/lang/StringBuilder
    //   2087: dup
    //   2088: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2091: ldc_w 396
    //   2094: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2097: aload 52
    //   2099: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2102: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2105: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2108: pop
    //   2109: ldc 17
    //   2111: astore 55
    //   2113: aload 48
    //   2115: checkcast 51	org/w3c/dom/Element
    //   2118: ldc_w 275
    //   2121: invokeinterface 245 2 0
    //   2126: astore 56
    //   2128: aload 56
    //   2130: iconst_0
    //   2131: invokeinterface 138 2 0
    //   2136: ifnull +37 -> 2173
    //   2139: aload 56
    //   2141: iconst_0
    //   2142: invokeinterface 138 2 0
    //   2147: invokeinterface 170 1 0
    //   2152: iconst_0
    //   2153: invokeinterface 138 2 0
    //   2158: invokeinterface 173 1 0
    //   2163: astore 55
    //   2165: aload 44
    //   2167: ldc_w 275
    //   2170: invokevirtual 387	com/appbuilder/sdk/android/AppAdvData:setAdvType	(Ljava/lang/String;)V
    //   2173: aload 44
    //   2175: aload 55
    //   2177: invokevirtual 399	com/appbuilder/sdk/android/AppAdvData:setAdvContent	(Ljava/lang/String;)V
    //   2180: ldc 144
    //   2182: new 146	java/lang/StringBuilder
    //   2185: dup
    //   2186: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2189: ldc_w 401
    //   2192: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2195: aload 55
    //   2197: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2200: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2203: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2206: pop
    //   2207: aload 48
    //   2209: checkcast 51	org/w3c/dom/Element
    //   2212: ldc_w 403
    //   2215: invokeinterface 245 2 0
    //   2220: astore 65
    //   2222: aload 65
    //   2224: iconst_0
    //   2225: invokeinterface 138 2 0
    //   2230: ifnull +29 -> 2259
    //   2233: aload 65
    //   2235: iconst_0
    //   2236: invokeinterface 138 2 0
    //   2241: invokeinterface 170 1 0
    //   2246: iconst_0
    //   2247: invokeinterface 138 2 0
    //   2252: invokeinterface 173 1 0
    //   2257: astore 52
    //   2259: aload 44
    //   2261: aload 52
    //   2263: invokevirtual 394	com/appbuilder/sdk/android/AppAdvData:setAdvRedirect	(Ljava/lang/String;)V
    //   2266: ldc 144
    //   2268: new 146	java/lang/StringBuilder
    //   2271: dup
    //   2272: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2275: ldc_w 396
    //   2278: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2281: aload 52
    //   2283: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2286: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2289: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2292: pop
    //   2293: aload 48
    //   2295: checkcast 51	org/w3c/dom/Element
    //   2298: ldc_w 405
    //   2301: invokeinterface 245 2 0
    //   2306: astore 61
    //   2308: aload 61
    //   2310: iconst_0
    //   2311: invokeinterface 138 2 0
    //   2316: ifnull +37 -> 2353
    //   2319: aload 61
    //   2321: iconst_0
    //   2322: invokeinterface 138 2 0
    //   2327: invokeinterface 170 1 0
    //   2332: iconst_0
    //   2333: invokeinterface 138 2 0
    //   2338: invokeinterface 173 1 0
    //   2343: astore 55
    //   2345: aload 44
    //   2347: ldc_w 405
    //   2350: invokevirtual 387	com/appbuilder/sdk/android/AppAdvData:setAdvType	(Ljava/lang/String;)V
    //   2353: aload 44
    //   2355: aload 55
    //   2357: invokevirtual 399	com/appbuilder/sdk/android/AppAdvData:setAdvContent	(Ljava/lang/String;)V
    //   2360: ldc 144
    //   2362: new 146	java/lang/StringBuilder
    //   2365: dup
    //   2366: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2369: ldc_w 407
    //   2372: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2375: aload 55
    //   2377: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2380: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2383: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2386: pop
    //   2387: aload 48
    //   2389: checkcast 51	org/w3c/dom/Element
    //   2392: ldc_w 409
    //   2395: invokeinterface 245 2 0
    //   2400: astore 63
    //   2402: aload 63
    //   2404: iconst_0
    //   2405: invokeinterface 138 2 0
    //   2410: ifnull +29 -> 2439
    //   2413: aload 63
    //   2415: iconst_0
    //   2416: invokeinterface 138 2 0
    //   2421: invokeinterface 170 1 0
    //   2426: iconst_0
    //   2427: invokeinterface 138 2 0
    //   2432: invokeinterface 173 1 0
    //   2437: astore 55
    //   2439: aload 44
    //   2441: aload 55
    //   2443: invokevirtual 399	com/appbuilder/sdk/android/AppAdvData:setAdvContent	(Ljava/lang/String;)V
    //   2446: ldc 144
    //   2448: new 146	java/lang/StringBuilder
    //   2451: dup
    //   2452: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2455: ldc_w 407
    //   2458: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2461: aload 55
    //   2463: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2466: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2469: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2472: pop
    //   2473: goto +3206 -> 5679
    //   2476: astore 58
    //   2478: ldc 17
    //   2480: ldc 17
    //   2482: invokestatic 412	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   2485: pop
    //   2486: goto -220 -> 2266
    //   2489: aload_0
    //   2490: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   2493: aload 44
    //   2495: invokevirtual 416	com/appbuilder/u846253p1176378/AppConfigure:setAppAdv	(Lcom/appbuilder/sdk/android/AppAdvData;)V
    //   2498: aload 8
    //   2500: ldc_w 418
    //   2503: invokeinterface 132 2 0
    //   2508: astore 66
    //   2510: aload 66
    //   2512: invokeinterface 229 1 0
    //   2517: istore 67
    //   2519: iconst_0
    //   2520: istore 68
    //   2522: iload 68
    //   2524: iload 67
    //   2526: if_icmpge +656 -> 3182
    //   2529: new 420	com/appbuilder/u846253p1176378/WidgetUILabel
    //   2532: dup
    //   2533: invokespecial 421	com/appbuilder/u846253p1176378/WidgetUILabel:<init>	()V
    //   2536: astore 69
    //   2538: aload 66
    //   2540: iload 68
    //   2542: invokeinterface 138 2 0
    //   2547: astore 70
    //   2549: aload 70
    //   2551: invokeinterface 236 1 0
    //   2556: iconst_1
    //   2557: if_icmpeq +6 -> 2563
    //   2560: goto +3125 -> 5685
    //   2563: ldc_w 295
    //   2566: aload 70
    //   2568: invokeinterface 298 1 0
    //   2573: invokeinterface 301 1 0
    //   2578: invokevirtual 304	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2581: ifeq +3104 -> 5685
    //   2584: aload 70
    //   2586: checkcast 51	org/w3c/dom/Element
    //   2589: ldc 244
    //   2591: invokeinterface 245 2 0
    //   2596: iconst_0
    //   2597: invokeinterface 138 2 0
    //   2602: invokeinterface 170 1 0
    //   2607: iconst_0
    //   2608: invokeinterface 138 2 0
    //   2613: invokeinterface 173 1 0
    //   2618: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2621: istore 71
    //   2623: ldc 144
    //   2625: new 146	java/lang/StringBuilder
    //   2628: dup
    //   2629: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2632: ldc_w 423
    //   2635: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2638: iload 71
    //   2640: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   2643: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2646: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2649: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2652: pop
    //   2653: aload 69
    //   2655: iload 71
    //   2657: invokevirtual 424	com/appbuilder/u846253p1176378/WidgetUILabel:setLeft	(I)V
    //   2660: aload 70
    //   2662: checkcast 51	org/w3c/dom/Element
    //   2665: ldc 254
    //   2667: invokeinterface 245 2 0
    //   2672: iconst_0
    //   2673: invokeinterface 138 2 0
    //   2678: invokeinterface 170 1 0
    //   2683: iconst_0
    //   2684: invokeinterface 138 2 0
    //   2689: invokeinterface 173 1 0
    //   2694: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2697: istore 73
    //   2699: ldc 144
    //   2701: new 146	java/lang/StringBuilder
    //   2704: dup
    //   2705: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2708: ldc_w 426
    //   2711: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2714: iload 73
    //   2716: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   2719: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2722: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2725: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2728: pop
    //   2729: aload 69
    //   2731: iload 73
    //   2733: invokevirtual 427	com/appbuilder/u846253p1176378/WidgetUILabel:setTop	(I)V
    //   2736: aload 70
    //   2738: checkcast 51	org/w3c/dom/Element
    //   2741: ldc_w 261
    //   2744: invokeinterface 245 2 0
    //   2749: iconst_0
    //   2750: invokeinterface 138 2 0
    //   2755: invokeinterface 170 1 0
    //   2760: iconst_0
    //   2761: invokeinterface 138 2 0
    //   2766: invokeinterface 173 1 0
    //   2771: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2774: istore 75
    //   2776: ldc 144
    //   2778: new 146	java/lang/StringBuilder
    //   2781: dup
    //   2782: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2785: ldc_w 429
    //   2788: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2791: iload 75
    //   2793: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   2796: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2799: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2802: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2805: pop
    //   2806: aload 69
    //   2808: iload 75
    //   2810: invokevirtual 430	com/appbuilder/u846253p1176378/WidgetUILabel:setWidth	(I)V
    //   2813: aload 70
    //   2815: checkcast 51	org/w3c/dom/Element
    //   2818: ldc_w 268
    //   2821: invokeinterface 245 2 0
    //   2826: iconst_0
    //   2827: invokeinterface 138 2 0
    //   2832: invokeinterface 170 1 0
    //   2837: iconst_0
    //   2838: invokeinterface 138 2 0
    //   2843: invokeinterface 173 1 0
    //   2848: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2851: istore 77
    //   2853: ldc 144
    //   2855: new 146	java/lang/StringBuilder
    //   2858: dup
    //   2859: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2862: ldc_w 432
    //   2865: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2868: iload 77
    //   2870: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   2873: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2876: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2879: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2882: pop
    //   2883: aload 69
    //   2885: iload 77
    //   2887: invokevirtual 433	com/appbuilder/u846253p1176378/WidgetUILabel:setHeight	(I)V
    //   2890: aload 70
    //   2892: checkcast 51	org/w3c/dom/Element
    //   2895: ldc_w 335
    //   2898: invokeinterface 245 2 0
    //   2903: iconst_0
    //   2904: invokeinterface 138 2 0
    //   2909: checkcast 51	org/w3c/dom/Element
    //   2912: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   2915: astore 79
    //   2917: aload 69
    //   2919: aload 79
    //   2921: invokevirtual 434	com/appbuilder/u846253p1176378/WidgetUILabel:setTitle	(Ljava/lang/String;)V
    //   2924: ldc 144
    //   2926: new 146	java/lang/StringBuilder
    //   2929: dup
    //   2930: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   2933: ldc_w 436
    //   2936: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2939: aload 79
    //   2941: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2944: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2947: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   2950: pop
    //   2951: aload 70
    //   2953: checkcast 51	org/w3c/dom/Element
    //   2956: ldc_w 438
    //   2959: invokeinterface 245 2 0
    //   2964: iconst_0
    //   2965: invokeinterface 138 2 0
    //   2970: invokeinterface 170 1 0
    //   2975: iconst_0
    //   2976: invokeinterface 138 2 0
    //   2981: invokeinterface 173 1 0
    //   2986: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2989: istore 82
    //   2991: aload 69
    //   2993: iload 82
    //   2995: invokevirtual 441	com/appbuilder/u846253p1176378/WidgetUILabel:setFontSize	(I)V
    //   2998: ldc 144
    //   3000: new 146	java/lang/StringBuilder
    //   3003: dup
    //   3004: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3007: ldc_w 443
    //   3010: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3013: iload 82
    //   3015: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   3018: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3021: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3024: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3027: pop
    //   3028: aload 70
    //   3030: checkcast 51	org/w3c/dom/Element
    //   3033: ldc_w 445
    //   3036: invokeinterface 245 2 0
    //   3041: iconst_0
    //   3042: invokeinterface 138 2 0
    //   3047: invokeinterface 170 1 0
    //   3052: iconst_0
    //   3053: invokeinterface 138 2 0
    //   3058: invokeinterface 173 1 0
    //   3063: astore 84
    //   3065: aload 69
    //   3067: aload 84
    //   3069: invokevirtual 448	com/appbuilder/u846253p1176378/WidgetUILabel:setColor	(Ljava/lang/String;)V
    //   3072: ldc 144
    //   3074: new 146	java/lang/StringBuilder
    //   3077: dup
    //   3078: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3081: ldc_w 450
    //   3084: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3087: aload 84
    //   3089: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3092: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3095: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3098: pop
    //   3099: aload 70
    //   3101: checkcast 51	org/w3c/dom/Element
    //   3104: ldc_w 452
    //   3107: invokeinterface 245 2 0
    //   3112: iconst_0
    //   3113: invokeinterface 138 2 0
    //   3118: invokeinterface 170 1 0
    //   3123: iconst_0
    //   3124: invokeinterface 138 2 0
    //   3129: invokeinterface 173 1 0
    //   3134: astore 86
    //   3136: aload 69
    //   3138: aload 86
    //   3140: invokevirtual 455	com/appbuilder/u846253p1176378/WidgetUILabel:setStyle	(Ljava/lang/String;)V
    //   3143: ldc 144
    //   3145: new 146	java/lang/StringBuilder
    //   3148: dup
    //   3149: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3152: ldc_w 457
    //   3155: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3158: aload 86
    //   3160: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3163: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3166: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3169: pop
    //   3170: aload_0
    //   3171: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   3174: aload 69
    //   3176: invokevirtual 461	com/appbuilder/u846253p1176378/AppConfigure:addLabel	(Lcom/appbuilder/u846253p1176378/WidgetUILabel;)V
    //   3179: goto +2506 -> 5685
    //   3182: aload 8
    //   3184: ldc_w 463
    //   3187: invokeinterface 132 2 0
    //   3192: astore 88
    //   3194: aload 88
    //   3196: invokeinterface 229 1 0
    //   3201: istore 89
    //   3203: iconst_0
    //   3204: istore 90
    //   3206: iload 90
    //   3208: iload 89
    //   3210: if_icmpge +947 -> 4157
    //   3213: new 465	com/appbuilder/u846253p1176378/WidgetUIButton
    //   3216: dup
    //   3217: invokespecial 466	com/appbuilder/u846253p1176378/WidgetUIButton:<init>	()V
    //   3220: astore 91
    //   3222: aload 88
    //   3224: iload 90
    //   3226: invokeinterface 138 2 0
    //   3231: astore 92
    //   3233: aload 92
    //   3235: invokeinterface 236 1 0
    //   3240: iconst_1
    //   3241: if_icmpeq +6 -> 3247
    //   3244: goto +2447 -> 5691
    //   3247: aload 92
    //   3249: checkcast 51	org/w3c/dom/Element
    //   3252: ldc 244
    //   3254: invokeinterface 245 2 0
    //   3259: iconst_0
    //   3260: invokeinterface 138 2 0
    //   3265: invokeinterface 170 1 0
    //   3270: iconst_0
    //   3271: invokeinterface 138 2 0
    //   3276: invokeinterface 173 1 0
    //   3281: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   3284: istore 93
    //   3286: ldc 144
    //   3288: new 146	java/lang/StringBuilder
    //   3291: dup
    //   3292: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3295: ldc_w 468
    //   3298: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3301: iload 93
    //   3303: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   3306: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3309: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3312: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3315: pop
    //   3316: aload 91
    //   3318: iload 93
    //   3320: invokevirtual 469	com/appbuilder/u846253p1176378/WidgetUIButton:setLeft	(I)V
    //   3323: aload 92
    //   3325: checkcast 51	org/w3c/dom/Element
    //   3328: ldc 254
    //   3330: invokeinterface 245 2 0
    //   3335: iconst_0
    //   3336: invokeinterface 138 2 0
    //   3341: invokeinterface 170 1 0
    //   3346: iconst_0
    //   3347: invokeinterface 138 2 0
    //   3352: invokeinterface 173 1 0
    //   3357: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   3360: istore 95
    //   3362: ldc 144
    //   3364: new 146	java/lang/StringBuilder
    //   3367: dup
    //   3368: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3371: ldc_w 471
    //   3374: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3377: iload 95
    //   3379: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   3382: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3385: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3388: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3391: pop
    //   3392: aload 91
    //   3394: iload 95
    //   3396: invokevirtual 472	com/appbuilder/u846253p1176378/WidgetUIButton:setTop	(I)V
    //   3399: aload 92
    //   3401: checkcast 51	org/w3c/dom/Element
    //   3404: ldc_w 261
    //   3407: invokeinterface 245 2 0
    //   3412: iconst_0
    //   3413: invokeinterface 138 2 0
    //   3418: invokeinterface 170 1 0
    //   3423: iconst_0
    //   3424: invokeinterface 138 2 0
    //   3429: invokeinterface 173 1 0
    //   3434: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   3437: istore 97
    //   3439: ldc 144
    //   3441: new 146	java/lang/StringBuilder
    //   3444: dup
    //   3445: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3448: ldc_w 474
    //   3451: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3454: iload 97
    //   3456: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   3459: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3462: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3465: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3468: pop
    //   3469: aload 91
    //   3471: iload 97
    //   3473: invokevirtual 475	com/appbuilder/u846253p1176378/WidgetUIButton:setWidth	(I)V
    //   3476: aload 92
    //   3478: checkcast 51	org/w3c/dom/Element
    //   3481: ldc_w 268
    //   3484: invokeinterface 245 2 0
    //   3489: iconst_0
    //   3490: invokeinterface 138 2 0
    //   3495: invokeinterface 170 1 0
    //   3500: iconst_0
    //   3501: invokeinterface 138 2 0
    //   3506: invokeinterface 173 1 0
    //   3511: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   3514: istore 99
    //   3516: ldc 144
    //   3518: new 146	java/lang/StringBuilder
    //   3521: dup
    //   3522: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3525: ldc_w 477
    //   3528: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3531: iload 99
    //   3533: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   3536: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3539: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3542: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3545: pop
    //   3546: aload 91
    //   3548: iload 99
    //   3550: invokevirtual 478	com/appbuilder/u846253p1176378/WidgetUIButton:setHeight	(I)V
    //   3553: aconst_null
    //   3554: astore 101
    //   3556: aload 92
    //   3558: checkcast 51	org/w3c/dom/Element
    //   3561: ldc_w 480
    //   3564: invokeinterface 245 2 0
    //   3569: iconst_0
    //   3570: invokeinterface 138 2 0
    //   3575: checkcast 51	org/w3c/dom/Element
    //   3578: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   3581: astore 101
    //   3583: aload 91
    //   3585: aload 101
    //   3587: invokevirtual 483	com/appbuilder/u846253p1176378/WidgetUIButton:setImageSourceUrl	(Ljava/lang/String;)V
    //   3590: ldc 144
    //   3592: new 146	java/lang/StringBuilder
    //   3595: dup
    //   3596: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3599: ldc_w 485
    //   3602: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3605: aload 101
    //   3607: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3610: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3613: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3616: pop
    //   3617: aload 91
    //   3619: aload 92
    //   3621: checkcast 51	org/w3c/dom/Element
    //   3624: ldc_w 487
    //   3627: invokeinterface 245 2 0
    //   3632: iconst_0
    //   3633: invokeinterface 138 2 0
    //   3638: checkcast 51	org/w3c/dom/Element
    //   3641: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   3644: invokevirtual 488	com/appbuilder/u846253p1176378/WidgetUIButton:setmImageData	(Ljava/lang/String;)V
    //   3647: ldc 144
    //   3649: new 146	java/lang/StringBuilder
    //   3652: dup
    //   3653: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3656: ldc_w 485
    //   3659: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3662: aload 101
    //   3664: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3667: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3670: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3673: pop
    //   3674: aload 92
    //   3676: checkcast 51	org/w3c/dom/Element
    //   3679: ldc_w 418
    //   3682: invokeinterface 245 2 0
    //   3687: iconst_0
    //   3688: invokeinterface 138 2 0
    //   3693: checkcast 51	org/w3c/dom/Element
    //   3696: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   3699: astore 106
    //   3701: aload 91
    //   3703: aload 106
    //   3705: invokevirtual 489	com/appbuilder/u846253p1176378/WidgetUIButton:setTitle	(Ljava/lang/String;)V
    //   3708: ldc 144
    //   3710: new 146	java/lang/StringBuilder
    //   3713: dup
    //   3714: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3717: ldc_w 491
    //   3720: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3723: aload 106
    //   3725: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3728: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3731: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3734: pop
    //   3735: aload 92
    //   3737: checkcast 51	org/w3c/dom/Element
    //   3740: ldc_w 438
    //   3743: invokeinterface 245 2 0
    //   3748: iconst_0
    //   3749: invokeinterface 138 2 0
    //   3754: invokeinterface 170 1 0
    //   3759: iconst_0
    //   3760: invokeinterface 138 2 0
    //   3765: invokeinterface 173 1 0
    //   3770: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   3773: istore 108
    //   3775: aload 91
    //   3777: iload 108
    //   3779: invokevirtual 492	com/appbuilder/u846253p1176378/WidgetUIButton:setFontSize	(I)V
    //   3782: ldc 144
    //   3784: new 146	java/lang/StringBuilder
    //   3787: dup
    //   3788: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3791: ldc_w 494
    //   3794: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3797: iload 108
    //   3799: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   3802: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3805: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3808: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3811: pop
    //   3812: aload 92
    //   3814: checkcast 51	org/w3c/dom/Element
    //   3817: ldc_w 496
    //   3820: invokeinterface 245 2 0
    //   3825: iconst_0
    //   3826: invokeinterface 138 2 0
    //   3831: invokeinterface 170 1 0
    //   3836: iconst_0
    //   3837: invokeinterface 138 2 0
    //   3842: invokeinterface 173 1 0
    //   3847: astore 110
    //   3849: aload 91
    //   3851: aload 110
    //   3853: invokevirtual 499	com/appbuilder/u846253p1176378/WidgetUIButton:setAlign	(Ljava/lang/String;)V
    //   3856: ldc 144
    //   3858: new 146	java/lang/StringBuilder
    //   3861: dup
    //   3862: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3865: ldc_w 501
    //   3868: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3871: aload 110
    //   3873: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3876: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3879: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3882: pop
    //   3883: aload 92
    //   3885: checkcast 51	org/w3c/dom/Element
    //   3888: ldc_w 445
    //   3891: invokeinterface 245 2 0
    //   3896: iconst_0
    //   3897: invokeinterface 138 2 0
    //   3902: invokeinterface 170 1 0
    //   3907: iconst_0
    //   3908: invokeinterface 138 2 0
    //   3913: invokeinterface 173 1 0
    //   3918: astore 112
    //   3920: aload 91
    //   3922: aload 112
    //   3924: invokevirtual 502	com/appbuilder/u846253p1176378/WidgetUIButton:setColor	(Ljava/lang/String;)V
    //   3927: ldc 144
    //   3929: new 146	java/lang/StringBuilder
    //   3932: dup
    //   3933: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   3936: ldc_w 504
    //   3939: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3942: aload 112
    //   3944: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3947: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3950: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   3953: pop
    //   3954: aload 92
    //   3956: checkcast 51	org/w3c/dom/Element
    //   3959: ldc_w 452
    //   3962: invokeinterface 245 2 0
    //   3967: iconst_0
    //   3968: invokeinterface 138 2 0
    //   3973: invokeinterface 170 1 0
    //   3978: astore 114
    //   3980: aconst_null
    //   3981: astore 115
    //   3983: aload 114
    //   3985: iconst_0
    //   3986: invokeinterface 138 2 0
    //   3991: invokeinterface 173 1 0
    //   3996: astore 115
    //   3998: aload 91
    //   4000: aload 115
    //   4002: invokevirtual 505	com/appbuilder/u846253p1176378/WidgetUIButton:setStyle	(Ljava/lang/String;)V
    //   4005: ldc 144
    //   4007: new 146	java/lang/StringBuilder
    //   4010: dup
    //   4011: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4014: ldc_w 507
    //   4017: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4020: aload 115
    //   4022: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4025: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4028: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4031: pop
    //   4032: aload 92
    //   4034: checkcast 51	org/w3c/dom/Element
    //   4037: ldc_w 509
    //   4040: invokeinterface 245 2 0
    //   4045: iconst_0
    //   4046: invokeinterface 138 2 0
    //   4051: invokeinterface 170 1 0
    //   4056: iconst_0
    //   4057: invokeinterface 138 2 0
    //   4062: invokeinterface 173 1 0
    //   4067: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   4070: istore 118
    //   4072: aload 91
    //   4074: iload 118
    //   4076: invokevirtual 512	com/appbuilder/u846253p1176378/WidgetUIButton:setOrder	(I)V
    //   4079: ldc 144
    //   4081: new 146	java/lang/StringBuilder
    //   4084: dup
    //   4085: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4088: ldc_w 514
    //   4091: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4094: iload 118
    //   4096: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   4099: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4102: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4105: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4108: pop
    //   4109: aload_0
    //   4110: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   4113: aload 91
    //   4115: invokevirtual 518	com/appbuilder/u846253p1176378/AppConfigure:addButton	(Lcom/appbuilder/u846253p1176378/WidgetUIButton;)V
    //   4118: goto +1573 -> 5691
    //   4121: astore 102
    //   4123: aload 91
    //   4125: ldc 17
    //   4127: invokevirtual 483	com/appbuilder/u846253p1176378/WidgetUIButton:setImageSourceUrl	(Ljava/lang/String;)V
    //   4130: goto -540 -> 3590
    //   4133: astore 104
    //   4135: aload 91
    //   4137: ldc 17
    //   4139: invokevirtual 488	com/appbuilder/u846253p1176378/WidgetUIButton:setmImageData	(Ljava/lang/String;)V
    //   4142: goto -495 -> 3647
    //   4145: astore 116
    //   4147: aload 91
    //   4149: ldc 17
    //   4151: invokevirtual 505	com/appbuilder/u846253p1176378/WidgetUIButton:setStyle	(Ljava/lang/String;)V
    //   4154: goto -149 -> 4005
    //   4157: aload 8
    //   4159: ldc_w 520
    //   4162: invokeinterface 132 2 0
    //   4167: astore 120
    //   4169: aload 120
    //   4171: invokeinterface 229 1 0
    //   4176: istore 121
    //   4178: iconst_0
    //   4179: istore 122
    //   4181: iload 122
    //   4183: iload 121
    //   4185: if_icmpge +339 -> 4524
    //   4188: new 522	com/appbuilder/u846253p1176378/WidgetUITab
    //   4191: dup
    //   4192: invokespecial 523	com/appbuilder/u846253p1176378/WidgetUITab:<init>	()V
    //   4195: astore 123
    //   4197: aload 120
    //   4199: iload 122
    //   4201: invokeinterface 138 2 0
    //   4206: astore 124
    //   4208: aload 124
    //   4210: invokeinterface 236 1 0
    //   4215: istore 125
    //   4217: iload 125
    //   4219: iconst_1
    //   4220: if_icmpeq +9 -> 4229
    //   4223: iinc 122 1
    //   4226: goto -45 -> 4181
    //   4229: aconst_null
    //   4230: astore 126
    //   4232: aload 124
    //   4234: checkcast 51	org/w3c/dom/Element
    //   4237: ldc_w 480
    //   4240: invokeinterface 245 2 0
    //   4245: iconst_0
    //   4246: invokeinterface 138 2 0
    //   4251: checkcast 51	org/w3c/dom/Element
    //   4254: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   4257: astore 126
    //   4259: aload 123
    //   4261: aload 126
    //   4263: invokevirtual 526	com/appbuilder/u846253p1176378/WidgetUITab:setIconUrl	(Ljava/lang/String;)V
    //   4266: ldc 144
    //   4268: new 146	java/lang/StringBuilder
    //   4271: dup
    //   4272: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4275: ldc_w 528
    //   4278: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4281: aload 126
    //   4283: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4286: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4289: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4292: pop
    //   4293: aload 123
    //   4295: aload 124
    //   4297: checkcast 51	org/w3c/dom/Element
    //   4300: ldc_w 487
    //   4303: invokeinterface 245 2 0
    //   4308: iconst_0
    //   4309: invokeinterface 138 2 0
    //   4314: checkcast 51	org/w3c/dom/Element
    //   4317: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   4320: invokevirtual 531	com/appbuilder/u846253p1176378/WidgetUITab:setmIconData	(Ljava/lang/String;)V
    //   4323: ldc 144
    //   4325: new 146	java/lang/StringBuilder
    //   4328: dup
    //   4329: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4332: ldc_w 528
    //   4335: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4338: aload 126
    //   4340: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4343: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4346: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4349: pop
    //   4350: aload 124
    //   4352: checkcast 51	org/w3c/dom/Element
    //   4355: ldc_w 418
    //   4358: invokeinterface 245 2 0
    //   4363: iconst_0
    //   4364: invokeinterface 138 2 0
    //   4369: checkcast 51	org/w3c/dom/Element
    //   4372: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   4375: astore 131
    //   4377: aload 123
    //   4379: aload 131
    //   4381: invokevirtual 534	com/appbuilder/u846253p1176378/WidgetUITab:setLabel	(Ljava/lang/String;)V
    //   4384: ldc 144
    //   4386: new 146	java/lang/StringBuilder
    //   4389: dup
    //   4390: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4393: ldc_w 536
    //   4396: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4399: aload 131
    //   4401: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4404: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4407: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4410: pop
    //   4411: aload 124
    //   4413: checkcast 51	org/w3c/dom/Element
    //   4416: ldc_w 509
    //   4419: invokeinterface 245 2 0
    //   4424: iconst_0
    //   4425: invokeinterface 138 2 0
    //   4430: invokeinterface 170 1 0
    //   4435: iconst_0
    //   4436: invokeinterface 138 2 0
    //   4441: invokeinterface 173 1 0
    //   4446: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   4449: istore 133
    //   4451: aload 123
    //   4453: iload 133
    //   4455: invokevirtual 537	com/appbuilder/u846253p1176378/WidgetUITab:setOrder	(I)V
    //   4458: ldc 144
    //   4460: new 146	java/lang/StringBuilder
    //   4463: dup
    //   4464: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4467: ldc_w 539
    //   4470: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4473: iload 133
    //   4475: invokestatic 249	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   4478: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4481: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4484: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4487: pop
    //   4488: aload_0
    //   4489: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   4492: aload 123
    //   4494: invokevirtual 543	com/appbuilder/u846253p1176378/AppConfigure:addTab	(Lcom/appbuilder/u846253p1176378/WidgetUITab;)V
    //   4497: goto -274 -> 4223
    //   4500: astore 127
    //   4502: aload 123
    //   4504: ldc 17
    //   4506: invokevirtual 526	com/appbuilder/u846253p1176378/WidgetUITab:setIconUrl	(Ljava/lang/String;)V
    //   4509: goto -243 -> 4266
    //   4512: astore 129
    //   4514: aload 123
    //   4516: ldc 17
    //   4518: invokevirtual 531	com/appbuilder/u846253p1176378/WidgetUITab:setmIconData	(Ljava/lang/String;)V
    //   4521: goto -198 -> 4323
    //   4524: new 545	java/util/HashMap
    //   4527: dup
    //   4528: invokespecial 546	java/util/HashMap:<init>	()V
    //   4531: astore 135
    //   4533: aload 8
    //   4535: ldc_w 548
    //   4538: invokeinterface 132 2 0
    //   4543: astore 136
    //   4545: aload 136
    //   4547: ifnull +191 -> 4738
    //   4550: iconst_0
    //   4551: istore 174
    //   4553: aload 136
    //   4555: invokeinterface 229 1 0
    //   4560: istore 175
    //   4562: iload 174
    //   4564: iload 175
    //   4566: if_icmpge +172 -> 4738
    //   4569: aload 136
    //   4571: iload 174
    //   4573: invokeinterface 138 2 0
    //   4578: astore 176
    //   4580: aload 176
    //   4582: invokeinterface 236 1 0
    //   4587: istore 177
    //   4589: iload 177
    //   4591: iconst_1
    //   4592: if_icmpeq +9 -> 4601
    //   4595: iinc 174 1
    //   4598: goto -45 -> 4553
    //   4601: aload 176
    //   4603: checkcast 51	org/w3c/dom/Element
    //   4606: ldc_w 509
    //   4609: invokeinterface 245 2 0
    //   4614: iconst_0
    //   4615: invokeinterface 138 2 0
    //   4620: invokeinterface 170 1 0
    //   4625: iconst_0
    //   4626: invokeinterface 138 2 0
    //   4631: invokeinterface 173 1 0
    //   4636: astore 179
    //   4638: aload 176
    //   4640: checkcast 51	org/w3c/dom/Element
    //   4643: ldc_w 381
    //   4646: invokeinterface 245 2 0
    //   4651: iconst_0
    //   4652: invokeinterface 138 2 0
    //   4657: invokeinterface 170 1 0
    //   4662: iconst_0
    //   4663: invokeinterface 138 2 0
    //   4668: invokeinterface 173 1 0
    //   4673: astore 180
    //   4675: aload 135
    //   4677: aload 179
    //   4679: invokevirtual 551	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   4682: ifeq +22 -> 4704
    //   4685: aload 135
    //   4687: aload 179
    //   4689: invokevirtual 555	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4692: checkcast 557	java/util/ArrayList
    //   4695: aload 180
    //   4697: invokevirtual 560	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   4700: pop
    //   4701: goto -106 -> 4595
    //   4704: aload 135
    //   4706: aload 179
    //   4708: new 557	java/util/ArrayList
    //   4711: dup
    //   4712: invokespecial 561	java/util/ArrayList:<init>	()V
    //   4715: invokevirtual 565	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   4718: pop
    //   4719: aload 135
    //   4721: aload 179
    //   4723: invokevirtual 555	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4726: checkcast 557	java/util/ArrayList
    //   4729: aload 180
    //   4731: invokevirtual 560	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   4734: pop
    //   4735: goto -140 -> 4595
    //   4738: aload 8
    //   4740: ldc_w 567
    //   4743: invokeinterface 132 2 0
    //   4748: astore 137
    //   4750: aload 137
    //   4752: invokeinterface 229 1 0
    //   4757: istore 138
    //   4759: iconst_0
    //   4760: istore 139
    //   4762: iload 139
    //   4764: iload 138
    //   4766: if_icmpge -4081 -> 685
    //   4769: new 569	com/appbuilder/sdk/android/Widget
    //   4772: dup
    //   4773: invokespecial 570	com/appbuilder/sdk/android/Widget:<init>	()V
    //   4776: astore 140
    //   4778: aload 140
    //   4780: aload_0
    //   4781: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   4784: invokevirtual 573	com/appbuilder/u846253p1176378/AppConfigure:getAppName	()Ljava/lang/String;
    //   4787: invokevirtual 574	com/appbuilder/sdk/android/Widget:setAppName	(Ljava/lang/String;)V
    //   4790: aload 140
    //   4792: aload_0
    //   4793: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   4796: invokevirtual 577	com/appbuilder/u846253p1176378/AppConfigure:getDateFormat	()I
    //   4799: invokevirtual 578	com/appbuilder/sdk/android/Widget:setDateFormat	(I)V
    //   4802: aload 137
    //   4804: iload 139
    //   4806: invokeinterface 138 2 0
    //   4811: astore 141
    //   4813: aload 141
    //   4815: invokeinterface 236 1 0
    //   4820: istore 142
    //   4822: iload 142
    //   4824: iconst_1
    //   4825: if_icmpeq +9 -> 4834
    //   4828: iinc 139 1
    //   4831: goto -69 -> 4762
    //   4834: aload 141
    //   4836: checkcast 51	org/w3c/dom/Element
    //   4839: ldc_w 335
    //   4842: invokeinterface 245 2 0
    //   4847: iconst_0
    //   4848: invokeinterface 138 2 0
    //   4853: invokeinterface 170 1 0
    //   4858: iconst_0
    //   4859: invokeinterface 138 2 0
    //   4864: invokeinterface 173 1 0
    //   4869: astore 172
    //   4871: aload 140
    //   4873: aload 172
    //   4875: invokevirtual 579	com/appbuilder/sdk/android/Widget:setTitle	(Ljava/lang/String;)V
    //   4878: ldc 144
    //   4880: new 146	java/lang/StringBuilder
    //   4883: dup
    //   4884: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4887: ldc_w 581
    //   4890: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4893: aload 172
    //   4895: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4898: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4901: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4904: pop
    //   4905: ldc 17
    //   4907: astore 144
    //   4909: aload 141
    //   4911: checkcast 51	org/w3c/dom/Element
    //   4914: ldc_w 583
    //   4917: invokeinterface 245 2 0
    //   4922: iconst_0
    //   4923: invokeinterface 138 2 0
    //   4928: invokeinterface 170 1 0
    //   4933: iconst_0
    //   4934: invokeinterface 138 2 0
    //   4939: invokeinterface 173 1 0
    //   4944: astore 144
    //   4946: aload 140
    //   4948: aload 144
    //   4950: invokestatic 200	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   4953: invokevirtual 584	com/appbuilder/sdk/android/Widget:setOrder	(I)V
    //   4956: ldc 144
    //   4958: new 146	java/lang/StringBuilder
    //   4961: dup
    //   4962: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   4965: ldc_w 586
    //   4968: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4971: aload 144
    //   4973: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   4976: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   4979: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   4982: pop
    //   4983: aload 141
    //   4985: checkcast 51	org/w3c/dom/Element
    //   4988: ldc_w 588
    //   4991: invokeinterface 245 2 0
    //   4996: iconst_0
    //   4997: invokeinterface 138 2 0
    //   5002: invokeinterface 170 1 0
    //   5007: iconst_0
    //   5008: invokeinterface 138 2 0
    //   5013: invokeinterface 173 1 0
    //   5018: astore 169
    //   5020: aload 140
    //   5022: aload 169
    //   5024: invokevirtual 591	com/appbuilder/sdk/android/Widget:setPluginName	(Ljava/lang/String;)V
    //   5027: ldc 144
    //   5029: new 146	java/lang/StringBuilder
    //   5032: dup
    //   5033: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5036: ldc_w 593
    //   5039: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5042: aload 169
    //   5044: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5047: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5050: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5053: pop
    //   5054: aload 140
    //   5056: aload 141
    //   5058: checkcast 51	org/w3c/dom/Element
    //   5061: ldc_w 381
    //   5064: invokeinterface 245 2 0
    //   5069: iconst_0
    //   5070: invokeinterface 138 2 0
    //   5075: invokeinterface 170 1 0
    //   5080: iconst_0
    //   5081: invokeinterface 138 2 0
    //   5086: invokeinterface 173 1 0
    //   5091: invokevirtual 596	com/appbuilder/sdk/android/Widget:setPluginType	(Ljava/lang/String;)V
    //   5094: aload 141
    //   5096: checkcast 51	org/w3c/dom/Element
    //   5099: ldc_w 598
    //   5102: invokeinterface 245 2 0
    //   5107: iconst_0
    //   5108: invokeinterface 138 2 0
    //   5113: invokeinterface 170 1 0
    //   5118: iconst_0
    //   5119: invokeinterface 138 2 0
    //   5124: invokeinterface 173 1 0
    //   5129: astore 167
    //   5131: aload 140
    //   5133: aload 167
    //   5135: invokevirtual 601	com/appbuilder/sdk/android/Widget:setPluginPackage	(Ljava/lang/String;)V
    //   5138: ldc 144
    //   5140: new 146	java/lang/StringBuilder
    //   5143: dup
    //   5144: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5147: ldc_w 603
    //   5150: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5153: aload 167
    //   5155: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5158: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5161: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5164: pop
    //   5165: aload 141
    //   5167: checkcast 51	org/w3c/dom/Element
    //   5170: ldc_w 605
    //   5173: invokeinterface 245 2 0
    //   5178: iconst_0
    //   5179: invokeinterface 138 2 0
    //   5184: invokeinterface 170 1 0
    //   5189: iconst_0
    //   5190: invokeinterface 138 2 0
    //   5195: invokeinterface 173 1 0
    //   5200: astore 165
    //   5202: aload 140
    //   5204: aload 165
    //   5206: invokevirtual 608	com/appbuilder/sdk/android/Widget:setPluginHash	(Ljava/lang/String;)V
    //   5209: ldc 144
    //   5211: new 146	java/lang/StringBuilder
    //   5214: dup
    //   5215: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5218: ldc_w 610
    //   5221: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5224: aload 165
    //   5226: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5229: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5232: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5235: pop
    //   5236: aload 141
    //   5238: checkcast 51	org/w3c/dom/Element
    //   5241: ldc_w 275
    //   5244: invokeinterface 245 2 0
    //   5249: iconst_0
    //   5250: invokeinterface 138 2 0
    //   5255: invokeinterface 170 1 0
    //   5260: iconst_0
    //   5261: invokeinterface 138 2 0
    //   5266: invokeinterface 173 1 0
    //   5271: astore 163
    //   5273: aload 140
    //   5275: aload 163
    //   5277: invokevirtual 613	com/appbuilder/sdk/android/Widget:setPluginUrl	(Ljava/lang/String;)V
    //   5280: ldc 144
    //   5282: new 146	java/lang/StringBuilder
    //   5285: dup
    //   5286: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5289: ldc_w 615
    //   5292: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5295: aload 163
    //   5297: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5300: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5303: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5306: pop
    //   5307: aload 141
    //   5309: checkcast 51	org/w3c/dom/Element
    //   5312: ldc_w 617
    //   5315: invokeinterface 245 2 0
    //   5320: iconst_0
    //   5321: invokeinterface 138 2 0
    //   5326: invokeinterface 170 1 0
    //   5331: iconst_0
    //   5332: invokeinterface 138 2 0
    //   5337: invokeinterface 173 1 0
    //   5342: astore 161
    //   5344: aload 140
    //   5346: aload 161
    //   5348: invokevirtual 620	com/appbuilder/sdk/android/Widget:setBackground	(Ljava/lang/String;)V
    //   5351: ldc 144
    //   5353: new 146	java/lang/StringBuilder
    //   5356: dup
    //   5357: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5360: ldc_w 622
    //   5363: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5366: aload 161
    //   5368: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5371: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5374: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5377: pop
    //   5378: aload 141
    //   5380: checkcast 51	org/w3c/dom/Element
    //   5383: ldc_w 624
    //   5386: invokeinterface 245 2 0
    //   5391: iconst_0
    //   5392: invokeinterface 138 2 0
    //   5397: invokeinterface 170 1 0
    //   5402: iconst_0
    //   5403: invokeinterface 138 2 0
    //   5408: invokeinterface 173 1 0
    //   5413: astore 159
    //   5415: aload 140
    //   5417: aload 159
    //   5419: invokevirtual 627	com/appbuilder/sdk/android/Widget:setTextColor	(Ljava/lang/String;)V
    //   5422: ldc 144
    //   5424: new 146	java/lang/StringBuilder
    //   5427: dup
    //   5428: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5431: ldc_w 629
    //   5434: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5437: aload 159
    //   5439: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5442: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5445: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5448: pop
    //   5449: aload 141
    //   5451: checkcast 51	org/w3c/dom/Element
    //   5454: ldc_w 631
    //   5457: invokeinterface 245 2 0
    //   5462: iconst_0
    //   5463: invokeinterface 138 2 0
    //   5468: checkcast 51	org/w3c/dom/Element
    //   5471: invokestatic 140	com/appbuilder/u846253p1176378/AppConfigureParser:getCharacterDataFromElement	(Lorg/w3c/dom/Element;)Ljava/lang/String;
    //   5474: astore 157
    //   5476: aload 140
    //   5478: aload 157
    //   5480: invokevirtual 634	com/appbuilder/sdk/android/Widget:setPluginXmlData	(Ljava/lang/String;)V
    //   5483: ldc 144
    //   5485: new 146	java/lang/StringBuilder
    //   5488: dup
    //   5489: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   5492: ldc_w 636
    //   5495: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5498: aload 157
    //   5500: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   5503: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   5506: invokestatic 162	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   5509: pop
    //   5510: aload 135
    //   5512: aload 144
    //   5514: invokevirtual 551	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   5517: ifeq +61 -> 5578
    //   5520: aload 135
    //   5522: aload 144
    //   5524: invokevirtual 555	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   5527: checkcast 557	java/util/ArrayList
    //   5530: astore 154
    //   5532: iconst_0
    //   5533: istore 155
    //   5535: aload 154
    //   5537: invokevirtual 638	java/util/ArrayList:size	()I
    //   5540: istore 156
    //   5542: iload 155
    //   5544: iload 156
    //   5546: if_icmpge +32 -> 5578
    //   5549: aload 140
    //   5551: aload 154
    //   5553: iload 155
    //   5555: invokevirtual 641	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   5558: checkcast 35	java/lang/String
    //   5561: new 643	java/lang/Boolean
    //   5564: dup
    //   5565: iconst_1
    //   5566: invokespecial 645	java/lang/Boolean:<init>	(Z)V
    //   5569: invokevirtual 649	com/appbuilder/sdk/android/Widget:addParameter	(Ljava/lang/String;Ljava/lang/Object;)V
    //   5572: iinc 155 1
    //   5575: goto -40 -> 5535
    //   5578: aload_0
    //   5579: getfield 24	com/appbuilder/u846253p1176378/AppConfigureParser:appConfig	Lcom/appbuilder/u846253p1176378/AppConfigure;
    //   5582: aload 140
    //   5584: invokevirtual 653	com/appbuilder/u846253p1176378/AppConfigure:addWidget	(Lcom/appbuilder/sdk/android/Widget;)V
    //   5587: goto -759 -> 4828
    //   5590: astore 153
    //   5592: goto -82 -> 5510
    //   5595: astore 152
    //   5597: goto -148 -> 5449
    //   5600: astore 151
    //   5602: goto -224 -> 5378
    //   5605: astore 150
    //   5607: goto -300 -> 5307
    //   5610: astore 149
    //   5612: goto -376 -> 5236
    //   5615: astore 148
    //   5617: goto -452 -> 5165
    //   5620: astore 147
    //   5622: goto -528 -> 5094
    //   5625: astore 146
    //   5627: goto -573 -> 5054
    //   5630: astore 145
    //   5632: goto -649 -> 4983
    //   5635: astore 143
    //   5637: goto -732 -> 4905
    //   5640: astore 81
    //   5642: goto -2472 -> 3170
    //   5645: astore 39
    //   5647: goto -4509 -> 1138
    //   5650: astore 15
    //   5652: goto -5308 -> 344
    //   5655: iinc 26 1
    //   5658: goto -5057 -> 601
    //   5661: iconst_0
    //   5662: istore 18
    //   5664: goto -5270 -> 394
    //   5667: iinc 200 1
    //   5670: goto -4500 -> 1170
    //   5673: iinc 184 1
    //   5676: goto -4349 -> 1327
    //   5679: iinc 47 1
    //   5682: goto -3817 -> 1865
    //   5685: iinc 68 1
    //   5688: goto -3166 -> 2522
    //   5691: iinc 90 1
    //   5694: goto -2488 -> 3206
    //   5697: astore 178
    //   5699: goto -1104 -> 4595
    //
    // Exception table:
    //   from	to	target	type
    //   430	504	663	java/lang/Exception
    //   22	30	684	java/lang/Exception
    //   36	52	684	java/lang/Exception
    //   55	102	684	java/lang/Exception
    //   102	286	684	java/lang/Exception
    //   286	344	684	java/lang/Exception
    //   344	386	684	java/lang/Exception
    //   394	430	684	java/lang/Exception
    //   578	598	684	java/lang/Exception
    //   608	639	684	java/lang/Exception
    //   642	660	684	java/lang/Exception
    //   665	681	684	java/lang/Exception
    //   692	708	684	java/lang/Exception
    //   711	1077	684	java/lang/Exception
    //   1077	1138	684	java/lang/Exception
    //   1138	1147	684	java/lang/Exception
    //   1307	1319	684	java/lang/Exception
    //   1327	1336	684	java/lang/Exception
    //   1832	1862	684	java/lang/Exception
    //   1872	1894	684	java/lang/Exception
    //   1897	1918	684	java/lang/Exception
    //   1922	1974	684	java/lang/Exception
    //   1974	1992	684	java/lang/Exception
    //   1992	2019	684	java/lang/Exception
    //   2023	2075	684	java/lang/Exception
    //   2075	2109	684	java/lang/Exception
    //   2113	2173	684	java/lang/Exception
    //   2173	2207	684	java/lang/Exception
    //   2266	2353	684	java/lang/Exception
    //   2353	2439	684	java/lang/Exception
    //   2439	2473	684	java/lang/Exception
    //   2478	2486	684	java/lang/Exception
    //   2489	2519	684	java/lang/Exception
    //   2529	2560	684	java/lang/Exception
    //   2563	2951	684	java/lang/Exception
    //   3170	3179	684	java/lang/Exception
    //   3182	3203	684	java/lang/Exception
    //   3213	3244	684	java/lang/Exception
    //   3247	3553	684	java/lang/Exception
    //   3556	3590	684	java/lang/Exception
    //   3590	3617	684	java/lang/Exception
    //   3617	3647	684	java/lang/Exception
    //   3647	3980	684	java/lang/Exception
    //   4005	4118	684	java/lang/Exception
    //   4123	4130	684	java/lang/Exception
    //   4135	4142	684	java/lang/Exception
    //   4147	4154	684	java/lang/Exception
    //   4157	4178	684	java/lang/Exception
    //   4188	4217	684	java/lang/Exception
    //   4232	4266	684	java/lang/Exception
    //   4266	4293	684	java/lang/Exception
    //   4293	4323	684	java/lang/Exception
    //   4323	4497	684	java/lang/Exception
    //   4502	4509	684	java/lang/Exception
    //   4514	4521	684	java/lang/Exception
    //   4524	4545	684	java/lang/Exception
    //   4553	4562	684	java/lang/Exception
    //   4569	4589	684	java/lang/Exception
    //   4738	4759	684	java/lang/Exception
    //   4769	4822	684	java/lang/Exception
    //   5510	5532	684	java/lang/Exception
    //   5535	5542	684	java/lang/Exception
    //   5549	5572	684	java/lang/Exception
    //   5578	5587	684	java/lang/Exception
    //   504	578	690	java/lang/Exception
    //   1150	1162	1305	java/lang/Exception
    //   1170	1179	1305	java/lang/Exception
    //   1186	1197	1305	java/lang/Exception
    //   1202	1213	1305	java/lang/Exception
    //   1216	1237	1305	java/lang/Exception
    //   1241	1293	1305	java/lang/Exception
    //   1293	1302	1305	java/lang/Exception
    //   1343	1354	1827	java/lang/Exception
    //   1359	1370	1827	java/lang/Exception
    //   1373	1394	1827	java/lang/Exception
    //   1398	1459	1827	java/lang/Exception
    //   1459	1532	1827	java/lang/Exception
    //   1532	1605	1827	java/lang/Exception
    //   1605	1664	1827	java/lang/Exception
    //   1664	1723	1827	java/lang/Exception
    //   1723	1796	1827	java/lang/Exception
    //   1796	1815	1827	java/lang/Exception
    //   1815	1824	1827	java/lang/Exception
    //   2207	2259	2476	java/lang/Exception
    //   2259	2266	2476	java/lang/Exception
    //   3556	3590	4121	java/lang/NullPointerException
    //   3617	3647	4133	java/lang/NullPointerException
    //   3983	4005	4145	java/lang/Exception
    //   4232	4266	4500	java/lang/NullPointerException
    //   4293	4323	4512	java/lang/NullPointerException
    //   5449	5510	5590	java/lang/Exception
    //   5378	5449	5595	java/lang/Exception
    //   5307	5378	5600	java/lang/Exception
    //   5236	5307	5605	java/lang/Exception
    //   5165	5236	5610	java/lang/Exception
    //   5094	5165	5615	java/lang/Exception
    //   5054	5094	5620	java/lang/Exception
    //   4983	5054	5625	java/lang/Exception
    //   4909	4983	5630	java/lang/Exception
    //   4834	4905	5635	java/lang/Exception
    //   2951	3170	5640	java/lang/Exception
    //   1077	1138	5645	java/lang/NullPointerException
    //   286	344	5650	java/lang/NullPointerException
    //   4601	4701	5697	java/lang/Exception
    //   4704	4735	5697	java/lang/Exception
  }

  public AppConfigure parseSAX()
  {
    try
    {
      if (this.xml.length() > 0)
        Xml.parse(this.xml, new AppConfigureHandler(null));
      while (true)
      {
        return this.appConfig;
        Xml.parse(this.xmlStream, Xml.Encoding.UTF_8, new AppConfigureHandler(null));
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.d("", "");
    }
  }

  private class AppConfigureHandler extends DefaultHandler
  {
    private AppAdvData advData = new AppAdvData();
    private String barName = "";
    private String barTitle = "";
    private WidgetUIButton button = null;
    private BarDesigner designer = null;
    private String func = "";
    private GPSItem gpsItem = null;
    private WidgetUIImage image = null;
    private boolean inAdv = false;
    private boolean inPlugin = false;
    private boolean inPushNs = false;
    private String itemPosition = "";
    private WidgetUILabel label = null;
    private LoginScreen loginScreen = null;
    HashMap<String, ArrayList<String>> plugins = new HashMap();
    private StringBuilder sb = new StringBuilder();
    private WidgetUITab tab = null;
    private String type = "";
    private Widget widget = null;

    private AppConfigureHandler()
    {
    }

    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      super.characters(paramArrayOfChar, paramInt1, paramInt2);
      this.sb.append(new String(paramArrayOfChar, paramInt1, paramInt2));
    }

    public void endDocument()
      throws SAXException
    {
      super.endDocument();
      for (int i = 0; i < AppConfigureParser.this.appConfig.getWidgetsCount(); i++)
      {
        Widget localWidget = AppConfigureParser.this.appConfig.getWidgetAtIndex(i);
        if (!this.plugins.containsKey(new Integer(localWidget.getOrder()).toString()))
          continue;
        ArrayList localArrayList = (ArrayList)this.plugins.get(new Integer(localWidget.getOrder()).toString());
        for (int j = 0; j < localArrayList.size(); j++)
          localWidget.addParameter((String)localArrayList.get(j), new Boolean(true));
      }
    }

    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      int i = 1;
      super.endElement(paramString1, paramString2, paramString3);
      if (paramString2.equalsIgnoreCase("gaUserCode"))
        AppConfigureParser.this.appConfig.setGoogleAnalyticsId(this.sb.toString().trim());
      while (true)
      {
        this.sb.setLength(0);
        return;
        if (paramString2.equalsIgnoreCase("appname"))
        {
          AppConfigureParser.this.appConfig.setAppName(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("rgbcolor"))
        {
          AppConfigureParser.this.appConfig.setBackgroundColor(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("backimage"))
        {
          AppConfigureParser.this.appConfig.setBackgroundImageUrl(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("backimagedata"))
        {
          AppConfigureParser.this.appConfig.setmBackgorundImageData(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("showlink"))
        {
          int m = (int)Float.parseFloat(this.sb.toString().trim());
          AppConfigure localAppConfigure = AppConfigureParser.this.appConfig;
          if (m > 0);
          while (true)
          {
            localAppConfigure.setShowLink(i);
            break;
            i = 0;
          }
        }
        if (paramString2.equalsIgnoreCase("autorun"))
        {
          try
          {
            AppConfigureParser.this.appConfig.setDefaultOrder(Integer.parseInt(this.sb.toString().trim()));
          }
          catch (Throwable localThrowable)
          {
            Log.d("", "");
          }
          continue;
        }
        if (paramString2.equalsIgnoreCase("splashscreen"))
        {
          AppConfigureParser.this.appConfig.setSplashScreen(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("dateformat"))
        {
          int k = (int)Float.parseFloat(this.sb.toString().trim());
          AppConfigureParser.this.appConfig.setDateFormat(k);
          continue;
        }
        if (paramString2.equalsIgnoreCase("showSidebar"))
        {
          if ((int)Float.parseFloat(this.sb.toString().trim()) == i)
          {
            AppConfigureParser.this.appConfig.setShowSidebar(i);
            continue;
          }
          AppConfigureParser.this.appConfig.setShowSidebar(false);
          continue;
        }
        if (paramString2.equalsIgnoreCase("showmenu"))
        {
          int j = (int)Float.parseFloat(this.sb.toString().trim());
          AppConfigureParser.this.appConfig.setShowMenu(j);
          continue;
        }
        if (paramString2.equalsIgnoreCase("image"))
        {
          AppConfigureParser.this.appConfig.addImage(this.image);
          AppConfigureParser.this.appConfig.addControl(this.image);
          this.image = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("x"))
        {
          if (this.image != null)
          {
            try
            {
              this.image.setLeft((int)Float.parseFloat(this.sb.toString().trim()));
            }
            catch (NumberFormatException localNumberFormatException6)
            {
            }
            continue;
          }
          if (this.label != null)
          {
            try
            {
              this.label.setLeft((int)Float.parseFloat(this.sb.toString().trim()));
            }
            catch (NumberFormatException localNumberFormatException5)
            {
            }
            continue;
          }
          if (this.button == null)
            continue;
          try
          {
            this.button.setLeft((int)Float.parseFloat(this.sb.toString().trim()));
          }
          catch (NumberFormatException localNumberFormatException4)
          {
          }
          continue;
        }
        if (paramString2.equalsIgnoreCase("y"))
        {
          if (this.image != null)
          {
            try
            {
              this.image.setTop((int)Float.parseFloat(this.sb.toString().trim()));
            }
            catch (NumberFormatException localNumberFormatException3)
            {
            }
            continue;
          }
          if (this.label != null)
          {
            try
            {
              this.label.setTop((int)Float.parseFloat(this.sb.toString().trim()));
            }
            catch (NumberFormatException localNumberFormatException2)
            {
            }
            continue;
          }
          if (this.button == null)
            continue;
          try
          {
            this.button.setTop((int)Float.parseFloat(this.sb.toString().trim()));
          }
          catch (NumberFormatException localNumberFormatException1)
          {
          }
          continue;
        }
        if (paramString2.equalsIgnoreCase("width"))
        {
          if (this.image != null)
          {
            this.image.setWidth((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (this.label != null)
          {
            this.label.setWidth((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (this.button == null)
            continue;
          this.button.setWidth((int)Float.parseFloat(this.sb.toString().trim()));
          continue;
        }
        if (paramString2.equalsIgnoreCase("height"))
        {
          if (this.image != null)
          {
            this.image.setHeight((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (this.label != null)
          {
            this.label.setHeight((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (this.button == null)
            continue;
          this.button.setHeight((int)Float.parseFloat(this.sb.toString().trim()));
          continue;
        }
        if (paramString2.equalsIgnoreCase("url"))
        {
          if (this.image != null)
          {
            this.image.setSourceUrl(this.sb.toString().trim());
            continue;
          }
          if (this.inAdv)
          {
            this.advData.setAdvType("url");
            this.advData.setAdvContent(this.sb.toString().trim());
            continue;
          }
          if (this.widget == null)
            continue;
          this.widget.setPluginUrl(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("imagedata"))
        {
          if (this.image == null)
            continue;
          this.image.setmImageData(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("pushns"))
        {
          this.inPushNs = false;
          continue;
        }
        if (paramString2.equalsIgnoreCase("android_account"))
        {
          if (!this.inPushNs)
            continue;
          AppConfigureParser.this.appConfig.setPushNotificationAccount(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("sender_id"))
        {
          if (!this.inPushNs)
            continue;
          AppConfigureParser.this.appConfig.setPushNotificationAccount(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("gps_object"))
        {
          AppConfigureParser.this.appConfig.addGPSNotification(this.gpsItem);
          this.gpsItem = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("latitude"))
        {
          if (this.gpsItem == null)
            continue;
          this.gpsItem.setLatitude(new Double(this.sb.toString().trim()).doubleValue());
          continue;
        }
        if (paramString2.equalsIgnoreCase("longitude"))
        {
          if (this.gpsItem == null)
            continue;
          this.gpsItem.setLongitude(new Double(this.sb.toString().trim()).doubleValue());
          continue;
        }
        if (paramString2.equalsIgnoreCase("title"))
        {
          if (this.gpsItem != null)
          {
            this.gpsItem.setTitle(Html.fromHtml(this.sb.toString().trim()).toString());
            continue;
          }
          if (this.widget != null)
          {
            this.widget.setTitle(Html.fromHtml(this.sb.toString().trim()).toString());
            continue;
          }
          if (this.label == null)
            continue;
          this.label.setTitle(Html.fromHtml(this.sb.toString().trim()).toString());
          continue;
        }
        if (paramString2.equalsIgnoreCase("description"))
        {
          if (this.gpsItem == null)
            continue;
          this.gpsItem.setDescription(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("radius"))
        {
          if (this.gpsItem == null)
            continue;
          this.gpsItem.setRadius(new Integer(this.sb.toString().trim()).intValue());
          continue;
        }
        if (paramString2.equalsIgnoreCase("show"))
        {
          if ((this.gpsItem == null) || (!this.sb.toString().trim().equalsIgnoreCase("plural")))
            continue;
          this.gpsItem.setCountOfView(GPSItem.Show.PLURAL);
          continue;
        }
        if (paramString2.equalsIgnoreCase("adv"))
        {
          this.inAdv = false;
          continue;
        }
        if (paramString2.equalsIgnoreCase("type"))
        {
          if (this.inAdv)
          {
            this.advData.setAdvType(this.sb.toString().trim());
            continue;
          }
          if (this.widget != null)
          {
            this.widget.setmPluginType(this.sb.toString().trim());
            continue;
          }
          if (!this.inPlugin)
            continue;
          this.type = this.sb.toString().trim();
          continue;
        }
        if (paramString2.equalsIgnoreCase("url_on_click"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvRedirect(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("html_on_click"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvRedirect(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("html"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvType("html");
          this.advData.setAdvContent(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("uid"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvContent(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("apId"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvApId(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("adSpaceId"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvAdSpaceId(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("publisherId"))
        {
          if (!this.inAdv)
            continue;
          this.advData.setAdvPublisherId(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("label"))
        {
          if (this.button != null)
          {
            this.button.setTitle(Html.fromHtml(this.sb.toString().trim()).toString());
            continue;
          }
          if (this.tab != null)
          {
            this.tab.setLabel(this.sb.toString().trim());
            continue;
          }
          AppConfigureParser.this.appConfig.addLabel(this.label);
          AppConfigureParser.this.appConfig.addControl(this.label);
          this.label = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("size"))
        {
          if (this.label != null)
          {
            this.label.setFontSize((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (this.button == null)
            continue;
          this.button.setFontSize((int)Float.parseFloat(this.sb.toString().trim()));
          continue;
        }
        if (paramString2.equalsIgnoreCase("color"))
        {
          if (this.label != null)
          {
            this.label.setColor(this.sb.toString().trim());
            continue;
          }
          if (this.button == null)
            continue;
          this.button.setColor(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("style"))
        {
          if (this.label != null)
          {
            this.label.setStyle(this.sb.toString().trim());
            continue;
          }
          if (this.button == null)
            continue;
          this.button.setStyle(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("button"))
        {
          AppConfigureParser.this.appConfig.addButton(this.button);
          AppConfigureParser.this.appConfig.addControl(this.button);
          this.button = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("icon"))
        {
          if (this.button != null)
          {
            this.button.setImageSourceUrl(this.sb.toString().trim());
            continue;
          }
          if (this.tab == null)
            continue;
          this.tab.setIconUrl(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("icondata"))
        {
          if (this.button != null)
          {
            this.button.setmImageData(this.sb.toString().trim());
            continue;
          }
          if (this.tab == null)
            continue;
          this.tab.setmIconData(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("align"))
        {
          if (this.button != null)
            this.button.setAlign(this.sb.toString().trim());
          if (this.label == null)
            continue;
          this.label.setAlign(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("func"))
        {
          if (this.button != null)
          {
            this.button.setOrder((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (this.tab != null)
          {
            this.tab.setOrder((int)Float.parseFloat(this.sb.toString().trim()));
            continue;
          }
          if (!this.inPlugin)
            continue;
          this.func = this.sb.toString().trim();
          continue;
        }
        if (paramString2.equalsIgnoreCase("tabitem"))
        {
          AppConfigureParser.this.appConfig.addTab(this.tab);
          this.tab = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("plugin"))
        {
          if (this.plugins.containsKey(this.func))
            ((ArrayList)this.plugins.get(this.func)).add(this.type);
          while (true)
          {
            this.inPlugin = false;
            break;
            this.plugins.put(this.func, new ArrayList());
            ((ArrayList)this.plugins.get(this.func)).add(this.type);
          }
        }
        if (paramString2.equalsIgnoreCase("widget"))
        {
          AppConfigureParser.this.appConfig.addWidget(this.widget);
          this.widget = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("order"))
        {
          if (this.widget == null)
            continue;
          this.widget.setOrder((int)Float.parseFloat(this.sb.toString().trim()));
          continue;
        }
        if (paramString2.equalsIgnoreCase("name"))
        {
          if (this.widget == null)
            continue;
          this.widget.setPluginName(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("package"))
        {
          if (this.widget == null)
            continue;
          this.widget.setPluginPackage(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("hash"))
        {
          if (this.widget == null)
            continue;
          this.widget.setPluginHash(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("background"))
        {
          if (this.widget == null)
            continue;
          this.widget.setBackground(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("textcolor"))
        {
          if (this.widget == null)
            continue;
          this.widget.setTextColor(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("data"))
        {
          if (this.widget == null)
            continue;
          this.widget.setPluginXmlData(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("subtitle"))
        {
          if (this.widget == null)
            continue;
          this.widget.setSubtitle(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("favicon"))
        {
          if (this.widget == null)
            continue;
          this.widget.setFaviconURL(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("sidebar"))
        {
          if (this.widget == null)
            continue;
          if (this.sb.toString().trim().compareTo("1") == 0)
          {
            this.widget.setAddToSidebar(i);
            continue;
          }
          this.widget.setAddToSidebar(false);
          continue;
        }
        if (paramString2.equalsIgnoreCase("navBar"))
        {
          if (this.designer == null)
            continue;
          AppConfigureParser.this.appConfig.setNavBarDesign(this.designer);
          this.designer = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("tabBar"))
        {
          if (this.designer == null)
            continue;
          AppConfigureParser.this.appConfig.setTabBarDesign(this.designer);
          this.designer = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("bottomBar"))
        {
          if (this.designer == null)
            continue;
          AppConfigureParser.this.appConfig.setBottomBarDesign(this.designer);
          this.designer = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("item"))
        {
          this.itemPosition = null;
          continue;
        }
        if (paramString2.equalsIgnoreCase("logo"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setLogo(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("useFacebook"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setUseFacebook(Boolean.valueOf(this.sb.toString().trim().equalsIgnoreCase("true")));
          continue;
        }
        if (paramString2.equalsIgnoreCase("useTwitter"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setUseTwitter(Boolean.valueOf(this.sb.toString().trim().equalsIgnoreCase("true")));
          continue;
        }
        if (paramString2.equalsIgnoreCase("useEmail"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setUseEmail(Boolean.valueOf(this.sb.toString().trim().equalsIgnoreCase("true")));
          continue;
        }
        if (paramString2.equalsIgnoreCase("allowSignUp"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setAllowSignup(Boolean.valueOf(this.sb.toString().trim().equalsIgnoreCase("true")));
          continue;
        }
        if (paramString2.equalsIgnoreCase("signupEndpoint"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setSignupEndpoint(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("loginEndpoint"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setLoginEndpoint(this.sb.toString().trim());
          continue;
        }
        if (paramString2.equalsIgnoreCase("recoveryPasswordEndpoint"))
        {
          AppConfigureParser.this.appConfig.getLoginScreen().setRecoveryPasswordEndpoint(this.sb.toString().trim());
          continue;
        }
        if (!paramString2.equalsIgnoreCase("appId"))
          continue;
        AppConfigureParser.this.appConfig.getLoginScreen().setAppId(this.sb.toString().trim());
      }
    }

    public void startDocument()
      throws SAXException
    {
      super.startDocument();
      AppConfigureParser.this.appConfig.setAppAdv(this.advData);
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      super.startElement(paramString1, paramString2, paramString3, paramAttributes);
      if (paramString2.equalsIgnoreCase("loginScreen"))
        AppConfigureParser.this.appConfig.setLoginScreen(new LoginScreen());
      while (true)
      {
        return;
        if (paramString2.equalsIgnoreCase("image"))
        {
          this.image = new WidgetUIImage();
          return;
        }
        if (paramString2.equalsIgnoreCase("pushns"))
        {
          this.inPushNs = true;
          return;
        }
        if (paramString2.equalsIgnoreCase("gps_object"))
        {
          this.gpsItem = new GPSItem();
          return;
        }
        if (paramString2.equalsIgnoreCase("adv"))
        {
          this.inAdv = true;
          return;
        }
        if (paramString2.equalsIgnoreCase("label"))
        {
          if ((this.button != null) || (this.tab != null))
            continue;
          this.label = new WidgetUILabel();
          return;
        }
        if (paramString2.equalsIgnoreCase("button"))
        {
          this.button = new WidgetUIButton();
          return;
        }
        if (paramString2.equalsIgnoreCase("tabitem"))
        {
          this.tab = new WidgetUITab();
          return;
        }
        if (paramString2.equalsIgnoreCase("plugin"))
        {
          this.inPlugin = true;
          return;
        }
        if (paramString2.equalsIgnoreCase("widget"))
        {
          this.widget = new Widget();
          this.widget.setAppName(AppConfigureParser.this.appConfig.getAppName());
          this.widget.setDateFormat(AppConfigureParser.this.appConfig.getDateFormat());
          return;
        }
        if (paramString2.equalsIgnoreCase("navBar"))
        {
          this.designer = new BarDesigner();
          this.barName = "navBar";
          try
          {
            this.designer.color = Color.parseColor(paramAttributes.getValue("color"));
            return;
          }
          catch (Exception localException15)
          {
            return;
          }
        }
        if (paramString2.equalsIgnoreCase("tabBar"))
        {
          this.designer = new BarDesigner();
          this.barName = "tabBar";
          try
          {
            this.designer.color = Color.parseColor(paramAttributes.getValue("color"));
            return;
          }
          catch (Exception localException14)
          {
            return;
          }
        }
        if (paramString2.equalsIgnoreCase("bottomBar"))
        {
          this.designer = new BarDesigner();
          this.barName = "bottomBar";
          try
          {
            this.designer.color = Color.parseColor(paramAttributes.getValue("color"));
            return;
          }
          catch (Exception localException13)
          {
            return;
          }
        }
        if (paramString2.equalsIgnoreCase("title"))
        {
          if (this.designer == null)
            continue;
          this.barTitle = "title";
        }
        try
        {
          this.designer.titleDesign.textColor = Color.parseColor(paramAttributes.getValue("textColor").trim());
          try
          {
            label471: this.designer.titleDesign.selectedColor = Color.parseColor(paramAttributes.getValue("selectionColor").trim());
            label497: this.designer.titleDesign.textAlignment = paramAttributes.getValue("textAlignment").trim();
            this.designer.titleDesign.numberOfLines = Integer.parseInt(paramAttributes.getValue("numberOfLines").trim());
            return;
            if (paramString2.equalsIgnoreCase("item"))
            {
              if (this.designer == null)
                continue;
              this.barTitle = "item";
              this.itemPosition = paramAttributes.getValue("position");
              if (this.itemPosition != null);
            }
            try
            {
              this.designer.itemDesign.textColor = Color.parseColor(paramAttributes.getValue("textColor").trim());
              try
              {
                label618: this.designer.itemDesign.selectedColor = Color.parseColor(paramAttributes.getValue("selectionColor").trim());
                try
                {
                  label644: this.designer.itemDesign.textAlignment = paramAttributes.getValue("textAlignment").trim();
                  this.designer.itemDesign.numberOfLines = Integer.parseInt(paramAttributes.getValue("numberOfLines").trim());
                  return;
                }
                catch (Exception localException10)
                {
                  return;
                }
                if (this.itemPosition.compareTo("left") == 0);
                try
                {
                  this.designer.leftButtonDesign.textColor = Color.parseColor(paramAttributes.getValue("textColor").trim());
                  try
                  {
                    label736: this.designer.leftButtonDesign.selectedColor = Color.parseColor(paramAttributes.getValue("selectionColor").trim());
                    try
                    {
                      label762: this.designer.leftButtonDesign.textAlignment = paramAttributes.getValue("textAlignment").trim();
                      this.designer.leftButtonDesign.numberOfLines = Integer.parseInt(paramAttributes.getValue("numberOfLines").trim());
                      return;
                    }
                    catch (Exception localException7)
                    {
                      return;
                    }
                    if (this.itemPosition.compareTo("right") != 0)
                      continue;
                    try
                    {
                      this.designer.rightButtonDesign.textColor = Color.parseColor(paramAttributes.getValue("textColor").trim());
                      try
                      {
                        label854: this.designer.rightButtonDesign.selectedColor = Color.parseColor(paramAttributes.getValue("selectionColor").trim());
                        try
                        {
                          label880: this.designer.rightButtonDesign.textAlignment = paramAttributes.getValue("textAlignment").trim();
                          this.designer.rightButtonDesign.numberOfLines = Integer.parseInt(paramAttributes.getValue("numberOfLines").trim());
                          return;
                        }
                        catch (Exception localException4)
                        {
                          return;
                        }
                        if ((!paramString2.equalsIgnoreCase("font")) || (this.designer == null))
                          continue;
                        if (this.barTitle.compareToIgnoreCase("title") == 0);
                        try
                        {
                          this.designer.titleDesign.fontFamily = paramAttributes.getValue("family").trim();
                          this.designer.titleDesign.fontSize = Integer.parseInt(paramAttributes.getValue("size").trim());
                          this.designer.titleDesign.fontWeight = paramAttributes.getValue("weight").trim();
                          while (true)
                          {
                            label1035: this.barTitle = "";
                            return;
                            if (this.barTitle.compareToIgnoreCase("item") != 0)
                              continue;
                            if (this.itemPosition == null)
                            {
                              this.designer.itemDesign.fontFamily = paramAttributes.getValue("family").trim();
                              this.designer.itemDesign.fontSize = Integer.parseInt(paramAttributes.getValue("size").trim());
                              this.designer.itemDesign.fontWeight = paramAttributes.getValue("weight").trim();
                              continue;
                            }
                            if (this.itemPosition.compareTo("left") == 0)
                            {
                              this.designer.leftButtonDesign.fontFamily = paramAttributes.getValue("family").trim();
                              this.designer.leftButtonDesign.fontSize = Integer.parseInt(paramAttributes.getValue("size").trim());
                              this.designer.leftButtonDesign.fontWeight = paramAttributes.getValue("weight").trim();
                              continue;
                            }
                            if (this.itemPosition.compareTo("right") != 0)
                              continue;
                            this.designer.rightButtonDesign.fontFamily = paramAttributes.getValue("family").trim();
                            this.designer.rightButtonDesign.fontSize = Integer.parseInt(paramAttributes.getValue("size").trim());
                            this.designer.rightButtonDesign.fontWeight = paramAttributes.getValue("weight").trim();
                          }
                        }
                        catch (Exception localException1)
                        {
                          break label1035;
                        }
                      }
                      catch (Exception localException3)
                      {
                        break label880;
                      }
                    }
                    catch (Exception localException2)
                    {
                      break label854;
                    }
                  }
                  catch (Exception localException6)
                  {
                    break label762;
                  }
                }
                catch (Exception localException5)
                {
                  break label736;
                }
              }
              catch (Exception localException9)
              {
                break label644;
              }
            }
            catch (Exception localException8)
            {
              break label618;
            }
          }
          catch (Exception localException12)
          {
            break label497;
          }
        }
        catch (Exception localException11)
        {
          break label471;
        }
      }
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.u846253p1176378.AppConfigureParser
 * JD-Core Version:    0.6.0
 */