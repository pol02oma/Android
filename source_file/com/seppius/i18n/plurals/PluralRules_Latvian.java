package com.seppius.i18n.plurals;

public class PluralRules_Latvian extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    if (paramInt == 0)
      return 1;
    if ((paramInt % 10 == 1) && (paramInt % 100 != 11))
      return 2;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Latvian
 * JD-Core Version:    0.6.0
 */