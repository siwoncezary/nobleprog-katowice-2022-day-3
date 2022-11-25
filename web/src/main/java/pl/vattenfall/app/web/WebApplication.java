package pl.vattenfall.app.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.vattenfall.app.data.entity.Book;
import pl.vattenfall.app.data.repository.BookRepository;

@SpringBootApplication(scanBasePackages = "pl.vattenfall.app.data")
public class WebApplication implements CommandLineRunner {

    final BookRepository books;

    public WebApplication(BookRepository books) {
        this.books = books;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        books.save(Book.builder()
                .id(1)
                .title("AAA")
                .author("BBB")
                .build());
        books.save(Book.builder()
                .id(2)
                .title("CCC")
                .author("EEE")
                .build());
        System.out.println(books.findBooksWithTitleAndAuthor());
    }
}
