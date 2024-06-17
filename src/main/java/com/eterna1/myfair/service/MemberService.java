package com.eterna1.myfair.service;

import com.eterna1.myfair.vo.Member;

import java.util.List;

public interface MemberService {
    List<Member> selectAllMembers();

    List<Member> searchMembers(String selection, String inputString);

    void deleteByPrimaryKey(Member member);

    void addMember(Member member);

    void editMember(Member member);
}
