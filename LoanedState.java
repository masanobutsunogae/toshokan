public final class LoanedState implements BookState {
  @Override
  public BookState borrowBook() throws Exception {
    throw new Exception("現在貸出中のため、借りることができません");
  }

  @Override
  public BookState returnBook() {
    System.out.println("返却処理を行いました。");
    return new AvailableState();
  }

  @Override
  public int getStatusNum() {
    return 0;
  }
}
