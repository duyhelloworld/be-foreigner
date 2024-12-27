package vn.edu.huce.beforeigner.domains.core;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.NoDeleteAuditedEntity;
import lombok.Getter;

@Getter
@Setter
@Entity
public class AccountToken extends NoDeleteAuditedEntity {
    
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Mã token
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String token;

    /**
     * Loại token
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType type;

    /**
     * Token hết hạn lúc ?
     */
    private LocalDateTime expiredAt;
}