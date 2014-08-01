package com.ibuildapp.romanblack.NewsPlugin;

import android.util.Log;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FeedItem
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String anounce = "";
  private int color = -12303292;
  private int dateformat = 0;
  private String description = "";
  private String encoding = "";
  private String feedUrl = "";
  private boolean imageFromDescription = false;
  private String imagePath = "";
  private String imageUrl = "";
  private String imageUrlAlt = "";
  private String indextext = "";
  private String link = "";
  private String mediaType = "";
  private String mediaUrl = "";
  private Date pubdate = null;
  private String title = "";

  private boolean validUrl(String paramString)
  {
    boolean bool = Pattern.compile("^(http:\\/\\/|https:\\/\\/)?([^\\.\\/]+\\.)*([a-zA-Z0-9])([a-zA-Z0-9-]*)\\.([a-zA-Z]{2,4})(\\/.*)?$", 2).matcher(paramString).matches();
    int i = 0;
    if (bool)
      i = 1;
    return i;
  }

  public void addMediaItem(String paramString1, String paramString2)
  {
    if (validUrl(paramString1))
    {
      this.mediaUrl = paramString1;
      this.mediaType = paramString2;
    }
  }

  public String getAnounce(int paramInt)
  {
    if (paramInt == 0)
      return this.anounce;
    if (paramInt > 30)
      paramInt -= 3;
    if (this.anounce.length() > paramInt)
      return this.anounce.substring(0, paramInt) + "...";
    return this.anounce;
  }

  public String getDescription()
  {
    return this.description;
  }

  public String getEncoding()
  {
    return this.encoding;
  }

  public String getImagePath()
  {
    return this.imagePath;
  }

  public String getImageUrl()
  {
    return this.imageUrl;
  }

  public String getImageUrlAlt()
  {
    return this.imageUrlAlt;
  }

  public String getIndextext()
  {
    return this.indextext;
  }

  public String getLink()
  {
    return this.link;
  }

  public String getMediaType()
  {
    return this.mediaType;
  }

  public String getMediaUrl()
  {
    return this.mediaUrl;
  }

  public String getPubdate(String paramString)
  {
    if (getPubdate() == null)
      return "";
    if (paramString.length() == 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Log.d("FeedItem", "Locale = " + Locale.getDefault());
      if (this.dateformat == 1)
      {
        if (Locale.getDefault().toString().equals("en_US"));
        for (SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("MMM dd yyyy hh:mm"); ; localSimpleDateFormat2 = new SimpleDateFormat("dd MMM yyyy HH:mm"))
        {
          localStringBuilder.append(localSimpleDateFormat2.format(this.pubdate));
          return localStringBuilder.toString();
        }
      }
      new SimpleDateFormat("dd MMM yyyy hh:mm");
      if (Locale.getDefault().toString().equals("en_US"));
      for (SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("MMM dd yyyy hh:mm"); ; localSimpleDateFormat1 = new SimpleDateFormat("dd MMM yyyy HH:mm"))
      {
        localStringBuilder.append(localSimpleDateFormat1.format(this.pubdate));
        break;
      }
    }
    SimpleDateFormat localSimpleDateFormat3 = new SimpleDateFormat(paramString);
    try
    {
      String str = localSimpleDateFormat3.format(getPubdate());
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public Date getPubdate()
  {
    return this.pubdate;
  }

  public int getTextColor()
  {
    return this.color;
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean hasImage()
  {
    return (this.imageUrl.length() > 0) || (this.imagePath.length() > 0);
  }

  public boolean hasMedia()
  {
    String str1 = getMediaUrl();
    int i = 0;
    if (str1 != null)
    {
      String str2 = getMediaType();
      i = 0;
      if (str2 != null)
      {
        int j = getMediaUrl().length();
        i = 0;
        if (j > 0)
        {
          int k = getMediaType().length();
          i = 0;
          if (k > 0)
            i = 1;
        }
      }
    }
    return i;
  }

  public boolean isDescriptionContainsImages()
  {
    return !Jsoup.parse(this.description).select("img").isEmpty();
  }

  public boolean isImageFromDescription()
  {
    return this.imageFromDescription;
  }

  public void setDateFormat(int paramInt)
  {
    int i = 1;
    monitorenter;
    if ((paramInt != 0) || (paramInt != i))
      i = 0;
    try
    {
      this.dateformat = i;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setDescription(String paramString)
  {
    monitorenter;
    try
    {
      String str = paramString.replaceAll("[\\n\\t]", "").trim();
      Document localDocument = Jsoup.parse(str);
      if ((this.imageUrl.length() == 0) || (this.imageFromDescription))
      {
        Element localElement = localDocument.select("img").first();
        if (localElement != null)
        {
          setImageUrl(localElement.attr("src"));
          this.imageFromDescription = true;
        }
        if (this.imageUrl == null)
        {
          this.imageUrl = "";
          this.imageFromDescription = false;
        }
      }
      this.anounce = localDocument.text();
      try
      {
        this.description = str;
        monitorexit;
        return;
      }
      catch (Exception localException)
      {
        while (true)
          this.description = localException.getMessage();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setEncoding(String paramString)
  {
    this.encoding = paramString;
  }

  public void setFeedUrl(String paramString)
  {
    this.feedUrl = paramString;
  }

  public void setImagePath(String paramString)
  {
    monitorenter;
    try
    {
      this.imagePath = paramString;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public void setImageUrl(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnull +149 -> 152
    //   6: aload_1
    //   7: ldc 38
    //   9: invokevirtual 160	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   12: istore_3
    //   13: iload_3
    //   14: ifne +138 -> 152
    //   17: new 243	java/net/URI
    //   20: dup
    //   21: aload_0
    //   22: getfield 69	com/ibuildapp/romanblack/NewsPlugin/FeedItem:feedUrl	Ljava/lang/String;
    //   25: invokespecial 244	java/net/URI:<init>	(Ljava/lang/String;)V
    //   28: astore 4
    //   30: new 243	java/net/URI
    //   33: dup
    //   34: aload_1
    //   35: invokespecial 244	java/net/URI:<init>	(Ljava/lang/String;)V
    //   38: astore 5
    //   40: aload 5
    //   42: invokevirtual 247	java/net/URI:getHost	()Ljava/lang/String;
    //   45: ifnull +24 -> 69
    //   48: aload 5
    //   50: invokevirtual 247	java/net/URI:getHost	()Ljava/lang/String;
    //   53: ldc 38
    //   55: invokevirtual 160	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +11 -> 69
    //   61: ldc 38
    //   63: ldc 38
    //   65: invokestatic 250	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   68: pop
    //   69: aload_0
    //   70: new 103	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   77: ldc 252
    //   79: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload 4
    //   84: invokevirtual 247	java/net/URI:getHost	()Ljava/lang/String;
    //   87: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: aload_1
    //   91: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: putfield 50	com/ibuildapp/romanblack/NewsPlugin/FeedItem:imageUrlAlt	Ljava/lang/String;
    //   100: aload 5
    //   102: invokevirtual 255	java/net/URI:getScheme	()Ljava/lang/String;
    //   105: ifnonnull +34 -> 139
    //   108: aload_1
    //   109: ldc_w 257
    //   112: invokevirtual 260	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   115: ifeq +40 -> 155
    //   118: new 103	java/lang/StringBuilder
    //   121: dup
    //   122: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   125: ldc_w 262
    //   128: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: aload_1
    //   132: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   138: astore_1
    //   139: aload_0
    //   140: aload_1
    //   141: putfield 48	com/ibuildapp/romanblack/NewsPlugin/FeedItem:imageUrl	Ljava/lang/String;
    //   144: ldc 38
    //   146: ldc 38
    //   148: invokestatic 250	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   151: pop
    //   152: aload_0
    //   153: monitorexit
    //   154: return
    //   155: aload_1
    //   156: ldc_w 264
    //   159: invokevirtual 260	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   162: ifeq +27 -> 189
    //   165: new 103	java/lang/StringBuilder
    //   168: dup
    //   169: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   172: ldc_w 266
    //   175: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: aload_1
    //   179: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: astore_1
    //   186: goto -47 -> 139
    //   189: aload_1
    //   190: ldc_w 268
    //   193: invokevirtual 260	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   196: ifeq +27 -> 223
    //   199: new 103	java/lang/StringBuilder
    //   202: dup
    //   203: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   206: ldc_w 270
    //   209: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: aload_1
    //   213: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: astore_1
    //   220: goto -81 -> 139
    //   223: new 103	java/lang/StringBuilder
    //   226: dup
    //   227: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   230: ldc 252
    //   232: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   235: aload_1
    //   236: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   239: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: astore 9
    //   244: aload 9
    //   246: astore_1
    //   247: goto -108 -> 139
    //   250: astore 6
    //   252: aload_0
    //   253: aload_1
    //   254: putfield 48	com/ibuildapp/romanblack/NewsPlugin/FeedItem:imageUrl	Ljava/lang/String;
    //   257: goto -105 -> 152
    //   260: astore_2
    //   261: aload_0
    //   262: monitorexit
    //   263: aload_2
    //   264: athrow
    //   265: astore 7
    //   267: goto -167 -> 100
    //
    // Exception table:
    //   from	to	target	type
    //   17	69	250	java/net/URISyntaxException
    //   69	100	250	java/net/URISyntaxException
    //   100	139	250	java/net/URISyntaxException
    //   139	152	250	java/net/URISyntaxException
    //   155	186	250	java/net/URISyntaxException
    //   189	220	250	java/net/URISyntaxException
    //   223	244	250	java/net/URISyntaxException
    //   6	13	260	finally
    //   17	69	260	finally
    //   69	100	260	finally
    //   100	139	260	finally
    //   139	152	260	finally
    //   155	186	260	finally
    //   189	220	260	finally
    //   223	244	260	finally
    //   252	257	260	finally
    //   69	100	265	java/lang/Exception
  }

  public void setIndextext(String paramString)
  {
    monitorenter;
    try
    {
      this.indextext = paramString.replaceAll("[\\n\\t]", "");
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setLink(String paramString)
  {
    monitorenter;
    try
    {
      this.link = paramString.replaceAll("[\\n\\t]", "");
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public String setPubdate(String paramString)
  {
    monitorenter;
    try
    {
      String str1 = paramString.replaceAll("[\\n\\t]", "").trim();
      if (str1.contains("+"))
        str1 = str1.substring(0, -1 + str1.lastIndexOf("+"));
      String[] arrayOfString = { "dd MMM yyyy hh:mm:ss", "dd MMM yyyy hh:mm:ss zzzzz", "yyyy.MM.dd G 'at' HH:mm:ss z", "EEE, MMM d, ''yy", "yyyyy.MMMMM.dd GGG hh:mm aaa", "EEE, d MMM yyyy HH:mm:ss Z", "yyMMddHHmmssZ", "d MMM yyyy HH:mm:ss z", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ss.SSSz", "EEE, d MMM yy HH:mm:ssz", "EEE, d MMM yy HH:mm:ss", "EEE, d MMM yy HH:mm z", "EEE, d MMM yy HH:mm Z", "EEE, d MMM yyyy HH:mm:ss z", "EEE, d MMM yyyy HH:mm:ss Z", "EEE, d MMM yyyy HH:mm:ss ZZZZ", "EEE, d MMM yyyy HH:mm z", "EEE, d MMM yyyy HH:mm Z", "d MMM yy HH:mm z", "d MMM yy HH:mm:ss z", "d MMM yyyy HH:mm z", "d MMM yyyy HH:mm:ss z" };
      int i = 0;
      SimpleDateFormat localSimpleDateFormat;
      if (i < arrayOfString.length)
        localSimpleDateFormat = new SimpleDateFormat(arrayOfString[i], Locale.ENGLISH);
      while (true)
      {
        try
        {
          if (getPubdate() != null)
            continue;
          this.pubdate = localSimpleDateFormat.parse(str1);
          str2 = arrayOfString[i];
          monitorexit;
          return str2;
        }
        catch (Exception localException)
        {
          i++;
        }
        break;
        String str2 = "";
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public void setPubdate(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ldc 209
    //   5: ldc 38
    //   7: invokevirtual 213	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   10: invokevirtual 216	java/lang/String:trim	()Ljava/lang/String;
    //   13: astore 4
    //   15: aload 4
    //   17: ldc_w 275
    //   20: invokevirtual 279	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   23: ifeq +21 -> 44
    //   26: aload 4
    //   28: iconst_0
    //   29: iconst_m1
    //   30: aload 4
    //   32: ldc_w 275
    //   35: invokevirtual 283	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   38: iadd
    //   39: invokevirtual 108	java/lang/String:substring	(II)Ljava/lang/String;
    //   42: astore 4
    //   44: new 162	java/text/SimpleDateFormat
    //   47: dup
    //   48: aload_2
    //   49: getstatic 339	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   52: invokespecial 342	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   55: astore 5
    //   57: aload_0
    //   58: invokevirtual 134	com/ibuildapp/romanblack/NewsPlugin/FeedItem:getPubdate	()Ljava/util/Date;
    //   61: ifnonnull +14 -> 75
    //   64: aload_0
    //   65: aload 5
    //   67: aload 4
    //   69: invokevirtual 345	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   72: putfield 46	com/ibuildapp/romanblack/NewsPlugin/FeedItem:pubdate	Ljava/util/Date;
    //   75: aload_0
    //   76: monitorexit
    //   77: return
    //   78: astore_3
    //   79: aload_0
    //   80: monitorexit
    //   81: aload_3
    //   82: athrow
    //   83: astore 6
    //   85: goto -10 -> 75
    //
    // Exception table:
    //   from	to	target	type
    //   2	44	78	finally
    //   44	57	78	finally
    //   57	75	78	finally
    //   57	75	83	java/lang/Exception
  }

  public void setTextColor(int paramInt)
  {
    monitorenter;
    if (paramInt != 0);
    try
    {
      this.color = paramInt;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString.trim().replaceAll("[\\n\\t]", "");
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.NewsPlugin.FeedItem
 * JD-Core Version:    0.6.0
 */