/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    /*
     * Testing strategy
     * 
     * toString() partitions the inputs as follows:
     * Number: Integer constructor, Double constructor
     * Variable: upper and lower case
     * Plus: Variable + Number, 
     * Times: Variable + Number
     * 
     * equals() partitions the inputs as follows:
     * Number: Number to Number, Number to Object
     * Variable: case-sensitive, upper and lower case
     * Compare Plus to Times with equal construction
     * 
     * hashCode() partitions the inputs as follows:
     * Number: Number to Number, Number to Object
     * variable: case-sensitive, upper and lower case
     * Compare Plus to Times with equal construction
     * 
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Expression
    
    //covers Number Double constructor and toString()
    @Test
    public void testNumberDoubleToString() {
    	Expression e = new Number(2.5);
    	String expected = "2.5";
    	
    	assertEquals("Expected String \"2.5\"", expected, e.toString());
    }
    
    // covers Number integer constructor and equals(Object)
    @Test
    public void testNumberIntegerConstructorEqualsDoubleConstructor() {
    	Expression e1 = new Number(1);
    	Expression e2 = new Number(1.000);
    	Object o2 = e2;
    	
    	assertTrue("Expected Expression e1 to equal Expression e2", e1.equals(e2));
    	assertTrue("Expected Expression e1 to equal Object o2", e1.equals(o2));
    }
    
    // covers Number hashCode()
    @Test
    public void testNumberHashCode() {
    	Expression e1 = new Number(1.0);
    	Expression e2 = new Number(1.000);
    	
    	assertEquals("Expected hashCode of e1 to equal hashCode of e2", e1.hashCode(), e2.hashCode());
    }
    
    // covers Variable toString() and case-sensitivity
    @Test
    public void testVariableToString() {
    	Expression e = new Variable("Foo");
    	String expectedTrue = "Foo";
    	String expectedFalse = "foo";
    	
    	assertTrue("Expected String \"Foo\" equal e.toString", e.toString().equals(expectedTrue));
    	assertFalse("Expected String \"foo\" not equal e.toString", e.toString().equals(expectedFalse));
    }
    
    // covers Variable equals(Object)
    @Test
    public void testVariableEquals() {
    	Expression e1 = new Variable("Foo");
    	Expression e2 = new Variable("Foo");
    	Expression e3 = new Variable("foo");
    	
    	Object o2 = e2;
    	
    	assertTrue("Expected Expression e1 to equal Expression e2", e1.equals(e2));
    	assertTrue("Expected Expression e1 to equal Object o2", e1.equals(o2));
    	assertFalse("Expected Expression e1 not to equal Expression e3", e1.equals(e3));
    }
    
    // covers Variable hashCode(), case-sensitive
    @Test
    public void testVariableHashCode() {
    	Expression e1 = new Variable("z");
    	Expression e2 = new Variable("z");
    	Expression e3 = new Variable("Z");
    	
    	Integer hashCode1 = e1.hashCode();
    	Integer hashCode2 = e3.hashCode();
    	
    	assertEquals("Expected hashCode of e1 to equal hashCode of e2", e1.hashCode(), e2.hashCode());
    	assertFalse("Expected different hashCode for same letter in lower and upper case", hashCode1.equals(hashCode2));
    }
    
    // covers Plus toString()
    @Test
    public void testPlusToString() {
    	Expression left = new Number(3.0);
    	Expression right = new Variable("foo");
    	Expression e = new Plus(left, right);
    	String expected = "3.0 + foo";
    	
    	assertEquals("Expected String \"3.0 + foo\"", expected, e.toString());
    }
    
    // covers Plus equals(Object) and case-sensitivity
    @Test
    public void testPlusEquals() {
    	Expression left1 = new Number(1);
    	Expression right1 = new Variable("X");
    	Expression left2 = new Number(1.0);
    	Expression right2 = new Variable("X");
    	Expression right3 = new Variable("x");
    	
    	Expression e1 = new Plus(left1, right1);
    	Expression e2 = new Plus(left2, right2);
    	Expression e3 = new Plus(left2, right3);
    	
    	Object o2 = e2;
    	
    	assertTrue("Expected Plus Expression e1 to equal Plus Expression e2", e1.equals(e2));
    	assertTrue("Expected Plus Expression e1 to equal Object o2", e1.equals(o2));
    	assertFalse("Expected Plus Expression e1 not equal Plus Expression e3", e1.equals(e3));
    }
    
    // covers Plus hashCode()
    @Test
    public void testPlusHashCode() {
    	Expression left = new Number(5);
    	Expression right = new Variable("x");
    	
    	Expression e1 = new Plus(left, right);
    	Expression e2 = new Plus(left, right);
    	
    	assertEquals("Expected hashCode of Plus e1 to equal hashCode of Plus e2", e1.hashCode(), e2.hashCode());
    }
    
    // covers Times toString()
    @Test
    public void testTimesToString() {
    	Expression left = new Number(5.0);
    	Expression right = new Variable("y");
    	Expression e = new Times(left, right);
    	String expected = "5.0 * y";
    	
    	assertEquals("Expected String \"5.0 * y\"", expected, e.toString());
    }
    
    // covers Times equals(Object), case-sensitivity and equality between Times and Plus Expressions
    @Test
    public void testTimesEquals() {
    	Expression left1 = new Variable("w");
    	Expression right1 = new Variable("y");
    	Expression left2 = new Variable("w");
    	Expression right2 = new Variable("y");
    	Expression right3 = new Variable("Y");
    	
    	Expression e1 = new Times(left1, right1);
    	Expression e2 = new Times(left2, right2);
    	Expression e3 = new Times(left2, right3);
    	Expression e4 = new Plus(left2, right2);
    	
    	Object o2 = e2;
    	
    	assertTrue("Expected Times Expression e1 to equal Times Expression e2", e1.equals(e2));
    	assertTrue("Expected Times Expression e1 to equal Object o2", e1.equals(o2));
    	assertFalse("Expected Times Expression e1 not to equal Times Expression e3", e1.equals(e3));
    	assertFalse("Expected Times Expression e1 not to equal Plus Expression e2", e1.equals(e4));
    }
    
    // covers Times hashCode()
    @Test
    public void testTimesHashCode() {
    	Expression left1 = new Variable("w");
    	Expression right1 = new Variable("y");
    	Expression left2 = new Variable("w");
    	Expression right2 = new Variable("y");
    	
    	Expression e1 = new Times(left1, right1);
    	Expression e2 = new Times(left2, right2);
    	Expression e3 = new Plus(left2, right2);
    	
    	Integer hashCode1 = e1.hashCode();
    	Integer hashCode2 = e3.hashCode();
    	
    	assertEquals("Expected hashCode of Times e1 to equal hashCode of Times e2", e1.hashCode(), e2.hashCode());
    	assertFalse("Expected hashCode of Times e1 to be different from hashCode of Plus e3", hashCode1.equals(hashCode2));
    }
    
}
