package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Attachment;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    public Attachment upload(MultipartFile srcFile);
    public HttpServletResponse download(HttpServletResponse response, String type, Long id);
}
