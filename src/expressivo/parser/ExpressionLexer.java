// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionLexer extends Lexer {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, INTEGER=3, DOUBLE=4, VARIABLE=5, OP=6, CP=7, SPACES=8;
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "INTEGER", "DOUBLE", "VARIABLE", "OP", "CP", "SPACES"
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


  public ExpressionLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }

  @Override
  public String getGrammarFileName() { return "Expression.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public String[] getModeNames() { return modeNames; }

  @Override
  public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\n8\b\1\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3"+
      "\3\3\3\3\4\6\4\31\n\4\r\4\16\4\32\3\5\7\5\36\n\5\f\5\16\5!\13\5\3"+
      "\5\3\5\6\5%\n\5\r\5\16\5&\3\6\6\6*\n\6\r\6\16\6+\3\7\3\7\3\b\3\b\3"+
      "\t\6\t\63\n\t\r\t\16\t\64\3\t\3\t\2\2\n\3\3\5\4\7\5\t\6\13\7\r\b\17"+
      "\t\21\n\3\2\5\3\2\62;\4\2C\\c|\3\2\"\"<\2\3\3\2\2\2\2\5\3\2\2\2\2"+
      "\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
      "\3\2\2\2\3\23\3\2\2\2\5\25\3\2\2\2\7\30\3\2\2\2\t\37\3\2\2\2\13)\3"+
      "\2\2\2\r-\3\2\2\2\17/\3\2\2\2\21\62\3\2\2\2\23\24\7-\2\2\24\4\3\2"+
      "\2\2\25\26\7,\2\2\26\6\3\2\2\2\27\31\t\2\2\2\30\27\3\2\2\2\31\32\3"+
      "\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\b\3\2\2\2\34\36\t\2\2\2\35\34"+
      "\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2"+
      "\2\2\"$\7\60\2\2#%\t\2\2\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2"+
      "\2\'\n\3\2\2\2(*\t\3\2\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+,\3\2\2\2,"+
      "\f\3\2\2\2-.\7*\2\2.\16\3\2\2\2/\60\7+\2\2\60\20\3\2\2\2\61\63\t\4"+
      "\2\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\66"+
      "\3\2\2\2\66\67\b\t\2\2\67\22\3\2\2\2\b\2\32\37&+\64\3\b\2\2";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}