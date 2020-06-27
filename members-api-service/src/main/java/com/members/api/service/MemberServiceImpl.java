package com.members.api.service;

import com.members.api.common.MemberFault;
import com.members.api.data.entity.Member;
import com.members.api.data.repository.MemberRepository;
import com.members.api.service.exception.MemberNotFoundException;
import com.members.api.service.exception.TechnicalException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberServiceImpl(final MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Optional<Member> getMemberById(Integer id)
      throws MemberNotFoundException, TechnicalException {
    Optional<Member> member = memberRepository.findById(id);

    return member;
  }

  public List<Member> findAllMembers() throws MemberNotFoundException, TechnicalException {

    return memberRepository.findAll();
  }

  public Member createMember(Member member) throws MemberNotFoundException, TechnicalException {

    Optional<Member> existingMember = memberRepository.findById(member.getId());

    if (existingMember.isPresent()) {
      log.warn(String.format("Member already exists with the id %d", member.getId()));

      throw new MemberNotFoundException(MemberFault.EXISTING_MEMBER);
    }

    return memberRepository.save(member);
  }
  
 public Member updateMember(int memberId, Member member)
	      throws MemberNotFoundException, TechnicalException {
	 Optional<Member> existingMember = memberRepository.findById(memberId);
	 
	 if (!existingMember.isPresent()) {
		 throw new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER);
		 
	 } else if (existingMember.get().getId() != member.getId()) {
		 
		 throw new MemberNotFoundException(MemberFault.COULD_NOT_MATCH_ID);
	 } else {
		 
		 return memberRepository.save(member);
	 }
 }


  public void updateSpouse(int memberId, int[] spouseIds)
      throws MemberNotFoundException, TechnicalException {

    Member member = memberRepository.findById(memberId).orElseThrow(() ->
        new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER));

    if (!StringUtils.isEmpty(spouseIds)) {
      Arrays.stream(spouseIds).boxed()
          .filter(id -> !member.getSpouseIds().contains(id))
          .collect(Collectors.toList())
          .forEach(id -> {

            member.addSpouseId(id);

            Member spouse = memberRepository.findById(id).get();

            if (!spouse.getSpouseIds().contains(memberId)) {
              spouse.addSpouseId(memberId);
              ;
              memberRepository.save(spouse);
            }
          });
    }

    memberRepository.save(member);
  }


  public void updateParents(int memberId, int[] parentIds)
      throws MemberNotFoundException, TechnicalException {
    Member member = memberRepository.findById(memberId).orElseThrow(() ->
        new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER));

    if (!StringUtils.isEmpty(parentIds)) {

      Arrays.stream(parentIds).boxed()
          .filter(id -> !member.getParentIds().contains(id))
          .collect(Collectors.toList())
          .forEach(id -> {

            member.addParentId(id);

            Member parent = memberRepository.findById(id).get();
            if (!parent.getChildrenIds().contains(memberId)) {
              parent.addChildId(id);
              memberRepository.save(parent);
            }


          });

    }

    memberRepository.save(member);
  }

  public void updateChildren(int memberId, int[] childrenIds)
      throws MemberNotFoundException, TechnicalException {
    Member member = memberRepository.findById(memberId).orElseThrow(() ->
        new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER));

    if (!StringUtils.isEmpty(childrenIds)) {

      Arrays.stream(childrenIds).boxed()
          .filter(id -> !member.getChildrenIds().contains(id))
          .collect(Collectors.toList())
          .forEach(id -> {

            member.addChildId(id);

            Member child = memberRepository.findById(id).get();
            if (!child.getParentIds().contains(memberId)) {
              child.addParentId(memberId);
              memberRepository.save(child);
            }


          });

    }

    memberRepository.save(member);
  }

  public List<Member> getChildrenByMemberId(int memberId)
      throws MemberNotFoundException, TechnicalException {

    return (List<Member>) memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER))
        .getChildrenIds()
        .stream()
        .map(id -> memberRepository.findById(id).get())
        .collect(Collectors.toList());

  }

  public List<Member> getParentsByMemberId(int memberId)
      throws MemberNotFoundException, TechnicalException {

    return (List<Member>) memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER))
        .getParentIds()
        .stream()
        .map(id -> memberRepository.findById(id).get())
        .collect(Collectors.toList());

  }

  public List<Member> getSpousesByMemberId(int memberId)
      throws MemberNotFoundException, TechnicalException {

    return (List<Member>) memberRepository.findById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER))
        .getSpouseIds()
        .stream()
        .map(id -> memberRepository.findById(id).get())
        .collect(Collectors.toList());

  }


}
