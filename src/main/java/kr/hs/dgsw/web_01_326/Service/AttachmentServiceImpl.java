package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Attachment;
import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Repository.AttachmentRepository;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Attachment upload(MultipartFile srcFile) {
        String destFilename = "C:\\Users\\User\\IdeaProjects\\web_01_326\\upload\\" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/")) + UUID.randomUUID().toString() + "_" + srcFile.getOriginalFilename();

        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);

            Attachment savedAttachment = new Attachment(destFilename, srcFile.getOriginalFilename());
            attachmentRepository.save(savedAttachment);

            return savedAttachment;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public HttpServletResponse download(HttpServletResponse response, String type, Long id) {
        try
        {
            String filepath = "";
            String filename = "";

            switch (type) {
                case "user" :
                    User user = userRepository.findById(id).orElse(null);

                    if(user != null) {
                        filepath = user.getStoredPath();
                        filename = user.getOriginalName();
                    }
                    break;

                case "comment" :
                    Comment comment = commentRepository.findById(id).orElse(null);

                    if(comment != null) {
                        filepath = comment.getStoredPath();
                        filename = comment.getOriginalName();
                    }
                    break;
            }


            File file = new File(filepath);
            if(!file.exists()) {
                return null;
            }

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());

            if(mimeType == null) {
                mimeType = "application/octet-stream";
            }

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int)file.length());

            InputStream is = null;
            is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());

            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
