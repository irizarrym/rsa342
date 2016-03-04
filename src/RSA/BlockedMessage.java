/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Converts strings to blocked format and vice versa
 */
public class BlockedMessage
{
    /**
     * Internally, the message is stored as a string and converted to block
     * format only on request.
     * 
     * This approach makes it easier to append variable-length strings which
     * don't align with the block size.
     */
    private String message;
    
    /**
     * Specifies the size of each block in characters
     */
    private int blockSize;
    
    public BlockedMessage(int blockSize)
    {
        this.blockSize = blockSize;
    }
    
    public BlockedMessage(String ascii, int blockSize)
    {
        this(blockSize);
        message = ascii;
    }
    
    public BlockedMessage(BigInt[] blocks, int blockSize)
    {
        this(blockSize);
        //@@ implement this
    }
    
    
    
    public int count()
    {
        return message.length();
    }
    
    public void append(String ascii)
    {
        message += ascii;
    }
    
    public void append(BigInt block)
    {
        //@@ implement this
    }
    
    BigInt getBlock(int index)
    {
        //@@ implement this
        
        // asciiToBlock(message.substring(begin, end));
        
        return null;
    }
    
    BigInt[] getBlocks()
    {
        BigInt[] blocks = new BigInt[count()];
        
        for(int i = 0; i < blocks.length; ++i)
        {
            blocks[i] = getBlock(i);
        }
        
        return blocks;
    }
    
    String getAscii()
    {
        return message;
    }
    
    
    
    private int encodeChar(char c)
    {
        //@@ implement this
        switch(c)
        {
            case '0':
        }
        
        return 0;
    }
    
    private char decodeChar(int value)
    {
        //@@ implement this
        
        return 0;
    }
    
    private BigInt encodeBlock(String ascii)
    {
        //@@ implement this
        return null;
    }
    
    private String decodeBlock(BigInt block)
    {
        //@@ implement this
        return null;
    }
}
