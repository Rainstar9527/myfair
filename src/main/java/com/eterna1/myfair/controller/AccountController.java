package com.eterna1.myfair.controller;

import com.eterna1.myfair.service.AccountService;
import com.eterna1.myfair.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    //1.获取dao层对象
    @Autowired
    AccountService accountService;

    //请求与响应
    //登录请求的处理方法
    @RequestMapping("/login")
    public String login(String adminName, String adminPwd)
    {
        Account account = new Account();
        account.setAdminName(adminName);
        account.setAdminPwd(adminPwd);
        Account loginAccount = accountService.selectByNameAndPassword(account);
        if (loginAccount == null)
        {
            return "Error";
        } else
        {
            return "ok";
        }
    }
}
