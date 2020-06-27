package com.members.api.web;

import com.members.api.common.MemberFault;
import com.members.api.data.entity.Member;
import com.members.api.service.MemberServiceImpl;
import com.members.api.service.exception.MemberNotFoundException;
import com.members.api.service.exception.TechnicalException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/members", headers = {"Version=1", "Accept=application/json"})

@RestController
public class MemberController {

  private MemberServiceImpl memberService;

  @Autowired
  public MemberController(MemberServiceImpl memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public ResponseEntity<List<Member>> getAllMembers()
      throws TechnicalException, MemberNotFoundException {
    List<Member> members;

    try {
      members = memberService.findAllMembers();
    } catch (MemberNotFoundException e) {
      throw e;

    } catch (TechnicalException e) {
      throw e;
    }

    return new ResponseEntity<List<Member>>(members, HttpStatus.OK);

  }

  @GetMapping("/{memberId}")
  public ResponseEntity<Member> getMemberById(@PathVariable int memberId)
      throws MemberNotFoundException, TechnicalException {

    return new ResponseEntity<Member>(memberService.getMemberById(memberId)
        .orElseThrow(() -> new MemberNotFoundException(MemberFault.COULD_NOT_FIND_MEMBER)),
        HttpStatus.OK);

  }

  @GetMapping("/children/{memberId}")
  public ResponseEntity<List<Member>> getAllChildrenByMemberId(@PathVariable int memberId)
      throws MemberNotFoundException, TechnicalException {

    return new ResponseEntity<List<Member>>(memberService.getChildrenByMemberId(memberId),
        HttpStatus.OK);
  }

  @GetMapping("/parents/{memberId}")
  public ResponseEntity<List<Member>> getParentsByMemberId(@PathVariable int memberId)
      throws MemberNotFoundException, TechnicalException {

    return new ResponseEntity<List<Member>>(memberService.getParentsByMemberId(memberId),
        HttpStatus.OK);
  }

  @GetMapping("/spouses/{memberId}")
  public ResponseEntity<List<Member>> getSousesByMemberId(@PathVariable int memberId)
      throws MemberNotFoundException, TechnicalException {

    return new ResponseEntity<List<Member>>(memberService.getSpousesByMemberId(memberId),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Member> createMember(@RequestBody Member member)
      throws MemberNotFoundException, TechnicalException {

    return new ResponseEntity<Member>(memberService.createMember(member), HttpStatus.CREATED);
  }

  @PutMapping(value = "/spouses/{memberId}")
  public ResponseEntity<?> updateSpouses(@PathVariable int memberId, @RequestParam int spouseIds[])
      throws MemberNotFoundException, TechnicalException {

    try {
      memberService.updateSpouse(memberId, spouseIds);
      return new ResponseEntity<String>("Spouse has been updated successfully.", HttpStatus.OK);
    } catch (MemberNotFoundException ex) {
      throw ex;
    } catch (TechnicalException ex) {
      throw ex;
    }

  }


  @PutMapping(value = "/parents/{memberId}")
  public ResponseEntity<?> updateParents(@PathVariable int memberId, @RequestParam int parentIds[])
      throws MemberNotFoundException, TechnicalException {

    memberService.updateParents(memberId, parentIds);

    return new ResponseEntity<String>("Parents have been updated successfully.", HttpStatus.OK);

  }


  @PutMapping(value = "/children/{memberId}")
  public ResponseEntity<?> updateChildren(@PathVariable int memberId,
      @RequestParam int childrenIds[])
      throws MemberNotFoundException, TechnicalException {

    memberService.updateChildren(memberId, childrenIds);

    return new ResponseEntity<String>("Children have been updated successfully.", HttpStatus.OK);
  }
  
  @PutMapping(value = "/{memberId}")
  public ResponseEntity<Member> updateMember(@PathVariable int memberId, @RequestBody Member member)
      throws MemberNotFoundException, TechnicalException {

    return new ResponseEntity<Member>(memberService.updateMember(memberId, member), HttpStatus.OK);

  }


}
