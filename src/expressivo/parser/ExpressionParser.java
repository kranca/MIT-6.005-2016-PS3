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
    T__0=1, T__1=2, INTEGER=3, DOUBLE=4, VARIABLE=5, OP=6, CP=7, SPACES=8;
  public static final int
    RULE_root = 0, RULE_expression = 1, RULE_plus = 2, RULE_times = 3, RULE_primitive = 4;
  public static final String[] ruleNames = {
    "root", "expression", "plus", "times", "primitive"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'+'", "'*'", null, null, null, "'('", "')'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, "INTEGER", "DOUBLE", "VARIABLE", "OP", "CP", "SPACES"
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
      setState(10);
      expression();
      setState(11);
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
    public TerminalNode OP() { return getToken(ExpressionParser.OP, 0); }
    public ExpressionContext expression() {
      return getRuleContext(ExpressionContext.class,0);
    }
    public TerminalNode CP() { return getToken(ExpressionParser.CP, 0); }
    public TimesContext times() {
      return getRuleContext(TimesContext.class,0);
    }
    public PlusContext plus() {
      return getRuleContext(PlusContext.class,0);
    }
    public PrimitiveContext primitive() {
      return getRuleContext(PrimitiveContext.class,0);
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
      setState(20);
      switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(13);
        match(OP);
        setState(14);
        expression();
        setState(15);
        match(CP);
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(17);
        times();
        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        setState(18);
        plus();
        }
        break;
      case 4:
        enterOuterAlt(_localctx, 4);
        {
        setState(19);
        primitive();
        }
        break;
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

  public static class PlusContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public PlusContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_plus; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPlus(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPlus(this);
    }
  }

  public final PlusContext plus() throws RecognitionException {
    PlusContext _localctx = new PlusContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_plus);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(22);
      primitive();
      setState(27);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__0) {
        {
        {
        setState(23);
        match(T__0);
        setState(24);
        primitive();
        }
        }
        setState(29);
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

  public static class TimesContext extends ParserRuleContext {
    public List<PrimitiveContext> primitive() {
      return getRuleContexts(PrimitiveContext.class);
    }
    public PrimitiveContext primitive(int i) {
      return getRuleContext(PrimitiveContext.class,i);
    }
    public TimesContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_times; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterTimes(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitTimes(this);
    }
  }

  public final TimesContext times() throws RecognitionException {
    TimesContext _localctx = new TimesContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_times);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(33);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEGER) | (1L << DOUBLE) | (1L << VARIABLE) | (1L << OP))) != 0)) {
        {
        {
        setState(30);
        primitive();
        }
        }
        setState(35);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(40);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__1) {
        {
        {
        setState(36);
        match(T__1);
        setState(37);
        primitive();
        }
        }
        setState(42);
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
    public TerminalNode INTEGER() { return getToken(ExpressionParser.INTEGER, 0); }
    public TerminalNode DOUBLE() { return getToken(ExpressionParser.DOUBLE, 0); }
    public TerminalNode VARIABLE() { return getToken(ExpressionParser.VARIABLE, 0); }
    public PlusContext plus() {
      return getRuleContext(PlusContext.class,0);
    }
    public TimesContext times() {
      return getRuleContext(TimesContext.class,0);
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
    enterRule(_localctx, 8, RULE_primitive);
    try {
      setState(54);
      switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
      case 1:
        enterOuterAlt(_localctx, 1);
        {
        setState(43);
        match(INTEGER);
        }
        break;
      case 2:
        enterOuterAlt(_localctx, 2);
        {
        setState(44);
        match(DOUBLE);
        }
        break;
      case 3:
        enterOuterAlt(_localctx, 3);
        {
        setState(45);
        match(VARIABLE);
        }
        break;
      case 4:
        enterOuterAlt(_localctx, 4);
        {
        setState(46);
        match(OP);
        setState(47);
        plus();
        setState(48);
        match(CP);
        }
        break;
      case 5:
        enterOuterAlt(_localctx, 5);
        {
        setState(50);
        match(OP);
        setState(51);
        times();
        setState(52);
        match(CP);
        }
        break;
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
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\n;\4\2\t\2\4\3"+
      "\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
      "\3\5\3\27\n\3\3\4\3\4\3\4\7\4\34\n\4\f\4\16\4\37\13\4\3\5\7\5\"\n"+
      "\5\f\5\16\5%\13\5\3\5\3\5\7\5)\n\5\f\5\16\5,\13\5\3\6\3\6\3\6\3\6"+
      "\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\69\n\6\3\6\2\2\7\2\4\6\b\n\2\2?\2\f"+
      "\3\2\2\2\4\26\3\2\2\2\6\30\3\2\2\2\b#\3\2\2\2\n8\3\2\2\2\f\r\5\4\3"+
      "\2\r\16\7\2\2\3\16\3\3\2\2\2\17\20\7\b\2\2\20\21\5\4\3\2\21\22\7\t"+
      "\2\2\22\27\3\2\2\2\23\27\5\b\5\2\24\27\5\6\4\2\25\27\5\n\6\2\26\17"+
      "\3\2\2\2\26\23\3\2\2\2\26\24\3\2\2\2\26\25\3\2\2\2\27\5\3\2\2\2\30"+
      "\35\5\n\6\2\31\32\7\3\2\2\32\34\5\n\6\2\33\31\3\2\2\2\34\37\3\2\2"+
      "\2\35\33\3\2\2\2\35\36\3\2\2\2\36\7\3\2\2\2\37\35\3\2\2\2 \"\5\n\6"+
      "\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$*\3\2\2\2%#\3\2\2\2&\'"+
      "\7\4\2\2\')\5\n\6\2(&\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\t\3\2"+
      "\2\2,*\3\2\2\2-9\7\5\2\2.9\7\6\2\2/9\7\7\2\2\60\61\7\b\2\2\61\62\5"+
      "\6\4\2\62\63\7\t\2\2\639\3\2\2\2\64\65\7\b\2\2\65\66\5\b\5\2\66\67"+
      "\7\t\2\2\679\3\2\2\28-\3\2\2\28.\3\2\2\28/\3\2\2\28\60\3\2\2\28\64"+
      "\3\2\2\29\13\3\2\2\2\7\26\35#*8";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}