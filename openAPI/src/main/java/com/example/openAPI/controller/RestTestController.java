package com.example.openAPI.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 공공데이터를 가져올 때 데이터를 주고 받는 내용이 있기 때문에 (xml,json)
// 그냥 controller 보다 Restcontroller 사용 

@RestController
public class RestTestController {

	// 서버를 실행하면
	// 웹 브라우저에서 url을 작성하고 엔터누르면
	// 디스팩처 서블릿이 모든 url을 받는다.
	// 전송할 때 get, post 그걸 구별해서 매핑시켜준다.
	// openapi 메서드가 실행한다.

	// 공공데이터를 실행하기 위해서 필요한 작업
	// 1. https://www.data.go.kr/ 접속
	// 2. 회원가입(나만의 고유의 key)
	// 3. 원하는 데이터 검색 후 활용 신청 (부산광역시_부산맛집정보 서비스)
	// 인증키 :
	// JIny3vXFVQax56ktxOu7%2F7oPchrui%2FTGWAQa48Q8O1p6KFe1oizf7ESjNsqG8SbVB094S%2FDNVmGJOlW%2BPBlFFQ%3D%3D
	// 요청url : http://apis.data.go.kr/6260000/FoodService/getFoodKr

	@GetMapping("/apitest") // http://localhost:9050/apitest
	public String openapi() {

		StringBuffer result = new StringBuffer();

		int pageNo = 1;

		// 필수 요소와 url을 저장
		// 맨 처음은 요청 url
		// servicekey
		// numOfRows
		// pageNo
		// "&resultType=xml"; xml 타입으로
		try {
			String apiUrl = "http://apis.data.go.kr/6260000/FoodService/getFoodKr?"
					+ "serviceKey=JIny3vXFVQax56ktxOu7%2F7oPchrui%2FTGWAQa48Q8O1p6KFe1oizf7ESjNsqG8SbVB094S%2FDNVmGJOlW%2BPBlFFQ%3D%3D"
					+ "&numOfRows=10" + "&pageNo=" + pageNo + "&resultType=xml";

			// URL 객체를 생성하는 클래스
			// 문자열이 지정하는 자원에 대한 url 객체 생성
			// URL 객체 만들면서 프로토콜 : 통신방법, 호스트 주소, 포트 번호, 파일 이름
			// 객체 생성

			URL url = new URL(apiUrl);
			// url 내용을 읽어오거나 url에 get, post 방식으로 데이터를 전달할 때 사용
			// 웹 페이지나 서블릿에 데이터를 전달할 수 있다.

			// 프로토콜이 http:// 인 경우 반환된 객체를 httpURLConnection객체로 캐스팅할 수 있다.
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // 연결

			// 실제 데이터 전송을 할 때 전송방식
			urlConnection.setRequestMethod("GET");

			// 실제 연결
			// urlConnection.connect();

			// 응답!(데이터를 받아온다.)
			// Input, Out Stream
			// Stream
			// - 데이터가 연속적으로 존재한다는 것을 표현한 객체
			// - 바이트로 데이터를 전달하기 때문에 스트림도 byte 연속된 집합이다.
			// - 사용자의 키보드의 입력, 파일 데이터, http 송수신 데이터 등이 모두 스트림

			// InputStream
			// 자바 프로그램 안으로 데이터를 가지고 온다.
			// 1byte 의 int형의 아스키 코드값으로 값을 저장

			// InputStreamReader
			// byte 대신 char 형태로 읽을 수 있게 아스키코드가 아닌 문자열로 출력이 가능하다.
			// String 객체로 변환할 수도 있게 된다.
			// InputSteam 인자로 받아와서 만들어진다.

			BufferedInputStream BufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

			// 실제 응답받은 데이터를 읽기
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BufferedInputStream, "UTF-8"));

			String returnLine;
			// <xmp> </xmp>
			// 문자, 숫자, 태그 소스 든 그대로 출력할 수 있게 도와주는 태그
			result.append("<xmp>");

			// 데이터를 응답받아서 안에 있는 내용들을 꺼내야된다.
			// 꺼낼때 데이터가 없으면 null 반환한다.
			// 한 행 ~ 한 줄 BufferedReader readLine()
			while ((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine + "\n");
			}

			// url 연결 끊기! 닫기!
			urlConnection.disconnect();

		} catch (Exception e) { // 모든 예외에 대해서 처리하겠다.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result + "</xmp>";
	}

	// json 타입으로 받기 key, value
	// 필요한 라이브러리가 있다. src/main/resources - static - libs

	@GetMapping("/jsonapi") //http://localhost:9050/jsonapi
	public String openapiJson() {

		// url 세팅
		StringBuffer result = new StringBuffer();

		int pageNo = 2;

		// try 안쪽에 변수 선언하면 데이터가 try문과 함께 사라지는 지역변수
		String jsonPrintString = null;

		try {
			String apiUrl = "http://apis.data.go.kr/6260000/FoodService/getFoodKr?"
					+ "serviceKey=JIny3vXFVQax56ktxOu7%2F7oPchrui%2FTGWAQa48Q8O1p6KFe1oizf7ESjNsqG8SbVB094S%2FDNVmGJOlW%2BPBlFFQ%3D%3D"
					+ "&numOfRows=10" + "&pageNo=" + pageNo;

			URL url = new URL(apiUrl);

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // 연결
			urlConnection.connect();

			BufferedInputStream BufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

			// 중간에 데이터를 임시저장공간인 버퍼에 저장한다.
			// 저장한 내용을 한꺼번에 가지고 들어온다.
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BufferedInputStream, "UTF-8"));

			String returnLine;

			while ((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine + "\n");
			}

			// json
			// json 파일을 스프링부트에서 사용할 수 있도록 특정 값을 가지고 오는
			// 내용을 작성

			// Jsonparser 객체의 도움을 받는다.
			// 1. Jsonparser 객체 생성
			// 2. reader를 이용해서 json 파일을 읽어온다.
			// 3. Array json 코드가 [] 감싸고 있을 경우 List 형식으로 index 값으로 불러온다.
			// 4. Object json 코드가 {}로 감싸고 있는 경우
			// Key : Value 형식으로 저장되어 있는 값을 불러온다. map형식

			JSONObject jsonObject = XML.toJSONObject(result.toString());
			jsonPrintString = jsonObject.toString();

		} catch (Exception e) { // 모든 예외에 대해서 처리하겠다.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonPrintString;
	}

}
