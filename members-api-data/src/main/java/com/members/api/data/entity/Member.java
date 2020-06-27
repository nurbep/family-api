package com.members.api.data.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Cacheable
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 50)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private String dob;

  @Column(name = "gender", nullable = false)
  private String gender;

  @OneToOne(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL)
  private Description description;

  @OrderColumn(name = "parent_sequence")
  @ElementCollection
  private List<Integer> parentIds = new ArrayList<>();

  @OrderColumn(name = "children_sequnce")
  @ElementCollection
  private List<Integer> childrenIds = new ArrayList<>();

  @OrderColumn(name = "spouse_sequence")
  @ElementCollection
  private List<Integer> spouseIds = new ArrayList<>();

  public void addSpouseId(int id) {
    this.spouseIds.add(id);
  }

  public void addChildId(int id) {
    this.childrenIds.add(id);
  }

  public void addParentId(int id) {
    this.parentIds.add(id);
  }


}
