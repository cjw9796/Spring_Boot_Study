package com.kh.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// ORM 
// Object- RelationMapping
// 객체와 데이터베이스 매핑을 이루는 것
// 객체가 테이블이 되도록 매핑시켜주는 프레임워크이다.
// 프로그램의 복잡도를 줄이고 자바 객체와 쿼리를 분리할 수 있으며 
// 트랜잭션 처리나 데이터베이스 관련 작업들을 좀 더 편리하게 처리할 수 있는 방법 

// SELECT * FROM MEMBER 

// JPA 
// java persistence API 
// ORM을 사용하기 위한 인터페이스를 모아둔 곳 
// 자바 어플리케이션에서 데이터베이스를 사용하는 방식을 정의한 인터페이스 

// Hibernate 
// JPA를 사용하기 위해서는 JPA 구현한 ORM 프레임워크 중 하나 

// JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 어노테이션을 필수로 붙여야 하고 엔티티라 부른다. 

// @Entity 어노테이션이 붙은 클래스 이름으로 springboot가 자동으로 테이블을 만들고 클래스 내부에 선언한 필드 이름으로 테이블을 구성하는 컬럼을 만든다. 


@Entity // 현재 클래스는 Entity로 사용되는 클래스임을 의미한다.
@NoArgsConstructor // 기본생성자로 객체를 생성해주는 것!
@AllArgsConstructor  // 매개변수 전체 받는 생성자 
@ToString
@Getter
public class Article {

//	기본키로 사용할 필드 선언
//	키본키를 자동으로 생성하려면 @Id와 @GeneratedValue 어노테이션을 함께 사용해야 한다.
	@Id // 필드를 기본키로 설정한다.
	//@GeneratedValue // 기본키 값을 자동으로 생성한다. 시퀀스가 무조건 1부터 시작한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column // 필드를 테이블 컬럼과 매핑한다.
	private String title;
	@Column
	private String content;
	
//	public Article() { }
//	public Article(Long id, String title, String content) {
//		this.id = id;
//		this.title = title;
//		this.content = content;
//	}
//	
//	@Override
//	public String toString() {
//		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
//	}	
}







/*
 * h2 데이터베이스
 * 웹용 콘솔(쿼리들) 제공하여 개발용 로컬 DB
 * 인메모리 데이터베이스는 디스크가 아니라 메인 메모리에 모든 데이터를 저장하는 데이터베이스 
 * 자료에 접근하는 빠르다는 장점 
 * 
 * 단점 : 휘발성(서버를 끝내면 전부 날라간다.)
 * 
 * 접근하는법: 
 * application.properties에  spring.h2.console.enabled=true 설정 추가 후 
 * http://localhost:8088/h2-console  
 * 
 */

