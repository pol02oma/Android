package org.apache.james.mime4j.field.address;

public class ASTdomain extends SimpleNode
{
  public ASTdomain(int paramInt)
  {
    super(paramInt);
  }

  public ASTdomain(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTdomain
 * JD-Core Version:    0.6.0
 */