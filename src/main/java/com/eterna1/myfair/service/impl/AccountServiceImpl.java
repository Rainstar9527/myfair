package com.eterna1.myfair.service.impl;

import com.eterna1.myfair.dao.AccountMapper;
import com.eterna1.myfair.service.AccountService;
import com.eterna1.myfair.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;


    @Override
    public Account selectByNameAndPassword(Account record) {
        return accountMapper.selectByNameAndPassword(record);
    }
}
