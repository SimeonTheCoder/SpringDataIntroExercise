package app.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long book_id;

    @Column(nullable = false)
    private AgeRestriction age_restriction;
    private int copies;
    @Column(nullable = false)
    private String description;
    private EditionType edition_type;
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDate release_date;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "categories")
    private Set<Category> category;

    public Book() {

    }

    public Book(String title, EditionType editionType, BigDecimal price, LocalDate release_date, AgeRestriction ageRestriction,
                Author author, Set<Category> categories, int copies) {
        this.title = title;
        this.edition_type = editionType;
        this.price = price;
        this.release_date = release_date;
        this.age_restriction = ageRestriction;
        this.author = author;
        this.category = categories;
        this.copies = copies;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public AgeRestriction getAge_restriction() {
        return age_restriction;
    }

    public void setAge_restriction(AgeRestriction age_restriction) {
        this.age_restriction = age_restriction;
    }

    public EditionType getEdition_type() {
        return edition_type;
    }

    public void setEdition_type(EditionType edition_type) {
        this.edition_type = edition_type;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}
