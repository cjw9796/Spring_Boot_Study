package com.kh.springbootthemeleaf.dto;


public class MyInfo {
	private String id; 
	private String name;
	private String email;
	private Integer age;
	private String gender;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "MyInfo [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", gender=" + gender + "]";
	} 

	
	
}
