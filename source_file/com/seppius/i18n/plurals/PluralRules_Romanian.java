package com.seppius.i18n.plurals;

public class PluralRules_Romanian extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = paramInt % 100;
    if (paramInt == 1)
      return 2;
    if ((paramInt == 0) || ((i >= 1) && (i <= 19)))
      return 8;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Romanian
 * JD-Core Version:    0.6.0
 */