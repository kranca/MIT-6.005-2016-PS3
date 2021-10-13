package expressivo;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.AdditiveContext;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.MinusContext;
import expressivo.parser.ExpressionParser.MultiplicativeContext;
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
	public void enterExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitExpression(ExpressionContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterAdditive(AdditiveContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitAdditive(AdditiveContext ctx) {
		List<ExpressionParser.MultiplicativeContext> addends = ctx.multiplicative();
		assert stack.size() >= addends.size();
		
		assert addends.size() > 0;
		Expression plus = stack.pop();
		
		String test = ctx.getText();
		test.replaceAll("[a-zA-Z0-9]", "");
		
		System.out.println(test);
		for (int i = 1; i < addends.size(); ++i) {
			
			plus = new Plus(stack.pop(), plus);
		}
		
		stack.push(plus);

	}

	@Override
	public void enterPlus(PlusContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPlus(PlusContext ctx) {
		//System.out.println();

	}

	@Override
	public void enterMinus(MinusContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitMinus(MinusContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterMultiplicative(MultiplicativeContext ctx) {
		

	}

	@Override
	public void exitMultiplicative(MultiplicativeContext ctx) {
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
	public void enterTimes(TimesContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitTimes(TimesContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterPrimitive(PrimitiveContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPrimitive(PrimitiveContext ctx) {
		if (ctx.INTEGER() != null) {
			int n = Integer.valueOf(ctx.INTEGER().getText());
			Expression intExpression = new Number(n);
			stack.push(intExpression);
		}
		else if (ctx.DOUBLE() != null) {
			double d = Double.valueOf(ctx.DOUBLE().getText());
			Expression doubleExpression = new Number(d);
			stack.push(doubleExpression);
		}
		else if (ctx.VARIABLE() != null) {
			Expression variable = new Variable(ctx.VARIABLE().getText());
			stack.push(variable);
		}
		else {
			// do nothing
		}

	}

}
