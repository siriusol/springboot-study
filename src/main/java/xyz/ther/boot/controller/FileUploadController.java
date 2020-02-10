package xyz.ther.boot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class FileUploadController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @PostMapping("/upload")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest request) {
        // uploadFiles 必须与表单的 name 值相同
        List<String> filePaths = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
            String format = simpleDateFormat.format(new Date());
            File folder = new File(realPath + format);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            try {
                uploadFile.transferTo(new File(folder, newName));
                String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + "/uploadFile/" + format + newName;
                filePaths.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败!";
            }
        }
        return filePaths.toString();
    }
}
