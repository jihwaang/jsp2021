package com.newlecture.web.controller.lecture;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/del")
public class DelController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(">> request id : " + id);
		
		NoticeService service = new NoticeService();
		
		//service.update(id, title, content);
		service.delete(id);
		//service.insert(title, content);
		resp.sendRedirect("list.jsp");
	}
}
