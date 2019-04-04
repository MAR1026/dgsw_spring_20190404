package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> listAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        //return this.userRepository.findByEmail(user.getEmail())
        //        .map(found -> null)
        //        .orElse(this.userRepository.save(user));

        if(this.userRepository.findByEmail(user.getEmail()).isPresent())
            return null;
        else {
            return this.userRepository.save(user);
        }
    }

    @Override
    public User searchUser(Long userid) {
        Optional<User> found = this.userRepository.findById(userid);
        if(found.isPresent()){
            return found.get();
        } else
            return null;

    }

    @Override
    public User updateUser(Long userid, User user) {
        User found = searchUser(userid);
        if(found != null) {
            found.setUsername(Optional.ofNullable(user.getUsername()).orElse(found.getUsername()));
            found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));

            this.userRepository.save(found);
            return found;
        } else
            return null;
    }

    @Override
    public boolean deleteUser(Long userid) {
        User found = searchUser(userid);
        if(found != null){
            this.userRepository.deleteById(userid);
            return true;
        }
        return false;
    }

    @Override
    public AttachmentProtocol uploadUserImage(Long userid, AttachmentProtocol fileName) {
        User found = searchUser(userid);
        if(found != null) {
            found.setOriginalName(Optional.ofNullable(fileName.getOriginalName()).orElse(found.getOriginalName()));
            found.setStoredPath(Optional.ofNullable(fileName.getStoredPath()).orElse(found.getStoredPath()));
            this.userRepository.save(found);
            return new AttachmentProtocol(found.getStoredPath(), found.getOriginalName());
        }
        return null;
    }


}
