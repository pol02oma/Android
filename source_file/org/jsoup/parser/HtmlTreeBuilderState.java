package org.jsoup.parser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.helper.DescendableLinkedList;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.QuirksMode;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

 enum HtmlTreeBuilderState
{
  private static String nullString;

  static
  {
    BeforeHtml = new HtmlTreeBuilderState("BeforeHtml", 1)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        paramHtmlTreeBuilder.insert("html");
        paramHtmlTreeBuilder.transition(BeforeHead);
        return paramHtmlTreeBuilder.process(paramToken);
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramToken.isDoctype())
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        if (paramToken.isComment())
          paramHtmlTreeBuilder.insert(paramToken.asComment());
        while (true)
        {
          return true;
          if (HtmlTreeBuilderState.access$100(paramToken))
            return true;
          if ((!paramToken.isStartTag()) || (!paramToken.asStartTag().name().equals("html")))
            break;
          paramHtmlTreeBuilder.insert(paramToken.asStartTag());
          paramHtmlTreeBuilder.transition(BeforeHead);
        }
        if (paramToken.isEndTag())
          if (StringUtil.in(paramToken.asEndTag().name(), new String[] { "head", "body", "html", "br" }))
            return anythingElse(paramToken, paramHtmlTreeBuilder);
        if (paramToken.isEndTag())
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        return anythingElse(paramToken, paramHtmlTreeBuilder);
      }
    };
    BeforeHead = new HtmlTreeBuilderState("BeforeHead", 2)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          return true;
        if (paramToken.isComment())
        {
          paramHtmlTreeBuilder.insert(paramToken.asComment());
          return true;
        }
        if (paramToken.isDoctype())
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("html")))
          return InBody.process(paramToken, paramHtmlTreeBuilder);
        if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("head")))
        {
          paramHtmlTreeBuilder.setHeadElement(paramHtmlTreeBuilder.insert(paramToken.asStartTag()));
          paramHtmlTreeBuilder.transition(InHead);
          return true;
        }
        if (paramToken.isEndTag())
          if (StringUtil.in(paramToken.asEndTag().name(), new String[] { "head", "body", "html", "br" }))
          {
            paramHtmlTreeBuilder.process(new Token.StartTag("head"));
            return paramHtmlTreeBuilder.process(paramToken);
          }
        if (paramToken.isEndTag())
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        paramHtmlTreeBuilder.process(new Token.StartTag("head"));
        return paramHtmlTreeBuilder.process(paramToken);
      }
    };
    InHead = new HtmlTreeBuilderState("InHead", 3)
    {
      private boolean anythingElse(Token paramToken, TreeBuilder paramTreeBuilder)
      {
        paramTreeBuilder.process(new Token.EndTag("head"));
        return paramTreeBuilder.process(paramToken);
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          paramHtmlTreeBuilder.insert(paramToken.asCharacter());
        Token.StartTag localStartTag;
        String str2;
        while (true)
        {
          return true;
          switch (HtmlTreeBuilderState.24.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
          {
          default:
            return anythingElse(paramToken, paramHtmlTreeBuilder);
          case 1:
            paramHtmlTreeBuilder.insert(paramToken.asComment());
            return true;
          case 2:
            paramHtmlTreeBuilder.error(this);
            return false;
          case 3:
            localStartTag = paramToken.asStartTag();
            str2 = localStartTag.name();
            if (str2.equals("html"))
              return InBody.process(paramToken, paramHtmlTreeBuilder);
            if (StringUtil.in(str2, new String[] { "base", "basefont", "bgsound", "command", "link" }))
            {
              Element localElement = paramHtmlTreeBuilder.insertEmpty(localStartTag);
              if ((!str2.equals("base")) || (!localElement.hasAttr("href")))
                continue;
              paramHtmlTreeBuilder.maybeSetBaseUri(localElement);
              return true;
            }
          case 4:
          }
        }
        if (str2.equals("meta"))
        {
          paramHtmlTreeBuilder.insertEmpty(localStartTag);
          return true;
        }
        if (str2.equals("title"))
        {
          HtmlTreeBuilderState.access$200(localStartTag, paramHtmlTreeBuilder);
          return true;
        }
        if (StringUtil.in(str2, new String[] { "noframes", "style" }))
        {
          HtmlTreeBuilderState.access$300(localStartTag, paramHtmlTreeBuilder);
          return true;
        }
        if (str2.equals("noscript"))
        {
          paramHtmlTreeBuilder.insert(localStartTag);
          paramHtmlTreeBuilder.transition(InHeadNoscript);
          return true;
        }
        if (str2.equals("script"))
        {
          paramHtmlTreeBuilder.insert(localStartTag);
          paramHtmlTreeBuilder.tokeniser.transition(TokeniserState.ScriptData);
          paramHtmlTreeBuilder.markInsertionMode();
          paramHtmlTreeBuilder.transition(Text);
          return true;
        }
        if (str2.equals("head"))
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        return anythingElse(paramToken, paramHtmlTreeBuilder);
        String str1 = paramToken.asEndTag().name();
        if (str1.equals("head"))
        {
          paramHtmlTreeBuilder.pop();
          paramHtmlTreeBuilder.transition(AfterHead);
          return true;
        }
        if (StringUtil.in(str1, new String[] { "body", "html", "br" }))
          return anythingElse(paramToken, paramHtmlTreeBuilder);
        paramHtmlTreeBuilder.error(this);
        return false;
      }
    };
    InHeadNoscript = new HtmlTreeBuilderState("InHeadNoscript", 4)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        paramHtmlTreeBuilder.error(this);
        paramHtmlTreeBuilder.process(new Token.EndTag("noscript"));
        return paramHtmlTreeBuilder.process(paramToken);
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramToken.isDoctype())
          paramHtmlTreeBuilder.error(this);
        while (true)
        {
          return true;
          if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("html")))
            return paramHtmlTreeBuilder.process(paramToken, InBody);
          if ((!paramToken.isEndTag()) || (!paramToken.asEndTag().name().equals("noscript")))
            break;
          paramHtmlTreeBuilder.pop();
          paramHtmlTreeBuilder.transition(InHead);
        }
        if ((!HtmlTreeBuilderState.access$100(paramToken)) && (!paramToken.isComment()))
        {
          if (paramToken.isStartTag())
            if (!StringUtil.in(paramToken.asStartTag().name(), new String[] { "basefont", "bgsound", "link", "meta", "noframes", "style" }));
        }
        else
          return paramHtmlTreeBuilder.process(paramToken, InHead);
        if ((paramToken.isEndTag()) && (paramToken.asEndTag().name().equals("br")))
          return anythingElse(paramToken, paramHtmlTreeBuilder);
        if (paramToken.isStartTag())
        {
          if (StringUtil.in(paramToken.asStartTag().name(), new String[] { "head", "noscript" }));
        }
        else
          if (!paramToken.isEndTag())
            break label237;
        paramHtmlTreeBuilder.error(this);
        return false;
        label237: return anythingElse(paramToken, paramHtmlTreeBuilder);
      }
    };
    AfterHead = new HtmlTreeBuilderState("AfterHead", 5)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        paramHtmlTreeBuilder.process(new Token.StartTag("body"));
        paramHtmlTreeBuilder.framesetOk(true);
        return paramHtmlTreeBuilder.process(paramToken);
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          paramHtmlTreeBuilder.insert(paramToken.asCharacter());
        while (true)
        {
          return true;
          if (paramToken.isComment())
          {
            paramHtmlTreeBuilder.insert(paramToken.asComment());
            continue;
          }
          if (paramToken.isDoctype())
          {
            paramHtmlTreeBuilder.error(this);
            continue;
          }
          if (paramToken.isStartTag())
          {
            Token.StartTag localStartTag = paramToken.asStartTag();
            String str = localStartTag.name();
            if (str.equals("html"))
              return paramHtmlTreeBuilder.process(paramToken, InBody);
            if (str.equals("body"))
            {
              paramHtmlTreeBuilder.insert(localStartTag);
              paramHtmlTreeBuilder.framesetOk(false);
              paramHtmlTreeBuilder.transition(InBody);
              continue;
            }
            if (str.equals("frameset"))
            {
              paramHtmlTreeBuilder.insert(localStartTag);
              paramHtmlTreeBuilder.transition(InFrameset);
              continue;
            }
            if (StringUtil.in(str, new String[] { "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "title" }))
            {
              paramHtmlTreeBuilder.error(this);
              Element localElement = paramHtmlTreeBuilder.getHeadElement();
              paramHtmlTreeBuilder.push(localElement);
              paramHtmlTreeBuilder.process(paramToken, InHead);
              paramHtmlTreeBuilder.removeFromStack(localElement);
              continue;
            }
            if (str.equals("head"))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            anythingElse(paramToken, paramHtmlTreeBuilder);
            continue;
          }
          if (paramToken.isEndTag())
          {
            if (StringUtil.in(paramToken.asEndTag().name(), new String[] { "body", "html" }))
            {
              anythingElse(paramToken, paramHtmlTreeBuilder);
              continue;
            }
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          anythingElse(paramToken, paramHtmlTreeBuilder);
        }
      }
    };
    InBody = new HtmlTreeBuilderState("InBody", 6)
    {
      boolean anyOtherEndTag(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        String str = paramToken.asEndTag().name();
        Iterator localIterator = paramHtmlTreeBuilder.getStack().descendingIterator();
        Element localElement;
        do
        {
          if (localIterator.hasNext())
          {
            localElement = (Element)localIterator.next();
            if (!localElement.nodeName().equals(str))
              continue;
            paramHtmlTreeBuilder.generateImpliedEndTags(str);
            if (!str.equals(paramHtmlTreeBuilder.currentElement().nodeName()))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(str);
          }
          return true;
        }
        while (!paramHtmlTreeBuilder.isSpecial(localElement));
        paramHtmlTreeBuilder.error(this);
        return false;
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        switch (HtmlTreeBuilderState.24.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
        {
        default:
        case 5:
        case 1:
        case 2:
        case 3:
        case 4:
        }
        label1139: String str1;
        while (true)
        {
          return true;
          Token.Character localCharacter2 = paramToken.asCharacter();
          if (localCharacter2.getData().equals(HtmlTreeBuilderState.nullString))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          if (HtmlTreeBuilderState.access$100(localCharacter2))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localCharacter2);
            continue;
          }
          paramHtmlTreeBuilder.reconstructFormattingElements();
          paramHtmlTreeBuilder.insert(localCharacter2);
          paramHtmlTreeBuilder.framesetOk(false);
          continue;
          paramHtmlTreeBuilder.insert(paramToken.asComment());
          continue;
          paramHtmlTreeBuilder.error(this);
          return false;
          Token.StartTag localStartTag2 = paramToken.asStartTag();
          String str2 = localStartTag2.name();
          if (str2.equals("html"))
          {
            paramHtmlTreeBuilder.error(this);
            Element localElement12 = (Element)paramHtmlTreeBuilder.getStack().getFirst();
            Iterator localIterator3 = localStartTag2.getAttributes().iterator();
            while (localIterator3.hasNext())
            {
              Attribute localAttribute3 = (Attribute)localIterator3.next();
              if (localElement12.hasAttr(localAttribute3.getKey()))
                continue;
              localElement12.attributes().put(localAttribute3);
            }
          }
          if (StringUtil.in(str2, new String[] { "base", "basefont", "bgsound", "command", "link", "meta", "noframes", "script", "style", "title" }))
            return paramHtmlTreeBuilder.process(paramToken, InHead);
          if (str2.equals("body"))
          {
            paramHtmlTreeBuilder.error(this);
            DescendableLinkedList localDescendableLinkedList5 = paramHtmlTreeBuilder.getStack();
            if ((localDescendableLinkedList5.size() == 1) || ((localDescendableLinkedList5.size() > 2) && (!((Element)localDescendableLinkedList5.get(1)).nodeName().equals("body"))))
              return false;
            paramHtmlTreeBuilder.framesetOk(false);
            Element localElement11 = (Element)localDescendableLinkedList5.get(1);
            Iterator localIterator2 = localStartTag2.getAttributes().iterator();
            while (localIterator2.hasNext())
            {
              Attribute localAttribute2 = (Attribute)localIterator2.next();
              if (localElement11.hasAttr(localAttribute2.getKey()))
                continue;
              localElement11.attributes().put(localAttribute2);
            }
          }
          if (str2.equals("frameset"))
          {
            paramHtmlTreeBuilder.error(this);
            DescendableLinkedList localDescendableLinkedList4 = paramHtmlTreeBuilder.getStack();
            if ((localDescendableLinkedList4.size() == 1) || ((localDescendableLinkedList4.size() > 2) && (!((Element)localDescendableLinkedList4.get(1)).nodeName().equals("body"))))
              return false;
            if (!paramHtmlTreeBuilder.framesetOk())
              return false;
            Element localElement10 = (Element)localDescendableLinkedList4.get(1);
            if (localElement10.parent() != null)
              localElement10.remove();
            while (localDescendableLinkedList4.size() > 1)
              localDescendableLinkedList4.removeLast();
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.transition(InFrameset);
            continue;
          }
          if (StringUtil.in(str2, new String[] { "address", "article", "aside", "blockquote", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "menu", "nav", "ol", "p", "section", "summary", "ul" }))
          {
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.insert(localStartTag2);
            continue;
          }
          if (StringUtil.in(str2, new String[] { "h1", "h2", "h3", "h4", "h5", "h6" }))
          {
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            if (StringUtil.in(paramHtmlTreeBuilder.currentElement().nodeName(), new String[] { "h1", "h2", "h3", "h4", "h5", "h6" }))
            {
              paramHtmlTreeBuilder.error(this);
              paramHtmlTreeBuilder.pop();
            }
            paramHtmlTreeBuilder.insert(localStartTag2);
            continue;
          }
          if (StringUtil.in(str2, new String[] { "pre", "listing" }))
          {
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.framesetOk(false);
            continue;
          }
          if (str2.equals("form"))
          {
            if (paramHtmlTreeBuilder.getFormElement() != null)
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.setFormElement(paramHtmlTreeBuilder.insert(localStartTag2));
            continue;
          }
          if (str2.equals("li"))
          {
            paramHtmlTreeBuilder.framesetOk(false);
            DescendableLinkedList localDescendableLinkedList3 = paramHtmlTreeBuilder.getStack();
            label1179: for (int i5 = -1 + localDescendableLinkedList3.size(); ; i5--)
            {
              Element localElement9;
              if (i5 > 0)
              {
                localElement9 = (Element)localDescendableLinkedList3.get(i5);
                if (!localElement9.nodeName().equals("li"))
                  break label1139;
                paramHtmlTreeBuilder.process(new Token.EndTag("li"));
              }
              do
              {
                if (paramHtmlTreeBuilder.inButtonScope("p"))
                  paramHtmlTreeBuilder.process(new Token.EndTag("p"));
                paramHtmlTreeBuilder.insert(localStartTag2);
                break;
                if (!paramHtmlTreeBuilder.isSpecial(localElement9))
                  break label1179;
              }
              while (!StringUtil.in(localElement9.nodeName(), new String[] { "address", "div", "p" }));
            }
          }
          if (StringUtil.in(str2, new String[] { "dd", "dt" }))
          {
            paramHtmlTreeBuilder.framesetOk(false);
            DescendableLinkedList localDescendableLinkedList2 = paramHtmlTreeBuilder.getStack();
            label1325: label1365: for (int i4 = -1 + localDescendableLinkedList2.size(); ; i4--)
            {
              Element localElement8;
              if (i4 > 0)
              {
                localElement8 = (Element)localDescendableLinkedList2.get(i4);
                if (!StringUtil.in(localElement8.nodeName(), new String[] { "dd", "dt" }))
                  break label1325;
                paramHtmlTreeBuilder.process(new Token.EndTag(localElement8.nodeName()));
              }
              do
              {
                if (paramHtmlTreeBuilder.inButtonScope("p"))
                  paramHtmlTreeBuilder.process(new Token.EndTag("p"));
                paramHtmlTreeBuilder.insert(localStartTag2);
                break;
                if (!paramHtmlTreeBuilder.isSpecial(localElement8))
                  break label1365;
              }
              while (!StringUtil.in(localElement8.nodeName(), new String[] { "address", "div", "p" }));
            }
          }
          if (str2.equals("plaintext"))
          {
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.tokeniser.transition(TokeniserState.PLAINTEXT);
            continue;
          }
          if (str2.equals("button"))
          {
            if (paramHtmlTreeBuilder.inButtonScope("button"))
            {
              paramHtmlTreeBuilder.error(this);
              paramHtmlTreeBuilder.process(new Token.EndTag("button"));
              paramHtmlTreeBuilder.process(localStartTag2);
              continue;
            }
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.framesetOk(false);
            continue;
          }
          if (str2.equals("a"))
          {
            if (paramHtmlTreeBuilder.getActiveFormattingElement("a") != null)
            {
              paramHtmlTreeBuilder.error(this);
              paramHtmlTreeBuilder.process(new Token.EndTag("a"));
              Element localElement7 = paramHtmlTreeBuilder.getFromStack("a");
              if (localElement7 != null)
              {
                paramHtmlTreeBuilder.removeFromActiveFormattingElements(localElement7);
                paramHtmlTreeBuilder.removeFromStack(localElement7);
              }
            }
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.pushActiveFormattingElements(paramHtmlTreeBuilder.insert(localStartTag2));
            continue;
          }
          if (StringUtil.in(str2, new String[] { "b", "big", "code", "em", "font", "i", "s", "small", "strike", "strong", "tt", "u" }))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.pushActiveFormattingElements(paramHtmlTreeBuilder.insert(localStartTag2));
            continue;
          }
          if (str2.equals("nobr"))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            if (paramHtmlTreeBuilder.inScope("nobr"))
            {
              paramHtmlTreeBuilder.error(this);
              paramHtmlTreeBuilder.process(new Token.EndTag("nobr"));
              paramHtmlTreeBuilder.reconstructFormattingElements();
            }
            paramHtmlTreeBuilder.pushActiveFormattingElements(paramHtmlTreeBuilder.insert(localStartTag2));
            continue;
          }
          if (StringUtil.in(str2, new String[] { "applet", "marquee", "object" }))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.insertMarkerToFormattingElements();
            paramHtmlTreeBuilder.framesetOk(false);
            continue;
          }
          if (str2.equals("table"))
          {
            if ((paramHtmlTreeBuilder.getDocument().quirksMode() != Document.QuirksMode.quirks) && (paramHtmlTreeBuilder.inButtonScope("p")))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.framesetOk(false);
            paramHtmlTreeBuilder.transition(InTable);
            continue;
          }
          if (StringUtil.in(str2, new String[] { "area", "br", "embed", "img", "keygen", "wbr" }))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insertEmpty(localStartTag2);
            paramHtmlTreeBuilder.framesetOk(false);
            continue;
          }
          if (str2.equals("input"))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            if (paramHtmlTreeBuilder.insertEmpty(localStartTag2).attr("type").equalsIgnoreCase("hidden"))
              continue;
            paramHtmlTreeBuilder.framesetOk(false);
            continue;
          }
          if (StringUtil.in(str2, new String[] { "param", "source", "track" }))
          {
            paramHtmlTreeBuilder.insertEmpty(localStartTag2);
            continue;
          }
          if (str2.equals("hr"))
          {
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.insertEmpty(localStartTag2);
            paramHtmlTreeBuilder.framesetOk(false);
            continue;
          }
          if (str2.equals("image"))
          {
            localStartTag2.name("img");
            return paramHtmlTreeBuilder.process(localStartTag2);
          }
          if (str2.equals("isindex"))
          {
            paramHtmlTreeBuilder.error(this);
            if (paramHtmlTreeBuilder.getFormElement() != null)
              return false;
            paramHtmlTreeBuilder.tokeniser.acknowledgeSelfClosingFlag();
            paramHtmlTreeBuilder.process(new Token.StartTag("form"));
            if (localStartTag2.attributes.hasKey("action"))
              paramHtmlTreeBuilder.getFormElement().attr("action", localStartTag2.attributes.get("action"));
            paramHtmlTreeBuilder.process(new Token.StartTag("hr"));
            paramHtmlTreeBuilder.process(new Token.StartTag("label"));
            if (localStartTag2.attributes.hasKey("prompt"));
            Attributes localAttributes;
            for (String str3 = localStartTag2.attributes.get("prompt"); ; str3 = "This is a searchable index. Enter search keywords: ")
            {
              Token.Character localCharacter1 = new Token.Character(str3);
              paramHtmlTreeBuilder.process(localCharacter1);
              localAttributes = new Attributes();
              Iterator localIterator1 = localStartTag2.attributes.iterator();
              while (localIterator1.hasNext())
              {
                Attribute localAttribute1 = (Attribute)localIterator1.next();
                if (StringUtil.in(localAttribute1.getKey(), new String[] { "name", "action", "prompt" }))
                  continue;
                localAttributes.put(localAttribute1);
              }
            }
            localAttributes.put("name", "isindex");
            Token.StartTag localStartTag3 = new Token.StartTag("input", localAttributes);
            paramHtmlTreeBuilder.process(localStartTag3);
            paramHtmlTreeBuilder.process(new Token.EndTag("label"));
            paramHtmlTreeBuilder.process(new Token.StartTag("hr"));
            paramHtmlTreeBuilder.process(new Token.EndTag("form"));
            continue;
          }
          if (str2.equals("textarea"))
          {
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
            paramHtmlTreeBuilder.markInsertionMode();
            paramHtmlTreeBuilder.framesetOk(false);
            paramHtmlTreeBuilder.transition(Text);
            continue;
          }
          if (str2.equals("xmp"))
          {
            if (paramHtmlTreeBuilder.inButtonScope("p"))
              paramHtmlTreeBuilder.process(new Token.EndTag("p"));
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.framesetOk(false);
            HtmlTreeBuilderState.access$300(localStartTag2, paramHtmlTreeBuilder);
            continue;
          }
          if (str2.equals("iframe"))
          {
            paramHtmlTreeBuilder.framesetOk(false);
            HtmlTreeBuilderState.access$300(localStartTag2, paramHtmlTreeBuilder);
            continue;
          }
          if (str2.equals("noembed"))
          {
            HtmlTreeBuilderState.access$300(localStartTag2, paramHtmlTreeBuilder);
            continue;
          }
          if (str2.equals("select"))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.framesetOk(false);
            HtmlTreeBuilderState localHtmlTreeBuilderState = paramHtmlTreeBuilder.state();
            if ((localHtmlTreeBuilderState.equals(InTable)) || (localHtmlTreeBuilderState.equals(InCaption)) || (localHtmlTreeBuilderState.equals(InTableBody)) || (localHtmlTreeBuilderState.equals(InRow)) || (localHtmlTreeBuilderState.equals(InCell)))
            {
              paramHtmlTreeBuilder.transition(InSelectInTable);
              continue;
            }
            paramHtmlTreeBuilder.transition(InSelect);
            continue;
          }
          if (StringUtil.in("optgroup", new String[] { "option" }))
          {
            if (paramHtmlTreeBuilder.currentElement().nodeName().equals("option"))
              paramHtmlTreeBuilder.process(new Token.EndTag("option"));
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localStartTag2);
            continue;
          }
          if (StringUtil.in("rp", new String[] { "rt" }))
          {
            if (!paramHtmlTreeBuilder.inScope("ruby"))
              continue;
            paramHtmlTreeBuilder.generateImpliedEndTags();
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals("ruby"))
            {
              paramHtmlTreeBuilder.error(this);
              paramHtmlTreeBuilder.popStackToBefore("ruby");
            }
            paramHtmlTreeBuilder.insert(localStartTag2);
            continue;
          }
          if (str2.equals("math"))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.tokeniser.acknowledgeSelfClosingFlag();
            continue;
          }
          if (str2.equals("svg"))
          {
            paramHtmlTreeBuilder.reconstructFormattingElements();
            paramHtmlTreeBuilder.insert(localStartTag2);
            paramHtmlTreeBuilder.tokeniser.acknowledgeSelfClosingFlag();
            continue;
          }
          if (StringUtil.in(str2, new String[] { "caption", "col", "colgroup", "frame", "head", "tbody", "td", "tfoot", "th", "thead", "tr" }))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          paramHtmlTreeBuilder.reconstructFormattingElements();
          paramHtmlTreeBuilder.insert(localStartTag2);
          continue;
          Token.EndTag localEndTag = paramToken.asEndTag();
          str1 = localEndTag.name();
          if (str1.equals("body"))
          {
            if (!paramHtmlTreeBuilder.inScope("body"))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.transition(AfterBody);
            continue;
          }
          if (str1.equals("html"))
            if (paramHtmlTreeBuilder.process(new Token.EndTag("body")))
              return paramHtmlTreeBuilder.process(localEndTag);
          if (StringUtil.in(str1, new String[] { "address", "article", "aside", "blockquote", "button", "center", "details", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "menu", "nav", "ol", "pre", "section", "summary", "ul" }))
          {
            if (!paramHtmlTreeBuilder.inScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.generateImpliedEndTags();
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(str1);
            continue;
          }
          if (str1.equals("form"))
          {
            Element localElement6 = paramHtmlTreeBuilder.getFormElement();
            paramHtmlTreeBuilder.setFormElement(null);
            if ((localElement6 == null) || (!paramHtmlTreeBuilder.inScope(str1)))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.generateImpliedEndTags();
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.removeFromStack(localElement6);
            continue;
          }
          if (str1.equals("p"))
          {
            if (!paramHtmlTreeBuilder.inButtonScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              Token.StartTag localStartTag1 = new Token.StartTag(str1);
              paramHtmlTreeBuilder.process(localStartTag1);
              return paramHtmlTreeBuilder.process(localEndTag);
            }
            paramHtmlTreeBuilder.generateImpliedEndTags(str1);
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(str1);
            continue;
          }
          if (str1.equals("li"))
          {
            if (!paramHtmlTreeBuilder.inListItemScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.generateImpliedEndTags(str1);
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(str1);
            continue;
          }
          if (StringUtil.in(str1, new String[] { "dd", "dt" }))
          {
            if (!paramHtmlTreeBuilder.inScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.generateImpliedEndTags(str1);
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(str1);
            continue;
          }
          if (StringUtil.in(str1, new String[] { "h1", "h2", "h3", "h4", "h5", "h6" }))
          {
            if (!paramHtmlTreeBuilder.inScope(new String[] { "h1", "h2", "h3", "h4", "h5", "h6" }))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.generateImpliedEndTags(str1);
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(new String[] { "h1", "h2", "h3", "h4", "h5", "h6" });
            continue;
          }
          if (str1.equals("sarcasm"))
            return anyOtherEndTag(paramToken, paramHtmlTreeBuilder);
          if (StringUtil.in(str1, new String[] { "a", "b", "big", "code", "em", "font", "i", "nobr", "s", "small", "strike", "strong", "tt", "u" }))
          {
            for (int i = 0; i < 8; i++)
            {
              Element localElement1 = paramHtmlTreeBuilder.getActiveFormattingElement(str1);
              if (localElement1 == null)
                return anyOtherEndTag(paramToken, paramHtmlTreeBuilder);
              if (!paramHtmlTreeBuilder.onStack(localElement1))
              {
                paramHtmlTreeBuilder.error(this);
                paramHtmlTreeBuilder.removeFromActiveFormattingElements(localElement1);
                return true;
              }
              if (!paramHtmlTreeBuilder.inScope(localElement1.nodeName()))
              {
                paramHtmlTreeBuilder.error(this);
                return false;
              }
              if (paramHtmlTreeBuilder.currentElement() != localElement1)
                paramHtmlTreeBuilder.error(this);
              Element localElement2 = null;
              int j = 0;
              DescendableLinkedList localDescendableLinkedList1 = paramHtmlTreeBuilder.getStack();
              int k = 0;
              int m = localDescendableLinkedList1.size();
              int n = k;
              Object localObject1 = null;
              if (n < m)
              {
                Element localElement5 = (Element)localDescendableLinkedList1.get(k);
                if (localElement5 == localElement1)
                {
                  localElement2 = (Element)localDescendableLinkedList1.get(k - 1);
                  j = 1;
                }
                do
                {
                  k++;
                  break;
                }
                while ((j == 0) || (!paramHtmlTreeBuilder.isSpecial(localElement5)));
                localObject1 = localElement5;
              }
              if (localObject1 == null)
              {
                paramHtmlTreeBuilder.popStackToClose(localElement1.nodeName());
                paramHtmlTreeBuilder.removeFromActiveFormattingElements(localElement1);
                return true;
              }
              Object localObject2 = localObject1;
              Object localObject3 = localObject1;
              int i1 = 0;
              while (i1 < 3)
              {
                if (paramHtmlTreeBuilder.onStack((Element)localObject2))
                  localObject2 = paramHtmlTreeBuilder.aboveOnStack((Element)localObject2);
                if (!paramHtmlTreeBuilder.isInActiveFormattingElements((Element)localObject2))
                {
                  paramHtmlTreeBuilder.removeFromStack((Element)localObject2);
                  i1++;
                  continue;
                }
                if (localObject2 != localElement1)
                  break label4292;
              }
              if (StringUtil.in(localElement2.nodeName(), new String[] { "table", "tbody", "tfoot", "thead", "tr" }))
              {
                if (localObject3.parent() != null)
                  localObject3.remove();
                paramHtmlTreeBuilder.insertInFosterParent(localObject3);
              }
              Element localElement3;
              while (true)
              {
                localElement3 = new Element(Tag.valueOf(str1), paramHtmlTreeBuilder.getBaseUri());
                Node[] arrayOfNode = (Node[])localObject1.childNodes().toArray(new Node[localObject1.childNodes().size()]);
                int i2 = arrayOfNode.length;
                for (int i3 = 0; i3 < i2; i3++)
                  localElement3.appendChild(arrayOfNode[i3]);
                label4292: Element localElement4 = new Element(Tag.valueOf(((Element)localObject2).nodeName()), paramHtmlTreeBuilder.getBaseUri());
                paramHtmlTreeBuilder.replaceActiveFormattingElement((Element)localObject2, localElement4);
                paramHtmlTreeBuilder.replaceOnStack((Element)localObject2, localElement4);
                localObject2 = localElement4;
                if ((localObject3 != localObject1) || (localObject3.parent() != null))
                  localObject3.remove();
                ((Element)localObject2).appendChild(localObject3);
                localObject3 = localObject2;
                break;
                if (localObject3.parent() != null)
                  localObject3.remove();
                localElement2.appendChild(localObject3);
              }
              localObject1.appendChild(localElement3);
              paramHtmlTreeBuilder.removeFromActiveFormattingElements(localElement1);
              paramHtmlTreeBuilder.removeFromStack(localElement1);
              paramHtmlTreeBuilder.insertOnStackAfter(localObject1, localElement3);
            }
            continue;
          }
          if (!StringUtil.in(str1, new String[] { "applet", "marquee", "object" }))
            break;
          if (paramHtmlTreeBuilder.inScope("name"))
            continue;
          if (!paramHtmlTreeBuilder.inScope(str1))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          paramHtmlTreeBuilder.generateImpliedEndTags();
          if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str1))
            paramHtmlTreeBuilder.error(this);
          paramHtmlTreeBuilder.popStackToClose(str1);
          paramHtmlTreeBuilder.clearFormattingElementsToLastMarker();
        }
        if (str1.equals("br"))
        {
          paramHtmlTreeBuilder.error(this);
          paramHtmlTreeBuilder.process(new Token.StartTag("br"));
          return false;
        }
        return anyOtherEndTag(paramToken, paramHtmlTreeBuilder);
      }
    };
    Text = new HtmlTreeBuilderState("Text", 7)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramToken.isCharacter())
          paramHtmlTreeBuilder.insert(paramToken.asCharacter());
        while (true)
        {
          return true;
          if (paramToken.isEOF())
          {
            paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.pop();
            paramHtmlTreeBuilder.transition(paramHtmlTreeBuilder.originalState());
            return paramHtmlTreeBuilder.process(paramToken);
          }
          if (!paramToken.isEndTag())
            continue;
          paramHtmlTreeBuilder.pop();
          paramHtmlTreeBuilder.transition(paramHtmlTreeBuilder.originalState());
        }
      }
    };
    InTable = new HtmlTreeBuilderState("InTable", 8)
    {
      boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        paramHtmlTreeBuilder.error(this);
        if (StringUtil.in(paramHtmlTreeBuilder.currentElement().nodeName(), new String[] { "table", "tbody", "tfoot", "thead", "tr" }))
        {
          paramHtmlTreeBuilder.setFosterInserts(true);
          boolean bool = paramHtmlTreeBuilder.process(paramToken, InBody);
          paramHtmlTreeBuilder.setFosterInserts(false);
          return bool;
        }
        return paramHtmlTreeBuilder.process(paramToken, InBody);
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        boolean bool = true;
        if (paramToken.isCharacter())
        {
          paramHtmlTreeBuilder.newPendingTableCharacters();
          paramHtmlTreeBuilder.markInsertionMode();
          paramHtmlTreeBuilder.transition(InTableText);
          bool = paramHtmlTreeBuilder.process(paramToken);
        }
        do
        {
          return bool;
          if (paramToken.isComment())
          {
            paramHtmlTreeBuilder.insert(paramToken.asComment());
            return bool;
          }
          if (paramToken.isDoctype())
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          Token.StartTag localStartTag;
          String str2;
          if (paramToken.isStartTag())
          {
            localStartTag = paramToken.asStartTag();
            str2 = localStartTag.name();
            if (str2.equals("caption"))
            {
              paramHtmlTreeBuilder.clearStackToTableContext();
              paramHtmlTreeBuilder.insertMarkerToFormattingElements();
              paramHtmlTreeBuilder.insert(localStartTag);
              paramHtmlTreeBuilder.transition(InCaption);
            }
          }
          do
            while (true)
            {
              return anythingElse(paramToken, paramHtmlTreeBuilder);
              if (str2.equals("colgroup"))
              {
                paramHtmlTreeBuilder.clearStackToTableContext();
                paramHtmlTreeBuilder.insert(localStartTag);
                paramHtmlTreeBuilder.transition(InColumnGroup);
                continue;
              }
              if (str2.equals("col"))
              {
                paramHtmlTreeBuilder.process(new Token.StartTag("colgroup"));
                return paramHtmlTreeBuilder.process(paramToken);
              }
              String[] arrayOfString2 = new String[3];
              arrayOfString2[0] = "tbody";
              arrayOfString2[bool] = "tfoot";
              arrayOfString2[2] = "thead";
              if (StringUtil.in(str2, arrayOfString2))
              {
                paramHtmlTreeBuilder.clearStackToTableContext();
                paramHtmlTreeBuilder.insert(localStartTag);
                paramHtmlTreeBuilder.transition(InTableBody);
                continue;
              }
              String[] arrayOfString3 = new String[3];
              arrayOfString3[0] = "td";
              arrayOfString3[bool] = "th";
              arrayOfString3[2] = "tr";
              if (StringUtil.in(str2, arrayOfString3))
              {
                paramHtmlTreeBuilder.process(new Token.StartTag("tbody"));
                return paramHtmlTreeBuilder.process(paramToken);
              }
              if (str2.equals("table"))
              {
                paramHtmlTreeBuilder.error(this);
                if (paramHtmlTreeBuilder.process(new Token.EndTag("table")))
                  return paramHtmlTreeBuilder.process(paramToken);
              }
              String[] arrayOfString4 = new String[2];
              arrayOfString4[0] = "style";
              arrayOfString4[bool] = "script";
              if (StringUtil.in(str2, arrayOfString4))
                return paramHtmlTreeBuilder.process(paramToken, InHead);
              if (str2.equals("input"))
              {
                if (!localStartTag.attributes.get("type").equalsIgnoreCase("hidden"))
                  return anythingElse(paramToken, paramHtmlTreeBuilder);
                paramHtmlTreeBuilder.insertEmpty(localStartTag);
                continue;
              }
              if (str2.equals("form"))
              {
                paramHtmlTreeBuilder.error(this);
                if (paramHtmlTreeBuilder.getFormElement() != null)
                  return false;
                paramHtmlTreeBuilder.setFormElement(paramHtmlTreeBuilder.insertEmpty(localStartTag));
                continue;
              }
              return anythingElse(paramToken, paramHtmlTreeBuilder);
              if (!paramToken.isEndTag())
                break;
              String str1 = paramToken.asEndTag().name();
              if (str1.equals("table"))
              {
                if (!paramHtmlTreeBuilder.inTableScope(str1))
                {
                  paramHtmlTreeBuilder.error(this);
                  return false;
                }
                paramHtmlTreeBuilder.popStackToClose("table");
                paramHtmlTreeBuilder.resetInsertionMode();
                continue;
              }
              String[] arrayOfString1 = new String[11];
              arrayOfString1[0] = "body";
              arrayOfString1[bool] = "caption";
              arrayOfString1[2] = "col";
              arrayOfString1[3] = "colgroup";
              arrayOfString1[4] = "html";
              arrayOfString1[5] = "tbody";
              arrayOfString1[6] = "td";
              arrayOfString1[7] = "tfoot";
              arrayOfString1[8] = "th";
              arrayOfString1[9] = "thead";
              arrayOfString1[10] = "tr";
              if (StringUtil.in(str1, arrayOfString1))
              {
                paramHtmlTreeBuilder.error(this);
                return false;
              }
              return anythingElse(paramToken, paramHtmlTreeBuilder);
            }
          while (!paramToken.isEOF());
        }
        while (!paramHtmlTreeBuilder.currentElement().nodeName().equals("html"));
        paramHtmlTreeBuilder.error(this);
        return bool;
      }
    };
    InTableText = new HtmlTreeBuilderState("InTableText", 9)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        Iterator localIterator;
        switch (HtmlTreeBuilderState.24.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
        {
        default:
          if (paramHtmlTreeBuilder.getPendingTableCharacters().size() <= 0)
            break;
          localIterator = paramHtmlTreeBuilder.getPendingTableCharacters().iterator();
        case 5:
          while (localIterator.hasNext())
          {
            Token.Character localCharacter2 = (Token.Character)localIterator.next();
            if (!HtmlTreeBuilderState.access$100(localCharacter2))
            {
              paramHtmlTreeBuilder.error(this);
              if (StringUtil.in(paramHtmlTreeBuilder.currentElement().nodeName(), new String[] { "table", "tbody", "tfoot", "thead", "tr" }))
              {
                paramHtmlTreeBuilder.setFosterInserts(true);
                paramHtmlTreeBuilder.process(localCharacter2, InBody);
                paramHtmlTreeBuilder.setFosterInserts(false);
                continue;
                Token.Character localCharacter1 = paramToken.asCharacter();
                if (localCharacter1.getData().equals(HtmlTreeBuilderState.nullString))
                {
                  paramHtmlTreeBuilder.error(this);
                  return false;
                }
                paramHtmlTreeBuilder.getPendingTableCharacters().add(localCharacter1);
                return true;
              }
              paramHtmlTreeBuilder.process(localCharacter2, InBody);
              continue;
            }
            paramHtmlTreeBuilder.insert(localCharacter2);
          }
          paramHtmlTreeBuilder.newPendingTableCharacters();
        }
        paramHtmlTreeBuilder.transition(paramHtmlTreeBuilder.originalState());
        return paramHtmlTreeBuilder.process(paramToken);
      }
    };
    InCaption = new HtmlTreeBuilderState("InCaption", 10)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if ((paramToken.isEndTag()) && (paramToken.asEndTag().name().equals("caption")))
        {
          if (!paramHtmlTreeBuilder.inTableScope(paramToken.asEndTag().name()))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          paramHtmlTreeBuilder.generateImpliedEndTags();
          if (!paramHtmlTreeBuilder.currentElement().nodeName().equals("caption"))
            paramHtmlTreeBuilder.error(this);
          paramHtmlTreeBuilder.popStackToClose("caption");
          paramHtmlTreeBuilder.clearFormattingElementsToLastMarker();
          paramHtmlTreeBuilder.transition(InTable);
        }
        while (true)
        {
          return true;
          if (paramToken.isStartTag())
          {
            if (StringUtil.in(paramToken.asStartTag().name(), new String[] { "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr" }));
          }
          else
            if ((!paramToken.isEndTag()) || (!paramToken.asEndTag().name().equals("table")))
              break;
          paramHtmlTreeBuilder.error(this);
          if (paramHtmlTreeBuilder.process(new Token.EndTag("caption")))
            return paramHtmlTreeBuilder.process(paramToken);
        }
        if (paramToken.isEndTag())
          if (StringUtil.in(paramToken.asEndTag().name(), new String[] { "body", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr" }))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
        return paramHtmlTreeBuilder.process(paramToken, InBody);
      }
    };
    InColumnGroup = new HtmlTreeBuilderState("InColumnGroup", 11)
    {
      private boolean anythingElse(Token paramToken, TreeBuilder paramTreeBuilder)
      {
        if (paramTreeBuilder.process(new Token.EndTag("colgroup")))
          return paramTreeBuilder.process(paramToken);
        return true;
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          paramHtmlTreeBuilder.insert(paramToken.asCharacter());
        do
        {
          return true;
          switch (HtmlTreeBuilderState.24.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
          {
          case 5:
          default:
            return anythingElse(paramToken, paramHtmlTreeBuilder);
          case 1:
            paramHtmlTreeBuilder.insert(paramToken.asComment());
            return true;
          case 2:
            paramHtmlTreeBuilder.error(this);
            return true;
          case 3:
            Token.StartTag localStartTag = paramToken.asStartTag();
            String str = localStartTag.name();
            if (str.equals("html"))
              return paramHtmlTreeBuilder.process(paramToken, InBody);
            if (str.equals("col"))
            {
              paramHtmlTreeBuilder.insertEmpty(localStartTag);
              return true;
            }
            return anythingElse(paramToken, paramHtmlTreeBuilder);
          case 4:
            if (paramToken.asEndTag().name().equals("colgroup"))
            {
              if (paramHtmlTreeBuilder.currentElement().nodeName().equals("html"))
              {
                paramHtmlTreeBuilder.error(this);
                return false;
              }
              paramHtmlTreeBuilder.pop();
              paramHtmlTreeBuilder.transition(InTable);
              return true;
            }
            return anythingElse(paramToken, paramHtmlTreeBuilder);
          case 6:
          }
        }
        while (paramHtmlTreeBuilder.currentElement().nodeName().equals("html"));
        return anythingElse(paramToken, paramHtmlTreeBuilder);
      }
    };
    InTableBody = new HtmlTreeBuilderState("InTableBody", 12)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        return paramHtmlTreeBuilder.process(paramToken, InTable);
      }

      private boolean exitTableBody(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if ((!paramHtmlTreeBuilder.inTableScope("tbody")) && (!paramHtmlTreeBuilder.inTableScope("thead")) && (!paramHtmlTreeBuilder.inScope("tfoot")))
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        paramHtmlTreeBuilder.clearStackToTableBodyContext();
        paramHtmlTreeBuilder.process(new Token.EndTag(paramHtmlTreeBuilder.currentElement().nodeName()));
        return paramHtmlTreeBuilder.process(paramToken);
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        Token.StartTag localStartTag;
        String str2;
        switch (HtmlTreeBuilderState.24.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
        {
        default:
          return anythingElse(paramToken, paramHtmlTreeBuilder);
        case 3:
          localStartTag = paramToken.asStartTag();
          str2 = localStartTag.name();
          if (!str2.equals("tr"))
            break;
          paramHtmlTreeBuilder.clearStackToTableBodyContext();
          paramHtmlTreeBuilder.insert(localStartTag);
          paramHtmlTreeBuilder.transition(InRow);
        case 4:
        }
        String str1;
        while (true)
        {
          return true;
          if (StringUtil.in(str2, new String[] { "th", "td" }))
          {
            paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.process(new Token.StartTag("tr"));
            return paramHtmlTreeBuilder.process(localStartTag);
          }
          if (StringUtil.in(str2, new String[] { "caption", "col", "colgroup", "tbody", "tfoot", "thead" }))
            return exitTableBody(paramToken, paramHtmlTreeBuilder);
          return anythingElse(paramToken, paramHtmlTreeBuilder);
          str1 = paramToken.asEndTag().name();
          if (!StringUtil.in(str1, new String[] { "tbody", "tfoot", "thead" }))
            break;
          if (!paramHtmlTreeBuilder.inTableScope(str1))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          paramHtmlTreeBuilder.clearStackToTableBodyContext();
          paramHtmlTreeBuilder.pop();
          paramHtmlTreeBuilder.transition(InTable);
        }
        if (str1.equals("table"))
          return exitTableBody(paramToken, paramHtmlTreeBuilder);
        if (StringUtil.in(str1, new String[] { "body", "caption", "col", "colgroup", "html", "td", "th", "tr" }))
        {
          paramHtmlTreeBuilder.error(this);
          return false;
        }
        return anythingElse(paramToken, paramHtmlTreeBuilder);
      }
    };
    InRow = new HtmlTreeBuilderState("InRow", 13)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        return paramHtmlTreeBuilder.process(paramToken, InTable);
      }

      private boolean handleMissingTr(Token paramToken, TreeBuilder paramTreeBuilder)
      {
        if (paramTreeBuilder.process(new Token.EndTag("tr")))
          return paramTreeBuilder.process(paramToken);
        return false;
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        String str2;
        if (paramToken.isStartTag())
        {
          Token.StartTag localStartTag = paramToken.asStartTag();
          str2 = localStartTag.name();
          if (StringUtil.in(str2, new String[] { "th", "td" }))
          {
            paramHtmlTreeBuilder.clearStackToTableRowContext();
            paramHtmlTreeBuilder.insert(localStartTag);
            paramHtmlTreeBuilder.transition(InCell);
            paramHtmlTreeBuilder.insertMarkerToFormattingElements();
          }
        }
        while (true)
        {
          return true;
          if (StringUtil.in(str2, new String[] { "caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr" }))
            return handleMissingTr(paramToken, paramHtmlTreeBuilder);
          return anythingElse(paramToken, paramHtmlTreeBuilder);
          if (!paramToken.isEndTag())
            break;
          String str1 = paramToken.asEndTag().name();
          if (str1.equals("tr"))
          {
            if (!paramHtmlTreeBuilder.inTableScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.clearStackToTableRowContext();
            paramHtmlTreeBuilder.pop();
            paramHtmlTreeBuilder.transition(InTableBody);
            continue;
          }
          if (str1.equals("table"))
            return handleMissingTr(paramToken, paramHtmlTreeBuilder);
          if (StringUtil.in(str1, new String[] { "tbody", "tfoot", "thead" }))
          {
            if (!paramHtmlTreeBuilder.inTableScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.process(new Token.EndTag("tr"));
            return paramHtmlTreeBuilder.process(paramToken);
          }
          if (StringUtil.in(str1, new String[] { "body", "caption", "col", "colgroup", "html", "td", "th" }))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          return anythingElse(paramToken, paramHtmlTreeBuilder);
        }
        return anythingElse(paramToken, paramHtmlTreeBuilder);
      }
    };
    InCell = new HtmlTreeBuilderState("InCell", 14)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        return paramHtmlTreeBuilder.process(paramToken, InBody);
      }

      private void closeCell(HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramHtmlTreeBuilder.inTableScope("td"))
        {
          paramHtmlTreeBuilder.process(new Token.EndTag("td"));
          return;
        }
        paramHtmlTreeBuilder.process(new Token.EndTag("th"));
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramToken.isEndTag())
        {
          String str = paramToken.asEndTag().name();
          if (StringUtil.in(str, new String[] { "td", "th" }))
          {
            if (!paramHtmlTreeBuilder.inTableScope(str))
            {
              paramHtmlTreeBuilder.error(this);
              paramHtmlTreeBuilder.transition(InRow);
              return false;
            }
            paramHtmlTreeBuilder.generateImpliedEndTags();
            if (!paramHtmlTreeBuilder.currentElement().nodeName().equals(str))
              paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.popStackToClose(str);
            paramHtmlTreeBuilder.clearFormattingElementsToLastMarker();
            paramHtmlTreeBuilder.transition(InRow);
            return true;
          }
          if (StringUtil.in(str, new String[] { "body", "caption", "col", "colgroup", "html" }))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          if (StringUtil.in(str, new String[] { "table", "tbody", "tfoot", "thead", "tr" }))
          {
            if (!paramHtmlTreeBuilder.inTableScope(str))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            closeCell(paramHtmlTreeBuilder);
            return paramHtmlTreeBuilder.process(paramToken);
          }
          return anythingElse(paramToken, paramHtmlTreeBuilder);
        }
        if (paramToken.isStartTag())
          if (StringUtil.in(paramToken.asStartTag().name(), new String[] { "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr" }))
          {
            if ((!paramHtmlTreeBuilder.inTableScope("td")) && (!paramHtmlTreeBuilder.inTableScope("th")))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            closeCell(paramHtmlTreeBuilder);
            return paramHtmlTreeBuilder.process(paramToken);
          }
        return anythingElse(paramToken, paramHtmlTreeBuilder);
      }
    };
    InSelect = new HtmlTreeBuilderState("InSelect", 15)
    {
      private boolean anythingElse(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        paramHtmlTreeBuilder.error(this);
        return false;
      }

      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        boolean bool2;
        switch (HtmlTreeBuilderState.24.$SwitchMap$org$jsoup$parser$Token$TokenType[paramToken.type.ordinal()])
        {
        default:
          bool2 = anythingElse(paramToken, paramHtmlTreeBuilder);
          return bool2;
        case 5:
          Token.Character localCharacter = paramToken.asCharacter();
          if (localCharacter.getData().equals(HtmlTreeBuilderState.nullString))
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          paramHtmlTreeBuilder.insert(localCharacter);
        case 1:
        case 2:
        case 3:
        case 4:
        case 6:
        }
        while (true)
        {
          return true;
          paramHtmlTreeBuilder.insert(paramToken.asComment());
          continue;
          paramHtmlTreeBuilder.error(this);
          return false;
          Token.StartTag localStartTag = paramToken.asStartTag();
          String str2 = localStartTag.name();
          if (str2.equals("html"))
            return paramHtmlTreeBuilder.process(localStartTag, InBody);
          if (str2.equals("option"))
          {
            paramHtmlTreeBuilder.process(new Token.EndTag("option"));
            paramHtmlTreeBuilder.insert(localStartTag);
            continue;
          }
          if (str2.equals("optgroup"))
          {
            if (paramHtmlTreeBuilder.currentElement().nodeName().equals("option"))
              paramHtmlTreeBuilder.process(new Token.EndTag("option"));
            while (true)
            {
              paramHtmlTreeBuilder.insert(localStartTag);
              break;
              if (!paramHtmlTreeBuilder.currentElement().nodeName().equals("optgroup"))
                continue;
              paramHtmlTreeBuilder.process(new Token.EndTag("optgroup"));
            }
          }
          if (str2.equals("select"))
          {
            paramHtmlTreeBuilder.error(this);
            return paramHtmlTreeBuilder.process(new Token.EndTag("select"));
          }
          if (StringUtil.in(str2, new String[] { "input", "keygen", "textarea" }))
          {
            paramHtmlTreeBuilder.error(this);
            boolean bool1 = paramHtmlTreeBuilder.inSelectScope("select");
            bool2 = false;
            if (!bool1)
              break;
            paramHtmlTreeBuilder.process(new Token.EndTag("select"));
            return paramHtmlTreeBuilder.process(localStartTag);
          }
          if (str2.equals("script"))
            return paramHtmlTreeBuilder.process(paramToken, InHead);
          return anythingElse(paramToken, paramHtmlTreeBuilder);
          String str1 = paramToken.asEndTag().name();
          if (str1.equals("optgroup"))
          {
            if ((paramHtmlTreeBuilder.currentElement().nodeName().equals("option")) && (paramHtmlTreeBuilder.aboveOnStack(paramHtmlTreeBuilder.currentElement()) != null) && (paramHtmlTreeBuilder.aboveOnStack(paramHtmlTreeBuilder.currentElement()).nodeName().equals("optgroup")))
              paramHtmlTreeBuilder.process(new Token.EndTag("option"));
            if (paramHtmlTreeBuilder.currentElement().nodeName().equals("optgroup"))
            {
              paramHtmlTreeBuilder.pop();
              continue;
            }
            paramHtmlTreeBuilder.error(this);
            continue;
          }
          if (str1.equals("option"))
          {
            if (paramHtmlTreeBuilder.currentElement().nodeName().equals("option"))
            {
              paramHtmlTreeBuilder.pop();
              continue;
            }
            paramHtmlTreeBuilder.error(this);
            continue;
          }
          if (str1.equals("select"))
          {
            if (!paramHtmlTreeBuilder.inSelectScope(str1))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.popStackToClose(str1);
            paramHtmlTreeBuilder.resetInsertionMode();
            continue;
          }
          return anythingElse(paramToken, paramHtmlTreeBuilder);
          if (paramHtmlTreeBuilder.currentElement().nodeName().equals("html"))
            continue;
          paramHtmlTreeBuilder.error(this);
        }
      }
    };
    InSelectInTable = new HtmlTreeBuilderState("InSelectInTable", 16)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        boolean bool2;
        if (paramToken.isStartTag())
          if (StringUtil.in(paramToken.asStartTag().name(), new String[] { "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th" }))
          {
            paramHtmlTreeBuilder.error(this);
            paramHtmlTreeBuilder.process(new Token.EndTag("select"));
            bool2 = paramHtmlTreeBuilder.process(paramToken);
          }
        while (true)
        {
          return bool2;
          if (!paramToken.isEndTag())
            break;
          if (!StringUtil.in(paramToken.asEndTag().name(), new String[] { "caption", "table", "tbody", "tfoot", "thead", "tr", "td", "th" }))
            break;
          paramHtmlTreeBuilder.error(this);
          boolean bool1 = paramHtmlTreeBuilder.inTableScope(paramToken.asEndTag().name());
          bool2 = false;
          if (!bool1)
            continue;
          paramHtmlTreeBuilder.process(new Token.EndTag("select"));
          return paramHtmlTreeBuilder.process(paramToken);
        }
        return paramHtmlTreeBuilder.process(paramToken, InSelect);
      }
    };
    AfterBody = new HtmlTreeBuilderState("AfterBody", 17)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          return paramHtmlTreeBuilder.process(paramToken, InBody);
        if (paramToken.isComment())
          paramHtmlTreeBuilder.insert(paramToken.asComment());
        do
          while (true)
          {
            return true;
            if (paramToken.isDoctype())
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("html")))
              return paramHtmlTreeBuilder.process(paramToken, InBody);
            if ((!paramToken.isEndTag()) || (!paramToken.asEndTag().name().equals("html")))
              break;
            if (paramHtmlTreeBuilder.isFragmentParsing())
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.transition(AfterAfterBody);
          }
        while (paramToken.isEOF());
        paramHtmlTreeBuilder.error(this);
        paramHtmlTreeBuilder.transition(InBody);
        return paramHtmlTreeBuilder.process(paramToken);
      }
    };
    InFrameset = new HtmlTreeBuilderState("InFrameset", 18)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          paramHtmlTreeBuilder.insert(paramToken.asCharacter());
        while (true)
        {
          return true;
          if (paramToken.isComment())
          {
            paramHtmlTreeBuilder.insert(paramToken.asComment());
            continue;
          }
          if (paramToken.isDoctype())
          {
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          if (paramToken.isStartTag())
          {
            Token.StartTag localStartTag = paramToken.asStartTag();
            String str = localStartTag.name();
            if (str.equals("html"))
              return paramHtmlTreeBuilder.process(localStartTag, InBody);
            if (str.equals("frameset"))
            {
              paramHtmlTreeBuilder.insert(localStartTag);
              continue;
            }
            if (str.equals("frame"))
            {
              paramHtmlTreeBuilder.insertEmpty(localStartTag);
              continue;
            }
            if (str.equals("noframes"))
              return paramHtmlTreeBuilder.process(localStartTag, InHead);
            paramHtmlTreeBuilder.error(this);
            return false;
          }
          if ((paramToken.isEndTag()) && (paramToken.asEndTag().name().equals("frameset")))
          {
            if (paramHtmlTreeBuilder.currentElement().nodeName().equals("html"))
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            paramHtmlTreeBuilder.pop();
            if ((paramHtmlTreeBuilder.isFragmentParsing()) || (paramHtmlTreeBuilder.currentElement().nodeName().equals("frameset")))
              continue;
            paramHtmlTreeBuilder.transition(AfterFrameset);
            continue;
          }
          if (!paramToken.isEOF())
            break;
          if (paramHtmlTreeBuilder.currentElement().nodeName().equals("html"))
            continue;
          paramHtmlTreeBuilder.error(this);
          return true;
        }
        paramHtmlTreeBuilder.error(this);
        return false;
      }
    };
    AfterFrameset = new HtmlTreeBuilderState("AfterFrameset", 19)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (HtmlTreeBuilderState.access$100(paramToken))
          paramHtmlTreeBuilder.insert(paramToken.asCharacter());
        do
        {
          while (true)
          {
            return true;
            if (paramToken.isComment())
            {
              paramHtmlTreeBuilder.insert(paramToken.asComment());
              continue;
            }
            if (paramToken.isDoctype())
            {
              paramHtmlTreeBuilder.error(this);
              return false;
            }
            if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("html")))
              return paramHtmlTreeBuilder.process(paramToken, InBody);
            if ((!paramToken.isEndTag()) || (!paramToken.asEndTag().name().equals("html")))
              break;
            paramHtmlTreeBuilder.transition(AfterAfterFrameset);
          }
          if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("noframes")))
            return paramHtmlTreeBuilder.process(paramToken, InHead);
        }
        while (paramToken.isEOF());
        paramHtmlTreeBuilder.error(this);
        return false;
      }
    };
    AfterAfterBody = new HtmlTreeBuilderState("AfterAfterBody", 20)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramToken.isComment())
          paramHtmlTreeBuilder.insert(paramToken.asComment());
        do
        {
          return true;
          if ((paramToken.isDoctype()) || (HtmlTreeBuilderState.access$100(paramToken)) || ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("html"))))
            return paramHtmlTreeBuilder.process(paramToken, InBody);
        }
        while (paramToken.isEOF());
        paramHtmlTreeBuilder.error(this);
        paramHtmlTreeBuilder.transition(InBody);
        return paramHtmlTreeBuilder.process(paramToken);
      }
    };
    AfterAfterFrameset = new HtmlTreeBuilderState("AfterAfterFrameset", 21)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        if (paramToken.isComment())
          paramHtmlTreeBuilder.insert(paramToken.asComment());
        do
        {
          return true;
          if ((paramToken.isDoctype()) || (HtmlTreeBuilderState.access$100(paramToken)) || ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("html"))))
            return paramHtmlTreeBuilder.process(paramToken, InBody);
        }
        while (paramToken.isEOF());
        if ((paramToken.isStartTag()) && (paramToken.asStartTag().name().equals("noframes")))
          return paramHtmlTreeBuilder.process(paramToken, InHead);
        paramHtmlTreeBuilder.error(this);
        return false;
      }
    };
    ForeignContent = new HtmlTreeBuilderState("ForeignContent", 22)
    {
      boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder)
      {
        return true;
      }
    };
    HtmlTreeBuilderState[] arrayOfHtmlTreeBuilderState = new HtmlTreeBuilderState[23];
    arrayOfHtmlTreeBuilderState[0] = Initial;
    arrayOfHtmlTreeBuilderState[1] = BeforeHtml;
    arrayOfHtmlTreeBuilderState[2] = BeforeHead;
    arrayOfHtmlTreeBuilderState[3] = InHead;
    arrayOfHtmlTreeBuilderState[4] = InHeadNoscript;
    arrayOfHtmlTreeBuilderState[5] = AfterHead;
    arrayOfHtmlTreeBuilderState[6] = InBody;
    arrayOfHtmlTreeBuilderState[7] = Text;
    arrayOfHtmlTreeBuilderState[8] = InTable;
    arrayOfHtmlTreeBuilderState[9] = InTableText;
    arrayOfHtmlTreeBuilderState[10] = InCaption;
    arrayOfHtmlTreeBuilderState[11] = InColumnGroup;
    arrayOfHtmlTreeBuilderState[12] = InTableBody;
    arrayOfHtmlTreeBuilderState[13] = InRow;
    arrayOfHtmlTreeBuilderState[14] = InCell;
    arrayOfHtmlTreeBuilderState[15] = InSelect;
    arrayOfHtmlTreeBuilderState[16] = InSelectInTable;
    arrayOfHtmlTreeBuilderState[17] = AfterBody;
    arrayOfHtmlTreeBuilderState[18] = InFrameset;
    arrayOfHtmlTreeBuilderState[19] = AfterFrameset;
    arrayOfHtmlTreeBuilderState[20] = AfterAfterBody;
    arrayOfHtmlTreeBuilderState[21] = AfterAfterFrameset;
    arrayOfHtmlTreeBuilderState[22] = ForeignContent;
    $VALUES = arrayOfHtmlTreeBuilderState;
    nullString = String.valueOf('\000');
  }

  private static void handleRawtext(Token.StartTag paramStartTag, HtmlTreeBuilder paramHtmlTreeBuilder)
  {
    paramHtmlTreeBuilder.insert(paramStartTag);
    paramHtmlTreeBuilder.tokeniser.transition(TokeniserState.Rawtext);
    paramHtmlTreeBuilder.markInsertionMode();
    paramHtmlTreeBuilder.transition(Text);
  }

  private static void handleRcData(Token.StartTag paramStartTag, HtmlTreeBuilder paramHtmlTreeBuilder)
  {
    paramHtmlTreeBuilder.insert(paramStartTag);
    paramHtmlTreeBuilder.tokeniser.transition(TokeniserState.Rcdata);
    paramHtmlTreeBuilder.markInsertionMode();
    paramHtmlTreeBuilder.transition(Text);
  }

  private static boolean isWhitespace(Token paramToken)
  {
    String str;
    if (paramToken.isCharacter())
      str = paramToken.asCharacter().getData();
    for (int i = 0; i < str.length(); i++)
      if (!StringUtil.isWhitespace(str.charAt(i)))
        return false;
    return true;
  }

  abstract boolean process(Token paramToken, HtmlTreeBuilder paramHtmlTreeBuilder);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.HtmlTreeBuilderState
 * JD-Core Version:    0.6.0
 */