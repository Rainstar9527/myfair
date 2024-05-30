package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.QueryPageEntity;
import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.FactoryService;
import com.eterna1.myfair.vo.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class FactoryController {
    @Autowired
    FactoryService factoryService;
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
}
