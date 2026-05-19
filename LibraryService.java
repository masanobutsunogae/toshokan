public class LibraryService {
  public void checkout(int bookId) throws Exception {
    LibraryDAO dao = new LibraryDAO();

    Integer stateNum = dao.getState(bookId);

    if (stateNum == null) throw new Exception("指定されたIDの本は存在しません。");

    BookState currentState = stateNum == 1 ? new AvailableState() : new LoanedState();

    Book book = new Book(currentState);

    book.borrow();

    dao.setState(bookId, book.getState().getStatusNum());

    System.out.println("貸し出し処理が成功しました。");
  }

  public void checkin(int bookId) throws Exception {
    LibraryDAO dao = new LibraryDAO();

    Integer stateNum = dao.getState(bookId);

    if (stateNum == null) throw new Exception("指定されたIDの本は存在しません。");

    BookState currentState = stateNum == 1 ? new AvailableState() : new LoanedState();

    Book book = new Book(currentState);

    book.doReturn();

    dao.setState(bookId, book.getState().getStatusNum());

    System.out.println("返却処理が成功しました。");
  }
}
