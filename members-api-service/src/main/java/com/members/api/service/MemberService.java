package com.members.api.service;

import com.members.api.data.entity.Member;
import com.members.api.service.exception.MemberNotFoundException;
import com.members.api.service.exception.TechnicalException;
import java.util.List;
import java.util.Optional;

public interface MemberService {

  Optional<Member> getMemberById(Integer id) throws MemberNotFoundException, TechnicalException;

  List<Member> findAllMembers() throws MemberNotFoundException, TechnicalException;

  Member createMember(Member member) throws MemberNotFoundException, TechnicalException;
  
  Member updateMember(int memberId, Member member)
	      throws MemberNotFoundException, TechnicalException;

  
  void updateSpouse(int memberId, int[] spouseIds)
      throws MemberNotFoundException, TechnicalException;

  void updateParents(int memberId, int[] parentIds)
      throws MemberNotFoundException, TechnicalException;

  void updateChildren(int memberId, int[] childrenIds)
      throws MemberNotFoundException, TechnicalException;

  List<Member> getChildrenByMemberId(int memberId)
      throws MemberNotFoundException, TechnicalException;

  List<Member> getParentsByMemberId(int memberId)
      throws MemberNotFoundException, TechnicalException;

  List<Member> getSpousesByMemberId(int memberId)
      throws MemberNotFoundException, TechnicalException;


}
