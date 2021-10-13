package expressivo;

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

}
