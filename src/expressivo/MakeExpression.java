package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.PlusContext;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.TimesContext;

public class MakeExpression implements ExpressionListener {
	private Stack<Expression> stack = new Stack<>();
	
	public Expression getExpression() {
		return stack.get(0);
	}

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRoot(RootContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRoot(RootContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterPlus(PlusContext ctx) {
		// not needed

	}

	@Override
	public void exitPlus(PlusContext ctx) {
		List<ExpressionParser.TimesContext> addends = ctx.times();
		assert stack.size() >= addends.size();
		
		assert addends.size() > 0;
		Expression plus = stack.pop();
		
		for (int i = 1; i < addends.size(); ++i) {
			plus = new Plus(stack.pop(), plus);
		}
		
		stack.push(plus);

	}

	@Override
	public void enterTimes(TimesContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitTimes(TimesContext ctx) {
		List<ExpressionParser.PrimitiveContext> factors = ctx.primitive();
		assert stack.size() >= factors.size();
		
		assert factors.size() > 0;
		Expression times = stack.pop();
		
		for (int i = 1; i < factors.size(); ++i) {
			times = new Times(stack.pop(), times);
		}
		
		stack.push(times);

	}

	@Override
	public void enterPrimitive(PrimitiveContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPrimitive(PrimitiveContext ctx) {
		if (ctx.INTEGER() != null) {
			int n = Integer.valueOf(ctx.INTEGER().getText());
			Expression intNumber = new Number(n);
			stack.push(intNumber);
		}
		else if (ctx.DOUBLE() != null) {
			Double d = Double.valueOf(ctx.DOUBLE().getText());
			Expression doubleNumber = new Number(d);
			stack.push(doubleNumber);
		}
		else if (ctx.VARIABLE() != null) {
			String variableName = ctx.VARIABLE().getText();
			Expression variable = new Variable(variableName);
			stack.push(variable);
		}
		else {
			// do nothing
		}

	}

}
