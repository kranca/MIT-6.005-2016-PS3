package expressivo;

import java.util.Map;

/**
 * @author Raul
 * immutable data type that represents a addition of two objects of type Expression
 */
public class Minus implements Expression {
	/*
	 * Abstraction function:
	 * deducts one object of type Expression from another
	 * 
	 * Rep invariant:
	 * nonempty expressions
	 * 
	 * Safety from rep exposure:
	 * left and right fields are private and final
	 */

	private final Expression left;
	private final Expression right;
	
	private void checkRep() {
		assert left != null;
		assert right != null;
	}
	
	/**
	 * creates Plus object which adds two expressions together
	 * @param left - left Expression
	 * @param right - right Expression
	 */
	public Minus(Expression left, Expression right) {
		this.left = left;
		this.right = right;
		checkRep();
	}
	
	@Override
	public String toString() {
		String toString = left.toString() + " - " + right.toString();
		return toString;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Minus)) return false;
		Minus thatMinus = (Minus) thatObject;
		checkRep();
		return (this.left.equals(thatMinus.left) && this.right.equals(thatMinus.right));
	}
	
	@Override
	public int hashCode() {
		return (left.hashCode() + right.hashCode())*3;
	}

	@Override
	public Expression differentiate(String variable) {
		// differentiate recursively left and right side
		Expression newLeft = left.differentiate(variable);
		Expression newRight = right.differentiate(variable);
		return new Minus(newLeft, newRight);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// update expression by simplifying recursively
		Expression simplifiedLeft = left.simplify(environment);
		Expression simplifiedRight = right.simplify(environment);
		
		if (simplifiedLeft.isNumber() && simplifiedRight.isNumber()) {
			Double valueLeft = Double.valueOf(simplifiedLeft.toString());
			Double valueRight = Double.valueOf(simplifiedRight.toString());
			Double result = valueLeft - valueRight;
			return new Number(result);
		}
		else if(simplifiedLeft.equals(simplifiedRight)) {
			return new Number(0);
		}
		else {
			return new Minus(simplifiedLeft, simplifiedRight);
		}
	}

	@Override
	public boolean isNumber() {
		// not instance of Number
		return false;
	}
	
	@Override
	public boolean isVariable() {
		// not instance of Variable
		return false;
	}
	
	@Override
	public boolean isTimes() {
		// not instance of Times
		return false;
	}
	
	@Override
	public boolean isLeftAndRightExpression() {
		// left and right Expression construction
		return true;
	}

	@Override
	public Double getFactor() {
		return 1.0;
	}
	
	@Override
	public Double getExponent() {
		return 1.0;
	}

	@Override
	public String getVariable() {
		throw new UnsupportedOperationException();
	}

}
