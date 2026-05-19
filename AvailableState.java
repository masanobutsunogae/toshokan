public final class AvailableState implements BookState {
  @Override
  public BookState borrowBook() {
    System.out.println("貸し出し処理を行いました。");
    return new LoanedState();
  }

  @Override
  public BookState returnBook() throws Exception {
    throw new Exception("この本はすでに図書館にあります(返却できません)。");
  }
}
