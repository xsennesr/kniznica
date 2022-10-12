package sk.stuba.fei.uim.oop.assignment3.List.Logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;
import sk.stuba.fei.uim.oop.assignment3.Book.Logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.Exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.List.Web.Bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.List.Data.ILendingRepository;
import sk.stuba.fei.uim.oop.assignment3.List.Data.Lending;

import java.util.List;

@Service
public class LendingService implements ILendingService {

    @Autowired
    private ILendingRepository repository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<Lending> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Lending create() {
        Lending lendingList=new Lending();
        return this.repository.save(lendingList);
    }

    @Override
    public Lending getById(Long id) throws NotFoundException{
        Lending l=this.repository.findLendingListById(id);
        if(l==null){
            throw new NotFoundException();
        }
        return l;
    }

    @Override
    public void deleteLendingList(Long id) throws NotFoundException {
        Lending l=getById(id);
        List<Book> allBooks=this.bookService.getAll();
        List<Book> books = l.getListLended();
        for(Book b1:allBooks) {
            for (Book b2 : books) {
                if (b1.getId().equals(b2.getId())) {
                    b1.setLendCount(b1.getLendCount() - 1);
                    this.bookService.save(b1);
                }
            }
        }
        this.repository.delete(l);
    }

    @Override
    public Lending addBookToLendingList(BookIdRequest bookId, Long id) throws NotFoundException, BadRequestException {
        Book b=this.bookService.getById(bookId.getId());
        Lending l=getById(id);
        if(l.isLended() || b == null || b.getAmount() < 1 || l.getListLended().contains(b)){
            throw new BadRequestException();
        }
        l.getListLended().add(b);
        return this.repository.save(l);
    }

    @Override
    public void removeBook(BookIdRequest bookId, Long id) throws NotFoundException {
        Lending l=getById(id);
        List<Book> list=l.getListLended();
        list.removeIf(b -> b.getId().equals(bookId.getId()));
        this.repository.save(l);
    }

    @Override
    public void lendBooks(Long id) throws NotFoundException, BadRequestException {
        Lending l=getById(id);
        if(l.isLended()){
            throw new BadRequestException();
        }
        l.setLended(true);
        List<Book> books=l.getListLended();
        for(Book b:books){
            b.setLendCount(b.getLendCount()+1);
        }
        this.repository.save(l);
    }
}
