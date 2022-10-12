package sk.stuba.fei.uim.oop.assignment3.Author.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository <Author, Long> {
    List<Author> findAll();
    Author findAuthorById(Long id);
}
