package com.eterna1.myfair.dao;

import com.eterna1.myfair.vo.Location;

import java.util.List;

public interface LocationMapper {
    int deleteByPrimaryKey(Integer pubId);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(Integer pubId);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    List<Location> selectAllLocations();

    List<Location> selectEmptyLocation();
}