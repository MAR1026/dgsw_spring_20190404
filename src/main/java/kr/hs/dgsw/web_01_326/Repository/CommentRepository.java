package kr.hs.dgsw.web_01_326.Repository;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>
{
    void deleteById(Long id);
}
