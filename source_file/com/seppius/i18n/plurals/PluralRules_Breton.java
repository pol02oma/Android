package com.seppius.i18n.plurals;

public class PluralRules_Breton extends PluralRules
{
  public int quantityForNumber(int paramInt)
  {
    if (paramInt == 0)
      return 1;
    if (paramInt == 1)
      return 2;
    if (paramInt == 2)
      return 4;
    if (paramInt == 3)
      return 8;
    if (paramInt == 6)
      return 16;
    return 0;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     com.seppius.i18n.plurals.PluralRules_Breton
 * JD-Core Version:    0.6.0
 */