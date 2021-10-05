package expressivo;

/**
 * @author Raul
 * immutable data type that represents a right side parenthesis - close parenthesis
 */
public class Cp implements Expression {
	/*
	 * Abstraction function:
	 * a right side parenthesis
	 * 
	 * Rep invariant:
	 * string value ")"
	 * 
	 * Safety from rep exposure:
	 * value field is private and final, string is of immutable type
	 */
	private final String cp = ")";
	
	private void checkRep() {
		assert cp == ")";
	}
	
	/**
	 * Creates an open parenthesis Expression
	 */
	public Cp() {
		checkRep();
	}
	
	@Override
	public String toString() {
		checkRep();
		return cp;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Cp)) return false;
		Cp thatCp = (Cp) thatObject;
		checkRep();
		return this.cp.equals(thatCp.cp);
	}
	
	@Override
	public int hashCode() {
		checkRep();
		return (int) cp.hashCode();
	}

}
