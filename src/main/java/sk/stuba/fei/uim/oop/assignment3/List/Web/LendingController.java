package sk.stuba.fei.uim.oop.assignment3.List.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Exception.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.List.Web.Bodies.BookIdRequest;
import sk.stuba.fei.uim.oop.assignment3.List.Web.Bodies.LendingResponse;
import sk.stuba.fei.uim.oop.assignment3.List.Logic.ILendingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingController {
    @Autowired
    private ILendingService service;

    @GetMapping()
    public List<LendingResponse> getAllLendingLists(){
        return this.service.getAll().stream().map(LendingResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<LendingResponse> addList() {
        return new ResponseEntity<>(new LendingResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public LendingResponse getLendingListById(@PathVariable("id")Long id) throws NotFoundException {
        return new LendingResponse(this.service.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteLendingListById(@PathVariable("id")Long id) throws NotFoundException {
        this.service.deleteLendingList(id);
    }

    @PostMapping("/{id}/add")
    public LendingResponse addBookToLendingList(@RequestBody BookIdRequest bookId, @PathVariable("id")Long id) throws NotFoundException, BadRequestException {
        return new LendingResponse(this.service.addBookToLendingList(bookId,id));
    }

    @DeleteMapping("/{id}/remove")
    public void removeBookFromLendingList(@RequestBody BookIdRequest bookId,@PathVariable("id")Long id) throws NotFoundException {
        this.service.removeBook(bookId,id);
    }

    @GetMapping("/{id}/lend")
    public void lendBooksFromLendingList(@PathVariable("id")Long id) throws NotFoundException, BadRequestException {
        this.service.lendBooks(id);
    }
}
