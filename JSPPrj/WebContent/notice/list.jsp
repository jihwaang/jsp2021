<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.newlecture.web.service.MemberService"%>
<%@page import="com.newlecture.web.entity.Member"%>
<%@page import="com.newlecture.web.service.NoticeService"%>
<%@page import="com.newlecture.web.entity.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String p = request.getParameter("p");
String category = request.getParameter("category");
if(category == null || category.isBlank()){
	category = "title";
}
String keyword = request.getParameter("keyword");
if(keyword == null || keyword.isBlank()){
	keyword = "";
}
int pg = 1;
if(null != p && !"".equals(p)){
	pg = Integer.parseInt(p);
}
System.out.println(">> input page : " + pg);
System.out.println(">> input category : " + category);
System.out.println(">> input keyword : " + keyword);

MemberService memberService = new MemberService();
NoticeService noticeService = new NoticeService();
List<Member> list = memberService.getList();
List<Notice> noticeList = noticeService.getList(pg, category, keyword);	
//List<Map<String, Object>> noticeList = noticeService.getList(pg, category, keyword);	
//System.out.println(list);
Map<String, Object> resultCount = noticeService.getCount(category, keyword);
int count = Integer.parseInt(resultCount.get("COUNT").toString());
int lastPage = (count / 10) + ((count % 10) == 0 ? 0: 1);
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="../css/style.css" type="text/css" rel="stylesheet" />
    <script defer src="../js/list.js"></script>
  </head>

  <body>
    <div id="root">
      <header id="header">
        <div class="float-content">
          <h1 class="logo">
            <img src="../images/logo.png" alt="뉴렉처 온라인" />
          </h1>
          <section>
            <h1 class="d-none">헤더</h1>
            <nav class="main-menu">
              <h1 class="d-none">메인메뉴</h1>
              <ul>
                <li><a class="redsun" href="" dir="ltr">학습가이드</a></li>
                <li><a href="">강좌선택</a></li>
                <li><a href="">AnswerIs</a></li>
              </ul>
            </nav>

            <section class="lecture-search-form">
              <h1 class="d-none">강좌검색폼</h1>
              <form>
                <fieldset>
                  <legend class="d-none">과정 검색 필드</legend>
                  <label>과정검색</label>
                  <input type="text" id="a1" value="테스트" />
                  <input class="button" type="submit" value="검색" />
                </fieldset>
              </form>
            </section>

            <nav class="member-menu">
              <h1 class="d-none">회원메뉴</h1>
              <ul>
                <li><a href="index.html">HOME</a></li>
                <li><a href="member/login.html">로그인</a></li>
                <li><a href="">회원가입</a></li>
              </ul>
            </nav>

            <nav class="customer-menu">
              <h1 class="d-none">고객센터메뉴</h1>
              <ul>
                <li><a class="button mypage-button" href="">마이페이지</a></li>
                <li><a class="button customer-button" href="">고객센터</a></li>
              </ul>
            </nav>
          </section>
        </div>
      </header>

      <div id="visual">
        <div class="float-content"></div>
      </div>

      <div id="body">
        <div class="float-content">
          <aside id="aside">
            <h1 id="aside-title">
              고객센터
            </h1>
            <!-- <script>
              const html = '<span>haha</span>';
              asideTitle.innerHTML += html;
            </script> -->
            <nav class="main-aside-menu">
              <h1>고객센터메뉴</h1>
              <ul>
                <li><a class="current" href="list.html">공지사항</a></li>
                <li><a href="">자주하는 질문</a></li>
                <li><a href="">수강문의</a></li>
                <li><a href="">이벤트</a></li>
              </ul>
            </nav>

            <nav class="aside-menu recommend-aside-menu">
              <h1>협력업체</h1>
              <ul>
                <li><a class="img img-notepubs" href="">노트펍스</a></li>
                <li><a class="img img-namoolab" href="">나무랩연구소</a></li>
              </ul>
            </nav>
          </aside>
          <main id="main">
            <section class="notice">
              <h1 class="main-title">공지사항</h1>
              <%-- <% for(int i = 0; i < 5; i++) {%>
              <div>hello</div>
              <%} %> --%>
              <section class="breadcrumb mt-3">
                <h1 class="d-none">경로</h1>
                <ol>
                  <li>home</li>
                  <li>고객센터</li>
                  <li>공지사항</li>
                </ol>
              </section>
              <section class="search-form">
                <h1>검색폼</h1>
                <form action="list.jsp" method="GET">
                  <label class="d-none">검색분류</label>
                  <%
                  	String selectedTitle = "";
                  	String selectedWriterId = "";
                  	
                  	if ("title".equals(category))
                  		selectedTitle = "selected";
                  	
                  	if ("writer_id".equals(category))
                  		selectedWriterId = "selected";
                  %>
                  <select name="category">
                  	<option value="">분류선택</option>
                  	<option <%=selectedTitle %> value="title">제목</option>
                  	<option <%=selectedWriterId %> value="writer_id">작성자</option>
                  </select>
                  <label class="d-none">검색어</label>
                  <input type="text" name="keyword" value="<%=keyword%>">
                  <input type="submit" value="검색">
                </form>
              </section>
              <section class="mt-3">
                <h1 class="d-none">공지사항 목록</h1>
                <table class="table notice-table">
                  <thead>
                    <tr>
                      <td class="w-1">번호</td>
                      <td class="truncate text-align-center">제목</td>
                      <td class="w-2">작성자</td>
                      <td class="w-2">작성일</td>
                      <td class="w-1">조회수</td>
                    </tr>
                  </thead>
                  <tbody>
                  <%-- <% for(Map<String, Object> row : noticeList) {%> --%>
                  <% for(Notice row : noticeList) {%>
                    <tr>
                      <td class="w-1"><%= row.getId() %></td>
                      <td class="truncate text-align-left">
                        <a href="detail.jsp?id=<%= row.getId() %>"><%=row.getTitle() %>[20]</a>
                      </td>
                      <td class="w-2"><%= row.getWriterId()%></td>
                      <td class="w-2"><%= row.getRegDate() %></td>
                      <td class="w-1"><%= row.getHit() %></td>
                    </tr>
                    <%-- <tr>
                      <td class="w-1"><%= row.get("ID") %></td>
                      <td class="truncate text-align-left">
                        <a href="#"><%=row.get("TITLE") %></a>
                      </td>
                      <td class="w-2"><%= row.get("WRITER_ID")%></td>
                      <td class="w-2"><%= row.get("REGDATE") %></td>
                      <td class="w-1"><%= row.get("HIT") %></td>
                    </tr> --%>
                    <%} %>
                    <!-- <tr>
                      <td>1023</td>
                      <td class="truncate text-align-left">
                        스프링 JSON 출력을 위한 라이브러리 설정
                        aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                      </td>
                      <td>newlec</td>
                      <td>2020-11-29</td>
                      <td>994</td>
                    </tr>
                    <tr>
                      <td>1022</td>
                      <td class="truncate text-align-left">
                        스프링 강좌 <kbd>Ctrl</kbd>+<kbd>Shift</kbd>
                        RestController 한글 깨짐 설정 코드
                      </td>
                      <td>newlec</td>
                      <td>2020-11-25</td>
                      <td>802</td>
                    </tr> -->
                  </tbody>
                </table>
                <div>
                	<a href="reg.jsp">글쓰기</a>
                </div>
              </section>

              <section class="page-status mt-3">
                <h1 class="d-none">현재 페이지 정보</h1>
                <div><span class="text-strong"><%=pg %></span> / <%=lastPage %> pages</div>
              </section>
				<%
					String current = "";
				%>
              <nav class="pager-nav mt-3">
                <h1 class="d-none">페이저</h1>
                <div class="pager-prev"></div>
                <ul class="pages">
                <% for(int i = 1; i <= lastPage; i++) {%>
                	<li><a class="<%=pg == i ? "text-strong" : ""%>" href="list.jsp?p=<%=i %>&category=<%=category%>&keyword=<%=keyword%>"><%=i %></a></li>
                <%} %>
                  <%-- <li><a href="list.jsp?p=2&category=<%=category%>&keyword=<%=keyword%>" class="text-strong">1</a></li>
                  <li><a href="list.jsp?p=2&category=<%=category%>&keyword=<%=keyword%>">2</a></li>
                  <li><a href="list.jsp?p=3&category=<%=category%>&keyword=<%=keyword%>">3</a></li>
                  <li><a href="list.jsp?p=4&category=<%=category%>&keyword=<%=keyword%>">4</a></li>
                  <li><a href="list.jsp?p=5&category=<%=category%>&keyword=<%=keyword%>">5</a></li> --%>
                </ul>
                <div class="pager-next"></div>
              </nav>
            </section>
          </main>
        </div>
      </div>

      <footer id="footer">
        <div class="float-content">
          <!-- 회사정보
        Copyright ⓒ newlecture.com 2012-2014 All Right Reserved. Contact admin@newlecture.com for more information -->
        </div>
      </footer>
    </div>
  </body>
</html>
