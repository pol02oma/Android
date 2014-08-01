package com.seppius.i18n.plurals;

public class PluralRules_Polish extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = paramInt % 100;
    int j = paramInt % 10;
    if (paramInt == 1)
      return 2;
    if ((j >= 2) && (j <= 4) && ((i < 12) || (i > 14)) && ((i < 22) || (i > 24)))
      return 8;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Polish
 * JD-Core Version:    0.6.0
 */