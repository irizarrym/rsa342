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
    /**
     * Constructs an RSA key from two XML-formatted files
     * 
     * @param publicFile    path to public key file
     * @param privateFile   path to private key file
     * @return              RSA object with keys loaded from public+private files
     */
    public static RSA loadKeys(String publicFile, String privateFile)
    {
        // TODO implement this
        return null;
    }
    
    /**
     * Saves an RSA key to two XML-formatted files
     * 
     * @param key           RSA key object to store in files
     * @param publicFile    path to public key file
     * @param privateFile   path to private key file
     */
    public static void saveKeys(RSA key, String publicFile, String privateFile)
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
    public static void encryptFile(String inFile, String outFile, RSA key)
    {
        // TODO implement this
    }
    
    /**
     * Decrypts a blocked file using the specified RSA key
     * @param inFile    path to input encrypted blocked file to decrypt
     * @param outFile   path to output file in raw blocked format
     * @param key       private key to use for decryption
     */
    public static void decryptFile(String inFile, String outFile, RSA key)
    {
        // TODO implement this
    }
}
