package com.members.api.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity {

  @Id
  @Column(name = "id", unique = true)
  private Integer id;
}
