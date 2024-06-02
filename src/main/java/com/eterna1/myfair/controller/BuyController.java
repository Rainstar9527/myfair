package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.BuyService;
import com.eterna1.myfair.utils.PicUpload;
import com.eterna1.myfair.vo.Buy;
import com.eterna1.myfair.vo.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BuyController {
    @Autowired
    BuyService buyService;
    @Autowired
    PicUpload picUpload;

    @RequestMapping("/getAllBuy")
    public Result selectAllBuy()
    {
        Result result = new Result();
        List<Buy> buys = buyService.selectAllBuy();

        result.setData(buys);
        result.setFlag(true);
        result.setMessage("success");

        return result;
    }

    @RequestMapping("/searchBuy")
    public Result searchFactory(@RequestBody Map<String, String> map) {
        Result result = new Result();
        String selection = map.get("selection");
        String inputString = map.get("inputString");

        List<Buy> buys = buyService.searchBuy(selection, inputString);
        result.setFlag(true);
        result.setData(buys);
        result.setMessage("ok");

        return result;
    }

    @RequestMapping("/addBuy")
    public Result addBuy(@RequestBody Buy buy){
        Result result = new Result();
        buyService.insertSelective(buy);
        result.setFlag(true);
        result.setMessage("success");
        return result;
    }

    @RequestMapping("/editBuy")
    public Result editBuy(@RequestBody Buy buy){
        Result result = new Result();
        buyService.updateByPrimaryKey(buy);
        result.setFlag(true);
        result.setMessage("success");
        return result;
    }
}
