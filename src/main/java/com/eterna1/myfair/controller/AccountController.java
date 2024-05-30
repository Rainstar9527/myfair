package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.AccountService;
import com.eterna1.myfair.utils.Encrypt;
import com.eterna1.myfair.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    //1.获取dao层对象
    @Autowired
    AccountService accountService;

    //请求与响应
    //登录请求的处理方法
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public Result login(@RequestBody Account account)
    {
        Result result = new Result();
//        String pwd = Encrypt.getMD5(account.getAdminPwd());
//        account.setAdminPwd(pwd);
        Account loginAccount = accountService.selectByNameAndPassword(account);
        if (loginAccount != null)
        {
            result.setData(loginAccount);
            result.setFlag(true);
            result.setMessage("success");
        } else
        {
            result.setFlag(false);
            result.setMessage("error");
        }
        return result;
    }
}
