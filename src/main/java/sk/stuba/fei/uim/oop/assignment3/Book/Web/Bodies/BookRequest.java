package sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies;

import lombok.Data;

@Data
public class BookRequest {
    private Long id;
    private String name;
    private String description;
    private Long Author;
    private int pages;
    private int amount;
    private int lendCount;
}
