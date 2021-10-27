package expressivo;

import java.util.Map;

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

	@Override
	public Expression differentiate(String variable) {
		// differentiate recursively
		Expression newCenter = center.differentiate(variable);
		return new Parenthesis(newCenter);
		
	}

	@Override
	public Expression expand() {
		// eliminate parenthesis and expand recursively
		Expression newCenter = center.expand();

		return newCenter;
	}
	
	@Override
	public Expression reduce() {
		// eliminate parenthesis and reduce recursively
		Expression newCenter = center.reduce();

		return newCenter;
	}

	@Override
	public Expression simplify(Map<String, Double> environment) {
		// simplify recursively and eliminate parenthesis if center is only Number or Variable
		Expression newCenter = center.simplify(environment);
		if (newCenter.isNumber() || newCenter.isVariable() || newCenter.isTimes()) {
			return newCenter;
		}
		else {
			return new Parenthesis(newCenter);
		}
	}

	@Override
	public boolean isNumber() {
		// not instance of Number
		return false;
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
		// not instance of Variable
		return false;
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
		throw new UnsupportedOperationException();
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
