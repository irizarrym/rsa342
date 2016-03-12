/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Provides facilities for encrypting and decrypting files using RSA encryption
 */
public final class RSAFileOp
{
    public static RSAPublicKey loadPublicKey(String filePath)
    {
        // TODO implement this
        return null;
    }
    
    public static RSAPrivateKey loadPrivateKey(String filePath)
    {
        // TODO implement this
        return null;
    }
    
    public static void savePublicKey(String filePath, RSAPublicKey key)
    {
        // TODO implement this
    }
    
    public static void savePrivateKey(String filePath, RSAPrivateKey key)
    {
        // TODO implement this
    }
    
    /**
     * Encodes a file from ASCII to blocked format
     * @param inFile    Path to input ASCII text file to encode in blocked format
     * @param outFile   Path to output file containing file encoded in blocked format
     * @param blockSize Size of each block written per line in outFile
     */
    public static void blockFile(String inFile, String outFile, int blockSize)
    {
        // TODO implement this
        BlockedMessage msg = new BlockedMessage(blockSize);
    }
    
    /**
     * Decodes a file from blocked to ASCII format
     * (Block size is inferred from file per line)
     * @param inFile    path to input blocked file
     * @param outFile   path to output ASCII file
     */
    public static void unblockFile(String inFile, String outFile)
    {
        // TODO implement this
    }
    
    /**
     * Encrypts a blocked file using the specified RSA key
     * @param inFile    path to input blocked file to encrypt
     * @param outFile   path to output file in encrypted blocked format
     * @param key       public key to use for encryption
     */
    public static void encryptFile(String inFile, String outFile, RSAPublicKey key)
    {
        // TODO implement this
    }
    
    /**
     * Decrypts a blocked file using the specified RSA key
     * @param inFile    path to input encrypted blocked file to decrypt
     * @param outFile   path to output file in raw blocked format
     * @param key       private key to use for decryption
     */
    public static void decryptFile(String inFile, String outFile, RSAPrivateKey key)
    {
        // TODO implement this
    }
}
