package expressivo;

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
		return (this.left.equals(thatPlus.left) && this.right.equals(thatPlus.right));
	}
	
	@Override
	public int hashCode() {
		return (left.hashCode() + right.hashCode());
	}

}
