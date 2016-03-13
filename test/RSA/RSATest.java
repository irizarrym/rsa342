package RSA;

/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for RSA algorithm
 */
public class RSATest
{
    private final RSAPublicKey pubKey;
    private final RSAPrivateKey prvKey;
    
    private static final String prime1 =
            "242091119628427719423661998577941558593";
    private static final String prime2 =
            "321535397227569768931565363004715950029";
    
    private static final String text1 =
        "abcdefghijklmnopqrstuvwxyz";
    
    private static final String text2 =
        "ABCDEFGHIJKLNOPQRSTUVWXYZ";
    
    public RSATest() throws Exception
    {
        BigInt p = new BigInt(prime1);
        BigInt q = new BigInt(prime2);
        
        RSAKeyGen keys = new RSAKeyGen(p, q);
        pubKey = keys.publicKey();
        prvKey = keys.privateKey();
    }

    @Test
    public void test01() throws Exception
    {
        BlockedMessage2 enc = new BlockedMessage2(text1, 30);
        assertEquals(1, enc.countBlocks());
        
        BigInt rawValue = new BigInt(enc.getBlock(0));
        BigInt encValue = pubKey.encrypt(rawValue);
        BigInt decValue = prvKey.decrypt(encValue);
        
        assertTrue(decValue.equals(rawValue));
        
        BlockedMessage2 decBlock = new BlockedMessage2(30);
        decBlock.appendBlock(decValue.toString(60));
        assertEquals(text1, decBlock.toString());
    }
    
    @Test
    public void test02() throws Exception
    {
        BlockedMessage2 enc = new BlockedMessage2(text2, 30);
        assertEquals(1, enc.countBlocks());
        
        BigInt rawValue = new BigInt(enc.getBlock(0));
        BigInt encValue = pubKey.encrypt(rawValue);
        BigInt decValue = prvKey.decrypt(encValue);
        
        assertTrue(decValue.equals(rawValue));
        
        BlockedMessage2 decBlock = new BlockedMessage2(30);
        decBlock.appendBlock(decValue.toString(60));
        assertEquals(text2, decBlock.toString());
    }
}
