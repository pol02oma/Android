package com.seppius.i18n.plurals;

public class PluralRules_Balkan extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = paramInt % 100;
    int j = paramInt % 10;
    if ((j == 1) && (i != 11))
      return 2;
    if ((j >= 2) && (j <= 4) && ((i < 12) || (i > 14)))
      return 8;
    if ((j == 0) || ((j >= 5) && (j <= 9)) || ((i >= 11) && (i <= 14)))
      return 16;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Balkan
 * JD-Core Version:    0.6.0
 */