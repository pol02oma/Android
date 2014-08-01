package com.ibuildapp.romanblack.CameraPlugin;

import android.util.Log;
import android.util.Xml;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CameraParcer
{
  private String buttonLabel = "";
  private String buttonType = "";
  private String eMail = "";
  private String xmlData = "";

  public CameraParcer(String paramString)
  {
    this.xmlData = paramString;
  }

  public String getButtonLabel()
  {
    return this.buttonLabel;
  }

  public String getButtonType()
  {
    return this.buttonType;
  }

  public String getEmail()
  {
    return this.eMail;
  }

  public void parse()
  {
    CameraHandler localCameraHandler = new CameraHandler();
    try
    {
      Xml.parse(this.xmlData, localCameraHandler);
      this.buttonType = localCameraHandler.getType();
      this.buttonLabel = localCameraHandler.getLabel();
      this.eMail = localCameraHandler.geteMail();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.w(localException.toString(), localException.getMessage());
    }
  }

  class CameraHandler extends DefaultHandler
  {
    private String Type = "";
    private String eMail = "";
    private boolean inButton = false;
    private boolean inEmail = false;
    private boolean inLabel = false;
    private boolean inType = false;
    private String label = "";

    CameraHandler()
    {
    }

    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws SAXException
    {
      super.characters(paramArrayOfChar, paramInt1, paramInt2);
      if ((this.inButton) && (this.inLabel))
      {
        String str3 = new String(paramArrayOfChar, paramInt1, paramInt2);
        if ((!str3.equals("\n")) && (!str3.equals(" ")))
          this.label = str3;
      }
      String str1;
      do
      {
        do
          while (true)
          {
            return;
            if ((!this.inButton) || (!this.inType))
              break;
            String str2 = new String(paramArrayOfChar, paramInt1, paramInt2);
            if ((str2.equals("\n")) || (str2.equals(" ")))
              continue;
            this.Type = str2;
            return;
          }
        while ((!this.inButton) || (!this.inEmail));
        str1 = new String(paramArrayOfChar, paramInt1, paramInt2).trim();
      }
      while ((str1.equals("\n")) || (str1.equals(" ")) || (str1.equals("")));
      this.eMail = str1;
    }

    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      super.endElement(paramString1, paramString2, paramString3);
      if (paramString2.equalsIgnoreCase("button"))
        this.inButton = false;
      do
      {
        return;
        if (paramString2.equalsIgnoreCase("label"))
        {
          this.inLabel = false;
          return;
        }
        if (!paramString2.equalsIgnoreCase("type"))
          continue;
        this.inType = false;
        return;
      }
      while (!paramString2.equalsIgnoreCase("type"));
      this.inEmail = false;
    }

    public String getLabel()
    {
      return this.label;
    }

    public String getType()
    {
      return this.Type;
    }

    public String geteMail()
    {
      return this.eMail;
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      super.startElement(paramString1, paramString2, paramString3, paramAttributes);
      if (paramString2.equalsIgnoreCase("button"))
        this.inButton = true;
      do
      {
        return;
        if (paramString2.equalsIgnoreCase("label"))
        {
          this.inLabel = true;
          return;
        }
        if (!paramString2.equalsIgnoreCase("type"))
          continue;
        this.inType = true;
        return;
      }
      while (!paramString2.equalsIgnoreCase("email"));
      this.inEmail = true;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.CameraPlugin.CameraParcer
 * JD-Core Version:    0.6.0
 */