package com.newlecture.web.controller.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/reg")
public class RegController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		System.out.println(">> title : " + title + "\n>> content : " + content);
		
		NoticeService service = new NoticeService();
		
		//service.update(id, title, content);
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setWriterId("jihwaang");
		service.insert(notice);
		//service.insert(title, content);
		resp.sendRedirect("list.jsp");
	}
}
