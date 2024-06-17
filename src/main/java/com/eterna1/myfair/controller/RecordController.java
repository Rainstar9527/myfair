package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.RecordService;
import com.eterna1.myfair.vo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RecordController {
    @Autowired
    RecordService recordService;

    @PostMapping("/getAllRecord")
    public Result selectAllRecord(){
        Result result = new Result();
        List<Object> records = recordService.selectAllRecord();
        result.setData(records);
        result.setFlag(true);
        result.setMessage("Success");
        return result;
    }

    @PostMapping("/addRecord")
    public Result addRecord(@RequestBody Record record){
        Result result = new Result();
        recordService.addRecord(record);
        result.setFlag(true);
        result.setMessage("Success");
        return result;
    }
}
