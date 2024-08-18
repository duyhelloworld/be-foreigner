package vn.edu.huce.beforeigner.repositories;

import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.entities.learn.Category;

@Repository
public interface CategoryRepo extends AuditedRepo<Category> {
    
}
