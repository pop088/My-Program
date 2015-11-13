import junit.framework.TestCase;
import junit.framework.TestResult;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
        assertEquals(new Rational(0,1), new Rational(0,2));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
        assertFalse(new Rational(0,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
        assertEquals(new Rational(0,3).numerator(), 0);
        assertEquals(new Rational(9,3).denominator(), 1);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }


    public void testplus() {
        assertEquals(new Rational(2,3).plus(new Rational(2,3)), new Rational(4,3));
        assertEquals(new Rational(1,2).plus(new Rational(1,4)), new Rational(3,4));
        assertEquals(new Rational(1,3).plus(new Rational(2,3)), new Rational(1,1));
        assertEquals(new Rational(0,3).plus(new Rational(2,3)), new Rational(2,3));
        assertEquals(new Rational(-1,3).plus(new Rational(1,3)), new Rational(0,3));
        assertEquals(new Rational(-2,3).plus(new Rational(-2,3)), new Rational(-4,3));

    }

    public void testtimes() {
        assertEquals(new Rational(2,3).times(new Rational(2,3)), new Rational(4,9));
    }

    public void testminus() {
        assertEquals(new Rational(2,3).minus(new Rational(2,3)), new Rational(0,3));
        assertEquals(new Rational(1,2).minus(new Rational(1,4)), new Rational(1,4));
        assertEquals(new Rational(1,3).minus(new Rational(2,3)), new Rational(-1,3));
        assertEquals(new Rational(0,3).minus(new Rational(2,3)), new Rational(-2,3));
        assertEquals(new Rational(-1,3).minus(new Rational(1,3)), new Rational(-2,3));
        assertEquals(new Rational(-2,3).minus(new Rational(-2,3)), new Rational(0,3));
    }

    public void testdivides() {
        assertEquals(new Rational(2,3).divides(new Rational(2,3)), new Rational(1,1));
    }

    public void testsetTolerance(){
		new Rational(1,2).setTolerance(new Rational(5,4));
		assertEquals(new Rational(3,2).getTolerance(),new Rational(5,4));
	}

    public void testabs() {
        assertEquals(new Rational(-2,3).abs(), new Rational(2,3));
        assertEquals(new Rational(0,2).abs(), new Rational(0,2));
        assertEquals(new Rational(1,3).abs(), new Rational(1,3));

    }

    public void testless() {
        assertFalse(new Rational(-2,3).isLessThan(new Rational(-2,3)));
        assertTrue(new Rational(0,2).isLessThan(new Rational(1,2)));
        assertTrue(new Rational(-1,3).isLessThan(new Rational(1,3)));
        assertTrue(new Rational(-2,3).isLessThan(new Rational(-1,3)));
    }

    public void testDenominator(){
    	Rational r = new Rational(1,0);
    	assertTrue(r.denominator()!=0);
    }

    public void testRoot1(){
        Rational s = new Rational( 16, 25 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertEquals(sRoot, new Rational(4,5));
}

    public void testPlusOverflow(){
    	Rational p1 = new Rational(2147483647,1);
    	Rational p2 = new Rational(2147483647,1);
    	Rational sum = p1.plus(p2);
    	assertFalse(new Rational(-2,1).equals(sum));
    }
    
    public void testTimesOverflow(){
    	Rational p1 = new Rational(2147483647,1);
    	Rational p2 = new Rational(2,1);
    	Rational prod = p1.times(p2);
    	assertFalse(new Rational(-2,1).equals(prod));
    }
    
    public void testMinusOverflow(){
    	Rational p1 = new Rational(3*2147483647,1);
    	Rational p2 = new Rational(2147483647,1);
    	Rational sub = p1.minus(p2);
    	assertFalse(new Rational(-2,1).equals(sub));
    }
    
    public void testDivideOverflow(){
    	Rational p1 = new Rational(2147483647,1);
    	Rational p2 = new Rational(1,2147483647);
    	Rational div = p1.divides(p2);
    	assertFalse(new Rational(1,1).equals(div));
    }

    public void testNumerator2(){
    	Rational r = new Rational(2147483647*2,1);
    	assertFalse(r.numerator()==-2);
    	
    }

    public void testdenominator2(){
    	Rational r = new Rational(1,2147483647*2);
    	assertFalse(r.denominator()==-2);
}

    public void testMain(){
    	 Rational s = new Rational( 1, 2 );
    	 Rational.main(new String[]{});
    	 assertTrue(1==1);
    }

    public void testlcm(){
    	Rational p1 = new Rational(1,-3);
    	Rational p2 = new Rational(1,-6);
    	Rational sum = p1.plus(p2);
    	assertEquals(sum,new Rational(-1,2));
    }

    public void testgcd(){
		Rational g = new Rational(12,16);
		assertEquals(g.numerator()*4,12);
		assertEquals(g.denominator()*4,16);
	}

    public void testCopy(){
		assertEquals(new Rational(new Rational(1,2)),new Rational(1,2));	
	}	

    public void testRoot3() {
        Rational s = new Rational( 46341, 1 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
            assert false;
        } catch (IllegalArgumentToSquareRootException e) {
            assert true;
        }
    }
    
    public void testRoot4() {
        Rational s = new Rational( -1, 1 );
        Rational sRoot = null;
        try {
            sRoot = s.root();
            assert false;
        } catch (IllegalArgumentToSquareRootException e) {
            assert true;
        }
    }

    public static void main(String args[]){
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner TR = new junit.textui.TestRunner();
	TR.main(testCaseName);

     
}
}
