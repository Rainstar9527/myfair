package com.eterna1.myfair.dao;

import com.eterna1.myfair.vo.Account;

import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer aid);

    Account selectByNameAndPassword(Account record);

    List<Account> selectAllAccount();

    List<Account> selectById(Integer aid);

    List<Account> selectByName(String adminName);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

}