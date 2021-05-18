package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Nana extends HttpServlet{
    public void service(HttpServletRequest request, 
			HttpServletResponse response) 
			throws IOException, ServletException {
    	PrintWriter out = response.getWriter();
    	int count = 10;
    	String c = request.getParameter("c");
    	if(c != null && !c.isBlank())
    		count = Integer.parseInt(c);
    	
    	for(int i = 0; i < count; i++)
    		out.println("hello Servlet");
    }
}