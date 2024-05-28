package com.eterna1.myfair.service;

import com.eterna1.myfair.vo.Account;

public interface AccountService {
    Account selectByNameAndPassword(Account record);
}
