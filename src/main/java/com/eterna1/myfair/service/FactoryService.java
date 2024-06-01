package com.eterna1.myfair.service;

import com.eterna1.myfair.common.QueryPageEntity;
import com.eterna1.myfair.vo.Factory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FactoryService {
    List<Factory> selectByPage(QueryPageEntity queryPageEntity);

    List<Factory> selectAllFactory();

    List<Factory> searchFactory(String selection, String inputString);

}
