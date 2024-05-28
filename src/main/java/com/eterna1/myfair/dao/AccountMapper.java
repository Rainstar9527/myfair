package com.eterna1.myfair.dao;

import com.eterna1.myfair.vo.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer aid);

    Account selectByNameAndPassword(Account record);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}