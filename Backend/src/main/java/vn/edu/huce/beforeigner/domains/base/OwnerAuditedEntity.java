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
/**
 * Audit nhưng chỉ chủ sở hữu được sửa/xóa
 */
public abstract class OwnerAuditedEntity {

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(length = 100, nullable = false)
    @CreatedBy
    @LastModifiedBy
    private String owner;

    @SoftDelete(columnName = "is_disabled")
    @Column(insertable = false)
    private boolean isDisabled = false;
}
