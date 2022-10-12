package sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private String description;
    private Long author;
    private int pages;
    private int amount;
    private int lendCount;

    public BookResponse(Book b){
        this.id=b.getId();
        this.name=b.getName();
        this.description=b.getDescription();
        this.author=b.getAuthor() != null ? b.getAuthor().getId() : null;
        this.pages=b.getPages();
        this.amount=b.getAmount();
        this.lendCount=b.getLendCount();
    }
}
