package vn.edu.huce.beforeigner.domains.roadmap.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.edu.huce.beforeigner.domains.roadmap.RoadmapLesson;
import vn.edu.huce.beforeigner.domains.roadmap.RoadmapLessonId;

@Repository
public interface RoadmapLessonRepository extends JpaRepository<RoadmapLesson, RoadmapLessonId> {
    
}
