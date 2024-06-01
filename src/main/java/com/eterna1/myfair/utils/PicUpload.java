package com.eterna1.myfair.utils;

import com.eterna1.myfair.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class PicUpload {

    @Value("${pictureFile.path}")
    private String picturePath;

    @Value("${pictureFile.path-mapping}")
    private String picturePathMapping;

    public Result imgUpload(MultipartFile file) throws IOException {
        Result result = new Result();

        File filePath = new File(picturePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File myfile = new File(filePath, file.getOriginalFilename());
        file.transferTo(myfile);

        result.setMessage("success");
        result.setFlag(true);
        String fileName = picturePathMapping+file.getOriginalFilename();
        result.setData(fileName);

        return result;
    }
}
