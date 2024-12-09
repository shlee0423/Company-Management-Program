package com.team;

import com.team.domain.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/upload")
    public String upload(Model model) {
        ImageDTO image = fileService.getFile();

        if(image != null) {
            model.addAttribute("image", image);
        }
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("data") MultipartFile data,
            Model model
    ){
        try {
            fileService.saveFile(data.getOriginalFilename(), data.getBytes());
            model.addAttribute("message", "File uploaded successfully!");

        }catch(Exception e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }
        return "upload";
    }

}
