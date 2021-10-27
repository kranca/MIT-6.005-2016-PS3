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
		if (dLeft.isPlus() || dLeft.isMinus()) {
			// wrap expression around Parenthesis
			dLeft = new Parenthesis(dLeft);
		}
		else {
			// do nothing
		}
		
		// if derivative of right side of the expression is instance of Times, Plus or Minus
		Expression dRight = right.differentiate(variable);
		if (dRight.isPlus() || dRight.isMinus()) {
			// wrap expression around Parenthesis
			dRight = new Parenthesis(dRight);
		}
		else {
			// do nothing
		}
		
		Expression newLeft = left;
		// if left side of the expression is instance of Times, Plus or Minus
		if (left.isPlus() || left.isMinus()) {
			// wrap expression around Parenthesis
			newLeft = new Parenthesis(left);
		}
		else {
			// do nothing
		}
		
		Expression newRight = right;
		// if right side of the expression is instance of Times, Plus or Minus
		if (right.isPlus() || right.isMinus()) {
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
	public Expression expand() {
		// left side:
		Expression eLeft = left.expand();
		
		// right side:
		Expression eRight = right.expand();
		
		// start empty expanded Expression
		Expression expanded;
		
		// Plus and Minus combinations:
		// Plus in left and right sides
		if (eLeft.isPlus() && eRight.isPlus()) {
			Expression LeftLeftTimes = new Times(eLeft.getLeft(), eRight.getLeft()).expand();
			Expression LeftRightTimes = new Times(eLeft.getLeft(), eRight.getRight()).expand();
			Expression RightLeftTimes = new Times(eLeft.getRight(), eRight.getLeft()).expand();
			Expression RightRightTimes = new Times(eLeft.getRight(), eRight.getRight()).expand();
			expanded = new Plus(LeftLeftTimes, new Plus(LeftRightTimes, new Plus(RightLeftTimes, RightRightTimes)));
		}
		// Plus in left side, Minus in right side
		else if (eLeft.isPlus() && eRight.isMinus()) {
			Expression LeftLeftTimes = new Times(eLeft.getLeft(), eRight.getLeft()).expand();
			Expression LeftRightTimes = new Times(eLeft.getLeft(), eRight.getRight()).expand();
			Expression RightLeftTimes = new Times(eLeft.getRight(), eRight.getLeft()).expand();
			Expression RightRightTimes = new Times(eLeft.getRight(), eRight.getRight()).expand();
			expanded = new Minus(LeftLeftTimes, new Plus(LeftRightTimes, new Minus(RightLeftTimes, RightRightTimes)));
		}
		// Minus in left and right sides
		else if (eLeft.isMinus() && eRight.isMinus()) {
			Expression LeftLeftTimes = new Times(eLeft.getLeft(), eRight.getLeft()).expand();
			Expression LeftRightTimes = new Times(eLeft.getLeft(), eRight.getRight()).expand();
			Expression RightLeftTimes = new Times(eLeft.getRight(), eRight.getLeft()).expand();
			Expression RightRightTimes = new Times(eLeft.getRight(), eRight.getRight()).expand();
			expanded = new Minus(LeftLeftTimes, new Minus(LeftRightTimes, new Plus(RightLeftTimes, RightRightTimes)));
		}
		// Minus in left side, Plus in right side
		else if (eLeft.isMinus() && eRight.isPlus()) {
			Expression LeftLeftTimes = new Times(eLeft.getLeft(), eRight.getLeft()).expand();
			Expression LeftRightTimes = new Times(eLeft.getLeft(), eRight.getRight()).expand();
			Expression RightLeftTimes = new Times(eLeft.getRight(), eRight.getLeft()).expand();
			Expression RightRightTimes = new Times(eLeft.getRight(), eRight.getRight()).expand();
			expanded = new Plus(LeftLeftTimes, new Minus(LeftRightTimes, new Minus(RightLeftTimes, RightRightTimes)));
		}
		// Plus and other Expression type combinations
		// Plus in left side combinations
		else if (eLeft.isPlus() && (eRight.isParenthesis() || eRight.isTimes() || eRight.isVariable() || eRight.isNumber())) {
			Expression LeftOtherTimes = new Times(eLeft.getLeft(), eRight).expand();
			Expression RightOtherTimes = new Times(eLeft.getRight(), eRight).expand();
			expanded = new Plus(LeftOtherTimes, RightOtherTimes);
		}
		// Plus in right side combinations
		else if (eRight.isPlus() && (eLeft.isParenthesis() || eLeft.isTimes() || eLeft.isVariable() || eLeft.isNumber())) {
			Expression LeftOtherTimes = new Times(eRight.getLeft(), eLeft).expand();
			Expression RightOtherTimes = new Times(eRight.getRight(), eLeft).expand();
			expanded = new Plus(LeftOtherTimes, RightOtherTimes);
		}
		// Minus and other Expression type combinations
		// Minus in left side combinations
		else if (eLeft.isMinus() && (eRight.isParenthesis() || eRight.isTimes() || eRight.isVariable() || eRight.isNumber())) {
			Expression LeftOtherTimes = new Times(eLeft.getLeft(), eRight).expand();
			Expression RightOtherTimes = new Times(eLeft.getRight(), eRight).expand();
			expanded = new Minus(LeftOtherTimes, RightOtherTimes);
		}
		// Minus in right side combinations
		else if (eRight.isMinus() && (eLeft.isParenthesis() || eLeft.isTimes() || eLeft.isVariable() || eLeft.isNumber())) {
			Expression LeftOtherTimes = new Times(eRight.getLeft(), eLeft).expand();
			Expression RightOtherTimes = new Times(eRight.getRight(), eLeft).expand();
			expanded = new Minus(LeftOtherTimes, RightOtherTimes);
		}
		// remaining combinations
		else {
			expanded = new Times(eLeft, eRight);
		}
		
		return expanded;
	}
	
	@Override
	public Expression reduce() {
		// reduce recursively left and right
		Expression rLeft = left.reduce();
		Expression rRight = right.reduce();
		
		// case left and right are same Variable
		if (rLeft.isVariable() && rRight.isVariable() && rLeft.getVariable().equals(rRight.getVariable())) {
			final Double newPower = rLeft.getExponent() + rRight.getExponent();
			Expression name = new Variable(rLeft.getVariable());
			return new Power(name, newPower);
		}
		// case left is Number and right is Variable
		else if (rLeft.isNumber() && rRight.isVariable()) {
			// case Number is 1.0, reduce to right side
			if (rLeft.getFactor().equals(1.0)) {
				return rRight;
			}
			// case Number is 0.0, reduce to Number 0
			if (rLeft.getFactor().equals(0.0)) {
				return new Number(0);
			}
			else {
				return new Times(rLeft, rRight);
			}
		}
		// case left is Variable and right is Number
		else if (rLeft.isVariable() && rRight.isNumber()) {
			// case Number is 1.0, reduce to left side
			if (rRight.getFactor().equals(1.0)) {
				return rLeft;
			}
			// case Number is 0.0, reduce to Number 0
			if (rRight.getFactor().equals(0.0)) {
				return new Number(0);
			}
			else {
				return new Times(rLeft, rRight);
			}
		}
		else {
			return new Times(rLeft, rRight);
		}
	}
	
	@Override
	public Expression simplify(Map<String, Double> environment) {
		// update expression by simplifying recursively
		Expression simplifiedLeft = left.simplify(environment);
		Expression simplifiedRight = right.simplify(environment);
		
		// case left and right are numbers
		if (simplifiedLeft.isNumber() && simplifiedRight.isNumber()) {
			final Double valueLeft = Double.valueOf(simplifiedLeft.toString());
			final Double valueRight = Double.valueOf(simplifiedRight.toString());
			final Double result = valueLeft * valueRight;
			return new Number(result);
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
	public boolean isPlus() {
		// not instance of Plus
		return false;
	}

	@Override
	public boolean isMinus() {
		// not instance of Minus
		return false;
	}
	
	@Override
	public boolean hasSameVariable(Expression thatVariable) {
		// evaluate recursively
		if (this.getVariable().equals(thatVariable.getVariable()) && this.getExponent().equals(thatVariable.getExponent())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean isParenthesis() {
		// not instance of Parenthesis
		return false;
	}

	@Override
	public Double getFactor() {
		// check if left or right are Number
		if (left.isNumber() && !right.isNumber()) {
			return left.getFactor();
		}
		else if(!left.isNumber() && right.isNumber()) {
			return right.getFactor();
		}
		else if (left.isNumber() && right.isNumber()) {
			return left.getFactor()*right.getFactor();
		}
		//else return 1
		else {
			return 1.0;
		}
	}
	
	@Override
	public Double getExponent() {
		// check if left or right are Variable
		if (left.isVariable() || right.isVariable()) {
			// return exponent recursively
			return left.getExponent() * right.getExponent();
		}
		else {
			return 1.0;
		}
	}

	@Override
	public String getVariable() {
		// check if left or right are Variable
		if (left.isVariable() && right.isVariable() && !left.getVariable().equals(right.getVariable())) {
			return left.getVariable() + right.getVariable();
		}
		else if (left.isVariable()) {
			return left.getVariable();
		}
		else if (right.isVariable()) {
			return right.getVariable();
		}
		else {
			throw new UnsupportedOperationException();
		}
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
