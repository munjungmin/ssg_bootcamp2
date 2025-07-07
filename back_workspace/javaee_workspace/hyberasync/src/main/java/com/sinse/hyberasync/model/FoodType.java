package com.sinse.hyberasync.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * [ ORM - java의 Object Relation (관계형 db) 자체를 직접 매핑 - 즉 테이블과 java객체와의 매핑 ]
 * JPA는 java의 자체 api에서 지원하는 인터페이스, 즉 java 표준
 * hibernate는 사설로 JPA를 구현한 구현체, 즉 표준 아님 
 */
@Data
@Entity  // hibernate의 매핑 객체
@Table(name="food_type")   // 어느 테이블에 매핑할지 
public class FoodType {
	
	@Id
	private int food_type_id;
	
	private String title;
}


