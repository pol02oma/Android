package org.apache.james.mime4j.field.address;

import java.io.StringReader;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.address.Address;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.Group;
import org.apache.james.mime4j.dom.address.Mailbox;

public class AddressBuilder
{
  public static final AddressBuilder DEFAULT = new AddressBuilder();

  public Address parseAddress(String paramString)
    throws ParseException
  {
    return parseAddress(paramString, DecodeMonitor.STRICT);
  }

  public Address parseAddress(String paramString, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    AddressListParser localAddressListParser = new AddressListParser(new StringReader(paramString));
    return Builder.getInstance().buildAddress(localAddressListParser.parseAddress(), paramDecodeMonitor);
  }

  public AddressList parseAddressList(String paramString)
    throws ParseException
  {
    return parseAddressList(paramString, DecodeMonitor.STRICT);
  }

  public AddressList parseAddressList(String paramString, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    AddressListParser localAddressListParser = new AddressListParser(new StringReader(paramString));
    return Builder.getInstance().buildAddressList(localAddressListParser.parseAddressList(), paramDecodeMonitor);
  }

  public Group parseGroup(String paramString)
    throws ParseException
  {
    return parseGroup(paramString, DecodeMonitor.STRICT);
  }

  public Group parseGroup(String paramString, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    Address localAddress = parseAddress(paramString, paramDecodeMonitor);
    if (!(localAddress instanceof Group))
      throw new ParseException("Not a group address");
    return (Group)localAddress;
  }

  public Mailbox parseMailbox(String paramString)
    throws ParseException
  {
    return parseMailbox(paramString, DecodeMonitor.STRICT);
  }

  public Mailbox parseMailbox(String paramString, DecodeMonitor paramDecodeMonitor)
    throws ParseException
  {
    AddressListParser localAddressListParser = new AddressListParser(new StringReader(paramString));
    return Builder.getInstance().buildMailbox(localAddressListParser.parseMailbox(), paramDecodeMonitor);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.AddressBuilder
 * JD-Core Version:    0.6.0
 */