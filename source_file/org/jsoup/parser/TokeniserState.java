package org.jsoup.parser;

 enum TokeniserState
{
  private static final char eof = '￿';
  private static final char nullChar = '\000';
  private static final char replacementChar = '�';
  private static final String replacementStr;

  static
  {
    CharacterReferenceInData = new TokeniserState("CharacterReferenceInData", 1)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        Character localCharacter = paramTokeniser.consumeCharacterReference(null, false);
        if (localCharacter == null)
          paramTokeniser.emit('&');
        while (true)
        {
          paramTokeniser.transition(Data);
          return;
          paramTokeniser.emit(localCharacter.charValue());
        }
      }
    };
    Rcdata = new TokeniserState("Rcdata", 2)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.current())
        {
        default:
          paramTokeniser.emit(paramCharacterReader.consumeToAny(new char[] { 38, 60, 0 }));
          return;
        case '&':
          paramTokeniser.advanceTransition(CharacterReferenceInRcdata);
          return;
        case '<':
          paramTokeniser.advanceTransition(RcdataLessthanSign);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramCharacterReader.advance();
          paramTokeniser.emit(65533);
          return;
        case '￿':
        }
        paramTokeniser.emit(new Token.EOF());
      }
    };
    CharacterReferenceInRcdata = new TokeniserState("CharacterReferenceInRcdata", 3)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        Character localCharacter = paramTokeniser.consumeCharacterReference(null, false);
        if (localCharacter == null)
          paramTokeniser.emit('&');
        while (true)
        {
          paramTokeniser.transition(Rcdata);
          return;
          paramTokeniser.emit(localCharacter.charValue());
        }
      }
    };
    Rawtext = new TokeniserState("Rawtext", 4)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.current())
        {
        default:
          paramTokeniser.emit(paramCharacterReader.consumeToAny(new char[] { 60, 0 }));
          return;
        case '<':
          paramTokeniser.advanceTransition(RawtextLessthanSign);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramCharacterReader.advance();
          paramTokeniser.emit(65533);
          return;
        case '￿':
        }
        paramTokeniser.emit(new Token.EOF());
      }
    };
    ScriptData = new TokeniserState("ScriptData", 5)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.current())
        {
        default:
          paramTokeniser.emit(paramCharacterReader.consumeToAny(new char[] { 60, 0 }));
          return;
        case '<':
          paramTokeniser.advanceTransition(ScriptDataLessthanSign);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramCharacterReader.advance();
          paramTokeniser.emit(65533);
          return;
        case '￿':
        }
        paramTokeniser.emit(new Token.EOF());
      }
    };
    PLAINTEXT = new TokeniserState("PLAINTEXT", 6)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.current())
        {
        default:
          paramTokeniser.emit(paramCharacterReader.consumeTo('\000'));
          return;
        case '\000':
          paramTokeniser.error(this);
          paramCharacterReader.advance();
          paramTokeniser.emit(65533);
          return;
        case '￿':
        }
        paramTokeniser.emit(new Token.EOF());
      }
    };
    TagOpen = new TokeniserState("TagOpen", 7)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.current())
        {
        default:
          if (!paramCharacterReader.matchesLetter())
            break;
          paramTokeniser.createTagPending(true);
          paramTokeniser.transition(TagName);
          return;
        case '!':
          paramTokeniser.advanceTransition(MarkupDeclarationOpen);
          return;
        case '/':
          paramTokeniser.advanceTransition(EndTagOpen);
          return;
        case '?':
          paramTokeniser.advanceTransition(BogusComment);
          return;
        }
        paramTokeniser.error(this);
        paramTokeniser.emit('<');
        paramTokeniser.transition(Data);
      }
    };
    EndTagOpen = new TokeniserState("EndTagOpen", 8)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.isEmpty())
        {
          paramTokeniser.eofError(this);
          paramTokeniser.emit("</");
          paramTokeniser.transition(Data);
          return;
        }
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createTagPending(false);
          paramTokeniser.transition(TagName);
          return;
        }
        if (paramCharacterReader.matches('>'))
        {
          paramTokeniser.error(this);
          paramTokeniser.advanceTransition(Data);
          return;
        }
        paramTokeniser.error(this);
        paramTokeniser.advanceTransition(BogusComment);
      }
    };
    TagName = new TokeniserState("TagName", 9)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        String str = paramCharacterReader.consumeToAny(new char[] { 9, 10, 12, 32, 47, 62, 0 }).toLowerCase();
        paramTokeniser.tagPending.appendTagName(str);
        switch (paramCharacterReader.consume())
        {
        default:
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BeforeAttributeName);
          return;
        case '/':
          paramTokeniser.transition(SelfClosingStartTag);
          return;
        case '>':
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.tagPending.appendTagName(TokeniserState.replacementStr);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    RcdataLessthanSign = new TokeniserState("RcdataLessthanSign", 10)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matches('/'))
        {
          paramTokeniser.createTempBuffer();
          paramTokeniser.advanceTransition(RCDATAEndTagOpen);
          return;
        }
        if ((paramCharacterReader.matchesLetter()) && (!paramCharacterReader.containsIgnoreCase("</" + paramTokeniser.appropriateEndTagName())))
        {
          paramTokeniser.tagPending = new Token.EndTag(paramTokeniser.appropriateEndTagName());
          paramTokeniser.emitTagPending();
          paramCharacterReader.unconsume();
          paramTokeniser.transition(Data);
          return;
        }
        paramTokeniser.emit("<");
        paramTokeniser.transition(Rcdata);
      }
    };
    RCDATAEndTagOpen = new TokeniserState("RCDATAEndTagOpen", 11)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createTagPending(false);
          paramTokeniser.tagPending.appendTagName(Character.toLowerCase(paramCharacterReader.current()));
          paramTokeniser.dataBuffer.append(Character.toLowerCase(paramCharacterReader.current()));
          paramTokeniser.advanceTransition(RCDATAEndTagName);
          return;
        }
        paramTokeniser.emit("</");
        paramTokeniser.transition(Rcdata);
      }
    };
    RCDATAEndTagName = new TokeniserState("RCDATAEndTagName", 12)
    {
      private void anythingElse(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        paramTokeniser.emit("</" + paramTokeniser.dataBuffer.toString());
        paramTokeniser.transition(Rcdata);
      }

      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.tagPending.appendTagName(str.toLowerCase());
          paramTokeniser.dataBuffer.append(str);
          return;
        }
        switch (paramCharacterReader.consume())
        {
        default:
          anythingElse(paramTokeniser, paramCharacterReader);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          if (paramTokeniser.isAppropriateEndTagToken())
          {
            paramTokeniser.transition(BeforeAttributeName);
            return;
          }
          anythingElse(paramTokeniser, paramCharacterReader);
          return;
        case '/':
          if (paramTokeniser.isAppropriateEndTagToken())
          {
            paramTokeniser.transition(SelfClosingStartTag);
            return;
          }
          anythingElse(paramTokeniser, paramCharacterReader);
          return;
        case '>':
        }
        if (paramTokeniser.isAppropriateEndTagToken())
        {
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        }
        anythingElse(paramTokeniser, paramCharacterReader);
      }
    };
    RawtextLessthanSign = new TokeniserState("RawtextLessthanSign", 13)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matches('/'))
        {
          paramTokeniser.createTempBuffer();
          paramTokeniser.advanceTransition(RawtextEndTagOpen);
          return;
        }
        paramTokeniser.emit('<');
        paramTokeniser.transition(Rawtext);
      }
    };
    RawtextEndTagOpen = new TokeniserState("RawtextEndTagOpen", 14)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createTagPending(false);
          paramTokeniser.transition(RawtextEndTagName);
          return;
        }
        paramTokeniser.emit("</");
        paramTokeniser.transition(Rawtext);
      }
    };
    RawtextEndTagName = new TokeniserState("RawtextEndTagName", 15)
    {
      private void anythingElse(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        paramTokeniser.emit("</" + paramTokeniser.dataBuffer.toString());
        paramTokeniser.transition(Rawtext);
      }

      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.tagPending.appendTagName(str.toLowerCase());
          paramTokeniser.dataBuffer.append(str);
          return;
        }
        if ((paramTokeniser.isAppropriateEndTagToken()) && (!paramCharacterReader.isEmpty()))
        {
          char c = paramCharacterReader.consume();
          switch (c)
          {
          default:
            paramTokeniser.dataBuffer.append(c);
            anythingElse(paramTokeniser, paramCharacterReader);
            return;
          case '\t':
          case '\n':
          case '\f':
          case ' ':
            paramTokeniser.transition(BeforeAttributeName);
            return;
          case '/':
            paramTokeniser.transition(SelfClosingStartTag);
            return;
          case '>':
          }
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        }
        anythingElse(paramTokeniser, paramCharacterReader);
      }
    };
    ScriptDataLessthanSign = new TokeniserState("ScriptDataLessthanSign", 16)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.emit("<");
          paramCharacterReader.unconsume();
          paramTokeniser.transition(ScriptData);
          return;
        case '/':
          paramTokeniser.createTempBuffer();
          paramTokeniser.transition(ScriptDataEndTagOpen);
          return;
        case '!':
        }
        paramTokeniser.emit("<!");
        paramTokeniser.transition(ScriptDataEscapeStart);
      }
    };
    ScriptDataEndTagOpen = new TokeniserState("ScriptDataEndTagOpen", 17)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createTagPending(false);
          paramTokeniser.transition(ScriptDataEndTagName);
          return;
        }
        paramTokeniser.emit("</");
        paramTokeniser.transition(ScriptData);
      }
    };
    ScriptDataEndTagName = new TokeniserState("ScriptDataEndTagName", 18)
    {
      private void anythingElse(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        paramTokeniser.emit("</" + paramTokeniser.dataBuffer.toString());
        paramTokeniser.transition(ScriptData);
      }

      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.tagPending.appendTagName(str.toLowerCase());
          paramTokeniser.dataBuffer.append(str);
          return;
        }
        if ((paramTokeniser.isAppropriateEndTagToken()) && (!paramCharacterReader.isEmpty()))
        {
          char c = paramCharacterReader.consume();
          switch (c)
          {
          default:
            paramTokeniser.dataBuffer.append(c);
            anythingElse(paramTokeniser, paramCharacterReader);
            return;
          case '\t':
          case '\n':
          case '\f':
          case ' ':
            paramTokeniser.transition(BeforeAttributeName);
            return;
          case '/':
            paramTokeniser.transition(SelfClosingStartTag);
            return;
          case '>':
          }
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        }
        anythingElse(paramTokeniser, paramCharacterReader);
      }
    };
    ScriptDataEscapeStart = new TokeniserState("ScriptDataEscapeStart", 19)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matches('-'))
        {
          paramTokeniser.emit('-');
          paramTokeniser.advanceTransition(ScriptDataEscapeStartDash);
          return;
        }
        paramTokeniser.transition(ScriptData);
      }
    };
    ScriptDataEscapeStartDash = new TokeniserState("ScriptDataEscapeStartDash", 20)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matches('-'))
        {
          paramTokeniser.emit('-');
          paramTokeniser.advanceTransition(ScriptDataEscapedDashDash);
          return;
        }
        paramTokeniser.transition(ScriptData);
      }
    };
    ScriptDataEscaped = new TokeniserState("ScriptDataEscaped", 21)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.isEmpty())
        {
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        }
        switch (paramCharacterReader.current())
        {
        default:
          paramTokeniser.emit(paramCharacterReader.consumeToAny(new char[] { 45, 60, 0 }));
          return;
        case '-':
          paramTokeniser.emit('-');
          paramTokeniser.advanceTransition(ScriptDataEscapedDash);
          return;
        case '<':
          paramTokeniser.advanceTransition(ScriptDataEscapedLessthanSign);
          return;
        case '\000':
        }
        paramTokeniser.error(this);
        paramCharacterReader.advance();
        paramTokeniser.emit(65533);
      }
    };
    ScriptDataEscapedDash = new TokeniserState("ScriptDataEscapedDash", 22)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.isEmpty())
        {
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        }
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataEscaped);
          return;
        case '-':
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataEscapedDashDash);
          return;
        case '<':
          paramTokeniser.transition(ScriptDataEscapedLessthanSign);
          return;
        case '\000':
        }
        paramTokeniser.error(this);
        paramTokeniser.emit(65533);
        paramTokeniser.transition(ScriptDataEscaped);
      }
    };
    ScriptDataEscapedDashDash = new TokeniserState("ScriptDataEscapedDashDash", 23)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.isEmpty())
        {
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        }
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataEscaped);
          return;
        case '-':
          paramTokeniser.emit(c);
          return;
        case '<':
          paramTokeniser.transition(ScriptDataEscapedLessthanSign);
          return;
        case '>':
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptData);
          return;
        case '\000':
        }
        paramTokeniser.error(this);
        paramTokeniser.emit(65533);
        paramTokeniser.transition(ScriptDataEscaped);
      }
    };
    ScriptDataEscapedLessthanSign = new TokeniserState("ScriptDataEscapedLessthanSign", 24)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createTempBuffer();
          paramTokeniser.dataBuffer.append(Character.toLowerCase(paramCharacterReader.current()));
          paramTokeniser.emit("<" + paramCharacterReader.current());
          paramTokeniser.advanceTransition(ScriptDataDoubleEscapeStart);
          return;
        }
        if (paramCharacterReader.matches('/'))
        {
          paramTokeniser.createTempBuffer();
          paramTokeniser.advanceTransition(ScriptDataEscapedEndTagOpen);
          return;
        }
        paramTokeniser.emit('<');
        paramTokeniser.transition(ScriptDataEscaped);
      }
    };
    ScriptDataEscapedEndTagOpen = new TokeniserState("ScriptDataEscapedEndTagOpen", 25)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createTagPending(false);
          paramTokeniser.tagPending.appendTagName(Character.toLowerCase(paramCharacterReader.current()));
          paramTokeniser.dataBuffer.append(paramCharacterReader.current());
          paramTokeniser.advanceTransition(ScriptDataEscapedEndTagName);
          return;
        }
        paramTokeniser.emit("</");
        paramTokeniser.transition(ScriptDataEscaped);
      }
    };
    ScriptDataEscapedEndTagName = new TokeniserState("ScriptDataEscapedEndTagName", 26)
    {
      private void anythingElse(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        paramTokeniser.emit("</" + paramTokeniser.dataBuffer.toString());
        paramTokeniser.transition(ScriptDataEscaped);
      }

      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.tagPending.appendTagName(str.toLowerCase());
          paramTokeniser.dataBuffer.append(str);
          paramCharacterReader.advance();
          return;
        }
        if ((paramTokeniser.isAppropriateEndTagToken()) && (!paramCharacterReader.isEmpty()))
        {
          char c = paramCharacterReader.consume();
          switch (c)
          {
          default:
            paramTokeniser.dataBuffer.append(c);
            anythingElse(paramTokeniser, paramCharacterReader);
            return;
          case '\t':
          case '\n':
          case '\f':
          case ' ':
            paramTokeniser.transition(BeforeAttributeName);
            return;
          case '/':
            paramTokeniser.transition(SelfClosingStartTag);
            return;
          case '>':
          }
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        }
        anythingElse(paramTokeniser, paramCharacterReader);
      }
    };
    ScriptDataDoubleEscapeStart = new TokeniserState("ScriptDataDoubleEscapeStart", 27)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.dataBuffer.append(str.toLowerCase());
          paramTokeniser.emit(str);
          return;
        }
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramCharacterReader.unconsume();
          paramTokeniser.transition(ScriptDataEscaped);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
        case '/':
        case '>':
        }
        if (paramTokeniser.dataBuffer.toString().equals("script"))
          paramTokeniser.transition(ScriptDataDoubleEscaped);
        while (true)
        {
          paramTokeniser.emit(c);
          return;
          paramTokeniser.transition(ScriptDataEscaped);
        }
      }
    };
    ScriptDataDoubleEscaped = new TokeniserState("ScriptDataDoubleEscaped", 28)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.current();
        switch (c)
        {
        default:
          paramTokeniser.emit(paramCharacterReader.consumeToAny(new char[] { 45, 60, 0 }));
          return;
        case '-':
          paramTokeniser.emit(c);
          paramTokeniser.advanceTransition(ScriptDataDoubleEscapedDash);
          return;
        case '<':
          paramTokeniser.emit(c);
          paramTokeniser.advanceTransition(ScriptDataDoubleEscapedLessthanSign);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramCharacterReader.advance();
          paramTokeniser.emit(65533);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    ScriptDataDoubleEscapedDash = new TokeniserState("ScriptDataDoubleEscapedDash", 29)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataDoubleEscaped);
          return;
        case '-':
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataDoubleEscapedDashDash);
          return;
        case '<':
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.emit(65533);
          paramTokeniser.transition(ScriptDataDoubleEscaped);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    ScriptDataDoubleEscapedDashDash = new TokeniserState("ScriptDataDoubleEscapedDashDash", 30)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataDoubleEscaped);
          return;
        case '-':
          paramTokeniser.emit(c);
          return;
        case '<':
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
          return;
        case '>':
          paramTokeniser.emit(c);
          paramTokeniser.transition(ScriptData);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.emit(65533);
          paramTokeniser.transition(ScriptDataDoubleEscaped);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    ScriptDataDoubleEscapedLessthanSign = new TokeniserState("ScriptDataDoubleEscapedLessthanSign", 31)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matches('/'))
        {
          paramTokeniser.emit('/');
          paramTokeniser.createTempBuffer();
          paramTokeniser.advanceTransition(ScriptDataDoubleEscapeEnd);
          return;
        }
        paramTokeniser.transition(ScriptDataDoubleEscaped);
      }
    };
    ScriptDataDoubleEscapeEnd = new TokeniserState("ScriptDataDoubleEscapeEnd", 32)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.dataBuffer.append(str.toLowerCase());
          paramTokeniser.emit(str);
          return;
        }
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramCharacterReader.unconsume();
          paramTokeniser.transition(ScriptDataDoubleEscaped);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
        case '/':
        case '>':
        }
        if (paramTokeniser.dataBuffer.toString().equals("script"))
          paramTokeniser.transition(ScriptDataEscaped);
        while (true)
        {
          paramTokeniser.emit(c);
          return;
          paramTokeniser.transition(ScriptDataDoubleEscaped);
        }
      }
    };
    BeforeAttributeName = new TokeniserState("BeforeAttributeName", 33)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.tagPending.newAttribute();
          paramCharacterReader.unconsume();
          paramTokeniser.transition(AttributeName);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '/':
          paramTokeniser.transition(SelfClosingStartTag);
          return;
        case '>':
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.newAttribute();
          paramCharacterReader.unconsume();
          paramTokeniser.transition(AttributeName);
          return;
        case '￿':
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        case '"':
        case '\'':
        case '<':
        case '=':
        }
        paramTokeniser.error(this);
        paramTokeniser.tagPending.newAttribute();
        paramTokeniser.tagPending.appendAttributeName(c);
        paramTokeniser.transition(AttributeName);
      }
    };
    AttributeName = new TokeniserState("AttributeName", 34)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        String str = paramCharacterReader.consumeToAny(new char[] { 9, 10, 12, 32, 47, 61, 62, 0, 34, 39, 60 });
        paramTokeniser.tagPending.appendAttributeName(str.toLowerCase());
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(AfterAttributeName);
          return;
        case '/':
          paramTokeniser.transition(SelfClosingStartTag);
          return;
        case '=':
          paramTokeniser.transition(BeforeAttributeValue);
          return;
        case '>':
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.appendAttributeName(65533);
          return;
        case '￿':
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        case '"':
        case '\'':
        case '<':
        }
        paramTokeniser.error(this);
        paramTokeniser.tagPending.appendAttributeName(c);
      }
    };
    AfterAttributeName = new TokeniserState("AfterAttributeName", 35)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.tagPending.newAttribute();
          paramCharacterReader.unconsume();
          paramTokeniser.transition(AttributeName);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '/':
          paramTokeniser.transition(SelfClosingStartTag);
          return;
        case '=':
          paramTokeniser.transition(BeforeAttributeValue);
          return;
        case '>':
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.appendAttributeName(65533);
          paramTokeniser.transition(AttributeName);
          return;
        case '￿':
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        case '"':
        case '\'':
        case '<':
        }
        paramTokeniser.error(this);
        paramTokeniser.tagPending.newAttribute();
        paramTokeniser.tagPending.appendAttributeName(c);
        paramTokeniser.transition(AttributeName);
      }
    };
    BeforeAttributeValue = new TokeniserState("BeforeAttributeValue", 36)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramCharacterReader.unconsume();
          paramTokeniser.transition(AttributeValue_unquoted);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '"':
          paramTokeniser.transition(AttributeValue_doubleQuoted);
          return;
        case '&':
          paramCharacterReader.unconsume();
          paramTokeniser.transition(AttributeValue_unquoted);
          return;
        case '\'':
          paramTokeniser.transition(AttributeValue_singleQuoted);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.appendAttributeValue(65533);
          paramTokeniser.transition(AttributeValue_unquoted);
          return;
        case '￿':
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '<':
        case '=':
        case '`':
        }
        paramTokeniser.error(this);
        paramTokeniser.tagPending.appendAttributeValue(c);
        paramTokeniser.transition(AttributeValue_unquoted);
      }
    };
    AttributeValue_doubleQuoted = new TokeniserState("AttributeValue_doubleQuoted", 37)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        String str = paramCharacterReader.consumeToAny(new char[] { 34, 38, 0 });
        if (str.length() > 0)
          paramTokeniser.tagPending.appendAttributeValue(str);
        switch (paramCharacterReader.consume())
        {
        default:
          return;
        case '"':
          paramTokeniser.transition(AfterAttributeValue_quoted);
          return;
        case '&':
          Character localCharacter = paramTokeniser.consumeCharacterReference(Character.valueOf('"'), true);
          if (localCharacter != null)
          {
            paramTokeniser.tagPending.appendAttributeValue(localCharacter.charValue());
            return;
          }
          paramTokeniser.tagPending.appendAttributeValue('&');
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.appendAttributeValue(65533);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    AttributeValue_singleQuoted = new TokeniserState("AttributeValue_singleQuoted", 38)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        String str = paramCharacterReader.consumeToAny(new char[] { 39, 38, 0 });
        if (str.length() > 0)
          paramTokeniser.tagPending.appendAttributeValue(str);
        switch (paramCharacterReader.consume())
        {
        default:
          return;
        case '\'':
          paramTokeniser.transition(AfterAttributeValue_quoted);
          return;
        case '&':
          Character localCharacter = paramTokeniser.consumeCharacterReference(Character.valueOf('\''), true);
          if (localCharacter != null)
          {
            paramTokeniser.tagPending.appendAttributeValue(localCharacter.charValue());
            return;
          }
          paramTokeniser.tagPending.appendAttributeValue('&');
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.appendAttributeValue(65533);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    AttributeValue_unquoted = new TokeniserState("AttributeValue_unquoted", 39)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        String str = paramCharacterReader.consumeToAny(new char[] { 9, 10, 12, 32, 38, 62, 0, 34, 39, 60, 61, 96 });
        if (str.length() > 0)
          paramTokeniser.tagPending.appendAttributeValue(str);
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BeforeAttributeName);
          return;
        case '&':
          Character localCharacter = paramTokeniser.consumeCharacterReference(Character.valueOf('>'), true);
          if (localCharacter != null)
          {
            paramTokeniser.tagPending.appendAttributeValue(localCharacter.charValue());
            return;
          }
          paramTokeniser.tagPending.appendAttributeValue('&');
          return;
        case '>':
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.tagPending.appendAttributeValue(65533);
          return;
        case '￿':
          paramTokeniser.eofError(this);
          paramTokeniser.transition(Data);
          return;
        case '"':
        case '\'':
        case '<':
        case '=':
        case '`':
        }
        paramTokeniser.error(this);
        paramTokeniser.tagPending.appendAttributeValue(c);
      }
    };
    AfterAttributeValue_quoted = new TokeniserState("AfterAttributeValue_quoted", 40)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramCharacterReader.unconsume();
          paramTokeniser.transition(BeforeAttributeName);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BeforeAttributeName);
          return;
        case '/':
          paramTokeniser.transition(SelfClosingStartTag);
          return;
        case '>':
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    SelfClosingStartTag = new TokeniserState("SelfClosingStartTag", 41)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.transition(BeforeAttributeName);
          return;
        case '>':
          paramTokeniser.tagPending.selfClosing = true;
          paramTokeniser.emitTagPending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.transition(Data);
      }
    };
    BogusComment = new TokeniserState("BogusComment", 42)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        paramCharacterReader.unconsume();
        Token.Comment localComment = new Token.Comment();
        localComment.data.append(paramCharacterReader.consumeTo('>'));
        paramTokeniser.emit(localComment);
        paramTokeniser.advanceTransition(Data);
      }
    };
    MarkupDeclarationOpen = new TokeniserState("MarkupDeclarationOpen", 43)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchConsume("--"))
        {
          paramTokeniser.createCommentPending();
          paramTokeniser.transition(CommentStart);
          return;
        }
        if (paramCharacterReader.matchConsumeIgnoreCase("DOCTYPE"))
        {
          paramTokeniser.transition(Doctype);
          return;
        }
        if (paramCharacterReader.matchConsume("[CDATA["))
        {
          paramTokeniser.transition(CdataSection);
          return;
        }
        paramTokeniser.error(this);
        paramTokeniser.advanceTransition(BogusComment);
      }
    };
    CommentStart = new TokeniserState("CommentStart", 44)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.commentPending.data.append(c);
          paramTokeniser.transition(Comment);
          return;
        case '-':
          paramTokeniser.transition(CommentStartDash);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append(65533);
          paramTokeniser.transition(Comment);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.emitCommentPending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.emitCommentPending();
        paramTokeniser.transition(Data);
      }
    };
    CommentStartDash = new TokeniserState("CommentStartDash", 45)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.commentPending.data.append(c);
          paramTokeniser.transition(Comment);
          return;
        case '-':
          paramTokeniser.transition(CommentStartDash);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append(65533);
          paramTokeniser.transition(Comment);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.emitCommentPending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.emitCommentPending();
        paramTokeniser.transition(Data);
      }
    };
    Comment = new TokeniserState("Comment", 46)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.current())
        {
        default:
          paramTokeniser.commentPending.data.append(paramCharacterReader.consumeToAny(new char[] { 45, 0 }));
          return;
        case '-':
          paramTokeniser.advanceTransition(CommentEndDash);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramCharacterReader.advance();
          paramTokeniser.commentPending.data.append(65533);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.emitCommentPending();
        paramTokeniser.transition(Data);
      }
    };
    CommentEndDash = new TokeniserState("CommentEndDash", 47)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.commentPending.data.append('-').append(c);
          paramTokeniser.transition(Comment);
          return;
        case '-':
          paramTokeniser.transition(CommentEnd);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append('-').append(65533);
          paramTokeniser.transition(Comment);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.emitCommentPending();
        paramTokeniser.transition(Data);
      }
    };
    CommentEnd = new TokeniserState("CommentEnd", 48)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append("--").append(c);
          paramTokeniser.transition(Comment);
          return;
        case '>':
          paramTokeniser.emitCommentPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append("--").append(65533);
          paramTokeniser.transition(Comment);
          return;
        case '!':
          paramTokeniser.error(this);
          paramTokeniser.transition(CommentEndBang);
          return;
        case '-':
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append('-');
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.emitCommentPending();
        paramTokeniser.transition(Data);
      }
    };
    CommentEndBang = new TokeniserState("CommentEndBang", 49)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.commentPending.data.append("--!").append(c);
          paramTokeniser.transition(Comment);
          return;
        case '-':
          paramTokeniser.commentPending.data.append("--!");
          paramTokeniser.transition(CommentEndDash);
          return;
        case '>':
          paramTokeniser.emitCommentPending();
          paramTokeniser.transition(Data);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.commentPending.data.append("--!").append(65533);
          paramTokeniser.transition(Comment);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.emitCommentPending();
        paramTokeniser.transition(Data);
      }
    };
    Doctype = new TokeniserState("Doctype", 50)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.transition(BeforeDoctypeName);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BeforeDoctypeName);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.createDoctypePending();
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    BeforeDoctypeName = new TokeniserState("BeforeDoctypeName", 51)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          paramTokeniser.createDoctypePending();
          paramTokeniser.transition(DoctypeName);
          return;
        }
        char c = paramCharacterReader.consume();
        switch (c)
        {
        case '\t':
        case '\n':
        case '\f':
        case ' ':
        default:
          paramTokeniser.createDoctypePending();
          paramTokeniser.doctypePending.name.append(c);
          paramTokeniser.transition(DoctypeName);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.name.append(65533);
          paramTokeniser.transition(DoctypeName);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.createDoctypePending();
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    DoctypeName = new TokeniserState("DoctypeName", 52)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.matchesLetter())
        {
          String str = paramCharacterReader.consumeLetterSequence();
          paramTokeniser.doctypePending.name.append(str.toLowerCase());
          return;
        }
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.doctypePending.name.append(c);
          return;
        case '>':
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(AfterDoctypeName);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.name.append(65533);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    AfterDoctypeName = new TokeniserState("AfterDoctypeName", 53)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        if (paramCharacterReader.isEmpty())
        {
          paramTokeniser.eofError(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        }
        if (paramCharacterReader.matchesAny(new char[] { 9, 10, 12, 32 }))
        {
          paramCharacterReader.advance();
          return;
        }
        if (paramCharacterReader.matches('>'))
        {
          paramTokeniser.emitDoctypePending();
          paramTokeniser.advanceTransition(Data);
          return;
        }
        if (paramCharacterReader.matchConsumeIgnoreCase("PUBLIC"))
        {
          paramTokeniser.transition(AfterDoctypePublicKeyword);
          return;
        }
        if (paramCharacterReader.matchConsumeIgnoreCase("SYSTEM"))
        {
          paramTokeniser.transition(AfterDoctypeSystemKeyword);
          return;
        }
        paramTokeniser.error(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.advanceTransition(BogusDoctype);
      }
    };
    AfterDoctypePublicKeyword = new TokeniserState("AfterDoctypePublicKeyword", 54)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.transition(BogusDoctype);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BeforeDoctypePublicIdentifier);
          return;
        case '"':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
          return;
        case '\'':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypePublicIdentifier_singleQuoted);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    BeforeDoctypePublicIdentifier = new TokeniserState("BeforeDoctypePublicIdentifier", 55)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.transition(BogusDoctype);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '"':
          paramTokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
          return;
        case '\'':
          paramTokeniser.transition(DoctypePublicIdentifier_singleQuoted);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    DoctypePublicIdentifier_doubleQuoted = new TokeniserState("DoctypePublicIdentifier_doubleQuoted", 56)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.doctypePending.publicIdentifier.append(c);
          return;
        case '"':
          paramTokeniser.transition(AfterDoctypePublicIdentifier);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.publicIdentifier.append(65533);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    DoctypePublicIdentifier_singleQuoted = new TokeniserState("DoctypePublicIdentifier_singleQuoted", 57)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.doctypePending.publicIdentifier.append(c);
          return;
        case '\'':
          paramTokeniser.transition(AfterDoctypePublicIdentifier);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.publicIdentifier.append(65533);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    AfterDoctypePublicIdentifier = new TokeniserState("AfterDoctypePublicIdentifier", 58)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.transition(BogusDoctype);
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BetweenDoctypePublicAndSystemIdentifiers);
          return;
        case '>':
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '"':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
          return;
        case '\'':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    BetweenDoctypePublicAndSystemIdentifiers = new TokeniserState("BetweenDoctypePublicAndSystemIdentifiers", 59)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.transition(BogusDoctype);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '>':
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '"':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
          return;
        case '\'':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    AfterDoctypeSystemKeyword = new TokeniserState("AfterDoctypeSystemKeyword", 60)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          return;
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          paramTokeniser.transition(BeforeDoctypeSystemIdentifier);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '"':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
          return;
        case '\'':
          paramTokeniser.error(this);
          paramTokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    BeforeDoctypeSystemIdentifier = new TokeniserState("BeforeDoctypeSystemIdentifier", 61)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.transition(BogusDoctype);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '"':
          paramTokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
          return;
        case '\'':
          paramTokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    DoctypeSystemIdentifier_doubleQuoted = new TokeniserState("DoctypeSystemIdentifier_doubleQuoted", 62)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.doctypePending.systemIdentifier.append(c);
          return;
        case '"':
          paramTokeniser.transition(AfterDoctypeSystemIdentifier);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.systemIdentifier.append(65533);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    DoctypeSystemIdentifier_singleQuoted = new TokeniserState("DoctypeSystemIdentifier_singleQuoted", 63)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        char c = paramCharacterReader.consume();
        switch (c)
        {
        default:
          paramTokeniser.doctypePending.systemIdentifier.append(c);
          return;
        case '\'':
          paramTokeniser.transition(AfterDoctypeSystemIdentifier);
          return;
        case '\000':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.systemIdentifier.append(65533);
          return;
        case '>':
          paramTokeniser.error(this);
          paramTokeniser.doctypePending.forceQuirks = true;
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    AfterDoctypeSystemIdentifier = new TokeniserState("AfterDoctypeSystemIdentifier", 64)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          paramTokeniser.error(this);
          paramTokeniser.transition(BogusDoctype);
        case '\t':
        case '\n':
        case '\f':
        case ' ':
          return;
        case '>':
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.eofError(this);
        paramTokeniser.doctypePending.forceQuirks = true;
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    BogusDoctype = new TokeniserState("BogusDoctype", 65)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        switch (paramCharacterReader.consume())
        {
        default:
          return;
        case '>':
          paramTokeniser.emitDoctypePending();
          paramTokeniser.transition(Data);
          return;
        case '￿':
        }
        paramTokeniser.emitDoctypePending();
        paramTokeniser.transition(Data);
      }
    };
    CdataSection = new TokeniserState("CdataSection", 66)
    {
      void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader)
      {
        paramTokeniser.emit(paramCharacterReader.consumeTo("]]>"));
        paramCharacterReader.matchConsume("]]>");
        paramTokeniser.transition(Data);
      }
    };
    TokeniserState[] arrayOfTokeniserState = new TokeniserState[67];
    arrayOfTokeniserState[0] = Data;
    arrayOfTokeniserState[1] = CharacterReferenceInData;
    arrayOfTokeniserState[2] = Rcdata;
    arrayOfTokeniserState[3] = CharacterReferenceInRcdata;
    arrayOfTokeniserState[4] = Rawtext;
    arrayOfTokeniserState[5] = ScriptData;
    arrayOfTokeniserState[6] = PLAINTEXT;
    arrayOfTokeniserState[7] = TagOpen;
    arrayOfTokeniserState[8] = EndTagOpen;
    arrayOfTokeniserState[9] = TagName;
    arrayOfTokeniserState[10] = RcdataLessthanSign;
    arrayOfTokeniserState[11] = RCDATAEndTagOpen;
    arrayOfTokeniserState[12] = RCDATAEndTagName;
    arrayOfTokeniserState[13] = RawtextLessthanSign;
    arrayOfTokeniserState[14] = RawtextEndTagOpen;
    arrayOfTokeniserState[15] = RawtextEndTagName;
    arrayOfTokeniserState[16] = ScriptDataLessthanSign;
    arrayOfTokeniserState[17] = ScriptDataEndTagOpen;
    arrayOfTokeniserState[18] = ScriptDataEndTagName;
    arrayOfTokeniserState[19] = ScriptDataEscapeStart;
    arrayOfTokeniserState[20] = ScriptDataEscapeStartDash;
    arrayOfTokeniserState[21] = ScriptDataEscaped;
    arrayOfTokeniserState[22] = ScriptDataEscapedDash;
    arrayOfTokeniserState[23] = ScriptDataEscapedDashDash;
    arrayOfTokeniserState[24] = ScriptDataEscapedLessthanSign;
    arrayOfTokeniserState[25] = ScriptDataEscapedEndTagOpen;
    arrayOfTokeniserState[26] = ScriptDataEscapedEndTagName;
    arrayOfTokeniserState[27] = ScriptDataDoubleEscapeStart;
    arrayOfTokeniserState[28] = ScriptDataDoubleEscaped;
    arrayOfTokeniserState[29] = ScriptDataDoubleEscapedDash;
    arrayOfTokeniserState[30] = ScriptDataDoubleEscapedDashDash;
    arrayOfTokeniserState[31] = ScriptDataDoubleEscapedLessthanSign;
    arrayOfTokeniserState[32] = ScriptDataDoubleEscapeEnd;
    arrayOfTokeniserState[33] = BeforeAttributeName;
    arrayOfTokeniserState[34] = AttributeName;
    arrayOfTokeniserState[35] = AfterAttributeName;
    arrayOfTokeniserState[36] = BeforeAttributeValue;
    arrayOfTokeniserState[37] = AttributeValue_doubleQuoted;
    arrayOfTokeniserState[38] = AttributeValue_singleQuoted;
    arrayOfTokeniserState[39] = AttributeValue_unquoted;
    arrayOfTokeniserState[40] = AfterAttributeValue_quoted;
    arrayOfTokeniserState[41] = SelfClosingStartTag;
    arrayOfTokeniserState[42] = BogusComment;
    arrayOfTokeniserState[43] = MarkupDeclarationOpen;
    arrayOfTokeniserState[44] = CommentStart;
    arrayOfTokeniserState[45] = CommentStartDash;
    arrayOfTokeniserState[46] = Comment;
    arrayOfTokeniserState[47] = CommentEndDash;
    arrayOfTokeniserState[48] = CommentEnd;
    arrayOfTokeniserState[49] = CommentEndBang;
    arrayOfTokeniserState[50] = Doctype;
    arrayOfTokeniserState[51] = BeforeDoctypeName;
    arrayOfTokeniserState[52] = DoctypeName;
    arrayOfTokeniserState[53] = AfterDoctypeName;
    arrayOfTokeniserState[54] = AfterDoctypePublicKeyword;
    arrayOfTokeniserState[55] = BeforeDoctypePublicIdentifier;
    arrayOfTokeniserState[56] = DoctypePublicIdentifier_doubleQuoted;
    arrayOfTokeniserState[57] = DoctypePublicIdentifier_singleQuoted;
    arrayOfTokeniserState[58] = AfterDoctypePublicIdentifier;
    arrayOfTokeniserState[59] = BetweenDoctypePublicAndSystemIdentifiers;
    arrayOfTokeniserState[60] = AfterDoctypeSystemKeyword;
    arrayOfTokeniserState[61] = BeforeDoctypeSystemIdentifier;
    arrayOfTokeniserState[62] = DoctypeSystemIdentifier_doubleQuoted;
    arrayOfTokeniserState[63] = DoctypeSystemIdentifier_singleQuoted;
    arrayOfTokeniserState[64] = AfterDoctypeSystemIdentifier;
    arrayOfTokeniserState[65] = BogusDoctype;
    arrayOfTokeniserState[66] = CdataSection;
    $VALUES = arrayOfTokeniserState;
    replacementStr = String.valueOf(65533);
  }

  abstract void read(Tokeniser paramTokeniser, CharacterReader paramCharacterReader);
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.jsoup.parser.TokeniserState
 * JD-Core Version:    0.6.0
 */