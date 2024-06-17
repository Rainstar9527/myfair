package com.eterna1.myfair.controller;

import com.eterna1.myfair.common.Result;
import com.eterna1.myfair.service.AccountService;
import com.eterna1.myfair.utils.Encrypt;
import com.eterna1.myfair.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AccountController {
    //1.获取dao层对象
    @Autowired
    AccountService accountService;

    //请求与响应
    //登录请求的处理方法
    @PostMapping("/login")
    public Result login(@RequestBody Account account)
    {
        Result result = new Result();
        String pwd = Encrypt.getMD5(account.getAdminPwd());
        account.setAdminPwd(pwd);
        Account loginAccount = accountService.selectByNameAndPassword(account);
        if (loginAccount != null)
        {
            if (loginAccount.getAdminState() != 0)
            {
                result.setFlag(false);
                result.setMessage("登陆失败,该账户无法登录");
            }else {
                result.setData(loginAccount);
                result.setFlag(true);
                result.setMessage("登录成功");
            }
        } else
        {
            result.setFlag(false);
            result.setMessage("登录失败");
        }
        return result;
    }

    @PostMapping("/getAllAccount")
    public Result getAllAccount()
    {
        Result result = new Result();
        List<Account> accounts = accountService.selectAllAccount();
        result.setData(accounts);
        result.setFlag(true);
        result.setMessage("Success");
        return result;
    }

    @PostMapping("/searchAccount")
    public Result searchAccount(@RequestBody Map<String, String> map)
    {
        Result result = new Result();
        String selection = map.get("selection");
        String inputString = map.get("inputString");
        List<Account> accounts = accountService.searchAccount(selection, inputString);
        result.setData(accounts);
        result.setFlag(true);
        result.setMessage("Success");
        return result;
    }

    @PostMapping("/removeAccount")
    public Result deleteAccount(@RequestBody Account account)
    {
        Result result = new Result();
        accountService.deleteAccount(account);

        result.setMessage("删除成功");
        result.setFlag(true);
        return result;
    }

    @PostMapping("/addAccount")
    public Result addAccount(@RequestBody Account account)
    {
        Result result = new Result();
        String pwd = Encrypt.getMD5(account.getAdminPwd());
        account.setAdminPwd(pwd);
        accountService.addAccount(account);
        result.setMessage("添加成功");
        result.setFlag(true);
        return result;
    }

    @PostMapping("/editAccount")
    public Result editAccount(@RequestBody Account account)
    {
        Result result = new Result();
        String pwd = Encrypt.getMD5(account.getAdminPwd());
        account.setAdminPwd(pwd);
        accountService.editAccount(account);
        result.setMessage("修改成功");
        result.setFlag(true);
        return result;
    }
}
