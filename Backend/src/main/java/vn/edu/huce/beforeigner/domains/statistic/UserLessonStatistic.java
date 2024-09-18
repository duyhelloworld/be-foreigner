package vn.edu.huce.beforeigner.domains.statistic;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.core.User;
import vn.edu.huce.beforeigner.domains.exam.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserLessonStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Lesson lesson;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private LessonStatus status;

    @OneToMany(mappedBy = "userLessonStatistic")
    private Set<Attempt> attempts = new HashSet<>();
}
