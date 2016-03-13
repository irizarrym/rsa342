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
    public RSAKeyGen(BigInt p, BigInt q) throws Exception
    {
        BigInt n = p.mul(q);
        BigInt phi = p.sub(BigInt.ONE).mul(q.sub(BigInt.ONE));
        BigInt e = genE(phi);
        BigInt d = genD(e, phi);
        
        pubKey = new RSAPublicKey(e, n);
        prvKey = new RSAPrivateKey(d, n);
    }
    
    /**
     * Retrieve generated public key
     * 
     * @return public key object
     */
    public RSAPublicKey publicKey()
    {
        return pubKey;
    }
    
    /**
     * Retrieve generated private key
     * 
     * @return private key object
     */
    public RSAPrivateKey privateKey()
    {
        return prvKey;
    }
    
    /**
     * Generates the "e" value for the public key
     */
    private BigInt genE(BigInt phi)
    {
        final int[] candidates = new int[]{ 65537, 257, 17, 5, 3 };
        BigInt e = BigInt.ZERO;
        
        for(int i : candidates)
        {
            e = new BigInt(i);
            if(e.gcd(phi).equals(BigInt.ONE)) return e;
        }
        
        do
        {
            e = e.add(BigInt.ONE);
        } while(!e.gcd(phi).equals(BigInt.ONE));
        
        return e;
    }
    
    /**
     * Generates the "d" value for the private key
     */
    private BigInt genD(BigInt u, BigInt v) throws Exception
    {
        /**
         * Computing the modular inverse using the Extended Euclidean Algorithm
         * 
         * @see http://www.di-mgt.com.au/euclidean.html#code-modinv
         */
        
        BigInt inv, u1, u3, v1, v3, t1, t3, q;
        int iter;
        
        // Step X1. Initialize
        u1 = BigInt.ONE;
        u3 = u;
        v1 = BigInt.ZERO;
        v3 = v;
        
        // Remember odd/even iterations
        iter = 1;
        
        // Step X2. Loop while v3 != 0
        while(!v3.equals(BigInt.ZERO))
        {
            // Step X3. Divide and "Subtract"
            q = u3.div(v3);
            t3 = u3.mod(v3);
            t1 = q.mul(v1).add(u1);
            // Swap
            u1 = v1; v1 = t1; u3 = v3; v3 = t3;
            iter = -iter;
        }
        
        // Make sure u3 = gcd(u, v) == 1
        if(!u3.equals(BigInt.ONE))
        {
            throw new Exception("Inverse does not exist");
        }
        
        if(iter < 0)
        {
            return v.sub(u1);
        }
        else
        {
            return u1;
        }
    }
}
