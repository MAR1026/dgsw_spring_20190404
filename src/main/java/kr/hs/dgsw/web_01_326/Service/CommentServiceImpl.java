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

        User user1 = this.userRepository.save(new User("김준영", "1@dgsw.hs.kr", "1", "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\2019\\04\\08\\9812a2b5-3ad4-47eb-ab08-ed0172b87c3c_caution.jpg", "caution.jpg"));
        User user2 = this.userRepository.save(new User("김진철", "2@dgsw.hs.kr", "1", "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\2019\\04\\08\\9812a2b5-3ad4-47eb-ab08-ed0172b87c3c_default.png", "default.jpg"));
        User user3 = this.userRepository.save(new User("박근혜", "3@dgsw.hs.kr", "1", "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\2019\\04\\08\\9812a2b5-3ad4-47eb-ab08-ed0172b87c3c_caution.jpg", "caution.jpg"));
        this.commentRepository.save(new Comment(user1.getId(), "Hi there111", "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\2019\\04\\08\\9812a2b5-3ad4-47eb-ab08-ed0172b87c3c_caution.jpg", "caution.jpg"));
        this.commentRepository.save(new Comment(user2.getId(), "Hi there222", "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\2019\\04\\08\\9812a2b5-3ad4-47eb-ab08-ed0172b87c3c_default.png", "default.jpg"));
        this.commentRepository.save(new Comment(user3.getId(), "Hi there333", "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\2019\\04\\08\\9812a2b5-3ad4-47eb-ab08-ed0172b87c3c_caution.jpg", "caution.jpg"));
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
        System.out.println(id);
        Comment found = searchComment(id);
        if(found != null) {
            System.out.println(id +"찾았다");
            found.setContent(Optional.ofNullable(comment.getContent()).orElse(found.getContent()));
            found.setStoredPath(Optional.ofNullable(comment.getStoredPath()).orElse(found.getStoredPath()));
            found.setOriginalName(Optional.ofNullable(comment.getOriginalName()).orElse(found.getOriginalName()));
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
