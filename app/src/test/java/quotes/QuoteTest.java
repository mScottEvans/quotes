package quotes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteTest {
    @Test
    void quoteConstructorWorksTest() {
        Quote sut = new Quote("Scott","Hello!");
        assertEquals("Scott",sut.author);
    }
}
