package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_326.Service.CommentService;
import kr.hs.dgsw.web_01_326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public List<CommentUsernameProtocol> list() {
        return this.commentService.listAllComments();
    }

    @PostMapping("/write")
    public CommentUsernameProtocol write(@RequestBody Comment comment)
    {
        return this.commentService.write(comment);
    }

    @PutMapping("/modify/{id}")
    public Comment modify(@PathVariable Long id, @RequestBody Comment comment)
    {
        return this.commentService.modify(id, comment);
    }

    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Long id)
    {
        return this.commentService.remove(id);
    }
}
