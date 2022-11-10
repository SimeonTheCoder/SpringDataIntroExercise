package app;

import app.entities.*;
import app.repositories.AuthorRepositoryImpl;
import app.repositories.BookRepositoryImpl;
import app.repositories.CategoryRepositoryImpl;
import app.services.AuthorServiceImpl;
import app.services.BookServiceImpl;
import app.services.CategoryServiceImpl;
import app.services.interfaces.AuthorService;
import app.services.interfaces.BookService;
import app.services.interfaces.CategoryService;
import org.springframework.boot.CommandLineRunner;
import app.repositories.interfaces.AuthorRepository;
import app.repositories.interfaces.BookRepository;
import app.repositories.interfaces.CategoryRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsoleRunner implements CommandLineRunner {
    private static final String RESOURCE_PATH = "src/main/resources/data/";
    private static final String BOOKS_FILE_NAME = "books.txt";

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public ConsoleRunner(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository,
                         AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    public ConsoleRunner() {
        this.categoryRepository = new CategoryRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
        this.authorRepository = new AuthorRepositoryImpl();

        this.categoryService = new CategoryServiceImpl();
        this.authorService = new AuthorServiceImpl();
        this.bookService = new BookServiceImpl();
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }

    public void seedDatabase() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

                    LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

                    int copies = Integer.parseInt(data[2]);
                    BigDecimal price = new BigDecimal(data[3]);

                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                    String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));

                    Set<Category> categories = categoryService.getRandomCategories();

                    Book book = new Book(title, editionType, price, releaseDate, ageRestriction, author, categories, copies);
                    bookRepository.save(book);
                });
    }
}
