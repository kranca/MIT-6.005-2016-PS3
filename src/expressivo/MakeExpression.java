package expressivo;

import java.util.List;
import java.util.Stack;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.AdditiveContext;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.MultiplicativeContext;
import expressivo.parser.ExpressionParser.ParenthesisContext;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.SubstractiveContext;

public class MakeExpression implements ExpressionListener {
	private Stack<Expression> stack = new Stack<>();
	
	// Invariant: stack contains the Expression value of each parse
    // subtree that has been fully-walked so far, but whose parent has not yet
    // been exited by the walk. The stack is ordered by recency of visit, so that
    // the top of the stack is the Expression for the most recently walked
    // subtree.
    //
    // At the start of the walk, the stack is empty, because no subtrees have
    // been fully walked.
    //
    // Whenever a node is exited by the walk, the Expression values of its
    // children are on top of the stack, in order with the last child on top. To
    // preserve the invariant, we must pop those child Expression values
    // from the stack, combine them with the appropriate Expression
    // producer, and push back an Expression value representing the entire
    // subtree under the node.
    //
    // At the end of the walk, after all subtrees have been walked and the
    // root has been exited, only the entire tree satisfies the invariant's
    // "fully walked but parent not yet exited" property, so the top of the stack
    // is the Expression of the entire parse tree.
	
	public Expression getExpression() {
		return stack.get(0);
	}

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// Rule not needed

	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// Rule not needed

	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// Rule not needed

	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// Rule not needed

	}

	@Override
	public void enterRoot(RootContext ctx) {
		// Rule not needed

	}

	@Override
	public void exitRoot(RootContext ctx) {
		// Rule not needed

	}

	@Override
	public void enterExpression(ExpressionContext ctx) {
		// Rule not needed

	}

	@Override
	public void exitExpression(ExpressionContext ctx) {
		// Rule not needed

	}

	@Override
	public void enterAdditive(AdditiveContext ctx) {
		// Rule not needed

	}

	@Override
	public void exitAdditive(AdditiveContext ctx) {
		// list contains amount of Expressions to be added
		List<ExpressionParser.SubstractiveContext> addends = ctx.substractive();
		
		// confirm stack with Expressions >= amount Expressions to be added
		assert stack.size() >= addends.size();
		
		// confirm at least one Expression will be added, since lexer is exiting an additive operation
		assert addends.size() > 0;
		
		// get latest Expression parsed
		Expression plus = stack.pop();
		
		// add Expressions parsing from right to left
		for (int i = 1; i < addends.size(); ++i) {
			
			plus = new Plus(stack.pop(), plus);
		}
		
		// push updated Plus Expression back to stack
		stack.push(plus);

	}

	@Override
	public void enterMultiplicative(MultiplicativeContext ctx) {
		// Rule not needed

	}

	@Override
	public void exitMultiplicative(MultiplicativeContext ctx) {
		// list contains amount of Expressions to be multiplied
		List<ExpressionParser.PrimitiveContext> factors = ctx.primitive();
		
		// confirm stack with Expressions >= amount Expressions to be multiplied
		assert stack.size() >= factors.size();
		
		// confirm at least one Expression will be multiplied, since lexer is exiting a multiplicative operation
		assert factors.size() > 0;
		
		// get latest Expression parsed
		Expression times = stack.pop();
		
		// multiply Expressions parsing from right to left
		for (int i = 1; i < factors.size(); ++i) {
			times = new Times(stack.pop(), times);
		}
		
		// push updated Times Expression back to stack
		stack.push(times);

	}

	@Override
	public void enterPrimitive(PrimitiveContext ctx) {
		// Rule not needed

	}

	@Override
	public void exitPrimitive(PrimitiveContext ctx) {
		// if lexer catches an Integer value, create a Number Expression
		if (ctx.INTEGER() != null) {
			int n = Integer.valueOf(ctx.INTEGER().getText());
			Expression intExpression = new Number(n);
			stack.push(intExpression);
		}
		// if lexer catches an Double value, create a Number Expression
		else if (ctx.DOUBLE() != null) {
			double d = Double.valueOf(ctx.DOUBLE().getText());
			Expression doubleExpression = new Number(d);
			stack.push(doubleExpression);
		}
		// if lexer catches an String value, create a Number Expression
		else if (ctx.VARIABLE() != null) {
			Expression variable = new Variable(ctx.VARIABLE().getText());
			stack.push(variable);
		}
		else {
			// do nothing
		}

	}

	@Override
	public void enterSubstractive(SubstractiveContext ctx) {
		// Rule not needed
		
	}

	@Override
	public void exitSubstractive(SubstractiveContext ctx) {
		// list contains amount of Expressions to be subtracted
		List<ExpressionParser.MultiplicativeContext> subtrahends = ctx.multiplicative();
		
		// confirm stack with Expressions >= amount Expressions to be subtracted
		assert stack.size() >= subtrahends.size();
		
		// confirm at least one Expression will be subtracted, since lexer is exiting a subtraction operation
		assert subtrahends.size() > 0;
		
		// get latest Expression parsed
		Expression minus = stack.pop();
		
		// identifier for an Expression starting with negative sign
		String startsWithMinus = ctx.getStart().getText();
		
		// Special handling for Expressions starting with a negative variable or number.
		// A zero is inserted before the "-", such that the mathematical meaning of the expression is kept
		if (startsWithMinus.equals("-")) {
			// casees x*-y and --x need to be reviewed, otherwise works well using parenthesis, x*(-y) and -(-x)
			Expression zero = new Number(0);
			
			// subtract Expressions parsing from right to left
			for (int i = 1; i < subtrahends.size(); ++i) {
				
				minus = new Minus(stack.pop(), minus);
				
			}
			// finally add a zero for cases handling negative expressions
			minus = new Minus(zero, minus);
		}
		
		else {
			
			// subtract regular positive expressions
			for (int i = 1; i < subtrahends.size(); ++i) {
				
				minus = new Minus(stack.pop(), minus);
				
			}
		}
		// push updated Minus Expression back to stack
		stack.push(minus);
		
	}

	@Override
	public void enterParenthesis(ParenthesisContext ctx) {
		// Rule not needed
		
	}

	@Override
	public void exitParenthesis(ParenthesisContext ctx) {
		
		// get latest parsed Expression from stack
		Expression e = stack.pop();
		
		// recursively create a new Parenthesis Expression
		e = new Parenthesis(e);
		
		// push updated Expression with parenthesis back to stack
		stack.push(e);
		
	}

}
