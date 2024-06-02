package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.utils.PicUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ImgController {

    @Autowired
    PicUpload picUpload;
    @RequestMapping("/addImg")
    public Result addImage(MultipartFile file) throws IOException {
        Result result = picUpload.imgUpload(file);
        return result;
    }
}
