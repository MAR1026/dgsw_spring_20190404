package kr.hs.dgsw.web_01_326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
    @Id
    private String storedPath;

    private String originalName;

    public Attachment(String storedPath, String originalName) {
        this.storedPath = storedPath;
        this.originalName = originalName;
    }
}
