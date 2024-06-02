package com.eterna1.myfair.service.impl;

import com.eterna1.myfair.dao.BuyMapper;
import com.eterna1.myfair.service.BuyService;
import com.eterna1.myfair.vo.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    BuyMapper buyMapper;
    @Override
    public List<Buy> selectAllBuy() {
        return buyMapper.selectAllBuy();
    }

    @Override
    public List<Buy> searchBuy(String selection, String inputString) {
        switch (selection){
            case "1":
                return buyMapper.searchBuyByName(inputString);
            case "2":
                return buyMapper.searchBuyByAddress(inputString);
        }
        return null;
    }

    @Override
    public void insertSelective(Buy buy) {
        buyMapper.insertSelective(buy);
    }

    @Override
    public void updateByPrimaryKey(Buy buy) {
        buyMapper.updateByPrimaryKey(buy);
    }
}
