package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.QueryPageEntity;
import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.FactoryService;
import com.eterna1.myfair.utils.PicUpload;
import com.eterna1.myfair.vo.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class FactoryController {
    @Autowired
    FactoryService factoryService;
    @Autowired
    PicUpload picUpload;
//    @PostMapping("/getByPage")
//    public Result selectByPage(@RequestBody QueryPageEntity queryPageEntity) {
//        Result result = new Result();
//        List<Factory> factories = factoryService.selectByPage(queryPageEntity);
//
//        result.setFlag(true);
//        result.setData(factories);
//        return result;
//    }

    @RequestMapping("/getAllFactory")
    public Result selectAllFactory()
    {
        Result result = new Result();
        List<Factory> factories = factoryService.selectAllFactory();

        result.setFlag(true);
        result.setData(factories);
        result.setMessage("ok");
        return result;
    }

    @RequestMapping("/searchFactory")
    public Result searchFactory(@RequestBody Map<String, String> map) {
        Result result = new Result();
        String selection = map.get("selection");
        String inputString = map.get("inputString");

        List<Factory> factories = factoryService.searchFactory(selection, inputString);
        result.setFlag(true);
        result.setData(factories);
        result.setMessage("ok");

        return result;
    }

    @PostMapping("/addFactory")
    public Result addFactory(@RequestBody Factory factory) {
        Result result = new Result();
        factoryService.insertSelective(factory);
        result.setFlag(true);
        result.setMessage("success");
        return result;
    }

    @RequestMapping("/addImg")
    public Result addImage(MultipartFile file) throws IOException {
        Result result = picUpload.imgUpload(file);
        return result;
    }
}
