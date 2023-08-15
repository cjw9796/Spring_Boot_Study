package com.kh.firstproject.dto;

import com.kh.firstproject.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//롬복
// set get 자동으로 생성해주는 어노테이션 사용! 

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data



public class ArticleForm {
	
	private Long id;
	private String title;
	private String content; 
	
	//DTO 클래스에 데이터를 Entity(테이블과 매핑되는 클래스)로
	//변환하는 메서드를 추가한다.

	public Article toEntity() {
		return new Article(id,title,content);
	}


}
