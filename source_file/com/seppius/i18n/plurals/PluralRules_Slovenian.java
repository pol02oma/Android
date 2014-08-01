package com.seppius.i18n.plurals;

public class PluralRules_Slovenian extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = paramInt % 100;
    if (i == 1)
      return 2;
    if (i == 2)
      return 4;
    if ((i >= 3) && (i <= 4))
      return 8;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Slovenian
 * JD-Core Version:    0.6.0
 */