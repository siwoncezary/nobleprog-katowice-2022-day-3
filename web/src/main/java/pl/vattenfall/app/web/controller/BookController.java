package pl.vattenfall.app.web.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.vattenfall.app.data.entity.Book;
import pl.vattenfall.app.data.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@SpringBootApplication(scanBasePackages = "pl.vattenfall.app.data")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/index")
    public String indexBook(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/index";
    }

    @GetMapping("/book/create")
    public String create(Model model) {
        model.addAttribute("book", Book.builder().build());
        return "book/create";
    }

    @PostMapping("/book/create")
    public String create(@Validated @ModelAttribute Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "book/create";
        } else {
            bookService.save(book);
            return "redirect:/book/index";
        }
    }

    @GetMapping("/book/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        if (id != null) {
            model.addAttribute("book", bookService.findById(id));
            return "redirect:/book/index";
        } else {
            return "error";//TODO przygotować stronę z błędem
        }
    }

    @PostMapping("/book/update")
    public String update(@Validated @ModelAttribute Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "book/update";
        } else {
            bookService.update(book);
            return "redirect:/book/index";
        }
    }

    @RequestMapping("/forward")
    public void forward(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        request.getRequestDispatcher("/book/create").forward(request, response);
    }


}
