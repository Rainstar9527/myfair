package com.eterna1.myfair.service;

import com.eterna1.myfair.vo.Record;

import java.util.List;

public interface RecordService {
    List<Object> selectAllRecord();

    void addRecord(Record record);
}
