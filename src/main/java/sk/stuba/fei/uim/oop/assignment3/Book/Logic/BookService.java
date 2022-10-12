package sk.stuba.fei.uim.oop.assignment3.Book.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Author.Data.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Logic.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository repository;

    @Autowired
    private AuthorService authorService;


    @Override
    public List<Book>getAll(){
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) throws NotFoundException {
        Author author=this.authorService.getById(request.getAuthor());
        Book book =this.repository.save(new Book(request,author));
        author.getBooks().add(book);
        book.setAuthor(author);
        return this.save(book);
    }

    @Override
    public Book getById(Long id) throws NotFoundException {
        Book b=this.repository.findBookById(id);
        if(b==null){
            throw new NotFoundException();
        }
        return b;
    }

    @Override
    public Book updateBook(Long id, BookUpdateRequest newBook) throws NotFoundException {
        Book old=this.getById(id);
        if(newBook.getName() != null){
            old.setName(newBook.getName());
        }
        if(newBook.getDescription()!= null){
            old.setDescription(newBook.getDescription());
        }
        if(newBook.getAuthor()!=null && newBook.getAuthor()!=0 ){
            this.authorService.updateBook(old,old.getAuthor().getId(), newBook.getAuthor());
            old.setAuthor(this.authorService.getById(newBook.getAuthor()));
        }
        if(newBook.getPages()!=0){
            old.setPages(newBook.getPages());
        }
        return this.repository.save(old);
    }

    @Override
    public void deleteBook(Long id) throws NotFoundException {
        this.authorService.deleteBook(this.getById(id));
    }

    @Override
    public int getAmount(Long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(Long id,int increment) throws NotFoundException {
        Book b=getById(id);
        b.setAmount(b.getAmount()+ increment);
        b=this.repository.save(b);
        return b.getAmount();
    }

    @Override
    public int getLendCount(Long id) throws NotFoundException {
        return this.getById(id).getLendCount();
    }

    @Override
    public Book save(Book old){
        return this.repository.save(old);
    }
}