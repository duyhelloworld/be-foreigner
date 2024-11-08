package vn.edu.huce.beforeigner.domains.core;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.OwnerAuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserToken extends OwnerAuditedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String token;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType type;

    public UserToken(String token, TokenType type) {
        this.token = token;
        this.type = type;
    }
    
}