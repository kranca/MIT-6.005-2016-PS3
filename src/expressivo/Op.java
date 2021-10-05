package expressivo;

/**
 * @author Raul
 * immutable data type that represents a left side parenthesis - open parenthesis
 */
public class Op implements Expression {
	/*
	 * Abstraction function:
	 * a left side parenthesis
	 * 
	 * Rep invariant:
	 * string value "("
	 * 
	 * Safety from rep exposure:
	 * value field is private and final, string is of immutable type
	 */
	private final String op = "(";
	
	private void checkRep() {
		assert op == "(";
	}
	
	/**
	 * Creates an open parenthesis Expression
	 */
	public Op() {
		checkRep();
	}
	
	@Override
	public String toString() {
		checkRep();
		return op;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Op)) return false;
		Op thatOp = (Op) thatObject;
		checkRep();
		return this.op.equals(thatOp.op);
	}
	
	@Override
	public int hashCode() {
		checkRep();
		return (int) op.hashCode();
	}

}
