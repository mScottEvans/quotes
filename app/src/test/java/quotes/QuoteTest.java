package quotes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteTest {
    @Test
    void quoteConstructorWorksTest() {
        Quote sut = new Quote("Scott","Hello!");
        System.out.println(sut.toString());
        assertEquals("quotes.Quote@537f60bf",sut.toString());
    }
}
