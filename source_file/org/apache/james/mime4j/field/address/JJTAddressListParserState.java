package org.apache.james.mime4j.field.address;

import java.util.ArrayList;
import java.util.List;

public class JJTAddressListParserState
{
  private List<Integer> marks = new ArrayList();
  private int mk = 0;
  private boolean node_created;
  private List<Node> nodes = new ArrayList();
  private int sp = 0;

  public void clearNodeScope(Node paramNode)
  {
    while (this.sp > this.mk)
      popNode();
    this.mk = ((Integer)this.marks.remove(-1 + this.marks.size())).intValue();
  }

  public void closeNodeScope(Node paramNode, int paramInt)
  {
    this.mk = ((Integer)this.marks.remove(-1 + this.marks.size())).intValue();
    int j;
    for (int i = paramInt; ; i = j)
    {
      j = i - 1;
      if (i <= 0)
        break;
      Node localNode = popNode();
      localNode.jjtSetParent(paramNode);
      paramNode.jjtAddChild(localNode, j);
    }
    paramNode.jjtClose();
    pushNode(paramNode);
    this.node_created = true;
  }

  public void closeNodeScope(Node paramNode, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int i = nodeArity();
      this.mk = ((Integer)this.marks.remove(-1 + this.marks.size())).intValue();
      int k;
      for (int j = i; ; j = k)
      {
        k = j - 1;
        if (j <= 0)
          break;
        Node localNode = popNode();
        localNode.jjtSetParent(paramNode);
        paramNode.jjtAddChild(localNode, k);
      }
      paramNode.jjtClose();
      pushNode(paramNode);
      this.node_created = true;
      return;
    }
    this.mk = ((Integer)this.marks.remove(-1 + this.marks.size())).intValue();
    this.node_created = false;
  }

  public int nodeArity()
  {
    return this.sp - this.mk;
  }

  public boolean nodeCreated()
  {
    return this.node_created;
  }

  public void openNodeScope(Node paramNode)
  {
    this.marks.add(Integer.valueOf(this.mk));
    this.mk = this.sp;
    paramNode.jjtOpen();
  }

  public Node peekNode()
  {
    return (Node)this.nodes.get(-1 + this.nodes.size());
  }

  public Node popNode()
  {
    int i = -1 + this.sp;
    this.sp = i;
    if (i < this.mk)
      this.mk = ((Integer)this.marks.remove(-1 + this.marks.size())).intValue();
    return (Node)this.nodes.remove(-1 + this.nodes.size());
  }

  public void pushNode(Node paramNode)
  {
    this.nodes.add(paramNode);
    this.sp = (1 + this.sp);
  }

  public void reset()
  {
    this.nodes.clear();
    this.marks.clear();
    this.sp = 0;
    this.mk = 0;
  }

  public Node rootNode()
  {
    return (Node)this.nodes.get(0);
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.address.JJTAddressListParserState
 * JD-Core Version:    0.6.0
 */