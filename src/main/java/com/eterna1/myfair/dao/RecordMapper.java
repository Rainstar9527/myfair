package com.eterna1.myfair.dao;

import com.eterna1.myfair.vo.Record;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer rid);

    List<Object> selectAllRecord();

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}