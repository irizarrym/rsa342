/*
    Group 58:
    Michael Irizarry (miriza6@uic.edu)
    Wenkan Zhu (wzhu24@uic.edu)

    CS 342 - Project 3
    RSA
*/

package RSA;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for class BigInt
 */
public class BigIntTest
{
    public BigIntTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorTest()
    {
        assertEquals("12345", (new BigInt(12345)).toString());
        assertEquals("12345", (new BigInt("12345")).toString());
        assertEquals("12345",
            new BigInt(new BigInt(12345)).toString());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void constructorTestThrow()
    {
        // Strings must only contain numeric digits,
        // no other characters or symbols.
        new BigInt("000 111");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void constructorTestThrow2()
    {
        // Integers must not be negative
        new BigInt(-5);
    }
    
    @Test
    public void addTest()
    {
        BigInt a = new BigInt("123456789");
        BigInt b = new BigInt("12345");
        BigInt c = new BigInt("123469134");
        BigInt zero = BigInt.ZERO;
        
        // Is addition commutative?
        assertTrue(a.add(b).equals(c));
        assertTrue(b.add(a).equals(c));
        
        // a + 0 == a
        assertTrue(a.add(zero).equals(a));
        assertTrue(zero.add(a).equals(a));
        assertTrue(zero.add(zero).equals(zero));
    }
    
    @Test
    public void addTestRandom()
    {
        Random r = new Random();
        
        for(int i = 0; i < 100; ++i)
        {
            int a = r.nextInt(Integer.MAX_VALUE / 2);
            int b = r.nextInt(Integer.MAX_VALUE / 2);
            int c = a + b;
            
            BigInt x = new BigInt(a);
            BigInt y = new BigInt(b);
            BigInt z = new BigInt(c);
            
            assertTrue(x.add(y).equals(z));
            assertTrue(y.add(x).equals(z));
        }
    }
    
    @Test
    public void subTest()
    {
        BigInt a = new BigInt("123456789");
        BigInt b = new BigInt("12345");
        BigInt c = new BigInt("123444444");
        BigInt d = a.sub(b);
        BigInt zero = BigInt.ZERO;
        
        assertTrue(d.equals(c));
        assertTrue(a.sub(a).equals(zero));
        assertTrue(b.sub(b).equals(zero));
        
        assertTrue(a.sub(zero).equals(a));
        assertTrue(b.sub(zero).equals(b));
        assertTrue(zero.sub(zero).equals(zero));
    }
    
    @Test
    public void subTestRandom()
    {
        Random r = new Random();
        
        for(int i = 0; i < 100; ++i)
        {
            int a = r.nextInt(Integer.MAX_VALUE / 2);
            int b = r.nextInt(Integer.MAX_VALUE / 2) + a;
            int c = b - a;
            
            BigInt x = new BigInt(a);
            BigInt y = new BigInt(b);
            BigInt z = new BigInt(c);
            
            assertTrue(y.sub(x).equals(z));
        }
    }
    
    @Test(expected=ArithmeticException.class)
    public void subTestThrows()
    {
        BigInt a = new BigInt("123456789");
        BigInt b = new BigInt("12345");
        
        // b is less than a so this should fail
        b.sub(a);
    }
    
    @Test
    public void mulTest()
    {
        BigInt a = new BigInt(12345);
        BigInt b = new BigInt(54321);
        BigInt c = new BigInt(12345*54321);
        BigInt zero = BigInt.ZERO;
        
        assertTrue(a.mul(b).equals(c));
        assertTrue(b.mul(a).equals(c));
        assertTrue(a.mul(zero).equals(zero));
        assertTrue(zero.mul(b).equals(zero));
    }
    
    @Test
    public void mulTestRandom()
    {
        Random r = new Random();
        
        for(int i = 0; i < 100; ++i)
        {
            int a = r.nextInt(10000);
            int b = r.nextInt(10000);
            int c = a * b;
            
            BigInt x = new BigInt(a);
            BigInt y = new BigInt(b);
            BigInt z = new BigInt(c);
            
            assertTrue(x.mul(y).equals(z));
            assertTrue(y.mul(x).equals(z));
        }
    }
    
    @Test
    public void divTest()
    {
        BigInt a = new BigInt(12345*7);
        BigInt b = new BigInt(12345);
        BigInt c = new BigInt(7);
        
        assertTrue(a.div(b).equals(c));
        assertTrue(b.div(a).equals(BigInt.ZERO));
        assertTrue(a.div(a).equals(BigInt.ONE));
        assertTrue(b.div(b).equals(BigInt.ONE));
        assertTrue(BigInt.ZERO.div(BigInt.ONE).equals(BigInt.ZERO));
        assertTrue(BigInt.ONE.div(BigInt.TWO).equals(BigInt.ZERO));
        assertTrue(a.div(BigInt.ONE).equals(a));
        assertTrue(b.div(BigInt.ONE).equals(b));
    }
    
    @Test
    public void divTestRandom()
    {
        Random r = new Random();
        
        for(int i = 0; i < 100; ++i)
        {
            int a = r.nextInt(Integer.MAX_VALUE);
            int b = r.nextInt(Integer.MAX_VALUE);
            
            BigInt x = new BigInt(a);
            BigInt y = new BigInt(b);
            BigInt z = new BigInt(a/b);
            BigInt zz = new BigInt(b/a);
            
            assertTrue(x.div(y).equals(z));
            assertTrue(y.div(x).equals(zz));
        }
    }
    
    @Test(expected=ArithmeticException.class)
    public void divTestThrows()
    {
        BigInt.TEN.div(BigInt.ZERO);
    }
    
    @Test
    public void modTest()
    {
        BigInt a = new BigInt(12345*7);
        BigInt b = new BigInt(12345);
        
        assertTrue(a.mod(b).equals(BigInt.ZERO));
        assertTrue(b.mod(a).equals(b));
        assertTrue(BigInt.ZERO.mod(BigInt.TEN).equals(BigInt.ZERO));
        assertTrue(a.mod(a).equals(BigInt.ZERO));
        assertTrue(b.mod(b).equals(BigInt.ZERO));
        assertTrue(BigInt.ONE.mod(BigInt.ONE).equals(BigInt.ZERO));
    }
    
    @Test
    public void modTestRandom()
    {
        Random r = new Random();
        
        for(int i = 0; i < 100; ++i)
        {
            int a = r.nextInt(Integer.MAX_VALUE);
            int b = r.nextInt(Integer.MAX_VALUE);
            
            BigInt x = new BigInt(a);
            BigInt y = new BigInt(b);
            BigInt z = new BigInt(a%b);
            BigInt zz = new BigInt(b%a);
            
            assertTrue(x.mod(y).equals(z));
            assertTrue(y.mod(x).equals(zz));
        }
    }
    
    @Test
    public void gcdTest()
    {
        // These are all large prime numbers
        String s1   = "3432031961807721128272555456084784389356675749704300733"
                    + "0482608143343054667115134377134463699176949771491341170"
                    + "69447433150420619418300514900710614456357677";
        String s2   = "2540466748247826809908424798999322389214635768382465990"
                    + "7845625141241245246919701464501179949183355980222014906"
                    + "90264878944085505581907518471961415423867301";
        String s3   = "4405407657232373019056619434180352628543129818697310644"
                    + "2927831894441865877327615394159140538630716022034166785"
                    + "52822233525966174160015274467413033357427743";
        
        BigInt b1 = new BigInt(s1);
        BigInt b2 = new BigInt(s2);
        BigInt b3 = new BigInt(s3);
        
        BigInt b4 = b1.mul(b2);
        BigInt b5 = b2.mul(b3);
        
        // These numbers are prime so should have no common factors
        assertTrue(b1.gcd(b2).equals(BigInt.ONE));
        assertTrue(b1.gcd(b3).equals(BigInt.ONE));
        assertTrue(b2.gcd(b3).equals(BigInt.ONE));
        
        // b4 and b5 share a common factor of b2
        assertTrue(b4.gcd(b5).equals(b2));
        
        // Test for divisibility
        assertTrue(b4.gcd(b1).equals(b1));
        assertTrue(b4.gcd(b2).equals(b2));
        assertTrue(b4.gcd(b3).equals(BigInt.ONE));
        
        assertTrue(b5.gcd(b1).equals(BigInt.ONE));
        assertTrue(b5.gcd(b2).equals(b2));
        assertTrue(b5.gcd(b3).equals(b3));
        
        // Zero|One Tests
        assertTrue(BigInt.ZERO.gcd(b1).equals(b1));
        assertTrue(b1.gcd(BigInt.ZERO).equals(b1));
        assertTrue(BigInt.ZERO.gcd(BigInt.ZERO).equals(BigInt.ZERO));
        assertTrue(BigInt.ONE.gcd(b1).equals(BigInt.ONE));
        assertTrue(b1.gcd(BigInt.ONE).equals(BigInt.ONE));
    }
    
    @Test
    public void equalsTest()
    {
        BigInt a = new BigInt("123456789");
        BigInt b = new BigInt("123456789");
        BigInt c = new BigInt("987654321");
        
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }
    
    @Test
    public void lessTest()
    {
        BigInt a = new BigInt(12345);
        BigInt b = new BigInt(54321);
        BigInt zero = new BigInt(0);
        
        assertTrue(a.less(b));
        assertFalse(b.less(a));
        assertFalse(a.less(a));
        assertFalse(b.less(b));
        
        assertTrue(zero.less(a));
        assertTrue(zero.less(b));
        assertFalse(a.less(zero));
        assertFalse(b.less(zero));
        assertFalse(zero.less(zero));
    }
    
    @Test
    public void greaterTest()
    {
        BigInt a = new BigInt(12345);
        BigInt b = new BigInt(54321);
        BigInt zero = new BigInt(0);
        
        assertFalse(a.greater(b));
        assertTrue(b.greater(a));
        assertFalse(a.greater(a));
        assertFalse(b.greater(b));
        
        assertTrue(a.greater(zero));
        assertTrue(b.greater(zero));
        assertFalse(zero.greater(a));
        assertFalse(zero.greater(b));
        assertFalse(zero.less(zero));
    }
    
    @Test
    public void toStringTest()
    {
        BigInt a = new BigInt(93485943);
        BigInt b = new BigInt(0);
        BigInt c = new BigInt(12345);
        
        assertEquals("93485943", a.toString());
        assertEquals("0", b.toString());
        assertEquals("12345", c.toString());
        
        assertEquals("00000", b.toString(5));
        assertEquals("00012345", c.toString(8));
    }
    
    @Test
    public void trimZeroTest()
    {
        BigInt a = new BigInt("0000012345");
        BigInt b = new BigInt("12345");
        
        assertTrue(a.toString().equals("12345"));
        assertTrue(a.equals(b));
    }
}
