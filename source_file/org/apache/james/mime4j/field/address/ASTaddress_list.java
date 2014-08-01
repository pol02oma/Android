package org.apache.james.mime4j.field.address;

public class ASTaddress_list extends SimpleNode
{
  public ASTaddress_list(int paramInt)
  {
    super(paramInt);
  }

  public ASTaddress_list(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTaddress_list
 * JD-Core Version:    0.6.0
 */