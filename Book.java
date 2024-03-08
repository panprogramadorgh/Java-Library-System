public class Book {

  public String title;
  public String author;
  public java.time.LocalDate publishDate;
  public String genre;
  public int id;

  public Book(
    String title,
    String author,
    java.time.LocalDate publishDate,
    String genre,
    int id
  ) {
    this.title = title;
    this.author = author;
    this.publishDate = publishDate;
    this.genre = genre;
    this.id = id;
  }

  @Override
  public String toString() {
    return (
      "Book{" +
      "title='" +
      title +
      '\'' +
      ", author='" +
      author +
      '\'' +
      ", publishDate=" +
      publishDate +
      ", genre='" +
      genre +
      '\'' +
      ", id='" +
      id +
      '\'' +
      '}'
    );
  }
}
