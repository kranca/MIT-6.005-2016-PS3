package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.PlusContext;
import expressivo.parser.ExpressionParser.PrimitiveContext;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.TimesContext;

public class MakeExpression implements ExpressionListener {
	private Stack<Expression> stack = new Stack<>();
	
	/**
	 * Invariant: stack contains the Expression value of each parse subtree that has been fully fully walked
	 * so far, but whose parent has not yet been exited by the walk. The stack is ordered by recency of visit,
	 * so that the top of the stack is the Expression for the most recently walked subtree.
	 * 
	 * At the start of the walk the stack is empty, because no subtrees have been fully walked.
	 * 
	 * Whenever a node is exited by the walk, the Expression values of its children are on top of the stack, in
	 * order with the last child on top. To preserve the invariant, we must pop those child Expression values from
	 * the stack, combine them with appropriate Expression producer and push back an Expression value representing
	 * the entire subtree under the node.
	 * 
	 * At the end of the walk, after all subtrees have been walked and the root has been exited, only the entire
	 * tree satisfies the invariant's "fully walked but parent not yet exited" property, so the top of the stack
	 * is the Expression of the entire parse tree.
	 */

	/**
	 * Returns the Expression constructed by this listener object.
	 * Requires that this listener has completely walked over an Expression parse tree using ParseTreeWalker.
	 * @return Expression for the parse tree that was walked.
	 */
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
		// parent node
	}

	@Override
	public void enterPlus(PlusContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPlus(PlusContext ctx) {
		List<ExpressionParser.PrimitiveContext> addends = ctx.primitive();
		
		assert stack.size() >= addends.size();
		
		//
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
		List<ExpressionParser.PrimitiveContext> addends = ctx.primitive();
		
		assert stack.size() >= addends.size();
		
		//
		assert addends.size() > 0;
		Expression times = stack.pop();
		
		for (int i = 1; i < addends.size(); ++i) {
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
			Expression integerNum = new Number(n);
			stack.push(integerNum);
		}
		else if (ctx.DOUBLE() != null) {
			Double d = Double.valueOf(ctx.DOUBLE().getText());
			Expression doubleNum = new Number(d);
			stack.push(doubleNum);
		}
		else if (ctx.VARIABLE() != null) {
			String s = ctx.VARIABLE().getText();
			Expression var = new Variable(s);
			stack.push(var);
		}
		// else if (ctx.OP() != null) {
		//	Expression op = new Op();
		//	stack.push(op);
		//}
		//else if (ctx.CP() != null) {
		//	Expression cp = new Cp();
		//	stack.push(cp);
		//}
		else {
			// do nothing because sum's or times' value is already on the stack
		}

	}

}
