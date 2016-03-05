/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Michael
 */
public class BigIntTest {
    
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
