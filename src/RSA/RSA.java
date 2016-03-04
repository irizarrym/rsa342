/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

/**
 * Performs RSA encryption on BigInt objects
 */
public class RSA
{
    private final BigInt e, d, n;
    
    /**
     * Generate keys from two given prime numbers
     */
    public RSA(BigInt prime1, BigInt prime2)
    {
        // TODO implement this
        e = null;
        d = null;
        n = null;
    }
    
    /**
     * Build keys from post-computed e, d, and n values
     * @param eValue    Value for e
     * @param dValue    Value for d
     * @param nValue    Value for n
     */
    public RSA(BigInt eValue, BigInt dValue, BigInt nValue)
    {
        e = eValue;
        d = dValue;
        n = nValue;
    }
    
    
    
    public BigInt encrypt(BigInt value)
    {
        // TODO implement this
        return null;
    }
    
    public BigInt decrypt(BigInt value)
    {
        // TODO implement this
        return null;
    }
    
    
    
    public BigInt eValue()
    {
        return e;
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
