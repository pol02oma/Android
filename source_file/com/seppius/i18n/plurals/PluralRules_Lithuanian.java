package com.seppius.i18n.plurals;

public class PluralRules_Lithuanian extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = paramInt % 100;
    int j = paramInt % 10;
    if ((j == 1) && ((i < 11) || (i > 19)))
      return 2;
    if ((j >= 2) && (j <= 9) && ((i < 11) || (i > 19)))
      return 8;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Lithuanian
 * JD-Core Version:    0.6.0
 */