package enrolment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data
public class studentDTO {
	
	
	
	private String hakbun; // 학번(id)
	private String passwd; // 비밀번호
	private String name; // 이름
	private String address; // 주소
	private String tel; // 전화번호
	private String email; // 이메일
	private String major; // 전공 (1==컴공, 2==전자공학, 3==응용수학 , 4==교양)
	private int grade; // 학년
	private int hakjom; // 학점(전공교양 합해서 130==>졸업조건)
	
	/*
	//DTO 클래스에 데이터를 Entity(테이블과 매핑되는 클래스)로
		//변환하는 메서드를 추가한다.
	public student toEntity() {
		return new student(hakbun,passwd,name,address,tel,email,major,grade,hakjom);
	}
	*/

}
