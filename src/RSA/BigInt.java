/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

import java.util.Vector;

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
    private final Vector<Byte> number;
    
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
        // TODO Implement this
        assert(value >= 0);
        
        number = new Vector<Byte>();
        
        do
        {
            number.add((byte)(value % 10));
        } while(value >= 0);
    }
    
    /**
     * Construct object using number encoded in a string, i.e. "12345"
     * 
     * @param value     Unsigned integer encoded as string, e.g. "12345"
     */
    public BigInt(String value)
    {
        // TODO Implement this
        number = new Vector<Byte>();
    }
    
    /**
     * Copy Constructor
     * 
     * @param value
     */
    public BigInt(BigInt value)
    {
        this.number = (Vector<Byte>) value.number.clone();
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
        // TODO Implement this
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
    throws ArithmeticException
    {
        // TODO Implement this
        
        if(greater(a, b)) throw new ArithmeticException("BigInt.sub(a,b): a is greater than b"); 
       
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
        // TODO Implement this
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
        // TODO Implement this
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
        // TODO Implement this
        // https://en.wikipedia.org/wiki/Montgomery_modular_multiplication
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
        // TODO Implement this
        // c = a+b -> n^c == n^(a+b) == n^a*n^b
        return null;
    }
    
    /**
     * Implements an efficient algorithm for computing b^e%m
     * 
     * @param b     Base
     * @param e     Exponent
     * @param m     Modulus
     * @return      new BigInt with the result of b^e%m
     */
    public static BigInt powMod(BigInt b, BigInt e, BigInt m)
    {
        // TODO Implement this
        
        /**
         * c = 1;
         * for(int i = 0; i < e; i++)
         *     c = (c*m)%n;
         */
        return null;
    }
    
    /**
     * Computes the greatest common divisor of a and b
     * 
     * @param a     value 1
     * @param b     value 2
     * @return      new BigInt with the greatest common divisor of a and b
     */
    public static BigInt gcd(BigInt a, BigInt b)
    {
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
        if(a.number.size() != b.number.size())
        {
            return false;
        }
        
        // Check that all digits in a and b are equal
        for(int i = 0; i < a.number.size(); ++i)
        {
            if(a.number.get(i).compareTo(b.number.get(i)) != 0)
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
        if(a.number.size() != b.number.size())
        {
            return a.number.size() < b.number.size();
        }
        
        // Starting from most-significant digit, check each digit for a < b
        for(int i = a.number.size(); i >= 0; --i)
        {
            if(a.number.get(i).compareTo(b.number.get(i)) < 0)
            {
                return true;
            }
            if(a.number.get(i).compareTo(b.number.get(i)) > 0)
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
        if(a.number.size() != b.number.size())
        {
            return a.number.size() > b.number.size();
        }
        
        // Starting from most-significant digit, check each digit for a > b
        for(int i = a.number.size(); i >= 0; --i)
        {
            if(a.number.get(i).compareTo(b.number.get(i)) > 0)
            {
                return true;
            }
            if(a.number.get(i).compareTo(b.number.get(i)) < 0)
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
    
    /**
     * Applies an approximate algorithm to conclude either the number is probably
     * prime or is definitely not prime.
     * The probably of the number returned being composite is pow(2, -128).
     * 
     * @param a     the value to test for primality
     * @return      true if number is probably prime; false if composite
     */
    public static boolean isPrime(BigInt a)
    {
        // TODO maybe implement this?
        return false;
    }
    
    /**
     * Generates a prime number with the specified number of digits.
     * An approximate prime generation algorithm is used.
     * The probably of the number returned being composite is pow(2, -128).
     * 
     * @param digits    Number of digits in the prime number
     * @return          BigInt object containing prime number
     */
    public static BigInt generatePrime(int digits)
    {
        // TODO maybe implement this?
        return null;
    }
    
    
    
    //
    //  The following overloads allow the user to chain multiple calls together
    //  
    //  BigInt a, b, c, d;
    //  ...
    //  d = a.pow(b).mod(c);
    //
    
    
    
    public BigInt add(BigInt b)
    {
        return add(this, b);
    }
    
    public BigInt sub(BigInt b)
    throws ArithmeticException
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
    
    public BigInt powMod(BigInt e, BigInt m)
    {
        return powMod(this, e, m);
    }
    
    public BigInt gcd(BigInt b)
    {
        return gcd(this, b);
    }
    
    public boolean equals(BigInt b)
    {
        return equals(this, b);
    }
    
    public boolean less(BigInt b)
    {
        return less(this, b);
    }
    
    public boolean greater(BigInt b)
    {
        return greater(this, b);
    }
    
    public boolean lessEqual(BigInt b)
    {
        return lessEqual(this, b);
    }
    
    public boolean greaterEqual(BigInt b)
    {
        return greaterEqual(this, b);
    }
    
    public boolean isPrime()
    {
        return isPrime(this);
    }
    
    
    
    /**
     * @return  string representation of this
     */
    @Override
    public String toString()
    {
        String result = "";
        
        for(int i = number.size() - 1; i >= 0; --i)
        {
            result += Integer.toString(number.get(i));
        }
        
        return result;
    }
    
    
    
    /**
     * Eliminates trailing zeroes in the stored number, e.g.
     * "0000000543210" becomes "543210"
     */
    private void trimZero()
    {
        // TODO implement this
    }
}
