import static org.junit.Assert.*;

import org.junit.Test;

public class AvailableStateTest {
  @Test
  public void borrowBook() throws Exception {
    BookState state = new AvailableState();

    BookState nextState = state.borrowBook();

    assertTrue(nextState instanceof LoanedState);
  }

  @Test
  public void returnBook() {
    BookState state = new AvailableState();

    try {
      state.returnBook();
      fail("Exceptionがスローされませんでした。");
    } catch (Exception e) {
      assertEquals("この本はすでに図書館にあります(返却できません)。", e.getMessage());
    }
  }
}
