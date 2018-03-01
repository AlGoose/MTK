public class Lexeme {
    private Main.LexemeType lexemeType;
    private String lexemeText;

    public Lexeme(Main.LexemeType lexemeType, String lexemeText){
        this.lexemeType = lexemeType;
        this.lexemeText = lexemeText;
    }

    public String getLexemeText(){
        return this.lexemeText;
    }

    public Main.LexemeType getLexemeType(){
        return this.lexemeType;
    }
}
