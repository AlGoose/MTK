import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int lastSymbol = -1;

    public Lexer(Reader reader){
        this.reader = reader;
    }

    public Lexeme getLexeme() throws IOException {
        int c;
        boolean isNumber = false;
        StringBuffer sb = new StringBuffer();

        if(lastSymbol == -1){
            c = reader.read();

            while(((char)c >= '0')&&((char)c <= '9')){
                isNumber = true;
                sb.append((char)c);
                c = reader.read();
            }

            if(isNumber){
                lastSymbol = c;
                return new Lexeme(Main.LexemeType.DIGIT, sb.toString());
            }

            sb.append((char)c);

            switch ((char)c){
                case '(':
                    return new Lexeme(Main.LexemeType.LEFT, sb.toString());
                case ')':
                    return new Lexeme(Main.LexemeType.RIGHT, sb.toString());
                case '+':
                    return new Lexeme(Main.LexemeType.PLUS, sb.toString());
                case '-':
                    return new Lexeme(Main.LexemeType.MINUS, sb.toString());
                case '*':
                    return new Lexeme(Main.LexemeType.MULTI, sb.toString());
                case '/':
                    return new Lexeme(Main.LexemeType.DIVIDE, sb.toString());
                case '^':
                    return new Lexeme(Main.LexemeType.POWER, sb.toString());
                default:
                    return new Lexeme(Main.LexemeType.EOF,"ERROR");
            }
        } else {
            sb.append((char)lastSymbol);

            switch((char)lastSymbol){
                case '+':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.PLUS, sb.toString());
                case '-':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.MINUS, sb.toString());
                case '(':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.LEFT, sb.toString());
                case ')':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.RIGHT, sb.toString());
                case '*':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.MULTI, sb.toString());
                case '/':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.DIVIDE, sb.toString());
                case '^':
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.POWER, sb.toString());
                default:
                    lastSymbol = -1;
                    return new Lexeme(Main.LexemeType.EOF,"ERROR");
            }
        }
    }
}
