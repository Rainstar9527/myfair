package com.eterna1.myfair.dao;

import com.eterna1.myfair.vo.Buy;

import java.util.List;

public interface BuyMapper {
    int deleteByPrimaryKey(Integer buyId);

    int insert(Buy record);

    int insertSelective(Buy record);

    Buy selectByPrimaryKey(Integer buyId);

    List<Buy> selectAllBuy();

    int updateByPrimaryKeySelective(Buy record);

    int updateByPrimaryKey(Buy record);

    List<Buy> searchBuyByName(String inputString);

    List<Buy> searchBuyByAddress(String inputString);
}