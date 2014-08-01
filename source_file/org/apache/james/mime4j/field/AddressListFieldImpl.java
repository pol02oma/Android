package org.apache.james.mime4j.field;

import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.field.address.AddressBuilder;
import org.apache.james.mime4j.field.address.ParseException;
import org.apache.james.mime4j.stream.Field;

public class AddressListFieldImpl extends AbstractField
  implements AddressListField
{
  public static final FieldParser<AddressListField> PARSER = new FieldParser()
  {
    public AddressListField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new AddressListFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private AddressList addressList;
  private ParseException parseException;
  private boolean parsed = false;

  AddressListFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    String str = getBody();
    try
    {
      this.addressList = AddressBuilder.DEFAULT.parseAddressList(str, this.monitor);
      this.parsed = true;
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        this.parseException = localParseException;
    }
  }

  public AddressList getAddressList()
  {
    if (!this.parsed)
      parse();
    return this.addressList;
  }

  public ParseException getParseException()
  {
    if (!this.parsed)
      parse();
    return this.parseException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.AddressListFieldImpl
 * JD-Core Version:    0.6.0
 */