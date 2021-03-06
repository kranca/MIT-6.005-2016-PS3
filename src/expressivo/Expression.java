/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.Map;

// uncomment to activate graphical tree visualization
//import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   Expression = Number(value:Integer, Double) + Variable(name:String)
	//				+ Plus(left:Expression, right: Expression)
	//				+ Times(left:Expression, right: Expression)
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        try {
        	// Same stream, lexer, tokens, parser and tree construction as in reading 18: parser generators
        	CharStream stream = new ANTLRInputStream(input);
        	
        	ExpressionLexer lexer = new ExpressionLexer(stream);
        	// error handling as in reading 18
        	lexer.reportErrorsAsExceptions();
        	
        	TokenStream tokens = new CommonTokenStream(lexer);
        	
        	ExpressionParser parser = new ExpressionParser(tokens);
        	// error handling as in reading 18
        	parser.reportErrorsAsExceptions();
        	
        	ParseTree tree = parser.root();
        	
        	// uncomment to activate graphical tree visualization
//        	Trees.inspect(tree, parser);
        	
        	ParseTreeWalker walker = new ParseTreeWalker();
        	
        	ExpressionListener listener = new MakeExpression();
        	
        	walker.walk(listener, tree);
        	
        	return ((MakeExpression) listener).getExpression();
        }
        catch (Exception e) {
        	throw new IllegalArgumentException("Expression was invalid");
        }
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    /**
     * Differentiates Expression with respect to a specified variable.
     * 
     * @param variable - specified variable for differentiation
     * @return differentiated expression
     */
    public Expression differentiate(String variable);
    
    /**
     * Expands Expression by multiplying factors and reducing parenthesis.
     * @return Expanded Expression without Parenthesis
     */
    public Expression expand();
    
    /**
     * Reduces Expression by merging together equal variables.
     * @return reduced Expression
     */
    public Expression reduce();
    
    /**
     * Simplifies an Expression using a given environment of variables and their numeric value
     * @param environment - Map: Key --> variable names, Value --> numeric value of variable
     * @return simplified Expression
     */
    public Expression simplify(Map<String,Double> environment);
    
    /**
     * Evaluates if Expression is instance of Number without using instanceof operation
     * @return true if Expression is instance of Number, false otherwise
     */
    public boolean isNumber();
    
    /**
     * Evaluates if Expression is instance of Variable without using instanceof operation
     * @return true if Expression is instance of Variable, false otherwise
     */
    public boolean isVariable();
    
    /**
     * Evaluates if Expression is instance of Times without using instanceof operation
     * @return true if Expression is instance of Times, false otherwise
     */
    public boolean isTimes();
    
    /**
     * Evaluates if Expression is instance of Plus without using instanceof operation
     * @return true if Expression is instance of Plus, false otherwise
     */
    public boolean isPlus();
    
    /**
     * Evaluates if Expression is instance of Minus without using instanceof operation
     * @return true if Expression is instance of Minus, false otherwise
     */
    public boolean isMinus();
    
    /**
     * Evaluates if Expression is instance of Parenthesis without using instanceof operation
     * @return true if Expression is instance of Parenthesis, false otherwise
     */
    public boolean isParenthesis();
    
    /**
     * Evaluates if this Variable Expression has the same Variable Expression as thatVariable.
     * Example: 2*x*x*x has the same Variable 5*x*x*x but not the same variable as 5*x*x or 5*y*y*y.
     * @param thatVariable Variable Expression to compare to
     * @return true if Variable within the Expression is the same, false otherwise
     */
    public boolean hasSameVariable(Expression thatVariable);
    
    /**
     * Gets numeric factor of an Expression. Example: 3*x gets factor 3.0
     * @return factor
     */
    public Double getFactor();
    
    /**
     * Gets numeric exponent of an Expression. Example: x*x*x*x gets exponent 4.0
     * @return exponent
     */
    public Double getExponent();
    
    /**
     * Gets name of Variable Expression or Variable component of Times Expression. Example: Expression "3.0 * x" or "x * x" gets String "x"
     * @return name
     */
    public String getVariable();
    
    /**
     * Gets left side Expression of Plus, Minus or Times. Example: "3 * x" gets Number Expression "3.0"
     * @return left side Expression
     */
    public Expression getLeft();
    
    /**
     * Gets right side Expression of Plus, Minus or Times. Example: 3.0 * x gets Variable Expression "x"
     * @return
     */
    public Expression getRight();
    
}
