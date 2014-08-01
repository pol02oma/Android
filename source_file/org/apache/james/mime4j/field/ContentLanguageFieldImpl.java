package org.apache.james.mime4j.field;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.dom.FieldParser;
import org.apache.james.mime4j.dom.field.ContentLanguageField;
import org.apache.james.mime4j.field.language.parser.ContentLanguageParser;
import org.apache.james.mime4j.stream.Field;

public class ContentLanguageFieldImpl extends AbstractField
  implements ContentLanguageField
{
  public static final FieldParser<ContentLanguageField> PARSER = new FieldParser()
  {
    public ContentLanguageField parse(Field paramField, DecodeMonitor paramDecodeMonitor)
    {
      return new ContentLanguageFieldImpl(paramField, paramDecodeMonitor);
    }
  };
  private List<String> languages;
  private org.apache.james.mime4j.field.language.parser.ParseException parseException;
  private boolean parsed = false;

  ContentLanguageFieldImpl(Field paramField, DecodeMonitor paramDecodeMonitor)
  {
    super(paramField, paramDecodeMonitor);
  }

  private void parse()
  {
    this.parsed = true;
    this.languages = Collections.emptyList();
    String str = getBody();
    ContentLanguageParser localContentLanguageParser;
    if (str != null)
      localContentLanguageParser = new ContentLanguageParser(new StringReader(str));
    try
    {
      this.languages = localContentLanguageParser.parse();
      return;
    }
    catch (org.apache.james.mime4j.field.language.parser.ParseException localParseException)
    {
      this.parseException = localParseException;
    }
  }

  public List<String> getLanguages()
  {
    if (!this.parsed)
      parse();
    return new ArrayList(this.languages);
  }

  public org.apache.james.mime4j.dom.field.ParseException getParseException()
  {
    return this.parseException;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.field.ContentLanguageFieldImpl
 * JD-Core Version:    0.6.0
 */