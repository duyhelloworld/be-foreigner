package vn.edu.huce.beforeigner.domains.exam;

import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.core.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;
    
    @ManyToOne
    private Question question;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @SoftDelete(columnName = "is_deleted")
    private Boolean isDeleted = false;
}
