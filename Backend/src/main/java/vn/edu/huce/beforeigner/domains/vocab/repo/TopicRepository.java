package vn.edu.huce.beforeigner.domains.vocab.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.vocab.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    
    @Query("SELECT t FROM Topic t WHERE t.id IN :ids")
    Set<Topic> findAllByIdIn(@Param("ids") List<Integer> ids);
}
