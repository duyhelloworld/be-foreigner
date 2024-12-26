package vn.edu.huce.beforeigner.domains.vocab.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.vocab.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    @Query(value = "SELECT w.id, w.value, w.mean, w.phonetic, w.image_url, w.audio_url, w.is_deleted, w.created_at, w.created_by, w.audio_public_id, w.image_public_id, w.updated_at, w.updated_by " +
            "FROM word w " +
            "JOIN answer a ON w.id = a.word_id " +
            "JOIN question q ON a.question_id = q.id " +
            "JOIN lesson_history lh ON q.lesson_id = lh.lesson_id " +
            "WHERE lh.owner = :owner " +
            "AND q.type = 'LEARN_WORD' " +
            "AND a.is_true = true " +
            "AND lh.status = 'COMPLETED' " +
            "ORDER BY RAND() " + 
            "LIMIT 1", nativeQuery = true)
    Optional<Word> getLearnedWord(@Param("owner") String owner);
}