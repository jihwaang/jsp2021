<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<% 
	int result = 13;
	// request / response
	// pageContext : 현재 페이지 / request : 두 서블릿 간의 저장소 / session : 사용자별 저장소 / application : 전역 저장소
	// out : 문자열 출력 도구
%>
<%! 
	public int add(int x, int y){
	return x+y;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
	<h1>계산기</h1>
		<div>
			<form action="/add" method="post">
				<input text="text" name="x">+<input type="text" name="y"><br>
				<input type="submit" value="덧셈"><span><%=result%></span>
			</form>
		</div>
	</section>
</body>
</html>