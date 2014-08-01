package com.appbuilder.sdk.android;

import java.io.Serializable;

public class BarDesigner
  implements Serializable
{
  public int color;
  public TitleDesign itemDesign = new TitleDesign();
  public TitleDesign leftButtonDesign = new TitleDesign();
  public TitleDesign rightButtonDesign = new TitleDesign();
  public TitleDesign titleDesign = new TitleDesign();

  public class TitleDesign
    implements Serializable
  {
    public String fontFamily = "";
    public int fontSize;
    public String fontWeight = "";
    public int numberOfLines;
    public int selectedColor;
    public String textAlignment = "";
    public int textColor;

    public TitleDesign()
    {
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.appbuilder.sdk.android.BarDesigner
 * JD-Core Version:    0.6.0
 */