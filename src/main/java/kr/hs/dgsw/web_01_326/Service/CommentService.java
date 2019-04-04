package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllComments();

    CommentUsernameProtocol write(Comment comment);

    Comment modify(Long id, Comment comment);

    Comment searchComment(Long id);

    boolean remove(Long id);
}
