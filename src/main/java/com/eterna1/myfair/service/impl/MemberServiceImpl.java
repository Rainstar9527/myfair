package com.eterna1.myfair.service.impl;

import com.eterna1.myfair.dao.MemberMapper;
import com.eterna1.myfair.service.MemberService;
import com.eterna1.myfair.utils.PicUpload;
import com.eterna1.myfair.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    PicUpload picUpload;
    @Autowired
    MemberMapper memberMapper;
    @Override
    public List<Member> selectAllMembers() {
        return memberMapper.selectAllMembers();
    }

    @Override
    public List<Member> searchMembers(String selection, String inputString) {
        switch (selection){
            case "1":
                return memberMapper.searchMemberByName(inputString);
            case "2":
                return memberMapper.searchMemberByPhone(inputString);
            case "3":
                return memberMapper.searchMemberByCode(inputString);
        }
        return null;
    }

    @Override
    public void deleteByPrimaryKey(Member member) {
        memberMapper.deleteByPrimaryKey(member.getMid());
    }

    @Override
    public void addMember(Member member) {
        memberMapper.insertSelective(member);
    }

    @Override
    public void editMember(Member member) {
        memberMapper.updateByPrimaryKey(member);
    }
}