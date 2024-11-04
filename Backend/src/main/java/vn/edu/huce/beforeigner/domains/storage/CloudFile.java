package vn.edu.huce.beforeigner.domains.storage;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
/**
 * File cloud
 */
public class CloudFile extends FullAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Đường dẫn public file
     */
    @Column(nullable = false)
    private String url;

    /**
     * Mã file trên cloudinary 
     */
    private String publicId;

    /**
     *  Tên file
     */
    private String filename;

    /**
     * Loại file
     */
    @Enumerated(EnumType.STRING)
    private CloudFileType type;

    public CloudFile(String url, String publicId, String filename, CloudFileType type) {
        this.url = url;
        this.publicId = publicId;
        this.filename = filename;
        this.type = type;
    }
}
