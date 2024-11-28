package vn.edu.huce.beforeigner.domains.core;

import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@SoftDelete(columnName = "is_disabled")
@EntityListeners(AuditingEntityListener.class)
public class UserToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType type;

    @CreatedDate
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @CreatedBy
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(nullable = false, insertable=false, updatable=false)
    private boolean isDisabled = false;
}