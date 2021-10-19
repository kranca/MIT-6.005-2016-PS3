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
		// and in order to reduce use of parenthesis:
		Expression dLeft = left.differentiate(variable);
		// if derivative of left side of the expression is instance of Times, Plus or Minus
		if (dLeft.isLeftAndRightExpression()) {
			// wrap expression around Parenthesis
			dLeft = new Parenthesis(dLeft);
		}
		else {
			// do nothing
		}
		
		// if derivative of right side of the expression is instance of Times, Plus or Minus
		Expression dRight = right.differentiate(variable);
		if (dRight.isLeftAndRightExpression()) {
			// wrap expression around Parenthesis
			dRight = new Parenthesis(dRight);
		}
		else {
			// do nothing
		}
		
		Expression newLeft = left;
		// if left side of the expression is instance of Times, Plus or Minus
		if (left.isLeftAndRightExpression()) {
			// wrap expression around Parenthesis
			newLeft = new Parenthesis(left);
		}
		else {
			// do nothing
		}
		
		Expression newRight = right;
		// if right side of the expression is instance of Times, Plus or Minus
		if (right.isLeftAndRightExpression()) {
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
				// return Number Expression on the left side of new Times Expression
				return new Times(simplifiedRight, simplifiedLeft);
			}
		}
		// case left and right are instance of variable
		else if (simplifiedLeft.isVariable() && simplifiedRight.isVariable()
				&& simplifiedLeft.getVariable().equals(simplifiedRight.getVariable())) {
			
			Double newFactor = simplifiedLeft.getExponent() + simplifiedRight.getExponent();
			
			return new Power(new Variable(simplifiedLeft.getVariable()), newFactor);
		}
		// case left and right are instance of times
		else if (simplifiedLeft.isTimes() || simplifiedRight.isTimes()) {
			Double newFactor = simplifiedLeft.getFactor() * simplifiedRight.getFactor();
			
			if (simplifiedLeft.getVariable().equals(simplifiedRight.getVariable())){
				return new Times(new Number(newFactor), new Power(new Variable(simplifiedLeft.getVariable()), simplifiedLeft.getExponent() + simplifiedRight.getExponent()));
			}
			else if (simplifiedLeft.isNumber()) {
				return new Times(new Number(newFactor), simplifiedRight);
			}
			else if (simplifiedRight.isNumber()) {
				return new Times(new Number(newFactor), simplifiedLeft);
			}
			else {
				return new Times(new Number(newFactor), new Times(new Power(new Variable(simplifiedLeft.getVariable()), simplifiedLeft.getExponent()), new Power(new Variable(simplifiedRight.getVariable()), simplifiedRight.getExponent())));
			}
		}
		else {
			return new Times(simplifiedLeft, simplifiedRight);
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
		// only Expression that returns true
		return true;
	}
	
	@Override
	public boolean isLeftAndRightExpression() {
		// left and right Expression construction
		return true;
	}

	@Override
	public Double getFactor() {
		// check if left or right are Number
		if (left.isNumber()) {
			return left.getFactor();
		}
		else if(right.isNumber()) {
			return right.getFactor();
		}
		//else return 1
		else {
			return 1.0;
		}
	}
	
	@Override
	public Double getExponent() {
		return 1.0;
	}

	@Override
	public String getVariable() {
		if (left.isVariable()) {
			return left.getVariable();
		}
		else if (right.isVariable()) {
			return right.getVariable();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

}
