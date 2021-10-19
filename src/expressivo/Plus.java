package expressivo;

import java.util.Map;

/**
 * @author Raul
 * immutable data type that represents a addition of two objects of type Expression
 */
public class Plus implements Expression {
	
	/*
	 * Abstraction function:
	 * adds two objects of type Expression together
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
	public Plus(Expression left, Expression right) {
		this.left = left;
		this.right = right;
		checkRep();
	}
	
	@Override
	public String toString() {
		String toString = left.toString() + " + " + right.toString();
		return toString;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Plus)) return false;
		Plus thatPlus = (Plus) thatObject;
		checkRep();
		return (this.left.equals(thatPlus.left) && this.right.equals(thatPlus.right)
				|| this.left.equals(thatPlus.right) && this.right.equals(thatPlus.left));
	}
	
	@Override
	public int hashCode() {
		return (left.hashCode() + right.hashCode());
	}

	@Override
	public Expression differentiate(String variable) {
		// differentiate recursively left and right side
		Expression newLeft = left.differentiate(variable);
		Expression newRight = right.differentiate(variable);
		
		return new Plus(newLeft, newRight);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// update expression by simplifying recursively
		Expression simplifiedLeft = left.simplify(environment);
		Expression simplifiedRight = right.simplify(environment);
		
		// case left and right are instance of Number
		if (simplifiedLeft.isNumber() && simplifiedRight.isNumber()) {
			Double valueLeft = Double.valueOf(simplifiedLeft.toString());
			Double valueRight = Double.valueOf(simplifiedRight.toString());
			Double result = valueLeft + valueRight;
			return new Number(result);
		}
		// case left and right are instance of Variable
		else if (simplifiedLeft.isVariable() && simplifiedRight.isVariable()) {
			// and left equals right
			if (simplifiedLeft.toString().equals(simplifiedRight.toString())) {
				return new Times(new Number(2), simplifiedLeft);
			}
			else {
				return new Plus(simplifiedLeft, simplifiedRight);
			}
		}
		// case left or right are instance of Times
		else if(simplifiedLeft.isTimes() || simplifiedRight.isTimes()) {
			if (simplifiedLeft.toString().equals(simplifiedRight.toString())) {
				return new Times(new Number(2), simplifiedLeft);
			}
			else if (simplifiedLeft.isTimes() && simplifiedRight.isVariable()) {
				Expression newFactor = new Number(simplifiedLeft.getFactor() + simplifiedRight.getFactor());
				Expression newVariable = new Variable(simplifiedRight.getVariable());
				return new Times(newFactor, new Power(newVariable, simplifiedLeft.getExponent()));
			}
			else if (simplifiedLeft.isVariable() && simplifiedRight.isTimes()) {
				Expression newFactor = new Number(simplifiedLeft.getFactor() + simplifiedRight.getFactor());
				Expression newVariable = new Variable(simplifiedLeft.getVariable());
				return new Times(newFactor, new Power(newVariable, simplifiedLeft.getExponent()));
			}
			else {
				return new Plus(simplifiedLeft, simplifiedRight);
			}
		}
		else if (simplifiedLeft.getFactor().equals(0.0)) {
			return simplifiedRight;
		}
		else if (simplifiedRight.getFactor().equals(0.0)) {
			return simplifiedLeft;
		}
		else {
			return new Plus(simplifiedLeft, simplifiedRight);
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
