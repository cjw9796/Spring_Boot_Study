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
public class MysubjectViewDTO {
	private int subjectNum;
	private String subjectName;
	private String professorName;
	private int hakjom;
	private String major;
	private int grade;
	
	/*
	//DTO 클래스에 데이터를 Entity(테이블과 매핑되는 클래스)로
			//변환하는 메서드를 추가한다.
		public MysubjectView toEntity() {
			return new MysubjectView(subjectNum,subjectName,professorName,hakjom,major,grade);
		}
	*/

}
