package vn.edu.huce.beforeigner.domains.roadmap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.roadmap.Roadmap;

@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap, Integer> {
    
}
