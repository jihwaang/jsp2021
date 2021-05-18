package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

@WebServlet("/add")
public class Calc extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get request");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		Optional<String> x = Optional.ofNullable(req.getParameter("x"));
		Optional<String> y = Optional.ofNullable(req.getParameter("y"));
		
		int[] numberParams = parseInt(x.orElse("0"), y.orElse("0"));
		
		int result = 0;
		String computeHistory = "";
		
		result = sum(numberParams);
		//computeHistory = getCalcHist(numberParams);
		if(!x.isPresent() && !y.isPresent()) {
			if(null != req.getParameter("result")) result = Integer.parseInt(req.getParameter("result"));
			if(null != req.getParameter("inputX")) x = Optional.of(req.getParameter("inputX"));
			if(null != req.getParameter("inputY")) y = Optional.of(req.getParameter("inputY"));			
			numberParams = parseInt(x.orElse("0"), y.orElse("0"));
			computeHistory = getCalcHist(numberParams);
		}
		
		
		StringBuilder template = new StringBuilder();
		template.append("<html>");
		template.append("<meta charset=\"UTF-8\">");
		template.append("<head>");
		template.append("<title>Insert title here</title>");
		template.append("</head>");
		template.append("<body>");
		template.append("<section>");
		template.append("<h1>계산기</h1>");
		template.append("<div>");
		if(!"0".equals(x.get())&&!"0".equals(y.get()))
			template.append(computeHistory);
		template.append("<form action=\"/add\" method=\"post\">");
		template.append("<input text=\"text\" name=\"x\">+<input type=\"text\" name=\"y\"><br>");
		template.append("<input type=\"submit\" value=\"덧셈\"><span>"+result+"</span>");
		template.append("</form>");
		template.append("</div>");
		template.append("</section>");
		template.append("</body>");
		template.append("</html>");
		out.write(template.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post request");
		Optional<String> x = Optional.ofNullable(req.getParameter("x"));
		Optional<String> y = Optional.ofNullable(req.getParameter("y"));
		
		int[] numberParams = parseInt(x.orElse("0"), y.orElse("0"));
		int result = 0;
		String computeHistory = "";
		
		result = sum(numberParams);
		computeHistory = getCalcHist(numberParams);
		
		resp.sendRedirect("/add?inputX="+x.orElse("0")+"&inputY="+y.orElse("0")+"&result="+result);
	}
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("text/html; charset=utf-8");
//		
//		PrintWriter out = resp.getWriter();
//		
//		Optional<String> x = Optional.ofNullable(req.getParameter("x"));
//		Optional<String> y = Optional.ofNullable(req.getParameter("y"));
//		
//		int[] numberParams = parseInt(x.orElse("0"), y.orElse("0"));
//		
//		int result = 0;
//		String computeHistory = "";
//		
//		if("POST".equals(req.getMethod())) {
//			System.out.println("execute calc here");
//			result = sum(numberParams);
//			computeHistory = getCalcHist(numberParams);
//		}
//		StringBuilder template = new StringBuilder();
//		template.append("<html>");
//		template.append("<meta charset=\"UTF-8\">");
//		template.append("<head>");
//		template.append("<title>Insert title here</title>");
//		template.append("</head>");
//		template.append("<body>");
//		template.append("<section>");
//		template.append("<h1>계산기</h1>");
//		template.append("<div>");
//		template.append("");
//		if("POST".equals(req.getMethod()))			
//			template.append(computeHistory);
//		template.append("<form action=\"/add\" method=\"post\">");
//		template.append("<input text=\"text\" name=\"x\">+<input type=\"text\" name=\"y\"><br>");
//		template.append("<input type=\"submit\" value=\"덧셈\"><span>"+result+"</span>");
//		template.append("</form>");
//		template.append("</div>");
//		template.append("</section>");
//		template.append("</body>");
//		template.append("</html>");
//		out.write(template.toString());
//	}
	
	public String getCalcHist(int[] numberParams) {
		StringBuilder params = new StringBuilder();
		params.append("<div><span>");
		
		int paramSize = numberParams.length;
		int idx = -1;
		
		for(int number : numberParams) {
			params.append(number);
			++idx;
			if(idx < paramSize-1) {
				params.append("+");				
			}
		}
		
		params.append("</span></div>");
		
		return params.toString();
	}
	
	public int[] parseInt(String...data) {
		int[] numberParams = new int[data.length];
		int idx = -1;
		for(String str : data) {
			if(null == str || str.isBlank()) str = "0";
			
			if(str.matches("^[0-9]*$")) numberParams[++idx] = Integer.parseInt(str);			
		}
		return numberParams;
	}
	
	public int sum(int[] numberParams) {
		int result = 0;
		for(int number : numberParams) 
				result += number;
		return result;
	}
}
