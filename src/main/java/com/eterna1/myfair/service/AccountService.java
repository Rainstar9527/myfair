package com.eterna1.myfair.service;

import com.eterna1.myfair.vo.Account;

import java.util.List;

public interface AccountService {
    Account selectByNameAndPassword(Account record);

    List<Account> selectAllAccount();

    List<Account> searchAccount(String selection, String inputString);

    void deleteAccount(Account account);

    void addAccount(Account account);

    void editAccount(Account account);
}
