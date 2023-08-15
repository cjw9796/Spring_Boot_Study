package com.example.openAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


// 네이버 파파고API 예제 
public class PapagoAPI {
	
	 public static void main(String[] args) {
	        String clientId = "jN5Ym07IaQ9vBFMJRp42";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "ouJuE3moAB";//애플리케이션 클라이언트 시크릿값";

	        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	        String text;
	        try {
	            text = URLEncoder.encode("안녕하세요. 오늘 기분은 어떻습니까?", "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("인코딩 실패", e);
	        }

	        Map<String, String> requestHeaders = new HashMap<>();
	        requestHeaders.put("X-Naver-Client-Id", clientId);
	        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

	        String responseBody = post(apiURL, requestHeaders, text);

	        System.out.println(responseBody);
	        
	        // json 데이터 파싱 
	        
	        JSONObject jObject = new JSONObject(responseBody);

	        // "message" 필드에서 "result" 객체 가져오기
	        JSONObject resultObject = jObject.getJSONObject("message").getJSONObject("result");

	        String srcLangType = resultObject.getString("srcLangType");
	        String tarLangType = resultObject.getString("tarLangType");
	        String translatedText = resultObject.getString("translatedText");
	        String engineType = resultObject.getString("engineType");

			
	        System.out.println("srcLangType: " + srcLangType);
	        System.out.println("tarLangType: " + tarLangType);
	        System.out.println("translatedText: " + translatedText);
	        System.out.println("engineType: " + engineType);
			
			
			
	        
	    }

	    private static String post(String apiUrl, Map<String, String> requestHeaders, String text){
	        HttpURLConnection con = connect(apiUrl);
	        String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
	        try {
	            con.setRequestMethod("POST");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            con.setDoOutput(true);
	            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
	                wr.write(postParams.getBytes());
	                wr.flush();
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
	                return readBody(con.getInputStream());
	            } else {  // 에러 응답
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	    private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }

	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();

	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }

	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }

}
