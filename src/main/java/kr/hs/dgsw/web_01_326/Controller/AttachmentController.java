package kr.hs.dgsw.web_01_326.Controller;


import kr.hs.dgsw.web_01_326.Domain.Attachment;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_326.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/attachment")
    public Attachment upload(@RequestPart MultipartFile srcFile){
        return attachmentService.upload(srcFile);
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable Long id) {
        response = attachmentService.download(response, type, id);
    }
}
