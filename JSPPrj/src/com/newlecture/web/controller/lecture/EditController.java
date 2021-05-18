package com.newlecture.web.controller.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/edit")
public class EditController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		System.out.println(">> id : " + id+ "\n>> title : " + title + "\n>> content : " + content);
		
		NoticeService service = new NoticeService();
		
		//service.update(id, title, content);
		
		Notice notice = service.get(id);
		notice.setId(id);
		notice.setTitle(title);
		notice.setContent(content);
		service.update(notice);
		resp.sendRedirect("detail.jsp?id="+id);
	}
}
