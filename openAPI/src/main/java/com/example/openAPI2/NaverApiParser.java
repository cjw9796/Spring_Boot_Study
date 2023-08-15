package com.example.openAPI2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NaverApiParser {

	private static String removeTag(String text) {
		if (text == null || text.length() == 0) {
			return text;
		}
		text = text.replaceAll("<br>", "\n");
		text = text.replaceAll("&gt;", ">");
		text = text.replaceAll("&lt;", "<");
		text = text.replaceAll("&quot;", "");
		text = text.replaceAll("&nbsp;", " ");
		text = text.replaceAll("&apos;", "'");

		return text.replaceAll("<(/)?([a-zA-Z])(\\s[a-zA-Z]=[^>])?(\\s)(/)?>", "");

	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

	public static Date toDate(JSONObject object, String tag) {
		try {
			return sdf.parse((String) object.get(tag));

		} catch (Exception e) {
			return null;
		}

	}

	public static String toString(JSONObject object, String tag, boolean isRemoveTag) {
		// 문자에 특수문자들(태그)이 들어있는 경우는 true false 이용해서 데이터 안에 특수문자들을
		// 제거하는 함수를 따로 실행해야되서 매개변수를 하나 더 주었다.
		String text = toString(object, tag);

		if (isRemoveTag == true && text != null && text.length() > 0) {
			return removeTag(text);
		}
		return text;

	}

	public static String toString(JSONObject object, String tag) {
		try {
			return (String) object.get(tag);
		} catch (Exception e) {
			return "-";
		}
	}

	public static int toInt(JSONObject object, String tag) {

		try {

			return Integer.parseInt((String) object.get(tag));
		} catch (Exception e) {
			return 0;
		}

	}

	// api에서 가져온 문자열 데이터를 json타입으로 파싱하는 메서드
	public static List<Product> parseShop(String json) throws ParseException {

		List<Product> list = new ArrayList();
		// jsonparser 객체 생성
		// json데이터를 파싱하는 java의 클래스입니다.
		// json데이터를 파싱하여 객체나 배열로 변환하기 위해서 jsonparser 객체를 사용할 수
		// 있습니다~

		// parse(): json 문자열을 파싱하여 jsonObject또는 jsonArray객체로 변환한다.

		JSONParser jsonParser = new JSONParser();
		JSONObject rootObj = (JSONObject) jsonParser.parse(json);
		// 여러가지 상품이 오는데 배열형태로 저장을 해야된다
		JSONArray array = (JSONArray) rootObj.get("items");

		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = (JSONObject) array.get(i);
			String title = toString(obj, "title");
			String link = toString(obj, "link");
			String image = toString(obj, "image");
			int lprice = toInt(obj, "lprice");
			int hprice = toInt(obj, "hprice");
			String mallName = toString(obj, "mallName");
			String productId = toString(obj, "productId");
			String productType = toString(obj, "productType");
			String brand = toString(obj, "brand");
			String maker = toString(obj, "maker");
			String category1 = toString(obj, "category1");
			String category2 = toString(obj, "category2");
			String category3 = toString(obj, "category3");
			String category4 = toString(obj, "category4");

			Product product = new Product(title, link, image, lprice, hprice, mallName, productId, productType, brand,
					maker, category1, category2, category3, category4);

			list.add(product);

		}

		return list;
	}
}