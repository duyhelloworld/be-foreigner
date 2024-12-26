package vn.edu.huce.beforeigner.domains.vocab;

import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.exam.Answer;
import vn.edu.huce.beforeigner.domains.exam.Question;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

@Getter
@Setter
@Entity
@NoArgsConstructor
/**
 * Từ vựng
 */
public class Word extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Từ vựng
     */
    @Column(nullable = false)
    private String value;

    /**
     * Nghĩa của từ  
     */
    @Column(nullable = false)
    private String mean;

    /**
     * Phiên âm
     */
    @Column(nullable = false)
    private String phonetic;

     /**
     * File âm thanh
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String audioUrl;

    /**
     * Mã file âm thanh
     */
    private String audioPublicId;

     /**
     * File ảnh 
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    /**
     * Mã fila ảnh
     */
    private String imagePublicId;

    /**
     * Các câu ví dụ sử dụng từ này
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn())
    private Set<Sentense> sentenses = new HashSet<>();

    /**
     * Các câu hỏi dùng từ này
     */
    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();
    
    /**
     * Các câu trả lời dùng từ này
     */
    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();
}
