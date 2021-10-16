package expressivo;

import java.util.Map;

/**
 * @author Raul
 * immutable data type that represents a multiplication of two objects of type Expression
 */
public class Times implements Expression {
	
	/*
	 * Abstraction function:
	 * multiplies two objects of type Expression together
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
	 * multiplies two objects of type Expression together
	 * @param left - left Expression
	 * @param right - right Expression
	 */
	public Times(Expression left, Expression right) {
		this.left = left;
		this.right = right;
		checkRep();
	}
	
	@Override
	public String toString() {
		String toString = left.toString() + " * " + right.toString();
		return toString;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if(!(thatObject instanceof Times)) return false;
		Times thatTimes = (Times) thatObject;
		checkRep();
		return (this.left.equals(thatTimes.left) && this.right.equals(thatTimes.right)
				|| this.left.equals(thatTimes.right) && this.right.equals(thatTimes.left));
	}
	
	@Override
	public int hashCode() {
		// return sum of hashCodes times 3 in order to have different hashCodes from Plus and Times Objects
		// created with the same left and right Objects
		return 3*(right.hashCode() + left.hashCode());
	}

	@Override
	public Expression differentiate(String variable) {
		// differentiate recursively left and right
		// special use of instanceof in order to reduce use of parenthesis
		Expression dLeft = left.differentiate(variable);
		// if derivative of left side of the expression is instance of Times, Plus or Minus
		if (dLeft instanceof Times || dLeft instanceof Plus || dLeft instanceof Minus) {
			// wrap expression around Parenthesis
			dLeft = new Parenthesis(dLeft);
		}
		else {
			// do nothing
		}
		
		// if derivative of right side of the expression is instance of Times, Plus or Minus
		Expression dRight = right.differentiate(variable);
		if (dRight instanceof Times || dRight instanceof Plus || dRight instanceof Minus) {
			// wrap expression around Parenthesis
			dRight = new Parenthesis(dRight);
		}
		else {
			// do nothing
		}
		
		Expression newLeft = left;
		// if left side of the expression is instance of Times, Plus or Minus
		if (left instanceof Times || left instanceof Plus || left instanceof Minus) {
			// wrap expression around Parenthesis
			newLeft = new Parenthesis(left);
		}
		else {
			// do nothing
		}
		
		Expression newRight = right;
		// if right side of the expression is instance of Times, Plus or Minus
		if (right instanceof Times || right instanceof Plus || right instanceof Minus) {
			// wrap expression around Parenthesis
			newRight = new Parenthesis(right);
		}
		else {
			// do nothing
		}
		
		Expression finalLeft = new Times(newLeft, dRight);
		Expression finalRight = new Times(newRight, dLeft);
		
		return new Plus(finalLeft, finalRight);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// update expression by simplifying recursively
		Expression simplifiedLeft = left.simplify(environment);
		Expression simplifiedRight = right.simplify(environment);
		
		// case left and right are numbers
		if (simplifiedLeft.isNumber() && simplifiedRight.isNumber()) {
			Double valueLeft = Double.valueOf(simplifiedLeft.toString());
			Double valueRight = Double.valueOf(simplifiedRight.toString());
			Double result = valueLeft * valueRight;
			return new Number(result);
		}
		// case left is Number
		else if (simplifiedLeft.isNumber() && !simplifiedRight.isNumber()) {
			// case Number value is 1, reduce to simplified right side
			if (Double.valueOf(simplifiedLeft.toString()).equals(1.0)) {
				return simplifiedRight;
			}
			// case Number value is 0, reduce to Number with value zero
			else if (Double.valueOf(simplifiedLeft.toString()).equals(0.0)) {
				return new Number(0);
			}
			else {
				return new Times(simplifiedLeft, simplifiedRight);
			}
		}
		// case right is Number
		else if (!simplifiedLeft.isNumber() && simplifiedRight.isNumber()) {
			// case Number value is 1, reduce to simplified right side
			if (Double.valueOf(simplifiedRight.toString()).equals(1.0)) {
				return simplifiedLeft;
			}
			// case Number value is 0, reduce to Number with value zero
			else if (Double.valueOf(simplifiedRight.toString()).equals(0.0)) {
				return new Number(0);
			}
			else {
				return new Times(simplifiedLeft, simplifiedRight);
			}
		}
		// case left and right are instance of variable
//		else if (simplifiedLeft.isVariable() && simplifiedRight.isVariable()) {
//			if (simplifiedLeft.isNumber())
//		}
		else {
			return new Times(simplifiedLeft, simplifiedRight);
		}
	}

	@Override
	public boolean isNumber() {
		// not of Number instance
		return false;
	}
	
	@Override
	public boolean isVariable() {
		// only instance of variable if left or right are Variable and opposite side is Number or if both are variable
		if (left.isNumber() && right.isVariable() || left.isVariable() && right.isNumber() || left.isVariable() && right.isVariable()) {
			return true;
		}
		else {
			return false;
		}
	}

}
