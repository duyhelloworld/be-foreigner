package vn.edu.huce.beforeigner.domains.base;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CronjobAuditedEntity {
    
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public LocalDateTime getLastUpdatedAt() {
        return updatedAt == null ? createdAt : updatedAt;
    }
}
