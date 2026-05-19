import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {
  @Test
  public void borrowBook() throws Exception {
    Book a = new Book(new AvailableState());

    a.borrow();

    assertTrue(a.getState() instanceof LoanedState);
  }

  @Test
  public void cantreturnBook() {
    Book a = new Book(new AvailableState());

    try {
      a.doReturn();
      fail("Exceptionがスローされませんでした。");
    } catch (Exception e) {
      assertEquals("この本はすでに図書館にあります(返却できません)。", e.getMessage());
    }
  }

  @Test
  public void returnBook() throws Exception {
    Book a = new Book(new LoanedState());

    a.doReturn();

    assertTrue(a.getState() instanceof AvailableState);
  }

  @Test
  public void cantborrowBook() {
    Book a = new Book(new LoanedState());

    try {
      a.borrow();
      fail("Exceptionがスローされませんでした。");
    } catch (Exception e) {
      assertEquals("現在貸出中のため、借りることができません", e.getMessage());
    }
  }
}
