package com.scm.ojt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_category")
public class PostCategory {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "post_id")
 private Integer post_id;
 
 @Column(name = "category_id")
 private Integer category_id;
}
