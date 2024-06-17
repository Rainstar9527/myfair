package com.eterna1.myfair.service;

import com.eterna1.myfair.vo.Buy;
import com.eterna1.myfair.vo.Factory;

import java.util.List;

public interface BuyService {
    List<Buy> selectAllBuy();

    List<Buy> searchBuy(String selection, String inputString);

    void insertSelective(Buy buy);

    void updateByPrimaryKey(Buy buy);

    void deleteByPrimaryKey(Buy buy);
}
