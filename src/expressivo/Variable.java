package expressivo;

import java.util.Map;

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

	@Override
	public Expression expand() {
		// no further expansion possible
		return new Variable(name);
	}
	
	@Override
	public Expression reduce() {
		// no further reduction possible
		return new Variable(name);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// if variable name in environment return Number Expression of corresponding value
		if (environment.containsKey(name)) {
			return new Number(environment.get(name));
		}
		else {
			//else return new Variable Expression with same name
			return new Variable(name);
		}
	}

	@Override
	public boolean isNumber() {
		// not instance of Number
		return false;
	}
	
	@Override
	public boolean isVariable() {
		// only Expression that returns true
		return true;
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
		// not instance of Minus
		return false;
	}
	
	@Override
	public boolean hasSameVariable(Expression thatVariable) {
		// 
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
		return 1.0;
	}
	
	@Override
	public Double getExponent() {
		return 1.0;
	}

	@Override
	public String getVariable() {
		return name;
	}

	@Override
	public Expression getLeft() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Expression getRight() {
		throw new UnsupportedOperationException();
	}

}
