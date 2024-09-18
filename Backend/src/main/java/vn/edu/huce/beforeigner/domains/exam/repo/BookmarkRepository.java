package vn.edu.huce.beforeigner.domains.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.exam.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    
}
