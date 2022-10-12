package sk.stuba.fei.uim.oop.assignment3.Book.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Book.Logic.BookService;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.*;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return this.service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable("id")Long id) throws NotFoundException {
        return new BookResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public BookResponse updateBookById(@RequestBody BookUpdateRequest newBook, @PathVariable("id")Long id) throws NotFoundException {
        return new BookResponse(this.service.updateBook(id,newBook));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id")Long id) throws NotFoundException {
        this.service.deleteBook(id);
    }

    @GetMapping("/{id}/amount")
    public Amount getBookAmount(@PathVariable("id")Long id) throws NotFoundException {
        return  new Amount(this.service.getAmount(id));
    }

    @PostMapping("/{id}/amount")
    public Amount addBookAmount(@PathVariable("id")Long id,@RequestBody Amount request) throws NotFoundException {
        return new Amount(this.service.addAmount(id,request.getAmount()));
    }

    @GetMapping("{id}/lendCount")
    public LendCount getBookLend(@PathVariable("id")Long id) throws NotFoundException {
        return new LendCount(this.service.getLendCount(id));
    }
}
