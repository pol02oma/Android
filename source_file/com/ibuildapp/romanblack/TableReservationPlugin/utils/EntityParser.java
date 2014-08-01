package com.ibuildapp.romanblack.TableReservationPlugin.utils;

import android.graphics.Color;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;
import android.util.Xml.Encoding;
import com.appbuilder.sdk.android.Utils;
import com.ibuildapp.romanblack.TableReservationPlugin.TableReservationInfo;
import java.io.ByteArrayInputStream;

public class EntityParser
{
  private TableReservationInfo parsedXML;
  private String xml = "";

  public EntityParser(String paramString)
  {
    this.xml = paramString;
    this.parsedXML = new TableReservationInfo();
  }

  public TableReservationInfo getTableReservationInfo()
  {
    return this.parsedXML;
  }

  public void parse()
  {
    RootElement localRootElement = new RootElement("data");
    localRootElement.setEndElementListener(new EndElementListener()
    {
      public void end()
      {
      }
    });
    localRootElement.getChild("color1").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.colorskin.color1 = Color.parseColor(paramString);
      }
    });
    localRootElement.getChild("color2").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.colorskin.color2 = Color.parseColor(paramString);
      }
    });
    localRootElement.getChild("starttime").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
        {
          int i = Integer.parseInt(new String(paramString));
          int j = i / 3600;
          int k = i / 60 - j * 60;
          EntityParser.this.parsedXML.setStartTime(j, k);
        }
      }
    });
    localRootElement.getChild("endtime").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
        {
          int i = Integer.parseInt(new String(paramString));
          int j = i / 3600;
          int k = i / 60 - j * 60;
          EntityParser.this.parsedXML.setEndTime(j, k);
        }
      }
    });
    localRootElement.getChild("timeoffset").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setOffsetTime(Integer.parseInt(new String(paramString)));
      }
    });
    localRootElement.getChild("maxpersons").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setMaxpersons(Integer.parseInt(new String(paramString)));
      }
    });
    localRootElement.getChild("longitude").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setLongitude(Double.valueOf(Double.parseDouble(paramString)));
      }
    });
    localRootElement.getChild("latitude").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setLatitude(Double.valueOf(Double.parseDouble(paramString)));
      }
    });
    localRootElement.getChild("restaurantname").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantName(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("restaurantimageurl").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantimageurl(paramString);
      }
    });
    localRootElement.getChild("restaurantaddress").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantadress(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("restaurantgreeting").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantGreeting(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("restaurantmail").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantmail(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("restaurantphone").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantphone(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("emailconfirmation").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
        {
          if (paramString.equals("1"))
            EntityParser.this.parsedXML.setEmailConfirmation(true);
        }
        else
          return;
        EntityParser.this.parsedXML.setEmailConfirmation(false);
      }
    });
    localRootElement.getChild("smsconfirmation").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
        {
          if (paramString.equals("1"))
            EntityParser.this.parsedXML.setSmsConfirmation(true);
        }
        else
          return;
        EntityParser.this.parsedXML.setSmsConfirmation(false);
      }
    });
    localRootElement.getChild("parking").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setRestaurantadditional(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("app_id").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setAppid(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("module_id").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setModuleid(Utils.removeSpec(paramString));
      }
    });
    localRootElement.getChild("restaurantkitchen").setEndTextElementListener(new EndTextElementListener()
    {
      public void end(String paramString)
      {
        if (paramString != null)
          EntityParser.this.parsedXML.setKitchen(Utils.removeSpec(paramString));
      }
    });
    try
    {
      Xml.parse(new ByteArrayInputStream(this.xml.getBytes()), Xml.Encoding.UTF_8, localRootElement.getContentHandler());
      return;
    }
    catch (Exception localException)
    {
      Log.d("parse", localException.getMessage());
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.ibuildapp.romanblack.TableReservationPlugin.utils.EntityParser
 * JD-Core Version:    0.6.0
 */