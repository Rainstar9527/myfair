package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.MemberService;
import com.eterna1.myfair.utils.PicUpload;
import com.eterna1.myfair.vo.Factory;
import com.eterna1.myfair.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class MemberController {
    @Autowired
    PicUpload picUpload;
    @Autowired
    MemberService memberService;

    @PostMapping("/getAllMember")
    public Result selectAllMember(){
        Result result = new Result();
        List<Member> members = memberService.selectAllMembers();
        result.setFlag(true);
        result.setData(members);
        result.setMessage("ok");
        return result;
    }

    @RequestMapping("/searchMember")
    public Result searchFactory(@RequestBody Map<String, String> map) {
        Result result = new Result();
        String selection = map.get("selection");
        String inputString = map.get("inputString");

        List<Member> members = memberService.searchMembers(selection, inputString);
        result.setFlag(true);
        result.setData(members);
        result.setMessage("ok");

        return result;
    }

    @PostMapping("/removeMember")
    public Result removeMember(@RequestBody Member member){
        Result result = new Result();
        memberService.deleteByPrimaryKey(member);
        result.setFlag(true);
        result.setMessage("删除成功");
        return  result;
    }

    @PostMapping("/addMember")
    public Result addMember(@RequestBody Member member){
        Result result = new Result();
        memberService.addMember(member);
        result.setFlag(true);
        result.setMessage("添加成功");
        return result;
    }

    @PostMapping("/editMember")
    public Result editMember(@RequestBody Member member){
        Result result = new Result();
        memberService.editMember(member);
        result.setFlag(true);
        result.setMessage("修改成功");
        return result;
    }
}
