/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Stores a public key and encrypts BigInt values
 */
public class RSAPublicKey
{
    private final BigInt e, n;
    
    public RSAPublicKey(BigInt eValue, BigInt nValue)
    {
        e = eValue;
        n = nValue;
    }
    
    public BigInt encrypt(BigInt value)
    {
        // TODO implement this
        return null;
    }
    
    public BigInt eValue()
    {
        return e;
    }
    
    public BigInt nValue()
    {
        return n;
    }
}
