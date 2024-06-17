package com.eterna1.myfair.dao;

import com.eterna1.myfair.vo.Member;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer mid);

    List<Member> selectAllMembers();

    List<Member> searchMemberByName(String mname);

    List<Member> searchMemberByCode(String mcode);

    List<Member> searchMemberByPhone(String mphone);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member selectMemberByCode(String mcode);
}