import java.io.IOException;

public class Parser {
    private Lexer lexer;
    private Lexeme currentLexeme;

    public Parser(Lexer lexer){
        this.lexer = lexer;
    }

    public int calculate() throws IOException, BadException {
        int result = parseExpression();
        if(currentLexeme.getLexemeType() == Main.LexemeType.EOF) {
            return result;
        } else {
            throw new BadException("Wrong expression!");
        }
    }

    public int parseExpression() throws IOException, BadException {
        int temp = parseTerm();

        while((currentLexeme.getLexemeType() == Main.LexemeType.PLUS)||(currentLexeme.getLexemeType() == Main.LexemeType.MINUS)){
            if(currentLexeme.getLexemeType() == Main.LexemeType.PLUS){
                temp += parseTerm();
            } else {
                temp -= parseTerm();
            }
        }
        return temp;
    }

    public int parseTerm() throws IOException, BadException {
        int temp = parseFactor();

        while((currentLexeme.getLexemeType() == Main.LexemeType.MULTI)||(currentLexeme.getLexemeType() == Main.LexemeType.DIVIDE)){
            if(currentLexeme.getLexemeType() == Main.LexemeType.MULTI){
                temp *= parseTerm();
            } else {
                temp /= parseTerm();
            }
        }
        return temp;
    }

    public int parseFactor() throws IOException, BadException {
        int power = parsePower();

        if(currentLexeme.getLexemeType() == Main.LexemeType.POWER) {
            int factor = parseFactor();
            return (int)Math.pow(power,factor);
        } else {
            return power;
        }
    }

    public int parsePower() throws IOException, BadException {
        Lexeme lexeme = lexer.getLexeme();
        currentLexeme = lexeme;

        if(lexeme.getLexemeType() == Main.LexemeType.MINUS){
            return -parseAtom();
        } else {
            return  parseAtom();
        }
    }

    public int parseAtom() throws IOException, BadException {
        if(currentLexeme.getLexemeType() == Main.LexemeType.MINUS){
            Lexeme lexeme = lexer.getLexeme();
            switch (lexeme.getLexemeType()) {
                case LEFT:
                    int exp = parseExpression();
                    if(currentLexeme.getLexemeType() == Main.LexemeType.RIGHT){
                        currentLexeme = lexer.getLexeme();
                        return exp;
                    } else {
                        throw new BadException("Wrong expression!");
                    }
                case DIGIT:
                    currentLexeme = lexer.getLexeme();
                    return Integer.parseInt(lexeme.getLexemeText());
                default:
                    throw new BadException("Wrong expression!");
            }
        } else if (currentLexeme.getLexemeType() == Main.LexemeType.DIGIT){
            Lexeme lexeme = currentLexeme;
            currentLexeme = lexer.getLexeme();
            return Integer.parseInt(lexeme.getLexemeText());
        } else if (currentLexeme.getLexemeType() == Main.LexemeType.LEFT){
            int exp = parseExpression();
            if(currentLexeme.getLexemeType() == Main.LexemeType.RIGHT){
                currentLexeme = lexer.getLexeme();
                return exp;
            } else {
                throw new BadException("Wrong expression!");
            }
        } else {
            throw new BadException("Wrong expression!");
        }
    }
}

class BadException extends Exception{
    public BadException(String message){
        super(message);
    }
}
