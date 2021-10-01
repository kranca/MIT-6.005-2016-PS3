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

}
