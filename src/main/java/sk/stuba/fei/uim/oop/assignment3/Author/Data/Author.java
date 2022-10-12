package sk.stuba.fei.uim.oop.assignment3.Author.Data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Book> books;

    public Author(){
        this.books=new ArrayList<>();
    }
}
