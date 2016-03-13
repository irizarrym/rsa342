/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Stores an RSA private key and decrypts BigInt values
 */
public class RSAPrivateKey
{
    private final BigInt d, n;
    
    public RSAPrivateKey(BigInt dValue, BigInt nValue)
    {
        d = dValue;
        n = nValue;
    }
    
    public BigInt decrypt(BigInt value)
    {
        return value.powMod(d, n);
    }
    
    public BigInt dValue()
    {
        return d;
    }
    
    public BigInt nValue()
    {
        return n;
    }
}
