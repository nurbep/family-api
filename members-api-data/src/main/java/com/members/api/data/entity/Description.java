package com.members.api.data.entity;

import com.members.api.common.ColorType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "description")
@Cacheable
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Description extends BaseEntity {

  @Column(name = "hair_color", length = 10, nullable = false)
  @Enumerated(EnumType.STRING)
  private ColorType hairColor;

  @Column(name = "eye_color", length = 10, nullable = true)
  @Enumerated(EnumType.STRING)
  private ColorType eyeColor;

  @Column(name = "skin_color", length = 10, nullable = true)
  @Enumerated(EnumType.STRING)
  private ColorType skinColor;

  @Column(name = "height_in_cm", nullable = true)
  private double height;

  @Column(name = "weight_in_kg", nullable = true)
  private double weight;

  @OneToOne
  @JoinColumn(name = "member_id")
  private Member member;


}
