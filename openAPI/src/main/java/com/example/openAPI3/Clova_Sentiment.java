package com.example.openAPI3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//	네이버 CLOVA 감정분석 API 예제 https://api.ncloud-docs.com/docs/ai-naver-clovasentiment-api#%EC%9A%94%EC%B2%AD-%EC%98%88%EC%8B%9C
// 실행x 
@Controller
public class Clova_Sentiment {

	@GetMapping("/sentiment")
	public String sentiment() {

		// id pw
		String clientId = "fzvnjqv0cs"; // 본인 애플리케이션 아이디값
		String clientSecret = "DIF8oSalVCUOJev9BwimDBE3SVzLYdGnjuMweyDj"; // 본인 애플리케이션 시크릿값

		StringBuffer response = new StringBuffer();

		try {
			String content = "{\"content\": \"싸늘하다. 가슴에 비수가 날아와 꽂힌다.\"}";

			String apiURL = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";
			// 주어진 url 주소에 대해 새 url 객체를 생성한다.
			// url 형식이 잘못된 경우에는 에러발생할 수 있다.
			URL url = new URL(apiURL);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 전송방식
			con.setRequestMethod("POST");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			con.setRequestProperty("Content-Type", "application/json");

			// post request
			String postParams = "content" + content;

			// urlconnection 이 서버에 데이터를 보내는데 사용할 수 있는지 여부를 설정한다.
			con.setDoOutput(true);
			// urlconnection 서버에서 콘텐츠를 읽는데 사용할 수 있는지 여부를 설정한다.
			con.setDoInput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);

			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 오류발생
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response.toString();
	}

}
