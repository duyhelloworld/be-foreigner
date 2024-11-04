package vn.edu.huce.beforeigner.domains.base;

import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
public abstract class FullAuditedEntity {
    
    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(length = 100, nullable = false)
    @CreatedBy
    private String createdBy;
    
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(length = 100, insertable = false)
    @LastModifiedBy
    private String updatedBy;

    public LocalDateTime getLastUpdatedAt() {
        return updatedAt == null ? createdAt : updatedAt;
    }

    @Column(name = "is_deleted")
    @SoftDelete(columnName = "is_deleted")
    private boolean isDeleted = false;
}
