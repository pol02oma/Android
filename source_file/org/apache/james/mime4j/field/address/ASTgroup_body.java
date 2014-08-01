package org.apache.james.mime4j.field.address;

public class ASTgroup_body extends SimpleNode
{
  public ASTgroup_body(int paramInt)
  {
    super(paramInt);
  }

  public ASTgroup_body(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.ASTgroup_body
 * JD-Core Version:    0.6.0
 */