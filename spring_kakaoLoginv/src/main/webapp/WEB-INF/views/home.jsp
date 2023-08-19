<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<!-- 카카오 로그인 
	
		1. 로그인( 클라이언트 쪽에서 로그인을 한다)
		2. 카카오서버가 redirect_url로 code를 전달한다. 인가코드
		3. 실제 접속해서 정보를 가지고 오는 토큰키를 발급받는다. 
		4. access_token을 서버로 전송한다.
		5. 서버에서 access_token이용해서 카카오 서버에서 사용자 정보를 받는다.
		6. 받은 사용자 정보를 이용해서 회원가입 또는 로그인을 진행한다. 
		
		본인코드로 작성 client_id=restAPI 절대 띄어쓰지 하지마세요~오류납니다!  
	 -->
	<a class="p-2" href="https://kauth.kakao.com/oauth/authorize?client_id=f5f9c8fe351b7a623b6cf2884d8ff60e&redirect_uri=http://localhost:9060/mvc/login&response_type=code">                 
		
		<img src="./resources/images/kakao_login_medium_narrow_login.png" style="height:50px">
	
	</a>
	
	

</body>
</html>
