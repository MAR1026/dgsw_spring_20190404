package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService
{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init() {
        if( this.commentRepository.count() > 0) return;

        User user1 = this.userRepository.save(new User("abc1", "abc1@dgsw"));
        User user2 = this.userRepository.save(new User("abc2", "abc2@dgsw"));
        User user3 = this.userRepository.save(new User("abc3", "abc3@dgsw"));
        this.commentRepository.save(new Comment(user1.getId(), "Hi there111"));
        this.commentRepository.save(new Comment(user2.getId(), "Hi there222"));
        this.commentRepository.save(new Comment(user3.getId(), "Hi there333"));
    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<CommentUsernameProtocol> cupList = new ArrayList<>();

        List<Comment> commentList = this.commentRepository.findAll();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = found.isPresent() ? found.get().getUsername() : null;
            cupList.add(new CommentUsernameProtocol(comment, username));
        });
        return cupList;
    }

    @Override
    public CommentUsernameProtocol write(Comment comment) {
        return new CommentUsernameProtocol(
                this.commentRepository.save(comment),
                this.userRepository
                    .findById(comment.getUserId())
                    .map(u -> u.getUsername())
                    .orElse(null));
    }

    @Override
    public Comment modify(Long id, Comment comment) {
        Comment found = searchComment(id);
        if(found != null) {
            found.setContent(Optional.ofNullable(comment.getContent()).orElse(found.getContent()));
            this.commentRepository.save(found);
            return found;
        } else
            return null;
    }

    @Override
    public Comment searchComment(Long id) {
    Optional<Comment> found = this.commentRepository.findById(id);
    if(found.isPresent()){
        return found.get();
    } else
        return null;

}

    @Override
    public boolean remove(Long id) {
        try
        {
            this.commentRepository.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
