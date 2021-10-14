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
     * Minus: Variable + Number
     * Groupings with Parenthesis: Plus Times Minus
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
     * differentiate() partitions the inputs as follows:
     * Combination of Number, Variable, Plus, Minus, Times and Parenthesis
     * Linked Times Expressions
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
    
 // covers Minus toString()
    @Test
    public void testMinusToString() {
    	Expression left = new Variable("W");
    	Expression right = new Number(5);
    	
    	Expression e1 = new Minus(left, right);
    	String expected = "W - 5.0";
    	
    	assertEquals("Expected String \"W - y\"", expected, e1.toString());
    }
    // covers Minus equals()
    @Test
    public void testMinusEquals() {
    	Expression left1 = new Number(2.5);
    	Expression right1 = new Variable("Z");
    	Expression left2 = new Number(2.5);
    	Expression right2 = new Variable("Z");
    	Expression right3 = new Variable("z");
    	
    	Expression e1 = new Minus(left1, right1);
    	Expression e2 = new Minus(left2, right2);
    	Expression e3 = new Minus(left2, right3);
    	Expression e4 = new Plus(left2, right2);
    	Expression e5 = new Times(left2, right2);
    	
    	Object o2 = e2;
    	
    	assertTrue("Expected Minus Expression e1 to equal Minus Expression e2", e1.equals(e2));
    	assertTrue("Expected Minus Expression e1 to equal Object o2", e1.equals(o2));
    	assertFalse("Expected Minus Expression e1 not to equal Minus Expression e3", e1.equals(e3));
    	assertFalse("Expected Minus Expression e1 not to equal Plus Expression e2", e1.equals(e4));
    	assertFalse("Expected Minus Expression e1 not to equal Times Expression e2", e1.equals(e5));
    }
    
    // covers Minus hashCode()
    public void testMinusHashCode() {
    	Expression left1 = new Variable("w");
    	Expression right1 = new Variable("y");
    	Expression left2 = new Variable("w");
    	Expression right2 = new Variable("y");
    	
    	Expression e1 = new Minus(left1, right1);
    	Expression e2 = new Minus(left2, right2);
    	Expression e3 = new Plus(left2, right2);
    	Expression e4 = new Times(left2, right2);
    	
    	Integer hashCode1 = e1.hashCode();
    	Integer hashCode2 = e3.hashCode();
    	Integer hashCode3 = e4.hashCode();
    	
    	assertEquals("Expected hashCode of Minus e1 to equal hashCode of Minus e2", e1.hashCode(), e2.hashCode());
    	assertFalse("Expected hashCode of Minus e1 to be different from hashCode of Plus e3", hashCode1.equals(hashCode2));
    	assertFalse("Expected hashCode of Minus e1 to be different from hashCode of Times e4", hashCode1.equals(hashCode3));
    }
    
 // covers Parenthesis toString()
    @Test
    public void testParenthesisToString() {
    	Expression left = new Variable("W");
    	Expression right = new Number(5);
    	
    	Expression e1 = new Plus(left, right);
    	Expression e2 = new Minus(left, right);
    	Expression p1 = new Parenthesis(e1);
    	Expression p2 = new Parenthesis(e2);
    	Expression t = new Times(p1, p2);
    	String expected = "( W + 5.0 ) * ( W - 5.0 )";
    	
    	assertEquals("Expected String \"( W + 5.0) * ( W - 5.0)\"", expected, t.toString());
    }
    // covers Parenthesis equals()
    @Test
    public void testParenthesisEquals() {
    	Expression left1 = new Number(2.5);
    	Expression right1 = new Variable("Z");
    	Expression left2 = new Number(2.5);
    	Expression right2 = new Variable("Z");
    	Expression right3 = new Variable("z");
    	
    	Expression e1 = new Minus(left1, right1);
    	Expression e2 = new Minus(left2, right2);
    	Expression e3 = new Minus(left2, right3);
    	Expression e4 = new Plus(left2, right2);
    	Expression e5 = new Times(left2, right2);
    	
    	Expression p1 = new Parenthesis(e1);
    	Expression p2 = new Parenthesis(e2);
    	Expression p3 = new Parenthesis(e3);
    	Expression p4 = new Parenthesis(e4);
    	Expression p5 = new Parenthesis(e5);
    	
    	Object o2 = p2;
    	
    	assertTrue("Expected Parenthesis Expression p1 to equal Parenthesis Expression p2", p1.equals(p2));
    	assertTrue("Expected Parenthesis Expression p1 to equal Object o2", p1.equals(o2));
    	assertFalse("Expected Parenthesis Expression p1 not to equal Parenthesis Expression p3", p1.equals(p3));
    	assertFalse("Expected Parenthesis Expression e1 not to equal Parenthesis Expression p4", p1.equals(p4));
    	assertFalse("Expected Parenthesis Expression e1 not to equal Parenthesis Expression p5", p1.equals(p5));
    }
    
    // covers Parenthesis hashCode()
    public void testParenthesisHashCode() {
    	Expression v1 = new Variable("w");
    	Expression v2 = new Variable("w");
    	Expression n1 = new Number(7);
    	Expression n2 = new Variable("W");
    	
    	Expression p1 = new Parenthesis(v1);
    	Expression p2 = new Parenthesis(v2);
    	Expression p3 = new Parenthesis(n1);
    	Expression p4 = new Parenthesis(n2);
    	
    	Integer hashCode1 = p1.hashCode();
    	Integer hashCode2 = p3.hashCode();
    	Integer hashCode3 = p4.hashCode();
    	Integer hashCode4 = v1.hashCode();
    	
    	assertEquals("Expected hashCode of Parenthesis p1 to equal hashCode of Parenthesis p2", p1.hashCode(), p2.hashCode());
    	assertFalse("Expected hashCode of Parenthesis p1 to be different from hashCode of Parenthesis p3", hashCode1.equals(hashCode2));
    	assertFalse("Expected hashCode of Parenthesis p1 to be different from hashCode of Parenthesis p4", hashCode1.equals(hashCode3));
    	assertFalse("Expected hashCode of Parenthesis p1 to be different from hashCode of Variable v1", hashCode1.equals(hashCode4));
    }
    
    // covers differentiate()
    @Test
    public void testDifferentiate() {
    	Expression left = new Variable("W");
    	Expression right = new Number(5);
    	
    	Expression e1 = new Plus(left, right);
    	Expression e2 = new Minus(left, right);
    	Expression p1 = new Parenthesis(e1);
    	Expression p2 = new Parenthesis(e2);
    	Expression t = new Times(p1, p2);
    	
    	Expression d = t.differentiate("W");
    	String expected = "( W + 5.0 ) * ( 1.0 - 0.0 ) + ( W - 5.0 ) * ( 1.0 + 0.0 )";
    	
    	assertEquals("Expected String \"( W + 5.0 ) * ( 1.0 - 0.0 ) + ( W - 5.0 ) * ( 1.0 + 0.0 )\"", expected, d.toString());
    }
    
    @Test
    public void testDifferentiateLinkedTimes() {
    	Expression x1 = new Variable("x");
    	Expression x2 = new Variable("x");
    	Expression x3 = new Variable("x");
    	Expression x4 = new Variable("x");
    	Expression x1x2 = new Times(x2, x1);
    	Expression x3x4 = new Times(x4, x3);
    	Expression t = new Times(x3x4, x1x2);
    	
    	Expression d = t.differentiate("x");
    	String expected = "( x * x ) * ( x * 1.0 + x * 1.0 ) + ( x * x ) * ( x * 1.0 + x * 1.0 )";
    	
    	assertEquals("Expected String \"( x * x ) * ( x * 1.0 + x * 1.0 ) + ( x * x ) * ( x * 1.0 + x * 1.0 )\"", expected, d.toString());
    }
}
