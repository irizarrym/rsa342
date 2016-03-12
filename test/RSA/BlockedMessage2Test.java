/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Unit tests for class BlockedMessage2
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlockedMessage2Test
{
    private static final String lowercase  = "abcdefghijklmnopqrstuvwxyz";
    private static final String uppercase  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerBlock =
        "9594939291908988878685848382818079787776757473727170";
    private static final String upperBlock =
        "6362616059585756555453525150494847464544434241403938";
    
    @Test
    public void constructorTest() throws Exception
    {
        new BlockedMessage2(lowercase, 3);
        new BlockedMessage2(uppercase, 7);
        
        String[] blocks = new String[]{ lowerBlock, upperBlock };
        new BlockedMessage2(blocks, 26);
    }
    
    @Test(expected=Exception.class)
    public void constructorTestThrows() throws Exception
    {
        // Test constructing string with invalid characters
        String text = "Invalid characters: ° ©";
        new BlockedMessage2(text, 5);
    }
    
    @Test(expected=Exception.class)
    public void constructorTestThrows2() throws Exception
    {
        // Test block.length() must match block size
        new BlockedMessage2(new String[]{ lowerBlock }, 26 - 1);
    }
    
    @Test
    public void appendStringTest() throws Exception
    {
        BlockedMessage2 msg = new BlockedMessage2(26);
        
        msg.appendString(lowercase);
        msg.appendString(uppercase);
        
        assertEquals(msg.length(), 52);
        assertEquals(msg.toString(), lowercase + uppercase);
    }
    
    @Test
    public void appendBlockTest() throws Exception
    {
        BlockedMessage2 msg = new BlockedMessage2(26);
        
        msg.appendBlock(lowerBlock);
        msg.appendBlock(upperBlock);
        
        assertEquals(msg.length(), 52);
        assertEquals(msg.toString(), lowercase + uppercase);
    }
    
    @Test(expected=Exception.class)
    public void appendBlockTestThrows() throws Exception
    {
        final String notABlock = "Hello!";
        
        BlockedMessage2 msg = new BlockedMessage2(3);
        msg.appendBlock(notABlock);
    }
    
    @Test(expected=Exception.class)
    public void appendBlockTestThrows2() throws Exception
    {
        // Block does not fit block size
        final String block = "42414039";
        
        BlockedMessage2 msg = new BlockedMessage2(3);
        msg.appendBlock(block);
    }
    
    @Test
    public void lengthTest() throws Exception
    {
        BlockedMessage2 msg = new BlockedMessage2(3);
        assertEquals(0, msg.length());
        
        msg.appendString("abc");
        assertEquals(3, msg.length());
        
        msg.appendBlock("424140");
        assertEquals(6, msg.length());
    }
    
    @Test
    public void countBlocksTest() throws Exception
    {
        BlockedMessage2 msg = new BlockedMessage2(3);
        assertEquals(0, msg.countBlocks());
        
        msg.appendString("ab");
        assertEquals(1, msg.countBlocks());
        
        msg.appendString("c");
        assertEquals(1, msg.countBlocks());
        
        msg.appendString("d");
        assertEquals(2, msg.countBlocks());
    }
    
    @Test
    public void getBlockTest() throws Exception
    {
        BlockedMessage2 msg = new BlockedMessage2(26);
        msg.appendString(lowercase);
        msg.appendString(uppercase);
        
        assertEquals(2, msg.countBlocks());
        assertEquals(lowerBlock, msg.getBlock(0));
        assertEquals(upperBlock, msg.getBlock(1));
        
        msg.appendString("?");
        assertEquals(3, msg.countBlocks());
        assertEquals(
            "0000000000000000000000000000000000000000000000000036",
            msg.getBlock(2));
    }
    
    @Test
    public void getBlocksTest() throws Exception
    {
        BlockedMessage2 msg=  new BlockedMessage2(26);
        msg.appendBlock(lowerBlock);
        msg.appendBlock(upperBlock);
        
        String[] blocks = msg.getBlocks();
        assertEquals(2, blocks.length);
        assertEquals(lowerBlock, blocks[0]);
        assertEquals(upperBlock, blocks[1]);
    }
}
