package expressivo;

import java.util.Map;

/**
 * @author Raul
 * immutable data type that represents a positive number or zero
 */
public class Number implements Expression {
	/*
	 * Abstraction function:
	 * a nonnegative number
	 * 
	 * Rep invariant:
	 * value>=0
	 * 
	 * Safety from rep exposure:
	 * value field is private and final, double is of immutable type
	 */
	private final Double value;
	
	private void checkRep() {
		assert value >= 0;
	}
	
	/**
	 * Number object constructor, uses Double input
	 * @param value - Double nonnegative value of number
	 */
	public Number(Double value) {
		this.value = value;
		checkRep();
	}
	
	/**
	 * Number object constructor, uses Integer input
	 * @param value - Integer nonnegative value of number
	 */
	public Number(Integer value) {
		this.value = value.doubleValue();
		checkRep();
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof Number)) return false;
		Number thatNumber = (Number) thatObject;
		return this.value.equals(thatNumber.value);
	}
	
	@Override
	public int hashCode() {
		return (int) value.hashCode();
	}

	@Override
	public Expression differentiate(String variable) {
		// single number differentiated with respect to a variable is 0
		return new Number(0);
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// return new Expression with same value
		return new Number(value);
	}

	@Override
	public boolean isNumber() {
		// only Expression that returns true
		return true;
	}
	
	@Override
	public boolean isVariable() {
		// not instance of Variable
		return false;
	}
	
	@Override
	public boolean isTimes() {
		// not instance of Times
		return false;
	}
	
	@Override
	public boolean isLeftAndRightExpression() {
		// no left and right Expression construction
		return false;
	}

	@Override
	public Double getFactor() {
		// Since Number has no Variable component, returns only its value
		return value;
	}
	
	@Override
	public Double getExponent() {
		return 1.0;
	}

	@Override
	public String getVariable() {
		throw new UnsupportedOperationException();
	}

}
