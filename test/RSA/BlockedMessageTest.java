/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for class BlockedMessage
 * 
 * @author Michael Irizarry
 */
public class BlockedMessageTest
{
    @Test
    public void countAsciiTest()
    {
        final String text = "abcdef";
        BlockedMessage msg = new BlockedMessage(text, 2);
        assertEquals(6, msg.countAscii());
        msg.append("g");
        assertEquals(7, msg.countAscii());
    }
    
    @Test
    public void countBlocksTest()
    {
        final String text = "abcdef";
        BlockedMessage msg = new BlockedMessage(text, 2);
        assertEquals(3, msg.countBlocks());
        msg.append("g");
        assertEquals(4, msg.countBlocks());
    }
    
    @Test
    public void appendTest() throws Exception
    {
        BlockedMessage msg = new BlockedMessage(3);
        BigInt block1 = new BigInt(403938);
        BigInt block2 = new BigInt(434241);
        BigInt block3 = new BigInt(44);
        
        msg.append(block1);
        msg.append(block2);
        msg.append(block3);
        msg.append("HIJ");
        
        assertEquals("ABCDEFGHIJ", msg.getAscii());
    }
    
    @Test
    public void getBlockTest() throws Exception
    {
        final String text = "ABCDEFG";
        BigInt block1 = new BigInt(403938);
        BigInt block2 = new BigInt(434241);
        BigInt block3 = new BigInt(44);
        
        BlockedMessage msg = new BlockedMessage(text, 3);
        assertEquals(3, msg.countBlocks());
        
        assertTrue(block1.equals(msg.getBlock(0)));
        assertTrue(block2.equals(msg.getBlock(1)));
        assertTrue(block3.equals(msg.getBlock(2)));
    }
    
    @Test
    public void getBlocksTest() throws Exception
    {
        final String text = "ABCDEFG";
        BigInt block1 = new BigInt(403938);
        BigInt block2 = new BigInt(434241);
        BigInt block3 = new BigInt(44);
        
        BlockedMessage msg = new BlockedMessage(text, 3);
        assertEquals(3, msg.countBlocks());
        
        BigInt[] blocks = msg.getBlocks();
        assertEquals(3, blocks.length);
        
        assertTrue(block1.equals(blocks[0]));
        assertTrue(block2.equals(blocks[1]));
        assertTrue(block3.equals(blocks[2]));
    }
    
    @Test
    public void getAsciiTest()
    {
        final String text = "abcdefg";
        BlockedMessage msg = new BlockedMessage(text, 2);
        assertEquals(text, msg.getAscii());
        msg.append("hij");
        assertEquals("abcdefghij", msg.getAscii());
    } 
}
