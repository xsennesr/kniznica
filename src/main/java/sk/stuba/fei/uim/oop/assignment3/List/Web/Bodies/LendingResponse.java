package sk.stuba.fei.uim.oop.assignment3.List.Web.Bodies;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Web.Bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.List.Data.Lending;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LendingResponse {
    private Long id;
    private List<BookResponse> lendingList;
    private boolean lended;

    public LendingResponse(Lending l) {
        this.id=l.getId();
        this.lendingList =l.getListLended().stream().map(BookResponse::new).collect(Collectors.toList());
        this.lended=l.isLended();
    }
}
