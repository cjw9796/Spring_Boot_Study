package com.kh.restapi.twoproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.restapi.twoproject.dto.CommentDto;

import com.kh.restapi.twoproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

// controller : url들어오면 컨트롤러가 관리해준다.
//              뷰 화면 보여주는 컨트롤러 

// json 타입의 데이터를 주고 받는 controller 
@RestController
@Slf4j
public class CommentApiController {

	// 실제 데이터베이스를 접근하기 위해서
	// 서비스한테 일을 보내야된다.
	@Autowired
	private CommentService commentSerivce; // 새로운변수를 만든다.

	// 댓글을 관리하는 컨트롤러
	// 목록 조회 요청
	// rest api는 결과를 보낼때 응답 코드와 데이터를 같이 보내야된다.
	// ResponeEntity
	// talend로 확인시 http://localhost:9080/api/comments/4/comments
//	댓글 목록 조회 요청
//	Talend API Tester(GET) => http://localhost:9090/api/comments/5/comments

//	CommentService의 메소드를 사용하기 위해서 @Autowired 어노테이션을 붙여서 CommentService의
//	bean을 가져온다.
	@Autowired
	private CommentService commentService;
	
//	댓글 목록 조회 요청
//	Talend API Tester(GET) => http://localhost:9090/api/comments/5/comments
	@GetMapping("/api/comments/{articleId}/comments")
	public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
		log.info("CommentApiController의 comments() 메소드 실행");
		log.info("articleId: {}", articleId);
//		서비스에 위임
		List<CommentDto> dtos = commentService.comments(articleId);
//		결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
//	댓글 생성 요청
//	Talend API Tester(POST) => http://localhost:9090/api/comments/5/comments
//	body 데이터
//	{
//		"nickname": "Hong",
//		"body": "슈룹",
//		"article_id": 5
//	}
	@PostMapping("/api/comments/{articleId}/comments")
	public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
		log.info("CommentApiController의 create() 메소드 실행");
		log.info("articleId: {}", articleId);
		log.info("dto: {}", dto);
//		서비스에 위임
		CommentDto createDto = commentService.create(articleId, dto);
//		결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(createDto);
	}
	
//	댓글 수정 요청
//	Talend API Tester(PATCH) => http://localhost:9090/api/comments/1
//	body 데이터
//	{
//		"id": 1,
//		"nickname": "홍길동",
//		"body": "댓글 수정",
//		"article_id": 5
//	}
	@PatchMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
		log.info("CommentApiController의 update() 메소드 실행");
		log.info("id: {}", id);
		log.info("dto: {}", dto);
//		서비스에 위임
		CommentDto updateDto = commentService.update(id, dto);
//		결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updateDto);
	}
	
//	댓글 삭제 요청
//	Talend API Tester(DELETE) => http://localhost:9090/api/comments/1
	@DeleteMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
		log.info("CommentApiController의 delete() 메소드 실행");
		log.info("id: {}", id);
//		서비스에 위임
		CommentDto deleteDto = commentService.delete(id);
//		결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
	}
}
