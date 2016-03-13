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
            "5593516160140905380432892506793403468019"
            + "5691613547283099974297678762995601993";
    private static final String prime2 = 
            "1047858154575742555993994021859261043407"
            + "11134300257018221878155921492470860681";
    
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
        BlockedMessage2 msg = new BlockedMessage2(text1, 30);
        assertEquals(1, msg.countBlocks());
        
        BigInt rawValue = new BigInt(msg.getBlock(0));
        BigInt encValue = pubKey.encrypt(rawValue);
        BigInt decValue = prvKey.decrypt(encValue);
        
        assertTrue(decValue.equals(rawValue));
    }
    
    @Test
    public void test02() throws Exception
    {
        BlockedMessage2 msg = new BlockedMessage2(text2, 30);
        assertEquals(1, msg.countBlocks());
        
        BigInt rawValue = new BigInt(msg.getBlock(0));
        BigInt encValue = pubKey.encrypt(rawValue);
        BigInt decValue = prvKey.decrypt(encValue);
        
        assertTrue(decValue.equals(rawValue));
    }
}
