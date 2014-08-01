package org.jsoup.safety;

import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;

public class Cleaner
{
  private Whitelist whitelist;

  public Cleaner(Whitelist paramWhitelist)
  {
    Validate.notNull(paramWhitelist);
    this.whitelist = paramWhitelist;
  }

  private int copySafeNodes(Element paramElement1, Element paramElement2)
  {
    List localList = paramElement1.childNodes();
    int i = 0;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      Node localNode = (Node)localIterator.next();
      if ((localNode instanceof Element))
      {
        Element localElement1 = (Element)localNode;
        if (this.whitelist.isSafeTag(localElement1.tagName()))
        {
          ElementMeta localElementMeta = createSafeElement(localElement1);
          Element localElement2 = localElementMeta.el;
          paramElement2.appendChild(localElement2);
          i = i + localElementMeta.numAttribsDiscarded + copySafeNodes(localElement1, localElement2);
          continue;
        }
        i = i + 1 + copySafeNodes(localElement1, paramElement2);
        continue;
      }
      if (!(localNode instanceof TextNode))
        continue;
      paramElement2.appendChild(new TextNode(((TextNode)localNode).getWholeText(), localNode.baseUri()));
    }
    return i;
  }

  private ElementMeta createSafeElement(Element paramElement)
  {
    String str = paramElement.tagName();
    Attributes localAttributes = new Attributes();
    Element localElement = new Element(Tag.valueOf(str), paramElement.baseUri(), localAttributes);
    int i = 0;
    Iterator localIterator = paramElement.attributes().iterator();
    while (localIterator.hasNext())
    {
      Attribute localAttribute = (Attribute)localIterator.next();
      if (this.whitelist.isSafeAttribute(str, paramElement, localAttribute))
      {
        localAttributes.put(localAttribute);
        continue;
      }
      i++;
    }
    localAttributes.addAll(this.whitelist.getEnforcedAttributes(str));
    return new ElementMeta(localElement, i);
  }

  public Document clean(Document paramDocument)
  {
    Validate.notNull(paramDocument);
    Document localDocument = Document.createShell(paramDocument.baseUri());
    copySafeNodes(paramDocument.body(), localDocument.body());
    return localDocument;
  }

  public boolean isValid(Document paramDocument)
  {
    Validate.notNull(paramDocument);
    Document localDocument = Document.createShell(paramDocument.baseUri());
    return copySafeNodes(paramDocument.body(), localDocument.body()) == 0;
  }

  private static class ElementMeta
  {
    Element el;
    int numAttribsDiscarded;

    ElementMeta(Element paramElement, int paramInt)
    {
      this.el = paramElement;
      this.numAttribsDiscarded = paramInt;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.safety.Cleaner
 * JD-Core Version:    0.6.0
 */