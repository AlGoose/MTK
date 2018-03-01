import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void calculate1() throws Exception {
        Reader reader = new StringReader("5+5");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        assertEquals(10,parser.calculate());
    }

    @Test
    public void calculate2() throws Exception {
        Reader reader = new StringReader("(1+2)+(3+4)");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        assertEquals(10,parser.calculate());
    }

    @Test
    public void calculate3() throws Exception {
        Reader reader = new StringReader("(2+1)^2+10");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        assertEquals(19,parser.calculate());
    }

    @Test(expected = BadException.class)
    public void calculate4() throws Exception {
        Reader reader = new StringReader("5+5)");
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        parser.calculate();
    }
}