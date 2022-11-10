package app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @OneToMany(targetEntity = Book.class)
    private List<Book> books;

    public Category() {
        books = new ArrayList<>();
    }

    public Category(long category_id, String name, List<Book> books) {
        this.category_id = category_id;
        this.name = name;
        this.books = books;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
