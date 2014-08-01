package org.apache.james.mime4j.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.io.LineNumberInputStream;
import org.apache.james.mime4j.util.CharsetUtil;

public class MimeTokenStream
{
  private final BodyDescriptorBuilder bodyDescBuilder;
  private final MimeConfig config;
  private EntityStateMachine currentStateMachine;
  private final LinkedList<EntityStateMachine> entities = new LinkedList();
  private final FieldBuilder fieldBuilder;
  private final DecodeMonitor monitor;
  private RecursionMode recursionMode = RecursionMode.M_RECURSE;
  private MimeEntity rootentity;
  private EntityState state = EntityState.T_END_OF_STREAM;

  public MimeTokenStream()
  {
    this(null);
  }

  public MimeTokenStream(MimeConfig paramMimeConfig)
  {
    this(paramMimeConfig, null, null, null);
  }

  public MimeTokenStream(MimeConfig paramMimeConfig, DecodeMonitor paramDecodeMonitor, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    this(paramMimeConfig, paramDecodeMonitor, null, paramBodyDescriptorBuilder);
  }

  public MimeTokenStream(MimeConfig paramMimeConfig, DecodeMonitor paramDecodeMonitor, FieldBuilder paramFieldBuilder, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    if (paramMimeConfig != null)
    {
      this.config = paramMimeConfig;
      if (paramFieldBuilder == null)
        break label79;
      label42: this.fieldBuilder = paramFieldBuilder;
      if (paramDecodeMonitor == null)
        break label97;
      label51: this.monitor = paramDecodeMonitor;
      if (paramBodyDescriptorBuilder == null)
        break label121;
    }
    while (true)
    {
      this.bodyDescBuilder = paramBodyDescriptorBuilder;
      return;
      paramMimeConfig = new MimeConfig();
      break;
      label79: paramFieldBuilder = new DefaultFieldBuilder(this.config.getMaxHeaderLen());
      break label42;
      label97: if (this.config.isStrictParsing())
      {
        paramDecodeMonitor = DecodeMonitor.STRICT;
        break label51;
      }
      paramDecodeMonitor = DecodeMonitor.SILENT;
      break label51;
      label121: paramBodyDescriptorBuilder = new FallbackBodyDescriptorBuilder();
    }
  }

  public MimeTokenStream(MimeConfig paramMimeConfig, BodyDescriptorBuilder paramBodyDescriptorBuilder)
  {
    this(paramMimeConfig, null, null, paramBodyDescriptorBuilder);
  }

  private void doParse(InputStream paramInputStream, EntityState paramEntityState)
  {
    boolean bool = this.config.isCountLineNumbers();
    Object localObject = null;
    if (bool)
    {
      LineNumberInputStream localLineNumberInputStream = new LineNumberInputStream(paramInputStream);
      localObject = localLineNumberInputStream;
      paramInputStream = localLineNumberInputStream;
    }
    MimeConfig localMimeConfig = this.config;
    EntityState localEntityState = EntityState.T_END_MESSAGE;
    DecodeMonitor localDecodeMonitor = this.monitor;
    FieldBuilder localFieldBuilder = this.fieldBuilder;
    BodyDescriptorBuilder localBodyDescriptorBuilder = this.bodyDescBuilder;
    this.rootentity = new MimeEntity(localObject, paramInputStream, localMimeConfig, paramEntityState, localEntityState, localDecodeMonitor, localFieldBuilder, localBodyDescriptorBuilder);
    this.rootentity.setRecursionMode(this.recursionMode);
    this.currentStateMachine = this.rootentity;
    this.entities.clear();
    this.entities.add(this.currentStateMachine);
    this.state = this.currentStateMachine.getState();
  }

  public static final String stateToString(EntityState paramEntityState)
  {
    return MimeEntity.stateToString(paramEntityState);
  }

  public BodyDescriptor getBodyDescriptor()
  {
    return this.currentStateMachine.getBodyDescriptor();
  }

  public MimeConfig getConfig()
  {
    return this.config;
  }

  public InputStream getDecodedInputStream()
  {
    return this.currentStateMachine.getDecodedContentStream();
  }

  public Field getField()
  {
    return this.currentStateMachine.getField();
  }

  public InputStream getInputStream()
  {
    return this.currentStateMachine.getContentStream();
  }

  public Reader getReader()
  {
    String str = getBodyDescriptor().getCharset();
    if ((str == null) || ("".equals(str)));
    for (Charset localCharset = CharsetUtil.US_ASCII; ; localCharset = Charset.forName(str))
      return new InputStreamReader(getDecodedInputStream(), localCharset);
  }

  public RecursionMode getRecursionMode()
  {
    return this.recursionMode;
  }

  public EntityState getState()
  {
    return this.state;
  }

  public boolean isRaw()
  {
    return this.recursionMode == RecursionMode.M_RAW;
  }

  public EntityState next()
    throws IOException, MimeException
  {
    if ((this.state == EntityState.T_END_OF_STREAM) || (this.currentStateMachine == null))
    {
      throw new IllegalStateException("No more tokens are available.");
      this.entities.removeLast();
      if (!this.entities.isEmpty())
        break label113;
      this.currentStateMachine = null;
    }
    while (true)
    {
      if (this.currentStateMachine == null)
        break label143;
      EntityStateMachine localEntityStateMachine = this.currentStateMachine.advance();
      if (localEntityStateMachine != null)
      {
        this.entities.add(localEntityStateMachine);
        this.currentStateMachine = localEntityStateMachine;
      }
      this.state = this.currentStateMachine.getState();
      if (this.state == EntityState.T_END_OF_STREAM)
        break;
      return this.state;
      label113: this.currentStateMachine = ((EntityStateMachine)this.entities.getLast());
      this.currentStateMachine.setRecursionMode(this.recursionMode);
    }
    label143: this.state = EntityState.T_END_OF_STREAM;
    return this.state;
  }

  public void parse(InputStream paramInputStream)
  {
    doParse(paramInputStream, EntityState.T_START_MESSAGE);
  }

  // ERROR //
  public Field parseHeadless(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +13 -> 14
    //   4: new 229	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 231
    //   10: invokespecial 232	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: new 234	org/apache/james/mime4j/stream/RawField
    //   17: dup
    //   18: ldc 236
    //   20: aload_2
    //   21: invokespecial 239	org/apache/james/mime4j/stream/RawField:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   24: astore_3
    //   25: aload_0
    //   26: getfield 61	org/apache/james/mime4j/stream/MimeTokenStream:bodyDescBuilder	Lorg/apache/james/mime4j/stream/BodyDescriptorBuilder;
    //   29: aload_3
    //   30: invokeinterface 245 2 0
    //   35: astore 5
    //   37: aload 5
    //   39: astore 6
    //   41: aload 6
    //   43: ifnonnull +6 -> 49
    //   46: aload_3
    //   47: astore 6
    //   49: aload_0
    //   50: aload_1
    //   51: getstatic 248	org/apache/james/mime4j/stream/EntityState:T_END_HEADER	Lorg/apache/james/mime4j/stream/EntityState;
    //   54: invokespecial 225	org/apache/james/mime4j/stream/MimeTokenStream:doParse	(Ljava/io/InputStream;Lorg/apache/james/mime4j/stream/EntityState;)V
    //   57: aload_0
    //   58: invokevirtual 250	org/apache/james/mime4j/stream/MimeTokenStream:next	()Lorg/apache/james/mime4j/stream/EntityState;
    //   61: pop
    //   62: aload 6
    //   64: areturn
    //   65: astore 4
    //   67: new 229	java/lang/IllegalArgumentException
    //   70: dup
    //   71: aload 4
    //   73: invokevirtual 253	org/apache/james/mime4j/MimeException:getMessage	()Ljava/lang/String;
    //   76: invokespecial 232	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   79: athrow
    //   80: astore 8
    //   82: new 199	java/lang/IllegalStateException
    //   85: dup
    //   86: aload 8
    //   88: invokespecial 256	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   91: athrow
    //   92: astore 7
    //   94: new 199	java/lang/IllegalStateException
    //   97: dup
    //   98: aload 7
    //   100: invokespecial 256	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   103: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	37	65	org/apache/james/mime4j/MimeException
    //   57	62	80	java/io/IOException
    //   57	62	92	org/apache/james/mime4j/MimeException
  }

  public void setRecursionMode(RecursionMode paramRecursionMode)
  {
    this.recursionMode = paramRecursionMode;
    if (this.currentStateMachine != null)
      this.currentStateMachine.setRecursionMode(paramRecursionMode);
  }

  public void stop()
  {
    this.rootentity.stop();
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.stream.MimeTokenStream
 * JD-Core Version:    0.6.0
 */