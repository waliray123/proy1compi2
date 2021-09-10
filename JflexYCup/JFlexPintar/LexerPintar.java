// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: Lexer.jflex

package Analizadores.Pintar;
import java.util.List;
import java.util.ArrayList;
import Analizadores.ErrorCom;


// See https://github.com/jflex-de/jflex/issues/222
@SuppressWarnings("FallThrough")
public class LexerPintar {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0, 0
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\1\u0100\37\u0200\1\u0300\u10de\u0200";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\0\1\2\22\0\1\3\1\4"+
    "\1\5\1\6\1\0\1\7\1\10\1\11\3\7\1\12"+
    "\1\7\1\13\1\14\1\7\12\15\2\7\1\16\1\17"+
    "\1\20\2\0\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46"+
    "\1\47\1\50\1\41\1\51\1\7\1\52\2\7\1\41"+
    "\1\0\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\1\50\1\41\1\51\1\7\1\53\1\7\262\0\2\54"+
    "\115\0\1\55\u01aa\0\1\56\325\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[1024];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\1\1\1\2\2\1\2\2\1\3"+
    "\3\2\20\4\3\1\3\0\1\5\4\0\1\6\4\4"+
    "\1\0\3\4\1\0\5\4\1\0\2\4\1\0\4\4"+
    "\1\0\3\4\1\7\2\4\1\7\2\4\4\0\1\3"+
    "\1\0\2\4\1\0\2\4\1\0\3\4\1\0\4\4"+
    "\1\0\3\4\1\0\3\4\1\0\1\4\1\0\7\4"+
    "\2\0\1\4\1\7\3\0\1\3\1\6\1\4\1\7"+
    "\2\4\1\0\3\4\4\0\2\4\1\0\1\4\1\0"+
    "\2\4\1\0\1\4\1\0\3\4\1\0\1\7\2\4"+
    "\1\0\1\7\1\0\1\3\3\4\2\0\2\4\2\0"+
    "\1\4\1\0\1\4\1\0\1\4\1\0\1\4\1\0"+
    "\1\4\1\0\3\4\1\0\2\4\2\0\1\3\1\4"+
    "\1\0\2\4\1\0\1\4\1\0\1\4\1\0\1\4"+
    "\1\0\1\4\1\0\1\3\1\4\1\0\1\4\1\0"+
    "\1\4\1\3\1\4\1\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[204];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\57\0\353\0\u011a"+
    "\0\u0149\0\u0178\0\u01a7\0\u01d6\0\u0205\0\u0234\0\u0263\0\u0292"+
    "\0\u02c1\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac\0\u03db\0\u040a"+
    "\0\u0439\0\u0468\0\u0497\0\u04c6\0\u04f5\0\u0524\0\u0553\0\u0582"+
    "\0\u05b1\0\u05e0\0\u0553\0\274\0\57\0\u060f\0\u063e\0\u066d"+
    "\0\u069c\0\u06cb\0\u06fa\0\u0729\0\u0758\0\u0787\0\u07b6\0\u07e5"+
    "\0\u0814\0\u0843\0\u0872\0\u08a1\0\u08d0\0\u08ff\0\u092e\0\u095d"+
    "\0\u098c\0\u09bb\0\u09ea\0\u0a19\0\u0a48\0\u0a77\0\u0aa6\0\u0ad5"+
    "\0\u0b04\0\u0b33\0\u0b62\0\u0b91\0\u0bc0\0\u0bef\0\u0c1e\0\u0c4d"+
    "\0\u0c7c\0\u0cab\0\u0cda\0\u0d09\0\u0d38\0\u0d67\0\u0d96\0\u0dc5"+
    "\0\u0df4\0\u0e23\0\u0e52\0\u0e81\0\u0eb0\0\u0edf\0\u0f0e\0\u0f3d"+
    "\0\u0f6c\0\u0f9b\0\u0fca\0\u0ff9\0\u1028\0\u1057\0\u1086\0\u10b5"+
    "\0\u10e4\0\u1113\0\u1142\0\u1171\0\u11a0\0\u11cf\0\u11fe\0\u122d"+
    "\0\u125c\0\u128b\0\u12ba\0\u12e9\0\u1318\0\u1347\0\u1376\0\u13a5"+
    "\0\u13d4\0\u1403\0\u1432\0\u0292\0\u1461\0\u1490\0\u14bf\0\u14ee"+
    "\0\57\0\u151d\0\57\0\u154c\0\u157b\0\u15aa\0\u15d9\0\u1608"+
    "\0\u1637\0\u1666\0\u1695\0\u16c4\0\u16f3\0\u1722\0\u1751\0\u1780"+
    "\0\u17af\0\u17de\0\u180d\0\u183c\0\u186b\0\u189a\0\u18c9\0\u18f8"+
    "\0\u1927\0\u1956\0\u1985\0\u19b4\0\u19e3\0\u1a12\0\u1a41\0\u1a70"+
    "\0\u1a9f\0\u1ace\0\u1afd\0\u1b2c\0\u1b5b\0\u1b8a\0\u1bb9\0\u1be8"+
    "\0\u1c17\0\u1c46\0\u1c75\0\u1ca4\0\u1cd3\0\u1d02\0\u1d31\0\u1d60"+
    "\0\u1d8f\0\u1dbe\0\u1ded\0\u1e1c\0\u1e4b\0\u1e7a\0\u1ea9\0\u1ed8"+
    "\0\u1f07\0\u1f36\0\u1f65\0\u1f94\0\u1fc3\0\u1ff2\0\u2021\0\u2050"+
    "\0\u207f\0\u20ae\0\u20dd\0\u210c\0\u213b\0\u216a\0\u2199\0\u21c8"+
    "\0\u21f7\0\u2226\0\u2255\0\u2284\0\u22b3\0\u22e2\0\u2311\0\u2340"+
    "\0\u236f\0\57\0\u239e\0\u23cd";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[204];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\3\3\1\4\1\5\1\2\1\6\1\7\1\10"+
    "\1\11\1\12\1\2\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\20\1\25\2\20"+
    "\1\26\1\27\1\30\1\31\1\20\1\32\1\20\1\33"+
    "\1\34\1\35\1\20\1\36\3\20\1\2\1\37\1\2"+
    "\1\40\1\41\60\0\3\3\57\0\1\6\3\0\1\42"+
    "\6\0\1\6\33\0\1\43\3\0\5\44\1\45\44\44"+
    "\1\0\4\44\10\0\1\6\42\0\1\6\3\0\6\46"+
    "\1\47\2\46\1\0\45\46\12\0\1\6\4\0\1\6"+
    "\52\0\1\6\57\0\1\50\1\13\54\0\1\51\3\0"+
    "\1\6\56\0\1\6\56\0\1\6\1\52\53\0\1\20"+
    "\3\0\21\20\1\53\7\20\22\0\1\20\3\0\31\20"+
    "\22\0\1\20\3\0\1\54\7\20\1\55\5\20\1\56"+
    "\12\20\2\0\1\57\17\0\1\20\3\0\4\20\1\60"+
    "\24\20\22\0\1\20\3\0\22\20\1\61\4\20\1\62"+
    "\1\20\3\0\1\63\16\0\1\20\3\0\1\64\30\20"+
    "\22\0\1\20\3\0\1\65\30\20\22\0\1\20\3\0"+
    "\4\20\1\66\24\20\22\0\1\20\3\0\10\20\1\67"+
    "\5\20\1\70\12\20\2\0\1\71\17\0\1\20\3\0"+
    "\4\20\1\72\3\20\1\73\20\20\2\0\1\74\17\0"+
    "\1\20\3\0\16\20\1\75\12\20\22\0\1\20\3\0"+
    "\1\76\7\20\1\77\10\20\1\100\7\20\2\0\1\101"+
    "\17\0\1\20\3\0\1\102\3\20\1\103\24\20\22\0"+
    "\1\20\3\0\1\104\7\20\1\105\13\20\1\106\1\20"+
    "\1\107\2\20\2\0\1\110\17\0\1\20\3\0\21\20"+
    "\1\111\7\20\22\0\1\20\3\0\1\112\30\20\60\0"+
    "\1\6\24\0\1\113\7\0\1\110\13\0\1\114\1\0"+
    "\1\115\4\0\1\110\27\0\1\116\41\0\1\6\57\0"+
    "\1\45\45\0\11\46\1\45\45\46\15\0\1\117\41\0"+
    "\13\51\1\120\4\51\1\0\36\51\2\52\1\0\54\52"+
    "\15\0\1\20\3\0\21\20\1\121\7\20\22\0\1\20"+
    "\3\0\22\20\1\122\6\20\3\0\1\123\16\0\1\20"+
    "\3\0\21\20\1\124\7\20\22\0\1\20\3\0\15\20"+
    "\1\125\13\20\47\0\1\126\31\0\1\20\3\0\5\20"+
    "\1\127\23\20\22\0\1\20\3\0\17\20\1\130\11\20"+
    "\22\0\1\20\3\0\23\20\1\131\5\20\45\0\1\132"+
    "\33\0\1\20\3\0\13\20\1\133\15\20\22\0\1\20"+
    "\3\0\2\20\1\134\26\20\22\0\1\20\3\0\4\20"+
    "\1\135\24\20\22\0\1\20\3\0\22\20\1\136\6\20"+
    "\3\0\1\137\16\0\1\20\3\0\15\20\1\140\13\20"+
    "\50\0\1\137\11\0\1\137\16\0\1\20\3\0\15\20"+
    "\1\141\13\20\22\0\1\20\3\0\4\20\1\142\24\20"+
    "\32\0\1\143\46\0\1\20\3\0\14\20\1\144\14\20"+
    "\22\0\1\20\3\0\21\20\1\145\7\20\22\0\1\20"+
    "\3\0\22\20\1\146\6\20\3\0\1\147\16\0\1\20"+
    "\3\0\10\20\1\150\20\20\2\0\1\151\45\0\1\147"+
    "\11\0\1\147\16\0\1\20\3\0\15\20\1\152\13\20"+
    "\22\0\1\20\3\0\17\20\1\153\3\20\1\154\5\20"+
    "\22\0\1\20\3\0\13\20\1\155\15\20\22\0\1\20"+
    "\3\0\15\20\1\156\13\20\22\0\1\20\3\0\14\20"+
    "\1\157\14\20\22\0\1\20\3\0\10\20\1\160\20\20"+
    "\2\0\1\161\40\0\1\162\35\0\1\20\3\0\24\20"+
    "\1\163\4\20\22\0\1\20\3\0\21\20\1\164\7\20"+
    "\41\0\1\165\57\0\1\166\52\0\1\161\22\0\1\161"+
    "\27\0\1\167\46\0\1\170\61\0\1\171\53\0\1\20"+
    "\3\0\4\20\1\172\24\20\22\0\1\20\3\0\16\20"+
    "\1\164\12\20\44\0\1\173\34\0\1\20\3\0\2\20"+
    "\1\174\26\20\22\0\1\20\3\0\23\20\1\175\5\20"+
    "\30\0\1\176\50\0\1\20\3\0\1\177\30\20\22\0"+
    "\1\20\3\0\4\20\1\200\24\20\22\0\1\20\3\0"+
    "\10\20\1\201\20\20\2\0\1\202\27\0\1\203\46\0"+
    "\1\20\3\0\22\20\1\163\6\20\3\0\1\204\16\0"+
    "\1\20\3\0\4\20\1\112\24\20\22\0\1\20\3\0"+
    "\17\20\1\164\11\20\22\0\1\20\3\0\23\20\1\145"+
    "\5\20\51\0\1\205\27\0\1\20\3\0\6\20\1\206"+
    "\22\20\22\0\1\20\3\0\22\20\1\207\6\20\3\0"+
    "\1\210\16\0\1\20\3\0\15\20\1\211\13\20\43\0"+
    "\1\212\35\0\1\20\3\0\1\20\1\213\27\20\22\0"+
    "\1\20\3\0\1\164\30\20\22\0\1\20\3\0\23\20"+
    "\1\214\5\20\51\0\1\215\27\0\1\20\3\0\15\20"+
    "\1\216\13\20\43\0\1\217\35\0\1\20\3\0\3\20"+
    "\1\220\25\20\22\0\1\20\3\0\21\20\1\221\7\20"+
    "\22\0\1\20\3\0\16\20\1\222\12\20\22\0\1\20"+
    "\3\0\10\20\1\112\20\20\2\0\1\223\17\0\1\20"+
    "\3\0\16\20\1\224\12\20\22\0\1\20\3\0\1\225"+
    "\30\20\22\0\1\20\3\0\23\20\1\226\5\20\51\0"+
    "\1\227\51\0\1\230\34\0\1\20\3\0\4\20\1\164"+
    "\24\20\36\0\1\223\22\0\1\223\23\0\1\231\75\0"+
    "\1\173\33\0\1\232\56\0\1\20\3\0\6\20\1\233"+
    "\22\20\22\0\1\20\3\0\24\20\1\234\4\20\22\0"+
    "\1\20\3\0\10\20\1\235\20\20\2\0\1\236\47\0"+
    "\1\237\26\0\1\20\3\0\24\20\1\240\4\20\22\0"+
    "\1\20\3\0\21\20\1\36\7\20\22\0\1\20\3\0"+
    "\4\20\1\241\24\20\32\0\1\242\73\0\1\243\41\0"+
    "\1\173\52\0\1\173\52\0\1\20\3\0\10\20\1\244"+
    "\20\20\2\0\1\245\17\0\1\20\3\0\1\246\30\20"+
    "\26\0\1\247\52\0\1\20\3\0\23\20\1\250\5\20"+
    "\51\0\1\251\27\0\1\20\3\0\21\20\1\163\7\20"+
    "\22\0\1\20\3\0\1\252\30\20\26\0\1\253\52\0"+
    "\1\20\3\0\2\20\1\254\26\20\30\0\1\255\50\0"+
    "\1\20\3\0\16\20\1\256\12\20\22\0\1\20\3\0"+
    "\16\20\1\257\12\20\22\0\1\20\3\0\21\20\1\260"+
    "\7\20\47\0\1\173\17\0\1\261\11\0\1\20\3\0"+
    "\31\20\22\0\1\20\3\0\21\20\1\262\7\20\22\0"+
    "\1\20\3\0\2\20\1\263\26\20\30\0\1\264\36\0"+
    "\1\261\115\0\1\265\31\0\1\266\56\0\1\20\3\0"+
    "\13\20\1\122\15\20\22\0\1\20\3\0\13\20\1\36"+
    "\15\20\22\0\1\20\3\0\15\20\1\267\13\20\43\0"+
    "\1\270\54\0\1\243\37\0\1\20\3\0\13\20\1\271"+
    "\15\20\22\0\1\20\3\0\15\20\1\272\13\20\43\0"+
    "\1\273\41\0\1\223\52\0\1\20\3\0\23\20\1\274"+
    "\5\20\51\0\1\275\27\0\1\20\3\0\11\20\1\163"+
    "\17\20\37\0\1\204\41\0\1\20\3\0\21\20\1\214"+
    "\7\20\47\0\1\215\31\0\1\20\3\0\22\20\1\164"+
    "\6\20\3\0\1\173\44\0\1\173\11\0\1\173\16\0"+
    "\1\20\3\0\10\20\1\276\20\20\2\0\1\277\33\0"+
    "\1\277\22\0\1\277\17\0\1\20\3\0\14\20\1\164"+
    "\14\20\22\0\1\20\3\0\3\20\1\300\25\20\22\0"+
    "\1\20\3\0\15\20\1\145\13\20\50\0\1\301\11\0"+
    "\1\301\16\0\1\20\3\0\10\20\1\302\20\20\2\0"+
    "\1\303\17\0\1\20\3\0\7\20\1\164\21\20\35\0"+
    "\1\173\57\0\1\303\22\0\1\303\17\0\1\304\56\0"+
    "\1\20\3\0\24\20\1\36\4\20\52\0\1\243\26\0"+
    "\1\20\3\0\23\20\1\164\5\20\22\0\1\20\3\0"+
    "\3\20\1\163\25\20\31\0\1\204\47\0\1\20\3\0"+
    "\24\20\1\305\4\20\52\0\1\306\26\0\1\20\3\0"+
    "\17\20\1\307\11\20\45\0\1\310\33\0\1\20\3\0"+
    "\24\20\1\311\4\20\36\0\1\173\22\0\1\173\17\0"+
    "\1\20\3\0\30\20\1\36\56\0\1\243\22\0\1\312"+
    "\56\0\1\20\3\0\3\20\1\164\25\20\31\0\1\173"+
    "\47\0\1\20\3\0\1\313\30\20\26\0\1\314\52\0"+
    "\1\20\3\0\2\20\1\155\26\20\22\0\1\20\3\0"+
    "\13\20\1\164\15\20\41\0\1\173\22\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[9212];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\33\1\3\0\1\11\4\0"+
    "\5\1\1\0\3\1\1\0\5\1\1\0\2\1\1\0"+
    "\4\1\1\0\11\1\4\0\1\1\1\0\2\1\1\0"+
    "\2\1\1\0\3\1\1\0\4\1\1\0\3\1\1\0"+
    "\3\1\1\0\1\1\1\0\7\1\2\0\2\1\3\0"+
    "\1\1\1\11\1\1\1\11\2\1\1\0\3\1\4\0"+
    "\2\1\1\0\1\1\1\0\2\1\1\0\1\1\1\0"+
    "\3\1\1\0\3\1\1\0\1\1\1\0\4\1\2\0"+
    "\2\1\2\0\1\1\1\0\1\1\1\0\1\1\1\0"+
    "\1\1\1\0\1\1\1\0\3\1\1\0\2\1\2\0"+
    "\2\1\1\0\2\1\1\0\1\1\1\0\1\1\1\0"+
    "\1\1\1\0\1\1\1\0\2\1\1\0\1\1\1\0"+
    "\1\1\1\11\1\1\1\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[204];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  @SuppressWarnings("unused")
  private boolean zzEOFDone;

  /* user code: */
    private List<ErrorCom> erroresCom;
    public PintarPalabras pintar = new PintarPalabras();
    /*------Inicializacion Variables-----*/
    private int identCant;
    private boolean identar;    

    private void error(String lexeme) {
        erroresCom.add(new ErrorCom("Lexico","Simbolo no existe en el lenguaje",String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme));
    }    

    private void errorPrueba(String lexeme, String tipo) {
        erroresCom.add(new ErrorCom("PRUEBA",tipo,String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme));
    }

    public List<ErrorCom> getErroresCom() {
        return erroresCom;
    }

    private int verificarIdentacion(String valor){
        for(char c : valor.toCharArray()) {
            if(c == '\n'){
                this.identCant = 0;
                identar = true;
            } else if (c == ' ' || c == 32) {
                this.identCant++;
            } else if (c == '\t') {
                this.identCant += 4;
            } else {
                identar = false;
            }
        }        
        if(identar == true && this.identCant != 0){
            int cantidadIdent = 0;
            while(this.identCant >= 4){
                cantidadIdent++;
                this.identCant = this.identCant - 4; 
            }
            this.identCant = 0;
            return cantidadIdent;
        }
        return -1;
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexerPintar(java.io.Reader in) {
      erroresCom = new ArrayList<>();
    identar = true;
    identCant = 0;
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  public int scanear() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return YYEOF;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { System.out.println("es un Error:"+yytext());
            }
            // fall through
          case 8: break;
          case 2:
            { /*vacio*/
            }
            // fall through
          case 9: break;
          case 3:
            { pintar.pintaMora(yychar,yylength());
            }
            // fall through
          case 10: break;
          case 4:
            { pintar.pintaVerde(yychar,yylength());
            }
            // fall through
          case 11: break;
          case 5:
            { pintar.pintaNara(yychar,yylength());
            }
            // fall through
          case 12: break;
          case 6:
            { pintar.pintaGris(yychar,yylength());
            }
            // fall through
          case 13: break;
          case 7:
            { pintar.pintaAzul(yychar,yylength());
            }
            // fall through
          case 14: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String[] argv) {
    if (argv.length == 0) {
      System.out.println("Usage : java LexerPintar [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          // Side-effect: is encodingName valid?
          java.nio.charset.Charset.forName(encodingName);
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        LexerPintar scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new LexerPintar(reader);
          while ( !scanner.zzAtEOF ) scanner.scanear();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
