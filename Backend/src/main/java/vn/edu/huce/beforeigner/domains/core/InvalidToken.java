package vn.edu.huce.beforeigner.domains.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(indexes = @Index(columnList = "token, id"))
public class InvalidToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    @OneToOne
    private User user;
}
