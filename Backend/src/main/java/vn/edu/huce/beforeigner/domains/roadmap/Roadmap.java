package vn.edu.huce.beforeigner.domains.roadmap;

import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Lộ trình
 */
public class Roadmap extends FullAuditedEntity {

    /**
     * Mã lộ trình
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Tên lộ trình
     */
    @Column(nullable = false)
    private String name;

    /**
     * Mô tả lộ trình
     */
    @Lob
    private String description;

    /**
     * Số từ vựng mục tiêu đạt được
     */
    @Column(nullable = false)
    private Integer wordCountTarget;
    
    /**
     * Các bài học trong lộ trình
     */
    @OneToMany(mappedBy = "roadmap", cascade = CascadeType.ALL)
    private Set<RoadmapLesson> roadmapLessons = new HashSet<>();
}
