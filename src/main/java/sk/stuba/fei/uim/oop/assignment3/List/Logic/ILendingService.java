package sk.stuba.fei.uim.oop.assignment3.List.Logic;

import sk.stuba.fei.uim.oop.assignment3.Exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.List.Web.Bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.List.Data.Lending;

import java.util.List;

public interface ILendingService {
    List<Lending> getAll();
    Lending create();
    Lending getById(Long id) throws NotFoundException;
    void deleteLendingList(Long id)throws NotFoundException;
    Lending addBookToLendingList(BookIdRequest bookId, Long id) throws NotFoundException, BadRequestException;
    void removeBook(BookIdRequest bookId,Long id) throws NotFoundException;
    void lendBooks(Long id) throws NotFoundException, BadRequestException;
}
