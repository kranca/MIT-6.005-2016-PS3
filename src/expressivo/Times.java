package expressivo;

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
		return (this.left.equals(thatTimes.left) && this.right.equals(thatTimes.right));
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

}
