import java.io.FileReader;
import java.io.IOException;

public class Main {
    enum LexemeType {DIGIT, PLUS, MINUS, LEFT, RIGHT, MULTI, DIVIDE, POWER, EOF}

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("src/file.txt");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        try{
            int result = parser.calculate();
            System.out.println(result);
        } catch (BadException e){
            System.out.println(e.getMessage());
        }
    }
}
