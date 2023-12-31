package com.kh.restapi.twoproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kh.restapi.twoproject.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {

	private Long id;
	private String nickname;
	private String body;
//		커맨드 객체를 사용해 json 데이터를 받아 저장하려 할 경우 json 데이터의 key는 스네이크 표기법을 사용하고
//		커맨드 객체는 카멜 표기법을 사용하면 데이터를 받지 못한다.
//		@JsonProperty("json 키") 어노테이션을 사용하면 json의 key와 커맨드 객체의 필드 이름이 다르더라도 데이터를 
//		받을 수 있다.
	@JsonProperty("article_id")
	private Long articleId;

//		entity를 dto로 변환하는 메소드
	public static CommentDto createCommentDto(Comment comment) {
		return new CommentDto(comment.getId(), comment.getNickname(), comment.getBody(), comment.getArticle().getId());
	}

}
