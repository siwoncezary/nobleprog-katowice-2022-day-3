package pl.vattenfall.app.data.service;



import pl.vattenfall.app.data.entity.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    Book findById(long id);

    List<Book> findAll();

    void deleteById(long id);

    void update(Book book);
}
