package sk.stuba.fei.uim.oop.assignment3.List.Web.Bodies;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookResponse;

import java.util.List;

@Data
public class LendingRequest {
    private Long id;
    private List<BookResponse> listLended;
    private boolean lended;
}


