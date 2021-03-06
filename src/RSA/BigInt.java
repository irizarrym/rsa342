/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

/**
 * Store and operate on arbitrarily large unsigned integers
 */
public class BigInt
{
    /**
     * Predefined constants for some common values also used internally
     * by this class
     */
    public static final BigInt ZERO = new BigInt(0);
    public static final BigInt ONE  = new BigInt(1);
    public static final BigInt TWO  = new BigInt(2);
    public static final BigInt TEN  = new BigInt(10);
    
    /**
     * Stores the integer as individual decimal digits from the
     * least-significant digit to the most-significant digit
     * e.g. 25143 -> [3, 4, 1, 5, 2]
     */
    private final ArrayList<Byte> number;
    
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
     * @throws IllegalArgumentException if value < 0
     */
    public BigInt(long value)
    {
        if(value < 0)
        {
            throw new IllegalArgumentException("Negative value: " + value);
        }
        
        number = new ArrayList<>();
        
        // Loop must run at least once so there is at least one digit
        // e.g. : 0 -> [0]
        do
        {
            number.add((byte)(value % 10));
            value /= 10;
        } while(value > 0);
    }
    
    /**
     * Construct object using number encoded in a string, i.e. "12345"
     * 
     * @param value     Unsigned integer encoded as string, e.g. "12345"
     * @throws IllegalArgumentException if "value" contains any
     * non-numeric characters or symbols
     */
    public BigInt(String value)
    {
        number = new ArrayList<>();
        
        for(int i = value.length() - 1; i >= 0; --i) switch(value.charAt(i))
        {
            case '0': number.add((byte)0); break;
            case '1': number.add((byte)1); break;
            case '2': number.add((byte)2); break;
            case '3': number.add((byte)3); break;
            case '4': number.add((byte)4); break;
            case '5': number.add((byte)5); break;
            case '6': number.add((byte)6); break;
            case '7': number.add((byte)7); break;
            case '8': number.add((byte)8); break;
            case '9': number.add((byte)9); break;
            default:
                throw new IllegalArgumentException(
                    "Non-numeric digit: " + value.charAt(i));
        }
        
        // Removes leading zeroes in value
        // e.g. "000123" becomes "123"
        trimZero();
        
        if(number.size() == 0) number.add((byte)0);
    }
    
    /**
     * Copy Constructor
     * 
     * @param value
     */
    public BigInt(BigInt value)
    {
        this.number = (ArrayList<Byte>) value.number.clone();
    }
    
    /**
     * Used internally by arithmetic operations to construct a BigInt
     * from an ArrayList<Byte>
     * 
     * @param digits 
     */
    private BigInt(ArrayList<Byte> digits)
    {
        // number = (ArrayList<Byte>) digits.clone();
        number = digits;
        
        // Removes leading zeroes in value
        // e.g. "000123" becomes "123"
        trimZero();
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
        ArrayList<Byte> result = new ArrayList<>();
        ArrayList<Byte> x = a.number;
        ArrayList<Byte> y = b.number;
        
        final int stopAt = Integer.max(x.size(), y.size());
        int overflow = 0;
        for(int i = 0; i < stopAt || overflow > 0; ++i)
        {
            int nextDigit = overflow;
            overflow = 0;
            
            if(i < x.size()) nextDigit += x.get(i);
            if(i < y.size()) nextDigit += y.get(i);
            
            overflow = 0;
            if(nextDigit > 9)
            {
                overflow = 1;
                nextDigit -= 10;
            }
            
            result.add((byte)nextDigit);
        }
        
        return new BigInt(result);
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
        if(less(a, b)) throw new ArithmeticException("BigInt.sub(a,b): a is less than b");
        
        ArrayList<Byte> result = new ArrayList<>();
        ArrayList<Byte> x = a.number;
        ArrayList<Byte> y = b.number;
        
        int underflow = 0;
        for(int i = 0; i < x.size(); ++i)
        {
            int nextDigit = x.get(i) + underflow;
            if(i < y.size()) nextDigit -= y.get(i);
            
            underflow = 0;
            if(nextDigit < 0)
            {
                underflow = -1;
                nextDigit += 10;
            }
            
            result.add((byte)nextDigit);
        }
        
        return new BigInt(result);
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
        BigInt result = ZERO;
        ArrayList<Byte> x = a.number;
        ArrayList<Byte> y = b.number;
        
        // Swap values if x has fewer digits than y
        // This will make the multiplication operation faster
        // This is safe because multiplication is commutative
        if(x.size() < y.size())
        {
            ArrayList<Byte> z = x;
            x = y;
            y = z;
        }
        
        int padding = 0;
        for(byte i : y)
        {
            if(i == 0)
            {
                padding += 1;
                continue;
            }
            
            ArrayList<Byte> temp = new ArrayList<>();
            int overflow = 0;
            
            for(int k = 0; k < padding; ++k)
            {
                temp.add((byte)0);
            }
            padding += 1;
            
            for(byte j : x)
            {
                int nextDigit = i*j + overflow;
                overflow = 0;
                if(nextDigit > 9)
                {
                    overflow = nextDigit / 10;
                    nextDigit %= 10;
                }
                temp.add((byte)nextDigit);
            }
            
            if(overflow > 0) temp.add((byte)overflow);
            
            result = result.add(new BigInt(temp));
        }
        
        return result;
    }
    
    /**
     * Divides the values of num and den
     * 
     * @param num   numerator
     * @param den   denominator
     * @return      new BigInt with the result of num / den
     */
    public static BigInt div(BigInt num, BigInt den)
    {
        if(den.equals(ZERO)) throw new ArithmeticException("Divide by Zero Error");
        
        if(num.less(den)) return ZERO;
        
        ArrayList<Byte> result = new ArrayList<>();
        ArrayList<Byte> n      = (ArrayList<Byte>) num.number.clone();
        BigInt remainder = ZERO;
        
        // Reverse n so that it begins with most-significant digit
        Collections.reverse(n);
        
        // Perform division just like grade school...
        for(byte digit : n)
        {
            remainder = remainder.mul(TEN).add(new BigInt(digit));
            
            byte nextDigit = 0;
            while(den.lessEqual(remainder))
            {
                nextDigit += 1;
                remainder = remainder.sub(den);
            }
            
            result.add(nextDigit);
        }
        
        // Expecting least-significant digit to be first
        Collections.reverse(result);
        
        return new BigInt(result);
    }
    
    /**
     * Computes the result of (a mod m)
     * 
     * @param a     left operand
     * @param m     right operand
     * @return      new BigInt with the result of a mod m
     */
    public static BigInt mod(BigInt a, BigInt m)
    {
        // Special cases
        if(m.equals(ONE))
        {
            return ZERO;
        }
        if(m.equals(TWO))
        {
            return (a.number.get(0)%2 == 1) ? ONE : ZERO;
        }
        
        // General case
        return sub(a, div(a, m).mul(m));
    }
    
    /**
     * Check if the BigInt is prime
     * 
     * @param n		BigInt to be checked
     * @return      True if n is prime, otherwise false
     */
    public static boolean isPrime(BigInt n)
    {
    	if (mod(n,TWO).equals(ZERO)) return false;
    	
    	
    	for(BigInt i = new BigInt(3); pow(i,TWO).lessEqual(n); i = i.add(TWO)) 
    	{
    		if(mod(n,i).equals(ZERO))
    			return false;
    	}
    	
    	return true;
    }
    
    /**
     * Computes the result of base^exp
     * 
     * @param base  left operand
     * @param exp   right operand
     * @return      new BigInt with the result of base^exp
     * @see         http://math.stackexchange.com/a/87410
     */
    public static BigInt pow(BigInt base, BigInt exp)
    {
        // Special cases
        if(base.equals(ZERO))
        {
            // 0^0 is undefined so throw error
            if(exp.equals(ZERO))
            {
                throw new ArithmeticException("Undefined: pow(0, 0)");
            }
            else return ZERO;
        }
        if(base.equals(ONE))  return ONE;
        if(exp.equals(ZERO)) return ONE;
        if(exp.equals(ONE))  return base;
        if(exp.equals(TWO)) return mul(base, base);
        
        BigInt r = ONE;
        while(exp.greater(ZERO))
        {
            if(mod(exp, TWO).equals(ONE)) r = mul(r, base);
            base = mul(base, base);
            exp = div(exp, TWO);
        }
        
        return r;
    }
    
    /**
     * Implements an efficient algorithm for computing base^exp%modulo
     * 
     * @param base      Base
     * @param exp       Exponent
     * @param modulo    Modulus
     * @return          new BigInt with the result of b^e%m
     * @see             http://stackoverflow.com/a/5989549
     */
    public static BigInt powMod(BigInt base, BigInt exp, BigInt modulo)
    {
        // Base Case
        if(exp.equals(ZERO)) return ONE;
        
        // Recursive Case
        if(mod(exp, TWO).equals(ZERO))
        {
            return powMod(base, div(exp, TWO), modulo).pow(TWO).mod(modulo);
        }
        else
        {
            return powMod(base, sub(exp, ONE), modulo).mul(base).mod(modulo);
        }
    }
    
    /**
     * Computes the greatest common divisor of a and b using the
     * Euclidean algorithm
     * 
     * @param a     value 1
     * @param b     value 2
     * @return      new BigInt with the greatest common divisor of a and b
     */
    public static BigInt gcd(BigInt a, BigInt b)
    {
        // Special cases
        if(a.equals(ZERO)) return b;
        if(b.equals(ZERO)) return a;
        
        while(!b.equals(ZERO))
        {
            BigInt t = b;
            b = a.mod(b);
            a = t;
        }
        
        return a;
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
        for(int i = a.number.size() - 1; i >= 0; --i)
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
        return less(b, a);
    }
    
    /**
     * Computes the result of a <= b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      true if a <= b; false if a > b
     */
    public static boolean lessEqual(BigInt a, BigInt b)
    {
        return !less(b, a);
    }
    
    /**
     * Computes the result of a >= b
     * 
     * @param a     left operand
     * @param b     right operand
     * @return      true if a >= b; false if a < b
     */
    public static boolean greaterEqual(BigInt a, BigInt b)
    {
        return !less(a, b);
    }
    
    /**
     * Generates a random BigInt with specified number of digits
     * 
     * @param digits    number of decimal digits
     * @return          BigInt with randomly generated value
     */
    public static BigInt randInt(int digits, Random r)
    {
        if(digits < 0)
            throw new IllegalArgumentException("Required: digits >= 0");
        
        ArrayList<Byte> tmp = new ArrayList<>();
        
        for(int i = 0; i < digits; ++i)
        {
            tmp.add((byte)r.nextInt(10));
        }
        
        return new BigInt(tmp);
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
    
    public BigInt sub(BigInt b) throws ArithmeticException
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
    
    
    
    /**
     * Constructs a string representation of the value
     * 
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
     * Constructs a string representation of the value with
     * a minimum number of digits. Leading zeroes will be
     * added if necessary to satisfy minimum width, e.g.
     * if digits == 8, then 12345 becomes "00012345".
     * 
     * @param digits    minimum number of digits in value
     * @return          string representation of this
     */
    public String toString(int digits)
    {
        String result = toString();
        
        while(result.length() < digits)
        {
            result = "0" + result;
        }
        
        return result;
    }
    
    /**
     * Converts value stored as BigInt to type int
     * 
     * @throws  Exception if value is too large to fit into int
     * @return  value as int
     */
    public int toInt() throws Exception
    {
        int result = 0;
        
        for(int i = number.size() - 1; i >= 0; --i)
        {
            int prev = result;
            result *= 10;
            result += number.get(i);
            
            // Check for multiplication overflow
            if(prev != result/10)
            {
                throw new Exception("Value too large to fit into int");
            }
        }
        
        return result;
    }
    
    /**
     * Converts value stored as BigInt to type long
     * 
     * @throws  Exception if value is too large to fit into long
     * @return  value as long
     */
    public long toLong() throws Exception
    {
        long result = 0;
        
        for(int i = number.size() - 1; i >= 0; --i)
        {
            long prev = result;
            result *= 10;
            result += number.get(i);
            
            // Check for multiplication overflow
            if(prev != result/10)
            {
                throw new Exception("Value too large to fit into long");
            }
        }
        
        return result;
    }
    
    
    
    /**
     * Eliminates leading zeroes in the stored number, e.g.
     * [3, 2, 1, 0, 0, 0] becomes [3, 2, 1] and
     * [0, 0, 0] becomes [0]
     */
    private void trimZero()
    {
        assert(number.size() > 0);
        
        while(number.size() > 1 && number.get(number.size() - 1) == 0)
        {
            number.remove(number.size() - 1);
        }
    }
}
