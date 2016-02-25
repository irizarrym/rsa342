/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

/**
 * Store and operate on arbitrarily large unsigned integers
 */
public class BigInt
{
    /**
     * Stores the integer as individual decimal digits from the least-significant
     * digit to the most-significant digit
     * e.g. 25143 -> [3, 4, 1, 5, 2]
     */
    private final byte[] number;
    
    /**
     * Constructs object initialized to zero
     */
    public BigInt()
    {
        this(0);
    }
    
    /**
     * Constructs object from long integer
     * @param value 
     */
    public BigInt(long value)
    {
        //@@ Implement this
        assert(value >= 0);
        
        number = new byte[0];
    }
    
    /**
     * Construct object using number encoded in a string, i.e. "12345"
     * 
     * @param value     Unsigned integer encoded as string, e.g. "12345"
     */
    public BigInt(String value)
    {
        //@@ Implement this
        number = new byte[0];
    }
    
    /**
     * Copy Constructor
     * 
     * @param value
     */
    public BigInt(BigInt value)
    {
        this.number = value.number.clone();
    }
    
    /**
     * Adds the values of a and b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      new BigInt with the result of a + b
     */
    public static BigInt add(BigInt a, BigInt b)
    {
        //@@ Implement this
        return null;
    }
    
    /**
     * Subtracts the values of a and b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      new BigInt with the result of a - b
     */
    public static BigInt sub(BigInt a, BigInt b)
    {
        //@@ Implement this
        return null;
    }
    
    /**
     * Multiplies the values of a and b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      new BigInt with the result of a * b
     */
    public static BigInt mul(BigInt a, BigInt b)
    {
        //@@ Implement this
        return null;
    }
    
    /**
     * Divides the values of a and b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      new BigInt with the result of a / b
     */
    public static BigInt div(BigInt a, BigInt b)
    {
        //@@ Implement this
        return null;
    }
    
    /**
     * Computes the result of (a mod b)
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      new BigInt with the result of a mod b
     */
    public static BigInt mod(BigInt a, BigInt b)
    {
        //@@ Implement this
        return null;
    }
    
    /**
     * Computes the result of a^b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      new BigInt with the result of a^b
     */
    public static BigInt pow(BigInt a, BigInt b)
    {
        //@@ Implement this
        return null;
    }
    
    /**
     * Computes the result of a == b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      true if a == b; false if a != b
     */
    public static boolean equals(BigInt a, BigInt b)
    {
        // Check if a and b contain same number of digits
        if(a.number.length != b.number.length)
        {
            return false;
        }
        
        // Check that all digits in a and b are equal
        for(int i = 0; i < a.number.length; ++i)
        {
            if(a.number[i] != b.number[i])
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Computes the result of a < b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      true if a < b; false if a >= b
     */
    public static boolean less(BigInt a, BigInt b)
    {
        // Check if a and b contain differing number of digits
        if(a.number.length != b.number.length)
        {
            return a.number.length < b.number.length;
        }
        
        // Starting from most-significant digit, check each digit for a < b
        for(int i = a.number.length; i >= 0; --i)
        {
            if(a.number[i] < b.number[i])
            {
                return true;
            }
            else if(a.number[i] > b.number[i])
            {
                return false;
            }
        }
        
        return false;
    }
    
    /**
     * Computes the result of a > b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      true if a > b; false if a <= b
     */
    public static boolean greater(BigInt a, BigInt b)
    {
        // Check if a and b contain differing number of digits
        if(a.number.length != b.number.length)
        {
            return a.number.length > b.number.length;
        }
        
        // Starting from most-significant digit, check each digit for a > b
        for(int i = a.number.length; i >= 0; --i)
        {
            if(a.number[i] > b.number[i])
            {
                return true;
            }
            else if(a.number[i] < b.number[i])
            {
                return false;
            }
        }
        
        return false;
    }
    
    public static boolean lessEqual(BigInt a, BigInt b)
    {
        return !greater(b, a);
    }
    
    public static boolean greaterEqual(BigInt a, BigInt b)
    {
        return !less(b, a);
    }
    
    
    
    //
    //  The following functions allow you to chain multiple calls together
    //  
    //  BigInt a, b, c, d;
    //  ...
    //  d = a.pow(b).mod(c);
    //
    
    
    
    /**
     * Adds the values of this and b
     * 
     * @param b     right operand
     * @return      BigInt.add(this, b)
     */
    public BigInt add(BigInt b)
    {
        return add(this, b);
    }
    
    public BigInt sub(BigInt b)
    {
        return sub(this, b);
    }
    
    public BigInt mul(BigInt b)
    {
        return mul(this, b);
    }
    
    public BigInt div(BigInt b)
    {
        return div(this, b);
    }
    
    public BigInt pow(BigInt b)
    {
        return pow(this, b);
    }
    
    public BigInt mod(BigInt b)
    {
        return mod(this, b);
    }
    
    
    
    /**
     * @return  string representation of this
     */
    @Override
    public String toString()
    {
        return "";
    }
}
