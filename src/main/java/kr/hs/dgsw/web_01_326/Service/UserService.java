package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;

import java.util.List;

public interface UserService {
    List<User> listAllUsers();
    User addUser(User user);
    User searchUser(Long userid);
    User updateUser(Long userid, User user);
    boolean deleteUser(Long userid);
    AttachmentProtocol uploadUserImage(Long userid, AttachmentProtocol fileName);
}
