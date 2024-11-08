package vn.edu.huce.beforeigner.domains.system;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Sysvar extends FullAuditedEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private SysvarKey sysvarKey;
    
    @Column(nullable = false)
    private String sysvarValue;

    public Sysvar(SysvarKey sysvarKey, String sysvarValue) {
        this.sysvarKey = sysvarKey;
        this.sysvarValue = sysvarValue;
    }
}
