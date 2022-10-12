package sk.stuba.fei.uim.oop.assignment3.List.Data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.Book.Data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Lending {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Book> listLended;
    private boolean lended;

    public Lending(){
        listLended=new ArrayList<>();
    }
}
