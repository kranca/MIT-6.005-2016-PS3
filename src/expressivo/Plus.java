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
	public Expression expand() {
		// expand recursively left and right
		Expression newLeft = left.expand();
		Expression newRight = right.expand();
		
		return new Plus(newLeft, newRight);
	}
	
	@Override
	public Expression reduce() {
		// reduce recursively left and right
		Expression rLeft = left.reduce();
		Expression rRight = right.reduce();
		
		//case left and right are same Variable
		if (rLeft.isVariable() && rRight.isVariable() && rLeft.getVariable().equals(rRight.getVariable())) {
			final Double newFactor = rLeft.getFactor() + rRight.getFactor();
			return new Times(new Number(newFactor), rLeft).reduce();
		}
		// case left is Times and right is Variable
		else if (rLeft.isTimes() && rRight.isVariable() && rLeft.hasSameVariable(rRight)) {
			if (rLeft.getLeft().isVariable() || rLeft.getRight().isVariable()) {
				final Double newFactor = rLeft.getFactor() + rRight.getFactor();
				return new Times(new Number(newFactor), rRight).reduce();
			}
			else {
				return new Plus(rLeft, rRight);
			}
		}
		// case left is Variable and right is Times
		else if (rLeft.isVariable() && rRight.isTimes() && rLeft.hasSameVariable(rRight)) {
			if (rRight.getLeft().isVariable() || rRight.getRight().isVariable()) {
				final Double newFactor = rLeft.getFactor() + rRight.getFactor();
				return new Times(new Number(newFactor), rLeft).reduce();
			}
			else {
				return new Plus(rLeft, rRight);
			}
		}
		// case left and right are Times and equal
		else if (rLeft.isTimes() && rRight.isTimes() && rLeft.hasSameVariable(rRight)) {
			final Double newFactor = rLeft.getFactor() + rRight.getFactor();
			if (!rLeft.getLeft().isNumber()) {
				return new Times(new Number(newFactor), rLeft.getLeft()).reduce();
			}
			else if (!rLeft.getRight().isNumber()) {
				return new Times(new Number(newFactor), rLeft.getRight()).reduce();
			}
			else {
				return new Plus(rLeft, rRight);
			}
		}
		// case left is Number 0
		else if (rLeft.isNumber() && rLeft.getFactor().equals(0.0)) {
			return rRight;
		}
		else if (rRight.isNumber() && rRight.getFactor().equals(0.0)) {
			return rLeft;
		}
		else {
			return new Plus(rLeft, rRight);
		}
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
	public boolean isPlus() {
		// only instance of Plus
		return true;
	}

	@Override
	public boolean isMinus() {
		// not instance of Minus
		return false;
	}
	
	@Override
	public boolean hasSameVariable(Expression thatVariable) {
		// not instance of Variable
		return false;
	}
	
	@Override
	public boolean isParenthesis() {
		// not instance of Parenthesis
		return false;
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

	@Override
	public Expression getLeft() {
		// returns copies of left Expression
		if (left.isNumber()) {
			return new Number(left.getFactor());
		}
		else if (left.isVariable()) {
			return new Power(new Variable(left.getVariable()), left.getExponent());
		}
		else if (left.isPlus()) {
			return new Plus(left.getLeft(), left.getRight());
		}
		else if (left.isMinus()) {
			return new Minus(left.getLeft(), left.getRight());
		}
		else if (left.isTimes()) {
			return new Times(left.getLeft(), left.getRight());
		}
		else if (left.isParenthesis()) {
			return new Parenthesis(left);
		}
		else {
			throw new UnsupportedOperationException("Expression type not implemented");
		}
	}

	@Override
	public Expression getRight() {
		// returns copies of right Expression
		if (right.isNumber()) {
			return new Number(right.getFactor());
		}
		else if (right.isVariable()) {
			return new Power(new Variable(right.getVariable()), right.getExponent());
		}
		else if (right.isPlus()) {
			return new Plus(right.getLeft(), right.getRight());
		}
		else if (right.isMinus()) {
			return new Minus(right.getLeft(), right.getRight());
		}
		else if (right.isTimes()) {
			return new Times(right.getLeft(), right.getRight());
		}
		else if (right.isParenthesis()) {
			return new Parenthesis(right);
		}
		else {
			throw new UnsupportedOperationException("Expression type not implemented");
		}
	}

}
