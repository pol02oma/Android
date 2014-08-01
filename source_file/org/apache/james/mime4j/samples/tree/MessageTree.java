package org.apache.james.mime4j.samples.tree;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.dom.BinaryBody;
import org.apache.james.mime4j.dom.Body;
import org.apache.james.mime4j.dom.Entity;
import org.apache.james.mime4j.dom.Header;
import org.apache.james.mime4j.dom.Message;
import org.apache.james.mime4j.dom.MessageBuilder;
import org.apache.james.mime4j.dom.Multipart;
import org.apache.james.mime4j.dom.TextBody;
import org.apache.james.mime4j.dom.address.AddressList;
import org.apache.james.mime4j.dom.address.Mailbox;
import org.apache.james.mime4j.dom.address.MailboxList;
import org.apache.james.mime4j.dom.field.AddressListField;
import org.apache.james.mime4j.dom.field.ContentTypeField;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.dom.field.UnstructuredField;
import org.apache.james.mime4j.field.address.AddressFormatter;
import org.apache.james.mime4j.message.BodyPart;
import org.apache.james.mime4j.message.DefaultMessageBuilder;
import org.apache.james.mime4j.message.MessageImpl;
import org.apache.james.mime4j.stream.Field;

public class MessageTree extends JPanel
  implements TreeSelectionListener
{
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextArea textView;
  private JTree tree = new JTree(createNode(paramMessage));

  public MessageTree(Message paramMessage)
  {
    super(new GridLayout(1, 0));
    this.tree.getSelectionModel().setSelectionMode(1);
    this.tree.addTreeSelectionListener(this);
    JScrollPane localJScrollPane1 = new JScrollPane(this.tree);
    this.contentPane = new JPanel(new GridLayout(1, 0));
    JScrollPane localJScrollPane2 = new JScrollPane(this.contentPane);
    JSplitPane localJSplitPane = new JSplitPane(1);
    localJSplitPane.setLeftComponent(localJScrollPane1);
    localJSplitPane.setRightComponent(localJScrollPane2);
    Dimension localDimension = new Dimension(100, 50);
    localJScrollPane2.setMinimumSize(localDimension);
    localJScrollPane1.setMinimumSize(localDimension);
    localJSplitPane.setDividerLocation(250);
    localJSplitPane.setPreferredSize(new Dimension(750, 500));
    add(localJSplitPane);
    this.textView = new JTextArea();
    this.textView.setEditable(false);
    this.textView.setLineWrap(true);
    this.contentPane.add(this.textView);
  }

  private static void createAndShowGUI(Message paramMessage)
  {
    JFrame localJFrame = new JFrame("MessageTree");
    localJFrame.setDefaultCloseOperation(3);
    MessageTree localMessageTree = new MessageTree(paramMessage);
    localMessageTree.setOpaque(true);
    localJFrame.setContentPane(localMessageTree);
    localJFrame.pack();
    localJFrame.setVisible(true);
  }

  private DefaultMutableTreeNode createNode(Entity paramEntity)
  {
    String str1 = "Message";
    if ((paramEntity instanceof BodyPart))
      str1 = "Body part";
    DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(new ObjectWrapper(str1, paramEntity));
    localDefaultMutableTreeNode.add(createNode(paramEntity.getHeader()));
    Body localBody = paramEntity.getBody();
    if ((localBody instanceof Multipart))
    {
      localDefaultMutableTreeNode.add(createNode((Multipart)localBody));
      return localDefaultMutableTreeNode;
    }
    if ((localBody instanceof MessageImpl))
    {
      localDefaultMutableTreeNode.add(createNode((MessageImpl)localBody));
      return localDefaultMutableTreeNode;
    }
    String str2 = "Text body";
    if ((localBody instanceof BinaryBody))
      str2 = "Binary body";
    localDefaultMutableTreeNode.add(new DefaultMutableTreeNode(new ObjectWrapper(str2 + " (" + paramEntity.getMimeType() + ")", localBody)));
    return localDefaultMutableTreeNode;
  }

  private DefaultMutableTreeNode createNode(Header paramHeader)
  {
    DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(new ObjectWrapper("Header", paramHeader));
    Iterator localIterator = paramHeader.getFields().iterator();
    while (localIterator.hasNext())
    {
      Field localField = (Field)localIterator.next();
      localDefaultMutableTreeNode.add(new DefaultMutableTreeNode(new ObjectWrapper(localField.getName(), localField)));
    }
    return localDefaultMutableTreeNode;
  }

  private DefaultMutableTreeNode createNode(Multipart paramMultipart)
  {
    DefaultMutableTreeNode localDefaultMutableTreeNode = new DefaultMutableTreeNode(new ObjectWrapper("Multipart", paramMultipart));
    localDefaultMutableTreeNode.add(new DefaultMutableTreeNode(new ObjectWrapper("Preamble", paramMultipart.getPreamble())));
    Iterator localIterator = paramMultipart.getBodyParts().iterator();
    while (localIterator.hasNext())
      localDefaultMutableTreeNode.add(createNode((Entity)localIterator.next()));
    localDefaultMutableTreeNode.add(new DefaultMutableTreeNode(new ObjectWrapper("Epilogue", paramMultipart.getEpilogue())));
    return localDefaultMutableTreeNode;
  }

  public static void main(String[] paramArrayOfString)
  {
    try
    {
      SwingUtilities.invokeLater(new Runnable(new DefaultMessageBuilder().parseMessage(new FileInputStream(paramArrayOfString[0])))
      {
        public void run()
        {
          MessageTree.access$000(this.val$message);
        }
      });
      return;
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      System.err.println("Wrong number of arguments.");
      System.err.println("Usage: org.mime4j.samples.tree.MessageTree path/to/message");
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      System.err.println("The file '" + paramArrayOfString[0] + "' could not be found.");
      return;
    }
    catch (IOException localIOException)
    {
      System.err.println("The file '" + paramArrayOfString[0] + "' could not be read.");
      return;
    }
    catch (MimeException localMimeException)
    {
      System.err.println("The file '" + paramArrayOfString[0] + "' is invalid.");
    }
  }

  public void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
  {
    DefaultMutableTreeNode localDefaultMutableTreeNode = (DefaultMutableTreeNode)this.tree.getLastSelectedPathComponent();
    this.textView.setText("");
    if (localDefaultMutableTreeNode == null);
    Object localObject;
    do
    {
      return;
      localObject = ((ObjectWrapper)localDefaultMutableTreeNode.getUserObject()).getObject();
    }
    while (!localDefaultMutableTreeNode.isLeaf());
    if ((localObject instanceof TextBody))
    {
      TextBody localTextBody = (TextBody)localObject;
      StringBuilder localStringBuilder3 = new StringBuilder();
      try
      {
        Reader localReader = localTextBody.getReader();
        while (true)
        {
          int m = localReader.read();
          if (m == -1)
            break;
          localStringBuilder3.append((char)m);
        }
      }
      catch (IOException localIOException2)
      {
        localIOException2.printStackTrace();
        this.textView.setText(localStringBuilder3.toString());
        return;
      }
    }
    if ((localObject instanceof BinaryBody))
    {
      BinaryBody localBinaryBody = (BinaryBody)localObject;
      int j = 0;
      try
      {
        InputStream localInputStream = localBinaryBody.getInputStream();
        while (true)
        {
          int k = localInputStream.read();
          if (k == -1)
            break;
          j++;
        }
      }
      catch (IOException localIOException1)
      {
        localIOException1.printStackTrace();
        this.textView.setText("Binary body\nMIME type: " + localBinaryBody.getParent().getMimeType() + "\n" + "Size of decoded data: " + j + " bytes");
        return;
      }
    }
    if ((localObject instanceof ContentTypeField))
    {
      ContentTypeField localContentTypeField = (ContentTypeField)localObject;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("MIME type: " + localContentTypeField.getMimeType() + "\n");
      Iterator localIterator = localContentTypeField.getParameters().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localStringBuilder2.append((String)localEntry.getKey() + " = " + (String)localEntry.getValue() + "\n");
      }
      this.textView.setText(localStringBuilder2.toString());
      return;
    }
    if ((localObject instanceof AddressListField))
    {
      MailboxList localMailboxList = ((AddressListField)localObject).getAddressList().flatten();
      StringBuilder localStringBuilder1 = new StringBuilder();
      for (int i = 0; i < localMailboxList.size(); i++)
      {
        Mailbox localMailbox = localMailboxList.get(i);
        localStringBuilder1.append(AddressFormatter.DEFAULT.format(localMailbox, false) + "\n");
      }
      this.textView.setText(localStringBuilder1.toString());
      return;
    }
    if ((localObject instanceof DateTimeField))
    {
      Date localDate = ((DateTimeField)localObject).getDate();
      this.textView.setText(localDate.toString());
      return;
    }
    if ((localObject instanceof UnstructuredField))
    {
      this.textView.setText(((UnstructuredField)localObject).getValue());
      return;
    }
    if ((localObject instanceof Field))
    {
      this.textView.setText(((Field)localObject).getBody());
      return;
    }
    this.textView.setText(localObject.toString());
  }

  public static class ObjectWrapper
  {
    private Object object = null;
    private String text = "";

    public ObjectWrapper(String paramString, Object paramObject)
    {
      this.text = paramString;
      this.object = paramObject;
    }

    public Object getObject()
    {
      return this.object;
    }

    public String toString()
    {
      return this.text;
    }
  }
}

/* Location:           C:\Users\Admin\Desktop\Development Tools\ReEngineering\JavaApp\decompilers\classes_dex2jar.jar
 * Qualified Name:     org.apache.james.mime4j.samples.tree.MessageTree
 * JD-Core Version:    0.6.0
 */