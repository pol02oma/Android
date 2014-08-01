package org.apache.james.mime4j.stream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.Base64InputStream;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.QuotedPrintableInputStream;
import org.apache.james.mime4j.io.BufferedLineReaderInputStream;
import org.apache.james.mime4j.io.LimitedInputStream;
import org.apache.james.mime4j.io.LineNumberSource;
import org.apache.james.mime4j.io.LineReaderInputStream;
import org.apache.james.mime4j.io.LineReaderInputStreamAdaptor;
import org.apache.james.mime4j.io.MaxHeaderLimitException;
import org.apache.james.mime4j.io.MaxLineLimitException;
import org.apache.james.mime4j.io.MimeBoundaryInputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.MimeUtil;

class MimeEntity
  implements EntityStateMachine
{
  private BodyDescriptor body;
  private final BodyDescriptorBuilder bodyDescBuilder;
  private final MimeConfig config;
  private MimeBoundaryInputStream currentMimePartStream;
  private LineReaderInputStreamAdaptor dataStream;
  private boolean endOfHeader;
  private final EntityState endState;
  private Field field;
  private final FieldBuilder fieldBuilder;
  private int headerCount;
  private final BufferedLineReaderInputStream inbuffer;
  private int lineCount;
  private final LineNumberSource lineSource;
  private final ByteArrayBuffer linebuf;
  private final DecodeMonitor monitor;
  private RecursionMode recursionMode;
  private EntityState state;
  private byte[] tmpbuf;

  MimeEntity(LineNumberSource paramLineNumberSource, InputStream paramInputStream, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    this(paramLineNumberSource, paramInputStream, new MimeConfig(), EntityState.T_START_MESSAGE, EntityState.T_END_MESSAGE, DecodeMonitor.SILENT, new DefaultFieldBuilder(-1), paramBodyDescriptorBuilder);
  }

  MimeEntity(LineNumberSource paramLineNumberSource, InputStream paramInputStream, FieldBuilder paramFieldBuilder, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    this(paramLineNumberSource, paramInputStream, new MimeConfig(), EntityState.T_START_MESSAGE, EntityState.T_END_MESSAGE, DecodeMonitor.SILENT, paramFieldBuilder, paramBodyDescriptorBuilder);
  }

  MimeEntity(LineNumberSource paramLineNumberSource, InputStream paramInputStream, MimeConfig paramMimeConfig, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
  }

  MimeEntity(LineNumberSource paramLineNumberSource, InputStream paramInputStream, MimeConfig paramMimeConfig, EntityState paramEntityState1, EntityState paramEntityState2, DecodeMonitor paramDecodeMonitor, FieldBuilder paramFieldBuilder, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    this.config = paramMimeConfig;
    this.state = paramEntityState1;
    this.endState = paramEntityState2;
    this.monitor = paramDecodeMonitor;
    this.fieldBuilder = paramFieldBuilder;
    this.bodyDescBuilder = paramBodyDescriptorBuilder;
    this.linebuf = new ByteArrayBuffer(64);
    this.lineCount = 0;
    this.endOfHeader = false;
    this.headerCount = 0;
    this.lineSource = paramLineNumberSource;
    this.inbuffer = new BufferedLineReaderInputStream(paramInputStream, 4096, paramMimeConfig.getMaxLineLen());
    this.dataStream = new LineReaderInputStreamAdaptor(this.inbuffer, paramMimeConfig.getMaxLineLen());
  }

  MimeEntity(LineNumberSource paramLineNumberSource, InputStream paramInputStream, MimeConfig paramMimeConfig, EntityState paramEntityState1, EntityState paramEntityState2, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
  }

  private void advanceToBoundary()
    throws IOException
  {
    if (!this.dataStream.eof())
    {
      if (this.tmpbuf == null)
        this.tmpbuf = new byte[2048];
      InputStream localInputStream = getLimitedContentStream();
      while (localInputStream.read(this.tmpbuf) != -1);
    }
  }

  private void clearMimePartStream()
  {
    this.currentMimePartStream = null;
    this.dataStream = new LineReaderInputStreamAdaptor(this.inbuffer, this.config.getMaxLineLen());
  }

  private void createMimePartStream()
    throws MimeException, IOException
  {
    String str = this.body.getBoundary();
    try
    {
      this.currentMimePartStream = new MimeBoundaryInputStream(this.inbuffer, str, this.config.isStrictParsing());
      this.dataStream = new LineReaderInputStreamAdaptor(this.currentMimePartStream, this.config.getMaxLineLen());
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    throw new MimeException(localIllegalArgumentException.getMessage(), localIllegalArgumentException);
  }

  private InputStream decodedStream(InputStream paramInputStream)
  {
    String str = this.body.getTransferEncoding();
    if (MimeUtil.isBase64Encoding(str))
      paramInputStream = new Base64InputStream(paramInputStream, this.monitor);
    do
      return paramInputStream;
    while (!MimeUtil.isQuotedPrintableEncoded(str));
    return new QuotedPrintableInputStream(paramInputStream, this.monitor);
  }

  private LineReaderInputStream getDataStream()
  {
    return this.dataStream;
  }

  private InputStream getLimitedContentStream()
  {
    long l = this.config.getMaxContentLen();
    if (l >= 0L)
      return new LimitedInputStream(this.dataStream, l);
    return this.dataStream;
  }

  private int getLineNumber()
  {
    if (this.lineSource == null)
      return -1;
    return this.lineSource.getLineNumber();
  }

  private EntityStateMachine nextMessage()
  {
    if (this.currentMimePartStream != null);
    for (Object localObject = this.currentMimePartStream; ; localObject = this.inbuffer)
    {
      InputStream localInputStream = decodedStream((InputStream)localObject);
      return nextMimeEntity(EntityState.T_START_MESSAGE, EntityState.T_END_MESSAGE, localInputStream);
    }
  }

  private EntityStateMachine nextMimeEntity()
  {
    return nextMimeEntity(EntityState.T_START_BODYPART, EntityState.T_END_BODYPART, this.currentMimePartStream);
  }

  private EntityStateMachine nextMimeEntity(EntityState paramEntityState1, EntityState paramEntityState2, InputStream paramInputStream)
  {
    if (this.recursionMode == RecursionMode.M_RAW)
      return new RawEntity(paramInputStream);
    MimeEntity localMimeEntity = new MimeEntity(this.lineSource, paramInputStream, this.config, paramEntityState1, paramEntityState2, this.monitor, this.fieldBuilder, this.bodyDescBuilder.newChild());
    localMimeEntity.setRecursionMode(this.recursionMode);
    return localMimeEntity;
  }

  private void readRawField()
    throws IOException, MimeException
  {
    if (this.endOfHeader)
      throw new IllegalStateException();
    LineReaderInputStream localLineReaderInputStream = getDataStream();
    int j;
    do
    {
      do
      {
        try
        {
          if (this.linebuf.length() > 0)
            this.fieldBuilder.append(this.linebuf);
          this.linebuf.clear();
          if (localLineReaderInputStream.readLine(this.linebuf) == -1)
          {
            monitor(Event.HEADERS_PREMATURE_END);
            this.endOfHeader = true;
            return;
          }
          int i = this.linebuf.length();
          if ((i > 0) && (this.linebuf.byteAt(i - 1) == 10))
            i--;
          if ((i > 0) && (this.linebuf.byteAt(i - 1) == 13))
            i--;
          if (i == 0)
          {
            this.endOfHeader = true;
            return;
          }
        }
        catch (MaxLineLimitException localMaxLineLimitException)
        {
          throw new MimeException(localMaxLineLimitException);
        }
        this.lineCount = (1 + this.lineCount);
      }
      while (this.lineCount <= 1);
      j = this.linebuf.byteAt(0);
    }
    while ((j == 32) || (j == 9));
  }

  public static final String stateToString(EntityState paramEntityState)
  {
    switch (1.$SwitchMap$org$apache$james$mime4j$stream$EntityState[paramEntityState.ordinal()])
    {
    default:
      return "Unknown";
    case 11:
      return "End of stream";
    case 1:
      return "Start message";
    case 12:
      return "End message";
    case 13:
      return "Raw entity";
    case 3:
      return "Start header";
    case 4:
      return "Field";
    case 5:
      return "End header";
    case 6:
      return "Start multipart";
    case 10:
      return "End multipart";
    case 7:
      return "Preamble";
    case 8:
      return "Epilogue";
    case 2:
      return "Start bodypart";
    case 14:
      return "End bodypart";
    case 9:
    }
    return "Body";
  }

  public EntityStateMachine advance()
    throws IOException, MimeException
  {
    switch (1.$SwitchMap$org$apache$james$mime4j$stream$EntityState[this.state.ordinal()])
    {
    default:
      if (this.state != this.endState)
        break;
      this.state = EntityState.T_END_OF_STREAM;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
      while (true)
      {
        return null;
        this.state = EntityState.T_START_HEADER;
        continue;
        this.state = EntityState.T_START_HEADER;
        continue;
        this.bodyDescBuilder.reset();
        if (nextField());
        for (EntityState localEntityState = EntityState.T_FIELD; ; localEntityState = EntityState.T_END_HEADER)
        {
          this.state = localEntityState;
          break;
        }
        this.body = this.bodyDescBuilder.build();
        String str = this.body.getMimeType();
        if (this.recursionMode == RecursionMode.M_FLAT)
        {
          this.state = EntityState.T_BODY;
          continue;
        }
        if (MimeUtil.isMultipart(str))
        {
          this.state = EntityState.T_START_MULTIPART;
          clearMimePartStream();
          continue;
        }
        if ((this.recursionMode != RecursionMode.M_NO_RECURSE) && (MimeUtil.isMessage(str)))
        {
          this.state = EntityState.T_BODY;
          return nextMessage();
        }
        this.state = EntityState.T_BODY;
        continue;
        if (this.dataStream.isUsed())
        {
          advanceToBoundary();
          this.state = EntityState.T_END_MULTIPART;
          continue;
        }
        createMimePartStream();
        this.state = EntityState.T_PREAMBLE;
        if (!this.currentMimePartStream.isEmptyStream())
          continue;
        advanceToBoundary();
        if ((this.currentMimePartStream.eof()) && (!this.currentMimePartStream.isLastPart()))
          monitor(Event.MIME_BODY_PREMATURE_END);
        do
        {
          boolean bool = this.currentMimePartStream.isFullyConsumed();
          clearMimePartStream();
          this.state = EntityState.T_EPILOGUE;
          if (!bool)
            break;
          this.state = EntityState.T_END_MULTIPART;
          break;
        }
        while (this.currentMimePartStream.isLastPart());
        clearMimePartStream();
        createMimePartStream();
        return nextMimeEntity();
        this.state = this.endState;
      }
    }
    throw new IllegalStateException("Invalid state: " + stateToString(this.state));
  }

  public BodyDescriptor getBodyDescriptor()
  {
    switch (1.$SwitchMap$org$apache$james$mime4j$stream$EntityState[getState().ordinal()])
    {
    case 10:
    default:
      throw new IllegalStateException("Invalid state :" + stateToString(this.state));
    case 6:
    case 7:
    case 8:
    case 9:
    case 11:
    }
    return this.body;
  }

  public InputStream getContentStream()
  {
    switch (1.$SwitchMap$org$apache$james$mime4j$stream$EntityState[this.state.ordinal()])
    {
    default:
      throw new IllegalStateException("Invalid state: " + stateToString(this.state));
    case 6:
    case 7:
    case 8:
    case 9:
    }
    return getLimitedContentStream();
  }

  public InputStream getDecodedContentStream()
    throws IllegalStateException
  {
    return decodedStream(getContentStream());
  }

  public Field getField()
  {
    switch (1.$SwitchMap$org$apache$james$mime4j$stream$EntityState[getState().ordinal()])
    {
    default:
      throw new IllegalStateException("Invalid state :" + stateToString(this.state));
    case 4:
    }
    return this.field;
  }

  public RecursionMode getRecursionMode()
  {
    return this.recursionMode;
  }

  public EntityState getState()
  {
    return this.state;
  }

  protected String message(Event paramEvent)
  {
    if (paramEvent == null);
    int i;
    for (String str = "Event is unexpectedly null."; ; str = paramEvent.toString())
    {
      i = getLineNumber();
      if (i > 0)
        break;
      return str;
    }
    return "Line " + i + ": " + str;
  }

  protected void monitor(Event paramEvent)
    throws MimeException, IOException
  {
    if (this.monitor.isListening())
    {
      String str = message(paramEvent);
      if (this.monitor.warn(str, "ignoring"))
        throw new MimeParseEventException(paramEvent);
    }
  }

  protected boolean nextField()
    throws MimeException, IOException
  {
    int i = this.config.getMaxHeaderCount();
    if (this.endOfHeader);
    LineReaderInputStream localLineReaderInputStream;
    ByteArrayBuffer localByteArrayBuffer;
    do
    {
      while (true)
      {
        return false;
        if ((i > 0) && (this.headerCount >= i))
          throw new MaxHeaderLimitException("Maximum header limit exceeded");
        this.headerCount = (1 + this.headerCount);
        this.fieldBuilder.reset();
        readRawField();
        try
        {
          RawField localRawField = this.fieldBuilder.build();
          if (localRawField == null)
            break;
          if (localRawField.getDelimiterIdx() != localRawField.getName().length())
            monitor(Event.OBSOLETE_HEADER);
          Object localObject = this.bodyDescBuilder.addField(localRawField);
          if (localObject != null);
          while (true)
          {
            this.field = ((Field)localObject);
            return true;
            localObject = localRawField;
          }
        }
        catch (MimeException localMimeException)
        {
          monitor(Event.INVALID_HEADER);
        }
      }
      if (!this.config.isMalformedHeaderStartsBody())
        break;
      localLineReaderInputStream = getDataStream();
      localByteArrayBuffer = this.fieldBuilder.getRaw();
    }
    while ((localByteArrayBuffer != null) && (localLineReaderInputStream.unread(localByteArrayBuffer)));
    throw new MimeParseEventException(Event.INVALID_HEADER);
  }

  public void setRecursionMode(RecursionMode paramRecursionMode)
  {
    this.recursionMode = paramRecursionMode;
  }

  public void stop()
  {
    this.inbuffer.truncate();
  }

  public String toString()
  {
    return getClass().getName() + " [" + stateToString(this.state) + "][" + this.body.getMimeType() + "][" + this.body.getBoundary() + "]";
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.MimeEntity
 * JD-Core Version:    0.6.0
 */