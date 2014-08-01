package org.jsoup.select;

import java.util.List;
import org.jsoup.nodes.Node;

public class NodeTraversor
{
  private NodeVisitor visitor;

  public NodeTraversor(NodeVisitor paramNodeVisitor)
  {
    this.visitor = paramNodeVisitor;
  }

  public void traverse(Node paramNode)
  {
    Node localNode = paramNode;
    int i = 0;
    while (true)
    {
      if (localNode != null)
      {
        this.visitor.head(localNode, i);
        if (localNode.childNodes().size() > 0)
        {
          localNode = localNode.childNode(0);
          i++;
          continue;
        }
        while ((localNode.nextSibling() == null) && (i > 0))
        {
          this.visitor.tail(localNode, i);
          localNode = localNode.parent();
          i--;
        }
        this.visitor.tail(localNode, i);
        if (localNode != paramNode);
      }
      else
      {
        return;
      }
      localNode = localNode.nextSibling();
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.select.NodeTraversor
 * JD-Core Version:    0.6.0
 */