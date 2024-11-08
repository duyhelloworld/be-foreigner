package vn.edu.huce.beforeigner.domains.exam;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Answer extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Lưu nghĩa / từ
     */
    @Column(columnDefinition = "TEXT")
    private String txt;

    private String audio;

    private String image;

    /**
     * Câu hỏi chọn
     */
    private Boolean isTrue;

    public Answer(String txt, String audio, String image, Boolean isTrue) {
        this.txt = txt;
        this.audio = audio;
        this.image = image;
        this.isTrue = isTrue;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Question question;
}
