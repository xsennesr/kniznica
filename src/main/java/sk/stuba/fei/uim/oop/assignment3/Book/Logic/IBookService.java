package sk.stuba.fei.uim.oop.assignment3.Book.Logic;

import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();
    Book create(BookRequest request) throws NotFoundException;
    Book getById(Long id) throws NotFoundException;
    Book updateBook(Long id, BookUpdateRequest newBook) throws NotFoundException;
    void deleteBook(Long id) throws NotFoundException;
    int getAmount(Long id) throws NotFoundException;
    int addAmount(Long id,int increment) throws NotFoundException;
    int getLendCount(Long id) throws NotFoundException;
    Book save(Book old);
}