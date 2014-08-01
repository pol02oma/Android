package org.apache.james.mime4j.field;

import java.util.Collections;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.field.address.LenientAddressBuilder;
import org.apache.james.mime4j.stream.Field;
import org.apache.james.mime4j.stream.ParserCursor;
import org.apache.james.mime4j.stream.RawField;
import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class AddressListFieldLenientImpl extends AbstractField
  implements AddressListField
{
  public static final FieldParser<AddressListField> PARSER = new FieldParser()
  {
    public AddressListField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new AddressListFieldLenientImpl(paramField, paramDecodeMonitor);
    }
  };
  private AddressList addressList;
  private boolean parsed = false;

  AddressListFieldLenientImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    RawField localRawField = getRawField();
    ByteSequence localByteSequence = localRawField.getRaw();
    int i = 1 + localRawField.getDelimiterIdx();
    if (localByteSequence == null)
    {
      String str = localRawField.getBody();
      if (str == null)
      {
        this.addressList = new AddressList(Collections.emptyList(), true);
        return;
      }
      localByteSequence = ContentUtil.encode(str);
      i = 0;
    }
    ParserCursor localParserCursor = new ParserCursor(i, localByteSequence.length());
    this.addressList = LenientAddressBuilder.DEFAULT.parseAddressList(localByteSequence, localParserCursor);
  }

  public AddressList getAddressList()
  {
    if (!this.parsed)
      parse();
    return this.addressList;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.AddressListFieldLenientImpl
 * JD-Core Version:    0.6.0
 */