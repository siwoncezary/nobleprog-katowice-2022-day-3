package pl.vattenfall.app.data.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.vattenfall.app.data.entity.Book;
import pl.vattenfall.app.data.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class BookServiceJpa implements BookService {
    private final BookRepository bookRepository;

    public BookServiceJpa(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Book book) {
        final Optional<Book> oBook = bookRepository.findById(book.getId());
        if (oBook.isPresent()) {
            {
                oBook.get().setTitle(book.getTitle());
                oBook.get().setAuthor(book.getAuthor());
                oBook.get().setEditionYear(book.getEditionYear());
            }
        }
    }
}