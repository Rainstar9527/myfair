package com.eterna1.myfair.dao;

import com.eterna1.myfair.common.QueryPageEntity;
import com.eterna1.myfair.vo.Factory;

import java.util.List;

public interface FactoryMapper {
    int deleteByPrimaryKey(Integer facId);

    int insert(Factory record);

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Integer facId);

    List<Factory> selectByPage(QueryPageEntity queryPageEntity);

    List<Factory> selectAllFactory();

    List<Factory> searchFacByName(String facName);

    List<Factory> searchFacByPhone(String facPhone);

    List<Factory> searchFacByType(String facDesc);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKey(Factory record);
}