package RSA;

/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

import java.io.*;
import java.nio.file.Files;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 * Unit tests for RSAFileOp class
 */
public class RSAFileOpTest
{
    private final RSAPublicKey pubKey;
    private final RSAPrivateKey prvKey;
    
    private static final String prime1 = "824890730938114883797187";
    private static final String prime2 = "419237048924978891958863";
    
    private static final String 
            tmpDir = System.getProperty("java.io.tmpdir"), 
            pubPath = tmpDir + "rsapublickey.xml",
            prvPath = tmpDir + "rsaprivatekey.xml"
            ;
    
    private static final String testFileContent =
        "Be thou blest, Bertram, and succeed thy father\n" +
        "In manners, as in shape! thy blood and virtue\n" +
        "Contend for empire in thee, and thy goodness\n" +
        "Share with thy birthright! Love all, trust a few,\n" +
        "Do wrong to none: be able for thine enemy\n" +
        "Rather in power than use, and keep thy friend\n" +
        "Under thy own life's key: be cheque'd for silence,\n" +
        "But never tax'd for speech. What heaven more will,\n" +
        "That thee may furnish and my prayers pluck down,\n" +
        "Fall on thy head! Farewell, my lord;\n" +
        "'Tis an unseason'd courtier; good my lord,\n" +
        "Advise him.";
    
    public RSAFileOpTest() throws Exception
    {
        // Generate public and private keys from prime numbers
        BigInt p = new BigInt(prime1);
        BigInt q = new BigInt(prime2);
        
        RSAKeyGen keys = new RSAKeyGen(p, q);
        
        pubKey = keys.publicKey();
        prvKey = keys.privateKey();
    }
    
    @Test
    public void publicKeyFileTest()
    throws ParserConfigurationException, TransformerException, SAXException, IOException
    {
        // Create temporary file
        File pubFile = File.createTempFile("rsatest_publickey", ".xml");
        String pubPath = pubFile.getAbsolutePath();
        
        try
        {
            // Load and save public key
            RSAFileOp.savePublicKey(pubPath, pubKey);
            RSAPublicKey key2 = RSAFileOp.loadPublicKey(pubPath);

            // Compare loaded key to original key
            assertEquals(pubKey.eValue().toString(), key2.eValue().toString());
            assertEquals(pubKey.nValue().toString(), key2.nValue().toString());
        }
        finally
        {
            // Delete temporary file
            pubFile.delete();
        }
    }
    
    @Test
    public void privateKeyFileTest()
    throws ParserConfigurationException, TransformerException, SAXException, IOException
    {
        // Create temporary file
        File prvFile = File.createTempFile("rsatest_privatekey", ".xml");
        String prvPath = prvFile.getAbsolutePath();
        
        try
        {
            // Load and save private key
            RSAFileOp.savePrivateKey(prvPath, prvKey);
            RSAPrivateKey key2 = RSAFileOp.loadPrivateKey(prvPath);

            // Compare loaded key to original key
            assertEquals(prvKey.dValue().toString(), key2.dValue().toString());
            assertEquals(prvKey.nValue().toString(), key2.nValue().toString());
        }
        finally
        {
            // Delete temporary file
            prvFile.delete();
        }
    }
    
    @Test
    public void blockUnblockTest() throws IOException, Exception
    {
        // Create temporary files
        File asciiFile = File.createTempFile("rsatest_ascii", ".txt");
        File blockFile = File.createTempFile("rsatest_block", ".txt");
        File unblockFile = File.createTempFile("rsatest_unblock", ".txt");

        // Extract file paths
        String asciiPath = asciiFile.getAbsolutePath();
        String blockPath = blockFile.getAbsolutePath();
        String unblockPath = unblockFile.getAbsolutePath();

        try
        {
            // Write contents to asciiPath
            PrintWriter writer = new PrintWriter(asciiFile, "UTF-8");
            writer.print(testFileContent);
            writer.flush();

            // Block and unblock ascii file
            RSAFileOp.blockFile(asciiPath, blockPath, 8);
            RSAFileOp.unblockFile(blockPath, unblockPath);

            // Compare unblocked file to original ascii text
            String result = new String(Files.readAllBytes(unblockFile.toPath()));
            assertEquals(testFileContent, result);
        }
        finally
        {
            // Delete temporary files
            asciiFile.delete();
            blockFile.delete();
            unblockFile.delete();
        }
    }
    
    @Test
    public void encryptDecryptTest() throws IOException, Exception
    {
        // Create temporary files
        File asciiFile = File.createTempFile("rsatest_ascii", ".txt");
        File blockFile = File.createTempFile("rsatest_block", ".txt");
        File unblockFile = File.createTempFile("rsatest_unblock", ".txt");
        File encryptFile = File.createTempFile("rsatest_encrypt", ".txt");
        File decryptFile = File.createTempFile("rsatest_decrypt", ".txt");

        // Extract file paths
        String asciiPath = asciiFile.getAbsolutePath();
        String blockPath = blockFile.getAbsolutePath();
        String unblockPath = unblockFile.getAbsolutePath();
        String encryptPath = encryptFile.getAbsolutePath();
        String decryptPath = decryptFile.getAbsolutePath();
        
        try
        {
            // Write contents to asciiPath
            PrintWriter writer = new PrintWriter(asciiFile, "UTF-8");
            writer.print(testFileContent);
            writer.flush();

            // Block -> Encrypt -> Decrypt -> Unblock
            RSAFileOp.blockFile(asciiPath, blockPath, 8);
            RSAFileOp.encryptFile(blockPath, encryptPath, pubKey);
            RSAFileOp.decryptFile(encryptPath, decryptPath, prvKey);
            RSAFileOp.unblockFile(decryptPath, unblockPath);

            // Compare unblocked file to original ascii text
            String result = new String(Files.readAllBytes(unblockFile.toPath()));
            assertEquals(testFileContent, result);
        }
        finally
        {
            // Delete temporary files
            asciiFile.delete();
            blockFile.delete();
            unblockFile.delete();
            encryptFile.delete();
            decryptFile.delete();
        }
    }
}
