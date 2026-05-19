public sealed interface BookState permits AvailableState, LoanedState {
  BookState borrowBook() throws Exception;

  BookState returnBook() throws Exception;

  int getStatusNum();
}
