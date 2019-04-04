package kr.hs.dgsw.web_01_326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String storedPath;
    private String originalName;

    @CreationTimestamp
    private LocalDateTime joined;

    @UpdateTimestamp
    private  LocalDateTime modified;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }


}
