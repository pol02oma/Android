package org.apache.james.mime4j.field.address;

public class ASTangle_addr extends SimpleNode
{
  public ASTangle_addr(int paramInt)
  {
    super(paramInt);
  }

  public ASTangle_addr(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTangle_addr
 * JD-Core Version:    0.6.0
 */