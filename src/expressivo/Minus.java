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
	public Expression expand() {
		// expand recursively
		return new Minus(left.expand(), right.expand());
	}
	
	@Override
	public Expression reduce() {
		// reduce recursively left and right
		Expression rLeft = left.reduce();
		Expression rRight = right.reduce();
		
		if (rLeft.equals(rRight)) {
			return new Number(0);
		}
		else {
			return new Minus(rLeft, rRight);
		}
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
			if (result < 0.0) {
				return new Minus(new Number(0), new Number(result*(-1.0)));
			}
			else {
				return new Number(result);
			}
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
	public boolean isPlus() {
		// not instance of Plus
		return false;
	}

	@Override
	public boolean isMinus() {
		// only instance of Minus
		return true;
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
