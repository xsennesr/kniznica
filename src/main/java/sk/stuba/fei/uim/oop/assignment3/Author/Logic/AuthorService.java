package sk.stuba.fei.uim.oop.assignment3.Author.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Author.Data.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.Bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Author.Data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private IAuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author create(AuthorRequest request){
        Author a=new Author();
        a.setName(request.getName());
        a.setSurname(request.getSurname());
        return this.repository.save(a);
    }

    @Override
    public Author getById(Long id) throws NotFoundException {
        Author a =this.repository.findAuthorById(id);
        if(a==null || a.getId()==0){
            throw new NotFoundException();
        }
        return a;
    }

    @Override
    public void deleteAuthor(Long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public Author updateAuthor(Long id, Author newAuthor) throws NotFoundException {
        Author old=getById(id);
        if(Objects.nonNull(newAuthor.getName())){
            old.setName(newAuthor.getName());
        }
        if(Objects.nonNull(newAuthor.getSurname())){
            old.setSurname(newAuthor.getSurname());
        }
        old=this.repository.save(old);
        return old;
    }

    @Override
    public void deleteBook(Book book) throws NotFoundException {
        Author a=this.getById(book.getAuthor().getId());
        a.getBooks().remove(book);
        this.repository.save(a);
    }

    @Override
    public void updateBook(Book book, Long oldAuthor, Long newAuthor)throws NotFoundException {
        Author oldA=this.getById(oldAuthor);
        Author newA=this.getById(newAuthor);
        newA.getBooks().add(book);
        oldA.getBooks().remove(book);
        this.repository.save(oldA);
        this.repository.save(newA);
    }
}
