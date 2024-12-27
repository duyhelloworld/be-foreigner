package vn.edu.huce.beforeigner.domains.streak;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.NoDeleteAuditedEntity;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Streak extends NoDeleteAuditedEntity {

     /**
     * Mã người dùng
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
     /**
     * Số chuỗi ngày học
     */
    @Column(nullable = false)
    private Integer currentStreak;

    /**
     * Số chuỗi cao nhất
     */
    @Column(nullable = false)
    private Integer highestStreak;

    /**
     * True nếu đã tăng streak, false nếu chưa
     */
    @Column(nullable = false)
    private boolean isPlusStreak;

    public void plusStreak() {
        this.currentStreak++;
    }
}
