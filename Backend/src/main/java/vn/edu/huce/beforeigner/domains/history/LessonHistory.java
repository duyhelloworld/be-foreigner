package vn.edu.huce.beforeigner.domains.history;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAudited;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import lombok.Getter;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class LessonHistory extends FullAudited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Lesson lesson;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;
}
