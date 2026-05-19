public class Book {
  private BookState state;

  public Book(BookState state) {
    this.state = state;
  }

  public void borrow() throws Exception {
    this.state = this.state.borrowBook();
  }

  public void return() throws Exception {
    this.state = this.state.returnBook();
  }

  public BookState getState() {
    return state;
  }
}
