/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

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
        BigInt zero = new BigInt(0);
        
        // Is addition commutative?
        assertTrue(a.add(b).equals(c));
        assertTrue(b.add(a).equals(c));
        
        // a + 0 == a
        assertTrue(a.add(zero).equals(a));
        assertTrue(zero.add(a).equals(a));
        assertTrue(zero.add(zero).equals(zero));
    }
    
    @Test
    public void subTest()
    {
        BigInt a = new BigInt("123456789");
        BigInt b = new BigInt("12345");
        BigInt c = new BigInt("123444444");
        BigInt d = a.sub(b);
        BigInt zero = new BigInt(0);
        
        assertTrue(d.equals(c));
        assertTrue(a.sub(a).equals(zero));
        assertTrue(b.sub(b).equals(zero));
        
        assertTrue(a.sub(zero).equals(a));
        assertTrue(b.sub(zero).equals(b));
        assertTrue(zero.sub(zero).equals(zero));
    }
    
    @Test(expected=ArithmeticException.class)
    public void subTest2()
    {
        BigInt a = new BigInt("123456789");
        BigInt b = new BigInt("12345");
        
        // b is less than a so this should fail
        b.sub(a);
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
