package vn.edu.huce.beforeigner.domains.vocab;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.FullAuditedEntity;
import vn.edu.huce.beforeigner.domains.exam.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Sentense extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * Câu tiếng Anh
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String value;

    /**
     * Ý nghĩa câu
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mean;

    /**
     * Các câu hỏi có dùng câu này
     */
    @OneToMany(mappedBy = "sentense", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    /**
     * Câu làm ví dụ cho các từ nào
     */
    @ManyToMany(mappedBy = "sentenses", cascade = CascadeType.ALL)
    private Set<Word> words = new HashSet<>();
}