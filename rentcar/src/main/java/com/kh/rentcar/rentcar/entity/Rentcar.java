package com.kh.rentcar.rentcar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@ToString
@Getter
public class Rentcar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	@Column
	private String name; 
	@Column
	private Long category;
	@Column
	private Long price; 
	@Column 
	private Long use_people; 
	@Column
	private String company;
	@Column
	private String img;
	@Column
	private String info;

}
