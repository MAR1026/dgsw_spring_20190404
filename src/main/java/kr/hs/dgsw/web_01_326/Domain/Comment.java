package kr.hs.dgsw.web_01_326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;

    private String storedPath;
    private String originalName;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(Comment c) {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.content = c.getContent();
        this.created = c.getCreated();
        this.modified = c.getModified();
    }

    public Comment(Long userId, String content, String storedPath, String originalName) {
        this.userId = userId;
        this.content = content;
        this.storedPath = storedPath;
        this.originalName = originalName;
    }
}
