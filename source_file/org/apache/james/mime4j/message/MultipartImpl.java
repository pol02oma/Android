package org.apache.james.mime4j.message;

import org.apache.james.mime4j.util.ByteSequence;
import org.apache.james.mime4j.util.ContentUtil;

public class MultipartImpl extends AbstractMultipart
{
  private ByteSequence epilogue = null;
  private transient boolean epilogueComputed = false;
  private transient String epilogueStrCache = null;
  private ByteSequence preamble = null;
  private transient boolean preambleComputed = false;
  private transient String preambleStrCache = null;

  public MultipartImpl(String paramString)
  {
    super(paramString);
  }

  public String getEpilogue()
  {
    if (!this.epilogueComputed)
      if (this.epilogue == null)
        break label37;
    label37: for (String str = ContentUtil.decode(this.epilogue); ; str = null)
    {
      this.epilogueStrCache = str;
      this.epilogueComputed = true;
      return this.epilogueStrCache;
    }
  }

  public ByteSequence getEpilogueRaw()
  {
    return this.epilogue;
  }

  public String getPreamble()
  {
    if (!this.preambleComputed)
      if (this.preamble == null)
        break label37;
    label37: for (String str = ContentUtil.decode(this.preamble); ; str = null)
    {
      this.preambleStrCache = str;
      this.preambleComputed = true;
      return this.preambleStrCache;
    }
  }

  public ByteSequence getPreambleRaw()
  {
    return this.preamble;
  }

  public void setEpilogue(String paramString)
  {
    if (paramString != null);
    for (ByteSequence localByteSequence = ContentUtil.encode(paramString); ; localByteSequence = null)
    {
      this.epilogue = localByteSequence;
      this.epilogueStrCache = paramString;
      this.epilogueComputed = true;
      return;
    }
  }

  public void setEpilogueRaw(ByteSequence paramByteSequence)
  {
    this.epilogue = paramByteSequence;
    this.epilogueStrCache = null;
    this.epilogueComputed = false;
  }

  public void setPreamble(String paramString)
  {
    if (paramString != null);
    for (ByteSequence localByteSequence = ContentUtil.encode(paramString); ; localByteSequence = null)
    {
      this.preamble = localByteSequence;
      this.preambleStrCache = paramString;
      this.preambleComputed = true;
      return;
    }
  }

  public void setPreambleRaw(ByteSequence paramByteSequence)
  {
    this.preamble = paramByteSequence;
    this.preambleStrCache = null;
    this.preambleComputed = false;
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.message.MultipartImpl
 * JD-Core Version:    0.6.0
 */