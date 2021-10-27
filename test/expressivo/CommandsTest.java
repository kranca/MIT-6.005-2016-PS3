/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // tests for Commands.differentiate() and Commands.simplify()
    
    // covers differentiate()
    @Test
    public void testDifferentiate() {
    	String expressionString = "(W+5)*(W-5)";
    	String differentiatedExpression = Commands.differentiate(expressionString, "W");
    	
    	String expected = "( W + 5.0 ) * ( 1.0 - 0.0 ) + ( W - 5.0 ) * ( 1.0 + 0.0 )";
    	
    	assertEquals("Expected String \"( W + 5.0 ) * ( 1.0 - 0.0 ) + ( W - 5.0 ) * ( 1.0 + 0.0 )\"", expected, differentiatedExpression);
    }
    
    @Test
    public void testDifferentiateLinkedTimes() {
    	String expressionString = "x*x*x*x";
    	String differentiatedExpression = Commands.differentiate(expressionString, "x");
    	
    	String expected = "x * ( x * ( x * 1.0 + x * 1.0 ) + x * x * 1.0 ) + x * x * x * 1.0";
    	
    	assertEquals("Expected String \"x * ( x * ( x * 1.0 + x * 1.0 ) + x * x * 1.0 ) + x * x * x * 1.0\"", expected, differentiatedExpression);
    }
    
    // covers simplify()
    @Test
    public void testSimplifyEmptyEnvironment() {
    	Map<String,Double> environment = new HashMap<>();
    	String expressionString = "(W+5)*(W-5)";
    	String differentiatedExpression = Commands.differentiate(expressionString, "W");
    	String simplifiedExpression = Commands.simplify(differentiatedExpression, environment);
    	
    	String expected = "W - 5.0 + W + 0.0 - 5.0";
    	
    	assertEquals("Expected String \"W - 5.0 + W + 0.0 - 5.0\"", expected, simplifiedExpression);
    }
    
    @Test
    public void testSimplifyWithEnvironment() {
    	Map<String,Double> environment = new HashMap<>();
    	environment.put("W", 5.0);
    	String expressionString = "(W+5)*(W-5)";
    	String simplifiedExpression = Commands.simplify(expressionString, environment);
    	
    	String expected = "0.0";
    	
    	assertEquals("Expected String \"0.0\"", expected, simplifiedExpression);
    }
    
    @Test
    public void testSimplifyEquation() {
    	Map<String,Double> environment = new HashMap<>();
    	
    	String expressionString = "5*x*x*x*x-3*x*x*x";
    	String differentiatedExpression = Commands.differentiate(expressionString, "x");
    	String simplifiedExpression = Commands.simplify(differentiatedExpression, environment);
    	
    	String expected = "20.0 * x * x * x + 0.0 - 9.0 * x * x";
    	
    	assertEquals("Expected String \"20.0 * x * x * x + 0.0 - 9.0 * x * x\"", expected, simplifiedExpression);
    }
    

}
