package expressivo;

import java.util.Map;

/**
 * @author Raul
 * immutable data type that represents a Variable Expression raised to a numeric Power
 */
public class Power implements Expression {
	
	/*
	 * Abstraction function:
	 * Raises a Variable Expression to a numeric Power
	 * 
	 * Rep invariant:
	 * nonempty expressions
	 * power is positive Double
	 * 
	 * Safety from rep exposure:
	 * left and right fields are private and final
	 */
	
	private final Expression variable;
	private final Double power;
	
	private void checkRep() {
		assert variable != null;
		assert power >= 0;
	}

	/**
	 * multiplies two objects of type Expression together
	 * @param left - left Expression
	 * @param right - right Expression
	 */
	public Power(Expression variable, Double power) {
		this.variable = variable;
		this.power = power;
		checkRep();
	}
	
	@Override
	public String toString() {
		Expression expanded = variable;
		for (int i = 1; i < power; ++i) {
    		expanded = new Times(variable, expanded);
    	}
		return expanded.toString();
		//return variable + "" + power.toString();
	}
	
	@Override
	public boolean equals(Object thatObject) {
		if(!(thatObject instanceof Power)) return false;
		Power thatPower = (Power) thatObject;
		checkRep();
		return (this.variable.equals(thatPower.variable) && this.power.equals(thatPower.power));
	}
	
	@Override
	public int hashCode() {
		Double base = Double.valueOf(variable.getVariable().hashCode());
		Double result = Math.pow(base, power);
		return result.hashCode();
	}

	@Override
	public Expression differentiate(String variable) {
		// Power Expression only used to simplify Expressions
		throw new UnsupportedOperationException();
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// if variable name in environment return Number Expression of corresponding value
		if (environment.containsKey(variable.getVariable())) {
			Double base = environment.get(variable.getVariable());
			Double result = Math.pow(base, power);
			return new Number(result);
		}
		else {
			//else return new Power Expression with same name
			return new Power(variable, power);
		}
	}

	@Override
	public boolean isNumber() {
		// not instance of Number
		return false;
	}

	@Override
	public boolean isVariable() {
		// instance of Variable
		return true;
	}
	
	@Override
	public boolean isTimes() {
		// not instance of Times
		return false;
	}

	@Override
	public boolean isLeftAndRightExpression() {
		// no left and right Expression
		return false;
	}

	@Override
	public Double getFactor() {
		return 1.0;
	}
	
	@Override
	public Double getExponent() {
		return power;
	}

	@Override
	public String getVariable() {
		return variable.getVariable();
	}

}
