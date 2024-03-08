import java.time.LocalDate;
import java.util.ArrayList;

public class Library {

  public final int MAX_PARTNERS = 10;
  private ArrayList<Book> books = new ArrayList<Book>();
  private ArrayList<User> partners = new ArrayList<User>();

  public Library() {
    this.initBooks();
  }

  /* -------------------- */

  private ArrayList<User> getPartners() {
    return new ArrayList<User>(this.partners);
  }

  private ArrayList<User> getPartners(int id) {
    ArrayList<User> filteredPartners = new ArrayList<User>(this.partners);
    for (User partner : this.partners) {
      if (partner.id == id) {
        filteredPartners.add(partner);
      }
    }
    return filteredPartners;
  }

  /* -------------------- */
  public void printPartners() {
    ArrayList<User> filtered = this.getPartners();
    if (filtered.size() < 1) {
      System.out.println("There is no partners");
      return;
    }
    for (int i = 0; i < filtered.size(); i++) {
      User partner = filtered.get(i);
      System.out.println(partner);
    }
  }

  public void printPartners(int id) {
    ArrayList<User> filtered = this.getPartners(id);
    if (filtered.size() < 1) {
      System.out.println("There is no partners");
      return;
    }
    for (int i = 0; i < filtered.size(); i++) {
      User partner = this.partners.get(i);
      System.out.println(partner);
    }
  }

  /* -------------------- */
  public void printAllBooks() {
    System.out.println("Libros disponibles:\n");
    for (Book book : this.getBooks()) {
      System.out.println(book.toString());
    }
    System.out.println("------------------");
    System.out.println("Libros alquilados:\n");
    for (User partner : this.getPartners()) {
      System.out.println(partner.toString());
      for (Book partnerBook : partner.getHiredBooks()) {
        System.out.println("\t" + partnerBook.toString());
      }
      System.out.println("\n");
    }
  }

  private ArrayList<Book> getBooks() {
    return new ArrayList<Book>(this.books);
  }

  private ArrayList<Book> getBooks(int id) {
    ArrayList<Book> filteredPartners = new ArrayList<Book>();
    for (Book book : this.books) {
      if (book.id == id) {
        filteredPartners.add(book);
      }
    }
    return filteredPartners;
  }

  /* -------------------- */
  public Book hireBook(Book book) throws Error {
    try {
      this.books.remove(book);
    } catch (Exception e) {
      throw new Error("Book not found");
    }
    return book;
  }

  /* -------------------- */

  public void printBooks() {
    ArrayList<Book> filtered = this.getBooks();
    if (filtered.size() < 1) {
      System.out.println("There is no books");
      return;
    }
    for (int i = 0; i < filtered.size(); i++) {
      Book book = filtered.get(i);
      System.out.println(book);
    }
  }

  public void printBooks(int id) {
    ArrayList<Book> filtered = this.getBooks(id);
    if (filtered.size() < 1) {
      System.out.println("There is no books");
      return;
    }
    for (int i = 0; i < filtered.size(); i++) {
      Book book = filtered.get(i);
      System.out.println(book);
    }
  }

  /* -------------------- */

  public ArrayList<Book> addBook(Book book) {
    this.books.add(book);
    return this.getBooks();
  }

  public ArrayList<Book> removeBook(Book book) {
    this.books.remove(book);
    return this.getBooks();
  }

  /* -------------------- */

  public Book requestBook(User user, int id) throws Error {
    if (user.library != this) {
      throw new Error("User is not partner of this library");
    }
    Book book;
    try {
      book = this.getBooks(id).get(0);
    } catch (Exception e) {
      throw new Error("Book not found");
    }
    // Otra paradigma seria no cambiar de lado la informacion, si no que los libros tuviesen una propiedad booleana isHired para posteriormente hacer una referencia dentro de cada usuario al nombre o id del libro.
    user.addHiredBook(this.hireBook(book));
    return book;
  }

  public ArrayList<Book> returnBook(User user, int id) throws Error {
    Book book = user.getHiredBook(id);
    // Adds the book to the library
    this.addBook(book);
    // Removes the book from the user
    user.removeHiredBook(book);
    return this.getBooks();
  }

  /* -------------------- */

  private void initBooks() {
    this.books.add(
        new Book(
          "La riqueza de las naciones",
          "Adam Smith",
          LocalDate.ofYearDay(1776, 15),
          "Economia",
          1
        )
      );
    this.books.add(
        new Book(
          "Dos tratados sobre el gobierno civil",
          "John Locke",
          LocalDate.ofYearDay(1689, 50),
          "Philosophy and politics",
          2
        )
      );
    this.books.add(
        new Book(
          "12 Reglas para la vida",
          "Jordan Bernt Peterson",
          LocalDate.ofYearDay(2018, 170),
          "Personal development",
          3
        )
      );
  }

  /* -------------------- */

  public void addPartner(User user) throws Error {
    boolean maximumExceeded = this.getPartners().size() > this.MAX_PARTNERS;
    if (user.library != null && user.library instanceof Library) {
      throw new Error(
        "The user it's already in the partners list of any library"
      );
    }
    if (maximumExceeded) {
      throw new Error("Maximum of partners it's been exceed");
    }
    user.library = this;
    this.partners.add(user);
  }
}
