package vn.edu.huce.beforeigner.domains.vocab;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.huce.beforeigner.domains.base.CloudinaryImage;
import vn.edu.huce.beforeigner.domains.base.FullAudited;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Topic extends FullAudited implements CloudinaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Tên chủ đề
     */
    private String name;
    
    /**
     * Ảnh bìa
     */
    private String coverImage;

    /**
     * Mô tả
     */
    private String description;

    private String publicId;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Word> words = new HashSet<>();

    public Topic(String name, String description, String coverImage) {
        this.name = name;
        this.description = description;
        this.coverImage = coverImage;
    }

    
}
