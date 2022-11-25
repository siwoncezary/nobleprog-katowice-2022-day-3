package pl.vattenfall.app.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.vattenfall.app.data.dto.TitleAndAuthor;
import pl.vattenfall.app.data.entity.Book;

import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select b.title, b.author from Book b", nativeQuery = true)
    List<TitleAndAuthor> findBooksWithTitleAndAuthor();

    Page<Book> findBookByEditionYear(int year, PageRequest request);
    @Query(value = "from book b", nativeQuery = false)
    Page<Book> findPaged(PageRequest page);
    @Query(value = "select * from book where id=?", nativeQuery = true)
    List<Map<String, Object>> findObjects(Long id);
}
