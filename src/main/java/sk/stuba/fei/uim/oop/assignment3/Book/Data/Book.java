package sk.stuba.fei.uim.oop.assignment3.Book.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.Author.Data.Author;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookRequest;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int pages;
    private int amount;
    private int lendCount;
    @ManyToOne
    private Author author;

    public Book(BookRequest request, Author author){
        this.name= request.getName();
        this.description= request.getDescription();
        this.pages=request.getPages();
        this.amount= request.getAmount();
        this.lendCount= request.getLendCount();
        this.author=author;
    }
}
