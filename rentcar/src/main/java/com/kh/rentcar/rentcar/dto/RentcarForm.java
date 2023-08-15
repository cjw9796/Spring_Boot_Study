package com.kh.rentcar.rentcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자 자동 완성
@AllArgsConstructor // 모든 변수를 사용하는 생성자 자동 완성
@Getter // getter 메소드 자동 완성
@Setter // setter 메소드 자동 완성
@ToString // toString() 메소드 자동 완성
@EqualsAndHashCode // equals() 메소드와 hashcode() 메소드 자동 완성
@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode 자동 완성
public class RentcarForm {

	private Long no;
	private String name;
	private Long category;
	private Long price;
	private Long use_people;
	private String company;
	private String img;
	private String info;

}
