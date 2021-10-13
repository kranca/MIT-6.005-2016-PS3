package expressivo;

/**
 * @author Raul
 * immutable data type that represents a right side parenthesis - close parenthesis
 */
public class Parenthesis implements Expression {
	/*
	 * Abstraction function:
	 * an Expression in parenthesis
	 * 
	 * Rep invariant:
	 * "( " + Expression + " )"
	 * 
	 * Safety from rep exposure:
	 * fields are private and final
	 */
	private final String op = "( ";
	private final String cp = " )";
	private final Expression center;
	
	private void checkRep() {
		assert op == "( ";
		assert cp == " )";
		assert center != null;
	}
	
	/**
	 * Surrounds an Expression with parenthesis
	 * @param expressionString
	 */
	public Parenthesis(Expression e) {
		this.center = e;
		checkRep();
	}
	
	@Override
	public String toString() {
		checkRep();
		return op + center.toString() + cp;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Parenthesis)) return false;
		Parenthesis thatOp = (Parenthesis) thatObject;
		checkRep();
		return this.center.equals(thatOp.center);
	}
	
	@Override
	public int hashCode() {
		checkRep();
		return (int) op.hashCode() + center.hashCode() + cp.hashCode();
	}

}
