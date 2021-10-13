// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(ExpressionParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(ExpressionParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void enterExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void exitExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#additive}.
   * @param ctx the parse tree
   */
  void enterAdditive(ExpressionParser.AdditiveContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#additive}.
   * @param ctx the parse tree
   */
  void exitAdditive(ExpressionParser.AdditiveContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#plus}.
   * @param ctx the parse tree
   */
  void enterPlus(ExpressionParser.PlusContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#plus}.
   * @param ctx the parse tree
   */
  void exitPlus(ExpressionParser.PlusContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#minus}.
   * @param ctx the parse tree
   */
  void enterMinus(ExpressionParser.MinusContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#minus}.
   * @param ctx the parse tree
   */
  void exitMinus(ExpressionParser.MinusContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#multiplicative}.
   * @param ctx the parse tree
   */
  void enterMultiplicative(ExpressionParser.MultiplicativeContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#multiplicative}.
   * @param ctx the parse tree
   */
  void exitMultiplicative(ExpressionParser.MultiplicativeContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#times}.
   * @param ctx the parse tree
   */
  void enterTimes(ExpressionParser.TimesContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#times}.
   * @param ctx the parse tree
   */
  void exitTimes(ExpressionParser.TimesContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#primitive}.
   * @param ctx the parse tree
   */
  void enterPrimitive(ExpressionParser.PrimitiveContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#primitive}.
   * @param ctx the parse tree
   */
  void exitPrimitive(ExpressionParser.PrimitiveContext ctx);
}