package kr.hs.dgsw.web_01_326.Repository;

import kr.hs.dgsw.web_01_326.Domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {

}
