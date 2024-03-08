public class Main {

  public static void main(String[] args) {
    Library myLibrary = new Library();
    User alvaro = new User("Alvaro", "Barrero", 1);
    User alfredo = new User("Alfredo", "Diaz", 2);
    alvaro.requestPartnership(myLibrary);
    alfredo.requestPartnership(myLibrary);

    alvaro.requestBook(1);
    alfredo.requestBook(2);
    myLibrary.printAllBooks();
  }
}
