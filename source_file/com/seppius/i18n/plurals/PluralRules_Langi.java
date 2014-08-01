package com.seppius.i18n.plurals;

public class PluralRules_Langi extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    int i = 2;
    if (paramInt == 0)
      i = 1;
    do
      return i;
    while ((paramInt > 0) && (paramInt < i));
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Langi
 * JD-Core Version:    0.6.0
 */