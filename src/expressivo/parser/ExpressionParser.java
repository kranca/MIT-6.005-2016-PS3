// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, INTEGER=6, DOUBLE=7, VARIABLE=8, 
    SPACES=9;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_additive = 2, RULE_substractive = 3, 
    RULE_multiplicative = 4, RULE_primitive = 5, RULE_parenthesis = 6;
  public static final String[] ruleNames = {
    "root", "expression", "additive", "substractive", "multiplicative", 
    "primitive", "parenthesis"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'-'", "'*'", "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, "INTEGER", "DOUBLE", "VARIABLE", 
    "SPACES"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the lexer or parser stop running if it encounters
      // invalid input and throw a ParseCancellationException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol,
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public ExpressionParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public TerminalNode EOF() { return getToken(ExpressionParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(14);
      expression();
      setState(15);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ExpressionContext extends ParserRuleContext {
    public AdditiveContext additive() {
      return getRuleContext(AdditiveContext.class,0);
    }
    public ExpressionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_expression; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
    }
  }

  public final ExpressionContext expression() throws RecognitionException {
    ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_expression);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(17);
      additive();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class AdditiveContext extends ParserRuleContext {
    public List<SubstractiveContext> substractive() {
      return getRuleContexts(SubstractiveContext.class);
    }
    public SubstractiveContext substractive(int i) {
      return getRuleContext(SubstractiveContext.class,i);
    }
    public AdditiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_additive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAdditive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAdditive(this);
    }
  }

  public final AdditiveContext additive() throws RecognitionException {
    AdditiveContext _localctx = new AdditiveContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_additive);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(27);
      _la = _input.LA(1);
      if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << INTEGER) | (1L << DOUBLE) | (1L << VARIABLE))) != 0)) {
        {
        setState(19);
        substractive();
        setState(24);
        _errHandler.sync(this);
        _la = _input.LA(1);
        while (_la==T__0) {
          {
          {
          {
          setState(20);
          match(T__0);
          }
          setState(21);
          substractive();
          }
          }
          setState(26);
          _errHandler.sync(this);
          _la = _input.LA(1);
        }
        }
      }

      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class SubstractiveContext extends ParserRuleContext {
    public List<MultiplicativeContext> multiplicative() {
      return getRuleContexts(MultiplicativeContext.class);
    }
    public MultiplicativeContext multiplicative(int i) {
      return getRuleContext(MultiplicativeContext.class,i);
    }
    public SubstractiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_substractive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterSubstractive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitSubstractive(this);
    }
  }

  public final SubstractiveContext substractive() throws RecognitionException {
    SubstractiveContext _localctx = new SubstractiveContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_substractive);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(29);
      multiplicative();
      setState(34);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__1) {
        {
        {
        {
        setState(30);
        match(T__1);
        }
        setState(31);
        multiplicative();
        }
        }
        setState(36);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MultiplicativeContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public MultiplicativeContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_multiplicative; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultiplicative(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultiplicative(this);
    }
  }

  public final MultiplicativeContext multiplicative() throws RecognitionException {
    MultiplicativeContext _localctx = new MultiplicativeContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_multiplicative);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(37);
      primitive();
      setState(42);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__2) {
        {
        {
        {
        setState(38);
        match(T__2);
        }
        setState(39);
        primitive();
        }
        }
        setState(44);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class PrimitiveContext extends ParserRuleContext {
    public ParenthesisContext parenthesis() {
      return getRuleContext(ParenthesisContext.class,0);
    }
    public TerminalNode INTEGER() { return getToken(ExpressionParser.INTEGER, 0); }
    public TerminalNode DOUBLE() { return getToken(ExpressionParser.DOUBLE, 0); }
    public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
    }
    public PrimitiveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_primitive; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPrimitive(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPrimitive(this);
    }
  }

  public final PrimitiveContext primitive() throws RecognitionException {
    PrimitiveContext _localctx = new PrimitiveContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_primitive);
    try {
      setState(51);
      switch (_input.LA(1)) {
      case T__3:
        enterOuterAlt(_localctx, 1);
        {
        setState(45);
        parenthesis();
        }
        break;
      case INTEGER:
        enterOuterAlt(_localctx, 2);
        {
        setState(46);
        match(INTEGER);
        }
        break;
      case DOUBLE:
        enterOuterAlt(_localctx, 3);
        {
        setState(47);
        match(DOUBLE);
        }
        break;
      case VARIABLE:
        enterOuterAlt(_localctx, 4);
        {
        setState(48);
        match(VARIABLE);
        }
        break;
      case T__1:
        enterOuterAlt(_localctx, 5);
        {
        setState(49);
        match(T__1);
        setState(50);
        primitive();
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ParenthesisContext extends ParserRuleContext {
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public ParenthesisContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_parenthesis; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterParenthesis(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitParenthesis(this);
    }
  }

  public final ParenthesisContext parenthesis() throws RecognitionException {
    ParenthesisContext _localctx = new ParenthesisContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_parenthesis);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(53);
      match(T__3);
      setState(54);
      expression();
      setState(55);
      match(T__4);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13<\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\3\3\3\3\3"+
      "\4\3\4\3\4\7\4\31\n\4\f\4\16\4\34\13\4\5\4\36\n\4\3\5\3\5\3\5\7\5"+
      "#\n\5\f\5\16\5&\13\5\3\6\3\6\3\6\7\6+\n\6\f\6\16\6.\13\6\3\7\3\7\3"+
      "\7\3\7\3\7\3\7\5\7\66\n\7\3\b\3\b\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16"+
      "\2\2<\2\20\3\2\2\2\4\23\3\2\2\2\6\35\3\2\2\2\b\37\3\2\2\2\n\'\3\2"+
      "\2\2\f\65\3\2\2\2\16\67\3\2\2\2\20\21\5\4\3\2\21\22\7\2\2\3\22\3\3"+
      "\2\2\2\23\24\5\6\4\2\24\5\3\2\2\2\25\32\5\b\5\2\26\27\7\3\2\2\27\31"+
      "\5\b\5\2\30\26\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33"+
      "\36\3\2\2\2\34\32\3\2\2\2\35\25\3\2\2\2\35\36\3\2\2\2\36\7\3\2\2\2"+
      "\37$\5\n\6\2 !\7\4\2\2!#\5\n\6\2\" \3\2\2\2#&\3\2\2\2$\"\3\2\2\2$"+
      "%\3\2\2\2%\t\3\2\2\2&$\3\2\2\2\',\5\f\7\2()\7\5\2\2)+\5\f\7\2*(\3"+
      "\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\13\3\2\2\2.,\3\2\2\2/\66\5\16"+
      "\b\2\60\66\7\b\2\2\61\66\7\t\2\2\62\66\7\n\2\2\63\64\7\4\2\2\64\66"+
      "\5\f\7\2\65/\3\2\2\2\65\60\3\2\2\2\65\61\3\2\2\2\65\62\3\2\2\2\65"+
      "\63\3\2\2\2\66\r\3\2\2\2\678\7\6\2\289\5\4\3\29:\7\7\2\2:\17\3\2\2"+
      "\2\7\32\35$,\65";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}