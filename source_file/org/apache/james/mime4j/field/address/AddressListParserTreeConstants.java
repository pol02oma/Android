package org.apache.james.mime4j.field.address;

public abstract interface AddressListParserTreeConstants
{
  public static final int JJTADDRESS = 2;
  public static final int JJTADDRESS_LIST = 1;
  public static final int JJTADDR_SPEC = 9;
  public static final int JJTANGLE_ADDR = 6;
  public static final int JJTDOMAIN = 11;
  public static final int JJTGROUP_BODY = 5;
  public static final int JJTLOCAL_PART = 10;
  public static final int JJTMAILBOX = 3;
  public static final int JJTNAME_ADDR = 4;
  public static final int JJTPHRASE = 8;
  public static final int JJTROUTE = 7;
  public static final int JJTVOID;
  public static final String[] jjtNodeName = { "void", "address_list", "address", "mailbox", "name_addr", "group_body", "angle_addr", "route", "phrase", "addr_spec", "local_part", "domain" };
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.AddressListParserTreeConstants
 * JD-Core Version:    0.6.0
 */