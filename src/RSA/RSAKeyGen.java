/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Generates RSA public and private keys from two distinct prime numbers
 */
public class RSAKeyGen
{
    private final RSAPublicKey  pubKey;
    private final RSAPrivateKey prvKey;
    
    /**
     * Generate keys from two given prime numbers
     */
    public RSAKeyGen(BigInt prime1, BigInt prime2)
    {
        // TODO implement this
        pubKey = null;
        prvKey = null;
    }
    
    /**
     * Take keys from existing publicKey and privateKey objects
     * 
     * @param publicKey
     * @param privateKey 
     */
    public RSAKeyGen(RSAPublicKey publicKey, RSAPrivateKey privateKey)
    {
        pubKey = publicKey;
        prvKey = privateKey;
    }
    
    public RSAPublicKey publicKey()
    {
        return pubKey;
    }
    
    public RSAPrivateKey privateKey()
    {
        return prvKey;
    }
}
