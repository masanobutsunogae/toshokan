public class LibraryService {
  public static void checkout(int bookId) throws Exception {
    LibraryDAO dao = new LibraryDAO();

    Integer stateNum = dao.getState(bookId);

    if (stateNum == null) throw new Exception("指定されたIDの本は存在しません。");

    BookState currentState = stateNum == 1 ? new AvailableState() : new LoanedState();

    Book book = new Book(currentState);

    book.borrow();

    boolean setStateResult = dao.setState(bookId, book.getState().getStatusNum());

    if (setStateResult) System.out.println("貸し出し処理が成功しました。");
    else throw new Exception("貸し出し処理の書き込みに失敗しました。");
  }

  public static void checkin(int bookId) throws Exception {
    LibraryDAO dao = new LibraryDAO();

    Integer stateNum = dao.getState(bookId);

    if (stateNum == null) throw new Exception("指定されたIDの本は存在しません。");

    BookState currentState = stateNum == 1 ? new AvailableState() : new LoanedState();

    Book book = new Book(currentState);

    book.doReturn();

    boolean setStateResult = dao.setState(bookId, book.getState().getStatusNum());

    if (setStateResult) System.out.println("返却処理が成功しました。");
    else throw new Exception("返却処理の書き込みに失敗しました。");
  }
}
