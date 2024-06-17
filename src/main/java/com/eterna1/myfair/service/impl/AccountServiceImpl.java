package com.eterna1.myfair.service.impl;

import com.eterna1.myfair.dao.AccountMapper;
import com.eterna1.myfair.service.AccountService;
import com.eterna1.myfair.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;
    @Override
    public Account selectByNameAndPassword(Account record) {
        return accountMapper.selectByNameAndPassword(record);
    }

    @Override
    public List<Account> selectAllAccount() {
        return accountMapper.selectAllAccount();
    }

    @Override
    public List<Account> searchAccount(String selection, String inputString) {
        switch (selection)
        {

            case "1": return accountMapper.selectById(Integer.parseInt(inputString));
            case "2": return accountMapper.selectByName(inputString);
        }
        return null;
    }

    @Override
    public void deleteAccount(Account account) {
        accountMapper.deleteByPrimaryKey(account.getAid());
    }

    @Override
    public void addAccount(Account account) {
        accountMapper.insertSelective(account);
    }

    @Override
    public void editAccount(Account account) {
        accountMapper.updateByPrimaryKey(account);
    }
}
