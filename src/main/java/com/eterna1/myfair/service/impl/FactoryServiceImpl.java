package com.eterna1.myfair.service.impl;


import com.eterna1.myfair.common.QueryPageEntity;
import com.eterna1.myfair.dao.FactoryMapper;
import com.eterna1.myfair.service.FactoryService;
import com.eterna1.myfair.vo.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    FactoryMapper factoryMapper;

    @Override
    public List<Factory> selectByPage(QueryPageEntity queryPageEntity){
        return factoryMapper.selectByPage(queryPageEntity);
    }

    @Override
    public List<Factory> selectAllFactory()
    {
        return factoryMapper.selectAllFactory();
    }

    @Override
    public List<Factory> searchFactory(String selection, String inputString) {
        switch (selection){
            case "1":
                return factoryMapper.searchFacByName(inputString);
            case "2":
                return factoryMapper.searchFacByPhone(inputString);
            case "3":
                return factoryMapper.searchFacByType(inputString);
        }
        return null;
    }
}
