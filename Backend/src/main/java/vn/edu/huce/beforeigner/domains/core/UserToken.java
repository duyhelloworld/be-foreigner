package vn.edu.huce.beforeigner.domains.core;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OwnerAudited;
import lombok.Getter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserToken extends OwnerAudited {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String token;

    private TokenType type;
}