/* Do not edit this line. ASCII_UCodeESC_CharStream.java Version 0.6.-8 */
package COM.sun.labs.javascript;

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain only ASCII characters (with java-like unicode escape processing).
 */

public final class ASCII_UCodeESC_CharStream
{
  static final int hexval(char c) throws java.io.IOException {
    switch(c)
    {
       case '0' :
          return 0;
       case '1' :
          return 1;
       case '2' :
          return 2;
       case '3' :
          return 3;
       case '4' :
          return 4;
       case '5' :
          return 5;
       case '6' :
          return 6;
       case '7' :
          return 7;
       case '8' :
          return 8;
       case '9' :
          return 9;

       case 'a' :
       case 'A' :
          return 10;
       case 'b' :
       case 'B' :
          return 11;
       case 'c' :
       case 'C' :
          return 12;
       case 'd' :
       case 'D' :
          return 13;
       case 'e' :
       case 'E' :
          return 14;
       case 'f' :
       case 'F' :
          return 15;
    }

    throw new java.io.IOException(); // Should never come here
  }

  public int bufpos = -1;
  int bufsize;
  int available;
  int tokenBegin;
  private int bufline[];
  private int bufcolumn[];

  private int column = 0;
  private int line = 1;

  private java.io.InputStream inputStream;

  private boolean prevCharIsCR = false;
  private boolean prevCharIsLF = false;

  private byte[] nextCharBuf;
  private char[] buffer;
  private int maxNextCharInd = 0;
  private int nextCharInd = -1;

  private final void ExpandBuff(boolean wrapAround)
  {
     char[] newbuffer = new char[bufsize + 2048];
     int newbufline[] = new int[bufsize + 2048];
     int newbufcolumn[] = new int[bufsize + 2048];

     try
     {
        if (wrapAround)
        {
           System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
           System.arraycopy(buffer, 0, newbuffer,
                                             bufsize - tokenBegin, bufpos);
           buffer = newbuffer;

           System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
           System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
           bufline = newbufline;

           System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
           System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
           bufcolumn = newbufcolumn;

           bufpos += (bufsize - tokenBegin);
        }
        else
        {
           System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
           buffer = newbuffer;

           System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
           bufline = newbufline;

           System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
           bufcolumn = newbufcolumn;

           bufpos -= tokenBegin;
        }
     }
     catch (Throwable t)
     {
        System.out.println("Error : " + t.getClass().getName());
        throw new Error();
     }

     available = (bufsize += 2048);
     tokenBegin = 0;
  }

  private final void FillBuff() throws java.io.IOException
  {
     int i;
     if (maxNextCharInd == 4096)
        maxNextCharInd = nextCharInd = 0;

     if ((i = inputStream.read(nextCharBuf, maxNextCharInd,
                                            4096 - maxNextCharInd)) == -1)
     {
        if (bufpos != 0)
        {
           --bufpos;
           backup(0);
        }
        else
        {
           bufline[bufpos] = line;
           bufcolumn[bufpos] = column;
        }
        throw new java.io.IOException();
     }
     else
        maxNextCharInd += i;
  }

  private final byte ReadByte() throws java.io.IOException
  {
     if (++nextCharInd >= maxNextCharInd)
        FillBuff();

     return nextCharBuf[nextCharInd];
  }

  public final char BeginToken() throws java.io.IOException
  {     
     if (inBuf > 0)
     {
        --inBuf;
        return buffer[tokenBegin = (bufpos == bufsize - 1) ? (bufpos = 0)
                                                           : ++bufpos];
     }

     tokenBegin = 0;
     bufpos = -1;

     return readChar();
  }     

  private final void AdjustBuffSize()
  {
     if (available == bufsize)
     {
        if (tokenBegin > 2048)
        {
           bufpos = 0;
           available = tokenBegin;
        }
        else
           ExpandBuff(false);
     }
     else if (available >= tokenBegin)
        available = bufsize;
     else if ((tokenBegin - available) < 2048)
        ExpandBuff(true);
     else
        available = tokenBegin;
  }

  private final void UpdateLineColumn(char c)
  {
     column++;

     if (prevCharIsLF)
     {
        prevCharIsLF = false;
        line += (column = 1);
     }
     else if (prevCharIsCR)
     {
        prevCharIsCR = false;
        if (c == '\n')
        {
           prevCharIsLF = true;
        }
        else
           line += (column = 1);
     }

     switch (c)
     {
        case '\r' :
           prevCharIsCR = true;
           break;
        case '\n' :
           prevCharIsLF = true;
           break;
        case '\t' :
           column += (9 - (column & 07));
           break;
        default :
           break;
     }

     bufline[bufpos] = line;
     bufcolumn[bufpos] = column;
  }

  private int inBuf = 0;
  public final char readChar() throws java.io.IOException
  {
     if (inBuf > 0)
     {
        --inBuf;
        return buffer[(bufpos == bufsize - 1) ? (bufpos = 0) : ++bufpos];
     }

     char c;

     if (++bufpos == available)
        AdjustBuffSize();

     if (((buffer[bufpos] = c = (char)((char)0xff & ReadByte())) == '\\'))
     {
        UpdateLineColumn(c);

        int backSlashCnt = 1;

        for (;;) // Read all the backslashes
        {
           if (++bufpos == available)
              AdjustBuffSize();

           try
           {
              if ((buffer[bufpos] = c = (char)((char)0xff & ReadByte())) != '\\')
              {
                 UpdateLineColumn(c);
                 // found a non-backslash char.
                 if ((c == 'u') && ((backSlashCnt & 1) == 1))
                 {
                    if (--bufpos < 0)
                       bufpos = bufsize - 1;

                    break;
                 }

                 backup(backSlashCnt);
                 return '\\';
              }
           }
           catch(java.io.IOException e)
           {
              if (backSlashCnt > 1)
                 backup(backSlashCnt);

              return '\\';
           }

           UpdateLineColumn(c);
           backSlashCnt++;
        }

        // Here, we have seen an odd number of backslash's followed by a 'u'
        ++column;
        try
        {
           while ((c = (char)((char)0xff & ReadByte())) == 'u')
              ++column;

           buffer[bufpos] = c = (char)(hexval(c) << 12 |
                                       hexval((char)((char)0xff & ReadByte())) << 8 |
                                       hexval((char)((char)0xff & ReadByte())) << 4 |
                                       hexval((char)((char)0xff & ReadByte())));

           bufline[bufpos] = line;
           bufcolumn[bufpos] = (column += 4);
        }
        catch(java.io.IOException e)
        {
           System.err.println("Invalid escape character at line " + line +
                                         " column " + column + ".");
           throw new Error(); // Should never come here
        }

        if (backSlashCnt == 1)
           return c;
        else
        {
           backup(backSlashCnt - 1);
           return '\\';
        }
     }
     else
     {
        UpdateLineColumn(c);
        return (c);
     }
  }

  /**
   * @deprecated 
   * @see #getEndColumn
   */

  public final int getColumn() {
      return bufcolumn[bufpos];
  }

  /**
   * @deprecated 
   * @see #getEndLine
   */

  public final int getLine() {
      return bufline[bufpos];
  }

  public final int getEndColumn() {
      return bufcolumn[bufpos];
  }

  public final int getEndLine() {
      return bufline[bufpos];
  }

  public final int getBeginColumn() {
      return bufcolumn[tokenBegin];
  }

  public final int getBeginLine() {
      return bufline[tokenBegin];
  }

  public final void backup(int amount) {

    inBuf += amount;
    if ((bufpos -= amount) < 0)
       bufpos += bufsize;
  }

  public ASCII_UCodeESC_CharStream(java.io.InputStream dstream,
                 int startline, int startcolumn, int buffersize)
  {
    inputStream = dstream;
    line = startline;
    column = startcolumn - 1;

    available = bufsize = buffersize;
    buffer = new char[buffersize];
    bufline = new int[buffersize];
    bufcolumn = new int[buffersize];
    nextCharBuf = new byte[4096];
  }


  public ASCII_UCodeESC_CharStream(java.io.InputStream dstream,
                                        int startline, int startcolumn)
  {
    inputStream = dstream;
    line = startline;
    column = startcolumn - 1;

    available = bufsize = 4096;
    buffer = new char[4096];
    nextCharBuf = new byte[4096];
    bufline = new int[4096];
    bufcolumn = new int[4096];
  }

  public final String GetImage()
  {
     if (bufpos >= tokenBegin)
        return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
     else
        return new String(buffer, tokenBegin, bufsize - tokenBegin) +
                              new String(buffer, 0, bufpos + 1);
  }

  public final char[] GetSuffix(int len)
  {
     char[] ret = new char[len];

     if ((bufpos + 1) >= len)
        System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
     else
     {
        System.arraycopy(buffer, bufsize - (len - bufpos - 1), ret, 0,
                                                          len - bufpos - 1);
        System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
     }

     return ret;
  }

  public void Done()
  {
     nextCharBuf = null;
     buffer = null;
     bufline = null;
     bufcolumn = null;
  }

}
