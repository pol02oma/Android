package org.apache.james.mime4j.field.address;

import java.io.PrintStream;

public class SimpleNode extends BaseNode
  implements Node
{
  protected Node[] children;
  protected int id;
  protected Node parent;
  protected AddressListParser parser;
  protected Object value;

  public SimpleNode(int paramInt)
  {
    this.id = paramInt;
  }

  public SimpleNode(AddressListParser paramAddressListParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramAddressListParser;
  }

  public Object childrenAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    if (this.children != null)
      for (int i = 0; i < this.children.length; i++)
        this.children[i].jjtAccept(paramAddressListParserVisitor, paramObject);
    return paramObject;
  }

  public void dump(String paramString)
  {
    System.out.println(toString(paramString));
    if (this.children != null)
      for (int i = 0; i < this.children.length; i++)
      {
        SimpleNode localSimpleNode = (SimpleNode)this.children[i];
        if (localSimpleNode == null)
          continue;
        localSimpleNode.dump(paramString + " ");
      }
  }

  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }

  public void jjtAddChild(Node paramNode, int paramInt)
  {
    if (this.children == null)
      this.children = new Node[paramInt + 1];
    while (true)
    {
      this.children[paramInt] = paramNode;
      return;
      if (paramInt < this.children.length)
        continue;
      Node[] arrayOfNode = new Node[paramInt + 1];
      System.arraycopy(this.children, 0, arrayOfNode, 0, this.children.length);
      this.children = arrayOfNode;
    }
  }

  public void jjtClose()
  {
  }

  public Node jjtGetChild(int paramInt)
  {
    return this.children[paramInt];
  }

  public int jjtGetNumChildren()
  {
    if (this.children == null)
      return 0;
    return this.children.length;
  }

  public Node jjtGetParent()
  {
    return this.parent;
  }

  public Object jjtGetValue()
  {
    return this.value;
  }

  public void jjtOpen()
  {
  }

  public void jjtSetParent(Node paramNode)
  {
    this.parent = paramNode;
  }

  public void jjtSetValue(Object paramObject)
  {
    this.value = paramObject;
  }

  public String toString()
  {
    return AddressListParserTreeConstants.jjtNodeName[this.id];
  }

  public String toString(String paramString)
  {
    return paramString + toString();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.SimpleNode
 * JD-Core Version:    0.6.0
 */