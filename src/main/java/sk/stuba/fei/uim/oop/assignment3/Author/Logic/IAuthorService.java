package sk.stuba.fei.uim.oop.assignment3.Author.Logic;

import sk.stuba.fei.uim.oop.assignment3.Author.Data.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.Bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

import java.util.List;

public interface IAuthorService {
    List<Author> getAll();
    Author create(AuthorRequest request);
    Author getById(Long id) throws NotFoundException;
    void deleteAuthor(Long id) throws NotFoundException;
    Author updateAuthor(Long id, Author newAuthor) throws NotFoundException;
    void deleteBook(Book book) throws NotFoundException;
    void updateBook(Book book, Long oldAuthor, Long newAuthor)throws NotFoundException;
}
