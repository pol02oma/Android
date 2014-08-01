package com.seppius.i18n.plurals;

public class PluralRules_Maltese extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = paramInt % 100;
    if (paramInt == 1)
      return 2;
    if ((paramInt == 0) || ((i >= 2) && (i <= 10)))
      return 8;
    if ((i >= 11) && (i <= 19))
      return 16;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Maltese
 * JD-Core Version:    0.6.0
 */