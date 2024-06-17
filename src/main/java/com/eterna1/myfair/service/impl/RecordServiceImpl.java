package com.eterna1.myfair.service.impl;

import com.eterna1.myfair.dao.RecordMapper;
import com.eterna1.myfair.service.RecordService;
import com.eterna1.myfair.vo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Object> selectAllRecord() {
        return recordMapper.selectAllRecord();
    }

    @Override
    public void addRecord(Record record) {
        recordMapper.insertSelective(record);
    }
}
