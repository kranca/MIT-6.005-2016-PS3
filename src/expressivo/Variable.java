package expressivo;

/**
 * @author Raul
 * immutable data type that represents a Variable
 */
public class Variable implements Expression {

	/*
	 * Abstraction function:
	 * a case-sensitive nonempty sequence of letters
	 * 
	 * Rep invariant:
	 * nonempty sequence of letters
	 * no spaces between letters
	 * 
	 * Safety from rep exposure:
	 * value field is private and final, String is of immutable type
	 */
	
	private final String name;
	
	private void checkRep() {
		assert name != "";
		String regex = "[a-zA-Z]+";
		assert name.matches(regex);
	}
	
	/**
	 * Creates a Variable object
	 * @param name - nonempty sequence of letters, case-sensitive
	 */
	public Variable(String name) {
		this.name = name;
		checkRep();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Variable)) return false;
		Variable thatVariable = (Variable) thatObject;
		checkRep();
		return this.name.equals(thatVariable.name);
	}
	
	@Override
	public int hashCode() {
		return (int) name.hashCode();
	}

	@Override
	public Expression differentiate(String variable) {
		// compare variable for differentiation with Variable (Expression) name
		if (name.equals(variable)) {
			return new Number(1);
		}
		else {
			return new Number(0);
		}
	}

}
