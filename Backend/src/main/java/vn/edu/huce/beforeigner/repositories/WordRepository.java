package vn.edu.huce.beforeigner.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Word;
import java.util.List;

@Repository
public interface WordRepository extends AuditedRepository<Word> {
    
    @Query("SELECT w FROM Word w JOIN w.categories c where c.id = ?1")
    List<Word> findByCategoryId(Integer categoryId);
}
