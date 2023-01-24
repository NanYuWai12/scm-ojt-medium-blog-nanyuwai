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
@Table(name = "categories")
public class Category {
    
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "cat_id")
 private Integer catId;
 
 @Column(name = "name")
 private String name;
 
 @Column(name = "created_at")
 private String createdAt;
 
 @Column(name = "updated_at")
 private String updatedAt;
}
