import static org.junit.Assert.*;

import org.junit.Test;

public class LoanedStateTest {
  @Test
  public void returnBook() throws Exception {
    BookState state = new LoanedState();

    BookState nextState = state.returnBook();

    assertTrue(nextState instanceof AvailableState);
  }

  @Test
  public void borrowBook() {
    BookState state = new LoanedState();

    try {
      state.borrowBook();
      fail("Exceptionがスローされませんでした。");
    } catch (Exception e) {
      assertEquals("現在貸出中のため、借りることができません", e.getMessage());
    }
  }
}
