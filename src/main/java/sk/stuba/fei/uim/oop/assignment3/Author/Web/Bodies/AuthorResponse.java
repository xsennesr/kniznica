package sk.stuba.fei.uim.oop.assignment3.Author.Web.Bodies;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Author.Data.Author;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private String surname;
    private List<Long> books;

    public AuthorResponse(Author a){
        books=new ArrayList<>();
        this.id=a.getId();
        this.name=a.getName();
        this.surname=a.getSurname();
        this.books=a.getBooks().stream().map(Book::getId).collect(Collectors.toList());
    }
}