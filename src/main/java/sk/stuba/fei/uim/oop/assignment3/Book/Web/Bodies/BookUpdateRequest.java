package sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies;

import lombok.Data;

@Data
public class BookUpdateRequest {
    private String name;
    private String description;
    private Long author;
    private int pages;
}
