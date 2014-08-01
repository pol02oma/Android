package org.apache.james.mime4j.dom.field;

import org.apache.james.mime4j.dom.address.AddressList;

public abstract interface AddressListField extends ParsedField
{
  public abstract AddressList getAddressList();
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.dom.field.AddressListField
 * JD-Core Version:    0.6.0
 */