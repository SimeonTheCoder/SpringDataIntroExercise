package app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long author_id;

    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    @OneToMany(targetEntity = Book.class)
    private List<Book> books;

    public Author() {
        books = new ArrayList<>();
    }

    public Author(long author_id, String first_name, String last_name, List<Book> books) {
        this.author_id = author_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.books = books;
    }

    public long getId() {
        return author_id;
    }

    public void setId(long id) {
        this.author_id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
