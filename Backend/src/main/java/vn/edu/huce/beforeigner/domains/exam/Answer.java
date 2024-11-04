package vn.edu.huce.beforeigner.domains.exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@Entity
public class Answer extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Lưu nghĩa /từ / cặp nghĩa từ trong câu nối
     * Phân biệt qua dấu -
     */
    @Column(columnDefinition = "TEXT")
    private String txt;

    private String audio;

    private String image;

    /**
     * Câu hỏi chọn
     */
    private Boolean isTrue;

    /**
     * Câu hỏi sắp xếp
     */
    private Integer index;

    @ManyToOne
    private Question question;
}
