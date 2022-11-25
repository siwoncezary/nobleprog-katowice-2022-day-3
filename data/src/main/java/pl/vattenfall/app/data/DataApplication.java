package pl.vattenfall.app.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.vattenfall.app.data.entity.Book;
import pl.vattenfall.app.data.repository.BookRepository;

import java.util.List;

@SpringBootApplication
public class DataApplication implements CommandLineRunner {

    final BookRepository books;

    public DataApplication(BookRepository books) {
        this.books = books;
    }

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        books.save(Book.builder()
                .id(1)
                .title("AAA")
                .author("BBB")
                .editionYear(2000)
                .build());
        books.save(Book.builder()
                .id(2)
                .title("CCC")
                .author("EEE")
                .editionYear(2000)
                .build());
        books.findBooksWithTitleAndAuthor().forEach(b -> {
            System.out.println(b.getTitle() +" " + b.getAuthor());
        });
        books.findObjects(1L).forEach(b -> {
            System.out.println(b.get("title"));
        });
        PageRequest request = PageRequest.of(0, 2);
        final Page<Book> paged = books.findPaged(request);
        paged.get().forEach(b -> {
            System.out.println(b.getTitle() +" " + b.getAuthor());
        });
    }
}
