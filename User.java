import java.util.ArrayList;

public class User {

  public Library library = null;
  public String name;
  public String lastname;
  public Integer id;
  private ArrayList<Book> hiredBooks = new ArrayList<Book>();

  public User(String name, String lastname, Integer id) {
    this.name = name;
    this.lastname = lastname;
    this.id = id;
  }

  // A diferencia del metodo de encontrar libros de la biblioteca, este devuelve el primer y unico libro con el id
  public Book getHiredBook(int id) throws Error {
    Book book = null;
    for (Book hiredBook : this.getHiredBooks()) {
      if (hiredBook.id == id) {
        book = hiredBook;
        break;
      }
    }
    if (book == null) {
      throw new Error("Book not found");
    }
    return book;
  }

  public ArrayList<Book> getHiredBooks() {
    return new ArrayList<Book>(this.hiredBooks);
  }

  public ArrayList<Book> addHiredBook(Book newBook) {
    this.hiredBooks.add(newBook);
    return this.getHiredBooks();
  }

  public ArrayList<Book> removeHiredBook(Book book) {
    this.hiredBooks.remove(book);
    return this.getHiredBooks();
  }

  public void printHiredBooks() {
    for (Book hiredBook : this.getHiredBooks()) {
      System.out.println(hiredBook.toString());
    }
  }

  public void requestPartnership(Library library) throws Error {
    library.addPartner(this);
  }

  public Book requestBook(int id) throws Error {
    Book book;
    if (this.library != null && this.library instanceof Library) {
      book = this.library.requestBook(this, id);
    } else {
      throw new Error("User must be partner of any library");
    }
    return book;
  }

  public ArrayList<Book> returnBook(int id) {
    this.getHiredBook(id);
    return this.library.returnBook(this, id);
  }

  @Override
  public String toString() {
    return (
      "Customer{" +
      "name='" +
      this.name +
      '\'' +
      ", lastname='" +
      this.lastname +
      '\'' +
      ", id=" +
      this.id +
      ", library=" +
      "\'" +
      this.library +
      "\'" +
      '}'
    );
  }
}
