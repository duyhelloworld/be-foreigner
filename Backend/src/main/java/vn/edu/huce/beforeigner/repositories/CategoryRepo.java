package vn.edu.huce.beforeigner.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Category;

@Repository
public interface CategoryRepo extends AuditedRepo<Category> {
    
    Set<Category> findByIdIn(List<Integer> ids);
}
