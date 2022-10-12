package sk.stuba.fei.uim.oop.assignment3.Author.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.Author.Data.Author;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.Bodies.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.Author.Web.Bodies.AuthorResponse;
import sk.stuba.fei.uim.oop.assignment3.Author.Logic.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.Exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping()
    public List<AuthorResponse> getAllAuthors(){
        return this.service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest request){
        return new ResponseEntity<>(new AuthorResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable("id") Long id) throws NotFoundException {
        return new AuthorResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public AuthorResponse updateAuthorById(@RequestBody Author newAuthor, @PathVariable("id") Long id) throws NotFoundException {
        return new AuthorResponse(this.service.updateAuthor(id,newAuthor));
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") Long id) throws NotFoundException {
        this.service.deleteAuthor(id);
    }
}
