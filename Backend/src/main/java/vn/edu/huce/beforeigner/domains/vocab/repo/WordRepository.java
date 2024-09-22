package vn.edu.huce.beforeigner.domains.vocab.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.vocab.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    
    Set<Word> findByIdIn(List<Integer> ids);

    @Query("SELECT w FROM Word w JOIN Topic t WHERE t.id = :id")
    Page<Word> findByTopicId(@Param("id") Integer topicId, PageRequest pageRequest);
}
