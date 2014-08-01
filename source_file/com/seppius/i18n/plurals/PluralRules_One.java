package com.seppius.i18n.plurals;

public class PluralRules_One extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    if (paramInt == 1)
      return 2;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_One
 * JD-Core Version:    0.6.0
 */