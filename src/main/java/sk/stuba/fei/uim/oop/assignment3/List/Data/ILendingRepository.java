package sk.stuba.fei.uim.oop.assignment3.List.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILendingRepository extends JpaRepository<Lending,Long> {
    List<Lending> findAll();
    Lending findLendingListById(Long id);
}
